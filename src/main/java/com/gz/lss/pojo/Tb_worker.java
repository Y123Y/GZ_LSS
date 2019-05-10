package com.gz.lss.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class Tb_worker implements Serializable{
	/**
	 * 工作人员
	 */
	private static final long serialVersionUID = -2795212033954782030L;
	//ID
	private Integer worker_id;
	//登录名
	@NotBlank(message="登录名不能为空")
	private String login_name;
	//登录密码
	@NotBlank(message="密码不能为空")
	private String passwd;
	//工作人员名称
	@NotBlank(message="用户名不能为空")
	private String name;
	//工作人员身份
	private Integer identity;
	//工作人员电话
	private String tel;

	public Tb_worker() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public Integer getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(Integer worker_id) {
		this.worker_id = worker_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
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

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Tb_worker [worker_id=" + worker_id + ", login_name=" + login_name + ", passwd=" + passwd + ", name="
				+ name + ", identity=" + identity + ", tel=" + tel + "]";
	}

}
