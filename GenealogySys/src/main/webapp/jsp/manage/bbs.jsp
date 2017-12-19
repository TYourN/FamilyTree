<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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
<script src="${pageContext.request.contextPath}/js/bbs.js"></script>
<script src="${pageContext.request.contextPath}/js/tool.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-headline" style="margin-bottom: 15px;">
			<div class="panel-heading" style="padding-bottom: 5px;">
				<div class="row" style="font-size: 20px;">
					<a class=""><i class="lnr lnr-home"></i> <i
						class="icon-submenu lnr lnr-chevron-right"></i><span>论坛</span></a>
				</div>
			</div>

			<div class="panel-body" style="padding-top: 8px;">
				<div class="row">
					<i class="lnr lnr-chart-bars"></i> <span style="color: #C0C0C0;">今日:</span>
					<span id="to_count" style="color: #C0C0C0;"></span> <span
						style="padding: 0px 3px;">|</span> <span style="color: #C0C0C0;">昨日:</span>
					<span id="ye_count" style="color: #C0C0C0;"></span> <span
						style="padding: 0px 3px;">|</span> <span style="color: #C0C0C0;">帖子:</span>
					<span id="post_count" style="color: #C0C0C0;"></span> <span
						style="padding: 0px 3px;">|</span> <span style="color: #C0C0C0;">公告:</span>
					<span id="an_count" style="color: #C0C0C0;"></span>
				</div>
			</div>
		</div>

		<div class="panel panel-info" style="margin-bottom: 0px;">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px;">
				<h4 class="panel-title">论坛内容</h4>
			</div>
			<div id="bbs_info" class="panel-body">
				<div class="panel panel-default"
					style="margin: 0 0 0 7%; width: 40%; float: left; cursor: pointer;">
					<div class="panel-body">
						<i class="lnr lnr-book"
							style="color: #AEB7C2; font-size: 58px; opacity: 0.8; float: left;"></i>
						<div style="margin-left: 57px;">
							<span style="color: #C0C0C0;">个人水贴</span>
							<div>
								<span style="color: #C0C0C0;">贴数:</span> <span id="posts"
									style="color: #C0C0C0;"></span> <span style="padding: 0px 3px;"
									style="color:#C0C0C0;">|</span> <span style="color: #C0C0C0;">回复数:</span>
								<span id="replies" style="color: #C0C0C0;"></span>
							</div>
							<div>
								<span style="color: #C0C0C0; float: left">发帖时间:</span> <span
									id="latest_post" style="color: #C0C0C0;"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default"
					style="margin: 0 6% 0 3%; width: 40%; float: right; cursor: pointer;">
					<div class="panel-body" style="background-color: transparent;">
						<i class="lnr lnr-book"
							style="color: #AEB7C2; font-size: 58px; opacity: 0.8; float: left;"></i>
						<div style="margin-left: 57px;">
							<span style="color: #C0C0C0;">家族公告</span>
							<div>
								<span style="color: #C0C0C0;">公告数:</span> <span id="reports"
									style="color: #C0C0C0;"></span>
							</div>
							<div>
								<span style="color: #C0C0C0; float: left">公告时间:</span> <span
									id="latest_report" style="color: #C0C0C0;"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-info">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px;">
				<h4 class="panel-title">具体信息</h4>
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
				<h4 class="panel-title">发布公告</h4>
			</div>
			<div class="panel-body">	
				<form class="form-horizontal" role="form">
					<div class="form-group" style="padding-top: 15px">
						<label for="firstname" class="col-sm-1 control-label">标题</label>
						<div class="col-sm-11">
							<input id="ptitle" type="text" class="form-control" id="firstname"
								placeholder="请输入标题">
						</div>
					</div>
					<div id="reeditor" class="form-group" style="padding-left: 3%;padding-top: 15px">
						<script id="editor" type="text/plain"></script>
					</div>
				</form>
				<div class="col-sm-10" style="padding-left: 2%">
							<button id="ok" onClick="postPosts()" class="btn btn-primary">发布</button>
				</div>
			</div>
		</div>
	</div>

	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-bd">你，确定要删除这条记录吗？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-confirm>确定</span>
				<span class="am-modal-btn" data-am-modal-cancel>取消</span>				
			</div>
		</div>
	</div>

	<div class="am-modal am-modal-no-btn" style="z-index: 850" id="doc-modal-1">
		<div class="am-modal-dialog">
			<div class="panel panel-info">
				<div class="panel-heading"
					style="padding-top: 20px; padding-bottom: 10px;">
					<h3 class="panel-title">家族水贴、公告详情查看</h3>
				</div>
				<div id="tieInfo" class="panel-body">
					<form method="post" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">标题:</label>
							<div class="col-sm-10">
								<input id="tieTitle" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">帖子内容:</label>
							<div class="col-sm-10">
								<!-- <textarea id="readOnly" class="form-control" rows="6"
									style="background-color: #eee; border-color: rgba(144, 130, 130, 0.38);"
									readonly="readonly"></textarea> -->
								<textarea class="am-validate" name="tiecontent"
									style="display: block" id="tie" required></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">发帖人:</label>
							<div class="col-sm-4" style="margin-top: 5px">
								<span style="float: left; font-size: 17px"></span>
							</div>
							<label for="firstname" class="col-sm-2 control-label">发帖时间:</label>
							<div class="col-sm-4" style="margin-top: 5px">
								<span style="float: left; font-size: 17px"></span>
							</div>
							<label for="firstname" class="col-sm-2 control-label">好评数:</label>
							<div class="col-sm-4" style="margin-top: 5px">
								<span style="float: left; font-size: 17px"></span>
							</div>
							<label for="firstname" class="col-sm-2 control-label">差评数:</label>
							<div class="col-sm-4" style="margin-top: 5px">
								<span style="float: left; font-size: 17px"></span>
								<span id="PostId" style="display:none"></span>
							</div>
						</div>

						<div style="position: relative; bottom: 7%; padding-left: 29%">
							<button id="postRep" type="submit" class="btn btn-default"
								style="padding: 20px 45px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="updateInfo()">提交</button>
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
			var $textArea = $('[name=tiecontent');
		  	var editor = UE.getEditor('tie',{
		  	  toolbars:[['source','simpleupload']],
			  wordCount:true,  
			  elementPathEnabled:true,
			  autoHeightEnabled: false,
			  initialFrameWidth: 618,
			  initialFrameHeight:150
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