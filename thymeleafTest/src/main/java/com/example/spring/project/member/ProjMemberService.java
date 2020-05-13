package com.example.spring.project.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjMemberService {

	@Autowired
	ProjMemberDAO memberDAO;
	
	//로그인 처리
	public int loginProc(ProjMemberDTO memberDTO) {
		
		return memberDAO.loginProc(memberDTO);
	}

	//회원정보
	public ProjMemberDTO loginSession(String cus_id) {
		
		return memberDAO.loginSession(cus_id);
	}

	//로그아웃
	public void logout(HttpSession session) {
		//세션 정보 초기화
		session.invalidate();
	}
	
	//마이페이지
	

}
