package com.chungli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController {
	
	
	public String checkSession(HttpServletRequest request){
		String url = null ;
		HttpSession session = null ;
		if (request != null) {
			session =  request.getSession(false) ;
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
}
