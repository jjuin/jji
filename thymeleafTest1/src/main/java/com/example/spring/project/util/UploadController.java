package com.example.spring.project.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring.user.UserTestController;

@Controller  //RestController로 했을시 form화면이 나오지않음.
@RequestMapping("/project/util/*")
public class UploadController {
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	UploadService uploadService;
	
	String uploadPath = "C:\\Users\\eriel\\Documents\\projectUpload"; //경로설정
	
	@RequestMapping(value = "uploadForm", method = RequestMethod.GET)
	public String uploadForm() {
		return "project/util/uploadForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=utf-8") //한글처리
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		return new ResponseEntity<String>(UploadService.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=utf-8") //한글처리
//	public ResponseEntity<Map<String, Object>> uploadAjax(MultipartHttpServletRequest multipartRequest) throws Exception{
//		return new ResponseEntity<Map<String, Object>>(UploadService.uploadFile(multipartRequest), HttpStatus.OK);
//	
//	}
	
	//파일 데이터 브라우저 전송
    // 업로드 된 파일을 화면에 보여주기
	@ResponseBody
	@RequestMapping(value = "/displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String filename) throws Exception{
		System.out.println(filename+":fliename---00000");
		//서버의 파일을 다운로드 하기 위한 스트림
		InputStream in = null; 
		ResponseEntity<byte[]> entity = null;
		try {
			//확장자를 추출하여 formatName에 저장
			String formatName = filename.substring(filename.lastIndexOf(".")+1);
			//추출한 확장자를 이미지파일여부를 검사하고 리턴받아 mType에 저장
			MediaType mType = MediaUtils.getMediaType(formatName);
			//헤더 구성객체(외부에서 데이터를 주고받ㅇㄹ 때에는 header, body를 구성해야하기에)
			HttpHeaders headers = new HttpHeaders();
			// 실제 경로의 파일을 읽어들이고 InputStream 객체 생성
			in = new FileInputStream(uploadPath+filename);
			//이미지 파일이면
			if(mType != null) {
				headers.setContentType(mType);
				
			}
			//이미지가 아니면
			else {
				filename = filename.substring(filename.indexOf("_")+1);
				//다운로드용 컨텐트 타입
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				
				//바이트배열을 스트링으로 
				//파일의 한글깨짐 방지
				headers.add("Content-Disposition", "attachment; filename=\""+new String(filename.getBytes("utf-8"), "iso-8859-1")+"\""); 
			
			}
			//바이트 배열, 헤더, HTTP상태코드
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			//entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
	        //entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		}catch(Exception e) {
			e.printStackTrace();
			//entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();  //스트림 닫기
		}
		return entity;
	}
	
	//파일저장
	//게시글 작성처리
    @RequestMapping(value = "fileInsert", method = RequestMethod.POST)
    public String noticeInsert(@ModelAttribute UploadDTO uploadDTO) throws Exception{
    	LOGGER.info("file 저장 controller");
    	
    	uploadService.fileInsert(uploadDTO);
    	
    	return "redirect:/project/util/uploadForm";
    }
    
    //파일삭제
    @ResponseBody
    @RequestMapping(value = "fileDelete", method = RequestMethod.POST)
    public ResponseEntity<String> fileDelete(String filename) throws Exception {
       System.out.println("delete file : " + filename);
        // 파일 삭제
        removeFile(filename);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
    
    // 파일 삭제 메서드
    private void removeFile(String fileName) {
        // 파일 확장자 추출
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 파일 확장자를 통해 이미지 파일인지 판별
        MediaType mediaType = MediaUtils.getMediaType(formatName);
        // 이미지 파일일 경우, 원본파일 삭제
        if (mediaType != null) {
            // 원본 이미지의 경로 + 파일명 추출
            // 날짜 경로 추출
            String front = fileName.substring(0, 12);
            // UUID + 파일명 추출
            String end = fileName.substring(14);
            // 원본 이미지 파일 삭제(구분자 변환)
            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }
        // 파일 삭제(일반 파일 or 썸네일 이미지 파일 삭제)
        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
    }
	
}
