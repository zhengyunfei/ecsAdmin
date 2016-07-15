<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>新增用户管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<script type="text/javascript">
		//图片放大操作
		 function fun(obj){
			 var src=$(obj).attr('src');
			 parent.fun(src);
		}
		 //修改地区
		 function save(){
			  	  var userId=$("#userId").val();
		    	  var code=$("#select").ligerGetComboBoxManager().getValue();
		    	  var name=$("#select").val();
		    	  var type=$("#type").val();
		    	  var remark=$("#remark").val();
		    	  var url="<%=basePath%>vipManage/updUserInfoAjax.shtml";
		    	  if(type!=''){
		    		  url="<%=basePath%>vipManage/updOrganInfoAjax.shtml";
		    	  }
		    	  var params={"userId":userId,"codeArea":code,"type":type,"remark":remark};
		    	  $.ajax({
		    		  url:url,
		    		  data:params,
		    		  type:'post',
		    		  dataType:"json",
		    		  success:function(data){
		    		  	if(data.result){
		    		  		parent.reload(data.message);
		    		  	}else{
		    		  		alert("操作失败");
		    		  	}
		    		  	
		    		  },error:function(){
		    			  
		    		  }
		    		  
		    	  });
		 }
		  $(function (){
					//查询用户信息列表
					var url='<%=basePath%>area/findAllList.shtml';
		             $("#tree").ligerTree({  
		             url:url, 
		             checkbox:false,
		             idFieldName :'code',
		             parentIDFieldName :'parentId',
		             textFieldName :'text',
		             onAfterAppend: function ()
	                {
	             		treeManger= $("#tree").ligerGetTreeManager();
	                    jsonData=JSON.stringify(treeManger.getData());
	                    jsonData=eval("("+jsonData+")");
						var combox=$("#select").ligerComboBox({
				        width: 350,
				        checkbox:true,
						isMultiSelect:  true,   //是否多选
			            isShowCheckBox: false,  //是否选择复选框
				        selectBoxWidth: 350,
				        selectBoxHeight: 350, 
				        valueField: 'code',
				        tree: { 
								data: jsonData, 
								checkbox:false,
								 single: true,
								 onClick:function(value){
									 $("#select").ligerGetComboBoxManager().setValue(value.data.code);
								 },
								ajaxType: 'post' 
							}
				    });
					//初始化赋值
						var codeArea="${organUserInfo.codeArea}";
						if(codeArea!='null'&&codeArea!=''){
							 $("#select").ligerGetComboBoxManager().setValue(codeArea);
						}
	                },
	            	}
	             );
		    
		      
			});
		
		</script>
	</head>
	<ul id="tree" style="display: none"></ul>
	<input type="hidden" id="userId" name="userId" value="${userId}"/>
	<input type="hidden" id="type" name="type" value="${type}"/>
	<body class="dialogBody">
		<table class="z-datagrid dg_scrollable  dg_nobr" style="width:100%">
		<tr>
			<td>所在地区</td>
			<td><input type="text" id="select"/></td>
		</tr>
		<tr>
			<td>机构名称</td>
			<td>${organUserInfo.orgName}</td>
		</tr>
		<tr>
			<td>机构净资产</td>
			<td>${organUserInfo.orgAssets}</td>
		</tr>
		<tr>
			<td>机构责任人姓名</td>
			<td>${organUserInfo.orgDutypersonName}</td>
		</tr>
		<tr>
			<td>机构责任人性别</td>
			<td>${organUserInfo.orgDutypersonSex}</td>
		</tr>
		<tr>
			<td>机构责任人手机号</td>
			<td>${organUserInfo.orgDutypepsonMobile}</td>
		</tr>
		<tr>
			<td>机构责任人邮箱</td>
			<td>${organUserInfo.orgDutypepsonEmaile}</td>
		</tr>
		<tr>
			<td>机构责任人职位</td>
			<td>${organUserInfo.orgDutypersonPosition}</td>
		</tr>
		<tr>
			<td>机构责任人名片</td>
			<td>
			<c:if test="${organUserInfo.orgDutypersonCardurl!=''}">
						 <a href="#" title="点击我查看大图" onclick="fun(this)"  src="${organUserInfo.orgDutypersonCardurl}" >名片</a>
			</c:if>
			</td>
		</tr>
		<tr>
			<td>机构营业执照</td>
			<td>
			<c:if test="${organUserInfo.orgLicenseUrl!=''}">
						 <a href="#" title="点击我查看大图" onclick="fun(this)"  src="${organUserInfo.orgLicenseUrl}">营业执照</a>
			</c:if>
			</td>
		</tr>
		<tr>
			<td>希望了解的清科产品</td>
			<td>${organUserInfo.likeProducts}</td>
		</tr>
		<tr>
			<td>是否使用邀请码</td>
			<td>
					<c:if test="${organUserInfo.invitationCode!=''}"><font color="red">使用</font></c:if>
					<c:if test="${organUserInfo.invitationCode==''}"><font color="green">未使用</font></c:if>
			</td>
		</tr>
		<tr>
			<td>邀请码</td>
			<td>${organUserInfo.invitationCode}</td>
		</tr>
		<tr>
			<td>备注</td>
			<td><textarea id="remark" style="width:350px;">${organUserInfo.remark}</textarea></td>
		</tr>
		
		
		</table>
		<div id="target1" style="width:200px; margin:3px; display:none;">
			<img id="targetImg" />
		</div>
	</body>
</html>
