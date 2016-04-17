package com.chungli.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chungli.dto.UserProfile;
import com.chungli.mapper.UserProfileExtraMapper;
import com.chungli.mapper.UserProfileMapper;


@Service
public class UserTeamProfileService {
	private static Logger logger = Logger.getLogger(UserTeamProfileService.class);
	@Autowired
	private UserProfileMapper userMapper ;
	@Autowired
	private UserProfileExtraMapper userProfileExtraMapper ;


	
	@Transactional(rollbackFor=Exception.class)
	public List<UserProfile> selectUserProfileList(String userId,String startDate, String endDate) throws Exception{
		List<UserProfile> userList  = null ;
		Map<String , String> map = new HashMap<String , String>();
		startDate = (startDate == null || "".equals(startDate)) ? null : startDate ;
		endDate   = (endDate == null   || "".equals(endDate)  ) ? null : endDate ;
		map.put("userId", userId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		userList = userProfileExtraMapper.selectUserTeamProfileList(map);
		return userList ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public UserProfile selectRefId(String userId) throws Exception{
		UserProfile userProfile = null ;
		userProfile = userProfileExtraMapper.selectUserProfileByLeader(userId);
		return userProfile ;
	}

}
