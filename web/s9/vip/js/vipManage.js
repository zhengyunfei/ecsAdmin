var grid = null;// 表格对象
var m; // 弹出窗口对象

/*
 * 初始化加载
 */
$( function() {
	// 查询会员信息列表
	var url = "../vipManage/vipInfoList.shtml";
	findVipInfoList(url);
});
function findVipInfoList(url) {
	var pageSize = 20;
	grid = $("#maingrid").ligerGrid(
			{
				width :'100%',
				height :'100%',
				headerRowHeight :28,
				rowHeight :26,
				checkbox :true,
				columns : [
				       	 {
							display :'手机号',
							name :'phoneNum',
							width :'33.33%',
							minWidth :130
						}, {
							display :'注册时间',
							name :'createTime',
							width :'33.33%',
							minWidth :130
						},{
						display :'车牌号',
						name :'account',
						width :'33.33%',
						minWidth :130
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
	var url= "../vipManage/updUserInit.shtml?userId="+userId;
	m = $.ligerDialog.open({ url: url, height: 480,width:600, title:'修改用户',isResize: true ,top:50});
}
/*
 * 修改用户信息
 */
function forUserUpd(){
	if(validData()){
			$("#form1").attr("onsubmit","");
			$("#form1").attr("action","../vipManage/updUser.shtml");
			$("#form1").submit();
		//}
	}
}
/*
 *验证表单数据
 */
function validData(){
	/*var userName = $("#userName").val();
	if(userName.replace(/(^\s*)|(\s*$)/,"").length==0){
		$.ligerDialog.error("用户名不能为空！");
		return false;

	}
	var mobile = $("#mobile").val();
	if(mobile.replace(/(^\s*)|(\s*$)/,"").length==0){
		$.ligerDialog.warn("手机号码不能为空！");
		return false;
	}*/
	return true;
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
	var  mobile= $("#phoneNum").val();
	var vipNo = $("#vipNo").val();
	// 查询会员信息列表
	var url = "../vipManage/vipInfoList.shtml?mobile=" + mobile ;
			
	findVipInfoList(url);
}
// 重置密码
function resetPassword() {
	var userId = '';
	var rows = grid.getCheckedRows();
	var length = rows.length;
	if (length < 1) {
		$.ligerDialog.error("请至少选择一条会员信息");
		return false;
	} else if (length > 1) {
		$.ligerDialog.error("只能选择一个会员进行重置！");
		return false;
	}
	var rows = grid.getCheckedRows();
	$(rows).each( function(index) {
		if (index == rows.length - 1) {
			userId += this.userId;
		} else {
			userId += this.userId + ",";
		}

	});
	var url = "../vipManage/initPwdReset.shtml?userId=" + userId;
	m = $.ligerDialog.open( {
		url :url,
		height :220,
		width :550,
		title :'密码重置',
		isResize :true,
		top :50
	});
}

// 保存密码重置
function saveResetPwd() {
	var newpwd = $("#newPwd").val();
	var surepwd = $("#newPwdAgain").val();
	if (validate(newpwd, surepwd)) {
		$("#userPassword").val(hex_md5($("#newPwd").val()));
		var url = "../vipManage/updResetPwd.shtml";
		$("#saveUpdResetPwd").attr('action', url);
		$("#saveUpdResetPwd").submit();
	}
}
// 密码校验
function validate(newpwd, surepwd) {
	if (newpwd == null) {
		$.ligerDialog.error("请输入您的新密码");
		return false;
	} else if (newpwd.length > 16 || newpwd.length < 6) {
		$.ligerDialog.error("密码应在6-16位之间");
		return false;
	} else if (surepwd == null) {
		$.ligerDialog.error("请确认您的新密码");
		return false;
	} else if (newpwd != surepwd) {
		$.ligerDialog.error("两次输入密码不一致");
		return false;
	}
	return true;
}
// 密码校验
function updPwdValidate(newpwd, surepwd) {
	if (newpwd == null) {
		$.ligerDialog.error("请输入您的新密码");
		return false;
	} else if (surepwd == null) {
		$.ligerDialog.error("请确认您的新密码");
		return false;
	} else if (newpwd != surepwd) {
		$.ligerDialog.error("两次输入密码不一致");
		return false;
	}
	return true;
}
/*
 * 新增会员信息初始化
 */
function addUserInit() {
	var url = "../vipManage/vipManageAddInit.shtml";
	m = $.ligerDialog.open( {
		url :url,
		height :480,
		width :520,
		title :'添加会员信息',
		isResize :true,
		top :50
	});
}

/*
 * 新增会员信息
 */
function forVipSave() {
	var newpwd = $("#vipPwd").val();
	var surepwd = $("#vipPwdAgain").val();
	if (validData()) {
		if (validate(newpwd, surepwd)) {
			var pwd = hex_md5(newpwd);
			$("#vipPwd").val(pwd);
			$("#form1").attr("onsubmit", "");
			$("#form1").attr("action", "../vipManage/addVip.shtml");
			$("#form1").submit();
		}
	}
}
/*
 * 修改会员信息初始化
 */
function updVipInit() {
	// 获取选中记录行
	var rowid = grid.getSelecteds();
	var length = rowid.length;
	if (length == 0) {
		$.ligerDialog.error("请选择需要修改的会员！");
		return false;
	}
	if (length > 1) {
		$.ligerDialog.error("只能选择一个会员信息进行修改！");
		return false;
	}
	var vipId = rowid[0].vipId;
	var url = "../vipManage/updVipInit.shtml?vipId=" + vipId;
	m = $.ligerDialog.open( {
		url :url,
		height :480,
		width :520,
		title :'修改会员',
		isResize :true,
		top :50
	});
}
/*
 * 修改会员信息
 */
function forUserUpd() {
	if (validData()) {
		/*var newpwd = $("#vipPwd").val();
		var surepwd = $("#vipPwdAgain").val();
		if (updPwdValidate(newpwd, surepwd)) {*/
			//var pwd = hex_md5(newpwd);
			//$("#vipPwd").val(pwd);
			$("#form1").attr("onsubmit", "");
			$("#form1").attr("action", "../vipManage/updUser.shtml");
			$("#form1").submit();
		//}
	}
}
/*
 * 验证表单数据
 */
function validData() {
	/*var vipNo = $("#vipNo").val();
	if (vipNo.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("会员编号不能为空！");
		return false;
	}
	var vipName = $("#vipName").val();
	if (vipName.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.error("会员名称不能为空！");
		return false;
	}
	var tel = $("#tel").val();
	if (tel.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("手机号码不能为空！");
		return false;
	}
	var licePlateNo = $("#licePlateNo").val();
	if (licePlateNo.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("车牌号不能为空！");
		return false;
	}
	*/
	return true;
}
function check() {
	return false;
}
/*
 * 会员信息成功添加返回重新刷新列表
 */
function reload(backInfo) {
	$.ligerDialog.success(backInfo);
	grid.loadData(); // 重新加载不查询数据库
	m.close();
}

/*
 * 删除选中的会员信息
 */
function delUser() {
	var vipId = '';
	var rowid=grid.getSelecteds();
		var length=rowid.length;

	if (length == 0) {
		$.ligerDialog.error("请选择要删除的记录！");
		return false;
	} else if (length > 0) {
		if ($.ligerDialog.confirm('确实要删除选择的' + length + '条记录吗', function(yes) {
			if (yes) {
				var url = "../vipManage/delUser.shtml";
				//获取删除的ID
				for(var i=0;i<length;i++){
					if(i<length-1){
						vipId+=rowid[i].id+",";
					}else{
						vipId+=rowid[i].id;
					}
				}
				$.ajax( {
					type :"post",
					url :url,
					data : {
						"userIds" :vipId
					},
					success : function(data) {
						// 如果删除成功，则刷新页面！
					if (data["message"] == "1") {
						$.ligerDialog.success("删除会员成功！");
					} else if (data["mssage"] == "0") {
						$.ligerDialog.error("删除会员失败！");
					} else {
						$.ligerDialog.error(data["mssage"]);
					}
					// 删除会员信息后查询初始化
					grid.loadData(); // 重新加载不查询数据库
				},
				error : function() {
					$.ligerDialog.error("删除会员失败！");
				}
				});
			}
		}))
			;
	}
}