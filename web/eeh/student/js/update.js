/*
 * 新增联系人信息1
 */
function forUpdate() {
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
	var classId = $("#classId").val();
	if (classId.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("班级名称不能为空！");
		return false;
	}
	var name=$("#name").val();
	if(""==name||null==name){
		$.ligerDialog.warn("姓名不能为空！");
		return;
	}
	/*var sex=$("#sex").val();
	if(""==sex||null==sex){
		$.ligerDialog.warn("性别不能为空！");
		return;
	}*/
/*	var password=$("#password").val();
	var password2=$("#password2").val();
	if(password!=password2){
		$.ligerDialog.warn("两次输入的密码不一致！");
		return;
	}else if(""!=password&&null!=password){
		password=hex_md5(password);
		$("#password").val(password);
	}*/
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
