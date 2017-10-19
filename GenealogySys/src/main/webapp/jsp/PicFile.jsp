<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/fhp/addFirstPics.do" method="post" enctype="multipart/form-data">
      <input type="file" name="file" id="doc" multiple="multiple" style="width:150px;" onchange="javascript:setImagePreviews();" accept="image/*" />
      <input type="submit" name="提交">
    </form> 
    <div id="dd" style=" width:500px;"></div>
    <script type="text/javascript">   
       function setImagePreviews(avalue){
    	   var docObj = document.getElementById("doc");
    	   var dd = document.getElementById("dd");
    	   dd.innerHTML = "";
    	   var fileList = docObj.files;
    	   for (var i = 0; i < fileList.length; i++) {
    		   dd.innerHTML += "<div style='float:left' > <img id='img" + i + "'  /> </div>";
    		   var imgObjPreview = document.getElementById("img"+i); 
    		   if (docObj.files && docObj.files[i]) {
    			   //火狐下设置img属性
    			   imgObjPreview.style.display = 'block';
    			   imgObjPreview.style.width = '100px';
    			   imgObjPreview.style.height = '80px';		   
    			   imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
    		   }else{
    			   docObj.select();
                   var imgSrc = document.selection.createRange().text;
                   var localImagId = document.getElementById("img" + i);
                   localImagId.style.width = "100px";
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
    </script>
</body>
</html>