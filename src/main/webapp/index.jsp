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
    <script src="layUI/layui.js" type="text/javascript" charset="UTF-8"></script>
    <script type="text/javascript">
        /**
         * 区域
         * @param conditionList
         */
        function setConditionScreen(conditionList) {
            conditionList.push("更多>>");
            let conditionBox = document.getElementById("screen_condition_box");
            for (let i = 0, len = conditionList.length; i < len; i++) {
                let conditionItem = document.createElement("div");
                conditionBox.appendChild(conditionItem);
                let conditionText = document.createElement("a");
                conditionText.innerHTML = conditionList[i];
                conditionItem.appendChild(conditionText);
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
                buildingItemBox.setAttribute("class", "image_item_in_title image_big");
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
                buildingItemBox.setAttribute("class", "fine_building_item_box image_big flex_column");
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
        function setTopBackground(imageUrl){
            let topBgBox = document.getElementById("top_bg_item_box");
            for (let i = 0, len = imageUrl.length; i < len; i++){
                let imgView = document.createElement("img");
                imgView.setAttribute("src", imageUrl[i]);
                topBgBox.appendChild(imgView);
            }

            // 启动轮播图
            layui.use('carousel', function () {
                let carousel = layui.carousel;
                //建造实例
                carousel.render({
                    elem: '#top_bg'
                    , width: '100%' //设置容器宽度
                    , height: '500'
                    , arrow: 'none' //始终显示箭头
                    // ,anim: 'fade' //切换动画方式
                    , interval: 3000
                });
            });
        }

        window.onload = function () {
            // TODO 假数据
            let conditionScreen = ["浦东", "黄浦", "卢湾", "徐汇", "长宁", "静安", "普陀", "闸北", "虹口"];
            setConditionScreen(conditionScreen);
            let buildingList = [
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    subtitle: "南京西路是被称为“中华商业第一街”──南京路（南京东路和南京西路）的西半部，跨黄浦、静安两区。"
                },
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    subtitle: "南京西路是被称为“中华商业第一街”──南京路（南京东路和南京西路）的西半部，跨黄浦、静安两区。"
                },
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    subtitle: "南京西路是被称为“中华商业第一街”──南京路（南京东路和南京西路）的西半部，跨黄浦、静安两区。"
                },
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    subtitle: "南京西路是被称为“中华商业第一街”──南京路（南京东路和南京西路）的西半部，跨黄浦、静安两区。"
                }
            ];
            setCoreBuilding(buildingList)

            let fineList = [
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    address: "南京西路",
                    price: "1.2"
                },
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    address: "南京西路",
                    price: "1.2"
                },
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    address: "南京西路",
                    price: "1.2"
                },
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    address: "南京西路",
                    price: "1.2"
                },
                {
                    imageUrl: "img/test.jpg",
                    title: "南京西路/江宁路写字楼",
                    address: "南京西路",
                    price: "1.2"
                }
            ];
            setFineBuildingList(fineList);

            let imgUrl = ["img/test.jpg", "img/test.jpg", "img/test.jpg", "img/test.jpg"];
            setTopBackground(imgUrl);
        }
    </script>
</head>

<body>
<div class="search_box">
    <div class="top_bg_box layui-carousel" id="top_bg">
        <div carousel-item id="top_bg_item_box">
        </div>
    </div>
    <div class="title_box">
        <div class="win flex_row position_relative">
            <img src="img/phone.png" class="top_logo">
            <div class="clickable">
                上海&nbsp;&nbsp;
            </div>
            <div class="clickable">
                [切换城市]
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
                <div>
                    <img src="img/phone.png" class="screen_type_icon"/>
                    <a class="screen_type_text">普通办公</a>
                </div>
                <div>
                    <img src="img/phone.png" class="screen_type_icon"/>
                    <a class="screen_type_text">共享办公</a>
                </div>
                <div>
                    <img src="img/phone.png" class="screen_type_icon"/>
                    <a class="screen_type_text">创意园区</a>
                </div>
            </div>
        </div>
        <div class="screen_item">
            <div class="screen_title">
                面积<span class="screen_subtitle">（单位：m²）</span>
            </div>
            <div class="screen_option_box flex_column" id="screen_area_box">
                <div class="flex_row">
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
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
            <div class="screen_option_box flex_column" id="screen_price_box">
                <div class="flex_row">
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
                    <a href="" class="screen_option"></a>
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