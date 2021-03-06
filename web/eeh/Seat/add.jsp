<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
		 pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>导入座位表</title>
	<!-- 引用本页面JS、CSS样式静态资源 -->
	<%@include file="/s9/common/common.jsp"%>
	<!-- 用户管理操作JS静态资源的引用 -->
	<script type="text/javascript" src="<%=basePath %>/eeh/Seat/js/add.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/Seat/js/number.js"></script>
	<script type="text/javascript" src="<%=basePath%>/web/upload/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/eeh/Seat/js/upload.js"></script>
</head>

<body class="dialogBody">
<form id="form1" onsubmit="return false;"
	  action="../Seat/forAddAjax.shtml" name="form1" method="post">
	<input type="hidden" id="courseId" name="courseId" value="${id}"/>
	<table width="100%"  align="center" cellpadding="2"  cellspacing="0">
		<tr>
			<td style="width: 100%">
				<div class="z-toolbar" id="ToolBar1" >
					<div class="z-toolbar-ct">
						<div class="z-toolbar-overflow">
							<div class="z-toolbar-nowrap">
								<a href="javascript:download();" class="z-btn z-btn-flat">
									<img class="icon021a12" ><b>导出座位表 然后下载到本地 修改后 在导入<i></i></b>
								</a>

							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>

		<tr>
			<td style="width: 100%">
				<div class="z-toolbar" id="ToolBar2" >
					<div class="z-toolbar-ct">
						<div class="z-toolbar-overflow">
							<div class="z-toolbar-nowrap">
								<a href="javascript:importExcel();" id="importExcel"   class="z-btn z-btn-flat">
								</a>

							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>

					</tbody>
				</table>
			</td>
		</tr>
	</table>

</form>
</body>
</html>
<script type="text/javascript">
	// 初始化加载
	$(function(){
		//var url='../Course/findAllList.shtml';
		//findAllList(url);
	})
	//下载标准课程表
	function download(){
		window.location.href="<%=basePath%>/eeh/excel/表格六：班级座位表.xlsx";
	}
	function forAdd(){
		parent.reload();
	}
</script>
