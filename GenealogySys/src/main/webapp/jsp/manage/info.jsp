<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/linearicons/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap-table.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/amaze/css/amazeui.min.css">
	<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-table.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script src="${pageContext.request.contextPath}/js/info.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/amaze/js/amazeui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-info" style="margin-bottom: 0px;">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px;">
				<span id="Title" class="panel-title"> </span>
			</div>
			<div class="panel-body">
				<button type="button" class="btn btn-default"
					style="padding: 4px 20px; margin-right: 10px" onclick="addDe()">增加</button>
				<button type="button" class="btn btn-default"
					style="padding: 4px 20px; margin-right: 10px" onclick="updateDe()">修改</button>
				<button type="button" class="btn btn-default"
					style="padding: 4px 20px; margin-right: 10px" onclick="deleteDe()">删除</button>
				<button type="button" class="btn btn-default"
					style="padding: 4px 20px; margin-right: 10px; float: right;">批量导入</button>
				<div style="margin-top: 25px;">
					<table id="info_server" class="table table-hover table-striped"
						style="vertical-align: middle !important; text-align: center;">
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 查看信息详情的框 -->
	<div class="am-modal am-modal-no-btn" style="z-index: 850" id="doc-modal-info">
		<div class="am-modal-dialog">
			<div class="panel panel-info">
				<div class="panel-heading"
					style="padding-top: 20px; padding-bottom: 10px;">
					<h3 id="head" class="panel-title" style="float: left;padding-left: 41%"></h3>
					<a id="closeFlag" href="javascript: void(0)" class="am-close am-close-spin" style="font-size: 35px;margin-left: 41%" data-am-modal-close>&times;</a>
				</div>
				<div id="DeInfo" class="panel-body">
					<form method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">标题:</label>
							<div class="col-sm-10">
								<input id="DeTitle" name="title" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>	
						</div>
						<div id="memo" class="form-group" style="display:none">
							<label for="firstname" class="col-sm-2 control-label">简介:</label>
							<div class="col-sm-10">
								<input id="DeMemo" name="memo" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>	
						</div>
						<div id="sShot" class="form-group">
							<label id="screenShot" for="firstname" class="col-sm-2 control-label" style="float:left"></label>
							<input type="file" name="file" id="doc" multiple="multiple" style="width:28%;padding-left: 2%;padding-top: 1%l;float:left" onchange="setImagePreviews()"/>
							<div id="pics" style="height:100px;width:98%;padding-left: 18.5%"></div>
							<div id="stype" class="col-sm-4" style="margin-top: 5px;display:none">
								<select id="stypesel" class="form-control">
									<option></option>
									<option value="起源">起源</option>
      								<option value="祖训">祖训</option>
      								<option value="名人事迹">名人事迹</option>
      								<option value="古迹">古迹</option>
								</select>
							</div>
							
						</div>
						<div class="form-group">
							<label id="Content" for="firstname" class="col-sm-2 control-label"></label>
							<textarea class="am-validate" name="contentDe" id="contentDe" style="float:left;padding-left: 15px" required></textarea>
							<input id="ucontent" name="content" style="display:none"/>
						</div>
						<div id="detail" class="form-group">
							<label for="firstname" class="col-sm-2 control-label">发布人:</label>
							<div class="col-sm-4" style="margin-top: 5px">
								<span id="operName" style="float: left; font-size: 17px"></span>
							</div>
							<label for="firstname" class="col-sm-2 control-label" style="display:none">类型:</label>
							<div class="col-sm-4" style="margin-top: 5px;display:none">
								<select id="typesel" class="form-control" style="display:none">
									<option></option>
									<option>起源</option>
      								<option>祖训</option>
      								<option>名人事迹</option>
      								<option>古迹</option>
								</select>
								<span id="utype" style="float: left; font-size: 17px"></span>
								<input id="ytype" name="type" style="display:none"/>
							</div>
							<label for="firstname" class="col-sm-2 control-label">发布时间:</label>
							<div class="col-sm-4" style="margin-top: 5px">
								<span id="operTime" style="float: left; font-size: 17px"></span>
							</div>
						</div>
						<div style="position: relative; bottom: 7%; padding-left: 29%">
							<button id="updateDe" class="btn btn-default"
								style="padding: 20px 45px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="updDe()">提交</button>
							<button id="postDe" class="btn btn-default"
								style="padding: 20px 45px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="subDe()">提交</button>
							<button id="closeModal" class="btn btn-default"
								data-am-modal-close
								style="padding: 20px 45px; background-color: rgba(255, 0, 118, 0.28); margin-right: 39%;display: none;float:right">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var $textArea = $('[name=contentDe');
  		var editor = UE.getEditor('contentDe',{
  			toolbars:[['source','bold','indent','italic','underline','subscript','pasteplain','time','deleterow','deletecol','splittorows','splittocols','inserttitle','fontfamily','fontsize','forecolor','lineheight','imagecenter','touppercase','tolowercase','simpleupload']],
	  		wordCount:true,  
	  		elementPathEnabled:true,
	  		initialFrameWidth: 618,
	  		initialFrameHeight:200
		});
	</script>
</body>
</html>