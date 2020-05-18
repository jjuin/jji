package com.example.spring.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring.user.UserTestController;

@Controller
public class MemberController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);

	@Autowired
	MemberService memberService;
	
//	@Autowired
//    AES256Util aes; //암호화를 위한 util (양방향)
//	
//	@Autowired
//    SHA256Util sha; //암호화를 위한 util (단방향)
	
	//변수
	Integer ori_no = 0; //회원 pk값
	String id = "";//암호화 전 아이디
	String pw = "";//암호화 전 pw
	String aes_id = ""; //암호화 후 아이디
	String sha_pw = ""; //암호화 후 pw
//	String aes_dec_id= ""; //복호화 아이디
	int check = 1; //1로 초기화 member테이블에서 회원count
	String login_name = ""; //세션 이름저장
	String login_id = "";
	String session_id = "";
	String login_interceptor = "로그인 후 이용해 주시기 바랍니다.";
	
	@RequestMapping(value="/member/login", method = RequestMethod.GET)
	public String login() throws Exception{
		return "member/login";
	}
	
	//로그인 체크
	@RequestMapping(value="/member/loginCheck", method=RequestMethod.POST)
	public ResponseEntity<String> loginCheck(@ModelAttribute MemberDTO dto, HttpServletRequest request) throws Exception{
		System.out.println("loginCheck");
		id = dto.getCus_id();
		pw = dto.getCus_pw();
		//암호화부분
		System.out.println("암호화 전 아이디 : " + id);
	    System.out.println("암호화 전 비밀번호 : " + pw);
	    
//	    dto.setCus_id(id); 
//	    dto.setCus_pw(pw); 
        
		String result = "true";
		LOGGER.info("logincheck 전 =---!");
		check = memberService.loginCheck(dto);
		LOGGER.info("logincheck 후 ->"+check);
		
		if(check == 0) {
			result = "false"; //회원아님.
		}
		else { //회원이면
			System.out.println("회원이면------------");
			dto = memberService.loginSession(dto); //여기서 문제발생
			System.out.println("회원이면2------------");
			//login_name = memberService.loginSession(vo);
			login_name = dto.getCus_name();//member에서 회원이름 
			System.out.println("------"+login_name);
			
			ori_no = dto.getOri_no(); //회원키값 저장
			System.out.println("회원 key값------>"+ori_no);
			
			login_id = dto.getCus_id();
			
			memberService.addLoginHistory(dto); //로그인 history
			
			HttpSession session = request.getSession();//세션 생성
			
			System.out.println("???---------");
			session.setAttribute("cus_id", login_id);//세션 저장
			session.setAttribute("cus_name", login_name);//세션 저장
			session.setAttribute("ori_no", ori_no); //세션 저장
			session.setMaxInactiveInterval(60*10);//세션 10분유지
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	//인터셉터처리
	@RequestMapping("/member/needLogin")
	public String needLogin(Model model) throws Exception{
		//ModelAndView mav = new ModelAndView();
		model.addAttribute("msg", login_interceptor);
		
		return "/member/loginWarning";
	}
	
	//로그아웃 처리
	@RequestMapping("/member/logout")
	public ModelAndView logout(HttpSession session) throws Exception{
		memberService.logout(session);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/login");
		return mv;
	}
	
	
	//메인
	@RequestMapping("/member/main")
	public ModelAndView main(@ModelAttribute MemberDTO dto) throws Exception{
	
		System.out.println("vo-get------>"+dto.getCus_id());//암호전 아이디
		if(dto.getCus_id() != null) {
//			session_id = aes.encrypt(vo.getCus_id());
//			session.setAttribute("cus_id", session_id);
//			System.out.println("session-getId=>"+session.getAttribute("cus_id"));
		}
		
		// 로그인 확인을 위한 세션값
//		String UID =(String)session.getAttribute("cus_id");
		
		//HttpSession UID = request.getSession();
		//LOGGER.info("UID--->"+UID);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("UID", dto.getCus_id());
		mv.setViewName("member/main");
		
		return mv;
	}

}
