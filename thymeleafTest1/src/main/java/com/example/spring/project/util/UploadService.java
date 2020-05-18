package com.example.spring.project.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.spring.user.UserTestController;

@Service
public class UploadService {

	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	UploadDAO uploadDAO;
	
	//파일 업로드
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
		//uuid발급
		UUID uuid = UUID.randomUUID();
		//저장할 파일명 = UUID+_원본이름
		String savedName = uuid.toString()+"_"+originalName;
		
		//업로드할 디렉터리(날짜별 폴더)생성
		String savedPath = calcPath(uploadPath);
		//파일경로(기존의 업로드 경로+날짜별경로), 파일ㄹ명을 받아 객체 생성
		File target = new File(uploadPath + savedPath, savedName);
		
		//임시 디렉터리에 업로드된 파일을  지정된 디렉터리로 복사
		FileCopyUtils.copy(fileData, target);
		
		//썸네일을 생성하기 위한 파일의 확장자 검사
		//파일명의 aaa.bbb.ccc.jpg일 경우 마지막 마침표를 찾기 위해
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1); //확장자
		String uploadedfilename = null;
		
		//이미지 파일은 썸네일 사용
		if(MediaUtils.getMediaType(formatName) != null) {
			//썸네일 생성
			uploadedfilename = makeThumbnail(uploadPath, savedPath, savedName);
		}
			//나머지는 아이콘
		else {
			uploadedfilename = makeIcon(uploadPath, savedPath, savedName);
		}	
		//noticeService.addAttach(vo);
		return uploadedfilename;  //어디로 가는거?
	}
	
	
	
	
	//날짜별
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		//File.separator:디렉터리 구분자(\\)
		//예) \\2017
		String yearPath = File.separator +cal.get(Calendar.YEAR);
		//월  \\2019\\05
		String monthPath = yearPath+File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		//일
		String datePath = monthPath+File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		//디렉터리 생성 메소드 호출
		makeDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}

	//디렉터리 생성
	private static void makeDir(String uploadPath, String... paths) {
		//(날짜 경로)디렉토리 존재하면
		/*if(new File(uploadPath+paths[paths.length -1 ]).exists()) {
			return;
		}*/
		if(new File(paths[paths.length - 1]).exists()) {
			return;
		}
		//디렉터리가 존재하지 않으면
		for(String path:paths) {
			File dirPath = new File(uploadPath+path);
			//디렉터리가 존재하지 않으면
			if(!dirPath.exists()) {
				dirPath.mkdir();  //디렉터리 생성
			}
		}
	}
	
	//썸네일
	private static String makeThumbnail(String uploadPath, String path, String filename) throws Exception{
		//이미지를 읽기 위한 버퍼
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path, filename));
		//100픽셀 단위의 썸네일 생성
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 200);
		//썸네일의 이름 생성(원본파일명에 's_'붙임)
		String thumbnailName = uploadPath + path +File.separator + "s_" +filename;
		File newFile = new File(thumbnailName);
		String formatName = filename.substring(filename.lastIndexOf(".")+1);
		//썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		//썸네일의 이름을 리턴함
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	
	}

	//아이콘
	private static String makeIcon(String uploadPath, String path, String filename) throws Exception{
		//아이콘 이름
		String iconName = uploadPath+path+File.separator +filename;
		//아이콘 이름을 리턴
		//File.separatorChar : 구분자
		//원도우 \ , 유닉스(리눅스) /
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	//파일 저장
	@Transactional  //트랜잭션 처리 메서드로 설정
	public void fileInsert(UploadDTO uploadDTO) throws Exception{
    	LOGGER.info("file저장 service");

		//게시물의 첨부파일정보 등록
		String[] files = uploadDTO.getFiles(); //첨부파일 배열
		LOGGER.info("file길이-->"+files.length);
		
		//uploadDAO.fileInsert(uploadDTO);
		
		for(String file : files) {  //첨부파일 갯수만큼
			uploadDAO.fileInsert(file);
			//갯수알기 위한 번호얻기
			//noticeDAO.getNo(fullname);
		}
	}


	


}
