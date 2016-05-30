package com.chungli.mapper;

import java.util.Map;

import com.chungli.dto.UserPay;

public interface UserPayExtraMapper {

	public UserPay selectMinUserPay(Map<String,String> map);
	
}
