<!DOCTYPE html>
<html>

	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="${ctx }/js/jquery-3.3.1.min.js"></script>
		<link rel="stylesheet" href="${ctx }/css/bootstrap/bootstrap.min.css" />
		<script type="text/javascript" src="${ctx }/js/bootstrap/bootstrap.min.js"></script>
		<link rel="stylesheet" href="${ctx }/css/admin.css" />
		<script type="text/javascript" src="${ctx }/js/admin.js"></script>

		<title>网站后台管理</title>
	</head>

	<body>
		<header>
			<span id="_account" style="display: none;">${admin.account }</span>
			<span id="_name" style="display: none;">${admin.name }</span>
		</header>
		<section>
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        					<span class="sr-only">Toggle navigation</span>
        					<span class="icon-bar"></span>
        					<span class="icon-bar"></span>
       						<span class="icon-bar"></span>
      					</button>
						<a class="navbar-brand" href="#">LSS</a>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="dropdown">
								<a onclick="" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">工作人员&nbsp;&nbsp;<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li>
										<a href="#" id="index_list_worker">所有工作人员</a>
									</li>
									<li>
										<a href="#" data-toggle="modal" data-target="#m_add_worker">增加工作人员</a>
									</li>
									<li>
										<a href="#" data-toggle="modal" data-target="#m_alter_worker">修改人员信息</a>
									</li>
									<li role="separator" class="divider"></li>
									<li>
										<a href="#" data-toggle="modal" data-target="#m_del_worker">删除工作人员</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理人员&nbsp;&nbsp;<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li>
										<a href="#" id="index_list_admin">所有管理人员</a>
									</li>
									<li>
										<a href="#" data-toggle="modal" data-target="#m_add_admin">增加增加人员</a>
									</li>
									<li role="separator" class="divider"></li>
									<li>
										<a href="#" data-toggle="modal" data-target="#m_del_admin">删除管理人员</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">身份审核&nbsp;&nbsp;<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li>
										<a href="#" id="index_list_review">所有身份审核</a>
									</li>
									<li>
										<a href="#" data-toggle="modal" data-target="#m_alter_review">处理审核请求</a>
									</li>
								</ul>
							</li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人信息&nbsp;&nbsp;<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li id="see_info_btn">
										<a href="#" data-toggle="modal" data-target="#m_see_info">查看个人信息</a>
									</li>
									<li>
										<a href="#" data-toggle="modal" data-target="#m_alter_info">修改基本信息</a>
									</li>
									<li>
										<a href="#" data-toggle="modal" data-target="#m_alter_passwd">修改密码</a>
									</li>
									<li role="separator" class="divider"></li>
									<li>
										<a href="${ctx }/admin/logout">退出登录</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>

			<!--.table-reponsive 内的table将具有响应式效果-->
			<div class="table-responsive ajust_table_bg">
				<div class="panel panel-default" id="p_list_worker">
					<div class="panel-heading">
						<h3 class="panel-title">工作人员管理</h3>
					</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>用户名</th>
									<th>姓名</th>
									<th>身份</th>
									<th>电话</th>
									<th>修改信息</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>guozhi</td>
									<td>果汁</td>
									<td>登记员</td>
									<td>15871577069</td>
									<td><button class="btn btn-warning p_alter_worker_btn" type="submit">修改</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<div class="panel panel-default" id="p_list_admin">
					<div class="panel-heading">
						<h3 class="panel-title">管理员账号管理</h3>
					</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>用户名</th>
									<th>姓名</th>
									<th>密码</th>
									<th>修改信息</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>guozhi</td>
									<td>果汁</td>
									<td>********</td>
									<td><button class="btn btn-warning p_del_admin_btn" type="submit">删除</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<div class="panel panel-default" id="p_list_review">
					<div class="panel-heading">
						<h3 class="panel-title">身份审核管理</h3>
					</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>工作人员</th>
									<th>身份</th>
									<th>请求</th>
									<th>说明</th>
									<th>处理</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>guozhi</td>
									<td>登记员</td>
									<td>借书员</td>
									<td>工作交接事务</td>
									<td>
										<button class="btn btn-warning p_alter_review_btn" type="submit">准许</button>
										<button class="btn btn-warning p_alter_review_btn2" type="submit">驳回</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>

		</section>
		<footer>
		</footer>

		<article>

			<!-- 增加工作人员 -->
			<div class="modal fade" id="m_add_worker" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">添加工作人员</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form_add_worker">
								<div class="form-group">
									<div class="col-sm-10">
										<input name="login_name" type="text" class="form-control" placeholder="用户名" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="passwd" type="password" class="form-control" placeholder="密码" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="passwd2" type="password" class="form-control" placeholder="确认密码" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="name" type="text" class="form-control" placeholder="姓名" maxlength="10">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="tel" type="tel" class="form-control" placeholder="手机号码" maxlength="10">
									</div>
								</div>
								<div class="form-group">
									<span class="input-group-addon">
										<div class="col-sm-2">
										<label for="m_add_worker_i1">找书员</label>
      									<input id="m_add_worker_i1" type="checkbox" class="ajust_checkbox" value="1" name="identity1">
      									</div>
										<div class="col-sm-2">
										<label for="m_add_worker_i2">登记员</label>
      									<input id="m_add_worker_i2" type="checkbox" class="ajust_checkbox" value="1" name="identity2">
      									</div>
										<div class="col-sm-2">
										<label for="m_add_worker_i3">派送员</label>
      									<input id="m_add_worker_i3" type="checkbox" class="ajust_checkbox" value="1" name="identity3">
      									</div>
									</span>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" id="form_add_worker_sub">Save changes</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 修改工作人员 -->
			<div class="modal fade" id="m_alter_worker" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">修改工作人员信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form_alter_worker">
								<input name="worker_id" type="hidden">
								<div class="form-group">
									<div class="col-sm-10">
										<input name="login_name" type="text" class="form-control" placeholder="用户名" maxlength="50">
									</div>
									<button class="btn btn-info" id="form_alter_worker_flu">刷新</button>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="passwd" type="text" class="form-control" placeholder="密码" maxlength="50" required="required">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="name" type="text" class="form-control" placeholder="姓名" maxlength="10" required="required">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="tel" type="tel" class="form-control" placeholder="手机号码" maxlength="10" required="required">
									</div>
								</div>
								<div class="form-group">
									<span class="input-group-addon">
										<div class="col-sm-2">
										<label for="m_alter_worker_i1">找书员</label>
      									<input id="m_alter_worker_i1" type="checkbox" class="ajust_checkbox" >
      									</div>
										<div class="col-sm-2">
										<label for="m_alter_worker_i2">登记员</label>
      									<input id="m_alter_worker_i2" type="checkbox" class="ajust_checkbox">
      									</div>
										<div class="col-sm-2">
										<label for="m_alter_worker_i3">派送员</label>
      									<input id="m_alter_worker_i3" type="checkbox" class="ajust_checkbox">
      									</div>
									</span>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary"  data-dismiss="modal" id="form_alter_worker_sub">Save changes</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 删除工作人员 -->
			<div class="modal fade" id="m_del_worker" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">删除指定工作人员</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form_del_worker">
								<div class="form-group">
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="用户名" maxlength="50">
									</div>
									<button class="btn btn-info" id="form_del_worker_sub">删除</button>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 增加管理人员 -->
			<div class="modal fade" id="m_add_admin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">添加新的管理账号</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form_add_admin">
								<div class="form-group">
									<div class="col-sm-10">
										<input name="login_name" type="text" class="form-control" placeholder="用户名" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="passwd" type="password" class="form-control" placeholder="密码" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="passwd2" type="password" class="form-control" placeholder="确认密码" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="name" type="text" class="form-control" placeholder="姓名" maxlength="50">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" id='form_add_admin_sub'>Save</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 删除管理人员 -->
			<div class="modal fade" id="m_del_admin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">更换身份</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form_del_admin">
								<div class="form-group">
									<div class="col-sm-10">
										<input name="login_name" type="text" class="form-control" placeholder="用户名" maxlength="50">
									</div>
									<button class="btn btn-info" id="form_del_admin_sub">删除</button>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 处理身份审核 -->
			<div class="modal fade" id="m_alter_review" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">更换身份</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form_alter_review">
								<div class="form-group">
									<div class="col-sm-10">
										<input name="login_name" type="text" class="form-control" placeholder="工作人员的用户名" maxlength="50">
									</div>
									<button class="btn btn-info" id="form_review_find">查找</button>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="identity" type="text" class="form-control" placeholder="姓名" maxlength="10">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="identity" type="text" class="form-control" placeholder="请求身份" maxlength="10">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<button class="btn btn-warning" id="form_alter_review_pass">准许</button>
										<button class="btn btn-warning" id="form_alter_review_reject">驳回</button>
									</div>
								</div>

								<input name="review_id" type="hidden">
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 查看个人信息 -->
			<div class="modal fade" id="m_see_info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">查看个人信息</h4>
						</div>
						<div class="modal-body">
							<label>账号：</label>
							<label id="m_see_info_account">123456</label><br />

							<label>姓名：</label>
							<label id="m_see_info_name">123456</label><br />

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 修改个人基础信息 -->
			<div class="modal fade" id="m_alter_info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">修改个人基础信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form_alter_info">
								<div class="form-group">
									<div class="col-sm-10">
										<input name="login_name" type="text" class="form-control" placeholder="用户名" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="name" type="text" class="form-control" placeholder="姓名" maxlength="50">
									</div>
								</div>
								<!--<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">确认修改</button>
								</div>
							</div>-->
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" id="form_alter_info_sub">Save</button>
						</div>
					</div>
				</div>
			</div>

			<!-- 修改个人密码信息 -->
			<div class="modal fade" id="m_alter_passwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">修改密码信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form_alter_passwd">
								<div class="form-group">
									<div class="col-sm-10">
										<input name="passwd_old" type="password" class="form-control" placeholder="原密码" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="passwd_new" type="password" class="form-control" placeholder="新密码" maxlength="50">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="passwd_new2" type="password" class="form-control" placeholder="确认新密码" maxlength="50">
									</div>
								</div>
								<!--<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">确认修改</button>
								</div>
							</div>-->
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" id="form_alter_infopwd_sub" >Save changes</button>
						</div>
					</div>
				</div>
			</div>

		</article>
	</body>

</html>