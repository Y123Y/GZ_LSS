package com.gz.lss.pojo;

import java.io.Serializable;

public class Tb_state implements Serializable {
	/**
	 * 状态信息表
	 */
	private static final long serialVersionUID = -7135769895758764645L;
	//状态信息id
	private Integer state_id;
	//状态信息的名称
	private String name;
	//状态信息对应的表格
	private String tb_name;
	
	
	public Integer getState_id() {
		return state_id;
	}
	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTb_name() {
		return tb_name;
	}
	public void setTb_name(String tb_name) {
		this.tb_name = tb_name;
	}
	@Override
	public String toString() {
		return "Tb_state [state_id=" + state_id + ", name=" + name + ", tb_name=" + tb_name + "]";
	}
	
}
