/**
 * @title : 组织机构 js操作
 * @author : zhengYunFei
 * @date ： 2013-08-06
 */
	var manager;//组织机构列表数据显示Grid
	
	/**
	* 加载页面列表数据
	*/
	function initData(){
		$.ajax({
			type:"post",
			url:"/c/organ/findOrganList",
			success:function(data){
			manager = $("#maingrid").ligerGrid({
				rowHeight:26,
		        headerRowHeight:28,
		        checkbox:true,
		        width: '100%',
		        height: '90%',
		        columns: [
					{ display: '机构ID', name: 'organId', width: 250, align: 'left',hide:true},
					{ display: '编码', name: 'organNo', width: 250, align: 'left',hide:true},
		            { display: '机构名称', name: 'organName', width: 120, align: 'center' },
		            { display: '名称简写', name: 'abbr', width: 120,  align: 'center' },
		            { display: '机构类型', name: 'organType', width: 120,  align: 'center' ,render: function (row){
						if(row.organType == "01"){
							return "单位";
						}else if(row.organType == "02"){
							return "部门";
						}else if(row.organType == "03"){
							return "岗位";
						}
			        }},
		            { display: '负责人', name: 'dutyNo', width: 120,  align: 'center' },
		            { display: '联系电话', name: 'mobile', width: 120,  align: 'center' },
		            { display: '传真', name: 'fax', width: 120,  align: 'center' },
		            { display: '系统来源', name: 'sysType', width: 120,  align: 'center' },
		            { display: '排列顺序', name: 'sortNo', width: 120,   type: 'int', align: 'center' },
		            { display: '是否有效', name: 'isactive', width: 100, type: 'int', align: 'center' ,render: function (row){
						if(row.isactive == "1"){
							return "<img src='/s9/res/img/yes.gif'>";
						}else{
							return "<img src='/s9/res/img/no.gif'>";
						}
			        }},
			        { display: '备注', name: 'remark', width: 120,  align: 'center' }
		            ], data: data, tree: { columnName: 'organName' }, pageSize:20,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize"
			       
		    });
			}
		});
		$("#pageloading").hide();
	}
		
	/**
	 * 初始化添加页面
	 * @param parentId
	 * @return
	 */
	function toAdd(){
		var url= "/c/organ/forAddOrgan" ;
		m = $.ligerDialog.open({ url: url, height: 580,width:700, title:'添加组织机构',isResize: true ,top:50}); 
	}
	
	/**
	 * 初始化编辑页面
	 * @param organId
	 * @return
	 */
	function show(){
		//获取选中记录行
   		var rowid=manager.getSelecteds();
   		var length=rowid.length;
		if(length ==0){
			$.ligerDialog.error("请选择需要查看的机构！");
			return false;
		}
		if(length > 1){
			$.ligerDialog.error("只能选择一个机构信息进行查看！");
			return false;
		}
		var organId = rowid[0].organId ;
		var url= "/c/organ/forEditOrgan?organId="+organId ;
		m = $.ligerDialog.open({ url: url, height: 580,width:700, title:'编辑组织机构',isResize: true ,top:50}); 
	}
	
	/**
	 * 启用机构
	 * @return
	 */
	function start(){
		updOrgStatus('1');
	}
	/**
	 * 停用机构 
	 * @return
	 */
	function stop(){
		updOrgStatus('0');
	}
	/**
	 * 机构启用和停用操作
	 * @return
	 */
	function updOrgStatus(isactive){
   		var organIds ='' ;
   		//获取选中记录行
   		var rowid=manager.getSelecteds();
   		var length=rowid.length;
		if (length ==0){
			$.ligerDialog.error("请选择要启用或停用的机构记录！");
			return false;
		}else if(length > 0){
			 if($.ligerDialog.confirm('确定要启用、停用选择的'+rowid.length+'条记录吗', 
				function (yes) { 
					if(yes){
						var url = "/c/organ/updOrgStatus";
						//获取需要操作的用户ID
						for(var i=0;i<length;i++){
							if(i<length-1){
								organIds+=rowid[i].organId+",";	
							}else{
								organIds+=rowid[i].organId;
							}
						}
						$.ajax({
							type : "post",
							url : url,
							data : {
								"organIds" : organIds,
								"isactive" : isactive
							},
							success : function(data) {
								//如果删除成功，则刷新页面！
								if(data["msg"]=="sucess" && isactive=="1") {
									$.ligerDialog.success("机构启用成功！");
								}else if(data["msg"]=="error" && isactive=="1") {
									$.ligerDialog.error("机构启用失败！");
								}else if(data["msg"]=="sucess" && isactive=="0") {
									$.ligerDialog.success("机构停用成功！");
								}else if(data["msg"]=="error" && isactive=="0") {
									$.ligerDialog.error("机构停用失败！");
								}else{
									$.ligerDialog.error(data["msg"]);
								}									
								//启用、停用后列表刷新
								manager.set({url:"/c/organ/findOrganList"});
							},
							error : function() {
								$.ligerDialog.error("启用、停用机构信息失败！");
							}
						});
					}
				}));
		}
	}
	/**
	 * 删除机构 
	 * @return
	 */
	function  del(){
		var organIds ='' ;
   		//获取选中记录行
   		var rowid=manager.getSelecteds();
   		var length=rowid.length;
		if (length !=1 ){
			$.ligerDialog.error("请选择一条要删除的机构记录！");
			return false;
		}else{
			if(manager.hasChildren(rowid[0])){
				if($.ligerDialog.confirm(rowid[0].organName+"有子组织机构是否删除？", 
						function (yes) { 
							if(yes){
								delOrgans(rowid[0].organId);
							}
				}));
			}else{
				delOrgans(rowid[0].organId);
			}
		}
	}
	
	function delOrgans(organIds){
		
		var url = "/c/organ/delOrgan";
		$.ajax({
			type : "post",
			url : url,
			data : {
				"organIds" : organIds
			},
			success : function(data) {
				//如果删除成功，则刷新页面！
				if(data["msg"]=="sucess" ) {
					$.ligerDialog.success("机构删除成功！");
				}else if(data["msg"]=="error") {
					$.ligerDialog.error("机构删除失败！");
				}else{
					$.ligerDialog.error(data["msg"]);
				}									
				//启用、停用后列表刷新
				manager.set({url:"/c/organ/findOrganList"});
			},
			error : function() {
				$.ligerDialog.error("删除机构信息失败！");
			}
		});
		
	}
	/*
	 * 返回重新刷新列表
	 */
	function reload(backInfo){
		$.ligerDialog.success(backInfo);
		manager.set({url:"/c/organ/findOrganList"});
		//manager.loadData(); //重新加载不查询数据库
		m.close();
	}
	
	
	
	