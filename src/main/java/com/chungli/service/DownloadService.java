package com.chungli.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chungli.dto.Brokerage;
import com.chungli.dto.BrokerageExample;
import com.chungli.mapper.BrokerageMapper;



@Service
public class DownloadService {
	private static Logger logger = Logger.getLogger(DownloadService.class);
	
	private static final ResourceBundle bundle  = ResourceBundle.getBundle("file");
	
	private String getCSVName(String userLiveId,String eaId ,String brokId){
		StringBuffer fileName = new StringBuffer()  ;
		String brokName = null ;
		String eaName = null ;
		String filePath = null ;
		Map<String,String> map  = getMap();
		if ("1".equals(brokId)) {
			brokName = map.get("GoMarket.brokName");
			filePath = map.get("GoMarket.filePath");
		} else {
			brokName = map.get("Synergy.brokName");
			filePath = map.get("Synergy.filePath");
		}
		if ("1".equals(eaId)) {
			eaName = map.get("BlueSky");
		} else if ("2".equals(eaId)){
			eaName = map.get("Laser");
		} else if ("3".equals(eaId)){
			eaName = map.get("EngleEye");
		} else {
			eaName = map.get("Warriors");
		}
		fileName.append(filePath);
		fileName.append(brokName);
		fileName.append("_");
		fileName.append(eaName);
		fileName.append("_");
		fileName.append(userLiveId);
		fileName.append(".csv");
		
		return fileName.toString() ;
	}
	
	private Map<String,String> getMap(){
		Set<String> set = bundle.keySet();
		Map<String , String> map = new HashMap<String , String>();
        for (String key : set) {
        	String value = bundle.getString(key) ;
        	map.put(key,value);
        }  
		return map;
	}
	
	public File getCSVFile(String email , String userLiveId,String eaId ,String brokId,String endTime) throws IOException, ParseException{
		File file = new File(getCSVName(userLiveId,eaId,brokId)) ;
		File file1 = null ;
		if (file.exists()) {
			file.delete();
			file.createNewFile();
		} else {
			file.createNewFile();
		}
		
		writeCsvFile(file.getAbsolutePath(),userLiveId,endTime);
		file1 = new File(file.getAbsolutePath());
		return file1 ;
	}
	
	public File getEaFile(String email , String userLiveId,String eaId ,String brokId,String endTime) throws IOException{
		File file = new File(getEaName(eaId)) ;
		return file ;
	}
	
	private String getEaName(String eaId){
		StringBuffer fileName = new StringBuffer()  ;
		String eaName = null ;
		String filePath = null ;
		Map<String,String> map  = getMap();
		
		if ("1".equals(eaId)) {
			eaName = map.get("BlueSkyRar");
		} else if ("2".equals(eaId)){
			eaName = map.get("LaserRar");
		} else if ("3".equals(eaId)){
			eaName = map.get("EngleEyeRar");
		} else {
			eaName = map.get("WarriorsRar");
		}
		fileName.append(eaName);	
		return fileName.toString() ;
	}
	
	
	private void writeCsvFile( String fileName,String userLiveId,String endTime) throws IOException, ParseException {
		 FileWriter   fileWriter = null ;
		 try {
	          fileWriter = new FileWriter(fileName);
		      // write TransType  
		      fileWriter.append(userLiveId);
		      fileWriter.append(",");
		      // write addEncodeIng
		      fileWriter.append(addEncoding(userLiveId));
		      fileWriter.append(",");
		      // write endTime()
		      fileWriter.append(getEndTime(endTime));

              logger.debug("CSV file was created successfully !!!");
	         } finally {
	        	try {
	        	     fileWriter.flush();
	        	     fileWriter.close();
	        	} catch (IOException e) {
	        		logger.error("Error while flushing/closing fileWriter !!!");
	        	     e.printStackTrace();
	        	}          
	       }
	 }
	 private String addEncoding(String userLiveId){
		 StringBuffer buffer = new StringBuffer("");
		 int uId = Integer.valueOf(userLiveId)*101;
		 int uIdStart = (int)Math.random()*9999 + 30154;
		 int uIdEnd = (int)Math.random()*8888 + 21789;
		 buffer.append(uIdStart);
		 buffer.append(uId);
		 buffer.append(uIdEnd);
		 return buffer.toString();
	 }
	 
	 private String getEndTime(String endTime) throws ParseException{
        long monthSecond = (long)30*24*60 ;
        long localTime = (long)0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd") ;
        localTime = (long)sdf.parse(endTime).getTime()/1000 ;
        localTime = localTime-28800 + monthSecond;
        return "" + localTime ;
	 }
}
