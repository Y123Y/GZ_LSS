package com.gz.lss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_admin;
import com.gz.lss.pojo.Tb_review;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.service.AdminOperationService;
import com.gz.lss.service.WorkerService;
import com.gz.lss.util.tag.PageModel;

@Controller
@RequestMapping("/adminOperation")
public class AdminOperationController {
	@Autowired
	private AdminOperationService adminOperationService;
	@Autowired
	private WorkerService workerService;
	
	/**
	 * 根据状态获取用户身份请求
	 * @param state	状态(12 审核中, 13 通过, 14 未通过)
	 * @return
	 */
	@RequestMapping("/getAllIdentityRequest")
	@ResponseBody
	public String getAllIdentityRequest(Integer state, Integer pageIndex) {
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Tb_review> requests = adminOperationService.getAllIdentityRequest(state, pageModel);
		return JSON.toJSONString(requests);
	}
	
	/**
	 * 获取所有工作人员
	 * @return
	 */
	@RequestMapping("/getWorkers")
	@ResponseBody
	public String getWorkers() {
		List<Tb_worker> workers = adminOperationService.selectWorker();
		return JSON.toJSONString(workers);
	}
	
	/**
	 * 增加工作人员
	 * @param worker 工作人员信息
	 * @return
	 */
	@RequestMapping("/addWorker")
	@ResponseBody
	public String addWorker(Tb_worker worker) {
		Map<String, Object> result = new HashMap<>();
		
		if(workerService.selectWorkerByLoginName(worker.getLogin_name()) != null) {
			result.put("type", "0");
			return JSON.toJSONString(result);
		}
		
		Integer worker_id = workerService.addWorker(worker);
		Tb_worker w = workerService.selectWorkerById(worker_id);
		if(worker_id != null) {
			result.put("type", "1");
			result.put("worker", w);
		}else {
			result.put("type", "0");
		}
		return JSON.toJSONString(result);
	}
	
	/**
	 * 修改工作人员信息
	 * @param worker 工作人员信息
	 * @return
	 */
	@RequestMapping("/modifyWorker")
	@ResponseBody
	public String modifyWorker(Tb_worker worker) {
		if(worker != null && workerService.updateWorker(worker)) {
			return "true";
		}else {
			return "false";
		}
	}
	
	/**
	 * 删除工作人员
	 * @param worker_id 工作人员ID
	 * @return
	 */
	@RequestMapping("/deleteWorker")
	@ResponseBody
	public String deleteWorker(Integer worker_id) {
		if(worker_id != null && adminOperationService.deleteWorker(worker_id)) {
			return "true";
		}else {
			return "false";
		}
	}
	
	/**
	 * 通过用户身份请求
	 * @param review_id 请求ID
	 * @return
	 */
	@RequestMapping("/pass")
	@ResponseBody
	public String passRequest(Integer review_id) {
		Map<String, Object> result = new HashMap<>();
		if(adminOperationService.passIdentityRequest(review_id)) {
			result.put("message", "处理完成");
		}else {
			result.put("message", "处理失败");
		}
		
		return JSON.toJSONString(result);
	}
	
	/**
	 * 驳回用户身份请求
	 * @param review_id 请求ID
	 * @return
	 */
	@RequestMapping("/reject")
	@ResponseBody
	public String rejectRequest(Integer review_id) {
		Map<String, Object> result = new HashMap<>();
		if(adminOperationService.rejectIdentityRequest(review_id)) {
			result.put("message", "处理完成");
		}else {
			result.put("message", "处理失败");
		}
		
		return JSON.toJSONString(result);
	}
	
	/**
	 * 添加管理员
	 * @param admin 管理员信息
	 * @return
	 */
	@RequestMapping("/addAdmin")
	@ResponseBody
	public String addAdmin(Tb_admin admin) {
		if(admin != null && adminOperationService.addAdmin(admin)) {
			return "true";
		}else {
			return "false";
		}
	}
	
	/**
	 * 删除管理员
	 * @param admin_id 管理员ID
	 * @return
	 */
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public String deleteAdmin(Integer admin_id) {
		if(admin_id != null && adminOperationService.deleteAdmin(admin_id)) {
			return "true";
		}else {
			return "false";
		}
	}
}
