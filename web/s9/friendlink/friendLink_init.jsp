<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow:hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>标准代码维护</title>
<!-- 静态资源的引用 -->
<%@include file="/s9/common/common.jsp"%>
<!-- JS静态资源的引用 -->
		<script type="text/javascript" src="../s9/friendlink/js/friendLink.js?v=1"></script>
</head>
<body class="z-body-list">
<input type="hidden" name="treeNodeId" id="treeNodeId" />
<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
  	<tbody>
  		<tr valign="top">
		    <td width="25%px" height="755px" class="centerColumnWrap" style="height: 770px;">
		    	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
			        <tbody>
				        <tr>
				          <td height="33">
							<div class="z-toolbar" id="ToolBar1">
								<div class="z-toolbar-ct">
								 	<div class="z-toolbar-overflow">
									  	<div class="z-toolbar-nowrap">
											<a href="javascript:void(0);" onclick="addGroup();return false;"  id="Button1" class="z-btn z-btn-flat"><img class="icon003a2" src="../s9/res/img/icon000.png"><b>添加分组<i></i></b></a>
											<a href="javascript:void(0);" onclick="editGroup();return false;"  id="Button2" class="z-btn z-btn-flat"><img class="icon003a4" src="../s9/res/img/icon000.png"><b>修改分组<i></i></b></a>
											<a href="javascript:void(0);" onclick="delGroup();return false;"  id="Button3" class="z-btn z-btn-flat"><img class="icon003a3" src="../s9/res/img/icon000.png"><b>删除 <i></i></b></a>
									  	</div>
									</div>
								</div>
							</div>
							</td>
				        </tr>
				        <tr>
							<td style="padding: 5px; height: 182px;" height="*">
								<div id="dg1_wrap" class="z-datagrid dg_scrollable dg_nobr"  style="height: 99.1%;">
									<div id="dg1_dock" class="dg_dock"><div id="dg1_dock_trigger" class="dock_trigger"></div>
									<div id="dg1_dock_station" class="dock_station"></div>
								</div>
								<div class="l-loading" style="display:block" id="pageloading"></div> 
							  	<div class="l-clear"></div>
								<div id="maingrid"></div>
								<div id="div1" style="display: block;"></div>
							</div>
						</td>
					</tr>
		        </tbody>
		        </table>
		    </td>
		    <td width="75%" style="height:755px; zoom: 1;">
			    <div id="splitter1" class="z-splitter-v shadow-v"></div>
			    <iframe id="groupManage" frameborder="0" width="100%" height="100%" src="" scrolling="auto" allowtransparency="true" ></iframe>
		    </td>
	  	</tr>
	</tbody>
</table>
<script type="text/javascript">
	//初始化加载数据
	 $(function (){ 
	     var url = "../friendLinkManage/findFriendLinkList";
	     initGroupData(url);
    });  
</script>
</body>
</html>