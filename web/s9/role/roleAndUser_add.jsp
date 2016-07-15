<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/s9/common/common.jsp"%>
<html>
	<head>
		<title>添加用户到角色</title>
		<!-- JS动态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/role/js/roleAndUser_add.js"></script>
	</head>
	<body class="z-body-detail">
		<input type="hidden" id="roleId" value="${roleId}"/>
		<table width="100%" height="*" border="0" cellspacing="0"
			cellpadding="0" id="js_layoutTable" class="js_layoutTable">
			<tbody>
				<tr>
					<td>
						<div class="gradient" style="padding: 5px 12px;">
							用户名称：<input type="text" id="userName" name="userName">
							真实姓名：<input type="text" id="realName" name="realName">
							<input type="button" name="Submitbutton" id="Submitbutton" value="搜索" onclick="doSearch()">
						</div>
					</td>
				</tr>
				<tr valign="top">
					<!-- 数据信息 --> 
					<td style="padding: 5px; height: auto;">
						<div class="l-loading" style="display:block" id="pageloading"></div> 
						<div class="l-clear"></div>
						<div id="maingrid"></div>
					</td> 
				</tr>
				<tr>
				 <td class="aui_footer">
					 <div class="aui_buttons">
						<button value="确定" id="z-dialog-1-OKButton" onclick="forSaveUserToRole();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">确定</button>
					</div> 
				 </td>
				</tr>
			</tbody>
		</table>
	</body>
</html>