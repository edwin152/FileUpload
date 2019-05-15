<%--
  Created by IntelliJ IDEA.
  User: mlt
  Date: 2019/5/15
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/all.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>

<body>
	<div class="content_box flexed_column">
		<h3>登录</h3>
		<input type="text" id="user_name" value="" placeholder="用户名" />
		<input type="password" id="user_password" value="" placeholder="密码" />
		<input type="submit" class="login_btn" id="login" name="login" value="登录" />
	</div>
</body>
</html>
