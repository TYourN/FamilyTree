$(function(){
	
	var data = {
		id : localStorage.getItem("id"),
		pid : getQueryVariable("itemid"),
		page : 1
	}
	
	$.ajax({
		type:"post",
		url:"../../../fuser/getUserDety.do",
		async:true,
		data:{"id":localStorage.getItem("id")},
		dataType:"json",
		success:function(data){
			$(".user_inf_name").html(data.name);
			$(".user_inf_place").html(data.place);
			$(".user_inf_img").attr("src",data.img);
			$(".user_img").attr("src",data.img);
		},
		error:function(){
			
		}
	});
	
	var imgobj =new Object;
	$("#dl").hover(function() {
		$(".dl_wrap").css("display", "block");
	}, function() {
		$(".dl_wrap").css("display", "none");
	})
	
	$("#user").hover(function() {
		$(".user_wrap").css("display", "block");
	}, function() {
		$(".user_wrap").css("display", "none");
	})
	
	$(".unlogin").click(function(){
		localStorage.setItem("id","");
		window.localStorage.href="Homepage.jsp"
	})
	
	$(".user_core").click(function() {
		var parent = $(this).parents(".user_wrap");
		var id = parent.attr("id").match(/[1-9][0-9]*/g);
		var url = "pcenter.jsp?id=" + id
		window.open(url);
	})
	
	$(".tcdPageCode").on("click","a",function(){
		if($(this).hasClass("prevPage")){
			data.page = $(".tcdPageCode").find(".current").html() - 1;
		}
		else if($(this).hasClass("nextPage")){
			data.page = $(".tcdPageCode").find(".current").html() + 1;
		}else{
			data.page = $(this).html();
		}
		getlist("../../../fposts/findShTieInfoById.do",data);
	})
	
	$(".content_wrap").on("click","#sreply",function(){
		//var repdata = {
			//userid : localStorage.getItem("id"),
			//pid : $(this).parents(".item_wrap").attr("id").match(/[1-9][0-9]*/g),
			//content : $(this).prev().val(),
			//img : ""
		//}
		var formData = new FormData();
		formData.append("id",localStorage.getItem("id"));
		formData.append("parentid",$(this).parents(".item_wrap").attr("id").match(/[1-9][0-9]*/g));
		formData.append("itemid",getQueryVariable("itemid"));
		formData.append("content","<p>" + $(this).prev().val() +"</p>");
		formData.append("img","")
		seedmess("../../../frep/addShReplies.do",formData);
	})
	
	$("#re_photo").hover(function(){
		$(".imgwrap").css("display","block");
	},function(){
		$(".imgwrap").css("display","none");
	})
	
	$("#sel_btn").click(function(){
		$("#img_sel").trigger("click");
	})
	
	$(".img_sel").on("click","p",function(){
		delete imgobj["img"+$(this).parents(".sel_img").attr("id")];
		$(this).parents(".sel_img").remove();
	})
	
	$("#img_sel").change(function(){
		var reader = new FileReader();
		var f = $("#img_sel")[0].files[0];
		reader.readAsDataURL(f);
		var time = Date.parse(new Date());
		reader.onload = function(e){
			$(".img_sel ul").append("<li id='img"+time+"' class='sel_img'><img src='" + this.result + "'><p>删除</p></li>");
			imgobj["img"+time] = f;
		}
		$("#img_sel").val("");
	})
	
	$(".reply_sure").click(function(){
		var img = new Array();
		for(prop in imgobj){
			img.push(imgobj[prop]);
		}
		/*var repdata = {
			userid : localStorage.getItem("id"),
			pid : 0,
			content : $(".reply_con").val(),
			img : img
		}*/
		var formData = new FormData();
		formData.append("id",localStorage.getItem("id"));
		formData.append("parentid",0);
		formData.append("itemid",getQueryVariable("itemid"));
		formData.append("content","<p>" + $(".reply_con").val() +"</p>");
		for (var i = 0; i < img.length; i++) {
			formData.append("img", img[i]);
		}
		seedmess("../../../frep/addShReplies.do",formData);
	})
	
	getlist("../../../fposts/findShTieInfoById.do",data);
	
	function getlist(url,listdata){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			data:JSON.stringify(listdata),
			contentType:"application/json;charset=UTF-8",
			dataType:"json",
			success:function(data){
				if($(".tcdPageCode").data("already")=="unfinished"){
					$(".tcdPageCode").createPage({
					    pageCount:data.page,
					    current:1,
					    backFn:function(p){}
				   	});
					$(".tcdPageCode").data("already","finished");
				}
				$(".list_title p").html(data.title);
				var html ="";
				var floor = 1;
				$.each(data.content, function(i,item) {
					html = html +  getDom(item,floor,listdata.page);
					floor++;
				});
				$("#content_wrap").html(html);
			},
			error:function(){
				
			},
			complete:function(){
				$(".item_time a").click(function(){
					sreply($(this));
				})
			}
		});
	}
	
	function getDom(data,floor,page){
		var data = {
			"id" : data.id,
			"img" : data.img,
			"user" : data.user,
			"time" : data.time,
			"content": data.content,
			"img" : data.img,
			"child" : data.children || "",
			"floor" : floor
		}
		console.log(floor,page,(data.floor-0)+((page-1)*10));
		var html = 	"<li class='item_wrap' id='item"+data.id+"'>"+
					"<div class='item_user'>"+
					"<img src='"+data.img+"'>"+
					"<p>"+data.user+"</p>"+
					"</div>"+
					"<div class='item_cwrap'>"+
					"<div class='item_content'>"+data.content+
					"</div>"+
					"<div class='item_time'>"
		if((data.floor-0)+((page-1)*10) != "1"){
			html = html + "<a>回复</a>";
		}
		html = html+ "<small>"+ ((data.floor-0)+((page-1)*10)) +"楼&nbsp;&nbsp;|&nbsp;&nbsp;"+data.time+"</small>"+
						"</div>"
		if(data.child==""){
			html = html +	"<div class='item_replay' style='display:none'>"+
							"<ul>"+
							"</ul>"+
							"</div>"+
							"</div>"+
							"</li>"
		}else{
			html = html +	"<div class='item_replay'><ul>"
			for(var i = 0; i< data.child.length;i++){
				html = html +	"<li id='user"+data.child[i].id+"' class='replay_detail'>"+
								"<span>"+data.child[i].user+":</span>"+data.child[i].content+
								"<small>"+data.child[i].time+"</small>"+
								"</li>"
			}
			html = html + "</ul></div></div></li>"
		}
		return html;
	}
	
	function sreply(el){
		var li = el.parents(".item_wrap");
		var tar = li.find(".item_replay");
		if(tar.find(".s_reply").length>0){
			tar.find(".s_reply").remove();
			if(tar.children("ul").children("li").length<1){
				tar.css("display","none");
			}
		}else{
			if(tar.css("display")=="none"){
				tar.css("display","block");
			}
			if($(".content_wrap").find(".s_reply").length>0){
				var con = $(".content_wrap").find(".s_reply").parent(".item_replay");
				if(con.children("ul").children("li").length<1){
					con.css("display","none");
				}
				$(".content_wrap").find(".s_reply").remove();
				tar.append(sreply_dom());
			}else{
				tar.append(sreply_dom());
			}
		}
	}
	
	function sreply_dom(){
		return 	"<div class='s_reply'>"+
				"<textarea></textarea>"+
				"<button class='btn btn-success btn-sm' id='sreply'>发表</button>"+
				"</div>"
	}
	
	function seedmess(url,data){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			data:data,
			contentType: false,  
	        processData: false,
	        dataType:"json",
			success:function(data){
				location.reload();
			},
			error:function(){
				
			}
		});
	}
	
	function getQueryVariable(variable){
	    var query = window.location.search.substring(1);
	    var vars = query.split("&");
	    for (var i=0;i<vars.length;i++) {
	        var pair = vars[i].split("=");
	        if(pair[0] == variable){return pair[1];}
	    }
	    return(false);
	}
})
