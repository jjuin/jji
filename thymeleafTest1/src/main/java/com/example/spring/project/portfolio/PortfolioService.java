package com.example.spring.project.portfolio;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.project.ProjEmpDTO;
import com.example.spring.project.util.UploadDTO;
import com.example.spring.user.UserTestController;

@Service
public class PortfolioService {
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	PortfolioDAO portfolioDAO;

	//포트폴리오 목록
	public List<PortfolioDTO> portfolioInfo() {
		
		return portfolioDAO.portfolioInfo();
	}

	//디자이너목록
	public List<ProjEmpDTO> designerInfo() {
		return portfolioDAO.designerInfo();
	}
	
	//포트폴리오 저장
	public void portfolioInsert(PortfolioDTO portfolioDTO) {
		LOGGER.info("portfolio저장 service");
		
		portfolioDAO.portfolioInsert(portfolioDTO);
		
		//파일저장
    	LOGGER.info("uploadDTO.getFiles->"+portfolioDTO.getFiles());
    	try {
			fileInsert(portfolioDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//파일저장
	//파일 저장
	@Transactional  //트랜잭션 처리 메서드로 설정
	public void fileInsert(PortfolioDTO portfolioDTO) throws Exception{
    	LOGGER.info("file저장 service");

		//게시물의 첨부파일정보 등록
		String file = portfolioDTO.getFiles(); //첨부파일 배열
		//LOGGER.info("file길이-->"+files.length);
		
		//uploadDAO.fileInsert(uploadDTO);
		//for(String file : files) {  //첨부파일 갯수만큼
			portfolioDAO.fileInsert(file);
			//갯수알기 위한 번호얻기
			//noticeDAO.getNo(fullname);
		//}
		
	}

	//좋아요 수 증가
	public void portfolioAddGood(int portfolio_no) {
		portfolioDAO.portfolioAddGood(portfolio_no);
		 
	}

	
}
