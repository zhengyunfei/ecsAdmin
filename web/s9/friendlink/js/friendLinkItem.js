var grid=null;
//初始化表格
function initChildData(url){
	grid = $("#maingrid").ligerGrid({
		height:'95%',
		width:'99.7%',
		headerRowHeight:28, 
		onAfterShowData:function() {                                
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
		alternatingRow: true,
		columns: [
          { display: '链接分类ID', name: 'typeId',align: 'center',width: '0%',hide:true},
          { display: '链接名称', name: 'fsiteName',align: 'center', width: '8%' },
          { display: '链接地址', name: 'fsiteUrl',align: 'center', width: '10%' },
          { display: '链接图标', name: 'logoUrl',align: 'center', width: '10%' },
          { display: '是否有效', name: 'validFlag',align: 'center', width: '10%',render: function (row){
				if(row.validFlag == "1"){
					return "是";
				}else{
					return "否";
				}
	        }},
          { display: '维护时间', name: 'addDate',align: 'center', width: '10%' },
          { display: '结束时间', name: 'effDate',align: 'center', width: '10%' },
          { display: '分类名称', name: 'typeName',align: 'center', width: '10%' },
          { display: '操作人', name: 'operName',align: 'center', width: '10%' },
          { display: '备注', name: 'remark',align: 'center', width: '10%' }
          ], url:url, tree: { columnName: 'typeName' }, pageSize:20,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize"
	});
  	$("#pageloading").hide();
}
//添加友情链接信息
function addFriendLinkInfo(typeId){
	var url= "../friendLinkManage/initSaveFriendLinkInfo?typeId="+typeId;
    m=$.ligerDialog.open({ url: url, height: 420,width:550, title:'添加友情链接信息',isResize: true });
    if(!m){
	  $.ligerDialog.error("操作失败！");
  	return;
  	}
}
function addInfoSave(){
	$("#saveAddInfo").submit();
}
//修改友情链接信息
function editFriendLinkInfo(typeId){
	var rowid = grid.getSelecteds();
	if (rowid.length != 1  ){
		$.ligerDialog.warn("请选择一行！");
		return false;
	}
	var id = rowid[0].id
	var url= "../friendLinkManage/initUpdFriendLinkInfo?id="+id+"&typeId="+typeId;
	m=$.ligerDialog.open({ url: url, height: 420,width:550, title:'编辑友情链接信息',isResize: true });
	if(!m){
		$.ligerDialog.error("操作失败！");
		return;
	}	   
}
function updInfoSave(){
	$("#saveUpdInfo").submit();
}
//删除友情链接信息
function delFriendLinkInfo(){
	var rowid = grid.getSelecteds();
	if (rowid.length == 0){
		$.ligerDialog.warn("请至少选择一行！");
		return false;
	}
	$.ligerDialog.confirm("确定将选中的记录删除吗？", function (yes){
		var rIds="";
		if(yes){
			var url = "../friendLinkManage/delFriendLinkInfo";
			for(var i=0;i<rowid.length;i++){
	            var rId=rowid[i].typeId;
	            rIds=rId+","+rIds;
	        }
			$.ajax({
				type : "post",
				url : url,
				data : {
					"ids" :rIds
				},
				success : function(data) {
					//如果删除成功，则刷新页面！
					if(data["msg"]=="sucess") {
						$.ligerDialog.alert("删除成功！");
						
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
function reload(flag){
	if(flag=='sucess'){
		$.ligerDialog.success("保存成功");
		grid.loadData(); //重新加载不查询数据库
		m.close();
	}else if(flag=='error'){
		$.ligerDialog.error("保存失败");
		return;
	}
}