package com.example.spring.api;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate session;

	//회원 로그인 체크
	public int loginCheck(MemberDTO dto) {
		return session.selectOne("com.example.spring.api.MemberDAO.loginCheck", dto);
	}
	
	//회원 세션체크
	public MemberDTO loginSession(MemberDTO dto) {
		return session.selectOne("com.example.spring.api.MemberDAO.loginSession", dto);
	}
	 
	//로그인 히스토리 추가
	public void addLoginHistory(MemberDTO dto) {
		session.insert("com.example.spring.api.MemberDAO.insertHistory", dto);
	}

	//회원 로그아웃
	public void logout(HttpSession session) {
	}

}
