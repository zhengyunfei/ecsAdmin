/**
 * Created by Administrator on 2015/12/22.
 */
var map ;
var localSearch;
$(function () {
    // 百度地图API功能
    map = new BMap.Map("allmap");
    map.centerAndZoom("北京", 16);
    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
    map.addControl(new BMap.OverviewMapControl({isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));   //右下角，打开
    localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
    map.addEventListener("click", function (e) {
        $("#email").val(e.point.lng);
        $("#remark").val(e.point.lat);
    });
})
function searchByStationName() {
    map.clearOverlays();//清空原来的标注
    var keyword = document.getElementById("expHintDate").value;
    localSearch.setSearchCompleteCallback(function (searchResult) {
        var poi = searchResult.getPoi(0);
        map.centerAndZoom(poi.point, 16);
        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
        map.addOverlay(marker);
        $("#email").val( poi.point.lng);
        $("#remark").val( poi.point.lat);
        $("#expHintDate").val($("#expHintDate").val());
        var content = document.getElementById("expHintDate").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
        var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
        marker.addEventListener("click", function () {
            this.openInfoWindow(infoWindow);
        });
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    });
    localSearch.search(keyword);
}