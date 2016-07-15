//保存友情链接分组信息
function saveAddGroup(){
	if(validate()){
		$("#saveAdd").submit();
	}
}
//修改友情链接分组信息
function updSave(){
	if(validate()){
		$("#saveUpd").submit();
	}
}
//校验
function validate(){
	var typeName = $("#typeName").val();
	var typeDesc = $("#typeDesc").val();
	if(typeName.replace(/(^\s*)|(\s*$)/,"").length==0){
		$.ligerDialog.warn("分类名称不能为空！");
			return false;
	}
	if(typeDesc.replace(/(^\s*)|(\s*$)/,"").length==0){
		$.ligerDialog.warn("分类描述不能为空！");
			return false;
	}
	return true;
}