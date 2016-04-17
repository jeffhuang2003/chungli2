<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<title>首頁</title>
<link rel="stylesheet" href="css/tt.css" type="text/css"/>
<link rel="stylesheet" href="css/jquery/formValidator/validationEngine.jquery.css" type="text/css"/>
<script>
</script>

</head>
<%@ include file="/include/pageHeader.jsp"%>
<frameset rows="70,860,70" >
	<frame name="banner" noresize="noresize" scrolling="no" src="main/title.jsp" target="contents">
	<frame name="contents" src="login.jsp" target="main">
	<frame name="footer" noresize="noresize" scrolling="no" src="main/title2.jsp" target="contents">
	<noframes>
	<body>

	<p>此網頁使用框架，但是您的瀏覽器不支援框架。</p>

	</body>
	</noframes>
</frameset>
</html>