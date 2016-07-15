var dialog=null;
$(function(){ 
	var win=null;
	init();
    //新增
	$("#add").bind("click", function() {
		var url = "../picType/forAddInit.shtml";
		dialog = $.ligerDialog.open({ url: url, width:650,height: 550, title:'添加图片轮播图分类',showMax: true, showToggle: true, showMin: true, isResize: true ,top:50}); 
	});
	//查询
	$("#search").bind('click',function(){
		search();
	})
	function search(){
		var url="../picType/forInit.shtml";
		$("#form").attr('action',url);
		$('#form').submit();
	}
	//编辑
	$("#edite").bind('click',function(){
		var obj=$("input:radio:checked");
		if(obj.length==0){
			$.ligerDialog.error("请选中要编辑的内容");
			return;
		}else if(obj.length>1){
			$.ligerDialog.error("只能同时编辑一行内容");
			return;
		}
		var id=$(obj[0]).attr('id');
		var url="../picType/forFindInit.shtml?id="+id;
		dialog = $.ligerDialog.open({ url: url, height: 350,width:450, title:'添加图片轮播图分类',isResize: true ,top:50}); 
	})
	$("#editeSave").bind('click',function(){
		var data=$("#editeForm").serialize();
		var url="../picType/update.shtml";
		 $.ajax({
					url:url,
					data:data,
					dataType:'json',
					success:function(msg){
						if(msg>0){
								parent.document.location.href="../picType/forInit.shtml?backInfo=update";
						}
						
					},error:function(){
						$.ligerDialog.error("服务器异常");
						return;
					}
				});
	})
	//删除
	$("#delete").bind("click",function(){
		var obj=$("input:radio:checked");
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
		var url="../picType/delete.shtml";
	    $.ligerDialog.confirm("确定要删除吗?", function (yes)
		           {
		               if(yes){
		            	   $.ajax({
							url:url,
							data:{"ids":id},
							dataType:'json',
							success:function(msg){
								if(msg.message=='1');{
									$.ligerDialog.success("删除成功",function(){
										document.location.href="../picType/forInit.shtml";
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

	//关闭弹窗
	function close(){
		 dialog.close();
		 search();
	}
    //点击行事件，查询行事件
    $(".dg-row").bind("click",function(){
    	var articleId=$(this).attr('id');
    	var $selectTr=$(this);
    	var obj=$("input:radio");
    	$(obj).each(function(){
    		var id=$(this).attr('id');
    		
			if(id==articleId){
				this.checked=true;
				$("#"+id).attr('bgcolor','#DAFAA2');
			}else if(id!='selectAll'){
				this.checked=false;
				$("#"+id).attr('bgcolor','');
			}
		});
    	var obj=$("input:checkbox:checked");
    	clickRow(articleId);
    });
    //点击行事件
    function clickRow(articleId){
    	var url="../picManage/forFindInit.shtml?pid="+articleId;
    	$("#detailIFrame").attr("src",url);
    }
  
 //全选
    $("#selectAll").live('click',function(){
    	var obj=$("input:checkbox");
    	var all=$("#selectAll").attr('checked');
    	$(obj).each(function(){
    		if($(this).attr('id')!='selectAll'){
    			if(all=='checked'){
    				this.checked=true;
    				$(this).parents("td:first").parents("td:first").attr('bgcolor','#DAFAA2');
    			}else{
    				this.checked=false;
    				$(this).parents("td:first").parents("td:first").attr('bgcolor','');
    			}
    			
	    	}
    	})
    	});
    	$("#save").bind('click',function(){
			var url="../picType/add.shtml";
			var name=$("#name").val();
			if(name==''){
					$.ligerDialog.error("图片分类名称不能为空");
				return;
			}
			$("#form").attr('onsubmit','');
			var data=$("#form").serialize();
			ajax(url,data);
	  })
	function ajax(url,data){
				$.ajax({
				 url:url,
				 type:"post",
				 dataType:"json",
				  data:data,
				  async:false, 
				  beforeSend:function(XMLHttpRequest){
				  },
				  success: function(data){
				  		if(data>0){
				  			parent.document.location.href="../picType/forInit.shtml?backInfo=add";
				  		}
				  },
				  error:function(){
				  	$.ligerDialog.error("服务器异常");
				  	return;
				  }
	  });
	}
    function init(){
    	var backInfo=$("#backInfo").val();
    	if(backInfo!=''&&backInfo!=null){
    		if(backInfo=='add'){
    			$.ligerDialog.success("保存成功");
    		}else if(backInfo=='update'){
    			$.ligerDialog.success("更新成功");
    		}
    		
    	}
    	//右侧页面初始化
    	var obj=$("input:radio");
    	//获取选中的
    	var pid=$(obj).attr('id');
		var url='../picManage/forFindInit.shtml';
		if(typeof(pid)!='undefined'){
			url+='?pid='+pid;
		}
    	$("#detailIFrame").attr('src',url);
    }
});  