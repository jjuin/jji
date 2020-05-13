package com.example.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	
	@Autowired
	FileDAO fileDAO;

		public int fileInsertService(FileDTO file) throws Exception{
		    return fileDAO.fileInsert(file);
		}
	
}
