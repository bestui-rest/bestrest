var uploadVideo = {}

$(function(){
	//页面加载完，立即获取课程方向数据
	uploadVideo.loadCourse();
	uploadVideo.loadLore();
	//页面加载完，给收费方式的select绑定change事件
	uploadVideo.changePrice();
	//页面加载完，给上传图片的span绑定change事件
	uploadVideo.uploadImage();
	uploadVideo.uploadMp4();
	uploadVideo.uploadProduct();
});

//给上传的按钮绑定点击事件
uploadVideo.uploadProduct = function(){
	$(".btn_blue").click(function(){
		$.ajax({
			"url":"uploadProduct.do",
			"type":"post",
			"data":$("form").serialize(),
			"success":function(){
				alert("success");
				//给子页面的src重新赋值
				alert($("#aa",window.parent.document).attr("src"));
				$("#aa",window.parent.document).
				attr("src",$("#aa",window.parent.document).attr("src"));
			},
			"error":function(){alert("系统繁忙!");}
		});
	});
}




//上传视频
uploadVideo.uploadMp4 = function(){
	document.getElementById("upload_video").onchange = function(){
		this.children[1].value = "";
		var mp4Url = this.children[0].value;
		var fix = mp4Url.substring(mp4Url.lastIndexOf(".")+1);
		if("mp4"!=fix){
			alert("请选择mp4格式的文件上传");
			return;
		}
		this.children[1].value = this.children[0].value;
		var mp4Data = this.children[0].files[0];
		var videoData = new FormData();
		videoData.append("mp4",mp4Data);
		uploadVideo.uploadFile(videoData);
	}
}





//上传图片
uploadVideo.uploadImage = function(){
	//给上传图片的span绑定change事件
	document.getElementById('upload_image').onchange = function(){
		//清空文本输入框中的数据
		this.children[1].value = "";
		//console.log(this.children[0].value);
		//判断图片的类型 是否是jpg
		var imageUrl = this.children[0].value;
		var fix = imageUrl.substring(
					imageUrl.lastIndexOf(".")+1);
		if("jpg"!=fix){
			alert("请选择jpg格式的图片上传");
			return;
		}
		this.children[1].value = this.children[0].value;
		var imageData = new FormData();
		imageData.append("image",this.children[0].files[0]);
		uploadVideo.uploadFile(imageData);
	}
	
}


uploadVideo.uploadFile = function(data){
	//向服务器发请求，上传文件，避免浏览器刷新，所以用ajax
		$.ajax({
			"url":"uploadFile",
			"type":"post",
			"data":data,
			"dataType":"json",
			"contentType":false,//告诉jquery，不要将contentType设置成application/.....
			"processData":false,//告诉jquery，不要将参数转成字符串
			"success":function(data){
				//给image与video的隐藏域赋值
				if(data.obj.fix=="jpg"){
					$("#image").val(data.obj.newName+"."+data.obj.fix);
				}else if(data.obj.fix=="mp4"){
					$("#video").val(data.obj.newName+"."+data.obj.fix);
				}
				alert("success");
			},
			"error":function(){alert("系统繁忙!");}
		});
}






uploadVideo.changePrice = function(){
	$("#status").change(function(){
		var priceStatus = this.value;
		if(priceStatus=='0'){
			//免费 
			$("#price").prop("readonly",true);
			$("#price").val("0");
		}else if(priceStatus=='1'){
			//收费
			$("#price").removeProp("readonly");
			$("#price").val("0");
		}
		
	});
	
}




//给课程方向的select绑定change事件
uploadVideo.loadLore = function(){
	$("#course").change(function(){
		$.ajax({
			"url":"loadLore.do",
			"type":"post",
			"data":{"courseId":$(this).val()},
			"dataType":"json",
			"success":function(data){
				//清空
				$("#lore").empty();
				$.each(data,function(index,obj){
					var $option = $('<option value="'+obj.id+'">'+
						obj.name+'</option>');
					$("#lore").append($option);
				});
			},
			"error":function(){alert("系统繁忙!");}
		});
	});
}




uploadVideo.loadCourse = function(){
	$.ajax({
		"url":"loadCourse.do",
		"type":"post",
		"dataType":"json",
		"success":function(data){
			$.each(data,function(index,obj){
				var $option = $('<option value="'+obj.id+'">'+
					obj.name+'</option>');
				$("#course").append($option);
			});
			//模拟手动触发change事件
			$("#course").trigger("change");
		},
		"error":function(){alert("系统繁忙!");}
	});
}



