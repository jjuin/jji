package com.example.spring.project.join;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.project.member.ProjMemberDTO;
import com.example.spring.project.util.AES256Util;
import com.example.spring.project.util.SHA256Util;
import com.example.spring.user.UserTestController;

@Controller
@RequestMapping("/project/join/*")
public class JoinController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	JoinService joinService;
	
	@Autowired
	AES256Util aesUtil;
	
	//@Autowired
	//SHA256Util shaUtil;
	
	//String cus_id = ""; //중복확인 위한 id
	String phone = ""; //뷰에서 받아온 phone값 합치기
	String email = ""; //뷰에서 받아온 eamil값 합치기
	
	//암호화
	String id = ""; //암호화 전 아이디
	String pw = ""; //암호화 전 pw
	String aes_id = ""; //암호화 후 아이디
	String sha_pw = ""; //암호화 후 pw
	
	@RequestMapping(value = "joinForm", method = RequestMethod.GET)
	public String joinForm() { //회원가입 화면
		return "project/join/joinForm";
	}

	//@ResponseBody
	@RequestMapping(value = "joinProc", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinProc(Model model, ProjMemberDTO memberDTO) throws Exception{ //회원가입 처리
		LOGGER.info("memberDTO-cus_id--->"+memberDTO.getCus_id());  //정확하게 들어옴
		LOGGER.info("memberDTO-cus_pw--->"+memberDTO.getCus_pw());  //정확하게 들어옴
		LOGGER.info("memberDTO-cus_name--->"+memberDTO.getCus_name());  //정확하게 들어옴
		
		phone = memberDTO.getCus_phone1() + memberDTO.getCus_phone2() + memberDTO.getCus_phone3();
		email = memberDTO.getCus_email1() +"@"+ memberDTO.getCus_email2();

		memberDTO.setCus_email(email);
		memberDTO.setCus_phone(phone);
		
		//암호화
		id = memberDTO.getCus_id();
		pw = memberDTO.getCus_pw();
		aes_id = aesUtil.encrypt(id);
		sha_pw = SHA256Util.getEncrypt(pw);
		//암호화 된 아이디와 비번 DTO에 셋팅
		memberDTO.setCus_id(aes_id);
		memberDTO.setCus_pw(sha_pw);
		
		LOGGER.info("memberDTO--->"+memberDTO.toString());
		
		joinService.joinInsert(memberDTO);
		
		LOGGER.info("joinInsert지나감.");
		
		return "project/join/joinSuccess";
	}
	
	@ResponseBody
	@RequestMapping("joinIdCheck")
	public Boolean joinIdCheck(Model model, ProjMemberDTO memberDTO) throws Exception{ //회원가입 아이디 중복확인
		LOGGER.info("memberDTO-cus_id--->"+memberDTO.getCus_id());  //정확하게 들어옴
		//cus_id = memberDTO.getCus_id();
		
		if(joinService.joinIdCheck(memberDTO) == 1) { //해당 아이디가 존재하면
			return false;
		}
		
		return true;
	}
	
	
	//----------------------------------
	
	@RequestMapping("about")
	public String joinProc1() {
		return "project/join/about";
	}
	
	@RequestMapping("service")
	public String joinProc2() {
		return "project/join/service";
	}
}
