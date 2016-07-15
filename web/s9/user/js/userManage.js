
	var grid = null;//表格对象
	var m ; 	//弹出窗口对象
	var treeManger; //树对象
	/*
	 * 初始化加载
	 */
	$(function(){
		//查询用户信息列表
		var url = "../userManage/findUserInfoListAjax.shtml" ;
		findUserInfoList(url);
		roleTree();
	});

	/*
	 * 查询用户信息列表数据
	 */
	function findUserInfoList(url){
		   var pageSize=20;
	       grid = $("#maingrid").ligerGrid({
	    	    width:'99.6%',
	    	    height:'100%',
				headerRowHeight:28,
				rowHeight:26,
				checkbox: true,
	    		columns: [
					{ display: '用户编号', name: 'userNo', width: '12%' },
					{ display: '登陆账号', name: 'userName', width: '14%' },
				/*	{ display: '区域编码', name: 'organNo', width: '13%' },*/
					{ display: '真实姓名', name: 'userRealName',  width: '13%' },
					{ display: '用户状态', name: 'curStatusCode', width: '6%', type: 'int', align: 'center' ,render: function (row){
						if(row.curStatusCode == "01"){
							return "正常";
						}else if(row.curStatusCode == "02"){
							return "<font color='red'>注销</font>";
						}
			        }},
					{ display: '性别', name: 'gender', width: '6%', type: 'int', align: 'center' ,render: function (row){
						if(row.gender == "01"){
							return "男";
						}else if(row.gender == "02"){
							return "女";
						}
			        }},
					{ display: '职务', name: 'position', width: '8%' },
					{ display: '微信openid', name: 'tel', width: '10%' },
					{ display: '移动电话', name: 'mobile', width: '10%' }

					/*{ display: '新增日期', name: 'addDate', width: '9%' },
					{ display: '添加人员', name: 'addUser', width: '9%' },*/
					/*{ display: '失效提醒日期', name: 'expHintDate', width: '9%' },*/
					/*{ display: '用户状态', name: 'curStatusCode', width: '6%' }*/

	          ], url:url, pageSize:20,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize"
			 });
	      $("#pageloading").hide();
	}

	/*
	 * 查询用户信息
	 */
	function doSearch(){
		var userNo = $("#userNo").val() ;
		var userName = $("#userName").val() ;
		var url="../userManage/findUserInfoListAjax.shtml?userNo="+userNo+"&userName="+userName ;
		findUserInfoList(url);
	}

	/*
	 * 新增用户信息初始化
	 */
	function addUserInit() {
		var url= "../userManage/addUserInit.shtml" ;
		m = $.ligerDialog.open({ url: url, height: 618,width:800, title:'添加用户',showMax: true, showToggle: true, showMin: true,isResize: true ,top:50});
	}

	/*
	 * 新增用户信息
	 */
	function forSave(){
		if(validData()){
			var pwd = hex_md5($("#userPwd").val())
			$("#userPwd").val(pwd);
			$("#form1").attr('onsubmit','');
			$("#form1").submit();
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
		var url= "../userManage/updUserInit.shtml?userId="+userId;
		m = $.ligerDialog.open({ url: url, height: 680,width:800, title:'修改用户',showMax: true, showToggle: true, showMin: true,isResize: true ,top:50});
	}

	/*
	*验证表单数据
	*/
	function validData(){
		var userName = $("#userName").val();
		if(userName.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("用户名称不能为空！");
			return false;
		}
		var userPwd = $("#userPwd").val();
		if(userPwd.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("密码不能不能为空！");
			return false;
		}

		return true;
	}
	function check(){
		return false;
	}

	/*
	 *删除选中的用户信息
	 */
	function delUser(){
		//用self参数来标识要删除的用户是否是登录账号本身
		var self = false;
   		var userId ='';
   		//获取选中记录行
   		var rowid=grid.getSelecteds();
   		var length=rowid.length;
		if (length ==0){
			$.ligerDialog.error("请选择要删除的记录！");
			return false;
		}else if(length > 0){
	   		//判断选择的记录中是否含有登录账号本身
			for(var i=0;i<length;i++){
				if(rowid[i].userId=="${userId}"){
					self = true;
				}
			}
			if(self==true){
				$.ligerDialog.error("所选记录中包含当前登录账号，不允许进行删除操作！");
				return;
			}else if($.ligerDialog.confirm('确实要删除选择的'+rowid.length+'条记录吗',
				function (yes) {
					if(yes){
						var url = "../userManage/delUser.shtml";
						//获取删除的ID
						for(var i=0;i<length;i++){
							if(i<length-1){
								userId+=rowid[i].userId+",";
							}else{
								userId+=rowid[i].userId;
							}
						}
						$.ajax({
							type : "post",
							url : url,
							data : {
								"userIds" : userId
							},
							success : function(data) {
								//如果删除成功，则刷新页面！
								if(data["message"]=="1") {
									$.ligerDialog.alert("删除用户成功！");
								}else if(data["mssage"]=="0")  {
									$.ligerDialog.error("删除用户失败！");
								}else{
									$.ligerDialog.error(data["mssage"]);
								}
								//删除用户信息后查询初始化
								findUserInfoList("../userManage/findUserInfoListAjax.shtml") ;
							},
							error : function() {
								$.ligerDialog.error("删除用户失败！");
							}
						});
					}
				}));
		}
	}


	/*
	 * 用户信息	启用和停用操作
	 */
	function updUserStatus(status){
		//用self参数来标识要操作的用户是否是登录账号本身
		var self = false ;
   		var userId ='' ;
   		var curStatusCode = '' ;
   		//获取选中记录行
   		var rowid=grid.getSelecteds();
   		var length=rowid.length;
		if (length ==0){
			$.ligerDialog.error("请选择要启用或停用的用户记录！");
			return false;
		}else if(length > 0){
			for(var i=0;i<length;i++){
				curStatusCode = rowid[i].curStatusCode ;
				if(curStatusCode=="02" && status=="0") {
					$.ligerDialog.error("选择记录中存在【注销】状态的用户!");
					return false;
				}else if(curStatusCode=="01" && status=="1") {
					$.ligerDialog.error("选择记录中存在【正常】状态的用户!");
					return false;
				}
			}
	   		//判断选择的记录中是否含有登录账号本身
			for(var i=0;i<length;i++){
				if(rowid[i].userId=="${userId}"){
					self = true;
				}
			}
			if(self==true){
				$.ligerDialog.error("所选记录中包含当前登录账号，不允许进行启用和停用操作！");
				return;
			}else if($.ligerDialog.confirm('确定要启用、停用选择的'+rowid.length+'条记录吗',
				function (yes) {
					if(yes){
						var url = "../userManage/updUserStatus.shtml";
						//获取需要操作的用户ID
						for(var i=0;i<length;i++){
							if(i<length-1){
								userId+=rowid[i].userId+",";
							}else{
								userId+=rowid[i].userId;
							}
						}
						$.ajax({
							type : "post",
							url : url,
							data : {
								"userIds" : userId,
								"status" : status
							},
							success : function(data) {
								//如果删除成功，则刷新页面！
								if(data["mssage"]=="1" && status=="1") {
									$.ligerDialog.error("用户启用成功！");
								}else if(data["mssage"]=="0" && status=="1") {
									$.ligerDialog.error("用户启用失败！");
								}else if(data["message"]=="1" && status=="0") {
									$.ligerDialog.error("用户停用成功！");
								}else if(data["mssage"]=="0" && status=="0") {
									$.ligerDialog.error("用户停用失败！");
								}else{
									$.ligerDialog.error(data["mssage"]);
								}
								//用户信息启用、停用后查询初始化
								findUserInfoList("../userManage/findUserInfoListAjax.shtml") ;
							},
							error : function() {
								$.ligerDialog.error("启用、停用用户信息失败！");
							}
						});
					}
				}));
		}
	}

	/*
	 * 用户信息成功添加返回重新刷新列表
	 */
	function reload(backInfo){
		$.ligerDialog.success(backInfo);
		grid.loadData(); //重新加载不查询数据库
		m.close();
	}

	/**
	 * @title: 获得角色树
	 * */
	function roleTree(){
		var url = "../sysRole/initRoleTree.shtml";
		$.ajax( {
			url : url,
			type : "POST",
			async : false,
			dataType : "json",
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(result) {
				var array = result.roleList;
				var treeData = [];
				//为每个根点赋值所有子节点
			treeData.push( {
				'id' : '-1',
				'icon' : '',
				'text' : '角色列表',
				'isexpand' : true,
				'pid' : ""
			});
			for ( var k = 0; k < array.length; k++) {
				treeData.push( {
					'id' : array[k].roleId,
					'icon' : '',
					'text' : array[k].roleName,
					'isexpand' : false,
					'pid' : "-1"
				});
			}
			loadTree(treeData);//加载树
		},
		error : function(error) {
			$.ligerDialog.error("服务器操作异常");
		}
		});

	}
	//加载树
	function loadTree(dataTree) {
		treeManger = $("#roleTree").ligerTree( {
			data : dataTree,
			idFieldName : 'id',
			parentIDFieldName : 'pid',
			checkbox : true,
			slide : true,
			nodeWidth : 350,
			onClick : onclick
		});
	}
	//单击事件
	function onclick(note) {
		//得到所选节点的id
		if(note.data.id!='-1'){
			var roleId = $("#roleId").val();
			var flag = false;
			if(roleId !="" && roleId !=null){
				var id = [];
				id = roleId.split(",");
				for(var i=0;i<id.length;i++){
					if(id[i] == note.data.id){
						roleId = roleId.replace(id[i]+",","");
						flag = true;
						break;
					}
				}
			}
			if(!flag){
				roleId += note.data.id+",";
			}
			$("#roleId").val(roleId);
		}
	}
