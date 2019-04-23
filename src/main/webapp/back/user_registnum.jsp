<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" class="col-xs-12" style="height: 600px;"></div>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        myChart.setOption({
            title: {
                text: '近期用户注册量'
            },
            tooltip: {},
            legend: {
                data:['用户注册量']
            },
            xAxis: {
                data: ["今天","本周","本月","这个季度","半年内"]
            },
            yAxis: {},
            series: [{
                type: 'bar',
                data: []
            }]
        });
        init()
    });
    function init(){
    $.ajax({
        url:"/user/bar",
        datatype:"json",
        type:"get",
        success:function (data) {
            var myChart = echarts.init(document.getElementById('main'));
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '用户注册量',
                    data: data
                }]
            });
        }
    })
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