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
		$.ligerDialog.warn("班级名称不能为空！");
		return false;
	}
	var gradeName=$("#gradeName").val();
	if(""==gradeName||null==gradeName){
		$.ligerDialog.warn("请先选择年级！");
		return;
	}

	var teacherName=$("#teacherName").val();
	if(""==teacherName||null==teacherName){
		$.ligerDialog.warn("班主任不能为空！");
		return;
	}
	var gradenum=$("#gradenum").val();
	if(""==gradenum||null==gradenum){
		$.ligerDialog.warn("班级人数不能为空！");
		return;
	}
	var classCadre=$("#classCadre").val();
	if(""==classCadre||null==classCadre){
		$.ligerDialog.warn("班团干部不能为空！");
		return;
	}
   /* var studentGridData=grid.rows;
	if(""==studentGridData||null==studentGridData){
		$.ligerDialog.warn("请先导入学生数据！");
		return;
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

