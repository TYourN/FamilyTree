/**
 * 
 */
$(function(){
	
	$.ajax({
		type:"post",
		url:"../../fhp/findPicsByAu.do",
		async:true,
		dataType:"json",
		success:function(data){
			$.each(data, function(i,item) {
				if(item.id=="homebg"){
					$("#homebg .img_wrap ul").html(getitem(item.item));
				}else if(item.id=="personbg"){
					$("#personbg .img_wrap").html("<img class='php_img' src='"+item.item+"'>");
				}else if(item.id=="phonebg"){
					$("#phonebg .img_wrap ul").html(getitem(item.item));
				}
			});
		},
		complete:function(){
			removedisable("#homebg",$("#homebg .goright"));
			removedisable("#phonebg",$("#phonebg .goright"));
		}
	});
	
	
	function getitem(data){
		var html = "";
		for(var i = 0;i < data.length;i++){
			html += "<li><img src='"+data[i]+"'</li>";
		}
		return html;
	}
	
	function removedisable(el,target){
		if($(el+" .img_wrap ul").children().length>4){
			target.attr("disabled",false);
		}
	}
	
	$(".goleft").click(function(){
		Target = $(this).parent(".panel-body").children(".img_wrap").children("ul");
		var tran = Target.css("transform").replace(/[^0-9\-,]/g,'').split(',')[4]||"0";
		var count = Target.children().length;
		Target.css("transform","translateX("+((tran-0)+220)+"px)");
		if(tran=="-220"){
			$(this).attr("disabled",true);
		}
		if((tran/(-220)+4)<=count){
			$(this).next().attr("disabled",false);
		}	
	})
	
	$(".goright").click(function(){
		Target = $(this).parent(".panel-body").children(".img_wrap").children("ul");
		var tran = Target.css("transform").replace(/[^0-9\-,]/g,'').split(',')[4]||"0";
		var count = Target.children().length;
		Target.css("transform","translateX("+(tran-220)+"px)");
		if(tran=="0"){
			$(this).prev().attr("disabled",false);
		}
		if((tran/(-220)+4)>=count-1){
			$(this).attr("disabled",true);
		}
	})
	
	$("#hbg_btn").click(function(){
		$("#hbg_input").trigger("click");
	})
	
	$("#php_btn").click(function(){
		$("#php_binput").trigger("click");
	})
	
	$("#phonebg_btn").click(function(){
		$("#phonebg_input").trigger("click");
	})
	
	$("#hbg_input").change(function(){
		var reader = new FileReader();
		var f = $("#hbg_input")[0].files[0];
		var formData = new FormData();
		formData.append("flag","第一页");
		formData.append("img",f);
		$.ajax({
			type:"post",
			url:"../../fhp/addFirstPics.do",
			async:true,
			data:formData,
	        contentType: false,  
	        processData: false,
	        dataType:"json",
			success:function(data){
				$("#homebg .img_wrap ul").append("<li><img src='"+data.img+"'></li>");
			}
		});
	})
	
	$("#php_binput").change(function(){
		var reader = new FileReader();
		var f = $("#php_binput")[0].files[0];
		var formData = new FormData();
		formData.append("flag","展示");
		formData.append("img",f);
		$.ajax({
			type:"post",
			url:"../../fhp/addFirstPics.do",
			async:true,
			data:formData,
	        contentType: false,  
	        processData: false,
	        dataType:"json",
			success:function(data){
				$("#homebg .img_wrap").append("<img class='php_img' src='"+data.img+"'>");
			}
		});
	})
	
	$("#phonebg_input").change(function(){
		var reader = new FileReader();
		var f = $("#phonebg_input")[0].files[0];
		var formData = new FormData();
		formData.append("flag","手机");
		formData.append("img",f);
		$.ajax({
			type:"post",
			url:"../../fhp/addFirstPics.do",
			async:true,
			data:formData,
	        contentType: false,  
	        processData: false,
	        dataType:"json",
			success:function(data){
				$("#phonebg_btn .img_wrap ul").append("<li><img src='"+data.img+"'></li>");
			}
		});
	})
})
