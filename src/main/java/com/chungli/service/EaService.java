package com.chungli.service;

import java.util.ArrayList;
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
	
	@Transactional(rollbackFor=Exception.class)
	public List<EaProgram> qryEaProgramList(String reqStr) throws Exception {
		List<EaProgram> list = null ;
		List<Integer> eaStrList = null ;
		String[] array = null ;
		EaProgramExample example = new EaProgramExample() ;
		if (reqStr.indexOf(",") !=-1) {
			array = reqStr.split(",") ;
			eaStrList = getEaProgramListStr(array);
			example.createCriteria().andEaIdIn(eaStrList);
		} else {
			example.createCriteria().andEaIdEqualTo(Integer.valueOf(reqStr));
		} 
		list = eaProgramMapper.selectByExample(example);
		return list ;
	}
	
	
	public  List<Integer>  getEaProgramListStr(String [] eaProgramArray){
		List<Integer>  list = new ArrayList<Integer>() ;
		for (String str : eaProgramArray) {
			list.add(Integer.valueOf(str));
		}
		return list ;
	}
	
}
