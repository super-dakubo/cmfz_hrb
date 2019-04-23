<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>持名法州后台管理中心</title>
			
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="css/common.css" type="text/css">
	<link rel="stylesheet" href="css/login.css" type="text/css">
	<script type="text/javascript" src="script/jquery.js"></script>
	<script type="text/javascript" src="script/common.js"></script>
	<script type="text/javascript" src="script/jquery.validate.js"></script>
	<style>
		label.error{
			padding-left: 16px;
			padding-bottom: 2px;
			font-weight: bold;
			color: #EA5200;
		}
	</style>
	<script type="text/javascript">

		$(function(){
			//点击更换验证码：
			var rom=1201201;
			var path = $("#captchaImage").prop("src");
			$("#captchaImage").click(function(){//点击更换验证码
			    rom++;
                //alert(path+"?asd="+rom);
                $(this).prop("src",path+"?asd="+rom);
                $(this).click();
			});

			// 表单验证
			$().ready(function () {
                $("#loginForm").validate({
					rules:{
					    username:{
					        required:true,
                            minlength: 2
						},
						password:{
					        required:true,
							minlength: 6
						},
                        code:{
					        required:true,
                            remote:{
                                url:"/code/checkCode",
                                    type:"post",
                                    dataType:"json",
                                    data:{
                                    code:function () {
                                        return $('#code').val();
                                    }
                                }
                			}
						}
					},
                    messages:{
					    username:{
                            required:"请输入用户名",
                            minlength: "用户名至少由两个字符组成"
						},
						password:{
					        required:"请输入密码",
                            minlength: "密码必需由两个字母组成"
						},
                        code:{
                            required:"验证码不能为空",
                            remote: "验证码错误"
						}
					},

				})
            });
			//基本验证成功
            $.validator.setDefaults({
                submitHandler: function() {
                    //alert($('#loginForm').valid());
                    var formdata = $("#loginForm").serialize();
                    $.post(
                        "/admin/login",
                        formdata,
                        function(result){
                            if(result.statuscodr==200){
                                window.location.replace("./back/main.jsp");
                            }else {
                                $("#message").html("<span style='color:red;'>" + result.message + "</span>");
                            }
                        },
                        "JSON"
                    );
                }
            });
		});
	</script>
</head>
<body>
		<div class="login">
			<form id="loginForm" action="/admin/login" method="post" >
				<table>
					<tbody>
						<tr>
							<td width="190" rowspan="2" align="center" valign="bottom">
								<img src="img/header_logo.gif" />
							</td>
							<th>
								<label for="username">用户名</label>
							</th>
							<td width="150">
								<input type="text" id="username" name="username"  maxlength="20" data-tip="请输入您的邮箱" />
							</td>
					  </tr>
					  <tr>
							<th>
								<label for="password">密&nbsp;&nbsp;&nbsp;码:</label>
							</th>
							<td>
								<input type="password" id="password" name="password"  maxlength="20" autocomplete="off"/>
							</td>
					  </tr>
					
						<tr>
							<td>&nbsp;</td>
							<th><label for="code">验证码</label></th>
							<td>
								<input type="text" id="code" name="code"  maxlength="4" autocomplete="off"/>
								<img id="captchaImage" style="margin-left: 0px" class="captchaImage" src="code/getImg" title="点击更换验证码"/>
							</td>
						</tr>					
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
						<td id="message"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit" class="loginButton" value="登录">
						</td>
					</tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2008-2017.</div>
				<div class="link">
					<a href="http://www.chimingfowang.com/">持名佛网首页</a> |
					<a href="http://www.chimingbbs.com/">交流论坛</a> |
					<a href="">关于我们</a> |
					<a href="">联系我们</a> |
					<a href="">授权查询</a>
				</div>
			</form>
		</div>
</body>
</html>