<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="./js/jquery.min.js"></script>
<script type="text/javascript">
var appname='<%=request.getContextPath()%>';
$(function(){
	$.ajax({
		url:appname+"/TestTT",
		type:'post',
		dataType:'text',
		success:function(data){
			var arr=data.split(",");
			$.each(arr,function(index,obj){
				console.log();
				var va=obj.replace("'","").replace("'","");
				str="<a style='margin:6px;' href="+obj+" target='bot'  >"+va+"</a>" ;
				$("#lkj").append(str);
			});
		}
	});
	
	
});

</script>
</head>
<body>
<div  id="lkj"></div>
<iframe name="bot" height="900px" width="900px" style="border:none;"></iframe>
</body>




</html>