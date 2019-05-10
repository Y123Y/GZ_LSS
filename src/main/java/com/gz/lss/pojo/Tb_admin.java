package com.gz.lss.pojo;

import java.io.Serializable;


public class Tb_admin implements Serializable {

	/**
	 * 系统管理员
	 */
	private static final long serialVersionUID = -265712772118154778L;
	//id
	private Integer admin_id;
	//账号
	private String account;
	//密码
	private String passwd;
	//名字
	private String name;
	
	public Tb_admin() {
		// TODO Auto-generated constructor stub
	}

	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Tb_admin [admin_id=" + admin_id + ", account=" + account + ", passwd=" + passwd + ", name=" + name
				+ "]";
	}
	
}
