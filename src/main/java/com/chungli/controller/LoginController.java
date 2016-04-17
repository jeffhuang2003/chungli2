package com.chungli.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;






import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;








import com.chungli.dto.UserProfile;
import com.chungli.service.LoginService;

@Controller
public class LoginController extends BaseController{
	private static Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService ;
	
	@RequestMapping(value="/loginInit")
	public ModelAndView loginInit(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String url = null ;
		url = checkSession(request);
		if (url == null) {
			HttpSession session = getSession(request) ;
			if (session != null) {
				UserProfile user = (UserProfile)session.getAttribute("user") ;
				if (user != null ) {
					if (user.getEmail() == null || "".equals(user.getEmail())) {
						url =  "pages/login" ;
					} else if (user.getEmail() == null || "".equals(user.getEmail())) {
						url =  "pages/login" ;
					} else{
						map.put("email", user.getEmail());
						map.put("userSize", -1);
						map.put("chineseName", "");
						url =  "pages/userData" ;
					}
				} else {
					url =  "pages/login" ;
				}
			} else{
				url =  "pages/login" ;
			}
			
		} 
		
		return new ModelAndView(url, map);
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public String  login(@RequestBody Map<String,String> map,HttpServletRequest request){
		logger.debug("login start !!!");
		JSONObject obj = new JSONObject();
		HttpSession session = null ;
		UserProfile user = null ;
		String email = map.get("email").trim() ;
		String pwd = map.get("password").trim() ;
		
	    try {
	    	session = request.getSession() ;
	    	user = loginService.loginUser(email, pwd);
	    	if (user != null) {
	    		session.setAttribute("user", user);
	    		obj.put("success","success");
	    		obj.put("user",user);
	    		obj.put("errorMessage","");
	    	} else {
	    		obj.put("success","error");
	    		obj.put("user",null);
	    		obj.put("errorMessage","使用者登入失敗;無此帳號密碼!!!");
	    	}
	    	
		} catch (Exception e) {
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","error");
			obj.put("user",null);
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}
	 
	    
		logger.debug("login end !!!");
		return obj.toString();
	}
	
}
