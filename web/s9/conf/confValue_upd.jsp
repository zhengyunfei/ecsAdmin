<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>编辑项目配置项信息</title>
		<!-- JS静态资源的引用 -->
		<%@include file="/s9/common/common.jsp"%>
		<script type="text/javascript" src="../s9/conf/js/confItem.js?v=1"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				var flag="${msg}";
				if(flag=='sucess'){
					window.parent.reload("保存成功");
				}else if(flag=='error'){
					$.ligerDialog.error("保存失败");
					return;
				}
			});
		</script>
	</head>
	<body class="dialogBody">
		<form action="../confManage/updConfValue.shtml" id="theForm" name="theForm" method="post">
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" >
			<tbody>
				<tr>
					<td width="30%" height="30" align="right">
						配置标识：
					</td>
					<td width="70%">
					    <input type="hidden" name="sortCode" id="sortCode" value="${valueObj.sortCode}" />
						<input type="text" id="valueId" name="valueId" value="${valueObj.valueId }" readonly="readonly" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						配置项：
					</td>
					<td>
						<input type="text" id="confKey" name="confKey" value="${valueObj.confKey }" style="width: 75%;" >
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						配置项值：
					</td>
					<td>
						<textarea id="confValue" name="confValue"  style="width: 75%;">${valueObj.confValue }</textarea>
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				
				<tr>
					<td height="30" align="right">
						描述：
					</td>
					<td>
						<textarea id="confDes" name="confDes" style="width: 75%;">${valueObj.confDes }</textarea>
					</td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td height="30" align="right">
						备注：
					</td>
					<td>
						<textarea id="remark" name="remark" style="width: 75%;">${valueObj.remark }</textarea>
					</td>
				</tr>
				<tr><td colspan="2"> &nbsp; </td></tr>
			</tbody>
		</table>
		<div class="aui_buttons">
			<button value="保存" type="button" id="z-dialog-1-OKButton" onclick="forSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		</div> 		
		</form>
	</body>

</html>