<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加代码分类信息</title>
		<!-- CSS静态资源的引用 -->
		<%@include file="/s9/common/common.jsp"%>
		<link  rel="stylesheet" type="text/css" href="../s9/res/js/calendar/skin/WdatePicker.css">
		<!-- JS静态资源的引用 -->
		<script type="text/javascript"  src="../s9/res/js/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="../s9/friendlink/js/friendLinkItem.js"></script>
		<script type="text/javascript">
		 $(document).ready(function(){
				var flag="${msg}";
				if(flag=='sucess'){
					$.ligerDialog.success("保存成功");
					var typeId = "${typeId}";
					window.parent.reload(flag);

				}else if(flag=='error'){
					$.ligerDialog.error("保存失败");
					return;
				}
			});		
		</script>
	</head>
	<body >
		<form action="../friendLinkManage/saveUpdFriendLinkInfo" id="saveUpdInfo" name="saveUpdInfo" method="post">
		<input type="hidden" id="typeId" name="typeId" value="${typeId}"/><!-- 链接分类类型ID -->
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" >
			<tbody>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td width="30%" height="30" align="right">
						链接名称：
					</td>
					<td width="70%">
						<input type="text" id="fsiteName" name="fsiteName" value="${friendLinkInfo.fsiteName}" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						链接地址：
					</td>
					<td width="70%">
						<input type="text" id="fsiteUrl" name="fsiteUrl" value="${friendLinkInfo.fsiteUrl}" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						链接图标：
					</td>
					<td>
						<input type="text" id="logoUrl" name="logoUrl" value="${friendLinkInfo.logoUrl}" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						是否有效：
					</td>
					<td height="30">
						<c:choose>
							<c:when test="${friendLinkInfo.validFlag} == 1">
								<input type="radio" id="validFlag" name="validFlag" value="1" checked="checked">
								是
								<input type="radio" id="validFlag" name="validFlag" value="0">
								否
							</c:when>
							<c:otherwise>
								<input type="radio" id="validFlag" name="validFlag" value="1">
								是
								<input type="radio" id="validFlag" name="validFlag" value="0" checked="checked">
								否
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="right">
						维护时间： 
					</td>
					<td height="30">
						<input type="text" id="addDate" name="addDate" value="${friendLinkInfo.addDate}" style="width: 75%;"  readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" class="Wdate" >
					</td>
				</tr>
				<tr>
					<td align="right">
						结束时间： 
					</td>
					<td height="30">
						<input type="text" id="effDate" name="effDate" value="${friendLinkInfo.effDate}" style="width: 75%;"  readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" class="Wdate" >
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						操作人：
					</td>
					<td>
						<input type="hidden" id="userId" name="userId" value="${friendLinkInfo.userId}">
						<input type="text" id="userName" name="userName" value="${friendLinkInfo.userName}" onclick="selectUser();" style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						备注：
					</td>
					<td>
						<textarea id="remark" name="remark"   style="width: 75%;">${friendLinkInfo.remark}</textarea>
					</td>
				</tr>
				<tr></tr>
			</tbody>
		</table>
		<div class="aui_buttons">
		<button value="保存" type="button" id="z-dialog-1-OKButton" onclick="updInfoSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		</div>
		</form>
	</body>

</html>