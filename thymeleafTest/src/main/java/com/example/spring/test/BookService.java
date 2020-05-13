package com.example.spring.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	BookDAO BookDAO;

	public void createBook(BookDTO dto) {
		BookDAO.createBook(dto);
	}
	
	public List<BookDTO> readBookAll() {
		return BookDAO.readBookAll();
	}

	

}
