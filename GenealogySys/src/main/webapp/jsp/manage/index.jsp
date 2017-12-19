<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>甬上人家之家谱信息管理系统</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/img/favicon.png">	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/amaze/css/amazeui.min.css">
	<link href="${pageContext.request.contextPath}/css/city-picker.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/klorofil-common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/amaze/js/amazeui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/home.js"></script>
	<script src="${pageContext.request.contextPath}/js/city-picker.data.js"></script>
	<script src="${pageContext.request.contextPath}/js/city-picker.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tool.js"></script>
</head>
<body>
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top" style="z-index:800">
			<div class="brand">
				<a href="index.html" style="float: left;"><img src="${pageContext.request.contextPath}/img/favicon.png" alt="Klorofil Logo" class="img-responsive logo" style="max-width: 48%;"></a>
			    <span id="FName" style="font-size: 28px;"></span>			    
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
				</div>
				<div class="navbar-btn navbar-btn-right">
					<a onclick="logout()" class="btn btn-success update-pro"  title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>退出登录</span></a>
				</div>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">					
						<li class="dropdown">
							<a id="userDetail" href="#" class="dropdown-toggle" data-toggle="dropdown"></a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="lnr lnr-user"></i> <span>个人中心</span></a></li>
								<li><a href="#"><i class="lnr lnr-cog"></i> <span>设置</span></a></li>
							</ul>
						</li>						
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul id="main-left" class="nav">
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div id="main-body" class="main-content">
				<div  class="container-fluid">					
					<div class="container-fluid">
						<div class="panel panel-profile">
							<div class="clearfix">
								<div class="profile-left">
									<div id="HeadInfo" class="profile-header">
										<div class="overlay"></div>
										<div class="profile-main">
											<img style="width:14%" src="" class="img-circle" />
											<h3></h3>
											<span class="online-status status-available">
												已入住
											</span>
										</div>
										<div class="profile-stat">
											<div class="row">
												<div class="col-lg-4 stat-item">
													<span></span>
													<span>家族总人数</span>
												</div>
												<div class="col-lg-4 stat-item">
													<span></span>
													<span>管理人员</span>
												</div>
												<div class="col-lg-4 stat-item">
													<span></span>
													<span>普通人员</span>
												</div>
											</div>
										</div>
									</div>
									<div id="detailInfo" class="profile-detail">
										<div class="profile-info">
											<h4 class="heading">家族信息</h4>
											<ul class="list-unstyled list-justify">
												<li>家族全名<span></span></li>
												<li>始兴于 <span></span></li>
												<li>地理位置 <span id="famPosition"></span></li>
												<li style="display:none"><span id="special"></span></li>
											</ul>
										</div>
										<div class="profile-info">
											<h4 class="heading">家族标志</h4>
											<ul class="list-inline social-icons">
																							
											</ul>
										</div>
										<div class="profile-info">
											<h4 class="heading">家族简介</h4>
											<p id="content"></p>
										</div>
										<div id="upFam" class="text-center">
											<a href="#" class="btn btn-primary" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 800}">
												修改信息
											</a>
										</div>
									</div>
								</div>
								<div class="profile-right">
									<h4>家族动态</h4>
									<div class="custom-tabs-line tabs-line-bottom left-aligned">
										<ul class="nav" role="tablist">
											<li class="active"><a href="#tab-bottom-left1" role="tab" data-toggle="tab" onclick="queryReportsTop()">家族公告</a></li>
											<li><a href="#tab-bottom-left2" role="tab" data-toggle="tab">管理人员<span class="badge" style="margin: 0 0 2px 8px;">1</span></a></li>
										</ul>						
									</div>
									<div class="tab-content">
										<div class="tab-pane fade in active" id="tab-bottom-left1">
											<ul class="list-unstyled activity-timeline">
											</ul>	
											<div id="morepost" class="text-center" style="margin-top: 88px;"><a onclick="pathload()" class="btn btn-default">查看更多</a></div>
										</div>
										<div class="tab-pane fade" id="tab-bottom-left2">
											<div class="table-responsive">
												<table class="table project-table">
													
												</table>
											</div>
											<div id="moreuser" class="text-center" style="margin-top: 88px;"><a href="#" class="btn btn-default">查看所有</a></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%-- <jsp:include page="bbs.jsp"></jsp:include> --%>
					<!-- <iframe src="bbs.jsp" name="pageIframe" style="width:100%;height:-webkit-fill-available"></iframe> -->
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->

		<div class="am-modal am-modal-no-btn" style="z-index:850" id="doc-modal-1">
			<div class="am-modal-dialog">
				<div class="panel panel-info">
					<div class="panel-heading"
						style="padding-top: 20px; padding-bottom: 10px;">
						<h3 class="panel-title">家族简介信息</h3>
					</div>
					<div id="diaInfo" class="panel-body" style="height:628px">
						<form method="post" class="form-horizontal" role="form">
							<div class="form-group">
								<label for="firstname" class="col-sm-2 control-label">家族全名:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="firstname" style="height:40px;background-color: #eee;border-color: rgba(144, 130, 130, 0.38);border-radius:0px;"
										placeholder="请输入名字">
								</div>
							</div>
							<div class="form-group">
								<label for="firstname" class="col-sm-2 control-label">始兴于:</label>
								<div class="col-sm-10 am-input-group am-datepicker-date">
									<input id="datapicker" type="text" class="am-form-field" readonly/> <span
										class="am-input-group-btn am-datepicker-add-on">
										<button class="am-btn am-btn-default" type="button">
											<span class="am-icon-calendar"></span>
										</button>
									</span>
								</div>
							</div>
							<div class="form-group">
								<label for="firstname" class="col-sm-2 control-label">家族标志:</label>
								<div class="col-sm-10">
									<textarea class="am-validate" name="myue" id="myue" required></textarea>
								</div>
							</div>	
							<div class="form-group">
								<label for="firstname" class="col-sm-2 control-label">家族简介:</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="3" style="border-color: #231e1e33"></textarea>
								</div>					
							</div>	
							<div class="form-group">
								<label for="firstname" class="col-sm-2 control-label">地理位置:</label>
								<div class="col-sm-10">
									<div class="docs-methods">
										<form class="form-inline">
											<div id="distpicker">
												<div class="form-group">
													<div style="position: relative;width:84%;padding-left:2%;float:left;font-size:14px">
														<input id="city-picker3" class="form-control" readonly
															type="text" data-toggle="city-picker">
													</div>
													<button class="btn btn-warning" id="rese" type="button">重置</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
							
							<div style="position: absolute;bottom: 7%;padding-left: 29%">
								<button type="submit" class="btn btn-default" style="padding:20px 45px;background-color:rgba(0, 255, 8, 0.44)" onclick="postPro()">提交</button>
								<button class="btn btn-default" data-am-modal-close style="padding:20px 45px;background-color:rgba(255, 0, 118, 0.28)">关闭</button>
							</div>																									
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>