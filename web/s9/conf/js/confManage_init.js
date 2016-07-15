
var treeManger; //树对象
//初始化加载数据
$(function() {
	initConfSortTree();
});
//初始化代码分类信息树方法
function initConfSortTree() {
	var url = "../confManage/initConfSortTree.shtml";
	$.ajax( {
		url : url,
		type : "POST",
		async : false,
		dataType : "json",
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(result) {
			var array = result.dataList;
			var treeData = [];
			var index = '';
			//为每个根点赋值所有子节点
		treeData.push( {
			'id' : '-1',
			'icon' : '',
			'text' : '栏目管理',
			'isexpand' : true,
			'pid' : ""
		});
		for ( var k = 0; k < array.length; k++) {
			treeData.push( {
				'id' : array[k].sortCode,
				'icon' : '',
				'text' : array[k].confDes,
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
	treeManger = $("#treeone").ligerTree( {
		data : dataTree,
		idFieldName : 'id',
		parentIDFieldName : 'pid',
		checkbox : false,
		slide : true,
		nodeWidth : 350,
		onClick : onclick
	});
	var sortCode = dataTree[1].id;
	treeManger.selectNode(sortCode);
	$("#confManage").attr("src","../confManage/initConfSortAndItem.shtml?sortCode="+ sortCode);
	$("#treeNodeId").val(sortCode);
}
//单击事件
function onclick(note) {
	//得到所选节点的id
	var sortCode = note.data.id;
	$("#confManage").attr("src","../confManage/initConfSortAndItem.shtml?sortCode="+ sortCode);
	$("#treeNodeId").val(sortCode);
}
/**
 * 添加分类信息
 */
function addConfSort() {
	var url = "../confManage/forInitSaveConfSort.shtml";
	m = $.ligerDialog.open( {
		url : url,
		height : 400,
		width : 500,
		title : '添加分类信息',
		isResize : true
	});
	if (!m) {
		$.ligerDialog.error("添加分类信息失败！");
		return;
	}
}
/**
 * 编辑分类信息
 */
function editConfSort() {
	var sortCode = $("#treeNodeId").val();
	if (sortCode != '') {
		var url = "../confManage/forInitUpdateConfSort.shtml?sortCode="+ sortCode;
		m = $.ligerDialog.open( {
			url : url,
			height : 400,
			width : 500,
			title : '编辑分类信息',
			isResize : true
		});
		if (!m) {
			$.ligerDialog.error("编辑分类信息失败！");
			return;
		}
	} else {
		$.ligerDialog.warn("请选择要编辑的分类信息！");
	}
}
/**
 * 删除分类信息
 */
function delConfSort() {
	var sortCode = $("#treeNodeId").val();
	if (sortCode != '') {
		var url = "../confManage/delConfSort.shtml?sortCode="+sortCode;
		$.ajax( {
			url : url,
			type : "POST",
			async : false,
			dataType : "json",
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(result) {
				var falg = result.msg;
				if (falg == 'sucess') {
					$.ligerDialog.success("删除成功！");
					removeTreeItem();
				}
			},
			error : function(error) {
				$.ligerDialog.error("服务器操作异常");
			}
		});
	} else {
		$.ligerDialog.warn("请选择要删除的代码信息！");
	}
}

//详细页面返回重新刷新列表
function reload(sortCode, sortName) {
	$.ligerDialog.success("保存成功");
	//操作成功后修改树
	if (sortCode == null) {
		updateTreeItem(sortName);
	} else {
		addTreeItem(sortCode, sortName);
	}
	//关闭弹出窗口
	m.close();
}

//添加树节点
function addTreeItem(sortCode, sortName) {
	var node = treeManger.getSelected();
	var nodes = [];
	nodes.push( {
		'id' : sortCode,
		'text' : sortName,
		'isexpand' : false,
		'pid' : "-1"
	});
	if (node)
		treeManger.append(node.target, nodes);
	else
		treeManger.append(null, nodes);
}
//删除树节点
function removeTreeItem() {
	var node = treeManger.getSelected();
	if (node)
		treeManger.remove(node.target);
	else
		$.ligerDialog.warn("请先选择节点");
}
//修改树节点
function updateTreeItem(sortName) {
	var node = treeManger.getSelected();
	if (node)
		treeManger.update(node.target, {
			text : sortName
	});
}





