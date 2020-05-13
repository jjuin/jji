package com.example.spring.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring.user.UserTestController;
import com.example.spring.util.PageNavigator;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);

	private String session_id = "";
	
	
	//페이징 초기화
	final int COUNTPERPAGE = 5;
	final int PAGEPERGROUP = 3;

//	
	//게시글 목록
	@RequestMapping("list")
	public String list(@RequestParam(value = "page", defaultValue = "1") int page, HttpSession session, Model model) throws Exception{
		//세션 확인
		session_id = (String) session.getAttribute("cus_id");
		//cus_id = (String) session.getAttribute("cus_id");
		
		LOGGER.info("session_id=>"+session_id);
		
		
		if(session_id == null) { //세션 끊어졋을지
			session_id = "";
		}
		
		if(session_id != null || session_id != "") { //세션이 존재할때
			session.setAttribute("session_id", session_id);
		}
		
		PageNavigator navi = new PageNavigator(COUNTPERPAGE, PAGEPERGROUP, page, boardService.selectCount());
		
		LOGGER.info("startPageGroup->"+navi.getStartPageGroup());
		LOGGER.info("endPageGroup->"+navi.getEndPageGroup());
		
		List<BoardDTO> list = boardService.listAll(navi);
		
		
		model.addAttribute("list", list);
		model.addAttribute("navi", navi);
		model.addAttribute("session_id", session_id);
		
		return "board/list";
	}
	
}
