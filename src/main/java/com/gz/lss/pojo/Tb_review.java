package com.gz.lss.pojo;

import java.io.Serializable;

public class Tb_review implements Serializable {
	/**
	 * 工作人员身份审核
	 */
	private static final long serialVersionUID = -6989498506972629267L;
	//审核id
	private Integer review_id;
	//发出请求的工作人员
	private Integer worker_id;
	//当前身份信息
	private Integer current;
	//想要变成的身份
	private Integer want;
	//请求的说明
	private String descript;
	//身份审核的状态
	private Integer state;
	
	
	public Integer getReview_id() {
		return review_id;
	}
	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}
	public Integer getWorker_id() {
		return worker_id;
	}
	public void setWorker_id(Integer worker_id) {
		this.worker_id = worker_id;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public Integer getWant() {
		return want;
	}
	public void setWant(Integer want) {
		this.want = want;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Tb_review [review_id=" + review_id + ", worker_id=" + worker_id + ", current=" + current + ", want="
				+ want + ", descript=" + descript + ", state=" + state + "]";
	}
	
}
