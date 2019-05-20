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
    <link rel="stylesheet" type="text/css" href="../css/editBuilding.css"/>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript">
        let building_id;
        let district_id;
        let zone_id;
        let metroNameList = [];
        let districtList = [];
        let metroList = [];
        let metroIdList = [1];
        let detailInfoSize = 0;
        let detailInfoList = [
            {key: "建筑面积", value: ""},
            {key: "得房率", value: ""},
            {key: "客梯数", value: ""},
            {key: "开发商", value: ""},
            {key: "物业公司", value: ""},
        ];

        window.onload = function () {
            let request = window.location.search;
            building_id = http.getParameter(request, "building_id");
            freshDetailInfoList();
            getBuilding();
        };

        function getBuilding() {
            if (building_id) {
                http.post({
                    url: "../edit/building",
                    params: {
                        id: building_id,
                    },
                    success(data) {
                        // let img_list = [];
                        let notes = JSON.parse(data.notes);
                        district_id = data.district_id;
                        zone_id = data.zone_id;
                        metroNameList = data.metro_name_list;
                        document.getElementById("name").value = data.name;
                        document.getElementById("address").value = data.address;
                        document.getElementById("metro_intro").value = notes.metro_intro;
                        document.getElementById("bus_intro").value = notes.bus_intro;
                        document.getElementById("introduce").value = data.introduce;
                        detailInfoList = notes.detail_info;
                        freshDetailInfoList();
                        getFilters();
                    }
                });
            } else {
                getFilters();
            }
        }

        function getFilters() {
            http.post({
                url: "../filter/all",
                success(data) {
                    districtList = data.districtList;
                    metroList = data.metroList;
                    freshDistrictList(district_id, zone_id);

                    if (metroNameList) {
                        let idList = [];
                        for (let metroName of metroNameList) {
                            for (let metro of metroList) {
                                if (metro.name === metroName) {
                                    idList.push(metro.id);
                                    break;
                                }
                            }
                        }
                        idList.sort();
                        metroIdList = idList;
                    }
                    freshMetroList();
                },
            });
        }

        function freshDistrictList(district_id, zone_id) {
            let district_list = document.getElementById("district_list");
            district_list.innerHTML = "";
            let zone_list = document.getElementById("zone_list");
            zone_list.innerHTML = "";

            for (let i = 0; i < districtList.length; i++) {
                if (!districtList.hasOwnProperty(i)) continue;

                let district = districtList[i];

                let option = document.createElement("option");
                option.setAttribute("value", district.id);
                option.innerHTML = district.name;
                district_list.appendChild(option);

                if (district.id === district_id
                    || district_id === undefined && i === 0) {
                    option.setAttribute("selected", "selected");
                    for (let zone of district.zoneList) {
                        let option = document.createElement("option");
                        option.setAttribute("value", zone.id);
                        option.innerHTML = zone.name;
                        if (zone.id === zone_id) {
                            option.setAttribute("selected", "selected");
                        }
                        zone_list.appendChild(option);
                    }
                }
            }
        }

        function changeDistrict(district_id) {
            district_id = parseInt(district_id);
            freshDistrictList(district_id);
        }

        function freshDetailInfoList() {
            let detail_info_list = document.getElementById("detail_info_list");
            detail_info_list.innerHTML = "";
            for (let i in detailInfoList) {
                if (!detailInfoList.hasOwnProperty(i)) continue;

                let detailInfo = detailInfoList[i];
                let detail_info = createDetailInfo(detailInfo.key, detailInfo.value);
                detail_info_list.appendChild(detail_info);
            }
        }

        function createDetailInfo(key, value) {
            let id = detailInfoSize;

            let detail_info = document.createElement("div");
            detail_info.className = "row";

            let infoKey = document.createElement("input");
            infoKey.setAttribute("value", key);
            infoKey.setAttribute("id", "detail_info_key_" + id);
            detail_info.appendChild(infoKey);

            let infoValue = document.createElement("input");
            infoValue.setAttribute("value", value);
            infoValue.setAttribute("id", "detail_info_value_" + id);
            detail_info.appendChild(infoValue);

            let deleteBtn = document.createElement("button");
            deleteBtn.innerHTML = "删除";
            deleteBtn.onclick = function () {
                deleteDetailInfo(detail_info);
            };
            detail_info.appendChild(deleteBtn);

            detailInfoSize++;
            return detail_info;
        }

        function addDetailInfo() {
            let detail_info_list = document.getElementById("detail_info_list");
            let detail_info = createDetailInfo("", "");
            detail_info_list.appendChild(detail_info);
        }

        function deleteDetailInfo(view) {
            view.parentElement.removeChild(view);
        }

        function changeMetro(index, metroId) {
            metroId = parseInt(metroId);
            if (metroId === 1) {
                return;
            }

            let i = metroIdList.indexOf(metroId);
            if (i === -1) {
                metroIdList[index] = metroId;
            } else {
                metroIdList[index] = 1;
                alert("已选择该地铁线路");
            }
            freshMetroList();
        }

        function freshMetroList() {
            let metro_list = document.getElementById("metro_list");
            metro_list.innerHTML = "";

            for (let i in metroIdList) {
                if (!metroIdList.hasOwnProperty(i)) continue;

                let metroId = metroIdList[i];
                let delete_btn = document.createElement("button");
                delete_btn.innerHTML = "删除";
                delete_btn.onclick = function () {
                    deleteMetro(metroId);
                };

                let metro_select = document.createElement("select");
                metro_select.onchange = function () {
                    changeMetro(i, metro_select.value);
                };
                for (let j in metroList) {
                    if (!metroList.hasOwnProperty(j)) continue;

                    let metro_option = document.createElement("option");
                    metro_option.setAttribute("value", metroList[j].id);
                    metro_option.innerHTML = metroList[j].name;
                    if (metroList[j].id === metroId) {
                        metro_option.setAttribute("selected", "selected");
                    }

                    metro_select.appendChild(metro_option);
                }

                metro_list.appendChild(metro_select);
                metro_list.appendChild(delete_btn);
            }
        }

        function addMetro() {
            metroIdList.push(1);
            freshMetroList();
        }

        function deleteMetro(metroId) {
            let index = metroIdList.indexOf(metroId);
            metroIdList.splice(index, 1);
            freshMetroList();
        }

        function submit() {
            let img_list = ["https://www.tuotuozu.com/public/upload/20180509/c2124cee3b358c59b314beeadfdc63dc.jpg"];
            let name = document.getElementById("name").value;
            let district_id = document.getElementById("district_list").value;
            let zone_id = document.getElementById("zone_list").value;
            let metro_list = [];
            let address = document.getElementById("address").value;
            let metro_intro = document.getElementById("metro_intro").value;
            let bus_intro = document.getElementById("bus_intro").value;
            let detail_info_list = [];
            let introduce = document.getElementById("introduce").value;

            for (let id of metroIdList) {
                if (id === 1) continue;

                metro_list.push(id);
            }

            for (let i = 0; i < detailInfoSize; i++) {
                let detail_info_key = document.getElementById("detail_info_key_" + i);
                let detail_info_value = document.getElementById("detail_info_value_" + i);

                if (!detail_info_key || isEmpty(detail_info_key.value)) continue;
                if (!detail_info_value || isEmpty(detail_info_value.value)) continue;

                detail_info_list.push({
                    key: detail_info_key.value,
                    value: detail_info_value.value,
                });
            }

            if (isEmpty(name)) {
                alert("请输入名字");
                return;
            }
            if (isEmpty(zone_id) || district_id === zone_id) {
                alert("请选择地区");
                return;
            }
            if (isEmpty(metro_list)) {
                alert("请选择地铁");
                return;
            }
            if (isEmpty(address)) {
                alert("请输入地址");
                return;
            }

            http.post({
                url: "../edit/editBuilding",
                params: {
                    id: building_id,
                    name: name,
                    zone_id: zone_id,
                    address: address,
                    metro_list: metro_list,
                    notes: {
                        detail_info: detail_info_list,
                        metro_intro: metro_intro,
                        bus_intro: bus_intro,
                    },
                    introduce: introduce,
                    img_list: img_list,
                },
                success: function () {
                    window.close();
                }
            });
        }

        function isEmpty(value) {
            return !value || value.length === 0;
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

        <input type="text" id="name"/>
    </div>

    <div class="row">
        <span class="key">地址</span>

        <div class="flexed_row">
            <span>区:&nbsp;</span>

            <select id="district_list" onchange="changeDistrict(this.value)"></select>

            <span>&nbsp;&nbsp;区域:&nbsp;</span>

            <select id="zone_list"></select>

            <span>&nbsp;&nbsp;详细地址:&nbsp;</span>

            <input type="text" id="address"/>
        </div>
    </div>

    <div class="row">
        <span class="key">地铁</span>

        <div class="flexed_row" id="metro_list"></div>

        <button onclick="addMetro()">新增</button>
    </div>

    <div class="row">
        <span class="key">地铁</span>

        <textarea class="introduce" rows="2" id="metro_intro"></textarea>
    </div>

    <div class="row">
        <span class="key">公交</span>

        <textarea class="introduce" rows="2" id="bus_intro"></textarea>
    </div>
</div>

<div class="box">
    <div class="row">
        <span class="title">写字楼信息</span>
    </div>

    <div id="detail_info_list">
    </div>

    <div class="row">
        <button onclick="addDetailInfo()">添加</button>
    </div>

    <div class="row">
        <span class="key">楼盘介绍</span>

        <textarea class="introduce" rows="5" id="introduce"></textarea>
    </div>
</div>

<div class="submit" onclick="submit()">提交</div>
</body>
</html>