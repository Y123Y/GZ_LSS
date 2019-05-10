package com.gz.lss.pojo;

import java.io.Serializable;

public class Tb_books implements Serializable {

	/**
	 * 订单详情
	 */
	private static final long serialVersionUID = -1155600802081291533L;
	//书目id
	private Integer books_id;
	//订单id
	private Integer order_id;
	//图书名称
	private String book_name;
	//图书索引号
	private String book_index;
	//订单详情的状态
	private Integer state;
	//备注
	private String remarks;
	//图书数目
	private Integer number;
	//出版社
	private String book_press;
	//作者
	private String book_author;
	
	
	public Tb_books () {
		// TODO Auto-generated constructor stub
	}


	public Integer getBooks_id() {
		return books_id;
	}


	public void setBooks_id(Integer books_id) {
		this.books_id = books_id;
	}


	public Integer getOrder_id() {
		return order_id;
	}


	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
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


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
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


	@Override
	public String toString() {
		return "Tb_books [books_id=" + books_id + ", order_id=" + order_id + ", book_name=" + book_name + ", book_index="
				+ book_index + ", state=" + state + ", remarks=" + remarks + ", number=" + number + ", book_press="
				+ book_press + ", book_author=" + book_author + "]";
	}

	
}
