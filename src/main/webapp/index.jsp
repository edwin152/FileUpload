<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>点点租</title>
    <link rel="stylesheet" href="layUI/css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="css/swiper.min.css"/>
    <script src="layUI/layui.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/utils.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/swiper.jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        /**
         * 区域
         * @param conditionList
         */
        function setConditionScreen(conditionList) {
            let conditionBox = document.getElementById("screen_condition_box");
            for (let i = 0, len = conditionList.length; i < len && i < 10; i++) {
                if (conditionList[i].id === 1) {
                    continue;
                }
                let conditionItem = document.createElement("div");
                conditionBox.appendChild(conditionItem);
                let conditionText = document.createElement("a");
                conditionText.setAttribute("href", "search.jsp?district_id=" + conditionList[i].id);
                conditionText.innerHTML = conditionList[i].name;
                conditionItem.appendChild(conditionText);
            }
            let conditionItem = document.createElement("div");
            conditionBox.appendChild(conditionItem);
            let conditionText = document.createElement("a");
            conditionText.setAttribute("href", "search.jsp");
            conditionText.innerHTML = "更多>>";
            conditionItem.appendChild(conditionText);
        }

        /**
         * 类型
         * @param typeList
         */
        function setTypeScreen(typeList) {
            let typeBox = document.getElementById("screen_type_box");
            for (let i = 0, len = typeList.length; i < len; i++) {
                if (typeList[i].id === 1) {
                    continue;
                }
                let typeItem = document.createElement("a");
                typeItem.setAttribute("href", "search.jsp?type_id=" + typeList[i].id);
                typeBox.appendChild(typeItem);
                let typeImg = document.createElement("img");
                typeImg.setAttribute("class", "screen_type_icon");
                switch (typeList[i].id) {
                    case 2:
                        typeImg.setAttribute("src", "img/icon_type_1.png");
                        break;
                    case 3:
                        typeImg.setAttribute("src", "img/icon_type_2.png");
                        break;
                    case 4:
                        typeImg.setAttribute("src", "img/icon_type_3.png");
                        break;
                }
                typeItem.appendChild(typeImg);
                let typeText = document.createElement("div");
                typeText.setAttribute("class", "screen_type_text");
                typeText.innerHTML = typeList[i].name;
                typeItem.appendChild(typeText);
            }
        }

        /**
         * 面积
         * @param areaRangeList
         */
        function setAreaRangeScreen(areaRangeList) {
            let areaRangeBox = document.getElementById("screen_area_box");
            for (let i = 0, len = areaRangeList.length; i < len && i < 10; i++) {
                if (areaRangeList[i].id === 1) {
                    continue;
                }
                let areaRangeText = document.createElement("a");
                areaRangeText.setAttribute("class", "screen_option");
                areaRangeText.setAttribute("href", "search.jsp?area_range_id=" + areaRangeList[i].id);
                areaRangeBox.appendChild(areaRangeText);
            }
        }

        /**
         * 价格
         * @param priceRangeList
         */
        function setPriceRangeScreen(priceRangeList) {
            let priceRangeBox = document.getElementById("screen_price_box");
            for (let i = 0, len = priceRangeList.length; i < len && i < 10; i++) {
                if (priceRangeList[i].id === 1) {
                    continue;
                }
                let priceRangeText = document.createElement("a");
                priceRangeText.setAttribute("class", "screen_option");
                priceRangeText.setAttribute("href", "search.jsp?price_range_id=" + priceRangeList[i].id);
                priceRangeBox.appendChild(priceRangeText);
            }
        }

        /**
         * 设置核心圈
         * @param buildingList 长度不超过4个
         */
        function setCoreBuilding(buildingList) {
            let coreBuildingListBox = document.getElementById("core_building");
            for (let i = 0, len = buildingList.length; i < len && i < 4; i++) {
                let buildingItemBox = document.createElement("div");
                buildingItemBox.setAttribute("class", "image_item_in_title image_big clickable");
                coreBuildingListBox.appendChild(buildingItemBox);
                let buildingItemImage = document.createElement("img");
                buildingItemImage.setAttribute("src", buildingList[i].imageUrl);
                buildingItemBox.appendChild(buildingItemImage);
                let buildingItemText = document.createElement("div");
                buildingItemText.setAttribute("class", "text_in_image");
                buildingItemBox.appendChild(buildingItemText);
                let buildingItemTitle = document.createElement("div");
                buildingItemTitle.setAttribute("class", "title_in_image");
                buildingItemTitle.innerHTML = buildingList[i].title;
                buildingItemText.appendChild(buildingItemTitle);
                let buildingItemSubtitle = document.createElement("div");
                buildingItemSubtitle.setAttribute("class", "subtitle_in_image");
                buildingItemSubtitle.innerHTML = buildingList[i].subtitle;
                buildingItemText.appendChild(buildingItemSubtitle);
            }
        }

        /**
         * 设置精选写字楼
         * @param buildingList 长度为4的倍数
         */
        function setFineBuildingList(buildingList) {
            let fineBuildingListBox = document.getElementById("fine_building");
            for (let i = 0, len = buildingList.length; i < len; i++) {
                let buildingItemBox = document.createElement("div");
                buildingItemBox.setAttribute("class", "fine_building_item_box image_big flex_column clickable");
                fineBuildingListBox.appendChild(buildingItemBox);

                let buildingImageBox = document.createElement("div");
                buildingImageBox.setAttribute("class", "fine_building_image_box");
                buildingItemBox.appendChild(buildingImageBox);
                let buildingImage = document.createElement("img");
                buildingImage.setAttribute("src", buildingList[i].imageUrl);
                buildingImageBox.appendChild(buildingImage);

                let buildingTitle = document.createElement("a");
                buildingTitle.setAttribute("class", "fine_building_title");
                buildingTitle.innerHTML = buildingList[i].title;
                buildingItemBox.appendChild(buildingTitle);

                let buildingInfoBox = document.createElement("div");
                buildingInfoBox.setAttribute("class", "fine_building_info");
                buildingItemBox.appendChild(buildingInfoBox);

                let buildingAddressBox = document.createElement("div");
                buildingAddressBox.setAttribute("class", "flex_row");
                buildingInfoBox.appendChild(buildingAddressBox);
                let buildingAddressIcon = document.createElement("div");
                buildingAddressIcon.setAttribute("class", "icon_address");
                buildingAddressBox.appendChild(buildingAddressIcon);
                let buildingAddressText = document.createElement("span");
                buildingAddressText.setAttribute("class", "address_text");
                buildingAddressText.innerHTML = buildingList[i].address;
                buildingAddressBox.appendChild(buildingAddressText);

                let buildingPriceBox = document.createElement("div");
                buildingPriceBox.setAttribute("class", "fine_building_price");
                buildingInfoBox.appendChild(buildingPriceBox);
                let buildingPriceText = document.createElement("span");
                buildingPriceText.setAttribute("class", "price_number");
                buildingPriceText.innerHTML = buildingList[i].price;
                buildingPriceBox.appendChild(buildingPriceText);

            }
        }

        /**
         * 设置头部背景图
         */
        function setTopBackground(imageUrl) {
            let topBgBox = document.getElementById("top_bg_item_box");
            for (let i = 0, len = imageUrl.length; i < len; i++) {
                let itemBox = document.createElement("div");
                itemBox.setAttribute("class", "swiper-slide");
                topBgBox.appendChild(itemBox);
                let imgView = document.createElement("img");
                imgView.setAttribute("src", imageUrl[i]);
                itemBox.appendChild(imgView);
            }
        }

        window.onload = function () {
            let imgUrl = ["img/test.jpg", "img/test.jpg", "img/test.jpg", "img/test.jpg"];
            setTopBackground(imgUrl);
            getFilterList();
        };

        function getFilterList() {
            let request = window.location.search;
            let keyword = http.getParameter(request, "keyword");
            if (keyword) {
                keyword = decodeURI(keyword);
                document.getElementById("keyword_input").setAttribute("value", keyword);
            }
            let filter = {};
            filter.checkedDistrictId = http.getParameter(request, "district_id");
            filter.checkedZoneId = http.getParameter(request, "zone_id");
            filter.checkedMetroId = http.getParameter(request, "metro_id");
            filter.checkedTypeId = http.getParameter(request, "type_id");
            filter.checkedAreaRangeId = http.getParameter(request, "area_range_id");
            filter.checkedPriceRangeId = http.getParameter(request, "price_range_id");
            filter.checkedDecorationId = http.getParameter(request, "decoration_id");
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
                    setConditionScreen(filter.districtList);
                    setTypeScreen(filter.typeList);
                    setAreaRangeScreen(filter.areaRangeList);
                    setPriceRangeScreen(filter.priceRangeList);

                    // TODO
                    setCoreBuilding(buildingList);
                    setFineBuildingList(fineList);

                    openSwiper();
                }
            });
        }

        function openSwiper(){
            // 启动轮播图
            new Swiper('#top_bg', {
                loop: true,
                autoplay: 2000,
                pagination: '#top_bg_position',
                paginationClickable: true,
                autoplayDisableOnInteraction: false,
            });
        }
    </script>
</head>

<body>
<div class="search_box">
    <div class="top_bg_box swiper-container" id="top_bg">
        <div class="swiper-wrapper" id="top_bg_item_box">
        </div>
        <div class="swiper_position swiper_top" id="top_bg_position"></div>
    </div>
    <div class="title_box">
        <div class="win flex_row position_relative">
            <img src="img/nav_logo_white.png" class="top_logo">
            <div class="clickable">
                上海&nbsp;&nbsp;
            </div>
            <div class="clickable">
                <%--[切换城市]--%>
            </div>
            <div>
                |
            </div>
            <div class="position_right">
                <div class="title_text_right">
                    <a href="" class="clickable">首页</a>
                </div>
                <div class="title_text_right title_obvious">
                    <a href="" class="clickable">写字楼出租</a>
                </div>
                <div class="title_text_right">
                    <a href="" class="clickable">共享办公</a>
                </div>
                <div class="title_text_right">
                    <a href="" class="clickable">资讯中心</a>
                </div>
            </div>
        </div>
    </div>
    <div class="top_search_box flex_row">
        <form action="search.jsp">
            <input type="text" class="top_search" name="keyword" placeholder="输入您要查找的楼盘或者区域商圈名称"/><input
                class="search_btn" type="submit" value="搜索"/>

        </form>
    </div>
</div>
<div class="screen_wrap">

    <div class="win screen_box">
        <div class="screen_item">
            <div class="screen_title">
                区域
            </div>
            <div class="screen_item_box" id="screen_condition_box">

            </div>
        </div>
        <div class="screen_item">
            <div class="screen_title">
                办公类型
            </div>
            <div class="screen_item_box" id="screen_type_box">
            </div>
        </div>
        <div class="screen_item">
            <div class="screen_title">
                面积<span class="screen_subtitle">（单位：m²）</span>
            </div>
            <div class="screen_option_box flex_column" >
                <div class="flex_row" id="screen_area_box">
                </div>
                <div class="flex_row">
                    <div class="screen_option_text">0</div>
                    <div class="screen_option_text">100</div>
                    <div class="screen_option_text">300</div>
                    <div class="screen_option_text">500</div>
                    <div class="screen_option_text">1000</div>
                    <div class="screen_option_text">∞</div>
                </div>
            </div>
        </div>
        <div class="screen_item">
            <div class="screen_title">
                价格<span class="screen_subtitle">（单位：元/m²/天）</span>
            </div>
            <div class="screen_option_box flex_column">
                <div class="flex_row" id="screen_price_box">
                </div>
                <div class="flex_row">
                    <div class="screen_option_text">0</div>
                    <div class="screen_option_text">3</div>
                    <div class="screen_option_text">4</div>
                    <div class="screen_option_text">5</div>
                    <div class="screen_option_text">7</div>
                    <div class="screen_option_text">9</div>
                    <div class="screen_option_text">12</div>
                    <div class="screen_option_text">∞</div>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="win">
    <h3 class="item_title">精选专题&nbsp;<span class="item_subtitle">优质写字楼 聚你所需</span></h3>

    <h3 class="item_title">核心商圈享&nbsp;<span class="item_subtitle">优质配套 与大咖公司为邻</span></h3>
    <div class="image_box_in_title flex_row" id="core_building">
    </div>
    <h3 class="item_title">精选写字楼&nbsp;<span class="item_subtitle">高性价 高格调 超精致</span></h3>
    <div class="fine_building flex_row" id="fine_building">
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