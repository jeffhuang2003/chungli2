package com.chungli.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chungli.dto.EaProgram;
import com.chungli.dto.EaProgramExample;
import com.chungli.mapper.EaProgramMapper;


@Service
public class EaService {
	private static Logger logger = Logger.getLogger(EaService.class);
	@Autowired
	private EaProgramMapper eaProgramMapper ;
	
	@Transactional(rollbackFor=Exception.class)
	public List<EaProgram> selectEaProgramList() throws Exception{
		List<EaProgram> list = null ;
		EaProgramExample example = new EaProgramExample() ;
		list = eaProgramMapper.selectByExample(example);
		return list ;
	}
	
	
}
