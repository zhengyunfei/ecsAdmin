var grid = null;// 表格对象
var m; // 弹出窗口对象
var statusData = [ {
	status :1,
	text :'有效'
}, {
	status :0,
	text :'无效'
} ];
var levelData = [ {
	commentLevel :0,
	text :'好'
}, {
	commentLevel :1,
	text :'中'
}, {
	commentLevel :2,
	text :'差'
} ];
/*
 * 初始化加载
 */
$( function() {
	// 查询评论信息列表
	var url = "../commentManage/commentInfoList.shtml";
	findCommentInfoList(url);
});
function findCommentInfoList(url) {
	var pageSize = 20;
	grid = $("#maingrid").ligerGrid( {
		width :'100%',
		height :'100%',
		headerRowHeight :28,
		rowHeight :26,
		checkbox :true,
		columns : [ {
			display :'会员ID',
			name :'vipId',
			width :'12%'
		}, {
			display :'评论等级',
			name :'commentLevel',
			width :'10%',
			type :'int',
			editor : {
				type :'select',
				data :levelData,
				valueField :'commentLevel'
			},
			render : function(item) {
				if (parseInt(item.commentLevel) == 0)
					return '好';
				if (parseInt(item.commentLevel) == 1)
					return '中';
				return '差';
			}
		}, {
			display :'状态',
			name :'status',
			width :'10%',
			type :'int',
			editor : {
				type :'select',
				data :statusData,
				valueField :'status'
			},
			render : function(item) {
				if (parseInt(item.status) == 0)
					return '无效';
				return '<font color="red">有效</font>';
			}
		}, {
			display :'评论时间',
			name :'commentDate',
			width :'14%',
			minWidth :140
		}, {
			display :'评论内容',
			name :'content',
			width :'25%',
			minWidth :140
		}

		],
		url :url,
		pageSize :20,
		enabledEdit :true,
		rownumbers :true,
		pageParmNae :"curNo",
		pagesizeParmName :"curSize",
		onSelectRow :onClickRow

	});
	$("#pageloading").hide();
}
// 根据userId查询明细
function detail(userId) {
	var url = "../commentManage/findUserInfoDetail.shtml?userId=" + userId;
	m = $.ligerDialog.open( {
		url :url,
		title :"评论信息明细",
		name :"myDetail",
		showMax :true,
		showToggle :true,
		showMin :true,
		isResize :true,
		slide :false,
		width :800,
		height :450,
		buttons : [ {
			text :'确定',
			onclick : function(item, dialog) {
				myDetail.save();
			}
		}, {
			text :'取消',
			onclick : function(item, dialog) {
				dialog.close();
			}
		} ]
	});

}

/**
 * 拓展行点击事件radio选中
 */
function onClickRow(rowdata, rowindex, rowDomElement) {
	var $v = $(rowDomElement);
	$v.find(":checkbox").attr("checked", "checked")
}

// 搜索
function doSearch() {
	var status = $("#status").val();
	var commentLevel = $("#commentLevel").val();
	// 查询评论信息列表
	var url = "../commentManage/commentInfoList.shtml?status=" + status + "&commentLevel="
			+ commentLevel;
	findCommentInfoList(url);
}




/*
 * 新增评论信息初始化
 */
function addCommentInit() {
	var url = "../commentManage/commentManageAddInit.shtml";
	m = $.ligerDialog.open( {
		url :url,
		height :400,
		width :600,
		title :'添加评论信息',
		isResize :true,
		top :50
	});
}

/*
 * 新增评论信息
 */
function forCommentSave() {
	if (validData()) {
		$("#form1").attr("onsubmit", "");
		$("#form1").attr("action", "../commentManage/addComment.shtml");
		$("#form1").submit();
	}
}

/*
 * 修改评论信息初始化
 */
function updCommentInit() {
	// 获取选中记录行
	var rowid = grid.getSelecteds();
	var length = rowid.length;
	if (length == 0) {
		$.ligerDialog.error("请选择需要修改的评论！");
		return false;
	}
	if (length > 1) {
		$.ligerDialog.error("只能选择一个评论信息进行修改！");
		return false;
	}
	var commentId = rowid[0].commentId;
	var url = "../commentManage/updCommentInit.shtml?commentId=" + commentId;
	m = $.ligerDialog.open( {
		url :url,
		height :400,
		width :600,
		title :'修改评论',
		isResize :true,
		top :30
	});
}
/*
 * 修改评论信息
 */
function forCommentUpd() {
	if (validData()) {
		$("#form1").attr("onsubmit", "");
		$("#form1").attr("action", "../commentManage/updComment.shtml");
		$("#form1").submit();
	}
}

/*
 * 验证表单数据
 */
function validData() {
	var vipId = $("#vipId").val();
	if (vipId.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("会员ID不能为空！");
		return false;
	}
	var status = $("#status").val();
	if (status.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.error("是否有效不能为空！");
		return false;
	}
	var commentLevel = $("#commentLevel").val();
	if (commentLevel.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("评论等级不能为空！");
		return false;
	}
	return true;
}
function checkComment() {
	return false;
}
/*
 * 评论信息成功添加返回重新刷新列表
 */
function reload(backInfo) {
	$.ligerDialog.success(backInfo);
	grid.loadData(); // 重新加载不查询数据库
	m.close();
}

/*
 * 删除选中的评论信息
 */
function delComment() {
	var commentId = '';
	var rowid = grid.getSelecteds();
	var length = rowid.length;
	if (length == 0) {
		$.ligerDialog.error("请选择要删除的记录！");
		return false;
	} else if (length > 0) {
		if ($.ligerDialog.confirm('确实要删除选择的' + length + '条记录吗', function(yes) {
			if (yes) {
				var url = "../commentManage/delComment.shtml";
				// 获取删除的ID
				for ( var i = 0; i < length; i++) {
					if (i < length - 1) {
						commentId += rowid[i].commentId + ",";
					} else {
						commentId += rowid[i].commentId;
					}
				}
				$.ajax( {
					type :"post",
					url :url,
					data : {
						"commentIds" :commentId
					},
					success : function(data) {
						// 如果删除成功，则刷新页面！
					if (data["message"] == "1") {
						$.ligerDialog.success("删除评论成功！");
					} else if (data["mssage"] == "0") {
						$.ligerDialog.error("删除评论失败！");
					} else {
						$.ligerDialog.error(data["mssage"]);
					}
					// 删除评论信息后查询初始化
					grid.loadData(); // 重新加载不查询数据库
				},
				error : function() {
					$.ligerDialog.error("删除评论失败！");
				}
				});
			}
		}))
			;
	}
}