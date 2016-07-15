<%@ page language="java" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="<%=basePath%>/web/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=basePath%>/web/ckeditor/config.js"></script>
	<script type="text/javascript"  src="<%=basePath%>/s9/pic/js/find.js?verson=1"></script>	
	<script type="text/javascript">
		
		 $(function (){
				var config=CKEDITOR.editorConfig = function(config)
				{
				    config.extraPlugins = 'code';
				    config.filebrowserUploadUrl = '../fileUpload.shtml?type=File' ;
					config.filebrowserImageUploadUrl = '../fileUpload.shtml?type=Image' ;
				    config.filebrowserImageBrowseUrl = '../browerServer.shtml?type=image'; 
				    config.toolbar = [[ 'Image', 'Code' ]];
				};
				
			  CKEDITOR.replace('content',config);
				
			
				var flag="${backInfo}";
				if(flag=='1'){
					window.parent.reload("添加机构信息成功");
				}else if(flag=='0'){
					$.ligerDialog.error("机构信息保存失败");
					return;
				}
			});
		   function setImageURL(value){
			   	$("#attachmentUrl").val(value);
			   $("#imgFile").attr('src',value);
		   }
		</script>
</head>
<body class="dialogBody">
		<form id="form2" action="" method="post" onsubmit="return false;">
		  <input type="hidden" id="pid"  name="pid" value="${pid}"/>
			<table width="100%" height="180" align="center" cellpadding="2" cellspacing="0">
				<tbody>
					<tr>
						<td width="100%">
							<div class="z-legend">
								<b>基本信息</b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="39%" height="30" align="right">
											图片名称：
										</td>
										<td width="61%">
											<input value="" type="text" id="name" name="name">
											<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
										</td>
									</tr>
									<tr>
										<td height="30" align="right">
											图片URL：
										</td>
										<td>
											<input type="text" id="url" name="url">
											<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
										</td>
									</tr>
									
									<tr>
										<td height="30" align="right">
											轮播图片：
										</td>
										<td>
											<input type="text" id="attachmentUrl" name="attachmentUrl" />
											
											<img class='icon403a20' src='../s9/res/img/icon000.png' onclick="CKEDITOR.tools.callFunction(4, this); return false;";>
											<span
												style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
										<div style="display: none">
							  		 		<textarea id="content" name="contents" rows="1" cols="1"  ></textarea>
 									  		 <img  id="imgFile" width="136" height="61" name="imgFile" src="">
							  			</div>
										</td>
									</tr>
									<tr style="">
										<td height="30" align="right">
											显示顺序：
										</td>
										<td>
											<input  type="text" id="tum" name="tum" value="">
										</td>
									</tr>
									<tr style="">
										<td height="30" align="right">
											备注：
										</td>
										<td>
											<input  type="text" id="remark" name="remark" value="">
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					
					</tr>
				</tbody>
			</table>
			<div class="aui_buttons">
				<button value="保存" id="save"  class="z-dlg-button z-dialog-okbutton aui_state_highlight">
					保存
				</button>
			</div>
		</form>

	</body>
</html>
