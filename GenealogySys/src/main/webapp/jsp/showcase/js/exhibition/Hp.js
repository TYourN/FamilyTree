$(function(){
	
	var hp={
		time2:0,
		bgitem_speed:1000,
		intro_speed:200
	}
	
	$("#sub").fullpage({
		verticalCentered:false,
		navigation:true,
		continuousVertical: true,
		menu: '#menu',
		navigationTooltips:["首页","家谱制作","百家姓","新闻"],
		navigationPosition:'left',
		anchors: ['page1', 'page2', 'page3', 'page4'],
		afterLoad:function(anchorLink, index){
			if(index == 2){
				var opc = $(".Ge_intro li");
				var intro = opc.length;
				for(var i = 0 ;i < intro ;i++){
					opc.eq(i).animate({"left":getintro(i,intro)+"px"},hp.intro_speed);
				}
			}
			if(index == 4){
				var sel = {
					nowitem : 0,
					count : $(".new_sec ul>li").length,
					newitem_width:900,
					new_speed_1:500,
					new_speed_2:1000
				}
				$(".new_sec ul li:eq(0) img").css("left","0px");
				$(".new_sec ul li:gt(0) img").css("left",sel.newitem_width);
				$(".new_con").animate({"opacity":"1"},sel.new_speed_1);
				$(".new_sec").animate({"opacity":"1"},sel.new_speed_1);
				setTimeout(function(){
				hp.time2 = setInterval(new_sel,3000);
				},sel.new_speed_1);
				function new_sel(){
						$(".new_sec ul li:eq("+item_cal(sel.nowitem,sel.count)[1]+") img").css("left",sel.newitem_width+"px");
						$(".new_sec ul li:eq("+item_cal(sel.nowitem,sel.count)[0]+") img").animate({"left":sel.newitem_width*(-1)+"px"},sel.new_speed_2);
						$(".new_sec ul li:eq("+item_cal(sel.nowitem,sel.count)[1]+") img").animate({"left":"0px"},sel.new_speed_2);
						sel.nowitem = item_cal(sel.nowitem,sel.count)[1];
				};
				/*随机函数*/
				$(".new_bg1").animate({"top":ran()+10+"px"},500);
				$(".new_bg2").animate({"left":ran()+"px"},1500);
				$(".new_bg3").animate({"top":ran()+500+"px"},1000);
			}
		},
		onLeave:function(index, direction){
			if(index == 2){
				$(".Ge_intro li:eq(1)").animate({"left":"0px"},hp.intro_speed);
				$(".Ge_intro li:eq(2)").animate({"left":"0px"},hp.intro_speed);
				$(".intro_icon").animate({opacity:"0","margin-left":"40px"},hp.intro_speed);
				$(".intro_con").animate({opacity:"0"},hp.intro_speed);
			}
			if(index == 4){
				clearTimeout(hp.time2);
				$(".new_con").css("opacity","0");
				$(".new_sec").css("opacity","0");
				$(".new_bg1").animate({"top":"-500px"},500);
				$(".new_bg2").animate({"left":"-300px"},200);
				$(".new_bg3").animate({"top":"800px"},300);
			}
		}
	});
	
	$.ajax({
		type:"post",
		url:"../../../fhp/findFirstPics.do",
		async:true,
		dataType:"json",
		success:function(data){
			var html = "";
			for(var i = 0; i < data.length; i++){
				html = html + bg(data[i]);
			}
			$(".sel_con").append(html);
		},
		error:function(data){
			alert("获取背景图片失败");
		},
		complete:function(data){
			var bg_sel={
				nowitem : 0,
				count : $(".sel_con>li").length,
				time:null
			}
			bg_sel.time = setInterval(carousel,5000);
			function carousel(){
				$(".sel_img:eq("+item_cal(bg_sel.nowitem,bg_sel.count)[0]+")").animate({opacity:"0"},hp.bgitem_speed);
				$(".sel_img:eq("+item_cal(bg_sel.nowitem,bg_sel.count)[1]+")").animate({opacity:"1"},hp.bgitem_speed);
				bg_sel.nowitem = item_cal(bg_sel.nowitem,bg_sel.count)[1];
			};
		}
	});
	
	function bg(item){
		return "<li><img class='sel_img' src='"+item+"'/></li>";
	}
	
	function ran(){
		var ma = 1;
		if(Math.random()>0.5)ma = -1;
		return Math.random()*100*ma
	}
	
	function getintro(index,all){
		return index * ((980 - all * 100) / (all - 1) + 100);
	}
	
	function item_cal(now,all){
		var item = new Array();
		if(now>=all-1){
			item.push(now);
			item.push(0);
		}else{
			item.push(now);
			item.push(now+1);
		}
		return item;
	}
	
	function appear(el,time1,time2){  //time1:延迟时间，time2：动画时间
		var ap={
			legth : $(el).children().length,
			index:"",
			timeout:"",
		}
		for(index=0;index<ap.legth;index++){
			(function(index){
				$(el + " li:eq(" +index+")").mouseover(function(){
					ap.timeout = setTimeout(function(){
						$(".intro_icon").animate({opacity:"1","margin-left":getintro(index,ap.legth)+ 40 + "px"},time2);
						$(".intro_con").animate({opacity:"1"},time2);
						$(".intro_con ul").animate({"left":-980 * index + "px"},time2)
					},time1);
					}).mouseout(function(index){
						clearTimeout(ap.timeout);
					})
			})(index)
		}
	}
	
	$.ajax({
		type:"get",
		url:"../json/intro.json",
		async:true,
		success:function(data){
//			var title = "";
			var content = "";
			$.each(data, function(i,item) {
//				title = title + intro_inf(item.title.img,item.title.p);
				content = content + intro_con(item.content);
			});
//			$(".Ge_intro ul").append(title);
			$(".intro_con ul").append(content);
		},
		error:function(data){
			alert("获取家谱制作图标失败");
		},
		complete:function(){
				appear(".Ge_intro ul","200","500");
		}
	});
	
//	function intro_inf(img,p){
//		return "<li><img src='" + img +"'/><p>"+ p +"</p></li>"
//	}
	
	function intro_con(data){
		if(data.img.length==1){
			return "<li><p class='intro_con_p'>"+data.p+"</p><img class='intro_con_m1' src='"+data.img[0]+"'/></li>"
		}else{
			return "<li><p class='intro_con_p'>"+data.p+"</p><img class='intro_con_m2' src='"+data.img[0]+"'/><img class='intro_con_m2' src='"+data.img[1]+"'/></li>"
		}
	}
	
	$.ajax({
		type:"post",
		url:"../../../fnews/findNewsTop.do",
		async:true,
		success:function(data){
			var html = "";
			var html_li;
			var img = "";
			var img_li;
			$.each(data, function(i,item) {
				html_li = "<li id=new"+item.id+"><span>"+item.title+"</span><span>"+item.date+"</span></li>" ;
				img_li = "<li><img class='new_img' src='"+item.img+"'/></li>"
				html = html + html_li;
				img = img + img_li;
			});
			$(".new_sec ul").append(img);
			$(".new_con ul").append(html);
		},
		error:function(data){
			alert("获取新闻失败");
		}
	});
	
	$.ajax({
		type:"get",
		url:"../json/information.json",
		async:true,
		success:function(data){
			$.each(data,function(i,item){
				$("#nameTabContent div").eq(parseInt(i/4)).append(bjx(item));
			})
		},
		error:function(data){
			alert("获取百家姓失败");
		}
	});
	
	function bjx(item){
		var html = "<p>"+item.title+"</p>";
		for(var i = 0;i<item.name.length;i++){
			html = html + "<a>" + item.name[i] + "</a>";
		}
		html = html + "</br>";
		return html;
	}
	
	$("#nav_login").click(function(){
		$.fn.fullpage.moveTo(1)
	})
	
	$("#login").click(function(){
		if($("#InputUser").val()!=""&&$("#InputPwd").val()!=""){
			var login = {
					name : $("#InputUser").val(),
					pwd : $("#InputPwd").val()
			}
			$.ajax({
				type:"post",
				url:"../../../fuser/shUserLogin.do",
				async:true,
				data:JSON.stringify(login),
				contentType:"application/json;charset=UTF-8",
				dataType:"json",
				success:function(data){
					if(data.id && data.id!=""){
						localStorage.setItem("id",data.id);
						window.location.href="person_hp.jsp";	
					}
				},
				error:function(data){
					
				}
			});	
		}
	})
	
	$(".new_con").on("click","li",function(){
		var itemid = $(this).attr("id").match(/[1-9][0-9]*/g);
		window.open("index.jsp?itemtype=news&&itemid=" + itemid);
	})
	
	$("#nameTabContent").on("click","a",function(){
		var itemid = Utf8ToUnicode($(this).html());
		window.open("index.jsp?itemtype=fm&&itemid="+itemid);
	})
	
	function Utf8ToUnicode(strUtf8) { 
	  var i,j; 
	  var uCode; 
	  var temp = new Array(); 
	  for(i=0,j=0; i<strUtf8.length; i++){ 
	    uCode = strUtf8.charCodeAt(i); 
	    if(uCode<0x100){         //ASCII字符 
	      temp[j++] = 0x00; 
	      temp[j++] = uCode; 
	    }else if(uCode<0x10000){ 
	      temp[j++] = (uCode>>8)&0xff; 
	      temp[j++] = uCode&0xff; 
	    }else if(uCode<0x1000000){ 
	      temp[j++] = (uCode>>16)&0xff; 
	      temp[j++] = (uCode>>8)&0xff; 
	      temp[j++] = uCode&0xff; 
	    }else if(uCode<0x100000000){ 
	      temp[j++] = (uCode>>24)&0xff; 
	      temp[j++] = (uCode>>16)&0xff; 
	      temp[j++] = (uCode>>8)&0xff; 
	      temp[j++] = uCode&0xff; 
	    }else{ 
	      break; 
	    } 
	  } 
	  temp.length = j; 
	  return temp; 
	} 
})