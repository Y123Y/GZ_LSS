package com.gz.lss.pojo;

import java.io.Serializable;

public class Tb_w_identity implements Serializable {

	/**
	 * 工作人员身份信息表
	 */
	private static final long serialVersionUID = 3374582553636134485L;
	
	//工作人员身份标识
	private Integer identity;
	//身份标识对应的名称
	private String name;
	
	public Tb_w_identity() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Integer getIdentity() {
		return identity;
	}
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Tb_w_identity [identity=" + identity + ", name=" + name + "]";
	}
	
}
