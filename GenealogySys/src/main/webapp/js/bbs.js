$(document).ready(function(){
	getPosts(1);
	getAllCount();
	
	$("#bbs_info .panel").click(function(){
		$(this).children(".panel-body").children("i").css("color","#000000");
		$(this).find("span").css("color","#000000");
		$(this).siblings().children(".panel-body").children("i").css("color","#AEB7C2")
		$(this).siblings().find("span").css("color","#C0C0C0");
		var ind=$("#bbs_info .panel").index(this)+1;
	    judge(ind);
	})
	
		
	ue.addListener("ready", function () {  
		$("#reeditor").children("#editor").children("#edui1").css("z-index","800");   
	});
})

function getPosts(u){
	var t = $("#table_server").bootstrapTable({  
        url:"../../fposts/findAllInfo.do", 
        method: 'get',  
        dataType: "json",  
        striped: true,//设置为 true 会有隔行变色效果  
        undefinedText: "空",//当数据为 undefined 时显示的字符  
        pagination: true, //分页  
        // paginationLoop:true,//设置为 true 启用分页条无限循环的功能。  
        showToggle: false,//是否显示 切换试图（table/card）按钮  
        showColumns: false,//是否显示 内容列下拉框  
        pageNumber: 1,//如果设置了分页，首页页码  
        //showPaginationSwitch:false,//是否显示 数据条数选择框  
        pageSize: 5,//如果设置了分页，页面数据条数  
        pageList: [5],  //如果设置了分页，设置可供选择的页面数据条数。设置为All 则显示所有记录。  
        paginationPreText: '‹',//指定分页条中上一页按钮的图标或文字,这里是<  
        paginationNextText: '›',//指定分页条中下一页按钮的图标或文字,这里是>  
        singleSelect: false,//设置True 将禁止多选  
        search: false, //显示搜索框  
        data_local: "zh-US",//表格汉化  
        sidePagination: "server", //服务端处理分页  
        queryParams:function queryParams(params){
        	var param = {    
                    pageNumber: params.pageNumber,    
                    pageSize: params.pageSize,
                    flag:u
            };    
            return param;
        },
        queryParamsType: "undefined",
        idField: "userId",//指定主键列  
        columns: [  
            {  
                title: '',//表的列名 
                field: 'postid',
                align: 'center',//水平居中  
                width: 50,
                formatter:function(value, row, index){
                	return index+1;
                }
            },  
            {  
                title: '标题',  
                field: 'title',  
                align: 'center',
                width: 400
            },  
            {  
                title: '类型',  
                field: 'type',  
                align: 'center',
                width: 150
            },  
            {    
                title: '发表时间',  
                field: 'ctimeString', 
                align: 'center',
                width: 300
            },  
            {  
                title: '操作',  
                field: 'postid',  
                align: 'center',
                width: 250,
                formatter: function (value, row, index) {//自定义显示可以写标签哦~  
                    return '<button type="button" class="btn btn-default" style="padding: 4px 20px;margin-right:10px" onclick="delInfo('+row.postid+')">删除</button>'+''+
                           '<button type="button" class="btn btn-default" style="padding: 4px 20px;" onclick="getDetailInfo('+row.postid+')">查看详情</button>';
                }  
            }  
  
        ]  
    });  
  
    
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数   
        $(".pull-right").css("display", "block");
        $("input[name='btSelectAll']").parent("div").parent("th").css("padding-left","0px");
    }); 
}

function judge(ind){
	if(ind==1){	
		$("#readOnly").css("display","block");
		$("#tie").css("display","none");
		$("#postRep").css("display","none");
		$("#tieTitle").attr("readonly","readonly");
		$("#table_server").bootstrapTable('refresh',{url:'../../fposts/findAllInfo.do'});
	}else if(ind==2){
		$("#readOnly").css("display","none");
		$("#tie").css("display","block");
		$("#postRep").css("display","block");
		$("#closeModal").css("margin-left","20px");
		$("#tieTitle").removeAttr("readonly");
		$("#table_server").bootstrapTable('refresh',{url:'../../fposts/findAllReportsInfo.do'});
	}else{
		getPosts();
	}
}

function deletePosts(){
	$("#table_server").bootstrapTable('refresh');
}
 
function getAllCount(){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fposts/findAllCount.do",
		dataType:"json",
		success:function(result){
			$("#to_count").text(result.Totie);
			$("#ye_count").text(result.Yestie);
			$("#post_count").text(result.Alltie);
			$("#an_count").text(result.Allreports);
			$("#posts").text(result.Alltie);
			$("#replies").text(result.Allreplies);
			$("#reports").text(result.Allreports);
			if(result.LatestReport==null){
			}else{
				$("#latest_report").text(Format(new Date(result.LatestReport), "yyyy-MM-dd"));
			}
			
			if(result.Latesttie==null){				
			}else{
				$("#latest_post").text(Format(new Date(result.Latesttie), "yyyy-MM-dd"));
			}
		}
	})
}

function getDetailInfo(id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fposts/findDetialById.do?postid="+id,
		dataType:"json",
		success:function(result){
			$("#tieTitle").val(result.title);
			var info=$("#tieInfo").children("form").children(".form-group").eq(2).children("div");
			info.eq(0).children("span").text(result.name);
			info.eq(1).children("span").text(Format(new Date(result.createtime),"yyyy-MM-dd"));			
			$("#PostId").text(id);
			var count=$("#tieInfo").children("form").children(".form-group").eq(2);
			if(result.type=="水贴"){	
				count.children("label").eq(2).css("display","block");
				count.children("div").eq(2).css("display","block");
				count.children("label").eq(3).css("display","block");
				count.children("div").eq(3).css("display","block");
				info.eq(2).children("span").text(result.goodcount);
				info.eq(3).children("span").text(result.badcount);
				$("#readOnly").val(result.content);
			}else{				
				count.children("label").eq(2).css("display","none");
				count.children("div").eq(2).css("display","none");
				count.children("label").eq(3).css("display","none");
				count.children("div").eq(3).css("display","none");
			    editor.setContent(result.content); 
			}
		}
	})
	$('#doc-modal-1').modal({
		closeViaDimmer: 0,
		width: 800
	})
}

function delInfo(id){
	$('#my-confirm').modal({
		onConfirm:function(){
			delIn(id);
		}	
	})
}

function delIn(id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fposts/deleteDetialById.do?postid="+id,
		dataType:"json",
		success:function(result){
			if(result==1){
				window.location.reload();
			}
		}
	})
}

function updateInfo(){
	var title=$("#tieTitle").val();
	var content=editor.getContent();
	var postid=$("#PostId").text();
	var rep={
		"title":title,
		"content":content,
		"postid":postid
	}
	
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fposts/updateRepById.do",
		data:JSON.stringify(rep),
		dataType:"json",
		success:function(result){
			if(result==1){
				alert("修改成功！！！");
				window.location.reload();
			}else{
				alert("修改失败。。。请联系管理员！！！");
				window.location.reload();
			}
		}
	})
}

function postPosts(){
	var title=$("#ptitle").val();
	var content=ue.getContent();
	var post={
		"title":title,
		"content":content
	}
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fposts/addPosts.do",
		data:JSON.stringify(post),
		dataType:"json",
		success:function(result){
			if(result==1){
				alert("发布成功！！！");
				window.location.reload();
			}else{
				alert("发布失败。。。请联系管理员！！！");
				window.location.reload();
			}
		}
	})
}