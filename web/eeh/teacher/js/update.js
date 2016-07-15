/*
 * 新增联系人信息1
 */
function forUpdate() {
	if(validData()){
		$("#form1").attr('onsubmit', '');
		var url=$('#form1').attr('action');
		var data=$('#form1').serialize();
		ajax(url,data);
	}
}
