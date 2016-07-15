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
	<title>修改教学楼</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<script type="text/javascript" src="<%=basePath %>/eeh/classroom/js/update.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/classroom/js/number.js"></script>
</head>

<body class="dialogBody">
<form id="form1" onsubmit="return false;"
	  action="../classroom/forUpdateAjax.shtml" name="form1" method="post">
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
							所在教学楼：
						</td>
						<td width="80%" >
							<select type="text" id="tbId" name="tbId"   style="width: 300px"  >
							<option value="">选择教学楼</option>
							<c:forEach items="${teachbuildList}" var="tb" >
								<c:choose>
									<c:when test="${tb.id==bo.tbId}">
										<option value="${tb.id }" data-floor="${tb.floors}" selected="selected">${tb.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${tb.id }" data-floor="${tb.floors}">${tb.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>

						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							楼层数：
						</td>
						<td width="80%" >
							<select type="text" id="floors" name="floors" style="width: 300px" >
							</select>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							所属班级：
						</td>
						<td width="80%" >
							<input type="text" id="name" name="name"   style="width: 300px"  value="${bo.name}"/>
							<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							对应班级识别码：
						</td>
						<td width="80%" >
							<input type="text" id="ip" name="ip"   style="width: 300px"  value="${bo.ip}"/>
						</td>
					</tr>
					<tr>
						<td width="20%" height="30" align="right">
							房间号：
						</td>
						<td width="80%" >
							<input type="text" id="homeNum" name="homeNum"  value="${bo.homeNum}"  style="width: 300px"  />
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
