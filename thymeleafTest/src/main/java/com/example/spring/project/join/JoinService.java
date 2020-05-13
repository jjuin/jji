package com.example.spring.project.join;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.project.member.ProjMemberDTO;
import com.example.spring.user.UserTestController;

@Service
public class JoinService {
	
	private static final Logger LOGGER = LogManager.getLogger(UserTestController.class);
	
	@Autowired
	JoinDAO joinDAO;

	public int joinIdCheck(ProjMemberDTO memberDTO) { //중복확인 체크 아이디
		
		return joinDAO.joinIdCheck(memberDTO);
	}

	public void joinInsert(ProjMemberDTO memberDTO) { //회원가입
		
		LOGGER.info("joinInsert-Service--");

		joinDAO.joinInsert(memberDTO);
	}

}
