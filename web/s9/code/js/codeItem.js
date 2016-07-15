	
/*****  代码项JS操作 lastDate 2013-07-24 by zhengYunFei ******************************************/
	/**
	*提交保存
	*/
	function forSave(){
		//var content1=$("#contentSelect").find('option:selected').;
		if(validData()){
			$("#theForm").submit();
		}
	}
	/***
		*验证表单数据
	*/
	function validData(){
		var codeSortName = $("#codeName").val();
			if(codeSortName.replace(/(^\s*)|(\s*$)/,"").length==0){
				$.ligerDialog.warn("代码名称不能为空！");
				return false;
			}
		   var dispSn = $("#dispSn").val();
			if(dispSn.replace(/(^\s*)|(\s*$)/,"").length==0 || isNaN(dispSn)){
				$.ligerDialog.warn("显示顺序不能为空或格式不正确！");
				return false;
			}
			return true;
}