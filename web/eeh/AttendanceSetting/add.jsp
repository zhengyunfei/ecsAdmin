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
	<title>新增</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<script type="text/javascript" src="<%=basePath %>/eeh/AttendanceSetting/js/add.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/AttendanceSetting/js/number.js"></script>
</head>

<body class="dialogBody">
<form id="form1" onsubmit="return false;"
	  action="../AttendanceSetting/forAddAjax.shtml" name="form1" method="post">
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
							课程分类：
						</td>
						<td width="80%" >
							<select id="status" name="status" style="width: 300px">
								<option value="0">日常课程</option>
								<option value="1">培优课程</option>
								<option value="2">其他</option>
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right" nowrap="nowrap">
							上课前xx分钟打卡有效：
						</td>
						<td width="80%" >
							<input type="text" id="before_xx_minute" name="before_xx_minute" style="width: 300px"  />
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							上课后XX分钟打卡有效：
						</td>
						<td width="80%" >
							<input type="text" id="after_xx_minute" name="after_xx_minute" style="width: 300px"  />
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							上课后XX分钟打卡为迟到：
						</td>
						<td width="80%" >
							<input type="text" id="after_xx_minute_late" name="after_xx_minute_late" style="width: 300px"  />
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							上课后XX分钟打卡为缺勤：
						</td>
						<td width="80%" >
							<input type="text" id="after_xx_minute_duty" name="after_xx_minute_duty" style="width: 300px"  />
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
				onclick="javascript:forAdd();"
				class="z-dlg-button z-dialog-okbutton aui_state_highlight">
			确定
		</button>
	</div>
</form>
</body>
</html>
