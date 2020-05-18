package com.example.spring.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserTestController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "hello")
    public void greet() {
        LOGGER.debug("Hello Debug level log");
        LOGGER.info("Hello Info level log");
        LOGGER.info("Hello!!!!!");

        LOGGER.error("Hello Error level log");
        
    }
	
	@RequestMapping("/user/info")
	public ModelAndView selectUser() throws Exception{
		
		List<UserDTO> userList = userService.getUserInfo(); 
		
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("userList", userList);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user_info");
		mav.addObject("userList", userList);
		//mav.addObject("map", map);
		//mav.addObject("userList", userList);
		
		return mav;
	}
	
	// 유저 추가 화면
	@RequestMapping("/user/addView")
	public String addView() throws Exception{
		
		return "user_addView";
	}
	
	//유저 추가
	@RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
	public String addUser(UserDTO dto) throws Exception{
		
		System.out.println("유저정보 확인"+ dto.getUser_id()+dto.getUser_name()+dto.getUser_pw());
		LOGGER.info("logger 유저정보 확인",dto.getUser_id());
	
		LOGGER.info(dto.getUser_name());
		LOGGER.info(dto.getUser_pw());
		
		userService.addUserInfo(dto);
		
		return "redirect:info";
		
	}
	
	 @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String init(){
        System.out.println("출력");
        return "index";
    }
	
}
