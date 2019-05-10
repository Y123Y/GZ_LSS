function getRealPath(){
	  var curWwwPath=window.document.location.href;
	  var pathName=window.document.location.pathname;
	  var pos=curWwwPath.indexOf(pathName);
	  var localhostPaht=curWwwPath.substring(0,pos);
	  // var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	  var realPath=localhostPaht;//+projectName;
	  return realPath;
}

$(function() {

	/* initialize */
	var pre_url = getRealPath();

	$("#p_list_admin").hide();
	$("#p_list_review").hide();

	/* 本地模拟数据测试开关 */
	var tip_debug = false;

	/* 个人信息存储 */
	var account = '';
	var name = '';

	init_info();

	function init_info() {
		/* 对 个人信息存储部分进行更新 */
		if(tip_debug) {
			account = 'guozhi';
			name = 'gz';
		}else{
			account=$("#_account").html();
			name=$("#_name").html();
		}
	}

	if(tip_debug) {
		var datas = [{
			'login_name': 'guozhi',
			'name': 'gz',
			'identity': '找书员',
			'tel': '15871577079',
			'worker_id': '23'
		}, {
			'login_name': 'guozhi2',
			'name': 'gz2',
			'identity': '找书员',
			'tel': '15871577070',
			'worker_id': '43'
		}];
		get_list_worker_res(datas);
	} else {
		get_list_worker();
	}

	/* 工作人员表格 */
	$("#index_list_worker").click(function() {
		if(tip_debug) {
			var datas = [{
				'login_name': 'guozhi',
				'name': 'gz',
				'identity': '找书员',
				'tel': '15871577079',
				'worker_id': '23'
			}, {
				'login_name': 'guozhi2',
				'name': 'gz2',
				'identity': '找书员',
				'tel': '15871577070',
				'worker_id': '43'
			}];
			get_list_worker_res(datas);
		} else {
			get_list_worker();
		}

		$("#p_list_worker").slideToggle();
		$("#p_list_admin").hide();
		$("#p_list_review").hide();

	})

	/* 获取全部的工作人员 */
	function get_list_worker() {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			success: get_list_worker_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function get_list_worker_res(datas) {
		console.log('updateAnswer:' + datas);
		if(datas == undefined || datas == '') {
			return;
		}
		$("#p_list_worker tbody").empty();
		$.each(datas, function(index, data) { //遍历json数组
			var atr = $("<tr></tr>");
			var loginName = $('<td>' + data.login_name + '</td>');
			var name = $('<td>' + data.name + '</td>');
			var identity = $('<td>' + data.identity + '</td>');
			var tel = $('<td>' + data.tel + '</td>');
			var worker_id = $('<td><button class="btn btn-warning p_alter_worker_btn" data-toggle="modal" data-target="#m_alter_worker" id=' + data.worker_id + ' >修改</button></td>');
			atr.append(loginName);
			atr.append(name);
			atr.append(identity);
			atr.append(tel);
			atr.append(worker_id);
			$("#p_list_worker tbody").append(atr);
		})
	}

	/* 添加工作人员 */
	$("#form_add_worker_sub").click(function() {

		var loginName = $("#form_add_worker input[name='login_name']").val();
		var passwd = $("#form_add_worker input[name='passwd']").val();
		var passwd2 = $("#form_add_worker input[name='passwd2']").val();
		var name = $("#form_add_worker input[name='name']").val();
		var tel = $("#form_add_worker input[name='tel']").val();
		var identity1 = $("#form_add_worker input[name='identity1']:checked").val();
		var identity2 = $("#form_add_worker input[name='identity2']:checked").val();
		var identity3 = $("#form_add_worker input[name='identity3']:checked").val();

		if(loginName == undefined || loginName == '') {
			alert("用户名不能为空！");
			return;
		}

		if(passwd == undefined || passwd2 == undefined || passwd != passwd2) {
			alert("两次密码输入不一致");
			return;
		}

		if(name == undefined || name == '') {
			alert("姓名不能为空！");
			return;
		}

		if(identity1 == undefined && identity2 == undefined && identity3 == undefined) {
			alert("身份选择不能为空");
			return;
		}

		var iden = 0;
		if(identity1 != undefined && identity2 == undefined && identity3 == undefined) {
			iden = 1;
		} else if(identity1 == undefined && identity2 != undefined && identity3 == undefined) {
			iden = 2;
		} else if(identity1 == undefined && identity2 == undefined && identity3 != undefined) {
			iden = 3;
		} else if(identity1 != undefined && identity2 != undefined && identity3 == undefined) {
			iden = 4;
		} else if(identity1 != undefined && identity2 == undefined && identity3 != undefined) {
			iden = 5;
		} else if(identity1 == undefined && identity2 != undefined && identity3 != undefined) {
			iden = 6;
		} else if(identity1 != undefined && identity2 != undefined && identity3 != undefined) {
			iden = 7;
		} else {
			alert("身份选择有异常，请重新刷新页面！");
			return;
		}

		var data = {
			'login_name': loginName,
			'passwd': passwd,
			'name': name,
			'tel': tel,
			'identity': iden
		}

		$("#m_add_worker").hide();

		/* 这里添加等待动画  */

		if(tip_debug) {
			var temp={
				'login_name': 'guozhi3',
				'passwd': '123123',
				'name': 'gz3',
				'tel': '1587157706',
				'identity': '找书员',
				'worker_id':12
			}
			add_worker_res(temp);
		} else {
			add_worker(data);
		}

		/* 这里关闭等待动画  */

	})

	function add_worker(data) {
		$.ajax({
			'type': "post",
			'url': pre_url + "/adminOperation/addWorker",
			'async': true, //同步上传数据
			'data': data,
			'dataType': 'json',
			'success': add_worker_res,
			'error': function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function add_worker_res(datas) {
		if(tip_debug){
			console.log("add_worker_res:" + datas);
			return ;
		}
		if(datas.type==0){
			alert("添加工作人员失败，请确认用户名存在");
			return;
		}
		var data=datas.worker;
			var atr = $("<tr></tr>");
			var loginName = $('<td>' + data.login_name + '</td>');
			var name = $('<td>' + data.name + '</td>');
			var identity = $('<td>' + data.identity + '</td>');
			var tel = $('<td>' + data.tel + '</td>');
			var worker_id = $('<td><button class="btn btn-warning p_alter_worker_btn" id=' + data.worker_id + ' >修改</button></td>');
			atr.append(loginName);
			atr.append(name);
			atr.append(identity);
			atr.append(tel);
			atr.append(worker_id);
			$("#p_list_worker tbody").append(atr);
	}

	/* 修改工作人员信息 */

	$("#form_alter_worker_flu").click(function() {
		var login_name = $("#form_alter_worker input[name='login_name']").val();
		if(login_name == undefined || login_name == '') {
			alert("请输入用户名查询工作人员");
			return;
		}

		var data = {
			'login_name': login_name
		}

		if(tip_debug) {
			var temp = {
				'worker_id': 12,
				'passwd': '123123',
				'name': 'gz',
				'tel': '15871757712',
				'identity': 1
			};
			worker_flush_res(temp);
		} else {
			worker_flush(data);
		}
	})

	function worker_flush(data) {
		$.ajax({
			'type': "post",
			'url': pre_url + "/adminOperation/all_ans",
			'async': false, //同步上传数据
			'data': data,
			dataType: "json",
			'success': worker_flush_res,
			'error': function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function worker_flush_res(data) {
		console.log("worker_flush_res:" + data);
		if(data == undefined || data == '') {
			alert('请输入正确的用户名');
			return;
		}
		$("#form_alter_worker input[name='worker_id']").attr('value', data.worker_id);
		$("#form_alter_worker input[name='login_name']").attr('value', data.login_name);
		$("#form_alter_worker input[name='passwd']").attr('value', data.passwd);
		$("#form_alter_worker input[name='tel']").attr('value', data.tel);

		var iden1 = false;
		var iden2 = false;
		var iden3 = false;

		if(data.identity == 1) {
			iden1 = true;
		} else if(data.identity = 2) {
			iden2 = true;
		} else if(data.identity = 3) {
			iden3 = true;
		} else if(data.identity = 4) {
			iden1 = true;
			iden2 = true;
		} else if(data.identity = 5) {
			iden1 = true;
			iden3 = true;
		} else if(data.identity = 6) {
			iden2 = true;
			iden3 = true;
		} else if(data.identity = 7) {
			iden1 = true;
			iden2 = true;
			iden3 = true;
		}

		if(iden1) {
			$('#m_alter_worker_i1').attr('checked', 'checked');
		}
		if(iden2) {
			$('#m_alter_worker_i2').attr('checked', 'checked');
		}
		if(iden2) {
			$('#m_alter_worker_i3').attr('checked', 'checked');
		}

	}

	$("#form_alter_worker_sub").click(function() {
		var worker_id = $("#form_alter_worker input[name='worker_id']").val();
		var loginName = $("#form_alter_worker input[name='login_name']").val();
		var passwd = $("#form_alter_worker input[name='passwd']").val();
		var name = $("#form_alter_worker input[name='name']").val();
		var tel = $("#form_alter_worker input[name='tel']").val();
		var identity1 = $("#m_alter_worker_i1").val();
		var identity2 = $("#m_alter_worker_i2").val();
		var identity3 = $("#m_alter_worker_i3").val();

		if(loginName == undefined || loginName == '') {
			alert("用户名不能为空！");
			return;
		}

		if(passwd == undefine || passwd == '') {
			alert("密码不能为空");
			return;
		}

		if(name == undefined || name == '') {
			alert("姓名不能为空！");
			return;
		}

		if(identity1 == undefined && identity2 == undefined && identity3 == undefined) {
			alert("身份选择不能为空");
			return;
		}

		var iden = 0;
		if(identity1 != undefined && identity2 == undefined && identity3 == undefined) {
			iden = 1;
		} else if(identity1 == undefined && identity2 != undefined && identity3 == undefined) {
			iden = 2;
		} else if(identity1 == undefined && identity2 == undefined && identity3 != undefined) {
			iden = 3;
		} else if(identity1 != undefined && identity2 != undefined && identity3 == undefined) {
			iden = 4;
		} else if(identity1 != undefined && identity2 == undefined && identity3 != undefined) {
			iden = 5;
		} else if(identity1 == undefined && identity2 != undefined && identity3 != undefined) {
			iden = 6;
		} else if(identity1 != undefined && identity2 != undefined && identity3 != undefined) {
			iden = 7;
		} else {
			alert("身份选择有异常，请重新刷新页面！");
			return;
		}

		var data = {
			'worker_id': worker_id,
			'login_name': login_name,
			'passwd': passwd,
			'name': name,
			'tel': tel,
			'identity': iden
		}

		$("#m_alter_worker").hide();

		if(tip_debug) {
			alter_worker_res("debug_" + data);
		} else {
			alter_worker(data);
		}

	})

	function alter_worker(data) {
		$.ajax({
			'type': "post",
			'url': pre_url + "/adminOperation/all_ans",
			'async': false, //同步上传数据
			'data': data,
			dataType: "json",
			'success': alter_worker_res,
			'error': function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function alter_worker_res(data) {
		console.log("alter_worker_res:" + data);
	}

	$(".p_alter_worker_btn").click(function() {
		var worker_id = $(this).attr('id');
		var login_name = $(this).parent().parent().children('tr:first-of-type').html();

		var data = {
			'login_name': login_name
		}

		$('#form_alter_worker')[0].reset();
		$('#form_alter_worker input[name="login_name"]').attr('value', login_name);
		$('#m_alter_worker').slideDown();

		if(tip_debug) {
			var temp = {
				'worker_id': 12,
				'passwd': '123123',
				'name': 'gz',
				'tel': '15871757712',
				'identity': 1
			};
			worker_flush_res(temp);
		} else {
			worker_flush(data);
		}
	})

	/* 删除工作人员 */
	$("#form_del_worker_sub").click(function() {
		var login_name = $("#form_del_worker input[name='login_name']").val();

		if(login_name == undefined || login_name == '') {
			alert('请输入用户名！');
			return;
		}

		var data = {
			'login_name': login_name
		}

		if(tip_debug) {
			del_worker_res("debug_" + data);
		} else {
			del_worker(data);
		}
	})

	function del_worker(data) {
		$.ajax({
			'type': "post",
			'url': pre_url + "/adminOperation/all_ans",
			'async': false, //同步上传数据
			'data': data,
			dataType: "json",
			'success': del_worker_res,
			'error': function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function del_worker_res(data) {
		console.log('del_worker_res:' + data);
	}

	/* 管理人员表格 */
	$("#index_list_admin").click(function() {
		console.log("tog")
		if(tip_debug) {
			var temp = [{
				'login_name': 'admin1',
				'name': 'adminName1',
				'passwd': '12123',
				'admin_id': '12'
			}, {
				'login_name': 'admin2',
				'name': 'adminName1',
				'passwd': '12123',
				'admin_id': '12'
			}]
			get_list_admin_res(temp);
		} else {
			get_list_admin();
		}

		$("#p_list_admin").slideToggle();
		$("#p_list_worker").hide();
		$("#p_list_review").hide();
	})

	function get_list_admin() {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			success: get_list_admin_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function get_list_admin_res(datas) {
		console.log('get_list_admin_res:' + datas);
		if(datas == undefined || datas == '') {
			return;
		}
		$("#p_list_admin tbody").empty();
		$.each(datas, function(index, data) { //遍历json数组
			var atr = $("<tr></tr>");
			var loginName = $('<td>' + data.login_name + '</td>');
			var name = $('<td>' + data.name + '</td>');
			var passwd = $('<td>' + data.passwd + '</td>');
			var admin_id = $('<td><button class="btn btn-warning" id=' + data.admin_id + ' >修改</button></td>');
			atr.append(loginName);
			atr.append(name);
			atr.append(passwd);
			atr.append(admin_id);
			$("#p_list_admin tbody").append(atr);
		})
	}

	/* 添加新的管理员账号 */

	$('#form_add_admin_sub').click(function() {
		var login_name = $('#form_add_admin_sub input[name="login_name"]').val();
		var passwd = $('#form_add_admin_sub input[name="passwd"]').val();
		var passwd2 = $('#form_add_admin_sub input[name="passwd2"]').val();
		var name = $('#form_add_admin_sub input[name="name"]').val();

		if(login_name == undefined || login_name == '') {
			alert('用户名不能为空');
			return;
		}
		if(passwd == undefined || passwd2 == undefined || passwd != passwd2 || passwd == '') {
			alert('密码不能为空');
			return;
		}
		if(name == undefined || name == '') {
			alert('姓名不能为空');
			return;
		}

		var data = {
			'login_name': login_name,
			'passwd': passwd,
			'name': name
		}

		$("#m_add_admin").hide();

		if(tip_debug) {
			add_amdin_res("debug_" + data);
		} else {
			add_admin(data);
		}

	})

	function add_admin(data) {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			'data': data,
			success: add_admin_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function add_admin_res(data) {
		console.log("add_admin_res:" + data);
	}

	/* 删除管理员 */
	$('#form_del_admin_sub').click(function() {
		var login_name = $('#form_del_admin input[name="login_name"]').val();

		if(login_name == undefined || login_name == '') {
			alert('用户名不能为空');
			return;
		}

		var data = {
			'account': login_name
		}
		if(tip_debug) {
			del_admin_res("debug_" + data);
		} else {
			del_admin(data);
		}

		$('#m_del_admin').hide();

	})

	function del_admin(data) {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			'data': data,
			success: del_admin_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function del_admin_res(data) {
		console.log('del_admin_res' + data);
	}

	$('.p_del_admin_btn').click(function() {
		var account = $(this).parent().parent().children('tr:first-of-type').html();

		var data = {
			'account': account
		}
		if(tip_debug) {
			del_admin_res("debug_" + data);
		} else {
			del_admin(data);
		}
	})

	/* 身份审核表格 */
	$("#index_list_review").click(function() {
		if(tip_debug) {
			var temp = [{
				'login_name': 'worker1',
				'current': '找书员',
				'want': '登记员',
				'review_id': 12,
				'description': '工作交接'
			}];
			get_list_review_res(temp);
		} else {
			get_list_review();
		}
		$("#p_list_review").slideToggle();
		$("#p_list_admin").hide();
		$("#p_list_worker").hide();

	})

	/* 获取所有身份审核信息 */
	function get_list_review() {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			success: get_list_review_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function get_list_review_res(datas) {
		console.log('get_list_review_res: ' + datas);
		if(datas == undefined || datas == '') {
			return;
		}
		$("#p_list_admin tbody").empty();
		$.each(datas, function(index, data) { //遍历json数组
			$("#p_list_review tbody").empty();
			var atr = $("<tr></tr>");
			var loginName = $('<td>' + data.login_name + '</td>');
			var current = $('<td>' + data.current + '</td>');
			var want = $('<td>' + data.want + '</td>');
			var desc = $('<td>' + data.description + '</td>');
			var review_id = $('<td><button class="btn btn-warning p_alter_review_btn " id=' + data.review_id + ' >准许</button><button class="btn btn-warning p_alter_review_btn2 " id=' + data.review_id + ' >驳回</button></td>');
			atr.append(loginName);
			atr.append(current);
			atr.append(want);
			atr.append(desc);
			atr.append(review_id);
			$("#p_list_review tbody").append(atr);
		})
	}

	/* 处理身份审核信息 */

	$('#form_review_find').click(function() {
		var login_name = $('#form_alter_review input[name="login_name"]').val();
		if(login_name == undefined || login_name == '') {
			alert('用户名不能为空');
			return;
		}

		var data = {
			'login_name': login_name
		}
		if(tip_debug) {
			find_review_res();
		} else {
			find_review(data);
		}

	})

	function find_review(data) {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			'data': data,
			success: find_review_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function find_review_res(data) {
		console.log('find_review_res:' + data);

	}

	$('#form_alter_review_pass').click(function() {
		var review_id = $('#form_alter_review input[name="review_id"]').val();
		var data = {
			'review_id': review_id,
			'type': 1
		}
		if(tip_debug) {
			alter_review_res("debug_" + data);
		} else {
			alter_review(data);
		}

	})

	$('#form_alter_review_reject').click(function() {
		var review_id = $('#form_alter_review input[name="review_id"]').val();
		var data = {
			'review_id': review_id,
			'type': 0
		}
		if(tip_debug) {
			alter_review_res("debug_" + data);
		} else {
			alter_review(data);
		}

	})

	function alter_review(data) {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			'data': data,
			success: alter_review_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function alter_review_res(data) {
		console.log('alter_review_res:' + data);
	}

	$('.p_alter_review_btn').click(function() {
		var review_id = $(this).attr('review_id');
		if(review_id == undefined || review_id == '') {
			alert('列表数据异常，请刷新后再试');
			return;
		}
		var data = {
			'review_id': review_id,
			'type': 1
		}
		if(tip_debug) {
			alter_review_res("debug_" + data);
		} else {
			alter_review(data);
		}
	})

	$('.p_alter_review_btn2').click(function() {
		var review_id = $(this).attr('review_id');
		if(review_id == undefined || review_id == '') {
			alert('列表数据异常，请刷新后再试');
			return;
		}
		var data = {
			'review_id': review_id,
			'type': 0
		}
		if(tip_debug) {
			alter_review_res("debug_" + data);
		} else {
			alter_review(data);
		}
	})

	/* 个人信息 */

	$('#see_info_btn').click(function() {
		console.log("account:" + account + "-name:" + name);
		$('#m_see_info_account').html(account);
		$('#m_see_info_name').html(name);
	})

	/* 修改个人信息 */

	$("#form_alter_info_sub").click(function() {
		var login_name = $('#form_alter_info input[name="login_name"]').val();
		var name = $('#form_alter_info input[name="name"]').val();

		if(login_name == undefined || login_name == '') {
			alert('用户名不能为空');
			return;
		}
		if(name == undefined || name == '') {
			alert('姓名不能为空');
			return;
		}

		var data = {
			'login_name': login_name,
			'name': name
		}

		if(tip_debug) {
			alter_info_res("debug_" + data)
		} else {
			alter_info(data)
		}

	})

	function alter_info(data) {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			'data': data,
			success: alter_info_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function alter_info_res(data) {
		console.log("alter_info_res:" + data);
	}

	/* 修改个人密码 */
	$('#form_alter_infopwd_sub').click(function() {

		var passwd_old = $('#form_alter_passwd input[name="passwd_old"]').val();
		var passwd_new = $('#form_alter_passwd input[name="passwd_new"]').val();

		var passwd_new2 = $('#form_alter_passwd input[name="passwd_new2"]').val();

		if(passwd_old == undefined || passwd_new == undefined || passwd_new2 == undefined || passwd_old == '' || passwd_new == '' || passwd_new2 == '') {
			alert("密码不能为空");
			return;
		}

		if(passwd_new != passwd_new2) {
			if(tip_debug) {
				console.log("error:" + passwd_new + "-passwd2:" + passwd_new2);
			}
			alert("两个输入的新密码不一致");
			return;
		}

		var data = {
			'account': account,
			'passwd_old': passwd_old,
			'passwd_new': passwd_new
		}
		if(tip_debug) {
			alter_infopwd_res("debug_" + data);
		} else {
			alter_infopwd(data);
		}

	})

	function alter_infopwd(data) {
		$.ajax({
			type: "post",
			url: pre_url + "/adminOperation/getWorkers",
			async: false, //异步上传数据
			dataType: "json",
			'data': data,
			success: alter_infopwd_res,
			error: function() { //没有得到返回的数据，网络中断
				alert("网络故障，请稍后重试");
			}
		});
	}

	function alter_infopwd_res(data) {
		console.log("alter_infopwd_res:" + data);
	}

})