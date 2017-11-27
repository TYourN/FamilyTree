<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/linearicons/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/chartist/css/chartist-custom.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/chartist/js/chartist.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/backup.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/klorofil-common.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-info" style="margin-bottom: 10px;">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px;">
				<h3 class="panel-title">家族信息备份和恢复</h3>
			</div>
			<div class="panel-body">
				<button type="button" class="btn btn-default"
					style="padding: 4px 20px; margin-right: 10px">数据备份</button>
				<button type="button" class="btn btn-default"
					style="padding: 4px 20px; margin-right: 10px">数据恢复</button>
			</div>
		</div>
		<div class="panel panel-headline">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px;">
				<h4 class="panel-title">家族外迁情况</h4>
			</div>
			<div id="all_info" class="row">
				<div class="col-md-6" style="padding-left: 7%;">
					<div class="metric">
						<span class="icon"> <i class="fa fa-home" style="margin-top: 32%"></i>
						</span>
						<p id="inhome">
							<span class="number"></span> <span class="title">在家族所在地中的</span>
						</p>
					</div>
				</div>
				<div class="col-md-6" style="padding-right: 7%;">
					<div class="metric">
						<span class="icon"> <i class="fa fa-blind" style="margin-top: 32%"></i>
						</span>
						<p id="outhome">
							<span class="number"></span> <span class="title">迁离家族所在地的</span>
						</p>
					</div>
				</div>
			</div>
			<div id="det_info" class="row">
				<div class="col-md-8" style="padding-left: 7%;">
					<div id="headline-chart" class="ct-chart"></div>
				</div>
				<div class="col-md-3">
					<div id="war" class="weekly-summary text-right">
						<span class="number"></span> <span class="percentage"></span>
						<span class="info-label">战争原因</span>
					</div>
					<div id="pol" class="weekly-summary text-right">
						<span class="number"></span> <span class="percentage"></span>
						<span class="info-label">政治原因</span>
					</div>
					<div id="per" class="weekly-summary text-right">
						<span class="number"></span> <span class="percentage"></span>
						<span class="info-label">其他原因</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div id="sex_info" class="col-md-6">
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title">家族性别比例</h3>
					</div>
					<div class="panel-body">
						<div id="sex_pie"></div>
						<h4>家族男女比例</h4>
						<ul id="sex_pie_info" class="list-unstyled list-justify">
							<li>"男性：" <span></span>
							</li>
							<li>"女性：" <span></span>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div id="age_info" class="col-md-6">
				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title">家族年龄比例</h3>
					</div>
					<div class="panel-body">
						<div id="age_pie"></div>
						<h4>家族年龄比例</h4>
						<ul id="age_pie_info" class="list-unstyled list-justify">
							<li>"0~20(青)：" <span></span>
							</li>
							<li>"21~35(状)：" <span></span>
							</li>
							<li>"36~50(中)：" <span></span>
							</li>
							<li>"51~65(更)：" <span></span>
							</li>
							<li>"66~100(老)：" <span></span>
							</li>
							<li>"101~(去世)：" <span></span>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>