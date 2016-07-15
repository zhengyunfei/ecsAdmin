<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>老师选课管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		    <!-- JS静态资源的引用 -->
		    <script type="text/javascript" src="<%=basePath%>/web/upload/jquery.uploadify.min.js"></script>
			<script type="text/javascript" src="<%=basePath%>/eeh/PewStudent/js/list.js"></script>
			<script type="text/javascript" src="<%=basePath %>/eeh/PewStudent/js/upload.js?v=1"></script>

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
										选择班级
										<select id="gradeName" name="gradeName"  onchange="selectGrade()">
											<c:forEach items="${gradeList}" varStatus="vs" var="grade">
												<option value="${grade.name}">${grade.name}</option>
											</c:forEach>
										</select>
										<a href="javascript:void(0);" class="z-btn z-btn-flat" onclick="add();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>手动添加学生<i></i></b>
										</a>

										<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="delObj();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
										</a>
										<a href="javascript:download();"  class="z-btn z-btn-flat">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>下载标准表格<i></i></b>
										</a>
										<a href="javascript:void(0);"  class="z-btn z-btn-flat" id="importExcel">

										</a>
										<%--<select id="status" name="status">
											<option value="已审">已审</option>
											<option value="待审">待审</option>
										</select>
											<a href="javascript:shenhe();"  class="z-btn z-btn-flat">
												<img class="icon021a4" src="../s9/res/img/icon000.png"><b>审核<i></i></b>
											</a>--%>
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
		var gradeName=$("#gradeName").val();
		var url='../PewStudent/findAllList.shtml?courseName='+param+"&courseType=1&classId="+gradeName;
		findAllList(url);

	}
   function query(){
	   var  name=$("#name").val();
	   var courseName=$("#courseName").val();
	   var gradeName=$("#gradeName").val();
	   var url='../PewStudent/findAllList.shtml?courseName='+courseName+"&name="+name+"&courseType=1&classId="+gradeName;
	   grid.setOptions({newPage:1});
	   findAllList(url);

   }
	/**
	 * 下载标准表格
	 * **/
	function download(){
		window.location.href="<%=basePath %>/eeh/excel/表格五：培优课学生表.xlsx";
	}
	/**
	 * 选择课程
	 */
	function selectCourse(){
		//var courseName=$("#courseName").val();
		//init(courseName);
		query();

	}

</script>
