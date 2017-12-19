$(function(){
	
	var news = {
		wid : window.screen.width,
		whe : window.screen.height
	}
	
	$(".back").click(function(){
		location.href="Homepage.jsp";
	})
	
	$(".login_bra").css("left",lef(news.wid));
	$(".gre").css({
		"height" : news.whe,
		"width" : news.wid
	});
	
	
	function lef(wid){
		return (wid - 393)/2;
	}
	
	$("#login").click(function(){
		$(".gre").css("display","block");
		$(".login_bra").css("display","block");
		$(".login_bra").animate({"opacity":"1"},200);
	})
	
	$(".fui-cross").click(function(){
		$(".gre").css("display","none");
		$(".login_bra").animate({"opacity":"0"},200);
		$(".login_bra").delay(200).css("display","none");
	})
	var page = 1;
	function getlist(url,data){
		$.ajax({
			type:"get",
			url:url,
			async:true,
			data:data,
			success:function(data){
				if($(".tcdPageCode").data("already")=="unfinished"){
					$(".tcdPageCode").createPage({
				        pageCount:data.page,
				        current:1,
				        backFn:function(p){}
			    	});
			    	$(".tcdPageCode").data("already","finished");
				}
				var html = "";
				$.each(data.content,function(i,item){
					html += getdom(item);
				})
				$(".list ul").html(html);
			},
			complete:function(){
				$(".list ul").on("click","li",function(){
					window.open("index.jsp?type=news&&itemid="+$(this).attr("id").match(/[1-9][0-9]*/g));
				})
			}
		});
	}
	getlist("",page);
	
	function getdom(data){
		var data = {
			id : data.id,
			time : data.time,
			title : data.title,
			scontent : data.scontent
		}
		return 	"<li id='news"+data.id+"'><span>"+data.time+"</span> <a>"+data.title+"</a>"+
				"<p>"+data.scontent+"</p></li>"
	}
	
	$(".tcdPageCode").on("click","a",function(){
		if($(this).hasClass("prevPage")){
			page = $(".tcdPageCode").find(".current").html() - 1;
		}
		else if($(this).hasClass("nextPage")){
			page = $(".tcdPageCode").find(".current").html() + 1;
		}else{
			page = $(this).html();
		}
		getlist("",page);
	})
})
