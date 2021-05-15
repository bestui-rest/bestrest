var admin = {};

$(function(){
	//加载管理员信息
	admin.loadAdmins(1);
	//页面加载完，给添加的按钮绑定点击事件
	admin.toAddAdmin();
	//页面加载完，给保存的按钮绑定点击事件
	admin.addAdmin();
	//页面加载完，给批量删除按钮绑定点击事件
	admin.batchDelAdmin();
	//页面加载完，给修改操作的保存按钮绑定点击事件
	admin.updateAdmin();
});

admin.updateAdmin = function(){
	//点击保存和关闭
	$(".editShade .save").on("click",function(){
		$.ajax({
			"url":"updateAdmin.do",
			"type":"post",
			"data":$("#updateForm").serialize(),
			"success":function(data){
				//更新表格数据
				admin.loadAdmins($("#current").val());
			
			},
			"error":function(){alert("系统繁忙!");}			
		});
		$(".editShade").removeClass("show").addClass("hide");
	})
}


//点击编辑 加载某一个admin的信息
admin.loadOneAdmin = function(){
	$(".edit").on("click",function(){
		$.ajax({
		"url":"loadAdmin.do",
		"type":"post",
		"data":{"id":$(this).data("id")},
		"dataType":"json",
		"success":function(data){
			//TODO
			$("#id").val(data.admin.id);
			$("#username").val(data.admin.username);
			$("#pwd").val(data.admin.pwd);
			$("#name").val(data.admin.name);
			//循环遍历roleList 生成角色名称复选框
			$("#role_edit").empty();
			$.each(data.roleList,function(index,obj){
				var $liStr = $('<li><input type="checkbox" value="'+obj.id+'"' +
					' name="roleEdit_name" id="role'+obj.id+'"/>' +
					'<label for="role'+obj.id+'">'+obj.name+'</label></li>');
				//判断admin用户拥有的角色id与所要添加的角色id是否相等
				//如果相等，添加checked属性
				$.each(data.admin.roles,function(index2,obj2){
					if(obj.id==obj2.id){
						//prop("checked",true) 
						//attr("checked","checked")
						$liStr.children("input").prop("checked",true);
					}
				});
				
				$("#role_edit").append($liStr);
				
			});
		},
		"error":function(){alert("系统繁忙!");}
	});
	
		
		
		
		$(".editShade").removeClass("hide").addClass("show");
	})
}




admin.batchDelAdmin = function(){
	$(".deleteMany").click(function(){
		//判断标题复选框是否被选中
		var checked = $(".col_5 .check[class~=checked]");
		if(checked.length==0){
			alert("请选择所要删除的记录");
			return;
		}
//		var flag = window.confirm("确定要删除吗?");
//		if(!flag){
//			return;
//		}
		//删除
		//<a data-id1="1"></a>
		var arr = [];//new Array();
		$.each(checked,function(index,obj){
			var id = $(obj).data("id");
			arr.push(id);
		})
		//console.log(arr);
		admin.delOneAdmin(arr);
	});
	
}




//给复选框绑定点击事件
admin.checkLabel = function(){
	//点击复选框切换勾选状态图
	//表头复选框
	$(".col_5 .checkAll").unbind().on("click",function(){
		if($(this).hasClass("checked")){
			//去掉表体的勾选状态
			$(".col_5 .check").removeClass("checked");
			$(this).removeClass("checked");
		}else{
			$(".col_5 .check").addClass("checked");
			$(this).addClass("checked");
		}
	})
	//表体复选框
	$(".col_5 .check").on("click",function(){
		if($(this).hasClass("checked")){
			$(this).removeClass("checked");
		}else{
			$(this).addClass("checked");
		}
	});
	
}



//删除单个管理员
admin.delOneAdmin = function(id){
	var flag = window.confirm("确认删除吗?");
	if(!flag){
		return;
	}
	$.ajax({
		"url":"delAdmin.do",
		"type":"post",
		"data":{"id":id},//jquery.param(,traditional)
		"traditional":true,//阻止jquery深度序列化
		//"dataType":"json",
		"success":function(data){
			//更新表格数据
			admin.loadAdmins(1);
		},
		"error":function(){alert("系统繁忙");}
	});
}



//保存所添加的管理员信息
admin.addAdmin = function(){
	$(".addShade .save").on("click",function(){
		//存储admin数据
		$.ajax({
			"url":"addAdmin.do",
			"type":"post",
			"data":$("#addForm").serialize(),
			"success":function(){
				//如果添加成功，隐藏添加管理员的div
				$(".addShade").removeClass("show").addClass("hide");
				//更新表格中的数据
				admin.loadAdmins(1);
			},
			"error":function(){alert("系统繁忙");}
		});
		
		
	})
}




//打开添加管理员的div
admin.toAddAdmin = function(){
	//点击增加
	$(".add").on("click",function(){
		//向服务器发请求，获取角色信息
		$.ajax({
			"url":"roleList.do",
			"type":"post",
			"dataType":"json",
			"success":function(data){
				//console.log(data);
				$("#role_add").empty();
				$.each(data,function(index,obj){
					var listr = '<li><input type="checkbox" ' +
					'value="'+obj.id+'" name="roleAdd_name" id="role'+obj.id+'"/>' +
					'<label for="role'+obj.id+'">'+obj.name+'</label></li>';
					$("#role_add").append(listr);
				});
			},
			"error":function(){alert("系统繁忙");}
			
		});
		$(".addShade").removeClass("hide").addClass("show");
	})
	
}



admin.loadAdmins = function(page){
	//使用ajax对象，向服务器发请求，获取admin
	$.ajax({
		"url":"adminList.do",
		"type":"post",
		"data":{"page":page},
		"dataType":"json",
		"success":function(data){
			var totalPages = data.totalPages;
			$(".tcdPageCode").createPage({
		       pageCount:totalPages,
		       current:page,
		       backFn:function(p){
				//将页码p的值记录下来
				$("#current").val(p);
		         //alert(p);
				//根据页码更新table中的数据		
				admin.loadAdmins(p);
		       }
		   });
			
			//清空表头中复选框勾选效果
			$(".col_5 label").removeClass("checked");
			$(".admintr").remove();
			//循环遍历
			$.each(data.list,function(index,obj){
				var admintr = '<tr class="admintr">';
				admintr += '<td class="col_5"><label class="check" ' +
				'data-id="'+obj.id+'"></label></td>';
				admintr += '<td class="col_5">'+((index+1)+(page-1)*2)+'</td>';
				admintr += '<td class="col_20">'+obj.username+'</td>';
				admintr += '<td class="col_20">'+obj.pwd+'</td>';
				admintr += '<td class="col_20">'+obj.name+'</td>';
				var rolename = '';
				$.each(obj.roles,function(index2,obj2){
					if(index2==obj.roles.length-1){
						rolename += obj2.name;
					}else{
						rolename += obj2.name+",";
					}
				});
				admintr += '<td class="col_20">'+rolename+'</td>';
				admintr += '<td><span class="edit" data-id="'+obj.id+'" ></span>/<span class="delete" ' +
				'onclick="admin.delOneAdmin('+obj.id+');"></span></td>';
				admintr += '</tr>';
				$("#adminData").append(admintr);
			});
			//表体加载完毕，绑定复选框点击事件
			admin.checkLabel();
			admin.loadOneAdmin();
			
		},
		"error":function(){alert("系统繁忙!");}
	});
}






