	var gridManager;//表格对象
	
	// 初始化加载用户列表数据
	$(function() {
		var roleId = $("#roleId").val();
		var url = "../sysRole/findUserList.shtml?roleId="+roleId ;
		findUserList(url);
		gridManager = $("#maingrid").ligerGetGridManager();
	});
	
	//加载用户列表
	function findUserList(url) {
		window['g'] = $("#maingrid").ligerGrid(
				{
					height : '89%',
					width : '99.7%',
					headerRowHeight : 28,
					rowHeight : 26,
					checkbox : true,
					columns : [ {display : '用户标识',name : 'userId',width : '15%'}, 
					            {display : '用户名称',name : 'userName',	width : '15%'}, 
					            {display : '用户真实姓名',name : 'userRealName',width : '16%'}, 
					            {display : '用户状态',name : 'curStatusCode',width : '12%',type : 'int',align : 'center',	render : function(row) {
									if (row.curStatusCode == "01") {
										return "正常";
									} else if (row.curStatusCode == "02") {
										return "<font color='red'>注销</font>";
									}
					            }}, 
					            {display : '性别',name : 'gender',width : '12%',type : 'int',align : 'center',render : function(row) {
									if (row.gender == "01") {
										return "男";
									} else if (row.gender == "02") {
										return "女";
									}
					            }}, 
					            {display : '职务',name : 'position',width : '12%'}
					            ],
					url : url,
					pageSize : 10,
					rownumbers : true,
					pageParmName : "curNo",
					pagesizeParmName : "curSize"
				});
		$("#pageloading").hide();
	}
	
	// 根据用户名称或者真实姓名模糊查询用户信息
	function doSearch() {
		var roleId = $("#roleId").val();
		var userName = $("#userName").val();
		var realName = $("#realName").val();
		var url = "../sysRole/findUserList.shtml?roleId="+roleId+"&userName=" + userName + "&realName="+ realName;
		findUserList(url);
	}
	
	//保存添加的用户信息
	function forSaveUserToRole() {
		var rowsdata = gridManager.getCheckedRows();
		if (rowsdata.length != 0) {
			var str = "";
			$(rowsdata).each(function() {
				str += this.userId + ",";
			});
			$.ajax( {
				url : "../sysRole/addUserToRole.shtml?userIds=" + str + "&roleId="
						+ $("#roleId").val(),
				type : "POST",
				async : false,
				dataType : "json",
				success : function(result) {
					var falg = result.msg;
					if (falg == 'success') {
						$.ligerDialog.success("操作成功！");
						parent.reload($("#roleId").val());
					} else {
						$.ligerDialog.error("操作失败");
					}
				},
				error : function(error) {
					$.ligerDialog.error("服务器操作异常");
				}
			});
		} else {
			$.ligerDialog.warn("请选择行");
		}
	}