<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../statics/echarts/echarts.min.js"></script>
    <script src="../statics/echarts/china.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="../statics/boot/js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            // 指定图表的配置项和数据
            var option = {
                title : {
                    text: 'iphone销量',
                    subtext: '纯属虚构',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:['男','女']
                },
                visualMap: {
                    min: 0,
                    max: 2500,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:[
                            {"name":"河南","value":2}
                        ]
                    },{
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:[
                            {"name":"河南","value":2}
                        ]
                    }
                ]
            };
            myChart.setOption(option);
            //init();
        });
        //刷新数据
        function init(){
            $.ajax({
                url:"/user/map",
                type:"get",
                datatype:"json",
                success:function (data) {
                    var myChart = echarts.init(document.getElementById('main'));
                    myChart.setOption({
                        series : [

                            {
                                name: '男',
                                type: 'map',
                                mapType: 'china',
                                roam: false,
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data:data.man
                            },
                            {
                                name: '女',
                                type: 'map',
                                mapType: 'china',
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data:data.woman
                            }
                        ]
                    })
                }
            });
        }

    </script>

</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

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
</body>
</html>