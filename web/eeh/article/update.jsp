<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	  <title>更新信息</title>
	  <!-- 引用本页面JS、CSS样式静态资源 -->
	  <%@include file="/s9/common/common.jsp"%>
<link  rel="stylesheet" type="text/css" href="<%=basePath%>/s9/res/js/calendar/skin/WdatePicker.css">
<script type="text/javascript"  src="<%=basePath%>/s9/res/js/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/web/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=basePath%>/web/ckeditor/config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/web/ueditor/ueditor.config.js?v=1"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/web/ueditor/_examples/editor_api.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="<%=basePath%>/web/ueditor/lang/zh-cn/zh-cn.js"></script>
<script>
var dialog=null;
//点击缩略图
function fun(obj){
	var url="../imageServer.shtml?type=image&imageType="+obj;
	dialog=$.ligerDialog.open({
		height: 600,
		width:960,
		url: url,
		title:'图片服务器'
	});
}
 //关闭弹出窗口，并为tex框赋值
	function selectPic(str,imageType){
 		if(imageType=='mobile'){
 			$("#mobileThumbnail").val(str);
 		}else{
			$("#attachmentUrl").val(str);
 		}
		dialog.close();
	}
 function clickPic(obj){
	 $("#thumbnail").val(obj);
		dialog.close();
 }
 function setImageURL(obj){
	 $("#attachmentUrl").val("<%=basePath%>"+obj);
	 $("#thumbnail").val("<%=basePath%>"+obj);
 }
 $(function() {
	     var config=CKEDITOR.editorConfig = function(config)
		 {
			config.extraPlugins += (config.extraPlugins?',code':'code');
			config.filebrowserUploadUrl = '../fileUpload.shtml?type=File' ;
			config.filebrowserImageUploadUrl = '../fileUpload.shtml?type=Image' ;
			config.filebrowserImageBrowseUrl = '../browerServer.shtml?type=image';
			config.toolbar = [[ 'Image', 'Code']];
		 };
		 CKEDITOR.replace('ckContent',config);
		 var html="";
		 var config = {};
		 var content='${bo.content}';
		 var ue = UE.getEditor('ckeditor',{
	 	 initialFrameWidth:700,
	 	 autoHeightEnabled:false,
	     enterTag:'',initialContent:content
	 });
		//保存事件
		$("#saveBtn").bind('click',function(){
			var type=$("#type").val();
			if(type==''){
				$.ligerDialog.error("信息类型不能为空！");
				return;
			}
			var title=$("#title").val();
			if(title==''){
				$.ligerDialog.error("信息标题不能为空！");
				return;
			}
		    var content=UE.getEditor('ckeditor').getContent();
		    $("#content").val(content);
		    if(content==''){
		    	$.ligerDialog.error("信息内容不能为空！");
				return;
		    }
		    $("#thumbnail").val($("#attachmentUrl").val());
		    $("#form1").attr("onsubmit","");
		    var url="../Article/forUpdateAjax.shtml";
		    var data=$("#form1").serialize();
		    $.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:data,
			async:false,
			beforeSend:function(XMLHttpRequest){
			},
			success:function(msg){
				$.ligerDialog.success("保存成功",function(){
					parent.reload();
				})

			},error:function(){
				$.ligerDialog.error("服务器异常");
				return;
			}});

		});

 });
 function del(obj){
	 $(obj).parents('tr:first').remove();
 }

</script>
 </head>
<body class="z-body-detail" style="background-color:white;overflow: hidden;">
   	<form onsubmit="return check();" action="" id="form1" name="form1" method="post" enctype="multipart/form-data">
		<input type="hidden" id="rootPath" name="rootPath" value="${param.rootPath}"/>
		<input type="hidden" id="articleId" name="articleId" value="${bo.articleId}"/>
   	<div id="_DivContainer" style="overflow: auto; width: 100%;height:100%; position: absolute; z-index: 2;">
   	<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="40" >
  <tr>
    <td align="left" id="trToolbar">
		<div class="z-toolbar" id="Toolbar" style="position:fixed;left:0px;top:0px;width:100%;z-index:999999">
		<div class="z-toolbar-ct">
		<div class="z-toolbar-overflow">
		<div class="z-toolbar-nowrap">
			<a href="javascript:void(0);"  id="saveBtn" class="z-btn z-btn-flat"><img class="icon003a16" src="<%=basePath%>/s9/res/img/icon000.png"><b>
			  保存
			<i></i></b></a>
       </div></div></div></div>
</td></tr>
</table>
          <table width="100%" >
           <tbody>
				<tr>
            	<td height="28"  align="right" style="padding-right: 5px;" nowrap="nowrap">
					<font color="#ff0000">*</font><span class="dye" >信息类型：</span>
	            </td>
	            <td colspan="3" align="left">
	            	<select id="type" name="type" style="width: 300px;" onchange="selectType()">
						<option value="班级风采" <c:if test="${bo.type=='班级风采'}">selected="selected"</c:if>>班级风采</option>
						<option value="通知" <c:if test="${bo.type=='通知'}">selected="selected"</c:if>>通知</option>
						<option value="公告" <c:if test="${bo.type=='公告'}">selected="selected"</c:if>>公告</option>
						<option value="班级明星" <c:if test="${bo.type=='班级明星'}">selected="selected"</c:if>>班级明星</option>
	            	</select>
	            </td>
           </tr>
				<c:choose>
				<c:when test="${bo.type=='班级风采'||bo.type=='班级明星'}">
				<tr id="selectGradeTr">
					</c:when>
					<c:otherwise>
				<tr id="selectGradeTr" style="display: none;">
					</c:otherwise>
					</c:choose>
				<td height="28"  align="right" style="padding-right: 5px;" nowrap="nowrap">
					<font color="#ff0000">*</font><span class="dye" >选择班级：</span>
				</td>
				<td colspan="3" align="left">
					<select id="gradeName" name="gradeName" style="width: 300px;">
						<c:forEach items="${gradeList}" varStatus="vs" var="grade">
							<option value="${grade.name}" <c:if test="${bo.gradeName==grade.name}">selected="selected" </c:if>>${grade.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
           <tr>
	            <td height="28" align="right" style="padding-right: 5px;">
					<font color="#ff0000">*</font><span class="dye" nowrap="nowrap">信息标题：</span>
	            </td>
	            <td colspan="3" align="left">
					<input name="title" type="text" id="title" style="width: 300px;" value="${bo.title}">
	            </td>
           </tr>
			<tr>
				<td  height="40" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
					时间：</span>
				</td>
				<td>
					<input name="time" value="${bo.time}" type="text" id="time" style="width:300px" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" class="Wdate" />
				</td>
			</tr>
			<c:choose>
				<c:when test="${bo.type=='班级风采'||bo.type=='班级明星'}">
						<tr id="thumbnailTr" >
				</c:when>
				<c:otherwise>
						<tr id="thumbnailTr" style="display: none">
				</c:otherwise>
			</c:choose>

	            <td height="28" align="right" style="padding-right: 5px;" nowrap="nowrap" >
					<span class="dye">缩略图：</span>
	            </td>
	            <td colspan="3" align="left">
	            	   <input type="text" name="attachmentUrl" value="${bo.thumbnail}"  id="attachmentUrl" style="width: 350px;" onclick="CKEDITOR.tools.callFunction(4, this,4); return false;"/>
	            	   <input type="hidden" id="thumbnail" name="thumbnail" value="${bo.thumbnail}"/>
	            	   <a href="javascript:void(0);"  class="z-btn z-btn-flat" onclick="fun('');">
							<img class="icon021a2" src="../s9/res/img/icon000.png"><b>浏览<i></i></b>
				       </a>
				  		<div style="display: none">
				  		 	<textarea id="ckContent" name="ckContent" rows="1" cols="1"  ></textarea>
				  		</div>
	            </td>
           </tr>
           <tr>
           <td valign="middle" align="right">
			   <font color="#ff0000">*</font><span class="dye">内容详情:</span></td>
           <td class="xheIframeArea">
           	 <input type="hidden" name="content" id="content"/>
        	 <textarea id="ckeditor" name="contents" rows="50" cols="80"  style="width:627px">
        	 </textarea>
           </td>
           </tr>
			<tr style="display: none" id="mottoTr">
				<td  height="40" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
				座右铭：</span>
				</td>
				<td>
					<textarea rows="" cols="" style="width:300px" id="motto" name="motto" >${bo.motto}</textarea>
				</td>
			</tr>
			<tr style="display: none" id="remarkTr">
				<td  height="40" align="right" style="padding-right: 5px;" nowrap="nowrap"><span class="dye" >
				备注：</span>
				</td>
				<td>
					<textarea rows="" cols="" style="width:300px" id="remark" name="remark"  >${bo.remark}</textarea>
				</td>
			</tr>
       </tbody>
       </table>
       </div>
       </form>

  </body>
</html>
<script>
	selectType();
	function selectType(){
		var type=$("#type").val();
		if(type=='班级风采'||type=='班级明星'){
			$("#selectGradeTr").show();
			$("#thumbnailTr").show();
		}else{
			$("#selectGradeTr").hide();
			$("#thumbnailTr").hide();
		}
		if(type=='班级明星'){
			$("#mottoTr").show();
			$("#remarkTr").show();
		}else{
			$("#mottoTr").hide();
			$("#remarkTr").hide();
		}
	}
</script>
