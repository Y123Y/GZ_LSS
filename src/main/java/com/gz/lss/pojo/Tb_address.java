package com.gz.lss.pojo;

import java.io.Serializable;

public class Tb_address implements Serializable {

	/**
	 * 收货地址表
	 */
	private static final long serialVersionUID = -8163078641456885632L;
	//地址id
	private Integer address_id;
	//用户id
	private Integer user_id;
	//地址内容
	private String content;
	//收件人
	private String name;
	//收件人电话
	private String tel;
	
	public Tb_address() {
		// TODO Auto-generated constructor stub
	}

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Tb_address [address_id=" + address_id + ", user_id=" + user_id + ", content=" + content + ", name="
				+ name + ", tel=" + tel + "]";
	}

	
}
