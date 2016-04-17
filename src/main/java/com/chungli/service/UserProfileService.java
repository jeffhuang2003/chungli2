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
import com.chungli.mapper.UserProfileExtraMapper;
import com.chungli.mapper.UserProfileMapper;
import com.chungli.mapper.UserRefenceMapper;


@Service
public class UserProfileService {
	private static Logger logger = Logger.getLogger(UserProfileService.class);
	@Autowired
	private UserProfileMapper userMapper ;
	@Autowired
	private UserProfileExtraMapper userProfileExtraMapper ;
	@Autowired
	private UserRefenceMapper userRefenceMapper ;
	
	@Transactional(rollbackFor=Exception.class)
	public UserProfile selectUserProfile(String userId) throws Exception{
		UserProfile user  = null ;
		user = userMapper.selectByPrimaryKey(userId);
		return user ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertUserProfile(UserProfile user,UserRefence userRefence) throws Exception{
		int count = 0 ;
		String insertUserId  = null ;
		String qryUserId = "%" +  userRefence.getReferrerId().substring(0, 3) + "%"  ;
		count = userProfileExtraMapper.selectUserProfileCount(qryUserId) ;
		count ++ ;
		insertUserId = userRefence.getReferrerId().substring(0, 3) + String.format("%012d", count);
		user.setUserId(insertUserId);
		userRefence.setUserId(insertUserId);
		count = 0 ;
		count = userMapper.insertSelective(user);
		if (count < 0) {
			throw new Exception();
		}
		count = userRefenceMapper.insertSelective(userRefence) ;
		return count ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int updateUserProfile(UserProfile user) throws Exception{
		int count = 0 ;
		count = userMapper.updateByPrimaryKeySelective(user);
		if (count < 0) {
			throw new Exception();
		}
		return count ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<UserProfile> selectUserProfileList(String userId,String chineseName) throws Exception{
		List<UserProfile> userList  = null ;
		List<UserProfile> userFinalList  = new ArrayList<UserProfile>() ;
		userList = userProfileExtraMapper.selectUserProfileList(userId);
		if (chineseName != null && !"".equals(chineseName)) {
			for (UserProfile userProfile : userList) {
				if (chineseName.trim().equals(userProfile.getChineseName()) || chineseName.equals(userProfile.getEnglishName())) {
					userFinalList.add(userProfile) ;
					break ;
				}
			}
			return userFinalList ;
		}
		
		return userList ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int deleteUserProfile(String userId) throws Exception{
		return userMapper.deleteByPrimaryKey(userId) ;
	}

	@Transactional(rollbackFor=Exception.class)
	public int checkEmailCount(String email) throws Exception{
		int count = 0 ;
		UserProfileExample example = new UserProfileExample();
		example.createCriteria().andEmailEqualTo(email);
		count = userMapper.countByExample(example) ;
		return count ;
	}
}
