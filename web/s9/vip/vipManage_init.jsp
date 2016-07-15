<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>用户管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
			<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/js/ligerUI/skins/Aqua/css/ligerui-all.css"	/>
		    <link href="<%=basePath%>/s9/res/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		    <link href="<%=basePath%>/s9/res/js/ligerUI/skins/Gray/css/grid.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="<%=basePath %>/s9/vip/js/vipManage.js?v=130"></script>
	</head>
	<body class="z-body-detail">
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
			<tbody>
				<tr>
					<td>
						<div class="z-toolbar" id="ToolBar1">
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow"> 
									<div class="z-toolbar-nowrap">
										   <%--<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="addUserInit();">
											   <img class="icon021a2" src="../s9/res/img/icon000.png"><b>新建<i></i></b>
										   </a>--%>
										   <a href="javascript:void(0);"  class="z-btn z-btn-flat z-btn-disabled" onclick="delUser();">
											   <img class="icon021a3" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
										   </a>
										   <a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="updUserInit();">
											   <img class="icon021a4" src="../s9/res/img/icon000.png"><b>编辑<i></i></b>
										   </a>
										   <a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="resetPassword();">
											<img  class="icon021a6" src="../s9/res/img/icon000.png"><b>密码重置<i></i></b>
										</a> 
									</div>
								</div>
							</div>
						</div>
						<div class="gradient" style="padding: 5px 12px;">
							
								用户手机号：<input type="text" id="phoneNum" name="phoneNum" value="">
								<input type="button" name="Submitbutton" id="Submitbutton" value="搜索" onclick="doSearch()">
							</div>
						</div>
					</td>
				</tr>
				<tr valign="top">
					<!-- 数据信息 --> 
					<td style="padding: 5px; height: auto;">
						<div class="l-loading" style="display:block;" id="pageloading"></div> 
						<div class="l-clear"></div>
						<div id="maingrid"></div>
					</td> 
				</tr>
			</tbody>
		</table>
		<div id="target1" style="width:200px; margin:3px; display:none;">
			<img id="targetImg" />
		</div>
		<div id="detailDiv" style="width:600px; margin:3px; display:none;">
			
		</div>
		<script type="text/javascript">
			// 初始化加载
			$(document).ready(function() { 
				//查询用户信息列表
				var url = "../vipManage/vipInfoList.shtml";
				findVipInfoList(url);
			});
			function flReload(){
				//关闭弹出窗口
				m.close();
			}
		</script>
	</body>
</html> 
