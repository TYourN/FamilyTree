$(function(){
	
	$("#nav_fallback").on("tap",function(){
		window.location.href="index.jsp";
	})
	
	$("#username").blur(function(){
		if($.trim($("#username").val())!=""){
			getuserimg("../../fuser/getUserImg.do?username=",$("#username").val(),".img_wrap img");
		}
	})
	
	$(".btn_sub").on("tap",function(){
		if($.trim($("#username").val())!=""&&$("userpwd").val()!=""){
			var data = {
				"username" : $.trim($("#username").val()),
				"userpwd" : $("#userpwd").val()
			}
			login("../../fuser/anUserLogin.do",data);
		}
	})
	
	function getuserimg(url,data,el){
		$.ajax({
			type:"get",
			url:url+data,
			async:true,
			contentType:"application/json;charset=UTF-8",
			dataType:"json",
			success:function(data){
				if(data.img!=""){
					$(el).attr("src",data.img);	
				}
			},
			error:function(){
				
			}
		});
	}
	
	function login(url,data){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			contentType:"application/json;charset=UTF-8",
			data:JSON.stringify(data),
			dataType:"json",
			success:function(data){
				console.log(data);
				if(data.id&&data.id!=""){
					localStorage.setItem("id",data.id);
					window.location.href="index.jsp";
				}else{
					$("#username").css("border-color","#E74C3C");
					$("#userpwd").css("border-color","#E74C3C");
				}
			}
		});
	}
})
