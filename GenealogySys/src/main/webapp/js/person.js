$(document).ready(function(){
	getUserInfo();
	getRFInfo("#roles_server","../../frole/findRoleInfo.do");
	getRFInfo("#funcs_server","../../ffunc/findAllfunc.do");
	getParent();
	
	$("#excelfile").change(function(){
		var reader = new FileReader();
		var f=$("#excelfile").get(0).files[0];
		reader.readAsDataURL(f);
		reader.onload = function(e) {
			var formData = new FormData();
			formData.append("eFile",f);
			$.ajax({
				type:"post",
				url:"../../fuser/insertUserByExcel.do",
				async:true,
				data:formData,
		        contentType: false,  
		        processData: false,
		        dataType:"json",
				success:function(data){
					
				}
			})
		}
	})
})

function getUserInfo(){
	var t = $("#users_server").bootstrapTable({  
        url:'../../fuser/findAllperson.do', 
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
        singleSelect: true,//设置True 将禁止多选  
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
        idField: "userinfoid",//指定主键列  
        columns: [  
            {  
                field: '',//json数据中rows数组中的属性名  
                checkbox:true,
                align: 'center',//水平居中  
                valign:'middle',
                width: 50    
            },  
            {  
                title: '用户名',  
                field: 'username',  
                align: 'center',
                width: 260
            }, 
            {  
                title: '真实姓名',  
                field: 'name',  
                align: 'center',
                width: 140
            },
            {  
                title: '操作',  
                field: 'userinfoid',  
                align: 'center',
                width: 140,
                formatter: function (value, row, index) {//自定义显示可以写标签哦~  
                    return '<button type="button" class="btn btn-default" style="padding: 4px 8px;" onclick="getDetailInfo('+row.userinfoid+')">查看详情</button>';
                }  
            } 
        ]  
    });  
  
    
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数  
    	$(".pull-left").css("display", "none");
        $(".pull-right").css("display", "block"); 
        $("input[name='btSelectAll']").parent("div").parent("th").css("padding-left","0px");
    });
    
    t.on('click-row.bs.table',function(e,row,ele){    	
    	$(ele).parent("tbody").children("tr").each(function(){
    		if($(this).index()!=$(ele).index()){
    			$(this).children("td").eq(0).children("input").prop('checked',false);
    		}
    	})
    	if($(ele).children("td").eq(0).children("input").is(':checked')){
    		$(ele).children("td").eq(0).children("input").prop('checked',false);
    		clearRole();
    		clearFunc();
    	}else{
    		$(ele).children("td").eq(0).children("input").prop('checked',true);
    		clearRole();
    		clearFunc();
    		findUserRF(row.userinfoid);
    	}
    })
}

function getRFInfo(id,url){
	var t = $(id).bootstrapTable({  
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
        pageSize: 12,//如果设置了分页，页面数据条数  
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
                width: 260
            }
        ]  
    });  
  
    
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数  
    	$(".pull-left").css("display", "none");
        $(".pull-right").css("display", "block");
        $("input[name='btSelectAll']").parent("div").parent("th").css("padding-left","0px");
    });
    
    if(id=="#roles_server"){
    	t.on('click-row.bs.table',function(e,row,ele){
    		$(ele).parent("tbody").children("tr").each(function(){
        		if($(this).index()!=$(ele).index()){
        			if($(this).index()%2==0){
        				$(this).css("background-color","#f9f9f9");
        			}else{
        				$(this).css("background-color","#ffffff");
        			}       			
        		}
        	})
        	if($(ele).css("background-color")=="rgb(249, 202, 202)"){
        		clearFuncC();
        		if($(ele).index()%2==0){
    				$(ele).css("background-color","#f9f9f9");
    			}else{
    				$(ele).css("background-color","#ffffff");
    			}        		
        	}else{
        		clearFuncC();
        		$(ele).css("background-color","#f9caca");
        		findRoleF(row.id);
        	}
    	})
    }else{
    	t.on('click-row.bs.table',function(e,row,ele){
        	if($(ele).css("background-color")=="rgb(249, 202, 202)"){
        		if($(ele).index()%2==0){
    				$(ele).css("background-color","#f9f9f9");
    			}else{
    				$(ele).css("background-color","#ffffff");
    			}        		
        	}else{
        		$(ele).css("background-color","#f9caca");
        	}
    	})
    }
}

function getDetailInfo(id){
	uclearData();
	ucheckInfo();
	uread();
	$("#head").text("用户信息查看");
	showDetail("../../fuser/findpersonById.do?id=",id);
	
	$('#users-info').modal({
		closeViaDimmer: 0,
		width: 600
	})
}

function showDetail(url,id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:url+id,
		dataType:"json",
		success:function(result){
			$("#UserName").val(result.username);
			$("#PassWord").val(result.password);
			$("#Name").val(result.name);
			$("#Address").val(result.address);
			$("#Phone").val(result.phone);
			$("#Birth").val(result.birth);
			$("#Sex").val(result.sex);
			$("#Age").val(result.age);
			$("#Marriage").val(result.marriage);
			$("#Identity").val(result.identity);
			$("#Exoducs").val(result.exoducs);
			if(result.pic!=null){
				$("#pics").append(
						'<img src="'+result.pic+'" style="height:98px;width:150px" />'
				)
			}
		}
	})
}

function showRDetail(id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../frole/findRInfoById.do?id="+id,
		dataType:"json",
		success:function(result){
			$("#Title").val(result.title);
			$("#Memo").val(result.memo);
		}
	})
}

function showFDetail(id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../ffunc/findFuncById.do?id="+id,
		dataType:"json",
		success:function(result){
			$("#FuncTitle").val(result.title);
			$("#parentsel").find("option[value='"+result.parentid+"']").attr("selected","selected");
			$("#FuncUrl").val(result.url);
			$("#FuncMemo").val(result.memo);
			$("#typesel").find("option[value='"+result.flag+"']").attr("selected","selected");
		}
	})
}

function userAdd(){
	uclearData();
	uwrite();
	uaddInfo();
	$("#head").text("增加用户信息");	
	$('#users-info').modal({
		closeViaDimmer: 0,
		width: 600
	})
}

function roleAdd(){
	rclearData();
	raddInfo();
	$("#rolehead").text("增加角色信息");
	$('#roles-info').modal({
		closeViaDimmer: 0,
		width: 400
	})
}

function funcAdd(){
	fclearData();
	faddInfo();
	$("#funchead").text("增加功能信息");
	
	$('#func-info').modal({
		closeViaDimmer: 0,
		width: 400
	})
}

function userUpdate(){
	uclearData();
	uwrite();
	uupdateInfo();
	var a=$("#users_server").bootstrapTable("getSelections");
	if(a.length<=0){
		alert("请选择一行！！！");
	}else if(a.length>1){
		alert("请只选择一行！！！");
	}else{
		$("#head").text("用户信息修改");
		showDetail("../../fuser/findpersonById.do?id=",a[0].userinfoid);		
		$('#users-info').modal({
			closeViaDimmer: 0,
			width: 600
		})
	}
}

function roleUpdate(){
	rclearData();
	rupdateInfo();
	var a=$("#roles_server").bootstrapTable("getSelections");
	if(a.length<=0){
		alert("请选择一行！！！");
	}else if(a.length>1){
		alert("请只选择一行！！！");
	}else{
		$("#rolehead").text("角色信息修改");
		showRDetail(a[0].id);
		$('#roles-info').modal({
			closeViaDimmer: 0,
			width: 400
		})
	}
}

function funcUpdate(){
	fclearData();
	fupdateInfo();
	var a=$("#funcs_server").bootstrapTable("getSelections");
	if(a.length<=0){
		alert("请选择一行！！！");
	}else if(a.length>1){
		alert("请只选择一行！！！");
	}else{
		$("#funchead").text("功能信息修改");
		showFDetail(a[0].id);
		$('#func-info').modal({
			closeViaDimmer: 0,
			width: 400
		})
	}
}

function userDel(){
	var a=$("#users_server").bootstrapTable("getSelections");
	var list=new Array();
	if(a.length>0){
		for(var i=0;i<a.length;i++){
			list[i]=a[i].userinfoid+"";
		}
		var idlist={
				"idlist":list
		}
		$('#my-confirm').modal({
			onConfirm:function(){
				delUser(idlist);
			}	
		})
	}else{
		alert("请选择一行！！！");
	}
}

function roleDel(){
	var a=$("#roles_server").bootstrapTable("getSelections");
	var list=new Array();
	if(a.length>0){
		for(var i=0;i<a.length;i++){
			list[i]=a[i].id+"";
		}
		var idlist={
				"idlist":list
		}
		$('#my-confirm').modal({
			onConfirm:function(){
				delRole(idlist);
			}	
		})
	}else{
		alert("请选择一行！！！");
	}
}

function funcDel(){
	var a=$("#funcs_server").bootstrapTable("getSelections");
	var list=new Array();
	if(a.length>0){
		for(var i=0;i<a.length;i++){
			if(a[i].id==0){
				alert(a[i].title+"是父标题，请确认是否删除！！！");
			}else{
				list[i]=a[i].id+"";
			}			
		}
		var idlist={
				"idlist":list
		}
		$('#my-confirm').modal({
			onConfirm:function(){
				delFunc(idlist);
			}	
		})
	}else{
		alert("请选择一行！！！");
	}
}

function addUserInfo(){
	if($("#UserName").val()==""||$("#PassWord").val()==""||$("#Name").val()==""||$("#Phone").val()==""||$("#Identity").val()==""){
		alert("您有必填项没有填写，请填写！！！");
		return;
	}else{
		$("#UserInfo").children("form").ajaxSubmit({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:"../../fuser/addPersonInfo.do",
			dataType: 'json',
			success:function(data){
				
			}
		})
	}
}

function addRoleInfo(){
	if($("#RoleTitle").val()==""){
		alert("您有必填项没有填写，请填写！！！");
		return;
	}else{
		var rContent={
				"title":$("#Title").val(),
				"memo":$("#Memo").val()
		}
		$.ajax({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:"../../frole/addRoleInfo.do",
			data:JSON.stringify(rContent),
			dataType: 'json',
			success:function(data){
				if(data==1){
					alert("添加成功！！！")
					window.location.reload();
				}else{
					alert("添加失败。。。请联系管理员！！！");
					window.location.reload();
				}
			}
		})
	}
}

function addFuncInfo(){
	if($("#FuncTitle").val()==""||$("#typesel").val()==""){
		alert("您有必填项没有填写，请填写！！！");
		return;
	}else{
		var content={
			"title":$("#FuncTitle").val(),
			"parent":$("#parentsel").val(),
			"url":$("#FuncUrl").val(),
			"memo":$("#FuncMemo").val(),
			"flag":$("#typesel").val()
		}
		$.ajax({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:"../../ffunc/addFuncInfo.do",
			data:JSON.stringify(content),
			dataType: 'json',
			success:function(data){
				if(data==1){
					alert("添加成功！！！")
					window.location.reload();
				}else{
					alert("添加失败。。。请联系管理员！！！");
					window.location.reload();
				}
			}
		})
		
	}
}

function updateUserInfo(){
	var a=$("#users_server").bootstrapTable("getSelections");
	if($("#UserName").val()==""||$("#PassWord").val()==""||$("#Name").val()==""||$("#Phone").val()==""||$("#Identity").val()==""){
		alert("您有必填项没有填写，请填写！！！");
		return;
	}else{
		$("#UserInfo").children("form").ajaxSubmit({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:"../../fuser/updatePersonInfo.do?id="+a[0].userinfoid,
			dataType: 'json',
			success:function(data){
				if(data==1){
					alert("修改成功！！！")
					window.location.reload();
				}else{
					alert("修改失败。。。请联系管理员！！！");
					window.location.reload();
				}
			}
		})
	}
}

function updateRoleInfo(){
	var a=$("#roles_server").bootstrapTable("getSelections");
	if($("#Title").val()==""){
		alert("您有必填项没有填写，请填写！！！");
		return;
	}else{
		var content={
			"roleid":a[0].id,
			"title":$("#Title").val(),
			"memo":$("#Memo").val()
		};
		$.ajax({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:"../../frole/updateRoleInfo.do",
			data:JSON.stringify(content),
			dataType: 'json',
			success:function(data){
				
			}
		});
	}	
}

function updateFuncInfo(){
	var a=$("#funcs_server").bootstrapTable("getSelections");
	if($("#FuncTitle").val()==""||$("#typesel").val()==""){
		alert("您有必填项没有填写，请填写！！！");
		return;
	}else{
		var content={
				"funcid":a[0].id,
				"title":$("#FuncTitle").val(),
				"parent":$("#parentsel").val(),
				"url":$("#FuncUrl").val(),
				"memo":$("#FuncMemo").val(),
				"flag":$("#typesel").val()
			}
			$.ajax({
				type:"POST",
				contentType:"application/json;charset=UTF-8",
				url:"../../ffunc/updateFunc.do",
				data:JSON.stringify(content),
				dataType: 'json',
				success:function(data){
					if(data==1){
						alert("修改成功！！！")
						window.location.reload();
					}else{
						alert("修改失败。。。请联系管理员！！！");
						window.location.reload();
					}
				}
			})
	}
}

function delUser(idlist){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fuser/delPersonInfo.do",
		data:JSON.stringify(idlist),
		dataType:"json",
		success:function(result){
			if(result!=0){
				alert("删除成功！！！");
				window.location.reload();
			}else{
				alert("删除失败。。。请联系管理员！！！");
				window.location.reload();
			}
		}
	})
}

function delRole(idlist){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../frole/deleteRoleInfo.do",
		data:JSON.stringify(idlist),
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

function delFunc(idlist){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../ffunc/delFunc.do",
		data:JSON.stringify(idlist),
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

function getParent(){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../ffunc/findParents.do",
		dataType:"json",
		success:function(result){
			for(var i=0;i<result.length;i++){
				$("#parentsel").append(
					'<option value="'+result[i].funcid+'">'+result[i].title+'</option>'
				)
			}	
		}
	})
}

function uclearData(){
	$("#UserName").val("");
	$("#PassWord").val("");
	$("#Name").val("");
	$("#Address").val("");
	$("#Phone").val("");
	$("#Birth").val("");
	$("#Sex").val("");
	$("#Age").val("");
	$("#Marriage").val("");
	$("#Identity").val("");
	$("#Exoducs").val("");
	$("#pics").empty();
}

function rclearData(){
	$("#Title").val("");
	$("#Memo").val("");
}

function fclearData(){
	$("#FuncTitle").val("");
	$("#FuncUrl").val("");
	$("#FuncMemo").val("");
	$("#Memo").val("");
}

function uread(){
	$("#UserName").attr("readonly","readonly");
	$("#PassWord").attr("readonly","readonly");
	$("#Name").attr("readonly","readonly");
	$("#Address").attr("readonly","readonly");
	$("#Phone").attr("readonly","readonly");
	$("#Birth").attr("readonly","readonly");
	$("#Sex").attr("readonly","readonly");
	$("#Age").attr("readonly","readonly");
	$("#Marriage").attr("readonly","readonly");
	$("#Identity").attr("readonly","readonly");
	$("#Exoducs").attr("readonly","readonly");
}

function uwrite(){
	$("#UserName").removeAttr("readonly");
	$("#PassWord").removeAttr("readonly");
	$("#Name").removeAttr("readonly");
	$("#Address").removeAttr("readonly");
	$("#Phone").removeAttr("readonly");
	$("#Birth").removeAttr("readonly");
	$("#Sex").removeAttr("readonly");
	$("#Age").removeAttr("readonly");
	$("#Marriage").removeAttr("readonly");
	$("#Identity").removeAttr("readonly");
	$("#Exoducs").removeAttr("readonly");
} 

function ucheckInfo(){
	$("#UserName").css("background-color","#eee");
	$("#PassWord").css("background-color","#eee");
	$("#Name").css("background-color","#eee");
	$("#Phone").css("background-color","#eee");
	$("#Identity").css("background-color","#eee");
	$("#updateDe").css("display","none");
	$("#postDe").css("display","none");
	$("#closeModal").css("display","block");
	$("#closeModal").css("margin-right","54%");
	$("#doc").css("display","none");
}

function uaddInfo(){
	$("#UserName").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#PassWord").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#Name").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#Phone").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#Identity").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#updateDe").css("display","none");
	$("#postDe").css("display","block");
	$("#closeModal").css("display","block");
	$("#closeModal").css("margin-right","39%");
	$("#doc").css("display","block");
}

function raddInfo(){
	$("#Title").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#updateRole").css("display","none");
	$("#postRole").css("display","block");
	$("#closeRole").css("display","block");
	$("#closeRole").css("margin-right","33%");
}

function faddInfo(){
	$("#FuncTitle").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#typesel").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#updateFunc").css("display","none");
	$("#postFunc").css("display","block");
	$("#closeFunc").css("display","block");
	$("#closeFunc").css("margin-right","33%");
}

function uupdateInfo(){
	$("#UserName").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#PassWord").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#Name").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#Phone").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#Identity").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#updateDe").css("display","block");
	$("#postDe").css("display","none");
	$("#closeModal").css("display","block");
	$("#closeModal").css("margin-right","39%");
	$("#doc").css("display","block");
}

function rupdateInfo(){
	$("#Title").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#updateRole").css("display","block");
	$("#postRole").css("display","none");
	$("#closeRole").css("display","block");
	$("#closeRole").css("margin-right","33%");
}

function fupdateInfo(){
	$("#FuncTitle").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#typesel").css("background-color","rgba(251, 27, 27, 0.36)");
	$("#updateFunc").css("display","block");
	$("#postFunc").css("display","none");
	$("#closeFunc").css("display","block");
	$("#closeFunc").css("margin-right","33%");
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
			   imgObjPreview.style.width = '200px';
			   imgObjPreview.style.height = '80px';		   
			   imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
		   }else{
			   docObj.select();
         var imgSrc = document.selection.createRange().text;
         var localImagId = document.getElementById("img" + i);
         localImagId.style.width = "200px";
         localImagId.style.height = "80px";
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

function findUserRF(id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fuser/findURF.do?id="+id,
		dataType:"json",
		success:function(result){
			var rlist=result.role;
			var flist=result.func;
			var rtable=$("#roles_server").children("tbody").children("tr");
			var ftable=$("#funcs_server").children("tbody").children("tr");
			rtable.each(function(){
				if(rlist.length!=0){
					for(var i=0;i<rlist.length;i++){
						if($(this).children("td").eq(0).children("input").val()==rlist[i].roleid){
							$(this).children("td").eq(0).children("input").prop('checked',true);
						}
					}
				}
			})
			
			ftable.each(function(){
				if(flist.length!=0){
					for(var i=0;i<flist.length;i++){
						if($(this).children("td").eq(0).children("input").val()==flist[i].funcid){
							$(this).children("td").eq(0).children("input").prop('checked',true);
						}
					}
				}
			})
		}
	})
}

function findRoleF(id){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../frole/findRFunc.do?id="+id,
		dataType:"json",
		success:function(result){
			var ftable=$("#funcs_server").children("tbody").children("tr");
			ftable.each(function(){
				if(result.length!=0){
					for(var i=0;i<result.length;i++){
						if($(this).children("td").eq(0).children("input").val()==result[i].funcid){
							$(this).css("background-color","#f9caca");
						}
					}
				}
			})
		}
	})
}

function saveUR(){
	var uid=null;
	var list=new Array();
	var utable=$("#users_server").children("tbody").children("tr");
	var rtable=$("#roles_server").children("tbody").children("tr");
	utable.each(function(){
		if($(this).children("td").eq(0).children("input").is(':checked')){
			uid=$(this).children("td").eq(0).children("input").val();
		}
	})
	rtable.each(function(){
		if($(this).children("td").eq(0).children("input").is(':checked')){
			list.push($(this).children("td").eq(0).children("input").val()+"");
		}
	})
	if(uid==null){
		alert("请选择用户！！！");
		return;
	}else{
		var idli={
				"uid":uid,
				"idlist":list
		}
		$.ajax({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:"../../fuser/updateUR.do",
			data:JSON.stringify(idli),
			dataType:"json",
			success:function(result){
				if(result!=0){
					alert("修改成功！！！");
					window.location.reload();
				}else{
					alert("修改失败。。。请联系管理员！！！");
					window.location.reload();
				}
			}
		})
	}
}

function saveRF(){
	var rid=null;
	var list=new Array();
	var rtable=$("#roles_server").children("tbody").children("tr");
	var ftable=$("#funcs_server").children("tbody").children("tr");
	rtable.each(function(){
		if($(this).css("background-color")=="rgb(249, 202, 202)"){
			rid=$(this).children("td").eq(0).children("input").val();
		}
	})
	ftable.each(function(){
		if($(this).css("background-color")=="rgb(249, 202, 202)"){
			list.push($(this).children("td").eq(0).children("input").val()+"");
		}
	})
	if(rid==null){
		alert("请选择角色！！！");
		return;
	}else{
		var idli={
				"rid":rid,
				"idlist":list
		}
		$.ajax({
			type:"POST",
			contentType:"application/json;charset=UTF-8",
			url:"../../frole/updateRF.do",
			data:JSON.stringify(idli),
			dataType:"json",
			success:function(result){
				if(result!=0){
					alert("修改成功！！！");
					window.location.reload();
				}else{
					alert("修改失败。。。请联系管理员！！！");
					window.location.reload();
				}
			}
		})
	}
}

function clearRole(){
	$("#roles_server").children("tbody").children("tr").each(function(){
		$(this).children("td").eq(0).children("input").prop('checked',false);
	})
}

function clearFunc(){
	$("#funcs_server").children("tbody").children("tr").each(function(){
		$(this).children("td").eq(0).children("input").prop('checked',false);
	})
}

function clearFuncC(){
	$("#funcs_server").children("tbody").children("tr").each(function(){
		if($(this).index()%2==0){
			$(this).css("background-color","#f9f9f9");
		}else{
			$(this).css("background-color","#ffffff");
		} 
	})
}

function upExcel(){
	$("#excelfile").trigger("click");
}