<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<script type="text/javascript">
		function forSave(){
			var url=$('#form1').attr('action');
			 	var data=$('#form1').serialize();
			 	$.ajax({
			 		url:url,
					type:'post',
					data:data,
					success:function(data){
						if(data.result){
							parent.reload("修改成功");
						}else{
							$.ligerDialog.error("修改失败");
						}
					},error:function(){
						$.ligerDialog.error("服务器也有休息的时候,你懂得");
					}
			 	})
				
			
		}
			 	
		</script>
	</head>
	
	<body class="dialogBody">
		<form id="form1" action="../car/updCar.shtml" name="form1" method="post" onsubmit="return false">
			
			<input type="hidden" id="preTime" name="preTime" value="${bo.preTime}" />
			
			<div style="height: 5px;"></div>
			<table width="100%" height="100" align="center" cellpadding="2" cellspacing="0">
			    <tr>
			      <td width="400">
			   	<div class="z-legend"><b>基本信息</b></div>
			      <table width="100%" border="0" cellpadding="0" cellspacing="0">
			      	<tbody>
						<tr>
							<td height="30" align="right"  style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
								状态：</span>
							</td>
							<td>
								<select name="status" id="status" style="width: 75%">
									<option value="1" <c:if test="${bo.status eq '1'}" > selected="selected"</c:if>>已排满</option>
									<option value="0" <c:if test="${bo.status eq '0'}" > selected="selected"</c:if>>未排满</option>
								</select>
							</td>
						</tr>
				      </tbody></table></td>
				    </tr>
					</table>
						<div class="aui_buttons">
								<button value="确定" id="saveBtn" onclick="forSave();"
									class="z-dlg-button z-dialog-okbutton aui_state_highlight">
									确定
								</button>
							</div>
		</form>
	</body>
</html>
