<%--suppress HtmlFormInputWithoutLabel --%>
<%--suppress JSJQueryEfficiency --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>楼列表</title>
    <link rel="stylesheet" type="text/css" href="../css/manage.css"/>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>

<script type="text/javascript">
    let keyword;
    let filter = {};
    let pageIndex = 0;
    let pageNum = 0;

    window.onload = function () {
        let request = window.location.search;
        keyword = http.getParameter(request, "keyword");
        if (keyword) {
            keyword = decodeURI(keyword);
            document.getElementById("keyword_input").setAttribute("value", keyword);
        }
        filter.checkedDistrictId = http.getParameter(request, "district_id");
        filter.checkedZoneId = http.getParameter(request, "zone_id");
        filter.checkedMetroId = http.getParameter(request, "metro_id");
        filter.checkedTypeId = http.getParameter(request, "type_id");
        filter.checkedAreaRangeId = http.getParameter(request, "area_range_id");
        filter.checkedPriceRangeId = http.getParameter(request, "price_range_id");
        filter.checkedDecorationId = http.getParameter(request, "decoration_id");
        getFilterList();
    };

    function getFilterList() {
        http.post({
            url: "../filter/all",
            params: {
                district_id: filter.checkedDistrictId,
                zone_id: filter.checkedZoneId,
                metro_id: filter.checkedMetroId,
                type_id: filter.checkedTypeId,
                area_range_id: filter.checkedAreaRangeId,
                price_range_id: filter.checkedPriceRangeId,
                decoration_id: filter.checkedDecorationId,
            },
            success: function (data) {
                filter = data;
                getBuildingList();
            }
        });
    }

    function getBuildingList(page) {
        http.post({
            url: "../edit/buildings",
            params: {
                keyword: keyword,
                district_id: filter.checkedDistrictId,
                zone_id: filter.checkedZoneId,
                metro_id: filter.checkedMetroId,
                type_id: filter.checkedTypeId,
                area_range_id: filter.checkedAreaRangeId,
                price_range_id: filter.checkedPriceRangeId,
                decoration_id: filter.checkedDecorationId,
                page: page,
            },
            success: function (data) {
                filter.checkedDistrictId = data.checkedDistrictId;
                filter.checkedZoneId = data.checkedZoneId;
                filter.checkedMetroId = data.checkedMetroId;
                filter.checkedTypeId = data.checkedTypeId;
                filter.checkedAreaRangeId = data.checkedAreaRangeId;
                filter.checkedPriceRangeId = data.checkedPriceRangeId;
                filter.checkedDecorationId = data.checkedDecorationId;
                setFilterList();
                pageIndex = data.pageIndex;
                pageNum = data.pageNum;
                setBuildingList(data.buildingList);
            },
        });
    }

    function setFilterList() {
        if (!filter) {
            return;
        }
        setFilterListByName(filter.districtList, 'districtList', filter.checkedDistrictId);
        let zoneList = filter.districtList[filter.checkedDistrictId - 1].zoneList;
        setFilterListByName(zoneList, 'zoneList', filter.checkedZoneId);
        setFilterListByName(filter.metroList, 'metroList', filter.checkedMetroId);
    }

    function setFilterListByName(list, name, checkedId) {
        if (!list || list.length < 2) {
            $("#" + name.substr(0, name.length - 4)).hide();
        } else {
            $("#" + name.substr(0, name.length - 4)).show();
        }
        let conditionBox = document.getElementById(name);
        conditionBox.innerHTML = "";
        for (let i = 0; list && i < list.length; i++) {
            let optionItem = document.createElement("span");
            if (list[i].id === checkedId) {
                optionItem.className = 'choose_value_positive';
            } else {
                optionItem.className = 'choose_value';
            }
            optionItem.innerHTML = list[i].name;
            optionItem.onclick = function () {
                switch (name) {
                    case "districtList":
                        filter.checkedDistrictId = list[i].id;
                        break;
                    case "zoneList":
                        filter.checkedZoneId = list[i].id;
                        break;
                    case "metroList":
                        filter.checkedMetroId = list[i].id;
                        break;
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
                getBuildingList();
            };
            conditionBox.appendChild(optionItem);
        }
    }

    function setBuildingList(buildingList) {
        let data_list = document.getElementById("data_list");
        if (pageIndex === 0) {
            data_list.innerHTML = "";
        }
        if (pageNum > pageIndex + 1) {
            $("#next_page").show();
        } else {
            $("#next_page").hide();
        }

        for (let building of buildingList) {
            let conditionBox = document.createElement("div");
            conditionBox.className = "box row";

            let imgLeft = document.createElement("img");
            imgLeft.setAttribute("src", building.img_list[0]);
            imgLeft.onclick = function () {
                openDetail(building.id);
            };

            let dataInfoBox = document.createElement("div");
            dataInfoBox.className = "column";

            let itemName = document.createElement("span");
            itemName.className = "row choose_value";
            itemName.innerHTML = building.name;
            itemName.onclick = function () {
                openDetail(building.id);
            };

            let itemAddress = document.createElement("span");
            itemAddress.className = "row";
            let district_name = document.createElement("span");
            district_name.className = "choose_value";
            district_name.innerHTML = building.district_name;
            district_name.onclick = function () {
                window.open("../search.jsp?district_id=" + building.district_id);
            };
            let zone_name = document.createElement("span");
            zone_name.className = "choose_value";
            zone_name.innerHTML = building.zone_name;
            zone_name.onclick = function () {
                window.open("../search.jsp?district_id=" + building.district_id
                    + "&zone_id=" + building.zone_id
                );
            };
            itemAddress.append("[ ");
            itemAddress.appendChild(district_name);
            itemAddress.append(" ] - [ ");
            itemAddress.appendChild(zone_name);
            itemAddress.append(" ] |  " + building.address);

            let itemPosition = document.createElement("span");
            itemPosition.className = "row";
            itemPosition.innerHTML = "共有" + building.office_num + "个房源";

            let button_list = document.createElement("div");
            button_list.className = "column";

            let edit_building = document.createElement("button");
            edit_building.innerHTML = "编辑";
            edit_building.onclick = function () {
                window.open("editBuilding?building_id=" + building.id, "_blank");
            };

            let add_office = document.createElement("button");
            add_office.innerHTML = "新增办公室";
            add_office.onclick = function () {
                window.open("editOffice?building_id=" + building.id, "_blank");
            };

            let manage_office = document.createElement("button");
            manage_office.innerHTML = "管理办公室";
            manage_office.onclick = function () {
                window.open("offices?building_id=" + building.id, "_blank");
            };

            let delete_building = document.createElement("button");
            delete_building.innerHTML = "删除";
            delete_building.onclick = function () {
                deleteBuilding(building.id, conditionBox);
            };

            button_list.appendChild(edit_building);
            button_list.appendChild(add_office);
            button_list.appendChild(manage_office);
            button_list.appendChild(delete_building);

            dataInfoBox.appendChild(itemName);
            dataInfoBox.appendChild(itemAddress);
            dataInfoBox.appendChild(itemPosition);

            conditionBox.appendChild(imgLeft);
            conditionBox.appendChild(dataInfoBox);
            conditionBox.appendChild(button_list);

            data_list.appendChild(conditionBox);
        }
    }

    function nextPage() {
        getBuildingList(pageIndex + 1);
    }

    function search() {
        let searchInput = document.getElementById("top_search");
        keyword = searchInput.value;
        getBuildingList();
    }

    function openDetail(building_id) {
        window.open("../buildingDetail.html?building_id=" + building_id, "_blank");
    }

    function deleteBuilding(building_id, view) {
        http.post({
            url: "../edit/deleteBuilding",
            params: {
                id: building_id,
            },
            success: function () {
                let parent = view.parentElement;
                parent.removeChild(view);
            }
        });
    }

    function addBuilding() {
        window.open("editBuilding", "_blank");
    }
</script>

<body>
<form action="buildings.jsp">
    <div class="box row">
        <input type="text" name="keyword" id="keyword_input"/>

        <button>搜索</button>
    </div>
</form>

<div class="box">
    <div class="row">
        <span class="key">区：</span>

        <div id="districtList"></div>
    </div>

    <div class="row" id="zone">
        <span class="key">区域：</span>

        <div id="zoneList"></div>
    </div>

    <div class="row" id="metro">
        <span class="key">地铁：</span>

        <div id="metroList"></div>
    </div>
</div>

<div class="submit" onclick="addBuilding()">新增</div>

<div id="data_list"></div>

<div class="submit" id="next_page" onclick="nextPage()">下一页</div>
</body>
</html>