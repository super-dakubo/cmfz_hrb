<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
    <script>
        // function show(f) {
        //     var rd = new FileReader();//创建文件读取对象
        //     var files = f.files[0];//获取file组件中的文件
        //     console.log(files);
        //     rd.readAsDataURL(files);
        //     rd.onloadend = function (e) {
        //         var $embed = $("<embed loop=\"-1\" autostart=\"true\"  src="+this.result+">aa</embed>");
        //         $("#audiotest").append($embed);
        //     }
        //
        // }
        $(function(){

            $.post(
                "/chapter/getAllAlbum",
                function (result) {
                    $.each(result,function (i,album) {
                        // console.log(album);
                        // console.log(i);
                        var option = $("<option value='"+album.id+"'>"+album.name+"</option>");
                        $("#inputselect").append(option);
                    })
                },
                "JSON"
            );

            // 表单验证
            $().ready(function () {
                $("#ff").validate({
                    rules:{
                        name:{
                            required:true,
                        },
                        fff:{
                            required:true,
                        },
                        orders:{
                            required:true,
                            digits:true
                        }
                    },
                    messages:{
                        name:{
                            required:"请输入用户名",
                        },
                        fff:{
                            required:"请输入用户名",
                        },
                        orders:{
                            required:"请输入用户名",
                            digits:"请输入数字"
                        }
                    }

                })
            });

            pageInit();
            //模态框监听开启事件
            $('#myModal').on('show.bs.modal', function (e) {


            });

            //文件上传 与表单的异步提交
            $("#edit").click(function ()   {

                if($("#ff").data("mark")==null){
                    var formData = new FormData($("#ff")[0]);
                    alert(1212);
                    $.ajax({
                        url: "/chapter/edit",
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
                                $("#as").html("<span style='color: red;'>"+data.message+"</span>");
                            }
                        }
                    });
                }else {
                    $(".imgshow").addClass("hide");
                }

            });
        });
        function pageInit(){
            $("#usertable").jqGrid({
                url:"/chapter/show",
                datatype:"json",
                styleUI : 'Bootstrap',//使用bootstrap风格样式
                height: 'auto',
                autowidth:true,
                rowNum: 10,
                rowList: [10,20,30],
                colNames:['章节顺序','章节名称',"专辑名称",'章节路径', '章节大小', '章节时长','章节播放数','上传时间','操作'],
                colModel:[
                    {name:'orders'},
                    {name:'name'},
                    {name:'album.name'},
                    {name:'audioPath'},
                    {name:'audioSize'},
                    {name:'audioDuration',formatter:function(cellvalue, options, row){
                            return getMyDate(cellvalue*1000);
                        }},
                    {name:'viewNum'},
                    {name:'uploadDate'},
                    {name:"ope",formatter:function(cellvalue, options, row){
                            // console.log(cellvalue);
                            // console.log(options);
                            // console.log(row.id+row.name);
                            var play= "<a style='margin-left: 10px;' data-op='play' id='a"+row.id+"'  onclick=\"javascript:pla('"+row.id+"');\" data-th='"+row.audioPath+"'>" +
                                "<span class=\"glyphicon glyphicon-play\"></span>" +
                                "<embed  width=\"0\" height=\"0\" loop=\"false\" autostart=\"false\"/>" +
                                "</a>";
                            var save = "<a style='margin-left: 20px;' href='/chapter/getAudio?audioPath="+row.audioPath+"&type=attachment'><span class=\"glyphicon glyphicon-save\"></span> </a>";

                            var del = "<a style='margin-left: 20px;' onclick=\"javascript:del('"+row.audioPath+"');\" ><span class=\"glyphicon glyphicon-remove\"></span></a>";
                            return play+save+del;
                        }}
                ],
                pager: "#toolbar",
                viewrecords: true,
                grouping:true,
                groupingView : {
                    groupField : ['album.name'],
                    groupColumnShow : [true],
                    groupText : ['<b>{0} - {1} Item(s)</b>'],
                    groupCollapse : true,
                    groupOrder: ['asc']
                }
            });



        }
        function pla(ss) {
            var path = ($("#a"+ss).data("th"));

            $("#a"+ss+" embed").prop("src","/chapter/getAudio?audioPath="+path+"&type=inline");
        }
        function del(data) {
            alert(data);
            $.post(
                "/chapter/delete",
                "fileName="+data,
                function (result) {
                    alert(result.status);
                    if(!result.status){
                        alert(result.message())
                    }
                    $("#usertable").jqGrid().trigger("reloadGrid");
                },
                "JSON"
            )
        }
        function getMyDate(num){
            var oDate = new Date(num);
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = getzf(oMin) +':'+getzf(oSen);//最后拼接时间
            return oTime;
        };
        //补0操作
        function getzf(num){
            if(parseInt(num) < 10){
                num = '0'+num;
            }
            return num;
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
            <a href="#profile"  data-toggle="modal" data-target="#myModal" >专辑添加</a>
        </li>
    </ul>
    <table id="usertable" ></table>
    <div id="toolbar"></div>

</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">章节上传 </h4>
                    <div id="message"></div>
            </div>

            <div class="modal-body ">

                <form id="ff" class="form-horizontal " method="post" enctype="multipart/form-data">
                    <div class="form-group hide">
                        <label for="inputid" class="col-sm-3 control-label">ID：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="inputid" name="id" />
                        </div>
                    </div>
                    <div class="form-group" >
                        <label for="inputselect" class="col-sm-3 control-label">选择专辑：</label>
                        <div class="col-sm-9" id=>
                            <select name="album.id" class="form-control" id="inputselect">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputorder" class="col-sm-3 control-label">集数：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="inputorder" name="orders" />
                        </div>
                    </div>
                    <div class="form-group" id="imgedit">
                        <label for="inputfile" class="col-sm-3 control-label">音频添加：</label>
                        <div class="col-sm-9" id="audiotest">
                            <input id="inputfile"  type="file" name="file" multiple accept="audio/*">
                        </div>
                    </div>

                    <div class="form-group" id="albumss">
                        <label for="inputname" class="col-sm-3 control-label">音频名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="inputname" name="name" />
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