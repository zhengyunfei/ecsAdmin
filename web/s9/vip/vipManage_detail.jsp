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
								 onClick:function(value){
									 $("#select").ligerGetComboBoxManager().setValue(value.data.code);
								 },
								ajaxType: 'post' 
							}
				    });
					//初始化赋值
						var codeArea="${userInfo.codeArea}";
						if(codeArea!='null'&&codeArea!=''){
							 $("#select").ligerGetComboBoxManager().setValue(codeArea);
						}
	                }
	            	}
	             );
		    closeWin();
		      
			});
		function closeWin(){
			$(".l-dialog-close").live("click",function(){
					alert("1112");
    	   				 var obj=$(".l-dialog");
    	   				 obj.remove();
    	   	})
		}
		</script>
	</head>
	<ul id="tree" style="display: none"></ul>
	<input type="hidden" id="userId" name="userId" value="${userId}"/>
	<input type="hidden" id="type" name="type" value="${type}"/>
	<body class="dialogBody">
		<table class="z-datagrid dg_scrollable  dg_nobr" style="width:100%">
		<tr>
			<td>所在地区</td>
			<td>
				<input type="text" id="select"/>
			</td>
		</tr>
		<tr>
			<td>会员真实姓名</td>
			<td>${userInfo.userName}</td>
		</tr>
		<tr>
			<td>是否使用邀请码</td>
			<td><font color="red">
			<c:if test="${userInfo.invitationCode!=''}">
				使用
			</c:if>
			<c:if test="${userInfo.invitationCode==''}">
				未使用
			</c:if>
			
			</font></td>
		</tr>
		<tr>
			<td>性别</td>
			<td>${userInfo.sex}</td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td>${userInfo.maile}</td>
		</tr>
		<tr>
			<td>职位</td>
			<td>${userInfo.position}</td>
		</tr>
		<tr>
			<td>公司名称</td>
			<td>${userInfo.company}</td>
		</tr>
		<tr>
			<td>会员名片</td>
			<td>
			<c:if test="${userInfo.userCardUrl!=''}">
		       <a href="#" title="点击我查看大图" onclick="fun(this)"  src="${userInfo.userCardUrl}" >名片</a>
		     </c:if>
			</td>
		</tr>
		<tr>
			<td>希望了解产品</td>
			<td>${userInfo.likePro}</td>
		</tr>
		<tr>
			<td>身份证</td>
			<td>
				<c:if test="${userInfo.idCardUrl!=''}">
					 <a href="#" title="点击我查看大图" onclick="fun(this)"  src="${userInfo.idCardUrl}">身份证</a>
				</c:if>
					      
			</td>
		</tr>
		<tr>
			<td>身份证号码</td>
			<td>${userInfo.idCard}</td>
		</tr>
		<tr>
			<td>金融资产(万元)</td>
			<td>${userInfo.assest}</td>
		</tr>
		<tr>
			<td>平均收入(万元)</td>
			<td>${userInfo.arpm}</td>
		</tr>
		<tr>
			<td>用户头像</td>
			<td>
			<c:if test="${userInfo.userPhoto!=''&&userInfo.userPhoto!=null}">
					 <a href="#" title="点击我查看大图" onclick="fun(this)"  src="${userInfo.userPhoto}">身份证</a>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>备注</td>
			<td><textarea id="remark" style="width:350px;">${userInfo.remark}</textarea></td>
		</tr>
		</table>
		<div id="target1" style="width:200px; margin:3px; display:none;">
			<img id="targetImg" />
		</div>
	</body>
</html>
