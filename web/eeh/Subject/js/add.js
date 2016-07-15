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
	var subjectClass = $("#subjectClass").val();
	if (subjectClass.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("科目分类不能为空！");
		return false;
	}
	var name=$("#name").val();
	if(""==name||null==name){
		$.ligerDialog.warn("科目名称不能为空！");
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
