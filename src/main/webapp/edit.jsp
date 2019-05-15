<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Initialize</title>
    <script src="js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>
<script>
    function reset() {
        http.post({
            url:"edit/reset"
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
                notes: {
                    detail_info: [
                        {"key": "建筑面积", "value": "284000m²"},
                        {"key": "得房率", "value": "75%"},
                        {"key": "客梯数", "value": "4个"},
                        {"key": "开发商", "value": "和记黄埔地产"},
                        {"key": "物业公司", "value": "上海德一置行物业管理有限公司"}
                    ],
                    metro_intro: "距离地铁9号线商城路站195米，步行3分钟；距离地铁2号线东昌路站750米，步行10分钟；距离地铁2号线、地铁4号线、地铁6号线、地铁9号线世纪大道站1365米，步行19分钟；临近地铁，减少换乘，上下班更轻松 ；",
                    bus_intro: "附近有783路、181路、338路、339路、583路等多条公交线路汇聚于此，便捷的交通为公司节约时间成本，是员工选择公司的最佳优势 ；",

                },
                introduce: "“世纪大都会”地处连接浦东金融中心与行政中心的世纪大道中段，毗邻上海城市轨道交通二、四、六、九号线“四线相交”的高效交通枢纽。",
                img_list: [
                    "https://www.tuotuozu.com/public/upload/20180509/c2124cee3b358c59b314beeadfdc63dc.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/463f9d4fdc11d9f83fa76943458bffb2.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/2cc119931c3715fe728c22de76e2a5d9.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/fa46c12d7891a7a89711c658d5c5c5e5.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/82d94882260d0abcde1968df183a0893.jpg",
                    "https://www.tuotuozu.com/public/upload/20180509/b04a99bb978d27106c9ba512b0fc1ac5.jpg",
                ],
            }
        });
    }

    function addOffice() {
        http.post({
            url: "edit/addOffice",
            params: {
                name: name,
                building_id: 1,
                type_id: 2,
                area: 1100,
                price: 10.5,
                decoration_id: 3,
                utilization_rate: 0.7,
                can_register: true,
                rent_free_period: "面议",
                notes: {
                    rent_payment: "押三付三",
                    visit_time: "联系我们，随时可看",
                    earliest_rent: "随时可租",
                    shortest_period: "12个月",
                    floor_info: "咨询顾问",
                },
                img_list: [
                    "https://www.tuotuozu.com/public/upload/20171213/afcb44f8590eb6ac46feff039374419b.jpg",
                ]
            }
        });
    }
</script>
<body>
<button onclick="reset()">reset</button>
<button onclick="addBuilding()">add building</button>
<button onclick="addOffice()">add office</button>
</body>
</html>
