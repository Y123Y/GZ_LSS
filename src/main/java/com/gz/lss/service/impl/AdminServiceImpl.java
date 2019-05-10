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

	@Override
	public Boolean updatePasswd(String account,String oldPasswd, String newPasswd){
		try {
			Tb_admin admin=dao.selectByUserName(account);
			if(admin!=null&&admin.getPasswd().equals(oldPasswd)) {
				dao.updatePwd(account, newPasswd);
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
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

	@Override
	public Boolean updateAdmin(Tb_admin admin) {
		Boolean b=false;
		try {
			b=dao.update(admin);
		}catch(Exception e) {
			e.printStackTrace();
			b=false;
		}
		return b;
	}

}
