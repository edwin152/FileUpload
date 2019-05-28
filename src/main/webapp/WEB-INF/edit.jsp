<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Initialize</title>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>
</head>
<script>
    function reset() {
        http.post({
            url: "edit/reset"
        });
    }
</script>
<body>
<button onclick="reset()">reset</button>
</body>
</html>
