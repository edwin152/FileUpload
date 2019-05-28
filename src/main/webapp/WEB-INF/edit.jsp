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

    function addNews() {
        http.post({
            url: "edit/addNews",
            params: {
                title: "123",
                sub_title: "456",
                content: "123456",
            }
        });
    }

    function deleteNews() {
        http.post({
            url: "edit/deleteNews",
            params: {
                id: 1558868332127,
            }
        });
    }

    function editNews() {
        http.post({
            url: "edit/editNews",
            params: {
                id: 1558868332127,
                title: "456",
                sub_title: "789",
                content: "456789",
            }
        });
    }

    function newsList() {
        http.post({
            url: "edit/newsList",
            params: {}
        });
    }
</script>
<body>
<button onclick="reset()">reset</button>
<button onclick="addNews()">addNews</button>
<button onclick="deleteNews()">deleteNews</button>
<button onclick="editNews()">editNews</button>
<button onclick="newsList()">newsList</button>
</body>
</html>