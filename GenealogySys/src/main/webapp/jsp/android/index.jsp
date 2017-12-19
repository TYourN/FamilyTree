<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${pageContext.request.contextPath}/jsp/android/css/mui.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/android/css/index.css" />
<script src="${pageContext.request.contextPath}/jsp/android/js/mui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/sroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/android/js/index.js"></script>
<script type="text/javascript" charset="utf-8">
	mui.init();
	document.addEventListener('plusready', function() {
		//console.log("所有plus api都应该在此事件发生后调用，否则会出现plus is undefined。"
		if (plus.navigator.isImmersedStatusbar()) { // 兼容immersed状态栏模式
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
			<p class="nav_title">首页</p>
			<div class="head_portrait">
				<img src="img/de_user.jpg" />
			</div>
		</div>
		<div class="content_wrap">
			<div class="sroll_img_wrap">
				<ul>
					<li><img class="sroll_item" src="img/sroll1.jpg"></li>
					<li><img class="sroll_item" src="img/sroll2.jpg"></li>
				</ul>
			</div>
			<div class="item_sel_wrap">
				<ul class="item_ul">
					<li id="modular_forum">
						<div class="item_sel" style="background-color: #01AAEE;">
							<img class="item_sel_img" src="img/forum_2.png" />
						</div>
						<p class="item_sel_word">论坛</p>
					</li>
					<li id="modular_honor">
						<div class="item_sel" style="background-color: #ffd647;">
							<img class="item_sel_img" src="img/honor_2.png" />
						</div>
						<p class="item_sel_word">荣誉</p>
					</li>
					<li id="modular_cultrue">
						<div class="item_sel" style="background-color: #a168c2;">
							<img class="item_sel_img" src="img/culture_2.png" />
						</div>
						<p class="item_sel_word">文化</p>
					</li>
					<li id="modular_tree">
						<div class="item_sel" style="background-color: #fe8b2b;">
							<img class="item_sel_img" src="img/tree_2.png" />
						</div>
						<p class="item_sel_word">家谱</p>
					</li>
				</ul>
			</div>
			<div class="modular_news">
				<p class="modular_title">--新闻--</p>
				<ul class="news_list">
				</ul>
			</div>
		</div>
	</div>
</body>
</html>