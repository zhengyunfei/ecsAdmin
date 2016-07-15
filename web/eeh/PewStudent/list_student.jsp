<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>学生选课管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		    <!-- JS静态资源的引用 -->
			<script type="text/javascript" src="<%=basePath%>/eeh/PewStudent/js/list_student.js"></script>

	</head>
	<body class="z-body-detail" style="overflow: hidden">
	    <input type="hidden" id="classId" name="classId" value="${classId}">
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="100%" class="js_layoutTable">
			<tbody>
				<tr>
					<td style="width:100%">
						<div class="z-toolbar" id="ToolBar1" >
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow">
									<div class="z-toolbar-nowrap">
										选择课程
										<select id="courseName" name="courseName" onchange="selectCourse()">
											<option value="">请选择课程</option>
											<c:forEach items="${courseList}" var="course">
												<option value="${course.courseName}">${course.courseName}</option>
											</c:forEach>
										</select>
										<input type="text" id="name" name="name">
										<a href="javascript:query();"  class="z-btn z-btn-flat">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>查询<i></i></b>
										</a>
									</div>
								</div>
							</div>
						</div>

					</td>

				</tr>
				<tr valign="top">
					<!-- 数据信息 -->
					<td style="padding: 5px; height: auto;" colspan="2">
						<div class="l-loading" style="display:block" id="pageloading"></div>
						<div class="l-clear"></div>
						<div id="maingrid"></div>
					</td>
				</tr>
			</tbody>
		</table>

	</body>
</html>
<script type="text/javascript">
	// 初始化加载
	$(function(){
		var courseName=$("#courseName").val();
		init(courseName);
	})
	/**
	 * 初始化查询
	 * */
	function init(param){
		var url='../PewStudent/findAllList.shtml?courseName='+param+"&courseType=0";
		findAllList(url);

	}
   function query(){
	   var  name=$("#name").val();
	   var courseName=$("#courseName").val();
	   var url='../PewStudent/findAllList.shtml?courseName='+courseName+"&name="+name+"&courseType=0";
	   findAllList(url);

   }
	/**
	 * 下载标准表格
	 * **/
	function download(){
		window.location.href="<%=basePath %>/eeh/excel/表格五：培优课学生表.xls";
	}
	/**
	 * 选择课程
	 */
	function selectCourse(){
		var courseName=$("#courseName").val();
		init(courseName);

	}

</script>
