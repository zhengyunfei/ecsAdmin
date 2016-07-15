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
	<title>新增学生</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<script type="text/javascript" src="<%=basePath %>/eeh/student/js/add.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/student/js/number.js"></script>
</head>

<body class="dialogBody">
<form id="form1" onsubmit="return false;"
	  action="../student/forAddAjax.shtml" name="form1" method="post">
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
							班级：
						</td>
						<td width="80%" >
							<select type="text" id="classId" name="classId" style="width: 300px"  >
								<option value="">选择班级</option>
								<c:forEach items="${gradeList}" var="grade" varStatus="vs">
									<option value="${grade.name}" >${grade.name}</option>
								</c:forEach>
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>

						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							姓名：
						</td>
						<td width="80%" >
							<input type="text" id="name" name="name"
								   style="width: 300px"   value="${bo.name}"/>
										<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							性别：
						</td>
						<td width="80%" >
						<select id="sex" name="sex"  style="width: 300px" >
							<option value="男" <c:if test="${bo.sex=='男'}"></c:if>>男</option>
							<option value="女" <c:if test="${bo.sex=='女'}"></c:if>>女</option>
						</select>
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							学号：
						</td>
						<td width="80%" >
							<input type="text" id="xhnum" name="xhnum"
								   style="width: 300px"   value="${bo.xhnum}"/>
										<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							类型：
						</td>
						<td width="80%" >
							<input type="text" id="stype" name="stype"
								   style="width: 300px"   value="${bo.stype}"/>
										<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							卡号：
						</td>
						<td width="80%" >
							<input type="text" id="cardNo" name="cardNo"
								   style="width: 300px"   value="${bo.cardNo}"/>
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
				onclick="javascript:forAdd();"
				class="z-dlg-button z-dialog-okbutton aui_state_highlight">
			确定
		</button>
	</div>
</form>
</body>
</html>
