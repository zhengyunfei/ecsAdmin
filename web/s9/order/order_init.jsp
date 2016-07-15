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
		<title>机构管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<script type="text/javascript"
			src="<%=basePath%>/s9/order/js/order.js?v=20"></script>
	</head>
	<script type="text/javascript">
	$( function() {
		var flag = "${backInfo}";
		if (flag == '1') {
			window.parent.reload("订单信息保存成功");
		} else if (flag == '0') {
			$.ligerDialog.error("订单信息保存失败");
			return;
		}
	});
</script>
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
										<!-- 										<a href="javascript:void(0);" id="Button2"	class="z-btn z-btn-flat" onclick="addNoticInit();"> -->
										<!-- 											<img class="icon021a2" src="../s9/res/img/icon000.png"><b>新建<i></i></b> -->
										<!-- 										</a> -->
										<!--
										 <a href="javascript:void(0);" id="BtnSave"	class="z-btn z-btn-flat" onclick="updOrderInit();">
											<img class="icon021a4" src="../s9/res/img/icon000.png"><b>修改<i></i></b>
										</a>
-->
										<!-- 										<a href="javascript:void(0);" id="Button3" class="z-btn z-btn-flat" onclick="updInatiutionsStatus(0);" > -->
										<!-- 											<img class="icon021a9" src="../s9/res/img/icon000.png"><b>停用<i></i></b> -->
										<!-- 										</a> -->
										<!-- 										<a href="javascript:void(0);" id="Button4" class="z-btn z-btn-flat" onclick="updInatiutionsStatus(1);"> -->
										<!-- 											<img class="icon021a8" src="../s9/res/img/icon000.png"><b>启用<i></i></b> -->
										</a>
										<!-- 										<a href="javascript:void(0);" id="Button5" class="z-btn z-btn-flat" onclick="delNotic();"> -->
										<!-- 											<img class="icon021a3" src="../s9/res/img/icon000.png"><b>删除<i></i></b> -->
										<!-- 										</a> -->
										<a href="javascript:void(0);" id="Button6"
											class="z-btn z-btn-flat" onclick="sendOrderAddInit();"> <img
												class="icon021a3" src="../s9/res/img/icon400a9.png"><b>派单<i></i>
										</b> </a>
									</div>
								</div>
							</div>
						</div>
						<div class="gradient" style="padding: 5px 12px;">
							<div style="float: right; white-space: nowrap;">
								订单编号：
								<input type="text" id="orderNo" name="orderNo"
									style="width: 120px">
								订单状态：
								<select id="status" name="status" style="width: 120px">
									<option value="">
										请选择
									</option>
									<option value="-1">
										未支付
									</option>
									<option value="1">
										支付完成
									</option>
									<option value="2">
										用户取消
									</option>
									<option value="0">
										支付失败
									</option>
								</select>
								派单状态：
								<select id="sendOrderStatus" name="sendOrderStatus"
									style="width: 120px">
									<option value="">
										请选择
									</option>
									<option value="0">
										未派单
									</option>
									<option value="1">
										已派单
									</option>
									<option value="2">
										派单完成
									</option>
								</select>
								<input type="button" name="Submitbutton" id="Submitbutton"
									value="搜索" onclick="doSearch()">
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
<script>
	function misic(){
		$('embed').remove();
		$('body').append('<embed src="../web/res/music/msg.mp3" autostart="true" hidden="true" loop="false">');
	}
	var messagePush="${messageFush}";
	if(messagePush==1){
		init();
	}
	/*第一次读取最新通知*/
	function init(){
		setTimeout(function() {
					Push();
				},
				200);
		/*30轮询读取函数*/
		setInterval(function() {
					Push();
				},
				30000);
	}


	/*请求函数的ajax*/
	function Push() {
		$.ajax({
			type: "POST",
			url: "../bsborder/findOrderByMap.shtml",
			beforeSend: function() {},
			success: function(data) {
				var obj = eval("(" + data + ")");
				if(obj>0){
					doSearch();
					misic();
				}

			}
		});
	}

</script>
