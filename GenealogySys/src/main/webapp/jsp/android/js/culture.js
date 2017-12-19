$(function(){
	
	$(".nav_fallback").on("tap",function(){
		if($(this).attr("id")=="nav_fallback"){
			window.location.href="index.jsp";
		}else if($(this).attr("id")=="other_fallback"){
			$(".title_wrap").children().remove();
			$(".con_wrap").children().remove();
			hideviews(".other_views");
		}
	})
	
	$(".culture_item").on("tap",function(){
		var data = {
			"id":localStorage.getItem("id"),
			"type":""
		}
		if($(this).hasClass("origin")){
			$("#otherwrap").css("background-color","#453246");
			$("#navtitle").html("起源");
			data.type = "起源";
		}else if($(this).hasClass("teach")){
			$("#otherwrap").css("background-color","#F86254");
			$("#navtitle").html("祖训");
			data.type = "祖训";
		}else if($(this).hasClass("celebrity")){
			$("#otherwrap").css("background-color","#B04D5D");
			$("#navtitle").html("名人名事");
			data.type = "名人名事";
		}else if($(this).hasClass("hs")){
			$("#otherwrap").css("background-color","#FFD05B");
			$("#navtitle").html("古迹");
			data.type = "古迹";
		}
		showviews(".other_views");
		getdom("../../fcul/findAnCul.do",data);
	})
	
	
	
	function showviews(el) {
		$(el).css("display", "block");
		$(el).animate({ "left": "0" }, 500);
	}

	function hideviews(el) {
		$(el).animate({ "left": "100%" }, 500);
		setTimeout(function() {
			$(el).css("display", "none");
		}, 500);
	}
	
	function getdom(url,data){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			data:JSON.stringify(data),
			contentType:"application/json;charset=UTF-8",
			dataType:"json",
			success:function(data){
				$(".title_wrap").html("<p>"+data.title+"</p>");
				$(".title_wrap").html("<small>"+data.date+"</small>");
				$(".con_wrap").html(data.content);
			},
			error:function(){
				
			}
		});
	}
})
