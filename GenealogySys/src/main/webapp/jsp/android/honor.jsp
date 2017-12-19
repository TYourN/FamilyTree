<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${pageContext.request.contextPath}/jsp/android/css/mui.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/font-awesome.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/honor.css" />
<script src="${pageContext.request.contextPath}/jsp/android/js/mui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/sroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/honor.js"></script>
<script type="text/javascript" charset="utf-8">
	mui.init();
	document.addEventListener('plusready', function() {
		//console.log("所有plus api都应该在此事件发生后调用，否则会出现plus is undefined。"
		if (plus.navigator.isImmersedStatusbar()) {// 兼容immersed状态栏模式
			// 获取状态栏高度并根据业务需求处理，这里重新计算了子窗口的偏移位置
			$(".nav_wrap").css("padding-top",
					plus.navigator.getStatusbarHeight() + 'px');
			$(".nav_wrap").css("height",
					plus.navigator.getStatusbarHeight() + 44 + 'px');
			$(".content_wrap").css("top",
					plus.navigator.getStatusbarHeight() + 44 + 'px');
			$(".search_wrap").css("top",
					plus.navigator.getStatusbarHeight() + 44 + 'px');
		}
	});
</script>
</head>
<body>
	<div class="main_views">
		<div class="nav_wrap">
			<a class="nav_fallback" id="nav_fallback"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="nav_title">荣誉</p>
			<a class="nav_more"><span class="icon-reorder"></span></a>
			<div class="more_wrap">
				<ul>
					<li id="search"><span class="icon-search search"></span><span>搜索</span></li>
				</ul>
			</div>
		</div>
		<div class="content_wrap">
			<ul>
			</ul>
		</div>
	</div>
	<div class="mask"></div>

	<div class="search_views">
		<div class="nav_wrap">
			<a class="nav_fallback" id="nav_search"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="nav_title">搜索</p>
			<a class="nav_search">立即搜索</a>
		</div>
		<div class="search_wrap">
			<input type="text" id="search_mass" placeholder="请输入搜索内容"
				autofocus="autofocus" />
			<div class="time">
				<div>
					<label>开始时间:</label> <select class="sel" id="b_year">
						<option value="#">默认</option>
					</select> <label>年</label> <select class="sel" id="b_month">
						<option value="#">默认</option>
					</select> <label>月</label>
				</div>
				<div>
					<label>结束时间:</label> <select class="sel" id="l_year">
						<option value="#">默认</option>
					</select> <label>年</label> <select class="sel" id="l_month">
						<option value="#">默认</option>
					</select> <label>月</label>
				</div>
			</div>
		</div>
	</div>
</body>
</html>