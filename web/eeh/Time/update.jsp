<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
		 pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>修改时段</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<link  rel="stylesheet" type="text/css" href="../s9/res/js/calendar/skin/WdatePicker.css">
	<!-- JS静态资源的引用 -->
	<script type="text/javascript"  src="../s9/res/js/calendar/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/Time/js/update.js?v=1"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/Time/js/number.js"></script>
</head>

<body class="dialogBody">
<form id="form1" onsubmit="return false;"
	  action="../Time/forUpdateAjax.shtml" name="form1" method="post">
	<input type="hidden" id="id" name="id" value="${bo.id}"/>
	<table width="100%"  align="center" cellpadding="2"
		   cellspacing="0">
		<tr>
			<td width="400">
				<div class="z-legend">
					<b>基本信息</b>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tbody>
					<tr>
						<td width="20%" height="30" align="right">
							时段类型：
						</td>
						<td width="80%" >
							<select id="type" name="type" style="width: 300px">
								<option value="0" <c:if test="${bo.type=='0'}">selected="selected" </c:if>>日程课程</option>
								<option value="1" <c:if test="${bo.type=='1'}">selected="selected" </c:if>>培优课程</option>
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							时段名称：
						</td>
						<td width="80%" >
							<input type="text" id="name" name="name"
								   style="width: 300px"  value="${bo.name}"/>
										<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							时段开始时间：
						</td>
						<td width="80%" >
							<input type="hidden" id="time" name="time" value="${bo.time}">
							<input type="text" id="startTime" name="startTime" onfocus="WdatePicker({dateFmt:'HH:mm',alwaysUseStartDate:true})" class="Wdate"
								   style="width: 300px"   value="${bo.startTime}"/>
										<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							时段结束时间：
						</td>
						<td width="80%" >
							<input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'HH:mm',alwaysUseStartDate:true})" class="Wdate"
								   style="width: 300px"   value="${bo.endTime}"/>
										<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>

					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<div class="aui_buttons">
		<button value="确定" id="z-dialog-2-OKButton"
				onclick="javascript:forUpdate();"
				class="z-dlg-button z-dialog-okbutton aui_state_highlight">
			确定
		</button>
	</div>
</form>
</body>
</html>
