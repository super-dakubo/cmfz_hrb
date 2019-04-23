<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form method="post" action="/slideshow/xls2sql" enctype="multipart/form-data">
    上传xls文档<input type="file" name="file"/>
    页名<input name="sheetName"/>
    <input type="submit" value="提交">
</form>
</body>
</html>