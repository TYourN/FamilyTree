<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${pageContext.request.contextPath}/jsp/android/css/mui.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/font-awesome.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/forum.css" />
<script src="${pageContext.request.contextPath}/jsp/android/js/mui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/iscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/pullToRefresh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/sroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/forum.js"></script>
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
			<p class="nav_title">论坛</p>
		</div>
		<div class="content_wrap">
			<div class="nav_list">
				<ul>
					<li id="forum_all">所有
						<div class="triangle"></div>
					</li>
					<li id="forum_notice">公告</li>
				</ul>
			</div>
			<div class="forum_content_wrap" id="forum_list">
				<ul>

				</ul>
			</div>
		</div>
	</div>

	<div class="publish">
		<span class="icon-pencil"></span>
	</div>

	<div class="publish_views">
		<div class="nav_wrap">
			<a class="nav_fallback" id="publish_back"><span
				class="mui-icon mui-icon-left-nav"></span></a>
			<p class="nav_title">主题</p>
			<a class="publish_sure">发表</a>
		</div>
		<div class="content_wrap" id="publish_content">
			<form>
				<input type="text" class="publish_title" placeholder="请输入标题" />
				<textarea class="publish_content" placeholder="说点什么......"></textarea>
				<div class="publish_tool">
					<ul>
						<li><span class="icon-picture picture"></span><input
							type="file" id="img_sel" accept="image/*"></li>
					</ul>
				</div>
				<div class="img_wrap">
					<ul>
					</ul>
				</div>
			</form>
		</div>
	</div>
</body>
</html>