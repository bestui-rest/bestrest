<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript">
var t='<%=request.getContextPath()%>';
function t3(){
	$(":header").css("color", "yellow");
	$("p:contains('1111')").css("color", "red");
}



$(document).ready(function(){
	$("[type='button']:first").click(function(){
		var width=parseInt($("#div2").css("width"));
		width=width*2;	
		if(width>1000){
			width=26;
		}
		width=width+"px";
		$("#div2").css("width",width);
	});
	$("[type='button']:eq(2)").click(function(){
	  var obj=document.getElementsByTagName("input")[2];
     console.log(window.getComputedStyle(obj,null)["height"]);
	  console.log(obj.hight);
	  console.log($(obj).attr("height"));
	  console.log($(obj).css("height"));
	});
	
	t3();
});
</script>

<style type="text/css">
#div1{
background:gray;
width:1000px; 
height:100px;
}
#div8{
background:gray;
width:600px; 
height:600px;
border-radius:100%;
}
#div2{
background:red;
width:100px; 
height:100px;
border-radius:100%;
}
.test{
height:35px;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<base href=<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/>
</head>

<body>

<div  id="div1">
<div id="div2"></div>
</div>
<div id="div3" width="120px"></div>
<input type="button" value="创建" />
<input type="button" value="创建" />
<input type="button" value="创建" style='width:80px;height:41px;' class="test"/>
<input type="button" value="创建" />
<input type="button" value="创建" />
<input type="button" value="创建" />
<input type="button" value="创建" />
<input type="button" value="创建" />
<input type="button" value="创建" />
<input type="button" value="创建" />
<input type="text" value=""/>
<h1>2323</h1>
<h2>2323w</h2>
<div>
<h2>1111</h2>
<p>1111</p>
<p>1111s</p>
<p>1711s</p>

<span class="class1 ">1711s</span>
</div>

<canvas id="canvas" width="1000" height="600" style="background-color:gray;">
你的浏览器bai暂不支持HTML5的canvas元素du
</canvas>


<script type="text/javascript">
var canvas=document.getElementById('canvas');//定义变量获制取画布daoDOM
var c=canvas.getContext('2d');//设置绘图环境为2D;
c.lineWidth=2;
c.strokeStyle="red";
c.fillStyle="yellow";
c.arc(400,500,300,Math.PI*7/6,Math.PI*11/6,false);
c.arc(400,500,150,Math.PI*11/6,Math.PI*7/6,true);
c.closePath() ;
c.fill();




</script>

</body>
</html>