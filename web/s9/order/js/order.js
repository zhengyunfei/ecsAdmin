var grid = null;// 表格对象
var m; // 弹出窗口对象
var sendOrderStatusData = [ {
	sendOrderStatus :0,
	text :'未派单'
}, {
	sendOrderStatus :1,
	text :'已派单'
}, {
	sendOrderStatus :2,
	text :'派单完成'
} ];
/*
 * 初始化加载
 */
$( function() {
	// 查询信息列表
	var url = "../bsborder/orderInfoList.shtml";
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
				{ display: 'ID', name: 'id', width: '150'},
				{ display: '订单号', name: 'orderId', width: '150'},
				{
					display :'总价',
					name :'price',
					width :'80'
				}, {
					display :'预约时间',
					name :'washTime',
					width :'160',
					type: 'date'
						
				},
				{
					display :'下单时间',
					name :'createTime',
					width :'160',
					type: 'date'
						
				}
				, {
					display :'订单状态',
					name :'orderStatus',
					width :'80',
					type :'String',
					align :'center',
					render : function(row) {
						if (row.orderStatus == "-1") {
							return "未支付";
						} else if (row.orderStatus == "1") {
							return "支付完成";
						} else if (row.orderStatus == "2") {
							return "用户取消";
						} else if (row.orderStatus == "0") {
							return "支付失败";
						}
					}
				}, {
					display :'地址',
					name :'address',
					width :'150'
				},
				 {
					display :'备注',
					name :'discription',
					width :'150'
				}
				, {
					display :'电话',
					name :'mobile',
					width :'100'
				}, {
					display :'车牌号',
					name :'carNum',
					width :'100'
				}, {
					display :'称呼',
					name :'car.name',
					width :'250'
				}, {
					display :'派单状态',
					name :'sendOrderStatus',
					width :'80',
					type :'String',
					align :'center',
					render : function(row) {
						if (row.sendOrderStatus == "0") {
							return "未派单";
						} else if (row.sendOrderStatus == "1") {
							return "已派单";
						} else if (row.sendOrderStatus == "2") {
							return "派单完成";
						}else if (row.sendOrderStatus == "3") {
							return "洗车开始";
						}else if (row.sendOrderStatus == "4") {
							return "洗车结束";
						}
						

					}
				},
				{
					display :'接单者',
					name :'sendOrderToName',
					width :'250'
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
 * 派单初始化
 */
function sendOrderAddInit() {
	// 获取选中记录行
	var rowid = grid.getSelecteds();
	var length = rowid.length;
	if (length == 0) {
		$.ligerDialog.error("请选择需要派单的订单！");
		return false;
	}
	if (length > 1) {
		$.ligerDialog.error("只能选择一条未派单的订单信息进行派单！");
		return false;
	}
	if(rowid[0].sendOrderStatus!="0"){
		$.ligerDialog.error("只能选择一条未派单的订单信息进行派单！");
		return false;
	}else{
	var orderId = rowid[0].id;
	var url = "../sendOrder/sendOrderAddInit.shtml?orderId=" + orderId;
	m = $.ligerDialog.open( {
		url :url,
		height :400,
		width :600,
		title :'派单',
		showMax:false,showToggle:true,showMin:true
	});
	}
}



/*
 * 新增联系人信息1
 */
function forSave() {
	// if(validData()){
	var productIdval = $("#productIdval").val();
	$("#productId").val(productIdval);
	var orderStatusval = $("#orderStatusval").val();
	$("#status").val(orderStatusval);
	alert(orderStatusval);
	$("#form1").attr('onsubmit', '');
	$("#form1").submit();
	// }
}


/*
 * 机构信息成功添加返回重新刷新列表
 */
function reload(backInfo) {
	$.ligerDialog.success(backInfo);
	grid.loadData(); // 重新加载不查询数据库
	m.close();
}

function updOrderInit() {
	// 获取选中记录行
	var rowid = grid.getSelecteds();
	var length = rowid.length;
	if (length == 0) {
		$.ligerDialog.error("请选择需要修改的联系人！");
		return false;
	}
	if (length > 1) {
		$.ligerDialog.error("只能选择一个联系人信息进行修改！");
		return false;
	}
	var orderId = rowid[0].orderId;
	var url = "/order/updOrderInit.shtml?orderId=" + orderId;
	m = $.ligerDialog.open( {
		url :url,
		height :450,
		width :500,
		title :'修改订单',
		isResize :true,
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
 * 修改机构信息
 */
function forOrderUpd() {
	var orderStatusval = $("#orderStatusval").val();
	$("#audit").val(orderStatusval);
	$("#form1").attr('onsubmit', '');
	$("#form1").submit();
}
/*
 * 验证表单数据
 */
function validData() {
	var orderName = $("#orderName").val();
	if (orderName.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("联系人名不能为空！");
		return false;
	}
	return true;
}