<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="./js/jquery-1.6.min.js"></script>
<script type="text/javascript">
var add={};
$(function(){

});
jq=jQuery.noConflict();

function ajs(){
jq.ajax({
type:"post",
url:"/shaml/ajaxtest.do",
dataType:"text",
timeout:10000,
success:function(data){
alert(data);
},
error:function(data){
alert(data);
}
});
}
function test(){
   alert("ooo");
}
function sub(){

  jq("form").submit();
  alert("000");  //先执行
}
var tt;
var time_begin = (new Date()).getTime();
function show_time()
{

	var time_now=(new Date()).getTime();

	var time_distance=time_now-time_begin;
	var int_minute=Math.floor(time_distance/60000)
	time_distance-=int_minute*60000;
	var int_second=Math.floor(time_distance/1000)

	var msgTxt = document.getElementById("msgTxt");
	if(msgTxt){
		msgTxt.innerHTML="<font color='#FF0000'>正在加载数据，已执行"+int_minute+"分"+int_second+"秒，请稍候......</font>";
	}

	try{
		var test_time = window.opener.document.getElementById("test_time");
		if(test_time){
			test_time.value= +int_minute+":"+int_second;
		}
	}catch(err) {
	}

	setTimeout("show_time()",1000);
}
function sub(){
show_time();
  jq("form").submit();
  alert("000");  //先执行
}

function sub3(){
 var wid=window.outerWidth;
 var hid=window.outerHeight;
  setTimeout(function(){jq("#gg").slideUp(3000);},3000);
}


function sub2(){
  ajsd();

}

function ajsd(){
jq.ajax({
type:"post",
url:"/shaml/test.do",
dataType:"text",
timeout:100000,
success:function(data){
alert("ok2");
},
error:function(data){
alert("ok1");
}
});
}

</script>
<style>
#gg{
border:solid red 3px;
width:100;
height:200;
position:absolute;
bottom:0;
right:0;

}
</style>
  </head>
  
  <body onload="sub3()">
  <form action="/UTILS/test.do">
 <input type="button" value="ajax触发" onclick=""/>
 <input type="button" value="ajax触发2" onclick="sub();"/>
 <input type="button" value="ajax触发3" onclick="sub2();"/>
 <input type="button" value="ajax触发3" onclick="sub3();"/>
 <div id="msgTxt"></div>
 <div id="gg" >dfdf</div>
 
 </form>
  </body>
</html>
