<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="./css/userAdmin.css">
</head>
<body>
	<div class="normal">
		<div class="main_title">用户管理</div>
		<div  class="main_body">
			<div class="nav_title autoH color_909090">
				<label>用户管理</label><span class="jiantou"></span><label class="color_0e6fb6">角色管理</label>
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
					<table cellspacing="0" cellpadding="0">
						<tr class="thead">
							<td class="col_5">
								<label  class="checkAll"></label>
							</td>
							<td class="col_10">序号</td>
							<td class="col_20">角色名称</td>
							<td class="col_20">权限</td>
							<td>操作</td>
						</tr>
						<tr>
							<td class="col_5">
								<label class="check" data-id="001"></label>
							</td>
							<td class="col_10">001</td>						
							<td class="col_20">超级管理员</td>
							<td class="col_20"></td>
							<td><span class="edit"></span>/<span class="delete"></span></td>
						</tr>
					</table>
					<!-- 分页 -->
					<div class="Page navigation">
					  <ul class="pagination">
					    <li><span class="prev">上一页</span></li>
					    <li><span class="curr">1</span></li>
					    <li><span>2</span></li>
					    <li><span>3</span></li>
					    <li><span>4</span></li>
					    <li><span>5</span></li>
					    <li><span class="next">下一页</span></li>
					  </ul>
					<input id="page" type="text" style="width:60px;height:25px;border: 1px solid #ddd;border-radius:5px;">
					<input id="selPage" type="button" value="确定" class="ok">
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
				<form method="post">	
					<div>
						<label>ID:</label>
						<span>
							<input type="text" name="">
						</span>
					</div>
					<div>
						<label>角色名称:</label>
						<span>
							<input type="text" name="">
						</span>
					</div>
					<div class="checkboxs">
						<label>权限:</label>
						<ul>
							<li><input type="checkbox" id="limits1"/><label for="limits1">教学</label></li>
							<li><input type="checkbox" id="limits2"/><label for="limits2">主任</label></li>
							<li><input type="checkbox" id="limits3"/><label for="limits3">普通人</label></li>
							<li><input type="checkbox" id="limits4"/><label for="limits4">教务</label></li>
							<li><input type="checkbox" id="limits5"/><label for="limits5">业务管理员</label></li>
							<li><input type="checkbox" id="limits6"/><label for="limits6">业务管理员</label></li>
						</ul>
					</div>
					<div class="btns">
						<input class="save" type="submit" value="保存">
						<input class="cancel" type="submit" value="取消">
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
				<form method="post">
					<div>
						<label>角色名称:</label>
						<span>
							<input type="text" name="">
						</span>
					</div>
					<div class="checkboxs">
						<label>权限:</label>
						<ul>
							<li><input type="checkbox" id="limits1"/><label for="limits1">教学</label></li>
							<li><input type="checkbox" id="limits2"/><label for="limits2">主任</label></li>
							<li><input type="checkbox" id="limits3"/><label for="limits3">普通人</label></li>
							<li><input type="checkbox" id="limits4"/><label for="limits4">教务</label></li>
							<li><input type="checkbox" id="limits5"/><label for="limits5">业务管理员</label></li>
							<li><input type="checkbox" id="limits6"/><label for="limits6">业务管理员</label></li>
						</ul>
					</div>
					<div class="btns">
						<input class="save" type="submit" value="保存">
						<input class="cancel" type="submit" value="取消">
					</div>
				</form>
			</div>
		</div>		
	</div>

</body>
<script type="text/javascript" src="./js/jquery.min.js"></script>
 <script type="text/javascript">
	//点击复选框切换勾选状态图
	$(".col_5 label").on("click",function(){
		if($(this).hasClass("checked")){
			$(this).removeClass("checked");
		}else{
			$(this).addClass("checked");
		}
	})
	//点击编辑
	$(".edit").on("click",function(){
		$(".editShade").removeClass("hide").addClass("show");
	})
	//点击保存和关闭
	$(".editShade .save").on("click",function(){
		$(".editShade").removeClass("show").addClass("hide");
	})
	$(".editShade .cancel").on("click",function(){
		$(".editShade").removeClass("hide").addClass("show");
	})
	//点击增加
	$(".add").on("click",function(){
		$(".addShade").removeClass("hide").addClass("show");
	})
	//点击保存和关闭
	$(".addShade .save").on("click",function(){
		$(".addShade").removeClass("show").addClass("hide");
	})
	$(".addShade .cancel").on("click",function(){
		$(".addShade").removeClass("hide").addClass("show");
	})
	//关闭右侧
	$(".main_title").on("click",function(){
		$('#aa', window.parent.document).attr("src","");
	})
 </script>
</html>










