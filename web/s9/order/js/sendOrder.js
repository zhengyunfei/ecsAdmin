var grid = null;// 表格对象
var m; // 弹出窗口对象

/*
 * 初始化加载
 */
$( function() {
	// 查询评论信息列表
	var url = "../sendOrder/sendOrderInfoList.shtml";
	findSendOrderInfoList(url);
});
function findSendOrderInfoList(url) {
	var pageSize = 20;
	grid = $("#maingrid").ligerGrid( {
		width :'100%',
		height :'100%',
		headerRowHeight :28,
		rowHeight :26,
		checkbox :true,
		columns : [ {
			display :'订单ID',
			name :'orderId',
			width :'12%'
		}, {
			display :'派单时间',
			name :'operatorDate',
			width :'14%',
			type :'String',
			minWidth :140
		
		}

		, {
			display :'指派给',
			name :'user.userRealName',
			width :'14%',
			minWidth :140
		}, {
			display :'状态',
			name :'status',
			width :'80',
			type :'String',
			align :'center',
			render : function(row) {
				if (row.status == "0") {
					return "未开始";
				} else if (row.status == "1") {
					return "已派单";
				} else if (row.status == "2") {
					return "派单完成";
				} else if (row.status == "3") {
					return "洗车开始";
				} else if (row.status == "4") {
					return "洗车结束";
				}
			}
		}, {
			display :'工作内容',
			name :'content',
			width :'25%',
			minWidth :140
		}, {
			display :'完成时间',
			name :'finishDate',
			width :'14%',
			type :'String',
			minWidth :140
		
		}

		],
		url :url,
		pageSize :20,
		enabledEdit :true,
		rownumbers :true,
		pageParmName :"curNo",
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

/*
 * 新增派单信息
 */
function forSendOrderSave() {
	if (validSendData()) {
		$("#form1").attr("onsubmit", "");
		$("#form1").attr("action", "../sendOrder/sendOrderAdd.shtml");
		$("#form1").submit();
	}
}
/*
 * 验证表单数据
 */
function validSendData() {
	var userId = $("#userId").val();
	if (userId.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.error("分配给不能为空！");
		return false;
	}else{
		return true;
	}

}

// 搜索
function doSearch() {
	var status = $("#status").val();
	var url = "../sendOrder/sendOrderInfoList.shtml?status="+status;
	findSendOrderInfoList(url);
}

/*
 * 修改派单信息初始化
 */
function updSendOrderInit() {
	// 获取选中记录行
	var rowid = grid.getSelecteds();
	var length = rowid.length;
	if (length == 0) {
		$.ligerDialog.error("请选择需要修改的派单！");
		return false;
	}
	if (length > 1) {
		$.ligerDialog.error("只能选择一个派单信息进行修改！");
		return false;
	}
	var id = rowid[0].id;
	var url = "../sendOrder/updSendOrderInit.shtml?id=" + id;
	m = $.ligerDialog.open( {
		url :url,
		height :500,
		width :600,
		title :'修改任务信息',
		isResize :true,
		top :30
	});
}
/*
 * 修改派单信息
 */
function forSendOrderUpd() {
	/* if (validData()) { */
	$("#form1").attr("onsubmit", "");
	$("#form1").attr("action", "../sendOrder/updSendOrder.shtml");
	$("#form1").submit();
	/* } */
}

/*
 * 验证表单数据
 */
function validData() {
	return true;
}
function checkComment() {
	return false;
}
/*
 * 派单信息成功添加返回重新刷新列表
 */
function reload(backInfo) {
	$.ligerDialog.success(backInfo);
	grid.loadData(); // 重新加载不查询数据库
	m.close();
}

/*
 * 删除选中的派单信息
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
						$.ligerDialog.success("删除派单成功！");
					} else if (data["mssage"] == "0") {
						$.ligerDialog.error("删除派单失败！");
					} else {
						$.ligerDialog.error(data["mssage"]);
					}
					// 删除派单信息后查询初始化
					grid.loadData(); // 重新加载不查询数据库
				},
				error : function() {
					$.ligerDialog.error("删除派单失败！");
				}
				});
			}
		}))
			;
	}
}