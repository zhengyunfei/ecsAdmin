<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>机构管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<script type="text/javascript"
			src="<%=basePath%>/s9/project/js/project.js"></script>
	</head>
	<script type="text/javascript">
	$( function() {
		var flag = "${backInfo}";
		if (flag == '1') {
			window.parent.reload("订单信息保存成功");
		} else if (flag == '0') {
			$.ligerDialog.error("订单信息保存失败");
			return;
		}
	});
</script>
	<body class="z-body-detail">
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0"
			cellpadding="0" height="*" class="js_layoutTable">
			<tbody>
				<tr>
					<td>
						<div class="z-toolbar" id="ToolBar1">
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow">
									<div class="z-toolbar-nowrap">
										<a href="javascript:void(0);" id="Button2"	class="z-btn z-btn-flat" onclick="forAddInit();"> 
												<img class="icon021a2" src="../s9/res/img/icon000.png"><b>新建<i></i></b> 
										</a>
										
										 <a href="javascript:void(0);" id="BtnSave"	class="z-btn z-btn-flat" onclick="forUpdateInit();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>修改<i></i></b>
										</a>

									
										<a href="javascript:void(0);" id="Button5" class="z-btn z-btn-flat" onclick="forDelete();"> 
												<img class="icon021a3" src="../s9/res/img/icon000.png"><b>删除<i></i></b> 
										</a> 
										
									</div>
								</div>
							</div>
						</div>
						
					</td>
				</tr>
				<tr valign="top">
					<!-- 数据信息 -->
					<td style="padding: 5px; height: auto;">
						<div class="l-loading" style="display: block" id="pageloading"></div>
						<div class="l-clear"></div>
						<div id="maingrid"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
