$(document).ready(function(){
	var attr=getQueryVariable();
	judge(attr);
})

function getQueryVariable(){
	    var query = window.location.hash;
	    var vars = query.split("#");
	    for (var i=0;i<vars.length;i++) {
	       return query;
	    }
	    return(false);
}

function judge(attr){
	if(attr=="#kudos.jsp"){
		$("#Title").text("家族荣誉");
		getData("../../fhon/findAllHon.do");
	}else if(attr=="#culture.jsp"){
		$("#Title").text("家族文化");
		getCulData("../../fcul/findFamCul.do");
	}else{
		$("#Title").text("家族资讯");		
		getData("../../fnews/findFamNews.do");
	}
}

function getData(url){
	var t = $("#info_server").bootstrapTable({  
        url:url, 
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
        pageSize: 8,//如果设置了分页，页面数据条数  
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
        idField: "id",//指定主键列  
        columns: [  
            {  
                field: '',//json数据中rows数组中的属性名  
                checkbox:true,
                align: 'center',//水平居中  
                valign:'middle',
                width: 50    
            },  
            {  
                title: '标题',  
                field: 'title',  
                align: 'center',
                width: 400
            },  
            {  
                title: '发布人',  
                field: 'name',  
                align: 'center',
                width: 150
            },  
            {  
                //EMAIL  
                title: '发布时间',  
                field: 'otime',  
                align: 'center',
                width: 300
            },  
            {  
                title: '操作',  
                field: 'id',  
                align: 'center',
                width: 150,
                formatter: function (value, row, index) {//自定义显示可以写标签哦~  
                    return '<button type="button" class="btn btn-default" style="padding: 4px 20px;" onclick="getDetailInfo('+row.id+','+row.id+')">查看详情</button>';
                }  
            }  
  
        ]  
    });  
  
    
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数   
        $(".pull-right").css("display", "block"); 
        $("input[name='btSelectAll']").parent("div").parent("th").css("padding-left","0px");
    });
}

function getCulData(url){
	var t = $("#info_server").bootstrapTable({  
        url:url, 
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
        pageSize: 8,//如果设置了分页，页面数据条数  
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
        idField: "id",//指定主键列  
        columns: [  
            {  
                field: '',//json数据中rows数组中的属性名  
                checkbox:true,
                align: 'center',//水平居中  
                valign:'middle',
                width: 50    
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
                //EMAIL  
                title: '发布时间',  
                field: 'otime',  
                align: 'center',
                width: 300
            },  
            {  
                title: '操作',  
                field: 'id',  
                align: 'center',
                width: 150,
                formatter: function (value, row, index) {//自定义显示可以写标签哦~  
                    return '<button type="button" class="btn btn-default" style="padding: 4px 20px;" onclick="getDetailInfo('+row.id+','+row.id+')">查看详情</button>';
                }  
            }  
  
        ]  
    });  
  
    
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数   
        $(".pull-right").css("display", "block"); 
        $("input[name='btSelectAll']").parent("div").parent("th").css("padding-left","0px");
    });
}

function getDetailInfo(id,type){
	clearData();
	if($("#Title").text()=='家族荣誉'){
		if(id==type){
			showData("家族荣誉详情","荣誉截图","荣誉内容");
		}else{
			showData("家族荣誉修改","荣誉截图","荣誉内容");
		}
		showDetail("hon","../../fhon/findHonDety.do?id=",id);
	}else if($("#Title").text()=='家族文化'){
		if(id==type){
			showData("家族文化详情","文化类型","文化内容");
		}
		else{
			showData("家族文化修改","文化类型","文化内容");
		}
		showDetail("cul","../../fcul/findCulById.do?id=",id);
	}else{
		if(id==type){
			showData("家族资讯详情","资讯截图","资讯内容");
		}else{
			showData("家族资讯修改","资讯截图","资讯内容");
		}		
		showDetail("news","../../fnews/findFamNewsDety.do?id=",id);
	}
	
	
	$('#doc-modal-info').modal({
		closeViaDimmer: 0,
		width: 800
	})
}

function showDetail(flag,url,id){
		$.ajax({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:url+id,
			dataType:"json",
			success:function(result){
				$("#DeTitle").val(result.title);				
				editor.setContent(result.content);				
				$("#operTime").text(result.otime);
				if($("#Title").text()=='家族资讯'||$("#Title").text()=='家族荣誉'){
					$("#pics").append(
							'<img src="'+result.url+'" style="height:98px;width:150px" />'
					)
					$("#operName").text(result.name);
					if($("#Title").text()=='家族资讯'){
						$("#DeMemo").val(result.memo);
					}					
				}else{
					$("#utype").text(result.type);
				}
				
				if($("#head").text()=="家族文化修改"){
					$("#stypesel").find("option[value='"+result.type+"']").attr("selected","selected");
					$("#ytype").val(result.type);
				}
			}
		})
}

function clearData(){
	$("#DeTitle").val("");
	editor.setContent("");
	$("#operName").text();
	$("#operTime").text();
	$("#doc").val("");
	$("#pics").empty();	
	$("#DeMemo").val("");
	$("#DeMemo").removeAttr("readonly");
	$("#DeTitle").removeAttr("readonly");
	$("#doc").css("display","block");
}

function showData(head,screen,content){
	var t=$("#detail").children("label");
	var y=$("#detail").children("div");
	
	if(head=="家族荣誉修改"||head=="家族资讯修改"||head=="家族文化修改"){
		$("#DeMemo").removeAttr("readonly");
		$("#DeTitle").removeAttr("readonly");
		$("#doc").css("display","block");
	}else{
		$("#DeMemo").attr("readonly","readonly");
		$("#DeTitle").attr("readonly","readonly");
		$("#doc").css("display","none");
	}
	
	if(head=="家族资讯详情"||head=="家族资讯修改"){
		$("#memo").css("display","block");
	}else{
		$("#memo").css("display","none");
	}
	
	if(head=="家族文化修改"){
		$("#stype").css("display","block");
		$("#pics").css("display","none");
		$("#doc").css("display","none");
	}else{
		$("#pics").css("display","block");
		$("#stype").css("display","none");
		
	}
	
	if(head=="家族文化详情"){
		$("#sShot").css("display","none");
		t.eq(0).css("display","none");
		y.eq(0).css("display","none");
		t.eq(1).css("display","block");
		y.eq(1).css("display","block");
	}else{
		$("#sShot").css("display","block");
		if(head=="家族文化修改"){
			t.eq(0).css("display","none");
			y.eq(0).css("display","none");
		}else{
			t.eq(0).css("display","block");
			y.eq(0).css("display","block");
		}
		t.eq(1).css("display","none");
		y.eq(1).css("display","none");
	}
	
	$("#head").text(head);	
	$("#screenShot").text(screen);
	$("#Content").text(content);	
	$("#head").css("float","left");
	$("#head").css("padding-left","41%");
	$("#detail").css("display","block");
	
	close(head);
}

function addDe(){
	if($("#Title").text()=='家族荣誉'){
		add("增加家族荣誉","荣誉截图","荣誉内容");
	}else if($("#Title").text()=='家族资讯'){
		add("增加家族资讯","资讯截图","资讯内容");
	}else{
		add("增加家族文化","","文化内容");
	}
}

function add(head,shot,content){
	var t=$("#detail").children("label");
	var y=$("#detail").children("div");
	
	if(head=="增加家族资讯"){
		$("#memo").css("display","block");
	}else{
		$("#memo").css("display","none");
	}
	
	if(head=="增加家族文化"){
		$("#sShot").css("display","none");
		t.eq(0).css("display","none");
		y.eq(0).css("display","none");
		t.eq(1).css("display","block");
		y.eq(1).css("display","block");
		t.eq(2).css("display","none");
		y.eq(2).css("display","none");
		$("#typesel").css("display","block");
		$("#utype").css("display","none");
	}else{
		$("#sShot").css("display","block");
		t.eq(0).css("display","block");
		y.eq(0).css("display","block");
		t.eq(1).css("display","none");
		y.eq(1).css("display","none");
		$("#detail").css("display","none");
	}
	
	$("#head").text(head);
	$("#screenShot").text(shot);
	$("#Content").text(content);
	clearData();
	show();
	$('#head').removeAttr("style");	
	$('#doc-modal-info').modal({
		closeViaDimmer: 0,
		width: 800
	})
}

function subDe(){
	var url;
	if($("#Title").text()=='家族荣誉'){
		url="../../fhon/addFamHon.do";
	}else if($("#Title").text()=='家族资讯'){
		url="../../fnews/addFamNews.do";
	}else{
		url="../../fcul/addFamCul.do"
		if($("#typesel").val()==""){
			alert("请选择类型！！！");
			return;
		}else{
			$("#ytype").val($("#typesel").val());
		}
	}
		
	$("#ucontent").val(editor.getContent());
	if($("#DeTitle").val()==""){
		alert("请输入标题！！！");
	}else{
		$("#DeInfo").children("form").ajaxSubmit({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:url,
			dataType: 'json',
			success:function(data){
				
			}
		})
	}
	
}

function updateDe(){
	var a=$("#info_server").bootstrapTable("getSelections");
	if(a.length<=0){
		alert("请选择一行！！！");
	}else if(a.length>1){
		alert("请只选择一行！！！");
	}else{
		getDetailInfo(a[0].id,"修改");
	}
}

function deleteDe(){
	var a=$("#info_server").bootstrapTable("getSelections");
	var list=new Array();
	if(a.length>0){
		for(var i=0;i<a.length;i++){
			list[i]=a[i].id+"";
		}
		var idlist={
			"idlist":list
		}
		if($("#Title").text()=='家族荣誉'){
			del(idlist,"../../fhon/deleteFamHon.do");
		}else if($("#Title").text()=='家族资讯'){
			del(idlist,"../../fnews/deleteFamNews.do");
		}else{
			del(idlist,"../../fcul/deleteFamCul.do");
		}
	}else{
		alert("请选择一行！！！");
	}
	
}

function del(data,url){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:url,
		data:JSON.stringify(data),
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

function updDe(){
	var a=$("#info_server").bootstrapTable("getSelections");
	$("#ucontent").val(editor.getContent());
	if($("#head").text()=="家族荣誉修改"){
		postUpdate("../../fhon/updateFamHon.do?id=",a[0].id);
	}else if($("#head").text()=="家族资讯修改"){
		postUpdate("../../fnews/updateFamNews.do?id=",a[0].id);
	}else if($("#head").text()=="家族文化修改"){
		postUpdate("../../fcul/updateFamCul.do?id=",a[0].id);
	}
}

function postUpdate(url,id){
	$("#DeInfo").children("form").ajaxSubmit({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:url+id,
		dataType: 'json',
		success:function(data){
			
		}
	})
}

function show(){
	$("#closeFlag").css("display","none");
	$("#postDe").css("display","block");
	$("#updateDe").css("display","none"); 
	$("#closeModal").css("display","block");
}

function close(head){
	if(head=='家族荣誉修改'||head=='家族资讯修改'||head=='家族文化修改'){
		$("#updateDe").css("display","block");
		$("#closeModal").css("display","block");
		
	}else{
		$("#updateDe").css("display","none");
		$("#closeModal").css("display","none");
		$("#closeFlag").css("display","inline-block");
	}	
	$("#postDe").css("display","none");	
}

function setImagePreviews(avalue){
	   var docObj = document.getElementById("doc");
	   var dd = document.getElementById("pics");
	   dd.innerHTML = "";
	   var fileList = docObj.files;
	   for (var i = 0; i < fileList.length; i++) {
		   dd.innerHTML += "<div style='float:left' > <img id='img" + i + "'  /> </div>";
		   var imgObjPreview = document.getElementById("img"+i); 
		   if (docObj.files && docObj.files[i]) {
			   //火狐下设置img属性
			   imgObjPreview.style.display = 'block';
			   imgObjPreview.style.width = '300px';
			   imgObjPreview.style.height = '98px';		   
			   imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
		   }else{
			   docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById("img" + i);
            localImagId.style.width = "300px";
            localImagId.style.height = "98px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
		   }
	   }
	   return true;
}