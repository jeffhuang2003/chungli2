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
 	jQuery('#formLogin').validationEngine();//'attach', {promptPosition : "center", autoPositionUpdate : true});
 	$.fn.serializeObject = function(){  
        var o = {};  
        var a = this.serializeArray();  
		 $.each(a, function(){  
            if (o[this.name]) {  
                if (!o[this.name].push) {  
                    o[this.name] = [o[this.name]];  
                }  
                o[this.name].push(this.value || '');  
            }  
            else {  
                o[this.name] = this.value || '';  
            }  
        });  
        return o;  
    };  


    
    $("#btnLogin").click(function(){
         $("#btnLogin").attr("disabled",true);
		if (!$('#formLogin').validationEngine('validate')) {
			$("#btnLogin").attr("disabled",false);
		     return;  
		} 
		$.ajax({
            type : "post",
            url : '/chungli2/login',
            cache : false,
            data : $.toJSON($('#formLogin').serializeObject()),
            dataType : 'json',
            contentType : "application/json",
            success : function(result) {
            	if(result.success=="success"){
            		//$.unblockUI();
            		alert("登入成功 ");
	            		//alert("result : " + JSON.stringify(result));
// 						$("#btnSave").attr("disabled", false);
            		//location.href="/chungli2/dataInit?userId="+result.user.userId + "&email="+ result.user.email ;
            		
            		// alert("userId : " + user.userId);
            		 //alert("email : " + user.email);
            		$('#formLogin').attr("action" ,"/chungli2/userDataInit");
            		$('#formLogin').append("<input type=hidden name='userId' id='userId' value='" + result.user.userId +"'></input>");
            		$('#formLogin').submit();
                } else {
            		//$.unblockUI();
            		//alert("result : " + result.toJson());
            		alert("登入失敗  :  "+ result.errorMessage);
            		$("#btnLogin").attr("disabled",false);
//             		//$("#btnSave").attr("disabled", false);
            	}

//             	alert("result : " + JSON.stringify(result));
            },

            error : function(result) {
            	$("#btnLogin").attr("disabled",false);
            	//$.unblockUI();
                //alert("登入失敗  : " + result.map.fail);
            	//alert("登入失敗  :  "+ result.map.errorMessage);
            }

        });
    });
});

</script>
</head>




<form id="formLogin" action="" method="post" target="_self" >
	<center>
        <table border="0" class="tblInTD" style="width:300px;" >
 			<tr>
                <td style="width:100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td style="width:150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td style="width:150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td style="width:150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px">&nbsp;&nbsp;&nbsp;&nbsp;</td><td style="width:150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:250px" colspan="2" align="center"><font size="6">會員登入</font></td>
            </tr>
             <tr>
                <td  style="width:100px"><font size="3">帳&nbsp;&nbsp;號(E-Mail)</font></td><td style="width:150px"><input type="text" style="width:150px" id="email" name="email" class="validate[required]"/></td>
            </tr>
             <tr>
                <td style="width:100px"><font size="3">密&nbsp;&nbsp;碼</font></td><td style="width:150px"><input type="password" style="width:150px" id="password" name="password"class="validate[required]"/></td>
            </tr>
             <tr>
                <td style="width:100px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td style="width:150px" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=button id="btnLogin" value="確定" style="width:50px" />
                </td>
            </tr>
        </table>
      </center>
</form>
</body>

</html>
