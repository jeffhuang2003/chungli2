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

    if ($("#userSize").val() !="" && $("#userSize").val() !="0"&& $("#userSize").val() !="-1") {
        $("#queryList").show();
        $("#queryListResult").hide();
    }else if ($("#userSize").val() =="-1"){
    	 $("#queryList").hide();
    	 $("#queryListResult").hide();
    }else {
   	 $("#queryList").hide();
	 $("#queryListResult").show();
	}

    $("#btnQry").click(function(){
        $("#formUser").submit();
     });

    $("#btnInsert").click(function(){
        $("#formUserLiveInsert").attr("action","/chungli2/insertUserLiveInit");
    	$('#formUserLiveInsert').append("<input type=hidden name='leaderUserId' id='leaderUserId1' value='" + $("#leaderUserId").val() +"'></input>");
    	$('#formUserLiveInsert').append("<input type=hidden name='email' id='email1' value='" + $("#email").val() +"'></input>");
        $("#formUserLiveInsert").submit();
     });
    
    
    tableToGrid($("#tabelResult"),{
 		autoheight:true,
 		autowidth:true,
		caption:"真倉程式資料",
		multiselect: false,  // 顯示勾選框
		colModel : [ 
				    {name : 'userLiveId',index : 'userLiveId',width: 130,sortable : false},
				    {name : 'edId',index : 'edId',width: 130, sortable : false},
				    {name : 'brokId',index : 'brokId',width: 100, sortable : false},
				    {name : 'count',index : 'count',width: 180, sortable : false},
				    {name : 'status',index : 'status',width: 180, sortable : false},
				    {name : 'userStamp',index : 'userStamp',width: 180, sortable : false},
				    {name : 'rButton',index : 'rButton', sortable : false,hidden:false, width: 100}
				    
                   ],
		prmNames:{
		 	rows:"pageSize",
			page:"pageCurrentNum"
			/* sort:"page.orderBy",
			order:"page.order"  */
						},
		pager:"gridPager",
		onSelectRow: function(id){
			
		}
	
	});  

   

});


function selectLiveUpdateInit(obj,userId,userLiveId){
	$('#formUserLive').attr("action" ,"/chungli2/updateUserLiveInit");
	$('#formUserLive').append("<input type=hidden name='userId' id='userId' value='" + userId +"'></input>");
	$('#formUserLive').append("<input type=hidden name='userLiveId' id='userLiveId' value='" + userLiveId +"'></input>");
	$('#formUserLive').append("<input type=hidden name='chineseName' id='chineseName' value='" +$("#chineseName").val() +"'></input>");
	$('#formUserLive').submit();
}

function delRow(delUrl,button,userId,userLiveId){
	var data1={"delUserId" : userId,
			  "delUserLiveId" : userLiveId
			 };
	if(window.confirm("確定刪除此筆資料?")){
		$.ajax({
            type : "post",
            url : delUrl,
            cache : false,
            data : $.toJSON(data1),
            dataType : 'json',
            contentType : "application/json",
            success : function(result) {
            	if(result.success=="success"){
             		var rowid=button.parentNode.parentNode.rowIndex;
	    	    	$("#tabelResult").jqGrid('delRowData', rowid);
	    	    	alert("刪除成功 ");
                } else if (result.success=="timeout") {
  	            		alert("新增失敗  :  "+ result.errorMessage);
  	            		location.href='/chungli2/logOut';
  	            } else {
            		alert("刪除失敗  :  "+ result.errorMessage);

            	}
//             	alert("result : " + JSON.stringify(result));
            },

            error : function(result) {
            }

        });
	}
}

</script>
</head>


<%@ include file="/pages/dataInit.jsp"%>

<form id="formUser" action="selectUserLiveQry" method="post" target="_self" >
	<center>
            <table border="0" class="tblInTD" style="width:900px;">
             
            <tr>
            	<th style="width:200x">帳&nbsp;&nbsp;號(E-Mail)</th> 
                <td style="width:200x" align="center"><input type="text" style="width:200px;color:red;background-color:#D3D3D3" id="email" name="email"  value="${email}" readonly="readonly"/></td>
                <th style="width:200x">&nbsp;</th>
                <td style="width:200x" align="left">&nbsp;</td>
            </tr> 
            <tr>
            	<th style="width:200x">EA智能  :</th>
                <td style="width:200x" align="center">
                <select id="eaProgram" name="eaProgram" style="width:100x">
                    <option selected="selected" value="">請選擇</option>
                    <c:forEach items="${eaList}" var="eaProgram2">
                     <c:if test="${eaProgram == eaProgram2.eaId}">
					     <option  value="${eaProgram2.eaId}" selected>${eaProgram2.eaName}</option>    
					 </c:if> 
					  <c:if test="${eaProgram != eaProgram2.eaId}">
					     <option  value="${eaProgram2.eaId}">${eaProgram2.eaName}</option>    
					 </c:if>             
                   </c:forEach>
                </select>
                </td>
                <th style="width:200x">&nbsp;</th>
                <td style="width:200x" align="left">&nbsp;</td>
            </tr> 
             <tr>
				<th style="width:200x">券商 :</th>
                <td style="width:200x" align="center">
                <select id="brokerAge" name="brokerAge" style="width:100x">
                    <option selected="selected" value="">請選擇</option>
                    <c:forEach items="${brokList}" var="brokerAge2">
                     <c:if test="${brokerAge == brokerAge2.brokId}">
                          <option  value="${brokerAge2.brokId}" selected>${brokerAge2.brokName}</option>   
                     </c:if>
                     <c:if test="${brokerAge != brokerAge2.brokId}">
                          <option  value="${brokerAge2.brokId}" >${brokerAge2.brokName}</option>   
                     </c:if>         
                   </c:forEach>
                </select>
                </td>
                <th style="width:200x">&nbsp;</th>
                <td style="width:200x" align="center">&nbsp;</td>
            </tr> 
            <tr>
                <td style="width:800x" colspan="4" nowrap="nowrap" align="center">
	                <input type="button" style="width:150px;" id="btnQry" name="btnQry"  value="會員真倉資料查詢"/>
	                <input type="button" style="width:150px;" id="btnInsert" name="btnInsert"  value="會員真倉資料新增"/>
                </td>
            </tr>     
            <tr>
                <td width="200px"><input type="hidden" width="200px" id="userId" name="userId"  value="${userId}"/></td>
                <td width="200px"><input type="hidden" width="200px" id="userSize" name="userSize"  value="${userSize}"/></td>
            </tr>

        </table>
        <div id="queryListResult">
           <font size="3">無資料</font>	
        </div> 
       <div id="queryList">
        <table  class="tblCommOuter" style="width:100%">
          <tr>  
            <td id="qryResult"  align="center"><!--查詢結果-->
            	<table  id="tabelResult"    border="1" align="left" class="tblItemList">     
                  <thead>
                  <tr>
                    <th id="userLiveId">真倉程式帳號</th>
                    <th id="edId">智能程式代號</th>
                    <th id="brokId">申請券商</th>
                    <th id="count">入倉金額</th>
                    <th id="status">申請狀態</th>
                    <th id="userStamp">異動時間</th>
                    <th id="rButton">&nbsp;</th>
				  </tr>
				 </thead>
				 <tbody>
				 <c:forEach items="${list}" var="userLive" varStatus="status">
				   <tr>
				       <td>${userLive.userLiveId}</td>
				       <td>
					       <c:forEach items="${eaList}" var="eaProgram" varStatus="status1">
					             <c:if test="${userLive.eaId == eaProgram.eaId}">
								 	${eaProgram.eaName}
						   </c:if>
						   </c:forEach>
					   </td>
<%-- 				       <c:if test="${userLive.eaId == 1}"> --%>
<!-- 							 	藍天(BlueSky) -->
<%-- 					   </c:if> --%>
<%-- 					   <c:if test="${userLive.eaId == 2}"> --%>
<!-- 							 	雷射(Laser) -->
<%-- 					   </c:if> --%>
<%-- 					   <c:if test="${userLive.eaId == 3}"> --%>
<!-- 							 	鷹眼(EngleEye) -->
<%-- 					   </c:if> --%>
<%-- 					   <c:if test="${userLive.eaId == 4}"> --%>
<!-- 							 	無雙(Warriors) -->
<%-- 					   </c:if> --%>
					   <td>
					       <c:forEach items="${brokList}" var="brokerAge" varStatus="status1">
					             <c:if test="${userLive.brokId == brokerAge.brokId}">
								 	${brokerAge.brokName}
						   </c:if>
						   </c:forEach>
					   </td>
<!-- 				       <td> -->
<%-- 				       <c:if test="${userLive.brokId == 1}"> --%>
<!-- 							 	GoMarket -->
<%-- 					   </c:if> --%>
<%-- 					   <c:if test="${userLive.brokId == 2}"> --%>
<!-- 							 	Synergy -->
<%-- 					   </c:if> --%>
<!-- 				       </td> -->
				       <td>${userLive.count}</td>
				       <td>
				       		<c:if test="${userLive.status == 1}">
							 	新申請
							</c:if>
							<c:if test="${userLive.status == 2}">
							   	已入金
							</c:if>
							<c:if test="${userLive.status == 3}">
							   	封包中
							</c:if>
							<c:if test="${userLive.status == 4}">
							   	模擬盤
							</c:if>
							<c:if test="${userLive.status == 5}">
							   	已上線
							</c:if>
							<c:if test="${userLive.status == 6}">
							   	未啟用
							</c:if>
							<c:if test="${userLive.status == 7}">
							   	已啟用
							</c:if>						
				       </td>
 					   <td><fmt:formatDate pattern="yyyy/MM/dd hh:mm:ss"  value="${userLive.dateStamp}"/></td>
					    <td>
							<input type="button" name="btnUpdate" id="btnUpdate" value="編輯" title="update" onclick = "selectLiveUpdateInit(this,'${userLive.userId}','${userLive.userLiveId}')" size="30px" />
							<input type="button" name="btnDel"  id="btnDel" value="刪除" title="delete"  onclick="delRow('/chungli2/deleteUserLive',this,'${userLive.userId}','${userLive.userLiveId}')"  size="30px" />
						</td>
				  </tr>
				 </c:forEach>
                 </tbody> 
                </table>

            </td>
          </tr>
          <tr>

   		</tr>
        </table>
        </div>
 	</center>
 	<input type="hidden" style="width:200px" id="chineseName" name="chineseName" value="${chineseName}"/>
 	<input type="hidden" style="width:200px" id="leaderEmail" name="leaderEmail" value="${leaderEmail}"/>
</form>
</body>
<form id="formUserLive" action="" method="post" target="_self" >
</form>
<form id="formUserLiveInsert" action="" method="post" target="_self" >
</form>
<form id="formUserLiveDel" action="" method="post" target="_self" >
</form>
</html>
