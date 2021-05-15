<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="../css/index.css">
	<link rel="stylesheet" type="text/css" href="../css/comm.css">
	<link rel="stylesheet" type="text/css" href="../css/course.css">
	<link rel="stylesheet" type="text/css" href="../css/apply.css">
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/course.js"></script>
	<script type="text/javascript" src="../js/apply.js"></script>
	<script type="text/javascript" src="../js/index.js"></script>

</head>
<body id="body">
	<!-- 引入头部start -->
	
	<!-- 引入头部end -->
	
	<!-- car_box -->
	<div class="order_box pay_box autoH">
		<div class="auto">
			<h1>确认付款</h1>
			<div class="order_pic">	
				<p class="p2 autoH"><span class="left">订单详情</span></p>
				<ul>	
					<li>
						<a class="left" href="#"><img src="../images/pic_1.png" alt=""></a>
						<span class="proName left"><a href="#">微信小程序商城构建全栈应用</a></span>
						<span class="price left">￥399.00</span>
					</li>
					
				</ul>
				<div class="payType_box">
					<p>
						<input type="radio" name="payType" checked>
						<span class="type_logo"><img src="../images/zs.png"></span>
						<span class="type_name">招商银行</span>
						<span class="type_code">**6393</span>
						<span class="type_tit">储蓄卡|快捷 推荐</span>
						<span class="type_money right">支付:￥399.00</span>
					</p>

				</div>
				<div class="pay_2">
					<p class="p_1">
						<a href="#" class="other">其他付款方式</a>
						<a href="#" class="addpay">添加快捷/网银付款</a>
					</p>
					<p class="p_2">支付密码:</p>
					<div class="p_3 autoH">
						<div class="alieditContainer left" id="payPassword_container">
							<input minlength="6" maxlength="6" tabindex="1" id="payPassword_rsainput" name="payPassword_rsainput" class="ui-input i-text" oncontextmenu="return false" onpaste="return false" oncopy="return false" oncut="return false" autocomplete="off" value="" type="password" autofocus>
							<div class="sixDigitPassword" tabindex="0">
						        <i class="active"><b></b></i>
						        <i><b></b></i>
						        <i><b></b></i>
						        <i><b></b></i>
						        <i><b></b></i>
						        <i><b></b></i>
						        <span class="guangbiao" style="left:0px;"></span>
						    </div>
						</div>
						<a href="#">忘记密码?</a>
					</div>
					<p  class="mess">请输入6位数字支付密码</p>
					<p class="p_5"><a href="#" id="confirm">确认付款</a></p>
				</div>

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