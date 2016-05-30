package com.chungli.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.chungli.dto.UserProfile;

public class BaseController {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd") ;
	
	public String checkSession(HttpServletRequest request){
		String url = null ;
		HttpSession session = null ;
		if (request != null) {
			session =  request.getSession(false) ;
			if (session != null) {
				session.setMaxInactiveInterval(20*60);	
			  UserProfile user = (UserProfile)session.getAttribute("user") ;
			  if (user == null) {
				  url = "pages/login" ;
			  } 
			}
			if (session == null) {
				url = "pages/login" ;
			} 
		}
		
        return url ;
	}

	public HttpSession getSession(HttpServletRequest request){
		if (request != null) {
			return request.getSession(false) ;
		}
		return null ;
	}
	
	public Map<String,Object> addMapKey(Map<String,Object> map , HttpSession session){
		map.put("tag4", session.getAttribute("tag4"));
		map.put("tag5", session.getAttribute("tag5"));
		return map ;
	}
	
	public static String getSysdDate(){
		return sdf.format(new Date());
	}
}
