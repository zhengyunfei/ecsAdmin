<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 
<html   xmlns="http://www.w3.org/1999/xhtml" >
  <head >
    <title>后台管理系统</title>
    <%-- 页面清缓存BGN --%>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<style>
	#Site_wrap { top:-1.6px; top:-3px\9;}
	#Site_wrap .inputText{background: transparent none; color:#F2E8B6; border:0;}
	#Site_wrap .arrowimg {
		background:url(s9/res/img/arrow_inCell_w.gif) no-repeat 0 0;
	}
	</style>
	<%@include file="/s9/common/common.jsp"%>
	<script type="text/javascript" src="../s9/menu/js/application.js?version=3"></script>
  <style>
body {
	background-color:#DBE8F4;
	min-width:1000px;
	min-height:400px;
}
.no_text_btn .z-btn b { padding-right:0;}
#oneKeyReprintTool_div { position:absolute; left:100px; top:100px;}
.treeItem_p_tool {
    display:inline-block;
    margin-left:6px;
    visibility:hidden;
    white-space: nowrap;
}
.treeItem_p_tool a {
    display:inline;
}
.treeItem_p_hover .treeItem_p_tool {
    visibility:visible;
}
ul.tabSwitch {
    margin: 2px 0 0;
    border-bottom: 0px solid #99A7B1;
    
}

ul.tabSwitch li {
    margin: 0 0 0 1px;
}
ul.tabSwitch li a{
    background: none;
    background-position: 0 0;
    padding-left: 5px;
}
ul.tabSwitch li a b{
    background: none;
    background-position: right 0;
    min-width:0;
    min-width:auto;
    padding: 2px 5px 0 0px;
}

ul.tabSwitch li a.current {
    background-image: url("s9/res/img/tabBtnBg_cur.gif");
    height: 27px;
    padding-left: 9px;
}
ul.tabSwitch li a.current b{
    background-image: url("s9/res/img/tabBtnBg_cur.gif");
    padding: 2px 8px 2px 0;
}
#btn_treeSetting i{ display: none;}
#btn_treeSetting img{vertical-align: inherit;}

</style>
<!--[if lte IE 6]>
<style>
body{
	width:expression(document.documentElement.clientWidth < 1000? "1000px" : "auto");
	height:expression(document.documentElement.clientHeight < 400? "400px" : "auto");
}
</style>
<![endif]-->
<!--[if !IE]><!-->
<style>
#MsgCount{ font-size:9px; font-family:Tahoma, Geneva, sans-serif; display:inline-block; text-align:center; line-height:11px; padding:0 4px 0 3px; border-radius:10px; height:11px; min-width:6px; border:2px solid rgba(255,255,255,0.9); color:#fff; background-color:#F30; box-shadow:1px 1px 2px rgba(0,0,0,0.5); position:relative; left:-5px; top:-5px;}
</style>
<!--<![endif]-->
<script>
/**
 * @title : 系统主页面js
 * @author： zhengYunFei
 */
$(function(){
	userSysMenu();
	//单击菜单 切换 选中 样式
	$(".sideNavigation a").click(function(_this){
		$(".sideNavigation a").removeClass();
		$(this).addClass('current');
	});
	var flag="${flag}";
	if(flag=='true'){
		var id=7;
		var _this=$("#"+id+"_");
		change(_this);
	}
		
});
</script>
</head>
<body class="z-body-index language-zh-cn" id="application"  >
<table id="js_layoutTable" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="js_layoutTable">
  <tbody><tr>
    <td height="60"><table id="_TableHeader" width="100%" border="0" cellpadding="0" cellspacing="0" class="bluebg">
        <tbody><tr>
          <td width="217" height="60" valign="middle">
            <div style="display:none; float:left; background:url(../s9/res/img/selectsitebg.gif) no-repeat right top; color:#666666; padding:6px 23px 0 10px; margin-bottom:-2px;">
            	</div></td>
          <td><div class="loginInfo"><span class="loginInfo_welcom">当前用户:<b>${user.userName }</b> 
          		<!-- 有委托记录 -->
          		<!-- 没有委托记录 
          		| <a href="javascript:void(0);" >修改密码</a>-->
          		| <a href="javascript:void(0);" onclick="userLogout();">退出登录</a>
           	</span></div>
              	
                	<div id="_Navigation" class="navigation">
                	</div>
        </td></tr>
      </tbody></table></td>
  </tr>
	<tr>
    <td height="*" class="mainArea">
    	<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" class="js_layoutTable" style="table-layout:auto\9;*table-layout:fixed;"><!-- ie8下tableLayout为fixed的表格，就算是隐藏单元格也占有宽度，所以这个表格在ie8下tableLayout设为auto，使可以正常隐藏侧栏 -->
        <tr valign="top">
          <td width="165" height="100%" class="leftColumnWrap" id="leftColumnWrap" >
          <table width="100%" id="leftColumn" border="0" cellspacing="0" cellpadding="0" height="100%" class="">
               <tr>
                <td height="33">
                <div class="z-toolbar no_text_btn">
            	  <div class="z-toolbar-ct">
               	  	<div class="z-toolbar-overflow">
                  	<div class="z-toolbar-nowrap">
	              	 </div>
	               </div>
	              </div>
	            </div>
               </td>
              </tr>
              <tr valign="top">                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
                <td height="*">
                	<div id="_sideNavigation">
						<!-- 二级菜单信息 -->                	
                  </div>
                </td>
              </tr>
          </table></td>
          <td  id="rightColumnWrap" >
          	<div id="_framesWrap" style="position:relative; height:100%">
          		<a id="toggleLeftColumn" href="#;" class="toggleBtn-left"></a>
          		<!-- 栏目 iframe -->
          	</div>
          </td>
        </tr>
      </table></td>
  </tr>
</tbody></table>

</body>

</html>

