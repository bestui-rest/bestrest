var videoList = {};

$(function(){
	videoList.loadProducts({"courseName":"",
			"name":"","creater":"",
			"createtime":"","page":1});
	videoList.searchClick();
	videoList.changePrice();
	videoList.loadCourse();//JAVA UID  trigger
	videoList.loadLore();//java基础  ajax
});

//给编辑的span绑定点击事件
videoList.loadOneProduct = function(){
	$(".edit").on("click",function(){
		
		//向服务器发请求，获取当前记录的数据
		$.ajax({
			"url":"loadOne.do",
			"type":"post",
			"data":{"id":$(this).data("id")},
			"dataType":"json",
			"success":function(data){
				//console.log(data);
				$("#id").val(data.id);
				//给隐藏框赋值
				$("#course").val(data.course_id);
				$("#course").trigger("change");
				$("#loreId").val(data.lore_id);
				$("#pname").val(data.name);
				$("#description").val(data.description);
				$("#status").val(data.status);
				$("#price").val(data.price);
				$("#upload_image").children("input[type='text']").val(data.image);
				$("#upload_video").children("input[type='text']").val(data.video);
				$("#pcreater").val(data.creater);
				$("#createtime").val(data.ctime);
				
			},
			"error":function(){alert("系统繁忙!");}
		});
		
		$(".editShade").removeClass("hide").addClass("show");
	})	
}

videoList.loadCourse = function(){
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
			//$("#course").trigger("change");
		},
		"error":function(){alert("系统繁忙!");}
	});
}

//给课程方向的select绑定change事件
videoList.loadLore = function(){
	$("#course").change(function(){
		$.ajax({
			"url":"loadLore.do",
			"type":"post",
			"data":{"courseId":$(this).val()},
			"dataType":"json",
			"success":function(data){
				var loreId = $("#loreId").val();
				//清空
				$("#lore").empty();
				$.each(data,function(index,obj){
					var $option ='';
					if(loreId == obj.id){
						$option = $('<option selected value="'+obj.id+'">'+
						  obj.name+'</option>');
					}else{
						$option = $('<option value="'+obj.id+'">'+
						obj.name+'</option>');
					}
					$("#lore").append($option);
				});
			},
			"error":function(){alert("系统繁忙!");}
		});
	});
}





videoList.changePrice = function(){
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





//给搜索按钮绑定点击事件
videoList.searchClick = function(){
	$(".search").click(function(){
		//向服务器发请求，更新table中的数据
		var courseName = $("#courseName").val();
		var name = $("#name").val();
		var creater = $("#creater").val();
		var createtime = $("#datetime").val();
		var page = $("#current").val();
		var searchData = {"courseName":courseName,
			"name":name,"creater":creater,
			"createtime":createtime,"page":page};
		videoList.loadProducts(searchData);
		
	});
	
}

//向服务器发请求，获取product表中所有的记录
videoList.loadProducts = function(searchData){
	$.ajax({
		"url":"listProduct.do",
		"type":"post",
		"data":searchData,
		"dataType":"json",
		"success":function(data){
			$(".tcdPageCode").createPage({
		        pageCount:data.totalPages,
		        current:searchData.page,
		        backFn:function(p){
				  //当用户点击页码时，修改隐藏域中的数据
				   $("#current").val(p);
				    var courseName = $("#courseName").val();
					var name = $("#name").val();
					var creater = $("#creater").val();
					var createtime = $("#datetime").val();
					var searchData = {"courseName":courseName,
						"name":name,"creater":creater,
						"createtime":createtime,"page":p};
		           videoList.loadProducts(searchData);
		        }
		    });
			
			
			//清空
			$(".productTr").remove();
			//更新表格中的数据
			$.each(data.list,function(index,obj){
				var trStr = '<tr class="productTr">';
				trStr +='<td class="col_10">'+obj.id+'</td>';
				trStr +='<td class="col_12">'+obj.course.name+'</td>';
				trStr +='<td class="col_20">'+obj.name+'</td>';
				trStr +='<td class="col_12">¥'+obj.price+'</td>';
				trStr +='<td class="col_12">'+obj.creater+'</td>';
				trStr +='<td class="col_20">'+obj.ctime+'</td>';
				trStr +='<td><span class="edit" data-id="'+obj.id+'"></span>/<span data-id="'+obj.id+'" class="delete"></span></td>';
				trStr += '</tr>';
				$("#productData").append(trStr);
			});
			//给删除的span绑定点击事件
			videoList.delProduct();
			videoList.loadOneProduct();
		},
		"error":function(){alert("系统繁忙!");}
	});	
}

videoList.delProduct = function(){
	$(".delete").click(function(){
		var flag = window.confirm("确定要删除吗?");
		if(!flag){
			return;
		}
		//向服务器发请求，删除product表中的数据以及相应的文件
		$.ajax({
			"url":"delProduct.do",
			"type":"post",
			"data":{"id":$(this).data("id")},
			"success":function(){
				//更新表格数据
				var courseName = $("#courseName").val();
				var name = $("#name").val();
				var creater = $("#creater").val();
				var createtime = $("#datetime").val();
				var searchData = {"courseName":courseName,
					"name":name,"creater":creater,
					"createtime":createtime,"page":1};
				videoList.loadProducts(searchData);
			},
			"error":function(){alert("系统繁忙!");}
		});
		
		
	});
	
}
