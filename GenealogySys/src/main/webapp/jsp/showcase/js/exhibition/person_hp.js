$(function() {
	
	if(!localStorage.getItem("id")||localStorage.getItem("id")==""){
		window.location.href = "Homepage.jsp";
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
	
	$.ajax({
		type: "get",
		url: "../json/modular.json",
		async: true,
		success: function(data) {
			var html = "";
			$.each(data, function(i, item) {
				html = html + getmodular(item.title, item.attr);
			});
			$(".top_frame ul").append(html);
		},
		error: function(data) {
			alert("获取模块失败");
		},
		complete: function(data) {
			$(".top_frame ul li").eq(0).children("a").addClass("mo_active");
			$(".bottom_frame").load($(".top_frame ul li").eq(0).children("a").attr("href").substr(1) + ".jsp #right_wrap");
			s1.href = "../css/exhibition/" + $(".top_frame ul li").eq(0).children("a").attr("href").substr(1) + ".css";
			$.getScript("../js/exhibition/" + $(".top_frame ul li").eq(0).children("a").attr("href").substr(1) + ".js");
			$(".top_frame ul").on("click", "a", function() {
				$(".bottom_frame").load($(this).attr("href").substr(1) + ".jsp #right_wrap");
				s1.href = "../css/exhibition/" + $(this).attr("href").substr(1) + ".css";
				$.getScript("../js/exhibition/" + $(this).attr("href").substr(1) + ".js");
				$(".top_frame ul").find(".mo_active").removeClass("mo_active");
				$(this).addClass("mo_active");
			})

			var lf = {
				height: $(".top_frame").height(),
				wheight: window.screen.height
			}

		}
	});
	//	}

	function getmodular(title, attr) {
		return "<li><a href='#" + attr + "'>" + title + "</a></li>"
	}

	$("#userinfo").click(function() {
		window.open("pcenter.jsp");
	})
	
	$("#unlogin").click(function() {
		localStorage.setItem("id","");
		window.location.href = "Homepage.jsp";
	})
	
	$("#manage").click(function(){
		window.open("../../login.jsp");
	})
})