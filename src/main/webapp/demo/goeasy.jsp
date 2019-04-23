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
    <title>Document</title>3
    <script>
        var goEasy = new GoEasy({
            appkey: "BS-20db983e298c46da9c08d2d6aa60d625"
        });
        goEasy.subscribe({
            channel: "my_channel",
            onMessage: function (message) {
                alert("new message");
                var split = message.content.split("*");
                if(split[1]!='me'){
                var span=$("<span style='color:red;'/>").text(split[0]);
                console.log(span);
                var div = $("<div/>").css("width","100px");
                div.append(new Date().toLocaleDateString()+"&nbsp;&nbsp;other:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append("<br/>").append(span).append("<br/>");
                $("#body").append(div);
                }
            }
        });
        $(function () {
            $("#bb").click(function () {
                var val = $("#aa").val();
                $("#aa").val('');
                var span=$("<span  style='color:blue;'/>").text(val);
                console.log(span);
                var div = $("<div/>").css("width","100px").css("float","right");
                div.append(new Date().toLocaleDateString()+"&nbsp;&nbsp;me:&nbsp;&nbsp;&nbsp;&nbsp&nbsp;;").append("<br/>").append(span).append("<br/>");
                $("#body").append(div);
                var goEasy = new GoEasy({
                    appkey: "BC-2b66fbf505a54de1a1ca0b060dc1be20"
                });//GoEasy-OTP可以对appkey进行有效保护，详情请参考 7.GoEasy-OTP
                goEasy.publish({
                    channel: "my_channel",
                    message: val+"*me"
                });
            });
        })

    </script>
</head>
<body>
<div id="body" style="height: 400px;width: 600px;border: #8a6d3b 1px solid;">

</div>
<textarea id="aa" style="height: 100px;width: 600px;"></textarea>
<input id="bb" type="button" id="bb" value="提交">
</body>
</html>