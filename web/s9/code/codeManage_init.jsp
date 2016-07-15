<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html >
<head>

<title>标准代码维护</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%-- 页面清缓存 --%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0" />

<!-- 静态资源的引用 -->
<%@include file="/s9/common/common.jsp"%>
<!-- JS静态资源的引用 -->
<script type="text/javascript"  src="<%=basePath %>/s9/code/js/codeManage_init.js?verson=2"></script>	
</head>
<body class="z-body-list">
<input type="hidden" name="treeNodeId" id="treeNodeId" />
<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="100%" class="js_layoutTable">
  <tbody><tr valign="top" >
    <td width="25%" height="100%" class="centerColumnWrap" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
        <tbody><tr>
          <td height="33">
			<div class="z-toolbar" id="ToolBar1">
			<div class="z-toolbar-ct">
			 <div class="z-toolbar-overflow">
			  <div class="z-toolbar-nowrap">
				<a href="javascript:void(0);" onclick="addCodeSort();return false;"  id="Button1" class="z-btn z-btn-flat"><img class="icon003a2" src="../s9/res/img/icon000.png"><b>添加 <i></i></b></a>
				<a href="javascript:void(0);" onclick="editCodeSort();return false;"  id="Button2" class="z-btn z-btn-flat"><img class="icon003a4" src="../s9/res/img/icon000.png"><b>修改 <i></i></b></a>
				<a href="javascript:void(0);" onclick="delCodeSort();return false;"  id="Button3" class="z-btn z-btn-flat"><img class="icon003a3" src="../s9/res/img/icon000.png"><b>删除 <i></i></b></a>											
			  </div>
			</div>
			</div>
			</div>
		</td>
        </tr>
        <tr>
			<td height="*" style="padding-top: 0px; padding-right: 2px; padding-bottom: 0px; padding-left: 2px; zoom: 1;">
			<!-- style="" -->
				<div class="dataList-wrap" style="padding-right: 2px; padding-left: 2px;width:24%;height:95%;position: absolute;"  >
				 <ul id="treeone" ></ul>
				</div>
			</td>
		</tr>
        </tbody>
        </table>
    </td>
    <td width="75%" height="*" style="height:99.5%; zoom: 1;">
	    <div id="splitter1" class="z-splitter-v shadow-v"></div>
	    <iframe id="codeManage" frameborder="0" width="100%" height="100%" src="" scrolling="auto" allowtransparency="true"></iframe>
    </td>
  </tr>
</tbody>
</table>
		
</body></html>
