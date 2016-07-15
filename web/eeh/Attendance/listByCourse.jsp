<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>按课程统计</title>
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
			<script type="text/javascript" src="<%=basePath%>/eeh/Attendance/js/list.js?v=111"></script>

	</head>
	<body class="z-body-detail" style="overflow: hidden">
	<form id="form1" method="post">
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="100%" class="js_layoutTable">
			<tbody>
				<tr>
					<td>
						<div class="z-toolbar" id="ToolBar1" >
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow">
									<div class="z-toolbar-nowrap">
										选择课程:
										<select id="courseName" id="courseName">
											<option value="">请选择课程</option>
											<c:forEach items="${subjectList}" var="subject">
												<option value="${subject.name}">${subject.name}</option>
											</c:forEach>
										</select>
										其他查询条件:
										姓名<input type="text" id="name" name="name" >
										性别<input type="text" id="sex" name="sex">
										<a href="javascript:void(0);" class="z-btn z-btn-flat" onclick="query();">
											<img class="icon021a1" src="../s9/res/img/icon000.png"><b>查询<i></i></b>
										</a>
										<a href="javascript:exportExcl();"  class="z-btn z-btn-flat" onclick="javascript:void(0);">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>导出excel<i></i></b>
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
						<div class="l-loading" style="display:block" id="pageloading"></div>
						<div class="l-clear"></div>
						<div id="maingrid"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
		<script type="text/javascript">
			// 初始化加载
			$(function(){
				var url='../Attendance/findAllList.shtml';
				findAllList(url);
			})
			function query(){
				var courseName=$("#courseName").val();
				var name=$("#name").val();
				var sex=$("#sex").val();
				var url='../Attendance/findAllList.shtml?courseName='+courseName+"&name="+name+"&sex="+sex;
				findAllList(url);
			}
			//导入统计表 to excel
			function exportExcl(){
				var data={};
				var url="<%=basePath%>/excelOperate/exportTongjiToExcel.shtml";
				ajax(url,data)
			}
			function ajax(url,data){
				$.ajax( {
					type :"post",
					url :url,
					data :data,
					async : false,
					dataType : "json",
					beforeSend : function(XMLHttpRequest) {
					},
					timeout:20000,
					success : function(r) {
						if(r.success){
							window.location.href= "<%=basePath%>"+r.path;
						}

					},
					error : function() {
						$.ligerDialog.error("操作失败！");
					}
				});
			}

		</script>
	</body>
</html>
