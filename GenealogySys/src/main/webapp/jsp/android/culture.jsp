<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家族文化</title>
<link href="${pageContext.request.contextPath}/jsp/android/css/mui.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/font-awesome.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/culture.css" />
<script src="${pageContext.request.contextPath}/jsp/android/js/mui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/culture.js"></script>
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
		}
	});
</script>
</head>
<body>
	<div class="main_views">
		<div class="nav_wrap">
			<a class="nav_fallback" id="nav_fallback"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="nav_title">文化</p>
		</div>
		<div class="content_wrap">
			<div class="culture_item origin">
				<img src="${pageContext.request.contextPath}/jsp/android/img/az2.png">
				<p>起源</p>
			</div>
			<div class="culture_item teach">
				<img src="${pageContext.request.contextPath}/jsp/android/img/15111592.png">
				<p>祖训</p>
			</div>
			<div class="culture_item celebrity">
				<img src="${pageContext.request.contextPath}/jsp/android/img/k92.png">
				<p>名人名事</p>
			</div>
			<div class="culture_item hs">
				<img src="${pageContext.request.contextPath}/jsp/android/img/zzzzz2.png">
				<p>古迹</p>
			</div>
		</div>
	</div>

	<div class="other_views">
		<div class="nav_wrap" id="otherwrap">
			<a class="nav_fallback" id="other_fallback"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="nav_title" id="navtitle"></p>
		</div>
		<div class="content_wrap">
			<div class="title_wrap"></div>
			<div class="con_wrap"></div>
		</div>
	</div>
</body>
</html>