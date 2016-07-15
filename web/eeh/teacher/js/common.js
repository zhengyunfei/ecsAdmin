/**
 * Created by Administrator on 2016/3/1.
 */
/*
 * 验证表单数据
 */
function validData() {
    var name = $("#name").val();
    if (name.replace(/(^\s*)|(\s*$)/, "").length == 0) {
        $.ligerDialog.warn("教师名称不能为空！");
        return false;
    }
    var subjectId=$("#subjectId").val();
    if(""==subjectId||null==subjectId){
        $.ligerDialog.warn("科目不能为空！");
        return;
    }
 /*  var gradeId=$("#gradeId").val();
    if(""==gradeId||null==gradeId){
        $.ligerDialog.warn("所属年级不能为空！");
        return;
    }*/
  /*  var classId=$("#classId").val();
    if(""==classId||null==classId){
        $.ligerDialog.warn("授课班级不能为空！");
        return;
    }*/
    return true;
}
function ajax(url,data){
    $.ajax( {
        type :"post",
        url :url,
        data :data,
        async : false,
        dataType : "json",
        beforeSend : function(XMLHttpRequest) {
        },
        timeout:20000,
        success : function(data) {
            if (data["success"]) {
                parent.reload("操作成功！");
            } else {
                parent.reload("操作失败！");
            }

        },
        error : function() {
            $.ligerDialog.error("操作失败！");
        }
    });
}
//记载科目
function loadKeMu(){
    //parentId:基础课程 基础课程
    var subjectClass=$("#pkemu").val();
    var url="../Subject/findListByQuery.shtml";
    var data={"subjectClass":subjectClass};
    var optId=$("#subjectId");
    loadOption(url,data,optId);
}
//加载班级
function loadBanJi(){
    //parentId:1 一年级
    var gradeName=$("#gradeId").val();
    var url="../grade/findListByQuery.shtml";
    var data={"gradeName":gradeName};
    var optId=$("#classId");
    loadOption(url,data,optId);
}
//动态创建option
function loadOption(url,data,optId){
    $.ajax( {
        type :"post",
        url :url,
        data :data,
        async : false,
        dataType : "json",
        beforeSend : function(XMLHttpRequest) {
        },
        timeout:20000,
        success : function(data) {
            var result=data.Rows;
            var count=data.Total;
            optId.empty();
            if(count==0){
                optId.append("<option value=''>请选择</option>");
            }else{
                for(var i=0;i<count;i++){
                    optId.append("<option value='"+result[i].name+"'>"+result[i].name+"</option>");
                }
            }


        },
        error : function() {
            $.ligerDialog.error("操作失败！");
        }
    });
}
