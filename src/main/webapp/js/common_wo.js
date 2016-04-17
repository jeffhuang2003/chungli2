/**
 * 2013-07-04 Eric 新增gotoSelectCircuit方法,查詢線路資料 userId productType customerID
 * dpCode multi
 */
function gotoSelectCircuitM(userId, productType, customerID, dpCode, multi) {
	// alert("M");
	var url = "SelectCircuit_queryInit.action";

	// alert(userId + " , " + productType + " , "+ customerID + " , "+ dpCode +
	// " , " + multi);

	if ($("#selectCircuitFormM").val() == undefined) {
		$('body')
				.append(
						"<form method='post' target='_blank' name='selectCircuitFormM' id='selectCircuitFormM'></form>");
		$("#selectCircuitFormM").append(
				"<input type=hidden name='userId' id='userId' value='" + userId
						+ "'></input>");
		$("#selectCircuitFormM").append(
				"<input type=hidden name='productType' id='productType' value='"
						+ productType + "'></input>");
		$("#selectCircuitFormM").append(
				"<input type=hidden name='customerID' id='customerID' value='"
						+ customerID + "'></input>");
		$("#selectCircuitFormM").append(
				"<input type=hidden name='dpCode' id='dpCode' value='" + dpCode
						+ "'></input>");
		$("#selectCircuitFormM").append(
				"<input type=hidden name='selectType' id='selectType' value='"
						+ multi + "'></input>");
	} else {
		$("#userId").attr("value", userId);
		$("#productType").attr("value", productType);
		$("#customerID").attr("value", customerID);
		$("#dpCode").attr("value", dpCode);
		$("#selectType").attr("value", multi);
	}

	$("#selectCircuitFormM").attr("action", url);
	$("#selectCircuitFormM").submit();
}

/**
 * 2013-03-14 Eric 新增gotoSelectCircuit方法,查詢線路資料 userId productType customerID
 * dpCode
 */
function gotoSelectCircuit(userId, productType, customerID, dpCode) {

	var url = "SelectCircuit_queryInit.action";

	// alert(userId + " , " + productType + " , "+ customerID + " , "+ dpCode);

	if ($("#selectCircuitForm").val() == undefined) {
		$('body')
				.append(
						"<form method='post' target='_blank' name='selectCircuitForm' id='selectCircuitForm'></form>");
		$("#selectCircuitForm").append(
				"<input type=hidden name='userId' id='userId' value='" + userId
						+ "'></input>");
		$("#selectCircuitForm").append(
				"<input type=hidden name='productType' id='productType' value='"
						+ productType + "'></input>");
		$("#selectCircuitForm").append(
				"<input type=hidden name='customerID' id='customerID' value='"
						+ customerID + "'></input>");
		$("#selectCircuitForm").append(
				"<input type=hidden name='dpCode' id='dpCode' value='" + dpCode
						+ "'></input>");
		$("#selectCircuitForm")
				.append(
						"<input type=hidden name='selectType' id='selectType' value=''></input>");
	} else {
		$("#userId").attr("value", userId);
		$("#productType").attr("value", productType);
		$("#customerID").attr("value", customerID);
		$("#dpCode").attr("value", dpCode);
		$("#selectType").attr("value", '');
	}

	$("#selectCircuitForm").attr("action", url);
	$("#selectCircuitForm").submit();
}

/**
 * 2013-03-14 Eric 建立 查詢站台資料 userId > dpCode ringCode > ringCode address > 地址
 * 
 * 
 */
function gotoQryNetSite(userId, ringCode, address) {
	var url = "QryNetSite_queryInit.action";

	// alert(userId + " , " + ringCode + " , " + address);

	if ($("#qryNetSiteForm").val() == undefined) {
		// alert("if");
		$('body')
				.append(
						"<form method='post' target='_blank' name='qryNetSiteForm' id='qryNetSiteForm'></form>");
		$("#qryNetSiteForm").append(
				"<input type=hidden name='applyCode' id='applyCode' value='"
						+ userId + "'></input>");
		$("#qryNetSiteForm").append(
				"<input type=hidden name='ringCode' id='ringCode' value='"
						+ ringCode + "'></input>");
		$("#qryNetSiteForm").append(
				"<input type=hidden name='address' id='address' value='"
						+ address + "'></input>");
	} else {
		// alert("else");
		$("#applyCode").attr("value", userId);
		$("#ringCode").attr("value", ringCode);
		$("#address").attr("value", address);

	}
	// alert($("#qryNetSiteForm"));

	$("#qryNetSiteForm").attr("action", url);
	$("#qryNetSiteForm").submit();

}

/**
 * 2013-03-14 Eric 查詢VPNID 待補
 * 
 * 
 * 
 */
function gotoVPNID(productType, circuitId) {
	var url = "VoipCustEnvApForm_queryInit.action";

	if ($("#qryVpnidForm").val() == undefined) {
		$('body')
				.append(
						"<form method='post' target='_blank' name='qryVpnidForm' id='qryVpnidForm'></form>");
		$("#qryVpnidForm").append(
				"<input type=hidden name='callerId' id='callerId' value='"
						+ userId + "'></input>");
		$("#qryVpnidForm").append(
				"<input type=hidden name='caseObjid' id='caseObjid' value='"
						+ ringCode + "'></input>");
		$("#qryVpnidForm").append(
				"<input type=hidden name='circuitId' id='circuitId' value='"
						+ circuitId + "'></input>");
	} else {
		$("#callerId").attr("value", userId);
		$("#caseObjid").attr("value", ringCode);
		$("#circuitId").attr("value", address);
	}

	$("#qryVpnidForm").attr("action", url);
	$("#qryVpnidForm").submit();
}

/**
 * 2013-03-14 Eric 查詢客戶環境需求單 userId > 呼叫者代碼 csoid > caseObjid (KEY) cirid >
 * CircuitId (暫不使用)
 * 
 */
function gotoCustEnvApForm(userId, csoid, cirid, woObjid) {

	
	var url = "";
	url += "VoipCustEnvApForm_queryInit.action?";

	url += "caseObjid=" + csoid;

	url += "&";

	url += "circuitId=" + cirid;
	
	url += "&";

	url += "woObjid=" + woObjid;
	
	window.open(url);

}

function gotoCHT(userId, cht) {
	var url = "QryEqp_queryInit.action";

	if ($("#qryCHTForm").val() == undefined) {
		// alert("if");
		$('body')
				.append(
						"<form method='post' target='_blank' name='qryCHTForm' id='qryCHTForm'></form>");
		$("#qryCHTForm").append(
				"<input type=hidden name='eqp' id='eqp' value='" + cht
						+ "'></input>");
		$("#qryCHTForm").append(
				"<input type=hidden name='userId' id='userId' value='" + userId
						+ "'></input>");
	} else {
		// alert("else");
		$("#eqp").attr("value", cht);
		$("#userId").attr("value", userId);

	}
	// alert($("#qryNetSiteForm"));

	$("#qryCHTForm").attr("action", url);
	$("#qryCHTForm").submit();

}

/**
 * 呼叫commactimpl.doQueryTtPpElmByActive 取回url字串並開啟
 * actionName參數為"struts_xx.xml"相對應呼叫的action name wom : 決定組出字串為WOM220M 250M 230R
 * woId : pwp.woObjid 對應的 tableXTtWo.xWoId
 */
function openCommAct_doQueryTtPpElmByActive(actionname, wom, woId, woObjid)
{
	/*$("#divProcessing").toggle(0, function callfunction()
	{
		
	});*/
	/*var sbtitle = document.getElementById("divProcessing");
	
	sbtitle.style.display = 'block';
	alert(sbtitle.style.display);*/
	
	//alert();
	
	//$("#divProcessing").show();
	
	//window.setTimeout(function(){
		
		//$("#divProcessing").show();
		// 預覽列印,物料預領,物料分配
		var result_comp = null;
		$.ajax({
			type : "POST", // POST方法
			url : actionname + "?wom=" + wom + "&woId=" + woId + "&woObjid="
					+ woObjid, // 取資料的頁面
			async : false,
			dataType : "json", // 使用JSON格式
			success : function(result)
			{
				// 預覽列印
				if (wom == "WOM230R") {
					
					
					if (result.countOcir == "0") {
						alert("資料未存檔");
					} else if (result.countOcir == "-1") {
						alert(result.alertErrorMessage);

					} else {
						
						openCommAct_doFilePreview(result.woId);
						// window.open("common_preprint.action" + "?" + "woId=" +
						// result.woId);
					}
				}
				// 物料預領
				else if (wom == "WOM220M") {
					window.open(result.urlStr);
				}
				// 物料分配
				else if (wom == "WOM250M") {
					window.open(result.urlStr);
				}
			},
			error : function() {
				alert('系統逾時或發生錯誤!');
			},
			complete : function() {
				doAjaxInitProcess(result_comp);
			}
		});
		
	//},1000);
	
	
	/*
	this.Sleep = function ZZzzzZZzzzzzzZZZz(naptime){
		
        naptime = naptime * 1000;
        var sleeping = true;
        var now = new Date();
        var alarm;
        var startingMSeconds = now.getTime();
       // alert("starting nap at timestamp: " + startingMSeconds + "\nWill sleep for: " + naptime + " ms");
        while(sleeping){
            alarm = new Date();
            alarmMSeconds = alarm.getTime();
            if(alarmMSeconds - startingMSeconds > naptime){ sleeping = false; }
        }    
      //  alert("Wakeup!");
     
    }
    
    this.Sleep(1);
    
	*/
	
}

function openCommAct_doFilePreview(woId)
{
	
 // 預覽列印,物料預領,物料分配
	
	var result = null;
	$.ajax({
		type : "POST", 
		url : "common_preprint.action" + "?" + "woId=" + woId, // 取資料的頁面
		async : false,
		dataType : "json", // 使用JSON格式
		success : function(msg)
		{
			
			result = msg;
			if (msg.ajaxResult == "success") {
				window.open("common_filePreview.action" + "?" + "filePath="
						+ msg.filePath);
			}
		},
		error : function() {
			alert('系統逾時或發生錯誤!');
		},
		complete : function() {
			
			doAjaxInitProcess(result);
		}
	});
		
}

function setUITagControl(objName) {
	var tag = objName.replace(/\=/g, ":");

	if (tag == "" || tag == null) {

	} else {

		var tag_json = eval("(" + tag + ")");

		$.each(tag_json, function(key, value) {
			// alert(key + " , " + value);

			// 0=disabled
			// 1=enabled
			// 10=hide
			// 11=show
			if (value == 0) {
				if ($("#" + key).is('input')) {
					if ($("#" + key).attr("type") == "button") {
						$("#" + key).attr('disabled', true);
					} else if ($("#" + key).attr("type") == "checkbox") {
						$("#" + key).attr('disabled', true);
					} else if ($("#" + key).attr("type") == "radio") {
						$("#" + key).attr('disabled', true);
					} else if ($("#" + key).attr("type") == "text") {
						$("#" + key).attr('readonly', true);
						$("#" + key).addClass('rOnly');
					} else {
						$("#" + key).addClass('rOnly');
					}
				} else if ($("#" + key).is("select")) {
					$("#" + key).attr('onfocus',
							'defaultIndex=this.selectedIndex');
					$("#" + key).attr('onchange',
							'this.selectedIndex=defaultIndex');

					$("#" + key).addClass('itemReadonly');
					// $("#"+key).attr('disabled',true);
				} else if ($("#" + key).is('textarea')) {
					$("#" + key).attr('readonly', true);
					$("#" + key).addClass('itemReadonly');

				} else if ($("#" + key).is('table')) {
					$("#add_" + key).addClass('ui-state-disabled');
					$("#edit_" + key).addClass('ui-state-disabled');
					$("#del_" + key).addClass('ui-state-disabled');
					$("#view_" + key).addClass('ui-state-disabled');
				}

			}

			if (value == 1) {
				// $("#"+key).attr('disabled',false);
				if ($("#" + key).is('input')) {
					if ($("#" + key).attr("type") == "button") {
						$("#" + key).attr('disabled', false);
					} else if ($("#" + key).attr("type") == "checkbox") {
						$("#" + key).attr('disabled', false);
					} else if ($("#" + key).attr("type") == "radio") {
						$("#" + key).attr('disabled', false);
					} else if ($("#" + key).attr("type") == "text") {
						$("#" + key).attr('readonly', false);
						$("#" + key).removeClass('rOnly');
					} else {
						$("#" + key).removeClass('rOnly');
					}
				} else if ($("#" + key).is("select")) {
					$("#" + key).removeClass('itemReadonly');
					// $("#"+key).attr('disabled',true);
				} else if ($("#" + key).is('textarea')) {
					$("#" + key).attr('readonly', false);
					$("#" + key).removeClass('itemReadonly');
				} else if ($("#" + key).is('table')) {
					$("#add_" + key).removeClass('ui-state-disabled');
					$("#edit_" + key).removeClass('ui-state-disabled');
					$("#del_" + key).removeClass('ui-state-disabled');
					$("#view_" + key).removeClass('ui-state-disabled');
				}
			}

			if (value == 10) {
				$("#" + key).hide();
			}

			if (value == 11) {
				$("#" + key).show();
			}

		});

	}
}

/**
 * 2013-03-21 Eric
 * 
 */
function setLineStatus(woType, prdName) {
	if ($(woType).val() == 'Activation') {
		$(prdName).empty();
		$(prdName).append("<option value='開通中' selected>開通中</option>");
		$(prdName).append("<option value='Normal' >Normal</option>");
	} else if ($(woType).val() == 'Service Change') {
		$(prdName).empty();
		$(prdName).append("<option value='Normal' selected>Normal</option>");
		$(prdName).append("<option value='Suspend' >Suspend</option>");
	} else if ($(woType).val() == 'Deactivation') {
		$(prdName).empty();
		$(prdName).append("<option value='退租中' selected>退租中</option>");
		$(prdName).append("<option value='Disable'>Disable</option>");
	} else if ($(woType).val() == 'Cancel') {
		$(prdName).empty();
		$(prdName).append("<option value='開通取消中' selected>開通取消中</option>");
		$(prdName).append("<option value='Disable' >Disable</option>");
	} else {
		$(prdName).empty();
		$(prdName).append("<option value='Normal' selected>Normal</option>");
		$(prdName).append("<option value='Suspend' >Suspend</option>");
		$(prdName).append("<option value='Disable' >Disable</option>");
	}
}

/**
 * 2013-03-21 Eric 2013-07-10 Eric 依SD修改規則
 * 
 */
function setLineStatusUI(woType, prdName, btnId) {

	if ($(woType).val() == 'Activation') {
		$(prdName).empty();
		$(prdName).append("<option value='開通中' selected>開通中</option>");
		$(prdName).append("<option value='Normal' >Normal</option>");

		// $(btnId).attr("disabled", true);
		// $(prdName).attr("disabled", true);
	} else if ($(woType).val() == 'Service Change') {
		$(prdName).empty();
		$(prdName).append("<option value='Normal' selected>Normal</option>");
		$(prdName).append("<option value='Suspend' >Suspend</option>");

		// $(btnId).attr("disabled", false);
		// $(prdName).attr("disabled", false);
	} else if ($(woType).val() == 'Deactivation') {
		$(prdName).empty();
		$(prdName).append("<option value='退租中' selected>退租中</option>");
		$(prdName).append("<option value='Disable'>Disable</option>");

		// $(btnId).attr("disabled", false);
		// $(prdName).attr("disabled", true);

	} else if ($(woType).val() == 'Cancel') {
		$(prdName).empty();
		$(prdName).append("<option value='開通取消中' selected>開通取消中</option>");
		$(prdName).append("<option value='Disable' >Disable</option>");

		// $(btnId).attr("disabled", false);
		// $(prdName).attr("disabled", true);
	} else if ($(woType).val() == 'Maintain') { // 20140902 add by Wei-wen
		$(prdName).empty();
		$(prdName).append("<option value='Normal' >Normal</option>");
	} else {
		$(prdName).empty();
		$(prdName).append("<option value='Normal' selected>Normal</option>");
		$(prdName).append("<option value='Suspend' >Suspend</option>");
		$(prdName).append("<option value='Disable' >Disable</option>");

		// $(btnId).attr("disabled", false);
		// $(prdName).attr("disabled", false);
	}

}

/**
 * 塞checkbox值給隱藏欄位 checkBox 物件ID v_true 勾選時填的值 v_false 不勾選時填的值 input 要塞的目標欄位
 * 
 */
function setCheckBoxValueToInput(checkBox, v_true, v_false, input) {

	$(input).attr('value', getCheckBoxValue(checkBox, v_true, v_false));

}

/**
 * 回傳物件勾選與不勾選時的值 checkBox 物件ID v_true 勾選時填的值 v_false 不勾選時填的值
 */
function getCheckBoxValue(checkBox, v_true, v_false) {
	if ($(checkBox + ":checked").val() == "on") {
		// alert(v_true);
		return v_true;
	} else {
		// alert(v_false);
		return v_false;
	}
}

/**
 * 取得一組radio button目前被選取的值 groupName 群組名稱
 * 
 */
function getRadioButtonData(groupName) {

	// alert($("input[name="+groupName+"]:radio:checked").val());

	return $("input[name='" + groupName + "']:radio:checked").val();

	// return $("input[type='radio',name='"+groupName+"']:radio:checked").val();
}
/**
 * 取得一組radio button目前被選取的值並塞到input groupName 群組名稱 input input物件ID
 */
function setRadioButtonDataToInput(groupName, input) {
	$(input).attr("value", getRadioButtonData(groupName));
}

/**
 * 把選取的combo box值塞到input comboBox combobox物件 input input物件
 */
function setComboBoxToInput(comboBox, input) {
	// alert($(comboBox).val());

	$(input).attr("value", $(comboBox).val());
}

/**
 * checkBox Listener UI互動 若勾選,combobox為enabled 若不勾選,combobox為disabled objId
 * checkBox ID combo comboBox ID
 */

function attCheckBoxForCombobox(objId, combo) {

	$(objId).change(function() {

		if ($(objId + ":checked").val() == "on") {

			$(combo).attr("disabled", false);

		} else {

			$(combo).attr("disabled", true);
		}

		// alert($(objId).attr("checked"));
	});
}

/**
 * 2013-03-26 selectBoxId : 下拉選項Id hiddenInputId : 隱藏欄位Id 若不勾選,combobox為disabled
 * keyArray 振烈參數Name dataArray 振烈參數val() url : ajax ActionName functionName :
 * 回呼程式
 */

function attSelectBoxForAjax(selectBoxId, hiddenInputId, keyArray, dataArray,
		url, functionName) {
	var data = {};
	var name = "";
	var value = "";
	var hiddenId = "";
	if (hiddenInputId.indexOf("#") != -1) {
		hiddenId = hiddenInputId.substring(1);
	} else {
		hiddenId = hiddenInputId;

	}
	if (selectBoxId.indexOf("#") == -1) {
		$("#" + hiddenId).val($("#" + selectBoxId).val());
		name = hiddenId;
		value = $("#" + hiddenId).val();
		$(data).attr(hiddenId, value);
	} else if (selectBoxId.indexOf("#") == 0) {
		$("#" + hiddenId).val($(selectBoxId).val());
		name = hiddenId;
		value = $("#" + hiddenId).val();
		$(data).attr(hiddenId, value);
	} else {
		alert("傳入傳數名稱有問題");
		return;
	}
	for ( var i = 0; i < keyArray.length; i++) {
		name = keyArray[i];
		value = dataArray[i];
		$(data).attr(name, value);
	}
	for ( var key in data) {
		alert('key: ' + key + '\n' + 'value: ' + data[key]);
	}
	doAjaxProcess(url, data, "" + functionName);
}

/**
 * 2013-08-02 將GRID轉JSON字串
 * 
 */
function array2json(arr) {

	var parts = [];
	var is_list = (Object.prototype.toString.apply(arr) === '[object Array]');

	for ( var key in arr) {
		var value = arr[key];

		if (typeof value == "object") {

			// Custom handling for arrays
			if (is_list) {

				parts.push(array2json(value));
			} else {
				parts[key] = array2json(value);
			}
		}

		else {
			var str = "";

			// alert("is_list="+is_list);

			try {
				if (!is_list) {
					// 塞KEY
					str = '"' + key + '":';

					// 塞VALUE
					// alert(""+key + " = '" + value + "'");
					if ($(value).is('input')) {
						if ($(value).attr("type") == "text") {

							str += '"' + $(value).attr('value') + '"';
						}
					} else {
						if ($(value).is('select')) {

							str += '"' + $(value).val() + '"';
						}

						else {

							str += '"' + value + '"';
						}

					}

				}
			} catch (Excetpion) {
				str += '""';
				// alert("str >>"+str+"<<");
				// alert(Excetpion.message + ">>"+str+"<<");
			}

			// alert("str >>"+str+"<<");
			parts.push(str);
		}
	}
	var json = parts.join(",");

	if (is_list) {
		return '[' + json + ']';// Return numerical JSON
	}
	return '{' + json + '}';// Return associative JSON
}

/**
 * 2013-08-02 Eric 傳入select及一個值 select會依值自動選到那一筆資料
 * 
 */
function selectedByValue(selected, data) {

	// alert(data);
	var myid = document.getElementById(selected);
	var st = myid.options;
	for ( var i = 0; i < st.length; i++) {

		// alert(st[i].value);
		if (st[i].value == data) {
			myid.selectedIndex = i;
		}
	}
}
