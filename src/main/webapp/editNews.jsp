<%--
  Created by IntelliJ IDEA.
  User: mlt
  Date: 2019/5/23
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑资讯</title>
    <link rel="stylesheet" type="text/css" href="layUI/css/layui.css"/>
    <script src="layUI/layui.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        window.onload = function(){
            let layedit;
            let textAreaId;
            layui.use('layedit', function(){
                layedit = layui.layedit;
                textAreaId = layedit.build('news_edit'); //建立编辑器
            });
            layui.use('form', function(){
                let form = layui.form;

                //监听提交
                form.on('submit(formDemo)', function(data){
                    console.log(layedit.getContent(textAreaId));
                    return false;
                });
            });
        }
    </script>
</head>
<body>
<form action="" method="post" >
    <textarea id="news_edit"  rows="0" cols="0" title="" style="display: none;"></textarea>
    <input type="submit" value="提交" class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo"/>
</form>
</body>
</html>
