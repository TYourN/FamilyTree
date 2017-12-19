<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>

	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="firstname" class="col-sm-1 control-label">标题</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="firstname"
						placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group" style="padding-left:4.6%;padding-right: 9.7%">
				<script id="editor" type="text/plain"></script>
			</div>
			<div class="form-group">
				<div class="col-sm-10" style="padding-left: 5%">
					<button id="ok" onclick="addOk()" type="submit"
						class="btn btn-primary">发布</button>
				</div>
			</div>
		</form>
	</div>
	<!-- <form class="form-horizontal" role="form">
		<div class="form-group">
			<label for="firstname" class="col-sm-1 control-label">标题</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="firstname"
					placeholder="请输入标题">
			</div>
		</div>
		<div class="form-group">
			<script id="editor" type="text/plain"></script> 
			<div id="editor">
				
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10" style="padding-left: 5%">
				<button id="ok" onclick="addOk()" type="submit" class="btn btn-primary">发布</button>
			</div>
		</div> 
	</form> -->
	
	

   
   <!-- <script type="text/javascript">
         //实例化编辑器
         //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
         var ue = UE.getEditor( 'editor', {
           autoHeightEnabled: true,
           autoFloatEnabled: true,
           /* initialFrameWidth: 1042, */
           initialFrameHeight:483
         });
         
         function addOk(){
        	 var con={"content":ue.getContent()};
        	 $.ajax({
        		 type:"POST",
        		 contentType:"application/json;charset=UTF-8",
        		 url:"${pageContext.request.contextPath}/ftest/Test.do",
        		 data:JSON.stringify(con),
        		 dataType:"json",
        		 success:function(result){
        			 
        		 }
        	 })
         }
    </script> -->
    
    <script type="text/javascript">
    	var ue=new baidu.editor.ui.Editor({
    		autoHeightEnabled: false,
            autoFloatEnabled: false,
            initialFrameWidth:'100%',
            initialFrameHeight:483
    	});
    	ue.render("editor");
    </script>
</body>
</html>