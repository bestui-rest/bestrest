<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<link id="skincss" href="css/aml_<bean:write name="style" scope="session"/>.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
   <form action='./login/controller' method='get'>
   <input type='text' name='username'/>
   <input type='password' name='password'/>
   <input type='submit' value='提交'/>
   </form>
  <!--  <a href="/shaml/login/controller?username=张三&password=123">dfsfsdf</a> -->
  <select name='dd' style="disabled:disabled;" >
  <option value='1'>ff</option>
  <option value='2'>ff2</option>
  <option value='3'>ff3</option>
  <option value='4'>ff4</option>
  </select>
	</body>

</html>
