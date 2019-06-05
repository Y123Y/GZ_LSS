package com.gz.lss.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tb_worker implements Serializable{
	/**
	 * 工作人员
	 */
	private static final long serialVersionUID = -2795212033954782030L;
	//ID
	private Integer worker_id;
	//登录名
	private String login_name;
	//登录密码
	private String passwd;
	//密钥
	private String secret_key;
	//工作人员名称
	private String name;
	//工作人员身份
	private Integer identity;
	//工作人员电话
	private String tel;
}
