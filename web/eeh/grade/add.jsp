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
	<title>新增班级</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<script type="text/javascript" src="<%=basePath %>/eeh/grade/js/add.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/grade/js/number.js"></script>

</head>

<body class="dialogBody" style="overflow-x: hidden">
<form id="form1" onsubmit="return false;" action="../grade/forAddAjax.shtml" name="form1" method="post">

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
							班级名称：
						</td>
						<td width="80%" >
							<input type="text" id="name" name="name" style="width: 300px"  />班
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							选择年级：
						</td>
						<td width="80%" >
						<select id="gradeName" name="gradeName" style="width:300px">
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
						<input type="hidden" id="gradeId" name="gradeId"/>
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							班主任：
						</td>
						<td width="80%" >
							<input type="text" id="teacherName" name="teacherName" style="width: 300px"  />
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							班级人数：
						</td>
						<td width="80%" >
							<input type="text" id="gradenum" name="gradenum" style="width: 300px"  onkeyup="clearNoNum(this)" value="${bo.gradenum}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							班干部：
						</td>
						<td width="80%" >
							<textarea id="classCadre" name="classCadre" rows="" cols="" style="width: 300px;"></textarea>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					</tbody>
				</table>
				<%--<div class="z-legend" style="width:100%">
					<b>学生管理</b>
				</div>
				<div class="aui_buttons">
					<button value="下载标准表格" onclick="javascript:download();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">
						下载标准表格
					</button>
					<button value="导入" id="importExcel" class="z-dlg-button z-dialog-okbutton aui_state_highlight">
						导入
					</button>
				</div>
				<div id="studentGrid" style="padding-left: 25px;"></div>--%>
				<div class="aui_buttons">
					<button value="保存"  onclick="javascript:forAdd();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">
						保存
					</button>
				</div>
			</td>
		</tr>
	</table>

</form>
</body>
</html>
<script type="text/javascript">
	// 初始化加载
	$(function(){
		findAllList();
	})
	/**
	 * 下载标准表格
	 * **/
	function download(){
		window.location.href="<%=basePath %>/eeh/excel/学生信息标准表.xlsx";
	}
</script>
