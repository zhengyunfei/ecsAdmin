<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统菜单信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 
	<%@include file="/s9/common/common.jsp"%>
  </head>
  
  <body class="dialogBody">
	<form id="form2" action="../menu/saveSysMenu.shtml" method="post">
	<input type="hidden" name="menuId" id="menuId" value="${sysMenu.menuId} ">
	<div class="aui_buttons">
		<button value="保存" id="z-dialog-1-OKButton" onclick="forSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
	</div>  
	<table width="95%" height="197" align="center" style="padding: 4px" border="1" bordercolor="#eeeeee">
				<tbody><tr>
					<td align="left" bgcolor="#F7F7F7">&nbsp;&nbsp;<strong> 菜单名称</strong></td>
					<td width="75%"><input type="text" name="menuName" id="menuName" value="${sysMenu.menuName }" /></td>
				</tr>
				<tr>
					<td align="left" bgcolor="#F7F7F7">&nbsp;&nbsp;<strong>父菜单</strong></td>
					<td><input type="text" name="pmenuCode" id="pmenuCode" value="${sysMenu.pmenuCode }" disabled="disabled"/></td>
				</tr>
				<tr>
					<td align="left" bgcolor="#F7F7F7">&nbsp;&nbsp;<strong>菜单编码</strong></td>
					<td><input type="text" name="menuCode" id="menuCode" value="${sysMenu.menuCode }" /></td>
				</tr>
				<tr>
					<td align="left" bgcolor="#F7F7F7">&nbsp;&nbsp;<strong> 图标</strong></td>
					<td>
						<span id="imgClass"></span>
						<input type="hidden" id="menuImg" name="menuImg" value="${sysMenu.menuImg }">
						<c:if test="${sysMenu.menuImg !=''}">
							<img class='${sysMenu.menuImg }' src='../s9/res/img/icon000.png' />
						</c:if>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img class='icon403a21' src='../s9/res/img/icon000.png' onclick='onclickImg();'/>
					</td>
				</tr>
				<tr>
					<td align="left" bgcolor="#F7F7F7">&nbsp;&nbsp;&nbsp;<strong>菜单路径</strong></td>
					<td><input type="text" name="pathCode" id="pathCode" value="${sysMenu.pathCode }" /></td>
				</tr>
				<tr>
					<td align="left" bgcolor="#F7F7F7">&nbsp;&nbsp;&nbsp;<strong>菜单顺序</strong></td>
					<td><input type="text" name="sortNo" id="sortNo" value="${sysMenu.sortNo }" /></td>
				</tr>
				<tr>
					<td align="left" bgcolor="#F7F7F7">&nbsp;&nbsp;<strong> 备注</strong></td>
					<td><input type="text" name="remark" id="remark" value="${sysMenu.remark }"/></td>
				</tr>
			</tbody></table>
	</form>
</body>
</html>
<script>
	$(function(){
		var message = '${message}';
		if(message !="" && message!=null){
			parent.updateSysMenuOK(message);
		}
	});
	
/**
 * @title : 保存修改信息
 * */
function forSave(){
	var menuName = $("#menuName").val();
	if(menuName == ""){
		$.ligerDialog.warn('菜单名称不可为空!');
		return false;
	}
	$("#form2").submit();
}
/**
 * @title : 系统菜单图片
 */
function onclickImg(){
	parent.sysMenuImg();
}
function imgClass(img){
	$("#menuImg").val(img);
	$("#imgClass").html("");
	$("#imgClass").html("<img class='"+img+"' src='../s9/res/img/icon000.png'>");
}
</script>
