$(document).ready(function(){
	$('#tbId').change(function(){
		var floor=$(this).children('option:selected').attr('data-floor');
		var p1=$(this).children('option:selected').val();//这就是selected的值
		if(''!=floor&&null!=floor)	{
			$("#floors").empty();
			for(var i=1;i<=floor;i++){
				$("#floors").append('<option value="'+i+'">'+i+'层</option>');
			}
		}

	})
})
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
	var tbId = $("#tbId").val();
	if (tbId.replace(/(^\s*)|(\s*$)/, "").length == 0) {
		$.ligerDialog.warn("教学楼名称不能为空！");
		return false;
	}
	var floors=$("#floors").val();
	if(""==floors||null==floors){
		$.ligerDialog.warn("楼层数不能为空！");
		return;
	}
	var name=$("#name").val();
	if(""==name||null==name){
		$.ligerDialog.warn("所属班级不能为空！");
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
