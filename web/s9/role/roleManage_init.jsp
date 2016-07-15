<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/s9/common/common.jsp"%>
<html>
	<head>
		<title>角色管理树</title>
		<!-- JS动态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/role/js/roleManage.js"></script>
	</head>
	<body class="z-body-list">
		<input type="hidden" name="treeNodeId" id="treeNodeId" />
		<table id="js_layoutTable" width="100%" height="100%" border="0"
			cellspacing="0" cellpadding="0" class="js_layoutTable">
				<tr valign="top">
					<td width="200" height="100%" class="centerColumnWrap"
						style="width: 220px;">
						<table id="js_layoutTable" width="100%" height="100%" border="0"
							cellspacing="0" cellpadding="0" class="js_layoutTable">
							<tbody>
								<tr>
									<td height="33">
										<div class="z-toolbar" id="ToolBar1">
											<div class="z-toolbar-ct">
												<div class="z-toolbar-overflow">
													<div class="z-toolbar-nowrap">
														<a href="javascript:void(0);" id="Button1" class="z-btn z-btn-flat" onclick="addRole();"><img class="icon003a2" src="../s9/res/img/icon000.png"><b>新建<i></i></b></a>
														<a href="javascript:void(0);" id="Button2" class="z-btn z-btn-flat" onclick="updRole();"><img class="icon003a4" src="../s9/res/img/icon000.png"><b>修改<i></i></b></a>
														<a href="javascript:void(0);" id="Button3" class="z-btn z-btn-flat" onclick="delRole();"><img class="icon003a3" src="../s9/res/img/icon000.png"><b>删除<i></i></b></a>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td height="100%"
										style="padding-top: 0px; padding-right: 2px; padding-bottom: 0px; padding-left: 2px;">
										<div class="dataList-wrap" id="dl1_wrap">
											<div id="roleTree" style="height: auto;"></div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td style="height: 100%; zoom: 1;">
						<div id="splitter1" class="z-splitter-v shadow-v"></div>
						<iframe id="roleManage" name="roleManage" frameborder="0"
							width="100%" height="100%" src="../s9/role/roleAndUser_init.jsp" scrolling="auto"></iframe>
					</td>
				</tr>
		</table>
	</body>
</html>
