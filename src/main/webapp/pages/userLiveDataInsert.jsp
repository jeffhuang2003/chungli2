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
 	jQuery('#formUserLive').validationEngine();//'attach', {promptPosition : "center", autoPositionUpdate : true});
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

    //清空
    $("#btnCancel").click(function(){
    	$("#userLiveId").val("");
    	$("#eaId").val("-1");
    	$("#brokId").val("-1");
    	$("#status").val("-1");
    	$("#count").val("");
    	$("#resultValue").val("");
    	$("#btnInsert").attr("disabled",false);
    });
    //離開
    $("#btnLeave").click(function(){
    	$("#formQueryUser").attr("action","/chungli2/selectUserLivetInit");
    	$("#formQueryUser").submit();
    });
    
    //新增
    $("#btnInsert").click(function(){
    	  $("#btnInsert").attr("disabled",true);
  		if (!$('#formUserLive').validationEngine('validate')) {
  			$("#btnInsert").attr("disabled",false);
  		     return;  
  		} 
  		if ($("#eaId").val() == -1) {
  	  		alert("請選擇智能程式代號!!!");
  	  		$("#btnInsert").attr("disabled",false);
  	  	 	return;
  	  	}
  		if ($("#brokId").val() == -1) {
  	  		alert("請選擇申請券商!!!");
  	  		$("#btnInsert").attr("disabled",false);
  	  	 	return;
  	  	}
  		if ($("#status").val() == -1) {
  	  		alert("請選擇申請狀態!!!");
  	  		$("#btnInsert").attr("disabled",false);
  	  	 	return;
  	  	}
  	  	checkUserLiveId();
    });
});

function checkUserLiveId(){
	   //userLiveId被修改
	   var data = {"userLiveId": $("#userLiveId").val(),
			   	   "userId"    : $("#userId").val()
			      };
	   $.ajax({
         type : "post",
         url : '/chungli2/checkUserLiveId',
         cache : false,
         data :  $.toJSON(data),
         dataType : 'json',
         contentType : "application/json",
         success : function(result) {
         	if(result.success=="success"){
         	 	alert("檢查結果 : "+ result.errorMessage);
      	  		//尚未使用
      			$.ajax({
      	            type : "post",
      	            url : '/chungli2/insertUserLive',
      	            cache : false,
      	            data : $.toJSON($('#formUserLive').serializeObject()),
      	            dataType : 'json',
      	            contentType : "application/json",
      	            success : function(result) {
      	            	if(result.success=="success"){
      	            		alert("新增成功 ");
      	            	} else if (result.success=="timeout") {
                		    $("#btnInsert").attr("disabled",false);
      	            		alert("新增失敗  :  "+ result.errorMessage);
      	            		location.href='/chungli2/logOut';
      	            	} else {
      	            		$("#btnInsert").attr("disabled",false);
      	            		alert("新增失敗  :  "+ result.errorMessage);;
      	            	}
      	            },

      	            error : function(result) {

      	            }

      	        });
         	} else {
	         	 alert("檢查結果 : "+ result.errorMessage);	  
	         	$("#resultValue").val(result.resultValue);
         	}
         },

         error : function(result) {
      	 		alert("檢查結果 : "+ result.errorMessage);
      	 		$("#resultValue").val(result.resultValue);
         }
     });                                 
}

</script>
</head>


<%@ include file="/pages/dataInit.jsp"%>

<form id="formUserLive" action="" method="post" target="_self" >
   <center>
   <br/>
   <br/>
   <br/>
   <fieldset>
         <legend>新增</legend>
         <table border="0" class="tblInTD" style="width:400px;">
            <tr>
                <td  style="width:100px" align="left"><font size="3">帳號&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text" id="email" name="email"   value="${email}" readonly="readonly" style="width:150px;color:red;background-color:#D3D3D3;"/></td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">真倉帳號&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text" style="width:150px" id="userLiveId" name="userLiveId" class="validate[required,maxSize[10]] text-input" /></td>
            </tr>
             <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">申請券商&nbsp;&nbsp;&nbsp;</font></td>
                 <td style="width:200px" align="left">     
                   <select name="brokId" id="brokId"  style="width : 150px;">
                     <option value="-1" selected>---請選擇---</option>
                     <c:forEach items="${brokList}" var="brokAge" varStatus="status">
                              <option value="${brokAge.brokId}">${brokAge.brokName}</option>
                     </c:forEach>
                   </select>
                </td>
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
                  <input type="text" style="width:150px"  id="count" name="count" value='' />
                </td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
             <tr>
                <td style="width:300px" colspan="2" align="center">
                	<input type="button" id="btnInsert" value="新增" style="width:100px" />
                	<input type="button" id="btnCancel" value="清空" style="width:100px"/>
                	<input type="button" id="btnLeave" value="離開" style="width:100px"/>
                </td>
            </tr>
        </table>
   </fieldset>
     </center>  
     <input type="hidden" id="resultValue" name="resultValue"  value="" /> 
</form>
<form id="formQueryUser" action="" method="post" target="_self" >
	<input type="hidden" style="width:200px" id="email1" name="email"  value="${email}" readonly="readonly" style="width:150px"/>
</form>
</body>

</html>
