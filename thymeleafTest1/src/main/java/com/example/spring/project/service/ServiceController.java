package com.example.spring.project.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/service/*")
public class ServiceController {
	//service 메인
	@RequestMapping("serviceForm")
	public String serviceForm(Model model) {
		
		return "project/service/serviceForm";
	}
}
