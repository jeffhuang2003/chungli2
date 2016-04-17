<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  session="false"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="../css/jquery/ui/jquery.ui.all.css" />
<link rel="stylesheet" href="../css/jquery/dynatree/ui.dynatree.css" />
<link rel="stylesheet" href="../css/tt.css" />
<script type="text/javascript" src="../js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript"  src="../js/common.js"></script>
<script type="text/javascript" src="../js/jquery/ui/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="../js/jquery/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="../js/jquery/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../js/jquery/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="../js/jquery/dynatree/jquery.dynatree.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$('#btnRefresh').click(function(){
		window.location.reload();
	});		
		
	var result=null;
	$("#tabs").tabs({
		selected:0
	});
	$.ajax({
		url:"LoginActImpl_menuInit.action",
		type:"post",
		dataType:"json",
		success:function(data){
			result = data;
			if(data.ajaxResult == "success"){
				$("#tab1").dynatree({
					minExpandLevel: 1,
					children: eval(data.queueJson),
					onClick: function(node, event){
						if( node.data.href ){
							window.open(node.data.href+"?"+node.data.hrefValue+"="+node.data.key,node.data.target);
						}
					}
				});
				$("#tab2").dynatree({
					minExpandLevel: 1,
					children: eval(data.functionJson),
					onClick: function(node, event){
						node.focus(false);
						if( node.data.href ){
							window.open(node.data.href, node.data.target);
						}
					}
				});
			}
		},
		error:function(){
	    	  alert('系統逾時或發生錯誤!');
	    },
	    complete:function(){
	    	$("#tab1").dynatree("getRoot").visit(function(node){
	    	    node.expand(true);
	    	});
	    	$("#tab2").dynatree("getRoot").visit(function(node){
	    	    node.expand(true);
	    	});
	        doAjaxInitProcess(result);
	    }
	});	
		
});
</script>
</head>
<body>

<%@ include file="/include/showMessage.jsp" %> 
	<fieldset >
		<legend style="border:0px;cursor:hand;"></legend>
		<button name="btnRefresh" id="btnRefresh">更新</button>
	</fieldset>
	<div id="tabs">
		<ul>
			<li><a href="#tab1">QUEUE</a></li>
			<li><a href="#tab2">Menu</a></li>
		</ul>
		<div>
			<div id="tab1">
                            
			</div>
			<div id="tab2">

			</div>
		</div>
	</div>
</body>
</html>