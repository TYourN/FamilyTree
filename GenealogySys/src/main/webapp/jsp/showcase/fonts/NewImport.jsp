<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE-edge,chrome=1" />
		<title>JSP Page</title>
		<link rel="stylesheet" href="../css/UserFunctionSettings_css.css" />
		<link rel="stylesheet" href="../css/inputStyle.css" />
	</head>
	<body>
		<div class="container pos">
			<div class="row">
				<div class="col-md-10">
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<!--选项1-->
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h4 class="panel-title">
                            		<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            			网站导入</a>
                        		</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<form>
										<select class="form-control sel_list" id="size" style="width:22%">
										  <option value="www.25pp.com">www.25pp.com</option>
										  <option value="www.shoujibaidu.com">www.shoujibaidu.com</option>
										</select>
										<button type="button" id="import" class="btn btn-primary particle_sub">导入</button>
									</form>
								</div>
							</div>
						</div>
						<!--选项2-->
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingTwo">
								<h4 class="panel-title">
                            		<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            			Excel导入</a>
                        		</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
								<div class="panel-body">
		<div class="block">
		<div style="height: 20%;width: 100%;margin-top:20px">
			<form role="form" >
				<input name="appname" placeholder="检测APP是否存在"  class="firstInStyle" type="text"  />
				<span><button type="button" style=" color: #53AD3F; background-color: white; height: 31px;width: 50px; border: 3px solid #53AD3F;border-radius: 15px;" id="check">提交</button></span>
				<span><button type="button" style="visibility: hidden; color: #53AD3F; background-color: white; height: 31px;width: 50px; border: 3px solid #53AD3F;border-radius: 15px;" id="close">取消</button></span>
				<div id="cue"></div>
			</form>
			</div>
			<div style="height: 80%;width: 100%;">
			<form role="form" runat="server" method="post" enctype="multipart/form-data" style="height: 65%;width: 100%;">
				<div id="text" style="display: none;left: 43%;top: 30%;position:absolute;width: 100px;height: 130px;background-color: #53AD3F;line-height: 25px;color: white;cursor: pointer;overflow: hidden;z-index: 1;">
					<!--<input name="appName" type="text"></input>-->
					<input class="instyle" onchange="handleFile()" type="file" id="file" /> <img style="height: 100px;width: 100px;" src="../img/untitled.png" />导入Excel</input>
				</div>
			<input id="nice"  style="display:none; border-radius: 5px; height: 23px;width: 200px;border: 3px solid #53AD3F;color:darkslategray;position: absolute;top:68%;left: 36%;text-align: left;overflow:hidden;text-overflow: ellipsis"/>
			<button type="button" id="sub" style="display:none;position: absolute; color: #53AD3F; background-color: white; height: 32px;width: 60px; border: 3px solid #53AD3F;border-radius: 15px;top:75%; left: 45%;">上传</button>
			</form>
			</div>
		          </div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../js/jquery-3.1.0.js"></script>
		<script type="text/javascript" src="../js/ajaxfileupload.js" ></script>
		<script type="text/javascript" src="../js/NewImport.js" ></script>
	</body>
</html>