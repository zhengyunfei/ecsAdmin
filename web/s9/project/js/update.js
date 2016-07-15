function forUpd() {
	$("#form1").attr('onsubmit', '');
	var url=$('#form1').attr('action');
	var data=$('#form1').serialize();
	ajax(url,data);
}
function ajax(url,data){
	$.ajax( {
					type :"post",
					url :url,
					data :data,
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
