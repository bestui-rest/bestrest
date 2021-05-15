<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="./css/userAdmin.css">
	<link rel="stylesheet" type="text/css" 
	href="/video_cms/css/jquery.page.css">
</head>
<body>
	<div class="normal">
		<div class="main_title">用户管理</div>
		<div  class="main_body">
			<div class="nav_title autoH color_909090">
				<label>用户管理</label><span class="jiantou"></span><label class="color_0e6fb6">管理员</label>
			</div>
			<!-- 右侧内容 -->
			<div class="content">
				<!-- 选项组 -->
				<div class="options">
					<div class="o_btns">
						<input class="deleteMany" value="批量删除"/>
						<input class="add" value="增加"/>
					</div>
				</div>
				<div class="c_main">
					<table id="adminData" cellspacing="0" cellpadding="0">
						<tr class="thead">
							<td class="col_5">
								<label  class="checkAll"></label>
							</td>
							<td class="col_5">序号</td>
							<td class="col_20">用户名</td>
							<td class="col_20">密码</td>
							<td class="col_20">姓名</td>
							<td class="col_20">角色名称</td>
							
							<td width=50>操作</td>
						</tr>
						<!--<tr>
							<td class="col_5">
								<label class="check" data-id="001"></label>
							</td>
							<td class="col_5">001</td>
							<td class="col_20">admin</td>
							<td class="col_20">1234</td>
							<td class="col_20">章三</td>
							<td class="col_20">超级管理员</td>
							
							<td><span class="edit"></span>/<span class="delete"></span></td>
						</tr>  -->
					</table>
					<!-- 分页 -->
					<div class="page_box">
					 	 <div class="tcdPageCode page_1"></div>
					 	 <input type="hidden" id="current" value="1"/>
					</div>
				</div>
			</div>
		</div>	
	</div>
	<!-- 修改的遮罩层 -->
	<div class="editShade hide">
		<div class="shade">	
		</div>
		<div class="shade_main rows">
			<div class="shade_title">修改信息</div>
			<div class="shade_content">
				<form id="updateForm" method="post">	
					<div>
						<label>ID:</label>
						<span>
							<input id="id" readonly="readonly" 
							type="text" name="id">
						</span>
					</div>
					<div>
						<label>用户名:</label>
						<span>
							<input id="username" type="text" name="username">
						</span>
					</div>
					<div>
						<label>真实姓名:</label>
						<span>
							<input id="name" type="text" name="name">
						</span>
					</div>
					<div>
						<label>密码:</label>
						<span>
							<input id="pwd" type="password" name="pwd">
						</span>
					</div>
					<div class="checkboxs">
						<label>角色名称:</label>
						<ul id="role_edit">
							<!-- <li><input type="checkbox" id="role1"/><label for="role1">业务管理员</label></li>
							<li><input type="checkbox" id="role2"/><label for="role2">管理员</label></li>
							<li><input type="checkbox" id="role3"/><label for="role3">普通人</label></li>
							<li><input type="checkbox" id="role4"/><label for="role4">业务管理员</label></li>
							<li><input type="checkbox" id="role5"/><label for="role5">业务管理员</label></li>
							<li><input type="checkbox" id="role6"/><label for="role6">业务管理员</label></li>
						    -->
						</ul>
					</div>
					
					<div class="btns">
						<input class="save" type="button" value="保存">
						<input class="cancel" type="button" value="取消">
					</div>
				</form>
			</div>
		</div>		
	</div>
	<!-- 增加的遮罩层 -->
	<div class="addShade hide">
		<div class="shade">	
		</div>
		<div class="shade_main rows">
			<div class="shade_title">增加人员</div>
			<div class="shade_content">
				<form method="post" id="addForm">
					<div>
						<label>用户名:</label>
						<span>
							<input type="text" name="username">
						</span>
					</div>
					<div>
						<label>真实姓名:</label>
						<span>
							<input type="text" name="name">
						</span>
					</div>
					<div>
						<label>密码:</label>
						<span>
							<input type="password" name="pwd">
						</span>
					</div>
					<div class="checkboxs">
						<label>角色名称:</label>
						<ul id="role_add">
							<!--<li><input type="checkbox" id="role1"/><label for="role1">业务管理员</label></li>
							<li><input type="checkbox" id="role2"/><label for="role2">管理员</label></li>
							<li><input type="checkbox" id="role3"/><label for="role3">普通人</label></li>
							<li><input type="checkbox" id="role4"/><label for="role4">业务管理员</label></li>
							<li><input type="checkbox" id="role5"/><label for="role5">业务管理员</label></li>
							<li><input type="checkbox" id="role6"/><label for="role6">业务管理员</label></li>
							  -->
						</ul>
					</div>
					
					<div class="btns">
						<input class="save" type="button" value="保存">
						<input class="cancel" type="button" value="取消">
					</div>
				</form>
			</div>
		</div>		
	</div>

</body>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="/video_cms/js/jquery.page.js"></script>
<script type="text/javascript" src="/video_cms/js/admin.js"></script>
 <script type="text/javascript">
 
	
	$(".editShade .cancel").on("click",function(){
		$(".editShade").removeClass("show").addClass("hide");
	})

	//点击保存和关闭
	
	$(".addShade .cancel").on("click",function(){
		$(".addShade").removeClass("show").addClass("hide");
	})
	//关闭右侧
	$(".main_title").on("click",function(){
		$('#aa', window.parent.document).attr("src","");
	})
 </script>
</html>










