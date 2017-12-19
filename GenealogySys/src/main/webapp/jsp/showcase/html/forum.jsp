<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/showcase/css/exhibition/forum.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/showcase/js/exhibition/forum.js" ></script>
</head>
<body>
	<div id="right_wrap">
		<ul class="forum_wrap"></ul>
		<div class="tcdPageCode" data-already="unfinished"></div>
		<div class="post">
			<div class="post_tip">
				<p>..:: 快速发贴 ::..</p>
			</div>
			<div class="post_title">
				<input type="test" class="form-control" id="inputtitle"
					placeholder="请输入帖子标题" />
			</div>
			<div class="post_style">
			<ul>
				<li>
					<p>字体：</p>
					<select class="post_select" id="fontstyle">
						<option value="SimSun">默认</option>
						<option value="SimSun">宋体</option>
						<option value="SimHei">黑体</option>
						<option value="Arial">Arial</option>
						<option value="Arial Black">Arial Black</option>
						<option value="Times New Roman">Times New Roman</option>
						<option value="Verdana">Verdana</option>
					</select>
				</li>
				<li>
					<p>字号：</p>
					<select class="post_select" id="fontsize">
						<option value="100%">默认</option>
						<option value="110%">110%</option>
						<option value="220%">120%</option>
						<option value="130%">130%</option>
						<option value="140%">140%</option>
					</select>
				</li>
				<li>
					<div class="simg_wrap">
						<span class="fui-image sflag"></span>
						<div class="imgwrap">
							<div class="img_sel">
								<ul>
								</ul>
							</div>
							<img id="sel_btn" src="${pageContext.request.contextPath}/jsp/showcase/img/addimg.png"> <input
								type="file" id="img_sel" accept="image/*" />
						</div>
					</div>
				</li>
			</ul>
			</div>
			<div class="post_content">
				<textarea class="form-control post_content" id="post_content"
					rows="10" placeholder="请输入帖子内容"></textarea>
			</div>
			<div class="post_btn">
				<button type="button" class="btn btn-primary" id="rep_btn">提交</button>
			</div>
		</div>
	</div>
</body>
</html>