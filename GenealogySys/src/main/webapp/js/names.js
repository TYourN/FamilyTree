/**
 * 
 */
/**
 * 
 */

$(document).ready(function(){
	getPosts();
	ue.addListener("ready", function () {  
		$("#reeditor").children("#editor").children("#edui1").css("z-index","800");   
	});
})

function getPosts(){
	var t = $("#table_server").bootstrapTable({  
        url:"../../fnames/findAllName.do", 
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
                    pageSize: params.pageSize
            };    
            return param;
        },
        queryParamsType: "undefined",
        idField: "fnameid",//指定主键列  
        columns: [  
            {  
                title: '',//表的列名 
                field: 'fnameid',
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
                width: 200
            },  
            {  
                title: '简介',  
                field: 'memo',  
                align: 'center',
                width: 500
            },   
            {  
                title: '操作',  
                field: 'fnameid',  
                align: 'center',
                width: 250,
                formatter: function (value, row, index) {//自定义显示可以写标签哦~  
                    return '<button type="button" class="btn btn-default" style="padding: 4px 20px;margin-right:10px" onclick="delInfo('+row.fnameid+')">删除</button>'+''+
                           '<button type="button" class="btn btn-default" style="padding: 4px 20px;" onclick="getDetailInfo('+row.fnameid+')">查看详情</button>';
                }  
            }  
  
        ]  
    });  
  
    
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数   
        $(".pull-right").css("display", "block");
        $("input[name='btSelectAll']").parent("div").parent("th").css("padding-left","0px");
    }); 
}

function getDetailInfo(id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fnames/findNameById.do?id="+id,
		dataType:"json",
		success:function(result){
			$("#title").val(result.title);
			$("#memo").val(result.memo);
			$("#nameid").text(id);
			editor.setContent(result.content);
		}
	})
	
	$('#doc-modal-1').modal({
		closeViaDimmer: 0,
		width: 800
	})
}

function updateInfo(){
	var title=$("#title").val();
	var memo=$("#memo").val();
	var content=editor.getContent();
	var nameid=$("#nameid").text();
	
	var info={
		title:title,
		memo:memo,
		content:content,
		nameid:nameid
	}
	
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fnames/updateMainNames.do",
		data:JSON.stringify(info),
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

function postNews(){
	var title=$("#ntitle").val();
	var memo=$("#nmemo").val();
	var content=ue.getContent();
	
	var info={
		title:title,
		memo:memo,
		content:content	
	}
	
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fnames/addMainNames.do",
		data:JSON.stringify(info),
		dataType:"json",
		success:function(result){
			if(result==1){
				alert("添加成功！！！");
				window.location.reload();
			}else{
				alert("添加失败。。。请联系管理员！！！");
				window.location.reload();
			}
		}
	})
}

function delInfo(id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fnames/deleteMainNames.do?nameid="+id,
		dataType:"json",
		success:function(result){
			if(result==1){
				alert("删除成功！！！");
				window.location.reload();
			}else{
				alert("删除失败。。。请联系管理员！！！");
				window.location.reload();
			}
		}
	})
}
