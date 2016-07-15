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
		<title>派单</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 派单操作JS静态资源的引用 -->
		<script type="text/javascript"
			src="<%=basePath %>/s9/order/js/sendOrder.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>/s9/res/js/util/md5.js"></script>
		<script type="text/javascript">
		 $(function (){
				var flag="${backInfo}";
				if(flag=='1'){
					window.parent.reload("派单成功");
				}else if(flag=='0'){
					$.ligerDialog.error("派单失败");
					return;
				}
			});
		</script>
	</head>

	<body class="dialogBody">
		<form id="form1" onsubmit="return false"
			action="../sendOrder/sendOrderAdd.shtml" name="form1" method="post">
			<input type="hidden" id="id" name="id" value="${sendOrder.id}" />
			<input type="hidden" id="operatorId" name="operatorId"
				value="${sendOrder.operatorId}" />
			<input type="hidden" id="operatorDate" name="operatorDate"
				value="${sendOrder.operatorDate}" />
			<input type="hidden" id="status" name="status"
				value="${sendOrder.status}" />

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
									<td width="25%" height="30" align="right">
										订单ID：
									</td>
									<td width="75%" id="tdOrderId">
										<input type="text" id="orderId" name="orderId"
											style="width: 300px" value="${orderId}" readyonly="true" />
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
								<tr>
									<td width="25%" height="30" align="right">
										分配给：
									</td>
									<td width="75%" id="tdUserId">
										<select id="userId" name="userId" style="width: 301px">
											<option value="">
												请选择
											</option>
											<c:forEach items="${userList}" var="user" varStatus="status">
												<option value="${user.userId }">
													${user.userName }
												</option>
											</c:forEach>
										</select>
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
								<%--<tr>
									<td width="25%" height="30" align="right">
										工作内容：
									</td>
									<td width="75%" id="tdContent">
										<textArea rows="3" id="content" name="content"
											style="width: 301px"></textArea>
									</td>
								</tr>--%>
	<%--<tr>
									<td width="25%" height="30" align="right">
										洗车前照片：
									</td>
									<td width="75%" id="tdOrderId">
										<input type="text" id="beforePhoto" name="beforePhoto"
											style="width: 300px"/>
									</td>
								</tr>--%>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<div class="aui_buttons">
				<button value="确定" id="z-dialog-2-OKButton"
					onclick="forSendOrderSave();"
					class="z-dlg-button z-dialog-okbutton aui_state_highlight">
					确定
				</button>
			</div>
		</form>
	</body>
</html>
