<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="../statics/boot/js/jquery-3.3.1.min.js"></script>
    <title>Document</title>
    <script>
        $(function () {
            $("#bb").click(function () {
                var val = $("#aa").val();
                var goEasy = new GoEasy({
                    appkey: "BC-2b66fbf505a54de1a1ca0b060dc1be20"
                });//GoEasy-OTP可以对appkey进行有效保护，详情请参考 7.GoEasy-OTP
                goEasy.publish({
                    channel: "my_channel",
                    message: val
                });
            });
        })

    </script>
</head>
<body>

<input id="aa" type="text">
<input type="button" id="bb" value="提交">
</body>
</html>