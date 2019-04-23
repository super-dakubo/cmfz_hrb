<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        init();
    });
    function init() {
        $("#aha").empty();
        $.post(
            "/log4cmfz/show",
            function (result) {
                $.each(result,function (i,log) {
                    var message ="*"+log.success+"*"+"[" + log.whens + ":   管理员@"+ log.who + "]  ：" + log.what+"     ";
                    if(log.success=='成功'){
                        var h4 = $("<h4 style='color: green;'>"+message+"</h4>")
                    }else{
                        var h4 = $("<h4 style='color: red;'>"+message+"</h4>")
                    }
                    $("#aha").append(h4);
                })
            },
            "JSON"
        );
    }
    var goEasy = new GoEasy({
        appkey: "BS-20db983e298c46da9c08d2d6aa60d625"
    });
    //监听通道
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            var parse = JSON.parse(message.content);
            console.log(parse);
            if (typeof parse === "boolean") {
                init();
            }
        }
    });
</script>
<div id="aha">
</div>