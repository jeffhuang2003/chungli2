package com.chungli.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chungli.dto.UserPay;
import com.chungli.dto.UserPayExample;
import com.chungli.mapper.UserPayExtraMapper;
import com.chungli.mapper.UserPayMapper;

@Service
public class UserPayService {
	private static Logger logger = Logger.getLogger(UserPayService.class);
	
	@Autowired
	private UserPayMapper userPayMapper ;
	
	@Autowired
	private UserPayExtraMapper userPayExtraMapper ;
	
	public List<UserPay> getUserPayList(String userId){
		List<UserPay> list = null;
		UserPayExample example = new UserPayExample();
		example.createCriteria().andUserIdEqualTo(userId);
		list = userPayMapper.selectByExample(example);
		
		return list;
	}
	
	public UserPay getMinUserPay(String userId,String endTime){
        Map<String,String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("endTime", endTime);
		return userPayExtraMapper.selectMinUserPay(map);
	}
	
	public UserPay getMaxUserPay(String userId,String endTime ,String eaId){
        Map<String,String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("endTime", endTime);
        map.put("eaId", "%" + eaId +"%");
		return userPayExtraMapper.selectMinUserPay(map);
	}
}
