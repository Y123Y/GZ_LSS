package com.gz.lss.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class Tb_user implements Serializable {

	/**
	 * 用户
	 */
	private static final long serialVersionUID = -8487160033433147927L;
	//ID
	private Integer user_id;
	//登录名
	@NotBlank(message="登录名不能为空")
	private String login_name;
	//借阅证号
	private String card_number;
	//名字
	@NotBlank(message="用户名不能为空")
	private String name;
	//密码
	@NotBlank(message="密码不能为空")
	private String passwd;
	//电话
	private String tel;
	//qq
	private String qq;
	//单位
	private String department;
	//办公电话
	private String office_tel;
	
	
	public Tb_user() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Tb_user(Integer user_id, String login_name, String card_number, String name, String passwd, String tel,
			String qq, String department, String office_tel) {
		super();
		this.user_id = user_id;
		this.login_name = login_name;
		this.card_number = card_number;
		this.name = name;
		this.passwd = passwd;
		this.tel = tel;
		this.qq = qq;
		this.department = department;
		this.office_tel = office_tel;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getOffice_tel() {
		return office_tel;
	}
	public void setOffice_tel(String office_tel) {
		this.office_tel = office_tel;
	}
	@Override
	public String toString() {
		return "Tb_user [user_id=" + user_id + ", login_name=" + login_name + ", card_number=" + card_number + ", name="
				+ name + ", passwd=" + passwd + ", tel=" + tel + ", qq=" + qq + ", department=" + department
				+ ", office_tel=" + office_tel + "]";
	}
}
