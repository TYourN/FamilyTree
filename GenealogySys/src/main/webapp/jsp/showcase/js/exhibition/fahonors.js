$(function(){
	
	var honordata = {
		id : localStorage.getItem("id"),
		page : 1,
		mess : "",
		byear : "",
		bmonth : "",
		lyear : "",
		lmonth : ""
	}
	
	var i_height = 0;
	
	function getmess(url,honordata){
		$.ajax({
			type:"post",
			url:url,
			async:true,
			contentType:"application/json;charset=UTF-8",
			data:JSON.stringify(honordata),
			dataType:"json",
			success:function(data){
				var html = "";
				$.each(data, function(i,item) {
					html = html + getdiv(item,i,i_height);
				});
				i_height = i_height + 630;
				if(honordata.page == 1){
					$(".img_wrap").html(html);
				}else{
					$(".img_wrap").append(html);		
				}
			}
		});	
	}
	
	getmess("../../../fhon/findShFamHon.do",honordata);
	
	function ran(){
		if(Math.random()>=0.5){
		return parseInt(Math.random()*15);
		}else{
		return parseInt(Math.random()*15*(-1));
		}
	}
	
	function getdiv(data,i,j){
		return 	"<div id='img"+data.id+"' class='photo_wrap' style='transform: rotate("+ran()+"deg);top:"+gettop(i,j)+";left:"+getleft(i)+";'>"+
				"<div class='img_mask'></div>"+
				"<img src="+data.url+">"+
				"</div>"
	}

	function getleft(i){
		return i % 3 * 280 + "px"
	}
	
	function gettop(i,j){
		return parseInt(i / 3) * 210 + j + "px"
	}
	
	var anima = {
		deg:"",
		left:"",
	}
	$(".img_wrap").on("mouseover",".img_mask",function(){
		var parent = $(this).parent(".photo_wrap");
		parent.css("z-index","1");
		anima.left = getpositon(parent);
		anima.deg = getrotate(parent.css("transform"));
		parent.animate({
			"width" : "560px",
			"height" : "420px",
			"left" : anima.left.newleft
		},1000);
		parent.addClass("anima");
	})
	$(".img_wrap").on("mouseleave",".img_mask",function(){
		var parent = $(this).parent(".photo_wrap");
		parent.stop();
		parent.removeClass("anima");
		parent.css({
			"width":"280px",
			"height":"210px",
			"z-index":"0",
			"left" : anima.left.oldleft
		});
		parent.find(".describe").remove();
		parent.find("img").css("display","block")
	})
	
	$(".img_wrap").on("click",".img_mask",function(){
		var parent = $(this).parent(".photo_wrap");
		var id = parent.attr("id").match(/[1-9][0-9]*/g);
		var idlist={
			id:localStorage.getItem("id"),
			honid:id
		}
		if(parent.find("img").css("display") != "none"){
			$.ajax({
				type:"post",
				url:"../../../fhon/findShHonDety.do",
				async:true,
				data:JSON.stringify(idlist),
				contentType:"application/json;charset=UTF-8",				
				dataType:"json",
				success:function(data){
					parent.find("img").css("display","none");
					parent.append(getwold(data));
				}
			});
		}
	})
	
	function getrotate(data){
		var values = data.split('(')[1].split(')')[0].split(',');
		var a = values[0];
		var b = values[1];
		var c = values[2];
		var d = values[3];
		var angle = Math.round(Math.atan2(b, a) * (180 / Math.PI));
		return angle;
	}

	function getpositon(el){
		var gp = {
			parent : el.parent(".img_wrap"),
			left : el.css("left"),
			index : el.parent(".img_wrap").children(".photo_wrap").index(el) + 1,
			el : el
		}
		var result = {
			oldleft : gp.left,
			newleft : ""
		}
		if(gp.index % 3 == 0){
			result.newleft = "280px";
		}else{
			result.newleft = gp.left;
		}
		return result;
	}	
	
	function getwold(data){
		var data = {
			title : data.title,
			content : data.content
		}
		return 	"<div class='describe'>"+
				"<h4>"+data.title+"</h4>"+
				data.content+
				"</div>"
	}
	
	for(var i = new Date().getYear()+1900;i>1900;i--){
		$("#b_year").append("<option value='"+i+"'>"+i+"</option>");
		$("#l_year").append("<option value='"+i+"'>"+i+"</option>");
	}
	for(var i = 1;i<=12;i++){
		$("#b_month").append("<option value='"+i+"'>"+i+"</option>");
		$("#l_month").append("<option value='"+i+"'>"+i+"</option>");
	}
	
	$("#sel").click(function(){
		honordata.mess = $("#sel_title").val()||"";
		honordata.byear = $("#b_year").val()||"";
		honordata.bmonth = $("#b_month").val()||"";
		honordata.lyear = $("#l_year").val()||"";
		honordata.lmonth = $("#l_month").val()||"";
		honordata.page = 1;
		i_height = 0;
		getmess("../../../fhon/findShFamHon.do",honordata);
	})
	
	$(window).scroll(function(){
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(this).height();
		if(scrollTop + windowHeight == scrollHeight){
			honordata.page = honordata.page + 1;
			getmess("../../../fhon/findShFamHon.do",honordata);
		}
	});
	
})