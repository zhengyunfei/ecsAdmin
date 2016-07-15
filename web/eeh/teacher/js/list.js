var grid = null;//表格对象
var m; 	//弹出窗口对象
var gradeArray=new Array();
gradeArray[0]="请选择";
gradeArray[1]="一年级";
gradeArray[2]="二年级";
gradeArray[3]="三年级";
gradeArray[4]="四年级";
gradeArray[5]="五年级";
gradeArray[6]="六年级";
gradeArray[7]="初一";
gradeArray[8]="初二";
gradeArray[9]="初三";
gradeArray[10]="高一";
gradeArray[11]="高二";
gradeArray[12]="高三";
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
				{ display: '教师名称', name: 'name' },
				{ display: '所属科目', name: 'subjectId' },
				{ display: '所属年级', name: 'gradeBo.gradeName',render:function(r){
					return gradeArray[r.gradeBo.gradeName];
				}},
				{ display: '授课班级', name: 'classId'},
				{ display: '操作', name: '',render:function(r){
					return "<a href='javascript:upd("+r.id+")'>编辑</a>";
				}
				}

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
	var url="../teacher/forAddInitPage.shtml";
	m = $.ligerDialog.open({ url: url, height: 300,width:450, title:'新增教师',showMax:true,showToggle:true,showMin:true});
}
function upd(id){
	var url="../teacher/forUpdateInitPage.shtml?id="+id;
	m = $.ligerDialog.open({ url: url, height: 300,width:450, title:'修改教师',isResize: true ,top:50});
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
		var url='../teacher/delSave.shtml';
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


