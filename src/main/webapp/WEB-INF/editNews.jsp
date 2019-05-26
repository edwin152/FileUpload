
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑资讯</title>
    <link rel="stylesheet" type="text/css" href="../layUI/css/layui.css"/>
    <script src="../layUI/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/jquery-1.12.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../js/utils.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        window.onload = function(){
            let layedit;
            let textAreaId;
            layui.use('layedit', function(){
                layedit = layui.layedit;
                layedit.set({
                    uploadImage: {
                        url: 'file/upload' //图片服务器接口url
                        ,type: 'post' //默认post
                    }
                });
                textAreaId = layedit.build('news_edit'); //建立编辑器
            });
            layui.use('form', function(){
                let form = layui.form;

                //监听提交
                form.on('submit(formDemo)', function(data){
                    let titleStr = data.form[0].value;
                    let subTitle = data.form[1].value;
                    // console.log(layedit.getContent(textAreaId));
                    http.post({
                        url: "/DDShop/edit/addNews",
                        params: {
                            title: titleStr,
                            sub_title: subTitle,
                            content: layedit.getContent(textAreaId)
                        },
                        success: function (data) {
                            console.log(data);
                            // let newsList = data;
                            layer.msg('成功');
                        }
                    });
                    return false;
                });
            });
        }
    </script>
</head>
<body>
<form action="" method="post" >
    <input type="text" id="title" name="title" placeholder="请输入标题"/>
    <input type="text" id="sub_title" name="title" placeholder="请输入副标题"/>
    <textarea id="news_edit"  rows="0" cols="0" title="" style="display: none;"></textarea>
    <input type="submit" value="提交" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo"/>
</form>
</body>
</html>
