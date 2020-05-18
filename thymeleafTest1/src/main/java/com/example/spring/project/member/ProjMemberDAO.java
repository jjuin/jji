package com.example.spring.project.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjMemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession; 

	public int loginProc(ProjMemberDTO memberDTO) {
		
		return sqlSession.selectOne("com.example.spring.project.member.ProjMemberDAO.selectId", memberDTO);
	}

	public ProjMemberDTO loginSession(String cus_id) {
		
		return sqlSession.selectOne("com.example.spring.project.member.ProjMemberDAO.selectUser", cus_id);
	}

}
