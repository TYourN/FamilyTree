$(function(){
	
	$(".nav_fallback").on("touchend", function() {
		if($(this).attr("id") == "nav_fallback") {
			window.location.href = "index.jsp";
		}
	})
	var idlist={
		id:localStorage.getItem("id"),
		newsid:getQueryVariable("id")
	}
	
	$.ajax({
		type:"post",
		url:"../../fnews/findAnNewsDety.do",
		async:true,
		data:JSON.stringify(idlist),
		contentType:"application/json;charset=UTF-8",
		dataType:"json",
		success:function(data){
			$(".title_wrap").append("<p>"+data.title+"</p>");
			$(".title_wrap").append("<small>"+data.date+"</small>");
			$(".con_wrap").append(data.content);
		}
	});
	
	
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
