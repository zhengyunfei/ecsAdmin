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
		<title>新增评论</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/project/js/add.js"></script>
		<script type="text/javascript" src="<%=basePath %>/s9/project/js/number.js"></script>
	</head>

	<body class="dialogBody">
		<form id="form1" onsubmit="return false;"
			action="../serviceProject/forAddAjax.shtml" name="form1" method="post">
			
			<div style="height: 5px;"></div>
			<table width="100%" height="227" align="center" cellpadding="2"
				cellspacing="0">
				<tr>
					<td width="400">
						<div class="z-legend">
							<b>基本信息</b>
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="10%" height="30" align="right">
										服务项目名称：
									</td>
									<td width="90%" >
										<input type="text" id="name" name="name"
											style="width: 300px"  />
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
								<tr>
									<td width="10%" height="30" align="right">
									  价格：
									</td>
									<td width="90%" >
										<input type="text" id="price" name="price"
											style="width: 300px"  onkeyup="clearNoNum(this)"/>
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
								<tr>
									<td width="10%" height="30" align="right">
										原价：
									</td>
									<td width="90%" >
										<input type="text" id="orignPrice" name="orignPrice"
											style="width: 300px"  onkeyup="clearNoNum(this)"/>
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
								<tr>
									<td width="10%" height="30" align="right">
										简介：
									</td>
									<td width="90%" >
										<textArea rows="3" id="remark" name="remark"
											style="width: 300px"></textArea>
									</td>
								</tr>
								<tr>
									<td width="10%" height="30" align="right" nowrap="nowrap">
										是否是主服务项目：
									</td>
									<td width="90%" >
										<select id="isMain" name="isMain" style="width: 300px">

											<option value="0"
												<c:if test="${bo.isMain eq '0'}" > selected="selected"</c:if>>
												附加项目
											</option>
											<option value="1"
												<c:if test="${bo.isMain eq '1'}" > selected="selected"</c:if>>
												主服务项目
											</option>
										</select>
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
