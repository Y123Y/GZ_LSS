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
<script type="text/javascript" src="${ctx }/js/global.js"></script>

<link href="${ctx}/css/maintop.css" rel="stylesheet">
<link href="${ctx}/css/footer.css" rel="stylesheet">
<link href="${ctx}/css/tableset.css" rel="stylesheet">
<title>历史借阅界面</title>
    

<script type="text/javascript">
	$(document).ready(function(){	
		$("tr.parent").click(function(){
			$(this).siblings(".child_" + this.id).toggle();
		});
		
		$(".btn_showdetail").click(function(e){
			e.stopPropagation();
			var parent = $(this).parent().parent();
			var order_id  = $(this).parent().parent().children("#orderid").html();
			var row_id = $(this).parent().parent().attr("id");
			
		/* 	//测试是否取到值
			alert(order_Id+"**"+row_id);
			return; */		
			$.ajax({
				url: getRealPath()+"/order/showOrderDetail",
				dataType: "json",
				data:{
					"order_id" : order_id
				},
				type: "POST",
				success: function(data){				
					$.each(data, function(i, data) {
					$(parent).after("<tr class='child_"+row_id+"'>"+
		                        "<td>"+data.book_name+"</td>"+
		                        "<td>"+data.book_author+"</td>"+
		                        "<td>"+data.book_index+" </td>"+
		                        "<td>"+data.book_press+"</td>"+
		                        "<td colspan='2'>数量:"+data.number+"</td>"+		                       		                      
		                        "</tr>");
					});
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
				 swal("未知错误!", "提示:"+XMLHttpRequest.readyState +"***"+ XMLHttpRequest.status +"***"+ XMLHttpRequest.responseText, "error");		
				}
			});

			$(this).attr("disabled",true);
		});
			
		$(".btn_delete").click(function(e){
			var order_Id  = $(this).parent().parent().children("#orderid").html();
			//防止子元素的事件被父元素执行
			e.stopPropagation();
		/* 	alert(order_Id);
			return; */ 
			 swal({
	                title: "提示",
	                text: "您确定要删除该记录么？",
	                type: "warning",
	                showCancelButton: true,
	                closeOnConfirm: false,
	                confirmButtonText: "是的，我要删除",
	                confirmButtonColor: "#ec6c62"
	            },function(){
	            	 //删除历史订单信息
	            	 window.location = "${ctx }/order/deleteOrder?order_id="+order_Id;
	            });
		});
	});

</script>

<style type="text/css">
	.parent{
		background: #999 !important;
		
	}
	.parent>td{
	  color:#fff !important;
	}
	.parent:HOVER{
		background: #888 !important;
	}
	tr[class^='child_']{
	 /* display: none; */
	}

</style>
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
	
	<!--  订单历史和管理表单-->
	<div class="l-width">	
        <div class="l-width table_title" style="height: 50px;">	
           <h2>订单历史管理</h2>
        </div>	 
		<table id="tb" class="table table-striped table-bordered table-hover  table-condensed booklist">
			  		<thead>
                      <tr>
                         <th>创建时间</th>               
                         <th>完成时间</th>
                         <th>派送时间</th>
                         <th>备注</th>
                         <th>状态</th>
                         <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                  <!--   <tr class="parent" id="row_01"> 
                    	<td style="display: none" id="orderid">11</td>                                                   
                        <td>2018/1/12</td>                    
                        <td>2018/1/13</td> 
                        <td>2018/1/14</td> 
                        <td>快点,花都谢了</td>                 
                        <td>已经完成</td>
                        <td><button  class="btn btn-danger btn-sm btn_delete">删除</button></td>
                      </tr> 
                      <tr class="child_row_01">  
                        <td>追风筝的人</td>               
                        <td>春上春树</td>                    
                        <td>E728/2345</td>                    
                        <td>人民大学出版设</td>
                        <td>2</td>
                        <td></td>
                      </tr>  
                        <tr class="child_row_01">  
                        <td>追风筝的人</td>               
                        <td>俊涛</td>                    
                        <td>E728/2345</td>                    
                        <td>大学</td>
                        <td>1</td>
                        <td>00</td>                    
                      </tr>    -->
                     <c:forEach items="${orders }" var="order" varStatus="stat">
                   			<tr class="parent" id="row_${stat.index}">
                   				<td style="display: none" id="orderid">${order.order_id}</td>                 			
                   				<td>${order.create_time }</td>
                   				<td>${order.finish_time }</td>
                   				<td>${order.deliver_time }</td>                 
                   				<td>${order.remarks }</td>
                   				<td>${order.state }</td>
                   				<td><button  class="btn btn-danger btn-sm btn_delete">删除</button>
                   					<button  class="btn btn-info btn-sm btn_showdetail">详情</button>
                   				</td>
                   			</tr>
                   	</c:forEach>                                               	          
                    
                   <%--  <tr valign="top"><td align="center" class="font3" colspan="6">
							<fkjava:pager
								pageIndex="${requestScope.pageModel.pageIndex}" 
								pageSize="${requestScope.pageModel.pageSize}" 
								recordCount="${requestScope.pageModel.recordCount}" 
								style="digg"
								submitUrl="${ctx}/order/history?pageIndex={0}"/>
						</td>
						</tr> --%>
                  
                     </tbody>	
			</table>
			<div class="l-width" style="height: 50px; background-color: #666"></div>		
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
<div class="alert alert-info alert-dismissibl tishi " id="xiaoxi" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <span id="message" style="font-size: 14px;"></span>
</div>

</body>
</html>