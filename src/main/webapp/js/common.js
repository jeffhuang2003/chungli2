/**
 *修改歷程:
 *2012/05/16 Bryan 增加方法 openWindow 
 *2012/05/28 Allen 增加方法 ajaxAutoComboBox
 *2012/06/07 Sku   修改方法 replaceComboBox
 *2012/06/06 Bryan 增加自訂 plugin
 * 							1. myDatepicker
 * 							2. myDatetimepicker
 *2012/12/24 阿賢   增加方法 openShowModalDialog
 *                       receieveReturnData
 *2012/2/18  阿賢   移除方法 1.ajaxAutoComboBox
 *                       2.delRowX
 *2012/2/20	 Bryan 補充說明 doAjaxProcess
 */

var btnElm;
var btnStates;
var inputTag;
var itemStatus;
var TICKET_TYPE_CASE = 1;
var TICKET_TYPE_SUBCASE = 2;
var TICKET_TYPE_ACTIONITEM = 3;

$(document).ready(function(){

	doPageDefaultProcess();
	doGetButtonStates();
	
	
	if( window.top == window.self ){
		$("#btnSetFrameset").hide();
	}
	
	$("#btnSetFrameset").click(function(){
		   var fs = parent.document.getElementsByTagName("frameset")[1];
		   if($("#btnSetFrameset").val() =="←"){
			   fs.cols = "1,*";
			   $("#btnSetFrameset").val("→");
		   }
		   else
		   {
			   fs.cols = "288,*";
			   $("#btnSetFrameset").val("←"); 
		   }
	});
	
});

function startLoading(){
	$("#divProcessing").show();
}
function endLoading(){
	$("#divProcessing").hide();
}

function setTitleLeft(newVal){
	$("#title_left").html(newVal);
}
function setTitleCenter(newVal){
	$("#title_center").html(newVal);
}
function setTitleRight(newVal){
	$("#title_right").html(newVal);
}
function doPageDefaultProcess(){
	doFieldsReadOnly();
	endLoading();
	doMessage();
	$('.funcNotReady').click(function(){
		alert('此功能暫不開放!');
		return false;
	});
	
	
}

function doMessage(){
	var msg = $('.alertMessage').html();
	msg = msg.replace(/\\n/g,"\n");
	(msg!='')?alert(msg):msg='';
	
	var docMsg = $('.docMessage').html();
	docMsg = docMsg.replace(/\\n/g,"<BR>");
	$('.docMessage').html(docMsg);
	(docMsg!='')?$('.docMessage').show():$('.docMessage').hide();
}

function clearMessage(){
	$('.docMessage').html("");
	$('.alertMessage').html("");
}

function doFieldsReadOnly(){
	$('.rOnly').attr('readonly','readonly');
}


function doAjaxInitProcess(data){
	if(data!=null){
		if(data.alertMessage!=null){
			$('.alertMessage').text(data.alertMessage);
		}
		if(data.docMessage!=null){
			$('.docMessage').text(data.docMessage);
		}
	}
	doPageDefaultProcess();
}


function showConfirm(userDefineMsg){
	var msgStr="確定執行?";
	if(userDefineMsg!=null){
		msgStr=userDefineMsg;
	}
	if(window.confirm("確定執行?"))
		return true;
	else
		return false;
}

/**
 * 執行ajax作業之統一函式
 * 
 *  <pre>
 *  ex1: 使用JSON格式帶入
 *  	 doAjaxProcess("xxx.action",{"param1",999,"param2",AAA},"someOtherFun");
 *  ex2: 使用JSON格式帶入(form自動轉JSON)
 *       doAjaxProcess("xxx.action",$("#myForm").serializeArray(),"someOtherFun");
 *  ex3: 使用一般格式帶入
 *  	 doAjaxProcess("xxx.action","param1=999&param2=AAA","someOtherFun");
 *  ex4: 不使用此方式
 *  	 doAjaxProcess("xxx.action?param1=999&param2=AAA,"","someOtherFun");
 *  
 *  function someOtherFun(result){
 *  	//result為JSON格式,內容為action內宣告之物件變數
 *  	//取用某一物件 result.myObject
 *  	//取用某一物件之屬性 result.myObject.name
 *  	//取用某一物件陣列
 *			for (var i=0; i<result.myArray.length; i++) {
 *				alert(result.myArray[i]["name"]);
 *  		}
 *  </pre>
 * @param url 				需執行之action URL
 * @param parameters	 	需傳至URL之參數 
 * @param invokeFunction 	執行完畢後需呼叫之function,
 * 						 	傳回之JSON資料將帶入此自訂function, 
 * 						 	(可以陣列方式傳入多個function name)
 * @returns
 */
function doAjaxProcess(url,parameters,invokeFunction){
		var result=null;
		startLoading();
		clearMessage();
		var showMsg=0;
		$.ajax( {
	      type: "POST",       // the default
	      url: url,
	      async:false,
	      dataType : "json",
	      data: parameters,
	      processData: true,
          contentType:"application/x-www-form-urlencoded",
	      //contentType:"application/json; charset=BIG5",
	      success:function(data){
	    	    result=data;
    	    	doAjaxInitProcess(result);
    	    	showMsg=1;
	    	    if(data.ajaxResult=="success"){
	    	    	if(invokeFunction!=null){
		    	    	if(invokeFunction.constructor==Array){
		    	    		for(var x=0;x<invokeFunction.length;x++){
		    	    			eval(invokeFunction[x] + "(result)");
		    	    		}
		    	    	}else{
		    	    		eval(invokeFunction + "(result)");
		    	    	}
	    	    	}
	    	    }
	      },
	      error:function(jqXHR, textStatus, errorThrown){
	    	 
	    	  if (jqXHR.responseText.indexOf("Session Expired") > 0) {
                  //Session has Expired,redirect to login page
                  //window.location.href = "Login.aspx";
	    		  alert('系統逾時,請重新登入');
	    		  if (window.opener == null) {
	    			  if (typeof($("#formLogin").val()) == 'undefined'  ) {
	    					//var winurl = 'Qrycircuit_q' ;
	    					$('body').append("<form name='formLogin' id='formLogin' action='../login.jsp' method='post' target='_top'></form>"); 
	    					
	    				}
	    			  //window.open("../preLogin.jsp");
	    			  $("#formLogin").submit();
	    		  } 
	    		 
              } else {
            	  alert('系統發生錯誤 (' + textStatus + ')');
              }
//	    	  var r =  jQuery.parseJSON(errorThrown.responseText) ;
//	    	  alert("Message: " + r.Message);
//	          alert("StackTrace: " + r.StackTrace);
//	          alert("ExceptionType: " + r.ExceptionType);
	    	  //alert('系統逾時或發生錯誤 (' + errorThrown + ')');
	      },
	      complete:function(){
	    	 if (showMsg==0){
	    		 doAjaxInitProcess(result);
	    		 showMsg=1;
	    	 }
	      }
	    } ).done(function(){
	    	endLoading();
	    });
  	  return result;
}


//================================
function doAjaxProcessAddError(url,parameters,invokeFunction){
	var result=null;
	startLoading();
	clearMessage();
	var showMsg=0;
	$.ajax( {
      type: "POST",       // the default
      url: url,
      async:false,
      dataType : "json",
      data: parameters,
      processData: true,
      contentType:"application/x-www-form-urlencoded",
      //contentType:"application/json; charset=BIG5",
      success:function(data){
    	    result=data;
	    	doAjaxInitProcess(result);
	    	showMsg=1;
    	    //if(data.ajaxResult=="success"){
	    	alert("doAjaxProcessAddError.data.ajaxResult="+data.ajaxResult);
    	    	if(invokeFunction!=null){
	    	    	if(invokeFunction.constructor==Array){
	    	    		for(var x=0;x<invokeFunction.length;x++){
	    	    			eval(invokeFunction[x] + "(result)");
	    	    		}
	    	    	}else{
	    	    		eval(invokeFunction + "(result)");
	    	    	}
    	    	}
    	   // }
      },
      error:function(jqXHR, textStatus, errorThrown){
    
    	  alert("error");
    	  
    	  if (jqXHR.responseText.indexOf("Session Expired") > 0) {
              //Session has Expired,redirect to login page
              //window.location.href = "Login.aspx";
    		  alert('系統逾時,請重新登入');
    		  if (window.opener == null) {
    			  if (typeof($("#formLogin").val()) == 'undefined'  ) {
    					//var winurl = 'Qrycircuit_q' ;
    					$('body').append("<form name='formLogin' id='formLogin' action='../login.jsp' method='post' target='_top'></form>"); 
    					
    				}
    			  //window.open("../preLogin.jsp");
    			  $("#formLogin").submit();
    		  } 
    		 
          } else {
        	  alert('系統發生錯誤 (' + textStatus + ')');
          }
//    	  var r =  jQuery.parseJSON(errorThrown.responseText) ;
//    	  alert("Message: " + r.Message);
//          alert("StackTrace: " + r.StackTrace);
//          alert("ExceptionType: " + r.ExceptionType);
    	  //alert('系統逾時或發生錯誤 (' + errorThrown + ')');
      },
      complete:function(){
    	 if (showMsg==0){
    		 doAjaxInitProcess(result);
    		 showMsg=1;
    	 }
      }
    } ).done(function(){
    	endLoading();
    });
	  return result;
}




function delRow(url,button){
	if(window.confirm("確定執行?")){
		var result=null;
		startLoading();
	  $.ajax( {
	      type: "POST",       // the default
	      url: url,
	      dataType : "json",
	      success:function(data,textStatus){
	    	    result=data;
	    	    if(data.ajaxResult=="success"){
	    	    	var rowid=button.parentNode.parentNode.rowIndex;
	    	    	$("#tabelResult").jqGrid('delRowData', rowid);
	    	    }
	      },
	      error:function(){
	    	  alert('系統逾時或發生錯誤!');
	      },
	      complete:function(){
	    	  doAjaxInitProcess(result);
	      }
	    } );
	}
}


/**
 * 將list中，移動至上一個選項前
 */
function move_item_up (targetlist)
{
	var $item = $("#"+targetlist+" option:selected:first"); 
	if (!$item.length)
	{
		return;
	}
	$item.prev().before($item);
}

/**
 * 將list中，移動至下一個選項後
 */
function move_item_down (targetlist)
{
	var $item = $("#"+targetlist+" option:selected:first"); 
	if (!$item.length)
	{
		return;
	}
	$item.next().after($item); 
}


/**
 * 取得目前頁面上所有按鈕的狀態
 * disabled=1
 * enabled=1
 */
function doGetButtonStates(){
	btnElm = $(':button');
	btnStates = new Array(btnElm.length);
	   for(var i=0;i<btnElm.length;i++)
	   {
		   if (btnElm[i].disabled==true)
		   {
			   btnStates[i]=1;
		   }
		   else
		   {
			   btnStates[i]=0;
		   }
	   }
}

//**********************************************頁面按鈕狀態鎖定相關方法**********************************************************************************

/***
 * 此功能目前暫不使用
 * 將頁面上的Form底下的物件除button之外全部都Disable
 * @param formId
 */
function formItemDisable(formId){
	doGetStatus(formId);
	for(var i=0;i<inputTag.length;i++){
		if(itemStatus[i] == 0){
			$("#"+inputTag[i].id).attr({'disabled':'disabled'});
		}
	}
}

/***
 * 此功能目前暫不使用
 * 將頁面上的Form底下的物件除button之外全部Enable
 */
function formItemEnable(formId){
	for(var i=0;i<inputTag.length;i++){
		if(itemStatus[i] == 0){
			$("#"+inputTag[i].id).removeAttr('disabled');
		}
	}
}

/**
 * 此功能目前暫不使用
 * 記錄頁面上的Form底下的物件除button之外全部狀態
 */
function doGetStatus(formId){
	inputTag = $("#"+formId).get(0);
	itemStatus = new Array(inputTag.length);
	for(var i=0;i<inputTag.length;i++){
		if($("#"+inputTag[i].id).attr('type') == "button"){
			itemStatus[i] = 1;
		}else{
			if(inputTag[i].disabled == true){
				itemStatus[i] = 1;
			}else{
				itemStatus[i] = 0;
			}
		}
	}
}

/**
 * 使用時機:在按下按鈕後，會跳出視窗讓使用者選擇，並在使用者選擇是後，先呼叫其他fubction處理,在作submit的動作時(使用方式可參考 perm/Dept_a.jsp)
 * 按下按鈕後，跳出視窗，選擇是後，將頁面上的按鈕設為Disable
 * 傳入參數如下
 * @param title  自訂的標題內容
 * @param formid form的id
 * @param funName 呼叫的function name
 */
function btnControlByFunction(title,formid,funName){
	if(window.confirm(title)){
		funName();
		$("#"+formid).submit();
		btnDisable();
		if (!($("#"+formid).validationEngine('validate'))){
			btnEnable();
		}
	}
}


/**
 * 使用時機:在按下按鈕後，會跳出視窗讓使用者選擇，並在使用者選擇是後，作submit的動作時(使用方式可參考 perm/Dept_e.jsp)
 * 按下按鈕後，跳出視窗，選擇是後，將頁面上的按鈕設為Disable
 * 傳入參數如下
 * @param title  自訂的標題內容
 * @param formid form的id
 */
function btnControl(title,formid){
	if(window.confirm(title)){
		$("#"+formid).submit();
		btnDisable();
		if (!($("#"+formid).validationEngine('validate'))){
			btnEnable();
		}
	}
}


/**
 * 使用時機:在按下按鈕後，不需跳出視窗讓使用者選擇,直接作submit的動作時(使用方式可參考 perm/Dept_q.jsp)
 * 按下按鈕後，跳出視窗，選擇是後，將頁面上的按鈕設為Disable
 * 傳入參數如下
 * @param formid form的id
 */
function btnControlBySubmit(formid){
	$("#"+formid).submit();
	btnDisable();
	if (!($("#"+formid).validationEngine('validate'))){
		btnEnable();
	}
}

/**
 * 將頁面上的按鈕設為Disable
 */
function btnDisable(){
	doGetButtonStates();
	   for(var i=0;i<btnElm.length;i++)
	   {
		   if(btnStates[i]==0)
		   {
			   $('#'+btnElm[i].id).attr({'disabled':'disabled'});
		   }
	   }
}

/**
 * 將頁面上的按鈕設為Enable，而在頁面載入時就Disable的按鈕，則不做改變
 */
function btnEnable(){
	   for(var i=0;i<btnElm.length;i++)
	   {
		   if(btnStates[i]==0)
		   {
			   $('#'+btnElm[i].id).removeAttr('disabled');
		   }
	   }
}

//********************************************************************************************************************************************

/***
 * 讀取Form底下 (text & Select) Tag 的 defaultValue
 * @param formId
 * @returns Boolean
 */
function formDefaultValue(formId){
	var allFormTag = $("#"+formId).get(0);
	var transactionFlog = false;
	for(var i=0; i<allFormTag.length; i++){
		if($("#"+allFormTag[i].id).attr('type') == "text"){
			if($("#"+allFormTag[i].id).prop("defaultValue") != $("#"+allFormTag[i].id).val()){
				transactionFlog = true;
			}	
		}
		if($("#"+allFormTag[i].id).prop('tagName') == "SELECT"){
			$("#"+allFormTag[i].id).children().each(function(){
				if(this.defaultSelected != this.selected){
					if(this.selected == true){
						alert(this.value);
					}
					transactionFlog = true;
				}
			});
		}
	}
	return transactionFlog;
}

/** ajaxReplaceComboBox
功能：置換下拉選單內容
修改歷程：2012/06/07 Sku 將rText參數改為可傳入array
傳入參數：
	1.result: ajax取回的data
	2.CBId: ComboBox Id(ex: selUser)
	3.lstName: json中的List名稱(ex: alist or alist.dataList)
	4.rVal: list中指定為value的欄位名稱
	5.rText: list中指定為Label的欄位名稱
回傳值：
	"0": success
	"1": no data found
*/
function replaceComboBox(result,CBId,lstName,rVal,rText){
    var obj = $('#'+CBId);
    var rtnVal = "0";
	if(result.ajaxResult!=null && result.ajaxResult=="success"){
		obj.empty();
		var lst = eval("result."+lstName);
		if(lst!=null && lst.length!=0){
			for (var i = 0; i < lst.length; i++) {
				if(rText.constructor==Array){
					var varList="";
    	    		for(var x=0;x<rText.length;x++){
    	    			varList+=lst[i][rText[x]];
    	    		}
    	    		obj.append("<option value='"+lst[i][rVal]+"'>"+varList+"</option>");
    	    	}else{
    	    		obj.append("<option value='"+lst[i][rVal]+"'>"+lst[i][rText]+"</option>");
    	    	}
			}
			rtnVal = "0";
		}else{
			obj.empty();
			rtnVal = "1";
		}
	}
	return rtnVal;
}
/** ajaxReplaceComboBox
功能：依ajax action置換下拉選單內容
傳入參數：
	1.actName: action 名稱(ex: User_queryUserByDeptCode.action)
	2.extVal: 傳給action的參數(ex: objid=1&abc=0)
	3.CBId: ComboBox Id(ex: selUser)
	4.lstName: json中的List名稱(ex: alist or alist.dataList)
	5.rVal: list中指定為value的欄位名稱
	6.rText: list中指定為Label的欄位名稱
回傳值：
	"0": success
	"1": no data found
	"2": ajax action error

*/
function ajaxReplaceComboBox(actName,extVal,CBId,lstName,rVal,rText){
    var obj = $('#'+CBId);
    var rtnVal = "0";
    var result=null;
	$.ajax({
		type: "POST",   //POST方法
		url: actName + "?"+ extVal, //取資料的頁面
		async: false,
		dataType: "json",   //使用JSON格式
		success: function(result) {
			if(result.ajaxResult!=null && result.ajaxResult=="success"){
				obj.empty();
				var lst = eval("result."+lstName);
				if(lst!=null && lst.length!=0){
					obj.append("<option></option>");
					for (var i = 0; i < lst.length; i++) {
						obj.append("<option value='"+lst[i][rVal]+"'>"+lst[i][rText]+"</option>");
					}
					rtnVal = "0";
				}else{
					rtnVal = "1";
				}
			}
		},
		error: function() {
			alert('系統逾時或發生錯誤!');
			rtnVal = "2"; 
		},
	    complete:function(){
    		doAjaxInitProcess(result);
	    }
	});
	return rtnVal;
}


/**
 取得現在日期，格式YYYY/MM/DD 
 */
function nowDateYYYYMMDD(){
	var DateStamp = new Date();
	var yyyy = DateStamp.getFullYear();
	var mm = (DateStamp.getMonth() +1);
	mm+="";
	if(mm.length<2)mm="0"+mm;
	var dd = DateStamp.getDate();
	dd+="";
	if(dd.length<2)dd="0"+dd;
	return yyyy+"/"+mm+"/"+dd;
}

/**
補足缺零日期，回傳格式YYYY/MM/DD 
*/
function dateFillZero(recDate){
	var rtnDate = recDate.split("/");
	if(rtnDate.length==3){
		if(rtnDate[1].length<2)rtnDate[1]="0"+rtnDate[1];
		if(rtnDate[2].length<2)rtnDate[2]="0"+rtnDate[2];
		return rtnDate[0]+"/"+rtnDate[1]+"/"+rtnDate[2];
	}else
		return recDate;
	
}

/*--jqGrid分頁控制----------------------------------------------------------------------------------*/

function attachPager(formId){
	var pageCurrentNum=parseInt($("#pageControl_pageCurrentNum").val());
	var pageCount = parseInt($("#pageControl_pageCount").val());
	var pageSize = parseInt($("#pageControl_pageSize").val());
	var option ;
/*	for(var i=1;i<=pageCount;i++){
		option= jQuery("new option"); 
		option.attr("value",i);
		option.text("a");
		$("#pageSelecter").append(option); 
		//$(option).attr("selected","true");
	}*/
/*	$("#pageSizeSelecter").children().each(function(){
		if ($(this).text()==pageSize){         
			$(this).attr("selected","true"); 
		} 
	}); */
	
	

	
	$("#pageNext").click(function(){
		if(pageCurrentNum ==0 ){
			return false;
		}
		 if( pageCurrentNum < pageCount ){
				$("#pageControl_pageCurrentNum").val(pageCurrentNum+1);
				$(formId).submit();
		 }else{
			 alert("已達最後一頁!");
		 }
		
	});
	
	$("#pagePrev").click(function(){
		if(pageCurrentNum ==0 ){
			return false;
		}
		 if( pageCurrentNum >1  ){
				$("#pageControl_pageCurrentNum").val(pageCurrentNum-1);
				$(formId).submit();
		 }else{
			 alert("已達第一頁!");
		 }
	}); 
	
	$("#pageFirst").click(function(){
		if(pageCurrentNum ==0 ){
			return false;
		}
		 if( pageCurrentNum != 1){
				$("#pageControl_pageCurrentNum").val(1);
				$(formId).submit();
		 }else{
			 alert("已達第一頁!");
		 }
	});
	
	$("#pageLast").click(function(){
		if(pageCurrentNum ==0 ){
			return false;
		}
		 if( pageCurrentNum != pageCount){
				$("#pageControl_pageCurrentNum").val(pageCount);
				$(formId).submit();
		 }else{
			 alert("已達最後一頁!");
		 }
	}); 	
}
/*------------------------------------------------------------------------------------*/

/**
 * 另開視窗
 * (需另開視窗之功能,統一呼叫此方法)
 * 
 * 修改歷程
 * 2012/05/16 Bryan 初版
 * 2013/09/11 阿賢 修改為模擬ShowDialogBox鎖定視窗方式
 * 
 * @param winUrl		另開視窗之URL
 * @param winConfigName 系統常數設定之名稱,請參考tt_open_window.js
 * @param winName		視窗名稱,若為null則使用系統預設值, 請參考tt_open_window.js
 * 						若此參數有值則以此設定值為主,否則以tt_open_window.js內之設定為主
 * @param winFeathure	視窗特性,若為null則使用系統預設值, 請參考tt_open_window.js
 * 						若此參數有值則以此設定值為主,否則以tt_open_window.js內之設定為主
 * @returns 另開視窗後之window 物件
 */
function openWindow(winUrl,winConfigName,winName,winFeathure){

	var tmpWinName = eval("WIN_" + winConfigName + "_NAME");
	var tmpWinFeathure = eval("WIN_" + winConfigName + "_FEATHURE");
	if(winName==null || winName==""){
		if(tmpWinName==null || tmpWinName=="")
			winName = "_blank";
		else
			winName = tmpWinName;
	}
	//alert("winName : " + winName);
	//alert("winUrl : " + winUrl);
	if(winFeathure==null || winFeathure==""){
		if(tmpWinFeathure==null || tmpWinFeathure == "")
//			winFeathure = "left=0, top=0, center=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=yes";
			winFeathure = "left=0, top=0, center=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=yes,hannelmode=no,fullscreen=no,scrollbars=no,toolbar=no,titlebar=no";
		else
			winFeathure = tmpWinFeathure; 
	}
	
	var oWindow =window.open(winUrl, winName, winFeathure);
	
	oWindow.focus();
//	var arrEvents = new Array('onfocus','onclick','ondblclick','onactivate','onfocusin','onbeforeunload');
//	for(var i=0;i<arrEvents.length;i++)
//	{
////	   document.body.attachEvent(arrEvents[i],__LockOpenWindow);
//	   document.body.attachEvent(arrEvents[i],function LockOpenWindow() {
//		   var sType=event.type;
//		   try {
//			   switch (sType) {
//			   case 'activate':
//				   case 'focus':
//					   case 'click':
//						   case 'dblclick':
//							   oWindow.focus();
//							   break;
//							   case 'beforeunload':
//								   oWindow.close();
//								   break;
//								   }
//			   }
//		   catch(err){
//			   oWindow=null;
//			   var arrEvents = new Array('onfocus','onclick','ondblclick','onactivate','onfocusin','onbeforeunload');
//			   for(var i=0;i<arrEvents.length;i++){
//				   document.body.detachEvent(arrEvents[i],LockOpenWindow);
//				   }
//			   }
//		   });
//	   }
	}
	


/**
 * 自訂jQuery Plugin
 */
(function($) {
	
	/**
	 * 日期選擇器
	 * 
	 * 修改歷程
	 * 2012/06/06 Bryan 初版 (轉call datepicker 並預設參數)
	 */
    $.fn.myDatepicker = function() {
        return this.each(function(settings) {
            var _defaultSettings = {
            		dateFormat: 'yy/mm/dd'
                };
        	var _settings = $.extend(_defaultSettings, settings);
        	$(this).datepicker(_settings);
        });
    };    
    
    /**
     * 日期+時間-選擇器
     * 
	 * 修改歷程
	 * 2012/06/06 Bryan 初版 (轉call datetimepicker 並預設參數)
     */
    $.fn.myDatetimepicker = function() {
        return this.each(function(settings) {
            var _defaultSettings = {
            		dateFormat: 'yy/mm/dd',
            		ampm: true,
            		hourMin: 0,
            		hourMax: 23, 
            		minuteMin: 0,
            		minuteMax: 59, 
            		secondMin: 0,
            		secondMax: 59, 
            		showSecond: true,
            		showMillisec: false,
            		timeFormat: 'HH:mm:ss', 
            		clearText:'Clear'	
                };
        	var _settings = $.extend(_defaultSettings, settings);        	
            $(this).datetimepicker(_settings);
        });
    };

})(jQuery);

/**
 * 另開視窗(showModalDialog版)
 * (需另開視窗之功能,統一呼叫此方法)
 * 
 * 修改歷程
 * 2012/12/24 阿賢 初版
 * 
 * @param winUrl		另開視窗之URL
 * @param winConfigName 系統常數設定之名稱,請參考tt_open_window.js
 * @param winName		視窗名稱,若為null則使用系統預設值, 請參考tt_open_window.js
 * 						若此參數有值則以此設定值為主,否則以tt_open_window.js內之設定為主
 * @param winFeathure	視窗特性,若為null則使用系統預設值, 請參考tt_open_window.js
 * 						若此參數有值則以此設定值為主,否則以tt_open_window.js內之設定為主
 * @returns 另開視窗後之window 物件
 */
function openShowModalDialog(winUrl,winConfigName,winName,winFeathure){
	   //alert(" xxx tmpWinFeathure : " + winFeathure);
		var tmpWinName = eval("WIN_" + winConfigName + "_NAME");
		var tmpWinFeathure = eval("WIN_" + winConfigName + "_FEATHURE");
		//alert(" 1111 tmpWinFeathure : " + tmpWinFeathure);
		if(winName==null || winName==""){
			if(tmpWinName==null || tmpWinName=="")
				winName = "_blank";
			else
				winName = tmpWinName;
		}
		
		if(winFeathure==null || winFeathure==""){
			if(tmpWinFeathure==null || tmpWinFeathure == "")
				winFeathure = "left=0, top=0, center=0, toolbar=no, menubar=no, scroll=yes, resizable=yes,location=no, status=yes";
			else
				winFeathure = tmpWinFeathure; 
		}
		//alert("tmpWinFeathure : " + tmpWinFeathure);
		return window.showModalDialog(winUrl, winName, winFeathure);
	
}

/**
 * 在另開視窗的中，執行指定的方法名稱，回傳資料
 * 修改歷程
 * 2012/12/24 阿賢 初版
 * @param callBackMethodName  要執行的方法名稱
 * @param data                要傳回的資料(Json格式)
 */
function receieveReturnData(callBackMethodName,data){
	if(callBackMethodName==null){
		alert("格式錯誤:請傳入method name");
	}
	else{
		eval(callBackMethodName + "(data)");
	}
}

/**
 * 頁面欄位唯讀設定
 * 說明:根據傳入的頁面欄位唯讀設定資訊，作頁面欄位唯讀設定，頁面欄位唯讀設定資訊中
 * 傳入參數:lockParm
 * 傳入參數格式 JSON{myStatus,pageLock}
 * myStatus: 頁面欄位唯讀設定執行狀態，0:不運作;1:運作
 * pageLock:欄位唯讀設定值，為HashMap物件，key為欄位名稱，Value為唯讀狀態:0:不唯讀;1:唯讀
 * 
 * @param lockParm
 */
function pageLock(lockParm){
	if(lockParm.myStatus=='1'){
		$.each(lockParm.pageLock, function(key, value) { 
			
            if(value=="1"){
            	if($("#"+key).is('input')){
            		if($("#"+key).attr("type")=="button"){
            			$("#"+key).attr('disabled',true);
            		}if($("#"+key).attr("type")=="checkbox"){
            		    $("#"+key).attr('onclick','return false;');
            		}if($("#"+key).attr("type")=="radio"){
            		    $("#"+key).attr('onclick','return false;');
            		}else{
            			$("#"+key).attr('readonly',true);
            			$("#"+key).attr('class','itemReadonly');
            		}
            	}
            	if($("#"+key).is('select')){
            		$("#"+key).attr('onfocus','defaultIndex=this.selectedIndex');
            		$("#"+key).attr('onchange','this.selectedIndex=defaultIndex');
            		$("#"+key).attr('class','itemReadonly');
            	}
            	if($("#"+key).is('textarea')){
            		$("#"+key).attr('readonly',true);
            		$("#"+key).attr('class','itemReadonly');
            	}
            }
		});
	}
}


/**
 * 置換頁面欄位值(做法可參考function:pageLock)
 * 說明:根據傳入的頁面欄位值設定資訊，作頁面欄位值設定，頁面欄位值設定資訊中
 * dataMap:欄位唯讀設定值，為Map物件，key為欄位名稱，Value需更新的欄位值
 * @param dataMap Json格式資料(需更新欄位設定值，為Map物件)
 */
function replaceTagValue(dataMap){
	
}

/**
 * 2013-10-14 阿賢
 * 說明根據傳入的參數類型，清除指定欄位的內容
 * 傳入參數
 * 1.fieldType:欄位類型
 * 2.fieldId:欄位Id
 */
function clearData(fieldType,fieldId){
	if(fieldType=='textarea')
	{
		var fieldStr=$.trim($("#"+fieldId).val());
		var temp="現場跳線資料";

		if(fieldStr.localeCompare(temp)==0)
		{
			$("#"+fieldId).val('');
		}
	}
	
}