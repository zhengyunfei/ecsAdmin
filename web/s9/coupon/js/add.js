function save(){
	 if(validateForm()){
			var url=$("#form1").attr('action');
			var data=$('#form1').serialize();
			$.ajax({
				url:url,
				dateType:'json',
				type:'post',
				data:data,
				success:function(result){
					if(result.success){
						$.ligerDialog.success("保存成功",function(){
							parent.reload();
						});
					}else{
						$.ligerDialog.error("保存失败");
					}
				},error:function(result){
						$.ligerDialog.error("服务器也有duang的时候，请您谅解一下好吗?");
				}
			});
	 }

}
//点击缩略图
  function fun(){
		var url="../imageServer.shtml?type=image";
		dialog=$.ligerDialog.open({ 
		height: 600,
		width:960,
		url: url,
		title:'图片服务器'
	 	});
	}
   //关闭弹出窗口，并为tex框赋值
	function selectPic(obj,imageType){
		$("#attachmentUrl").val(obj);
		$("#shortImage").val(obj);
		$("#imgView").attr('src',obj);
		$("#imgView").show();
		dialog.close();
	}
 function clickPic(obj){
	 $("#thumbnail").val(obj);
		dialog.close();
 }
 //验证表单
function validateForm(){
	var flag=true;
	var name=$('#name').val();
	var price=$('#price').val();
	var oprice=$('#oprice').val();
	var status=$('#status').val();
	var number=$('#number').val();
	if(name==''||null==name){
		$.ligerDialog.error('洗车券名称不能为空');flag=false;return flag;
	}
	if(price==''||null==price){
		$.ligerDialog.error('价格不能为空');flag=false;return flag;
	}
	if(oprice==''||null==oprice){
		$.ligerDialog.error('原价不能为空');flag=false;return flag;
	}
	if(number==''||null==number){
		$.ligerDialog.error('数量不能为空');flag=false;return flag;
	}
	if(status==''||null==status){
		$.ligerDialog.error('有效标识不能为空');flag=false;return flag;
	}
	return flag;
}