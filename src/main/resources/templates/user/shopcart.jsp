<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
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
<title>购物车界面</title>
<script type="text/javascript">
	$(function(){
		var boxs = $("input[type='checkbox'][id^='box_']");
		
		/** 给全选按钮绑定点击事件  */
		$("#checkAll").click(function(){
			// this是checkAll  this.checked是true
			// 所有数据行的选中状态与全选的状态一致
			boxs.attr("checked",this.checked);
		})
		
		/** 删除员工绑定点击事件 */
		$("#delete_btn").click(function(){
			   /** 获取到用户选中的复选框  */
			   var checkedBoxs = boxs.filter(":checked");
			   if(checkedBoxs.length < 1){
				   swal("操作提示！", "当前无选中项！", "warning");
				   return;
			   }else{
				   /** 得到用户选中的所有的需要删除的ids */
				   var ids = checkedBoxs.map(function(){
					   return this.value;
				   });
				   
				  
					 if(confirm("确认删除选中的信息么?")){
						   // alert("删除："+ids.get());
						   // 发送请求
						   window.location = "${ctx }/userOperation/deleteBookFromCart?cart_ids=" + ids.get();
					   }
			   }
		});
		
		$("#submit_cart").click(function(){
			   /** 获取到用户选中的复选框  */
			   var checkedBoxs = boxs.filter(":checked");
			   if(checkedBoxs.length < 1){
				   swal("操作提示！", "当前无选中项！", "warning");
				   return;
			   }else{
				   /** 得到用户选中的所有的需要删除的ids */
				   var ids = checkedBoxs.map(function(){
					   return this.value;
				   });
				   
				  
					 if(confirm("确认生成订单么?")){
						   // alert("删除："+ids.get());
						   // 发送请求						
						   window.location = "${ctx }/userOperation/placeOrder?bookIds=" + ids.get();
					   }
			   }
		});
		
		/* //判断number是否为数字且是否符合实际
		$("table").delegate('table tr .number','blur',function(){		
			var reg = /^\+?([0-9]{1})|([1-9][0-9]*)$/;
		    var checknum = $(this).val();
			if(reg.test(checknum) == false){
			  swal("输入提示！", "您输入的合法的整型数字,请修改!", "warning");
			  $(this).val(0);
			} 
		}); */
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
	
	<!--  购物车表单-->
  <div class="l-width">	
        <div class="l-width table_title" style="height: 50px;">	
           <h2>购物车Order信息</h2>
        </div>	 
		<table id="tb" class="table table-striped table-bordered table-hover  table-condensed booklist">
			  		<thead>
                      <tr>
                      	 <th><input type="checkbox" id="checkAll"/></th>
                         <th>书名</th>              
                         <th>作者</th>                      
                         <th>索取号</th>
                         <th>出版年份</th>
                         <th>出版社</th>
                         <th>数目</th>
                         <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>           
                   		<c:forEach items="${cart }" var="book" varStatus="stat">
                   			<tr>
                   				<td><input type="checkbox" id="box_${stat.index}" value="${book.cart_id}"/></td>
                   				<td>${book.book_name }</td>
                   				<td>${book.book_author }</td>
                   				<td>${book.book_index }</td>
                   				<td>${book.publish_year }</td>
                   				<td>${book.book_press }</td>
                   				<td>${book.number }</td>
                   				<td><a href="${ctx }/userOperation/deleteBookFromCart?cart_ids=${book.cart_id}" class="btn btn-danger btn-sm">删除</a></td>
                   			</tr>
                   		</c:forEach>
						<tr valign="top"><td align="center" class="font3" colspan="8">
							<fkjava:pager
								pageIndex="${requestScope.pageModel.pageIndex}" 
								pageSize="${requestScope.pageModel.pageSize}" 
								recordCount="${requestScope.pageModel.recordCount}" 
								style="digg"
								submitUrl="${ctx}/userOperation/bookCart?pageIndex={0}"/>
						</td></tr>
                    </tbody>	
			</table>
			<div class="l-width" style="height: 50px; background-color: #666">
			<button id="delete_btn" class="btn btn-danger btn-lg pull-left" style="margin-top: 2px; margin-left: 10px;">批量删除</button>		
			<button id="submit_cart" class="btn btn-success btn-lg pull-right" style="margin-top: 2px; margin-right: 10px;" >去下单</button> 
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