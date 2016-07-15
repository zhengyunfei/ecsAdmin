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
	var name = $("#name").val();
	if (name.replace(/(^\s*)|(\s*$)/, "").length == 0) {
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
