package com.gz.lss.service;



import com.gz.lss.entity.OrderInfo;
import com.gz.lss.pojo.Tb_books;
import com.gz.lss.pojo.Tb_order;
import com.gz.lss.util.tag.PageModel;

import java.util.List;



public interface OrderService {
	/**
	 * 根据用户ID查询订单记录
	 * @param user_id 用户ID
	 * @return
	 */
	List<Tb_order> selectOrderByUser(Integer user_id, PageModel pageModel);
	
	/**
	 * 根据订单ID查询订单详情
	 * @param order_id 订单ID
	 * @return
	 */
	List<Tb_books> selectBooksByOrder(Integer order_id);
	
	/**
	 * 删除订单
	 * @param order_id 订单ID
	 * @return
	 */
	Boolean deleteOrder(Integer order_id);
	
	/**
	 * 添加订单并返回订单ID
	 * @param order 订单信息
	 * @return	订单ID
	 */
	Integer addOrder(Tb_order order);
	
	/**
	 * 向书目表中加入一条记录
	 * @param book 图书信息
	 * @return
	 */
	Boolean addBooks(Tb_books book);
	
	/**
	 * 改变订单状态
	 * @param state
	 * @return
	 */
	Boolean setOrderState(Integer order_id, Integer state);

	/* ===================================== worker ============================================= */

	/**
	 * 联表查询所有订单
	 * @return
	 */
	List<OrderInfo> selectAllOrder();
}
