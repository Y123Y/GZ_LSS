<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
	<head>
		<meta charset="UTF-8">
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script th:src="@{/js/jquery/2.0.0/jquery.min.js}"></script>
		<link th:href="@{/css/bootstrap/3.3.6/bootstrap.min.css}" rel="stylesheet">
		<script th:src="@{/js/bootstrap/3.3.6/bootstrap.min.js}"></script>
		<link th:href="@{/css/admin_login.css}" rel="stylesheet">
		<title>网站后台管理</title>
	</head>

	<body>
		<div class="panel panel-default ajust_panel center-block">
			<div class="panel-heading ajust_panel_head">
				<h3 class="h4">网站后台管理登录</h3>
			</div>
			<div class="panel-body ajust_panel_body">
				<form class="form-horizontal" method="post" action="@{/admin/login}">
					<div class="form-group">
						<label for="inputOldPassword" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input name="account" type="text" class="form-control" placeholder="Account" maxlength="50">
						</div>
					</div>
					<div class="form-group">
						<label for="inputOldPassword" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-10">
							<input name="password" type="password" class="form-control" placeholder="Password" maxlength="50">
						</div>
					</div>
					<button type="submit" class="btn btn-info center-block ajust_btn">登录</button>
				</form>
			</div>
		</div>
	</body>

</html>