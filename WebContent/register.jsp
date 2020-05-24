<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstra p/3.0.3/js/bootstrap.min.js"></script>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/login/style.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
		<form action="${pageContext.request.contextPath}/user/register.do">
			<div class="login_acount_text">邮箱</div>
			<div class="loginInput ">
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-envelope"></span>
                </span>
                <input class="email" id="email" name="email" placeholder="邮箱" type="text">
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
            <div style="margin-top:20px">
                <button class="btn btn-block redButton" type="submit" onclick="">注册</button>
            </div>
		</form>
</body>
</html>