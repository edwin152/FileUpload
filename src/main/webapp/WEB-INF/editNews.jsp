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
            let layedit;
            let textAreaId;
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
                    let titleStr = data.form[0].value;
                    let subTitle = data.form[1].value;
                    // console.log(layedit.getContent(textAreaId));
                    http.post({
                        url: "../edit/addNews",
                        params: {
                            title: titleStr,
                            sub_title: subTitle,
                            content: layedit.getContent(textAreaId)
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
        }
    </script>
</head>
<body>
<div class="win matop">
    <form action="" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">资讯标题</label>
            <div class="layui-input-block">
                <input type="text" id="title" name="title" required lay-verify="required" placeholder="请输入标题"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">资讯类型</label>
            <div class="layui-input-block">
                <input type="text" id="sub_title" name="sub_title" required lay-verify="required" placeholder="请输入资讯类型"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
         <%-- TODO 下拉框 --%>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">资讯内容</label>
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
