<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/s9/common/common.jsp"%>
<html>
	<head>
		<title>角色管理</title>
		<!-- JS动态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/role/js/roleAndUser.js"></script>
	</head>
	<body class="z-body-detail">
	<input type="hidden" id="roleId" name="roleId" value="${role.roleId }"/>
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0" id="js_layoutTable" class="js_layoutTable">
			<tbody>
				<tr>
					<td height="33" valign="top" style="position: relative">
						<div class="z-tabpanel">
							<div class="z-tabpanel-ct">
								<div class="z-tabpanel-overflow">
									<div class="z-tabpanel-nowrap">
										<a href="javascript:void(0);" id="role"
											class="z-tab z-tab-current" onclick="roleInfo();"><b>基本信息</b>
										</a>
										<a href="javascript:void(0);" id="menu"
											class="z-tab" onclick="roleMenu();"><b>菜单权限</b>
										</a>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="100%" valign="top" style="height: auto;">
						<div class="ui-page-container" id="roleInfo">
							<div class="ui-page ui-body-c" style="">
								&nbsp;
							</div>
							<div class="ui-page ui-body-c ui-page-active" style="">
								<table width="100%" id="js_layoutTable" border="0"
									cellspacing="0" cellpadding="0" height="100%"
									class="js_layoutTable">
									<tbody>
										<tr>
											<td style="padding: 5px 5px;">
												<table width="100%" cellpadding="2" cellspacing="0"
													class="z-datagrid">
													<tbody>
														<tr class="dataTableHead">
															<td width="2%">&nbsp;</td>
															<td width="14%">名称</td>
															<td width="35%">值</td>
															<td width="14%">名称</td>
															<td width="35%">值</td>														</tr>
														<tr>
															<td class="noellipsis">&nbsp;</td>
															<td>角色编码：</td>
															<td>${role.roleNo}</td>
															<td>角色名称：</td>
															<td>${role.roleName}</td>

														</tr>
														<tr>
															<td class="noellipsis">&nbsp;</td>
															<td>创建人员：</td>
															<td>${role.userNo}</td>
															<td>创建时间：</td>
															<td>${role.createTime}</td>
														</tr>
														<tr>
															<td class="noellipsis">&nbsp;</td>
															<td>可用标识：</td>
															<td>${role.isActive}</td>
															<td>角色描述：</td>
															<td>${role.roleDesc}</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td>
												<div class="z-toolbar z-toolbar-flat" id="ToolBar1">
													<div class="z-toolbar-ct">
														<div class="z-toolbar-overflow">
															<div class="z-toolbar-nowrap">
																<a href="javascript:void(0);" id="Button1" class="z-btn" onclick="addUserToRole('${role.roleId}');"><img class="icon003a2" src="../s9/res/img/icon000.png"><b>添加用户到角色<i></i></b></a>
																<a href="javascript:void(0);" id="Button3" class="z-btn" onclick="delUserFromRole(${role.roleId});"><img class="icon003a3" src="../s9/res/img/icon000.png"><b>从角色中删除用户<i></i></b></a>
															</div>
														</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td style="padding: 5px; height: auto;" height="*">
												<div class="l-clear"></div>
												<div id="maingrid"></div>
												<div id="div1" style="display: block;"></div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						
						<div id="roleMenu" style="display: none;height:100%">
							<!-- 菜单权限 -->
							<iframe id="iRoleMenu" name="iRoleMenu" frameborder="0" width="100%" height="100%" src="../s9/menu/menuRole_init.jsp" scrolling="auto"></iframe>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>