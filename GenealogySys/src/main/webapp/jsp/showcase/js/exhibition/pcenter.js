$(function(){
	
	var img = "";
	
	$(".img_wrap").hover(function(){
		$(".img_wrap").children("a").animate({"top":"110px"},100);
	},function(){
		$(".img_wrap").children("a").animate({"top":"150px"},100);
	})
	
	$(".img_wrap a").click(function(){
		$(".mask").css("display","block");
		$(".img_change").css("display","block");
		$(".i_img_item").html("<img src='"+$(".img_wrap").children("img").attr("src")+"'>");
		var wid = $(".img_change").css("width");
		$(".img_change").css("left",getleft(wid));
	})
	
	$("#i_title .fui-cross").click(function(){
		$(".mask").css("display","none");
		$(".img_change").css("display","none");
	})
	
	$("#img_cencel").click(function(){
		$(".mask").css("display","none");
		$(".img_change").css("display","none");
	})
	
	$(".useredit").click(function(){
		$(".mask").css("display","block");
		$("#message_change").css("display","block");
		var wid = $("#message_change").css("width");
		$("#message_change").css("left",getleft(wid));
		var ue = {
			username : gethtml($("#username")),
			userage : gethtml($("#userage")),
			usergender : gethtml($("#usergender")),
			useraddress : gethtml($("#useraddress")),
			userphone : gethtml($("#userphone")),
			userms : gethtml($("#userms")),
		}
		$("#inputname").val(ue.username);
		$("#inputage").val(ue.userage);
		if(ue.usergender == "男"){
			$("#male").attr("checked","true");
		}else{
			$("#female").attr("checked","true");
		}
		$("#inputplace").val(ue.useraddress);
		$("#inputphone").val(ue.userphone);
		if(ue.userms == "未婚"){
			$("#unmarried").attr("checked","true");
		}else{
			$("#married").attr("checked","true");
		}
	})
	
	$("#m_title .fui-cross").click(function(){
		$(".mask").css("display","none");
		$("#message_change").css("display","none");
	})
	
	$("#mess_cencel").click(function(){
		$(".mask").css("display","none");
		$("#message_change").css("display","none");
	})
	
	$(".change_wrap a").click(function(){
		 $("#img_sel").trigger("click");
	})
	
	$("#img_sel").change(function(){
		var reader = new FileReader();
		var f = $("#img_sel")[0].files[0];
		reader.readAsDataURL(f);
		img = f;
		reader.onload = function(e){
			$(".i_img_item img").attr("src",this.result);
		}
		$("#img_sure").removeAttr("disabled");
		$("#img_sel").val("");
	})
	
	$("#img_sure").click(function(){
		var formData = new FormData();
		formData.append("id",localStorage.getItem("id"));
		formData.append("img",img);
		$.ajax({
			type:"post",
			url:"../../../fuser/updateShPic.do",
			async:true,
			data:formData,
			contentType: false,  
	        processData: false,
	        dataType:"json",
			success:function(data){
				$(".mask").css("display","none");
				$(".img_change").css("display","none");
			},
			error:function(data){
				
			}
		});
	})
	
	$("#changepwd").click(function(){
		$("#message_change").css("display","none");
		var wid = $("#c_pwd").css("width");
		$("#c_pwd").css("left",getleft(wid));
		$("#c_pwd").css("display","block");
	})
	
	$("#pwd_title .fui-cross").click(function(){
		$("#c_pwd").css("display","none");
		$("#message_change").css("display","block");
		$("#inputoldpwd").val("");
		$("#inputnewpwd").val("");
		$("#inputsurepwd").val("");
		$(".c_pwd_tip").html("");
		$("#pwd_sure").attr("disabled","disabled");
	})
	
	$("#pwd_cencel").click(function(){
		$("#c_pwd").css("display","none");
		$("#message_change").css("display","block");
		$("#inputoldpwd").val("");
		$("#inputnewpwd").val("");
		$("#inputsurepwd").val("");
		$(".c_pwd_tip").html("");
		$("#pwd_sure").attr("disabled","disabled");
	})
	
	function getleft(wid){
		var wwid = document.body.clientWidth;
		var wid = parseFloat(wid);
		return (wwid - wid) / 2;
	}
	
	function gethtml(el){
		return el.children(".mess").children("p").html();
	}
	
	$.ajax({
		type:"get",
		url:"../../../fuser/findShPersonById.do",
		async:true,
		data:{"id":localStorage.getItem("id")},
		success:function(data){
			$(".img_wrap").children("img").attr("src",data.userhp);
			getmessdom($("#username"),data.username);
			getmessdom($("#userage"),data.userage);
			getmessdom($("#usergender"),data.usergender);
			getmessdom($("#useraddress"),data.useraddress);
			getmessdom($("#userphone"),data.userphone);
			getmessdom($("#userms"),data.userms);
		},
		error:function(data){
			alert("获取个人信息失败");
		}
	
	});
	
	function getmessdom(el,data){
		el.find("p").html(data);
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
	
	$("#inputoldpwd,#inputnewpwd,#inputsurepwd").change(function(){
		if(($("#inputoldpwd").val()!="")&&($("#inputnewpwd").val()!="")&&($("#inputsurepwd").val()!="")){
			$("#pwd_sure").removeAttr("disabled");
		}else{
			$("#pwd_sure").attr("disabled","disabled");
		}
	})
	
	$("#pwd_sure").click(function(){
		if($("#inputnewpwd").val()!=$("#inputsurepwd").val()){
			$(".c_pwd_tip").html("两次输入密码不一致");
		}else{
			var content={
				id:localStorage.getItem("id"),
				pwd:$("#inputoldpwd").val()
			}
			$.ajax({
				type:"post",
				url:"../../../fuser/updateShPassword.do",
				async:true,
				contentType:"application/json;charset=UTF-8",
				data: JSON.stringify(content),
				dataTpye: "json",
				success:function(data){
					
				},
				error:function(){
					
				}
			});
		}
	})
	
	$("#mess_sure").click(function(){
		if($.trim($("#inputname").val())!=""){
			var messdata = {
					id:localStorage.getItem("id"),
					name : $.trim($("#inputname").val()),
					age : $("#inputage").val(),
					sex : $("input[name='sex']:checked").val(),
					address : $("#inputplace").val(),
					phone : $("#inputphone").val(),
					ms : $("input[name='Marriage']:checked").val()
			}
			$.ajax({
				type:"post",
				url:"../../../fuser/updateShCongInfo.do",
				async:true,
				contentType:"application/json;charset=UTF-8",
				data: JSON.stringify(messdata),
				dataTpye: "json",
				success:function(data){
					$(".mask").css("display","none");
					$("#message_change").css("display","none");
				},
				error:function(){
					
				}
			});
		}
	})
})
