<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="/s9/common/common.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>编辑代码项信息</title>
		<!-- JS静态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/code/js/codeItem.js"></script>
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
		<form action="<%=basePath %>/codeManage/updCodeInfo.shtml" id="theForm" name="theForm" method="post">
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" >
			<tbody>
				<tr>
					<td width="30%" height="30" align="right">
						代码分类标识：
					</td>
					<td width="70%">
					     <input type="text"  id="codeSortId" name="codeSortId" value="${codeObj.codeSortId}"  readonly="readonly"  style="width: 75%;">
					    <input type="hidden"  id="codeId" name="codeId" value="${codeObj.codeId}">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						上级代码值：
					</td>
					<td>
						<input type="text" id="pcode" name="pcode"   value="${codeObj.pcode}" readonly="readonly"  style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						代码值：
					</td>
					<td>
						<input type="text" id="codeValue" name="codeValue"   value="${codeObj.codeValue}"  style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				
				<tr>
					<td height="30" align="right">
						代码名称：
					</td>
					<td>
						<textarea id="codeName" name="codeName"  style="width: 75%;" >${codeObj.codeName }</textarea>
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						代码类型：
					</td>
					<td>
						<input type="text" id="codeType" name="codeType" value="${codeObj.codeType}"  style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						链接地址(content1)：
					</td>
					<td>
					     <select id="content1" name="content1" style="width: 75%;">
							<c:forEach var="inatitutionMap" items="${listSelect}" varStatus="count">
							<c:if test="${codeObj.content1==inatitutionMap.templateCode}">
								<option value="${inatitutionMap.templateCode}" selected="selected">${inatitutionMap.templateName}</option>
							</c:if>
							<c:if test="${codeObj.content1!=inatitutionMap.templateCode}">
								<option value="${inatitutionMap.templateCode}">${inatitutionMap.templateName}</option>
							</c:if>	
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						是否为菜单：
					</td>
					<td>
						<select id="isMenu" name="isMenu" style="width: 75%;">
						<c:choose>
							<c:when test="${codeObj.isMenu==1}">
								<option value="0" >否</option>
								<option value="1" selected="selected">是</option>
							</c:when>
							<c:otherwise>
								<option value="0" selected="selected">否</option>
								<option value="1" >是</option>
							</c:otherwise>
						</c:choose>
							
						</select>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						值(content2)：
					</td>
					<td>
						<input type="text" id="content2" name="content2"  style="width: 75%;" value="${codeObj.content2}">
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						显示顺序：
					</td>
					<td>
						<input type="text" id="dispSn" name="dispSn"  value="${codeObj.dispSn }"  style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td align="right">
						是否有效：
					</td>
					<td height="30">
						
						<input type="radio" id="validFlag" name="validFlag" value="1" <c:if test="${codeObj.validFlag ==1 }">checked="checked"</c:if> />
						是
						<input type="radio" id="validFlag" name="validFlag" value="0" <c:if test="${codeObj.validFlag ==0 }">checked="checked"</c:if> />
						否
					</td>
				</tr>
			</tbody>
		</table>
		<div class="aui_buttons">
			<button value="保存" type="button" id="z-dialog-1-OKButton" onclick="forSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		</div> 		
		</form>
	</body>

</html>