	var gridManager;//表格对象
	
	//初始化加载该角色下的用户列表数据
    $(function (){ 
    	var roleId=$("#roleId").val();
		var url = "../sysRole/initUserRole.shtml?roleId="+roleId;
		initUserRole(url);
		gridManager = $("#maingrid").ligerGetGridManager();
    });
    
	//初始化角色下的用户列表数据
	function initUserRole(url){
		window['g'] = $("#maingrid").ligerGrid({
				height:'90%',
				width:'99.6%',
				headerRowHeight:28,
				rowHeight:26,
				checkbox: true,
		  		columns: [
			        { display: '用户标识', name: 'userId', width: '13%' },
			        { display: '用户编号', name: 'userNo', width: '9%' },
			        { display: '用户名称', name: 'userName', width: '13%' },
			        { display: '组织机构标识', name: 'organId',  width: '9%' },
			        { display: '用户真实姓名', name: 'userRealName', width: '13%' },
			        { display: '用户状态', name: 'curStatusCode',  width: '6%' , type: 'int', align: 'center' ,render: function (row){
						if(row.curStatusCode == "01"){
							return "正常";
						}else if(row.curStatusCode == "02"){
							return "<font color='red'>注销</font>";
						}
			        }},
			        { display: '性别', name: 'gender', width: '5%' , type: 'int', align: 'center' ,render: function (row){
						if(row.gender == "01"){
							return "男";
						}else if(row.gender == "02"){
							return "女";
						}
					}},
			        { display: '联系电话', name: 'tel', width: '9%' },
			        { display: '移动电话', name: 'mobile', width: '9%' },
			        { display: '电子邮件', name: 'email', width: '9%' }
		        ], url:url, pageSize:20,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize"
			 });
	    $("#pageloading").hide();
	}
	
	//添加用户到角色，弹出用户列表页
	function addUserToRole(roleId){
		var node = parent.treeManger.getSelected();
		if(node){
			var url = "../sysRole/forInitAddUser.shtml?roleId="+roleId;
			m = $.ligerDialog.open( {
				url : url,
				height : 520,
				width : 530,
				title : '用户列表',
				isResize : true
			});
			if (!m) {
				$.ligerDialog.error("添加用户到角色失败！");
				return;
			}
		}else{
			$.ligerDialog.warn("请先选择角色");
		}
	}
	
	//添加用户到角色成功后关闭弹出框，并重新初始该角色下的用户列表
	function reload(roleId){
		m.close();
		var url = "../sysRole/initUserRole.shtml?roleId="+roleId;
		initUserRole(url);
	}
	
	//从角色中删除用户
	function delUserFromRole(roleId){
		var rowsdata = gridManager.getCheckedRows();
		if(rowsdata.length!=0){
			$.ligerDialog.confirm("确认删除选中用户",function(flag){
				if(flag){
					var str="";
					$(rowsdata).each(function (){
			           str += this.userId + ",";
			        });
					$.ajax( {
						url : "../sysRole/delUserFromRole.shtml?userIds="+str+"&roleId="+roleId,
						type : "POST",
						async : false,
						dataType : "json",
						success : function(result) {
							var falg = result.msg;
							if (falg == 'success') {
								$.ligerDialog.success("删除成功！");
								var url = "../sysRole/initUserRole.shtml?roleId="+roleId;
								initUserRole(url);
							}else{
								$.ligerDialog.error("服务器操作异常");
						}
						},
						error : function(error) {
							$.ligerDialog.error("服务器操作异常");
						}
					});
				}
			});
	  	}else{
	  		$.ligerDialog.warn("请选择行");
	  	}
	}
	
	//点击基本信息tab页，将隐藏菜单权限tab页
	function roleInfo(){
	  	$("#role").addClass("z-tab z-tab-current");
	  	$("#menu").removeClass();
	  	$("#menu").addClass("z-tab");
	  	$("#roleMenu").hide();
	  	$("#roleInfo").show();
	}
	
	//点击菜单权限tab页，将隐藏基本信息tab页
	function roleMenu(){
	  	$("#role").removeClass();
	  	$("#role").addClass("z-tab");
	  	$("#menu").addClass("z-tab z-tab-current");
	  	$("#roleInfo").hide();
	  	$("#roleMenu").show();
	}