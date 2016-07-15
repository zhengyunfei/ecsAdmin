<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/s9/common/common.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>编辑代码分类信息</title>
		<!-- CSS静态资源的引用 -->
		<link  rel="stylesheet" type="text/css" href="<%=basePath %>/s9/res/js/calendar/skin/WdatePicker.css">
		<!-- JS静态资源的引用 -->
		<script type="text/javascript"  src="<%=basePath %>/s9/res/js/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/s9/code/js/codeSort.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				var flag="${msg}";
				if(flag=='sucess'){
					var codeSortName = "${codeSortObj.codeSortName}";
					window.parent.reload(null,codeSortName);
				}else if(flag=='error'){
					$.ligerDialog.error("保存失败");
					return;
				}
				$('#codeSortType').val('${codeSortObj.codeSortType}');
			});
		</script>
	</head>
	<body class="dialogBody">
		<form action="<%=basePath %>/codeManage/updCodeSort.shtml" id="theForm" name="theForm" method="post">
		<input type="hidden" id="submitType" value="update"/>
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" >
			<tbody>  
				<tr><td colspan="2">&nbsp;</td></tr>	
				<tr>
					<td width="30%" height="30" align="right">
						分类标识：
					</td>
					<td width="70%">
						<input id="codeSortId" name="codeSortId" value="${codeSortObj.codeSortId }" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						分类名称：
					</td>
					<td>
						<input type="text" id="codeSortName" name="codeSortName" value="${codeSortObj.codeSortName}" style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						代码类型：
					</td>
					<td>
						<input type="text" id="codeType" name="codeType"  value="${codeSortObj.codeType}"  style="width: 75%;"> 
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						代码版本：
					</td>
					<td>
						<input type="text" id="codeVn" name="codeVn" value="${codeSortObj.codeVn}" style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td align="right">
						是否有效：
					</td>
					<td height="30">
					  <input type="radio" id="validFlag" name="validFlag" value="1" <c:if test="${codeSortObj.validFlag ==1 }">checked="checked"</c:if> />
						是
			    	 <input type="radio" id="validFlag" name="validFlag" value="0" <c:if test="${codeSortObj.validFlag ==0 }">checked="checked"</c:if> />
						否	
					</td>
				</tr>
				<tr>
					<td align="right">
						生效日期:
					</td>
					<td height="30">
						<input type="text"  id="effectDate" name="effectDate" value="${codeSortObj.effectDate}" class="Wdate" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						备注：
					</td>
					<td>
					    <textarea id="remark" name="remark" style="width: 75%;"></textarea>
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