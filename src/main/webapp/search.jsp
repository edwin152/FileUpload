<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/search.css"/>
</head>

<script type="text/javascript">
    let data;

    window.onload = function () {
        getFilterList();
    };

    function getFilterList() {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                // console.log(xmlHttp.responseText);
                data = JSON.parse(xmlHttp.responseText);
                search();
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "info/filters", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send();
    }

    function search(id, keyword, business_center_id, page) {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                console.log(xmlHttp.responseText);
                let officeData = JSON.parse(xmlHttp.responseText);
                data.checkedDistrictId = officeData.checkedDistrictId;
                data.checkedZoneId = officeData.checkedZoneId;
                data.checkedMetroId = officeData.checkedMetroId;
                data.checkedTypeId = officeData.checkedTypeId;
                data.checkedAreaRangeId = officeData.checkedAreaRangeId;
                data.checkedPriceRangeId = officeData.checkedPriceRangeId;
                data.checkedDecorationId = officeData.checkedDecorationId;
                setFilterList();
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "search/offices", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        if (page === undefined || page === null) {
            page = 0;
        }
        xmlHttp.send("id=" + id === undefined ? "null" : id
            + "&keyword=" + keyword === undefined ? "null" : keyword
            + "&business_center_id=" + business_center_id === undefined ? "null" : business_center_id
            + "&district_id=" + data.checkedDistrictId
            + "&zone_id=" + data.checkedZoneId
            + "&metro_id=" + data.checkedMetroId
            + "&type_id=" + data.checkedTypeId
            + "&area_range_id=" + data.checkedAreaRangeId
            + "&price_range_id=" + data.checkedPriceRangeId
            + "&decoration_id=" + data.checkedDecorationId
            + "&page=" + page
        );
    }

    function setFilterList() {
        if (data === undefined) {
            return;
        }
        if (data.districtList !== undefined) {
            setListByName(data.districtList, 'districtList', data.checkedDistrictId);
        }
        let zoneList = data.districtList[data.checkedDistrictId - 1].zoneList;
        if (zoneList !== undefined) {
            setListByName(zoneList, 'zoneList', data.checkedZoneId);
        }
        if (data.metroList !== undefined) {
            setListByName(data.metroList, 'metroList', data.checkedMetroId);
        }
        if (data.typeList !== undefined) {
            setListByName(data.typeList, 'typeList', data.checkedTypeId);
        }
        if (data.areaRangeList !== undefined) {
            setListByName(data.areaRangeList, 'areaRangeList', data.checkedAreaRangeId);
        }
        if (data.priceRanges !== undefined) {
            setListByName(data.priceRanges, 'priceRanges', data.checkedPriceRangeId);
        }
        if (data.decorationList !== undefined) {
            setListByName(data.decorationList, 'decorationList', data.checkedDecorationId);
        }
    }

    function setListByName(list, name, checkedId) {
        let conditionBox = document.getElementById(name);
        let children = conditionBox.childNodes;
        for (let i = children.length - 1; i >= 0; i--) {
            conditionBox.removeChild(children[i]);
        }
        for (let i = 0; i < list.length; i++) {
            let optionItem = document.createElement("span");
            if (list[i].id === checkedId) {
                optionItem.className = 'condition_option_select';
            } else {
                optionItem.className = 'condition_option';
            }
            // optionItem.setAttribute("href", "#");
            optionItem.innerHTML = list[i].name;
            optionItem.onclick = function () {
                switch (name) {
                    case "districtList":
                        data.checkedDistrictId = list[i].id;
                        break;
                    case "zoneList":
                        data.checkedZoneId = list[i].id;
                        break;
                    case "metroList":
                        data.checkedMetroId = list[i].id;
                        break;
                    case "typeList":
                        data.checkedTypeId = list[i].id;
                        break;
                    case "areaRangeList":
                        data.checkedAreaRangeId = list[i].id;
                        break;
                    case "priceRanges":
                        data.checkedPriceRangeId = list[i].id;
                        break;
                    case "decorationList":
                        data.checkedDecorationId = list[i].id;
                        break;
                }
                search();
            };
            conditionBox.appendChild(optionItem);
        }
    }
</script>
<body class="bg_gray">
<div class="title_box">
    <div class="win flexed_row">
        <div class="title_text">
            上海
        </div>
        <div class="title_text">
            [切换城市]
        </div>
        <div class="title_text">
            |
        </div>
        <div class="title_text title_text_right">
            <a href="">首页</a>
        </div>
        <div class="title_text title_text_right title_obvious">
            <a href="">写字楼出租</a>
        </div>
        <div class="title_text title_text_right">
            <a href="">共享办公</a>
        </div>
        <div class="title_text title_text_right">
            <a href="">资讯中心</a>
        </div>
    </div>
</div>
<div class="bg_white">
    <div class="win top_box flexed_row">
        <img src="" class="top_logo"/>
        <a href="" class="black_a logo_right">上海写字楼出租</a>
        <div class="top_search_box flexed_row">
            <input type="text" class="top_search" name="search" id="top_search" value=""
                   placeholder="输入您要查找的楼盘或者区域商圈名称"/>
            <input class="top_search_btn" type="submit" value="搜索"/>
        </div>
    </div>
</div>
<div class="matop bg_white">
    <div class="win condition_box" id="condition_box">
        <div class="condition_line flexed_row">
            <div class="condition_title">
                区：
            </div>
            <div class="option_box flexed_row" id="districtList">
            </div>
        </div>
        <div class="condition_line flexed_row">
            <div class="condition_title">
                区域：
            </div>
            <div class="option_box flexed_row" id="zoneList">
            </div>
        </div>
        <div class="condition_line flexed_row">
            <div class="condition_title">
                地铁：
            </div>
            <div class="option_box flexed_row" id="metroList">
            </div>
        </div>
        <div class="condition_line flexed_row">
            <div class="condition_title">
                类型：
            </div>
            <div class="option_box flexed_row" id="typeList">
            </div>
        </div>
        <div class="condition_line flexed_row">
            <div class="condition_title">
                面积：
            </div>
            <div class="option_box flexed_row" id="areaRangeList">
            </div>
        </div>
        <div class="condition_line flexed_row">
            <div class="condition_title">
                价格：
            </div>
            <div class="option_box flexed_row" id="priceRanges">
            </div>
        </div>
        <div class="condition_line flexed_row">
            <div class="condition_title">
                装修：
            </div>
            <div class="option_box flexed_row" id="decorationList">
            </div>
        </div>
    </div>
</div>
<div class="content_box matop win flexed_row">
    <div class="content_left bg_white">
        <h3 class="sec_title">默认搜索</h3>
        <div class="error_not_text">
            抱歉，暂无数据！
        </div>
        -->
        <div class="data_list">
            <div class="data_item_box flexed_row" id="dataList">
            </div>
        </div>

    </div>
    <div class="content_right bg_white">
        <div class="contact_way">
            <div class="contact_info">
                <img src="" class="contact_img"/>
                <div class="service_box">
                    <span class="service_name">妥妥租</span>上海写字楼专属顾问
                </div>
            </div>
            <div class="service_phone flexed_row">
                <div class="icon_phone"></div>
                <div class="service_phone_text">
                    400-865-9520
                </div>
            </div>
            <div class="ready_see">
                预约看房
            </div>
            <input class="ready_phone" type="text" name="ready_phone" id="ready_phone" value="" placeholder="请输入手机号"/>
            <div class="contact_info">
                <div class="ready_see btn">
                    预约看房
                </div>
            </div>
            <div class="content_right_title">
                <img src="" class="content_right_title_img"/>热门楼盘
            </div>
        </div>
    </div>
</div>
<div class="bottom_box flexed_column">
    <div class="win">
        <div class="flexed_row bottom_top">
            <div class="foot_l">
                <div class="ad_title">办公选址上妥妥租，妥妥的 !</div>
                <a href="https://www.tuotuozu.com/sh/zxd/id/9.html">关于妥妥租</a> -
                <a href="https://www.tuotuozu.com/sh/zxd/id/10.html">服务介绍</a> -
                <br/> ©2017-2018 京ICP备17066298号-1 地址：北京市朝阳区凯旋城E座1807
            </div>
            <div class="foot_m">
                客服服务：（早9:00-晚21:00）<br/>
                <span>400-865-9520</span>
                <div class="flexed_row">
                    <div>SOCIAL MEDIA</div>
                    <div>
                        <a href="#">facebook</a>
                    </div>
                    <div>
                        <a href="#">twitter</a>
                    </div>
                    <div>
                        <a href="#">linkIn</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="foot_link">
            友情链接：
            <a href="https://www.haozu.com/sh/zuxiezilou/" target="_blank">上海写字楼出租</a>
            <script type="text/javascript">
                let cnzz_protocol = (("https:" === document.location.protocol) ? " https://" : " http://");
                document.write(unescape("%3Cspan id='cnzz_stat_icon_4383806'%3E%3C/span%3E%3Cscript src='" +
                    cnzz_protocol +
                    "s4.cnzz.com/stat.php%3Fid%3D4383806%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
            </script>
        </div>
    </div>
</div>
</body>

</html>
