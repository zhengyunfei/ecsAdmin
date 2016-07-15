<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>培优座位表</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
			<script type="text/javascript" src="<%=basePath%>/eeh/Seat/js/pewList.js"></script>
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
										<%--<a href="javascript:void(0);" class="z-btn z-btn-flat" onclick="add();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>添加座位<i></i></b>
										</a>
										<a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="delObj();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
										</a>--%>
										    科目
											<select id="kemu" name="kemu" style="width:60px;">
												<option value="">选择科目</option>
												<c:forEach items="${subjectList}" var="subject">
													<option value="${subject.name}">${subject.name}</option>
												</c:forEach>
											</select>
											教室
											<select id="classRoom" name="classRoom" style="width:60px;">
												<option value="">选择教室</option>
												<c:forEach items="${gradeList}" var="grade">
													<option value="${grade.name}">${grade.name}</option>
												</c:forEach>
											</select>

											是否已导入座位表
										<select id="seatSetStatus" name="seatSetStatus">
											<option value="">请选择</option>
											<option value="0">未导入</option>
											<option value="1">已导入</option>
										</select>
										<a href="javascript:query();"  class="z-btn z-btn-flat">
											<img class="icon021a5" src="../s9/res/img/icon000.png"><b>查询<i></i></b>
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
				var url='../Course/findAllList.shtml';
				findAllList(url);
			})
			function query(){
				var data=$("#form1").serialize();
				var url='../Course/findAllList.shtml?'+data;
				findAllList(url);
			}
		</script>
	</body>
</html>
