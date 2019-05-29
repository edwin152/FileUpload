<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑资讯</title>
    <link rel="stylesheet" type="text/css" href="../layUI/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../css/all.css"/>
    <script src="../layUI/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">

        window.onload = function () {

            initLayUI();

            getNewsTag();
        };

        function getNewsById(newsId) {
            http.post({
                url: "../edit/news",
                success: function (data) {
                    document.getElementById("title").innerHTML = data.title;
                    let img = JSON.stringify(data.img_list);
                    if (img && img.length > 0){
                        document.getElementById("cover_img").innerHTML = data.title;
                    }
                    document.getElementById("sub_title").innerHTML = data.sub_title;
                    let typeList = document.getElementById("type_box").children;
                    for (let i = 0, len = typeList.length; i < len; i++){
                        if (typeList[i].getAttribute("value") === data.news_tag_id){
                            typeList[i].setAttribute("selected", "")
                        }
                    }
                    let hotList = document.getElementById("is_hot").children;
                    let isHot = data.hot ? '1' : '0';
                    for (let i = 0, len = hotList.length; i < len; i++){
                        if (typeList[i].getAttribute("value") === isHot){
                            typeList[i].setAttribute("selected", "")
                        }
                    }
                    document.getElementById("news_edit").innerHTML = data.content;
                }
            });
        }

        function initLayUI() {
            let layedit;
            let textAreaId;
            let imageUrl;
            layui.use('layedit', function () {
                layedit = layui.layedit;
                layedit.set({
                    uploadImage: {
                        //图片服务器接口url
                        url: '../file/upload',
                        // url: '47.96.165.78:8080/ddzu/file/upload',
                    }
                });
                textAreaId = layedit.build('news_edit'); //建立编辑器
            });
            layui.use('form', function () {
                let form = layui.form;

                //监听提交
                form.on('submit(formDemo)', function (data) {
                    let titleStr = data.field.title;
                    let subTitle = data.field.sub_title;
                    let type = data.field.type;
                    let hot = data.field.hot === '1';
                    // console.log(layedit.getContent(textAreaId));
                    http.post({
                        url: "../edit/addNews",
                        params: {
                            title: titleStr,
                            sub_title: subTitle,
                            content: layedit.getContent(textAreaId),
                            news_tag_id: type,
                            hot: hot,
                            img_list: [imageUrl]
                        },
                        success: function (data) {
                            console.log(data);
                            // let newsList = data;
                            layer.msg('编辑成功');
                        }
                    });
                    return false;
                });
            });
            layui.use('upload', function () {
                let upload = layui.upload;

                //执行实例
                let uploadInst = upload.render({
                    elem: '#upload_img' //绑定元素
                    , url: '../file/upload' //上传接口
                    , acceptMime: 'image/*'
                    , done: function (res) {
                        //上传完毕回调
                        console.log(res);
                        let coverImg = document.getElementById("cover_img");
                        coverImg.setAttribute("src", res.data.src);
                        imageUrl = res.data.src;
                        coverImg.setAttribute("style", "");
                    }
                    , error: function () {
                        //请求异常回调
                        console.log("失败");
                    }
                });
            });
        }

        function getNewsTag() {
            http.post({
                url: "../filter/newsTag",
                success: function (data) {
                    filter = data;
                    if (!filter.newsTagList) return;
                    let typeBox = document.getElementById("type_box");
                    for (let i = 0, len = filter.newsTagList.length; i < len; i++) {
                        let typeItem = document.createElement("option");
                        typeItem.innerHTML = filter.newsTagList[i].name;
                        typeItem.setAttribute("value", filter.newsTagList[i].id);
                        typeBox.appendChild(typeItem);
                    }

                    let request = window.location.search;
                    let newsId = http.getParameter(request, "news_id");
                    getNewsById(newsId);
                }
            });
        }

        function upload(e) {
            console.log(e);
            http.upload({
                url: "../file/upload",
                file: e[0],
                success: function (data) {
                    imgList.push(data.src);
                    let coverImg = document.getElementById("cover_img");
                    coverImg.setAttribute("src", data.src)
                }
            });

            $("#add_image").val("");
        }
    </script>
</head>
<body>
<div class="win matop">
    <form action="" method="post" class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label" for="title">资讯标题</label>
            <div class="layui-input-block">
                <input type="text" id="title" name="title" required lay-verify="required" placeholder="请输入标题"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="sub_title">封面图</label>
            <div class="layui-input-block">
                <%--<button onclick="$('#add_image').click()">添加图片</button>--%>
                <%--<input type="file" name="image" value="添加图片" id="add_image" onchange="upload(this.files)"--%>
                <%--style="display: none;" accept="image/jpeg,image/png,image/jpg,image/gif">--%>
                <button type="button" class="layui-btn" id="upload_img">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
            </div>
        </div>
        <div class="layui-input-block">
            <img id="cover_img" class="image_100_100" style="display: none;"/>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="sub_title">副标题</label>
            <div class="layui-input-block">
                <input type="text" id="sub_title" name="sub_title" required lay-verify="" placeholder="请输入资讯类型"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">资讯类型</label>
            <div class="layui-input-block">
                <select id="type_box" title="" name="type" lay-verify="" class="layui-form-select">
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">是否热门</label>
            <div class="layui-input-block" id="is_hot">
                <input type="radio" name="hot" value="1" title="是">
                <input type="radio" name="hot" value="0" title="否" checked>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label" for="news_edit">资讯内容</label>
            <div class="layui-input-block">
            <textarea id="news_edit" class="layui-textarea" rows="0" cols="0" title=""
                      style="display: none;"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
            </div>
        </div>
    </form>
</div>
</body>
</html>
