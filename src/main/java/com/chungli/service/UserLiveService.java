package com.chungli.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;














import com.chungli.controller.LoginController;
import com.chungli.dto.UserLive;
import com.chungli.dto.UserLiveExample;
import com.chungli.dto.UserLiveKey;
import com.chungli.dto.UserProfile;
import com.chungli.dto.UserProfileExample;
import com.chungli.dto.UserProfileExample.Criteria;
import com.chungli.dto.UserRefence;
import com.chungli.mapper.UserLiveMapper;
import com.chungli.mapper.UserProfileExtraMapper;
import com.chungli.mapper.UserProfileMapper;
import com.chungli.mapper.UserRefenceMapper;


@Service
public class UserLiveService {
	private static Logger logger = Logger.getLogger(UserLiveService.class);
	@Autowired
	private UserLiveMapper userLiveMapper ;

	
	@Transactional(rollbackFor=Exception.class)
	public List<UserLive> selectUserLiveList(String userId) throws Exception{
		List<UserLive> list = null ;
		UserLiveExample example = new UserLiveExample();
		example.createCriteria().andUserIdEqualTo(userId);
		list = userLiveMapper.selectByExample(example);
		return list ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public UserLive checkUserLiveId(String userLiveId ,String userId) throws Exception{
		UserLive userLive = null ;
		UserLiveKey key = new UserLiveKey();
		key.setUserId(userId);
		key.setUserLiveId(userLiveId);
		userLive = userLiveMapper.selectByPrimaryKey(key);
		return userLive ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertUserLive(UserLive userLive) throws Exception{
		int count = 0 ;
		count = userLiveMapper.insertSelective(userLive) ;
		if (count < 0) {
			throw new Exception();
		}
		return count ;
	}
}
