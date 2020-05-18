package com.example.spring.project.book;


import java.util.List;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring.project.ProjEmpDTO;
import com.example.spring.project.ProjectController;
import com.example.spring.user.UserTestController;

@Controller
@RequestMapping("/project/book/*")
public class ProjBookController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	ProjBookService bookService;
	
	String cus_id = "";
	
	//예약하기 폼
	@RequestMapping(value = "bookForm", method = RequestMethod.GET)
	public String bookForm(Model model) {
		//디자이너 정보 가져오기
		List<ProjEmpDTO> empList =  bookService.empInfo(); 
		LOGGER.info("bookForm-list-->"+empList.toString());
		
		model.addAttribute("empList", empList);
		model.addAttribute("sessionID", ProjectController.sessionID);
		model.addAttribute("cus_name", ProjectController.cus_name);
		
		return "project/book/bookForm";  
	}
	
	//예약처리
	@ResponseBody
	@RequestMapping(value = "bookInsert", method=RequestMethod.POST)
	public String bookInsert(Model model, ProjBookDTO bookDTO) {
		LOGGER.info("bookDTO--->"+bookDTO.toString());
		
		bookService.bookInsert(bookDTO);
		return "true";
	}
	
	//예약정보 확인
	@RequestMapping(value = "bookInfo", method = RequestMethod.GET) //get방식..?
	public String bookInfo(Model model, HttpSession session) {
		
		cus_id = (String) session.getAttribute("cus_id");
		LOGGER.info("bookInfo-cus_id--->"+cus_id);
		
		List<ProjBookDTO> bookList = bookService.bookInfo(cus_id);
		LOGGER.info("bookList--->"+bookList.toString());
		
		model.addAttribute("bookList", bookList);
		
		return "project/book/bookInfo";
	}
	
	//직원의 일하는 시간대 가져오기
	@ResponseBody
	@RequestMapping(value = "empWorkTime", method=RequestMethod.POST)
	public List<ProjBookDTO> empWorkTime(Model model, int emp_no){
		//LOGGER.info("bookDTO-emp_no-->"+bookDTO.getEmp_no());
		LOGGER.info("emp_no-->"+emp_no);
		List<ProjBookDTO> list = bookService.empWorkTime(emp_no);
		LOGGER.info("list--->"+list.toString());
		
		//Map<String, Object> map = new HashMap<String, Object>();
		
		//map.put("list", list);
		
		model.addAttribute("list", list);
		return list;
	}

}
