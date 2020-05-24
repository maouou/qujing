<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstra p/3.0.3/js/bootstrap.min.js"></script>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path %>/css/login/style.css" rel="stylesheet">
<title>登入</title>
</head>
<body>
<div class="loginSize">
	<div class="logo">
		<img alt="" src="<%=path %>/img/logo.jpg">
	</div>
	<div>
		<h3 class="title">欢迎使用 取经</h3>
	</div>
	<form class="loginForm"  method="post">
		<input type="hidden" name="type" value="login"/>
        <div id="loginSmallDiv" class="loginSmallDiv">
            <div class="loginErrorMessageDiv">
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage">${msg}</span>
                </div>
            </div>

            <div class="login_acount_text">用户名</div>
            <div class="loginInput ">
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
                <input class="text" id="name" name="name" placeholder="用户名" type="text">
            </div>
            <div class="login_acount_text">密码</div>
            <div class="loginInput ">
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-lock"></span>
                </span>
                <input class="text" id="password" name="password" type="password" placeholder="密码" type="text">
            </div>
            <span class="text-danger"></span><br><br>
            <input type="checkbox" name="remenberpwd">记住密码
            <div>
                <a class="notImplementLink" href="#nowhere">忘记登录密码</a>
                <a href="register.jsp" class="pull-right">免费注册</a>
            </div>
            <div style="margin-top:20px">
                <button class="btn btn-block redButton" type="submit" onclick="loginSubmit()">登录</button>
            </div>
        </div>
    </form>
</div>
<div class="photoSize">
	<div class="bg">
		<img alt="" src="<%=path %>/img/loginbg.jpg" class="loginbg">
	</div>
</div>
<script type="text/javascript">
	function loginSubmit() {
		var username = $("#name").val();    // 获取id为username的<input>框数据
        var password = $("#password").val();    // 获取id为password的<input>框数据
        if (username.length == 0) {
            $("#name").tips({msg: "请输入用户名。"});
            return;
        }
        if (password.length == 0) {
            $("#password").tips({msg: "请输入密码。"});
            return;
        }
        $.ajax({
            url: "user/login.do",    // 提交到controller的url路径
            type: "post",    // 提交方式
            data: {"username": username, "password": password},  // data为String类型，必须为 Key/Value 格式。
            dataType: "json",    // 服务器端返回的数据类型
            success: function (data) {    // 请求成功后的回调函数，其中的参数data为controller返回的map,也就是说,@ResponseBody将返回的map转化为JSON格式的数据，然后通过data这个参数取JSON数据中的值
                if (data.message == "success") {    
                	window.location.href="success.jsp";
                } else {
                	window.location.href="false.jsp";
                }
            },
        });
	}
</script>
</body>
</html>