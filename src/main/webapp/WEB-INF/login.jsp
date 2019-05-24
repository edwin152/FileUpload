<%--suppress HtmlUnknownTarget --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录</title>
    <script src="js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>

<script type="text/javascript">
    window.onload = function () {
        let request = window.location.search;
        let warning = http.getParameter(request, "warning");
        if (warning) {
            $("#warning").show();
            $("#warning").innerHTML = warning;
        }
    };
</script>

<body>
<form action="">
    <h3>登录</h3>
    <label for="user_name">用户名：</label>
    <input type="text" id="user_name" value="" name="username"/>
    <label for="user_password">密码：</label>
    <input type="password" id="user_password" name="password"/>
    <input type="submit" value="登录"/>
</form>
<span id="warning"></span>
</body>
</html>
