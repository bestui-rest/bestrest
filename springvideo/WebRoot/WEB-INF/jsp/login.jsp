<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">

</head>
<body>
<form action="/springvideo/login/login.do">
	<div class="login_box">
		<div class="left col_50">
			<!-- <img src="images/login/logo_2.png">
			<h3>视频点播系统</h3> -->
		</div>
		<div class="right col_50 login_form">
			<div class="row_1 row_350 margin_40" >
				<input type="text" data-name="uname" name="email" placeholder="请输入用户名" autocomplete="off">
				
			</div>
			<div class="row_1 row_350">
				<input type="password" data-name="upass" name="password" placeholder="请输入密码">
				<label>${mess }</label>
			</div>
			
			<div class="row_1">
				<input type="checkbox" name=""> 记住密码
				<a href="#" class="color_999 a_right">忘记密码</a>
			</div>
			<div class="row_1">
				<input type="submit" value="登录" />
			</div>
			<div class="row_1">
				<a href="register.html" class="color_blue a_right">立即注册</a>
			</div>

		</div>
	</div>
</form>
</body>
</html>