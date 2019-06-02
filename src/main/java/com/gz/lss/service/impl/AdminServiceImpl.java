package com.gz.lss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.dao.AdminDao;
import com.gz.lss.pojo.Tb_admin;
import com.gz.lss.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao dao;
	
	@Override
	public Tb_admin adminLogin(String loginname, String password){
		Tb_admin admin=null;
		try {
			admin=dao.selectByUserName(loginname);
			if(admin==null||!admin.getPasswd().equals(password)) {
				admin=null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			admin=null;
		}
		return admin;
	}

	/**
	 * 修改管理员id
	 * @param admin_id
	 * @param oldPasswd	旧密码
	 * @param newPasswd	新密码
	 * @return
	 */
	@Override
	public Boolean updatePasswd(Integer admin_id,String oldPasswd, String newPasswd){
		Boolean res;
		try {
			if(dao.updatePwd(admin_id, oldPasswd, newPasswd) <= 0){
				res = false;
			}else{
				res = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}



	@Override
	public Tb_admin selectAdminById(Integer admin_id) {
		Tb_admin admin=null;
		try {
			admin=dao.select(admin_id);
		}catch(Exception e) {
			e.printStackTrace();
			admin=null;
		}
		return admin;
	}

	@Override
	public Tb_admin selectAdminByAccount(String account) {
		Tb_admin admin=null;
		try{
			admin=dao.selectByAccount(account);
		}catch(Exception e) {
			e.printStackTrace();
			admin=null;
		}
		return admin;
	}

	/**
	 * 修改管理员姓名
	 *
	 * @param admin_id
	 * @param name
	 * @return
	 */
	@Override
	public Boolean upadteAdminName(Integer admin_id, String name) {
		Boolean res;
		try{
			dao.updateAdminName(admin_id, name);
			res = true;
		}catch (Exception e){
			e.printStackTrace();
			res = false;
		}
		return res;
	}
}
