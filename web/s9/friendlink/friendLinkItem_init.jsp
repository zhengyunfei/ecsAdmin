<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html style="overflow:hidden">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>代码项管理</title>
	<!-- 静态资源的引用 -->
	<%@include file="/s9/common/common.jsp"%>
	</head>
	<body class="z-body-detail">
	<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
		<tbody>
		<tr>
			<td>
				<div class="z-toolbar z-toolbar-flat" id="ToolBar1">
				<div class="z-toolbar-ct"><div class="z-toolbar-overflow">
				<div class="z-toolbar-nowrap">
				<a href="javascript:void(0);" onclick="addFriendLinkInfo('${typeId}');return false;"  id="Button1" class="z-btn"><img class="icon003a2" src="/s9/res/img/icon000.png"><b>添加 <i></i></b></a>
				<a href="javascript:void(0);" onclick="editFriendLinkInfo('${typeId}');return false;" id="Button2" class="z-btn"><img class="icon003a4" src="/s9/res/img/icon000.png"><b>修改 <i></i></b></a>
				<a href="javascript:void(0);" onclick="delFriendLinkInfo();return false;" id="Button3" class="z-btn"><img class="icon003a3" src="/s9/res/img/icon000.png"><b>删除 <i></i></b></a>
		        </div></div></div></div>
		    </td>
		</tr>
		<tr>
			<td style="padding: 5px; height: 182px;" height="*">
				<div id="dg1_wrap" class="z-datagrid dg_scrollable dg_nobr"  style="height: 99.1%;">
				<div id="dg1_dock" class="dg_dock"><div id="dg1_dock_trigger" class="dock_trigger"></div>
				<div id="dg1_dock_station" class="dock_station"></div></div>
					<div class="l-loading" style="display:block" id="pageloading"></div> 
				  	<div class="l-clear"></div>
					<div id="maingrid"></div>
				</div>
			</td>
		</tr>
		</tbody>
	</table>
	<script type="text/javascript" src="../s9/friendlink/js/friendLinkItem.js?v=1"></script>
	<script type="text/javascript">
		//初始化加载数据
		 $(function (){
			 alert("111");
		    var url = "../friendLinkManage/findFriendLinkInfoList?typeId=${typeId}";
	       	initChildData(url);
	    });  
	</script>
	</body>
</html>