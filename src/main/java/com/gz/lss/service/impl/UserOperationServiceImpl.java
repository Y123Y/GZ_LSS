package com.gz.lss.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.common.LssConstants;
import com.gz.lss.dao.CartDao;
import com.gz.lss.entity.BookInfo;
import com.gz.lss.pojo.Tb_cart;
import com.gz.lss.service.UserOperationService;
import com.gz.lss.util.BookUtils;
import com.gz.lss.util.tag.PageModel;

@Service
@Transactional
public class UserOperationServiceImpl implements UserOperationService {

	@Autowired
	private CartDao cartDao;
	
	@Override
	public Map<String, Object> selectBooks(String keyword, Integer page) {
		//爬取网页
		Map<String, Object> data = new HashMap<>();
	 	String st = URLEncoder.encode(keyword);
		String URL_PATH = "http://210.42.38.33/searchresult.aspx?anywords="
				+ st.trim()
				+ "&dt=ALL&cl=ALL&dept=ALL&sf=M_PUB_YEAR&ob=DESC&page=" + page
				+ "&dp=" + LssConstants.PAGE_DEFAULT_SIZE + "&sm=table";
		BookUtils bk = new BookUtils(URL_PATH);
		List<BookInfo> list = bk.getSearchBookMessage();
		data.put("list", list);
		data.put("totalPage", bk.getResultPages());
		return data;
	}

	@Override
	public Boolean addBooktoCart(Tb_cart cart) {
		try {
			cartDao.insert(cart);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteBookFromCart(Integer cart_id) {
		try {
			cartDao.delete(cart_id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Tb_cart> selectCartByUser(Integer user_id, PageModel pageModel) {
		List<Tb_cart> list = null;
		try {
			Integer count=cartDao.countByUser(user_id);
			if(count>0) {
				pageModel.setRecordCount(count);
				Map<String,Object> map=new HashMap<>();
				map.put("user_id", user_id);
				pageModel.setRecordCount(count);
				map.put("pageModel", pageModel);
				list=cartDao.selectsByUserWithPage(map);
			}else {
				list=cartDao.selectByUser(user_id);
			}
		}catch(Exception e) {
			e.printStackTrace();
			list=null;
		}
		return list;
	}

	@Override
	public Tb_cart selectCartById(Integer cart_id) {
		Tb_cart cart=null;
		try {
			cart=cartDao.select(cart_id);
		}catch(Exception e) {
			e.printStackTrace();
			cart=null;
		}
		return cart;
	}

}
