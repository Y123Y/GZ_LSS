package com.gz.lss.service;

import java.util.List;
import java.util.Map;

import com.gz.lss.entity.WorkerExamine;
import com.gz.lss.pojo.Tb_admin;
import com.gz.lss.pojo.Tb_review;
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
	 * 通过用户身份请求
	 * @param review_id 请求ID
	 * @return
	 * @throws Exception 
	 */
	Boolean passIdentityRequest(Integer review_id);
	
	/**
	 * 驳回用户身份请求
	 * @param review_id 请求ID
	 * @return
	 * @throws Exception 
	 */
	Boolean rejectIdentityRequest(Integer review_id);
	
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
	
	/**
	 * 添加管理员
	 * @param admin 管理员信息
	 * @return
	 */
	Boolean addAdmin(Tb_admin admin);
	
	/**
	 * 删除管理员
	 * @param admin_id 管理员ID
	 * @return
	 */
	Boolean deleteAdmin(Integer admin_id);
}
