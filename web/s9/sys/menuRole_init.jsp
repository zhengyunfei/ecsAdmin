<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>角色权限</title>
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/s9/common/common.jsp"%>
	<script type="text/javascript" src="<%=basePath %>/web/res/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>/s9/sys/js/menuRole.js"></script>
  </head>
  
  <body>
    <form id="menuPriv_form1" method="post" style="height: 100%; ">
		<input type="hidden" id="Type" value="R">
		<input type="hidden" id="roleId" name="roleId">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
	<tbody><tr id="menuPriv_trToolbar">
		<td height="33">
		<div class="z-toolbar" id="menuPriv_toolbar1">
		<div class="z-toolbar-ct">
		<div class="z-toolbar-overflow">
		<div class="z-toolbar-nowrap">
		<a href="javascript:void(0);" id="ButtonSave" class="z-btn z-btn-flat" onclick="save();">
		<img class="icon018a16" src="../s9/res/img/icon000.png"><b>
					保存
		<i></i></b></a>
		<a href="javascript:void(0);" id="AllSelect" class="z-btn z-btn-checkable" onclick="onSelectAllClick();">
		<img src="../s9/res/img/yes.gif" width="20" height="20"><b>
					全选
		<i></i></b></a></div></div></div></div></td>
	</tr>
	<tr valign="top">
		<td height="*" style="padding-top: 7px; padding-right: 7px; padding-bottom: 7px; padding-left: 7px; height: 750px; ">
		<div class="z-overflowPanel" id="menuInfo">
			<!-- 菜单资源项数据 -->
 		</div>
		</td>
	</tr>
</tbody></table>
</form>
  </body>
</html>
