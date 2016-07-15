<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html >
<head>

<title>图片管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 静态资源的引用 -->
<%@include file="/s9/common/common.jsp"%>
<!-- JS静态资源的引用 -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/css/common.css" >
<script type="text/javascript"  src="<%=basePath%>/s9/pic/js/find.js?verson=1"></script>	
</head>
<body class="z-body-detail">
<input type="hidden" id="backInfo" value="${backInfo }"/>"
	<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" ztype="tab_table" class="js_layoutTable">
	<tbody>
	<input type="hidden" id="pid" value="${pid}"/>
	<tr><td height="*" ztype="tab_tbody" valign="top" >
	
<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="">
	<tbody>
<tr>
				<td height="37">
					<div  class="z-toolbar-anchor z-toolbar-fixed">
					<div class="z-toolbar" style="position: fixed; width: 100%; z-index: 1111; top: 0px; ritht: 0px;">
					<div class="z-toolbar-ct">
					<div class="z-toolbar-overflow" style="width: auto;">
					<div class="z-toolbar-nowrap" >
			  			 <a href="javascript:void(0);"  id="add" class="z-btn z-btn-flat"   ><img class="icon003a2" src="<%=basePath %>/s9/res/img/icon000.png"><b>新建 <i></i></b></a>
			  			 <a href="javascript:void(0);"  id="edite" class="z-btn z-btn-flat"   ><img class="icon003a1" src="<%=basePath %>/s9/res/img/icon000.png"><b>编辑 <i></i></b></a>
              			<a href="javascript:void(0);"  id="delete" class="z-btn z-btn-flat"  ><img class="icon003a3" src="<%=basePath %>/s9/res/img/icon000.png"><b>删除<i></i></b></a>
					</div>
					</div>
					</div>
					</div>
					</div>
           </td>
			</tr>
	<tr valign="top">
		<td style="padding: 10px;" height="*">
		<table width="100%" cellpadding="0" cellspacing="0" class="dg-headTable"><tbody>
				</tbody></table></div>
				<div id="dg1_outer_body" class="dg-body" style="" tabindex="-1">
				<table width="100%" cellpadding="0" cellspacing="0" class="z-datagrid"><thead>
				<tr ztype="head" class="dg-headTr">
					<th width="6%" ></th>
					<th width="6%" >
					<input type="checkbox" id="selectAll"></th>
					<th width="25%">图片</th>
					<th width="65%">图片名称和图片链接信息</th>
				</tr></thead><tbody>
				
				<c:forEach items="${list}" var="item" varStatus="index">
				<tr class="dg-row" id="${item.id }">
					<td  class="rowNo dg-cell">${index.count }</td>
					<td class="dg-cell-selector dg-cell" >
					<input type="checkbox" name="checkbox" id="${item.id }"></td>
					<td width="120" _id="td3" class="dg-cell"> 
							<img align="center" width="120"  src="${item.attachmentUrl }">
					</td>
					<td style="line-height:1.6;" _id="td4" class="dg-cell">
						<b>${item.name }</b><br>
						<em>链接地址:</em> ${item.url }<br>
					</td>
				</tr>
			</c:forEach>
			</tbody></table></div></td>
	</tr>
</tbody></table>
</td></tr></tbody></table>



</body>
</html>
