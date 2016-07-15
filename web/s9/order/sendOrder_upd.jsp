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
		<title>修改派单</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 评论管理操作JS静态资源的引用 -->
		<script type="text/javascript"
			src="<%=basePath %>/s9/order/js/sendOrder.js?version=1"></script>
			<script type="text/javascript" src="<%=basePath%>/web/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/s9/res/js/config.js"></script>
		<script type="text/javascript"
			src="<%=basePath %>/s9/res/js/util/md5.js"></script>
		<script type="text/javascript">
		 $(function() {
		 	  var config=CKEDITOR.editorConfig = function(config)
				{
				    config.extraPlugins += (config.extraPlugins?',code':'code');
				    config.extraPlugins += (config.extraPlugins?',idcard':'idcard');
				    config.filebrowserUploadUrl = '../fileUpload.shtml?type=File' ;
					config.filebrowserImageUploadUrl = '../fileUpload.shtml?type=Image' ;
				    config.filebrowserImageBrowseUrl = '../browerServer.shtml?type=image'; 
				    config.toolbar = [[ 'Image', 'Code','Idcard' ]];
				};
	 			CKEDITOR.replace('ckContent',config);
	 			
	 			
		 })
		
		 $(function (){
				var flag="${backInfo}";
				if(flag=='1'){
					window.parent.reload("任务信息修改成功");
				}else if(flag=='0'){
					$.ligerDialog.error("任务信息修改失败");
					return;
				}
			});
		</script>
	</head>

	<body class="dialogBody">
		<form id="form1" onsubmit="return checkComment()"
			action="../sendOrder/updSendOrder.shtml" name="form1" method="post">
<input type="hidden" id="id" name="id"
				value="${sendOrder.id}" />
			<input type="hidden" id="operatorId" name="operatorId"
				value="${sendOrder.operatorId}" />
			<input type="hidden" id="operatorDate" name="operatorDate"
				value="${sendOrder.operatorDate}" />
			<input type="hidden" id="userId" name="userId" style="width: 302px"
				value="${sendOrder.userId}" />
			<td width="75%" id="tdUserId">
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
												style="width: 300px" value="${sendOrder.orderId}"
												readonly="readonly" />
											<!--<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									-->
										</td>
									</tr>
									<tr>
										<td width="25%" height="30" align="right">
											分配给：
										</td>
										<td width="75%" id="tdUserId">
											${userRealName}

										</td>
									</tr>
									<tr>
										<td width="25%" height="30" align="right">
											工作内容：
										</td>
										<td width="75%" id="tdContent">
											<textArea rows="3" id="content" name="content"
												style="width: 300px">${sendOrder.content}</textArea>
										</td>
									</tr>
									<tr>
										<td width="25%" height="30" align="right">
											洗车前照片：
										</td>
										<td width="75%" id="tdOrderId">
											     <img src="${sendOrder.beforePhoto}" id="imgFile" width="136" height="61" onclick="CKEDITOR.tools.callFunction(4, this,4); return false;">
												 <input type="hidden" id="beforePhoto" name="beforePhoto" value="${sendOrder.beforePhoto}"/>
												<div style="display: none">
										  		 	<textarea id="ckContent" name="ckContent" rows="1" cols="1"  ></textarea>
										  		</div>
										</td>
									</tr>
									  
									<tr>
										<td width="25%" height="30" align="right">
											洗车后照片：
										</td>
										<td width="75%" id="tdOrderId">
									      <img src="${sendOrder.afterPhoto}" id="attachmentUrl" name="attachmentUrl" width="136" height="61" onclick="CKEDITOR.tools.callFunction(5, this,5); return false;">
											<input type="hidden" id="afterPhoto" name="afterPhoto" value="${sendOrder.afterPhoto}" />
										</td>
									</tr>
									<tr>
										<td width="25%" height="30" align="right">
											状态：
											</span>
										</td>
										<td width="75%" id="tdStatus">
											<select id="status" name="status" style="width: 303px">
												<option value="0"
													<c:if test="${sendOrder.status eq '0'}" > selected="selected"</c:if>>
													未派单
												</option>
												<option value="1"
													<c:if test="${sendOrder.status eq '1'}" > selected="selected"</c:if>>
													已派单
												</option>
												<option value="2"
													<c:if test="${sendOrder.status eq '2'}" > selected="selected"</c:if>>
														派单完成
												</option>
												<option value="3"
													<c:if test="${sendOrder.status eq '3'}" > selected="selected"</c:if>>
													洗车开始
												</option>
												<option value="4"
													<c:if test="${sendOrder.status eq '4'}" > selected="selected"</c:if>>
													洗车结束
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
						onclick="forSendOrderUpd();"
						class="z-dlg-button z-dialog-okbutton aui_state_highlight">
						确定
					</button>
				</div>
		</form>
	</body>
</html>
