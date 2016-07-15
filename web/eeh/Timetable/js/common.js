/**
 * Created by Administrator on 2016/3/1.
 */
/*
 * 验证表单数据
 */
function validData() {
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
