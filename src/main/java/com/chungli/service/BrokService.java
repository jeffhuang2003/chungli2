package com.chungli.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;











import com.chungli.controller.LoginController;
import com.chungli.dto.Brokerage;
import com.chungli.dto.BrokerageExample;
import com.chungli.dto.UserProfile;
import com.chungli.dto.UserProfileExample;
import com.chungli.dto.UserProfileExample.Criteria;
import com.chungli.dto.UserRefence;
import com.chungli.mapper.BrokerageMapper;
import com.chungli.mapper.UserProfileExtraMapper;
import com.chungli.mapper.UserProfileMapper;
import com.chungli.mapper.UserRefenceMapper;


@Service
public class BrokService {
	private static Logger logger = Logger.getLogger(BrokService.class);
	@Autowired
	private BrokerageMapper brokerageMapper ;
	
	@Transactional(rollbackFor=Exception.class)
	public List<Brokerage> selectBrokerageList() throws Exception{
		List<Brokerage> list = null ;
		BrokerageExample example = new BrokerageExample() ;
		list = brokerageMapper.selectByExample(example);
		return list ;
	}
	
	
}
