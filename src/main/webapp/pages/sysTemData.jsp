<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/noCache.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="/include/pageHeader.jsp"%>
<title>登入</title>
<script>
$(document).ready(function(){
 	//jQuery('#formLogin').validationEngine();//'attach', {promptPosition : "center", autoPositionUpdate : true});

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

   $("#btnUpdate").click(function(){
	   edit();
	});

   

});

function edit(){
	if ($("#defaultPassword").val() == "") {
		alert("請輸入就密碼");
		return ;
	}
	if ($("#confrimPassword").val() == "") {
		alert("請輸入新密碼");
		return ;
	}
	if ($("#defaultPassword").val() == $("#confrimPassword").val()) {
		alert("新舊密碼不能相同");
		return ;
	}
	$("#btnUpdate").attr("disabled",true);
	var data1={"email" : $("#email").val(),
			   "defaultPassword" : $("#defaultPassword").val(),
			   "confrimPassword" : $("#confrimPassword").val()
			  };
	chceckPassword(data1);
}

function chceckPassword(data1){
		$.ajax({
         type : "post",
         url : "/chungli2/checkPassword",
         cache : false,
         data : $.toJSON(data1),
         dataType : 'json',
         contentType : "application/json",
         success : function(result) {
         	if(result.success=="success"){
	    	      alert("密碼輸入正確");
	    	      editPassword(data1) ;
             } else {
         		 alert("密碼輸入錯誤;請重新輸入");
         		 $("#btnUpdate").attr("disabled",false);
                 $("#defaultPassword").val("");
         	}
         },

         error : function(result) {
         }

     });
}

function editPassword(data1){
	$.ajax({
     type : "post",
     url : "/chungli2/editPassword",
     cache : false,
     data : $.toJSON(data1),
     dataType : 'json',
     contentType : "application/json",
     success : function(result) {
     	if(result.success=="success"){
    	      alert("密碼修改成功");
    	      $("#btnUpdate").attr("disabled",false);
         }  else if (result.success=="timeout") {
        	 $("#btnUpdate").attr("disabled",false);
           	 alert("修改失敗  :  "+ result.errorMessage);
          	 location.href='/chungli2/logOut';
          } else {
     		alert("密碼修改失敗 :  "+ result.errorMessage);
     		 $("#btnUpdate").attr("disabled",false);
     		 $("#defaultPassword").val("");
     	}
     },

     error : function(result) {
     }

 });
}
</script>
</head>


<%@ include file="/pages/dataInit.jsp"%>

<form id="formUser" action="" method="post" target="_self" >
	<center>
        <table border="0" class="tblInTD" style="width:400px;">
            <tr>
                <td  style="width:400px;" align="left" colspan="2" nowrap="nowrap"><font size="3">帳&nbsp;&nbsp;號(E-Mail)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type="text" style="width:200px;background-color:#D3D3D3;color: red"  id="email" name="email"  value="${email}" readonly="readonly" /></td>
            </tr>
             <tr>
                <td  style="width:400px;" align="left" colspan="2" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td  style="width:400px;" align="left" colspan="2" nowrap="nowrap"><font size="3">會員密碼(舊)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type="password" style="width:200px" id="defaultPassword" name="defaultPassword"  maxlength="20"/></td>
            </tr>
            <tr>
                <td  style="width:400px;" align="left" colspan="2" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
             <tr>
                <td  style="width:400px;" align="left" colspan="2" nowrap="nowrap"><font size="3">會員密碼(新)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type="text" style="width:200px" id="confrimPassword" name="confrimPassword" maxlength="20"/></td>
            </tr>
            <tr>
                <td  style="width:400px;" align="left" colspan="2" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
             <tr>
                <td style="width:400px;" align="center" colspan="2">
                	<input type="button" id="btnUpdate" value="修改密碼" style="width:100px;" />
                </td>
              
            </tr>
            
            
        </table>
        
           
 	</center>
</form>
</body>

</html>
