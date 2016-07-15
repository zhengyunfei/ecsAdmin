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
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ndpzwNS0VvRHGGbXAEO0KUcV"></script>
		<script type="text/javascript" src="<%=basePath %>/s9/area/js/map.js"></script>
		<style type="text/css">
			body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		</style>
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
		<form action="<%=basePath %>/area/updCodeInfo.shtml" id="theForm" name="theForm" method="post">
		<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" >
			<tbody>
				<tr>
					<td width="30%" height="30" align="right">
						代码分类标识：
					</td>
					<td width="70%">
					     <input type="text"  id="codeSortId" name="codeSortId" value="${codeObj.codeSortId}"  readonly="readonly"  style="width: 75%;">
					    <input type="hidden"  id="codeId" name="codeId" value="${codeObj.codeId}">
					    <input type="hidden"  id="codeValue" name="codeValue" value="${codeObj.codeValue}">
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
						地区名称：
					</td>
					<td>
						<textarea  id="codeName" name="codeName"  style="width: 75%;">${codeObj.codeName}</textarea>
						<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
						<input type="button" value="查询" onclick="searchByStationName();"/>
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						所在位置经度：
					</td>
					<td>
						<input type="text" id="content1" name="content1" value="${codeObj.content1}"  style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						所在位置纬度：
					</td>
					<td>
						<input type="text" id="content2" name="content2"  value="${codeObj.content2}" style="width: 75%;">
					</td>
				</tr>
				<tr>
					<td  height="30" align="right">
						覆盖范围：
					</td>
					<td>
						<input type="text" id="content3" name="content3" value="${codeObj.content3}"  style="width: 75%;">米
					</td>
				</tr>
				<tr>

					<td colspan="2" align="center">
						<font color="red">
							覆盖范围：即在该区域多少米范围内洗车有效，超出该区域之外不提供洗车服务
						</font>
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
						<input type="radio" id="validFlag" name="validFlag" value="1" checked="checked">
						是
						<input type="radio" id="validFlag" name="validFlag" value="0">
						否
					</td>
				</tr>

			</tbody>
		</table>
		<div class="aui_buttons">
			<button value="保存" type="button" id="z-dialog-1-OKButton" onclick="forSave();" class="z-dlg-button z-dialog-okbutton aui_state_highlight">保存</button>
		</div>
			<div id="allmap"></div>
		</form>
	</body>

</html>