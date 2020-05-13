package com.example.spring.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring.user.UserTestController;
import com.example.spring.util.PageNavigator;

@Repository
public class BoardDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<BoardDTO> listAll(PageNavigator navi) {
		List<BoardDTO> board_list = new ArrayList<BoardDTO>(); 
		
		try {
			RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
			board_list = sqlSession.selectList("com.example.spring.board.BoardDAO.listAll", rb);
			LOGGER.info("board_list => "+board_list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
			return board_list;
		}
		return board_list;
		//return sqlSession.selectList("com.example.spring.board.BoardDAO.listAll");
	}

	//전체 글 수 조회
	public int selectCount() {
		
		LOGGER.info("service- selectCount()--------");
		
		return sqlSession.selectOne("com.example.spring.board.BoardDAO.selectCount");
	}
}
