package com.chungli.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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

import com.chungli.dto.Brokerage;
import com.chungli.dto.EaProgram;
import com.chungli.dto.UserLive;
import com.chungli.dto.UserProfile;
import com.chungli.service.BrokService;
import com.chungli.service.EaService;
import com.chungli.service.UserLiveService;
import com.chungli.service.UserProfileService;


@Controller
public class UserLiveController extends BaseController{
	private static Logger logger = Logger.getLogger(UserLiveController.class);
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private UserLiveService userLiveService;
	
	@Autowired
	private EaService eaService;
	
	@Autowired
	private BrokService brokService;

	@RequestMapping(value="/updateUserLiveInit", method = RequestMethod.POST)
	public ModelAndView updateUserLiveInit(@RequestParam(value="userId",required=false) String userId , @RequestParam(value="leaderUserId",required=false) String leaderUserId,@RequestParam(value="leaderEmail",required=false) String leaderEmail,@RequestParam(value="chineseName",required=false) String chineseName ,HttpServletRequest request){
		logger.debug("updateUserLiveInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		UserProfile userProfile = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			userProfile = userProfileService.selectUserProfile(userId);
			map.put("userId", userProfile.getEmail());
			map.put("englishName", userProfile.getEnglishName());
			map.put("phone", userProfile.getPhone());
			map.put("email", userProfile.getEmail());
			map.put("team", userProfile.getTeam());
			if (userProfile !=null) {
				map.put("leaderUserId", userProfile.getLeaderEmail());
			} else {
				map.put("leaderUserId", leaderUserId);
			}
			map.put("leaderEmail", userProfile.getLeaderEmail());
			map.put("chineseName", userProfile.getChineseName());
			map.put("parentChineseName", chineseName);
			
		} catch (Exception e) {
			logger.debug("updateUserLiveInit error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("updateUserLiveInit end !!!");
		return new ModelAndView("pages/userLiveDataUpdate", map);
	}
	
	@RequestMapping(value="/insertUserLiveInit", method = RequestMethod.POST)
	public ModelAndView insertUserLiveInit(@RequestParam(value="leaderUserId",required=false) String leaderUserId,@RequestParam(value="leaderEmail",required=false) String leaderEmail,@RequestParam(value="email",required=false) String email,@RequestParam(value="userId",required=false) String userId , HttpServletRequest request){
		logger.debug("insertUserLiveInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Brokerage> brokList = null ;
		List<EaProgram> eaList = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			brokList = brokService.selectBrokerageList();
			eaList = eaService.selectEaProgramList();
			map.put("userId", userId);
			map.put("leaderUserId", leaderUserId);
			map.put("email", email);
			map.put("leaderEmail", leaderEmail);
			map.put("eaList", eaList);
			map.put("brokList", brokList);
		   		
		} catch (Exception e) {
			logger.debug("insertUserLiveInit error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		
		logger.debug("insertUserLiveInit end !!!");
		return new ModelAndView("pages/userLiveDataInsert", map);
	}
	
	@RequestMapping(value="/updateUserLive", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserLive(@RequestBody Map<String,String> map,HttpServletRequest request){
		logger.debug("updateUserLive start !!!");
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
	    	user.setCrDate(sysDate);
	    	user.setDateStamp(sysDate);
	    	user.setChineseName(chineseName);
	    	count =  userProfileService.updateUserProfile(user);

	    	if (count > 0) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","");
	    		obj.put("email", email);
	    	} else {
	    		throw new Exception("新增失敗");
	    	}
	    	
		} catch (Exception e) {
			logger.error("updateUserLive error : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("updateUserLive end !!!");
		return obj.toString();
	}
	
	@RequestMapping(value="/insertUserLive", method = RequestMethod.POST)
	@ResponseBody
	public String insertUserLive(@RequestBody Map<String,String> map,HttpServletRequest request){
		logger.debug("insertUserLive start !!!");
		JSONObject obj = new JSONObject();
		String userLiveId = map.get("userLiveId") != null ? map.get("userLiveId").trim() : null ;
		logger.debug("userLiveId : " + userLiveId);
		String eaId = map.get("eaId")!= null ? map.get("eaId").trim() : null  ;
		logger.debug("userLiveId : " + userLiveId);
		String brokId = map.get("brokId")!= null ? map.get("brokId").trim() : null  ;
		logger.debug("brokId : " + brokId);
		String status = map.get("status")!= null ? map.get("status").trim() : null  ;
		logger.debug("status : " + status);
		String count = map.get("count")!= null ? map.get("count").trim() : "0"  ;
		logger.debug("count : " + count);
		String userId = map.get("userId")!= null ? map.get("userId").trim() : null  ;
		logger.debug("userId : " + userId);
		String leaderUserId = map.get("leaderUserId")!= null ? map.get("leaderUserId").trim() : null  ;
		logger.debug("leaderUserId : " + leaderUserId);
		UserLive userLive = new UserLive() ;;
		Date sysDate = new Date();
		int insertCount = 0 ;
		List<UserProfile> list= null ;
		UserProfile userProfile = null ;
	    try {
	    	list = userProfileService.selectUserProfileList(leaderUserId, null);
	    	if (list != null && !list.isEmpty()) {
	    		userProfile = list.get(0);
	    	} else {
	    		throw new Exception("新增失敗");
	    	}
	    	userLive.setUserId(userId);
	    	userLive.setUserLiveId(userLiveId);
	    	userLive.setEaId(new Integer(eaId));
	    	userLive.setBrokId(new Integer(brokId));
	    	userLive.setStatus(new Integer(status));
	    	userLive.setCount(new Integer(count));
	    	userLive.setCrDate(sysDate);
	    	userLive.setCrUser(userProfile.getChineseName());
	    	userLive.setUserStamp(userProfile.getChineseName());
	    	userLive.setDateStamp(sysDate);
	    	insertCount = userLiveService.insertUserLive(userLive) ;
	    	if (insertCount > 0) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","");
	    	} else {
	    		throw new Exception("新增失敗");
	    	}
	    	
		} catch (Exception e) {
			logger.error("insertUserLive error : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("insertUserLive end !!!");
		return obj.toString();
	}
	
	@RequestMapping(value="/queryUserLiveProfile", method = RequestMethod.POST)
	public ModelAndView queryUserLiveProfile(@RequestParam(value="email",required=false) String email ,@RequestParam(value="userId",required=false) String userId,@RequestParam(value="chineseName",required=false) String chineseName , HttpServletRequest request){
		logger.debug("queryUserLiveProfile start !!!");
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
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
			map.put("chineseName", chineseName);
		} catch (Exception e) {
			logger.debug("queryUserLiveProfile error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("queryUserLiveProfile end !!!");
		return new ModelAndView("pages/userLiveData", map);
	}
	
	@RequestMapping(value="/queryUserLiveByLeaderInsert", method = RequestMethod.POST)
	public ModelAndView queryUserLiveByLeaderInsert(@RequestParam(value="leaderUserId",required=false) String leaderUserId ,@RequestParam(value="leaderEmail",required=false) String leaderEmail,@RequestParam(value="userId",required=false) String userId ,@RequestParam(value="email",required=false) String email,HttpServletRequest request){
		logger.debug("queryUserLiveByLeaderInsert start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserLive> list = null ;
		List<Brokerage> brokList = null ;
		List<EaProgram> eaList = null ;
		try {
			
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userLiveService.selectUserLiveList(leaderUserId);
			brokList = brokService.selectBrokerageList();
			eaList = eaService.selectEaProgramList();
			map.put("list", list);
			map.put("brokList", brokList);
			map.put("eaList", eaList);
			map.put("email", email);
			map.put("leaderUserId", leaderUserId);
			map.put("leaderEmail", leaderEmail);
			map.put("userId", userId);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
		} catch (Exception e) {
			logger.debug("queryUserLiveByLeaderInsert error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("queryUserLiveByLeaderInsert end !!!");
		return new ModelAndView("pages/userLiveData", map);
	}
	
	@RequestMapping(value="/queryUserLiveByLeaderUpdate", method = RequestMethod.POST)
	public ModelAndView queryUserLiveByLeaderUpdate(@RequestParam(value="leaderUserId",required=false) String leaderUserId ,@RequestParam(value="leaderEmail",required=false) String leaderEmail,@RequestParam(value="parentChineseName",required=false) String parentChineseName ,HttpServletRequest request){
		logger.debug("queryUserLiveByLeaderUpdate start !!!");
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
			logger.debug("queryUserLiveByLeaderUpdate error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("queryUserLiveByLeaderUpdate end !!!");
		return new ModelAndView("pages/userLiveData", map);
	}
	
	@RequestMapping(value="/userLiveDelete")
	@ResponseBody
	public String userLiveDelete(@RequestBody String userId){
		logger.debug("userLiveDelete start !!!");
		JSONObject obj = new JSONObject();
		try {
			userProfileService.deleteUserProfile(userId);
			obj.put("success", "success");
			obj.put("errorMessage", "");
		} catch (Exception e) {
			logger.debug("userLiveDelete error : " + e.getMessage());
			logger.debug(getTrace(e));
			obj.put("success", "error");
			obj.put("errorMessage", e.getMessage());
		}
		logger.debug("userLiveDelete end !!!");
		return obj.toString();
	}
	
	
	@RequestMapping(value="/selectUserLivetInit", method = RequestMethod.POST)
	public ModelAndView selectUserLivetInit(@RequestParam(value="userId",required=false) String userId ,@RequestParam(value="email",required=false) String email , @RequestParam(value="leaderUserId",required=false) String leaderUserId ,@RequestParam(value="chineseName",required=false) String chineseName ,HttpServletRequest request){
		logger.debug("selectUserLivetInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserLive> list = null ;
		List<Brokerage> brokList = null ;
		List<EaProgram> eaList = null ;
		try {
			
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userLiveService.selectUserLiveList(userId);
			brokList = brokService.selectBrokerageList();
			eaList = eaService.selectEaProgramList();
//			if (list == null ||  list.isEmpty()) {
//				list = new ArrayList<UserLive>() ;
//				list.add(getUserLive());
//			}
			map.put("list", list);
			map.put("brokList", brokList);
			map.put("eaList", eaList);
			map.put("userId", userId);
			map.put("leaderUserId", leaderUserId);
			map.put("email", email);
			map.put("chineseName", chineseName);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
		} catch (Exception e) {
			logger.debug("selectUserLivetInit error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("selectUserLivetInit end !!!");
		return new ModelAndView("pages/userLiveData2", map);
	}
	@RequestMapping(value="/checkUserLiveId", method = RequestMethod.POST)
	@ResponseBody
	public String checkUserLiveId(@RequestBody Map<String,String> map ,HttpServletRequest request){
		logger.debug("checkUserLiveId start !!!");
		JSONObject obj = new JSONObject();
		String userLiveId 	= map.get("userLiveId")    == null? "" : map.get("userLiveId").trim();
		String userId 	= map.get("userId")    == null? "" : map.get("userId").trim();
		UserLive userLive = null ;
	    try {
	    	if ( "".equals(userLiveId)) {
	    		throw new Exception("真倉帳號不能為空");
	    	} else {
	    		userLive = userLiveService.checkUserLiveId(userLiveId,userId);
	    	}

	    	if (userLive == null) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","此帳號尚未有人使用");
	    		obj.put("resultValue","true");
	    	} else {
	    		obj.put("success","error");
	    		obj.put("resultValue","false");
	    		obj.put("errorMessage","此帳號已有人使用");
	    	}
	    	
		} catch (Exception e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","error");
			obj.put("resultValue","false");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("checkUserLiveId end !!!");
		return obj.toString();
	}
	
	private UserLive getUserLive(){
		UserLive live = new UserLive();
		live.setBrokId(1);
		live.setCount(1000);
		live.setDateStamp(new Date());
		live.setEaId(1);
		live.setUserId("JON000000000001");
		live.setStatus(1);
		live.setUserLiveId("811301");
		return live ;
	}
	
	private String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
}
