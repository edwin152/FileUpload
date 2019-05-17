<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>点点租</title>
    <link rel="stylesheet" type="text/css" href="../css/editBuilding.css"/>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>

<script type="text/javascript">
    let building_id;
    let filter = {};
    let pageIndex = 0;
    let pageNum = 0;

    window.onload = function () {
        let request = window.location.search;
        building_id = http.getParameter(request, "building_id");
        getFilterList();
    };

    function getFilterList() {
        http.post({
            url: "../filter/office",
            params: {
                building_id: building_id,
                type_id: filter.checkedTypeId,
                area_range_id: filter.checkedAreaRangeId,
                price_range_id: filter.checkedPriceRangeId,
                decoration_id: filter.checkedDecorationId,
            },
            success: function (data) {
                filter = data;
                getOfficeList();
            }
        });
    }

    function getOfficeList(page) {
        http.post({
            url: "../edit/offices",
            params: {
                building_id: building_id,
                type_id: filter.checkedTypeId,
                area_range_id: filter.checkedAreaRangeId,
                price_range_id: filter.checkedPriceRangeId,
                decoration_id: filter.checkedDecorationId,
                page: page,
            },
            success: function (data) {
                filter.checkedTypeId = data.checkedTypeId;
                filter.checkedAreaRangeId = data.checkedAreaRangeId;
                filter.checkedPriceRangeId = data.checkedPriceRangeId;
                filter.checkedDecorationId = data.checkedDecorationId;
                setFilterList();
                pageIndex = data.pageIndex;
                pageNum = data.pageNum;
                setOfficeList(data.officeList);
            }
        });
    }

    function setFilterList() {
        if (!filter) {
            return;
        }
        setFilterListByName(filter.typeList, 'typeList', filter.checkedTypeId);
        setFilterListByName(filter.areaRangeList, 'areaRangeList', filter.checkedAreaRangeId);
        setFilterListByName(filter.priceRangeList, 'priceRangeList', filter.checkedPriceRangeId);
        setFilterListByName(filter.decorationList, 'decorationList', filter.checkedDecorationId);
    }

    function setFilterListByName(list, name, checkedId) {
        if (!list || list.length <= 2) {
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
                getOfficeList();
            };
            conditionBox.appendChild(optionItem);
        }
    }

    function setOfficeList(officeList) {
        let data_list = document.getElementById("data_list");
        if (pageIndex === 0) {
            data_list.innerHTML = "";
        }
        if (pageNum > pageIndex + 1) {
            $("#next_page").show();
        } else {
            $("#next_page").hide();
        }

        for (let office of officeList) {
            let box = document.createElement("div");
            box.className = "box row";

            // 图片
            let image = document.createElement("img");
            if (office.img_list && office.img_list.length > 0) {
                image.setAttribute("src", office.img_list[0]);
            }
            image.onclick = function () {
                openRoomDetail(office.id);
            };
            box.appendChild(image);

            let column = document.createElement("div");
            column.className = "column";

            let source_info = document.createElement("span");
            source_info.innerHTML = office.source_info;
            source_info.onclick = function () {
                openRoomDetail(office.id);
            };
            column.appendChild(source_info);
            let area = document.createElement("span");
            area.innerHTML = office.area + " m²";
            column.appendChild(area);
            let price = document.createElement("span");
            price.innerHTML = office.price + " 元/m²/天";
            column.appendChild(price);
            box.appendChild(column);

            let total_price = document.createElement("span");
            total_price.innerHTML = office.total_price + " 元/m²/天";
            box.appendChild(total_price);


            let button_list = document.createElement("div");
            button_list.className = "column";

            let edit_office = document.createElement("button");
            edit_office.innerHTML = "编辑";
            button_list.appendChild(edit_office);
            edit_office.onclick = function () {
                window.open("editOffice.jsp?building_id=" + building_id + "&office_id=" + office.id, "_blank");
            };
            let delete_office = document.createElement("button");
            delete_office.innerHTML = "删除";
            delete_office.onclick = function () {
                deleteBuilding(building.id, conditionBox);
            };
            button_list.appendChild(delete_office);
            box.appendChild(button_list);

            data_list.appendChild(box);
        }
    }

    function nextPage() {
        getOfficeList(pageIndex + 1);
    }

    function openRoomDetail(office_id) {
        window.open("../officeDetail.jsp?office_id=" + office_id, "_blank");
    }

    function deleteOffice(office_id, view) {
        http.post({
            url: "../edit/deleteOffice",
            params: {
                id: office_id,
            },
            success: function () {
                let parent = view.parentElement;
                parent.removeChild(view);
            }
        });
    }

    function addOffice() {
        window.open("editOffice.jsp?building_id=" + building_id, "_blank");
    }
</script>

<body>
<div class="box">
    <div class="row" id="type">
        <span class="key">类型：</span>

        <div id="typeList"></div>
    </div>

    <div class="row" id="areaRange">
        <span class="key">面积：</span>

        <div id="areaRangeList"></div>
    </div>

    <div class="row" id="priceRange">
        <span class="key">价格：</span>

        <div id="priceRangeList"></div>
    </div>

    <div class="row" id="decoration">
        <span class="key">装修：</span>

        <div id="decorationList"></div>
    </div>
</div>

<div class="submit" onclick="addOffice()">新增</div>

<div id="data_list"></div>

<div class="submit" id="next_page" onclick="nextPage()">下一页</div>
</body>
</html>