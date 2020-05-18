package com.example.spring.project.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.project.ProjEmpDTO;

@Service
public class ProjBookService {

	@Autowired
	ProjBookDAO bookDAO;
	
	
	public List<ProjBookDTO> bookInfo(String cus_id) {
		
		return bookDAO.bookInfo(cus_id);
	}

	public void bookInsert(ProjBookDTO bookDTO) { //예약하기

		bookDAO.bookInsert(bookDTO); //예약처리
		bookDAO.work_time_isshow(bookDTO); //사원별 시간표 업데이트
	}

	public List<ProjBookDTO> empWorkTime(int cus_no) { //직워별 시간대 가져오기
		
		return bookDAO.empWorkTime(cus_no);
	}

	public List<ProjEmpDTO> empInfo() { //직원정보 가져오기
		
		return bookDAO.empInfo();
	}
	
	
}
