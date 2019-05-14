<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>test</title>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <script src="js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>
<script>

    window.onload = function () {
        // addBuilding();
        // for (let i = 0; i < 15; i++) {
        //     addOffice(i + 1);
        // }
    };

    function getFilterList() {
        http.post({
            url: "info/filters",
            onSuccess: function (data) {
                console.log(data);
            }
        });
    }

    function addBuilding() {
        http.post({
            url: "edit/addBuilding",
            params: {
                name: "浦东世纪大都会",
                zone_id: 36,
                address: "世纪大道1229号",
                metro_list: [2, 4, 6],
                notes: [
                    {
                        "key": "建筑面积",
                        "value": "284000m²"
                    },
                    {
                        "key": "得房率",
                        "value": "75%"
                    },
                    {
                        "key": "客梯数",
                        "value": "4个"
                    },
                    {
                        "key": "开发商",
                        "value": "和记黄埔地产"
                    },
                    {
                        "key": "物业公司",
                        "value": "上海德一置行物业管理有限公司"
                    }
                ],
                introduce: "“世纪大都会”地处连接浦东金融中心与行政中心的世纪大道中段，毗邻上海城市轨道交通二、四、六、九号线“四线相交”的高效交通枢纽。",
                img_list: [
                    "https://www.tuotuozu.com/public/upload/20180509/c2124cee3b358c59b314beeadfdc63dc.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/463f9d4fdc11d9f83fa76943458bffb2.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/2cc119931c3715fe728c22de76e2a5d9.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/fa46c12d7891a7a89711c658d5c5c5e5.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/82d94882260d0abcde1968df183a0893.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/b04a99bb978d27106c9ba512b0fc1ac5.jpg",
                ],
            },
            onSuccess: function (data) {
                console.log(data);
            }
        });
    }

    function addOffice(name) {
        http.post({
            url: "edit/addOffice",
            params: {
                name: name,
                building_id: 1,
                type_id: 2,
                area: name % 2 === 0 ? 450 : name * 100,
                price: 8,
                decoration_id: 2,
                img_list: [
                    "https://www.tuotuozu.com/public/upload/20180325/cover/a572a2428ffc977a8d8748546986c7be.jpg",
                ]
            },
            onSuccess: function (data) {
                console.log(data);
            }
        });
    }
</script>

<body>
<div class="search_box_main">
    <img src="https://images.diandianzu.com/Public/Home/v5/images/public/nav_logo.png" class="search_image" alt=""/>
    <div class="top_search_box flexed_row">
        <input type="text" class="top_search" name="search" id="top_search" value="" placeholder="输入您要查找的楼盘或者区域商圈名称"/>
        <input class="top_search_btn" type="submit" value="搜索"/>
    </div>
</div>
</body>

</html>