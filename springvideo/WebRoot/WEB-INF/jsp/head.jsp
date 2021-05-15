<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.zyd.bean.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/springvideo/css/comm.css">
	<link rel="stylesheet" type="text/css" href="/springvideo/css/index.css">
	<script type="text/javascript" src="/springvideo/js/jquery.min.js"></script>
	<script type="text/javascript" src="/springvideo/js/banner.js"></script>
	<script type="text/javascript" src="../js/index.js"></script>
	<%User user=(User)session.getAttribute("user");if(user==null){%>
<div class="header ">
		<div class="auto">
			<a href="index.html"><img class="left logo" src="/springvideo/images/logo.png"></a>
			<div class="right login_area">
				<span class="car" id="end">
				<a href="/springvideo/main/car.do">购物车</a><span class="nums"></span></span>
				<span class="a_btns"><a href="/springvideo/login/tologin.do">登录</a></span>
				<span class="a_btns"><a href="/springvideo/register/register.do">注册</a></span>
			</div>
		</div>
	</div>
	<%}else{ %>
	<div class="header header_blue">
		<div class="auto">
			<a href="index.html"><img class="left logo" src="/springvideo/images/logo_fff.png"></a>
			<div class="right login_area">
				<span class="car" id="end">
				<a href="/springvideo/main/car.do?userid=${user.id}">购物车</a><span class="nums"></span></span>
				<div class="user_head right "><img class="head_pic" src="/springvideo/images/head_pic.jpg">
					<div class="logout">
						<div class="row_1 overflowH"><img src="/springvideo/images/head_pic.jpg" class="left"><p class="left"><span name="uname">${user.nickname }</span><span>经验14,029&nbsp;&nbsp;积分0</span></p></div>
						<ul class="row_2">
							<li><a href="#">我的课程</a><a href="#">订单中心</a></li>
							<li><a href="#">积分商城</a><a href="#">个人设置</a></li>
						</ul>
						<div class="row_3"><a href="/springvideo/login/tonull.do">安全退出</a></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%}%>