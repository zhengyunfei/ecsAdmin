<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>信息管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
			<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/js/ligerUI/skins/Aqua/css/ligerui-all.css"	/>
		    <link href="<%=basePath%>/s9/res/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		    <link href="<%=basePath%>/s9/res/js/ligerUI/skins/Gray/css/grid.css" rel="stylesheet" type="text/css" />
		    <link  rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/js/calendar/skin/WdatePicker.css">
		    <!-- JS静态资源的引用 -->
			<script type="text/javascript" src="<%=basePath%>/s9/res/js/jquery.min.js"></script>
			<script type="text/javascript" src="<%=basePath%>/s9/res/js/ligerUI/js/ligerui.all.js"></script>
			<script type="text/javascript"  src="<%=basePath%>/s9/res/js/calendar/WdatePicker.js"></script>
			<script type="text/javascript" src="<%=basePath%>/eeh/article/js/list.js?v=2"></script>
			<script type="text/javascript" src="<%=basePath%>/web/res/js/common.js"></script>

	</head>
	<body class="z-body-detail" style="overflow: hidden">
	<input type="hidden" id="rootPath" value="${rootPath}"/>
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="100%" class="js_layoutTable">
			<tbody>
				<tr>
					<td>
						<div class="z-toolbar" id="ToolBar1" >
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow">
									<div class="z-toolbar-nowrap">

									   <a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="add();">
											<img class="icon021a2" src="../s9/res/img/icon000.png"><b>添加<i></i></b>
										</a>
										<%--<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="updUserInit();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>修改<i></i></b>
										</a>--%>
										<a href="javascript:del();"  class="z-btn z-btn-flat" >
											<img class="icon021a3" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
										</a>
										<select id="type" onchange="findByType()">
											<option value="">请选择信息类型</option>
											<option value="通知">通知</option>
											<option value="公告">公告</option>
											<option value="班级风采">班级风采</option>
											<option value="班级明星">班级明星</option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr valign="top">
					<!-- 数据信息 -->
					<td style="padding: 5px; height: auto;">
						<div class="l-loading" style="display:block" id="pageloading"></div>
						<div class="l-clear"></div>
						<div id="maingrid"></div>
					</td>
				</tr>
			</tbody>
		</table>
		<script type="text/javascript">
			// 初始化加载
			var combox;
			$(function(){
				//查询信息列表
				var url = "../Article/findAllList.shtml";
				findArticleList(url);
			});
			function flReload(){
				//关闭弹出窗口
				m.close();
			}
			function findByType(){
				var type=$("#type").val();
				var url = "../Article/findAllList.shtml?type="+type;
				findArticleList(url);
			}
		</script>
	</body>
</html>
