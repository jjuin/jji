package com.example.spring.project.member;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.project.util.AES256Util;
import com.example.spring.project.util.SHA256Util;
import com.example.spring.user.UserTestController;

@Controller
@RequestMapping("/project/member/*")
public class ProjMemberController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);

	@Autowired
	ProjMemberService memberService;
	
	@Autowired
	AES256Util aesUtil;
	
	//로그인 폼페이지
	@RequestMapping("loginForm")
	public String loginForm() {
		return "project/member/loginForm";
	}
	
	//로그인 처리
	@ResponseBody
	@RequestMapping("loginProc")
	public ResponseEntity<String> loginProc(Model model, ProjMemberDTO memberDTO, HttpServletRequest request) {
		
		int login_result = 0; //로그인 비교 결과
		String aes_id = ""; //암호화 된 id
		String sha_pw = ""; //암호화 된 pw
		//String id = ""; //복호화된 id
		//String pw = ""; //복호화된 pw
		String cus_name = "";
		String result = "false"; //결과
		
		LOGGER.info("memberDTO-cus_id-->"+memberDTO.getCus_id());
		LOGGER.info("memberDTO-cus_pw-->"+memberDTO.getCus_pw());
		
		//db와 비교를 위해 암호화 시킨다.
		try {
			aes_id = aesUtil.encrypt(memberDTO.getCus_id());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		sha_pw = SHA256Util.getEncrypt(memberDTO.getCus_pw());
		
		//암호화 시킨 아이디와 비번을 DTO에 저장
		memberDTO.setCus_id(aes_id);
		memberDTO.setCus_pw(sha_pw);
		
		LOGGER.info("memberDTO-cus_id-->"+memberDTO.getCus_id());
		LOGGER.info("memberDTO-cus_pw-->"+memberDTO.getCus_pw());
		
		login_result = memberService.loginProc(memberDTO); //로그인시도
		
		LOGGER.info("memberDTO---->"+memberDTO.toString());
		
		HttpSession session = request.getSession();//세션 생성
		
		if(login_result == 1) { //로그인 성공
			
			memberDTO = memberService.loginSession(memberDTO.getCus_id());
			
			LOGGER.info("memberDTO 로그인 성공---->"+memberDTO.toString());
			
			cus_name = memberDTO.getCus_name();

			session.setAttribute("cus_id", aes_id); //암호화된 id세션저장
			session.setAttribute("cus_name", cus_name);
			session.setMaxInactiveInterval(60*60); //세션 1시간 유지
			
			result = "true";
		}
		
		return new ResponseEntity<String>(result, HttpStatus.OK); 
	}
	
	//인터셉터처리
	@RequestMapping("needLogin")
	public String needLogin(Model model) throws Exception{
		String login_interceptor = "로그인 후 이용해 주시기 바랍니다."; //로그인 인터셉터 처리
		//ModelAndView mav = new ModelAndView();
		model.addAttribute("msg", login_interceptor);
		
		return "project/member/";
	}
	
	//로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		memberService.logout(session);
		
		return "project/index";
	}
	
	//마이페이지
//	@RequestMapping("mypage")
//	public String mypage(Model model, MemberDTO memberDTO) {
//		//memberService.mypage()
//		
//		return "project/mypage";
//	}

}
