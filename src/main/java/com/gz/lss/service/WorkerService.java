package com.gz.lss.service;

import java.util.List;

import com.gz.lss.pojo.Tb_review;
import com.gz.lss.pojo.Tb_w_identity;
import com.gz.lss.pojo.Tb_worker;

public interface WorkerService {
	/**
	 * 检查用户是否可以登录
	 * @param loginname	登录名
	 * @param password	密码
	 * @return	可以登录就返回用户信息，否则返回NULL
	 */
	Tb_worker workerLogin(String loginname, String password);
	
	/**
	 * 判断用户是否存在
	 * @param worker_id	工作人员ID
	 * @return	存在返回用户信息, 不存在返回NULL
	 */
	Tb_worker selectWorkerById(Integer worker_id);
	
	/**
	 * 判断用户是否存在
	 * @param loginname	用户名
	 * @return	存在返回用户信息, 不存在返回NULL
	 */
	Tb_worker selectWorkerByLoginName(String loginname);
	
	/**
	 * 添加一个用户
	 * @param worker	用户信息
	 * @return 成功返回worker_id
	 */
	Integer addWorker(Tb_worker worker);
	
	/**
	 * 更新用户信息
	 * @param worker	用户信息
	 * @return
	 */
	Boolean updateWorker(Tb_worker worker);
	
	/**
	 * 更新用户密码
	 * @param oldPasswd	旧密码
	 * @param newPasswd	新密码
	 * @return
	 */
	Boolean updatePasswd(String worker_name, String oldPasswd, String newPasswd);

	/**
	 * 更改身份请求
	 * @param worker_id 工作人员ID
	 * @param currentIdentity 当前身份
	 * @param wantIdentity 请求身份
	 * @param descript 说明
	 * @return 成功OR失败
	 */
	Boolean changeIdentity(Integer worker_id, Integer currentIdentity, Integer wantIdentity, String descript);
	
	/**
	 * 返回工作人员所有身份(除未审核人员)
	 * @return
	 */
	List<Tb_w_identity> getIdentities();
	
	/**
	 * 根据员工ID查找审核中的身份请求
	 * @param worker_id 工作人员ID
	 * @return
	 */
	Tb_review selectReviewById(Integer worker_id);
}
