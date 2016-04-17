<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/noCache.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="/include/pageHeader.jsp"%>
<title>登入</title>
<style type="text/css">
<!--
#apDiv1 {
	position:absolute;
	width:500px;
	height:115px;
	z-index:1;
}

-->

ul, li {
	margin: 0;
	padding: 0;
	list-style: none;
}
.abgne_tab {
	clear: left;
	width: 700px;
	margin: 10px 0;
}
ul.tabs {
	width: 700px;
	height: 32px;
	border-bottom: 0px solid #999;
	border-left: 0px solid #fff;
}
ul.tabs li {
	float: left;
	height: 31px;
	width : 120px;
	line-height: 31px;
	overflow: hidden;
	position: relative;
	margin-bottom: -1px;	/* 讓 li 往下移來遮住 ul 的部份 border-bottom */
	border: 1px solid #999;
	border-left: 0px solid #999;
	background: #e1e1e1;
	font-size:9pt;
	
}
ul.tabs li a {
	display: block;
	padding: 0 20px;
	color: #000;
	border: 1px solid #fff;
	text-decoration: none;
	width: 120px;
}
ul.tabs li a:hover {
	background: #ccc;
}
ul.tabs li.active  {
	background: #fff;
	border-bottom: 1px solid #fff;
}
ul.tabs li.active a:hover {
	background: #fff;
}
div.tab_container {
	clear: left;
	width: 100px;
	border: 1px solid #999;
	border-top: none;
	background: #fff;
}
div.tab_container .tab_content {
	padding-bottom: 0px;
	padding-left: 0px;
	padding-right: 1px;
	padding-top: 1px;
	overflow-x:hidden;
	overflow-y:auto;
/* 	border-left: 0px solid #999; */

}
div.tab_container .tab_content h2 {
	margin: 0 0 10px;
}
</style>
<script>
$(document).ready(function(){
 	//jQuery('#formLogin').validationEngine();//'attach', {promptPosition : "center", autoPositionUpdate : true});

 	$('ul.tabs li').click(function() {
		var $this = $(this),
			_clickTab = $this.find('a').attr('href');
		$this.addClass('active').siblings('.active').removeClass('active');
		$(_clickTab).stop(false, true).show().siblings().hide();
 		return false;
	}).find('a').focus(function(){
		this.blur();
	});	

    $("#tab1").click(function(){
        $("#formUserInit").submit();
    });

    $("#tab2").click(function(){
    	$("#formUserInit").attr("action","/chungli2/userLiveInit");
        $("#formUserInit").submit();
    });

    $("#tab3").click(function(){
    	$("#formUserInit").attr("action","/chungli2/userTeamInit");
    	$("#formUserInit").append("<input type=hidden name='userEmail'  id='userEmail'  value='" + $("#email").val()  +"'></input>");
    	$("#formUserInit").append("<input type=hidden name='control'  	  id='control'  value='0'></input>"); 
        $("#formUserInit").submit();
    });
    
});

</script>
</head>




<form id="formLogin" action="" method="post" target="_self" >
	<left>
        <table border="0" class="tblInTD" style="width:700px;" >
            <tr>
				<td>
				    <div class="abgne_tab">
                          <ul class="tabs" style="width: 700px">
                            <li><a id='tab1' href="#tab1">會員資料</a></li>
                            <li><a id='tab2'href="#tab2">會員真倉資料</a></li>
                            <li><a id='tab3'href="#tab2">查詢</a></li>
                            <li><a id='tab4'href="#tab3">程式下載</a></li>
                            <li><a id='tab5'href="#tab4">系統</a></li>
                          </ul>
                   </div>       
				</td>
            </tr>
        </table>
      </left>
</form>
<form id="formUserInit" action="/chungli2/userDataInit" method="post" target="_self" >
         <input type="hidden" id="userId" name="userId"  value="${userId}"/>
         <input type="hidden"  id="email" name="email"  value="${email}"/>
</form>
</body>

</html>
