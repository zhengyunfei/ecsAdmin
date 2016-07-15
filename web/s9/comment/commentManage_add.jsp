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
		<script type="text/javascript"
			src="<%=basePath %>/s9/comment/js/commentManage.js?version=1"></script>
		<script type="text/javascript"
			src="<%=basePath %>/s9/res/js/util/md5.js"></script>
		<script type="text/javascript">
		 $(function (){
				var flag="${backInfo}";
				if(flag=='1'){
					window.parent.reload("评论信息添加成功");
				}else if(flag=='0'){
					$.ligerDialog.error("评论信息添加失败");
					return;
				}
			});
		</script>
	</head>

	<body class="dialogBody">
		<form id="form1" onsubmit="return checkComment()"
			action="../commentManage/addComment.shtml" name="form1" method="post">
			<input type="hidden" id="commentId" name="commentId"
				value="${comment.commentId}" />
			<input type="hidden" id="commentDate" name="commentDate"
				value="${comment.commentDate}" />
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
										会员ID：
									</td>
									<td width="75%" id="tdVipId">
										<input type="text" id="vipId" name="vipId"
											style="width: 300px" />
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
								<tr>
									<td width="25%" height="30" align="right">
										评论内容：
									</td>
									<td width="75%" id="tdContent">
										<textArea rows="3" id="content" name="content"
											style="width: 300px"></textArea>
									</td>
								</tr>
								<tr>
									<td width="25%" height="30" align="right">
										是否有效：
										</span>
									</td>
									<td width="75%" id="tdStatus">
										<select id="status" name="status" style="width: 300px">
											<option value="">
												请选择
											</option>
											<option value="0">
												无效
											</option>
											<option value="1">
												有效
											</option>
										</select>
										<span
											style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
									</td>
								</tr>
								<tr>
									<td width="25%" height="30" align="right">
										评论等级：
										</span>
									</td>
									<td width="75%" id="tdCommentLevel">
										<select id="commentLevel" name="commentLevel"
											style="width: 300px">
											<option value="">
												请选择
											</option>
											<option value="0">
												好
											</option>
											<option value="1">
												中
											</option>
											<option value="2">
												差
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
					onclick="forCommentSave();"
					class="z-dlg-button z-dialog-okbutton aui_state_highlight">
					确定
				</button>
			</div>
		</form>
	</body>
</html>
