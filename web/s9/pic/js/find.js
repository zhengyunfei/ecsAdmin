
$(function(){ 
	init();
    //新增
	$("#add").bind("click", function() {
		var pid=$("#pid").val();
		var url = "../picManage/forAddInit.shtml?pid="+pid;
		dialog = $.ligerDialog.open({ url: url, height: 600,width:800, title:'添加图片',showMax: true, showToggle: true, showMin: true, isResize: true ,top:50}); 
	});
	//保存
	$('#save').bind('click',function(){
		var name=$("#name").val();
		var attachmentUrl=$('#attachmentUrl').val();
		var url=$("#url").val();
		if(name==''||name==null){
			$.ligerDialog.error('图片名称不能为空');
			return;
		}
		if(attachmentUrl==''||attachmentUrl==null){
			$.ligerDialog.error('轮播图不能为空');
			return;
		}
		if(url==''||url==null){
			$.ligerDialog.error('图片URL不能为空');
			return;
		}
		$("#form2").attr('onsubmit','');
		var url="../picManage/add.shtml";
		var data=$('#form2').serialize();
		ajax(url,data);
	})
	//初始化
	  function init(){
    	var backInfo=$("#backInfo").val();
    	if(backInfo!=''&&backInfo!=null){
    		if(backInfo=='add'){
    			$.ligerDialog.success("保存成功");
    		}else if(backInfo=='update'){
    			$.ligerDialog.success("更新成功");
    		}
    		
    	}
    }
	//编辑
	$("#edite").bind('click',function(){
		var obj=$("input:checkbox:checked");
		if(obj.length==0){
			$.ligerDialog.error("请选中要编辑的内容");
			return;
		}else if(obj.length>1){
			$.ligerDialog.error("只能同时编辑一行内容");
			return;
		}
		var id=$(obj[0]).attr('id');
		var url="../picManage/forUpdateInit.shtml?id="+id;
		dialog = $.ligerDialog.open({ url: url, height: 600,width:800, title:'编辑轮播图',showMax: true, showToggle: true, showMin: true, isResize: true ,top:50}); 
	});
	//编辑保存事件
	$("#editeSave").bind('click',function(){
		var data=$("#editeForm").serialize();
		var url="../picManage/update.shtml";
		ajax(url,data);
	})
	//删除
	$("#delete").bind("click",function(){
		var obj=$("input:checkbox:checked");
		if(obj.length==0){
			$.ligerDialog.error("请选中要删除的内容");
			return;
		}
		var id='';
		for(var i=0;i<obj.length;i++){
			if(i<obj.length-1){
				id+=$(obj[i]).attr('id')+",";
			}else{
				id+=$(obj[i]).attr('id');
			}
			
		}
		var pid=$("#pid").val();
		var url="../picManage/delete.shtml";
	    $.ligerDialog.confirm("确定要删除吗?", function (yes)
		           {
		               if(yes){
		            	   $.ajax({
							url:url,
							data:{"ids":id},
							dataType:'json',
							success:function(msg){
								if(msg>0);{
									$.ligerDialog.success("删除成功",function(){
										document.location.href="../picManage/forFindInit.shtml?pid="+pid;
									})
								}
								
							},error:function(){
								$.ligerDialog.error("服务器异常");
								return;
							}
						});
		            	   }
		            });
	});
	
	//ajax方法提交
	function ajax(url,data){
		$.ajax({
					url:url,
					data:data,
					type:"post",
					dataType:'json',
					async:false, 
					beforeSend:function(XMLHttpRequest){
					},
					success:function(msg){
						if(msg.count>0){
							var id=msg.id;
							var pid=msg.pid;
							parent.location.reload();
							//parent.document.location.href="/c/picManage/forFindInit?backInfo=update&pid="+pid;
						}
						
					},error:function(){
						$.ligerDialog.error("服务器异常");
						return;
					}
				});
	}
	
	    //点击行事件，查询行事件
    $(".dg-row").bind("click",function(){
    	var tid=$(this).attr('id');
    	var $selectTr=$(this);
    	var obj=$("input:checkbox");
    	$(obj).each(function(){
    		var id=$(this).attr('id');
			if(id==tid){
				this.checked=true;
				$("#"+id).attr('bgcolor','#DAFAA2');
			}else if(id!='selectAll'){
				this.checked=false;
				$("#"+id).attr('bgcolor','');
			}
		});
    	var obj=$("input:checkbox:checked");
    });
    //全选
        $("#selectAll").live('click',function(){
    	var obj=$("input:checkbox");
    	var all=$("#selectAll").attr('checked');
    	$(obj).each(function(){
    		if($(this).attr('id')!='selectAll'){
    			if(all=='checked'){
    				this.checked=true;
    				$(this).parents("tr:first").attr('bgcolor','#DAFAA2');
    			}else{
    				this.checked=false;
    				$(this).parents("tr:first").attr('bgcolor','');
    			}
    			
	    	}
    	})
    	});
});