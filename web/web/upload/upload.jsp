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
  $(function() {
      $("#file_upload").uploadify({
       'auto' : false,
       'method' : "post",
       'formData' : {'folder' : 'file'},
          'height' : 23,
          'swf' : '<%=basePath%>/web/upload/uploadify.swf', 
          'uploader' : '<%=basePath%>/c/uploadify',
          'width' : 60,
          'buttonText' : '上传',
          'uploadLimit' : 5,
          'atuo':false,
          'multi':true,
          // 两个配套使用
           'fileTypeExts': "*.pdf;*.word;*.rar",             // 扩展名
            'fileTypeDesc': "请选择 pdf word rar文件",     // 文件说明
          //'successTimeout' : 5,
          'requeueErrors' : false,
          'removeTimeout' : 10,
          'removeCompleted' : false,
          'queueSizeLimit' :10,
          'queueID'  : 'uploader_queue',
          'progressData' : 'speed',
          'onInit' : function (){
          },
       // 单个文件上传成功时的处理函数
       'onUploadSuccess' : function(file, data, response){
    		// $("#uploader_view").append('<img height="60" alt="" src="upload/source/'+ data + '"/>');
    },
          'onQueueComplete' : function(queueData) {
    		 $('#uploader_msg').html(queueData.uploadsSuccessful + ' files were successfully uploaded.');
    }      
      });
  });
 </script>
  </head>
  <body>
 <div id="uploader">
 <nobr>

  <input type="file" name="file_upload" id="file_upload"><nobr></nobr></input>
        <div id="uploader_queue" style="float: left;padding-left:50px">
        </div>
        <div id="uploader_msg"><nobr></nobr></div>
  		<div id="uploader_view"><nobr></nobr></div>
 </nobr>
 </div>
  </body>
</html>