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
	<title>修改教师</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<script type="text/javascript" src="<%=basePath %>/eeh/teacher/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/teacher/js/update.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/teacher/js/number.js"></script>
</head>

<body class="dialogBody">
<form id="form1" onsubmit="return false;"
	  action="../teacher/forUpdateAjax.shtml" name="form1" method="post">
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
							教师名称：
						</td>
						<td width="80%" >
							<input type="text" id="name" name="name" style="width: 300px"  value="${bo.name}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							所属科目：
						</td>
						<td width="80%" >
							<select id="pkemu"    style="width: 150px" onchange="loadKeMu()">
								<option value="">选择科目</option>
								<option value="基础课程" selected>基础课程</option>
								<option value="培优课程">培优课程</option>
								<option value="其他">其他</option>
							</select>
							<select  id="subjectId" name="subjectId" style="width: 150px"   value="${bo.subjectId}">
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<%--<tr>
						<td width="20%" height="30" align="right">
							所属年级：
						</td>
						<td width="80%" >
							<select  id="gradeId" name="gradeId" style="width: 300px"  onchange="loadBanJi()">
								<option value="">选择年级</option>
								<option value="1">一年级</option>
								<option value="2">二年级</option>
								<option value="3">三年级</option>
								<option value="4">四年级</option>
								<option value="5">五年级</option>
								<option value="6">六年级</option>
								<option value="7">初一</option>
								<option value="8">初二</option>
								<option value="9">初三</option>
								<option value="10">高一</option>
								<option value="11">高二</option>
								<option value="12">高三</option>
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>--%>
					<tr>
						<td width="20%" height="30" align="right">
							授课班级：
						</td>
						<td width="80%" >
							<select  id="classId" name="classId"  style="width: 300px" >
								<option value="">选择班级</option>
								<c:forEach items="${gradeList}" var="grade">
									<option value="${garde.name}" <c:if test="${grade.name==bo.classId}">selected="selected" </c:if>>${grade.name}</option>
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
<script>
	init();
	function init(){
		$("#subjectId").append("<option value='${bo.subjectId}'>${bo.subjectId}</option>");
		//$("#classId").append("<option value='${bo.classId}'>${bo.classId}</option>");
		var gradeId="${bo.classId}";
		$("#gradeId").val(gradeId.substr(0,1));

	}
</script>
