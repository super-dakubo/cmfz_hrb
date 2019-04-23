<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>持明法洲后台管理系统</title>
    <link rel="stylesheet" href="../statics/boot/css/bootstrap.min.css">
    <!--引入jqgrid中主题css-->
    <link rel="stylesheet" href="../statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <!--引入js文件-->
    <script src="../statics/boot/js/jquery-3.3.1.min.js"></script>
    <script src="../statics/boot/js/bootstrap.min.js"></script>
    <script src="../statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="../statics/jqgrid/js/trirand/jquery.jqGrid.min(1).js"></script>
    <link rel="icon" href="../img/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="../css/login.css" type="text/css">
    <script type="text/javascript" src="../script/jquery.validate.js"></script>
    <!-- 引入 ECharts 文件 -->
    <script src="../statics/echarts/echarts.min.js"></script>
    <script src="../statics/echarts/china.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="../utf8-jsp/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../utf8-jsp/ueditor.all.js"></script>
    <%--魔方--%>
    <style>
        label.error{
            padding-left: 16px;
            padding-bottom: 2px;
            font-weight: bold;
            color: #EA5200;
        }
    </style>
    <script>
        //登陆验证
        <%--<c:if test="${empty sessionScope.login}">--%>
        <%--alert("请重新登陆");--%>
            <%--window.location.replace("../login.jsp");--%>
        <%--</c:if>--%>
        $(function () {

            $('#show').load('./${sessionScope.path}.jsp');

            $("#saveOut").click(function () {

                $.post(
                    "/admin/safeOut",
                    function (result) {
                        window.location.replace("../login.jsp");
                    },
                    "JSON"
                );

            });

        })
    </script>
</head>
<body>
<nav  class="navbar navbar-inverse  navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand">持明法洲后台管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a>你好:${sessionScope.login.username}</a>
                </li>
                <li><a id="saveOut">退出登录 <span class="glyphicon glyphicon-share"></span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->
</nav>

<div class="container-fluid" style="margin-top: 60px;margin-bottom: 20px;">

    <div class="row">
        <%--左侧工具栏--%>
        <div style="margin-top: 60px;z-index:auto;" class="col-xs-2 navbar-fixed-top">

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                轮播图模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div id="slideshow" class="panel-body">
                           <a href="javascript:$('#show').load('./slideshow.jsp');">轮播图管理</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                专辑模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                             <a href="javascript:$('#show').load('./album.jsp');">专辑管理</a>
                        </div>
                        <div class="panel-body">
                             <a href="javascript:$('#show').load('./chapter.jsp');">章节管理</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                文章模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            文章管理
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="heading4">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse4">
                            用户模块
                        </a>
                    </h4>
                </div>
                <div id="collapse4" class="panel-collapse collapse">
                    <div class="panel-body">
                        用户管理
                    </div>
                </div>
            </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="heading5">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse5">
                                日志模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapse5" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#show').load('./cmfzlogs.jsp');">日志管理</a>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="heading6">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse6">
                                系统模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapse6" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#show').load('./user_inmap.jsp');">用户地理分布信息</a>
                        </div>
                        <div class="panel-body">
                            <a href="javascript:$('#show').load('./user_registnum.jsp');">用户注册量信息</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <%--右侧信息展示--%>
        <div id="show" class="col-xs-10 col-xs-offset-2 ui" style="height: 600px;">


                <div class="ui__game" style="height: 600px;"></div>


                <div class="ui__buttons ">
                    <button class="btn btn--bl btn--stats">
                        <icon trophy></icon>
                    </button>
                    <button class="btn btn--bl btn--prefs">
                        <icon settings></icon>
                    </button>
                    <button class="btn btn--bl btn--back">
                        <icon back></icon>
                    </button>

                </div>



            <script src='https://cdnjs.cloudflare.com/ajax/libs/three.js/95/three.min.js'></script>



            <script  src="../statics/cube/js/index.js"></script>
        </div>
    </div>

</div>
<div class="navbar navbar-default navbar-fixed-bottom">
        <a style="margin-left: 40%;">Copyright&copy; 2019-04-08 dakubo@qq.com 津IPC备130146526号-4</a>
</div>
</body>