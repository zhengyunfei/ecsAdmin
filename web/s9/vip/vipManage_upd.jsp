<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>修改会员信息</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<script type="text/javascript"
			src="<%=basePath %>/s9/vip/js/vipManage.js?v=100"></script>
		<script type="text/javascript"
			src="<%=basePath %>/s9/res/js/util/md5.js"></script>
		<script type="text/javascript">
		 $(function (){
				var flag="${flg}";

				if(flag=='1'){
					window.parent.reload("会员信息修改成功");
				}else if(flag=='0'){
					$.ligerDialog.error("会员信息修改失败");
					return;
				}
			});
		</script>
	</head>

	<body class="dialogBody">
		<form id="form1" onsubmit="return false"
			action="../vipManage/updUser.shtml" name="form1" method="post">
			<input type="hidden" id="userId" name="userId" value="${user.userId}" />
			<div style="height: 5px;"></div>
			<table width="100%" height="227" align="center" cellpadding="2"
				cellspacing="0">
				<tr>
					<td width="400">
						<div class="z-legend">
							<b>基本信息</b>
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td height="30" align="right">
										手机号：
									</td>
									<td>
										<input type="text" id="phoneNum" name="phoneNum" value="${user.phoneNum}" />
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
								<tr>
									<td height="30" align="right">
										车牌号：
									</td>
									<td>
										<input type="text" id="account" name="account"
											value="${user.account}" />
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<div class="aui_buttons">
				<button value="确定" id="z-dialog-2-OKButton" onclick="forUserUpd();"
					class="z-dlg-button z-dialog-okbutton aui_state_highlight">
					确定
				</button>
			</div>
		</form>
	</body>
</html>
