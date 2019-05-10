package com.gz.lss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.dao.UserDao;
import com.gz.lss.pojo.Tb_user;
import com.gz.lss.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public Tb_user userLogin(String loginname, String password) {
		Tb_user user=null;
		try {
			user=dao.selectByLogin(loginname);
			if(user==null||!user.getPasswd().equals(password)) {
				user=null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			user=null;
		}
		return user;
	}

	@Override
	public Tb_user selectUserById(Integer user_id) {
		Tb_user user=null;
		try {
			user=dao.selectById(user_id);
		}catch(Exception e) {
			e.printStackTrace();
			user=null;
		}
		return user;
	}

	@Override
	public Tb_user selectUserByLoginName(String loginname) {
		Tb_user user=null;
		try {
			user=dao.selectByLogin(loginname);
		}catch(Exception e) {
			e.printStackTrace();
			user=null;
		}
		return user;
	}

	@Override
	public Boolean addUser(Tb_user user) {
		try {
			dao.insert(user);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean updateUser(Tb_user user) {
		try {
			Tb_user user2=dao.selectByLogin(user.getLogin_name());
			//判断是否修改了用户名，如果修改了，那是否已存在这样的用户名
			if(user2!=null&&!user2.getUser_id().equals(user.getUser_id())) {
				return false;
			}
			dao.updateNoPwd(user);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean updatePasswd(Integer user_id,String oldPasswd, String newPasswd) {
		try {
			Tb_user user=dao.selectById(user_id);
			if(user!=null&&user.getPasswd().equals(oldPasswd)) {
				dao.updatePwd(user_id, newPasswd);
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
