package com.gz.lss.service;

import com.gz.lss.pojo.Tb_admin;

public interface AdminService {
	/**
	 * 检查管理员是否可以登录
	 * @param loginname	登录名
	 * @param password	密码
	 * @return	可以登录就返回用户信息，否则返回NULL
	 */
	Tb_admin adminLogin(String loginname, String password);
	
	/**
	 * 更新管理员密码
	 * @param oldPasswd	旧密码
	 * @param newPasswd	新密码
	 * @return
	 */
	Boolean updatePasswd(Integer admin_id, String oldPasswd, String newPasswd);
	
	/**
	 * 根据管理员ID返回用户信息
	 * @param admin_id	管理员ID
	 * @return	
	 */
	Tb_admin selectAdminById(Integer admin_id);

	/**
	 * 修改管理员姓名
	 * @param admin_id
	 * @param name
	 * @return
	 */
	Boolean upadteAdminName(Integer admin_id, String name);
}
