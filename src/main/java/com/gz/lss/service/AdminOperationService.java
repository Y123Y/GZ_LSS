package com.gz.lss.service;

import java.util.List;
import java.util.Map;

import com.gz.lss.entity.WorkerExamine;
import com.gz.lss.pojo.Tb_worker;

public interface AdminOperationService {
	/**
	 * 重置工作人员的密码
	 * @return
	 */
	Boolean ressetPasswordOfWorker(Integer worker_id);

	/**
	 * 获取状态标识和用户身份标识的键值对信息
	 * @return
	 */
	Map<String, Object> getStringAndCode();

	/**
	 * 根据审核信息的状态获取所有的审核信息
	 * @return
	 */
	List<WorkerExamine> getExamineOfNeed();

	/**
	 * 处理身份审核
	 * @param review_id
	 * @param suggestion
	 * @return
	 */
	Boolean handleExamine(Integer review_id, Boolean suggestion);
	
	/**
	 * 获取所有工作人员信息
	 * @return
	 */
	List<Tb_worker> selectWorker();
	
	/**
	 * 删除工作人员
	 * @param worker_id
	 * @return
	 */
	Boolean deleteWorker(Integer worker_id);

}
