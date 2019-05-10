/**
 * 
 */
$(document).ready(function(){
	
     var select_addrId = null;
	//添加新地址框
	$(".pop_addressDiv").click(function(){		
		$(".bg_pop").show();
	});
	$("#close_pop").click(function(){
		$(".bg_pop").hide();
	});
	
	//修改地址框框
	$(".update_address").click(function(){
		var content = $(this).html();
		var address_id = $(this).attr("id");
		$(".bg_pop1").show(); 
		$("#content").val(content);
		select_addrId = address_id;
	});
	$("#close_pop1").click(function(){
		$(".bg_pop1").hide();
	});
	//删除地址添加地址修改地址全部发ajax请求
	$("#addaddr_btn").click(function(){
		var newcontent = $("#newcontent").val();
		var newname = $("#newname").val();
		var newtel = $("#newtel").val();
		/*alert(newcontent+newname+newtel);
		return;*/
		$.ajax({
			url: getRealPath()+"/address/addAddress",
			dataType: "json",
			data:{
				"content" : newcontent,
				"name" : newname,
				"tel" : newtel
			},
			type: "POST",
			success: function(data){
				swal("操作成功", "提示:"+data.message, "success");	
				$("#message").html(data.message);
				$("#xiaoxi").show();
				$("#xiaoxi").fadeOut(3000);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
			 swal("未知错误!", "提示:"+XMLHttpRequest.readyState +"***"+ XMLHttpRequest.status +"***"+ XMLHttpRequest.responseText, "error");		
			}
	});
		$(".bg_pop").hide();
	});
	//更新
	$("#updateaddr_btn").click(function(){
		
		var updatecontent = $("#content").val();
		var updatename = $("#name").val();
		var updatetel = $("#tel").val();
	/*	alert(updatecontent+updatename+updatetel);
		return;*/
		$.ajax({
			url: getRealPath()+"/address/updateAddress",
			dataType: "json",
			data:{
				"content" : updatecontent,
				"name" : updatename,
				"tel" : updatetel,
				"address_id" : select_addrId
			},
			type: "POST",
			success: function(data){
				swal("操作成功", "提示:"+data.message, "success");	
				$("#message").html(data.message);
				$("#xiaoxi").show();
				$("#xiaoxi").fadeOut(3000);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				 swal("未知错误!", "提示:"+XMLHttpRequest.readyState +"***"+ XMLHttpRequest.status +"***"+ XMLHttpRequest.responseText, "error");
			}
	});	
		$(".bg_pop1").hide();
	});
	//删除地址
	$("#deleteaddr_btn").click(function(){
		/*alert(select_addrId);
		return;*/
		$.ajax({
			url: getRealPath()+"/address/deleteAddress",
			dataType: "json",
			data:{
				"address_id" : select_addrId
			},
			type: "POST",
			success: function(data){
				swal("操作成功", "提示:"+data.message, "success");	
				$("#message").html(data.message);
				$("#xiaoxi").show();
				$("#xiaoxi").fadeOut(3000);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				 swal("未知错误!", "提示:"+XMLHttpRequest.readyState +"***"+ XMLHttpRequest.status +"***"+ XMLHttpRequest.responseText, "error");
			}
	});	
		$(".bg_pop1").hide(); 
	});
	
	$("#loginout").click(function(){
		 swal({
             title: "提示",
             text: "确定注销当前用户么？",
             type: "warning",
             showCancelButton: true,
             closeOnConfirm: false,
             confirmButtonText: "确定",
             confirmButtonColor: "#ec6c62"
         }, function() {          
           window.location = getRealPath()+"/user/logout";
         });
	});
});