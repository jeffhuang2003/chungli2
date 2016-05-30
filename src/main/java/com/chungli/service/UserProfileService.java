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
import com.chungli.mapper.UserProfileExtraMapper;
import com.chungli.mapper.UserProfileMapper;



@Service
public class UserProfileService {
	private static Logger logger = Logger.getLogger(UserProfileService.class);
	@Autowired
	private UserProfileMapper userMapper ;
	@Autowired
	private UserProfileExtraMapper userProfileExtraMapper ;

	
	@Transactional(rollbackFor=Exception.class)
	public UserProfile selectUserProfile(String userId) throws Exception{
		UserProfile user  = null ;
		user = userMapper.selectByPrimaryKey(userId);
		return user ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertUserProfile(UserProfile user) throws Exception{
		int count = 0 ;
		count = 0 ;
		count = userMapper.insertSelective(user);
		if (count < 0) {
			throw new Exception();
		}
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
				if (chineseName.trim().equals(userProfile.getChineseName()) || chineseName.equals(userProfile.getChineseName())) {
					userFinalList.add(userProfile) ;
				}
			}
			return userFinalList ;
		}
		
		return userList ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int deleteUserProfile(String userId) throws Exception{
		int count = 0 ;
		count = userMapper.deleteByPrimaryKey(userId) ;
		return count ;
	}

	@Transactional(rollbackFor=Exception.class)
	public int checkEmailCount(String email) throws Exception{
		int count = 0 ;
		UserProfileExample example = new UserProfileExample();
		example.createCriteria().andEmailEqualTo(email);
		count = userMapper.countByExample(example) ;
		return count ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<UserProfile> selectTotalList(String userId,String chineseName) throws Exception{
		List<UserProfile> userList  = null ;
		List<UserProfile> userFinalList  = new ArrayList<UserProfile>() ;
		userList = userProfileExtraMapper.selectTotalList(userId);
		if (chineseName != null && !"".equals(chineseName)) {
			for (UserProfile userProfile : userList) {
				if (chineseName.trim().equals(userProfile.getChineseName()) || chineseName.equals(userProfile.getChineseName())) {
					userFinalList.add(userProfile) ;
				}
			}
			return userFinalList ;
		}
		
		return userList ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int selectChildListCount(String userId) throws Exception{
		int count  = 0 ;
        UserProfileExample example = new UserProfileExample();
        example.createCriteria().andLeaderEmailEqualTo(userId) ;
        count = userMapper.countByExample(example) ;
		return count ;
	}
}
