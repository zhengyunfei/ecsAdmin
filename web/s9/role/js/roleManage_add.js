	//判断添加角色是否成功。成功则关闭弹出框，失败提示保存失败。
	$(function() {
		var flag = $("#msg").val();
		if (flag == "success") {
			var roleId = $("#roleId").val();
			var roleName = $("#roleName").val();
			parent.reload(roleId, roleName, "add");
		} else if (flag == "error") {
			$.ligerDialog.error("保存失败");
			return;
		}
	});
	
	//添加角色表单提交单击事件
	function forSaveAddRole() {
		if (validateAddRole()) {
			$("#addRole").submit();
		}
	}
	
	//添加角色表单验证
	function validateAddRole() {
		return true;
	}