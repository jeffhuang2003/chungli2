<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/noCache.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="/include/pageHeader.jsp"%>
<title>會員新增</title>
<script>
$(document).ready(function(){
 	jQuery('#formUser').validationEngine();//'attach', {promptPosition : "center", autoPositionUpdate : true});
 	$("#eaId").val("${eaId}");
 	$("#brokIdHidden").val("${brokId}");
 	$("#brokId").val("${brokId}");
 	$("#status").val("${status}");
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

    //離開
    $("#btnLeave").click(function(){
    	$("#formQueryUser").attr("action","/chungli2/selectUserLivetInit");
    	$("#formQueryUser").submit();
    });
    //新增
    $("#btnUpdate").click(function(){
    	  $("#btnUpdate").attr("disabled",true);
  		if ($("#eaId").val() == "-1") {
  			 alert("請選擇EA程式");
  			$("#btnUpdate").attr("disabled",false);
     	   return false ;
        }
  		if ($("#brokId").val() == "-1") {
  			alert("請選券商");
  			$("#btnUpdate").attr("disabled",false);
      	   return false ;
        }
  		if ($("#status").val() == "-1") {
  			alert("請選申請狀態");
  			$("#btnUpdate").attr("disabled",false);
      	   return false ;
        }
  		if ($("#count").val().trim() == "" || isNaN(Number($("#count").val().trim()))) {
  			alert("入金金額必須為數字");
  			$("#btnUpdate").attr("disabled",false);
      	   return false ;
        } else {
            if ($("#count").val().trim().indexOf(".") !=-1) {
            	alert("入金金額必須為整數");
            	$("#btnUpdate").attr("disabled",false);
            	 return false ;
            }
        }
		$.ajax({
            type : "post",
            url : '/chungli2/updateUserLive',
            cache : false,
            data : $.toJSON($('#formUser').serializeObject()),
            dataType : 'json',
            contentType : "application/json",
            success : function(result) {
            	if(result.success=="success"){
            		$("#btnUpdate").attr("disabled",false);
            		$("#email").val(result.email);
            		alert("編輯成功 ");
            	} else if (result.success=="timeout") {
            		    $("#btnUpdate").attr("disabled",false);
  	            		alert("新增失敗  :  "+ result.errorMessage);
  	            		location.href='/chungli2/logOut';
  	            } else {
            		$("#btnUpdate").attr("disabled",false);
            		alert("編輯失敗  :  "+ result.errorMessage);;
            	}
            },

            error : function(result) {

            }

        });
    });
});

function checkEmail(obj){
	  var re1 =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9])+$/; 
	   if (re1.test($(obj).val()) != true) {
	   	  alert("你的電子郵件格式不合！");
	      $(obj).focus();   
	      return false ;
	   }
       
	   //email被修改
	   var data = {"email":$(obj).val()};
	   $.ajax({
           type : "post",
           url : '/chungli2/checkEmail',
           cache : false,
           data :  $.toJSON(data),
           dataType : 'json',
           contentType : "application/json",
           success : function(result) {
           	if(result.success=="success"){
           	 alert("檢查結果 : "+ result.errorMessage);;
           	} else {
           	 alert("檢查結果 : "+ result.errorMessage);;
           	 $(obj).focus();  	
           	}
           },

           error : function(result) {
        	 alert("檢查結果 : "+ result.errorMessage);;  
        	 $(obj).focus();  
           }

       });                              
}
</script>
</head>


<%@ include file="/pages/dataInit.jsp"%>

<form id="formUser" action="" method="post" target="_self" >
   <center>
   <br/>
   <br/>
   <br/>
   <fieldset>
         <legend>編輯</legend>
                  <table border="0" class="tblInTD" style="width:400px;">
            <tr>
                <td  style="width:100px" align="left"><font size="3">帳號&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text" id="email" name="email"   value="${email}" readonly="readonly" style="width:150px;color:red;background-color:#D3D3D3;"/></td>
            </tr>
             <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">真倉帳號&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text"  id="userLiveId" name="userLiveId" value="${userLiveId}" readonly="readonly" style="width:150px;color:red;background-color:#D3D3D3;"/></td>
            </tr>
             <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">申請券商&nbsp;&nbsp;&nbsp;</font></td>
                 <td style="width:200px" align="left">     
                   <select name="brokId" id="brokId"  style="width : 150px; color: red;background-color:#D3D3D3;" disabled>
                     <option value="-1" selected>---請選擇---</option>
                     <c:forEach items="${brokList}" var="brokAge" varStatus="status" >
                              <option value="${brokAge.brokId}">${brokAge.brokName}</option>
                     </c:forEach>
                   </select>
                <input type="hidden" id="brokIdHidden" name="brokIdHidden" style="width:100px" />
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
  			<tr>
                <td style="width:100px" align="left"><font size="3">智能程式代號&nbsp;&nbsp;</font></td>
                <td style="width:200px" align="left">     
                   <select name="eaId" id="eaId"  style="width : 150px;">
                     <option value="-1" selected>---請選擇---</option>
                     <c:forEach items="${eaList}" var="eaProgram" varStatus="status">
                              <option value="${eaProgram.eaId}">${eaProgram.eaName}</option>
                     </c:forEach>
                   </select>
                </td>
                <td></td>
            </tr>
            <tr>
                <td  style="width:200px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">申請狀態</font></td>
                <td style="width:200px" align="left">
                   <select name="status" id="status"  style="width : 150px;">
                    	<option value="-1" selected>---請選擇---</option>
                        <option value="1">新申請</option>
                        <option value="2">已入金</option>
                        <option value="3">封包中</option>
                        <option value="4">模擬盤</option>
                        <option value="5">已上線</option>
                        <option value="6">未啟用</option>
                        <option value="7">已啟用</option>
                    	</select>
                </td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">入倉金額</font></td>
                <td style="width:200px" align="left">
                  <input type="text" style="width:150px"  id="count" name="count" value='${count}' />
                </td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
             <tr>
                <td style="width:300px" colspan="2" align="center">
                	<input type="button" id="btnUpdate" value="編輯" style="width:100px" />
                	<input type="button" id="btnCancel" value="清空" style="width:100px"/>
                	<input type="button" id="btnLeave" value="離開" style="width:100px"/>
                </td>
            </tr>
        </table>
   </fieldset>
     </center>  
</form>
<form id="formQueryUser" action="" method="post" target="_self" >
   	<input type="hidden" style="width:200px" id="email1" name="email"  value="${email}" readonly="readonly" style="width:150px"/>
</form>
</body>

</html>
