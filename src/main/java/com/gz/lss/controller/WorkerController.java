package com.gz.lss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_review;
import com.gz.lss.pojo.Tb_state;
import com.gz.lss.pojo.Tb_w_identity;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.service.WorkerOperationService;
import com.gz.lss.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerController {
	@Autowired
	private WorkerService workerService;
	@Autowired
	private WorkerOperationService workerOperationService;
	
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
		Tb_worker worker = workerService.workerLogin(loginname, password);

		if(worker != null) {
			Tb_worker w = new Tb_worker();
			w.setWorker_id(worker.getWorker_id());
			w.setLogin_name(worker.getLogin_name());
			// 将用户ID保存到HttpSession当中
			session.setAttribute(LssConstants.WORKER_SESSION, w);
			mv.setViewName("redirect:/"+LssConstants.WORKERMAIN);
		}else {
			//登录失败
			mv.addObject("message", "登录失败，用户名或密码错误");
			mv.setViewName("forward:/"+LssConstants.WORKERLOGIN);
		}
		
		return mv;
	}
	
	/**
	 * 跳转到工作人员登录页
	 * @return
	 */
	@RequestMapping("/loginForm")
	public String workerLoginForm() {
		return LssConstants.WORKERLOGIN;
	}
	
	/**
	 * 跳转到工作人员主页
	 * @return
	 */
	@RequestMapping("/main")
	public String workerMain(HttpSession session, Model model) {
		Tb_worker w = (Tb_worker) session.getAttribute(LssConstants.WORKER_SESSION);
		Tb_worker worker = workerService.selectWorkerById(w.getWorker_id());
		
		List<Tb_w_identity> identities = workerService.getIdentities();
		List<Tb_state> states = workerOperationService.selectStatesByIdentity(worker.getIdentity());

		model.addAttribute("states", states);
		model.addAttribute("identities", identities);
		model.addAttribute("worker", worker);
		return LssConstants.WORKERMAIN;
	}
	
	/**
	 * 验证用户名是否合法
	 * @param login_name	用户名
	 * @return
	 */
	@RequestMapping("/checkWorkerId")
	@ResponseBody
	public String checkWorkerId(String login_name) {
		if(workerService.selectWorkerByLoginName(login_name) != null) {
			return "用户名已存在";
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
		List<Tb_w_identity> list = workerService.getIdentities();
		model.addAttribute("tb_worker", new Tb_worker());
		model.addAttribute("identities", list);
		return LssConstants.WORKERREGISTER;
	}
	
	/**
	 * 用户注册
	 * @param worker	用户信息
	 * @param model	
	 * @return
	 */
	@RequestMapping("/register")
	public String register(@Valid @ModelAttribute Tb_worker worker, Errors error, Model model) {
		if(workerService.selectWorkerByLoginName(worker.getLogin_name()) != null) {
			model.addAttribute("message", "注册失败，用户名已存在");
			return "forward:/"+LssConstants.WORKERREGISTER;
		}
		
		if(error.hasErrors()) {
			List<Tb_w_identity> list = workerService.getIdentities();
			model.addAttribute("identities", list);
			return LssConstants.WORKERREGISTER;
		}
		
		Integer wantIdentity = worker.getIdentity();
		worker.setIdentity(8);
		Integer worker_id = workerService.addWorker(worker);
		
		if(worker_id == null) {
			model.addAttribute("message", "注册失败");
			List<Tb_w_identity> list = workerService.getIdentities();
			model.addAttribute("identities", list);
			return "forward:/"+LssConstants.WORKERREGISTER;
		}

		//身份请求
		workerService.changeIdentity(worker_id, worker.getIdentity(), wantIdentity, null);
		worker.setIdentity(8);
		
		model.addAttribute("message", "注册成功");
		return "forward:/"+LssConstants.WORKERLOGIN;
	}
	
	/**
	 * 返回用户信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/workerInfo")
	@ResponseBody
	public String userInfo(HttpSession session) {
		Tb_worker w = (Tb_worker) session.getAttribute(LssConstants.WORKER_SESSION);
		
		Tb_worker worker = workerService.selectWorkerById(w.getWorker_id());
		
		worker.setPasswd(null);
		
		return JSON.toJSONString(worker);
	}
	
	/**
	 * 更新用户信息
	 * @param worker
	 * @return
	 */
	@RequestMapping("/updateWorkerInfo")
	@ResponseBody
	public String updateUserInfo(@ModelAttribute Tb_worker worker, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		Tb_worker currentWorker = (Tb_worker) session.getAttribute(LssConstants.WORKER_SESSION);
		worker.setWorker_id(currentWorker.getWorker_id());
		System.out.println(worker);
		if(workerService.updateWorker(worker)) {
			Tb_worker w = new Tb_worker();
			w.setWorker_id(worker.getWorker_id());
			w.setLogin_name(worker.getLogin_name());
			session.setAttribute(LssConstants.WORKER_SESSION, w);
			result.put("message", "用户信息更新成功");
		}else {
			result.put("message", "用户信息更新失败");
		}
		
		return JSON.toJSONString(result);
	}
	
	/**
	 * 修改用户密码
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 * @return
	 */
	@RequestMapping("/changePasswd")
	@ResponseBody
	public String changePasswd(@RequestParam("oldPasswd") String oldPassword, @RequestParam("newPasswd") String newPassword, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		Tb_worker w = (Tb_worker) session.getAttribute(LssConstants.WORKER_SESSION);
		
		if(workerService.updatePasswd(w.getWorker_id(), oldPassword, newPassword)) {
			result.put("message", "密码修改成功");
		}else {
			result.put("message", "密码修改失败");			
		}
		
		return JSON.toJSONString(result);
	}
	
	/**
	 *返回工作人员所有身份(除未审核人员)
	 * @return
	 */
	@RequestMapping("/getIdentities")
	@ResponseBody
	public String getIdentities() {
		List<Tb_w_identity> list = workerService.getIdentities();
		return JSON.toJSONString(list);
	}
	
	/**
	 * 检查是否有未完成身份请求
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkUnfinished")
	@ResponseBody
	public String checkUnfinishedIdentityRequest(HttpSession session) {
		Tb_worker w = (Tb_worker) session.getAttribute(LssConstants.WORKER_SESSION);
		Tb_review review = workerService.selectReviewById(w.getWorker_id());
		if(review != null) {
			return "true";
		}
		return "false";
	}
	
	/**
	 * 更改身份请求
	 * @param identity	想要的身份
	 * @param descript	说明
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeIdentity")
	@ResponseBody
	public String changeIdentity(Integer identity, String descript, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		Tb_worker w = (Tb_worker) session.getAttribute(LssConstants.WORKER_SESSION);
		
		Tb_worker worker = workerService.selectWorkerById(w.getWorker_id());
		
		if(workerService.changeIdentity(w.getWorker_id(), worker.getIdentity(), identity, descript)) {
			result.put("message", "请求提交成功");
		}else {
			result.put("message", "请求提交失败");			
		}
			
		return JSON.toJSONString(result);
	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(LssConstants.WORKER_SESSION);
		
		return "redirect:/"+LssConstants.WORKERLOGIN;
	}
}
