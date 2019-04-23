<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>UEditor</title>
    <!-- 配置文件 -->
    <script type="text/javascript" src="../utf8-jsp/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../utf8-jsp/ueditor.all.js"></script>
</head>
<body>
<form action="/album/edit" method="post">
    id:<input name="id" type="text"/>
    <!-- 加载编辑器的容器 -->
    <div>
        <script id="container" name="abstracts" type="text/plain">
            这里写你的初始化内容
        </script>
    </div>
    ​
    <input type="submit" value="提交">
</form>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var editor = UE.getEditor('container');
</script>
</body>
</html>