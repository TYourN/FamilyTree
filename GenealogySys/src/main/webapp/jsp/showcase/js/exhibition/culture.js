$(function(){
	
	function getdata(url,data){
		$.ajax({
			type:"post",
			url:"../../../fcul/findShCul.do",
			async:true,
			contentType:"application/json", 
			data:JSON.stringify(data),
			dataType:"json",
			success:function(data){
				$(".con_title").html("<p>"+data.title+"</p><small>"+data.date+"</small>");
				$(".con_con").html(data.content);
			},
			error:function(){
				
			}
		});
	}
	var culture = "起源";
	var content={
		id:localStorage.getItem("id"),
		culture:culture
	}
	getdata("url",content);
	$(".tap_item").click(function(){
		if($(this).hasClass("tap_origin")){
			culture = "起源";
			content.culture=culture;
			getdata("url",content);
		}else if($(this).hasClass("tap_train")){
			culture = "祖训";
			content.culture=culture;
			getdata("url",content);
		}else if($(this).hasClass("tap_celebrity")){
			culture = "名人事迹";
			content.culture=culture;
			getdata("url",content);
		}else if($(this).hasClass("tap_hs")){
			culture = "古迹";
			content.culture=culture;
			getdata("url",content);
		}
	})
})
