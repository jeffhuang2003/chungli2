package com.chungli.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;















import com.chungli.dto.UserLive;
import com.chungli.dto.UserLiveExample;
import com.chungli.dto.UserLiveExample.Criteria;
import com.chungli.dto.UserLiveKey;
import com.chungli.mapper.UserLiveMapper;



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
	public UserLive selectUserLive(String userId,String userLiveId) throws Exception{
		List<UserLive> list = null ;
		UserLive userLive = null ;
		UserLiveExample example = new UserLiveExample();
		Criteria criteria= example.createCriteria() ;
		criteria.andUserIdEqualTo(userId);
		criteria.andUserLiveIdEqualTo(userLiveId);
		list = userLiveMapper.selectByExample(example);
		if (list != null && !list.isEmpty()) {
			userLive = list.get(0);
		}
		return userLive ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<UserLive> selectUserLiveQry(String userId,String eaProgram, String brokerAge) throws Exception{
		List<UserLive> list = null ;
		UserLiveExample example = new UserLiveExample();
		Criteria criteria= example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		if (eaProgram != null && !"".equals(eaProgram)) {
			criteria.andEaIdEqualTo(new Integer(eaProgram));
		}
		if (brokerAge != null && !"".equals(brokerAge)) {
			criteria.andBrokIdEqualTo(new Integer(brokerAge));
		}
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
	
	@Transactional(rollbackFor=Exception.class)
	public int updateUserLive(UserLive userLive) throws Exception{
		int count = 0 ;
		count = userLiveMapper.updateByPrimaryKeySelective(userLive) ;
		if (count < 0) {
			throw new Exception();
		}
		return count ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int deltetUserLive(String email , String userLiveId) throws Exception{
		int count = 0 ;
		UserLiveKey key = new UserLiveKey();
		key.setUserId(email);
		key.setUserLiveId(userLiveId);
		count = userLiveMapper.deleteByPrimaryKey(key) ;
		if (count < 0) {
			throw new Exception("刪除失敗");
		}
		return count ;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<UserLive> selectUserLive(String userId,String userLiveId,int eaId) throws Exception{
		List<UserLive> list = null ;
		UserLiveExample example = new UserLiveExample();
		Criteria c= example.createCriteria();
		c.andUserIdEqualTo(userId);
		c.andUserLiveIdEqualTo(userLiveId);
		c.andEaIdEqualTo(eaId);
		list = userLiveMapper.selectByExample(example);
		return list ;
	}
}
