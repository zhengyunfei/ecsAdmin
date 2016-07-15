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
	<script type="text/javascript" src="<%=basePath %>/s9/weixin/js/weixin.js?version=10"></script>
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
							<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat" onclick="addUserInit();">
								<img class="icon021a2" src="../s9/res/img/icon000.png"><b>初始化数据<i></i></b>
							</a>
							<%--<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat" onclick="updUserInit();">
                                <img class="icon021a4" src="../s9/res/img/icon000.png"><b>编辑<i></i></b>
                            </a>
                            <a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat z-btn-disabled" onclick="delUser();" >
                            <a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat z-btn-disabled" onclick="delUser();">
                                <img class="icon021a3" src="../s9/res/img/icon000.png"><b>删除<i></i></b>
                            </a>  --%>
							<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat" onclick="exportToTxt();">
								<img  class="icon021a6" src="../s9/res/img/icon000.png"><b>导出txt<i></i></b>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="gradient" style="padding: 5px 12px;">
				<div style="float: left;white-space:nowrap">
					<!--<label>会员状态更改</label>
							 <select id="userStatus" style="width:80px;">
							<%-- <c:forEach var="oneMap" items="${statusMap}" >
							 		<c:if test="${oneMap.key>0}">
										<option value="${oneMap.key}" >${oneMap.value}</option>
									</c:if>
								</c:forEach>--%>
							 </select>
							 <input type="button" name="updateStatusBtn" id="updateStatusBtn" value="批量修改" onclick="update('1')">
							 <input type="button" name="updateStatusBtn" id="sendMessageBtn" value="群发站内信" onclick="sendMessage()">
							 <input type="button" name="updateStatusBtn" id="sendCodeBtn" value="发送邀请码" onclick="sendInvitationCode()">
							</div>
							<div style="float: left;white-space:nowrap;padding-left:10px;display: none">
							<label>会员等级</label>
							 <select id="userGroup" style="width:100px;">

							 </select>
							 <input type="button" name="updateBtn" id="updateBtn" value="批量修改" onclick="update('2')">
							</div>
							-->
					<div style="float: right; white-space: nowrap;">
					<form action="" id="form1" name="form1" method="get">
					<label>公众号APPID</label>
					<input type="text" id="appid" name="appid" style="width:120px;">
					<label>公众号秘钥</label>
					<input type="text" id="secret" name="secret" style="width:180px;">
					用户昵称：<input type="text" id="nickname" name="nickname" value="${nicename}">
					openid：<input type="text" id="openId" name="openId" value="${openId}">
					国家：<input type="text" id="country" name="country" value="">
					省份：<input type="text" id="province" name="province" value="">
					城市：<input type="text" id="city" name="city" value="">
					性别：
					<select id="sex" name="sex">
							<option value="">请选择</option>
							<option value="男">男</option>
							<option value="女">女</option>
					</select>
					<input type="button" name="Submitbutton" id="Submitbutton" value="搜索" onclick="doSearch()">
					</form>
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

		doSearch();

	})
	function doSearch(){
		var nickname=$("#nickname").val();
		var secret=$("#secret").val();
		var appid=$("#appid").val();
		var openId=$("#openId").val();
		var data=$("#form1").serialize();
		//?nickname="+nickname+"&appid="+appid+"&secret="+secret+"&openId="+openId
		var url = "../wx/findAllWeiXinUser.shtml?"+data;
		findVipInfoList(url);
	}
	function exportToTxt(){
	/*	var secret=$("#secret").val();
		var appid=$("#appid").val();
		if(appid==''||null==appid){
			alert("请输入appid");
			return ;
		}else if(null==secret||''==secret){
			alert("请输入秘钥");
			return ;
		}else{
			var url="../wxMenuManage/exportToTxt.shtml?appid="+appid+"&secret="+secret;
			window.location.href=url;

		}*/
		var data=$("#form1").serialize();
		var url="../wx/exportToTxt.shtml?"+data;
		window.location.href=url;

	}
</script>
</body>
</html>
