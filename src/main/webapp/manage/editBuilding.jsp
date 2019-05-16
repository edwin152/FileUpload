<%--suppress HtmlFormInputWithoutLabel --%>
<%--
  Created by IntelliJ IDEA.
  User: mlt
  Date: 2019/5/15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>编辑写字楼信息</title>
    <link rel="stylesheet" type="text/css" href="../css/all.css"/>
    <link rel="stylesheet" type="text/css" href="../css/editBuilding.css"/>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript">
        let districtList = [];

        let detailInfoList = [
            {"key": "建筑面积", "value": ""},
            {"key": "得房率", "value": ""},
            {"key": "客梯数", "value": ""},
            {"key": "开发商", "value": ""},
            {"key": "物业公司", "value": ""}
        ];

        window.onload = function () {
            freshDetailInfoList();
            getFilters();
        };

        function getFilters() {
            http.post({
                url: "../filter/all",
                success(data) {
                    districtList = data.districtList;
                    freshDistrictList(data.checkedDistrictId);
                },
            });
        }

        function freshDistrictList(district_id) {
            let district_list = document.getElementById("district_list");
            district_list.innerHTML = "";
            for (let district of districtList) {
                let option = document.createElement("option");
                option.setAttribute("value", district.id);
                option.innerHTML = district.name;
                district_list.appendChild(option);

                if (district.id === district_id) {
                    let zone_list = document.getElementById("zone_list");
                    zone_list.innerHTML = "";
                    for (let zone of district.zoneList) {
                        let option = document.createElement("option");
                        option.setAttribute("value", zone.id);
                        option.innerHTML = zone.name;
                        zone_list.appendChild(option);
                    }
                }
            }
        }

        function freshDetailInfoList() {
            let detail_info_list = document.getElementById("detail_info_list");
            detail_info_list.innerHTML = "";
            for (let i in detailInfoList) {
                let detailInfo = detailInfoList[i];
                let detail_info = createDetailInfo(detailInfo.key, detailInfo.value);
                detail_info.setAttribute("index", i);
                detail_info_list.appendChild(detail_info);
            }
        }

        function createDetailInfo(key, value) {
            let detail_info = document.createElement("div");
            detail_info.className = "row";

            let infoKey = document.createElement("input");
            infoKey.setAttribute("value", key);
            detail_info.appendChild(infoKey);

            let infoValue = document.createElement("input");
            infoValue.setAttribute("value", value);
            detail_info.appendChild(infoValue);

            let deleteBtn = document.createElement("button");
            deleteBtn.innerHTML = "删除";
            deleteBtn.onclick = function () {
                deleteDetailInfo(detail_info);
            };
            detail_info.appendChild(deleteBtn);

            return detail_info;
        }

        function addTable() {
            let detailInfo = {
                key: "",
                value: "",
            };
            detailInfoList.push(detailInfo);
            freshDetailInfoList();
        }

        function deleteDetailInfo(item) {
            let index = item.getAttribute("index");
            detailInfoList.splice(index, 1);
            freshDetailInfoList();
        }
    </script>
</head>
<body>
<div class="box">
    <div class="row">
        <span class="title">写字楼图片</span>
    </div>

    <div class="row" id="img_list">
        <img src="../img/test.jpg" alt=""/>
    </div>

    <div class="row">
        <button>添加图片</button>
    </div>
</div>

<div class="box">
    <div class="row">
        <span class="title">基本信息</span>
    </div>

    <div class="row">
        <span class="key">楼盘名称</span>

        <input type="text" placeholder="请输入楼盘名称"/>
    </div>

    <div class="row">
        <span class="key">价格</span>

        <input type="text" placeholder="请输入楼盘价格"/>元/m²/天
    </div>

    <div class="row">
        <span class="key">地址</span>

        <div class="row">
            <span>区:&nbsp;</span>

            <select id="district_list">
                <%-- <option value="volvo">Volvo</option>--%>
                <%-- <option value="saab">Saab</option>--%>
                <%-- <option value="opel">Opel</option>--%>
                <%-- <option value="audi">Audi</option>--%>
            </select>

            <span>&nbsp;&nbsp;区域:&nbsp;</span>

            <select id="zone_list">
                <%-- <option value="volvo">Volvo</option>--%>
                <%-- <option value="saab">Saab</option>--%>
                <%-- <option value="opel">Opel</option>--%>
                <%-- <option value="audi">Audi</option>--%>
            </select>

            <span>&nbsp;&nbsp;详细地址:&nbsp;</span>

            <input type="text" placeholder="请输入楼盘名称"/>
        </div>
    </div>
</div>

<div class="box">
    <div class="row">
        <span class="title">写字楼信息</span>
    </div>

    <div id="detail_info_list">
    </div>

    <div class="row">
        <button onclick="addTable()">添加</button>
    </div>

    <div class="row">
        <span class="key">楼盘介绍</span>

        <textarea class="introduce" rows="5"></textarea>
    </div>
</div>
</body>
</html>
