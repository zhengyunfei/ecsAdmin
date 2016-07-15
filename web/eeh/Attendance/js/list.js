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
				{ display: '学生卡号', name: 'cardNo' },
				{ display: '学生姓名', name: 'student.name' },
				{ display: '课程', name: 'courseName' },
				{ display: '教室', name: 'classRoom' },
				{ display: '打卡日期', name: 'dayTime'},
				{ display: '打卡时间', name: 'schoolTime'},
				{ display: '考勤状态', name: 'type',render:function(r){
					if(r.type=='1'){
						return "<font color='red'>迟到</font>";
					}else if(r.type=='2'){
						return "<font color='red'>请假</font>";
					}else if(r.type=='3'){
						return "<font color='red'>早退</font>";
					}else if(r.type=='4'){
						return "<font color='red'>缺勤</font>";
					}else{
						return "<font color='green'>正常</font>";
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
	var url="../Attendance/forAddInitPage.shtml";
	m = $.ligerDialog.open({ url: url, height: 300,width:450, title:'新增科目',showMax:true,showToggle:true,showMin:true});
}
function upd(id){
	var url="../Attendance/forUpdateInitPage.shtml?id="+id;
	m = $.ligerDialog.open({ url: url, height: 250,width:500, title:'修改科目',isResize: true ,top:50});
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
		var url='../Attendance/delSave.shtml';
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


