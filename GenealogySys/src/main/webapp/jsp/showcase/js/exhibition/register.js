$(function(){
	
	var select = $('.city-picker-select').cityPicker({
			dataJson: cityData,
			renderMode: false,
			linkage: false
	});
	
	$("#register_ok").click(function(){
		check($("#inputPlace")); 
		check($("#inputSurname")); 
		check($("#inputName")); 
		check($("#inputPwd")); 
		check($("#inputSurepwd"));
		check($("#inputId")); 
		check($("#inputPhone"));
		if($(".sub_con").find(".Novalue").length<1){
				if($("#inputPwd").val()==$("#inputSurepwd").val()){
					$("#inputSurepwd").css("border-color","#bdc3c7");
					var data = {
							address : $(".province").find("option:selected").text() + 
										$(".city").find("option:selected").text()+
										$(".district").find("option:selected").text()+
										$("#inputPlace").val(),
							surname : $("#inputSurname").val(),
							username : $("#inputName").val(),
							pwd : $("#inputPwd").val(),
							sfz :$("#inputId").val(),
							phone : $("#inputPhone").val()
					}
					$.ajax({
						type:"post",
						url:"../../../fdb/addDB.do",
						contentType:"application/json",
						async:true,
						data:JSON.stringify(data),
						dataTpye: "json",
						success:function(data){
							window.location.href="Homepage.jsp"
						},
						error:function(){
							
						}
					});
				}else{
					$("#inputSurepwd").css("border-color","#E74C3C");
				}
		}
	})
	
	function check(el){
		if($.trim(el.val())==""){
			el.css("border-color","#E74C3C");
			el.addClass("Novalue");
		}else{
			el.css("border-color","#bdc3c7");
			el.removeClass("Novalue");
		}
	}
})
