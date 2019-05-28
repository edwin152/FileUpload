
var markers = [];

function setMarkers(data) {
    addMapOverlay(data);
}
//创建和初始化地图函数：
function initMap() {
    createMap(); //创建地图
    setMapEvent(); //设置地图事件
    addMapControl(); //向地图添加控件
    addMapOverlay(markers); //向地图添加覆盖物
}

function createMap() {
    map = new BMap.Map("map");
    map.centerAndZoom(new BMap.Point(121.48054, 31.235929), 15);
}

function setMapEvent() {
    map.enableKeyboard();
    map.enableDragging();
    map.enableDoubleClickZoom()
}

function addClickHandler(target, window) {
    target.addEventListener("click", function() {
        target.openInfoWindow(window);
    });
}

function addMapOverlay(markers) {
    for(var index = 0; index < markers.length; index++) {
        var point = new BMap.Point(markers[index].position.lng, markers[index].position.lat);
        var imgSrc = "img/address1.png";
        var imgSrc2 = "img/address2.png";
        var marker = new BMap.Marker(point, {
            icon: new BMap.Icon("http://api.map.baidu.com/lbsapi/createmap/images/icon.png", new BMap.Size(20, 25), {
                imageOffset: new BMap.Size(markers[index].imageOffset.width, markers[index].imageOffset.height)
            })
        });
        var label = new BMap.Label(markers[index].title, {
            offset: new BMap.Size(25, 5)
        });
        var backGround = "#FFFFFF";
        var textColor = "#333333";
        if(markers[index].isSelect) {
            backGround = "#FFBB99";
            textColor = "#111111"
        }
        label.setStyle({
            backgroundColor: backGround,
            border: "1px solid #696969",
            borderRadius: "4px",
            color: textColor,
            padding : "5px"
        });
        var opts = {
            width: 200,
            title: markers[index].title,
            enableMessage: false
        };
        var infoWindow = new BMap.InfoWindow(markers[index].content, opts);
        marker.setLabel(label);
        addClickHandler(marker, infoWindow);
        map.addOverlay(marker);
    };
}
//向地图添加控件
function addMapControl() {
    var scaleControl = new BMap.ScaleControl({
        anchor: BMAP_ANCHOR_BOTTOM_LEFT
    });
    scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
    map.addControl(scaleControl);
    var navControl = new BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        type: BMAP_NAVIGATION_CONTROL_LARGE
    });
    map.addControl(navControl);
}
var map;