package com.example.spring.board;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	public int fileInsert(FileDTO file) {
		return sqlSession.selectOne("com.example.spring.board.FileDAO.fileInsert");
	}

	

}
