package com.example.spring.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spring.user.UserTestController;

@Controller
public class BookController {

	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/book/book1", method = RequestMethod.GET)
	public String book(Model model, BookDTO dto) {
		return "book/bookList";
	}
	
	@RequestMapping(value = "/book/book2", method = RequestMethod.POST)
	public String book2(Model model, BookDTO dto) {
		bookService.createBook(dto);
		LOGGER.info("createBook----");
		LOGGER.info("createBook----"+model.toString());
		LOGGER.info("createBook----"+dto.getCreator());

		List<BookDTO> bookList = bookService.readBookAll();
		
		model.addAttribute("bookMap",bookList);
		LOGGER.info("readBookAll----");
		LOGGER.info("readBookAll----"+bookList.toString());
		
		return "book/bookList :: #list";
		
	}
}
