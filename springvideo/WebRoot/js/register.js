	var thg={"email":false,"pwd":false,"nickname":false,"sendvv":false};
$(function(){

	$("#email").blur(function(){
	var gh=$("#email").val();
	if(gh.trim()==""){
	  $("#p_email").html("邮箱不能为空");
	  }
	else{
		//var tfg=new RegExp("^\\w+([\\-\\+\\.]\\w+)*@\\w+([\\-\\.]\\w+)*\\.\\w+([\\-\\.]\\w+)*$");;
		var t=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		alert(gh);
		if(t.test(gh)){
			$.ajax({
				"url":"/springvideo/register/testemail.do",
				"type":"post",
				"data":"email="+gh,
				"dataType":"text",
			"success":function(data,txt){
				$("#p_email").html("邮箱可以注册");
				thg.email=true;
			},
			"error":function(data,txt){
				$("#p_email").html("邮箱不可以注册");
			}
			});
		}else{
			$("#p_email").html("邮箱格式不正确");
		}
	}
	});
	//非空检测
	//格式jianc
	//ajax
	$("#password").blur(function(){
	var gh=$("#password").val();
	if(gh.trim()==""){
	  $("#p_pwd").html("密码不能为空");
	  }
	else{
		var t=/^[a-zA-Z]\w{5,17}$/;
		if(gh.match(t)){
			$("#p_pwd").html("密码格式正确");
			thg.pwd=true;
		}else{
			$("#p_pwd").html("密码格式不正确");
		}
	}
	});
	$("#nickname").blur(function(){
	var gh=$("#nickname").val();
	if(gh.trim()==""){
	  $("#nickname").html("昵称不能为空");
	  }
	else{
		var t=/^[\u4e00-\u9fa5]{2}$/;
		if(gh.match(t)){
			$("#p_nickname").html("昵称格式正确");
			thg.nickname=true;
		}else{
			$("#p_nickname").html("昵称格式不正确");
		}
	}
	});
	$("#sendv").click(function(){
		$("#sendv").val("验证码已发送");
		$.post("/springvideo/register/vm.do",null,function(data){
		
			$("#sendv").val("重新发送");
		});
	});
	
	$("#sendvv").blur(function(){
	var gh=$("#sendvv").val();
	if(gh.trim()==""){
	  $("#p_yanzheng").html("验证码不能为空");
	  }
	else{
		var t=/^[0-9]{4}$/;
		if(gh.match(t)){
			$.post("/springvideo/register/testvm.do",{"value":gh},function(data){
				alert(data);
				$("#p_yanzheng").html("验证码正确").css({"color":"green"});
				thg.sendvv=true;
			});
		}else{
			$("#p_yanzheng").html("验证码格式不正确");
		}
	}
	});
	
    $("#121212").click(function(){
    	if(thg.email&&thg.pwd&&thg.nickname&&thg.sendvv){
   // $("#fl").trigger("click");
    		document.forms[0].submit();
    		//alert("submit");
    		console.log($("#ghjsd"));
    		console.log("0001");
    		//$("#ghjsd").submit();
    		
    	}else{
    		alert("请检查表单");
    		return false;
    	}
    });
	

});


