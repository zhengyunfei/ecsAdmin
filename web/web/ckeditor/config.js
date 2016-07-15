var editor;
var html="";
var config = {};
config.width = '99%';//编辑器的宽度
config.height = 650;//编辑器的高度
config.toolbar ='Full';

//config.skin='office2003';
config.language = 'zh-cn';
config.toolbarCanCollapse=false;
config.toolbarLocation='top';
config.toolbarStartupExpanded =true;
config.filebrowserUploadUrl = '../fileUpload.shtml?type=File' ;
config.filebrowserImageUploadUrl = '../fileUpload.shtml?type=Image' ;
//config.toolbar = 'Basic'; //基础
//config.toolbar_Full = [//自定义
//['Image','Smiley','SpecialChar']];
// 图片浏览配置
config.filebrowserImageBrowseUrl = '../browerServer.shtml?type=image';
config.resize_enabled = true;
CKEDITOR.on('instanceReady', function (e) {
	//$("#cke_content").hide();
})
CKEDITOR.on('dialogDefinition',function (ev) {
	var dialogName = ev.data.name;
	var dialogDefinition = ev.data.definition;
	dialogDefinition.removeContents('advanced');
	dialogDefinition.removeContents('Link');
});

