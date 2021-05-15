<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="./css/videosUpload.css">
</head>
<body>
	<div class="main_title">产品管理</div>
	<div  class="main_body">
		<div class="nav_title autoH color_909090">
			<label>产品管理</label><span class="jiantou"></span><label class="color_0e6fb6">视频上传</label>
		</div>
		<!-- <div class="main video-upload color_666 rows">	 -->
		<div class="main video-upload rows">
			<form >	
				<div><label>课程名称：</label>
				<span><input type="text" name="name"></span></div>
				<div><label>课程方向：</label>
					<span>
					<select id="course" class="select" size="1" name="course_id">
						<!--<option value="1">UID</option>
						<option value="2">JAVA</option>
						<option value="3">WEB</option>  -->
					</select>
					</span>
				</div>
				<div><label>课程内容：</label>
					<span>
					<select id="lore" class="select" size="1" name="lore_id">
						<!--<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>  -->
					</select>
					</span>
				</div>
				<div><label>课程描述：</label>
				<span><input type="text" name="description"></span></div>
				<div><label>收费方式：</label>
					<span>	
					<select id="status" class="select" size="1" name="status">
						<option value="0">免费</option>
						<option value="1">收费</option>
					</select>
					</span>
				</div>
				<div><label>课程价格：</label>
					<span>	
					<input id="price" readonly="readonly" type="text" name="price" value="0"/>
					</span>
				</div>
				<div>
					<label>课程图片：</label>
					<span class="upload_file" id="upload_image">
						<input type="file"  name="">
						<input type="text"  name="">
						<input type="hidden" id="image" name="image"/>
					</span>
				</div>
				<div>
					<label>课程视频：</label>
					<span class="upload_file" id="upload_video">
					<input type="file" name=""> 
					<input type="text" name="">
					<input type="hidden" id="video" name="video"/>
					</span>					
				</div>
				<div>
					<input class="btn_blue" type="button" value=上传>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<script type="text/javascript" src="/video_cms/js/uploadVideo.js"></script>
	<script type="text/javascript">
		// 上传图片效果
		
		$(".main_title").on("click",function(){
			$('#aa', window.parent.document).attr("src","");
		})
	</script>

</body>
</html>