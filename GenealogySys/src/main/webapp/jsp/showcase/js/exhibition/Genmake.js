$(function(){
	
	$("#add_item").click(function() {
		$(".gen_wrap>ul").append(getDom())
	})
	
	$(".gen_wrap").on("click",".in_main",function(){
		var Parent = $(this).parent(".list_item");
		if(Parent.hasClass("op")){
			if($(this).data("sex")=="male"){
				Parent.find(".spouse").remove();
				Parent.find(".in_spouse").remove();
				$(this).css("background-color","#fd367e");
				$(this).html("♀");
				$(this).data("sex","female");
			}else{
				$("<input type='text' placeholder='配偶' class='form-control item_input spouse'/>"+
				"<span class='in_spouse'>♀</span>").insertAfter($(this));
				$(this).css("background-color"," #5ac8d8");
				$(this).html("♂");
				$(this).data("sex","male");
			}
		}
	})
	
	$(".gen_wrap").on("click",".add_i",function(){
		$(this).parent("li").children("ul").append(getDom());
	})
	
	$(".gen_wrap").on("click",".de_i",function(){
		$(".dele_wrap").css("display","block");
		$(".mask").css("display","block");
		$(".dele_wrap").css("left",getleft($(".dele_wrap").css("width")));
		var list = $(this).parent("li");
		$("#dele_sure").one("click",function(){
			list.remove();
			$(".dele_wrap").css("display","none");
			$(".mask").css("display","none");
		})
	})
	
	$(".gen_wrap").on("click",".fui-check-circle",function(){
		var pare = $(this).parent(".list_item");
		var main = pare.children(".main").val();
		if($.trim(main)!=""){
			if(pare.children(".in_main").data("sex")=="male"){
				var spouse = pare.children(".spouse").val();
				if($.trim(spouse)!=""){
					pare.children(".spouse").remove();
					pare.children(".main").remove();
					$("<p class='main'>"+main+"</p>").insertBefore(pare.children(".in_main"));
					$("<p class='spouse'>"+spouse+"</p>").insertBefore(pare.children(".in_spouse"));
				}else{
					pare.children(".spouse").remove();
					pare.children(".in_spouse").remove();
					pare.children(".main").remove();
					$("<p class='main'>"+main+"</p>").insertBefore(pare.children(".in_main"));
				}
			}else{
				pare.children(".main").remove();
				$("<p class='main'>"+main+"</p>").insertBefore(pare.children(".in_main"));
			}
			$(this).addClass("fui-new").removeClass("fui-check-circle");
			pare.removeClass("op");
		}
	})
	
	$(".gen_wrap").on("click",".fui-new",function(){
		var pare = $(this).parent(".list_item");
		var main = pare.children(".main").html();
		pare.children(".main").remove();
		$("<input type='text' placeholder='名字' class='form-control item_input main' value='"+main+"'/>")
			.insertBefore(pare.children(".in_main"));
		if(pare.children(".in_main").data("sex")=="male"){
			if(pare.children(".spouse").length>0){
				var spouse = pare.children(".spouse").html();
				pare.children(".spouse").remove();
				$("<input type='text' placeholder='配偶' class='form-control item_input spouse' value='"+spouse+"'/>")
				.insertBefore(pare.children(".in_spouse"));
			}else{
				$("<input type='text' placeholder='配偶' class='form-control item_input spouse'/><span class='in_spouse'>♀</span>")
				.insertAfter(pare.children(".in_main"));
			}
		}
		$(this).removeClass("fui-new").addClass("fui-check-circle");
		pare.addClass("op");
	})
		
	$("#dele_close").click(function(){
		$(".dele_wrap").css("display","none");
		$(".mask").css("display","none");
	})
	
	$("#dele_cencel").click(function(){
		$(".dele_wrap").css("display","none");
		$(".mask").css("display","none");
	})
	
	$(".gen_wrap").on("click",".add_list",function(){
		var item = $(this).parent("li");
		$("#addlist").trigger("click");
		$("#addlist").change(function(){
			var reader = new FileReader();
			var f = $("#addlist")[0].files[0];
			 reader.readAsText(f);
			reader.onload = function(e){
				item.children("ul").html(this.result);
				$("#addlist").val("");
			}
		})
	})
	
	function getDom(){
		return 	"<li>"+
				"<div class='list_item op'>"+
				"<input type='text' placeholder='名字' class='form-control item_input main'/>"+
				"<span class='in_main' data-sex='male'>♂</span>"+
				"<input type='text' placeholder='配偶' class='form-control item_input spouse'/>"+
				"<span class='in_spouse'>♀</span>"+
				"<span class='fui-check-circle in_ok'></span>"+
				"</div>"+
				"<span class='fui-plus-circle add_i'></span>"+
				"<span class='fui-cross-circle de_i'></span>"+
				"<span class='fui-link add_list'></span>"+
				"<ul></ul>"+
				"</li>"
	}
	
	function fake_click(obj) {
	    var ev = document.createEvent("MouseEvents");
	    ev.initMouseEvent(
	        "click", true, false, window, 0, 0, 0, 0, 0
	        , false, false, false, false, 0, null
	        );
	    obj.dispatchEvent(ev);
	}
	
	function export_raw(name, data) {
	    var urlObject = window.URL || window.webkitURL || window;
	
	    var export_blob = new Blob([data]);
	
	    var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
	    save_link.href = urlObject.createObjectURL(export_blob);
	    save_link.download = name;
	    fake_click(save_link);
	}
	
	$("#save_local").click(function() {
		export_raw('test.text',$(".gen_wrap ul").html());
	});
	
	$("#r_local").click(function(){
		$("#txtt").trigger("click");
	})
	
	$("#txtt").change(function(){
		var reader = new FileReader();
		var f = $("#txtt")[0].files[0];
		 reader.readAsText(f);
		reader.onload = function(e){
			$(".gen_wrap ul").html(this.result);
			$("#txtt").val("");
		}
	})
	
	$("#up_load").click(function(){
		if($(".gen_wrap").find("input").length<1){
			for(var i = 0 ; i < $(".gen_wrap").find("li").length;i++){
				$(".gen_wrap").find("li").eq(i).data("num",i+1);
			}
			var arr = new Array;
			for(var i = 0 ; i < $(".gen_wrap").find("li").length;i++){
				var item = {
					id : $(".gen_wrap").find("li").eq(i).data("num"),
					pid : $(".gen_wrap").find("li").eq(i).parent("ul").parent("li").data("num")||0,
					name : $(".gen_wrap").find("li").eq(i).children(".list_item").children(".main").html(),
					sex : $(".gen_wrap").find("li").eq(i).children(".list_item").children(".in_main").data("sex"),
					spouse : $(".gen_wrap").find("li").eq(i).children(".list_item").children(".spouse").html()||"",
				}
				arr.push(item);
			}
			console.log(arr);
			$.ajax({
				type:"post",
				url:"../../../fnode/addNodeFrag.do?id="+localStorage.getItem("id"),
				async:true,
				contentType:"application/json", 
				data:JSON.stringify(arr),
				dataType:"json",
				success:function(data){
					
				}
			});
		}
	})
	
	function getleft(wid){
		var wwid = document.body.clientWidth;
		var wid = parseFloat(wid);
		return (wwid - wid) / 2;
	}
})
