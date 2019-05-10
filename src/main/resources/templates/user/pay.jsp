<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>确认订单页面</title> 
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
         <a href="#">该用户还没有地址信息</a>           
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
	
	<!--  购物车表单-->
  <div class="l-width">	
        <div class="l-width table_title" style="height: 50px;">	
           <h2>订单确认</h2>
        </div>	 
		<table id="tb" class="table table-striped table-bordered table-hover  table-condensed booklist">			  
                    <tbody>
                   	   <tr>
                       <th colspan="6">借阅人基本信息</th>
                       </tr>
                       <tr>
                         <th>姓名</th> 
                         <th>借阅证号</th>
                         <th>联系方式</th>                 
                         <th>地址</th>
                         <th colspan="2">备注</th>                                               
                       </tr>
                        <tr>
                         <td>${user.login_name }</td> 
                         <td>${user.card_number }</td>
                         <td>${user.tel }</td>                 
                         <td><select class="form-control" id="select_address">                        	
                         	 <c:forEach items="${addresses }" var="address">
                         	 	<option value="${address.address_id }">${address.content }</option>
                         	 </c:forEach>
                         	 </select>
                         </td>
                         <td colspan="2"><input type="text" name="remarks" id="remarks" class="form-control"/></td>                                               
                       </tr>        
                       <tr>
                       <th colspan="6">订单详细信息</th>
                       </tr>
                       <tr>                                         
                         <th>书名</th>              
                         <th>作者</th>                      
                         <th>索取号</th>
                         <th>出版年份</th>
                         <th>出版社</th>
                         <th>数目</th>
                      </tr>           
                      <c:forEach items="${books }" var="book" varStatus="stat">
                   			<tr>
                   				<td style="display: none"><input type="checkbox" id="box_${stat.index}" value="${book.cart_id}" checked="checked"/></td>
                   				<td>${book.book_name }</td>
                   				<td>${book.book_author }</td>
                   				<td>${book.book_index }</td>
                   				<td>${book.publish_year }</td>
                   				<td>${book.book_press }</td>
                   				<td>${book.number }</td>
                   			</tr>
                   		</c:forEach>   
                     </tbody>	
			</table>
			<div class="l-width" style="height: 50px; background-color: #666">		
			<a id="submit_pay" class="btn btn-success btn-lg pull-right" style="margin-top: 2px; margin-right: 10px;">提交订单</a> 
			<a id="reset_pay" class="btn btn-danger btn-lg pull-right" style="margin-top: 2px; margin-right: 10px;">取消订单</a></div>		
    </div>	
	<script type="text/javascript">
	$(document).ready(function(){
		var boxs = $("input[type='checkbox'][id^='box_']");
        
		$("#submit_pay").click(function() {
			var checkedBoxs = boxs.filter(":checked");
        	var ids = checkedBoxs.map(function(){
				   return this.value;
			   });
        	var remarksContent = $("#remarks").val();
        	var address_id = $("#select_address option:selected").val();
            swal({
                title: "提示",
                text: "您确定要提交订单么？",
                type: "warning",
                showCancelButton: true,
                closeOnConfirm: false,
                confirmButtonText: "是的，我要下单",
                confirmButtonColor: "#ec6c62"
            }, function() {          
              window.location = "${ctx }/order/placeOrder?address_id=" + address_id+"&remarks="+remarksContent+"&bookIds="+ids.get();
            });
        });
	});
	$("#reset_pay").click(function(){
		 swal({
             title: "提示",
             text: "您确定取消订单么？",
             type: "warning",
             showCancelButton: true,
             closeOnConfirm: false,
             confirmButtonText: "取消订单",
             confirmButtonColor: "#ec6c62"
         },function(){
        	swal("取消成功", "您的订单取消成功!", "success");
        	//然后从定向跳转到主页
        	setTimeout(function(){
        	window.location = "${ctx }/user/main";	
        	},2000);
         });
	});
</script>
	
		
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
 
 
<div class="bg_pop">
 	<div class="pop">
 		<img alt="关闭窗口" src="${ctx}/images/close_pop.png" id="close_pop" width="20px" height="20px"/>
 	    <h2 >添加新地址</h2>
 	    <form action="${ctx}/address/addAddress" method="post">
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
 		<input class="form-control" type="text" name="content" />
 		</div>
 		<div><label for="name" class="control-label">收件人:</label>	
 		<input class="form-control" type="text" name="name" />
 		</div>
 		<div><label for="tel" class="control-label">电&nbsp;&nbsp;话:</label>	
 		<input class="form-control" type="text" name="tel"/>
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