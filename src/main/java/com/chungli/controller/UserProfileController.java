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
import com.chungli.service.UserRefenceService;


@Controller
public class UserProfileController extends BaseController{
	private static Logger logger = Logger.getLogger(UserProfileController.class);
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private UserRefenceService userRefenceService;

	@RequestMapping(value="/updateUserProfileInit", method = RequestMethod.POST)
	public ModelAndView updateUserProfileInit(@RequestParam(value="userId",required=false) String userId , @RequestParam(value="leaderUserId",required=false) String leaderUserId,@RequestParam(value="leaderEmail",required=false) String leaderEmail,@RequestParam(value="chineseName",required=false) String chineseName ,HttpServletRequest request){
		logger.debug("updateUserProfileInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		UserProfile userProfile = null ;
		UserRefence userRefence = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			userProfile = userProfileService.selectUserProfile(userId);
			userRefence = userRefenceService.selectUserRefence(userId) ;
			map.put("userId", userProfile.getUserId());
			map.put("englishName", userProfile.getEnglishName());
			map.put("phone", userProfile.getPhone());
			map.put("email", userProfile.getEmail());
			map.put("team", userProfile.getTeam());
			if (userRefence !=null) {
				map.put("leaderUserId", userRefence.getReferrerId());
			} else {
				map.put("leaderUserId", leaderUserId);
			}
			map.put("leaderEmail", userProfile.getLeaderEmail());
			map.put("chineseName", userProfile.getChineseName());
			map.put("parentChineseName", chineseName);
			
		} catch (Exception e) {
			logger.debug("userUpdateInit error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("updateUserProfileInit end !!!");
		return new ModelAndView("pages/userDataUpdate", map);
	}
	
	@RequestMapping(value="/insertUserProfileInit", method = RequestMethod.POST)
	public ModelAndView insertUserProfileInit(@RequestParam(value="leaderUserId",required=false) String userId,@RequestParam(value="leaderEmail",required=false) String email,@RequestParam(value="chineseName",required=false) String chineseName , HttpServletRequest request){
		logger.debug("insertUserProfileInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			map.put("userId", userId);
			map.put("leaderUserId", userId);
			map.put("leaderEmail", email);
			map.put("email", email);
			map.put("chineseName", chineseName);
			String tempStr = userId.substring(0,3) ;
			if ("JON".equals(tempStr)) {
				map.put("team", "3");
			} else if ("PEL".equals(tempStr)){
				map.put("team", "2");
			} else if ("CHU".equals(tempStr)){
				map.put("team", "1");
			}else {
				map.put("team", "4");
			}
		   		
		} catch (Exception e) {
			logger.debug("insertUserProfileInit error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		
		logger.debug("insertUserProfileInit end !!!");
		return new ModelAndView("pages/userDataInsert", map);
	}
	
	@RequestMapping(value="/updateUserProfile", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserProfile(@RequestBody Map<String,String> map,HttpServletRequest request){
		logger.debug("updateUserProfile start !!!");
		JSONObject obj = new JSONObject();
		String email = map.get("email") != null ? map.get("email").trim() : null ;
		logger.debug("email : " + email);
		String chineseName = map.get("chineseName")!= null ? map.get("chineseName").trim() : null  ;
		logger.debug("chineseName : " + chineseName);
		String englishName = map.get("englishName")!= null ? map.get("englishName").trim() : null  ;
		logger.debug("englishName : " + englishName);
		String phone = map.get("phone")!= null ? map.get("phone").trim() : null  ;
		logger.debug("phone : " + phone);
		String team = map.get("team")!= null ? map.get("team").trim() : null  ;
		logger.debug("team : " + team);
		String userId = map.get("userId")!= null ? map.get("userId").trim() : null  ;
		logger.debug("userId : " + userId);
		String leaderUserId = map.get("leaderUserId")!= null ? map.get("leaderUserId").trim() : null  ;
		logger.debug("leaderUserId : " + leaderUserId);
		UserProfile user = new UserProfile() ;
		UserProfile leaderUser = null;
		int count = 0 ;
		Date sysDate = new Date();
	    try {
	    	leaderUser = userProfileService.selectUserProfile(leaderUserId);
	    	user.setCrUser(leaderUser.getChineseName());
	    	user.setUserStamp(leaderUser.getChineseName());
	    	user.setEmail(email);
	    	user.setEnglishName(englishName);
	    	user.setTeam(new Integer(team));
	    	user.setPhone(phone);
	    	user.setDateStamp(sysDate);
	    	user.setChineseName(chineseName);
	    	user.setUserId(userId);
	    	count =  userProfileService.updateUserProfile(user);

	    	if (count > 0) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","");
	    		obj.put("email", email);
	    	} else {
	    		throw new Exception("新增失敗");
	    	}
	    	
		} catch (Exception e) {
			logger.error("updatetUserProfile error : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("updateUserProfile end !!!");
		return obj.toString();
	}
	
	@RequestMapping(value="/insertUserProfile", method = RequestMethod.POST)
	@ResponseBody
	public String insertUserProfile(@RequestBody Map<String,String> map,HttpServletRequest request){
		logger.debug("insertUserProfileInit start !!!");
		JSONObject obj = new JSONObject();
		String email = map.get("userEmail") != null ? map.get("userEmail").trim() : null ;
		logger.debug("email : " + email);
		String chineseName = map.get("chineseName")!= null ? map.get("chineseName").trim() : null  ;
		logger.debug("chineseName : " + chineseName);
		String englishName = map.get("englishName")!= null ? map.get("englishName").trim() : null  ;
		logger.debug("englishName : " + englishName);
		String phone = map.get("phone")!= null ? map.get("phone").trim() : null  ;
		logger.debug("phone : " + phone);
		String team = map.get("team")!= null ? map.get("team").trim() : null  ;
		logger.debug("team : " + team);
		String userId = map.get("userId")!= null ? map.get("userId").trim() : null  ;
		logger.debug("userId : " + userId);
		UserProfile user = new UserProfile() ;
		UserProfile leaderUser = null;
		UserRefence userRefence = null ;
		int count = 0 ;
		Date sysDate = new Date();
	    try {
	    	leaderUser = userProfileService.selectUserProfile(userId);
	    	user.setCrUser(leaderUser.getChineseName());
	    	user.setUserStamp(leaderUser.getChineseName());
	    	user.setEmail(email);
	    	user.setEnglishName(englishName);
	    	user.setTeam(new Integer(team));
	    	user.setPhone(phone);
	    	user.setPassword("0000");
	    	user.setCrDate(sysDate);
	    	user.setDateStamp(sysDate);
	    	user.setChineseName(chineseName);
	    	user.setLeaderEmail(leaderUser.getEmail());
	    	userRefence = new UserRefence();
	    	userRefence.setCrDate(sysDate);
	    	userRefence.setUserStamp(leaderUser.getChineseName());
	    	userRefence.setReferrerId(leaderUser.getUserId());
	    	userRefence.setDateStamp(sysDate);
	    	userRefence.setCrUser(leaderUser.getChineseName());
	    	count =  userProfileService.insertUserProfile(user,userRefence);

	    	if (count > 0) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","");
	    	} else {
	    		throw new Exception("新增失敗");
	    	}
	    	
		} catch (Exception e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("insertUserProfileInit end !!!");
		return obj.toString();
	}
	
	@RequestMapping(value="/queryUserProfile", method = RequestMethod.POST)
	public ModelAndView queryUserProfile(@RequestParam(value="email",required=false) String email ,@RequestParam(value="userId",required=false) String userId,@RequestParam(value="chineseName",required=false) String chineseName , HttpServletRequest request){
		logger.debug("queryUserProfile start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userProfileService.selectUserProfileList(userId, chineseName);
			map.put("list", list);
			map.put("email", email);
			map.put("userId", userId);
			map.put("userId", userId);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
			map.put("chineseName", chineseName);
		} catch (Exception e) {
			logger.debug("queryUserProfile error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("queryUserProfile end !!!");
		return new ModelAndView("pages/userData", map);
	}
	
	@RequestMapping(value="/queryUserProfileByLeaderInsert", method = RequestMethod.POST)
	public ModelAndView queryUserProfileByLeaderInsert(@RequestParam(value="leaderUserId",required=false) String leaderUserId ,@RequestParam(value="leaderEmail",required=false) String leaderEmail,@RequestParam(value="parentChineseName",required=false) String parentChineseName ,HttpServletRequest request){
		logger.debug("queryUserProfileByLeaderInsert start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		try {
			
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userProfileService.selectUserProfileList(leaderUserId, parentChineseName);
			map.put("list", list);
			map.put("email", leaderEmail);
			map.put("userId", leaderUserId);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
			map.put("chineseName", parentChineseName);
		} catch (Exception e) {
			logger.debug("queryUserProfileByLeader error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("queryUserProfileByLeaderInsert end !!!");
		return new ModelAndView("pages/userData", map);
	}
	
	@RequestMapping(value="/queryUserProfileByLeaderUpdate", method = RequestMethod.POST)
	public ModelAndView queryUserProfileByLeaderUpdate(@RequestParam(value="leaderUserId",required=false) String leaderUserId ,@RequestParam(value="leaderEmail",required=false) String leaderEmail,@RequestParam(value="parentChineseName",required=false) String parentChineseName ,HttpServletRequest request){
		logger.debug("queryUserProfileByLeaderUpdate start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		try {
			
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userProfileService.selectUserProfileList(leaderUserId, parentChineseName);
			map.put("list", list);
			map.put("email", leaderEmail);
			map.put("userId", leaderUserId);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
			map.put("chineseName", parentChineseName);
		} catch (Exception e) {
			logger.debug("queryUserProfileByLeader error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("queryUserProfileByLeader end !!!");
		return new ModelAndView("pages/userData", map);
	}
	
	@RequestMapping(value="/userDelete")
	@ResponseBody
	public String userDelete(@RequestBody String userId){
		logger.debug("userDelete start !!!");
		JSONObject obj = new JSONObject();
		try {
			userProfileService.deleteUserProfile(userId);
			obj.put("success", "success");
			obj.put("errorMessage", "");
		} catch (Exception e) {
			logger.debug("userUpdateInit error : " + e.getMessage());
			logger.debug(getTrace(e));
			obj.put("success", "error");
			obj.put("errorMessage", e.getMessage());
		}
		logger.debug("userDelete end !!!");
		return obj.toString();
	}
	
	
	@RequestMapping(value="/checkEmail", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmail(@RequestBody Map<String,String> map ,HttpServletRequest request){
		logger.debug("checkEmail start !!!");
		JSONObject obj = new JSONObject();
		String email 	= map.get("email")    == null? "" : map.get("email").trim();
		int count = 0 ;
	    try {
	    	if ( "".equals(email)) {
	    		throw new Exception("mail不能為空");
	    	} else {
	    		count = userProfileService.checkEmailCount(email);
	    	}

	    	if (count == 0) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","此帳號尚未有人使用");
	    	} else {
	    		obj.put("success","error");
	    		obj.put("errorMessage","此帳號已有人使用");
	    	}
	    	
		} catch (Exception e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("checkEmail end !!!");
		return obj.toString();
	}
	
	
	
	
	private String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
}
