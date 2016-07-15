<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>添加代码分类信息</title>
		<!-- CSS静态资源的引用 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- JS静态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/vip/js/vipManage.js"></script>
		<script type="text/javascript" src="<%=basePath %>/s9/res/js/util/md5.js"></script>
		<script type="text/javascript">
		 $(document).ready(function(){
				var flag="${msg}";
				if(flag=='sucess'){
					$.ligerDialog.success("保存成功");
					window.parent.flReload();
				}else if(flag=='error'){
					$.ligerDialog.error("保存失败");
					return;
				}
			});	
		</script>
	</head>
	<body>
		<form action="" id="saveUpdResetPwd" name="saveUpdResetPwd" method="post">
		<input type="hidden" id="userId" name="userId" value="${userId}">
		<input type="hidden" name="userPassword" id="userPassword">
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" >
			<tbody>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td width="30%" height="30" align="right">
						重置密码：
					</td>
					<td width="70%">
						<input type="password" id="newPwd" name="password" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						确认密码：
					</td>
					<td width="70%">
						<input type="password" id="newPwdAgain" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr></tr>
			</tbody>
		</table>
		<div class="aui_buttons">
			<button value="保存" type="button" id="z-dialog-1-OKButton" onclick="saveResetPwd();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		</div>
		</form>
	</body>
</html>