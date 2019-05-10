package com.gz.lss.pojo;

import java.io.Serializable;
import java.util.Date;

public class Tb_order implements Serializable {

	/**
	 * 订单
	 */
	private static final long serialVersionUID = -1155611474667025443L;
	//订单id
	private Integer order_id;
	//用户id
	private Integer user_id;
	//创建时间
	private Date create_time;
	//完成时间
	private Date finish_time;
	//地址id
	private Integer address_id;
	//派送时间
	private Date deliver_time;
	//备注
	private String remarks;
	//状态
	private Integer state;
	
	public Tb_order() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Tb_order [order_id=" + order_id + ", user_id=" + user_id + ", create_time=" + create_time
				+ ", finish_time=" + finish_time + ", address_id=" + address_id + ", deliver_time=" + deliver_time
				+ ", remarks=" + remarks + ", state=" + state + "]";
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public Date getDeliver_time() {
		return deliver_time;
	}

	public void setDeliver_time(Date deliver_time) {
		this.deliver_time = deliver_time;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
}
