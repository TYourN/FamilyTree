<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家谱树管理</title>
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
	href="${pageContext.request.contextPath}/css/faTree.css" />
<script
	src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-table.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/faTree.js"></script>
</head>
<body>
	<div class="panel panel-info" id="edit">
		<div class="panel-heading"
			style="padding-top: 10px; padding-bottom: 10px;">
			<h4 class="panel-title">族谱制作</h4>
		</div>
		<div class="panel-body">
			<div class="edit_wrap">
				<ul></ul>
			</div>
			<div class="edit_control_wrap">
				<button class="btn btn-default" id="addnew">
					<span class="glyphicon glyphicon-plus"></span>添加新的节点
				</button>
				<button class="btn btn-success" id="suretree">
					<span class="glyphicon glyphicon-share"></span>保存
				</button>
			</div>
		</div>
	</div>

	<div class="panel panel-info" id="assembly">
		<div class="panel-heading tree_assembly"
			style="padding-top: 10px; padding-bottom: 10px;">
			<h4 class="panel-title" style="display: inline-block;">组件</h4>
		</div>
		<div class="panel-body" id="assembly_wrap">
			<ul>
			</ul>
		</div>
	</div>

	<div class="mask"></div>
	<div class="dele_wrap">
		<div class="title_wrap">
			<p>提示</p>
			<span class="glyphicon glyphicon-remove wrap_close" id="dele_close"></span>
		</div>
		<div class="c_item">
			<p></p>
			<div class="mess_btn">
				<button class="btn btn-primary" id="dele_sure">确定</button>
				<button class="btn btn-default" id="dele_cencel">取消</button>
			</div>
		</div>
	</div>

	<div class="use_wrap">
		<div class="title_wrap">
			<p>选择组件</p>
			<span class="glyphicon glyphicon-remove wrap_close" id="use_close"></span>
		</div>
		<div class="u_item">
			<ul></ul>
			<div class="mess_btn">
				<button class="btn btn-primary" id="use_sure">确定</button>
				<button class="btn btn-default" id="use_cencel">取消</button>
			</div>
		</div>
	</div>
</body>
</html>