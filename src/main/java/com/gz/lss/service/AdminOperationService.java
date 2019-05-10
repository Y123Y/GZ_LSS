package com.gz.lss.service;

import java.util.List;

import com.gz.lss.pojo.Tb_admin;
import com.gz.lss.pojo.Tb_review;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.util.tag.PageModel;

public interface AdminOperationService {
	/**
	 * 根据状态获取用户身份请求
	 * @param state	状态(12 审核中, 13 通过, 14 未通过)
	 * @param pageModel 分页
	 * @return
	 * @throws Exception 
	 */
	List<Tb_review> getAllIdentityRequest(Integer state, PageModel pageModel);
	
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
	 * @param identity
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
