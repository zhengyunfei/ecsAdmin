	
/*****  项目配置管理参数项JS操作 lastDate 2013-07-24 by zhengYunFei ******************************************/
	/**
	*提交保存
	*/
	function forSave(){
		if(validData()){
			$("#theForm").submit();
		}
	}
	/***
		*验证表单数据
	*/
	function validData(){
		var confKey = $("#confKey").val();
			if(confKey.replace(/(^\s*)|(\s*$)/,"").length==0){
				$.ligerDialog.warn("配置项不能为空！");
				return false;
			}
		var confValue = $("#confValue").val();
			if(confValue.replace(/(^\s*)|(\s*$)/,"").length==0){
				$.ligerDialog.warn("配置项值不能为空！");
				return false;
				
			}
		var confDes = $("#confDes").val();
			if(confDes.replace(/(^\s*)|(\s*$)/,"").length==0){
				$.ligerDialog.warn("描述不能为空！");
				return false;
			}
		var remark = $("#remark").val();
		var length=remark.replace(/[^\x00-\xff]/g, "**").length;//
			if(length>256){
				$.ligerDialog.warn("备注长度不能大于128个英文字符或者多于64个中文字符！");
				return false;
			}	
				return true;
   }