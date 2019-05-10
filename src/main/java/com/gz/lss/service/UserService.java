package com.gz.lss.service;

import com.gz.lss.pojo.Tb_user;

public interface UserService {

	/**
	 * 检查用户是否可以登录
	 * @param loginname	登录名
	 * @param password	密码
	 * @return	可以登录就返回用户信息，否则返回NULL
	 */
	Tb_user userLogin(String loginname, String password);

	
	/**
	 * 根据用户ID返回用户信息
	 * @param user_id	用户ID
	 * @return	存在返回用户信息, 不存在返回NULL
	 */
	Tb_user selectUserById(Integer user_id);
	
	/**
	 * 判断用户是否存在
	 * @param loginname	用户登录名
	 * @return	存在返回用户信息, 不存在返回NULL
	 */
	Tb_user selectUserByLoginName(String loginname);
	
	/**
	 * 添加一个用户
	 * @param user	用户信息
	 * @return 成功返回true
	 */
	Boolean addUser(Tb_user user);
	
	/**
	 * 更新用户信息
	 * @param user	用户信息
	 * @return
	 */
	Boolean updateUser(Tb_user user);
	
	/**
	 * 更新用户密码
	 * @param oldPasswd	旧密码
	 * @param newPasswd	新密码
	 * @return
	 */
	Boolean updatePasswd(Integer user_id, String oldPasswd, String newPasswd);
	
}
