<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>点点租</title>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>

<body>
<div class="search_box_main">
    <img src="https://images.diandianzu.com/Public/Home/v5/images/public/nav_logo.png" class="search_image" alt=""/>
    <div class="top_search_box flexed_row">
        <form action="search.jsp">
            <input type="text" class="top_search" name="keyword"
                   placeholder="输入您要查找的楼盘或者区域商圈名称"/>
            <input class="search_btn" type="submit" value="搜索"/>
        </form>
    </div>
</div>
</body>

</html>