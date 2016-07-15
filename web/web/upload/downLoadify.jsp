<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <style>
     #uploader{
      position:relative;
     }
     #uploader_queue{
      position:absolute;
      width:300px;
      left:150px;
      top:0;
     }
    </style>
    <link type="text/css" rel="stylesheet" href="<%=basePath%>/web/upload/uploadify.css"/>
<%--全局公共页面设置--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />	

<%-- 页面清缓存 --%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0" />
 <script type="text/javascript" src="<%=basePath%>/s9/res/js/jquery.min.js"></script>
 <script type="text/javascript" src="<%=basePath%>/web/upload/jquery.uploadify.min.js"></script>
 <script>
 </script>
  </head>
  <body>
  <form id="form" method="post">
  <input type="hidden" id="filePath" name="filePath" value='/upload/source/20141023013520_89.pdf'/>
	<a href='/c/downLoad?filePath=/upload/source/20141104030957_21.pdf&title=北京市工作居住证办理流程.pdf'>点我下载</a>
	</form>
  </body>
</html>