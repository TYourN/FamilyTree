$(function(){
	
	var objR = new Object;
	
	$(".nav_fallback").on("touchstart",function(){
		$(this).css("background-color","#007aff");
	})
	$(".nav_fallback").on("touchend",function(){
		$(this).css("background-color","#01aaee");
		if($(this).attr("id")=="nav_fallback"){
			window.location.href="index.jsp";
		}
		if($(this).attr("id")=="publish_back"){
			/*初始化界面*/
			$(".publish_title").val("");
			$(".publish_content").val("");
			$(".img_wrap ul").children().remove();
			$(".img_wrap").css("display","none");
			$("#img_sel").val("");
			$(".publish_views").animate({"left":"100%"},500);
			setTimeout(function() {
				$(".publish_views").css("display", "none");
			}, 500);
		}
	})
	
	$(".publish").on("tap",function(){
		$(".publish_views").css("display","block");
		$(".publish_views").animate({"left":"0"},500);
	})
	
	function list(url,forumdata,el){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			contentType:"application/json;charset=UTF-8",
			dataType:"json",
			data:JSON.stringify(forumdata),
			success:function(data){
				var html="";
				$.each(data, function(i,item) {
					html = html + getlist(item);
				});
				if(forumdata.page==1){
					$(el).html(html);	
				}else{
					$(el).append(html);	
				}
			},
			error:function(data){
				
			},
			complete:function(data){
			}
		});
	}
	
	var forumdata = {
		"id" : localStorage.getItem("id"),
		"page" : 1,
		"type" : "all"
	}
	
	list("../../fposts/findTieInfo.do",forumdata,"#forum_list ul");
	
	function getlist(data){
		var item = {
			id : data.id,
			title : data.title,
			firstname : data.firstname,
			number : data.number,
			lasttime : data.lasttime,
			type : data.type
		}
		if(item.type=="水贴"){
			return	"<li id='list"+item.id+"' class='list_item'>"+
					"<div class='item_title'>"+
					"<p style='color: #000000;'>"+item.title+"</p>"+
					"</div>"+
					"<div class='item_detail'>"+
					"<small><span class='icon-user iconuser'></span><p>"+item.firstname+"</p></small>"+
					"<small>"+
					"<span class='icon-time icontime'></span><p>"+item.lasttime+"</p>"+
					"<span class='icon-comments iconcom'></span><p>"+item.number+"</p>"+
					"</small>"+
					"</div>"+
					"</li>"
		}
		if(item.type=="公告"){
			return	"<li id='list"+item.id+"' class='list_item'>"+
					"<div class='item_title'>"+
					"<p style='color:#06B'>"+item.title+"</p>"+
					"</div>"+
					"<div class='item_detail'>"+
					"<small><span class='icon-user iconuser'></span><p>"+item.firstname+"</p></small>"+
					"<small>"+
					"<span class='icon-time icontime'></span><p>"+item.lasttime+"</p>"+
					"<span class='icon-comments iconcom'></span><p>"+item.number+"</p>"+
					"</small>"+
					"</div>"+
					"</li>"
		}
	}
	
	$(".nav_list ul").on("tap","li",function(){
		var before= $(".nav_list").find(".triangle");
		before.parent().css("line-height","40px");
		before.remove();
		$(this).css("line-height","30px");
		$(this).append("<div class='triangle'></div>");
		if($(this).attr("id")=="forum_all"){
			forumdata.page = 1;
			forumdata.type = "all";
			list("../../fposts/findTieInfo.do",forumdata,"#forum_list ul");
		}
		if($(this).attr("id")=="forum_notice"){
			forumdata.page = 1;
			forumdata.type = "forum_notice";
			list("../../fposts/findTieInfo.do",forumdata,"#forum_list ul");
		}
	})

	$("#forum_list").on("tap","li",function(){
		var id = $(this).attr("id").match(/[1-9][0-9]*/g);;
		window.location.href="forum_item.jsp?id="+ id;
	})
	
		$(".picture").on("tap", function() {
		$("#img_sel").trigger("click");
	})

	$("#img_sel").change(function() {
		var reader = new FileReader();
		var f = $("#img_sel")[0].files[0];
		reader.readAsDataURL(f);
		var time = Date.parse(new Date());
		reader.onload = function(e) {
			$(".img_wrap").css("display", "block");
			$(".img_wrap ul").append("<li id='img"+time+"'><img src='" + this.result + "'></li>");
			objR["img"+time] = f;
		}
		$("#img_sure").removeAttr("disabled");
	})

	$(".img_wrap").on("tap", "li", function() {
		delete objR["img"+$(this).attr("id")];
		$(this).remove();
		if($(".img_wrap ul").children("li").length < 1) {
			$(".img_wrap").css("display", "none");
		}
	})
	
	$(".publish_sure").on("tap", function() {
		var img = new Array();
		for(prop in objR){
			img.push(objR[prop]);
		}
		var formData = new FormData();
		formData.append("id",localStorage.getItem("id"));
		formData.append("title",$(".publish_title").val());
		formData.append("content","<p>" + $(".publish_content").val() + "</p>");
		for (var i = 0; i < img.length; i++) {
			formData.append("img", img[i]);
		}
//		formData.append("img",img);
		console.log(img);
		/*var data = {
			title : $(".publish_title").val(),
			content: "<p>" + $(".publish_content").val() + "</p>",
			img : img

		};*/
		var data=formData;
//		var img = $(".img_wrap ul").children("li");
//		for(var i = 0; i < $(".img_wrap ul li").length; i++) {
//			data.title = data.title + $(".img_wrap ul").children("li").eq(i).html();
//		}

		$(".edit_views").animate({ "left": "100%" }, 500);
		setTimeout(function() {
			$(".edit_views").css("display", "none");
		}, 500);
		/*初始化页面*/
			$(".publish_title").val("");
			$(".publish_content").val("");
			$(".img_wrap ul").children().remove();
			$(".img_wrap").css("display","none");
			$("#img_sel").val("");
		/*此处ajax，form表单提交*/
		console.log(data);
		$.ajax({
			type:"post",
			url:"../../fposts/addShuiTie.do",
			async:true,
			data:data,
			contentType: false,  
	        processData: false,
	        dataType:"json",
			success:function(data){
				
			},
			error:function(){
				
			}
		});
	})
	$("#forum_list").load_refresh({
		targetdiv:"#forum_list",
		containerdiv:".content_wrap",
		dataload:function(){
			forumdata.page = 1;
			if($("#forum_all").find(".triangle").length>0){
				forumdata.type = "all";
			}else{
				forumdata.type = "forum_notice";
			}
			list("../../fposts/findTieInfo.do",forumdata,"#forum_list ul");
			console.log(forumdata.type)
		},
		datarefresh:function(){
			forumdata.page += 1;
			if($("#forum_all").find(".triangle").length>0){
				forumdata.type = "all";
			}else{
				forumdata.type = "forum_notice";
			}
			list("../../fposts/findTieInfo.do",forumdata,"#forum_list ul");
			console.log(forumdata.type)
		}
	})
})
