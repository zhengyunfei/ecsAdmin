var grid = null;//表格对象
var m; 	//弹出窗口对象
// 查询用户信息列表数据
function findAllList(url){
	   var pageSize=20;
       grid = $("#maingrid").ligerGrid({
    	    width:'99.6%',
    	    height:'100%',
			headerRowHeight:28,
			rowHeight:26,
			rownumbers:true,
			checkbox: true,
    		columns: [
				{ display: '设备编号', name: 'dev_no' },
				{ display: '设备名称', name: 'dev_name' },
				{ display: 'ip地址', name: 'ip' },
				{ display: 'mac地址', name: 'mac' },
				{ display: '设备活动状态', name: 'dev_status',render:function(r){
					var status= r.dev_status;
					if(status=='0'){
						return "<font color='green'>正常运行</font>";
					}else if(status=='1'){
						return "<font color='red'>重启</font>";
					}else if(status=='2'){
						return "<font color='red'>关机</font>";
					}
				}},{ display: '操作', name: 'dev_status',render:function(r) {
					var id= r.id;
					var html="<a href='javascript:updateStatus("+id+",1,"+ r.dev_no+",\""+ r.dev_name+"\",\""+ r.ip+"\",\""+ r.mac+"\")'>重启</a>";
						html+="<a href='javascript:updateStatus("+id+",2,"+ r.dev_no+",\""+ r.dev_name+"\",\""+ r.ip+"\",\""+ r.mac+"\")'>关机</a>";
					return html;
				}
				}

          ], url:url, pageSize:20,rownumbers:false,pageParmName:"curNo",enabledEdit: false,pagesizeParmName:"curSize"

		 });
      $("#pageloading").hide();
}
function updateStatus(id,status,dev_no,dev_name,ip,mac){
	var data={'id':id,'dev_status':status,'dev_no':dev_no,'dev_name':dev_name,'ip':id,'mac':mac};
	var url='../DevConf/forUpdateAjax.shtml';
	ajax(url,data);
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
	var url="../DevConf/forAddInitPage.shtml";
	m = $.ligerDialog.open({ url: url, height: 300,width:450, title:'新增',showMax:true,showToggle:true,showMin:true});
}
function upd(){
	var rowid=grid.getSelecteds();
	var length=rowid.length;
	if(length ==0){
		$.ligerDialog.error("请选择需要修改的信息！");
		return false;
	}
	if(length>1){
		$.ligerDialog.error("只能选择1条需要修改的信息！");
		return false;
	}
	var id=rowid[0].id;
	var url="../DevConf/forUpdateInitPage.shtml?id="+id;
	m = $.ligerDialog.open({ url: url, height: 300,width:500, title:'修改',isResize: true ,top:50});
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
				ids+=rowid[i].id+",";
			}else{
				ids+=rowid[i].id;
			}
		}
		var data={'ids':ids};
		var url='../DevConf/delSave.shtml';
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
	if(msg){
		$.ligerDialog.success(msg);
	}


	grid.loadData()
}


