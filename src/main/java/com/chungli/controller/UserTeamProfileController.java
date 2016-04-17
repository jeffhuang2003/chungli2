package com.chungli.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chungli.dto.UserProfile;
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
	public ModelAndView queryUserTeamProfile(@RequestParam(value="email",required=false) String email ,
											 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, 
											 @RequestParam(value="parentEmail",required=false) String parentEmail 	, 
											 @RequestParam(value="startDate",required=false) String startDate 	,
											 @RequestParam(value="endDate",required=false) String endDate ,
			 								 @RequestParam(value="control",required=false) String control ,HttpServletRequest request){
		logger.debug("queryUserTeamProfile start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		UserProfile user = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			list = userTeamProfileService.selectUserProfileList(email, startDate, endDate);
			user = userProfileService.selectUserProfile(email) ;
			map.put("list", list);
			map.put("email", email);
			if (StringUtils.isBlank(leaderEmail) || StringUtils.isBlank(parentEmail)) {
				map.put("leaderEmail", email);
				map.put("parentEmail", email);
			} else {
				if (email.equals(leaderEmail) &&  email.equals(parentEmail)) {
					map.put("leaderEmail", email);
					map.put("parentEmail", email);
				} else {
					map.put("leaderEmail", leaderEmail);
					map.put("parentEmail", parentEmail);
				}
			}
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
	public ModelAndView selectLeaderUserTeam(@RequestParam(value="userEmail",required=false) String email 				, 
											 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, 
										     @RequestParam(value="parentEmail",required=false) String parentEmail 	, 
											 @RequestParam(value="startDate",required=false) String startDate 	,
											 @RequestParam(value="endDate",required=false) String endDate ,
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
			
			list = userTeamProfileService.selectUserProfileList(leaderEmail, startDate, endDate);
			userProfile = userProfileService.selectUserProfile(leaderEmail) ;
			map.put("list", list);
			map.put("email", leaderEmail);
			if (parentEmail.equals(userProfile.getEmail())) {
				map.put("leaderEmail", parentEmail);
				map.put("control", "0");
			} else {
				map.put("leaderEmail", userProfile.getLeaderEmail());
			} 
			map.put("parentEmail", parentEmail);
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
	public ModelAndView selectParentUserTeam(@RequestParam(value="email",required=false) String email 				, 
			 								 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, 
			 								 @RequestParam(value="parentEmail",required=false) String parentEmail 	, 
			 								 @RequestParam(value="startDate",required=false) String startDate 	,
											 @RequestParam(value="endDate",required=false) String endDate ,
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
			
			list = userTeamProfileService.selectUserProfileList(parentEmail, startDate, endDate);
			map.put("list", list);
			map.put("email", parentEmail);

			map.put("leaderEmail", parentEmail);

			map.put("parentEmail", parentEmail);

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
	public ModelAndView selectChildUserTeam(@RequestParam(value="email",required=false) String email 				, 
			 								 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	,
			 								 @RequestParam(value="parentEmail",required=false) String parentEmail 	,
			 								 @RequestParam(value="startDate",required=false) String startDate 	,
											 @RequestParam(value="endDate",required=false) String endDate ,
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
			
			list = userTeamProfileService.selectUserProfileList(email, startDate, endDate);
			userProfile = userProfileService.selectUserProfile(email) ;
			map.put("list", list);
			map.put("email", email);
			map.put("leaderEmail", userProfile.getLeaderEmail());
			map.put("parentEmail", parentEmail);
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
