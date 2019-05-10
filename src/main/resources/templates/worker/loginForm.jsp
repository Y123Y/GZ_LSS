<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
		    pageEncoding="UTF-8"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
		<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
		<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

		<link rel="stylesheet" href="${ctx }/css/worker/workerLogin.css" />
	</head>

	<body>

		<div class="MyHeader">
		</div>

		<div class="MySecter">
			<c:choose>
				<c:when test="${requestScope.message != null }">
					<p class="bg-warning" style="text-align: center; color: #c40000; background-color: rgba(0,0,0,0); height: 20px;"><span class="glyphicon glyphicon-info-sign" style="margin-right: 5px;height: 20px; line-height: 20px; font-size: 15px"></span>
				  		<span style="margin-right: 5px;height: 20px; line-height: 20px;">${requestScope.message }</span>
				 	</p>
				</c:when>
			</c:choose>
			<div class="LoginModel">
				<form action="${ctx }/worker/login" method="POST">
					<div class="Account">
						<span class="glyphicon glyphicon-user" style="display: block; float: left; line-height: 34px; margin-right: 3%;"></span>
						<input type="text" name="loginname" class="form-control" placeholder="Type Your Account Here ..." style="font-size: 18px; width: 90%; display: block; float: left;" />
					</div>
					<div class="Key">
						<span class="glyphicon glyphicon-lock" style="display: block; float: left; line-height: 34px; margin-right: 3%;"></span>
						<input type="password" name="password" class="form-control" placeholder="Type Your Password Here ..." style="font-size: 18px; width: 90%; display: block; float: left;" />
	
					</div>
					<input type="submit" value="Login" class="btn btn-success"  onfocus="this.blur()" style="font-size: 24px; width: 30%; height: 30%; margin-left: 7.2%; margin-top: 8%;"/>
					<a href="${ctx}/worker/registerForm" class="btn btn-primary"  onfocus="this.blur()" style="font-size: 24px; width: 30%; height: 30%; margin-left: 28%; margin-top: 8%; line-height: 50px;">Register</a>
				</form>
			</div>
		</div>

		<div class="MyFooter">

		</div>

	</body>

</html>