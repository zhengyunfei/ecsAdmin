	var treeManger; //树对象
	var m;//弹出框对象
	
	//初始加载角色树
	$(function() {
		initRoleTree();
	});
	
	//初始化角色树方法
	function initRoleTree() {
		var url = "../sysRole/initRoleTree.shtml";
		$.ajax( {
			url : url,
			type : "POST",
			async : false,
			dataType : "json",
			success : function(result) {
				if(result.msg=="success"){
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
				}else{
					$.ligerDialog.error("角色树加载失败");
				}
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
			checkbox : false,
			slide : true,
			nodeWidth : 350,
			onClick : onclick
		});
		//选中角色树的第一个节点,执行单击事件
		var roleId = dataTree[1].id;
		treeManger.selectNode(roleId);
		onclick(treeManger.getSelected());
	}
	
	//单击事件
	function onclick(note) {
		//得到所选节点的id
		if(note.data.id!='-1'){
			var roleId = note.data.id;
			$("#roleManage").attr("src","../sysRole/initUserOfRole.shtml?roleId="+ roleId);
			$("#treeNodeId").val(roleId);
		}
	}
	
	//添加角色，弹出添加角色表单
	function addRole(){
		var url = "../sysRole/forInitAddRole.shtml";
		m = $.ligerDialog.open( {
			url : url,
			height : 230,
			width : 450,
			title : '新建角色',
			isResize : true
		});
		if (!m) {
			$.ligerDialog.error("新建角色失败！");
			return;
		}
	}
	
	//修改角色信息,弹出修改角色表单
	function updRole() {
		var roleId = $("#treeNodeId").val();
		if (roleId != '') {
			var url = "../sysRole/forInitUpdRole.shtml?roleId="+ roleId;
			m = $.ligerDialog.open( {
				url : url,
				height : 230,
				width : 450,
				title : '修改角色',
				isResize : true
			});
			if (!m) {
				$.ligerDialog.error("修改角色失败！");
				return;
			}
		} else {
			$.ligerDialog.warn("请选择要修改的角色！");
		}
	}
	
	//删除角色信息
	function delRole() {
		var roleId = $("#treeNodeId").val();
		if (roleId != '') {
			var node = treeManger.getSelected();
			$.ligerDialog.confirm("确认删除角色"+node.data.text+"?",function(flag){
				if(flag){
					var url = "../sysRole/delRole.shtml?roleId="+roleId;
					$.ajax( {
						url : url,
						type : "POST",
						async : false,
						dataType : "json",
						success : function(result) {
							var flag = result.msg;
							if (flag == 'success') {
								$.ligerDialog.success("删除成功！");
								removeRoleTree();
							}else{
								$.ligerDialog.success("删除失败！");
							}
						},
						error : function(error) {
							$.ligerDialog.error("服务器操作异常");
						}
					});
				}
			});
		} else {
			$.ligerDialog.warn("请选择要删除的角色！");
		}
	}
	
	//添加，修改页面成功后返回重新刷新角色树
	function reload(roleId, roleName,type) {
		$.ligerDialog.success("保存成功");
		//操作成功后修改树
		if (type == "upd") {//修改角色树节点
			updateRoleTree(roleName);
		} else if(type == "add") {//添加角色树节点
			addRoleTree(roleId, roleName);
		}
		//关闭弹出窗口
		m.close();
	}
	
	//添加角色树节点
	function addRoleTree(roleId, roleName) {
		var nodes = [];
		nodes.push( {
			'id' : roleId,
			'text' : roleName,
			'isexpand' : false,
			'pid' : "-1"
		});
		treeManger.append('0', nodes);
	}
	
	//删除角色树节点
	function removeRoleTree() {
		var node = treeManger.getSelected();
		if (node)
			treeManger.remove(node.target);
		else
			$.ligerDialog.warn("请先选择节点");
	}
	
	//修改角色树节点
	function updateRoleTree(roleName) {
		var node = treeManger.getSelected();
		if (node)
			treeManger.update(node.target, {
				text : roleName
		});
	}
