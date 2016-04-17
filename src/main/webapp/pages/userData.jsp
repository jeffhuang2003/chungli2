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

    $("#btnSelect").click(function(){
        $("#formUser").attr("action","/chungli2/queryUserProfile");
        $("#formUser").submit();
     });
    $("#btnInsert").click(function(){
        $("#formUserInsert").attr("action","/chungli2/insertUserProfileInit");
        $("#chineseName").val($("#chineseName").val());
        $("#formUserInsert").submit();
     });
    
    tableToGrid($("#tabelResult"),{
 		autoheight:true,
 		autowidth:false,
		caption:"會員資料",
		multiselect: false,  // 顯示勾選框
		colModel : [ 
				    {name : 'chineseName',index : 'chineseName',width: 130,sortable : false},
				    {name : 'engName',index : 'engName',width: 130, sortable : false},
				    {name : 'phone',index : 'phone',width: 100, sortable : false},
				    {name : 'email',index : 'email',width: 150, sortable : false},
				    {name : 'team',index : 'team',width: 150, sortable : false},
				    {name : 'userStamp',index : 'userStamp',width: 150, sortable : false},
				    {name : 'rButton',index : 'rButton', sortable : false,hidden:false, width: 50}
				    
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


function updateInit(obj,userId){
	$('#formUserUpdate').attr("action" ,"/chungli2/updateUserProfileInit");
	$('#formUserUpdate').append("<input type=hidden name='userId' id='userId' value='" + userId +"'></input>");
	$("#chineseName").val($("#chineseName").val());
	$('#formUserUpdate').submit();
}





</script>
</head>


<%@ include file="/pages/dataInit.jsp"%>

<form id="formUser" action="" method="post" target="_self" >
	<center>
        <table border="0" class="tblInTD" style="width:200px;">
            <tr>
                <td  width="400px" align="left" colspan="2" nowrap="nowrap"><font size="3">帳&nbsp;&nbsp;號(E-Mail)</font><input type="text" style="width:200px;background-color:#D3D3D3;color: red"  id="email" name="email"  value="${email}" readonly="readonly" /></td>
            </tr>
            <tr>
                <td  width="400px" align="left" colspan="2" nowrap="nowrap"><font size="3">會員姓名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type="text" style="width:200px" id="chineseName" name="chineseName" value="${chineseName}"/></td>
            </tr>
            <tr>
                <td  width="200px" align="left" colspan="2" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
             <tr>
                <td  width="200px" align="left" colspan="2" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
             <tr>
                <td width="200px" align="center" colspan="2">
                	<input type="button" id="btnSelect" value="會員查詢" width="100px" />
                	<input type="button" id="btnInsert" value="會員新增" width="100px" />
                </td>
              
            </tr>
            
            
        </table>
        
            <table border="0" class="tblInTD" style="width:850px;">
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
                    <th id="chineseName">會員中文姓名</th>
                    <th id="engName">會員英文姓名</th>
                    <th id="phone">電話</th>
                    <th id="email">email</th>
                    <th id="team">會員所屬團隊</th>
                    <th id="userStamp">異動時間</th>
                    <th id="rButton">&nbsp;</th>
				  </tr>
				 </thead>
				 <tbody>
				 <c:forEach items="${list}" var="user" varStatus="status">
				   <tr>
				       <td>${user.chineseName}</td>
				       <td>${user.englishName}</td>
				       <td>${user.phone}</td>
				       <td>${user.email}</td>
				       <td>
				       		<c:if test="${user.team eq 1}">
							 	 台中洪老師團隊
							</c:if>
							<c:if test="${user.team eq 2}">
							   	台北黃老師團隊
							</c:if>
							<c:if test="${user.team eq 3}">
							   	中壢葉老師團隊
							</c:if>
							<c:if test="${user.team eq 4}">
							   	台南王老師團隊
							</c:if>
				       </td>
 					   <td><fmt:formatDate pattern="yyyy/MM/dd hh:mm:ss"  value="${user.dateStamp}"/></td>
					    <td>
					    <c:if test="${status.count != 1 }">
							<input type="button" name="btnUpdate" id="btnUpdate" value="編輯" title="update" onclick = "updateInit(this,'${user.userId}')" size="30px" />
<%-- 	                        <input type="button" name="btnDel"  id="btnDel" value="刪除" title="delete"  onclick="delRow('/chungli2/userDelete',this,'${user.userId}')"  size="30px" /> --%>
						</c:if>
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
</form>
<form id="formUserUpdate" action="" method="post" target="_self">
     <input type="hidden" id="leaderUserId" name="leaderUserId"  value="${userId}"/>
     <input type="hidden" id="leaderEmail" name="leaderEmail"  value="${email}" />
     <input type="hidden" id="chineseName" name="chineseName" value="${chineseName}"/>
</form>

<form id="formUserInsert" action="" method="post" target="_self">
     <input type="hidden" id="leaderUserId" name="leaderUserId"  value="${userId}"/>
     <input type="hidden" id="leaderEmail" name="leaderEmail"  value="${email}" />
     <input type="hidden" id="chineseName" name="chineseName" value="${chineseName}" />
</form>
</body>

</html>
