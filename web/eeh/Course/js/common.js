/**
 * Created by Administrator on 2016/3/2.
 */
/*
 * 验证表单数据
 */
function validData() {

   /* var courseName = $("#courseName").val();
    if (courseName.replace(/(^\s*)|(\s*$)/, "").length == 0) {
        $.ligerDialog.warn("课程名称不能为空！");
        return false;
    }*/
    var kemu = $("#kemu").val();
    if (kemu.replace(/(^\s*)|(\s*$)/, "").length == 0) {
        $.ligerDialog.warn("科目名称不能为空！");
        return false;
    }
    var peopleMax=$("#peopleMax").val();
    if(""==peopleMax||null==peopleMax){
        $.ligerDialog.warn("上线人数不能为空！");
        return;
    }
    var teacher=$("#teacher").val();
    if(""==teacher||null==teacher){
        $.ligerDialog.warn("授课老师不能为空！");
        return;
    }
    var time=$("#time").find("option:selected").text();
    if(""==time||null==time){
        $.ligerDialog.warn("上课时间不能为空！");
        return;
    }else{
        var week=time.substr(0,2);
        var schoolTime=time.substr(3);
        $("#week").val(week);
        $("#schoolTime").val(schoolTime);
    }
    var classRoom=$("#classRoom").val();
    if(""==classRoom||null==classRoom){
        $.ligerDialog.warn("授课教室不能为空！");
        return;
    }
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
