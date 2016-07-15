<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>新增系统菜单</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%@include file="/s9/common/common.jsp"%>
		<script type="text/javascript" src="<%=basePath %>/s9/menu/js/sysMenu_add.js?version=1"></script>
	</head>
	<body class="dialogBody">
		<form id="form2" action="../menu/addSysMenu.shtml" method="post" onsubmit="return false;">
			<table width="770" height="227" align="center" cellpadding="2" cellspacing="0">
				<tbody>
					<tr>
						<td height="10"></td>
						<td></td>
					</tr>
					<tr>
						<td width="400">
							<div class="z-legend">
								<b>基本信息</b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="39%" height="30" align="right">
											菜单名称：
										</td>
										<td width="61%">
											<input value="" type="text" id="menuName" name="menuName">
											<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
										</td>
									</tr>
									<tr>
										<td height="30" align="right">
											上级菜单：
												
										</td>
										<td>
											<input type="hidden" id="pmenuCode" name="pmenuCode">
											<input type="text" id="pMenuName" name="pMenuName" readonly="readonly"/>
											<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
										</td>
									</tr>
									<tr style="">
										<td height="30" align="right">
											菜单编码：
										</td>
										<td>
											<input value="" type="text" id="menuCode" name="menuCode">
										</td>
									</tr>
									<tr>
										<td height="30" align="right">
											菜单URL：
										</td>
										<td>
											<input type="text" id="pathCode" name="pathCode">
										</td>
									</tr>
									<tr>
										<td height="30" align="right">
											菜单顺序：
										</td>
										<td>
											<input type="text" id="sortNo" name="sortNo">
										</td>
									</tr>
									<tr>
										<td height="30" align="right">
											菜单图片：
										</td>
										<td>
											<input type="hidden" id="menuImg" name="menuImg">
											<div id="imgClass"></div>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<img class='icon403a20' src='../s9/res/img/icon000.png' onclick=onclickImg();>
										</td>
									</tr>
									<tr>
										<td height="30" align="right">
											菜单状态：
										</td>
										<td>
											<input type="radio" id="isActive" name="isActive" value="0">
											<label for="Status_0">
												停用
											</label>
											<input type="radio" id="isActive" name="isActive" value="1" checked="checked">
											<label for="Status_1">
												启用
											</label>
										</td>
									</tr>
									<tr>
										<td height="30" align="right">
											备注：
										</td>
										<td>
											<input type="text" id="remark" name="remark">
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td width="200" valign="top">
							<div class="z-legend">
								<b>上级菜单</b>
							</div>
							<div id="menuTree">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="aui_buttons">
				<button value="保存" id="z-dialog-1-OKButton" onclick="forSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">
					保存
				</button>
			</div>
		</form>

	</body>
</html>
<script language="javascript">
	$(function(){
		var message = '${message}';
		if(message !="" && message!=null){
			parent.addSysMenuOK(message);
		}
	})
</script>
