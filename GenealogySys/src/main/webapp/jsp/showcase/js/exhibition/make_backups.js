$(function(){
$(function() {
			$("#add_item").click(function() {
				$(".gen_wrap>ul").append(getitem())
			})

			$(".gen_wrap").on("click", "span", function() {

				if($(this).hasClass("fui-check-circle")) {
					if($.trim(getname($(this)).val()) != "") {
						var name = $.trim(getname($(this)).val());
						getparent($(this), 1).children(".list_item")
							.find("input")
							.remove();
						$("<p>" + name + "</p>").insertBefore($(this));
						$(this).removeClass("fui-check-circle")
							.addClass("fui-new");
					}
				}
				
				else if($(this).hasClass("fui-new")) {
					var name = getname($(this)).val();
					getparent($(this), 1).children(".list_item")
						.find("p")
						.remove();
					$("<input type='text' placeholder='名字及配偶名字'class='form-control item_input item_word'/>").insertBefore($(this));
					$(this).removeClass("fui-new")
						.addClass("fui-check-circle");
					getparent($(this), 1).children(".list_item")
						.find("input")
						.val(name);
				} 
				
				else if($(this).hasClass("fui-plus-circle")) {
					getparent($(this), 2).children("ul").append(getitem());
				} 
				
				else if($(this).hasClass("fui-cross-circle")) {
					getparent($(this), 2).remove();
				}
			})

			$(".gen_wrap").on("click", "p", function() {
				var children = getparent($(this),1).children(".list_item");
				var hide = getparent($(this),1).children("ul");
				if(children.hasClass("item_hide")){
					hide.show("fast");
					children.removeClass("item_hide");
				}else{
					hide.hide("fast");
					children.addClass("item_hide");
				}
			})
			
			

				function getitem() {
					return "<li>" +
						"<div class='list_item'>" +
						"<input type='text' placeholder='名字及配偶名字' class='form-control item_input item_word'/>" +
						"<span class='fui-check-circle in_ok'></span>" +
						"</div>" +
						"<span class='fui-plus-circle add_i'></span>" +
						"<span class='fui-cross-circle de_i'></span>" +
						"<ul></ul>" +
						"</li>"
				}

				function getparent(el, position) {
					if(position == "1")
						return el.parent(".list_item").parent("li");
					if(position == "2")
						return el.parent("li");
				}

				function getname(el) {
					return getparent(el, "1").children(".list_item").children(".item_word");
				}
			})
			$("#save_local").click(function(){
//				var blob = new Blob(["Hello, world!"], {type: "text/plain;charset=utf-8"});
//				saveAs(blob, "hello world.txt");
				var html = $(".gen_wrap").children("ul").html();
//				f.write(html);
				
			})
			
})