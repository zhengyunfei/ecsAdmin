/**
 * @title : 菜单树 js操作
 * @author : zhengYunFei
 * @date ： 2013-7-08
 */
var manager;
var imgManager;
var addManager;
var editorWin;
$(function(){
	menuTree();
});

/**
 * @title : 显示菜单树
 * */
function menuTree(){
	$.ajax({
		type:"post",
		url:"../menu/listSysMenu.shtml",
		success:function(data){
		manager = $("#maingrid").ligerGrid({
			rowHeight:26,
	        headerRowHeight:28,
	        width: '100%',
	        height: '97%',
	        checkbox:false,
	        alternatingRow: true,
	        usePager :false,
	        columns: [
	            { display: '选择', name: '',  width:'4%', render: function (row){                        
					var html = '<input type="radio" name="raido" />';                         
					return html;                           
				}},
				{ display: 'ID', name: 'menuId', width: 250, align: 'left',hide:true},
				{ display: 'IMG', name: 'menuImg', width: 250, align: 'left',hide:true},
	            { display: '菜单名称', name: 'menuName', width: 250, align: 'left',render:function (row){
					if(row.menuImg !=""){
						return "<img class='"+row.menuImg+"' src='../s9/res/img/icon000.png'/>&nbsp;<span>"+row.menuName+"</span>";
					}else{
						return row.menuName;
					}
	            }},
	            { display: '菜单编码', name: 'menuCode', width: 120, type: 'int', align: 'center' },
	            { display: '状态', name: 'isActive', width: 100, type: 'int', align: 'center' ,render: function (row){
					if(row.isActive == "1"){
						return "<img src='../s9/res/img/yes.gif'>";
					}else{
						return "<img src='../s9/res/img/no.gif'>";
					}
		        }},
	            { display: 'URL', name: 'pathCode', width: 250, align: 'center', editor: { type: 'text'} },
	            { display: '顺序', name: 'sortNo', width: 50, align: 'center', editor: { type: 'text'} }
	            ], data: data, tree: { columnName: 'menuName' }, rownumbers: true, onSelectRow:onClickRow
	    });
		}
	});
}
/**
 * 拓展行点击事件radio选中
 **/
function onClickRow(rowdata, rowindex, rowDomElement) {
	var $v = $(rowDomElement);
	$v.find(":radio").attr("checked","checked")
}

/**
 * @title : 停用菜单 设置无效
 * */
function stop(){
	var id =  manager.getSelecteds();
	if(id.length !=0){
		$.ajax({
			type:"post",
			url:"../menu/updSysMenuStatus.shtml",
			data:{
				"menuId":id[0].menuId,
				"isActive":0
			},
			async : false,
			success:function(data){
				if(data.message == "success"){
					manager.set({url:"../menu/listSysMenu.shtml"});
					$.ligerDialog.success('操作成功!');
				}else{
					$.ligerDialog.error('服务器忙!');
				}
			}
		});
	}else{
		$.ligerDialog.error('请选择菜单项!');
	}
	
}

/**
 * @title : 启用菜单
 * */
function start(){
	var id =  manager.getSelecteds();
	if(id.length !=0){
		$.ajax({
			type:"post",
			url:"../menu/updSysMenuStatus.shtml",
			data:{
				"menuId":id[0].menuId,
				"isActive":1
			},
			async : false,
			success:function(data){
				if(data.message == "success"){
					manager.set({url:"../menu/listSysMenu.shtml"});
					$.ligerDialog.success('操作成功!');
				}else{
					$.ligerDialog.error('服务器忙!');
				}
			}
		});
	}else{
		$.ligerDialog.error('请选择菜单项!');
	}
}
/**
 * @title : 查看菜单详细信息    
 */
function show(){
	var id =  manager.getSelecteds();
	if(id.length !=0){
		var url="../menu/showSysMenu.shtml?menuId="+id[0].menuId;
		editorWin = $.ligerDialog.open({ title: '系统菜单信息', name:'winselector',width: 700, height: 400, url: url,isHidden: false});
	}else{
		$.ligerDialog.error('请选择菜单项!');
	}
	
}

function updateSysMenuOK(message){
	//$.ligerDialog.hide();
	editorWin.close();
	if(message == "success"){
		$.ligerDialog.success("修改成功!",function(){
			window.parent.location.href="../login/initApplication.shtml?flag=true";
		});
	//	manager.set({url:"/c/login/initApplication"});
	}else if(message == "error"){
		$.ligerDialog.error('修改失败!');
	}
}
function addSysMenuOK(message){
	addManager.close();
	if(message == "success"){
		$.ligerDialog.success("新增成功!");
		manager.set({url:"../menu/listSysMenu.shtml"});
	}else if(message == "error"){
		$.ligerDialog.error('新增失败!');
	}
}
/**
 * @title : 新增系统菜单
 */
function addSysMenu(){
	var url="../menu/addSysMenuInit.shtml";
	addManager = $.ligerDialog.open({ title: '新增系统菜单', name:'winselector',width: 800, height: 420, url: url,isHidden: false});
	
}

/**
 * @title : 菜单图片资源
 */
function sysMenuImg(){
	var url="../menu/sysMenuImg.shtml";
	imgManager = $.ligerDialog.open({ title: '系统菜单图片', name:'imgClass',width: 900, height: 600, url: url,isHidden: false});
}

/**
 * @title : 系统菜单图标资源
 */
function getSysMenuImg(imgC){
	imgManager.close();
	winselector.window.imgClass(imgC); //父页面调用子页面
}