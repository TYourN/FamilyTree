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
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
   <div>
      <script id="editor" type="text/plain"></script>   
   </div>
   <button type="button" id="ok" onclick="addOk()" class="btn btn-primary">发布</button>  
   <script type="text/javascript">
         //实例化编辑器
         //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
         var ue = UE.getEditor( 'editor', {
           autoHeightEnabled: true,
           autoFloatEnabled: true,
           initialFrameWidth: 690,
           initialFrameHeight:483
         });
         
         function addOk(){
        	 var con={"content":ue.getContent()};
        	 $.ajax({
        		 type:"POST",
        		 contentType:"application/json;charset=UTF-8",
        		 url:"${pageContext.request.contextPath}/fSname/Test.do",
        		 data:JSON.stringify(con),
        		 dataType:"json",
        		 success:function(result){
        			 
        		 }
        	 })
         }
    </script>
</body>
</html>