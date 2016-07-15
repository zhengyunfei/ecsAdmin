var grid = null;//表格对象
var m; 	//弹出窗口对象
// 查询用户信息列表数据
function findVipInfoList(url){
	   var pageSize=20;
       grid = $("#maingrid").ligerGrid({
    	    width:'100%',
    	    height:'100%',
			headerRowHeight:28,
			rowHeight:26,
			checkbox: true,
    		columns: [
				{ display: '用户ID', name: 'userId', width: '10%', type: 'long',minWidth: 120,render:function(r){
				
					return '<a href="javascript:detail(\''+r.userId+'\')">'+r.userId+'</a>';	
				}},
				{ display: '会员名称', name: 'userName', width: '10%',minWidth: 140 },
				{ display: '手机号', name: 'mobile',  width: '10%',minWidth: 130 },
				{ display: '会员状态', name: 'userStatusName', width: '10%',minWidth: 100 },
				{ display: '注册时间', name: 'userInputtime',  width: '10%' ,minWidth: 130},
				{ display: '登录次数', name: 'userLoginnum', width: '10%' ,minWidth: 100},
				{ display: '最近登录时间', name: 'userLasttime', width: '10%' ,minWidth: 100},
				{ display: '备注', name: 'remark', width: '15%',minWidth: 90}
			
          ], url:url, pageSize:20,enabledEdit: true,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize",onSelectRow:onClickRow
//          , onDblClickRow : function (data, rowindex, rowobj)
//                {
//    	  					 var obj=$(".l-dialog");
//    	  					 if(obj.length!=0){
//    	  						 $(obj).close();
//    	  					 }
//    	   			  $.ligerDialog.prompt('编辑备注',data.remark, function (yes,value) { 
//    	   				if(yes){
//    	   					updateUserRemark(data.userId,value); 
//    	   				}
//    	   				
//    	   			});
//    	   			   $(".l-dialog-close").live("click",function(){
//    	   				 var obj=$(".l-dialog");
//    	   				 obj.remove();
//    	   			 })
//                    //$.ligerDialog.alert('选择的是' + data.userId);
//                } 
	
		 });
      $("#pageloading").hide();
}
//根据userId查询明细
function detail(userId){
		 var url="../vipManage/findOrganInfoDetail.shtml?userId="+userId;
	 	 m=$.ligerDialog.open({ url: url,title:"会员信息明细",name:"myDetail",showMax: true, showToggle: true, showMin: true, isResize: true, slide: false,width:800,height:600, buttons: [
	 		 { text: '确定', onclick: function (item, dialog) {
	 			 	myDetail.save();
	 		 } },
	 		 { text: '取消', onclick: function (item, dialog) { dialog.close(); } 
	 		 } 
	 		 ] 
	 		 });
	 	}

//修改地区
function updateArea(value){
	var obj=value.split(',');
	var userId=obj[0];
	var codeArea=obj[1];
	var url="../vipManage/initAreaPage.shtml?userId="+userId+"&codeArea="+codeArea+'&type=organ';
	m = $.ligerDialog.open({ height: 400,width:600, title:'所属地区', url: url, showMax: true, showToggle: true, showMin: true, isResize: true, slide: false });
}

//图片放大
function fun(src){
	var url="../vipManage/picPage.shtml?src="+src;
	var m=$.ligerDialog.open({ url: url,title:"原始图片",showMax: true, showToggle: true, showMin: true, isResize: true, slide: false,width:800,height:600 });
}
//修改备注
 function updateUserRemark(userId,remark){
	 var url="../vipManage/updUserAjax.shtml";
	 $.ajax({
		 url:url,
		 type:'post',
		 dataType:'json',
		 data:{"userId":userId,"remark":remark},
		 success:function(data){
		 	if(data.result){
		 		$.ligerDialog.success("操作成功！",function(){
		 			grid.loadData(); //重新加载不查询数据库
		 		});
				
		 	}
		 },error:function(){
			 $.ligerDialog.error("操作失败！");
				return false;
		 }
		 
	 });
 }
 //群发站内信
 function sendMessage(){
	var userId='';
	var rows = grid.getCheckedRows();
	var length=rows.length;
	if(length<1){
		 $.ligerDialog.error("请至少选择一条会员信息");
		 return false;
	}
	var rows = grid.getCheckedRows();
    $(rows).each(function (index)
    {
    	if(index==rows.length-1){
    		 userId += this.userId;
    	}else{
    		 userId += this.userId + ",";
    	}
       
    });
	  var url="../vipManage/initPage.shtml?userId="+userId;
	  m = $.ligerDialog.open({ height: 400,width:600, title:'群发站内信', url: url, showMax: true, showToggle: true, showMin: true, isResize: true, slide: false }); 
 }
  //发送邀请码
 function sendInvitationCode(){
	 var userId='';
	 var mobile='';
	var rows = grid.getCheckedRows();
	var length=rows.length;
	if(length<1){
		 $.ligerDialog.error("请至少选择一条会员信息");
		 return false;
	}
	var rows = grid.getCheckedRows();
    $(rows).each(function (index)
    {
    	if(index==rows.length-1){
    		 userId += this.userId;
    		 mobile+=this.mobile;
    	}else{
    		 userId += this.userId + ",";
    		  mobile+=this.mobile+",";
    	}
       
    });
	  var url="../invitationCode/initAddPage.shtml?userId="+userId+"&mobile="+mobile;
	  m = $.ligerDialog.open({ height: 400,width:600, title:'群发邀请码', url: url, showMax: true, showToggle: true, showMin: true, isResize: true, slide: false }); 
 }
/**
 * 拓展行点击事件radio选中
 **/
function onClickRow(rowdata, rowindex, rowDomElement) {
	var $v = $(rowDomElement);
	$v.find(":checkbox").attr("checked","checked")
}

//搜索
function doSearch(){
	var mobile = $("#mobile").val();
	var userName = $("#userName").val();
	var userStatus=$("#vipStatus").val();
	var userType="organ";
	//查询用户信息列表
	var url = "../vipManage/vipInfoList.shtml?mobile="+mobile+"&userName="+userName+"&userStatus="+userStatus+"&userType="+userType ;
	findVipInfoList(url);
}
//重置密码
function resetPassword(){
	var userId='';
	var rows = grid.getCheckedRows();
	var length=rows.length;
	if(length<1){
		 $.ligerDialog.error("请至少选择一条会员信息");
		 return false;
	}else if(length>1){
		$.ligerDialog.error("只能选择一个用户进行重置！");
		return false;
	}
	var rows = grid.getCheckedRows();
    $(rows).each(function (index)
    {
    	if(index==rows.length-1){
    		 userId += this.userId;
    	}else{
    		 userId += this.userId + ",";
    	}
       
    });
	var url= "../vipManage/initPwdReset.shtml?userId="+userId;
	m = $.ligerDialog.open({ url: url, height: 220,width:550, title:'密码重置',isResize: true ,top:50}); 
}
//批量修改会员状态
function update(flag){
	var userId='';
	var mobile='';
	var userName='';
	var vipStatus=$('#vipStatus').val();
	if(length ==0){
		$.ligerDialog.error("没有选中任何会员！");
		return false;
	}
	
	/**********/
	var userId='';
	var rows = grid.getCheckedRows();
	var length=rows.length;
	if(length<1){
		 $.ligerDialog.error("请至少选择一条会员信息");
		 return false;
	}
	var rows = grid.getCheckedRows();
    $(rows).each(function (index)
    {
    	if(index==rows.length-1){
    		 userId += this.userId;
    		 mobile+=this.mobile;
    		 userName+=this.userName;
    	}else{
    		  userId += this.userId + ",";
    		  mobile+=this.mobile+",";
    		  userName+=this.userName+",";
    	}
       
    });
	/*********/
	
	var userStatus=$("#userStatus").val();
	var userGroup=$("#userGroup").val();
	var data;
	if(flag==1){//会员状态
		
		 data={
			      "userIds" : userId,
			      "vipStatus":vipStatus,
			      "userStatus":userStatus
			     };
	}
	if(flag==2){//会员等级
	    data={
			      "userIds" : userId,
			     "userGroup":userGroup
			     };
	}
	var url="../vipManage/updSelectAllUser.shtml";
	$.ajax({
						type : "post",
						url : url,
						data : data,
						success : function(data) {
							//如果删除成功，则刷新页面！
							if(data["message"]=="1") {
								$.ligerDialog.success("批量操作成功！",function(){
									if(flag==1){//发送短信
											$.ligerDialog.confirm('是否需要给会员发送短信提示?', function (yes) {
												if(yes){
														var url='../vipManage/sendMsg.shtml';
													    var data={"mobile":mobile,"userStatus":userStatus,'userName':userName};
														$.ajax({
															type : "post",
															url : url,
															data : data,
															success : function(result) {
																$.ligerDialog.success(length+"条短信发送成功");
																//记录短信发送内容
															},error:function(){
																
															 }
															});
												}
												
											});
										
									    }
									});
							}else if(data["mssage"]=="0")  {
								$.ligerDialog.error("批量操作失败！");
							}else{
								$.ligerDialog.error(data["mssage"]);
							}
							grid.loadData(); //重新加载不查询数据库
						},
						error : function() {
							$.ligerDialog.error("批量操作失败！");
						}
					});
}
//保存密码重置
function saveResetPwd(){
	var newpwd = $("#newPwd").val();
	var surepwd = $("#newPwdAgain").val();
	if(validate(newpwd,surepwd)){
		$("#userPassword").val(hex_md5($("#newPwd").val()));
		var url="../vipManage/updResetPwd.shtml";
		$("#saveUpdResetPwd").attr('action',url);
		$("#saveUpdResetPwd").submit();
	}
}
//密码校验
function validate(newpwd,surepwd){
	if(newpwd == null){
		$.ligerDialog.error("请输入您的新密码");
		return false;
	}else if(newpwd.length > 16 || newpwd.length < 6){
		$.ligerDialog.error("密码应在6-16位之间");
		return false;
	}else if(surepwd == null){
		$.ligerDialog.error("请确认您的新密码");
		return false;
	}else if(newpwd != surepwd){
		$.ligerDialog.error("两次输入密码不一致");
		return false;
	}
	return true;
}
//密码校验
function updPwdValidate(newpwd,surepwd){
	if(newpwd == null){
		$.ligerDialog.error("请输入您的新密码");
		return false;
	}else if(surepwd == null){
		$.ligerDialog.error("请确认您的新密码");
		return false;
	}else if(newpwd != surepwd){
		$.ligerDialog.error("两次输入密码不一致");
		return false;
	}
	return true;
}
/*
 * 新增会员信息初始化
 */
function addUserInit() {
	var url= "../vipManage/vipManageAddInit.shtml" ;
	m = $.ligerDialog.open({ url: url, height: 480,width:600, title:'添加会员信息',isResize: true ,top:50}); 
}

/*
 * 新增用户信息
 */
function forSave(){
	var newpwd=$("#userPassword").val();
    var surepwd=$("#userPasswordAgain").val();
	if(validData()){
		if(validate(newpwd,surepwd)){
			var pwd = hex_md5(newpwd);
			$("#userPassword").val(pwd);
			$("#form1").attr("onsubmit","");
			$("#form1").attr("action","../vipManage/addUser.shtml");
			$("#form1").submit();
		}
	}
}
/*
 * 修改用户信息初始化
 */
function updUserInit() {
	//获取选中记录行
		var rowid=grid.getSelecteds();
		var length=rowid.length;
		if(length ==0){
			$.ligerDialog.error("请选择需要修改的用户！");
			return false;
		}
		if(length > 1){
			$.ligerDialog.error("只能选择一个用户信息进行修改！");
			return false;
		}
		var userId = rowid[0].userId ;
		var url= "../vipManage/updUserInit.shtml?userId="+userId;
		m = $.ligerDialog.open({ url: url, height: 480,width:600, title:'修改用户',isResize: true ,top:50}); 
}
/*
 * 修改用户信息
 */
function forUserUpd(){
	if(validData()){
		var newpwd = $("#userPassword").val();
		var surepwd = $("#userPasswordAgain").val() ;
		if(updPwdValidate(newpwd,surepwd)){
			var pwd = hex_md5(newpwd);
			$("#userPassword").val(pwd);
			$("#form1").attr("onsubmit","");
			$("#form1").attr("action","../vipManage/updUser.shtml");
			$("#form1").submit();
		}
	}
}
/*
*验证表单数据
*/
function validData(){
	var userName = $("#userName").val();
	if(userName.replace(/(^\s*)|(\s*$)/,"").length==0){
		$.ligerDialog.error("用户名不能为空！");
		return false;
	
	}
	var mobile = $("#mobile").val();
	if(mobile.replace(/(^\s*)|(\s*$)/,"").length==0){
		$.ligerDialog.warn("手机号码不能为空！");
		return false;
	}
	return true;
}
function check(){
	return false;
}
/*
 * 用户信息成功添加返回重新刷新列表
 */
function reload(backInfo){
	$.ligerDialog.success(backInfo);
	grid.loadData(); //重新加载不查询数据库
	m.close();
}

/*
 *删除选中的用户信息 
 */
function delUser(){
	var userId ='';
	var obj=$('input:checkbox[name=ckbox]:checked');
	var length=obj.length;
	obj.each(function(i){
		if(i<length-1){
			userId+=$(this).attr('id')+",";
		}else{
			userId+=$(this).attr('id');
		}
	});
		
	if (length ==0){
		$.ligerDialog.error("请选择要删除的记录！");
		return false;
	}else if(length > 0){
   		if($.ligerDialog.confirm('确实要删除选择的'+length+'条记录吗', 
			function (yes) { 
				if(yes){
					var url = "../vipManage/delUser.shtml";
					$.ajax({
						type : "post",
						url : url,
						data : {
							"userIds" : userId
						},
						success : function(data) {
							//如果删除成功，则刷新页面！
							if(data["message"]=="1") {
								$.ligerDialog.success("删除用户成功！");
							}else if(data["mssage"]=="0")  {
								$.ligerDialog.error("删除用户失败！");
							}else{
								$.ligerDialog.error(data["mssage"]);
							}
							//删除用户信息后查询初始化
							grid.loadData(); //重新加载不查询数据库
						},
						error : function() {
							$.ligerDialog.error("删除用户失败！");
						}
					});
				}
			}));
	}
}