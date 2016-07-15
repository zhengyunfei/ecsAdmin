	/**
	*提交保存
	*/
	function forSave(){
	  validData();
	}
	/***
	*验证表单数据
	*/
	function validData(){
		$("#theForm").attr("onsubmit","");
		var codeSortName = $("#codeSortName").val();
		if(codeSortName.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("分类名称不能为空！");
				return false;
		}
		var submitType=$("#submitType").val();
		//查询分类名称是否已经存在
		var url="../codeManage/findCodeSortNameIsExist.shtml";
		$.ajax( {
			url : url,
			type : "POST",
			async : false,
			data:{"codeName":codeSortName},
			dataType : "json",
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(result) {
				var count=0;
				if(submitType=='add'){
					count=0;
				}else if(submitType=='update'){
					count=1;
				}
				if(result>count){
					$.ligerDialog.error("分类名称已经存在！");
					return false;
				}else{
					var remark = $("#remark").val();
				    var length=remark.replace(/[^\x00-\xff]/g, "**").length;//
					if(length>256){
						$.ligerDialog.warn("备注长度不能大于128个英文字符或者多于64个中文字符！");
						return false;
					}else{
						$("#theForm").submit();
					}
				}
			},error:function(){
				
			}});
	}
	
	function check(){
		return false;
	}


	
