<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>教室管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		    <link  rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/js/calendar/skin/WdatePicker.css">
		    <!-- JS静态资源的引用 -->
			<script type="text/javascript"  src="<%=basePath%>/s9/res/js/calendar/WdatePicker.js"></script>
			<script type="text/javascript" src="<%=basePath%>/eeh/classroom/js/list.js?v=1"></script>
			<script type="text/javascript" src="<%=basePath%>/web/upload/jquery.uploadify.min.js"></script>
			<script type="text/javascript" src="<%=basePath %>/eeh/classroom/js/upload.js"></script>

	</head>
	<body class="z-body-detail" style="overflow: hidden">
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="100%" class="js_layoutTable">
			<tbody>
				<tr>
					<td>
						<div class="z-toolbar" id="ToolBar1" >
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow">
									<div class="z-toolbar-nowrap">
										<a href="javascript:void(0);" class="z-btn z-btn-flat" onclick="add();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>添加教室<i></i></b>
										</a>

										<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="delObj();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
										</a>
										<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="download();">
											<img class="icon021a2" src="../s9/res/img/icon000.png"><b>下载教室表<i></i></b>
										</a>
										<select id="tbId" name="tbId">
											<option value="">选择教学楼</option>
											<c:forEach items="${teachbuildList}" var="tb">
												<option value="${tb.id}">${tb.name}</option>
											</c:forEach>
										</select>
										<a href="javascript:void(0);" id="importExcel"   class="z-btn z-btn-flat">
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
		<script type="text/javascript">
			// 初始化加载
			$(function(){
				var url='../classroom/findAllList.shtml';
				findAllList(url);
			})

           function download(){
			   window.location.href="<%=basePath%>/eeh/excel/教室标准表格.xlsx";
		   }
		</script>
	</body>
</html>
