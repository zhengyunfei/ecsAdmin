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
				{ display: '星期', name: 'week' },
				{ display: '班级', name: 'gradeName' },
				{ display: '第一节', name: 'firstClass'},
				{ display: '第二节', name: 'secondClass'},
				{ display: '第三节', name: 'threeClass'},
				{ display: '第四节', name: 'fourClass'},
				{ display: '第五节', name: 'fiveClass'},
				{ display: '第六节', name: 'sixClass'},
				{ display: '第七节', name: 'sevenClass'},
				{ display: '第八节', name: 'eightClass'},
				{ display: '第九节', name: 'nineClass'},
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
	var url="../Timetable/forAddInitPage.shtml";
	m = $.ligerDialog.open({ url: url, height: 520,width:450, title:'新增日常课程',showMax:true,showToggle:true,showMin:true});
}
function upd(id){
	var url="../Timetable/forUpdateInitPage.shtml?id="+id;
	m = $.ligerDialog.open({ url: url, height: 520,width:450, title:'修改日常课程',isResize: true ,top:50});
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
		var url='../Timetable/delSave.shtml';
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


