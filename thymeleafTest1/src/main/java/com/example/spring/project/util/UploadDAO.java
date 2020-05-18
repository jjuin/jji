package com.example.spring.project.util;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UploadDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession; 

	public void fileInsert(String file) {
		sqlSession.insert("com.example.spring.project.util.UploadDAO.fileInsert", file);
	}

}
