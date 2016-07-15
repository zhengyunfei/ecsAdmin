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
 <script>
  $(function (){
	 $("#upload").bind('click',function()
	 {
		$("#addform").submit();
	 });
  });

 </script>
  </head>
  <body>
  <form id="addform" method="post" enctype="multipart/form-data" action="<%=basePath%>/ImageUpload.shtml?type=Image">
<table border="0" class="perview" align="center">
      
        <tr>
            <th>选择文件</th>
            <th width="50%">预览图</th>
        </tr>
        <tr>
            <td height="200"><input id="idFile" name="upload" type="file" />
            	<br/><a href="#" id="upload">上传图片</a>
            </td>
            <td align="center">
                <img src="" id="imgFile" name="imgFile" width="136" height="61">
              
            </td>
        </tr>
</table>
</form>
  </body>
</html>