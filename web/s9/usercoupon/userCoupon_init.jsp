<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>评论管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 会员管理操作JS静态资源的引用 -->

		<script type="text/javascript"
			src="<%=basePath%>/s9/usercoupon/js/userCouponManage.js?v=2"></script>
	</head>
	<body class="z-body-detail">
		<table width="100%" id="js_layoutTable" border="0" cellspacing="0"
			cellpadding="0" height="*" class="js_layoutTable">
			<tbody>
				<tr>
					<td>
						<div class="z-toolbar" id="ToolBar1">
							<div class="z-toolbar-ct">
								<div class="z-toolbar-overflow">
									<div class="z-toolbar-nowrap">
										<%--
										<a href="javascript:void(0);" id="Button2"
											class="z-btn z-btn-flat" onclick="addCommentInit();"> <img
												class="icon021a2" src="../s9/res/img/icon000.png"><b>新建<i></i>
										</b> </a>
										<a href="javascript:void(0);" id="BtnSave"
											class="z-btn z-btn-flat" onclick="updCommentInit();"> <img
												class="icon021a4" src="../s9/res/img/icon000.png"><b>修改<i></i>
										</b> </a>
--%>
										<a href="javascript:void(0);" id="Button5"
											class="z-btn z-btn-flat" onclick="delUserCoupon();"> <img
												class="icon021a3" src="../s9/res/img/icon000.png"><b>删除<i></i>
										</b> </a>
										<!-- 
										<a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat" onclick="changePassword();">
											<img class="icon021a6" src="../s9/res/img/icon000.png"><b>修改密码<i></i></b>
										</a> 
										 -->
									</div>
								</div>
							</div>
						</div>
						<div class="gradient" style="padding: 5px 12px;">
							<div style="float: right; white-space: nowrap;">
								<!-- 所属机构：
								<div id="BranchInnerCode_wrap" class="z-combox" style="display: inline-block; * zoom: 1; * display: inline; vertical-align: middle; height: auto; width: auto; position: relative; border: none 0; margin: 0; padding: 0; white-space: nowrap;">
									<input type="text" id="BranchInnerCode" name="BranchInnerCode" tabindex="-1" class="inputText" style="position: absolute; z-index: -1;"/>
									<input type="text" id="BranchInnerCode_textField" class="inputText" style="vertical-align: middle; cursor: default;" value="" readonly="" tabindex="0"/>
									<img class="arrowimg" src="/s9/res/img/blank.gif" width="18" height="20" id="BranchInnerCode_arrow"
										style="position: relative; left: -18px; margin-right: -19px; cursor: pointer; width: 18px; height: 20px; vertical-align: middle;"/>
									<div id="BranchInnerCode_list" class="optgroup" style="text-align: left; display: none;"/>
										<div id="BranchInnerCode_ul" style="left: -1px; width: -1px;"></div>
									</div>
								</div> -->

							</div>
						</div>
					</td>
				</tr>
				<tr valign="top">
					<!-- 数据信息 -->
					<td style="padding: 5px; height: auto;">
						<div class="l-loading" style="display: block" id="pageloading"></div>
						<div class="l-clear"></div>
						<div id="maingrid"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
