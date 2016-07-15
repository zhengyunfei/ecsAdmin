<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/s9/res/js/ligerUI/skins/Aqua/css/ligerui-all.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/s9/res/css/spirites.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/s9/res/css/common.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/s9/res/css/default.css">
		<script type="text/javascript"
			src="<%=basePath%>/s9/res/js/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/s9/res/js/ligerUI/js/ligerui.all.js"></script>
		<script type="text/javascript" src="<%=basePath%>/s9/coupon/js/add.js"></script>
	</head>
	<body class="z-body-detail"
		style="background-color: white; overflow: hidden;">
		<div class="z-legend">
			<b>基本信息</b>
		</div>
		<form onsubmit="return false" action="../coupon/addSave.shtml"
			id="form1" name="form1" method="post">
			<table width="100%" cellpadding="12" cellspacing="10">
				<tbody>
					<tr>
						<td height="28" align="right" style="padding-right: 5px;">
							<font color="#ff0000">*</font><span class="dye" nowrap="nowrap">洗车卷名称：</span>
						</td>
						<td colspan="3" align="left">
							<input name="name" type="text" id="name" style="width: 380px;">
						</td>
					</tr>
					<tr>
						<td height="28" align="right" style="padding-right: 5px;">
							<font color="#ff0000">*</font><span class="dye" nowrap="nowrap">备注：</span>
						</td>
						<td colspan="3" align="left">
							<textarea rows="" cols="" style="width: 380px;" id="info" name="info"></textarea>
						</td>
					</tr>
					<tr>
						<td height="28" align="right" style="padding-right: 5px;"
							nowrap="nowrap">
							<font color="#ff0000">*</font><span class="dye">价格：</span>
						</td>
						<td colspan="3" align="left">
							<input name="price" type="text" style="width: 380px;" id="price"
								size="50">
						</td>
					</tr>
					<tr>
						<td height="28" align="right" style="padding-right: 5px;"
							nowrap="nowrap">
							<font color="#ff0000">*</font><span class="dye">原价：</span>
						</td>
						<td colspan="3" align="left">
							<input name="oprice" type="text" id="oprice"
								style="width: 380px;">
						</td>
					</tr>
					<tr>
						<td height="28" align="right" style="padding-right: 5px;"
							nowrap="nowrap">
							<font color="#ff0000">*</font><span class="dye">数量：</span>
						</td>
						<td colspan="3" align="left">
							<input name="number" type="text" id="number"
								style="width: 380px;">
						</td>
					</tr>
					<tr>
						<td height="28" align="right" style="padding-right: 5px;"
							nowrap="nowrap">
							<font color="#ff0000">*</font><span class="dye">是否有效：</span>
						</td>
						<td colspan="3" align="left">
							<select id="status" name="status" style="width: 380px">
								<option value="">
									请选择
								</option>
								<option value="0">
									无效
								</option>
								<option value="1">
									有效
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td height="28" align="right" style="padding-right: 5px;"
							nowrap="nowrap">
							<font color="#ff0000">*</font><span class="dye">图片名称：</span>
						</td>
						<td colspan="3" align="left">
							<input name="pic" type="text" style="width: 380px;" id="pic"
								   size="50">
						</td>
					</tr>
					<tr>
						<td colspn="4" align="right">

						</td>
					</tr>
				</tbody>
			</table>
			<div align="right" style="padding-top: 20px; padding-right: 50px">
				<a href="javascript:save();" id="saveBtn" class="z-btn z-btn-flat">
					<img class="icon003a16" src="<%=basePath%>/s9/res/img/icon000.png">
					<b>保存</b> </a>
			</div>

		</form>
	</body>
</html>
