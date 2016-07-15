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
<script type="text/javascript"  src="<%=basePath%>/s9/pic/js/picManage_init.js?verson=1"></script>	
</head>
<body class="z-body-list">
<form id="form" name="form">
<input type="hidden" id="backInfo" value="${backInfo}"/>
<table id="js_layoutTable" width="100%" height="*" border="0" cellspacing="0" cellpadding="0" class="js_layoutTable">
	<tbody><tr valign="top">
		<td width="350" height="100%" class="centerColumnWrap" style="height: 892px;">
		<table id="js_layoutTable" width="100%" height="*" border="0" cellspacing="0" cellpadding="0" class="js_layoutTable">
			<tbody><tr>
				<td height="37">
					<div  class="z-toolbar-anchor z-toolbar-fixed">
					<div class="z-toolbar" style="position: fixed; width: 344px; z-index: 1111; top: 0px; left: 0px;">
					<div class="z-toolbar-ct">
					<div class="z-toolbar-overflow" style="width: auto;">
					<div class="z-toolbar-nowrap" >
			  			 <a href="javascript:void(0);"  id="add" class="z-btn z-btn-flat"   ><img class="icon003a2" src="<%=basePath%>/s9/res/img/icon000.png"><b>新建 <i></i></b></a>
			  			 <a href="javascript:void(0);"  id="edite" class="z-btn z-btn-flat"   ><img class="icon003a1" src="<%=basePath%>/s9/res/img/icon000.png"><b>编辑 <i></i></b></a>
              			<a href="javascript:void(0);"  id="delete" class="z-btn z-btn-flat"  ><img class="icon003a3" src="<%=basePath%>/s9/res/img/icon000.png"><b>删除<i></i></b></a>
					</div>
					</div>
					</div>
					</div>
					</div>
           </td>
			</tr>
			<tr>
				<td style="padding: 0 1px">
				<div class="gradient" style="padding: 4px;">名称:
				  <input name="name" type="text" id="name" placeholder="播放器名称" style="width: 110px" value="${name}">
				  <input type="button" name="search" id="search" value="搜索" ></div>
				</td>
			</tr>
			<tr>
				<td height="*" style="padding: 0px 2px; height: 825px;">
				<div class="dataList-wrap">
				<div  class="z-datagrid-outer dg-scrollable dg-nobr" style="height: 99.8%;">
				<div  class="dg-dock">
				</div><div  class="dg-body" style="height: 768px;" >
				<table width="100%" cellpadding="0" cellspacing="0" class="z-datagrid"  page="true" size="30" ><thead>
						<tr ztype="head" class="dg-headTr" onselectstart="return false;">
							<th width="14%" ztype="selector" field="id" disabledresize="true">
							</th>
							<th width="13%">序号</th>
							<th width="43%">名称</th>
							<th width="43%">备注</th>
						</tr></thead><tbody>
						  <c:forEach items="${resultList}" var="item" varStatus="index">
						<c:choose>
						<c:when test="${item.id==id}">
								<tr    class="dg-row" id='${item.id}' bgcolor='#DAFAA2'>
								<td class="dg-cell-selector dg-cell">
								<input type="radio" name="ckbox" id="${item.id}" checked="checked"></td>
						</c:when>
						
						<c:otherwise>
						<c:choose>
							<c:when test="${index.count==1}">
								<tr    class="dg-row" id='${item.id}' bgcolor='#DAFAA2'>
								<td class="dg-cell-selector dg-cell">
								<input type="radio" name="ckbox" id="${item.id}" checked="checked"></td>
							</c:when>
							<c:otherwise>
								<tr    class="dg-row" id='${item.id}'>
								<td class="dg-cell-selector dg-cell">
								<input type="radio" name="ckbox" id="${item.id}"></td>
							</c:otherwise>
						</c:choose>
						</c:otherwise>
						</c:choose>
							
							<td  class="dg-cell">${index.count}</td>
							<td  class="dg-cell">${item.name}</td>
							<td  class="dg-cell">${item.remark}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table></div></div>
				</div>
				</td>
			</tr>
		</tbody></table>
		</td>
		<td style="height: 892px; zoom: 1;">
		<div id="splitter1" class="z-splitter-v shadow-v"></div>
		<iframe id="detailIFrame" frameborder="0" width="100%" height="100%" src="../picManage/forFindInit.shtml" scrolling="no" allowtransparency="true"></iframe></td>
	</tr>
</tbody></table>
</form>
</html>
