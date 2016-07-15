<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<link  rel="stylesheet" type="text/css" href="<%=basePath %>/s9/res/js/calendar/skin/WdatePicker.css">
		<script type="text/javascript" src="<%=basePath %>/s9/order/js/order.js"></script>
		<script type="text/javascript"  src="<%=basePath %>/s9/res/js/calendar/WdatePicker.js"></script>
		<script type="text/javascript">
		 $(function (){
				var flag="${backInfo}";
				if(flag=='1'){
					window.parent.reload("修改管理人信息成功");
				}else if(flag=='0'){
					$.ligerDialog.error("管理人修改失败");
					return;
				}
			});
		</script>
	</head>
	
	<body class="dialogBody">
		<form id="form1" action="/order/updOrder.shtml" nameorderm1" method="post" onsubmit="return false">
			<input type="hidden" id="orderId" name="orderId" value="${order.orderId}" />
			<input type="hidden" id="productId" name="productId" value="${order.productId}" />
			<div style="height: 5px;"></div>
			<table width="100%" height="100" align="center" cellpadding="2" cellspacing="0">
			    <tr>
			      <td width="400">
			   	<div class="z-legend"><b>基本信息</b></div>
			      <table width="100%" border="0" cellpadding="0" cellspacing="0">
			      	<tbody>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								订单号：</span>
							</td>
							<td>
								<input type="text" id="orderNum" name="orderNum" value="${order.orderNum}"  readonly="readonly"/>
							</td>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								产品内容：</span>
							</td>
							<td>
								<input type="text" id="productContent" name="productContent" value="${order.productContent}"  readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								总金额：</span>
							</td>
							<td>
								<input type="text" id="price" name="price" value="${order.price}"  readonly="readonly"/>
							</td>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								需要发票：</span>
							</td>
							<td>
								<input type="text" id="invoice" name="invoice" value="${order.invoice}"/>
							</td>
						</tr>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								发票类型：</span>
							</td>
							<td>
								<input type="text" id="invoiceType" name="invoiceType" value="${order.invoiceType}"/>
							</td>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								发票抬头：</span>
							</td>
							<td>
								<input type="text" id="invoiceName" name="invoiceName" value="${order.invoiceName}"/>
							</td>
						</tr>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								邮资：</span>
							</td>
							<td>
								<input type="text" id="postage" name="postage" value="${order.postage}"/>
							</td>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								姓名：</span>
							</td>
							<td>
								<input type="text" id="userName" name="userName" value="${order.userName}"/>
							</td>
						</tr>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								公司名称：</span>
							</td>
							<td>
								<input type="text" id="company" name="company" value="${order.company}"/>
							</td>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								地址：</span>
							</td>
							<td>
								<input type="text" id="address" name="address" value="${order.address}"/>
							</td>
						</tr>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								邮编：</span>
							</td>
							<td>
								<input type="text" id="post" name="post" value="${order.post}"/>
							</td>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								固定电话：</span>
							</td>
							<td>
								<input type="text" id="tel" name="tel" value="${order.tel}"/>
							</td>
						</tr>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								手机：</span>
							</td>
							<td>
								<input type="text" id="phone" name="phone" value="${order.phone}"/>
							</td>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								电子邮件：</span>
							</td>
							<td>
								<input type="text" id="email" name="email" value="${order.email}"/>
							</td>
						</tr>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								处理状态：</span>
							</td>
							<td>
								<input type="hidden" id="audit" name="audit" value="${order.audit}"/>
								<select name="orderStatusval" id="orderStatusval" style="width: 75%">
									<option value="1" <c:if test="${order.audit eq '1'}" > selected="selected"</c:if>>已处理</option>
									<option value="0" <c:if test="${order.audit eq '0'}" > selected="selected"</c:if>>未处理</option>
								</select>
							</td>
						</tr>
				      </tbody></table></td>
				    </tr>
					</table>
						<div class="aui_buttons">
								<button value="确定" id="z-dialog-2-OKButton" onclick="forOrderUpd();" 
									class="z-dlg-button z-dialog-okbutton aui_state_highlight">
									确定
								</button>
							</div>
		</form>
	</body>
</html>
