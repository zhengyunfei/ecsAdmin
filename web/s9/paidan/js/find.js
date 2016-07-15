

$(function(){ 
		
				var config=CKEDITOR.editorConfig = function(config)
				{
				    config.extraPlugins = 'code';
				    config.filebrowserUploadUrl = '/fileUpload.shtml?type=File' ;
					config.filebrowserImageUploadUrl = '/fileUpload.shtml?type=Image' ;
				    config.filebrowserImageBrowseUrl = '/browerServer.shtml?type=image'; 
				    config.toolbar = [[ 'Image', 'Code' ]];
				};
				
			  CKEDITOR.replace('content',config);
 })