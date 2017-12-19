$(function() {

	var win = {
		wid: window.screen.width,
		hei: window.screen.height
	}

	//	if(localStorage.indexsroll!=""){
	//	console.log(localStorage.indexsroll);
	//	console.log($(".content_wrap").scrollTop())
	//		$(".content_wrap").scroll(localStorage.indexsroll+"px");
	//	}

	var sroll = {
		length: $(".sroll_img_wrap ul li").length,
		speed: 1000,
		wid: $(".sroll_img_wrap ul li").css("width").match(/[1-9][0-9]*/g),
		item: ".sroll_img_wrap ul li",
		time: 5000,
		nowitem: 0
	}
	
	sroll.time = setInterval(carousel, sroll.time);

	function carousel() {
		$(sroll.item).eq(item_cal(sroll.nowitem, sroll.length)[1]).css("left", sroll.wid + "px");
		$(sroll.item).eq(item_cal(sroll.nowitem, sroll.length)[0]).animate({ "left": (-1) * sroll.wid + "px" }, sroll.speed);
		$(sroll.item).eq(item_cal(sroll.nowitem, sroll.length)[1]).animate({ "left": "0px" }, sroll.speed);
		sroll.nowitem = item_cal(sroll.nowitem, sroll.length)[1];
	};

	function item_cal(now, all) {
		var item = new Array();
		if(now >= all - 1) {
			item.push(now);
			item.push(0);
		} else {
			item.push(now);
			item.push(now + 1);
		}
		return item;
	}

	function item_wid() {
		var wid = parseFloat($(".news_img_wrap").css("width"));
		var img_wid = wid - 20;
		var img_hei = img_wid / 4 * 3;
		$(".news_img_wrap img").css({
			"width": img_wid + "px",
			"height": img_hei + "px"
		})
		var hei = parseFloat($(".news_img_wrap").css("height"));
		var p_hei = parseFloat($(".news_title_wrap").css("height"));
		$(".news_title_wrap").css("padding", (hei - p_hei) / 2 + "px 5px");
		$(".news_item_wrap").css("height", hei + "px");
	}
	
	var newsData = {
		"id":"",
		"page" : 1
	};
	
	if(localStorage.getItem("id") && localStorage.getItem("id") != "") {
		newsData.id = localStorage.getItem("id");
	}else{
		newsData.id = "0"
	}
	
	console.log(newsData);
	
	function getnews(url,newsdata){
		$.ajax({
			type: "post",
			url: url,
			async: true,
			contentType:"application/json;charset=UTF-8",
			data:JSON.stringify(newsdata),
			dataType: "json",
			success: function(data) {
				var html = "";
				$.each(data, function(i, item) {
					html = html + getnewsdom(item);
				});
				if(newsdata.page=="1"){
					$(".news_list").html(html);
					console.log(1);
				}else{
					$(".news_list").append(html);
					console.log(2);
				}
				item_wid();
			},
			error: function(data) {
				$(".modular_news").append(outline());
			},
			complete:function(){
				newsclick();
			}
		});
	}
	
	 getnews("../../fnews/findAnFamNews.do",newsData);
	
	function getnewsdom(data) {
		var item = {
			id: data.id,
			title: data.title,
			img: data.img,
			time: data.time
		}
		return "<li id='news" + item.id + "' class='news_item_wrap'>" +
			"<div class='news_img_wrap'>" +
			"<img src=" + data.img + ">" +
			"</div><div class='news_title_wrap'>" +
			"<p>" + item.title + "</p>" +
			"<small>" + item.time + "</small>" +
			"</div>" +
			"</li>"
	}

	function outline() {
		return "<div class='outline'>" +
			"<img src='img/outline.png'>" +
			"<p>网络异常</p>" +
			"</div>"
	}
	
	function newsclick(){
		$(".news_item_wrap").on("tap",function(){
			var id = $(this).attr("id").match(/[1-9][0-9]*/g);
			window.location.href="news.jsp?id="+id;
		})
	}
	
	/*用户头像*/
	if(localStorage.getItem("id") && localStorage.getItem("id") != "") {
		$.ajax({
			type: "post",
			url: "../../fuser/findAnPersonById.do?id="+localStorage.getItem("id"),
			async: true,
			contentType:"application/json;charset=UTF-8",
			dataType: "json",
			success: function(data) {
				$(".head_portrait img").attr("src",data.img);
			}
		});
	}

	$(".item_ul li").on("touchstart", function() {
		$(this).css("background-color", "#efeff4");
	})
	$(".item_ul li").on("touchend", function() {
		$(this).css("background-color", "#ffffff");
	})
	
	$(".item_ul li").on("tap", function() {
		$(this).css("background-color", "#ffffff");
		if(localStorage.getItem("id") && localStorage.getItem("id") != "") {
				if($(this).attr("id") == "modular_forum") {
					window.location.href = "forum.jsp"
				}
				if($(this).attr("id") == "modular_honor") {
					window.location.href = "honor.jsp"
				}
				if($(this).attr("id") == "modular_cultrue") {
					window.location.href = "culture.jsp"
				}
				if($(this).attr("id") == "modular_tree") {
					window.location.href = "tree.jsp"
				}
		} else {
			window.location.href = "login.jsp"
		}
	})

	$(".head_portrait").on("tap", function() {
		if(localStorage.getItem("id") && localStorage.getItem("id") != "") {
			window.location.href = "personal.jsp"
		} else {
			window.location.href = "login.jsp"
		}
	})
	
	$(".news_list").load_refresh({
		targetdiv:".news_list",
		containerdiv:".content_wrap",
		dataload:function(){
			newsData.page = "1";
			getnews("../../fnews/findAnFamNews.do",newsData);
		},
		datarefresh:function(){
			newsData.page += 1;
			getnews("../../fnews/findAnFamNews.do",newsData);
		}
	})
})