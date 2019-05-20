<%--suppress HtmlFormInputWithoutLabel --%>
<%--suppress JSJQueryEfficiency --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>点点租</title>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/search.css"/>
    <script src="js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>

<script type="text/javascript">
    let keyword;
    let filter = {};
    let pageIndex = 0;
    let pageNum = 0;

    window.onload = function () {
        let request = window.location.search;
        keyword = http.getParameter(request, "keyword");
        if (keyword) {
            keyword = decodeURI(keyword);
            document.getElementById("keyword_input").setAttribute("value", keyword);
        }
        filter.checkedDistrictId = http.getParameter(request, "district_id");
        filter.checkedZoneId = http.getParameter(request, "zone_id");
        filter.checkedMetroId = http.getParameter(request, "metro_id");
        filter.checkedTypeId = http.getParameter(request, "type_id");
        filter.checkedAreaRangeId = http.getParameter(request, "area_range_id");
        filter.checkedPriceRangeId = http.getParameter(request, "price_range_id");
        filter.checkedDecorationId = http.getParameter(request, "decoration_id");
        getFilterList();
    };

    function getFilterList() {
        http.post({
            url: "filter/all",
            params: {
                district_id: filter.checkedDistrictId,
                zone_id: filter.checkedZoneId,
                metro_id: filter.checkedMetroId,
                type_id: filter.checkedTypeId,
                area_range_id: filter.checkedAreaRangeId,
                price_range_id: filter.checkedPriceRangeId,
                decoration_id: filter.checkedDecorationId,
            },
            success: function (data) {
                filter = data;
                getBuildingList();
            }
        });
    }

    function getBuildingList(page) {
        http.post({
            url: "search/buildings",
            params: {
                keyword: keyword,
                district_id: filter.checkedDistrictId,
                zone_id: filter.checkedZoneId,
                metro_id: filter.checkedMetroId,
                type_id: filter.checkedTypeId,
                area_range_id: filter.checkedAreaRangeId,
                price_range_id: filter.checkedPriceRangeId,
                decoration_id: filter.checkedDecorationId,
                page: page,
            },
            success: function (data) {
                filter.checkedDistrictId = data.checkedDistrictId;
                filter.checkedZoneId = data.checkedZoneId;
                filter.checkedMetroId = data.checkedMetroId;
                filter.checkedTypeId = data.checkedTypeId;
                filter.checkedAreaRangeId = data.checkedAreaRangeId;
                filter.checkedPriceRangeId = data.checkedPriceRangeId;
                filter.checkedDecorationId = data.checkedDecorationId;
                setFilterList();
                pageIndex = data.pageIndex;
                pageNum = data.pageNum;
                setBuildingList(data.buildingList);
            },
        });
    }

    function setFilterList() {
        if (!filter) {
            return;
        }
        setFilterListByName(filter.districtList, 'districtList', filter.checkedDistrictId);
        let zoneList = filter.districtList[filter.checkedDistrictId - 1].zoneList;
        setFilterListByName(zoneList, 'zoneList', filter.checkedZoneId);
        setFilterListByName(filter.metroList, 'metroList', filter.checkedMetroId);
        setFilterListByName(filter.typeList, 'typeList', filter.checkedTypeId);
        setFilterListByName(filter.areaRangeList, 'areaRangeList', filter.checkedAreaRangeId);
        setFilterListByName(filter.priceRangeList, 'priceRangeList', filter.checkedPriceRangeId);
        setFilterListByName(filter.decorationList, 'decorationList', filter.checkedDecorationId);
    }

    function setFilterListByName(list, name, checkedId) {
        if (!list || list.length < 2) {
            $("#" + name.substr(0, name.length - 4)).hide();
        } else {
            $("#" + name.substr(0, name.length - 4)).show();
        }
        let conditionBox = document.getElementById(name);
        conditionBox.innerHTML = "";
        for (let i = 0; list && i < list.length; i++) {
            let optionItem = document.createElement("span");
            if (list[i].id === checkedId) {
                optionItem.className = 'condition_option_select';
            } else {
                optionItem.className = 'condition_option';
            }
            optionItem.innerHTML = list[i].name;
            optionItem.onclick = function () {
                switch (name) {
                    case "districtList":
                        filter.checkedDistrictId = list[i].id;
                        break;
                    case "zoneList":
                        filter.checkedZoneId = list[i].id;
                        break;
                    case "metroList":
                        filter.checkedMetroId = list[i].id;
                        break;
                    case "typeList":
                        filter.checkedTypeId = list[i].id;
                        break;
                    case "areaRangeList":
                        filter.checkedAreaRangeId = list[i].id;
                        break;
                    case "priceRangeList":
                        filter.checkedPriceRangeId = list[i].id;
                        break;
                    case "decorationList":
                        filter.checkedDecorationId = list[i].id;
                        break;
                }
                getBuildingList();
            };
            conditionBox.appendChild(optionItem);
        }
    }

    function setBuildingList(buildingList) {
        let dataListBox = document.getElementById("data_list_box");
        if (pageIndex === 0) {
            dataListBox.innerHTML = "";
        }
        if (pageNum > pageIndex + 1) {
            $("#next_page").show();
        } else {
            $("#next_page").hide();
        }
        if (pageIndex === 0 && buildingList.length === 0) {
            $("#error_not_text").show();
        } else {
            $("#error_not_text").hide();
        }

        for (let i = 0; i < buildingList.length; i++) {
            let building = buildingList[i];

            let conditionBox = document.createElement("div");

            conditionBox.className = "data_item_box flex_row";
            let imgLeft = document.createElement("img");
            imgLeft.className = 'item_image clickable';
            imgLeft.setAttribute("src", building.img_list[0]);
            imgLeft.onclick = function () {
                openDetail(building.id);
            };
            conditionBox.appendChild(imgLeft);

            let dataInfoBox = document.createElement("div");
            dataInfoBox.className = 'data_info_box flexed_column';
            conditionBox.appendChild(dataInfoBox);

            let itemName = document.createElement("div");
            itemName.className = 'item_name clickable';
            itemName.innerHTML = building.name;
            itemName.onclick = function () {
                openDetail(building.id);
            };
            dataInfoBox.appendChild(itemName);

            let itemAddress = document.createElement("div");
            itemAddress.className = 'item_address item_margin';
            let item_title = document.createElement("span");
            item_title.className = "item_title";
            item_title.innerHTML = "地址：";
            let district_name = document.createElement("a");
            district_name.className = "hover_de clickable";
            district_name.innerHTML = building.district_name;
            district_name.onclick = function () {
                window.open("search.jsp?district_id=" + building.district_id);
            };
            let zone_name = document.createElement("a");
            zone_name.className = "hover_de clickable";
            zone_name.innerHTML = building.zone_name;
            zone_name.onclick = function () {
                window.open("search.jsp?district_id=" + building.district_id
                    + "&zone_id=" + building.zone_id
                );
            };
            itemAddress.appendChild(item_title);
            itemAddress.append("[ ");
            itemAddress.appendChild(district_name);
            itemAddress.append(" ] - [ ");
            itemAddress.appendChild(zone_name);
            itemAddress.append("] |  " + building.address);
            dataInfoBox.appendChild(itemAddress);

            let itemSize = document.createElement("div");
            itemSize.className = 'item_size item_margin';
            dataInfoBox.appendChild(itemSize);

            itemSize.innerHTML = "<span class=\"item_title\">面积：</span>" + building.area_range;

            let itemPosition = document.createElement("div");
            itemPosition.className = 'item_position item_margin';
            itemPosition.innerHTML = "共有" + building.office_num + "个房源";
            dataInfoBox.appendChild(itemPosition);

            let itemSizeBtnBox = document.createElement("div");
            itemSizeBtnBox.className = 'item_size_btn_box flex_row';
            dataInfoBox.appendChild(itemSizeBtnBox);

            for (let j = 0; j < building.area_list.length && j < 4; j++) {
                let itemSizeBtn = document.createElement("div");
                itemSizeBtn.className = 'item_size_btn hover_de clickable';
                itemSizeBtn.innerHTML = building.area_list[j] + "m²"; // -----------------------
                itemSizeBtnBox.appendChild(itemSizeBtn);
                itemSizeBtn.onclick = function () {
                    openDetail(building.id);
                }
            }

            let itemPrice = document.createElement("div");
            itemPrice.className = 'item_price';
            itemPrice.innerHTML = "<span class=\"item_price_num\">" +
                building.price_average + // -----------------------
                "</span>元/m²/天";
            conditionBox.appendChild(itemPrice);
            dataListBox.appendChild(conditionBox);
        }
    }

    function nextPage() {
        getBuildingList(pageIndex + 1);
    }

    function search() {
        let searchInput = document.getElementById("top_search");
        keyword = searchInput.value;
        getBuildingList();
    }

    function openDetail(building_id) {
        window.open("buildingDetail.jsp?building_id=" + building_id, "_blank");
    }
</script>

<body class="bg_gray">
<div class="title_box">
    <div class="win flex_row">
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
    <div class="win top_box flex_row">
        <img src="" class="top_logo" alt=""/>
        <a href="" class="black_a logo_right">上海写字楼出租</a>
        <div class="top_search_box flex_row">
            <form action="">
                <input type="text" class="top_search" name="keyword" id="keyword_input"
                       placeholder="输入您要查找的楼盘或者区域商圈名称"/>
                <input type="submit" class="top_search_btn clickable" value="搜索"/>
            </form>
        </div>
    </div>
</div>
<div class="matop bg_white">
    <div class="win condition_box" id="condition_box">
        <div class="condition_line flex_row">
            <div class="condition_title" id="district">
                区：
            </div>
            <div class="option_box flex_row" id="districtList">
            </div>
        </div>
        <div class="condition_line flex_row" id="zone">
            <div class="condition_title">
                区域：
            </div>
            <div class="option_box flex_row" id="zoneList">
            </div>
        </div>
        <div class="condition_line flex_row" id="metro">
            <div class="condition_title">
                地铁：
            </div>
            <div class="option_box flex_row" id="metroList">
            </div>
        </div>
        <div class="condition_line flex_row" id="type">
            <div class="condition_title">
                类型：
            </div>
            <div class="option_box flex_row" id="typeList">
            </div>
        </div>
        <div class="condition_line flex_row" id="areaRange">
            <div class="condition_title">
                面积：
            </div>
            <div class="option_box flex_row" id="areaRangeList">
            </div>
        </div>
        <div class="condition_line flex_row" id="priceRange">
            <div class="condition_title">
                价格：
            </div>
            <div class="option_box flex_row" id="priceRangeList">
            </div>
        </div>
        <div class="condition_line flex_row" id="decoration">
            <div class="condition_title">
                装修：
            </div>
            <div class="option_box flex_row" id="decorationList">
            </div>
        </div>
    </div>
</div>
<div class="content_box matop win flex_row">
    <div class="content_left bg_white">
        <h3 class="sec_title">默认排序</h3>
        <div class="error_not_text" id="error_not_text">
            抱歉，暂无数据！
        </div>
        <div class="data_list flex_column" id="data_list_box">
            <div class="data_item_box flex_row" id="dataList">
            </div>
        </div>

        <div class="paging" id="next_page">
            <span class="paging_item" onclick="nextPage()">
                下一页
            </span>
        </div>
    </div>
    <div class="content_right bg_white">
        <div class="contact_way">
            <div class="contact_info">
                <img src="" class="contact_img" alt=""/>
                <div class="service_box">
                    <span class="service_name">妥妥租</span>上海写字楼专属顾问
                </div>
            </div>
            <div class="service_phone flex_row">
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
                <img src="" class="content_right_title_img" alt=""/>热门楼盘
            </div>
        </div>
    </div>
</div>
<div class="bottom_box flex_column">
    <div class="win">
        <div class="flex_row bottom_top">
            <div class="foot_l">
                <div class="ad_title">办公选址上妥妥租，妥妥的 !</div>
                <a href="https://www.tuotuozu.com/sh/zxd/id/9.html">关于妥妥租</a> -
                <a href="https://www.tuotuozu.com/sh/zxd/id/10.html">服务介绍</a> -
                <br/> ©2017-2018 京ICP备17066298号-1 地址：北京市朝阳区凯旋城E座1807
            </div>
            <div class="foot_m">
                客服服务：（早9:00-晚21:00）<br/>
                <span>400-865-9520</span>
                <div class="flex_row">
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