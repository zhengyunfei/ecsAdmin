/**
 * @title : 角色菜单权限
 */

$(function(){
	var role = $("#roleId",parent.document).val();
	$("#roleId").val(role);
	init(role);
});
//js获取项目根路径，如： http://localhost:8083/uimcardprj  
function getRootPath(){  
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp  
    var curWwwPath=window.document.location.href;  
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8083  
    var localhostPaht=curWwwPath.substring(0,pos);  
    //获取带"/"的项目名，如：/uimcardprj  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
    return(localhostPaht+projectName);  
}  
/**
 * 初始化角色权限
 * */
function init(roleId){
	var path=getRootPath();
	$.ajax({
		type:"post",
		url:path+"/menu/findRoleMenu.shtml",
		data:{
			"roleId":roleId
		},
		success:function(data){
			var topMenu = data.topMenu;
			var twoMenu = data.twoMenu;
			var roleMenu = data.roleMenu; //当前角色的菜单资源权限
			var html = ""; 
			$("#menuInfo").html("");
			//顶级节点
			for(var i = 0;i<topMenu.length;i++){
				html += '<div style="padding-top:4px;"><div class="z-legend">';
				var flag = false;
				for(var r = 0;r<roleMenu.length;r++){
					if(roleMenu[r] == topMenu[i].menuId){
						flag = true;
						break;
					}
				}
				if(flag){
					html +='<input type="checkbox" name="menuId" id="menuId" menuType="top" menuCode="'+topMenu[i].menuCode+'" value="'+topMenu[i].menuId+'" onclick="onclickSysMenu(this);" checked="checked"><label><img class="'+topMenu[i].menuImg+'" src="'+path+'/s9/res/img/icon000.png" >&nbsp;<b>'+topMenu[i].menuName+'</b></label></div>';
				}else{
					html +='<input type="checkbox" name="menuId" id="menuId" menuType="top" menuCode="'+topMenu[i].menuCode+'" value="'+topMenu[i].menuId+'" onclick="onclickSysMenu(this);"><label><img class="'+topMenu[i].menuImg+'" src="'+path+'/s9/res/img/icon000.png" >&nbsp;<b>'+topMenu[i].menuName+'</b></label></div>';
				}
				html +='<div style=" padding:5px 0;"></div></div>';
				for(var s = 0; s<twoMenu.length;s++){
					if(twoMenu[s].pmenuCode == topMenu[i].menuCode){
						html += '<div style="padding-top:4px;padding-left:20px"><div class="z-legend">';
						var flag1 = false;
						for(var r = 0;r<roleMenu.length;r++){
							if(roleMenu[r] == twoMenu[s].menuId){
								flag1 = true;
								break;
							}
						}
						if(flag1){
							html += '<input type="checkbox" name="menuId" id="menuId" menuType="two" pmenuCode="'+twoMenu[s].pmenuCode+'" menuCode="'+twoMenu[s].menuCode+'" value="'+twoMenu[s].menuId+'" onclick="onclickSysMenu(this);" checked="checked"><label><img class="'+twoMenu[s].menuImg+'" src="'+path+'/s9/res/img/icon000.png">&nbsp;<b>'+twoMenu[s].menuName+'</b></label></div>';
						}else{
							html += '<input type="checkbox" name="menuId" id="menuId" menuType="two" pmenuCode="'+twoMenu[s].pmenuCode+'" menuCode="'+twoMenu[s].menuCode+'" value="'+twoMenu[s].menuId+'" onclick="onclickSysMenu(this);"><label><img class="'+twoMenu[s].menuImg+'" src="'+path+'/s9/res/img/icon000.png">&nbsp;<b>'+twoMenu[s].menuName+'</b></label></div>';
						}
						//循环资源项
						//
						html += '</div>';
					}
				}
			}
			$("#menuInfo").html(html);
			
		}
	});
}

/**
 * @title : 菜单事件
 * */
function onclickSysMenu(_this){
	var menuType = $(_this).attr("menuType");
	var menuCode = $(_this).attr("menuCode");
	var code = $(_this).attr("menuCode");
	if(menuType == "top"){
		//点击顶级节点
		if($(_this).attr("checked")=="checked" || $(_this).attr("checked")==true){
			//添加权限
			//选择顶级节点下所有菜单资源
			$("[pmenuCode='"+code+"']").attr("checked","checked");
		}else{
			//删除权限
			//取消顶级节点下所有菜单资源
			$("[pmenuCode='"+code+"']").removeAttr("checked");
		}
	}else if(menuType == "two"){
		//点击二级节点
		var pmenuCode = $(_this).attr("pmenuCode");
		$("[menuCode='"+pmenuCode+"']").attr("checked","checked");
	}else{
		
	}
}

/**
 *  @title : 保存
 */
function save(){
	var path=getRootPath();
	var url = path+"/menu/addRoleMenu.shtml";
	var roleId= $("#roleId").val();
	var menuId = [];
	//获得复选框中的值
	$('input[name="menuId"]:checked').each(function(){
		menuId.push($(this).val());
	});
	menuId+="";
	$.ajax({
		type:"post",
		url:url,
		data:{
			"roleId":roleId,
			"menuId":menuId
		},
		success:function(data){
			var message = data.message;
			if(message !="" && message!=null){
				if(message == "success"){
					$.ligerDialog.success("保存成功!");
				}else if(message == "error"){
					$.ligerDialog.error("保存失败!");
				}
			}
		}
		});
}

/**
 * @title :全选
 * */
function onSelectAllClick(){
	$('input[name="menuId"]').attr("checked","checked");
}