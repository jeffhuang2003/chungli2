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
    	$("#chineseName").val("");
    	$("#englishName").val("");
    	$("#phone").val("");
    	$("#userEmail").val("");
    	$("#btnInsert").attr("disabled",false);
    });
    //離開
    $("#btnLeave").click(function(){
    	$("#formQueryUser").attr("action","/chungli2/queryUserProfileByLeaderInsert");
    	$('#formQueryUser').append("<input type=hidden name='leaderEmail' id='leaderEmail' value='" + $("#leaderEmail").val() +"'></input>");
    	$("#formQueryUser").submit();
    });
    //新增
    $("#btnInsert").click(function(){
    	  $("#btnInsert").attr("disabled",true);
  		if (!$('#formUser').validationEngine('validate')) {
  			$("#btnInsert").attr("disabled",false);
  		     return;  
  		} 
  		if (!checkEmail()){
  			$("#btnInsert").attr("disabled",false);
  	    }
    });
});

function checkEmail(){
	
	 // var re1 =   /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/; 
// 	   if (re1.test($("#userEmail").val())) {
// 	   	  alert("你的電子郵件格式不合！");
// 	      $("#userEmail").focus();
// 	      return false ;   
// 	   }
	   var data = {"email" : $("#userEmail").val()};
	   $.ajax({
         type : "post",
         url : '/chungli2/checkEmail',
         cache : false,
         data :  $.toJSON(data),
         dataType : 'json',
         contentType : "application/json",
         success : function(result) {
         	if(result.success=="success"){
         	 	 alert("檢查結果 : "+ result.errorMessage);
         		$.ajax({
      	            type : "post",
      	            url : '/chungli2/insertUserProfile',
      	            cache : false,
      	            data : $.toJSON($('#formUser').serializeObject()),
      	            dataType : 'json',
      	            contentType : "application/json",
      	            success : function(result) {
      	            	if(result.success=="success"){
      	            		$("#btnInsert").attr("disabled",true);
      	            		alert("新增成功 ");
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
	         	 $("#userEmail").focus();   
	         	 return false ;   
         	}
         },

         error : function(result) {
      	 		alert("檢查結果 : "+ result.errorMessage);; 
      	 	 $("#userEmail").focus();  
      	 	 return false ;   
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
         <legend>新增</legend>
         <table border="0" class="tblInTD" style="width:400px;">
            <tr>
                <td  style="width:100px" align="left"><font size="3">會員名&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text" style="width:150px" id="chineseName" name="chineseName" class="validate[required,maxSize[50]] text-input" /></td>
            </tr>
             <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">英文名&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text" style="width:150px" id="englishName" name="englishName" /></td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">電話&nbsp;&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text" style="width:150px" id="phone" name="phone" class="validate[required,maxSize[20]] text-input"/></td>
            </tr>
            <tr>
                <td  style="width:200px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">帳號(E-Mail)</font></td><td style="width:200px" align="left"><input type="text" style="width:150px" id="userEmail" name="userEmail" class="validate[required,maxSize[50]] text-input" /></td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">所屬團隊</font></td>
                <td style="width:200px" align="left">
                   <select name="team" id="team"  style="width : 150px;">
                    	<option value="-1" selected>---請選擇---</option>
                        <option value="1" >台中洪老師</option>
                        <option value="2">台北黃老師</option>
                        <option value="3" >中壢葉老師</option>
                        <option value="4">台南王老師</option>
                    	</select>
                </td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">leader</font></td><td style="width:200px" align="left"><input type="text" style="width:150px;background-color:#D3D3D3;color: red"  id="leaderEmail" name="leaderEmail" value='${leaderEmail}' readonly="readonly"/></td>
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
        
            <table border="0" class="tblInTD" style="width:600px;">
            <tr>
                <td style="width:200px"><input type="hidden" style="width:200px" id="userId" name="userId"  value="${userId}" readonly="readonly" style="width:150px;background-color:black;"/></td>
            </tr>
        </table>
   </fieldset>
     </center>  
</form>
<form id="formQueryUser" action="" method="post" target="_self" >
</form>
</body>

</html>
