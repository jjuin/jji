package com.example.spring.project.portfolio;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring.project.ProjEmpDTO;
import com.example.spring.project.util.UploadDTO;

@Repository
public class PortfolioDAO {

	@Autowired
	private SqlSessionTemplate sqlSession; 

	//포트폴리오 목록
	public List<PortfolioDTO> portfolioInfo() {
		return sqlSession.selectList("com.example.spring.project.portfolio.PortfolioDAO.portfolioInfo");
	}
	
	//포트폴리오 상세
//	public List<PortfolioDTO> portfolioDetail(int emp_no) {
//		return sqlSession.selectList("com.example.spring.project.portfolio.PortfolioDAO.portfolioDetail", emp_no);
//	}
	
	//디자이너 정보
	public List<ProjEmpDTO> designerInfo() {
		return sqlSession.selectList("com.example.spring.project.portfolio.PortfolioDAO.designerInfo");
	}
	
	//포트폴리오 저장
	public void portfolioInsert(PortfolioDTO portfolioDTO) {
		sqlSession.insert("com.example.spring.project.portfolio.PortfolioDAO.portfolioInsert", portfolioDTO);
	}

	//파일 저장
	public void fileInsert(String file) {
		sqlSession.insert("com.example.spring.project.portfolio.PortfolioDAO.fileInsert", file);
	}

	public void portfolioAddGood(int portfolio_no) {
		sqlSession.update("com.example.spring.project.portfolio.PortfolioDAO.portfolioAddGood", portfolio_no);
	}

	

}
