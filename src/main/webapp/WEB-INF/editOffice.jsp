<%--suppress HtmlUnknownTarget --%>
<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>编辑写办公室信息</title>
    <link rel="stylesheet" type="text/css" href="../css/manage.css"/>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript">
        let office_id;
        let building_id;
        let type_id;
        let decoration_id;
        let canRegister;
        let imgList = [];
        let typeList = [];
        let decorationList = [];

        window.onload = function () {
            let request = window.location.search;
            office_id = http.getParameter(request, "office_id");
            building_id = http.getParameter(request, "building_id");
            getOffice();
        };

        function getOffice() {
            if (office_id) {
                http.post({
                    url: "../edit/office",
                    params: {
                        id: office_id,
                    },
                    success(data) {
                        imgList = data.img_list;
                        let notes = JSON.parse(data.notes);
                        type_id = data.type_id;
                        decoration_id = data.decoration_id;
                        metroNameList = data.metro_name_list;
                        canRegister = data.can_register;
                        document.getElementById("area").value = data.area;
                        document.getElementById("price").value = data.price;
                        document.getElementById("utilization_rate").value = data.utilization_rate * 100;
                        document.getElementById("rent_free_period").value = data.rent_free_period;
                        document.getElementById("rent_payment").value = notes.rent_payment;
                        document.getElementById("visit_time").value = notes.visit_time;
                        document.getElementById("earliest_rent").value = notes.earliest_rent;
                        document.getElementById("shortest_period").value = notes.shortest_period;
                        document.getElementById("floor_info").value = notes.floor_info;
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
                    typeList = data.typeList;
                    freshTypeList(type_id);
                    decorationList = data.decorationList;
                    freshDecorationList(decoration_id);
                    freshCanRegisterList(canRegister === undefined ? true : canRegister);
                },
            });
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

        function freshTypeList(type_id) {
            let type_list = document.getElementById("type_list");
            type_list.innerHTML = "";

            for (let type of typeList) {
                let type_option = document.createElement("option");
                type_option.setAttribute("value", type.id);
                type_option.innerHTML = type.name;
                if (type_id === type.id) {
                    type_option.setAttribute("selected", "selected");
                }

                type_list.appendChild(type_option);
            }
        }

        function freshDecorationList(decoration_id) {
            let decoration_list = document.getElementById("decoration_list");
            decoration_list.innerHTML = "";

            for (let decoration of decorationList) {
                let decoration_option = document.createElement("option");
                decoration_option.setAttribute("value", decoration.id);
                decoration_option.innerHTML = decoration.name;
                if (decoration_id === decoration.id) {
                    decoration_option.setAttribute("selected", "selected");
                }

                decoration_list.appendChild(decoration_option);
            }
        }

        function freshCanRegisterList(can_register) {
            let can_register_list = document.getElementById("can_register_list");
            can_register_list.innerHTML = "";

            let can_register_true = document.createElement("option");
            can_register_true.setAttribute("value", "true");
            can_register_true.innerHTML = "能";

            let can_register_false = document.createElement("option");
            can_register_false.setAttribute("value", "false");
            can_register_false.innerHTML = "否";

            if (can_register) {
                can_register_true.setAttribute("selected", "selected");
            } else {
                can_register_false.setAttribute("selected", "selected");
            }

            can_register_list.appendChild(can_register_true);
            can_register_list.appendChild(can_register_false);
        }

        function submit() {
            let type_id = document.getElementById("type_list").value;
            let area = document.getElementById("area").value;
            let price = document.getElementById("price").value;
            let decoration_id = document.getElementById("decoration_list").value;
            let utilization_rate = document.getElementById("utilization_rate").value;
            let can_register = document.getElementById("can_register_list").value;
            let rent_free_period = document.getElementById("rent_free_period").value;
            let rent_payment = document.getElementById("rent_payment").value;
            let visit_time = document.getElementById("visit_time").value;
            let earliest_rent = document.getElementById("earliest_rent").value;
            let shortest_period = document.getElementById("shortest_period").value;
            let floor_info = document.getElementById("floor_info").value;

            if (isEmpty(imgList)) {
                alert("请上传图片");
                return;
            }
            if (isEmpty(area)) {
                alert("请输入地址");
                return;
            }
            if (isEmpty(price)) {
                alert("请输入价格");
                return;
            }
            if (decoration_id === "1") {
                alert("请选择装修");
                return;
            }
            if (isEmpty(utilization_rate)) {
                alert("请输入使用率");
                return;
            }
            if (isEmpty(rent_free_period)) {
                alert("请输入免租期");
                return;
            }
            if (isEmpty(rent_payment)) {
                alert("请输入付款方式");
                return;
            }
            if (isEmpty(visit_time)) {
                alert("请输入看房时间");
                return;
            }
            if (isEmpty(earliest_rent)) {
                alert("请输入最早可租");
                return;
            }
            if (isEmpty(shortest_period)) {
                alert("请输入最短租期");
                return;
            }
            if (isEmpty(floor_info)) {
                alert("请输入楼层信息");
                return;
            }

            utilization_rate = parseFloat(utilization_rate) / 100;

            http.post({
                url: "../edit/editOffice",
                params: {
                    id: office_id,
                    building_id: building_id,
                    type_id: type_id,
                    area: area,
                    price: price,
                    decoration_id: decoration_id,
                    utilization_rate: utilization_rate,
                    can_register: can_register,
                    rent_free_period: rent_free_period,
                    notes: {
                        rent_payment: rent_payment,
                        visit_time: visit_time,
                        earliest_rent: earliest_rent,
                        shortest_period: shortest_period,
                        floor_info: floor_info,
                    },
                    img_list: imgList
                },
                success: function () {
                    window.close();
                }
            });
        }

        function isEmpty(value) {
            return !value || value.length === 0;
        }

        function upload(e) {
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
    </script>
</head>
<body>
<div class="box">
    <div class="row">
        <span class="title">写字楼图片</span>
    </div>

    <div class="row" id="img_list">
    </div>

    <button onclick="$('#add_image').click()">添加图片</button>

    <input type="file" name="image" value="添加图片" id="add_image" onchange="upload(this.files)"
           style="display: none;" accept="image/jpeg,image/png,image/jpg,image/gif">
</div>

<div class="box">
    <div class="row">
        <span class="title">基本信息</span>
    </div>

    <div class="row">
        <label class="key" for="area">面积</label>

        <input type="text" id="area"/>m²
    </div>

    <div class="row">
        <label class="key" for="price">价格</label>

        <input type="text" id="price"/>元/m²/天
    </div>

    <div class="row">
        <label class="key" for="utilization_rate">使用率</label>

        <input type="text" id="utilization_rate"/>%
    </div>

    <div class="row">
        <label class="key" for="rent_free_period">免租期</label>

        <input type="text" id="rent_free_period"/>
    </div>

    <div class="row">
        <label class="key" for="type_list">类型</label>

        <select id="type_list"></select>
    </div>

    <div class="row">
        <label class="key" for="decoration_list">装修</label>

        <select id="decoration_list"></select>
    </div>

    <div class="row">
        <label class="key" for="can_register_list">能否注册</label>

        <select id="can_register_list"></select>
    </div>
</div>

<div class="box">
    <div class="row">
        <span class="title">办公室信息</span>
    </div>

    <div class="row">
        <label class="key" for="rent_payment">支付方式</label>

        <input type="text" id="rent_payment"/>
    </div>

    <div class="row">
        <label class="key" for="visit_time">看房时间</label>

        <input type="text" id="visit_time"/>
    </div>

    <div class="row">
        <label class="key" for="earliest_rent">最早可租</label>

        <input type="text" id="earliest_rent"/>
    </div>

    <div class="row">
        <label class="key" for="shortest_period">最短租期</label>

        <input type="text" id="shortest_period"/>
    </div>

    <div class="row">
        <label class="key" for="floor_info">楼层信息</label>

        <input type="text" id="floor_info"/>
    </div>
</div>

<div class="submit" onclick="submit()">提交</div>
</body>
</html>
