package com.chungli.mapper;

import java.util.List;
import java.util.Map;

import com.chungli.dto.UserProfile;

public interface UserProfileExtraMapper {

	public List<UserProfile> selectUserProfileList(String userId);
	
	public int selectUserProfileCount(String userId);
	
	public List<UserProfile> selectUserTeamProfileList(Map<String ,String> map);
	public UserProfile selectUserProfileByLeader(String userId);
}
