<!DOCTYPE >
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript" src="${ctx }/js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="${ctx }/css/bootstrap/bootstrap.min.css" />
<script type="text/javascript" src="${ctx }/js/bootstrap/bootstrap.min.js"></script>

<link rel="stylesheet" href="${ctx }/css/worker/com.css" />
<link rel="stylesheet" href="${ctx }/css/worker/header.css" />
<link rel="stylesheet" href="${ctx }/css/worker/section.css" />
<link rel="stylesheet" href="${ctx }/css/worker/footer.css" />

<script type="text/javascript" src="${ctx }/js/global.js"></script>

<title></title>
<style type="text/css">
	input[type="checkbox"] {
		width: 20px;
		height: 20px;
	}
</style>
<title>工作人员页面</title>
<script type="text/javascript">
	$(document).ready(function() {
		identity = $("#identityID").html();
		
		var is_finder = false;
		var is_register = false;
		var is_transporter = false;
		
		changeInfo = function() {
			$.ajax({
				url: getRealPath()+"/worker/workerInfo",
				dataType: "json",
				type: "POST",
				success: function(data){
					$("#inputName").val(data.name);
					$("#inputName1").val(data.login_name);
					$("#inputName2").val(data.tel);
				}
			});
		};
		
		updateWorkerInfo = function(){
			$.ajax({
				url: getRealPath()+"/worker/checkWorkerId",
				data: {
					"login_name" : $("#inputName1").val()
				},
				dataType: "text",
				type: "POST",
				success: function(data){
					if(data != "true"){
						alert(data);
					}else{
						$.ajax({
							url: getRealPath()+"/worker/updateWorkerInfo",
							data: {
								"login_name" : $("#inputName1").val(),
								"name": $("#inputName").val(),
								"tel": $("#inputName2").val()
							},
							dataType: "json",
							type: "POST",
							success: function(data){
								alert(data.message);
							}
						});
					}
				}
			});
		}
		
		changePasswd = function(){
			$.ajax({
				url: getRealPath()+"/worker/changePasswd",
				data: {
					"oldPasswd" : $("#inputOldPassword").val(),
					"newPasswd": $("#inputNewPassword").val()
				},
				dataType: "json",
				type: "POST",
				success: function(data){
					alert(data.message);
				}
			});
		}
		
		$("form#identity").submit(function(e) {
			var checkboxs = $("form#identity input[type='checkbox']");
			is_finder = checkboxs[0].checked;
			is_register = checkboxs[1].checked;
			is_transporter = checkboxs[2].checked;
			checkIdentity();
			$("#close").click();
			return false;
		});
		
		checkIdentity = function() {
			$.ajax({
				url: getRealPath()+"/worker/checkUnfinished",
				dataType: "json",
				type: "POST",
				success: function(data){
					if(data){
						alert("已经有一个身份请求在处理中");
					}else{
						changeIdentity();
					}
				}
			});
		}
		
		changeIdentity = function () {
			var identity = 0;
			
			if(is_finder && is_register && is_transporter) {
				identity = 7;
			}
			else if(is_register && is_transporter) {
				identity = 6;
			}
			else if(is_finder && is_transporter) {
				identity = 5;
			}
			else if(is_finder && is_register) {
				identity = 4;
			}
			else if(is_transporter){
				identity = 3;
			}
			else if(is_register){
				identity = 2;
			}
			else if(is_finder){
				identity = 1;
			}
			
			if(identity > 0){
				$.ajax({
					url: getRealPath()+"/worker/changeIdentity",
					data: {
						"identity" : identity
					},
					dataType: "json",
					type: "POST",
					success: function(data){
						alert(data.message);
					}
				});
			}
		}
		
		selectBooks = function(state) {
			$.ajax({
				url: getRealPath()+"/workerOperation/selectBooks",
				data: {
					"state" : state
				},
				dataType: "json",
				type: "POST",
				success: function(data){
					$("tbody").html("");
					if(state == '5' && (identity == '1' || identity == '4' || identity == '5' || identity == '7')){
						$.each(data, function(i, item) {
							/* <td><input type='checkbox' id='box_"+i+"' value='"+item.books_id+"'/></td> */
							$("tbody").append("<tr>"+
			                        "<td>"+item.order_id+"</td>"+
			                        "<td>"+item.book_name+"</td>"+
			                        "<td>"+item.book_index+" </td>"+
			                        "<td>"+item.number+"</td>"+
			                        "<td>"+item.book_author+"</td>"+
			                        "<td>"+item.book_press+"</td>"+
			                        "<td><button class='btn btn-warning' onclick='changeState("+item.books_id+", 7)'>已找到</button> "+
									"<button class='btn btn-warning' onclick='changeState("+item.books_id+", 6)'>未找到</button>"+
			                        "</td></tr>");
						});
					}else if(state == '7' && (identity == '2' || identity == '4' || identity == '6' || identity == '7')){
						$.each(data, function(i, item) {
							$("tbody").append("<tr>"+
			                        "<td>"+item.order_id+"</td>"+
			                        "<td>"+item.book_name+"</td>"+
			                        "<td>"+item.book_index+" </td>"+
			                        "<td>"+item.number+"</td>"+
			                        "<td>"+item.book_author+"</td>"+
			                        "<td>"+item.book_press+"</td>"+
			                        "<td><button class='btn btn-warning' onclick='changeState("+item.books_id+", 8)'>登记成功</button> "+
									"<button class='btn btn-warning' onclick='changeState("+item.books_id+", 9)'>登记失败</button>"+
			                        "</td></tr>");
						});
					}else if(state == '8' && (identity == '3' || identity == '5' || identity == '6' || identity == '7')){
						$.each(data, function(i, item) {
							$("tbody").append("<tr>"+
			                        "<td>"+item.order_id+"</td>"+
			                        "<td>"+item.book_name+"</td>"+
			                        "<td>"+item.book_index+" </td>"+
			                        "<td>"+item.number+"</td>"+
			                        "<td>"+item.book_author+"</td>"+
			                        "<td>"+item.book_press+"</td>"+
			                        "<td><button class='btn btn-warning' onclick='changeState("+item.books_id+", 10)'>派送成功</button> "+
									"<button class='btn btn-warning' onclick='changeState("+item.books_id+", 11)'>派送失败</button>"+
			                        "</td></tr>");
						});
					}else{
						$.each(data, function(i, item) {
							$("tbody").append("<tr>"+
			                        "<td>"+item.order_id+"</td>"+
			                        "<td>"+item.book_name+"</td>"+
			                        "<td>"+item.book_index+" </td>"+
			                        "<td>"+item.number+"</td>"+
			                        "<td>"+item.book_author+"</td>"+
			                        "<td>"+item.book_press+"</td>"+
			                        "<td><button class='btn btn-warning'>无操作</button> "+
			                        "</td></tr>");
						});
					}
				}
			});
		}
		
		changeState = function(books_id, state) {
			$.ajax({
				url: getRealPath()+"/workerOperation/changBookState",
				data: {
					"books_id" : books_id,
					"state_id" : state
				},
				dataType: "json",
				type: "POST",
				success: function(data){
					if(data == true){
						alert("操作完成");
						if(state == '6' || state == '7'){
							selectBooks(5);
						}
						if(state == '8' || state == '9'){
							selectBooks(7);
						}
						if(state == '10' || state == '11'){
							selectBooks(8);
						}
					}else{
						alert("操作失败");
					}
				}
			});
		}
	});
</script>
</head>
<body>
		<header>
			<!-- 头部图片或标题 -->
			<c:choose>
				<c:when test="${requestScope.message != null }">
					<p class="bg-warning" style="text-align: center; color: #c40000; background-color: rgba(0,0,0,0); height: 20px;"><span class="glyphicon glyphicon-info-sign" style="margin-right: 5px;height: 20px; line-height: 20px; font-size: 15px"></span>
				  		<span style="margin-right: 5px;height: 20px; line-height: 20px;">${requestScope.message }</span>
				 	</p>
				</c:when>
			</c:choose>
			<div class="dropdown view_po">
				<div class="btn-group">
					<button type="button" class="btn btn-info">${worker.login_name }</button>
					<button type="button" class="btn btn-info dropdown-toggle" style="transform:translate(0,7.5px) scale(1,1.83);" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				        <span class="caret"></span>
				        <span class="sr-only">Toggle Dropdown</span>
    			  	</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
						<!--<li class="dropdown-header">Dropdown header</li>-->
						<li>
							<a href="#" onclick="changeInfo();" data-toggle="modal" data-target="#info_update">修改基本信息</a>
						</li>
						<li>
							<a href="#" data-toggle="modal" data-target="#pwd_update">修改密码</a>
						</li>
						<li>
							<a href="#" data-toggle="modal" data-target="#identity_update">更换身份</a>
						</li>
						<li role="separator" class="divider"></li>
						<li>
							<a href="${ctx }/worker/logout">退出登录</a>
						</li>
					</ul>
				</div>
			</div>
		</header>
		<section>
			<div class="panel panel-default">
				<div class="panel-heading">
					<span>当前身份：
						<c:choose>
							<c:when test="${worker.identity == 8}">
								<span id="identityID" style="display: none;">${worker.identity }</span>
								未审核人员
							</c:when>
						</c:choose>
						<c:forEach items="${identities }" var="identity">
							<c:choose>
								<c:when test="${identity.identity == worker.identity }">
									<span id="identityID" style="display: none;">${worker.identity }</span>
									${identity.name }
								</c:when>
							</c:choose>
						</c:forEach>
					</span>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    					可操作类型
    					<span class="caret"></span>
 						 </button>
						<ul class="dropdown-menu">
							<c:forEach items="${states }" var="state">
								<li><a href="javascript:void(0);" onclick="selectBooks('${state.state_id}')">${state.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="panel-body">
					<!-- Table -->
					<table class="table table-hover">
						<thead>
							<tr>
								<!-- <th><input type="checkbox" id="checkAll"/></th> -->
								<th>订单id</th>
								<th>图书名称</th>
								<th>索引号</th>
								<th>图书数目</th>
								<th>作者</th>
								<th>出版社</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>
				<div class="panel-footer">
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<li>
								<a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<li>
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#">4</a>
							</li>
							<li>
								<a href="#">5</a>
							</li>
							<li>
								<a href="#" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</section>
		<footer>
		</footer>
		<!-- Modals -->

		<!--查看或修改个人信息-->
		<div class="modal fade" id="info_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">修改基本信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="${ctx }/worker/updateWorkerInfo" method="POST">
							<div class="form-group">
								<label for="inputName" class="col-sm-2 control-label">Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName" name="name" placeholder="Name">
								</div>
								<label for="inputName1" class="col-sm-2 control-label" style="margin-top: 10px;">登录名</label>
								<div class="col-sm-10" style="margin-top: 10px;">
									<input type="text" class="form-control" id="inputName1" name="login_name" placeholder="登录名">
								</div>
								<label for="inputName2" class="col-sm-2 control-label" style="margin-top: 10px;">电话号码</label>
								<div class="col-sm-10" style="margin-top: 10px;">
									<input type="tel" class="form-control" id="inputName2" name="tel" placeholder="电话号码">
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<button type="button" onclick="updateWorkerInfo()" class="btn btn-primary" data-dismiss="modal">Save changes</button>
							</div>
						</form>
					</div>
					
				</div>
			</div>
		</div>

		<!--修改密码-->
		<div class="modal fade" id="pwd_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">修改密码</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="${ctx }/worker/changePasswd" method="POST">
							<div class="form-group">
								<label for="inputOldPassword" class="col-sm-2 control-label">Old Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" name="oldPasswd" id="inputOldPassword" placeholder="Old Password">
								</div>
							</div>
							<div class="form-group">
								<label for="inputNewPassword" class="col-sm-2 control-label">New Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" name="newPasswd" id="inputNewPassword" placeholder="New Password">
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<button onclick="changePasswd()" class="btn btn-primary"  data-dismiss="modal">Save changes</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!--更换身份-->
		<div class="modal fade" id="identity_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">更换身份</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="identity">
							<div class="form-group">
									<span class="input-group-addon">
										<div class="col-sm-2">
										<label for="m_add_worker_i1">找书员</label>
      									<input id="m_add_worker_i1" type="checkbox" class="ajust_checkbox" style="transform: translate(0px, 3px);" value="1" name="identity1">
      									</div>
										<div class="col-sm-2">
										<label for="m_add_worker_i2">登记员</label>
      									<input id="m_add_worker_i2" type="checkbox" class="ajust_checkbox" style="transform: translate(0px, 3px);" value="1" name="identity2">
      									</div>
										<div class="col-sm-2">
										<label for="m_add_worker_i3">派送员</label>
      									<input id="m_add_worker_i3" type="checkbox" class="ajust_checkbox" style="transform: translate(0px, 3px);" value="1" name="identity3">
      									</div>
									</span>
								</div>
							<!-- <div class="checkbox">
								<label>
      								<input type="checkbox" id="finder"> 找书员
   								</label>
								<label>
      								<input type="checkbox" id="register"> 登记员
      								
   								</label>
								<label>
      								<input type="checkbox" id="transporter"> 派送员
      								
   								</label>
							</div> -->
							<div class="modal-footer">
								<button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Save changes</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>