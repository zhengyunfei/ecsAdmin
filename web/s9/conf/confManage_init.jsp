<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow:hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>项目配置管理</title>
<!-- 静态资源的引用 -->
<%@include file="/s9/common/common.jsp"%>
<!-- JS静态资源的引用 -->
<script type="text/javascript" src="../s9/conf/js/confManage_init.js"></script>	
</head>
<body class="z-body-list">
<input type="hidden" name="treeNodeId" id="treeNodeId" />
<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
  <tbody><tr valign="top">
    <td width="220" height="100%" class="centerColumnWrap" style="height: 770px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
        <tbody><tr>
          <td height="33">
			<div class="z-toolbar" id="ToolBar1">
			<div class="z-toolbar-ct">
			 <div class="z-toolbar-overflow">
			  <div class="z-toolbar-nowrap">
				<a href="javascript:void(0);"  onclick="addConfSort();return false;"  id="Button1" class="z-btn z-btn-flat"><img class="icon003a2" src="../s9/res/img/icon000.png"><b>添加 <i></i></b></a>
				<a href="javascript:void(0);"  onclick="editConfSort();return false;"   id="Button2" class="z-btn z-btn-flat"><img class="icon003a4" src="../s9/res/img/icon000.png"><b>修改 <i></i></b></a>
				<a href="javascript:void(0);"  onclick="delConfSort();return false;"    id="Button3" class="z-btn z-btn-flat"><img class="icon003a3" src="../s9/res/img/icon000.png"><b>删除 <i></i></b></a>											
			  </div>
			</div>
			</div>
			</div>
		</td>
        </tr>
        <tr valign="top">
          <td height="*" style="height: 675px;">
			<div id="tree1_outer" class="treeContainer" style="-moz-user-select:none;height:553;width:100%;">
			 <div  id="tree1"  class="z-tree" customscrollbar="true">
			<table>
			 <tbody>
				<tr>
					<td>
					 <div id="treeone" ></div>
					</td>
					</tr>
				</tbody>
				</table>
			 </div>
			 </div>        
          </td>
        </tr>
        </tbody>
        </table>
    </td>
    <td width="75%" style="height:755px; zoom: 1;">
	    <div id="splitter1" class="z-splitter-v shadow-v"></div>
	    <iframe id="confManage" frameborder="0" width="100%" height="100%" src="" scrolling="auto" allowtransparency="true"></iframe>
    </td>
  </tr>
</tbody>
</table>

</body>
</html>