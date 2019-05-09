<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>test</title>
		<link rel="stylesheet" type="text/css" href="css/all.css" />
		<link rel="stylesheet" type="text/css" href="css/index.css" />
	</head>
	<script>
		function getFilterList() {
			let xmlHttp = new XMLHttpRequest();
			xmlHttp.onreadystatechange = function() {
				if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
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
			xmlHttp.onreadystatechange = function() {
				if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
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
			xmlHttp.onreadystatechange = function() {
				if(xmlHttp.readyState === 4 && xmlHttp.status === 200) {
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
		<div class="search_box_main">
			<div class="top_search_box flexed_row">
				<input type="text" class="top_search" name="search" id="top_search" value="" placeholder="输入您要查找的楼盘或者区域商圈名称" />
				<input class="top_search_btn" type="submit" value="搜索" />
			</div>
		</div>
	</body>

</html>