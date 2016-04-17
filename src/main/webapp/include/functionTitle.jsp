<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="java.util.Map" %> 
<%

String fname=request.getRequestURI( );
String functionName = "";
if (fname!=null){
	fname = fname.substring(fname.lastIndexOf("/")+1,fname.indexOf("_"));
	
	Map func = (Map)application.getAttribute("functionNameMapping");//session.getAttribute("functionNameMapping");
	if(func!=null){
		if (func.get(fname)==null){
			functionName=fname;
		}else{
			functionName=func.get(fname).toString();
		}
	}
}

%>
<div class="docTitle">
<input  Type="button"  id="btnSetFrameset" value="←" style="width:30px;float:left;">
<span id="title_left" style="float:left;"><%=fname%></span><span id="title_center" ><%=functionName%></span><span id="title_right"></span></div>



