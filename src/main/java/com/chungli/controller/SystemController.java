package com.chungli.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
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

import com.chungli.dto.UserProfile;
import com.chungli.service.UserProfileService;



@Controller
public class SystemController extends BaseController{
	private static Logger logger = Logger.getLogger(SystemController.class);
	@Autowired
	private UserProfileService userProfileService;

	@RequestMapping(value="/checkPassword", method = RequestMethod.POST)
	@ResponseBody
	public String checkPassword(@RequestBody Map<String,String> map ,HttpServletRequest request){
		logger.debug("checkPassword start !!!");
		JSONObject obj = new JSONObject();
		String email 	= map.get("email")    == null? "" : map.get("email").trim();
		String defaultPassword 	= map.get("defaultPassword")    == null? "" : map.get("defaultPassword").trim();
		UserProfile  userProfile = null ;
	    try {
	    	String url = null ;
			url = checkSession(request);
			if (url != null) {
				throw new TimeoutException();
			}
	    	userProfile = userProfileService.selectUserProfile(email);

	    	if (userProfile == null || (userProfile.getPassword() == null || "".equals(userProfile.getPassword()))  || !userProfile.getPassword().equals(defaultPassword)) {
	    		if (userProfile == null) {
	    			throw new Exception("此帳號不存在") ;
	    		} else if (userProfile.getPassword() == null || "".equals(userProfile.getPassword())) {
	    			throw new Exception("密碼為空") ;
	    		} else  {
	    			throw new Exception("密碼輸入錯誤") ;
	    		}
	    	} else {
	    		obj.put("success","success");
	    		obj.put("errorMessage","密碼輸入正確");
	    	}
	    	
		} catch (TimeoutException e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","timeout");
			obj.put("errorMessage","系統愈時;請重新登入");
		} catch (Exception e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("checkPassword end !!!");
		return obj.toString();
	}
	
	@RequestMapping(value="/editPassword", method = RequestMethod.POST)
	@ResponseBody
	public String editPassword(@RequestBody Map<String,String> map ,HttpServletRequest request){
		logger.debug("editPassword start !!!");
		JSONObject obj = new JSONObject();
		String email 	= map.get("email")    == null? "" : map.get("email").trim();
		String confrimPassword 	= map.get("confrimPassword")    == null? "" : map.get("confrimPassword").trim();
		UserProfile  userProfile = null ;
		int count = 0 ;
	    try {
	    	userProfile = userProfileService.selectUserProfile(email);
	    	if (userProfile == null || (userProfile.getPassword() == null || "".equals(userProfile.getPassword()))) {
	    		throw new Exception() ;
	    	} else {
	    		userProfile.setPassword(confrimPassword);
		    	count = userProfileService.updateUserProfile(userProfile);
	    		obj.put("success","success");
	    		obj.put("errorMessage","密碼修改成功");
	    	}
		} catch (Exception e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("editPassword end !!!");
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
