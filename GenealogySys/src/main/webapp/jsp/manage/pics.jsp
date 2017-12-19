<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片资源管理</title>
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
	href="${pageContext.request.contextPath}/css/pics.css" />
<script
	src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/pics.js"></script>
</head>
<body>
	<div class="panel-group" id="accordion">
		<div class="panel panelcolor">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne">首页背景</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in">
				<div class="panel-body" id="homebg">
					<button class="btn btn-success" id="hbg_btn">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加
					</button>
					<input type="file" class="inp" id="hbg_input" accept="image/*" />
					<button class="btn btn-default goleft" style="margin-left: 30px;"
						disabled>
						<span class="glyphicon glyphicon-chevron-left"></span>
					</button>
					<button class="btn btn-default goright" disabled>
						<span class="glyphicon glyphicon-chevron-right"></span>
					</button>
					<div class="img_wrap">
						<ul></ul>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panelcolor">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseTwo">家族页面背景</a>
				</h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse">
				<div class="panel-body" id="personbg">
					<button class="btn btn-success" id="php_btn">
						<span class="glyphicon glyphicon-refresh"></span>&nbsp;&nbsp;改变
					</button>
					<input type="file" class="inp" id="php_binput" accept="image/*" />
					<div class="img_wrap"></div>
				</div>
			</div>
		</div>
		<div class="panel panelcolor">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseThree">手机端首页头部</a>
				</h4>
			</div>
			<div id="collapseThree" class="panel-collapse collapse">
				<div class="panel-body" id="phonebg">
					<button class="btn btn-success" id="phonebg_btn">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加
					</button>
					<input type="file" class="inp" id="phonebg_input" accept="image/*" />
					<button class="btn btn-default goleft" style="margin-left: 30px;"
						disabled>
						<span class="glyphicon glyphicon-chevron-left"></span>
					</button>
					<button class="btn btn-default goright" disabled>
						<span class="glyphicon glyphicon-chevron-right"></span>
					</button>
					<div class="img_wrap">
						<ul></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>