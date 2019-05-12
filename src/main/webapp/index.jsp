<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>test</title>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<script>

    window.onload = function () {
        addBuilding();
        for (let i = 0; i < 15; i++) {
            addOffice(i + 1);
        }
    };

    function getFilterList() {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                document.getElementById("test").innerHTML = xmlHttp.responseText;
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "info/filters", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send();
    }

    function getOfficeList() {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                document.getElementById("test").innerHTML = xmlHttp.responseText;
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "search/offices", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send();
    }

    function addBuilding() {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                document.getElementById("test").innerHTML = xmlHttp.responseText;
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "edit/addBuilding", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("name=Four Lock Center" +
            "&zone_id=41" +
            "&address=The Bond No.1" +
            "&metro_list=[3]" +
            "&img_list=[\"https://www.tuotuozu.com/public/upload/20180325/cover/a572a2428ffc977a8d8748546986c7be.jpg\"]");
    }

    function addOffice(name) {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                document.getElementById("test").innerHTML = xmlHttp.responseText;
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "edit/addOffice", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("name=" + name +
            "&building_id=1" +
            "&type_id=2" +
            "&area_value=" + (name % 2 === 0 ? 450 : name * 100) +
            "&price=8" +
            "&decoration_id=2" +
            "&img_list=[\"https://www.tuotuozu.com/public/upload/20180325/cover/a572a2428ffc977a8d8748546986c7be.jpg\"]");
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