var grid = null;//表格对象
var m; 	//弹出窗口对象
// 查询用户信息列表数据
function findAllList(url){
	   var pageSize=20;
	   var sexData = [{ status: 1, text: '已经排满' }, { status: 0, text: '未排满'}];
       grid = $("#maingrid").ligerGrid({
    	    width:'99.6%',
    	    height:'100%',
			headerRowHeight:28,
			rowHeight:26,
			rownumbers:true,
			checkbox: true,
    		columns: [
				{ display: 'ID', name: 'id',hide:true},
				{ display: '预约时间', name: 'preTime',width: 200 },
				{ display: '状态', name: 'status',width: 200,type: 'int',
                    editor: { type: 'select', data: sexData, valueField: 'status' },
                    render: function (item)
                    {
                        if (parseInt(item.status) == 0) return '未排满';
                        return '<font color="red">已经排满</font>';
                    }
                },
				
				{ display: '预约人次', name: 'washInfo',width: 200 }
			
          ], url:url, pageSize:20,rownumbers:false,pageParmName:"curNo",enabledEdit: false,pagesizeParmName:"curSize"
    
		 });
      $("#pageloading").hide();
}
 function beginEdit(rowid) { 
				
	            grid.beginEdit(rowid);
	        }
	        function cancelEdit(rowid) { 
	            grid.cancelEdit(rowid);
	        }
	        function endEdit(rowid)
	        {
	            grid.endEdit(rowid);
	        }
	 
	        function deleteRow(rowid)
	        {
	            if (confirm('确定删除?'))
	            {
	                grid.deleteRow(rowid);
	            }
	        }
/**
 * 拓展行点击事件radio选中
 **/
function onClickRow(rowdata, rowindex, rowDomElement) {
	var $v = $(rowDomElement);
	$v.find(":checkbox").attr("checked","checked")
}
//重置按钮
function reset(){
	$('#name').val('');
	$('#shortName').val('');
	$('#title').val('');
	$('#sendStartTime').val('');
	$('#sendEndTime').val('');
}
//新增
function add(){
	var url="../invitationLetter/forAddInitPage.shtml";
	m = $.ligerDialog.open({ url: url, height: 800,width:800, title:'添加邀请函',showMax:true,showToggle:true,showMin:true}); 
}
//修改
function update(){
	    //获取选中记录行
   		var rowid=grid.getSelecteds();
   		var length=rowid.length;
		if(length ==0){
			$.ligerDialog.error("请选择需要修改的信息！");
			return false;
		}
		if(length > 1){
			$.ligerDialog.error("不要贪得无厌啊，只能选择一条信息进行修改！");
			return false;
		}
		var id = rowid[0].id ;
		var preTime=rowid[0].preTime;
		var status=rowid[0].status;
		
	    var url="../car/forUpdateInitPage.shtml?id="+id+"&preTime="+preTime+"&status="+status;
	    m = $.ligerDialog.open({ url: url, height: 250,width:500, title:'修改排单信息',isResize: true ,top:50}); 
}
//删除
function delObj(){
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
				ids+=rowid[i].id+",";
			}else{
				ids+=rowid[i].id;
			}	
		}
		var data={'ids':ids};
		var url='../invitationLetter/delSave.shtml';
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
	$.ligerDialog.success(msg);
	grid.loadData()
}
//搜索
function doSearch(){
	var name=$('#name').val();
	var shortName=$('#shortName').val();
	var title=$('#title').val();
	var sendStartTime=$('#sendStartTime').val();
	var sendEndTime=$('#sendEndTime').val();
	if(sendStartTime>sendEndTime){
		$.ligerDialog.error('结束时间小于开始时间');return;
	}else{
		var params="?name="+name+"&shortName="+shortName+"&title="+title+"&sendStartTime="+sendStartTime+"&sendEndTime="+sendEndTime;
		var url='../invitationLetter/findAllList.shtml'+params;
		findAllList(url);
	}
	
}

