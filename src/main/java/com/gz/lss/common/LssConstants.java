package com.gz.lss.common;

public class LssConstants {
	
	// 数据库表常量
	public static final String TBORDER= "tb_order";
	public static final String TBBOOKS = "tb_books";
	public static final String TBCART = "tb_cart";
	public static final String TBADDRESS = "tb_address";
	public static final String TBUSER = "tb_user";
	public static final String TBWORKER = "tb_worker";
	public static final String TBWIDENTITY = "tb_w_identity";
	public static final String TBREVIEW = "tb_review";
	public static final String TBSTATE = "tb_state";
	public static final String TBADMIN = "tb_admin";
	
	//用户页面
	public static final String USERLOGIN = "user/loginForm";		//登录页
	public static final String USERMAIN = "user/main";				//首页
	public static final String REGISTER = "user/registerForm";		//注册页
	public static final String USERINFO = "user/personsetting";		//信息页
	public static final String ORDERHISTORY = "user/history";		//订单历史页
	public static final String BOOKCART = "user/shopcart";			//待订书单页面
	public static final String BOOKORDERDETERMINE = "user/pay";		//订单确认页面
	
	//工作人员页面
	public static final String WORKERLOGIN = "worker/loginForm";	//登录页
	public static final String WORKERMAIN = "worker/main";			//首页
	public static final String WORKERREGISTER = "worker/registerForm";		//注册页
	public static final String WORKERINFO = "worker/info";			//信息页
	
	//管理员页面
	public static final String ADMINLOGIN = "admin/loginForm";			//登录页
	public static final String ADMINMAIN = "admin/main";			//首页
	
	// 用户的session对象
	public static final String USER_SESSION = "user_session";
	public static final String WORKER_SESSION = "worker_session";
	public static final String ADMIN_SESSION = "admin_session";
	
	// 默认每页20条数据
	public static int PAGE_DEFAULT_SIZE = 20;
	
	
	
}