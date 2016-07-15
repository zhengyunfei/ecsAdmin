<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>用户管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
			<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/js/ligerUI/skins/Aqua/css/ligerui-all.css"	/>
		    <link href="<%=basePath%>/s9/res/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		    <link href="<%=basePath%>/s9/res/js/ligerUI/skins/Gray/css/grid.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="<%=basePath %>/s9/vip/js/organManage.js?version=1"></script>
	</head>
	<body class="z-body-detail">
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
			<tbody>
				<tr>
					<td>
						<div class="z-toolbar" id="ToolBar1">
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow"> 
									<div class="z-toolbar-nowrap">
									   <%--<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat" onclick="addUserInit();">
											<img class="icon021a2" src="../s9/res/img/icon000.png"><b>新建<i></i></b>
										</a>
										<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat" onclick="updUserInit();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>编辑<i></i></b>
										</a> 
										<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat z-btn-disabled" onclick="delUser();" >
										<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat z-btn-disabled" onclick="delUser();">
											<img class="icon021a3" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
										</a>  --%>
										<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat" onclick="resetPassword();">
											<img  class="icon021a6" src="../s9/res/img/icon000.png"><b>密码重置<i></i></b>
										</a> 
									</div>
								</div>
							</div>
						</div>
						<div class="gradient" style="padding: 5px 12px;">
							<div style="float: left;white-space:nowrap">
							<label>会员状态更改</label>
							 <select id="userStatus" style="width:80px;">
							 <c:forEach var="oneMap" items="${statusMap}" >
							 		<c:if test="${oneMap.key>0}">
										<option value="${oneMap.key}" >${oneMap.value}</option>
									</c:if>
								</c:forEach>
							 </select>
							 <input type="button" name="updateStatusBtn" id="updateStatusBtn" value="批量修改" onclick="update('1')">
							 <input type="button" name="updateStatusBtn" id="sendMessageBtn" value="群发站内信" onclick="sendMessage()">
							 <input type="button" name="updateStatusBtn" id="sendCodeBtn" value="发送邀请码" onclick="sendInvitationCode()">
							</div>
							<div style="float: left;white-space:nowrap;padding-left:10px;display: none">
							<label>会员等级</label>
							 <select id="userGroup" style="width:100px;">
							 <c:forEach var="oneMap" items="${vipRankMap}" >
									<option value="${oneMap.key}" >${oneMap.value}</option>
								</c:forEach>
							 </select>
							 <input type="button" name="updateBtn" id="updateBtn" value="批量修改" onclick="update('2')">
							</div>
							<div style="float: right; white-space: nowrap;">
							 <label>会员状态查询</label>
							 <select id="vipStatus" style="width:80px;">
							 <c:forEach var="oneMap" items="${statusMap}" >
									<option value="${oneMap.key}">${oneMap.value}</option>
								</c:forEach>
							 </select>
								会员手机号：<input type="text" id="mobile" name="mobile" value="">
								会员名称：<input type="text" id="userName" name="userName" value="">
								<input type="button" name="Submitbutton" id="Submitbutton" value="搜索" onclick="doSearch()">
							</div>
						</div>
					</td>
				</tr>
				<tr valign="top">
					<!-- 数据信息 --> 
					<td style="padding: 5px; height: auto;">
						<div class="l-loading" style="display:block;" id="pageloading"></div> 
						<div class="l-clear"></div>
						<div id="maingrid"></div>
					</td> 
				</tr>
			</tbody>
		</table>
		<div id="target1" style="width:200px; margin:3px; display:none;">
			<img id="targetImg" />
		</div>
		<script type="text/javascript">
			// 初始化加载
			$(document).ready(function() { 
				//查询用户信息列表
				var userStatus='2';//默认审核通过
				var userType="organ";
				$('#vipStatus').val(userStatus);
				$('#userStatus').val(userStatus);
				var text=$('#userStatus').find('option:selected').text();
				setStatus(text);
				var url = "../vipManage/vipInfoList.shtml?userStatus="+userStatus+"&userType="+userType ;
				findVipInfoList(url);
				$('#vipStatus').bind('change',function(){
					var userStatus=$('#vipStatus').val();
					var text=$("#vipStatus").find('option:selected').text();
					setStatus(text);
					var userId=$('#userId').val();
					var userName=$('#userName').val();
					var url = "../vipManage/vipInfoList.shtml?userStatus="+userStatus +"&userType="+userType ;
					if(userId!=''){
						url+="&userId="+userId;
					}
					if(userName!=''){
						url+="&userName="+userName;
					}
					findVipInfoList(url);
				})
			}); 
			var html=$('#userStatus').html();
			function setStatus(text){
				//1待审核 2审核通过 3审核不通过 4冻结
				$('#userStatus').empty();
				$('#userStatus').append(html);
				$("#updateStatusBtn").removeAttr("disabled");  
				$("#userStatus").removeAttr("disabled");  
				$("#sendMessageBtn").removeAttr("disabled");  
				$("#sendCodeBtn").removeAttr("disabled");  
				if(text=='待审核'){
						$("#userStatus option[value='1']").remove();  
						$("#userStatus option[value='4']").remove(); 
						$('#userStatus').val('2');//让审核通过的显示在前面
					}else if(text=='审核通过'){
						$("#userStatus option[value='2']").remove();
						$("#userStatus option[value='1']").remove();
						$("#userStatus option[value='3']").remove();
					}else if(text=='审核不通过'||text=='信息未完善'){
						//$("#updateStatusBtn").attr("disabled","disabled");  
						//$("#userStatus").attr("disabled","disabled");  
						$("#userStatus option[value='4']").remove();
						$("#userStatus option[value='3']").remove();
						$("#userStatus option[value='2']").remove();
						//禁用所有可输入框
						$("#updateStatusBtn").attr("disabled",true);  
						$("#userStatus").attr("disabled",true); 
						$("#sendMessageBtn").attr("disabled",true);  
						$("#sendCodeBtn").attr("disabled",true); 
					}else if(text=='冻结'){
						$("#userStatus option[value='4']").remove();
						$("#userStatus option[value='3']").remove();
						$("#userStatus option[value='1']").remove();
					}
			}
			function flReload(){
				//关闭弹出窗口
				m.close();
			}
		</script>
	</body>
</html> 
