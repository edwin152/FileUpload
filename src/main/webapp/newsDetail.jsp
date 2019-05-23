<%--
  Created by IntelliJ IDEA.
  User: mlt
  Date: 2019/5/23
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资讯详情</title>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/news.css"/>
    <link rel="stylesheet" type="text/css" href="css/newsDetail.css"/>
    <script type="text/javascript">
        window.onload = function () {
            let typeList = [
                {
                    name: "全部",
                },
                {
                    name: "行业新闻",
                },
                {
                    name: "楼盘介绍",
                },
                {
                    name: "找房攻略",
                },
                {
                    name: "妥妥新闻",
                },
                {
                    name: "媒体报道",
                }
            ];
            setNewsType(typeList);

            let heatNewsList = [
                {
                    name: "办公场地租赁平台挑选技巧大放送",
                },
                {
                    name: "有关泗泾办公室出租需要了解的知识",
                },
                {
                    name: "2点关于上海共享办公最新报道",
                },
                {
                    name: "火车站办公室出租注意事项，不得不了解的内容！",
                },
            ];
            setHeatNews(heatNewsList);

            let newsBean = {
                title: "租写字间注意事项有哪些_写字间租赁过程中应该注意哪些？",
                time: "2019-04-09 17:47:24",
                content: "<p>　首先，先了解下“写字间”是指的什么意思。“写字间”是相较于写字楼来说单独独立的房间，包括在写字楼之内的房间。是开发商用来提供给中小企业或者个人用来办公或者休憩的场所。可以用来作为临时住宿的地方。写字间一般实用面积比较狭小。这种房间多设在商业繁华的地段，因为选址周围环境较为繁华，导致其写字间租赁价格相对较高，需要租房的企业在进行选择的时候较为被动。那么今儿我们来聊一聊<strong>租写字间注意事项</strong>。</p><p style=\"text-align: center;\"><img width=\"600\" height=\"350\" title=\"租写字间注意事项有哪些_写字间租赁过程中应该注意哪些？\" alt=\"租写字间注意事项有哪些_写字间租赁过程中应该注意哪些？\" src=\"https://www.tuotuozu.com/ueditor/php/upload/image/20190409/1554803227.png\" border=\"0\" vspace=\"0\"></p><p>　　<strong>首先是房屋租期</strong><br>　　租写字间和租写字楼还是有些差别。租写字楼的话，一般会被要求租一年起租，三年之内。而租写字间的一般短租人群更多，同样的也只有极少数商务中心会做，面积较小，价格也高很多。短租超过三年，一般房东不会同意，如果短租时间较长，中间答应对方租金上有一定的涨幅，租到的成功率会高一些。这是<strong>租写字间注意事项</strong>中最需要了解的一点。<br>　　<strong>其次是管理费用</strong><br>　　管理费用中，包含公共部分的水电，维修保养和清洁卫生等，而需要注意的是20元以下的多半是没有包含中央空调的费用，20元以上的可能会包含到空调空调的费用。（写字楼的中央空调很少24小时提供，一般会根据各个单位的上班下班时间来定，比如早八点晚七）这点在了解该房屋信息的时候需要和对方了解清楚。<br>　　<strong>最后是租赁合同</strong><br>　　房屋敲定好，之后就是租赁合同的签订。签两份合同，一份用来房管局的标准备案，一份是详细的补充协议。房管局的合同相对笼统，写清楚租金，年限双方盖章签字就可以。<br>　　我们选择办公写字楼的时候不能只看地理位置，其实需要考虑的因素很多，重要的是在租赁的过程中要签订合同，合同可以保护双方的权益，这一点要引起大家的注意，以上就是针对<strong>租写字间注意事项</strong>提到的一些建议，希望小编的介绍可以帮助到大家。</p>"
            };

            setNewsContent(newsBean);
        };

        /**
         * 设置类型
         * @param typeList
         */
        function setNewsType(typeList) {
            if (typeList == null) return;
            let newTypeBox = document.getElementById("news_type");
            for (let i = 0, len = typeList.length; i < len; i++) {
                let typeItem = document.createElement("div");
                typeItem.setAttribute("class", "link");
                // TODO
                typeItem.innerHTML = typeList[i].name;
                newTypeBox.appendChild(typeItem);
            }
        }

        /**
         * 设置热门资讯
         * @param heatNewsList
         */
        function setHeatNews(heatNewsList) {
            if (heatNewsList == null) return;
            let ListBox = document.getElementById("heat_news");
            for (let i = 0, len = heatNewsList.length; i < len; i++) {
                let itemView = document.createElement("div");
                // TODO
                itemView.innerHTML = heatNewsList[i].name;
                ListBox.appendChild(itemView);
            }
        }

        function setNewsContent(newsBean){
            let newsTitle = document.getElementById("news_detail_title");
            newsTitle.innerHTML = newsBean.title;
            let newsTime = document.getElementById("news_time");
            newsTime.innerHTML = newsBean.time;
            let newsContent = document.getElementById("news_content");
            newsContent.innerHTML = newsBean.content;
        }

    </script>
</head>
<body>
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
        <img src="img/nav_logo_white.png" class="top_logo" alt=""/>
        <div class="flex_row" id="news_type">
        </div>
    </div>
</div>


<div class="flex_row matop win content_box">
    <div class="content_left_box">
        <div class="left_title">
            <img src="img/phone.png" alt="" class="left_title_img"/>
            热门资讯
        </div>
        <div class="heat_news" id="heat_news">

        </div>
    </div>
    <div class="content_right_box" id="news_detail">
        <h3 class="news_detail_title" id="news_detail_title"></h3>
        <hr/>
        <div class="news_time" id="news_time"></div>
        <div class="news_content" id="news_content"></div>
        <%-- TODO 是否添加上下篇 --%>
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
