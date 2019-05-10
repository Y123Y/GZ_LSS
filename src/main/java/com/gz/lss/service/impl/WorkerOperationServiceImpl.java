package com.gz.lss.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.dao.BooksDao;
import com.gz.lss.pojo.Tb_books;
import com.gz.lss.pojo.Tb_state;
import com.gz.lss.service.WorkerOperationService;
import com.gz.lss.util.tag.PageModel;

@Service
@Transactional
public class WorkerOperationServiceImpl implements WorkerOperationService {

	@Autowired
	private BooksDao booksDao;
	
	@Override
	public Boolean changBookState(Integer detail_id, Integer state_id) {
		try {
			booksDao.updateState(detail_id, state_id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Tb_state> selectStatesByIdentity(Integer identity) {
		List<Tb_state> list=new ArrayList<>();
		try {
			Set<Integer> set = new HashSet<>();
			if(identity==1||identity==4||identity==5||identity==7) {
				set.add(5);
				set.add(6);
				set.add(7);
			}
			if(identity==2||identity==4||identity==6||identity==7){
				set.add(7);
				set.add(8);
				set.add(9);
			}
			if(identity==3||identity==5||identity==6||identity==7) {
				set.add(8);
				set.add(10);
				set.add(11);				
			}
			for(Integer i : set) {
				list.add(booksDao.selectById(i));
			}
		}catch(Exception e) {
			e.printStackTrace();
			list=null;
		}
		return list;
	}

	@Override
	public List<Tb_books> selectBooksByState(Integer state, PageModel pageModel) {
		List<Tb_books> list=null;
		try {
			Integer count=booksDao.countByState(state);
			if(count>0) {
				pageModel.setRecordCount(count);
				Map<String,Object> map=new HashMap<>();
				map.put("state", state);
				map.put("pageModel", pageModel);
				list=booksDao.selectsByStateWithPage(map);
			}else {
				list=booksDao.selectsByState(state);
			}
		}catch(Exception e) {
			e.printStackTrace();
			list=null;
		}
		return list;
	}

	@Override
	public Tb_books selectBookById(Integer books_id) {
		// TODO 自动生成的方法存根
		Tb_books book = null;
		try {
			book = booksDao.selectBookById(books_id);
		}catch(Exception e) {
			e.printStackTrace();
			book = null;
		}
		
		return book;
	}

}
