$(function() {

	$(".nav_fallback").on("touchend", function() {
		if($(this).attr("id") == "nav_fallback") {
			window.location.href = "index.jsp";
		}
		if($(this).attr("id") == "set_fallback") {
			hideviews("#set_views");
		}
		if($(this).attr("id") == "user_fallback") {
			hideviews("#user_set");
			$("#user").parent().children("label").css("color", "#000000");
		}
		if($(this).attr("id") == "pwd_fallback") {
			$("#oldpwd").val("");
			$("#newpwd").val("");
			$("#spwd").val("");
			$("#pwd_tips").html("");
			hideviews("#pwd_set");
		}
		if($(this).attr("id") == "cur_fallback") {
			hideviews("#currency_set");
		}
	})

	$(".nav_set").on("tap", function() {
		showviews("#set_views");
	})

	$("#us").on("tap", function() {
		showviews("#user_set");
		getmess("../../fuser/findAnPersonById.do?id=",localStorage.getItem("id"));
	})

	$("#pwds").on("tap", function() {
		showviews("#pwd_set");
	})

	$("#cs").on("tap", function() {
		showviews("#currency_set");
	})

	$("#mess_sure").on("tap", function() {
		if($.trim($("#user").val()) != "") {
			var messdata = {
				id:localStorage.getItem("id"),
				username : 	$("#user").val(),
				age : $("#age").val(),
				address : $("#address").val(),
				phone : $("phone").val(),
				ms : $("#ms").text()
			}
			mess_sure("../../fuser/updateCongInfo.do", messdata);
		} else {
			$("#user").parent().children("label").css("color", "#dd524d");
		}
	})

	$("#pwd_sure").on("tap", function() {
		if($("#oldpwd").val() != "" && $("#newpwd").val() != "" && $("#spwd").val() != "") {
			if($("#newpwd").val() == $("#spwd").val()) {
				var message={
					id:	localStorage.getItem("id"),
					newpassword:$("#newpwd").val()
				}
				spwd("../../fuser/updatePassword.do",message);
			} else {
				$("#pwd_tips").html("密码不一致");
			}
		} else {
			$("#pwd_tips").html("表单尚未填写完毕");
		}

	})

	$("#per_bg").on("tap", function() {
		$("#img_sel").trigger("click");
	})
	
	$(".nav_mess_wrap img").on("tap",function(){
		$("#userhead").trigger("click");
	})
	
	$("#userhead").change(function(){
		$("#userid").val(localStorage.getItem("id"));
		var reader = new FileReader();
		var f = $("#userhead")[0].files[0];
		reader.readAsDataURL(f);
		reader.onload = function(e) {
			var formData = new FormData();
			formData.append("id",localStorage.getItem("id"));
			formData.append("img",f);
			changeuserimg("../../fuser/udatePic.do",formData);
		}
	})
	
	$("#img_sel").change(function() {
		var reader = new FileReader();
		var f = $("#img_sel")[0].files[0];
		reader.readAsDataURL(f);
		reader.onload = function(e) {
			$(".nav_bg").html("<img src='" + this.result + "' data-adaptive-background='1'>");
			bg_color();
		}
	})
	
	$(".unlogin").on("tap",function(){
		localStorage.setItem("id","");
		location.href="login.jsp";
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

	function navimg(urla) {
		$.ajax({
			type: "get",
			url: urla,
			async: true,
			dataType: "json",
			success: function(data) {
				$(".nav_bg").html("<img src='" + data.bg + "' data-adaptive-background='1'>");
			},
			complete: function() {
				bg_color();
			}
		});
	}

	function Colorchange(OldColor) {
		var OldColorValue = OldColor.replace(/[a-zA-Z\)\(]*/g, "").split(",");
		var red = OldColorValue[0];
		var green = OldColorValue[1];
		var blue = OldColorValue[2];
		var newc = "rgb(" + red + "," + green + "," + blue + ")";
		return newc;
	}

	function person(url, data) {
		$.ajax({
			type: "post",
			url: url+data,
			async: true,
			contentType:"application/json;charset=UTF-8",
			dataTpye: "json",
			success: function(data) {
				$(".nav_mess_wrap").children("form").children("img").attr("src", data.img || "img/de_user.jpg");
				$(".nav_mess_wrap").children("p").html(data.username);
				getmessdom($("#username"), data.username || "尚未填写");
				getmessdom($("#userage"), data.userage || "尚未填写");
				getmessdom($("#usergender"), data.usergender || "尚未填写");
				getmessdom($("#useraddress"), data.useraddress || "尚未填写");
				getmessdom($("#userphone"), data.userphone || "尚未填写");
				getmessdom($("#userms"), data.userms || "尚未填写");
			},
			error: function() {

			}
		});
	}

	function getmessdom(el, data) {
		el.append("<p>" + data + "</p>");
	}

	function getmess(url, data) {
		$.ajax({
			type: "post",
			url: url+data,
			async: true,
			contentType:"application/json;charset=UTF-8",
			dataType: "json",
			success: function(data) {
				$("#user").val(data.username);
				$("#age").val(data.userage);
				$("#sex").html(data.usergender || "尚未填写");
				$("#address").val(data.useraddress);
				$("#phone").val(data.userphone);
				$("#ms").html(data.userms || "尚未填写");
			},
			complete: function() {

				$("#sel_sex").on("tap", function() {
					if($("#sex").html() == "尚未填写" || $("#sex").html() == "女") {
						$("#sex").html("男");
					} else {
						$("#sex").html("女");
					}
				})

				$("#sel_ms").on("tap", function() {
					if($("#ms").html() == "尚未填写" || $("#ms").html() == "已婚") {
						$("#ms").html("未婚");
					} else {
						$("#ms").html("已婚");
					}
				})
			}
		});
	}

	function mess_sure(url, data) {
		$.ajax({
			type: "post",
			url: url,
			async: true,
			contentType:"application/json;charset=UTF-8",
			data: JSON.stringify(data),
			dataTpye: "json",
			success: function(data) {
				$("#user").parent().children("label").css("color", "#000000");
				hideviews("#user_set");
			}
		});
	}

	function spwd(url, data) {
		$.ajax({
			type: "post",
			url: url,
			async: true,
			data: JSON.stringify(data),
			contentType:"application/json;charset=UTF-8",
			dataType: "json",
			success: function(data) {
				$("#pwd_tips").html("");
				hideviews("#pwd_set");
			},
			error: function() {

			}
		});
	}

	function bg_color() {
		$.adaptiveBackground.run({
			success: function($img, data) {
				var color = data.color;
				var ncolor = Colorchange(color);
				$(".content_wrap").css({ "background": "linear-gradient(" + color + ",rgb(255,255,255)" });
				//				   	150,231,252
			}
		});
	}
	
	function changeuserimg(url,data){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			data:data,
	        contentType: false,  
	        processData: false,
	        dataType:"json",
			success:function(data){
				$(".nav_mess_wrap img").attr("src",data.url);
			}
		});
	}
	person("../../fuser/findAnPersonById.do?id=",localStorage.getItem("id"));
	navimg("json/person_bg.json");
})