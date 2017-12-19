<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery.fileupload.js"></script>
</head>
<body>
	<div id="file_size"
		style="width: 400px; border-bottom: 1px solid #C0C0C0;"></div>
	<div style="width: 400px; height: 300px;">
		<div
			style="font-size: 13px; font-weight: bold; color: #808080; font-family: '微软雅黑', '黑体', '华文细黑';">对于上传按钮和选择文件按钮，你可以使用其他任何形式的元素，可以是图片或一切你能想到的东西，都是可以的</div>
		<input id="Button1" type="button" value="选择文件" /> <input id="files"
			type="button" value="上传" />
		<!-- <div style="width: 420px; height: 180px; overflow:auto;border:1px solid #C0C0C0;">-->
		<div id="fileList" style="margin-top: 10px; padding-top: 10px; font-size: 13px; width: 400px"></div>
	</div>
	<br />
	<div id="testdiv"></div>
</body>
<script type="text/javascript">
	$(function() { 
		var btn = $("#Button1").uploadFile({
			url:"${pageContext.request.contextPath}/ftest/Upload.do",
			fileSuffixs : [ "jpg", "png", "gif", "txt" ],
			maximumFilesUpload : 10,//最大文件上传数  
			onComplete : function(msg) {
				$("#testdiv").append(msg + "<br/>");
			},
			onAllComplete : function() {
				alert("全部上传完成");
			},
			isGetFileSize : true,//是否获取上传文件大小，设置此项为true时，将在onChosen回调中返回文件fileSize和获取大小时的错误提示文本errorText  
			onChosen : function(file, obj, fileSize, errorText) {
				if (!errorText) {
					$("#file_size").text(file + "文件大小为：" + fileSize + "KB");
				} else {
					alert(errorText);
					return false;
				}
				return true;//返回false将取消当前选择的文件  
			},
			perviewElementId : "fileList", //设置预览图片的元素id  
			perviewImgStyle : {
				width : '100px',
				height : '100px',
				border : '1px solid #ebebeb'
			}
		//设置预览图片的样式  
		});

		var upload = btn.data("uploadFileData");

		$("#files").click(function() {
			upload.submitUpload();
		});
	});
</script>
</html>