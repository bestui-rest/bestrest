<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FileJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function ge(){
console.log(document.getElementsByName("filename")[0].value);
console.log(document.getElementsByName("filename")[0].files[0]);
}
</script>
  </head>
  
  <body>
   <form action="/ArgsTest/fileLoad" method="post" enctype="multipart/form-data">
   <input type="text" name="username">
   <input type="password" name="password"/>
   <input type="file" name="filename" onchange="ge()"/>
   <input type="submit" value="提交"/>
   </form>
  </body>
</html>
