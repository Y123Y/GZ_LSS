package com.gz.lss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gz.lss.common.ResultGenerator;
import com.gz.lss.common.ResultMsg;
import com.gz.lss.entity.WorkerExamine;
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

@Controller
@RequestMapping("/adminOperation")
public class AdminOperationController {
	@Autowired
	private AdminOperationService adminOperationService;
	@Autowired
	private WorkerService workerService;

	/**
	 * 获取状态标识和身份标识所对应的对象信息
	 * @return
	 */
	@RequestMapping("/getStringAndCode")
	@ResponseBody
	public ResultMsg getStringAndCode() {
		Map<String, Object> map = adminOperationService.getStringAndCode();
		if(map == null || map.isEmpty()){
			return ResultGenerator.genFailResultMsg("数据为空");
		}else {
			return ResultGenerator.genSuccessResultMsg(map);
		}
	}

	/**
	 * 根据状态获取用户身份请求
	 * @return
	 */
	@RequestMapping("/getExamieOfNeed")
	@ResponseBody
	public ResultMsg getExamieOfNeed() {
		List<WorkerExamine> requests = adminOperationService.getExamineOfNeed();
		if (requests == null || requests.isEmpty())
			return ResultGenerator.genFailResultMsg("数据为空");
		else{
			return ResultGenerator.genSuccessResultMsg(requests);
		}
	}


	
	/**
	 * 获取所有工作人员
	 * @return
	 */
	@RequestMapping("/getWorkers")
	@ResponseBody
	public ResultMsg getWorkers() {
		List<Tb_worker> workers = adminOperationService.selectWorker();
		return ResultGenerator.genSuccessResultMsg(workers);
	}


	
	/**
	 * 删除工作人员
	 * @param worker_id 工作人员ID
	 * @return
	 */
	@RequestMapping("/deleteWorker")
	@ResponseBody
	public ResultMsg deleteWorker(Integer worker_id) {
		if(worker_id != null && adminOperationService.deleteWorker(worker_id)) {
			return ResultGenerator.genSuccessResultMsg();
		}else {
			return ResultGenerator.genFailResultMsg("删除失败");
		}
	}


	/**
	 * 重置工作人员密码
	 * @param worker_id
	 * @return
	 */
	@RequestMapping("/resetWorker")
	@ResponseBody
	public ResultMsg resetWorker(Integer worker_id) {
		if(adminOperationService.ressetPasswordOfWorker(worker_id)){
			return ResultGenerator.genSuccessResultMsg();
		}else {
			return ResultGenerator.genFailResultMsg("重置失败");
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
}
