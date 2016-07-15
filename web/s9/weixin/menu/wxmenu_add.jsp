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
		<title>添加代码项信息</title>
		<!-- JS静态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/code/js/codeItem.js"></script>
		<script type="text/javascript">
		 $(function (){
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
		<form action="<%=basePath %>/wxMenuManage/addCodeInfo.shtml" id="theForm" name="theForm" method="post">
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr><td colspan="2">&nbsp;</td></tr>	
				<tr>
					<td width="30%" height="30" align="right">
						代码分类标识：
					</td>
					<td width="70%">
					    <input  id="codeId" name="codeId" value="${codeId}" readonly="readonly"  style="width: 75%;">
					    <input type="hidden" id="codeSortId" name="codeSortId" value="${codeSortId}"/>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						上级代码值：
					</td>
					<td>
						<input type="text" id="pcode" name="pcode"   value="${pcode}"  readonly="readonly"  style="width: 75%;">
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						菜单名称：
					</td>
					<td>
						<textarea  id="codeName" name="codeName"  style="width: 75%;"></textarea>
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						类型类型：
					</td>
					<td>
						<select  id="codeType" name="codeType"  style="width: 75%;">
						<option value="view">view</option>
						<option value="click">click</option>
						
						</select>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						链接地址(content1)：
					</td>
					<td>
					   <!--  <input type="hidden" id="content1" name="content1"/>-->
						<select id="content1" name="content1" style="width: 75%;">
							<c:forEach var="inatitutionMap" items="${listSelect}" varStatus="count">
								<option value="${inatitutionMap.templateCode}" >${inatitutionMap.templateName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<!--<tr>
					<td  height="30" align="right" >
						是否为菜单：
					</td>
					<td>
						<select id="isMenu" name="isMenu" style="width: 75%;">
							<option value="0">否</option>
							<option value="1">是</option>
						</select>
					</td>
				</tr>
				--><tr>
					<td  height="30" align="right">
						菜单URL(content2)：
					</td>
					<td>
						<input type="text" id="content2" name="content2"  style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
						显示顺序：
					</td>
					<td>
						<input type="text" id="dispSn" name="dispSn"  style="width: 75%;">
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
				
			</tbody>
		</table>
		 <div class="aui_buttons">
			<button value="保存"  type="button" id="z-dialog-1-OKButton" onclick="forSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		 </div> 		
	</form>
</body>

</html>