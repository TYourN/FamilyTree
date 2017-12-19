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
		<div class="control">
			<button class="btn btn-primary add_item" id="add_item">
				<span class="fui-plus"></span>
				<p>添加初始节点</p>
			</button>
			<button class="btn btn-info add_item" id="save_local">
				<span class="fui-export"></span>
				<p>保存到本地</p>
			</button>
			<button class="btn btn-inverse add_item" id="r_local">
				<span class="fui-folder"></span>
				<p>读取本地数据</p>
			</button>
			<button class="btn btn-info add_item" id="up_load">
				<span class="fui-export"></span>
				<p>上传</p>
			</button>
			<input type="file" id="txtt" class="txt_input" accept="text/plain" />
			<input type="file" id="addlist" class="txt_input" accept="text/plain" />
		</div>
		<div class="gen_wrap">
			<ul>
			</ul>
		</div>
		<div class="mask"></div>
		<div class="dele_wrap">
			<div class="title_wrap">
				<p>提示</p>
				<span class="fui-cross" id="dele_close"></span>
			</div>
			<div class="c_item">
				<p>是否删除此节点</p>
				<div class="mess_btn">
					<button class="btn btn-primary" id="dele_sure">确定</button>
					<button class="btn btn-default" id="dele_cencel">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>