	/**
	 * @title : 新增系统菜单js
	 */
	var treeManger;
	$(function(){
		treeSysMenu();
	});
	
	/**
	 * @title : 以树的形式展示系统一级菜单
	 * */
	function treeSysMenu(){
		var url = "../menu/getSysMenu.shtml";
		$.ajax({
			type:"post",
			url: url,
			async : false,
			success:function(data){
				var treeData = [];
				treeData.push( {
					'id' : '-1',
					'icon' : '',
					'text' : '菜单列表',
					'isexpand' : true,
					'pid' : ""
				});
				var sysMenuList = data.sysMenu;
				for(var i=0;i<sysMenuList.length;i++){
					treeData.push( {
						'id' : sysMenuList[i].menuCode,
						'icon' : '',
						'text' : sysMenuList[i].menuName,
						'isexpand' : false,
						'pid' : "-1"
					});
				}
				loadTree(treeData);
			}
		});
	}
	
	//加载树
	function loadTree(dataTree) {
		treeManger = $("#menuTree").ligerTree( {
			data : dataTree,
			idFieldName : 'id',
			parentIDFieldName : 'pid',
			checkbox : false,
			slide : true,
			nodeWidth : 350,
			onClick : onclick
		});
	}
	
	function onclick(note){
		var menuCode= note.data.id;
		var pMenuName = note.data.text;
		if(menuCode == "-1"){
			$("#pmenuCode").val("999999");
			$("#pMenuName").val("菜单列表");
		}else{
			$("#pmenuCode").val(menuCode);
			$("#pMenuName").val(pMenuName) ;
		}
	}
	
	function forSave(){
		var menuName=$("#menuName").val();
		var pMenuName=$("#pMenuName").val();
		if(menuName==''){
			$.ligerDialog.error("菜单名称不能为空!");
			return;
		}
		
		else if(pMenuName==''){
			$.ligerDialog.error("上级菜单不能为空!");
			return;
		}else{
			$("#form2").attr('onsubmit','');
			$("#form2").submit();
		}
	}
	
	/**
	 * @title : 系统菜单图片
	 */
	function onclickImg(){
		parent.sysMenuImg();
	}
	
	function imgClass(img){
		$("#menuImg").val(img);
		$("#imgClass").html("<img class='"+img+"' src='../s9/res/img/icon000.png'>");
	}
