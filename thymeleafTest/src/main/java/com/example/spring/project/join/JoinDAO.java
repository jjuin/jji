package com.example.spring.project.join;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring.project.member.ProjMemberDTO;
import com.example.spring.user.UserTestController;

@Repository
public class JoinDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);

	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	public int joinIdCheck(ProjMemberDTO memberDTO) {
		
		return sqlSession.selectOne("com.example.spring.project.join.JoinDAO.selectId", memberDTO);
	}

	public void joinInsert(ProjMemberDTO memberDTO) {

		LOGGER.info("joinInsert-DAO--");
		
		sqlSession.insert("com.example.spring.project.join.JoinDAO.insertUser", memberDTO);
	}

}
