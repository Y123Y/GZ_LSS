<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>
<h3>测试JSR 303</h3>
登录名：${requestScope.user.loginname }<br>
密码：${requestScope.user.password }<br>
用户名：${requestScope.user.username }<br>
手机号码：${requestScope.user.phone }<br>
电话：${requestScope.user.officePhone }<br>
qq：${requestScope.user.qq }<br>
单位：${requestScope.user.unit }<br>

</body>
</html>