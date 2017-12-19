<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${pageContext.request.contextPath}/jsp/android/css/mui.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/font-awesome.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/personal.css" />
<script src="${pageContext.request.contextPath}/jsp/android/js/mui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery.adaptive-backgrounds.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/personal.js"></script>
<script type="text/javascript" charset="utf-8">
	mui.init();
	document.addEventListener('plusready', function() {
		//console.log("所有plus api都应该在此事件发生后调用，否则会出现plus is undefined。"
		if (plus.navigator.isImmersedStatusbar()) {// 兼容immersed状态栏模式
			// 获取状态栏高度并根据业务需求处理，这里重新计算了子窗口的偏移位置
			$(".nav_wrap").css("padding-top",
					plus.navigator.getStatusbarHeight() + 'px');
			$(".set_nav_wrap").css("height",
					plus.navigator.getStatusbarHeight() + 44 + 'px');
			$(".set_nav_wrap").css("padding-top",
					plus.navigator.getStatusbarHeight() + 'px');
			$(".set_content_wrap").css("top",
					plus.navigator.getStatusbarHeight() + 44 + 'px');
		}
	});
</script>
</head>
<body>
	<div class="main_views">
		<div class="nav_wrap">
			<div class="nav_transparent">
				<a class="nav_fallback" id="nav_fallback"><span
					class="mui-icon mui-icon-left-nav"></span></a> <a class="nav_set"><span
					class="icon-cog"></span></a>
			</div>
			<div class="nav_bg"></div>
			<div class="nav_mess_wrap">
			<form id="userpic">
				<img src="img/de_user.jpg" /><input type="file" id="userhead" name="file"
					accept="image/*" />
			</form>
				<p></p>
			</div>
		</div>
		<div class="content_wrap">
			<ul id="personal">
				<li>
					<div class="list" id="username">
						<p>用户名</p>
					</div>
				</li>
				<li>
					<div class="list" id="userage">
						<p>年龄</p>
					</div>
				</li>
				<li>
					<div class="list" id="usergender">
						<p>性别</p>
					</div>
				</li>
				<li>
					<div class="list" id="useraddress">
						<p>住址</p>
					</div>
				</li>
				<li>
					<div class="list" id="userphone">
						<p>电话</p>
					</div>
				</li>
				<li>
					<div class="list" id="userms">
						<p>婚姻状况</p>
					</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="set_views" id="set_views">
		<div class="set_nav_wrap">
			<a class="nav_fallback" id="set_fallback"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="set_nav_title">设置</p>
		</div>
		<div class="set_content_wrap">
			<ul class="mui-table-view mui-table-view-chevron set_wrap">
				<li class="mui-table-view-cell"><a class="mui-navigate-right"
					id="us">个人设置</a></li>
				<li class="mui-table-view-cell"><a class="mui-navigate-right"
					id="pwds">密码修改</a></li>
				<li class="mui-table-view-cell"><a class="mui-navigate-right"
					id="cs">通用</a></li>
			</ul>
			<div class="unlogin">
				<p>退出登录</p>
			</div>
		</div>
	</div>

	<div class="set_views" id="user_set">
		<div class="set_nav_wrap">
			<a class="nav_fallback" id="user_fallback"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="set_nav_title">个人设置</p>
			<a class="set_sure" id="mess_sure">保存</a>
		</div>
		<div class="set_content_wrap">
			<div class="content">
				<div class="mui-input-row content_item">
					<label>用户名</label> <input type="text" id="user"
						class="mui-input-clear" placeholder="请输入用户名">
				</div>
				<div class="mui-input-row content_item">
					<label>年龄</label>
					<div class="mui-numbox" data-numbox-step='1' data-numbox-min='1'
						data-numbox-max='100'>
						<button class="mui-btn mui-numbox-btn-minus" type="button">-</button>
						<input class="mui-numbox-input" type="number" id="age" />
						<button class="mui-btn mui-numbox-btn-plus" type="button">+</button>
					</div>
				</div>
				<div class="mui-input-row content_item">
					<label>性别</label>
					<div class="sel_wrap">
						<span class="res" id="sex"></span> <a class="sel_roulette"
							id="sel_sex"><span class="icon-refresh"></span></a>
					</div>
				</div>
				<div class="mui-input-row content_item">
					<label>住址</label> <input type="text" class="mui-input-clear"
						id="address" placeholder="请输入住址">
				</div>
				<div class="mui-input-row content_item">
					<label>电话</label> <input type="text" class="mui-input-clear"
						id="phone" placeholder="请输入手机号">
				</div>
				<div class="mui-input-row content_item">
					<label>婚姻状况</label>
					<div class="sel_wrap">
						<span class="res" id="ms"></span> <a class="sel_roulette"
							id="sel_ms"><span class="icon-refresh"></span></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="set_views" id="pwd_set">
		<div class="set_nav_wrap">
			<a class="nav_fallback" id="pwd_fallback"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="set_nav_title">密码设置</p>
			<a class="set_sure" id="pwd_sure">保存</a>
		</div>
		<div class="set_content_wrap">
			<div class="content">
				<div class="mui-input-row content_item">
					<label>原密码</label> <input type="password" id="oldpwd"
						class="mui-input-password" placeholder="请输入原密码">
				</div>
				<div class="mui-input-row content_item">
					<label>新密码</label> <input type="password" id="newpwd"
						class="mui-input-password" placeholder="请输入新密码">
				</div>
				<div class="mui-input-row content_item">
					<label>确认密码</label> <input type="password" id="spwd"
						class="mui-input-password" placeholder="请确认新密码">
				</div>
				<p class="pwd_tips" id="pwd_tips"></p>
			</div>
		</div>
	</div>

	<div class="set_views" id="currency_set">
		<div class="set_nav_wrap">
			<a class="nav_fallback" id="cur_fallback"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="set_nav_title">通用设置</p>
		</div>
		<div class="set_content_wrap">
			<ul class="mui-table-view mui-table-view-chevron set_wrap">
				<li class="mui-table-view-cell"><a class="mui-navigate-right"
					id="per_bg">背景墙</a> <input type="file" id="img_sel" class="in_sel"
					accept="image/*" /></li>
				<li class="mui-table-view-cell"><a class="mui-navigate-right"
					id="night">夜间模式</a>
					<div class="mui-switch mui-switch-blue" id="night">
						<div class="mui-switch-handle"></div>
					</div></li>
			</ul>
		</div>
	</div>
</body>
</html>