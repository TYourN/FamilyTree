<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>甬上人家</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/flat-ui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/exhibition/pcenter.css" />
<link href="${pageContext.request.contextPath}/jsp/showcase/img/icon.ico" rel="shortcut icon">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/flat-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/application.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/exhibition/pcenter.js"></script>
</head>
<body>
	<div class="nav">
		<div class="nav_content">
			<h4>甬上人家</h4>
			<span>个人中心</span>
		</div>
	</div>
	<div class="content_wrap">
		<div class="form_wrap" id="user11">
			<div class="box_link_1"></div>
			<div class="box_link_2"></div>
			<div class="img_wrap">
				<img src="${pageContext.request.contextPath}/jsp/showcase/img/de_user.jpg" class="user_img" /> <a>上传头像</a>
			</div>
			<div class="useredit">
				<span class="fui-new"></span>
				<p>编辑</p>
			</div>
			<div class="data_wrap">
				<div class="messbox" id="username">
					<label>用户名</label>
					<div class="mess">
						<p></p>
					</div>
				</div>
				<div class="messbox" id="userage">
					<label>年龄</label>
					<div class="mess">
						<p></p>
					</div>
				</div>
				<div class="messbox" id="usergender">
					<label>性别</label>
					<div class="mess">
						<p></p>
					</div>
				</div>
				<div class="messbox" id="useraddress">
					<label>住址</label>
					<div class="mess">
						<p></p>
					</div>
				</div>
				<div class="messbox" id="userphone">
					<label>电话</label>
					<div class="mess">
						<p></p>
					</div>
				</div>
				<div class="messbox" id="userms">
					<label>婚姻状况</label>
					<div class="mess">
						<p></p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="mask"></div>
	<div class="img_change">
		<div class="title_wrap" id="i_title">
			<p>更换头像</p>
			<span class="fui-cross"></span>
		</div>
		<div class="i_img_item">
			<img src="${pageContext.request.contextPath}/jsp/showcase/img/de_user.jpg" />
		</div>
		<div class="i_btn_wrap">
			<div class="change_wrap">
				<a>上传头像</a> <input type="file" id="img_sel" accept="image/*" />
			</div>
			<button class="btn btn-primary" id="img_sure" disabled>确定</button>
			<button class="btn btn-default" id="img_cencel">取消</button>
		</div>
	</div>

	<div class="message_change" id="message_change">
		<div class="title_wrap" id="m_title">
			<p>编辑个人信息</p>
			<span class="fui-cross"></span>
		</div>
		<div class="mess_item">
			<form class="form-horizontal">
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">用户名</label>
					<div class="col-lg-9">
						<input type="test" class="form-control" id="inputname"
							name="inputname" >
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">密码</label>
					<div class="col-lg-7">
						<input type="password" class="form-control" id="inputpwd"
							name="inputpwd" disabled>
					</div>
					<button class="btn btn-default" type="button" id="changepwd">修改</button>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">年龄</label>
					<div class="col-lg-9">
						<input type="test" class="form-control" id="inputage"
							name="inputage">
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">性别</label>
					<div class="col-lg-9">
						<label class="radio box_sel" for="male"> <input
							type="radio" name="sex" data-toggle="radio" value="" id="male"
							required checked>男
						</label> <label class="radio box_sel" for="female"> <input
							type="radio" name="sex" data-toggle="radio" value="" id="female"
							required>女
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">住址</label>
					<div class="col-lg-9">
						<input type="test" class="form-control" id="inputplace"
							placeholder="请输入地区" name="inputplace">
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">电话</label>
					<div class="col-lg-9">
						<input type="test" class="form-control" id="inputphone"
							placeholder="请输入电话" name="inputphone">
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">婚姻</label>
					<div class="col-lg-9">
						<label class="radio box_sel" for="unmarried"> <input
							type="radio" name="Marriage" data-toggle="radio" value="未婚"
							id="unmarried" required checked>未婚
						</label> <label class="radio box_sel" for="married"> <input
							type="radio" name="Marriage" data-toggle="radio" value="已婚"
							id="married" required>已婚
						</label>
					</div>
				</div>
			</form>
		</div>
		<div class="mess_btn">
			<button class="btn btn-primary" id="mess_sure">确定</button>
			<button class="btn btn-default" id="mess_cencel">取消</button>
		</div>
	</div>
	<div class="message_change" id="c_pwd">
		<div class="title_wrap" id="pwd_title">
			<p>修改密码</p>
			<span class="fui-cross"></span>
		</div>
		<div class="c_item">
			<form class="form-horizontal">
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">原密码</label>
					<div class="col-lg-9">
						<input type="password" class="form-control" id="inputoldpwd"
							name="inputoldpwd">
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">新密码</label>
					<div class="col-lg-9">
						<input type="password" class="form-control" id="inputnewpwd"
							name="inputnewpwd">
					</div>
				</div>
				<div class="form-group">
					<label for="inputSurname" class="col-lg-3 control-label">确认新密码</label>
					<div class="col-lg-9">
						<input type="password" class="form-control" id="inputsurepwd"
							name="inputsurepwd">
					</div>
				</div>
			</form>
			<div class="mess_btn" id="pwd_btn">
				<button class="btn btn-primary" id="pwd_sure" disabled>确定</button>
				<button class="btn btn-default" id="pwd_cencel">取消</button>
				<p class="c_pwd_tip"></p>
			</div>
		</div>
	</div>
</body>
</html>