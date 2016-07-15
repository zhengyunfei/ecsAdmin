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
	<title>修改课程</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<script type="text/javascript" src="<%=basePath %>/eeh/Course/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/Course/js/update.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/Course/js/number.js"></script>
</head>

<body class="dialogBody">
<form id="form1" onsubmit="return false;"
	  action="../Course/forUpdateAjax.shtml" name="form1" method="post">
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
							选择科目：
						</td>
						<td width="80%" >
							<select id="kemu" name="kemu" style="width:300px">
								<option>请选择科目</option>
								<c:forEach items="${subjectList}" var="subject" varStatus="vs">
									<option value="${subject.name}" <c:if test="${subject.name==bo.kemu}">selected="selected" </c:if>>${subject.name}</option>
								</c:forEach>
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>

					<tr>
						<td width="20%" height="30" align="right">
							上线人数：
						</td>
						<td width="80%" >
							<input type="text" id="peopleMax" name="peopleMax" value="${bo.peopleMax}"
								   style="width: 300px"  onkeyup="clearNoNum(this)"/>
										<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							授课老师：
						</td>
						<td width="80%" >
							<select id="teacher" name="teacher" style="width:300px">
								<option>请选择</option>
								<c:forEach items="${teacherList}" varStatus="vs" var="teacher">
									<option value="${teacher.name}" <c:if test="${teacher.name==bo.teacher}">selected="selected" </c:if>>${teacher.name}</option>
								</c:forEach>
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							上课时间：
						</td>
						<td width="80%" >
							<select id="time" name="time" style="width:300px">
								<option>请选择</option>
								<c:forEach items="${timeList}" var="time" varStatus="vs">
									<option value="${time.id}" <c:if test="${time.name==bo.week&&time.time==bo.schoolTime}">selected="selected" </c:if>>${time.name}&nbsp;${time.time}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="week" name="week" value="${bo.week}"/>
							<input type="hidden" id="schoolTime" name="schoolTime" value="${bo.schoolTime}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							授课教室：
						</td>
						<td width="80%" >
							<select id="classRoom" name="classRoom" style="width:300px">
								<option>请选择</option>
								<c:forEach items="${classRoomList}" varStatus="vs" var="room">
									<option value="${room.name}" <c:if test="${room.name==bo.classRoom}">selected="selected" </c:if>>${room.name}</option>
								</c:forEach>
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
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
