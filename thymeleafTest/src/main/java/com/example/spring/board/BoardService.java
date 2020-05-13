package com.example.spring.board;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.user.UserTestController;
import com.example.spring.util.PageNavigator;

@Service
public class BoardService {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	BoardDAO boardDAO;

	public List<BoardDTO> listAll(PageNavigator navi) throws Exception{
		return boardDAO.listAll(navi);
	}

	public int selectCount() throws Exception{
		
		LOGGER.info("service- selectCount()--------");
		
		return boardDAO.selectCount();
	}


}
