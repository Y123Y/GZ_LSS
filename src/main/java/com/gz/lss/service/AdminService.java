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
	Boolean updatePasswd(String account, String oldPasswd, String newPasswd);
	
	/**
	 * 根据管理员ID返回用户信息
	 * @param admin_id	管理员ID
	 * @return	
	 */
	Tb_admin selectAdminById(Integer admin_id);
	
	/**
	 * 判断管理员是否存在
	 * @param account	管理员登录名
	 * @return	存在返回管理员信息, 不存在返回NULL
	 */
	Tb_admin selectAdminByAccount(String account);
	
	/**
	 * 更新管理员信息
	 * @param admin	管理员信息
	 * @return
	 */
	Boolean updateAdmin(Tb_admin admin);
}
