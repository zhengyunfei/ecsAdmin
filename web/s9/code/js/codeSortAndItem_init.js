
/***************** 代码项Js zhengYunFei 2013-07-24**************/
var  m; //弹出窗口对象
//初始化表格
function initChildData(url){
 window['g'] = $("#maingrid").ligerGrid({
			height:'98.9%',
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
          { display: '代码标识', name: 'codeId', width: '15%' },
          { display: '代码键', name: 'codeName', width: '15%' },
          { display: '代码值', name: 'codeValue',  width: '15%' },
          { display: '分类标识', name: 'codeType', width: '10%' },
          { display: '显示顺序', name: 'dispSn',  width: '10%' },
          { display: 'content1', name: 'content1',  width: '12%' },
          { display: 'content2', name: 'content2',  width: '12%' },
          { display: '是否有效', name: 'validFlag', width: '11%' ,render: function (row){
				if(row.validFlag == "1"){
					return "是";
				}else{
					return "否";
				}
	        }}
          ], url:url, tree: { columnName: 'codeName' }, pageSize:20,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize"
		 });
      $("#pageloading").hide();
}
/**
* 添加代码信息
*/   
function addCodeInfo(codeSortId,codeType){
	// 获取代码项 的 值  
	var pcode="";
	var rowid = g.getSelecteds();
	if (rowid.length > 1  ){
		$.ligerDialog.warn("请选择一行！");
		return false;
	}
	if (rowid.length == 1  ){
		pcode = rowid[0].codeValue;
	}
  var url= "../codeManage/forInitSaveCodeInfo.shtml?codeSortId="+codeSortId+"&codeType="+codeType+"&pcode="+pcode+"&codeId="+codeSortId;	
    m=$.ligerDialog.open({ url: url, height: 500,width:550, title:'添加代码信息',isResize: true });
  if(!m){
	  $.ligerDialog.error("操作失败！");
  	return;
  	}
}
/**
* 编辑代码信息
*/   
function editCodeInfo(){
	var rowid = g.getSelecteds();
	if (rowid.length != 1  ){
		$.ligerDialog.warn("请选择一行！");
		return false;
	 }
	var codeId = rowid[0].codeId;
	var url= "../codeManage/forInitUpdateCodeInfo.shtml?codeId="+codeId;	
    m=$.ligerDialog.open({ url: url, height: 480,width:550, title:'编辑代码信息',isResize: true });
    if(!m){
		$.ligerDialog.error("操作失败！");
  	    return;
    }	   
}
/**
 * 将选中的代码信息删除
 * 
 */
function delCodeInfo()
{
   var rowid = g.getSelecteds();
	if (rowid.length == 0){
		$.ligerDialog.warn("请至少选择一行！");
		return false;
	}
	$.ligerDialog.confirm("确定将选中的记录删除吗？", function (yes){
		var rIds="";
		if(yes){
			var url = "../codeManage/delCodeInfo.shtml";
			for(var i=0;i<rowid.length;i++){
	            var rId=rowid[i].codeId;
	            rIds=rId+","+rIds;
	        }
			$.ajax({
				type : "post",
				url : url,
				data : {
					"codeIds" :rIds
				},
				success : function(data) {
					//如果删除成功，则刷新页面！
					if(data["msg"]=="sucess") {
						$.ligerDialog.success("删除成功！",function(){
							g.loadData();
						});
						
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



