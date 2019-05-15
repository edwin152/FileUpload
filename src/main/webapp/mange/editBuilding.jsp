<%--
  Created by IntelliJ IDEA.
  User: mlt
  Date: 2019/5/15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑写字楼信息</title>
    <link rel="stylesheet" type="text/css" href="../css/all.css"/>
    <link rel="stylesheet" type="text/css" href="../css/editBuilding.css"/>

    <script type="text/javascript">
        let keyNumber = 1;
        function addTable(event){
            let divTr = document.createElement("div");
            divTr.setAttribute("class", "table_tr");
            let divKey = document.createElement("div");
            divKey.setAttribute("class", "table_key");
            divTr.appendChild(divKey);
            let inputKey = document.createElement("input");
            inputKey.setAttribute("type", "text");
            inputKey.setAttribute("name", "building_info_key" + (keyNumber++));
            divKey.appendChild(inputKey);
            let divValue = document.createElement("div");
            divValue.setAttribute("class", "table_value");
            divTr.appendChild(divValue);
            let inputValue = document.createElement("input");
            inputValue.setAttribute("class", "building_info_value" + (keyNumber++));
            divValue.appendChild(inputValue);
            let divDelete = document.createElement("div");
            divDelete.setAttribute("class", "table_menu");
            divDelete.innerHTML = "删除";
            divDelete.onclick = function () {
                deleteInput(divDelete);
            };
            divTr.appendChild(divDelete);

            let parent = event.parentElement;
            parent.insertBefore(divTr, event);
        }

        function deleteInput(event){
            let parent = event.parentElement;
            let parentParent = parent.parentElement;
            parentParent.removeChild(parent);
        }
    </script>
</head>
<body>
<form action="">

    <div class="table_box">
        <div class="table_tr">
            <div class="table_title">写字楼图片</div>
        </div>
        <div class="table_tr" id="building_image_box">
            <img src="../img/test.jpg" class="building_image_item"/>
        </div>
        <div class="table_add">添加图片</div>
    </div>
    <div class="table_box">
        <div class="table_tr">
            <div class="table_title">基本信息</div>
        </div>
        <div class="table_tr">
            <div class="table_key">楼盘名称</div>
            <div class="table_value">
                <input type="text" class="building_input_value" name="building_name" placeholder="请输入楼盘名称"
                       required="required"/>
            </div>
        </div>
        <div class="table_tr">
            <div class="table_key">价格</div>
            <div class="table_value">
                <input type="text" name="building_name" placeholder="请输入楼盘价格" required="required"/>元/m²/天
            </div>
        </div>
        <div class="table_tr">
            <div class="table_key">地址</div>
            <div class="table_value">
                <div class="table_key">区域</div>
                <div class="table_value">
                    <select name="" >
                        <option value="volvo">Volvo</option>
                        <option value="saab">Saab</option>
                        <option value="opel">Opel</option>
                        <option value="audi">Audi</option>
                    </select>
                </div>
                <div class="table_key">区</div>
                <div class="table_value">
                    <select name="" >
                        <option value="volvo">Volvo</option>
                        <option value="saab">Saab</option>
                        <option value="opel">Opel</option>
                        <option value="audi">Audi</option>
                    </select>
                </div>
                <div class="table_key">详细地址</div>
                <div class="table_value">
                    <input type="text" name="building_name" placeholder="请输入楼盘名称"/>
                </div>
            </div>
        </div>
    </div>

    <div class="table_box">
        <div class="table_tr">
            <div class="table_title">写字楼信息</div>
        </div>
        <div class="table_tr">
            <div class="table_key">
                <input type="text" name="building_info_key1" placeholder="建筑面积:"/>
            </div>
            <div class="table_value">
                <input type="text" name="building_info_value2" placeholder="284000平方米"/>
            </div>
            <div class="table_menu" onclick="deleteInput(this)">删除</div>
        </div>
        <div class="table_add" onclick="addTable(this)">添加</div>
        <div class="table_tr">
            <div class="table_key">楼盘介绍</div>
            <div class="table_value">
                <textarea class="building_textarea" rows="5" placeholder="" name="building_textarea"></textarea>
            </div>
        </div>
    </div>
</form>
</body>
</html>
