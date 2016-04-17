package com.chungli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.chungli.dto.UserProfile;
import com.chungli.dto.UserProfileExample;
import com.chungli.dto.UserProfileExample.Criteria;
import com.chungli.mapper.UserProfileMapper;


@Service
public class LoginService {
   
	@Autowired
	private UserProfileMapper userMapper ;
	
	
	@Transactional(rollbackFor=Exception.class)
	public UserProfile loginUser(String email,String pwd) throws Exception{
		UserProfile user  = null ;
		UserProfileExample example = new UserProfileExample();
		Criteria c = example.createCriteria() ;
		c.andEmailEqualTo(email);
		c.andPasswordEqualTo(pwd);
		
		List<UserProfile> userList = userMapper.selectByExample(example);
		if (userList != null && !userList.isEmpty()) {
			user = userList.get(0);
		}
		return user ;
	}
	
	
}
