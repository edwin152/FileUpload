<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>点点租</title>
    <link rel="stylesheet" type="text/css" href="css/swiper.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/officeDetail.css"/>
    <script src="js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/swiper.jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>

<script type="text/javascript">
    let office_id;

    window.onload = function () {
        let request = window.location.search;
        office_id = http.getParameter(request, "office_id");
        office_id = parseInt(office_id);
        getOffice();
    };

    function getOffice() {
        http.post({
            url: "search/office",
            params: {
                id: office_id,
            },
            success: function (data) {
                setOffice(data);
            }
        })
    }

    function setOffice(data) {
        let office = data.office;
        let building = data.building;
        let officeNotes = JSON.parse(office.notes);
        let buildingNotes = JSON.parse(building.notes);

        document.getElementById("detail_info_name").innerHTML
            = office.source_info;

        document.getElementById("total_price").innerHTML
            = office.total_price;

        document.getElementById("price").innerHTML
            = office.price;

        document.getElementById("area").innerHTML
            = office.area + "m²";

        document.getElementById("workplace_accommodation").innerHTML
            = office.workplace_accommodation + "个";

        document.getElementById("decoration").innerHTML
            = office.decoration_name;

        document.getElementById("utilization_rate").innerHTML
            = office.utilization_rate * 100 + "%";

        document.getElementById("free_rent_period").innerHTML
            = office.rent_free_period;

        document.getElementById("can_register").innerHTML
            = office.can_register ? "是" : "否";

        document.getElementById("building_name").innerHTML
            = building.name;

        document.getElementById("metro_name_list").innerHTML
            = building.metro_name_list;

        document.getElementById("bottom_area").innerHTML
            = "面积" + office.area + "m²";

        document.getElementById("bottom_utilization_rate").innerHTML
            = office.utilization_rate * 100
            + "%，可容纳工位" + office.workplace_accommodation + "个";

        document.getElementById("bottom_can_register").innerHTML
            = office.can_register ? "是" : "否";

        document.getElementById("price_advantage").innerHTML
            = office.price + "元/m²/天" +
            "，约" + office.total_price + "元/月" +
            "，" + officeNotes.rent_payment;

        document.getElementById("bottom_decoration").innerHTML
            = office.decoration_name;

        document.getElementById("bottom_free_rent_period").innerHTML
            = office.rent_free_period;

        document.getElementById("visit_time").innerHTML
            = officeNotes.visit_time;

        document.getElementById("earliest_rent").innerHTML
            = officeNotes.earliest_rent;

        document.getElementById("shortest_period").innerHTML
            = officeNotes.shortest_period;

        document.getElementById("floor_info").innerHTML
            = officeNotes.floor_info;

        document.getElementById("metro_intro").innerHTML
            = buildingNotes.metro_intro;

        document.getElementById("bus_intro").innerHTML
            = buildingNotes.bus_intro;

        document.getElementById("building_img")
            .setAttribute("src", building.img_list[0]);

        document.getElementById("bottom_building_name").innerHTML
            = building.name;

        document.getElementById("building_introduce").innerHTML
            = building.introduce;

        let imageListBox = document.getElementById("top_image_list");
        for (let img of office.img_list) {
            let itemBox = document.createElement("div");
            itemBox.setAttribute("class", "swiper-slide");
            imageListBox.appendChild(itemBox);
            let itemImg = document.createElement("img");
            itemImg.setAttribute("class", "top_info_image");
            itemImg.setAttribute("src", img);
            itemBox.appendChild(itemImg);
        }
        startSwiper();
    }

    function startSwiper() {
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
            <input type="text" class="top_search" name="search" id="top_search" value=""
                   placeholder="输入您要查找的楼盘或者区域商圈名称"/>
            <input class="top_search_btn" type="submit" value="搜索"/>
        </div>
    </div>
</div>
<div class="top_info_box matop">
    <div class="detail_info_name" id="detail_info_name">
    </div>
    <div class="top_info flex_row">
        <div class="swiper-container swiper_top_info" id="swiper_top_info">
            <div class="swiper-wrapper" id="top_image_list">
            </div>
            <div class="swiper_position swiper_top"></div>
        </div>
        <div class="detail_info_box flex_column">
            <div class="detail_info_price_box">
                <span class="detail_info_price" id="total_price">0</span>
                元/月 （单价:<span id="price"></span>元/m²/天）
            </div>
            <div class="infodown_box flex_row">
                <div class="infodown">
                    <div class="infodown_top" id="area">
                        0m²
                    </div>
                    <div class="infodown_bottom">
                        建筑面积
                    </div>
                </div>
                <div class="infodown">
                    <div class="infodown_top" id="workplace_accommodation">
                        0个
                    </div>
                    <div class="infodown_bottom">
                        可容纳工位数
                    </div>
                </div>
                <div class="infodown">
                    <div class="infodown_top" id="decoration">
                    </div>
                    <div class="infodown_bottom">
                        装修程度
                    </div>
                </div>
            </div>
            <div class="page_info_more">
                使用率： <span id="utilization_rate"></span>
            </div>
            <div class="page_info_more">
                免租期： <span id="free_rent_period"></span>
            </div>
            <div class="page_info_more">
                是否可注册： <span id="can_register"></span>
            </div>
            <div class="page_info_more">
                所属楼盘： <span id="building_name"></span>
            </div>
            <div class="page_info_more mini_line_height flex_row">
                地铁：<span id="metro_name_list"></span>
            </div>
            <div class="page_info_service flex_row">
                <img src="img/phone.png" class="page_info_service_imgage" alt=""/>
                <div class="page_info_service_right flex_column">
                    <div class="page_info_service_name">
                        <span>同普大厦写字楼出租</span> 专属顾问
                    </div>
                    <div class="page_info_service_btn_box flex_row">
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

<div class="content_box matop win flex_row">
    <div class="content_left">
        <div class="bg_white left_item matop">
            <h3 class="sec_title">概况</h3>
            <div class="data_list">
                <div class="data_item_box flex_row">
                    <div class="bottom_info_box flex_row">
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                面积信息：
                            </div>
                            <div class="bottom_info_text" id="bottom_area">
                                面积0m²
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                使用率：
                            </div>
                            <div class="bottom_info_text" id="bottom_utilization_rate">
                                0%，可容纳工位0个
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                可注册：
                            </div>
                            <div class="bottom_info_text" id="bottom_can_register">
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                价格优势：
                            </div>
                            <div class="bottom_info_text" id="price_advantage">
                                0元/m²/天，约0元/月
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                装修情况：
                            </div>
                            <div class="bottom_info_text" id="bottom_decoration">
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                免租时间：
                            </div>
                            <div class="bottom_info_text" id="bottom_free_rent_period">
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                看房时间：
                            </div>
                            <div class="bottom_info_text" id="visit_time">
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                最早可租：
                            </div>
                            <div class="bottom_info_text" id="earliest_rent">
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                最短租期：
                            </div>
                            <div class="bottom_info_text" id="shortest_period">
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                楼层信息：
                            </div>
                            <div class="bottom_info_text" id="floor_info">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bg_white left_item matop">
            <h3 class="sec_title">交通出行</h3>
            <div class="data_list">
                <div class="data_item_box flex_row">
                    <div class="bottom_info_box flex_row">
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                地 铁：
                            </div>
                            <div class="bottom_info_text" id="metro_intro">
                            </div>
                        </div>
                        <div class="bottom_info_item flex_row">
                            <div class="bottom_info_title">
                                公 交：
                            </div>
                            <div class="bottom_info_text" id="bus_intro">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="introduction_estate_box bg_white left_item matop">
            <h3 class="sec_title">楼盘介绍</h3>
            <div class="flex_row matop">
                <img src="" class="introduction_estate_img" id="building_img" alt=""/>
                <div class="flex_column introduction_estate_info_box">
                    <div class="introduction_estate_name clickable" id="bottom_building_name">
                    </div>
                    <div class="introduction_estate_info" id="building_introduce">
                    </div>
                </div>
            </div>
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
<div class="bottom_box matop flex_column">
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