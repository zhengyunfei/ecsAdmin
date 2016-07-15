	//修改保存之后判断是否修改成功。成功则关闭弹出框，刷新用户列表.
	$(function() {
		var flag = $("#msg").val();
		if (flag == "success") {
			var roleId = $("#roleId").val();
			var roleName = $("#roleName").val();
			parent.reload(roleId, roleName, "upd");
		} else if (flag == "error") {
			$.ligerDialog.error("保存失败");
			return;
		}
		if ($("#isActive").val() == "1") {
			$("#active").attr("checked", "checked");
		} else {
			$("#noactive").attr("checked", "checked");
		}
	});
	
	//修改角色表单提交
	function forSaveUpdRole() {
		if (validateUpdRole()) {
			$("#updRole").submit();
		}
	}
	
	//修改角色表单验证
	function validateAddRole() {
		return true;
	}