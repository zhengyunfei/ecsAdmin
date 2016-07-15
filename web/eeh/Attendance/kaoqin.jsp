<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>考勤列表</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
			<script type="text/javascript" src="<%=basePath%>/eeh/Attendance/js/kaoqin.js?v=111"></script>
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
										选择课程名
										<select id="className" id="className">
											<option value="">请选择课程</option>
											<c:forEach items="${subjectList}" var="subject">
												<option value="${subject.name}">${subject.name}</option>
											</c:forEach>
										</select>
										查询子项
										<select id="type" name="type">
											<option value="">请选择</option>
											<option value="1">迟到</option>
											<option value="2">请假</option>
											<option value="3">早退</option>
											<option value="4">缺勤</option>
											<option value="5">正常</option>
										</select>
										<%--姓名<input type="text" id="name" name="name" >
										性别<input type="text" id="sex" name="sex">--%>
										<a href="javascript:void(0);" class="z-btn z-btn-flat" onclick="query();">
											<img class="icon021a1" src="../s9/res/img/icon000.png"><b>查询<i></i></b>
										</a>
										<%--<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="javascript:void(0);">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>导出excel<i></i></b>
										</a>
										<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="delObj();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
										</a>--%>
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
				var data=$("#form1").serialize();
				var className=$("#className").val();
				var type=$("#type").val();
				var url='../Attendance/findAllList.shtml?className='+className+"&type="+type;
				findAllList(url);
			}
		</script>
	</body>
</html>
