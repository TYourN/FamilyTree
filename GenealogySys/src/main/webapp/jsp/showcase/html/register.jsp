<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/flat-ui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/bootstrapValidator.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/exhibition/register.css" />
<link href="${pageContext.request.contextPath}/jsp/showcase/img/icon.ico" rel="shortcut icon">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/flat-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/application.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/citydata.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/cityPicker-1.0.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/exhibition/register.js"></script>
</head>
<body>
	<div class="nav">
		<div class="nav_content">
			<h4>甬上人家</h4>
			<span>新家族注册</span>
		</div>
	</div>
	<div class="content">
		<div class="sub_con">
			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputPlace" class="col-lg-2 control-label">地区</label>
					<div class="col-lg-8">
						<div class="city-picker-select"></div>
						<input type="test" class="form-control" id="inputPlace"
							placeholder="请输入地区" name="inputPlace">
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-2 control-label">姓氏</label>
					<div class="col-lg-8">
						<input type="test" class="form-control" id="inputSurname"
							placeholder="请输入姓氏" name="inputSurname">
					</div>
				</div>
				<div class="form-group">
					<label for="inputName" class="col-lg-2 control-label">用户名</label>
					<div class="col-lg-8">
						<input type="test" class="form-control" id="inputName"
							placeholder="请输入管理员用户名" name="inputName">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPwd" class="col-lg-2 control-label">密码</label>
					<div class="col-lg-8">
						<input type="password" class="form-control" id="inputPwd"
							placeholder="请输入管理员密码" name="inputPwd">
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurepwd" class="col-lg-2 control-label">确认密码</label>
					<div class="col-lg-8">
						<input type="password" class="form-control" id="inputSurepwd"
							placeholder="请确认密码" name="inputSurepwd">
					</div>
				</div>
				<div class="form-group">
					<label for="inputId" class="col-lg-2 control-label">身份证</label>
					<div class="col-lg-8">
						<input type="test" class="form-control" id="inputId"
							placeholder="请输入管理员身份证" name="inputId">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPhone" class="col-lg-2 control-label">手机号</label>
					<div class="col-lg-8">
						<input type="test" class="form-control" id="inputPhone"
							placeholder="请输入管理员手机号" name="inputPhone">
					</div>
				</div>
				<button type="button" class="btn btn-primary col-lg-8 register_sure"
					id="register_ok">注册</button>
			</form>
		</div>
		<div class="register_tip">
			<p class="tip_title">注册说明</p>
			<p>
				1.<span>地区</span>、<span>姓氏</span>用作区分每个家族，请谨慎填写
			</p>
			<p>
				2.注册成功后<span>此账号</span>将自动成为此家族的<span>管理员</span>
			</p>
			<p>
				3.注册成功后<span>所有家族成员账号</span>可由此账号统一添加
			</p>
		</div>
</body>
</html>