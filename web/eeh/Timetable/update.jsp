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
	<title>修改课程表</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<script type="text/javascript" src="<%=basePath %>/eeh/Timetable/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/Timetable/js/update.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/Timetable/js/number.js"></script>
</head>

<body class="dialogBody">
<form id="form1" onsubmit="return false;"
	  action="../Timetable/forUpdateAjax.shtml" name="form1" method="post">
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
							星期：
						</td>
						<td width="80%" >
							<input type="text" id="week" name="week" style="width: 300px"  value="${bo.week}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							班级：
						</td>
						<td width="80%" >
							<input type="text" id="gradeName" name="gradeName" style="width: 300px"  value="${bo.gradeName}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>

					<tr>
						<td width="20%" height="30" align="right">
							第一节课：
						</td>
						<td width="80%" >
							<input type="text" id="firstClass" name="firstClass" style="width: 300px"  value="${bo.firstClass}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							第二节课：
						</td>
						<td width="80%" >
							<input type="text" id="secondClass" name="secondClass" style="width: 300px"  value="${bo.secondClass}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							第三节课：
						</td>
						<td width="80%" >
							<input type="text" id="threeClass" name="threeClass" style="width: 300px"  value="${bo.threeClass}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							第四节课：
						</td>
						<td width="80%" >
							<input type="text" id="fourClass" name="fourClass" style="width: 300px"  value="${bo.fourClass}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							第五节课：
						</td>
						<td width="80%" >
							<input type="text" id="fiveClass" name="fiveClass" style="width: 300px"  value="${bo.fiveClass}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							第六节课：
						</td>
						<td width="80%" >
							<input type="text" id="sixClass" name="sixClass" style="width: 300px"  value="${bo.sixClass}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							第七节课：
						</td>
						<td width="80%" >
							<input type="text" id="sevenClass" name="sevenClass" style="width: 300px"  value="${bo.sevenClass}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							第八节课：
						</td>
						<td width="80%" >
							<input type="text" id="eightClass" name="eightClass" style="width: 300px"  value="${bo.eightClass}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							第九节课：
						</td>
						<td width="80%" >
							<input type="text" id="nineClass" name="nineClass" style="width: 300px"  value="${bo.nineClass}"/>
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

</script>
