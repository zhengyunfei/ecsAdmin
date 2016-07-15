<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>添加学生</title>
	<%@include file="/s9/common/common.jsp"%>
	<script type="text/javascript" src="<%=basePath%>/eeh/PewStudent/js/pewAdd.js"></script>
</head>
<body class="z-body-detail" style="overflow: hidden">
<input type="hidden" id="electiveCourse" name="electiveCourse" value="${courseName}">
<table width="100%" id="js_layoutTable" border="0" cellspacing="0" cellpadding="0" height="100%" class="js_layoutTable">
	<tbody>
	<tr>
		<td style="width:100%" align="right">
			<div class="z-toolbar" id="ToolBar1" >
				<div class="z-toolbar-ct">
					<div class="z-toolbar-overflow">
						<div class="z-toolbar-nowrap">
							选择班级：
							<select id="classId" name="classId" onchange="selectGrade()">
								<option value="">请选择班级</option>
								<c:forEach items="${gradeList}" var="grade">
									<c:choose>
										<c:when test="${classId==grade.name}">
											<option value="${grade.name}" selected="selected">${grade.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${grade.name}">${grade.name}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</div>

		</td>

	</tr>
	<tr valign="top">
		<!-- 数据信息 -->
		<td style="padding: 5px; height: auto;" colspan="2">
			<div class="l-loading" style="display:block" id="pageloading"></div>
			<div class="l-clear"></div>
			<div id="maingrid"></div>
		</td>
	</tr>
	<tr>
		<td style="width:100%" align="right">
			<div class="z-toolbar" >
				<div class="z-toolbar-ct">
					<div class="z-toolbar-overflow">
						<div class="z-toolbar-nowrap">
							<a href="javascript:void(0);" class="z-btn z-btn-flat" onclick="forSave();">
								<img class="icon021a4" src="../s9/res/img/icon000.png"><b>提交<i></i></b>
							</a>
						</div>
					</div>
				</div>
			</div>

		</td>

	</tr>
	</tbody>
</table>

</body>
</html>
<script type="text/javascript">
	// 初始化加载
	$(function(){
		var classId=$("#classId").val();
		var url='../student/findAllList.shtml';
		if(''!=classId&&null!=classId){
			url+="?classId="+classId;
		}
		findAllList(url);
	})
    //选择班级
	function selectGrade(){
		var classId=$("#classId").val();
		var url='../student/findAllList.shtml?classId='+classId;
		findAllList(url);

	}
	//保存
	function  forSave(){
		//获取选中记录行
		var rowid=grid.getSelecteds();
		var length=rowid.length;
		if(length ==0){
			$.ligerDialog.error("请先选择学生信息！");
			return false;
		}
		var ids='';
		for(var i= 0;i<length;i++){
			if(i<length-1){
				ids+=rowid[i].id+",";
			}else{
				ids+=rowid[i].id;
			}
		}
		//获取选择的课程
		var courseName=$("#electiveCourse").val();
		var data={'ids':ids,"courseName":courseName};
		var url="../PewStudent/forAddAjax.shtml";
		addSaveAjax(url,data);

	}
	function addSaveAjax(url,data){
		$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			data:data,
			success:function(result){
				if(result.success){
						parent.reload("操作成功");
				}else{
					$.ligerDialog.error('操作失败');
				}
			},error:function(){
				$.ligerDialog.error('服务器也有duang的时候，请您谅解我一下了');
			}
		})
	}

</script>
