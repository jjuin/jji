package com.example.spring.project.book;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring.project.ProjEmpDTO;

@Repository
public class ProjBookDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession; 

	//예약정보 가져오기
	public List<ProjBookDTO> bookInfo(String cus_id) {

		return sqlSession.selectList("com.example.spring.project.book.ProjBookDAO.selectBookInfo", cus_id);
	}

	//예약하기
	public void bookInsert(ProjBookDTO bookDTO) {
		
		sqlSession.insert("com.example.spring.project.book.ProjBookDAO.bookInsert", bookDTO);
	}
	
	//시간표 업데이트
	public void work_time_isshow(ProjBookDTO bookDTO) {
		sqlSession.update("com.example.spring.project.book.ProjBookDAO.updateWorkTime", bookDTO);
	}

	//시간표 가져오기
	public List<ProjBookDTO> empWorkTime(int cus_no) {
		return sqlSession.selectList("com.example.spring.project.book.ProjBookDAO.selectEmpWorkTime", cus_no);
	}

	//직원정보 가져오기
	public List<ProjEmpDTO> empInfo() {
		return sqlSession.selectList("com.example.spring.project.book.ProjBookDAO.selectEmpInfo");
	}



}
