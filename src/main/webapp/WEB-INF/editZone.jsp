<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    'use strict';

    let filter = {};
    let checkedDistrictId = 1;
    let checkedZoneId = 1;
    let imgList = [];

    window.onload = function () {
        getFilterList();
    };

    function getFilterList() {
        http.post({
            url: "../edit/zones",
            success: function (data) {
                filter.districtList = data;
                setFilterList();
            }
        });
    }

    function setFilterList() {
        if (!filter) {
            return;
        }
        setFilterListByName(filter.districtList, 'districtList', checkedDistrictId);
        let zoneList = filter.districtList[checkedDistrictId - 1].zoneList;
        setFilterListByName(zoneList, 'zoneList', checkedZoneId);
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
                        checkedDistrictId = list[i].id;
                        checkedZoneId = list[i].id;
                        break;
                    case "zoneList":
                        checkedZoneId = list[i].id;
                        break;
                }
                setFilterList();
                freshData();
            };
            conditionBox.appendChild(optionItem);
        }
    }

    function freshData() {
        let zone = getZone();
        if (zone.img_list) {
            imgList = zone.img_list.slice();
        } else {
            imgList = [];
        }
        freshImgList();

        let center = zone.center;
        if (center) {
            $("#center").prop("checked", true);
        } else {
            $("#center").prop("checked", false);
        }
    }

    function freshImgList() {
        let img_list = document.getElementById("img_list");
        img_list.innerHTML = "";
        for (let index in imgList) {
            if (!imgList.hasOwnProperty(index)) continue;

            let image = imgList[index];
            let img = document.createElement("img");
            img.setAttribute("src", image);
            img.onclick = function () {
                imgList.splice(parseInt(index), 1);
                freshImgList();
            };
            img_list.append(img);
        }
    }

    function upload(e) {
        if (checkedDistrictId === 1) {
            alert("请选择区");
            return;
        }

        console.log(e);
        http.upload({
            url: "../file/upload",
            file: e[0],
            success: function (data) {
                imgList.push(data.src);
                freshImgList();
            }
        });

        $("#add_image").val("");
    }

    function submit() {
        if (checkedDistrictId === 1) {
            alert("请选择区");
            return;
        }

        let center = document.getElementById("center").checked;
        http.post({
            url: "../edit/editZone",
            params: {
                zone_id: checkedZoneId,
                img_list: imgList,
                center: center,
            },
            success: function () {
                let zone = getZone();
                zone.img_list = imgList;
                zone.center = center;
            }
        });
    }

    function getZone() {
        for (let d of filter.districtList) {
            if (d.id !== checkedDistrictId) continue;
            for (let z of d.zoneList) {
                if (z.id !== checkedZoneId) continue;
                return z;
            }
        }
        return null;
    }
</script>

<body>
<div class="box">
    <div class="row">
        <span class="key">区：</span>

        <div id="districtList"></div>
    </div>

    <div class="row" id="zone">
        <span class="key">区域：</span>

        <div id="zoneList"></div>
    </div>
</div>


<div class="box">
    <div class="row">
        <label for="center">是否为商圈</label><input type="checkbox" id="center">
    </div>

    <div class="row" id="img_list">
    </div>

    <button onclick="$('#add_image').click()">添加图片</button>

    <input type="file" name="image" value="添加图片" id="add_image" onchange="upload(this.files)"
           style="display: none;" accept="image/jpeg,image/png,image/jpg,image/gif">
</div>

<div class="submit" onclick="submit()">提交</div>
</body>
</html>