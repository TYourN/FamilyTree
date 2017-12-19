$(function(){
	
	var listdata = {
		"id":localStorage.getItem("id"),
		"page":1
	}
	
	var objR = new Object;
	
	$(".tcdPageCode").on("click","a",function(){
		if($(this).hasClass("prevPage")){
			listdata.page = $(".tcdPageCode").find(".current").html() - 1;
		}
		else if($(this).hasClass("nextPage")){
			listdata.page = $(".tcdPageCode").find(".current").html() + 1;
		}else{
			listdata.page = $(this).html();
		}
		getlist("../../../fposts/findShTieInfo.do",listdata);
	})
	
	$(".simg_wrap").hover(function(){
		$(".imgwrap").css("display","block");
	},function(){
		$(".imgwrap").css("display","none");
	})
	
	$("#sel_btn").click(function(){
		$("#img_sel").trigger("click");
	})
	
	$(".img_sel").on("click","p",function(){
		delete objR[$(this).parents(".sel_img").attr("id")];
		$(this).parents(".sel_img").remove();
	})
	
	$("#img_sel").change(function(){
		var reader = new FileReader();
		var f = $("#img_sel")[0].files[0];
		reader.readAsDataURL(f);
		var time = Date.parse(new Date());
		reader.onload = function(e){
			$(".img_sel ul").append("<li id='img"+time+"' class='sel_img'><img src='" + this.result + "'><p>删除</p></li>");
			objR["img"+time] = f;
		}
		$("#img_sel").val("");
	})
	
	$("#rep_btn").click(function(){
		var fsize = $("#fontsize").val();
		var fstyle = $("#fontstyle").val();
		var con = "<p style='font-size:"+fsize+";font-family:"+fstyle+"'>"+$("#post_content").val()+"</p>";
		var img_ = new Array();
			for(prop in objR){
				img_.push(objR[prop]);
			}
		/*var repdata = {
			id : localStorage.getItem("id"),
			title : $("#inputtitle").val(),
			content : con,
			img_ : img_,
			time : new Date()
		}*/
		var formData = new FormData();	
		formData.append("id",localStorage.getItem("id"));
		formData.append("title",$("#inputtitle").val());
		formData.append("content",con);
		for (var i = 0; i < img_.length; i++) {
			formData.append("img", img_[i]);
		}
		rep("../../../fposts/addShShuiTie.do",formData);
	})
	
	getlist("../../../fposts/findShTieInfo.do",listdata);
	function getlist(url,data){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			data:JSON.stringify(data),
			contentType:"application/json;charset=UTF-8",
			dataType:"json",
			success:function(data){
				if($(".tcdPageCode").data("already")=="unfinished"){
					$(".tcdPageCode").createPage({
				        pageCount:data.page,
				        current:1,
				        backFn:function(p){}
			    	});
			    	$(".tcdPageCode").data("already","finished");
				}
				var list = "";
				$.each(data.content, function(i,item) {
					list = list + getforumlist(item);
				});
				$(".forum_wrap").html(list);
			},
			error:function(data){
				alert("论坛已关闭")
			},
			complete:function(){
				$(".forum_wrap").on("click",".item_title",function(){
					var itemid = $(this).parents("li").attr("id").match(/[1-9][0-9]*/g);
					window.open("forum_item.jsp?itemid="+itemid);
				})
			}
		});
	}
	
	function getforumlist(data){
		var list = {
			id:data.id,
			number : data.number,
			title : data.title,
			firstname : data.firstname,
			firsttime : data.firsttime,
			lastname : data.lastname,
			lasttime : data.lasttime
		}
		return 	"<li id='item"+list.id+"'>"+
    			"<div class='item_wrap'>"+
	    		"<div class='fmitem item_number'>"+
	    		"<p>"+list.number+"</p>"+
	    		"</div>"+
	    		"<div class='fmitem item_title'>"+
	    		"<p>"+list.title+"</p>"+
	    		"</div>"+
	    		"<div class='fmitem item_reply'>"+
	    		"<p>"+list.firstname+"</p>"+
	    		"<small>"+list.firsttime+"</small>"+
	    		"</div>"+
	    		"<div class='fmitem item_reply'>"+
	    		"<p>"+list.lastname+"</p>"+
	    		"<small>"+list.lasttime+"</small>"+
	    		"</div>"+
    			"</div>"+
    			"</li>"
	}
	
	function rep(url,data){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			data:data,
			contentType: false,  
	        processData: false,
	        dataType:"json",
			success:function(data){
				location.reload();
			}
		});
	}
})
