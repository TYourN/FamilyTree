<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div id="right_wrap" class="right_wrap">
		<div class="mess_sel">
			<p>搜索</p>
			<label for="exampleInputEmail1">标题:</label> <input type="text"
				class="form-control" id="sel_title"> <label>开始时间:</label>
			<div>
				<select class="sel" id="b_year">
					<option value="">默认</option>
				</select> <label>年</label> <select class="sel" id="b_month">
					<option value="">默认</option>
				</select> <label>月</label>
			</div>
			<label>结束时间:</label>
			<div>
				<select class="sel" id="l_year">
					<option value="">默认</option>
				</select> <label>年</label> <select class="sel" id="l_month">
					<option value="">默认</option>
				</select> <label>月</label>
			</div>
			<button class="btn btn-success" id="sel">搜索</button>
		</div>
		<div class="img_wrap"></div>
	</div>
</body>
</html>