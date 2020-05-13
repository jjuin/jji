package com.example.spring.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxTestController {
	
	@ResponseBody
	@RequestMapping("/ajax")
	public String ajaxTest() {
		return "dddd";
	}
	
	@ResponseBody
	@RequestMapping("/ajax2")
	public Map<String, Object> ajaxTest2(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "victolee");
		map.put("age", 25);
		
		return map;
	}

}
