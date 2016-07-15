<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>内容管理系统</title>
		<!-- 静态资源的引用 -->
		<%@include file="/s9/common/common.jsp"%>
		<script type="text/javascript">
		 $(function (){
				var flag="${msg}";
				if(flag=='sucess'){
					$.ligerDialog.success("保存成功");
					var sortCode = "${confSortObj.sortCode}";
					var confDes = "${confSortObj.confDes}";
					window.parent.reload(sortCode,confDes);
				}else if(flag=='error'){
					$.ligerDialog.error("保存失败");
					return;
				}
			});	
		</script>
	</head>
	<body class="dialogBody">
		<form action="../confManage/addConfSort.shtml" id="theForm" name="theForm" method="post">
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr><td colspan="2">&nbsp;</td></tr>	
				<tr>
					<td width="30%" height="30" align="right">
						系统类型 ：
					</td>
					<td width="70%">
						<input id="proType" name="proType" style="width: 75%">
								<!--  <option value="">---请选择---</option>
							<c:forEach var="oneMap" items="${typeMap}">
								<option value="${oneMap.key}" >${oneMap.value}</option>
							</c:forEach>-->
						</input>
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						项目编码：
					</td>
					<td>
						<input id="proCode" name="proCode" style="width: 75%">
								<!-- <option value="">---请选择---</option>
							<c:forEach var="oneMap" items="${proCodeMap}">
								<option value="${oneMap.key}" >${oneMap.value}</option>
							</c:forEach> -->
						</input>
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						描述信息：
					</td>
					<td>
						<textarea id="confDes" name="confDes" style="width: 75%"></textarea>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						备注：
					</td>
					<td>
						<textarea id="remark" name="remark" style="width: 75%"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		 <div class="aui_buttons">
			<button value="保存" type="button" id="z-dialog-1-OKButton" onclick="forSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		 </div> 	
		</form>
	<!-- JS静态资源的引用 -->
    <script type="text/javascript" src="../s9/conf/js/confSort.js?v=1"></script>
	</body>

</html>