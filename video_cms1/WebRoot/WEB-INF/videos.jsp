<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>XX科技</title>
	<link rel="stylesheet" type="text/css" href="./css/videos.css">
</head>
<body>
	<!-- 头部logo	 -->
	<img class="head" src="./images/logo.png"></img>
	<!-- 主体内容 -->
	<div class="main">
		<!-- 左侧导航 -->
		<div class="main_left">
			<div class="li_title">用户管理<span class="down"></span></div>
			<ul>
				<li data-src="user-admin.do">管理员</li>
				<li data-src="role-list.do">角色管理</li>
				<li data-src="user-vip.do">会员</li>
			</ul>
			<div class="li_title">产品管理<span class="down"></span></div>
			<ul>
				<li data-src="videos-list.do">视频查看</li>
				<li data-src="videos-upload.do">视频上传</li>
			</ul>
			
		</div>
		<!-- 右侧内容 -->
		<div class="main_right">
			<iframe frameborder="0" scrolling="yes" style="width:100%;height:100%" src="" id="aa"></iframe>
		</div>
	</div>
	<!-- 尾部签名 -->
	<div class="footer">
		<ul>
			<li>关于我们</li>
			<li>最新动态</li>
			<li>代理合作</li>
			<li>XX限公司</li>
			<li>@2017</li>
			<li>京ICP备</li>
			<li>1234567号</li>
		</ul>
	</div>	
</body>
</html>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/videos.js"></script>

