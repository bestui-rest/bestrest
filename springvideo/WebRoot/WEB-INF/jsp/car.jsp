<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<script type="text/javascript" src="../js/index.js"></script>
<script>


</script>
</head>
<body id="body">
	<!-- 引入头部start -->
		<%@include file="head.jsp" %>
	<!-- 引入头部end -->
	
	<!-- car_box -->
	<div class="car_box autoH">
		<div class="auto">
			<h1>购物车</h1>
			<div class="tit2">
				<span class="s1">商品信息</span><span class="s2">金额</span><span>操作</span>
			</div>
			<ul>
			<c:forEach items="${listitem}" var="u">
				<li>
					<input type="checkbox" name="pro" >
					<a href="#"><img src="${u.c_picture}" alt=""></a>
					<span class="proName"><a href="#">${u.c_name }</a></span>
					<span class="price">&yen;${u.c_price }</span>
					<span class="del" onclick="window.location.href='/springvideo/main/deletecaritem.do?itemid=${u.id}'">删除</span>
				</li>
				</c:forEach>
			</ul>
			<div class="tit2 autoH tit_bottom">
				<p class="left">
					<input type="checkbox" name="all"> 全选
					<a href="" class="delAll">删除</a>
				</p>
				<p class="right">
					<span>已选商品<span name="count">0</span>件</span>
					<span>合计:<span name="prices">0.00</span></span>
					<a href="order.html"><span name="pay">结算</span></a>
				</p>
			</div>


		</div>
		
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