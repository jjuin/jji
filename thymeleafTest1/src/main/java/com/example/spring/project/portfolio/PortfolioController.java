package com.example.spring.project.portfolio;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring.project.ProjEmpDTO;
import com.example.spring.project.ProjectController;
import com.example.spring.project.util.MediaUtils;
import com.example.spring.project.util.UploadService;
import com.example.spring.user.UserTestController;

@Controller
@RequestMapping("/project/portfolio/*")
public class PortfolioController {
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	PortfolioService portfolioService;
	
	String uploadPath = "C:\\Users\\eriel\\Documents\\projectUpload"; //경로설정
	
	//int emp_no = 0; //사원번호 임시변수
	
	//int add_good_cnt = 0; //좋아요 수 임시변수
	
	//포트폴리오 메인
	@RequestMapping(value = "portfolioForm", method = RequestMethod.GET)
	public String portfolioForm(Model model) {
		
		//사원정보 불러오기
		List<PortfolioDTO> list = portfolioService.portfolioInfo();
		if(list.size() != 0) {
			LOGGER.info("list--->"+list.get(0).toString());
			model.addAttribute("list", list);
			model.addAttribute("sessionID", ProjectController.sessionID);
			model.addAttribute("cus_name", ProjectController.cus_name);
		}
		return "project/portfolio/portfolioForm";
	}
	
	//좋아요수 증가
	@ResponseBody
	@RequestMapping(value = "portfolioAddGood", method = RequestMethod.POST)
	public String portfolioAddGood(int portfolio_no, HttpSession session) {
		String result = "false";
		if(!("".equals(session.getAttribute("cus_id")) || null == session.getAttribute("cus_id"))){
			portfolioService.portfolioAddGood(portfolio_no); 
			
			result = "true";
		} 
		return result;
		//return "redirect:/project/portfolio/portfolioForm";
	}
	
	//포트폴리오 작성 폼
	@RequestMapping(value = "portfolioWrite", method = RequestMethod.GET)
	public String portfolioWrite(Model model, ProjEmpDTO empDTO) {
		
		//디자이너 정보 불러오기
		List<ProjEmpDTO> empList = portfolioService.designerInfo();
		
		model.addAttribute("empList", empList);
		return "project/portfolio/portfolioWrite";
	}
	
	//파일업로드 ajax
	@ResponseBody
	@RequestMapping(value = "fileUpload", method = RequestMethod.POST, produces = "text/plain;charset=utf-8") //한글처리
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		return new ResponseEntity<String>(UploadService.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
	}
	
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
			
		}catch(Exception e) {
			e.printStackTrace();
			//entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();  //스트림 닫기
		}
		return entity;
	}
	
	//파일저장&포트폴리오 저장
	//게시글 작성처리
    @RequestMapping(value = "portfolioInsert", method = RequestMethod.POST)
    public String portfolioInsert(@ModelAttribute PortfolioDTO portfolioDTO) throws Exception{
    	LOGGER.info("file저장 controller");
    	
    	//포트폴리오 저장
    	LOGGER.info("portfolio-->"+portfolioDTO.getEmp_no());
    	portfolioService.portfolioInsert(portfolioDTO);
    	
    	//파일저장  -> Service에서 처리
    	//LOGGER.info("uploadDTO.getFiles->"+portfolioDTO.getFiles());
    	//portfolioService.fileInsert(portfolioDTO);
    	
    	return "redirect:/project/portfolio/portfolioForm";
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
