package com.gz.lss.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gz.lss.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.dao.BooksDao;
import com.gz.lss.dao.OrderDao;
import com.gz.lss.pojo.Tb_books;
import com.gz.lss.pojo.Tb_order;
import com.gz.lss.service.OrderService;
import com.gz.lss.util.tag.PageModel;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;
	
	@Autowired
	private BooksDao booksDao;
	
	@Override
	public List<Tb_order> selectOrderByUser(Integer user_id, PageModel pageModel) {

		try {
			return dao.selectsByUser(user_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Tb_books> selectBooksByOrder(Integer order_id) {

		try {
			return booksDao.selectsByOrder(order_id);
		}catch(Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public Boolean deleteOrder(Integer order_id) {
		try {
			return dao.delete(order_id) > 0;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer addOrder(Tb_order order) {
		Integer id=null;
		try {
			if (dao.insert(order) > 0) {
				id=dao.selectId();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Boolean addBooks(Tb_books book) {
		boolean b = false;
		try {
			b = booksDao.insert(book) > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/* ===================================== worker ============================================= */

	@Override
	public Boolean setOrderState(Integer order_id, Integer state) {
		Boolean b = false;
		try {
			Tb_order order = dao.selectOrderById(order_id);
			if (!order.getState().equals(state)) {
				b = dao.updateState(order_id,state) > 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public List<OrderInfo> selectAllOrder() {
		return dao.selectAllOrder();
	}
}
