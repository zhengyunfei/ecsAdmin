<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>学生管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
			<script type="text/javascript" src="<%=basePath%>/eeh/student/js/list.js"></script>
		    <script type="text/javascript" src="<%=basePath%>/web/upload/jquery.uploadify.min.js"></script>
			<script type="text/javascript" src="<%=basePath %>/eeh/student/js/upload.js?v=1"></script>
	</head>
	<body class="z-body-detail" style="overflow: hidden">
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="100%" class="js_layoutTable">
			<tbody>
				<tr>
					<td style="width:100%" align="left">
						<div class="z-toolbar" id="ToolBar1" >
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow">
									<div class="z-toolbar-nowrap">
										<%--<a href="javascript:void(0);" class="z-btn z-btn-flat" onclick="add();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>添加学生<i></i></b>
										</a>--%>
										<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="delObj();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
										</a>

										选择班级：
										<select id="classId" name="classId" onchange="selectGrade()">
											<option value="">请选择班级</option>
											<c:forEach items="${gradeList}" var="grade">
												<c:choose>
													<c:when test="${classId==grade.name}">
														<option value="${grade.name}" selected="selected">${grade.name}</option>
													</c:when>
													<c:otherwise>
														<option value="${grade.name}">${grade.name}</option>
													</c:otherwise>
												</c:choose>

											</c:forEach>
										</select>
												<a href="javascript:download();"  class="z-btn z-btn-flat">
													<img class="icon021a4" src="../s9/res/img/icon000.png"><b>下载班级学生表<i></i></b>
												</a>
												<a href="javascript:void(0);"  class="z-btn z-btn-flat" id="importExcelBtn">
													<img class="icon021a4" src="../s9/res/img/icon000.png"><b>导入<i></i></b>
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
		var classId=$("#classId").val();
		var url='../student/findAllList.shtml';
		if(''!=classId&&null!=classId){
			url+="?classId="+classId;
		}
		findAllList(url);
	})
	/**
	 * 下载标准表格
	 * **/
	function download(){
		window.location.href="<%=basePath %>/eeh/excel/表格一：班级学生表.xlsx";
	}
	function selectGrade(){
		var classId=$("#classId").val();
		var url='../student/findAllList.shtml?classId='+classId;
		findAllList(url);

	}
</script>
