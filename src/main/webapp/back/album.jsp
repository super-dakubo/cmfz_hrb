<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    function show(f) {
        var rd = new FileReader();//创建文件读取对象
        var files = f.files[0];//获取file组件中的文件
        console.log(files);
        rd.readAsDataURL(files);
        rd.onloadend = function (e) {
            //加载完毕之后获取结果赋值给img
            $("#imgs").empty();
            var $img = $("<img  class=\"img-responsive\"  src="+this.result+">");
            $("#imgs").append($img);
        }

    }
    $(function () {
        $("#usertable").jqGrid({
            url:"/album/show",
            datatype:"json",
            styleUI : 'Bootstrap',//使用bootstrap风格样式
            pager:"#toolbar",
            height:300,
            autowidth:true,
            cellEdit:false,
            viewrecords:true,
            rowNum:5,
            rowList:[5,10,15,50,100],
            colNames:["专辑名称","专辑分数","专辑播音","专辑作者","上传时间","章节数量","专辑封面","专辑状态","操作"],
            colModel:[
                {name:"name"},
                {name:"mark"},
                {name:"announcer"},
                {name:"author"},
                {name:"uploadDate"},
                {name:"count",formatter:function(cellvalue, options, row){
                    if(row.count==null){
                        return 0;
                    }
                    return row.count;
                    }},
                {name:"imgPath"},
                {name:"status"},
                {name:"ope",formatter:function(cellvalue, options, row){
                        // console.log(cellvalue);
                        // console.log(options);
                        // console.log(row);
                        // console.log("77777777777777");
                        return "<a data-op='kt'  data-imgpath='"+row.imgPath+"'    href=\"#profile\" data-toggle=\"modal\" data-target=\"#myModal1\"  style=\"margin-left: 30px;\"><span class=\"glyphicon glyphicon-search\"></span></a>&nbsp;&nbsp;" +
                           "<a data-op='gx'  href=\"#profile\" data-toggle=\"modal\" data-target=\"#myModal1\"   style=\"margin-left: 30px;\"" +
                            " data-id='"+row.id+"' data-name='"+row.name+"' data-mark='"+row.mark+"' data-announcer='"+row.announcer+"' data-author='"+row.author+"' " +
                            " data-imgpath='"+row.imgPath+"' data-abstracts='"+row.abstracts+"' data-status='"+row.status+"' >" +
                            "<span class=\"glyphicon glyphicon-edit\"></span></a>&nbsp;";

                    }}
            ]
        });//增加增删改工具栏 参数1: 固定写死navGrid  参数2:分页工具栏的选择器 参数3:增删改配置对象;

        //模态框消失，数据清除
        $('#myModal1').on('hidden.bs.modal', function (e) {
            $("#ff").removeClass("hide");
            $("#inputid").val("");
            $("#ff")[0].reset();
            $("#ff").removeData("mark");
            $("#imgs").empty();
            $(".imgshow").removeClass("hide");



        });

        //监听模态框展示
        $('#myModal1').on('show.bs.modal', function (e) {
            var editor = UE.getEditor('inputmiaoshu');
            if($(e.relatedTarget).data("op")=="gx"){
                $("#imgedit").addClass("hide");
                var id = $(e.relatedTarget).data("id");
                var name = $(e.relatedTarget).data("name");
                var mark = $(e.relatedTarget).data("mark");
                var announcer = $(e.relatedTarget).data("announcer");
                var author = $(e.relatedTarget).data("author");
                var abstracts = $(e.relatedTarget).data("abstracts");
                var status = $(e.relatedTarget).data("status");
                var imgpath = $(e.relatedTarget).data("imgpath");



                //console.log(imgpath);
                $("#imgs").empty();
                var $img = $("<img  class=\"img-responsive\"  src='/album/download/?imgPath="+imgpath+"'>");
                $("#imgs").append($img);
                $("#inputid").val(id);
                $("#inputname").val(name);
                $("#inputmark").val(mark);
                $("#inputannoucer").val(announcer);
                $("#inputauthor").val(author);


                editor.ready(function() {
                    editor.setContent(abstracts);
                });


                $("#inputstatus [value="+status+"]").prop("checked",true);
            }else if($(e.relatedTarget).data("op")=="kt"){
                $("#ff").addClass("hide");
                $("#ff").data("mark","cc");
                var imgpath = $(e.relatedTarget).data("imgpath");
                //console.log(imgpath);
                $("#imgs").empty();
                var $img = $("<img  class=\"img-responsive\"  src='/album/download/?imgPath="+imgpath+"'>");
                $("#imgs").append($img);
            }
        });

        //文件上传 与表单的异步提交
        $("#edit").click(function ()   {
            alert($("#ff").data("mark"));
            if($("#ff").data("mark")==null){
                var formData = new FormData($("#ff")[0]);
                $.ajax({
                    url: "/album/edit",
                    type: "POST",
                    data: formData,
                    /**
                     *必须false才会自动加上正确的Content-Type
                     */
                    contentType: false,
                    /**
                     * 必须false才会避开jQuery对 formdata 的默认处理
                     * XMLHttpRequest会对 formdata 进行正确的处理
                     */
                    processData: false,
                    success: function (data) {
                        if(data.status){
                            $("#usertable").jqGrid('clearGridData');//清空表格
                            $("#usertable").jqGrid().trigger("reloadGrid");
                            $("#myModal1").modal('hide');
                        }else{
                            $("#as").html("<span style='color: red;'>"+data.message+"</span>");
                        }
                    }
                });
            }else {
                $(".imgshow").addClass("hide");
            }

        });

        });
    function showOne(obj) {
        console.log(obj);
        console.log(this);
    }


</script>
<div class="row">
    <div class="page-header" style="margin-top: 0px">
        <h3 style="margin-top: 0px">专辑管理</h3>
    </div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation ">
            <a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑列表</a>
        </li>
        <li role="presentation">
            <a href="#profile" data-toggle="modal" data-target="#myModal1" >专辑添加</a>
        </li>
    </ul>
    <table id="usertable"></table>
    <div id="toolbar"></div>

</div>









<!-- Modal -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header imgshow">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">轮播图 </h4>

            </div>
            <div class="form-group" id="as">
                <%--<label for="imgs" class="col-sm-3 control-label">封面展示：</label>--%>
                <div class="col-sm-12" id="imgs">

                </div>
            </div>
            <div class="modal-body imgshow">


                <form id="ff" class="form-horizontal " method="post" enctype="multipart/form-data">
                    <div class="form-group hide" id="idss">
                        <div class="col-sm-9 col-sm-offset-3" id="ids">
                            <input type="hidden" id="inputid" name="id" />
                        </div>
                    </div>


                    <div class="form-group" id="imgedit">
                        <label for="inputfile" class="col-sm-3 control-label">封面添加：</label>
                        <div class="col-sm-9">
                            <input id="inputfile" onchange="show(this)" type="file" name="fff" multiple accept="image/gif, image/png, image/jpg, image/jpeg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputname" class="col-sm-3 control-label">专辑名称：</label>
                        <div class="col-sm-9">
                            <input type="text" name="name" class="form-control" id="inputname" placeholder="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputmark" class="col-sm-3 control-label">专辑分数：</label>
                        <div class="col-sm-9">
                            <input type="text" name="mark" class="form-control" id="inputmark" placeholder="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputauthor" class="col-sm-3 control-label">专辑作者：</label>
                        <div class="col-sm-9">
                            <input type="text" name="author" class="form-control" id="inputauthor" placeholder="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputannoucer" class="col-sm-3 control-label">专辑播音：</label>
                        <div class="col-sm-9">
                            <input type="text" name="announcer" class="form-control" id="inputannoucer" placeholder="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputmiaoshu" class="col-sm-3 control-label">专辑描述：</label>
                        <div class="col-sm-9">
                            <script id="inputmiaoshu" name="abstracts" type="text/plain">
                                这里写你的初始化内容
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputstatus" class="col-sm-3 control-label">专辑状态：</label>
                        <div class="col-sm-9" id="inputstatus">
                            <div class="checkbox">
                                <label>
                                    <input name="status" checked value="激活" type="radio"> 激活
                                </label>
                                <label>
                                    <input name="status" value="冻结" type="radio"> 冻结
                                </label>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer imgshow">
                <button type="button" id="quxiao" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="edit" class="btn btn-primary">继续操作</button>
            </div>
        </div>
    </div>
</div>