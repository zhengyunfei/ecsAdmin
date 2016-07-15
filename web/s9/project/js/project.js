var grid = null;// 表格对象
var m; // 弹出窗口对象
var sendOrderStatusData = [ {
	sendOrderStatus :0,
	text :'否'
}, {
	sendOrderStatus :1,
	text :'是'
}];
/*
 * 初始化加载
 */
$( function() {
	// 查询信息列表
	var url = "../serviceProject/serviceProjectList.shtml";
	findOrderInfoList(url);
});
function findOrderInfoList(url) {
	var pageSize = 20;
	grid = $("#maingrid").ligerGrid(
			{
				width :'99.6%',
				height :'100%',
				headerRowHeight :28,
				rowHeight :26,
				checkbox :true,
				columns : [
				{ display: '名称', name: 'name', width: '150'},
				{
					display :'价格',
					name :'price',
					width :'80'
				}, {
					display :'原价',
					name :'orignPrice',
					width :'160',
					type: 'text'
						
				},  {
					display :'备注',
					name :'remark',
					width :'350'
				},  {
					display :'是否为主服务项目',
					name :'isMain',
					width :'100',render: function (row){
						if(row.isMain == "1"){
							return "是";
						}else{
							return "否";
						}
			        }
				}

				],
				onAfterShowData : function() {
					$(".l-grid-row-cell-inner").css("height", "auto"); // 单元格高度自动化，撑开
				var i = 0;
				$("tr", ".l-grid2", "#maingrid").each(
						function() {
							$($("tr", ".l-grid1", "#maingrid")[i]).css(
									"height", $(this).height()); // 2个表格的tr高度一致
							i++;
						});
			},
			url :url,
			pageSize :20,
			rownumbers :true,
			pageParmName :"curNo",
			pagesizeParmName :"curSize"
			});
	$("#pageloading").hide();
}




/*
 * 机构信息成功添加返回重新刷新列表
 */
function reload(backInfo) {
	$.ligerDialog.success(backInfo);
	grid.loadData(); // 重新加载不查询数据库
	m.close();
}

function forUpdateInit() {
	// 获取选中记录行
	var rowid = grid.getSelecteds();
	var length = rowid.length;
	if (length == 0) {
		$.ligerDialog.error("请选择需要修改的信息！");
		return false;
	}
	if (length > 1) {
		$.ligerDialog.error("只能选择一个信息进行修改！");
		return false;
	}
	var id = rowid[0].id;
	var url = "../serviceProject/updOrderInit.shtml?id=" + id;
	m = $.ligerDialog.open( {
		url :url,
		height :450,
		width :520,
		title :'修改',
		isResize :true,
		showMax: true, 
		showMin:true,
		top :50
	});
}
function forAddInit() {
	
	var url = "../serviceProject/addProjectForInitPage.shtml";
	m = $.ligerDialog.open( {
		url :url,
		height :450,
		width :520,
		title :'新增',
		isResize :true,
		showMax: true, 
		showMin:true,
		top :50
	});
}
/*
 * 查询机构信息
 */
function doSearch() {
	var orderNo = $("#orderNo").val();
	var sendOrderStatus = $("#sendOrderStatus").val();
	var status = $("#status").val();
	var url = "../bsborder/orderInfoList.shtml?status="+status+"&orderNo=" + orderNo + "&sendOrderStatus="
			+ sendOrderStatus;
	findOrderInfoList(url);
}

/*
 * 验证表单数据
 */
function validData() {
	var name = $("#name").val();
	if (name==''||name==null) {
		$.ligerDialog.warn("服务项目名称不能为空！");
		return false;
	}
	var price=$("#price").val();
	var orignPrice=$("#orignPrice").val();
	var remark=$("#remark").val();
	if(""==price||null==price){
		$.ligerDialog.warn("价格不能为空！");
		return;
	}
	if(""==orignPrice||null==orignPrice){
		$.ligerDialog.warn("原价不能为空！");
		return;
	}
	if(""==remark||null==remark){
		$.ligerDialog.warn("简介不能为空！");
		return;
	}
	return true;
}

function forDelete() {
	var id = '';
	var rowid = grid.getSelecteds();
	var length = rowid.length;
	if (length == 0) {
		$.ligerDialog.error("请选择要删除的记录！");
		return false;
	} else if (length > 0) {
		if ($.ligerDialog.confirm('确实要删除选择的' + length + '条记录吗', function(yes) {
			if (yes) {
				var url = "../serviceProject/delProject.shtml";
				// 获取删除的ID
				for (var i = 0; i < length;i++) {
					if (i < length - 1) {
						id += rowid[i].id + ",";
					} else {
						id += rowid[i].id;
					}
				}
				$.ajax( {
					type :"post",
					url :url,
					data : {
						"ids" :id
					},
					success : function(data) {
						// 如果删除成功，则刷新页面！
					if (data["message"] == "1") {
						$.ligerDialog.success("删除成功！");
					} else if (data["mssage"] == "0") {
						$.ligerDialog.error("删除失败！");
					} else {
						$.ligerDialog.error(data["mssage"]);
					}
					// 删除评论信息后查询初始化
					grid.loadData(); // 重新加载不查询数据库
				},
				error : function() {
					$.ligerDialog.error("删除失败！");
				}
				});
			}
		}))
			;
	}
}
