﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="../css/index.css">
	<link rel="stylesheet" type="text/css" href="../css/comm.css">
	<link rel="stylesheet" type="text/css" href="../css/course.css">
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/course.js"></script>
	<script type="text/javascript" src="../js/jqPaginator.js"></script>
	

</head>
<body id="body">
	
	<div class="header header_blue">
		<div class="auto">
			<!-- <a href="index.html"><img class="left logo" src="images/logo_fff.png"></a> -->
			<p class="left course_tit"><a onclick="javascript:history.go(-1)"><img src="../images/jiantou_03.png"></a>${video.description}</p>
			<div class="right login_area">
				<span class="car" id="end">
				<a href="car.html">购物车</a><span class="nums"></span></span>
				<div class="user_head right "><span class="head_pic"></span><p class="logout">${user.nickname}&nbsp;&nbsp;&nbsp; 注销登录</p></div>
			</div>
		</div>
	</div>
	
	<!-- car_box -->
	<div class="order_box video_box success autoH">
		
			<video controls="controls" poster="${video.url}">
				<source src="${video.url}" type="">
			</video>
	
	
	</div>
	<!-- foot -->
	<div class="foot foot_blue">
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