<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/swiper.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/detail.css"/>
    <script src="js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/swiper.jquery.min.js" type="text/javascript" charset="utf-8"></script>
</head>

<script type="text/javascript">
    window.onload = function () {
        let params = window.location.href.split('?')[1];
        let building_id = params.split("=")[1];
        getOffice(building_id);
    };

    function getOffice(building_id) {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                console.log(xmlHttp.responseText);
                let data = JSON.parse(xmlHttp.responseText);
                setDataList(data.officeList);
                setTopInfo(data.building);
                setBottomInfo(data.building);
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "search/offices", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("building_id=" + (building_id === undefined ? "null" : building_id));
    }

    $(function () {
        new Swiper('#swiper_top_info', {
            loop: true, //循环 最后面一个往后面滑动会滑到第一个
            autoplay: 2000,
            //指示器
            pagination: '.swiper_position',
            paginationClickable: true,
            autoplayDisableOnInteraction: false,
        });
    });

    /**
     * 设置顶部数据
     * @param {Object} data 数据
     */
    function setTopInfo(data) {
        document.getElementById("detail_info_name").innerHTML = data.name;
        let imgBox = document.getElementById("top_image_list");
        // TODO 数据为字符串，如果变为数组，这里改动
        let imageData = JSON.parse(data.img_list);
        for (let i = 0; i < imageData.length; i++) {
            let itemBox = document.createElement("div");
            itemBox.setAttribute("class", "swiper-slide");
            imgBox.appendChild(itemBox);
            let itemImg = document.createElement("img");
            itemImg.setAttribute("class", "top_info_image");
            itemImg.setAttribute("src", imageData[i]);
            itemBox.appendChild(itemImg);
        }

        // TODO 价格
        document.getElementById("detail_info_price").innerHTML = "";

        // TODO 地址
        document.getElementById("page_info_more").innerHTML = "地址："
            + "[" + data.district_name + "]"
            + "[" + data.zone_name + "]"
            + data.address;

        // TODO 距离地铁距离
        document.getElementById("metro_name_list").innerHTML = "距离地铁："
            + "[" + data.metro_name_list + "]";
    }

    /**
     * 设置列表数据
     * @param {Object} data
     */
    function setDataList(data) {
        let contentBox = document.getElementById("data_item_box");
        for (let i = 0; i < data.length; i++) {
            let trView = document.createElement("tr");
            contentBox.appendChild(trView);
            let tdImage = document.createElement("td");
            trView.appendChild(tdImage);

            // 图片
            let imageView = document.createElement("img");
            imageView.className = "table_item_image";
            imageView.setAttribute("src", ""); // TODO
            tdImage.appendChild(imageView);

            // 名称
            let tdName = document.createElement("td");
            tdName.innerHTML = data[i].decoration_name;
            trView.appendChild(tdName);

            // 面积
            let areaName = document.createElement("td");
            areaName.innerHTML = data[i].area_range_name;
            trView.appendChild(areaName);

            // 单价
            let priceName = document.createElement("td");
            priceName.innerHTML = data[i].price_range_name;
            trView.appendChild(priceName);

            // 总价
            let areaValue = document.createElement("td");
            areaValue.innerHTML = data[i].area_value;
            trView.appendChild(areaValue);
        }
    }

    /**
     * 设置底部写字楼信息
     * @param {Object} data
     */
    function setBottomInfo(data) {
        document.getElementById("bottom_info_text").innerHTML = data.sssss;
        // TODO
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
            <div class="detail_info_name" id="detail_info_name">
            </div>
            <div class="detail_info_price_box">
                <span class="detail_info_price" id="detail_info_price">2.8-3.3</span>元/m²/天
            </div>
            <div class="infodown_box flexed_row">
                <div class="infodown">
                    <div class="infodown_top">
                        2个
                    </div>
                    <div class="infodown_bottom">
                        在租房源
                    </div>
                </div>
                <div class="infodown">
                    <div class="infodown_top">
                        130 - 173m²
                    </div>
                    <div class="infodown_bottom">
                        可租面积
                    </div>
                </div>
            </div>
            <div class="page_info_more">
                地址： [ 普陀 ] - [ 长风商务区 ] 同普路1220号
            </div>
            <div class="page_info_more mini_line_height flexed_row">
                距离地铁：
                <div id="metro_name_list">
                    距离 13号线 祁连山南路 约598米 <br/> 距离 2号线 北新泾 约1807米
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
            <h3 class="sec_title">默认搜索</h3>

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
            <div class="data_list">
                <div class="data_item_box flexed_row" id="dataList">
                </div>
            </div>
            <table class="table_box" id="data_item_box">
                <tr class="table_title">
                    <td>全部</td>
                    <td>房源信息</td>
                    <td>面积排序</td>
                    <td>单价排序</td>
                    <td>总价排序</td>
                </tr>
            </table>
        </div>
        <div class="bg_white left_item matop">
            <h3 class="sec_title">写字楼信息</h3>
            <div class="data_list">
                <div class="data_item_box flexed_row">
                    <div class="bottom_info_box flexed_row">
                        <div class="bottom_info_item flexed_row">
                            <div class="bottom_info_title">
                                建筑面积：
                            </div>
                            <div class="bottom_info_text" id="bottom_info_text">
                                27703m²
                            </div>
                        </div>
                        <div class="bottom_info_item flexed_row">
                            <div class="bottom_info_title">
                                得房率：
                            </div>
                            <div class="bottom_info_text">
                                27703m²
                            </div>
                        </div>
                        <div class="bottom_info_item flexed_row">
                            <div class="bottom_info_title">
                                标准层高：
                            </div>
                            <div class="bottom_info_text">
                                27703m²
                            </div>
                        </div>
                        <div class="bottom_info_item flexed_row">
                            <div class="bottom_info_title">
                                客梯数：
                            </div>
                            <div class="bottom_info_text">
                                27703m²
                            </div>
                        </div>
                        <div class="bottom_info_item flexed_row">
                            <div class="bottom_info_title">
                                开发商：
                            </div>
                            <div class="bottom_info_text">
                                27703m²
                            </div>
                        </div>
                        <div class="bottom_info_item flexed_row">
                            <div class="bottom_info_title">
                                已入驻企业：
                            </div>
                            <div class="bottom_info_text">
                                上海荣戈机电设备有限公司、杰姆士（上海）酒业贸易、上海同为建筑设计有限公司、BOA上海源鸣文化传播有限公司
                            </div>
                        </div>
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