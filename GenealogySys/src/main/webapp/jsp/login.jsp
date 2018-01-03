<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">
<!-- GOOGLE FONTS -->
<link href="${pageContext.request.contextPath}/css/fontcss.css" rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/img/favicon.png">
<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap" style="height: -webkit-fill-available;">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center"><img src="${pageContext.request.contextPath}/img/logo-login.png" alt="Klorofil Logo"></div>		
								<p class="lead">家谱信息管理系统</p>
							</div>
							<form id="userInfo" method="post" class="form-auth-small">
								<div class="form-group">
									<label for="signin-name" class="control-label sr-only">Name</label>
									<input type="text" name="username" class="form-control" id="signin-name" value="" placeholder="用户名">
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">Password</label>
									<input type="password" name="password" class="form-control" id="signin-password" value="" placeholder="密码">
								</div>
								<div class="form-group clearfix">
									<label class="fancy-checkbox element-left">
										<input type="checkbox">
										
									</label>
								</div>
								<button type="button" class="btn btn-primary btn-lg btn-block" onclick="UserLogin()">登录</button>
								<div class="bottom">
									<span class="helper-text"></span>
								</div>
							</form>
						</div>
					</div>
					<div class="right">
						<div class="overlay" style="background: url(${pageContext.request.contextPath}/img/generations.jpg) no-repeat;"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">"登录失败,数据库缺失！！！"</h4>
				</div>
				<div class="modal-body">确认上传本地数据库？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="upSql">确认上传</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>				
				</div>
			</div>			
		</div>		
	</div>
	
</body>
</html>