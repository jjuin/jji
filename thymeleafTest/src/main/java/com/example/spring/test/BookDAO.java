package com.example.spring.test;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void createBook(BookDTO dto) {
		
		sqlSession.insert("com.example.spring.test.BookDAO.createBook", dto);
	
	}

	public List<BookDTO> readBookAll() {
		
		return sqlSession.selectList("com.example.spring.test.BookDAO.readBookAll");	
		
	}

}
