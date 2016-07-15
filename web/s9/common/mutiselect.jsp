<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%--
请再各页面的<head>区域<title>标签之后使用：
<%@include file="/s9/common/common.jsp"%>
引用本页面
--%>
<%--全局公共页面设置--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../s9/res/js/mutiselect/multiselectSrc/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="../s9/res/js/mutiselect/assets/style.css" />
<link rel="stylesheet" type="text/css" href="../s9/res/js/mutiselect/assets/prettify.css" />
<link rel="stylesheet" type="text/css" href="../s9/res/js/mutiselect/ui/jquery-ui.css" />

<script type="text/javascript" src="../s9/res/js/mutiselect/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="../s9/res/js/mutiselect/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../s9/res/js/mutiselect/assets/prettify.js"></script>
<script type="text/javascript" src="../s9/res/js/mutiselect/multiselectSrc/jquery.multiselect.js"></script>
<script type="text/javascript">
    $(function(){
        $("#tbId").multiselect({
            noneSelectedText: "请选择",
            checkAllText: "全选",
            uncheckAllText: '全不选',
            selectedList:3,
            minWidth:'200px'
        });

    });
    function showValues() {
        var valuestr = $("#sela").multiselect("MyValues");
        alert(valuestr);
    }
    </script>

