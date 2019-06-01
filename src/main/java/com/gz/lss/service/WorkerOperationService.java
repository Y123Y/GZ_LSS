package com.gz.lss.service;

import java.util.List;

import com.gz.lss.pojo.Tb_books;
import com.gz.lss.pojo.Tb_state;
import com.gz.lss.util.tag.PageModel;

public interface WorkerOperationService {
	/**
	 * 更改订单详情图书状态
	 * @param detail_id 订单详情ID
	 * @param state_id 状态ID
	 */
	Boolean changBookState(Integer detail_id, Integer state_id);
	
	/**
	 * 根据用户身份返回其所能操作的状态
	 * @param identity
	 * @return
	 */
	List<Tb_state> selectStatesByIdentity(Integer identity);
	
	/**
	 * 根据状态返回其所能操作的数据
	 * @param identity	身份
	 * @param pageModel 分页
	 * @return
	 */
	List<Tb_books> selectBooksByState(Integer state, PageModel pageModel);
	
	/**
	 * 根据books_id查找一本书
	 * @param books_id
	 * @return
	 */
	Tb_books selectBookById(Integer books_id);

	/* ================================================================================= */

	/**
	 * 获取所有状态信息
	 * @return
	 */
	List<Tb_state> getStates();

	/**
	 * 根据身份返回能查看的图书列表
	 * @param identity
	 * @return
	 */
	List<Tb_books> selectBooksByIdentity(Integer identity);

	/**
	 * 根据订单查找图书
	 * @param orderId
	 * @return
	 */
	List<Tb_books> getBooksByOrderId(Integer orderId);
}
