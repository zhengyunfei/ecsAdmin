<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html >
<head>

<title>图片管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 静态资源的引用 -->
<%@include file="/s9/common/common.jsp"%>
<!-- JS静态资源的引用 -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/css/common.css" >
<script type="text/javascript"  src="<%=basePath%>/s9/pic/js/picManage_init.js"></script>
</head>
<body class="dialogBody">
		<form id="editeForm"  method="post" onsubmit="return false;">
		<input type="hidden" id="backInfo" value="${backInfo}"/>
		<input type="hidden" id="id"  name="id" value="${bo.id}"/>
			<table width="400" height="227" align="center" cellpadding="2" cellspacing="0">
				<tbody>
					<tr>
						<td height="10"></td>
						<td></td>
					</tr>
					<tr>
						<td width="300">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="39%" height="30" align="right">
											图片分类名称：
										</td>
										<td width="61%">
											<input  type="text" id="name" name="name" value="${bo.name}">
											<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
										</td>
									</tr>
									
									<tr style="">
										<td height="30" align="right">
											备注：
										</td>
										<td>
											<input type="text" id="remark" name="remark" value="${bo.remark }">
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					
					</tr>
				</tbody>
			</table>
			<div class="aui_buttons">
				<button value="保存" id="editeSave" class="z-dlg-button z-dialog-okbutton aui_state_highlight">
					保存
				</button>
			</div>
		</form>

	</body>
</html>
