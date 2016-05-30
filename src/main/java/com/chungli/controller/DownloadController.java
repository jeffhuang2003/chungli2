package com.chungli.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.bsf.util.IOUtils;
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
import com.chungli.dto.UserPay;
import com.chungli.dto.UserProfile;
import com.chungli.service.BrokService;
import com.chungli.service.DownloadService;
import com.chungli.service.EaService;
import com.chungli.service.UserLiveService;
import com.chungli.service.UserPayService;

@Controller
public class DownloadController extends BaseController{
	private static Logger logger = Logger.getLogger(DownloadController.class);
	
	
	
	@Autowired
	private UserPayService userPayService ;
	@Autowired
	private UserLiveService userLiveService ;
	@Autowired
	private EaService eaService ;
	@Autowired
	private BrokService brokService;
	@Autowired
	private DownloadService downloadService;
	
	@RequestMapping(value="/checkDownloadCSV", method = RequestMethod.POST)
	@ResponseBody
	public String checkDownloadCSV(@RequestBody Map<String,String> map,HttpServletRequest request){
		logger.debug("checkDownloadCSV start !!!");
		JSONObject obj = new JSONObject();
		String email = map.get("email") != null ? map.get("email").trim() : null ;
		logger.debug("email : " + email);
		String userLiveId = map.get("userLiveId")!= null ? map.get("userLiveId").trim() : null  ;
		logger.debug("userLiveId : " + userLiveId);
		String eaId = map.get("eaId")!= null ? map.get("eaId").trim() : null  ;
		logger.debug("eaId : " + eaId);
		UserPay userPay = null ;
		List<UserLive> list = null ;
		String url = null ;
	    try {
			url = checkSession(request);
			if (url != null) {
				throw new TimeoutException();
			}
			userPay = userPayService.getMaxUserPay(email, getSysdDate(), eaId);
			list = userLiveService.selectUserLive(email, userLiveId,Integer.valueOf(eaId));
			if (userPay != null && (list != null && !list.isEmpty())) {
				//有下載的權限
				obj.put("success","success");
				obj.put("errorMessage","");
			    obj.put("eaId",eaId);
			    obj.put("userLiveId",userLiveId);
			} else {
				//已無下載的權限
				obj.put("success","error");
				obj.put("errorMessage","無下載權限");
			}

		} catch (TimeoutException e) {
			logger.error("checkDownloadCSV error : " + e.getMessage());
			logger.error("errorMessage : " + e.getMessage());
			obj.put("success","timeout");
			obj.put("errorMessage","系統愈時;請重新登入");
		} catch (Exception e) {
			logger.error("checkDownloadCSV error : " + e.getMessage());
			obj.put("success","error");
			obj.put("errorMessage","系統發生錯誤;請通知IT人員!!!");
		}

		logger.debug("checkDownloadCSV end !!!");
		return obj.toString();
	}
	
	@RequestMapping(value="/downdloadCSV", method = RequestMethod.POST)
	public ModelAndView downdloadCSV(@RequestParam(value="email",required=false) String email,@RequestParam(value="userLiveId",required=false) String userLiveId,
									 @RequestParam(value="eaId",required=false) String eaId,@RequestParam(value="brokId",required=false) String brokId , 
									 HttpServletRequest request ,HttpServletResponse response){
		logger.debug("downdloadCSV start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Brokerage> brokList = null ;
		List<EaProgram> eaList = null ;
		HttpSession session = null ;
		UserPay userPay = null ;
		File file = null ;
		FileInputStream input = null ;
		OutputStream output = null ;
		try {
			String url = null ;
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
			brokList = brokService.selectBrokerageList();
			eaList = eaService.selectEaProgramList();
			map.put("email", email);
			map.put("eaId", eaId);
			map.put("userLiveId", userLiveId);
			map.put("borkId", brokId);
			map.put("eaList", eaList);
			map.put("brokList", brokList);
			map = this.addMapKey(map, request.getSession(false));	
			
			
			userPay = userPayService.getMaxUserPay(email, getSysdDate(), eaId);
			file = downloadService.getCSVFile(email ,userLiveId,eaId ,brokId,userPay.getEndTime());
			
			ServletContext context = session.getServletContext();
			input = new FileInputStream(file) ;
			response.setContentLength((int)file.length());
			response.setContentType(file.getAbsolutePath());
			String key    = "Content-Disposition";
			String value  = String.format("attachment;fileName=\"%S\"", file.getName());
			response.setHeader(key, value);
			
			output = response.getOutputStream();
			org.apache.commons.io.IOUtils.copy(input, output) ;
			
			
		} catch (Exception e) {
			logger.error("downloadCSV error : " + e.getMessage());
			logger.error(getTrace(e));
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (Exception e) {
				logger.error(getTrace(e));
			} 
			
		}
		
		
		logger.debug("downloadCSV end !!!");
		return new ModelAndView("pages/downloadCSV", map);
	}
	
	
	@RequestMapping(value="/downdloadEa", method = RequestMethod.POST)
	public ModelAndView downloadEa(@RequestParam(value="email",required=false) String email,@RequestParam(value="userLiveId",required=false) String userLiveId,
									 @RequestParam(value="eaId",required=false) String eaId,@RequestParam(value="brokId",required=false) String brokId , 
									 HttpServletRequest request ,HttpServletResponse response){
		logger.debug("downloadEa start !!!");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Brokerage> brokList = null ;
		List<EaProgram> eaList = null ;
		HttpSession session = null ;
		UserPay userPay = null ;
		File file = null ;
		FileInputStream input = null ;
		OutputStream output = null ;
		try {
			String url = null ;
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
			brokList = brokService.selectBrokerageList();
			eaList = eaService.selectEaProgramList();
			map.put("email", email);
			map.put("eaId", eaId);
			map.put("userLiveId", userLiveId);
			map.put("borkId", brokId);
			map.put("eaList", eaList);
			map.put("brokList", brokList);
			map = this.addMapKey(map, request.getSession(false));	
			
			
			userPay = userPayService.getMaxUserPay(email, getSysdDate(), eaId);
			file = downloadService.getEaFile(email ,userLiveId,eaId ,brokId,userPay.getEndTime());
			
			ServletContext context = session.getServletContext();
			input = new FileInputStream(file) ;
			response.setContentLength((int)file.length());
			response.setContentType("application/octet-stream");
			String key    = "Content-Disposition";
			String value  = String.format("attachment;fileName=\"%S\"", file.getName());
			response.setHeader(key, value);
			
			output = response.getOutputStream();
			org.apache.commons.io.IOUtils.copy(input, output) ;
			
			
		} catch (Exception e) {
			logger.error("downdloadEa error : " + e.getMessage());
			logger.error(getTrace(e));
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (Exception e) {
				logger.error(getTrace(e));
			} 
			
		}
		
		
		logger.debug("downdloadEa end !!!");
		return new ModelAndView("pages/downloadCSV", map);
	}
	
	
	

	private String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
	


}
