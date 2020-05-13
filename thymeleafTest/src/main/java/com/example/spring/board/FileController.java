package com.example.spring.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;

	@RequestMapping("/insertProc")
    private String dtoInsertProc(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception{
        
        FileDTO dto = new FileDTO();
        
        if(files.isEmpty()){ //업로드할 파일이 없을 시
        	//fileService.boardInsertService(board); //게시글 insert
        }else{
//        dto.setSubject(request.getParameter("subject"));
//        dto.setContent(request.getParameter("content"));
//        dto.setWriter(request.getParameter("writer"));
//        
//        String sourceFileName = files.getOriginalFilename(); 
//        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
//        File destinationFile; 
//        String destinationFileName;
//        String fileUrl = "uploadFiles 폴더 위치";
 
        String fileName = files.getOriginalFilename(); 
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
        File destinationFile; 
        String destinationFileName; 
        String fileUrl = "/Users/seowon/Documents/Workspace/MyProject/tistory/demo/src/main/webapp/WEB-INF/uploadFiles/";
        
        do { 
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension; 
            destinationFile = new File(fileUrl + destinationFileName); 
        } while (destinationFile.exists()); 
        
        destinationFile.getParentFile().mkdirs(); 
        files.transferTo(destinationFile); 
        
        dto.setBno(1);
        dto.setFileName(destinationFileName);
        dto.setFileOriName(fileName);
        dto.setFileUrl(fileUrl);
        
        fileService.fileInsertService(dto);
        
        
        }
        return "redirect:/list";
	}
}
