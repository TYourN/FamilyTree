$(function(){
	
	$.ajax({
		type:"post",
		url:"../../../fnews/findShFamNews.do?id="+localStorage.getItem("id"),
		async:true,
		success:function(data){
			var html = "";
			$.each(data, function(i,item) {
				html = html + getdom(item); 
			});
			$(".right_wrap").children("ul").append(html);
		},
		error:function(data){
			alert("资讯获取失败");
		},
		complete:function(){
			$(".right_wrap").on("click","li",function(){
				var id = $(this).attr("id").match(/[1-9][0-9]*/g);
				window.open("newsindex.jsp?itemid="+id);
			})
		}
	});
	
	function getdom(data){
		var dom = {
			id : data.newsid,
			title : data.newstitle,
			img : data.newsimg,
			time : data.newstime
		}
		return 	"<li id='news"+dom.id+"'>"+
				"<div class='news_wrap'>"+
				"<div class='news_img'>"+
				"<img src='"+dom.img+"'/>	"+				
				"</div>"+
				"<div class='news_title'>"+
				"<p>"+dom.title+"</p>"+
				"<small>"+dom.time+"</small>"+
				"</div>"+
				"</div>"+
				"</li>"
	}
})
