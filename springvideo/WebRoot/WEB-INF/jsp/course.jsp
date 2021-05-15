<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<script type="text/javascript" src="../js/index.js"></script>

</head>
<body id="body">
	<!-- 引入头部start -->
	<%@include file="head.jsp" %>
	<!-- 引入头部end -->

	<div class="category">
		<div class="auto">
			<ul>
				<li class="s001">
					<label class="direction">方向:</label>
						<c:choose>
					<c:when test="${curcoursedir==0}">
					   <span class="current" data-id="0" name=""aff>全部</span>
					   </c:when>
					   <c:otherwise><span data-id="0" name="aff">全部</span></c:otherwise>
					   </c:choose>
					<c:forEach items="${AllCourseDir}" var="u">
					<c:choose>
					<c:when test="${(curcoursedir!=0)&&(u.id==curcoursedir)}">
					            <span class="current" data-id="${u.id}">${u.name}</span>
					            </c:when>
					            <c:otherwise>
					            <span data-id="${u.id}">${u.name}</span>
					            </c:otherwise>
					            </c:choose>
					</c:forEach>
					<input type="hidden" value="${curcoursedir}" id="curcoursedir"/>
					<input type="hidden" value="${contentid}" id="contentid"/>
					<input type="hidden" value="${page}" id="pagecur"/>
				</li>
				<li class="s002">
					<label class="content">内容:</label>
					<c:choose>
					<c:when test="${contentid==0}">
					   <span class="current" data-id="0">全部</span>
					   </c:when>
					   <c:otherwise><span data-id="0">全部</span></c:otherwise>
					   </c:choose>
					
				<c:forEach items="${AllCourseCon}" var="u">
				<c:choose>
						<c:when test="${(contentid!=0)&&(u.id==contentid)}">
					            <span class="current" data-id="${u.id}">${u.name}</span>
					            </c:when>
					            <c:otherwise>
					            <span data-id="${u.id}">${u.name}</span>
					            </c:otherwise>
					            </c:choose>
					</c:forEach>
				</li>
			</ul>
		</div>
	</div>
	<!-- course_box -->
	<div class="course_box autoH">
		<div class="auto courses">
		
		<c:forEach items="${AllCourse}" var="u">
			<a href="/springvideo/main/tovideo.do?id=${u.id}">
			<div class="course c_5">
				<img src="${u.picture}">
				<h3>${u.name }</h3>
				<div>
					<p class="p1">${u.name }</p>
					<p class="p2">${u.description}</p>
					<p class="p2 p3">初级.4人在学</p>
				</div>
			</div>
			</a>
</c:forEach>
		</div>
		<!--分页-->
		<div id="pages" class="pages">
			<a href="#">上一页</a>
			<c:forEach begin="1" end="${totalPage}" var="g">
			<a href="" class="current_page">${g}</a>
		</c:forEach>
			<a href="#">下一页</a>
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