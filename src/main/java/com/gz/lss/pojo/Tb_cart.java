package com.gz.lss.pojo;

import java.io.Serializable;

public class Tb_cart implements Serializable{
	/**
	 * 待借书单
	 */
	private static final long serialVersionUID = 1639106397406192667L;
	//书目id
	private Integer cart_id;
	//图书名称
	private String book_name;
	//图书索引号
	private String book_index;
	//出版年份
	private String publish_year;
	//图书数目
	private Integer number;
	//出版社
	private String book_press;
	//作者
	private String book_author;
	//用户
	private Integer user_id;
	
	public Tb_cart() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Integer getCart_id() {
		return cart_id;
	}
	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_index() {
		return book_index;
	}
	public void setBook_index(String book_index) {
		this.book_index = book_index;
	}
	public String getPublish_year() {
		return publish_year;
	}
	public void setPublish_year(String publish_year) {
		this.publish_year = publish_year;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getBook_press() {
		return book_press;
	}
	public void setBook_press(String book_press) {
		this.book_press = book_press;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Tb_cart [cart_id=" + cart_id + ", book_name=" + book_name + ", book_index=" + book_index + ", publish_year="
				+ publish_year + ", number=" + number + ", book_press=" + book_press + ", book_author=" + book_author
				+ ", user_id=" + user_id + "]";
	}
	
}
