<%--
  Created by IntelliJ IDEA.
  User: mlt
  Date: 2019/5/22
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资讯中心</title>
    <link rel="stylesheet" type="text/css" href="layUI/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/news.css"/>
    <script src="layUI/layui.js" type="text/javascript" charset="utf-8"></script>
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

            let newsList = [
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
                {
                    imageUrl: "img/test.jpg",
                    type: "找房攻略",
                    title: "公司租写字楼好还是商品房小区好？这两者之间有什么利弊？",
                    time: "2019-04-03 17:40:32",
                    text: "租写字楼的网站有哪些？这个问题可真是难不住小编，互联网发展到现在很多的现象已经发生了改变，其中在网上找写字楼就是我们最好的途径，那么下面小编就给大家看看现在专业做写字楼、联合办公出租租赁的网站有哪些？希望可以帮助到大家！"
                },
            ];
            setNewsList(newsList);

            // TODO 数据设置完成调用
            loadPageUtil(500);
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

        /**
         * 设置资讯列表
         * @param newsList
         */
        function setNewsList(newsList){
            if (newsList == null) return;
            let ListBox = document.getElementById("news_list_box");
            for (let i = 0, len = newsList.length; i < len; i++) {
                let itemView = document.createElement("div");
                itemView.setAttribute("class", "news_item_box");
                // 在分页前添加
                ListBox.insertBefore(itemView, document.getElementById("page_box"));

                let itemImg = document.createElement("img");
                itemImg.setAttribute("class", "news_img");
                // TODO 图片URL
                itemImg.setAttribute("src", newsList[i].imageUrl);
                itemView.appendChild(itemImg);

                let infoView = document.createElement("div");
                infoView.setAttribute("class", "news_info_box");
                itemView.appendChild(infoView);

                let titleViewBox = document.createElement("div");
                titleViewBox.setAttribute("class", "news_title_box");
                infoView.appendChild(titleViewBox);

                let typeView = document.createElement("span");
                typeView.setAttribute("class", "item_type");
                // TODO 类型
                typeView.innerHTML = newsList[i].type;
                titleViewBox.appendChild(typeView);

                let titleView = document.createElement("div");
                titleView.setAttribute("class", "news_title");
                // TODO 标题
                titleView.innerHTML = newsList[i].title;
                titleViewBox.appendChild(titleView);

                let timeView = document.createElement("div");
                timeView.setAttribute("class", "news_time");
                // TODO 时间
                timeView.innerHTML = newsList[i].time;
                infoView.appendChild(timeView);

                let textView = document.createElement("div");
                textView.setAttribute("class", "news_text");
                // TODO 内容
                textView.innerHTML = newsList[i].text;
                infoView.appendChild(textView);
            }
        }

        /**
         * 加载分页工具
         * @param dataSize 数据总数
         */
        function loadPageUtil(dataSize){
            layui.use('laypage', function () {
                var laypage = layui.laypage;
                //执行一个laypage实例
                laypage.render({
                    //注意，这里的 test1 是 ID，不用加 # 号
                    elem: 'page_box',
                    //数据总数，从服务端得到
                    count: dataSize,
                    limit: 20,
                    jump: function(obj, first){
                        //obj包含了当前分页的所有参数，比如：
                        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        console.log(obj.limit); //得到每页显示的条数

                        //首次不执行
                        if(!first){
                            // TODO 请求下一页数据
                            let pageNumber = obj.curr+1;
                        }
                    }
                });
            });
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
    <div class="content_right_box" id="news_list_box">
        <div class="news_item_box">
        </div>
        <div id="page_box"></div>
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
