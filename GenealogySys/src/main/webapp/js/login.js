/**
 * 
 */
$(document).ready(function(){
	$.ajax({
		url:"../fuser/Logout.do",
		type:"post",
		success:function(data){
		}
	})
	
	$("#upSql").click(function(){
		$.ajax({
			url:"../fdb/importDB.do",
			type:"get",
			success:function(data){
				if(data==true){
					alert("数据库导入成功！！！");
				}else{
					alert("导入失败！！！")
				}
			}
		})
	})
})

function UserLogin(){
	if($("#signin-name").val()==""){
		alert("请输出用户名！！！");
		return;
	}else if($("#signin-password").val()==""){
		alert("请输出密码！！！");
		return;
	}else{
		var options={
			url:"../fuser/userLogin.do",
			type:"post",
			success:function(data){
				if(data==1){
					location.href="manage/index.jsp";					
				}else if(data==0){
					alert("登录失败,请核对用户名和密码是否正确！！！");
				}else if(data==2){
					alert("登录失败,数据库出现问题。请联系管理员！！！");
				}else if(data==3){
					$('#myModal').modal('show');
				}else if(data==4){
					location.href="manage/admin.jsp"
				}
			}
		}
		$("#userInfo").ajaxSubmit(options);
	}	
	
	
}