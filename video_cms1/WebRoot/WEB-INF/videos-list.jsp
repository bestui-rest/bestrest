<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="./css/videosList.css">
	<link rel="stylesheet" type="text/css" href="./datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="/video_cms/css/jquery.page.css"/>
</head>
<body>
	<div class="normal">
		<div class="main_title">产品管理</div>
		<div  class="main_body">
			<div class="nav_title autoH color_909090">
				<label>产品管理</label><span class="jiantou"></span><label class="color_0e6fb6">视频查看</label>
			</div>
			<!-- 右侧内容 -->
			<div class="content">
				<!-- 选项组 -->
				<div class="options">
					<div>
						<label>课程方向:</label>
						<span><input id="courseName" class="find" type="text" name="" placeholder="请输入信息"></span>
					</div>
					<div>
						<label>课程名称:</label>
						<span><input id="name" class="find" type="text" name="" placeholder="请输入信息"></span>
					</div>
					<div>
						<label>上传者:</label>
						<span><input id="creater" class="find" type="text" name="" placeholder="请输入信息"></span>
					</div>
					<div>
						<input type="button"  class="datetime" id="datetime"  value="-请选择-">	
					</div>
					<div class="search">搜索</div>
				</div>
				<div class="c_main">
					<table id="productData" cellspacing="0" cellpadding="0">
						<tr class="thead">
							
							<td class="col_10">视频编号</td>
							<td class="col_12">课程方向</td>
							<td class="col_20">课程名称</td>							
							<td class="col_12">课程价格</td>
							<td class="col_12">上传者</td>
							<td class="col_10">上传时间</td>
							<td>操作</td>
						</tr>
						<!--<tr>
							
							<td class="col_10">001</td>
							<td class="col_12">UID</td>
							<td class="col_20">PS CC 2017启动界面实现方式</td>
							<td class="col_12">¥35.00</td>
							<td class="col_12">会飞的猪</td>
							<td class="col_10">2017.8.21</td>
							<td><span class="edit"></span>/<span class="delete"></span></td>
						</tr>  -->
					</table>
					<!-- 分页 -->
					<div class="page_box">
					<div class="tcdPageCode page_1"></div>	
					<!-- 当前页 -->
					<input type="hidden" value="1" id="current"/>
					  <!--<ul class="pagination">
					    <li><span class="prev">上一页</span></li>
					    <li><span class="curr">1</span></li>
					    <li><span>2</span></li>
					    <li><span>3</span></li>
					    <li><span>4</span></li>
					    <li><span>5</span></li>
					    <li><span class="next">下一页</span></li>		    
					  </ul>  -->
					</div>
				</div>
			</div>
		</div>	
	</div>
	<!-- 遮罩层 -->
	<div class="editShade hide">
		<div class="shade">	
		</div>
		<div class="shade_main rows">
			<div class="shade_title">修改信息</div>
			<div class="shade_content">
				<form method="post">	
					<input type="hidden" id="courseId"/>
					<input type="hidden" id="loreId"/>
					<div>
						<label>课程ID:</label>
						<span>
							<input id="id" readonly="readonly"  type="text" name="">
						</span>
					</div>
					<div>
						<label>课程方向:</label>
						<span>
							<select id="course" class="select" size="1" name="">
								<!--<option value="1">UID</option>
								<option value="2">JAVA</option>
								<option value="3">WEB</option>  -->
							</select>
						</span>
					</div>
					<div>
						<label>课程名称:</label>
						<span>
							<input id="pname" type="text" name="">
						</span>
					</div>
					<div><label>课程内容:</label>
						<span>
							<select id="lore" class="select" size="1" name="">
								<!--<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>  -->
							</select>
						</span>
					</div>
					<div>
						<label>课程描述:</label>
						<span><input id="description" type="text" name=""></span>
					</div>					
					<div><label>收费方式：</label>
					<span>	
					<select id="status" class="select" size="1" name="">
						<option  value="0">免费</option>
						<option value="1">收费</option>
					</select>
					</span>
					</div>
					<div><label>课程价格：</label>
					<span>	
					<input id="price"  type="text" name="" value="0"/>
					</span>
					</div>
					<div>
					<label>课程图片:</label>
					<span class="upload_file" id="upload_image">
					<input type="file" id="file" name="">
					<input type="text" id="hidden_txt" name="">
					</span>
					</div>
					<div>
					<label>课程视频:</label>
					<span class="upload_file" id="upload_video">
					<input type="file" name="">
					<input type="text" name="">
					</span>
					</div>
					<div>
						<label>上传者:</label>
						<span>
							<input id="pcreater" readonly="readonly"  type="text" name="">
						</span>
					</div>
					<div>
						<label>上传时间:</label>
						<span>
							<input id="createtime" readonly="readonly"  type="text" name="">
						</span>
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
<script type="text/javascript" src="./datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="/video_cms/js/videoList.js"></script>
 <script type="text/javascript">
	$('#datetime').datetimepicker({
		language:  'zh-CN',//语言
		timepicker:false,//不选时间
	  	format:'Y/m/d'//日期格式
	});
	//点击复选框切换勾选状态图
	$(".col_5 label").on("click",function(){
		if($(this).hasClass("checked")){
			$(this).removeClass("checked");
		}else{
			$(this).addClass("checked");
		}
	})
	//点击编辑
	
	//点击保存和关闭
	$(".save").on("click",function(){
		$(".editShade").removeClass("show").addClass("hide");
	})
	$(".cancel").on("click",function(){
		$(".editShade").removeClass("show").addClass("hide");
	})
	//关闭右侧
	$(".main_title").on("click",function(){
		$('#aa', window.parent.document).attr("src","");
	})
 </script>
</html>










