<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>甬上人家</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/flat-ui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/exhibition/person_hp.css" />
<link rel="stylesheet" href="" id="s1" />
<link href="${pageContext.request.contextPath}/jsp/showcase/img/icon.ico" rel="shortcut icon">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/flat-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/jquery.page.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/application.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/FileSaver.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/exhibition/person_hp.js"></script>
</head>
<body>
	<div class="head_wrap">
		<div class="nav_bg">
			<div class="nav_bg1"></div>
			<div class="nav_bg2"></div>
		</div>
		<div class="nav">
			<h4 class="logo">
				<a href="person_hp.jsp">甬上人家</a>
			</h4>
			<ul class="nav_right">
				<li id="dl"><a class="download_app">下载APP</a>
					<div class="dl_wrap">
						<p>扫描下载App</p>
						<img src="${pageContext.request.contextPath}/jsp/showcase/img/dlapp.jpg">
					</div></li>
				<li><a class="user_li"><span class="fui-chat remind"
						id="remind"></span></a></li>
				<li id="user"><a class="user_li"><img
						src="${pageContext.request.contextPath}/jsp/showcase/img/de_user.jpg" class="user_img" /></a>
					<div class="user_wrap">
						<div class="user_inf_wrap">
							<img src="${pageContext.request.contextPath}/jsp/showcase/img/de_user.jpg" class="user_inf_img" />
							<div class="user_inf">
								<p class="user_inf_name"></p>
								<p class="user_inf_place"></p>
							</div>
							<button id="userinfo" class="btn btn-primary user_core"
								target="_blank">个人中心</button>
							<button class="btn btn-success user_core" target="_blank"
								id="manage">系统管理</button>
							<button class="btn btn-default user_core" target="_blank"
								id="unlogin">注销</button>
						</div>
					</div></li>
			</ul>
		</div>
	</div>
	<div class="top_frame">
		<ul></ul>
	</div>
	<div class="bottom_frame"></div>
</body>
</html>