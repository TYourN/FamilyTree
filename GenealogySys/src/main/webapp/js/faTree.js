$(function() {

	$("#addnew").click(function() {
		$(".edit_wrap>ul").append(additem());
	})
	
	$("#assembly").css("width",$("#edit").css("width"));

	/*制作版操作*/
		/*增加节点*/
	$(".edit_wrap").on("click",".add_i",function(){
		if($(this).parent("li").children("ul").length<1){
			$(this).parent("li").append("<ul>"+additem()+"</ul>")
		}else{
			$(this).parent("li").children("ul").append(additem());	
		}
	})
		/*删除节点*/
	$(".edit_wrap").on("click",".de_i",function(){
		dele("是否删除此节点",$(this).parent("li"));
	})
	
	$("#dele_close").click(function(){
		$(".dele_wrap").css("display","none");
		$(".mask").css("display","none");
	})
	
	$("#dele_cencel").click(function(){
		$(".dele_wrap").css("display","none");
		$(".mask").css("display","none");
	})
		/*完成节点*/
	$(".edit_wrap").on("click",".glyphicon-check",function(){
		var pare = $(this).parent(".list_item");
		var domination = pare.children(".domination").val();
		if($.trim(domination)!=""){
			if(pare.children(".in_domination").data("sex")=="male"){
				var spouse = pare.children(".spouse").val();
				if($.trim(spouse)!=""){
					pare.children(".spouse").remove();
					pare.children(".domination").remove();
					$("<p class='domination'>"+domination+"</p>").insertBefore(pare.children(".in_domination"));
					$("<p class='spouse'>"+spouse+"</p>").insertBefore(pare.children(".in_spouse"));
				}else{
					pare.children(".spouse").remove();
					pare.children(".in_spouse").remove();
					pare.children(".domination").remove();
					$("<p class='domination'>"+domination+"</p>").insertBefore(pare.children(".in_domination"));
				}
			}else{
				pare.children(".domination").remove();
				$("<p class='domination'>"+domination+"</p>").insertBefore(pare.children(".in_domination"));
			}
			$(this).addClass("glyphicon-edit").removeClass("glyphicon-check");
			pare.removeClass("op");
		}
	})
		/*编辑节点*/
	$(".edit_wrap").on("click",".glyphicon-edit",function(){
		var pare = $(this).parent(".list_item");
		var domination = pare.children(".domination").html();
		pare.children(".domination").remove();
		$("<input type='text' placeholder='名字' class='form-control item_input domination' value='"+domination+"'/>")
			.insertBefore(pare.children(".in_domination"));
		if(pare.children(".in_domination").data("sex")=="male"){
			if(pare.children(".spouse").length>0){
				var spouse = pare.children(".spouse").html();
				pare.children(".spouse").remove();
				$("<input type='text' placeholder='配偶' class='form-control item_input spouse' value='"+spouse+"'/>")
				.insertBefore(pare.children(".in_spouse"));
			}else{
				$("<input type='text' placeholder='配偶' class='form-control item_input spouse'/><span class='in_spouse'>♀</span>")
				.insertAfter(pare.children(".in_domination"));
			}
		}
		$(this).addClass("glyphicon-check").removeClass("glyphicon-edit");
		pare.addClass("op");
	})
		
		/*性别切换*/
	$(".edit_wrap").on("click",".in_domination",function(){
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
	
		/*引用组件*/
	$(".edit_wrap").on("click",".glyphicon-link",function(){
		var Parent = $(this).parent("li");
		var Item = $("#assembly_wrap").children("ul").children("li");
		$(".u_item").children("ul").children().remove();
		for(var i = 0; i < Item.length; i++){
			$(".u_item").children("ul").append(getcheckbox(Item.eq(i)));
		}
		$(".use_wrap").css("display","block");
		$(".mask").css("display","block");
		$(".use_wrap").css("left",getleft($(".use_wrap").css("width")));
		$("#use_sure").one("click",function(){
			$.ajax({
				type:"post",
				url:"../../fnode/findMaNodeByCount.do?count="+$("input[name='count']:checked").val(),
				async:true,
				success:function(data){
					var html = new treeMenu(data).init(0,0);
					html = html.substring(4);
					html = html.substring(0,html.length-5);
					if(Parent.children("ul").length<1){
						Parent.append("<ul>"+html+"</ul>");
					}else{
						Parent.children("ul").append(html);	
					}
					$(".use_wrap").css("display","none");
					$(".mask").css("display","none");
				}
			});
		})
	})
	$("#use_close").click(function(){
		$(".use_wrap").css("display","none");
		$(".mask").css("display","none");
	})
	
	$("#use_cencel").click(function(){
		$(".use_wrap").css("display","none");
		$(".mask").css("display","none");
	})
	
	
	/*数据读取*/
		$.ajax({
			type:"post",
			url:"../../fnode/findAllNode.do",
			async:true,
			dataType: "json",
			success:function(data){
				$.each(data, function(i,item) {
					if(item.count == "0"){
						var html = new treeMenu(item.content).init(0,0);
						html = html.substring(4);
						html = html.substring(0,html.length-5);
						$(".edit_wrap ul").html(html);
					}else{
						$("#assembly_wrap>ul").append(getshort(item));
					}
				});
			},
			complete:function(){
				$(".sitem").hover(function(){
					$(this).find(".smalltree").css("display","block");
					},function(){
					$(this).find(".smalltree").css("display","none");
				})
			}
		});
	
	/*组件删除*/
	$("#assembly_wrap").on("click",".item_remove",function(){
		var list = $(this).parents(".sitem");
		var count = $(this).parents(".sitem").data("count");
		dele("是否删除此组件",list);
		$.ajax({
			type:"post",
			url:"../../fnode/deleteNode.do?count="+count,
			async:true,
			success:function(data){
				
			}
		});
	})
	
	/*保存*/
	$("#suretree").click(function(){
		if($(".edit_wrap").find("input").length<1){
			for(var i = 0 ; i < $(".edit_wrap").find("li").length;i++){
				$(".edit_wrap").find("li").eq(i).data("num",i+1);
			}
			var arr = new Array;
			for(var i = 0 ; i < $(".edit_wrap").find("li").length;i++){
				var item = {
					id : $(".edit_wrap").find("li").eq(i).data("num"),
					pid : $(".edit_wrap").find("li").eq(i).parent("ul").parent("li").data("num")||0,
					name : $(".edit_wrap").find("li").eq(i).children(".list_item").children(".domination").html(),
					sex : $(".edit_wrap").find("li").eq(i).children(".list_item").children(".in_domination").data("sex"),
					spouse : $(".edit_wrap").find("li").eq(i).children(".list_item").children(".spouse").html()||"",
				}
				arr.push(item);
			}
			$.ajax({
				type:"post",
				url:"../../fnode/addMaNodeFrag.do",
				async:true,
				contentType:"application/json", 
				data:JSON.stringify(arr),
				dataType:"json",
				success:function(data){
					
				}
			});
		}
	})
	
	/*树建立*/
	function treeMenu(data) {
		this.tree = data || [];
		this.groups = {};
	};
	treeMenu.prototype = {
		init: function(pid,j) {
			this.group();
			return this.getDom(this.groups[pid],j);
		},
		group: function() {
			for(var i = 0; i < this.tree.length; i++) {
				if(this.groups[this.tree[i].pId]) {
					this.groups[this.tree[i].pId].push(this.tree[i]);
				} else {
					this.groups[this.tree[i].pId] = [];
					this.groups[this.tree[i].pId].push(this.tree[i]);
				}
			}
		},
		getDom: function(data,j) {
			if(!data) { return '' }
			var html = '<ul>';
			for(var i = 0; i < data.length; i++) {
				html += '<li>';
				if(j == "0"){
					html += getlongdom(data[i]);
				}else{
					html += getshortdom(data[i]);
				}
				html += this.getDom(this.groups[data[i].id],j);
				html += '</li>';
			};
			html += '</ul>';
			return html;
		}
	};
	
	function additem() {
		return 	"<li>"+
				"<div class='list_item op'>"+
				"<input type='text' placeholder='名字' class='item_input domination'/>"+
				"<span class='in_domination' data-sex='male'>♂</span>"+
				"<input type='text' placeholder='配偶' class='item_input spouse'/>"+
				"<span class='in_spouse'>♀</span>"+
				"<span class='glyphicon glyphicon-check in_ok'></span>"+
				"</div>"+
				"<span class='glyphicon glyphicon-plus-sign add_i'></span>"+
				"<span class='glyphicon glyphicon-remove-sign de_i'></span>"+
				"<span class='glyphicon glyphicon-link add_list'></span>"+
				"<ul></ul>"+
				"</li>"
	}
	
	function getleft(wid){
		var wwid = document.body.clientWidth;
		var wid = parseFloat(wid);
		return (wwid - wid) / 2;
	}
	
	function getlongdom(data){
		var data = {
			name : data.name,
			sex : data.sex,
			spouse : data.spouse || "",
		}
		var html = 	"<div class='list_item'>"+
					"<p class='domination'>"+data.name+"</p>";
		if(data.sex=="male"){
			html += "<span class='in_domination' data-sex='male'>♂</span>";
		}else{
			html += "<span class='in_domination' data-sex='female' style='background-color: #FD367E'>♀</span>";
		}
		if(data.spouse != ""){
			html += "<p class='spouse'>"+data.spouse+"</p>"+
					"<span class='in_spouse'>♀</span>"
		}
		html += "<span class='glyphicon glyphicon-edit in_ok'></span>"+
				"</div>"+
				"<span class='glyphicon glyphicon-plus-sign add_i'></span>"+
				"<span class='glyphicon glyphicon-remove-sign de_i'></span>"+
				"<span class='glyphicon glyphicon-link add_list'></span>"
		return html;
	}
	
	function getshortdom(data){
		var data = data.name;
		return "<div class='list_item'><p>"+data+"</p></div>";
	}
	
	function getshort(data){
		var data = {
			count : data.count,
			content : data.content,
			fname : data.content[0].name,
			lname : data.content[data.content.length-1].name
		}
		var html = new treeMenu(data.content).init(0,1);
		return 	"<li class='sitem' data-count='"+data.count+"' data-name='"+data.fname+"-"+data.lname+"'>"+
				"<div class='item_wrap'>"+
				"<p>"+data.fname+"-"+data.lname+"</p>"+
				"<i class='glyphicon glyphicon-remove item_remove'></i>"+
				"<div class='smalltree'>"+
				html+
				"</div>"+
				"</div>"+
				"</li>"
	}
	
	function dele(word,list){
		$(".dele_wrap").css("display","block");
		$(".c_item").find("p").html(word);
		$(".mask").css("display","block");
		$(".dele_wrap").css("left",getleft($(".dele_wrap").css("width")));
		$("#dele_sure").one("click",function(){
			list.remove();
			$(".dele_wrap").css("display","none");
			$(".mask").css("display","none");
		})
	}
	
	function getcheckbox(data){
		var data = {
			Count : data.data("count"),
			Name : data.data("name")
		}
		return 		"<li>"+
					"<label class='radio'>"+
		            "<input type='radio' name='count' data-toggle='radio' value='"+data.Count+"' required>"+
		            data.Name+
		            "</label>"+
		            "</li>"
	}
})