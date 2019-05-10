package com.gz.lss.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@RequestMapping("/login")
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
			mv.setViewName("redirect:/"+LssConstants.ADMINMAIN);
		}else {
			//登录失败
			mv.addObject("message", "登录失败，用户名或密码错误");
			mv.setViewName("forward:/"+LssConstants.ADMINLOGIN);
		}
		
		return mv;
	}
	
	/**
	 * 跳转到管理员登录页
	 * @return
	 */
	@RequestMapping("/loginForm")
	public String adminLoginForm() {
		return LssConstants.ADMINLOGIN;
	}
	
	/**
	 * 跳转到管理员主页
	 * @return
	 */
	@RequestMapping("/main")
	public String adminMain(HttpSession session, Model model) {
		Tb_admin currentAdmin = (Tb_admin) session.getAttribute(LssConstants.ADMIN_SESSION);
		Tb_admin admin = adminService.selectAdminById(currentAdmin.getAdmin_id());
		model.addAttribute("admin", admin);
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
	 * @param model
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
	 * @param admin
	 * @return
	 */
	@RequestMapping("/updateAdminInfo")
	@ResponseBody
	public String updateAdminInfo(@ModelAttribute Tb_admin admin, HttpSession session) {
		if(adminService.updateAdmin(admin)) {
			Tb_admin a = new Tb_admin();
			a.setAdmin_id(admin.getAdmin_id());
			a.setAccount(admin.getAccount());
			session.setAttribute(LssConstants.ADMIN_SESSION, a);
			return "用户信息更新成功";
		}else {
			return "用户信息更新失败";
		}
	}
	
	/**
	 * 修改管理员密码
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 * @return
	 */
	@RequestMapping("/changePasswd")
	public String changePasswd(@RequestParam("oldPasswd") String oldPassword, @RequestParam("newPasswd") String newPassword, HttpSession session, Model model) {
		Tb_admin currentAdmin = (Tb_admin) session.getAttribute(LssConstants.ADMIN_SESSION);
		
		if(adminService.updatePasswd(currentAdmin.getAccount(), oldPassword, newPassword)) {
			model.addAttribute("message", "密码修改成功");
		}else {
			model.addAttribute("message", "密码修改失败");			
		}
		
		return LssConstants.ADMINMAIN;
	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(LssConstants.ADMIN_SESSION);
		
		return "redirect:/"+LssConstants.ADMINLOGIN;
	}
}
