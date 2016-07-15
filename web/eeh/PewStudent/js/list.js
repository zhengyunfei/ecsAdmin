var grid = null;//表格对象
var m; 	//弹出窗口对象
// 查询用户信息列表数据
function findAllList(url){
	   var pageSize=20;
       grid = $("#maingrid").ligerGrid({
    	    width:'100%',
    	    height:'100%',
			headerRowHeight:28,
			rowHeight:26,
			rownumbers:true,
			checkbox: true,
    		columns: [
				{ display: '课程名', name: 'electiveCourse' },
				{ display: '班级', name: 'className' },
				{ display: '姓名', name: 'name' },
				{ display: '学号', name: 'xhnum'},
				{ display: '性别', name: 'sex' },
				{ display: '类型', name: 'type' },
				//{ display: '上课教室', name: 'classRoom' },
				//{ display: '状态', name: 'status' },
				{ display: '座位号', name: 'seatNumer' },
				{ display: '上课时间', name: 'schoolTime' },
				{display:'操作',name:'',render:function(r){
					return '<a href="javascript:upd('+ r.id+')">编辑</a>';
				}}

          ], url:url, pageSize:20,rownumbers:true,pageParmName:"curNo",enabledEdit: false,pagesizeParmName:"curSize"

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
	//手动添加学生选修课程的时候，必须先选择课程才可以添加
	var courseName=$("#courseName").val();
    if(''==courseName||null==courseName){
		$.ligerDialog.error("请先选择课程");
		return;
	}
	var url="../PewStudent/forAddInitPageForPew.shtml?courseName="+courseName;
	m = $.ligerDialog.open({ url: url, height: 600,width:650, title:'新增学生',showMax:true,showToggle:true,showMin:true});
}
function upd(id){
	var url="../PewStudent/updateTeacherXuanKe.shtml?id="+id;
	m = $.ligerDialog.open({ url: url, height: 600,width:500, title:'修改老师选课',isResize: true ,top:50});
}
//已审
function updStatused(id){
	var url="../Course/forUpdateAjax.shtml";
	var data={"id":id,"status":"已审"};
	ajax(url,data)
}
//待审
function updStatusing(id){
	var url="../PewStudent/forUpdateAjax.shtml";
	var data={"id":id,"status":"待审"};
	ajax(url,data)
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
		var url='../PewStudent/delSave.shtml';
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


