<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "form" uri= "http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctx}/js/jquery/2.0.0/jquery.min.js"></script>
<link href="${ctx}/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="${ctx}/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<link href="${ctx}/css/sweetalert.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/sweetalert.min.js"></script>
<script type="text/javascript" src="${ctx}/js/maintop.js"></script>

<link href="${ctx}/css/maintop.css" rel="stylesheet">
<link href="${ctx}/css/footer.css" rel="stylesheet">
<link href="${ctx}/css/tableset.css" rel="stylesheet">
<link href="${ctx}/css/personsetting.css" rel="stylesheet">

<title>个人设置界面</title>

<script type="text/javascript">
$(document).ready(function(){
	$("#update_check").click(function(){
		$(".form-group > td > input").removeAttr("disabled");
		$(".updateuser").removeAttr("disabled");
	});
	$("#update_password").click(function(){		
		$(".bg_popmima").show();
	});
	$("#close_pop2").click(function(){
		$(".bg_popmima").hide();
	});
	$("#check_newPasswd").blur(function(){
		var newPasswd = $("#newPasswd").val();
		var check_newPasswd = $(this).val();
		if(newPasswd == check_newPasswd){
			$(".updatePasswd").removeAttr("disabled");	
			$("#errmessage").hide();
			return;
		}else{
			$("#errmessage").show();
		}
	});
});
</script>


    
</head>
<body>
 <div class="wrap">
  <div id="header">
    <div class="top" id="top">
	  			<li>            
   					<div class="dropdown">
    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
       我的地址
      <span class="caret"></span>          
    </button>
     
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
          <li class="dropdown-header">
           <span style="font-weight: bold; font-size: 13px;">已有地址</span>
          </li>
     <c:choose>
     <c:when test="${addresses == null}">
      <li role="presentation">
         <a href="#">没有地址信息</a>           
      </li>
     </c:when>
     <c:otherwise>
 	 <c:forEach items="${addresses }" var="address">
  	   <li>
  	   		<a id="${address.address_id }" class="update_address">${address.content }</a>
  	   </li>                         	 	
  	</c:forEach>
  	</c:otherwise>
  	</c:choose>
          <li class="dropdown-header">
          <span style="font-weight: bold; font-size: 13px;">操作</span>
          </li>
 
      <li role="presentation">
         <a class="pop_addressDiv">添加新地址</a>           
      </li>
    </ul>
</div>    	
				</li>
				<div id="topright">
				<c:choose>
				<c:when test="${sessionScope.user_session.login_name != null }">
					<li>
						<a href="#">欢迎您,<span>${sessionScope.user_session.login_name}</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="${ctx}/user/loginForm">你好！请登录</a>
					</li>
					<li>
						<a href="${ctx}/user/registerForm">免费注册</a>
					</li>
				</c:otherwise>
				</c:choose>	
					<li>|
						<a href="${ctx}/order/history">我的订单</a>
					</li>
					<li>|
						<a href="">收藏夹 <i class="arrow"></i></a>
						<ul>
							<li><a href="${ctx}/order/history">已借的书籍</a></li>
							<li><a href="#">收藏的书籍</a></li>
						</ul>
						
					</li>
					<li>
						<a href="">客服服务<i class="arrow"> </i></a>
						<ul>
							<li><a href="">书籍跟踪</a></li>
							<li><a href="">书籍借阅</a></li>
						</ul>
					</li>
					<li>
						<a href="">网站导航<i class="arrow"> </i></a>
						<ul>
							<li><a href="${ctx}/user/userInfo">个人中心</a></li>
							<li><a href="">业务理赔</a></li>
							<li><a href="">客服接待</a></li>
							<li><a href="">会员通道</a></li>		
						</ul>
					</li>
					<li>| 关注我们：
						<a href=""><img src="${ctx}/images/sh1.png" /></a>
					</li>
					<li>
						<a href=""><img src="${ctx}/images/sh2.png" /></a>|</li>
					<li>
						<a href="">手机版<img src="${ctx}/images/i_tel.png" style="margin-left: 5px; vertical-align:middle;" /></a>
					</li>
				</div>
			</div>
		</div>
	
	<!--  个人设置表单 -->
	<div class="l-width">	
        <div class="l-width table_title" style="height: 50px;">	
           <h2>个人信息</h2>
        </div>	 
		
<form:form modelAttribute="user" method="post" action="${ctx }/user/updateUserInfo"  class="bg-settig">
	<table>
		<tr style="border-bottom: 1px solid #888;">
		<td><p style="color:#1E90FF; font-weight:bold; font-size: 14px; text-align: left; margin-left:5px; margin-bottom: 0px;">用户基本资料</p></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="login_name" class="control-label">登录名:</label></td>		
			<td	class="col-sm-6"><form:input path="login_name" type="text" class="form-control" disabled="true"/></td>
			<td class="col-sm-6"><form:errors path="login_name" cssStyle= "color:red"/></td>		
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="name" class="control-label">姓&nbsp;&nbsp;名:</label></td>
			<td class="col-sm-6"><form:input path="name" type="text" class="form-control"   disabled="true"/></td>
			<td class="col-sm-6"><form:errors path="name" cssStyle= "color:red"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="card_number" class="control-label">借阅证号:</label></td>
			<td class="col-sm-6"><form:input path="card_number" type="text" class="form-control" disabled="true"/></td>
			<td class="col-sm-6"><form:errors path="card_number" cssStyle= "color:red"/></td>
		</tr>
		<tr style="border-bottom: 1px solid #888;">
		<td><p style="color:#1E90FF; font-weight:bold; font-size: 14px; text-align: left; margin-left:5px; margin-bottom: 0px;">填写联系方式</p></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="tel" class="control-label">手机号码:</label></td>
			<td class="col-sm-6"><form:input path="tel"  type="text" class="form-control" disabled="true"/></td>
			<td class="col-sm-6"><form:errors path="tel" cssStyle= "color:red"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="office_tel" class="control-label">办公电话:</label></td>
			<td class="col-sm-6"><form:input path="office_tel" type="text" class="form-control" disabled="true"/></td>
			<td class="col-sm-6"><form:errors path="office_tel" cssStyle= "color:red"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="qq" class="control-label ">q&nbsp;q&nbsp;号码:</label></td>
			<td class="col-sm-6"><form:input path="qq" type="text" class="form-control" disabled="true"/></td>
		</tr>
		<tr class="form-group">
			<td class="col-sm-2"><label for="department" class="control-label ">单&nbsp;&nbsp;&nbsp;位:</label></td>
			<td class="col-sm-6"><form:input path="department" type="text" class="form-control" disabled="true"/></td>
		</tr>	
		<tr	class="form-group">
		<td class="col-sm-2"></td>
		<td class="col-sm-6">	
 			<button type="submit" class="btn btn-success form-control updateuser" disabled="disabled">保存修改信息</button>	
 		</td>
		</tr>
	</table>
</form:form>
		<div class="l-width" style="height: 50px; background-color: #666; text-align:center; margin-top: 20px;">
		<button id="update_check" class="btn btn-success btn-lg pull-right" style="margin: 2px auto; width: 200px; margin-right: 10px;">修改信息</button> 
		<button id="update_password" class="btn btn-danger btn-lg pull-right" style="margin: 2px auto; width: 200px; margin-right: 10px;">修改密码</button> 
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
 <div id="right_item">
 	 <ul>
 	 	<li><a href="${ctx}/user/main"><img src="${ctx}/images/home.png"/></a><div class="toggle_title">返回首页</div></li>
 	 	<li><a href="${ctx}/user/userInfo"><img src="${ctx}/images/geren.png"/></a><div class="toggle_title">个人信息</div></li>
 	 	<li><a href="${ctx}/userOperation/bookCart"><img src="${ctx}/images/shopcart.png"/></a><div class="toggle_title">购物车</div></li> 	 	
 	 	<li><a href="${ctx}/order/history"><img src="${ctx}/images/history.png"/></a><div class="toggle_title">订单管理</div></li>
 	 	<li style="height: 70px;" class="depart"></li>
 	 	<li><a id="loginout"><img src="${ctx}/images/zhuxiao.png"/></a><div class="toggle_title">退出登陆</div></li>
 	 	<li><a href="#top"><img src="${ctx}/images/top01.png"/></a><div class="toggle_title return_top">返回顶部</div></li>
 	 </ul>
 </div>	
  
 </div>
 <!-- 修改密码使用遮罩层 -->
 <div class="bg_popmima">
 	<div class="popmima">
 		<img alt="关闭窗口" src="${ctx}/images/close_pop.png" id="close_pop2" width="20px" height="20px"/>
 	    <h2 >修改密码</h2>
 		<form action="${ctx}/user/changePasswd" method="post">
 		<div style="margin: 0 auto; height: 20px; text-align: center;">
 		<label id="errmessage" style=" color:red; display: none;">前后密码输入不一致!</label>	 		
 		</div>
 		<div style="margin-top: 5px;"><label for="oldPasswd" class="control-label">*原&nbsp;&nbsp;密&nbsp;&nbsp;码:</label>	
 		<input class="form-control" type="password" name="oldPasswd" id="oldPasswd" placeholder="请输入原密码"/>
 		</div>
 		<div>
 		<label for="newPasswd" class="control-label">*新&nbsp;&nbsp;密&nbsp;&nbsp;码:</label>
 		<input class="form-control" type="password" name="newPasswd" id="newPasswd" placeholder="请输入新密码"/>
 		</div>
 		<div>
 		<label for="check_newPasswd" class="control-label">*新&nbsp;&nbsp;密&nbsp;&nbsp;码:</label>
 		<input class="form-control"  type="password" name="check_newPasswd" id="check_newPasswd" placeholder="请再次输入新密码"/>	
 		</div>
 		<div align="center">
 		<button type="submit" class="btn btn-danger form-control updatePasswd" disabled="disabled">确认修改密码</button>
 		</div>
 		</form>
 	</div>
 </div>
 
 <div class="bg_pop">
 	<div class="pop">
 		<img alt="关闭窗口" src="${ctx}/images/close_pop.png" id="close_pop" width="20px" height="20px"/>
 	    <h2 >添加新地址</h2>
 	    <form action="${ctx}/address/addAddress" method="post">
 	    <div id="address_content"> 		
 		<div><label for="content" class="control-label">地址内容:</label>	
 		<input class="form-control" type="text" name="content" />
 		</div>
 		<div><label for="name" class="control-label">收件人:</label>	
 		<input class="form-control" type="text" name="name" />
 		</div>
 		<div><label for="tel" class="control-label">电&nbsp;&nbsp;话:</label>	
 		<input class="form-control" type="text" name="tel" />
 		</div>
 		</div>
 		<div align="center" class="btn_address">
 		<button type="submit" class="btn btn-success form-control updateuser" >添加新地址</button>
 		</div>
 		</form>
 	</div>
 </div>
 <!-- 修改地址操作 -->
 <div class="bg_pop1">
 	<div class="pop1">
 		<img alt="关闭窗口" src="${ctx}/images/close_pop.png" id="close_pop1" width="20px" height="20px"/>
 	    <h2 >修改地址</h2>
 	    <form action="${ctx}/address/updateAddress" method="post">
 	    <div id="address_content"> 		
 		<div><label for="content" class="control-label">地址内容:</label>	
 		<input class="form-control" type="text" name="content" id="content"/>
 		</div>
 		<div><label for="name" class="control-label">收件人:</label>	
 		<input class="form-control" type="text" name="name" id="name"/>
 		</div>
 		<div><label for="tel" class="control-label">电&nbsp;&nbsp;话:</label>	
 		<input class="form-control" type="text" name="tel" id="tel"/>
 		</div>
 		</div>
 		<div align="center" class="btn_address">
 		<button type="submit" class="btn btn-success form-control updateuser" >修改地址</button>
 		<button class="btn btn-danger form-control updateuser" >删除地址</button>
 		</div>
 		</form>
 	</div>
 </div>
 <!--每次操作完后的提示消息  -->
 <!--每次操作完后的提示消息  -->
<div class="alert alert-info alert-dismissibl tishi " id="xiaoxi" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <span id="message" style="font-size: 14px;"></span>
</div>
 
 
</body>
</html>