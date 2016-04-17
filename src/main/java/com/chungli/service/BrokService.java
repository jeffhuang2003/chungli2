package com.chungli.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chungli.dto.Brokerage;
import com.chungli.dto.BrokerageExample;
import com.chungli.mapper.BrokerageMapper;



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
