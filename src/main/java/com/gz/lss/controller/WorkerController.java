package com.gz.lss.controller;

import com.gz.lss.common.LssConstants;
import com.gz.lss.common.ResultGenerator;
import com.gz.lss.common.ResultMsg;
import com.gz.lss.pojo.Tb_review;
import com.gz.lss.pojo.Tb_w_identity;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.service.WorkerService;
import com.gz.lss.util.security.PasswordHelper;
import com.gz.lss.util.security.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/worker")
public class WorkerController {
	@Autowired
	private WorkerService workerService;

	/**
	 * 处理登录请求
	 * @param username	登录名
	 * @param password	密码
	 */
	@RequestMapping("/login")
	public ResultMsg login(@RequestParam("username") String username,
						   @RequestParam("password") String password,
						   HttpServletResponse response) {
		// 调用业务逻辑组件判断用户是否可以登录
		Tb_worker worker = workerService.workerLogin(username, password);

		ResultMsg result;
		if(worker != null) {
			try {
				//生成token，并以用户名为键，将token放入redis中
				String token = TokenUtil.updateToken(worker);
				log.info(token);
				//将token放入cookie返回客户端
				TokenUtil.addCookie(response, token);
				result = ResultGenerator.genSuccessResultMsg();
			}catch (Exception e){
				log.error("生成Token出错");
				e.printStackTrace();
				result = ResultGenerator.genFailResultMsg("登陆失败");
			}
		}else {
			result = ResultGenerator.genFailResultMsg("用户名或密码错误");
		}

		return result;
	}

	@RequestMapping("/loginByToken")
	public ResultMsg loginByToken(){
		return ResultGenerator.genSuccessResultMsg();
	}

	/**
	 * 验证用户名是否合法
	 * @param login_name	用户名
	 */
	@RequestMapping("/checkWorkerId")
	@ResponseBody
	public ResultMsg checkWorkerId(@RequestParam("username") String login_name) {
		if(workerService.selectWorkerByLoginName(login_name) != null) {
			return ResultGenerator.genFailResultMsg("用户名已存在");
		}else {
			return ResultGenerator.genSuccessResultMsg();
		}
	}

	/**
	 * 用户注册
	 * @param worker	用户信息
	 */
	@RequestMapping("/register")
	public ResultMsg register(@ModelAttribute Tb_worker worker) {
		if(workerService.selectWorkerByLoginName(worker.getLogin_name()) != null) {
			return ResultGenerator.genFailResultMsg("注册失败，用户名已存在");
		}

		String password = worker.getPasswd();
		//对密码进行散列并将密码散列值和secret_key存储在数据库中
		String secret_key = PasswordHelper.getSecretKey(16);
		password = PasswordHelper.getPasswordDigest(password, secret_key);
		worker.setPasswd(password);
		worker.setSecret_key(secret_key);

		Integer worker_id = workerService.addWorker(worker);
		
		if(worker_id == null) {
			return ResultGenerator.genFailResultMsg("注册失败");
		}

		return ResultGenerator.genSuccessResultMsg();
	}
	
	/**
	 * 返回用户信息
	 */
	@RequestMapping("/workerInfo")
	public ResultMsg userInfo(HttpServletRequest request) {
		String worker_name = (String) request.getAttribute(LssConstants.TOKEN_PAYLOAD_KEY);

		Tb_worker worker = workerService.selectWorkerByLoginName(worker_name);
		
		worker.setPasswd(null);
		worker.setSecret_key(null);
		
		return ResultGenerator.genSuccessResultMsg(worker);
	}
	
	/**
	 * 更新用户信息
	 * @param worker 用户信息
	 */
	@RequestMapping("/updateWorkerInfo")
	public ResultMsg updateUserInfo(@ModelAttribute Tb_worker worker, HttpServletRequest request, HttpServletResponse response) {
		ResultMsg result;
		Integer worker_id = Integer.parseInt((String) request.getAttribute("worker_id"));

		worker.setWorker_id(worker_id);
		log.info(worker.toString());
		if(workerService.updateWorker(worker)) {
			try {
				String updateToken = TokenUtil.updateToken(worker.getLogin_name());
				TokenUtil.addCookie(response, updateToken);
				result = ResultGenerator.genSuccessResultMsg();
			} catch (Exception e) {
				e.printStackTrace();
				result = ResultGenerator.genFailResultMsg("用户信息更新出错");
			}
		}else {
			result = ResultGenerator.genFailResultMsg("用户信息更新失败");
		}
		
		return result;
	}
	
	/**
	 * 修改用户密码
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 */
	@RequestMapping("/changePasswd")
	public ResultMsg changePasswd(@RequestParam("oldPasswd") String oldPassword, @RequestParam("newPasswd") String newPassword, HttpServletRequest request) {
		ResultMsg result;
		String worker_name = (String) request.getAttribute(LssConstants.TOKEN_PAYLOAD_KEY);

		if(workerService.updatePasswd(worker_name, oldPassword, newPassword)) {
			result = ResultGenerator.genSuccessResultMsg();
		}else {
			result = ResultGenerator.genFailResultMsg("密码修改失败, 请稍后重试");
		}
		
		return result;
	}
	
	/**
	 *返回工作人员所有身份
	 */
	@RequestMapping("/getIdentities")
	public ResultMsg getIdentities() {
		List<Tb_w_identity> list = workerService.getIdentities();
		Map<Integer, String> map = new HashMap<>();
		list.forEach((w) -> map.put(w.getIdentity(), w.getName()));
		return ResultGenerator.genSuccessResultMsg(map);
	}
	
	/**
	 * 更改身份请求
	 * @param identity	想要的身份
	 * @param description	说明
	 */
	@RequestMapping("/changeIdentity")
	public ResultMsg changeIdentity(@RequestParam("identity") Integer identity, @RequestParam("description") String description, HttpServletRequest request) {
		ResultMsg result;
		String worker_name = (String) request.getAttribute(LssConstants.TOKEN_PAYLOAD_KEY);
		Tb_worker worker = workerService.selectWorkerByLoginName(worker_name);

        Tb_review review = workerService.selectReviewById(worker.getWorker_id());
        if(review != null) {
            return ResultGenerator.genFailResultMsg("已有处理中的一个身份请求，请不要重复提交");
        }

		if(workerService.changeIdentity(worker.getWorker_id(), worker.getIdentity(), identity, description)) {
			result = ResultGenerator.genSuccessResultMsg();
		}else {
			result = ResultGenerator.genFailResultMsg("请求提交失败");
		}

		return result;
	}

	/**
	 * 退出登录
	 * @param worker_name
	 * @return
	 */
	@RequestMapping("/logout")
	public ResultMsg logout(String worker_name) {
		TokenUtil.deleteToken(worker_name);
		return ResultGenerator.genSuccessResultMsg();
	}
}
