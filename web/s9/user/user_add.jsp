<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

	<head>
		<title>新增用户管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/user/js/userManage.js?version=1"></script>
		<script type="text/javascript" src="<%=basePath %>/s9/res/js/util/md5.js"></script>

		<script type="text/javascript">
		 $(function (){
				var flag="${backInfo}";
				if(flag=='1'){
					window.parent.reload("添加用户信息成功");
				}else if(flag=='0'){
					$.ligerDialog.error("用户保存失败");
					return;
				}
			});
		</script>
	</head>

	<body class="dialogBody">
		<form id="form1" action="../userManage/addUser.shtml" name="form1" method="post" onsubmit="return check();">
			<input type="hidden" id="userId" name="userId" value="${user.userId}" />
			<input type="hidden" id="addUser" name="addUser" value="${user.addUser}" />
			<input type="hidden" id="roleId" name="roleId" />
			<div style="height: 5px;"></div>
			<table width="770" height="227" align="center" cellpadding="2" cellspacing="0">
			    <tbody><tr>
			      <td height="10"></td>
			      <td></td>
			    </tr>
			    <tr>
			      <td width="400">
			   	<div class="z-legend"><b>基本信息</b></div>
			      <table width="100%" border="0" cellpadding="0" cellspacing="0">
			      	<tbody><tr>
							<td width="39%" height="30" align="right">
								用户编号：
							</td>
							<td width="61%" id="tdUserNo">
								<input type="text" id="userNo" name="userNo"  />
							</td>
						</tr>
						<tr>
							<td width="39%" height="30" align="right">
								登陆账号：
							</td>
							<td width="61%" id="tdUserName">
								<input type="text" id="userName" name="userName"  />
								<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
							</td>
						</tr>
						<tr>
							<td height="30" align="right">
								角色类型：
							</td>
							<td>
								<select  id="organId" name="organId"  >
									<option value="老师">老师</option>
									<option value="学生">学生</option>
									<option value="家长">家长</option>
									<option value="管理员">管理员</option>
								</select>
							</td>
						</tr>
						<tr>
							<td height="30" align="right">
								用户真实姓名：
							</td>
							<td>
								<input type="text" id="userRealName" name="userRealName" value=" "/>
							</td>
						</tr>
						<tr id="tr_Password2">
							<td height="30" align="right">
								密码：
							</td>
							<td>
								<input type="password" id="userPwd" name="userPwd" />
								<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
							</td>
						</tr>
						<tr>
							<td height="30" align="right">
								职务：
							</td>
							<td>
								<input type="text" id="position" name="position" value="${user.position}"/>
							</td>
						</tr>
						<tr>
							<td height="30" align="right">
								状态：
							</td>
							<td>
								<input type="radio" id="curStatusCode_01" checked="checked" name="curStatusCode"  />
								<label for="curStatusCode_01">
									启用
								</label>
								<input type="radio" id="curStatusCode_02" name="curStatusCode" />
								<label for="curStatusCode_02">
									停用
								</label>
							</td>
						</tr>
						<tr>
							<td height="30" align="right">
								性别：
							</td>
							<td>
								<input type="radio" id="gender_01" name="gender" value="01">
								<label for="gender_01">
									男
								</label>&nbsp;&nbsp;
								<input type="radio" id="gender_02" name="gender" value="02" >
								<label for="gender_02">
									女
								</label>
							</td>
						</tr>

						<tr>
							<td height="30" align="right">
								微信openid：
							</td>
							<td>
								<input type="text" id="tel" name="tel"  />
							</td>
						</tr>
						<tr>
							<td height="30" align="right">
								移动电话：
							</td>
							<td>
								<input type="text" id="mobile" name="mobile"  />
							</td>
						</tr>

				      </tbody></table></td>
				      <td width="200" valign="top">
						 <div class="z-legend"><b>角色列表</b></div>
						 <div id="roleTree">
						 </div>
					  </td>
				    </tr>
					</tbody></table>
						<div class="aui_buttons">
								<button value="确定" id="z-dialog-2-OKButton" onclick="forSave();"
									class="z-dlg-button z-dialog-okbutton aui_state_highlight">
									确定
								</button>
							</div>

		</form>
	</body>
</html>