<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>test</title>
</head>
<%--suppress EqualityComparisonWithCoercionJS --%>
<script>
    function getFilterList() {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
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
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                document.getElementById("test").innerHTML = xmlHttp.responseText;
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "search/offices", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send();
    }

    function addOffice() {
        let xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                document.getElementById("test").innerHTML = xmlHttp.responseText;
            }
        };
        xmlHttp.charset = "utf-8";
        xmlHttp.open("POST", "edit/addOffice", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("name=edwin" +
            "&zone_id=10" +
            "&address=ZhongHuan No.1" +
            "&metro_list=[3]" +
            "&type_id=1" +
            "&area_value=523" +
            "&price=8" +
            "&decoration_id=1");
    }
</script>
<body>
<p id="test">Hello World!</p>
<button type="button" onclick="getFilterList()">get filter list</button>
<button type="button" onclick="getOfficeList()">get office list</button>
<button type="button" onclick="addOffice()">add office</button>
</body>
</html>