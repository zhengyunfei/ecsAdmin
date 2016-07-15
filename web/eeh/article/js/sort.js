var m;
  //详细页面返回重新刷新列表
function reload(backMsg){
	$.ligerDialog.success(backMsg,function(){
		parent.reload();
	});
}
function reflush(){
	parent.reflush();
}
/**
* 添加代码信息
*/   
function addCodeInfo(){
	 var pcode= $("#pcode").val();
     var codeSortId=$("#pcode").val();
     var codeId=$("#codeId").val();
    var count=manager.getSelected();
    if(count==''||count==null){
    	$.ligerDialog.error("请选择一行！");return;
    }else{
    	  var url= "../codeManage/forInitSaveCodeInfo.shtml?codeSortId="+codeSortId+"&codeType="+codeType+"&pcode="+pcode+"&codeId="+codeId;	
   		  m=$.ligerDialog.open({ url: url, height: 500,width:550, title:'添加代码信息',isResize: true });
		  if(!m){
			  $.ligerDialog.error("操作失败！");
		  	  return;
		  	}
    }
 
}
/**
* 编辑代码信息
*/   
function editCodeInfo(){
     var codeId=$("#codeId").val();
	if(codeId==''){
    	$.ligerDialog.error("请选择一行！");
		return false;
    }else{
    	var url= "../codeManage/forInitUpdateCodeInfo.shtml?codeId="+codeId;	
	    m=$.ligerDialog.open({ url: url, height: 480,width:550, title:'编辑代码信息',isResize: true });
	    if(!m){
			$.ligerDialog.error("操作失败！");
	  	    return;
	    }
    }
		   
}
/**
 * 将选中的代码信息删除
 * 
 */
function delCodeInfo()
{
     var codeId=$("#codeId").val();
	if(codeId==''){
    	$.ligerDialog.error("请选择一行！");
		return false;
    }
	$.ligerDialog.confirm("确定将选中的记录删除吗？", function (yes){
		var rIds="";
		if(yes){
			var url = "../codeManage/delCodeInfo.shtml";
			$.ajax({
				type : "post",
				url : url,
				data : {
					"codeIds" :codeId
				},
				success : function(data) {
					//如果删除成功，则刷新页面！
					if(data["msg"]=="sucess") {
						reflush();
					}else if(data["msg"]=="error")  {
						$.ligerDialog.error("失败！");
					}else{
						$.ligerDialog.error(data["msg"]);
					}	
				},
				error : function() {
					$.ligerDialog.error("操作失败！");
				}
			});
		}
	});	  
  
}
