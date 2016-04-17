<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/noCache.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="/include/pageHeader.jsp"%>
<title>登入</title>
<script>
$(document).ready(function(){
 	//jQuery('#formLogin').validationEngine();//'attach', {promptPosition : "center", autoPositionUpdate : true});
	$('#formLogin').submit();
 	});

</script>
</head>




<form id="formLogin" action="/chungli2/loginInit" method="post" target="_self" >
	<center>
        <table border="0" class="tblInTD" style="width:300px;" >
 			<tr>
                <td width="100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td width="150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td width="100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td width="150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td width="100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td width="150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td width="100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td width="150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td width="2500px" colspan="2" align="center"><font size="6">會員登入</font></td>
            </tr>
            <tr>
                <td width="100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td width="150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td  width="100px"><font size="3">帳&nbsp;&nbsp;號(E-Mail)</font></td><td width="150px"><input type="text" width="150px" id="email" name="email"  maxlength="50" class="validate[required]"/></td>
            </tr>
            <tr>
                <td width="100px"><font size="3">密&nbsp;&nbsp;碼&nbsp;&nbsp;</font></td><td width="150px"><input type="text" width="150px" id="password" name="password" maxlength="50" class="validate[required]"/></td>
            </tr>
            <tr>
                <td width="100px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td width="150px" align="right"><input type=submit id="btnLogin" value="確定
                " width="100px" />
                </td>
            </tr>
        </table>
      </center>
</form>
</body>

</html>
