$(document).ready(function(){
	show();
	getUserDetail();
	getURole();
	$.ajax({
		url:"../../ffunc/findUserFunc.do",
		type:"POST",
	   	dataType:"json",
	   	success:function(json){
	   		$("#main-left").append(
	   			'<li><a href="index.jsp" class="active"></div><i class="lnr lnr-home"></i><span>首页</span></a></li>'
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
	   						'<li id="child"><a style="cursor:pointer" class="" href="#'+json[i].children[j].url+'"><div style="display:none">'+json[i].children[j].url+'</div><i class=""></i> <span>'+json[i].children[j].title+'</span></a></li>'
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
	getHeadInfo();
	queryReportsTop();
	
	
	$("#rese").click(function(){
		$(".city-picker-span").children(".placeholder").css("display","inline");
		$(".city-picker-span").children(".title").css("display","none");
	});
	
	editor.addListener("ready",function(){
		$("#edui3").css("float","left");
		$("#edui4").css("margin-right","560px");
	})
	
	$("#diaInfo").children("form").children(".form-group").eq(1).children("div").children("span").datepicker().on('changeDate.datepicker.amui', function(event){
		var date=new Date(event.date);
		$("#datapicker").val($(this).data("date"));
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

function getHeadInfo(){
	$.ajax({
		type:"POST",
		url:"../../fmhp/findFamPro.do",
		dataType:"json",
		success:function(data){
			$("#FName").text(data.fulname);
			var head=$("#HeadInfo").children(".profile-main");
			var count=$("#HeadInfo").children(".profile-stat").children(".row").children("div");
			var detInfo=$("#detailInfo").children(".profile-info");
			if(data.urlList==null){
			}else{
				var ulist=data.urlList;
				head.children("img").attr("src",ulist[0]);
				for(var i=0;i<ulist.length;i++){
					detInfo.eq(1).children("ul").append(
						'<li><img src='+ulist[i]+' style="height:45px;width:50px"/></li>'
					);
				}
			}
			head.children("h3").text(data.fulname);
			count.eq(0).children("span").eq(0).text(data.countList[0]);
			count.eq(1).children("span").eq(0).text(data.countList[1]);
			count.eq(2).children("span").eq(0).text(data.countList[2]);
			detInfo.eq(0).children("ul").children("li").eq(0).children("span").text(data.fulname);
			detInfo.eq(0).children("ul").children("li").eq(1).children("span").text(data.createage);
			detInfo.eq(0).children("ul").children("li").eq(2).children("span").text(data.famlocal);
			detInfo.eq(2).children("p").text(data.introduce);
			popuper(data);
			limitWord();
		}
	})
}

function limitWord(){
	var maxwidth=30;
	var max=10;
	var text=$("#content").text();
	var t=$("#famPosition").text();
	if($("#content").text().length>maxwidth){
        $("#content").text($("#content").text().substring(0,maxwidth));
        $("#content").html($("#content").html()+"..."+"<a id='more' style='cursor:pointer'>查看详情</a>");//如果字数超过最大字数，超出部分用...代替，并且在后面加上点击展开的链接；
    };
    $("#content").find("a").popover({
    	theme:'success',
    	content:text
    })
    
    if($("#famPosition").text().length>max){
    	$("#famPosition").text($("#famPosition").text().substring(0,max));
        $("#famPosition").html($("#famPosition").html()+"..."+"<a id='mo' style='cursor:pointer'>详情</a>");
    }
    
    $("#famPosition").find("a").popover({
    	theme:'success',
    	content:t
    })
}

function popuper(data){
	var updateInfo=$("#diaInfo").children("form").children(".form-group");
	updateInfo.eq(0).children(".col-sm-10").children("input").val(data.fulname);
	updateInfo.eq(1).children(".col-sm-10").children("input").val(data.createage);
	updateInfo.eq(3).children(".col-sm-10").children("textarea").val(data.introduce);
	$(".city-picker-span").children(".placeholder").text(data.famlocal);
	if(data.urlList==null){
		
	}else{
		editor.addListener("ready", function () { 
           editor.setContent(data.html); 
        });
	}
}

function postPro(){
	var updateInfo=$("#diaInfo").children("form").children(".form-group");
	var fulname=updateInfo.eq(0).children(".col-sm-10").children("input").val();
	var createage=$("#datapicker").val();
	var introduce=updateInfo.eq(3).children(".col-sm-10").children("textarea").val();
	var url=editor.getContent();
	var famlocal;
	if($(".city-picker-span").children(".title").css("display")!="none"){
		var p=$(".city-picker-span").children(".title").children("span");
		famlocal=p.eq(0).text()+"/"+p.eq(1).text()+"/"+p.eq(2).text();
		
	}else{
		famlocal=$(".city-picker-span").children(".placeholder").text();
	}
	
	var info={
			"fulname":fulname,
			"createage":createage,
			"introduce":introduce,
			"url":url,
			"famlocal":famlocal
	}
	
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fmhp/updateFamPro.do",
		data:JSON.stringify(info),
		dataType:"json",
		success:function(result){
			if(result==1){
				$("#doc-modal-1").modal('close');
				window.location.reload();
			}
		}
	})
}

function queryReportsTop() {
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fposts/findReportsTop.do",
		dataType:"json",
		success:function(result){
			$("#tab-bottom-left1").children("ul").children("li").remove();
			if(result!=null){			
				for(var i=0;i<result.length;i++){
					$("#tab-bottom-left1").children("ul").append(
							'<li><i class="fa fa-comment activity-icon"></i><p><span>'+
							result[i].title+'</span><span class="timestamp">'
							+Format(new Date(result[i].createtime),"yyyy-MM-dd")+'</span></p>'
					)
				}
			}
		}
	})
}

function pathload(){
	alert("请前往家族论坛管理");
}

var editor;
function show(){	
	var $textArea = $('[name=myue');
	editor = UE.getEditor('myue',{
	  toolbars:[['source','simpleupload']],
	  wordCount:false,  
	  elementPathEnabled:false,
	  autoHeightEnabled: false,
	  initialFrameWidth: 618,
	  initialFrameHeight:150
	});
} 

function getUserDetail(){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fuser/findUserDetail.do",
		dataType:"json",
		success:function(result){
			$("#userDetail").append(
				'<img src="'+result.pic+'" class="img-circle"><span style="font-size:large;">'+result.name+'</span><i class="icon-submenu lnr lnr-chevron-down"></i>'
			)
		}
	})
}

function getURole(){
	$.ajax({
		type:"POST",
		url:"../../ffunc/findUserR.do",
		dataType:"json",
		success:function(data){
			if(data.role=="族管理员"){
				$("#upFam").css("display","block");
				$("#morepost").css("display","block");
				$("#moreuser").css("display","block");
			}else{
				$("#upFam").css("display","none");
				$("#morepost").css("display","none");
				$("#moreuser").css("display","none");
			}
		}
	})
}