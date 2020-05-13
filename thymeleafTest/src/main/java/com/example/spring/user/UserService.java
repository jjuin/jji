package com.example.spring.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	public List<UserDTO> getUserInfo() {
		return userDAO.getUserInfo();
	}
	
	public void addUserInfo(UserDTO dto) {
		userDAO.addUserInfo(dto);
	}

}
