<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/js/ligerUI/skins/Aqua/css/ligerui-all.css" >
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/css/spirites.css" >
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/web/upload/uploadify.css"> 
<link  rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/js/calendar/skin/WdatePicker.css">
<script type="text/javascript"  src="<%=basePath%>/s9/res/js/calendar/WdatePicker.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/web/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=basePath%>/web/ckeditor/config.js"></script>
<script type="text/javascript" src="<%=basePath%>/s9/res/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/s9/res/js/ligerUI/js/ligerui.all.js"></script>

<script type="text/javascript" charset="utf-8" src="<%=basePath%>/web/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/web/ueditor/_examples/editor_api.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/web/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
var combox;
var dialog=null;

 $(function() {
	var config=CKEDITOR.editorConfig = function(config)
				{
				    config.extraPlugins = 'code';
				    config.filebrowserUploadUrl = '../fileUpload.shtml?type=File' ;
					config.filebrowserImageUploadUrl = '../fileUpload.shtml?type=Image' ;
				    config.filebrowserImageBrowseUrl = '../browerServer.shtml?type=image'; 
				    config.toolbar = [[ 'Image', 'Code' ]];
				};
	 CKEDITOR.replace('ckContent',config);
})
 

</script>
</head>
<body class="z-body-detail" style="background-color:white;height:1000;overflow: hidden;" >
   	<form onsubmit="return false" action="" id="form1" name="form1" method="post">
   	<div id="_DivContainer" style="overflow: auto; width: 100%;height:100%; position: absolute; z-index: 2;">
   	<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="40" class="js_layoutTable">
  <tbody><tr>
    <td align="left" id="trToolbar">
		<div class="z-toolbar" id="Toolbar" style="position:fixed;left:0px;top:0px;width:100%;z-index:999999">
		<div class="z-toolbar-ct">
		<div class="z-toolbar-overflow">
		<div class="z-toolbar-nowrap">
			<a href="javascript:save();"  id="saveBtn" class="z-btn z-btn-flat"><img class="icon003a16" src="<%=basePath%>/s9/res/img/icon000.png"><b>
			  保存
			<i></i></b></a>
			
       </div></div></div></div>
		</td></tr></tbody></table>
          <table width="100%" cellpadding="12" cellspacing="10">
           <tbody>
           <tr>
            <td height="28" align="right" style="padding-right: 5px;"><font color="#ff0000">*</font><span class="dye" nowrap="nowrap">邀请名：</span>
            </td>
            <td colspan="3" align="left"><input name="name" type="text" id="name" style="width: 380px;">
            </td>
           </tr>
           <tr>
            <td height="28" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye">简称：</span>
            </td>
            <td colspan="3" align="left"><input name="shortName" type="text" style="width: 380px;" id="shortName" size="50" ></td>
           </tr>
            <tr>
            <td height="28" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye">二级标题：</span>
            </td>
            	<td colspan="3" align="left"><input name="title" type="text" id="title" style="width: 380px;">
            </td>
           </tr>
            <tr>
           <td valign="middle" align="right"><font color="#ff0000">*</font><span class="dye">内容:</span></td>
           <td class="xheIframeArea">
        	 <div id="ckeditor" style="height:100%"></div>
        	  <textarea id="content" cols="" rows="" style="display: none"></textarea>
        	 <div style="padding-top: 10px;"></div>
           </td>
           </tr>
             <tr>
            <td height="28" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye">地点：</span>
            </td>
            	<td colspan="3" align="left"><input name="area" type="text" id="area" style="width: 380px;">
            </td>
           </tr>
            <tr>
				<td width="30%" height="40" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
					下发开始时间：</span>
				</td>
				<td> 
					<input name="sendStartTime" value="${sendTime}" type="text" id="sendStartTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" class="Wdate" />
				</td>
			</tr>
			 <tr>
				<td width="30%" height="40" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
					下发结束时间：</span>
				</td>
				<td> 
					<input name="sendEndTime" value="${sendTime}" type="text" id="sendEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" class="Wdate" />
				</td>
			</tr>
			 <tr>
				<td width="30%" height="40" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
					开始时间：</span>
				</td>
				<td> 
					<input name="sendTime" value="${sendTime}" type="text" id="sendTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" class="Wdate" />
				</td>
			</tr>
               <tr>
            <td height="28" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye">缩略图：</span>
            </td>
            	<td colspan="3" align="left">
            	 <input type="text" name="attachmentUrl"  id="attachmentUrl" style="width: 380px;" onclick="CKEDITOR.tools.callFunction(4, this); return false;"/>
            	   <input type="hidden" id="thumbnail" name="thumbnail"/>
            	   <a href="javascript:void(0);" id="Button6" class="z-btn z-btn-flat" onclick="fun();">
											<img class="icon021a2" src="../s9/res/img/icon000.png"><b>浏览<i></i></b>
										</a>
			  		<div style="display: none">
			  		 	<textarea id="ckContent" name="ckContent" rows="1" cols="1"  ></textarea>
			  		</div>
            </td>
           </tr>
       </tbody>
       </table>
       </div>
       </form>
  
  </body>
</html>
