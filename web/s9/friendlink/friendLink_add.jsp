<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加代码分类信息</title>
		<!-- CSS静态资源的引用 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- JS静态资源的引用 -->
		<script type="text/javascript" src="../s9/friendlink/js/friendLink_addupd.js"></script>
		<script type="text/javascript">
		 $(document).ready(function(){
				var flag="${msg}";
				if(flag=='sucess'){
					$.ligerDialog.success("保存成功");
					window.parent.flReload();
				}else if(flag=='error'){
					$.ligerDialog.error("保存失败");
					return;
				}
			});	
		</script>
	</head>
	<body>
		<form action="../friendLinkManage/saveAddFriendLinkGroup" id="saveAdd" name="saveAdd" method="post">
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" >
			<tbody>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td width="30%" height="30" align="right">
						分类名称：
					</td>
					<td width="70%">
						<input type="text" id="typeName" name="typeName" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						分类描述：
					</td>
					<td width="70%">
						<input type="text" id="typeDesc" name="typeDesc" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr></tr>
			</tbody>
		</table>
		<div class="aui_buttons">
			<button value="保存" type="button" id="z-dialog-1-OKButton" onclick="saveAddGroup();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		</div>
		</form>
	</body>
</html>