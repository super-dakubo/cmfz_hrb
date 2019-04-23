<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    function show1(f){
        var rd = new FileReader();//创建文件读取对象
        var files = f.files[0];//获取file组件中的文件
        console.log(files);
        rd.readAsDataURL(files);
        rd.onloadend = function (e) {
            //加载完毕之后获取结果赋值给img
            $("#showFname").text(files.name).css("color","green");
        }
    }


    $(function () {
        $("#usertable").jqGrid({
            url:"/slideshow/showAll",
            datatype:"json",
            styleUI : 'Bootstrap',//使用bootstrap风格样式
            pager:"#toolbar",
            autowidth:true,
            cellEdit:false,
            viewrecords:true,
            rowNum:5,
            rowList:[5,10,15,50,100],
            viewrecords:true,
            colNames:["图片名称","图片路径","图片描述","图片状态","上传时间","操作"],
            colModel:[
                {name:"title",editable:true},
                {name:"imgPath"},
                {name:"describes",editable:true},
                {name:"status",editable:true},
                {name:"uploadDate",formatter:function (cellvalue, options, row) {
                    //alert(row.uploadDate);
                        return row.uploadDate;
                    }},
                {name:"ope",formatter:function(cellvalue, options, row){
                    // console.log(cellvalue);
                    // console.log(options);
                    // console.log(row);//这个对象
                    //     var data=JSON.stringify(row);
                        //var data = {id:rowobject.id,title:rowobject.title};
                        // console.log("----------");
                        return "<a data-pa='kt' data-ath='"+row.imgPath+"'  data-id='"+row.id+"' data-title='"+row.title+"'   href=\"#profile\" data-toggle=\"modal\" data-target=\"#myModal\"  style=\"margin-left: 30px;\"><span class=\"glyphicon glyphicon-search\"></span></a>&nbsp;&nbsp;" +
                            "<a data-pa='gx' style=\"margin-left: 30px;\" href=\"#profile\" data-toggle=\"modal\" data-target=\"#myModal\" data-status='"+row.status+"'  data-ath='"+row.imgPath+"' data-describes='"+row.describes+"' data-id='"+row.id+"' data-title='"+row.title+"' ><span class=\"glyphicon glyphicon-edit\"></span></a>&nbsp;";
                    }}
            ]
        });
        //模态框消失，数据清除
        $('#myModal').on('hidden.bs.modal', function (e) {
            $("#ff>div").removeClass("hide");
            $("#ff")[0].reset();
            $("#imgs").empty();
        });

        //监听模态框展示
        $('#myModal').on('show.bs.modal', function (e) {

            var  id = $(e.relatedTarget).data("id");
            var title = $(e.relatedTarget).data("title");
            var imgPath = $(e.relatedTarget).data("ath");
            var describes = $(e.relatedTarget).data("describes");
            var status = $(e.relatedTarget).data("status");

            if($(e.relatedTarget).data("pa")=="gx"){
               // alert("11");
                //修改操作
                //禁止文件上传
                $("#imgedit").addClass("hide");

                $("#inputid").val(id);
                $("#inputtitle").val(title);
                $("#inputmiaoshu").val(describes);
                $("#inputstatus [value="+status+"]").prop("checked",true);

                $("#imgs").empty();

                var $img = $("<img  class=\"img-responsive\"  src='/slideshow/download?imgPath="+imgPath+"'/>");

                $("#imgs").append($img);

            }else if($(e.relatedTarget).data("pa")=="kt"){
                //看图
                $("#ff>div").addClass("hide");
                $("#ff>div:eq(1)").removeClass("hide");


                $("#imgs").empty();
                var $a = $("<a  target='_blank' href='/slideshow/download?imgPath="+imgPath+"'/>");
                var $img = $("<img  class=\"img-responsive\"  src='/slideshow/download?imgPath="+imgPath+"'>");
                $a.append($img);
                $("#imgs").append($a);
            }else {
                //添加
                 $("#imgedit").removeClass("hide");
            }
        });
        //图片上传 与表单的异步提交
        $("#edit").click(function () {
            var formData = new FormData($("#ff")[0]);
            $.ajax({
                url: "/slideshow/edit",
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
                        $("#myModal").modal('hide');
                    }else{
                        $("#imgs").html("<span style='color: red;'>"+data.message+"</span>");
                    }
                }
            });
        });
        //xls文件上传
        $("#upxls").click(function () {
            var formData = new FormData($("#xlsff")[0]);
            $.ajax({
                url: "/slideshow/xls2sql",
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
            });
        });
        $("#piliang").click(function () {
            $("#inputxls").click();
            $("#inputxls").clear();
        });

    });
</script>
<div class="row">
    <div class="page-header" style="margin-top: 0px">
        <h3 style="margin-top: 0px">轮播图管理</h3>
    </div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">轮播图列表</a></li>
        <li role="presentation"><a href="#profile" data-toggle="modal" data-target="#myModal">轮播图添加</a>
        </li>
        <li role="presentation"><a href="/slideshow/downloadxls" >下载数据到.xls</a>
        </li>
        <li role="presentation"><a id="piliang" class="btn btn-default">点击选择文件：<span id="showFname"></span></a>
        </li>
        <li role="presentation">
            <form class="form-inline" id="xlsff">
                <div class="form-group hide">
                    <input onchange="show1(this);" type="file" name="file" id="inputxls" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                </div>
                <a id="upxls"  class="btn btn-default">上传文件</a>
            </form>
        </li>
    </ul>
    <table id="usertable"></table>
    <div id="toolbar"></div>


</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">轮播图 </h4>

            </div>
            <div class="modal-body">

                <form id="ff" class="form-horizontal" method="post" enctype="multipart/form-data">
                    <div class="form-group hide" id="idss">
                        <div class="col-sm-9 col-sm-offset-3" id="ids">
                            <input type="hidden" id="inputid" name="id" />
                        </div>
                    </div>

                    <div class="form-group" id="as">
                        <label for="imgs" class="col-sm-3 control-label">图片展示：</label>
                        <div class="col-sm-9" id="imgs">

                        </div>
                    </div>
                    <div class="form-group" id="imgedit">
                        <label for="inputfile" class="col-sm-3 control-label">图片添加：</label>
                        <div class="col-sm-9">
                            <input id="inputfile" onchange="show(this)" type="file" name="fff" multiple accept="image/gif, image/png, image/jpg, image/jpeg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputtitle" class="col-sm-3 control-label">图片名称：</label>
                        <div class="col-sm-9">
                            <input type="text" name="title" class="form-control" id="inputtitle" placeholder="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputmiaoshu" class="col-sm-3 control-label">图片描述：</label>
                        <div class="col-sm-9">
                            <textarea name="describes" class="form-control" id="inputmiaoshu" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputstatus" class="col-sm-3 control-label">图片状态：</label>
                        <div class="col-sm-9" id="inputstatus">
                            <div class="checkbox">
                                <label>
                                    <input name="status" value="激活" type="radio"> 激活
                                </label>
                                <label>
                                    <input name="status" value="冻结" type="radio"> 冻结
                                </label>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="edit" class="btn btn-primary">继续操作</button>
            </div>
        </div>
    </div>
</div>