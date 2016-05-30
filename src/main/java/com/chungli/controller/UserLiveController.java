package com.chungli.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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
	public ModelAndView updateUserLiveInit(@RequestParam(value="userId",required=false) String userId , @RequestParam(value="userLiveId",required=false) String userLiveId,@RequestParam(value="chineseName",required=false) String chineseName ,HttpServletRequest request){
		logger.debug("updateUserLiveInit start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		UserLive userLive = null ;
		List<Brokerage> brokList = null ;
		List<EaProgram> eaList = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			userLive = userLiveService.selectUserLive(userId, userLiveId);
			brokList = brokService.selectBrokerageList();
			eaList = eaService.selectEaProgramList();
			map.put("email", userLive.getUserId());
			map.put("userLiveId", userLive.getUserLiveId());
			map.put("eaId", userLive.getEaId());
			map.put("brokId", userLive.getBrokId());
			map.put("status", userLive.getStatus());
			map.put("count", userLive.getCount());
			map.put("eaList", eaList);
			map.put("brokList", brokList);
			map = this.addMapKey(map, request.getSession(false));
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
			eaList = eaService.selectEaProgramList();;
			map.put("email", email);
			map.put("leaderEmail", leaderEmail);
			map.put("eaList", eaList);
			map.put("brokList", brokList);
			map = this.addMapKey(map, request.getSession(false));	
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
		String userLiveId = map.get("userLiveId")!= null ? map.get("userLiveId").trim() : null  ;
		logger.debug("userLiveId : " + userLiveId);
		String eaId = map.get("eaId")!= null ? map.get("eaId").trim() : null  ;
		logger.debug("eaId : " + eaId);
		String brokId = map.get("brokIdHidden")!= null ? map.get("brokIdHidden").trim() : null  ;
		logger.debug("brokId : " + brokId);
		String status = map.get("status")!= null ? map.get("status").trim() : "0"  ;
		logger.debug("status : " + status);
		String count = map.get("count")!= null ? map.get("count").trim() : null  ;
		logger.debug("count : " + count);
		UserLive userLive = null ;
		int countSize = 0 ;
		Date sysDate = new Date();
		UserProfile userProfile = null ;
	    try {
	    	String url = null ;
			url = checkSession(request);
			if (url != null) {
				throw new TimeoutException();
			}
	    	userLive = new UserLive();
	    	userProfile = userProfileService.selectUserProfile(email);
	    	if (userProfile == null) {
	    		throw new Exception("新增失敗");
	    	} 
	    	if (userProfile != null) {
		    	userLive.setUserStamp(userProfile.getChineseName());
	    	} 
	    	userLive.setUserId(email);
	    	userLive.setUserLiveId(userLiveId);
	    	userLive.setStatus(new Integer(status));
	    	userLive.setBrokId(new Integer(brokId));
	    	userLive.setEaId(new Integer(eaId));
	    	userLive.setUserStamp("System");
	    	userLive.setDateStamp(sysDate);
	    	userLive.setCount(new Integer(count));
	    	countSize = userLiveService.updateUserLive(userLive) ;
	    	if (countSize > 0) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","");
	    	} else {
	    		throw new Exception("新增失敗");
	    	}
	    	
		} catch (TimeoutException e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","timeout");
			obj.put("errorMessage","系統愈時;請重新登入");
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
		logger.debug("eaId : " + eaId);
		String brokId = map.get("brokId")!= null ? map.get("brokId").trim() : null  ;
		logger.debug("brokId : " + brokId);
		String status = map.get("status")!= null ? map.get("status").trim() : null  ;
		logger.debug("status : " + status);
		String count = map.get("count")!= null ? map.get("count").trim() : "0"  ;
		logger.debug("count : " + count);
		String email = map.get("email")!= null ? map.get("email").trim() : null  ;
		logger.debug("userId : " + email);
		UserLive userLive = new UserLive() ;;
		Date sysDate = new Date();
		int insertCount = 0 ;
		UserProfile userProfile = null ;
	    try {
	    	String url = null ;
			url = checkSession(request);
			if (url != null) {
				throw new TimeoutException();
			}
	    	userProfile = userProfileService.selectUserProfile(email);
	    	if (userProfile == null) {
	    		throw new Exception("新增失敗");
	    	} 
	    	if (userProfile != null) {
	    		userLive.setCrUser(userProfile.getChineseName());
		    	userLive.setUserStamp(userProfile.getChineseName());
	    	} 
	    	userLive.setUserId(email);
	    	userLive.setUserLiveId(userLiveId);
	    	userLive.setEaId(new Integer(eaId));
	    	userLive.setBrokId(new Integer(brokId));
	    	userLive.setStatus(new Integer(status));
	    	userLive.setCount(new Integer(count));
	    	userLive.setCrDate(sysDate);
	    	userLive.setDateStamp(sysDate);
	    	insertCount = userLiveService.insertUserLive(userLive) ;
	    	if (insertCount > 0) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","");
	    	} else {
	    		throw new Exception("新增失敗");
	    	}
	    	
		} catch (TimeoutException e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","timeout");
			obj.put("errorMessage","系統愈時;請重新登入");
		} catch (Exception e) {
			logger.error("insertUserLive error : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("insertUserLive end !!!");
		return obj.toString();
	}
	
	@RequestMapping(value="/queryUserLiveProfile", method = RequestMethod.POST)
	public ModelAndView queryUserLiveProfile(@RequestParam(value="email",required=false) String email ,@RequestParam(value="chineseName",required=false) String chineseName , HttpServletRequest request){
		logger.debug("queryUserLiveProfile start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserProfile> list = null ;
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				return new ModelAndView(url, map);
			}
			
			list = userProfileService.selectTotalList(email, chineseName);
			map.put("list", list);
			map.put("email", email);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
			map.put("chineseName", chineseName);
			map = this.addMapKey(map, request.getSession(false));
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
			map = this.addMapKey(map, request.getSession(false));
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
			map = this.addMapKey(map, request.getSession(false));
		} catch (Exception e) {
			logger.debug("queryUserLiveByLeaderUpdate error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("queryUserLiveByLeaderUpdate end !!!");
		return new ModelAndView("pages/userLiveData", map);
	}
	
	@RequestMapping(value="/userLiveDelete")
	@ResponseBody
	public String userLiveDelete(@RequestBody String userId,HttpServletRequest request){
		logger.debug("userLiveDelete start !!!");
		JSONObject obj = new JSONObject();
		try {
			String url = null ;
			url = checkSession(request);
			if (url != null) {
				throw new TimeoutException();
			}
			userProfileService.deleteUserProfile(userId);
			obj.put("success", "success");
			obj.put("errorMessage", "");
		} catch (TimeoutException e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","timeout");
			obj.put("errorMessage","系統愈時;請重新登入");
		} catch (Exception e) {
			logger.debug("userLiveDelete error : " + e.getMessage());
			logger.debug(getTrace(e));
			obj.put("success", "error");
			obj.put("errorMessage", e.getMessage());
		}
		logger.debug("userLiveDelete end !!!");
		return obj.toString();
	}
	
	
	@RequestMapping(value="/selectUserLiveQry", method = RequestMethod.POST)
	public ModelAndView selectUserLiveQry(@RequestParam(value="email",required=false) String email , @RequestParam(value="leaderEmail",required=false) String leaderEmail ,@RequestParam(value="chineseName",required=false) String chineseName ,
										  @RequestParam(value="eaProgram",required=false) String eaProgram , @RequestParam(value="brokerAge",required=false) String brokerAge , 
										  HttpServletRequest request){
		logger.debug("selectUserLiveQry start !!!");
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
			
			list = userLiveService.selectUserLiveQry(email,eaProgram,brokerAge);
			brokList = brokService.selectBrokerageList();
			eaList = eaService.selectEaProgramList();
			map.put("list", list);
			map.put("brokList", brokList);
			map.put("eaList", eaList);
			map.put("email", email);
			map.put("leaderEmail", leaderEmail);
			map.put("chineseName", chineseName);
			map.put("eaProgram", eaProgram);
			map.put("brokerAge", brokerAge);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
			map = this.addMapKey(map, request.getSession(false));
		} catch (Exception e) {
			logger.debug("selectUserLiveQry error : " + e.getMessage());
			logger.debug(getTrace(e));
		}
		
		logger.debug("selectUserLiveQry end !!!");
		return new ModelAndView("pages/userLiveData2", map);
	}
	
	
	@RequestMapping(value="/selectUserLivetInit", method = RequestMethod.POST)
	public ModelAndView selectUserLivetInit(@RequestParam(value="email",required=false) String email , @RequestParam(value="leaderEmail",required=false) String leaderEmail ,@RequestParam(value="chineseName",required=false) String chineseName ,HttpServletRequest request){
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
			
			list = userLiveService.selectUserLiveList(email);
			brokList = brokService.selectBrokerageList();
			eaList = eaService.selectEaProgramList();
			map.put("list", list);
			map.put("brokList", brokList);
			map.put("eaList", eaList);
			map.put("email", email);
			map.put("leaderEmail", leaderEmail);
			map.put("chineseName", chineseName);
			if (list != null && !list.isEmpty()) {
				map.put("userSize", list.size());
			}else {
				map.put("userSize", 0);
			}
			map = this.addMapKey(map, request.getSession(false));
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
		String email 	= map.get("email")    == null? "" : map.get("email").trim();
		UserLive userLive = null ;
	    try {
	    	if ( "".equals(userLiveId)) {
	    		throw new Exception("真倉帳號不能為空");
	    	} else {
	    		userLive = userLiveService.checkUserLiveId(userLiveId,email);
	    	}

	    	if (userLive == null) {
	    		obj.put("success","success");
	    		obj.put("errorMessage","此帳號尚未申請");
	    		obj.put("resultValue","true");
	    	} else {
	    		obj.put("success","error");
	    		obj.put("resultValue","false");
	    		obj.put("errorMessage","此帳號已申請");
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
	@RequestMapping(value="/deleteUserLive", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUserLive(@RequestBody Map<String,String> map ,HttpServletRequest request){
		logger.debug("deleteUserLive start !!!");
		JSONObject obj = new JSONObject();
		String delUserLiveId 	= map.get("delUserLiveId")    == null? "" : map.get("delUserLiveId").trim();
		String delUserId 	= map.get("delUserId")    == null? "" : map.get("delUserId").trim();
	    try {
	    	String url = null ;
			url = checkSession(request);
			if (url != null) {
				throw new TimeoutException();
			}
	    	userLiveService.deltetUserLive(delUserId, delUserLiveId) ;
	    	obj.put("success","success");
	    	obj.put("errorMessage","");
		} catch (TimeoutException e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","timeout");
			obj.put("errorMessage","系統愈時;請重新登入");
		} catch (Exception e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("deleteUserLive end !!!");
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
