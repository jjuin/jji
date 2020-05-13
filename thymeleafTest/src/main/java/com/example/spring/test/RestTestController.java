package com.example.spring.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

	@RequestMapping(value = "/restTest", method = RequestMethod.GET)
	public String test() {
		return "welcome :)";
	}
	
	@RequestMapping(value = "/restTest2", method = RequestMethod.GET)
	public String test2(String user) {
		return "welcome"+user;
	}
	
	@RequestMapping(value = "/restTest3", method = RequestMethod.GET)
	public BookDTO test3() {
		BookDTO dto = new BookDTO();
		dto.setBook_no(1);
		dto.setTitle("책 제목");
		dto.setCreator("책 저자");
		dto.setPublisher("출판사");
		dto.setPublishedYear(2020);
		
		return dto;
	}
}
