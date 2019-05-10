package com.gz.lss.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Tb_order> list=null;
		try {
			Integer count=dao.countUser(user_id);
			if(count>0) {
				pageModel.setRecordCount(count);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("user_id", user_id);
				map.put("pageModel", pageModel);
				list=dao.selectsByUserWithPage(map);
			}else {
				list=dao.selectsByUser(user_id);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Tb_books> selectBooksByOrder(Integer order_id) {
		List<Tb_books>  list=null;
		try {
			list=booksDao.selectsByOrder(order_id);
		}catch(Exception e) {
			e.printStackTrace();
			list=null;
		}
		return list;
	}

	@Override
	public Boolean deleteOrder(Integer order_id) {
		try {
			dao.delete(order_id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Integer addOrder(Tb_order order) {
		Integer id=null;
		try {
			dao.insert(order);
			id=dao.selectId();
		}catch(Exception e) {
			e.printStackTrace();
			id=null;
		}
		return id;
	}

	@Override
	public Boolean addBooks(Tb_books book) {
		Boolean b=false;
		try {
			b=booksDao.insert(book);
		}catch(Exception e) {
			e.printStackTrace();
			b=false;
		}
		return b;
	}

	@Override
	public Boolean setOrderState(Integer order_id, Integer state) {
		Boolean b=false;
		try {
			b=dao.updateState(order_id,state);
		}catch(Exception e) {
			e.printStackTrace();
			b=false;
		}
		return b;
	}
}
