var  m; //弹出窗口对象
var grid=null; //表格对象
//初始化表格
function initGroupData(url){
	grid = $("#maingrid").ligerGrid({
		height:'95%',
		width:'99.7%',
		headerRowHeight:28, 
		onAfterShowData:function(data) {
      	  	typeId=data.Rows[0].typeId;
      	  	$("#groupManage").attr("src","../friendLinkManage/initFriendLinkInfoList?typeId="+ data.Rows[0].typeId);
      	  	//单元格高度自动化，撑开
      	  	$(".l-grid-row-cell-inner").css("height","auto"); 
      	  	var i=0;
      	  	$("tr",".l-grid2","#maingrid").each(function (){                                            
               $($("tr",".l-grid1","#maingrid")).css("height",$(this).height()); //2个表格的tr高度一致
               i++;                        
      	  	});                                            
		},
		selectRowButtonOnly: true,
		rowHeight:26,
		checkbox: true,
		columns: [
          { display: '分类ID', name: 'typeId',align: 'center',width: '0%',hide:true},
          { display: '分类名称', name: 'typeName',align: 'center', width: '90%',render: function (row){
              var html = '<a href="javascript:onClickRow('+row.typeId+');" title="'+row.typeDesc+'">'+row.typeName+'</a>';
              return html;
              }
          }
          ], url:url, pageSize:20,rownumbers:false,pageParmName:"curNo",pagesizeParmName:"curSize"
	});
  	$("#pageloading").hide();
}
function onClickRow(typeId){
	  $("#groupManage").attr("src","../friendLinkManage/initFriendLinkInfoList?typeId="+typeId);
}
function addGroup(){
	//添加友情链接信息
	var url= "../friendLinkManage/initAddFriendLinkGroup";
    m=$.ligerDialog.open({ url: url, height: 220,width:550, title:'添加友情链接分组信息',isResize: true });
    if(!m){
    	$.ligerDialog.error("操作失败！");
  	return;
  	}
}
//编辑友情链接分组信息
function editGroup(){
	var rowid = grid.getSelecteds();
	if (rowid.length != 1  ){
		$.ligerDialog.warn("请选择一行！");
		return false;
	}
	var typeId = rowid[0].typeId;
	var typeName = rowid[0].typeName;
	var typeDesc = rowid[0].typeDesc;
	//编辑友情链接分组信息
	var url= "../friendLinkManage/initUpdFriendLinkGroup?typeId="+typeId+"&typeName="+typeName+"&typeDesc="+typeDesc;
    m=$.ligerDialog.open({ url: url, height: 220,width:550, title:'添加友情链接分组信息',isResize: true });
    if(!m){
    	$.ligerDialog.error("操作失败！");
    	return;
  	}
}
//删除友情链接分组信息
function delGroup(){
	var rowid = grid.getSelecteds();
	if (rowid.length == 0){
		$.ligerDialog.warn("请至少选择一行！");
		return false;
	}
	$.ligerDialog.confirm("确定将选中的记录删除吗？", function (yes){
		var rIds="";
		if(yes){
			var url = "../friendLinkManage/delFriendLinkGroup";
			for(var i=0;i<rowid.length;i++){
	            var rId=rowid[i].typeId;
	            rIds=rId+","+rIds;
	        }
			$.ajax({
				type : "post",
				url : url,
				data : {
					"typeIds" :rIds
				},
				success : function(data) {
					//如果删除成功，则刷新页面！
					if(data["msg"]=="sucess") {
						$.ligerDialog.alert("删除成功！");
						//
						flReload()
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
function reload(typeId){
	  $("#groupManage").attr("src","../friendLinkManage/initFriendLinkInfoList?typeId="+typeId);
	  //关闭弹出窗口
	  m.close();
}
function flReload(){
	grid.loadData(); //重新加载不查询数据库
	//关闭弹出窗口
	m.close();
}