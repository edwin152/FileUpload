<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/swiper.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/search.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail.css"/>
    <script src="js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/swiper.jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>

<script type="text/javascript">
    let building_id;
    let filter;
    let pageIndex = 0;
    let pageNum = 0;

    window.onload = function () {
        let request = window.location.search;
        building_id = http.getParameter(request, "building_id");
        building_id = parseInt(building_id);
        getFilterList();
        getBuilding();
    };

    function getFilterList() {
        http.post({
            url: "filter/office",
            params: {
                building_id: building_id,
            },
            onSuccess: function (data) {
                // console.log(data);
                filter = data;
                getOfficeList();
            }
        });
    }

    function getBuilding() {
        http.post({
            url: "search/building",
            params: {
                id: building_id,
            },
            onSuccess: function (data) {
                // console.log(data);
                setTopInfo(data);
                setBottomInfo(data);
            }
        })
    }

    function getOfficeList(page) {
        http.post({
            url: "search/offices",
            params: {
                building_id: building_id,
                type_id: filter.checkedTypeId,
                area_range_id: filter.checkedAreaRangeId,
                price_range_id: filter.checkedPriceRangeId,
                decoration_id: filter.checkedDecorationId,
                page: page,
            },
            onSuccess: function (data) {
                console.log(data);
                filter.checkedTypeId = data.checkedTypeId;
                filter.checkedAreaRangeId = data.checkedAreaRangeId;
                filter.checkedPriceRangeId = data.checkedPriceRangeId;
                filter.checkedDecorationId = data.checkedDecorationId;
                setFilterList();
                pageIndex = data.pageIndex;
                pageNum = data.pageNum;
                setOfficeList(data.officeList);
            }
        });
    }

    function setFilterList() {
        if (!filter) {
            return;
        }
        setFilterListByName(filter.typeList, 'typeList', filter.checkedTypeId);
        setFilterListByName(filter.areaRangeList, 'areaRangeList', filter.checkedAreaRangeId);
        setFilterListByName(filter.priceRangeList, 'priceRangeList', filter.checkedPriceRangeId);
        setFilterListByName(filter.decorationList, 'decorationList', filter.checkedDecorationId);
    }

    function setFilterListByName(list, name, checkedId) {
        if (!list || list.length <= 2) {
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
                getOfficeList();
            };
            conditionBox.appendChild(optionItem);
        }
    }

    /**
     * 设置顶部数据
     * @param building 数据
     */
    function setTopInfo(building) {
        // 名字
        document.getElementById("building_name").innerHTML = building.name;
        // 图片
        let imgBox = document.getElementById("top_image_list");
        for (let img of building.img_list) {
            let itemBox = document.createElement("div");
            itemBox.setAttribute("class", "swiper-slide");
            imgBox.appendChild(itemBox);
            let itemImg = document.createElement("img");
            itemImg.setAttribute("class", "top_info_image");
            itemImg.setAttribute("src", img);
            itemBox.appendChild(itemImg);
        }
        // 价格范围
        document.getElementById("detail_info_price").innerHTML = building.price_range;
        // 联系客服顶部价格
        document.getElementById("service_info_price").innerHTML = building.price_range;
        // 地址
        document.getElementById("district_name").innerHTML = building.district_name;
        document.getElementById("zone_name").innerHTML = building.zone_name;
        document.getElementById("address_info_name").innerHTML = building.address;
        // TODO 距离地铁距离
        document.getElementById("metro_name_list").innerHTML = building.metro_name_list;
        // 在租房源
        document.getElementById("office_num").innerHTML = building.office_num + "个";
        // 可租面积
        document.getElementById("area_range").innerHTML = building.area_range + "m²";
        openSwiper();
    }

    /**
     * 设置办公室列表数据
     */
    function setOfficeList(officeList) {
        let contentBox = document.getElementById("data_item_box");
        if (pageIndex === 0) {
            // 清除子view
            let childList = contentBox.childNodes;
            while (childList.length > 2) {
                contentBox.removeChild(childList[2]);
            }
        }
        if (pageNum > pageIndex + 1) {
            $("#next_page").show();
        } else {
            $("#next_page").hide();
        }
        // if (pageIndex === 0 && buildingList.length === 0) {
        //     $("#error_not_text").show();
        // } else {
        //     $("#error_not_text").hide();
        // }

        for (let office of officeList) {
            let trView = document.createElement("tr");
            contentBox.appendChild(trView);
            let tdImage = document.createElement("td");
            trView.appendChild(tdImage);

            // 图片
            let imageView = document.createElement("img");
            imageView.className = "table_item_image clickable";
            if (office.img_list) {
                imageView.setAttribute("src", office.img_list[0]);
            }
            tdImage.onclick = function () {
                openRoomDetail(office.id);
            };
            tdImage.appendChild(imageView);

            // 房源信息
            let sourceInfo = document.createElement("td");
            sourceInfo.setAttribute("class", "source_info_td");
            sourceInfo.innerHTML = "<div class='clickable'>" + office.source_info + "</div>";
            sourceInfo.onclick = function () {
                openRoomDetail(office.id);
            };
            trView.appendChild(sourceInfo);

            // 面积
            let area = document.createElement("td");
            let areaName = document.createElement("span");
            areaName.innerHTML = office.area;
            areaName.setAttribute("class", "big_text");
            let areaEnd = document.createElement("span");
            areaEnd.innerHTML = "m²";
            area.appendChild(areaName);
            area.appendChild(areaEnd);
            trView.appendChild(area);

            // 单价
            let price = document.createElement("td");
            let priceName = document.createElement("span");
            priceName.innerHTML = office.price;
            priceName.setAttribute("class", "big_text price_color");
            let priceEnd = document.createElement("span");
            priceEnd.innerHTML = "元/m²/天";
            price.appendChild(priceName);
            price.appendChild(priceEnd);
            trView.appendChild(price);

            // 总价
            let totalPrice = document.createElement("td");
            let totalPriceName = document.createElement("span");
            totalPriceName.innerHTML = office.total_price;
            totalPriceName.setAttribute("class", "big_text");
            let totalPriceEnd = document.createElement("span");
            totalPriceEnd.innerHTML = "元/月";
            totalPrice.appendChild(totalPriceName);
            totalPrice.appendChild(totalPriceEnd);
            trView.appendChild(totalPrice);
        }
    }

    /**
     * 设置底部写字楼信息
     */
    function setBottomInfo(building) {
        let notes = JSON.parse(building.notes);
        let detail_info = notes.detail_info;
        let bottom_info = document.getElementById("bottom_info");
        bottom_info.innerHTML = "";
        if (!detail_info || detail_info.length === 0) {
            $("#bottom_info").hide();
        } else {
            $("#bottom_info").show();
        }
        for (let info of detail_info) {
            let box = document.createElement("div");
            box.className = "bottom_info_item flexed_row";

            let key = document.createElement("div");
            key.className = "bottom_info_title";
            key.innerHTML = info.key + ":";

            let value = document.createElement("div");
            value.className = "bottom_info_text";
            value.innerHTML = info.value;

            box.appendChild(key);
            box.appendChild(value);
            bottom_info.appendChild(box);
        }

        // TODO 楼盘介绍 补进UI
        // let introduce = building.introduce;
        document.getElementById("introduce_info").innerHTML = building.introduce;
    }

    /**
     * 下一页
     */
    function nextPage() {
        getOfficeList(pageIndex + 1);
    }

    function openRoomDetail(office_id) {
        // TODO 点击事件
        window.open("roomDetail.jsp?office_id=" + office_id, "_blank");
    }

    function openSwiper() {
        new Swiper('#swiper_top_info', {
            loop: true,
            autoplay: 2000,
            pagination: '.swiper_position',
            paginationClickable: true,
            autoplayDisableOnInteraction: false,
        });
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
        <img src="" class="top_logo" alt=""/>
        <a href="" class="black_a logo_right">上海写字楼出租</a>
        <div class="top_search_box flexed_row">
            <input type="text" class="top_search" name="search" id="top_search" value=""
                   placeholder="输入您要查找的楼盘或者区域商圈名称"/>
            <input class="top_search_btn" type="submit" value="搜索"/>
        </div>
    </div>
</div>
<div class="top_info_box">
    <div class="top_info flexed_row">
        <div class="swiper-container swiper_top_info" id="swiper_top_info">
            <div class="swiper-wrapper" id="top_image_list">
            </div>
            <div class="swiper_position swiper_top"></div>
        </div>
        <div class="detail_info_box flexed_column">
            <div class="detail_info_name" id="building_name">
            </div>
            <div class="detail_info_price_box">
                <span class="detail_info_price" id="detail_info_price">2.8-3.3</span>元/m²/天
            </div>
            <div class="infodown_box flexed_row">
                <div class="infodown">
                    <div class="infodown_top" id="office_num">
                    </div>
                    <div class="infodown_bottom">
                        在租房源
                    </div>
                </div>
                <div class="infodown">
                    <div class="infodown_top" id="area_range">
                    </div>
                    <div class="infodown_bottom">
                        可租面积
                    </div>
                </div>
            </div>
            <div class="page_info_more" id="area_detail">
                地址： [
                <span id="district_name" class="clickable address_clickable"></span>
                ] - [ <span id="zone_name" class="clickable address_clickable"></span>
                ] <span id="address_info_name"></span>
            </div>
            <div class="page_info_more mini_line_height flexed_row">
                距离地铁：
                <div id="metro_name_list">
                </div>
            </div>
            <div class="page_info_service flexed_row">
                <img src="img/phone.png" class="page_info_service_imgage" alt=""/>
                <div class="page_info_service_right flexed_column">
                    <div class="page_info_service_name">
                        <span>同普大厦写字楼出租</span> 专属顾问
                    </div>
                    <div class="page_info_service_btn_box flexed_row">
                        <div class="page_info_service_btn">
                            免费咨询
                        </div>
                        <div class="page_info_service_phone">
                            400-865-9520
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="content_box matop win flexed_row">
    <div class="content_left">
        <div class="bg_white left_item">
            <h3 class="sec_title">出租房源</h3>

            <div class="condition_line flexed_row" id="type">
                <div class="condition_title">
                    类型：
                </div>
                <div class="option_box flexed_row" id="typeList">
                </div>
            </div>

            <div class="condition_line flexed_row" id="areaRange">
                <div class="condition_title">
                    面积：
                </div>
                <div class="option_box flexed_row" id="areaRangeList">
                </div>
            </div>

            <div class="condition_line flexed_row" id="priceRange">
                <div class="condition_title">
                    价格：
                </div>
                <div class="option_box flexed_row" id="priceRangeList">
                </div>
            </div>

            <div class="condition_line flexed_row" id="decoration">
                <div class="condition_title">
                    装修：
                </div>
                <div class="option_box flexed_row" id="decorationList">
                </div>
            </div>

            <div class="data_list">
                <div class="data_item_box flexed_row" id="dataList">
                </div>
            </div>

            <table class="table_box" id="data_item_box">
                <tr class="table_title">
                    <td>全部</td>
                    <td>房源信息</td>
                    <td>面积</td>
                    <td>单价</td>
                    <td>总价</td>
                </tr>
            </table>

            <div class="paging" id="next_page">
                <span class="paging_item" onclick="nextPage()">
                    下一页
                </span>
            </div>
        </div>

        <div class="bg_white left_item matop">
            <h3 class="sec_title">写字楼信息</h3>
            <div class="data_list">
                <div class="data_item_box flexed_row">
                    <div class="bottom_info_box flexed_row" id="bottom_info">
                    </div>
                </div>
            </div>
            <div class="introduce_info" id="introduce_info">

            </div>
        </div>
    </div>

    <div class="content_right bg_white">
        <div class="contact_way">
            <div class="service_info_price_box">
                <span class="service_info_price" id="service_info_price"></span>元/m²/天
            </div>
            <div class="contact_info">
                <img src="" class="contact_img" alt=""/>
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
            <div class="content_right_title" style="display: none;">
                <img src="" class="content_right_title_img" alt=""/>热门楼盘
            </div>
        </div>
    </div>
</div>
<div class="bottom_box matop flexed_column">
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