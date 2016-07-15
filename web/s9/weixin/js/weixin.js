var grid = null;//表格对象
var m; 	//弹出窗口对象
// 查询用户信息列表数据
function findVipInfoList(url){
	var pageSize=200;
	grid = $("#maingrid").ligerGrid({
		width:'100%',
		height:'100%',
		onAfterShowData:function()
		{
			$(".l-grid-row-cell-inner").css("height","auto"); //单元格高度自动化，撑开
			var i=0;
			$("tr",".l-grid2","#maingrid").each(function ()
			{
				$($("tr",".l-grid1","#maingrid")).css("height",$(this).height()); //2个表格的tr高度一致
				i++;
			});
		},
		headerRowHeight:28,
		rowHeight:26,
		checkbox: false,
		columns: [

			{ display: '昵称', name: 'nickname', width: '8%',minWidth: 100 },
			{ display: '头像', name: 'headimgurl', width: '15%',minWidth: 200,render:function(r){

				return '<img src='+r.headimgurl+' width="200" height="100">';
			}},
			{ display: '性别', name: 'sex',  width: '3%',minWidth: 30 },
			{ display: 'openid', name: 'openid', width: '20%',minWidth: 160},
			{ display: '国家', name: 'country', width: '4%',minWidth: 60 },
			{ display: '省份', name: 'province',  width: '5%' ,minWidth: 60},
			{ display: '城市', name: 'city', width: '5%' ,minWidth: 60},
			{ display: '关注时间', name: 'subscribe_time', width: '10%' ,minWidth: 160}


		], url:url, pageSize:20,enabledEdit: true,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize",onSelectRow:onClickRow
//          , onDblClickRow : function (data, rowindex, rowobj)
//                {
//    	  					 var obj=$(".l-dialog");
//    	  					 if(obj.length!=0){
//    	  						 $(obj).close();
//    	  					 }
//    	   			  $.ligerDialog.prompt('编辑备注',data.remark, function (yes,value) {
//    	   				if(yes){
//    	   					updateUserRemark(data.userId,value);
//    	   				}
//
//    	   			});
//    	   			   $(".l-dialog-close").live("click",function(){
//    	   				 var obj=$(".l-dialog");
//    	   				 obj.remove();
//    	   			 })
//                    //$.ligerDialog.alert('选择的是' + data.userId);
//                }

	});
	$("#pageloading").hide();


}
/**
 *重新加载数据
 */
function addUserInit(){
	var url="../wx/loadData.shtml";
	var secret=$("#secret").val();
	var appid=$("#appid").val();
	if(appid==''||null==appid){
		alert("请输入appid");
		return ;
	}else if(null==secret||''==secret){
		alert("请输入秘钥");
		return ;
	}else{
		$.ajax( {
			type :"post",
			url :url,
			data:{"appid":appid,"secret":secret},
			success : function(data) {
				alert("加载数据完成");
				grid.loadData();
			},
			error : function() {
				$.ligerDialog.error("error");
			}
		});
	}


}
/**
 * 拓展行点击事件radio选中
 **/
function onClickRow(rowdata, rowindex, rowDomElement) {
	var $v = $(rowDomElement);
	$v.find(":checkbox").attr("checked","checked")
}