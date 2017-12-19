$(function(){
	var content={
		id:localStorage.getItem("id"),
		count:0
	}	
	$.ajax({
		type: "post",
		url: "../../../fnode/findshNodeByCount.do",
		async: true,
		data:JSON.stringify(content),
		contentType:"application/json;charset=UTF-8",
		dataType: "json",
		success: function(data) {
			var html = new treeMenu(data).init(0);
			$(".right_wrap").html(html);
		}
	});

	function treeMenu(data) {
		this.tree = data || [];
		this.groups = {};
	};
	treeMenu.prototype = {
		init: function(pid) {
			this.group();
			return this.getDom(this.groups[pid],0);
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
			j += 1;
			for(var i = 0; i < data.length; i++) {
				
				html += '<li id="people'+data[i].id+'">';
				html += getdom(data[i],j);
				html += this.getDom(this.groups[data[i].id],j);
				html += '</li>';
			};
			html += '</ul>';
			return html;
		}
	};

	$(".right_wrap").on("click", ".list_item", function() {
		var hide = $(this).parent("li").children("ul");
		if($(this).hasClass("item_hide")) {
			hide.show("fast");
			$(this).removeClass("item_hide");
		} else {
			hide.hide("fast");
			$(this).addClass("item_hide");
		}
	})
	
	function getdom(data,j){
		var data = {
			name : data.name,
			seniority : j,
			sex : data.sex,
			spouse : data.spouse || "",
		}
		var html = 	"<div class='list_item'>"+
					"<div class='main'>"+
					"<small class='seniority'>"+data.seniority+"</small>"+
					"<p class='name'>"+data.name+"</p>"
		if(data.sex == "male"){
			html = html + "<span class='sex' style='background-color: #5AC8D8'>♂</span>"+
					"</div>"
		}else if(data.sex =="female"){
			html = html + "<span class='sex' style='background-color: #FD367E'>♀</span>"+
					"</div>"
		}
		if(data.spouse == ""){
			html = html + "</div>"
		}else{
			html = html + "<div>"+
					"<small class='spouse'>配</small>"+
					"<p class='name'>"+data.spouse+"</p>"+
					"<span class='sex' style='background-color: #FD367E'>♀</span>"+
					"</div>"+
					"</div>"
		}
		return html;
	}
})
