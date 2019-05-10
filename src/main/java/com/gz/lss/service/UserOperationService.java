package com.gz.lss.service;

import java.util.List;
import java.util.Map;

import com.gz.lss.pojo.Tb_cart;
import com.gz.lss.util.tag.PageModel;

public interface UserOperationService {
	
	/**
	 * 查询图书
	 * @param keyword 关键字
	 * @param page 页数
	 * @return	包含图书信息的链表
	 */
	Map<String, Object> selectBooks(String keyword, Integer page);
	
	/**
	 * 添加图书至待订书单
	 * @param cart 图书信息
	 * @return
	 */
	Boolean addBooktoCart(Tb_cart cart);
	
	/**
	 * 从待订书单删除图书
	 * @param cart_id	待借书目ID
	 * @return
	 */
	Boolean deleteBookFromCart(Integer cart_id);
	
	/**
	 * 根据用户ID查询待借书单
	 * @param user_id 用户ID
	 * @return
	 */
	List<Tb_cart> selectCartByUser(Integer user_id, PageModel pageModel);
	
	/**
	 * 根据ID查找一条tb_cart记录
	 * @param cart_id ID
	 * @return
	 */
	Tb_cart selectCartById(Integer cart_id);
}
