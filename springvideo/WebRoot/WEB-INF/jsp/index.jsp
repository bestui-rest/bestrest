<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>视频网</title>
	<link rel="stylesheet" type="text/css" href="/springvideo/css/comm.css">
	<link rel="stylesheet" type="text/css" href="/springvideo/css/index.css">
	<script type="text/javascript" src="/springvideo/js/jquery.min.js"></script>
	<script type="text/javascript" src="/springvideo/js/banner.js"></script>
	<script type="text/javascript" src="../js/index.js"></script>
	<script>
function buy(id){
	$.post("/springvideo/main/buy.do",{"id":id},function(data){
	if(data=="ajax")
	{
	console.log("this is a ajax");
	window.location.href="/springvideo/login/tologin.do";
	}else{
	switch(data){
	case 1:$("#course_"+id).html("购买成功");break;
	case 2:$("#course_"+id).html("已购买");break;
	default:$("#course_"+id).html("购买失败");break;
	}
	}
	});
}
</script>
</head>
<body id="body">&nbsp; 

	
	<!-- 引入头部start -->
<%@include file="head.jsp" %>
	<!-- main -->
	<div class="main">
		<div class="bgfff">
			<!-- banner -->
			<div class="banner_box auto">
				<div class="banner ">
					<c:forEach items="${list}" var="u">
						<img src="${u.picture_url}">
						</c:forEach>
				</div>
				<div class="side_bar">
					<ul>
						<c:forEach items="${list}" var="u">
						<a href="/springvideo/main/tocourse.do?coursedir=${u.id}&page=1&contentid=0"><li>${u.name }</li></a>
						</c:forEach>
					</ul>
				</div>
				<span class="prev btns"><img src="/springvideo/images/prev.png"></span>
				<span class="next btns"><img src="/springvideo/images/next.png"></span>
				<div class="points">
				<c:forEach begin="1" end="${fn:length(list)}">
                <span></span>
               </c:forEach>
				
				</div>
			</div>

			<!-- column -->
			
			<div class="column auto autoH">
				<h3>实/战/推/荐</h3>
				<c:forEach items="${list1}" var="u">
				<div class="course_box">
					<a href="/springvideo/main/tovideo.do?id=${u.id}"  >
					<img src="${u.picture}"></a>
					<div>
						<p class="title"><a href="video.html" >${u.description}</a></p>
						<p class="level">${u.name}</p>
						<p class="price"><span>${price}</span>
						<span class="right buy">点击购买</span></p>
						<p><span></span></p>
					</div>
					 
				</div>
				</c:forEach>
                
			</div>
			<!-- column end -->

		</div>
		<!-- column -->
			<div class="column auto autoH">
				<h3>新/手/入/门</h3>
				<c:forEach items="${list2}" var="s">
				<div class="course_box">
					<a href="/springvideo/main/tovideo.do?id=${s.id}" >
					<img src="${s.picture}"></a>
					<div>
						<p class="title"><a href="video.html" >${s.description}</a></p>
						<p class="level">实战 初级</p>
						<p class="price"><span>${s.price}</span>
						<span class="right buy" onclick="buy(${s.id})">点击购买</span></p>
						<p><span id="course_${s.id}"></span></p>
					</div>
					
				</div>
				</c:forEach>

			</div>
			<!-- column end -->


		<!--  -->

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