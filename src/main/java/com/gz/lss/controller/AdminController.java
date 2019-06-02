package com.gz.lss.controller;

import javax.servlet.http.HttpSession;

import com.gz.lss.common.ResultGenerator;
import com.gz.lss.common.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_admin;
import com.gz.lss.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	/**
	 * 处理登录请求
	 * @param loginname	登录名
	 * @param password	密码
	 * @return
	 */
	@PostMapping("/login")
	public ModelAndView login(@RequestParam("account") String loginname,
			 @RequestParam("password") String password,
			 HttpSession session, ModelAndView mv) {
		// 调用业务逻辑组件判断用户是否可以登录
		Tb_admin currentadmin = adminService.adminLogin(loginname, password);
		if(currentadmin != null) {
			// 将用户信息保存到HttpSession当中
			Tb_admin admin = new Tb_admin();
			admin.setAdmin_id(currentadmin.getAdmin_id());
			admin.setAccount(currentadmin.getAccount());
			session.setAttribute(LssConstants.ADMIN_SESSION, admin);
			mv.setViewName(LssConstants.ADMINMAIN);
			mv.addObject("name", currentadmin.getName());
		}else {
			//登录失败
			mv.addObject("message", "登录失败，用户名或密码错误");
			mv.setViewName(LssConstants.ADMINLOGIN);
		}
		
		return mv;
	}
	
	/**
	 * 跳转到管理员登录页
	 * @return
	 */
	@GetMapping("/login")
	public String adminLoginForm() {
		return LssConstants.ADMINLOGIN;
	}

	/**
	 * 跳转到管理员主页
	 * @return
	 */
	@RequestMapping("/main")
	public String adminMain() {
		System.out.println("test main page");
		return LssConstants.ADMINMAIN;

	}
	
	/**
	 * 验证用户名是否合法
	 * @param account	用户名
	 * @return 用户名存在返回false
	 */
	@RequestMapping("/checkAdminAccount")
	@ResponseBody
	public String checkWorkerId(String account) {
		if(adminService.selectAdminByAccount(account) != null) {
			return "false";
		}else {
			return "true";
		}
	}
	
	/**
	 * 返回管理员信息
	 * @return
	 */
	@RequestMapping("/adminInfo")
	@ResponseBody
	public String userInfo(HttpSession session) {
		Tb_admin currentAdmin = (Tb_admin) session.getAttribute(LssConstants.ADMIN_SESSION);
		
		Tb_admin admin = adminService.selectAdminById(currentAdmin.getAdmin_id());
		admin.setPasswd(null);
		
		return JSON.toJSONString(admin);
	}

	/**
	 * 更新管理员信息
	 * @param name
	 * @param oldPassword
	 * @param newPassword
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateAdminInfo")
	@ResponseBody
	public ResultMsg updateAdminInfo(@RequestParam("name") String name,
									 @RequestParam("oldPassword") String oldPassword,
									 @RequestParam("newPassword") String newPassword,
									 HttpSession session) {
		System.out.println("test update info");
		Tb_admin currentAdmin = (Tb_admin) session.getAttribute(LssConstants.ADMIN_SESSION);
		if (currentAdmin.getAdmin_id() == null){
			return ResultGenerator.genFailResultMsg("登录的id或账号为空");
		}
		boolean res = true;
		if((! "".equals(name.trim()))){
			res &= adminService.upadteAdminName(currentAdmin.getAdmin_id(), name);
		}
		if((! "".equals(oldPassword.trim())) && (! "".equals(newPassword.trim()))){
			res &= adminService.updatePasswd(currentAdmin.getAdmin_id(), oldPassword, newPassword);
		}
		currentAdmin = adminService.selectAdminById(currentAdmin.getAdmin_id());
		session.setAttribute(LssConstants.ADMIN_SESSION, currentAdmin);
		if (res){
			return ResultGenerator.genSuccessResultMsg();
		}else {
			return ResultGenerator.genFailResultMsg("修改失败");
		}
	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(LssConstants.ADMIN_SESSION);
		return LssConstants.ADMINLOGIN;
	}
}
