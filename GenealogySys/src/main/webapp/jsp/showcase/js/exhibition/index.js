$(function(){
	
	var in_ = {
		wid : window.screen.width,
		whe : window.screen.height
	}
	
	$(".back").click(function(){
		location.href="Homepage.jsp";
	})
	
	var indexdata = {
		itemid :  getQueryVariable("itemid"),
		itemtype : getQueryVariable("itemtype")
	}
	console.log(indexdata);
	if(indexdata.itemtype=="fm"){
		var fmdata = UnicodeToUtf8(indexdata.itemid.split(","));
		$.ajax({
			type:"post",
			url:"../../../fnames/findNameDety.do?title="+fmdata,
			async:true,
			dataType:"json",
			success:function(data){
				var lo_map = "当前位置：";
				$("#title").html(data.title);
				$("#date").html(data.memo);
				$(".content_con").html(data.content);
				$(".now_lo_wrap span").append("<a>首页</a>><a>百家姓</a>>"+data.title);
			}
		});
	}else if(indexdata.itemtype=="news"){
		$.ajax({
			type:"post",
			url:"../../../fnews/findNewDety.do?newsid="+indexdata.itemid,
			async:true,
			dataType:"json",
			success:function(data){
				var lo_map = "当前位置：";
				$("#title").html(data.title);
				$("#date").html(data.time);
				$(".content_con").html(data.content);
				$(".now_lo_wrap span").append("<a>首页</a>><a>新闻中心</a>>"+data.title);
			}
		});
	}
	
	
	$(".login_bra").css("left",lef(in_.wid));
	$(".gre").css({
		"height" : in_.whe,
		"width" : in_.wid
	});
	
	
	function lef(wid){
		return (wid - 393)/2;
	}
	
	$("#login").click(function(){
		$(".gre").css("display","block");
		$(".login_bra").css("display","block");
		$(".login_bra").animate({"opacity":"1"},200);
	})
	
	$(".fui-cross").click(function(){
		$(".gre").css("display","none");
		$(".login_bra").animate({"opacity":"0"},200);
		$(".login_bra").delay(200).css("display","none");
	})
	
	function getQueryVariable(variable){
	    var query = window.location.search.substring(1);
	    var vars = query.split("&");
	    for (var i=0;i<vars.length;i++) {
	        var pair = vars[i].split("=");
	        if(pair[0] == variable){return pair[1];}
	    }
	    return(false);
	}
	
	function UnicodeToUtf8(unicode) { 
		  var uchar; 
		  var utf8str = ""; 
		  var i; 
		  for(i=0; i<unicode.length;i+=2){      
		    uchar = (unicode[i]<<8) | unicode[i+1];        //UNICODE为2字节编码，一次读入2个字节 
		    utf8str = utf8str + String.fromCharCode(uchar);  //使用String.fromCharCode强制转换 
		  } 
		  return utf8str; 
		} 
})
