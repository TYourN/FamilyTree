$(function(){
	
	if(!localStorage.getItem("id")&&localStorage.getItem("id")==""){
		window.location.href = "Homepage.jsp";
	}
	
	$.ajax({
		type:"get",
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
	
	$(".user_core").click(function() {
		var parent = $(this).parents(".user_wrap");
		var id = parent.attr("id").match(/[1-9][0-9]*/g);
		var url = "pcenter.jsp?id=" + id
		window.open(url);
	})
	
	var indexdata = {
		id:localStorage.getItem("id"),
		itemid : getQueryVariable("itemid")
	}
	
	$.ajax({
		type:"post",
		url:"../../../fnews/findShNewsDety.do",
		async:true,
		contentType:"application/json;charset=UTF-8",
		data: JSON.stringify(indexdata),
		dataTpye: "json",
		success:function(data){
			$(".con_title").html("<p>"+data.title+"</p><small>"+data.time+"</small>");
			$(".con_con").html(data.content);
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
