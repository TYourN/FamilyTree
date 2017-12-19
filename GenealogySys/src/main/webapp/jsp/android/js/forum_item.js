$(function() {
	$(".nav_fallback").on("touchstart", function() {
		$(this).css("background-color", "#007aff");
	})
	
	var objR = new Object;
	
	$(".nav_fallback").on("touchend", function() {
		$(this).css("background-color", "#01aaee");
		if($(this).attr("id") == "main_back") {
			window.location.href = "forum.jsp";
		}
		if($(this).attr("id") == "reply_back") {
			$(".edit_views").animate({ "left": "100%" }, 500);
			setTimeout(function() {
				$(".edit_views").css("display", "none");
			}, 500);
			$(".edit_wrap").val("");
			$(".img_wrap ul").children().remove();
			$("#img_sel").val("");
			$(".img_wrap").css("display", "none");
		}
	})

	$("#item_content").on("tap", ".operation", function() {
		data.id = $(this).parents("li").attr("id");
		$(".edit_views").css("display", "block");
		$(".edit_tool").css("display", "none");
		$(".edit_views").animate({ "left": "0" }, 500);
	})

	$(".publish").on("tap", function() {
		data.id = 0;
		$(".edit_views").css("display", "block");
		$(".edit_views").animate({ "left": "0" }, 500);
		$(".edit_tool").css("display", "block");
		$(".edit_wrap").css("padding-bottom", "50px");
	})

	function getQueryVariable(variable) {
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for(var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			if(pair[0] == variable) { return pair[1]; }
		}
		return(false);
	}

	data = {
		page: "1",
		pid: getQueryVariable("id"),
		id: ""
	}

	function getdata(url, itemdata, el) {
		$.ajax({
			type: "post",
			url: url,
			async: true,
			data:JSON.stringify(itemdata),
			contentType:"application/json;charset=UTF-8",
			dataType: "json",
			success: function(data) {
				var html = "";
				console.log(data);
				$(".content_title").html(data.title);
				$.each(data.content, function(i, item) {
					html = html + getdatadom(item, i + 1,itemdata.page);
				});
				if(itemdata.page==1){
					$(el).html(html);
				}else{
					$(el).append(html);
				}
			},
			error: function(data) {
				
			}
		});
	}
	
	var itemdata = {
		id: localStorage.getItem("id"),
		pid: getQueryVariable("id"),
		page : 1
	}
	getdata("../../fposts/findTieInfoById.do", itemdata , "#item_content ul");

	function getdatadom(data, i, page) {
		var data = {
			id: data.id,
			img: data.img,
			user: data.user,
			time: data.time,
			content: data.content,
			child: data.children
		}
		var html = "";
		var hf = "";
		if(data.child && data.child.length > 0) {
			for(var j = 0; j < data.child.length; j++)
				html = html + "<span id='user" + data.child[j].id + "'>" + data.child[j].user + "：</span>" + data.child[j].content;
		}
		if(i*page!="1"){
			hf="<span class='icon-comment-alt operation'></span>";
		}
		return "<li id='" + data.id + "'>" +
			"<div class='item_title'>" +
			"<img src='" + data.img + "'>" +
			"<div class='item_user'>" +
			"<p>" + data.user + "</p>" +
			"<small>" + i*page + "楼|" + data.time + "</small>" + 
			"</div>" +
				hf +
			"</div>" +
			"<div class='item_message'>" + data.content +
			"</div>" +
			"<div class='item_reply'>" + html +
			"</div>" +
			"</li>"
	}

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

	$(".edit_sure").on("tap", function() {
		var img = new Array();
		for(prop in objR){
			img.push(objR[prop]);
		}
		console.log(img);
		/*var html = {
			data: "<p>" + $(".edit_wrap").val() + "</p>",
			id: data.id,
			pid: data.pid,
			img : img
		};*/
		var formData = new FormData();
		formData.append("id",localStorage.getItem("id"));
		formData.append("postid",data.pid);
		formData.append("parentid",data.id);
		formData.append("content","<p>" + $(".edit_wrap").val() + "</p>");
		for (var i = 0; i < img.length; i++) {
			formData.append("img", img[i]);
		}
		
//		var img = $(".img_wrap ul").children("li");
//		for(var i = 0; i < $(".img_wrap ul li").length; i++) {
//			html.data = html.data + $(".img_wrap ul").children("li").eq(i).html();
//		}
		$(".edit_views").animate({ "left": "100%" }, 500);
		setTimeout(function() {
			$(".edit_views").css("display", "none");
		}, 500);
		/*初始化页面*/
		$(".edit_wrap").val("");
		$(".img_wrap ul").children().remove();
		$("#img_sel").val("");
		$(".img_wrap").css("display", "none");
		/*此处ajax，form表单提交,主恢复*/
				$.ajax({
					type:"post",
					url:"../../frep/addReplies.do",
					async:true,
					data:formData,
					contentType: false,  
			        processData: false,
			        dataType:"json",
					success:function(data){
						console.log(11);
					},
					error:function(){
						
					}
				});
	})
	$(".content_wrap ul").load_refresh({
		targetdiv:".content_wrap ul",
		containerdiv:".content_wrap",
		dataload:function(){
			itemdata.page = "1";
			getdata("../../fposts/findTieInfoById.do", itemdata , "#item_content ul");
		},
		datarefresh:function(){
			itemdata.page += 1;
			getdata("../../fposts/findTieInfoById.do", itemdata , "#item_content ul");
		}
	})
})