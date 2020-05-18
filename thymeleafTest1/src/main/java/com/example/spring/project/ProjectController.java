package com.example.spring.project;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.api.MemberDTO;
import com.example.spring.project.util.AES256Util;
import com.example.spring.user.UserTestController;

@Controller
@RequestMapping("/project/*")
public class ProjectController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	ProjectService service;
	
	@Autowired
	AES256Util aesUtil;
	
	private String aec_cus_id = ""; //암호화 된 아이디
	public static String cus_name = ""; //회원 이름
	public static String sessionID = ""; //세션 아이디

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(Model model, MemberDTO memberDTO, HttpSession session) {
		
		LOGGER.info("index-cus_id--->"+memberDTO.getCus_id()); //아이디 확인
		if(memberDTO.getCus_id() != null) { //로그인 되었다면
			try {
				aec_cus_id = aesUtil.encrypt(memberDTO.getCus_id()); //아이디 암호화
				cus_name = (String) session.getAttribute("cus_name"); //이름 지정
				session.setAttribute("cus_id", aec_cus_id); 
								
				LOGGER.info("index-cus_id 암호화--->"+session.getAttribute("cus_id")); //아이디 확인
				LOGGER.info("index-cus_name session--->"+cus_name); //아이디 확인

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}
		}
		
		sessionID = (String) session.getAttribute("cus_id");
		LOGGER.info("sessionID 확인--->"+sessionID); //아이디 확인
		LOGGER.info("cus_name 확인--->"+cus_name); //아이디 확인
		
		model.addAttribute("sessionID", sessionID);
		model.addAttribute("cus_name", cus_name);
		
		return "project/index";
	} 
}
