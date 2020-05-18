package com.example.spring.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
//@Component
public class UserDAO {
//
	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	public List<UserDTO> getUserInfo(){
		// userMapper 라는 부분과 5단계에 있는 mapper.xml 파일의 namespace를 동일하게 맞춰준다
        //.getUserInfo 와 5단계에 있는 <select id= 부분를 동일하게 맞춰준다.
        return sqlSession.selectList("com.example.spring.dao.UserDAO.selectUserList");
	}

	public void addUserInfo(UserDTO dto) {
		sqlSession.insert("com.example.spring.dao.UserDAO.addUserInfo", dto);
	}

}
