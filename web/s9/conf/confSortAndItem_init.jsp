<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow:hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>项目配置项管理</title>
<!-- 静态资源的引用 -->
<%@include file="/s9/common/common.jsp"%>
<script>
</script>
</head>
<body class="z-body-detail">
<table width="100%" height="*" border="0" cellspacing="0" cellpadding="0" id="js_layoutTable" class="js_layoutTable"><tbody><tr><td height="33" valign="top" style="_position:relative"><div class="z-tabpanel"><div class="z-tabpanel-ct"><div class="z-tabpanel-overflow"><div class="z-tabpanel-nowrap">
<a href="javascript:void(0);" ztype="tab" hidefocus="true" id="Info" class="z-tab z-tab-current" src="roleInfo.zhtml" targeturl="roleInfo.zhtml" onclick=";Zving.TabPage.onChildTabClick(this);checkCode()"><b>基本信息</b></a>
</div></div></div></div>
</td></tr>

<tr>
<td height="*" valign="top" style="height: 330px;">
<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="*" class="js_layoutTable">
<tbody><tr> 
	<td style="padding:5px 5px;">
		<table width="100%" cellpadding="2" cellspacing="0" class="z-datagrid">
			<tbody><tr class="dataTableHead">
		  		<td width="2%">&nbsp;</td>
		  		<td width="14%">名称</td>
		  		<td width="35%">值</td>
      			<td width="14%">名称</td>
		  		<td width="35%">值</td>
	  		</tr>
			<tr>
			 	<td class="noellipsis">&nbsp;</td>
				<td>分类标识：</td>
				<td>${confSortObj.sortCode}</td>
				<td>系统类型：</td>
				<td>${confSortObj.proType}</td>
			</tr>
			<tr>
				<td class="noellipsis">&nbsp;</td>
				<td>描述：</td>
				<td>${confSortObj.confDes}</td>
				<td>备注：</td>
				<td>${confSortObj.remark}</td>
			</tr>
	  	</tbody></table>
	  </td>
</tr>
<tr>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>
		<div class="z-toolbar z-toolbar-flat" id="ToolBar1">
		<div class="z-toolbar-ct"><div class="z-toolbar-overflow">
		<div class="z-toolbar-nowrap">
		<a href="javascript:void(0);"  onclick="add('${confSortObj.sortCode}');return false;" id="Button1" class="z-btn"><img class="icon003a2" src="../s9/res/img/icon000.png"><b>添加 <i></i></b></a>
		<a href="javascript:void(0);"  onclick="edit();return false;"  id="Button2" class="z-btn"><img class="icon003a4" src="../s9/res/img/icon000.png"><b>修改 <i></i></b></a>
		<a href="javascript:void(0);"  onclick="del();return false;"  id="Button3" class="z-btn"><img class="icon003a3" src="../s9/res/img/icon000.png"><b>删除 <i></i></b></a>
        </div></div></div></div>
    </td>
</tr>
<tr>
	<td style="padding: 5px; height: 182px;" height="*">
		<div id="dg1_wrap" class="z-datagrid dg_scrollable dg_nobr" style="height: 99.1%;">
		<div id="dg1_dock" class="dg_dock"><div id="dg1_dock_trigger" class="dock_trigger"></div>
		<div id="dg1_dock_station" class="dock_station"></div></div>
			<div class="l-clear"></div>
			<div id="maingrid"></div>
			<div id="div1" style="display: block;"></div>
		</div>
	</td>
</tr>
</tbody></table>

</td>
</tr>

</tbody></table>
<script type="text/javascript" src="../s9/conf/js/confSortAndItem_init.js"></script>
	<script type="text/javascript">
		//初始化加载数据
		 $(function (){ 
		     var url = "../confManage/findConfValues.shtml?sortCode=${confSortObj.sortCode}";
		       	initChildData(url);
		    });  
	</script>

</body></html>