<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctx}/js/jquery/2.0.0/jquery.min.js"></script>
<link href="${ctx}/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="${ctx}/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<link href="${ctx}/css/maintop.css" rel="stylesheet">
<link href="${ctx}/css/footer.css" rel="stylesheet">
<link href="${ctx}/css/tableset.css" rel="stylesheet">
<title>收藏界面</title>
    
</head>
<body>
 <div class="wrap">
  <div id="header">
    <div class="top" id="top">
				<li>送书地址:
					<a href="" id="useraddress">信息科学楼B-3419</a>
				</li>
				<div id="topright">
				
					<li>
						<a href="#">你好！请登录</a>
					</li>
					<li>
						<a href="#">免费注册</a>
					</li>
					
					<li>|
						<a href="shopcart">我的订单</a>
					</li>
					<li>|
						<a href="">收藏夹 <i class="arrow"></i></a>
						<ul>
							<li><a href="history">已借的书籍</a></li>
							<li><a href="shouchang">收藏的书籍</a></li>
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
							<li><a href="personsetting">个人中心</a></li>
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
	<!--  收藏书籍表单-->
	<div class="l-width">	
        <div class="l-width table_title" style="height: 50px;">	
           <h2>收藏书籍信息</h2>
        </div>	 
		<table id="tb" class="table table-striped table-bordered table-hover  table-condensed booklist">
			  		<thead>
                      <tr>
                         <th>书名</th> 
                         <th>馆藏</th>
                         <th>可借</th>                 
                         <th>索取号</th>
                         <th>作者</th>                      
                         <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>           
                      <tr>                       
                        <td>挪威的森林</td>                    
                        <td>4</td>
                        <td>1</td>
                        <td>I313.4/C987/4/-6</td>
                        <td>村上春树著；林少华译</td>
                        <td><button id="button1" class="btn btn-success btn-sm">添加</button>
                        <button class="btn btn-danger btn-sm">删除</button>
                      </tr>
                      <tr>
                        <td>挪威的森林</td>                    
                        <td>4</td>
                        <td>1</td>
                        <td>I313.4/C987/4/-6</td>
                        <td>村上春树著；林少华译</td>
                        <td><button id="button1" class="btn btn-success btn-sm">添加</button>
                        <button class="btn btn-danger btn-sm">删除</button>
                        </td>                       
                      </tr>        
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
 	 	<li><a href="${ctx}/user/shopcart"><img src="${ctx}/images/shopcart.png"/></a><div class="toggle_title">购物车</div></li>
 	 	<li><a href="${ctx}/user/shouchang"><img src="${ctx}/images/shouchang.png"/></a><div class="toggle_title">我的收藏</div></li>
 	 	<li><a href="${ctx}/user/history"><img src="${ctx}/images/history.png"/></a><div class="toggle_title">订单管理</div></li>
 	 	<li style="height: 100px;" class="depart"></li>
 	 	<li><a href="#top"><img src="${ctx}/images/top01.png"/></a><div class="toggle_title return_top">返回顶部</div></li>
 	 </ul>
 </div>	
  
 </div>
</body>
</html>