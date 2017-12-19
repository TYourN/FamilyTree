(function($) {
	
	$.fn.load_refresh = function(options) {
		
		options = $.extend({
			"targetdiv":"body",
			"containerdiv":"body",
			"dataload":null,
			"datarefresh":null
		}, options);
	
		var sroll_ = {
			lstarty : "",
			lendy : "",
			lmoveY : "",
			fstarty : "",
			fendy : "",
			fmoveY : "",
		}
		
		$(options.targetdiv).on("touchstart",function(event){
			if($(options.containerdiv).scrollTop()==0){
				sroll_.lstarty = event.touches[0].clientY;
			}
			if($(options.containerdiv).height() + $(options.containerdiv)[0].scrollTop >= $(options.containerdiv)[0].scrollHeight){
				sroll_.fstarty = event.touches[0].clientY;
			}
		})
		$(options.targetdiv).on("touchmove",function(event){
			if(sroll_.lstarty != ""){
				sroll_.lendy = event.touches[0].clientY;
				var move = sroll_.lendy - sroll_.lstarty;
				if(move > 0 ){
					$(options.targetdiv).css("transform","translateY("+move/3+"px)");
				}
	   		}
			if(sroll_.fstarty != ""){
				sroll_.fendy = event.touches[0].clientY;
				var move = sroll_.fendy - sroll_.fstarty;
				if(move < 0 ){
					$(options.containerdiv).css("transform","translateY("+move/3+"px)");
				}
	   		}
			
		})	
		
		$(options.targetdiv).on("touchend",function(event){
			if((sroll_.lendy - sroll_.lstarty) > 20){
				options.dataload();
			}
			if((sroll_.fendy - sroll_.fstarty) < -20){
				options.datarefresh();
			}
			sroll_.lstarty = "";
			sroll_.lendy = "";
			sroll_.fstarty = "";
			sroll_.fendy = "";
			$(options.targetdiv).css("transform","translateY(0px)");
			$(options.containerdiv).css("transform","translateY(0px)");
		})
	
	};
})(jQuery)