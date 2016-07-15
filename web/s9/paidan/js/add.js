function save(){
	 if(validateForm()){
		 	 var content=UE.getEditor('ckeditor').getContent();
		     $("#content").val(content);
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
	var shortName=$('#shortName').val();
	var title=$('#title').val();
	var content=UE.getEditor('ckeditor').getContent();
	var area=$('#area').val();
	var sendStartTime=$('#sendStartTime').val();
	var sendEndTime=$('#sendEndTime').val();
	var startTime=$('#startTime').val();
	var endTime=$('#expirationDate').val();
	if(name==''||null==name){
		$.ligerDialog.error('邀请名不能为空');flag=false;return flag;
	}
	if(shortName==''||null==shortName){
		$.ligerDialog.error('简称不能为空');flag=false;return flag;
	}
	if(title==''||null==title){
		$.ligerDialog.error('二级标题不能为空');flag=false;return flag;
	}
	if(content==''||null==content){
		$.ligerDialog.error('内容不能为空');flag=false;return flag;
	}
	if(area==''||null==area){
		$.ligerDialog.error('地点不能为空');flag=false;return flag;
	}
	if(sendStartTime==''||null==sendStartTime){
		$.ligerDialog.error('下发开始时间不能为空');flag=false;return flag;
	}
	if(sendEndTime==''||null==sendEndTime){
		$.ligerDialog.error('下发结束时间不能为空');flag=false;return flag;
	}
	if(sendEndTime<sendStartTime){
		$.ligerDialog.error('下发结束时间不能小于下发开始时间');flag=false;return flag;
	}
	if(startTime==''||null==startTime){
		$.ligerDialog.error('开始时间不能为空');flag=false;return flag;
	}
	return flag;
}