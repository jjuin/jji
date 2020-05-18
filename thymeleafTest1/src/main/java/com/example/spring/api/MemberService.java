package com.example.spring.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
    MemberDAO memberDAO;

	//로그인 세션 체크
	public MemberDTO loginSession(MemberDTO dto) throws Exception{
		return memberDAO.loginSession(dto);	
	}
  
	//회원 로그아웃
	public void logout(HttpSession session) {
		//세션 변수 개별 삭제-> session.removeAttribute("cus_id");
		//세션 정보를 초기화
		session.invalidate();
	}
	
	//로그인 체크
	public int loginCheck(MemberDTO dto) throws Exception{
		return memberDAO.loginCheck(dto);	
	}

	//로그인history에 추가
	public void addLoginHistory(MemberDTO dto) {
		memberDAO.addLoginHistory(dto);
	}

}
