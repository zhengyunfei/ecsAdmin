/*
 * 新增联系人信息1
 */
function forAdd() {
	if(validData()){
		$("#form1").attr('onsubmit', '');
		var url=$('#form1').attr('action');
		var data=$('#form1').serialize();
		ajax(url,data);
	}
}
/*
 * 验证表单数据
 */
function validData() {
	var before_xx_minute = $("#before_xx_minute").val();
	if (before_xx_minute.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("上课前xx分钟打卡有效不能为空！");
		return false;
	}
	var after_xx_minute=$("#after_xx_minute").val();
	if(""==after_xx_minute||null==after_xx_minute){
		$.ligerDialog.warn("上课后XX分钟打卡有效不能为空！");
		return;
	}
	return true;
}
function ajax(url,data){
	$.ajax( {
		type :"post",
		url :url,
		data :data,
		async : false,
		dataType : "json",
		beforeSend : function(XMLHttpRequest) {
		},
		timeout:20000,
		success : function(data) {
			if (data["success"]) {
				parent.reload("操作成功！");
			} else {
				parent.reload("操作失败！");
			}

		},
		error : function() {
			$.ligerDialog.error("操作失败！");
		}
	});
}
