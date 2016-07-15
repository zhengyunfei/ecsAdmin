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
				{ display: 'ID', name: 'id',hide:true},
				{ display: '班级名称', name: 'name' },
				{ display: '年级', name: 'gradeName',render:function(r){
					return gradeArray[r.gradeName];
				}},
				{ display: '班主任', name: 'teacherName'},
				{ display: '班级人数', name: 'gradenum'},
				{ display: '实际人数', name: 'peopleCount'},
				{ display: '操作', name: '',render:function(r){
					return "<a href='javascript:upd("+r.id+")'>编辑</a>"
						+"&nbsp;&nbsp;<a href='javascript:studentManage("+r.name+")'>学生管理</a>";
				}
				}

          ],  pageSize:20,url:url,rownumbers:false,pageParmName:"curNo",enabledEdit: false,pagesizeParmName:"curSize"

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
	var url="../grade/forAddInitPage.shtml";
	m = $.ligerDialog.open({ url: url, height: 450,width:600, title:'新增班级',showMax:true,showToggle:true,showMin:true});
}
function upd(id){
	var url="../grade/forUpdateInitPage.shtml?id="+id;
	m = $.ligerDialog.open({ url: url, height: 450,width:600, title:'修改班级',isResize: true ,top:50});
}
function studentManage(id){
	var url="../student/forInit.shtml?id="+id;
	m = $.ligerDialog.open({ url: url, height: 650,width:800, title:'学生管理',showMax:true,showToggle:true,showMin:true});
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
	$.ligerDialog.confirm("确定要删除吗?", function (yes) {
		if (yes) {
			var ids='';
			for(var i= 0;i<length;i++){
				if(i<length-1){
					ids+=rowid[i].id+",";
				}else{
					ids+=rowid[i].id;
				}
			}
			var data={'ids':ids};
			var url='../grade/delSave.shtml';
			ajax(url,data);
		}
	})

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


