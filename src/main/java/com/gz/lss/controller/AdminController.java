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

import java.util.HashMap;
import java.util.Map;

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
			mv.setViewName("redirect:main");
			mv.addObject("name", currentadmin.getName());
		}else {
			//登录失败
			mv.addObject("msg", "用户名或密码错误！");
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
		Tb_admin currentAdmin = (Tb_admin) session.getAttribute(LssConstants.ADMIN_SESSION);
		if (currentAdmin.getAdmin_id() == null){
			return ResultGenerator.genUnAuthorityResultMsg();
		}
		Map<String, String> res = new HashMap<>();
		if((! "".equals(name.trim()))){
			if (adminService.upadteAdminName(currentAdmin.getAdmin_id(), name)){
				res.put("update_name", "true");
				res.put("update_name_msg", "用户名修改成功");
			}else{
				res.put("update_name", "false");
				res.put("update_name_msg", "用户名修改失败");
			}
		}
		if((! "".equals(oldPassword.trim())) && (! "".equals(newPassword.trim()))){
			if (adminService.updatePasswd(currentAdmin.getAdmin_id(), oldPassword, newPassword)){
				res.put("update_pwd", "true");
				res.put("update_pwd_msg", "密码修改成功");
			}else {
				res.put("update_pwd", "false");
				res.put("update_pwd_msg", "密码修改失败");
			}
		}
		currentAdmin = adminService.selectAdminById(currentAdmin.getAdmin_id());
		session.setAttribute(LssConstants.ADMIN_SESSION, currentAdmin);
		return ResultGenerator.genSuccessResultMsg(res);
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
