<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>甬上人家</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/flat-ui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/exhibition/news.css" />
<link href="${pageContext.request.contextPath}/jsp/showcase/img/icon.ico" rel="shortcut icon">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/flat-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/jquery.page.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/exhibition/news.js"></script>
</head>
<body>
	<div class="nav">
		<div class="nav_content">
			<h4>甬上人家</h4>
			<!--<div class="login_wrap">
				<a id="login">登录</a> <a href="register.jsp">注册</a>
			</div> -->
		</div>
	</div>

	<div class="content_wrap">
		<div class="now_lo_wrap">
			<h3>新闻中心</h3>
			<span> 当前位置： <a href="Homepage.jsp">首页</a> > <a
				href="news.jsp">新闻中心</a>
			</span>
		</div>
		<div class="list">
			<ul>
			</ul>
		</div>
		<div class="tcdPageCode" data-already="unfinished"></div>
	</div>
	<div class="back">
		<span class="fui-triangle-left-large"></span>
		<p>返回首页</p>
	</div>
	<div class="gre"></div>
	<div class="login_bra">
		<div class="l_wrap">
			<p>登录</p>
			<span class="fui-cross"></span>
		</div>
		<div class="con_wrap">
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputname" class="col-lg-2 control-label">账号:</label>
					<div class="col-lg-12">
						<input type="test" class="form-control" id="inputname"
							placeholder="请输入账号" name="inputSurname">
					</div>
				</div>
				<div class="form-group">
					<label for="inputpwd" class="col-lg-2 control-label">密码:</label>
					<div class="col-lg-12">
						<input type="test" class="form-control" id="inputpwd"
							placeholder="请输入密码" name="inputSurname">
					</div>
				</div>
				<button type="button" class="btn btn-primary col-lg-12 l_sure"
					id="register_ok">登录</button>
				<a class="forget" href="register.jsp">立即注册</a>
			</form>
		</div>
	</div>
</body>
</html>