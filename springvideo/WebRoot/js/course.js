$(function(){

	$(".course_box .courses a").each(function(){
		// console.log($(this).index()+1);

		if(($(this).index()+1) % 5 == 0){
			$(this).css("margin-right",0);
		}

	})

	$(".s001 span").click(function(event) {
		$(this).addClass('current').siblings().removeClass('current');
		var curcoursedir=$(this).data("id");
		$("#curcoursedir").val(curcoursedir);
		var i = $(this).index()-1;
		var contentid= $("#contentid").val();
		//alert("curcoursedir"+curcoursedir);
		$(this).siblings('label').attr("title",i);
		 var page=1;
		 $("pagecur").val(1);
		//alert($(this).attr("name")+"  "+curcoursedir+"  "+contentid+"   "+page);
		 window.location.href="/springvideo/main/tocourse.do?coursedir="+curcoursedir+"&page="+page+"&contentid="+contentid;
		
	});
	$(".s002 span").click(function(event) {
		$(this).addClass('current').siblings().removeClass('current');
		var contentid=$(this).data("id");
		 $("#contentid").val(contentid);
		var i = $(this).index()-1;
		 console.log("contentid"+contentid);
		//alert("contentid"+contentid);
		$(this).siblings('label').attr("title",i);
		var page=1;
		var curcoursedir=$("#curcoursedir").val();
		 window.location.href="/springvideo/main/tocourse.do?coursedir="+curcoursedir+"&page="+page+"&contentid="+contentid;
	});

	// 全选/反选
	$("input").click(function(){
		// console.log($(this).prop("checked"));
		if($(this).attr("name")=="all"){
		   $("input[type=checkbox]").prop("checked",$(this).prop("checked"));
		   }
		var chile=$(".price");
		var chil=document.getElementsByName("pro");
		var totalPrice=0;
		var con=0;
		for(var i=0;i<chil.length;i++){
			var f=chil[i];
			if(f.checked){
				con++;
			var tem=parseFloat(chile[i].innerText.substr(1));
			var tt=parseFloat(tem);
			totalPrice+=tt;
			}
		}
		document.getElementsByName("count")[0].innerText=con;
		document.getElementsByName("prices")[0].innerText=totalPrice;

	})

	// 全部商品
	$(".car_box span[name=all_counts]").html($(".car_box ul li").length);


//	$("input[type=checkbox]").prop("checked",true);
	

	

	
	
	
	

	

	// 确认付款
	$("#confirm").click(function(){
		// 满足6位数字
		if(new RegExp(/^[0-9]{6}$/).test($("#payPassword_rsainput").val())){
			window.location = "paySuccess.html";
		}
	})


})