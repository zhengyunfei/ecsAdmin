<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>添加代码分类信息</title>
		<!-- CSS静态资源的引用 -->
		<%@include file="/s9/common/common.jsp"%>
		<link  rel="stylesheet" type="text/css" href="/s9/res/js/calendar/skin/WdatePicker.css">
		<!-- JS静态资源的引用 -->
		<script type="text/javascript"  src="<%=basePath %>/s9/res/js/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/s9/code/js/codeSort.js?version=3"></script>
		<script type="text/javascript">
		 $(document).ready(function(){
			 $("#effectDate").val("${effectDate}");
				var flag="${msg}";
				if(flag=='sucess'){
					$.ligerDialog.success("保存成功");
					var codeSortId = "${codeSortObj.codeSortId}";
					var codeSortName = "${codeSortObj.codeSortName}";
					window.parent.reload(codeSortId,codeSortName);

				}else if(flag=='error'){
					$.ligerDialog.error("保存失败");
					return;
				}
			});	
		</script>
	</head>
	<body >
		<form onsubmit="return check();" action="<%=basePath %>/codeManage/addCodeSort.shtml" id="theForm" name="theForm" method="post" >
		<input type="hidden" id="submitType" value="add"/>
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" >
			<tbody>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td width="30%" height="30" align="right">
						分类名称：
					</td>
					<td width="70%">
						<input type="text" id="codeSortName" name="codeSortName" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						代码类型：
					</td>
					<td>
						<input type="text" id="codeType" name="codeType" style="width: 75%;"> 
					</td>
				</tr>
				
				<tr>
					<td height="30" align="right">
						代码版本：
					</td>
					<td>
						<input type="text" id="codeVn" name="codeVn" style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td align="right">
						是否有效：
					</td>
					<td height="30">
						<input type="radio" id="validFlag" name="validFlag" value="1" checked="checked">
						是
						<input type="radio" id="validFlag" name="validFlag" value="0">
						否
					</td>
				</tr>
				<tr>
					<td align="right">
						生效日期： 
					</td>
					<td height="30">
						<input type="text" value="" id="effectDate" name="effectDate" value="${effectDate}"  style="width: 75%;"  readonly="readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" class="Wdate" >
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						备注：
					</td>
					<td>
						<textarea id="remark" name="remark"   style="width: 75%;"></textarea>
					</td>
				</tr>
				<tr></tr>
			</tbody>
		</table>
		<div class="aui_buttons">
		<button value="保存" type="button" id="z-dialog-1-OKButton" onclick="forSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		</div>
		</form>
	</body>

</html>