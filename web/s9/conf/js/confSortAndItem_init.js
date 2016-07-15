
/***************** 代码项Js zhengYunFei 2013-07-24**************/
var  m; //弹出窗口对象
//初始化表格
function initChildData(url){
 window['g'] = $("#maingrid").ligerGrid({
			height:'90%',
			width:'99.7%',
			headerRowHeight:28, 
			onAfterShowData:function()
	           {                                
	               $(".l-grid-row-cell-inner").css("height","auto"); //单元格高度自动化，撑开
	               var i=0;
	               $("tr",".l-grid2","#maingrid").each(function ()
	               {                                            
	                   $($("tr",".l-grid1","#maingrid")).css("height",$(this).height()); //2个表格的tr高度一致
	                   i++;                        
	               });                                            
	           },
			rowHeight:26,
			checkbox: true,
    		 columns: [
          { display: '标识', name: 'valueId', width: '9%' },
          { display: '配置项', name: 'confKey', width: '16%' },
          { display: '配置项值', name: 'confValue',  width: '14%' },
          { display: '分类标识', name: 'sortCode', width: '8%' },
          { display: '描述', name: 'confDes',  width: '22%' },
          { display: '备注', name: 'remark', width: '25%' }
          ], url:url, pageSize:20,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize"
		 });
      $("#pageloading").hide();
}
/**
* 添加项目配置参数信息
*/   
function add(sortCode){
  var url= "../confManage/forInitSaveConfValue.shtml?sortCode="+sortCode;	
    m=$.ligerDialog.open({ url: url, height: 450,width:550, title:'添加配置参数信息',isResize: true });
  if(!m){
	  $.ligerDialog.error("添加项目配置参数信息失败！");
  	return;
  	}
}
/**
* 编辑项目配置参数信息
*/   
function edit(){
	var rowid = g.getSelecteds();
	if (rowid.length != 1  ){
		$.ligerDialog.warn("请选择一行！");
		return false;
	}
	var valueId = rowid[0].valueId
	var url= "../confManage/forInitUpdateConfValue.shtml?valueId="+valueId;	
    m=$.ligerDialog.open({ url: url, height: 450,width:550, title:'编辑配置参数信息',isResize: true });
   if(!m){
	   $.ligerDialog.error("编辑配置参数信息失败！");
  	return;
  }	   
}
/**
 * 将选中的项目配置参数信息删除
 * 
 */
function del()
{
   var rowid = g.getSelecteds();
	if (rowid.length == 0){
		$.ligerDialog.warn("请至少选择一行！");
		return false;
	}
	$.ligerDialog.confirm("确定将选中的记录删除吗？", function (yes){
		var rIds="";
		if(yes){
			var url = "../confManage/delConfValue.shtml";
			for(var i=0;i<rowid.length;i++){
	            var rId=rowid[i].valueId;
	            rIds=rId+","+rIds;
	        }
			$.ajax({
				type : "post",
				url : url,
				data : {
					"valueIds" :rIds
				},
				success : function(data) {
					//如果删除成功，则刷新页面！
					if(data["msg"]=="sucess") {
						$.ligerDialog.alert("删除成功！");
						 g.loadData(); //重新加载不查询数据库
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
//详细页面返回重新刷新列表
function reload(backMsg){
	$.ligerDialog.success(backMsg);
	g.loadData(); //重新加载不查询数据库
	m.close();
}



