<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公共新闻管理</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendor/linearicons/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap-table.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/amaze/css/amazeui.min.css">
<script
	src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-table.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/amaze/js/amazeui.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="${pageContext.request.contextPath}/js/pubnews.js"></script>
<script src="${pageContext.request.contextPath}/js/tool.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-info">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px;">
				<h4 class="panel-title">公共新闻信息</h4>
			</div>
			<div class="panel-body">
				<table id="table_server" class="table table-hover table-striped"
					style="vertical-align: middle !important; text-align: center;">
				</table>
			</div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 25px;">
				<h4 class="panel-title">发布新闻</h4>
			</div>
			<div class="panel-body">	
				<form class="form-horizontal" role="form">
					<div class="form-group" style="padding-top: 15px">
						<label for="firstname" class="col-sm-1 control-label">标题</label>
						<div class="col-sm-11">
							<input id="ntitle" type="text" class="form-control" id="firstname"
								placeholder="请输入标题">
						</div>
					</div>
					<div class="form-group" style="padding-top: 15px">
						<label for="firstname" class="col-sm-1 control-label">标语</label>
						<div class="col-sm-11">
							<input id="nmemo" type="text" class="form-control" id="firstname"
								placeholder="请输入标语">
						</div>
					</div>
					<div id="reeditor" class="form-group" style="padding-left: 3%;padding-top: 15px">
						<script id="editor" type="text/plain"></script>
					</div>
				</form>
				<div class="col-sm-10" style="padding-left: 2%">
							<button id="ok" onClick="postNews()" class="btn btn-primary">发布</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="am-modal am-modal-no-btn" style="z-index: 850" id="doc-modal-1">
		<div class="am-modal-dialog">
			<div class="panel panel-info">
				<div class="panel-heading"
					style="padding-top: 20px; padding-bottom: 10px;">
					<h3 class="panel-title">公共新闻内容查看</h3>
				</div>
				<div id="newsInfo" class="panel-body">
					<form method="post" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">标题:</label>
							<div class="col-sm-10">
								<input id="title" type="text" class="form-control"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">标语:</label>
							<div class="col-sm-10">
								<input id="memo" type="text" class="form-control"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">新闻内容:</label>
							<div class="col-sm-10">
								<textarea class="am-validate" name="newscontent"
								    id="news" required></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">发帖时间:</label>
							<div class="col-sm-4" style="margin-top: 5px">
								<span style="float: left; font-size: 17px"></span>								
							</div>
							<span id="nameid" style="display:none"></span>
						</div>

						<div style="position: relative; bottom: 7%; padding-left: 29%">
							<button id="postRep" type="submit" class="btn btn-default"
								style="padding: 20px 45px; background-color: rgba(0, 255, 8, 0.44);float: left" onclick="updateInfo()">提交</button>
							<button id="closeModal" class="btn btn-default"
								data-am-modal-close
								style="padding: 20px 45px; background-color: rgba(255, 0, 118, 0.28); margin-right: 245px">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var $textArea = $('[name=newscontent');
  		var editor = UE.getEditor('news',{
  	  		toolbars:[['source','bold','indent','italic','underline','subscript','pasteplain','time','deleterow','deletecol','splittorows','splittocols','inserttitle','fontfamily','fontsize','forecolor','lineheight','imagecenter','touppercase','tolowercase','simpleupload']],
	  		wordCount:true,  
	  		elementPathEnabled:true,
	  		autoHeightEnabled: false,
	  		initialFrameWidth: 618,
	  		initialFrameHeight:260
		});
	
		var ue=new baidu.editor.ui.Editor({
  			toolbars:[['source','bold','indent','italic','underline','subscript','pasteplain','time','deleterow','deletecol','splittorows','splittocols','inserttitle','fontfamily','fontsize','forecolor','lineheight','imagecenter','touppercase','tolowercase','simpleupload']],
  			autoHeightEnabled: false,
        	autoFloatEnabled: false,
        	autoHeightEnabled: false,
        	initialFrameWidth:'99%',
        	initialFrameHeight:300
  		});
  		ue.render("editor");
  		
  		
	</script>
</body>
</html>