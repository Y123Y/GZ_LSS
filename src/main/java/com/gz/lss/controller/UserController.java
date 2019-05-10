package com.gz.lss.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_address;
import com.gz.lss.pojo.Tb_user;
import com.gz.lss.service.AddressService;
import com.gz.lss.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	
	/**
	 * 处理登录请求
	 * @param loginname	登录名
	 * @param password	密码
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("loginname") String loginname,
			 @RequestParam("password") String password,
			 HttpSession session, ModelAndView mv) {
		// 调用业务逻辑组件判断用户是否可以登录
		Tb_user user = userService.userLogin(loginname, password);

		if(user != null) {
			// 将用户信息保存到HttpSession当中
			Tb_user u = new Tb_user();
			u.setUser_id(user.getUser_id());
			u.setLogin_name(user.getLogin_name());
			session.setAttribute(LssConstants.USER_SESSION, u);
			mv.setViewName("redirect:/"+LssConstants.USERMAIN);
		}else {
			//登录失败
			mv.addObject("loginname", loginname);
			mv.addObject("message", "登录失败，用户名或密码错误");
			mv.setViewName("forward:/"+LssConstants.USERLOGIN);
		}
		
		return mv;
	}
	
	/**
	 * 跳转到用户登录页
	 * @return
	 */
	@RequestMapping("/loginForm")
	public String userLoginForm() {
		return LssConstants.USERLOGIN;
	}
	
	/**
	 * 跳转到用户主页
	 * @return
	 */
	@RequestMapping("/main")
	public String userMain(HttpSession session, Model model) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		List<Tb_address> addresses = addressService.getAddresses(currentUser.getUser_id());
		model.addAttribute("addresses", addresses);
		return LssConstants.USERMAIN;
	}
	
	/**
	 * 验证用户名是否合法
	 * @param loginname	用户名
	 * @return
	 */
	@RequestMapping("/checkLoginName")
	@ResponseBody
	public String checkUserId(String loginname) {
		if(userService.selectUserByLoginName(loginname) != null) {
			return "false";
		}else {
			return "true";
		}
	}
	
	/**
	 * 返回注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/registerForm")
	public String registerForm(Model model) {
		model.addAttribute("tb_user", new Tb_user());
		return LssConstants.REGISTER;
	}
	
	/**
	 * 用户注册
	 * @param user	用户信息
	 * @param model	
	 * @return
	 */
	@RequestMapping("/register")
	public String register(@Valid @ModelAttribute Tb_user user, Errors error, Model model) {
		if(userService.selectUserByLoginName(user.getLogin_name()) != null) {
			model.addAttribute("message", "注册失败，用户名已存在");
			return LssConstants.REGISTER;
		}

		if(error.hasErrors()) {
			return LssConstants.REGISTER;
		}
		
		if(!userService.addUser(user)) {
			model.addAttribute("message", "注册失败");
			return LssConstants.REGISTER;
		}
		
		model.addAttribute("message", "注册成功");
		return "redirect:/"+LssConstants.USERLOGIN;
	}
	
	/**
	 * 返回用户信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/userInfo")
	public String userInfo(HttpSession session, Model model) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		
		Tb_user user = userService.selectUserById(currentUser.getUser_id());
		
		user.setPasswd(null);
		model.addAttribute("user", user);
		
		return LssConstants.USERINFO;
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateUserInfo")
	public String updateUserInfo(@ModelAttribute Tb_user user, HttpSession session, Model model) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		user.setUser_id(currentUser.getUser_id());
		if(userService.updateUser(user)) {
			Tb_user u = new Tb_user();
			u.setUser_id(user.getUser_id());
			u.setLogin_name(user.getLogin_name());
			session.setAttribute(LssConstants.USER_SESSION, u);
			model.addAttribute("message", "用户信息更新成功");
		}else {
			model.addAttribute("message", "用户信息更新失败");
		}
		
		return LssConstants.USERMAIN;
	}
	
	/**
	 * 修改用户密码
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 * @return
	 */
	@RequestMapping("/changePasswd")
	public String changePasswd(@RequestParam("oldPasswd") String oldPassword, @RequestParam("newPasswd") String newPassword, HttpSession session, Model model) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		
		if(userService.updatePasswd(currentUser.getUser_id(), oldPassword, newPassword)) {
			model.addAttribute("message", "密码修改成功");
		}else {
			model.addAttribute("message", "密码修改失败");			
		}
		
		return LssConstants.USERMAIN;
	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(LssConstants.USER_SESSION);

		return "redirect:/"+LssConstants.USERLOGIN;
	}
}
