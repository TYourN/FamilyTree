$(function(){
	
	var honor = {
		id : localStorage.getItem("id"),
		page : 1,
		mess : "",
		byear : "",
		bmonth : "",
		lyear : "",
		lmonth : ""
	}
	
	$(".nav_fallback").on("touchstart",function(){
		$(this).css("background-color","#FFD05B");
	})
	
	$(".nav_fallback").on("touchend",function(){
		$(this).css("background-color","#ffd647");
		if($(this).attr("id")=="nav_fallback"){
			window.location.href="index.jsp";
		}
		if($(this).attr("id")=="nav_search"){
			$(".search_views").animate({"left":"100%"},500);
			setTimeout(function() {
				$(".search_views").css("display", "none");
			}, 500);
		}
	})
	
	$(".nav_more").on("tap",function(){
		if($(".more_wrap").css("display")=="none"){
			$(".more_wrap").css("display","block");
			$(".mask").css("display","block");
		}else{
			$(".more_wrap").css("display","none");
			$(".mask").css("display","none");
		}
	})
	
	$(".mask").on("touchend",function(){
		$(".more_wrap").css("display","none");
		$(".mask").css("display","none");
	})
	
	$("#search").on("tap",function(){
		$(".search_views").css("display","block");
		$(".more_wrap").css("display","none");
		$(".mask").css("display","none");
		$(".search_views").animate({"left":"0"},500);
	})
	
	$(".nav_search").on("tap",function(){
		honor = {
			id : localStorage.getItem("id"),
			page : 1,
			mess : $("#search_mass").val(),
			byear : $("#b_year").val(),
			bmonth : $("#b_month").val(),
			lyear : $("#l_year").val(),
			lmonth : $("#l_month").val()
		}
		
		getdata("../../fhon/findAnFamHon.do",honor,".content_wrap ul");
		
		$(".search_views").animate({"left":"100%"},500);
		setTimeout(function() {
			$(".search_views").css("display", "none");
		}, 500);

		/*重置搜索条件*/
		$("#search_mass").val("");
		$("#b_year").val("#");
		$("#b_month").val("#");
		$("#l_year").val("#");
		$("#l_month").val("#");
	})
	
	function getdata(url,honordata,el){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			data:JSON.stringify(honordata),
			contentType:"application/json;charset=UTF-8",
			dataType:"json",
			success:function(data){
				var html = "";
				$.each(data, function(i,item) {
					html = html + getdom(item);
				});
				if(honordata.page=="1"){
					$(el).html(html);	
				}else{
					$(el).append(html);	
				}
			},
			error:function(data){
				
			}
		});
	}
	
	getdata("../../fhon/findAnFamHon.do",honor,".content_wrap ul");
	
	function getdom(data){
		var data = {
			id : data.id,
			img : data.img,
			title : data.title,
			time : data.time,
			content : data.content  
		}
		return	"<li id='honor"+data.id+"'>"+
				"<img class='honor_img' src='"+data.img+"'>"+
				"<div class='honor_wrap'>"+
				"<p class='honor_title'>"+data.title+"</p>"+
				"<p class='honor_time'>"+data.time+"</p>"+
				"<p class='honor_content'>"+data.content+"</p>"+
				"</div>"+
				"</li>"
	}
	
	for(var i = new Date().getYear()+1900;i>1900;i--){
		$("#b_year").append("<option value='"+i+"'>"+i+"</option>");
		$("#l_year").append("<option value='"+i+"'>"+i+"</option>");
	}
	for(var i = 1;i<=12;i++){
		$("#b_month").append("<option value='"+i+"'>"+i+"</option>");
		$("#l_month").append("<option value='"+i+"'>"+i+"</option>");
	}
	
	$(".content_wrap ul").load_refresh({
		targetdiv:".content_wrap ul",
		containerdiv:".content_wrap",
		dataload:function(){
			honor.page = "1";
			getdata("../../fhon/findAnFamHon.do",honor,".content_wrap ul");
		},
		datarefresh:function(){
			honor.page += 1;
			getdata("../../fhon/findAnFamHon.do",honor,".content_wrap ul");
		}
	})
})
