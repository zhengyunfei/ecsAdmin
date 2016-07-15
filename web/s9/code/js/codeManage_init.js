
var treeManger; //树对象
//初始化加载数据
$(function() {
	initCodeSortTree();
});
//初始化代码分类信息树方法
function initCodeSortTree() {
	var url = "../codeManage/initCodeSortTree.shtml";
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
			'text' : '字典表',
			'isexpand' : true,
			'pid' : ""
		});
		for ( var k = 0; k < array.length; k++) {
			treeData.push( {
				'id' : array[k].codeSortId,
				'icon' : '',
				'text' : array[k].codeSortName,
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
	var higth = $("#treeone").height();
	treeManger = $("#treeone").ligerTree( {
		data : dataTree,
		idFieldName : 'id',
		parentIDFieldName : 'pid',
		checkbox : false,
		slide : true,
		nodeWidth : 350,
		onClick : onclick
	});
	var codeSortId = dataTree[1].id;
	treeManger.selectNode(codeSortId);
	$("#codeManage").attr("src","../codeManage/initCodeSortAndItem.shtml?codeSortId="+ codeSortId);
	$("#treeNodeId").val(codeSortId);
}
//单击事件
function onclick(note) {
	//得到所选节点的id
	var codeSortId = note.data.id;
	if(codeSortId!=-1){
		$("#codeManage").attr("src","../codeManage/initCodeSortAndItem.shtml?codeSortId="+ codeSortId);
		$("#treeNodeId").val(codeSortId);
	}
}


/**
 * 添加分类信息
 */
function addCodeSort() {
	var url = "../codeManage/forInitSaveCodeSort.shtml";
	m = $.ligerDialog.open( {
		url : url,
		height : 550,
		width : 550,
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
function editCodeSort() {
	var codeSortId = $("#treeNodeId").val();
	if (codeSortId != '') {
		var url = "../codeManage/forInitUpdateCodeSort.shtml?codeSortId="+ codeSortId;
		m = $.ligerDialog.open( {
			url : url,
			height : 400,
			width : 550,
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
function delCodeSort() {
	var codeSortId = $("#treeNodeId").val();
	if (codeSortId != '') {
		var url = "../codeManage/delCodeSort.shtml?codeSortId="+codeSortId;
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
					$.ligerDialog.success("删除成功！",function(){
						removeTreeItem();
					});
					
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
function reload(codeSortId, codeSortName) {
	$.ligerDialog.success("保存成功");
	//操作成功后修改树
	if (codeSortId == null) {
		updateTreeItem(codeSortName);
	} else {
		addTreeItem(codeSortId, codeSortName);
	}
	//关闭弹出窗口
	m.close();
}

//添加树节点
function addTreeItem(codeSortId, codeSortName) {
	var node = treeManger.getSelected();
	var nodes = [];
	nodes.push( {
		'id' : codeSortId,
		'text' : codeSortName,
		'isexpand' : false,
		'pid' : "-1"
	});
	if (node)
		treeManger.append(node.target, nodes);
	else
		treeManger.append(null, nodes);
	initTree();
}
//重新初始化树
function initTree(){
	$("#treeone").html('');
	initCodeSortTree();
}
//删除树节点
function removeTreeItem() {
	var node = treeManger.getSelected();
	if (node){
		treeManger.remove(node.target);
	}
	else{
		$.ligerDialog.warn("请先选择节点");
	}
	initTree();
}
//修改树节点
function updateTreeItem(codeSortName) {
	var node = treeManger.getSelected();
	if (node)
		treeManger.update(node.target, {
			text : codeSortName
		});
}


