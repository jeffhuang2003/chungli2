package com.chungli.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chungli.dto.UserProfile;

@Controller
public class DataController extends BaseController{
	private static Logger logger = Logger.getLogger(DataController.class);

	@RequestMapping(value="/userDataInit" , method = RequestMethod.POST)
	public ModelAndView userDataInit(@RequestParam(value="email",required=false) String email ,@RequestParam(value="userId",required=false) String userId,HttpServletRequest request){
		logger.debug("userDataInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		String url = null ;
		url = checkSession(request);
		if (url != null) {
			return new ModelAndView(url, map);
		}
		HttpSession session = getSession(request) ;
		if (session != null) {
			UserProfile user = (UserProfile)session.getAttribute("user") ;
			map.put("email", user.getEmail());
			map.put("userId", user.getUserId());
			map.put("userSize", -1);
			map.put("chineseName", "");
		}
		logger.debug("userDataInit end !!!");
		return new ModelAndView("pages/userData", map);
	}
	
	
	@RequestMapping(value="/userTeamInit" , method = RequestMethod.POST)
	public ModelAndView userTeamInit(@RequestParam(value="email",required=false) String email 				, @RequestParam(value="userId",required=false) String userId,
									 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, @RequestParam(value="leaderUserId",required=false) String leaderUserId ,
									 @RequestParam(value="parentEmail",required=false) String parentEmail 	, @RequestParam(value="parentUserId",required=false) String parentUserId ,
									 @RequestParam(value="control",required=false) String control ,HttpServletRequest request){
		logger.debug("userTeamInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		String url = null ;
		url = checkSession(request);
		if (url != null) {
			return new ModelAndView(url, map);
		}
		
		map.put("email", email);
		map.put("userId", userId);
		map.put("userSize", -1);
		map.put("leaderEmail", leaderEmail);
		map.put("leaderUserId", leaderUserId);
		map.put("parentEmail", parentEmail);
		map.put("parentUserId", parentUserId);
		map.put("control", control);
		logger.debug("userTeamInit end !!!");
		return new ModelAndView("pages/userTeamData", map);
	}
	
	@RequestMapping(value="/userLiveInit" , method = RequestMethod.POST)
	public ModelAndView userLiveInit(@RequestParam(value="email",required=false) String email ,@RequestParam(value="userId",required=false) String userId,HttpServletRequest request){
		logger.debug("userLiveInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		String url = null ;
		url = checkSession(request);
		if (url != null) {
			return new ModelAndView(url, map);
		}
		HttpSession session = getSession(request) ;
		if (session != null) {
			UserProfile user = (UserProfile)session.getAttribute("user") ;
			map.put("email", user.getEmail());
			map.put("userId", user.getUserId());
			map.put("userSize", -1);
			map.put("chineseName", "");
		}
		logger.debug("userLiveInit end !!!");
		return new ModelAndView("pages/userLiveData", map);
	}

}
