package com.chungli.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chungli.dto.Brokerage;
import com.chungli.dto.EaProgram;
import com.chungli.dto.UserPay;
import com.chungli.dto.UserProfile;
import com.chungli.service.BrokService;
import com.chungli.service.EaService;
import com.chungli.service.UserPayService;

@Controller
public class DataController extends BaseController{
	private static Logger logger = Logger.getLogger(DataController.class);

	@Autowired
	private UserPayService userPayService ;
	@Autowired
	private EaService eaService ;
	@Autowired
	private BrokService brokService;	
	
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
			map.put("userSize", -1);
			map.put("chineseName", "");
		} 
		
		UserPay userPay = userPayService.getMinUserPay(email,getSysdDate());
		if (userPay != null ) {
			map.put("tag4", "1");
			map.put("tag5", "1");
		} else{
			map.put("tag4", "0");
			map.put("tag5", "0");
		}
		session.setAttribute("tag4", map.get("tag4"));
		session.setAttribute("tag5", map.get("tag5"));
		logger.debug("userDataInit end !!!");
		return new ModelAndView("pages/userData", map);
	}
	
	
	@RequestMapping(value="/userTeamInit" , method = RequestMethod.POST)
	public ModelAndView userTeamInit(@RequestParam(value="email",required=false) String email 				, 
									 @RequestParam(value="leaderEmail",required=false) String leaderEmail 	, 
									 @RequestParam(value="parentEmail",required=false) String parentEmail 	, 
									 @RequestParam(value="control",required=false) String control ,HttpServletRequest request){
		logger.debug("userTeamInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		String url = null ;
		url = checkSession(request);
		if (url != null) {
			return new ModelAndView(url, map);
		}
		logger.debug("email : " + email);
		map.put("email", email);
		map.put("userSize", -1);
		map.put("leaderEmail", leaderEmail);
		map.put("parentEmail", parentEmail);
		map.put("control", control);
		map = this.addMapKey(map, request.getSession(false));
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
			map.put("userSize", -1);
			map.put("chineseName", "");
			map = this.addMapKey(map, request.getSession(false));
		}
		logger.debug("userLiveInit end !!!");
		return new ModelAndView("pages/userLiveData", map);
	}
	
	@RequestMapping(value="/systemInit" , method = RequestMethod.POST)
	public ModelAndView systemInit(@RequestParam(value="email",required=false) String email ,HttpServletRequest request){
		logger.debug("systemInit start !!!");
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
			map = this.addMapKey(map, request.getSession(false));
		}
		logger.debug("systemInit end !!!");
		return new ModelAndView("pages/sysTemData", map);
	}
	
	@RequestMapping(value="/downloadCSVInit" , method = RequestMethod.POST)
	public ModelAndView downloadCSVInit(@RequestParam(value="email",required=false) String email ,HttpServletRequest request){
		logger.debug("downloadCSVInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		String url = null ;
		HttpSession session = null ;
		UserPay userPay = null ;
		List<EaProgram> eaList = null ;
		List<Brokerage> brokList = null ;
		try {
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			session = getSession(request) ;
			if (session != null) {
				UserProfile user = (UserProfile)session.getAttribute("user") ;
				map.put("email", user.getEmail());
				map = this.addMapKey(map, request.getSession(false));
			}
			eaList = eaService.selectEaProgramList();
			brokList = brokService.selectBrokerageList();
			map.put("eaList", eaList);
			map.put("brokList", brokList);
		} catch(Exception e) {
			logger.error("eeror : " + e.getMessage());
		}
		
		
		logger.debug("downloadCSVInit end !!!");
		return new ModelAndView("pages/downloadCSV", map);
	}
		
}
