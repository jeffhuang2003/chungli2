package com.chungli.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;









import com.chungli.controller.LoginController;
import com.chungli.dto.UserProfile;
import com.chungli.dto.UserProfileExample;
import com.chungli.dto.UserProfileExample.Criteria;
import com.chungli.dto.UserRefence;
import com.chungli.dto.UserRefenceExample;
import com.chungli.mapper.UserProfileExtraMapper;
import com.chungli.mapper.UserProfileMapper;
import com.chungli.mapper.UserRefenceMapper;


@Service
public class UserRefenceService {
	private static Logger logger = Logger.getLogger(UserRefenceService.class);

	@Autowired
	private UserRefenceMapper userRefenceMapper ;
	
	@Transactional(rollbackFor=Exception.class)
	public UserRefence selectUserRefence(String userId) throws Exception{
		UserRefence userRefence  = null ;
		UserRefenceExample example = new UserRefenceExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<UserRefence> userRefenceList = userRefenceMapper.selectByExample(example);
		if (userRefenceList != null && !userRefenceList.isEmpty()) {
			userRefence = userRefenceList.get(0);
		}
		return userRefence ;
	}
	
	
}
