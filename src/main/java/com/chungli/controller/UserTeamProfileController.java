package com.chungli.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chungli.dto.UserProfile;
import com.chungli.dto.UserRefence;
import com.chungli.service.UserProfileService;
import com.chungli.service.UserTeamProfileService;


@Controller
public class UserTeamProfileController extends BaseController {
	private static Logger logger = Logger.getLogger(UserTeamProfileController.class);
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private UserTeamProfileService userTeamProfileService;
	

	@RequestMapping(value="/queryUserTeamProfile", method = RequestMethod.POST)
	public ModelAndView queryUserTeamProfile(@RequestParam(value="email",required=false) String email 				, @RequestParam(value="userId",required=false) String userId,
			 								 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, @RequestParam(value="leaderUserId",required=false) String leaderUserId ,
			 								 @RequestParam(value="parentEmail",required=false) String parentEmail 	, @RequestParam(value="parentUserId",required=false) String parentUserId ,
			 								 @RequestParam(value="startDate",required=false) String startDate 	, @RequestParam(value="endDate",required=false) String endDate ,
			 								 @RequestParam(value="control",required=false) String control ,HttpServletRequest request){
		logger.debug("queryUserTeamProfile start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			list = userTeamProfileService.selectUserProfileList(userId, startDate, endDate);
			map.put("list", list);
			map.put("email", email);
			map.put("userId", userId);
			map.put("leaderEmail", leaderEmail);
			map.put("leaderUserId", leaderUserId);
			map.put("parentEmail", parentEmail);
			map.put("parentUserId", parentUserId);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("control", control);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
		} catch (Exception e) {
			logger.debug("queryUserTeamProfile error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("queryUserTeamProfile end !!!");
		return new ModelAndView("pages/userTeamData", map);
	}
	
	@RequestMapping(value="/selectLeaderUserTeam", method = RequestMethod.POST)
	public ModelAndView selectLeaderUserTeam(@RequestParam(value="email",required=false) String email 				, @RequestParam(value="userId",required=false) String userId,
			 								 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, @RequestParam(value="leaderUserId",required=false) String leaderUserId ,
			 								 @RequestParam(value="parentEmail",required=false) String parentEmail 	, @RequestParam(value="parentUserId",required=false) String parentUserId ,
			 								 @RequestParam(value="control",required=false) String control  ,HttpServletRequest request){
		logger.debug("selectLeaderUserTeam start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		UserProfile userProfile = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userTeamProfileService.selectUserProfileList(leaderUserId, null, null);
			userProfile = userTeamProfileService.selectRefId(leaderUserId) ;
			map.put("list", list);
			map.put("email", leaderEmail);
			map.put("userId", leaderUserId);
			if (parentUserId.equals(userProfile.getUserId())) {
				map.put("leaderEmail", parentEmail);
				map.put("leaderUserId", parentUserId);
				map.put("control", "0");
			} else {
				map.put("leaderEmail", userProfile.getEmail());
				map.put("leaderUserId", userProfile.getUserId());
			} 
			map.put("parentEmail", parentEmail);
			map.put("parentUserId", parentUserId);
			map.put("control", control);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
		} catch (Exception e) {
			logger.debug("selectLeaderUserTeam error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("selectLeaderUserTeam end !!!");
		return new ModelAndView("pages/userTeamData", map);
	}	
	
	@RequestMapping(value="/selectParentUserTeam", method = RequestMethod.POST)
	public ModelAndView selectParentUserTeam(@RequestParam(value="email",required=false) String email 				, @RequestParam(value="userId",required=false) String userId,
			 								 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, @RequestParam(value="leaderUserId",required=false) String leaderUserId ,
			 								 @RequestParam(value="parentEmail",required=false) String parentEmail 	, @RequestParam(value="parentUserId",required=false) String parentUserId ,
			 								 @RequestParam(value="control",required=false) String control 			 ,HttpServletRequest request ){
		logger.debug("selectParentUserTeam start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		try {
			
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userTeamProfileService.selectUserProfileList(parentUserId, null, null);
			map.put("list", list);
			map.put("email", parentEmail);
			map.put("userId", parentUserId);
			map.put("leaderEmail", parentEmail);
			map.put("leaderUserId", parentUserId);
			map.put("parentEmail", parentEmail);
			map.put("parentUserId", parentUserId);
			map.put("control", control);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
		} catch (Exception e) {
			logger.debug("selectParentUserTeam error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("selectParentUserTeam end !!!");
		return new ModelAndView("pages/userTeamData", map);
	}
	
	@RequestMapping(value="/selectChildUserTeam", method = RequestMethod.POST)
	public ModelAndView selectChildUserTeam(@RequestParam(value="email",required=false) String email 				, @RequestParam(value="userId",required=false) String userId,
			 								 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, @RequestParam(value="leaderUserId",required=false) String leaderUserId ,
			 								 @RequestParam(value="parentEmail",required=false) String parentEmail 	, @RequestParam(value="parentUserId",required=false) String parentUserId ,
			 								 @RequestParam(value="control",required=false) String control , HttpServletRequest request ){
		logger.debug("selectChildUserTeam start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		UserProfile userProfile = null ;
		try {
			
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userTeamProfileService.selectUserProfileList(userId, null, null);
			userProfile = userTeamProfileService.selectRefId(userId) ;
			map.put("list", list);
			map.put("email", email);
			map.put("userId", userId);
			if (userId.equals(userProfile.getUserId())) {
				map.put("leaderEmail", email);
				map.put("leaderUserId", userId);
			} else {
				map.put("leaderEmail", userProfile.getEmail());
				map.put("leaderUserId", userProfile.getUserId());
			} 
			map.put("parentEmail", parentEmail);
			map.put("parentUserId", parentUserId);
			map.put("control", control);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
		} catch (Exception e) {
			logger.debug("selectChildUserTeam error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("selectChildUserTeam end !!!");
		return new ModelAndView("pages/userTeamData", map);
	}
	
	private String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
}
