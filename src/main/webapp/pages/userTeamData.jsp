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

    	var result = vailDate($("#startDate").val(),$("#endDate").val()) ;
    	if(result == false){
    		alert("結束月不可小於起始月");
    		return ;
        } else {
        	 $("#formUser").attr("action","/chungli2/queryUserTeamProfile");
             if ($("#email").val() == $("#email").val()) {
             	$("#control").val("0");
             } else {
             	$("#control").val("1");
             }
             
             $("#formUser").submit();
        }
    	
       
     });
    $("#btnParent").click(function(){
        $("#formUser").attr("action","/chungli2/selectParentUserTeam");
        $("#control").val("0");
        $("#formUser").submit();
    });
    $("#btnLeader").click(function(){
        $("#formUser").attr("action","/chungli2/selectLeaderUserTeam");
        if ($("#parentEmail").val() == $("#leaderEmail").val()) {
        	$("#control").val("0");
        }  else {
        	 $("#control").val("2");
        }

        $("#formUser").submit();
    });
    
    tableToGrid($("#tabelResult"),{
 		autoheight:true,
 		autowidth:true,
		caption:"會員資料",
		multiselect: false,  // 顯示勾選框
		colModel : [ 
				    {name : 'chineseName',index : 'chineseName',width: 130,sortable : false},
				    {name : 'engName',index : 'engName',width: 130, sortable : false},
				    {name : 'phone',index : 'phone',width: 100, sortable : false},
				    {name : 'email',index : 'email',width: 180, sortable : false},
				    {name : 'team',index : 'team',width: 180, sortable : false},
				    {name : 'userStamp',index : 'userStamp',width: 180, sortable : false},
				    {name : 'rButton',index : 'rButton', sortable : false,hidden:false, width: 80}
				    
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

	$("#endDate").datepicker({
		changeMonth: true, 
	    changeYear: true, 
	    showButtonPanel: true, 
	    dateFormat: 'yymm',
	    onClose: function(dateText, inst) { 
	        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
	        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
	        $(this).datepicker('setDate', new Date(year, month, 1)); 
	    }
	});

// 	 var dpFunc = $.datepicker._generateHTML; 
// 	 $.datepicker._generateHTML = function (inst) {
// 	     var thishtml = $(dpFunc.call($.datepicker, inst)); 
// 	     thishtml = $('<div />').append(thishtml); 
// 	     $('.ui-datepicker-buttonpane', thishtml).append(
// 	       $('<button class="\ui-datepicker-clear ui-state-default ui-priority-primary ui-corner-all\"\>Clear</button>').click(function () {
// 	           inst.input.attr('value', '');
// 	           inst.input.datepicker('hide');
// 	       });
// 	    );
// 	     thishtml = thishtml.children(); 
// 	     return thishtml; 
// 	};



	$("#startDate").datepicker({
		changeMonth: true, 
	    changeYear: true, 
	    showButtonPanel: true, 
	    dateFormat: 'yymm',
	    onClose: function(dateText, inst) { 
	        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
	        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
	        $(this).datepicker('setDate', new Date(year, month, 1)); 
	    }
	});

	$("#clearStartDate").click(function(){
		$("#startDate").val("");
	});
	$("#clearEndDate").click(function(){
		$("#endDate").val("");
	});


});
function vailDate(startDate , endDate){
	if (startDate == null || startDate == "") {
		return true;
	}
	if (endDate == null || endDate == "") {
		return true;
	}
	var startYear = Number(startDate.substring(0,4));
	var endYear = Number(endDate.substring(0,4));
	var startMonth = Number(startDate.substring(4));
	var endMonth = Number(endDate.substring(4));

    if (endYear - startYear < 0) {
    	return false;
     } else {
         if (endYear - startYear ==  0) {
        	 if (endMonth - startMonth < 0) {
        	    	return false;
        	 }
         }
     }
    return true;
	
}

function selectChildUser(email){
	$('#formChildUser').attr("action" ,"/chungli2/selectChildUserTeam");
	$("#formChildUser").append("<input type=hidden name='email' id='email' value='" + email +"'></input>");
	$("#formChildUser").append("<input type=hidden name='leaderEmail'  id='leaderEmail3'  value='" + $("#leaderEmail").val()  +"'></input>");
	$("#formChildUser").append("<input type=hidden name='parentEmail'  id='parentEmail3'  value='" + $("#parentEmail").val()  +"'></input>");
	
	$('#formChildUser').submit();
}


</script>
</head>


<%@ include file="/pages/dataInit.jsp"%>

<form id="formUser" action="" method="post" target="_self" >
	<center>
        <table border="0" class="tblInTD" style="width:800px;">
            <tr>
                <td  width="400px" align="left" colspan="2" nowrap="nowrap"><font size="3">帳&nbsp;&nbsp;號(E-Mail)</font><input type="text" style="width:200px;background-color:#D3D3D3;color: red"  id="email" name="email"  value="${email}" readonly="readonly" /></td>
            </tr>
            <tr>
                <td  width="400px" align="left" colspan="2" nowrap="nowrap">
                     <font size="3">起始月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type="text" style="width:100px" id="startDate" name="startDate" value="${startDate}" readonly='readonly'/>
                     <input type="button" style="width:50px" id="clearStartDate" value="清空" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <font size="3">結束月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type="text" style="width:100px" id="endDate" name="endDate" value="${endDate}"readonly='readonly'/>
                      <input type="button" style="width:50px" id="clearEndDate" value="清空" />
                </td>
            </tr>
            <tr>
                <td  width="400px" align="left" colspan="2" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
             <tr>
                <td  width="400px" align="left" colspan="2" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
             <tr>
              <c:if test="${control == 0 }">
	                <td width="400px" align="center" colspan="2" nowrap="nowrap">
	                	<input type="button" id="btnSelect" value="會員查詢" width="100px" />
	                </td>
              </c:if>
              <c:if test="${control  != 0 }">
	                <td width="400px" align="center" colspan="2" nowrap="nowrap">
	                    <c:if test="${userSize  != 0 }">
	                	  <input type="button" id="btnSelect" value="會員查詢" width="100px" />
	                	</c:if>
	                	<input type="button" id="btnLeader" value="回上層查詢" width="100px" />
	                	<input type="button" id="btnParent" value="最上層查詢" width="100px" />
	                </td>
              </c:if>
            </tr>
            
            
        </table>
        
            <table border="0" class="tblInTD" style="width:900px;">
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
							<input type="button" name="btnSelectChild" id="btnSelectChild" value="子會員查詢"  onclick = "selectChildUser('${user.email}')" size="30px" />
<%-- 	                        <input type="button" name="btnDel"  id="btnDel" value="刪除" title="delete"  onclick="delRow('/chungli2/userDelete',this,'${user.userId}')"  size="30px" /> --%>
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
     <input type="hidden" id="leaderEmail" name="leaderEmail"  value="${leaderEmail}" />
     <input type="hidden" id="parentEmail" name="parentEmail"  value="${parentEmail}" />
     <input type="hidden" id="control" name="control" value="${control}"/>
</form>
     
<form id="formChildUser" action="" method="post" target="_self">
</form>

</body>
</html>
