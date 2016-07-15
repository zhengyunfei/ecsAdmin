
$(function(){
	 var editor;
	var html="";
	var config = {};

	window.onload= function() {

	config.width = 800;//编辑器的宽度
	config.height = 400;//编辑器的高度
	config.toolbar = 'Full'; //全能
	config.skin='kama';
	config.language = 'zh-cn';
	config.uiColor ='#ebe';
	config.toolbarCanCollapse=false;
	config.toolbarLocation='top';
	config.toolbarStartupExpanded =true;
	config.filebrowserUploadUrl = '/fileUpload.shtml?type=File' ;
	config.filebrowserImageUploadUrl = '/fileUpload.shtml?type=Image' ;
	config.image_previewText=' '; //预览区域显示内容
	//config.toolbar = 'Basic'; //基础
	//config.toolbar_Full = [//自定义
	//['Image','Smiley','SpecialChar']];
	// 图片浏览配置
    config.filebrowserImageBrowseUrl = '/browerServer.shtml?type=image';
	config.resize_enabled = true;
	editor = CKEDITOR.replace( 'ckeditor', config);
	//editor = CKEDITOR.appendTo( 'test', config, html );
	}
	$(".cke_dialog_ui_button").live('click',function(){
		var text=$(this).html();
		if(text=='确定'){
		}
	});

	var url='../codeManage/getCodeValueAjax.shtml';
	   //初始化文章类型
		var combox=$("#articleTypeName").ligerComboBox({
	        width: 350,
	        checkbox:true,
			isMultiSelect: false,   //是否多选
            isShowCheckBox: false,  //是否选择复选框
	        selectBoxWidth: 350,
	        selectBoxHeight: 350,
	        valueField: 'id',
	        tree: {
					url: url+"?codeId=102",
					checkbox:false,
					ajaxType: 'post'
				}
	    });

		//保存事件
		$("#saveBtn").bind('click',function(){
			var articleType=$("#articleTypeName").ligerGetComboBoxManager().getValue();
			$("#articleType").val(articleType);
			alert(articleType);
			return;
			if(articleType==''){
				$.ligerDialog.error("文章类型不能为空！");
				return;
			}

			var articleTitle=$("#articleTitle").val();
			if(articleTitle==''){
				$.ligerDialog.error("文章标题不能为空！");
				return;
			}
		    var content=$("#xh_editor").val();
		    if(content==''){
		    	$.ligerDialog.error("文章内容不能为空！");
				return;
		    }
		    //获取文章属性信息
		    var keyArr=new Array();
			 var valueArr=new Array();
			 var jsonStr='';
			$("input[name$='keys']").each(function(){
				var value=$(this).val();
				 if(value!=''){
					 keyArr.push(value);
				 }
			 });
			$("input[name$='values']").each(function(){
				var value=$(this).val();
				 if(value!=''){
					 valueArr.push(value);
				 }
			 });
			for(var i=0;i<keyArr.length;i++){
				if(i<keyArr.length-1){
					jsonStr+=keyArr[i]+":"+valueArr[i]+",";
				}else{
					jsonStr+=keyArr[i]+":"+valueArr[i];
				}
			}
		    //获取文章附件信息
		    var ArticleAttach='';
		    var itemList=$(".uploadify-queue-item");
		    var AttachArray = '';
		    itemList.each(function(index){
		    	   var ArticleAttach = new Object();
		    		ArticleAttach.filePath=$(this).attr('filepath');
		    		ArticleAttach.fileName=$(this).attr('filename');
		    		ArticleAttach.uploadAuthor=$(this).attr('uploadauthor');
		    		ArticleAttach.uploadTime=$(this).attr('uploadtime');
		    		var finalInfoStr = JSON.stringify(ArticleAttach);
		    		if(index<itemList.length-1){
		    			AttachArray+=finalInfoStr+",";
		    		}else{
		    				AttachArray+=finalInfoStr;
		    		}

		    });
		    $("#form1").attr("onsubmit","");
		    var content=editor.getData();
		    $("#content").val(content);
		    var url="addArticle?articleAttrArr="+jsonStr+"&articleAttachmentArr="+AttachArray;
		    var data=$("#form1").serialize();
		    $.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:data,
			async:false,
			beforeSend:function(XMLHttpRequest){
			},
			success:function(msg){
				$.ligerDialog.success("保存成功",function(){
					window.close();
					opener.location.reload();
				})

			},error:function(){
				$.ligerDialog.error("服务器异常");
				return;
			}});

		});
})

