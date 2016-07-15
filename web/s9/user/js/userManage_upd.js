/**
 * @title : 用户更改页面js
 * 
 */
var userRoleData = [];
$(function(){
	userRole();
	roleTree();
	
});
function check(){
	return false;
}
	function userRole(){
		var url = "../userManage/findUserRole.shtml";
		$.ajax( {
			url : url,
			type : "POST",
			data : "userId="+$("#userId").val(),
			async : false,
			dataType : "json",
			success : function(data) {
				if(data.message == "success"){
					userRoleData = data.list;
				}
			}
		});
	}
		//获得角色树
	function roleTree(){
		var url = "../sysRole/initRoleTree.shtml";
		$.ajax( {
			url : url,
			type : "POST",
			async : false,
			dataType : "json",
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(result) {
				var array = result.roleList;
				var treeData = [];
				//为每个根点赋值所有子节点
			treeData.push( {
				'id' : '-1',
				'icon' : '',
				'text' : '角色列表',
				'isexpand' : true,
				'pid' : ""
			});
			var roleId = $("#roleId").val();
			for ( var k = 0; k < array.length; k++) {
				var flag = false;
				for(var i=0;i<userRoleData.length;i++){
					if(userRoleData[i] == array[k].roleId){
						roleId += userRoleData[i]+",";
						flag = true;
						break;
					}
				}
				treeData.push( {
					'id' : array[k].roleId,
					'icon' : '',
					'text' : array[k].roleName,
					'isexpand' : false,
					'ischecked':flag,
					'pid' : "-1"
				});
			}
			$("#roleId").val(roleId);
			loadTree(treeData);//加载树
		},
		error : function(error) {
			$.ligerDialog.error("服务器操作异常");
		}
		});
		
	}
	//加载树
	function loadTree(dataTree) {
		treeManger = $("#roleTree").ligerTree( {
			data : dataTree,
			idFieldName : 'id',
			parentIDFieldName : 'pid',
			checkbox : true,
			slide : true,
			nodeWidth : 350,
			onClick : onclick
		});
	}
	//单击事件
	function onclick(note) {
		//得到所选节点的id
		if(note.data.id!='-1'){
			var roleId = $("#roleId").val();
			var flag = false;
			if(roleId !="" && roleId !=null){
				var id = [];
				id = roleId.split(",");
				for(var i=0;i<id.length;i++){
					if(id[i] == note.data.id){
						roleId = roleId.replace(id[i]+",","");
						flag = true;
						break;
					}
				}
			}
			if(!flag){
				roleId += note.data.id+",";
			}
			$("#roleId").val(roleId);
		}
	}
	
	/*
	 * 修改用户信息
	 */
	function forUserUpd(){
		if(validData()){
			var initPass = $("#initPass").val();
			var userPwd = $("#userPwd").val() ;
			if(initPass != userPwd) {
				$("#userPwd").val(hex_md5(userPwd));
			}
			$("#form").attr('onsubmit','');
			$("#form").submit();
		}
	}
	
	/*
	*验证表单数据
	*/
	function validData(){
		var userName = $("#userName").val();
		if(userName.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.warn("用户名称不能为空！");
			return false;
		}
		var userPwd = $("#userPwd").val();
		if(userPwd.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("密码不能不能为空！");
			return false;
		}
		
		return true;
	}
