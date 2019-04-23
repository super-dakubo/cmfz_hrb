<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../statics/echarts/echarts.min.js"></script>
    <script src="../statics/echarts/china.js"></script>
    <script src="../statics/echarts/earth.min.js"></script>

    <script src="../statics/boot/js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            // 指定图表的配置项和数据
            var option = {
                    backgroundColor: '#000',
                    globe: {
                        baseTexture: "data-gl/asset/world.topo.bathy.200401.jpg",
                        heightTexture: "data-gl/asset/bathymetry_bw_composite_4k.jpg",
                        displacementScale: 0.2,
                        shading: 'realistic',
                        environment: 'data-gl/asset/starfield.jpg',
                        realisticMaterial: {
                            roughness: '/asset/get/s/data-1497599804873-H1SHkG-mZ.jpg',
                            metalness: '/asset/get/s/data-1497599800643-BJbHyGWQW.jpg',
                            textureTiling: [8, 4]
                        },
                        postEffect: {
                            enable: true
                        },
                        viewControl: {
                            autoRotate: false
                        },
                        light: {
                            main: {
                                intensity: 2,
                                shadow: true
                            },
                            ambientCubemap: {
                                texture: 'data-gl/asset/pisa.hdr',
                                exposure: 2,
                                diffuseIntensity: 2,
                                specularIntensity: 2
                            }
                        }
                    }
                };
            myChart.setOption(option);
        });

    </script>

</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>