<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/s9/common/common.jsp"%>
<html>
	<head>
		<title>添加角色</title>
		<!-- JS动态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/role/js/roleManage_add.js"></script>
	</head>
	<body class="z-body-detail">
		<form action="../sysRole/addRole.shtml" id="addRole" name="addRole" method="post">
		<input type="hidden" id="msg" value="${msg }"/>
		<input type="hidden" id="roleId" value="${role.roleId }"/>
		<input type="hidden" id="roleName" value="${role.roleName }"/>
		<table width="100%" height="*" border="0" cellspacing="0"
			cellpadding="0" id="js_layoutTable" class="js_layoutTable">
			<tbody>
				<tr>
					<td width="39%" height="30" align="right">
						角色编码：
					</td>
					<td>
						<input type="text" id="roleNo" name="roleNo">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						角色名称：
					</td>
					<td>
						<input type="text" id="roleName" name="roleName">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						可用标识：
					</td>
					<td>
						<input type="radio" id="active" name="isActive" value="1"/>可用
						<input type="radio" id="noactive" name="isActive" value="0"/>不可用
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						角色描述：
					</td>
					<td>
						<input type="text" id="roleDesc" name="roleDesc">
					</td>
				</tr>
				<tr>
				 <td colspan="2" class="aui_footer">
					 <div class="aui_buttons">
						<button value="保存" id="z-dialog-1-OKButton" onclick="forSaveAddRole();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
					</div> 
				 </td>
				</tr>
			</tbody>
		</table>
		</form>
	</body>

</html>