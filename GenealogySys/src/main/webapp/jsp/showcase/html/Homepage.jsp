<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>甬上人家</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/jquery.fullPage.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/flat-ui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/exhibition/Hp.css" />
<link href="${pageContext.request.contextPath}/jsp/showcase/img/icon.ico" rel="shortcut icon">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/jquery.fullPage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/flat-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/application.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/exhibition/Hp.js"></script>
</head>
<body>
	<div id="nav" class="nav_wrap">
		<div class="nav_head">
			<ul id="menu" class="menu">
				<li data-menuanchor="page1" class="active"><a href="#page1">首页</a></li>
				<li data-menuanchor="page2"><a href="#page2">家谱制作</a></li>
				<li data-menuanchor="page3"><a href="#page3">百家姓</a></li>
				<li data-menuanchor="page4"><a href="#page4">新闻</a></li>
			</ul>
			<div id="control">
				<a class="control" href="register.jsp" target="_blank">注册</a> <a
					class="control" id="nav_login"><span class="fui-user"></span>&nbsp登录</a>
			</div>
		</div>
	</div>
	<div id="sub">
		<div class="section section1">
			<div class="psel">
				<ul class="sel_con"></ul>
			</div>
			<div class="login_wrap">
				<h3>马上登录，加入我们</h3>
				<form role="form">
					<div class="form-group">
						<input type="text" class="form-control login-field" id="InputUser"
							placeholder="账号">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="InputPwd"
							placeholder="密码">
					</div>
					<button type="button" class="btn btn-primary" id="login">登录</button>
					<a class="login_tips">忘记密码</a> <a class="login_tips"
						href="register.jsp" target="_blank">注册新家族</a>
				</form>
			</div>
		</div>
		<div class="section section2">
			<div class="section_con">
				<div class="Ge_intro">
					<ul>
						<li><img src="${pageContext.request.contextPath}/jsp/showcase/img/icons/svg/clipboard.svg" />
						<p>在线编修</p></li>
						<li><img src="${pageContext.request.contextPath}/jsp/showcase/img/icons/svg/compas.svg" />
						<p>随时查看</p></li>
						<li><img src="${pageContext.request.contextPath}/jsp/showcase/img/icons/svg/chat.svg" />
						<p>家族圈</p></li>
					</ul>
				</div>
				<div class="intro_icon"></div>
				<div class="intro_con">
					<ul>

					</ul>
				</div>
			</div>
		</div>
		<div class="section section3">
			<div class="section_con">
				<div class="sec3_title">
					<h3>百家姓</h3>
				</div>
				<div class="name_tab">
					<ul id="myTab" class="nav nav-tabs" role="tablist">
						<li class="active"><a href="#a_d" role="tab"
							data-toggle="tab">A~D</a></li>
						<li><a href="#e_h" role="tab" data-toggle="tab">E~H</a></li>
						<li><a href="#i_l" role="tab" data-toggle="tab">I~L</a></li>
						<li><a href="#m_p" role="tab" data-toggle="tab">M~P</a></li>
						<li><a href="#q_t" role="tab" data-toggle="tab">Q~T</a></li>
						<li><a href="#u_x" role="tab" data-toggle="tab">U~X</a></li>
						<li><a href="#y_z" role="tab" data-toggle="tab">Y~X</a></li>
					</ul>
					<div id="nameTabContent" class="tab-content name_tabcon">
						<div class="tab-pane fade in active" id="a_d"></div>
						<div class="tab-pane fade" id="e_h"></div>
						<div class="tab-pane fade" id="i_l"></div>
						<div class="tab-pane fade" id="m_p"></div>
						<div class="tab-pane fade" id="q_t"></div>
						<div class="tab-pane fade" id="u_x"></div>
						<div class="tab-pane fade" id="y_z"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="section section4">
			<div class="section_con">
				<div class="new_sec">
					<ul></ul>
				</div>
				<div class="new_con">
					<div class="sec4_title">
						<h5>新闻中心</h5>
						<a href="news.jsp" target="_blank">查看更多></a>
					</div>
					<ul></ul>
				</div>
			</div>
			<div class="new_bg1"></div>
			<div class="new_bg2"></div>
			<div class="new_bg3"></div>
		</div>
	</div>
</body>
</html>