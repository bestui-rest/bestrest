//定义命名空间
var userVip = {};

$(function(){
	userVip.loadUsers(1);
	userVip.clickBtn();
});

//给确定按钮绑定点击事件
//获取输入框中的数据
//调用loadUsers 更新表格中的数据
userVip.clickBtn = function(){
	$("#selPage").click(function(){
		var page = $("#page").val();
		//验证....
		userVip.loadUsers(page);
	});	
}





//加载所有会员信息
userVip.loadUsers = function(page){
	$.ajax({
		"url":"userList.do",
		"type":"post",
		"data":{"page":page},
		"dataType":"json",
		"success":function(data){
			//获取总页数
			var totalPages = data.totalPages;
			
			//给table添加tr之前 先清空原先的table中表体数据
			$(".viptr").remove();
			//循环遍历data 获取每一个user对象
			$.each(data.list,function(index,obj){
				var trStr = '<tr class="viptr">';
				trStr += '<td class="col_15">'+obj.id+'</td>';
				trStr += '<td class="col_25">'+obj.username+'</td>';
				trStr += '<td class="col_25">'+obj.email+'</td>';
				trStr += '<td class="col_20">'+obj.rtime+'</td>';
				trStr += '</tr>';
				$("#userData").append(trStr);
			});
		
		},
		"error":function(){alert("系统繁忙!");}
	});
}





