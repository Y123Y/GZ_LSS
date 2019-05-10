<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "form" uri= "http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${ctx}/js/jquery/2.0.0/jquery.min.js"></script>
<link href="${ctx}/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="${ctx}/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<link href="${ctx}/css/sweetalert.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/sweetalert.min.js"></script>
<script type="text/javascript" src="${ctx}/js/global.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(document).ready(function(){
	 var loginname = null;
     $("#login_name").on('blur',function(){
    	if($("#login_name").val() == ''){
    		swal("警告", "登录名不能为空!", "warning");
    	}else{
    		loginname = $("#login_name").val();
/*     		alert(loginname);
    		return; */
    		searchUser();
    	}
     });

function searchUser(){
	$.ajax({
		dataType: "json",
		type: "POST", 
		url: getRealPath()+"/user/checkLoginName",
		data:{
			"loginname" : loginname
		},
		success:function(data){
			if(data.message == "failed"){
				swal("警告", "你输入的登录名已被使用!", "error");
				return;
			}

		}		
	});
	}
	
});
</script>
<style type="text/css">
.errormessage{
	position: fixed;
	width:40%;
	left: 30%;
	top:65px;
	color:#c40000;
	font-size: 14px;

}
</style>
<title>注册界面</title>
<link href="${ctx}/css/registerForm.css" rel="stylesheet">
</head>
<body>
    <div id="header">
		<div class="l-width">
		 <div id="logo">
		 	<a href="http://www.lib.ctgu.edu.cn/">
		 		<img alt="三峡大学图书馆" src="${ctx }/images/logo.png">
		 	</a>
			</div>
		 	<div id="top-nav">
		 		<ul>
		 			<li><a href="${ctx}/worker/loginForm" class="btn btn-info">工作人员登录</a></li>
		 			<li><a href="${ctx}/user/loginForm" class="btn btn-info">教师登录</a></li>
		 		</ul>
		 	</div>
		 </div>
	</div>
<div id="body">
<ul id="myTab" class="nav nav-tabs" style="margin-top:95px; border:none;">
   <li class="active">
      <a href="#userregister" data-toggle="tab">
        	用户注册
      </a>
   </li>
   
</ul>


<div id="myTabContent" class="tab-content">	
<div class="tab-pane fade in active" id="userregister">
<div class="panel panel-info register-box">
  <div class="panel-heading clearfix">
  <h3 class="pull-left">注&nbsp;册</h3>
  <p class="text-info pull-right" style="margin-bottom: 0px; line-height: 30px;"><span class="glyphicon glyphicon-map-marker"></span>欢迎注册三峡大学网上借书系统<p>
  </div>
  <div class="panel-body">
<form:form modelAttribute="tb_user" method="post" action="${ctx }/user/register" >
	<table>
		<tr style="border-bottom: 1px solid #888;">
		<td><p style="color:#1E90FF; font-weight:bold; font-size: 14px; text-align: left; margin-left:5px; margin-bottom: 0px;">填写账号资料</p></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="login_name" class="control-label">登录名:</label></td>		
			<td	class="col-sm-6"><form:input path="login_name" type="text" class="form-control" placeholder="请填写您的登录名"/></td>
			<td class="col-sm-6"><form:errors path="login_name" cssStyle= "color:red"/></td>		
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="passwd" class="control-label">密&nbsp;&nbsp;码:</label></td>
			<td class="col-sm-6"><form:input  path="passwd" type="password" class="form-control" placeholder="请填写您的密码" /></td>
			<td class="col-sm-6"><form:errors path="passwd" cssStyle= "color:red"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="name" class="control-label">姓&nbsp;&nbsp;名:</label></td>
			<td class="col-sm-6"><form:input path="name" type="text" class="form-control"  placeholder="请填写您的真实姓名"/></td>
			<td class="col-sm-6"><form:errors path="name" cssStyle= "color:red"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="card_number" class="control-label">借阅证号:</label></td>
			<td class="col-sm-6"><form:input path="card_number" type="text" class="form-control"/></td>
			<td class="col-sm-6"><form:errors path="card_number" cssStyle= "color:red"/></td>
		</tr>
		<tr style="border-bottom: 1px solid #888;">
		<td><p style="color:#1E90FF; font-weight:bold; font-size: 14px; text-align: left; margin-left:5px; margin-bottom: 0px;">填写联系方式</p></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="tel" class="control-label">手机号码:</label></td>
			<td class="col-sm-6"><form:input path="tel"  type="text" class="form-control"/></td>
			<td class="col-sm-6"><form:errors path="tel" cssStyle= "color:red"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="office_tel" class="control-label">办公电话:</label></td>
			<td class="col-sm-6"><form:input path="office_tel" type="text" class="form-control"/></td>
			<td class="col-sm-6"><form:errors path="office_tel" cssStyle= "color:red"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="qq" class="control-label ">q&nbsp;q&nbsp;号码:</label></td>
			<td class="col-sm-6"><form:input path="qq" type="text" class="form-control"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="department" class="control-label ">单&nbsp;&nbsp;&nbsp;位:</label></td>
			<td class="col-sm-6"><form:input path="department" type="text" class="form-control"/></td>
		</tr>	
		<tr	class="form-group">
		<td class="col-sm-2"></td>
		<td class="col-sm-6">	
 			<button type="submit" class="btn btn-success form-control">注&nbsp;&nbsp;册</button>	
 		</td>
		</tr>
	</table>
</form:form>
    
   
	<c:choose>
	<c:when test="${requestScope.message == null }">
	</c:when>
	<c:otherwise>
	<div class="errormessage ">
	<p class="bg-warning" style="text-align: center; color: #c40000;"><span class="glyphicon glyphicon-info-sign" style="margin-right: 5px;height: 20px; line-height: 20px;"></span>
  		${requestScope.message }
 	</p>
 	</div>
	</c:otherwise>
	</c:choose>

    
  </div>
	</div>
</div>
</div>
</div>
<div id="footer">
	<div class="l-width">
		<div class="content">
		<p>Copyright©2017
			<a href="http://www.ctgu.edu.cn/" target="_blank">三峡大学</a>	图书馆版权所有　　地址：宜昌市大学路8号　　邮编：443002　　馆长信箱：lib@ctgu.edu.cn　　电话：0717-6392083　　</p>
		</div>
	</div>
	</div>

</body>
</html>