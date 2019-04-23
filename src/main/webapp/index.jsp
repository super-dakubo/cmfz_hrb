<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法洲后台管理系统</title>
    <link rel="stylesheet" href="./statics/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="./statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="./statics/boot/js/jquery-3.3.1.min.js"></script>
    <script src="./statics/boot/js/bootstrap.min.js"></script>
    <script src="./statics/jqgrid/js/trirand/jquery.jqGrid.min(1).js"></script>
    <script src="./statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script>

    </script>
</head>
<body>
<embed id="aa" width="0" height="0" loop="false" autostart="false"/>
<a href=""><span class="glyphicon glyphicon-save"></span> </a>
<input id="bb" type="button"  value="aa">
<hr>
<script type="text/javascript">
    $(function () {
        $("#bb").click(function () {
            $("#aa").prop("src","/chapter/getAudio?audioPath=1a.mp3&type=inline");
        });
    })
</script>
</body>
</html>
