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

    $("#userLiveId").blur(function(){
    	if ($("#userLiveId").val() != $("#oldUserLiveId").val() ) {
    	    	cleanDowdloadBtn();
    	} 
    });
    $("#eaId").change(function(){
        if ($("#eaId").val() != $("#oldEaId").val()) {
        	cleanDowdloadBtn();
        }
    });
   	
	$("#btnCheck").click(function(){
		if ($("#userLiveId").val() =="") {
			alert("請輸入微小帳號");
			return ;
		}
		if ($("#eaId").val() =="") {
			alert("請選擇EA程式代號");
			return ;
		}
		var data = {"email"         : $("#email").val(),
					"userLiveId"    : $("#userLiveId").val(),
		   	     	"eaId"          : $("#eaId").val()
		};
		checkDownload(data);
	});
    
});

function checkDownload(data){
	$("#btnCheck").attr("disabled",true);
	
	   $.ajax({
	         type : "post",
	         url : '/chungli2/checkDownloadCSV',
	         cache : false,
	         data :  $.toJSON(data),
	         dataType : 'json',
	         contentType : "application/json",
	         success : function(result) {
	         	if(result.success=="success"){
	         		//檢核通過
	         		$("#btnCheck").attr("disabled",false);
	         	 	alert("檢核成功;可以下載");
	         	 	$("#oldEaId").val(result.eaId);
	         	 	$("#oldUserLiveId").val(result.userLiveId);
	         	 	createDowdloadBtn();
	         	 	createDowdloadBtn1();
	         	} else if (result.success=="timeout") {
	         		$("#btnCheck").attr("disabled",false);
	         		cleanDowdloadBtn();
	            	alert("檢核結果  :  "+ result.errorMessage);
	            	location.href='/chungli2/logOut';
	            } else {
	            	 $("#btnCheck").attr("disabled",false);
	            	 cleanDowdloadBtn();
		         	 alert("檢核結果: "+ result.errorMessage);	  
	         	}
	         },
	         error : function(result) {
	        	 	$("#btnCheck").attr("disabled",false);
	        	 	cleanDowdloadBtn();
	      	 		alert("檢核結果: "+ result.errorMessage);
	         }
	     });

		
	     
}

function cleanDowdloadBtn(){
	$("#downloadDiv").empty();
	$("#downloadDiv1").empty();
}
function createDowdloadBtn(){
	$("#downloadDiv").append("<input type=button name='downloadCSV' id='downloadCSV' value='封包下載' style='width:100px' onclick='downdloadCSV()' />");
}
function createDowdloadBtn1(){
	$("#downloadDiv1").append("<input type=button name='downloadEa' id='downloadEa' value='空機程式下載' style='width:100px' onclick='downdloadEa()' />");
}
function downdloadCSV(){
	$("#formUserLive").attr("action","/chungli2/downdloadCSV");
	$("#formUserLive").submit();
}
function downdloadEa(){
	$("#formUserLive").attr("action","/chungli2/downdloadEa");
	$("#formUserLive").submit();
}
</script>
</head>


<%@ include file="/pages/dataInit.jsp"%>
<body>
<form id="formUserLive" action="" method="post" target="_self"  >
   <center>
   <br/>
   <br/>
   <br/>
   <fieldset>
         <legend>封包&空機程式下載</legend>
         <table border="0" class="tblInTD" style="width:400px;">
            <tr>
                <td  style="width:100px" align="left"><font size="3">帳號&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text" id="email" name="email"   value="${email}" readonly="readonly" style="width:150px;color:red;background-color:#D3D3D3;"/></td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td  style="width:100px" align="left" ><font size="3">真倉微小帳號&nbsp;&nbsp;</font></td><td style="width:200px" align="left"><input type="text" style="width:150px" id="userLiveId" name="userLiveId" class="validate[required,maxSize[10]] text-input" /></td>
            </tr>
             <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td style="width:100px" align="left"><font size="3">申請券商&nbsp;&nbsp;&nbsp;</font></td>
                 <td style="width:200px" align="left">     
                   <select name="brokId" id="brokId"  style="width : 150px;">
                     <option value="" selected>---請選擇---</option>
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
                     <option value="" selected>---請選擇---</option>
                     <c:forEach items="${eaList}" var="eaProgram" varStatus="status">
                              <option value="${eaProgram.eaId}">${eaProgram.eaName}</option>
                     </c:forEach>
                   </select>
                </td>
            </tr>
            <tr>
                <td  style="width:100px" align="left"><font size="3">&nbsp;&nbsp;</font></td><td style="width:200px" align="left">&nbsp;&nbsp;</td>
            </tr>
             <tr>
	             <td style="width:200px" align="center">
	                  <input type="button" id="btnCheck" value="檢核" style="width:100px" />
	             </td>

                <td style="width:200px" align="center" id="downloadTd">
		               <div id="downloadDiv">
		             
		             </div>
	            </td>
	            <td style="width:200px" align="center" id="downloadTd">
		               <div id="downloadDiv1">
		             
		             </div>
	            </td>
            </tr>
        </table>
   </fieldset>
     </center>  
     <input type="hidden" style="width:200px" id="oldEaId"   name="oldEaId"  value="" />
     <input type="hidden" style="width:200px" id="oldUserLiveId"   name="oldUserLiveId"  value="" />
</form>

</body>

</html>
