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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap-table.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/amaze/css/amazeui.min.css">
	<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-table.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/js/person.js"></script>
	
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/amaze/js/amazeui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
</head>
<body>
	<div class="container-fluid" style="margin-left: 1%;">
		<div class="panel panel-info" style="width: 37%; float: left;height:525px">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px; text-align: center;">
				<h4 class="panel-title">人员信息管理</h4>
			</div>
			<div id="users_info" class="panel-body">
				<button type="button" class="btn btn-default"
					style="margin-right: 3px; padding: 5px 13px" onclick="userAdd()">增加</button>
				<button type="button" class="btn btn-default"
					style="margin-right: 3px; padding: 5px 13px" onclick="userUpdate()">修改</button>
				<button type="button" class="btn btn-default"
					style="margin-right: 3px; padding: 5px 13px" onclick="userDel()">删除</button>
				<button type="button" class="btn btn-default"
					style="margin-right: 3px; padding: 5px 13px; float: right;" onclick="upExcel()">批量导入</button>
				<input type="file" id="excelfile" style="display:none"/>
				<div style="margin-top: 15px;">
					<table id="users_server" class="table table-hover table-striped"
						style="vertical-align: middle !important; text-align: center;">
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-info" style="width: 31%; float: left;height:525px">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px; text-align: center;">
				<h4 class="panel-title">角色信息管理</h4>
			</div>
			<div id="roles_info" class="panel-body">
				<button type="button" class="btn btn-default"
					style=" padding: 5px 13px" onclick="roleAdd()">增加</button>
				<button type="button" class="btn btn-default"
					style=" padding: 5px 13px" onclick="roleUpdate()">修改</button>
				<button type="button" class="btn btn-default"
					style=" padding: 5px 13px" onclick="roleDel()">删除</button>
				<button type="button" class="btn btn-default"
					style=" padding: 5px 13px" onclick="saveUR()">保存</button>
				<div style="margin-top: 15px;">
					<table id="roles_server" class="table table-hover table-striped"
						style="vertical-align: middle !important; text-align: center;">
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-info" style="width: 31%; float: left;">
			<div class="panel-heading"
				style="padding-top: 10px; padding-bottom: 10px; text-align: center;">
				<h4 class="panel-title">功能信息管理</h4>
			</div>
			<div id="funcs_info" class="panel-body">
				<button type="button" class="btn btn-default"
					style=" padding: 5px 13px" onclick="funcAdd()">增加</button>
				<button type="button" class="btn btn-default"
					style=" padding: 5px 13px" onclick="funcUpdate()">修改</button>
				<button type="button" class="btn btn-default"
					style=" padding: 5px 13px" onclick="funcDel()">删除</button>
				<button type="button" class="btn btn-default"
					style=" padding: 5px 13px" onclick="saveRF()">保存</button>
				<div style="margin-top: 15px;">
					<table id="funcs_server" class="table table-hover table-striped"
						style="vertical-align: middle !important; text-align: center;">
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 用户查看信息详情的框 -->
	<div class="am-modal am-modal-no-btn" style="z-index: 850" id="users-info">
		<div class="am-modal-dialog">
			<div class="panel panel-info">
				<div class="panel-heading"
					style="padding-top: 20px; padding-bottom: 10px;">
					<h3 id="head" class="panel-title"></h3>					
				</div>
				<div id="UserInfo" class="panel-body">
					<form method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">用户名:</label>
							<div class="col-sm-4">
								<input id="UserName" name="username" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>
							<label for="firstname" class="col-sm-2 control-label">密码:</label>
							<div class="col-sm-4">
								<input id="PassWord" name="password" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>		
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">姓名:</label>
							<div class="col-sm-4">
								<input id="Name" name="name" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>
							<label for="firstname" class="col-sm-2 control-label">住址:</label>
							<div class="col-sm-4">
								<input id="Address" name="address" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>		
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">电话:</label>
							<div class="col-sm-4">
								<input id="Phone" name="phone" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>
							<label for="firstname" class="col-sm-2 control-label">生日:</label>
							<div class="col-sm-4">
								<input id="Birth" name="birth" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>	
						</div>						
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">性别:</label>
							<div class="col-sm-4">
								<input id="Sex" name="sex" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>
							<label for="firstname" class="col-sm-2 control-label">年龄:</label>
							<div class="col-sm-4">
								<input id="Age" name="age" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>	
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">婚姻:</label>
							<div class="col-sm-4">
								<input id="Marriage" name="marriage" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>
							<label for="firstname" class="col-sm-2 control-label">身份证:</label>
							<div class="col-sm-4">
								<input id="Identity" name="identity" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>	
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">户籍:</label>
							<div class="col-sm-10">
								<input id="Exoducs" name="exoducs" type="text" class="form-control" id="title"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;"
									readonly="readonly">
							</div>	
						</div>
						<div id="UPic" class="form-group">
							<label for="firstname" class="col-sm-2 control-label" style="float:left;">照片:</label>
							<input type="file" name="file" id="doc" multiple="multiple" style="width:37%;padding-left: 2.5%;padding-top: 1%l;float:left" onchange="setImagePreviews()"/>
							<div id="pics" style="width:98%;padding-left: 18%"></div>
						</div>
						<div style="position: relative; bottom: 7%; padding-left: 29%">
							<button id="updateDe" class="btn btn-default"
								style="padding: 14px 27px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="updateUserInfo()">提交</button>
							<button id="postDe" class="btn btn-default"
								style="padding: 14px 27px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="addUserInfo()">提交</button>
							<button id="closeModal" class="btn btn-default"
								data-am-modal-close
								style="padding: 14px 27px; background-color: rgba(255, 0, 118, 0.28); margin-right: 28%;display: none;float:right">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 删除确认框 -->
	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-bd">你，确定要删除这些记录吗？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-confirm>确定</span>
				<span class="am-modal-btn" data-am-modal-cancel>取消</span>				
			</div>
		</div>
	</div>
	
	<!-- 角色的详情框 -->
	<div class="am-modal am-modal-no-btn" style="z-index: 850" id="roles-info">
		<div class="am-modal-dialog">
			<div class="panel panel-info">
				<div class="panel-heading"
					style="padding-top: 20px; padding-bottom: 10px;">
					<h3 id="rolehead" class="panel-title"></h3>					
				</div>
				<div id="RoleInfo" class="panel-body">
					<form method="post" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-4 control-label">角色名:</label>
							<div class="col-sm-8">
								<input id="Title" name="title" type="text" class="form-control"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-4 control-label">简介:</label>
							<div class="col-sm-8">
								<input id="Memo" name="memo" type="text" class="form-control"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;">
							</div>
						</div>
						<div style="position: relative; bottom: 7%; padding-left: 29%">
							<button id="updateRole" class="btn btn-default"
								style="padding: 11px 19px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="updateRoleInfo()">提交</button>
							<button id="postRole" class="btn btn-default"
								style="padding: 11px 19px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="addRoleInfo()">提交</button>
							<button id="closeRole" class="btn btn-default"
								data-am-modal-close
								style="padding: 11px 19px; background-color: rgba(255, 0, 118, 0.28); margin-right: 28%;display: none;float:right">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 功能详情框 -->
	<div class="am-modal am-modal-no-btn" style="z-index: 850" id="func-info">
		<div class="am-modal-dialog">
			<div class="panel panel-info">
				<div class="panel-heading"
					style="padding-top: 20px; padding-bottom: 10px;">
					<h3 id="funchead" class="panel-title"></h3>					
				</div>
				<div id="FuncInfo" class="panel-body">
					<form method="post" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-4 control-label">功能名:</label>
							<div class="col-sm-8">
								<input id="FuncTitle" name="title" type="text" class="form-control"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-4 control-label">父标题:</label>
							<div class="col-sm-8">
								<select id="parentsel" class="form-control">
									<option value="0"></option>
								</select>
							</div>						
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-4 control-label">链接:</label>
							<div class="col-sm-8">
								<input id="FuncUrl" name="url" type="text" class="form-control"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-4 control-label">简介:</label>
							<div class="col-sm-8">
								<input id="FuncMemo" name="memo" type="text" class="form-control"
									style="height: 40px; background-color: #eee; border-color: rgba(144, 130, 130, 0.38); border-radius: 0px;">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-4 control-label">类型:</label>
							<div class="col-sm-8">
								<select id="typesel" class="form-control">
									<option></option>
									<option value="管理">管理</option>
      								<option value="展示">展示</option>
								</select>
							</div>
						</div>
						<div style="position: relative; bottom: 7%; padding-left: 29%">
							<button id="updateFunc" class="btn btn-default"
								style="padding: 11px 19px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="updateFuncInfo()">提交</button>
							<button id="postFunc" class="btn btn-default"
								style="padding: 11px 19px; background-color: rgba(0, 255, 8, 0.44); display: none; float: left" onclick="addFuncInfo()">提交</button>
							<button id="closeFunc" class="btn btn-default"
								data-am-modal-close
								style="padding: 11px 19px; background-color: rgba(255, 0, 118, 0.28); margin-right: 28%;display: none;float:right">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>