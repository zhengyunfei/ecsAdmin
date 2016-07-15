//培优座位表
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
			{ display: '科目', name: 'kemu' },
			{ display: '授课老师', name: 'teacher'},
			{ display: '教室', name: 'classRoom'},
			{ display: '上课时间', name: 'schoolTime',render:function(r){
				return r.week+""+ r.schoolTime;
			}},
			{ display: '人数', name: 'peopleMax'},
			{ display: '座位', name: 'seatSetStatus',render:function(r){
				var h="";
				if(r.seatSetStatus=='1'){
					h+= "<a href=\"javascript:lookSeat("+ r.id+")\"><font color='green'>座位查看</font></a>";
					h+="<a href=\"javascript:seatSet("+r.id+")\"><font color='red'>修改</font></a>";
					return h;
				}else{
					return "<a href=\"javascript:seatSet("+r.id+")\">座位设置</a><font color='red'>(未导入)</font>";

				}
			}}

		], url:url, pageSize:20,rownumbers:false,pageParmName:"curNo",enabledEdit: false,pagesizeParmName:"curSize"

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
	var url="../Course/forAddInitPage.shtml";
	m = $.ligerDialog.open({ url: url, height: 300,width:500, title:'新增培优课程'});
}
function upd(id){
	var url="../Course/forUpdateInitPage.shtml?id="+id;
	m = $.ligerDialog.open({ url: url, height: 300,width:500, title:'修改培优课程',isResize: true ,top:50});
}
function start(id){
	var url="../Course/forUpdateAjax.shtml?id="+id;
	var data={"status":"已开始"};
	ajax(url,data);
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
	var url='../Course/delSave.shtml';
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
				//$.ligerDialog.success('操作成功',function(){
					reload('操作成功');
				//});
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
//座位查看
function lookSeat(id){
	var url="../Seat/forLookSeat.shtml?id="+id+"&type=1";
	m = $.ligerDialog.open({ url: url, height: 500,width:600, title:'查看座位'});

}

//座位设置
function seatSet(id){
	/**
	 * 设置座位表
	 * id 课程id
	 * classRoom 上课教室
	 * week星期
	 * schoolTime上课时间
	 * @type {string}
	 */
		var url="../Seat/forAddInitPage.shtml?id="+id;
		m = $.ligerDialog.open({ url: url, height: 150,width:300, title:'座位设置'});
}

