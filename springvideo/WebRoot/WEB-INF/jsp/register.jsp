<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="../css/comm.css">
	<link rel="stylesheet" type="text/css" href="../css/register.css">
	<script src="../js/jquery.min.js"></script>
	<script src="../js/register.js"></script>
</head>
<body>
	<div class="head">
		<div class="auto">
			l<img src="../images/logo.png">
		</div>
	</div>
	<div class="auto register_box">
		<form action="/springvideo/register/submit.do" id="ghjsd">
			<h3>新用户注册</h3>
			<div class="rows">
				<label>邮箱账号</label>
				<input type="text" name="email" placeholder="请输入邮箱账号" id="email">
				<p id="p_email"></p>
			</div>
			<div class="rows">
				<label>登录密码</label>
				<input type="password" name="password" placeholder="请输入密码" id="password">
				<p id="p_pwd"></p>
			</div>
			<div class="rows">
				<label>昵称</label>
				<input type="nickname" name="nickname" placeholder="请输入昵称" id="nickname">
				<p id="p_nickname"></p>
			</div>
			<div class="rows">
				<label>验证码</label>
				<input type="text" name="sendvv" data-title="ucode" placeholder="请输入验证码" id="sendvv">
				<input type="button" data-title="sendmess" value="发送验证码" id="sendv"/>
				<p id="p_yanzheng"></p>
			</div>
			
			<div class="rows">
				<input type="button" data-title="submit" value="立即注册"  id="121212"/>
				
			</div>

		</form>
	</div>
	<!-- foot -->
	<div class="foot">
		<a href="#">关于我们</a>
		<a href="#">最新动态</a>
		<a href="#">代理合作</a>
		<span>南京学码思科技教育有限公司</span>       
		<span>@2017</span> 
		<span>京ICP备</span>
		<span>1234567号</span>      
	</div>
</body>
</html>