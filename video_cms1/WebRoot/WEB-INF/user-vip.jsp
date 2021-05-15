<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="./css/userVip.css">
	<link rel="stylesheet" type="text/css" 
	href="/video_cms/css/jquery.page.css">
</head>
<body>
	<div class="normal">
		<div class="main_title">用户管理</div>
		<div  class="main_body">
			<div class="nav_title autoH color_909090">
				<label>用户管理</label><span class="jiantou"></span><label class="color_0e6fb6">会员</label>
			</div>
			<!-- 右侧内容 -->
			<div class="content">
				<div class="c_main">
					<table id="userData" cellspacing="0" cellpadding="0">
						<tr class="thead">
							<td class="col_15">序号</td>
							<td class="col_25">昵称</td>
							<td class="col_25">邮箱账号</td>
							<td class="col_20">注册时间</td>		
						</tr>
						<!-- <tr>
							<td class="col_15">001</td>
							<td class="col_25">会飞的猪</td>
							<td class="col_25">44445567@qq.com</td>
							<td class="col_20">2018.8.21</td>						
						</tr> -->
					</table>
					<!-- 分页 -->
				
					<div class="page_box">
					  <div class="tcdPageCode page_1"></div>
					  <!--  <ul class="pagination">
					    <li><span class="prev">上一页</span></li>
					    <li><span class="curr">1</span></li>
					    <li><span>2</span></li>
					    <li><span>3</span></li>
					    <li><span>4</span></li>
					    <li><span>5</span></li>
					    <li><span class="next">下一页</span></li>
					  </ul>-->
					<input id="page" type="text" style="width:60px;height:25px;border: 1px solid #ddd;border-radius:5px;">
					<input id="selPage" type="button" value="确定" class="ok">
					</div> 
				</div>
			</div>
		</div>	
	</div>
</body>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="/video_cms/js/jquery.page.js"></script>
<script type="text/javascript" src="/video_cms/js/userVip.js"></script>
 <script type="text/javascript">
 	//关闭右侧	
	$(".main_title").on("click",function(){
		$('#aa', window.parent.document).attr("src","");
	})
 </script>
</html>










