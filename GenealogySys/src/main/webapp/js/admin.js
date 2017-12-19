$(document).ready(function(){
	$.ajax({
		url:"../json/function.json",
	    type:"GET",
	   	dataType:"json",
	   	success:function(json){
	   		$("#main-left").append(
	   			'<li><a href="admin.jsp" class="active"></div><i class="lnr lnr-home"></i> <span>首页</span></a></li>'
	   		);
	   		for(var i=0;i<json.length;i++){
	   			var id=''+i;
	   			if(json[i].children.length==0){	   				
	   			    $("#main-left").append(
	   			    	'<li><a style="cursor:pointer" class="" href="#'+json[i].attr+'"><div style="display:none">'+json[i].attr+'</div><i class="'+json[i].memo+'"></i> <span>'+json[i].text+'</span></a></li>'
	   			    )
	   			}else{
	   				$("#main-left").append(
	   					'<li><a id="list" style="cursor:pointer" class=""><div style="display:none">'+json[i].attr+'</div><i class="'+json[i].memo+'"></i> <span>'+json[i].text+'</span><i class="icon-submenu lnr lnr-chevron-down" style="padding-left:35px;"></i></a>'+
	   					'<div id="subPages" class="collapse"><ul id="main-child" class="nav">'+
	   					'</ul></div></li>'
	   				);
	   				for(var j=0;j<json[i].children.length;j++){
	   					$("#main-child").append(
	   						'<li id="child"><a style="cursor:pointer" class="" href="#'+json[i].children[j].attr+'"><div style="display:none">'+json[i].children[j].attr+'</div><i class=""></i> <span>'+json[i].children[j].text+'</span></a></li>'
	   					)	   					
	   				}
	   			}	   			
	   		}
	   	}
	})
	
	$("#main-left").on("click", "a", function() {
    	var op = $(this).parent("li").children(".collapse");
    	var opp= $(this).children("i").eq(1);
    	if($(this).parent("li").attr("id")!="child"){
    		$("#main-left li a").removeClass("active");
    		$(this).addClass("active");
    	}
		if(op.css("display") == "none") {
			op.show("fast");
			opp.removeClass("icon-submenu lnr lnr-chevron-down");
			opp.addClass("icon-submenu lnr lnr-chevron-up");
		} else {
			op.hide("fast");
			opp.removeClass("icon-submenu lnr lnr-chevron-up");
			opp.addClass("icon-submenu lnr lnr-chevron-down");
		}
		
		if($(this).attr("id")==""||$(this).attr("id")==null){
			if($(this).parent("li").attr("id")!="child"){
				if($("#list").length>0){
					$("a[id='list']").each(function(){
						var app = $(this).children("i").eq(1);
						var ap = $(this).parent("li").children(".collapse");
						if(app.attr("class")=="icon-submenu lnr lnr-chevron-up"){
							ap.hide("fast");
			   				app.removeClass("icon-submenu lnr lnr-chevron-up");
							app.addClass("icon-submenu lnr lnr-chevron-down");
						}
					})
				}	
			}			
		}
		
		var o=$(this).children("div").eq(0).html();
		HTMLLoad(o);
	})    
})

function HTMLLoad(o){
    	if(o==""||o==null){
    	
    	}else if(o=="kudos.jsp"||o=="culture.jsp"||o=="news.jsp"){
    		$("#main-body").empty();
    		$("#main-body").append(
    			'<iframe src="info.jsp#'+o+'" name="pageIframe" style="width:100%;height:-webkit-fill-available"></iframe>'
    		);
    	}else{
    		$("#main-body").empty();
    		$("#main-body").append(
    			'<iframe src="'+o+'" name="pageIframe" style="width:100%;height:-webkit-fill-available"></iframe>'
    		);
    	}
}

function logout(){
	location.href="../login.jsp";
}


