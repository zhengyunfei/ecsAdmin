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
		var proType = $("#proType").val();
			if(proType.replace(/(^\s*)|(\s*$)/,"").length==0){
				$.ligerDialog.warn("请选择系统类型！");
				return false;
			}
		 var proCode = $("#proCode").val();
			if(proCode.replace(/(^\s*)|(\s*$)/,"").length==0){
				$.ligerDialog.warn("请选择项目编码！");
				return false;
			}
		var confDes = $("#confDes").val();
			if(confDes.replace(/(^\s*)|(\s*$)/,"").length==0){
				$.ligerDialog.warn("项目分类描述不能为空！");
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