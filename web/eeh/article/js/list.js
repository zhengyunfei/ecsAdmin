var grid = null;//表格对象
var m; 	//弹出窗口对象
// 查询用户信息列表数据
function findArticleList(url){
	   var pageSize=20;
       grid = $("#maingrid").ligerGrid({
    	    width:'99.6%',
    	    height:'100%',
			headerRowHeight:28,
			rowHeight:26,
			rownumbers:true,
			checkbox: true,
    		columns: [
				{ display: '标题', name: 'title'},
				{ display: '类型', name: 'type' },
				{ display: '时间', name: 'time' },
				{ display: '标签', name: 'gradeName',render:function(r){
					if(r.gradeName!=''&& r.gradeName!=null){
						return r.gradeName+"班";
					}
				} },
				{ display: '操作', name: '',render:function(r){
					return "<a href='javascript:upd("+r.articleId+")'>编辑</a>";
				  }
				}

          ], url:url, pageSize:20,rownumbers:false,pageParmName:"curNo",pagesizeParmName:"curSize",onSelectRow:onClickRow

		 });
      $("#pageloading").hide();
}

/**
 * 拓展行点击事件radio选中
 **/
function onClickRow(rowdata, rowindex, rowDomElement) {
	var $v = $(rowDomElement);
	$v.find(":checkbox").attr("checked","checked")
}
//新增
function add(){
	var url="../Article/forAddInitPage.shtml";
	m = $.ligerDialog.open({ url: url, height: 600,width:850, title:'新增信息',showMax:true,showToggle:true,showMin:true});
}
function upd(id){
	var url="../Article/forUpdateInitPage.shtml?articleId="+id;
	m = $.ligerDialog.open({ url: url, height: 600,width:850, title:'修改信息',isResize: true ,top:50});
}
//删除
function del(){
	//获取选中记录行
	var rowid=grid.getSelecteds();
	var length=rowid.length;
	if(length ==0){
		$.ligerDialog.error("请选择需要删除的信息！");
		return false;
	}
	var ids='';
	for(var i= 0;i<length;i++){
		if(i<length-1){
			ids+=rowid[i].articleId+",";
		}else{
			ids+=rowid[i].articleId;
		}
	}
	var data={'ids':ids};
	var url='../Article/delSave.shtml';
	ajax(url,data);
}
function ajax(url,data){
	$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		data:data,
		success:function(result){
			if(result.success){
				$.ligerDialog.success('操作成功',function(){
					reload();
				});
			}else{
				$.ligerDialog.error('操作失败');
			}
		},error:function(){
			$.ligerDialog.error('服务器也有duang的时候，请您谅解我一下了');
		}
	})
}
function reload(){
	if(m){
		m.close();
	}

	grid.loadData();
}
function reload(msg){
	if(m){
		m.close();
	}
	if(''!=msg&&null!=msg){
		$.ligerDialog.success(msg);
	}

	grid.loadData()
}
