package com.gz.lss.service.impl;

import java.util.*;

import com.gz.lss.dao.OrderDao;
import com.gz.lss.dao.WorkerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.dao.BooksDao;
import com.gz.lss.pojo.Tb_books;
import com.gz.lss.pojo.Tb_state;
import com.gz.lss.service.WorkerOperationService;
import com.gz.lss.util.tag.PageModel;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Transactional
public class WorkerOperationServiceImpl implements WorkerOperationService {

	private final BooksDao booksDao;
	private final WorkerDao workerDao;
	private final OrderDao orderDao;

	@Autowired
	public WorkerOperationServiceImpl(BooksDao booksDao, WorkerDao workerDao, OrderDao orderDao) {
		this.booksDao = booksDao;
		this.workerDao = workerDao;
		this.orderDao = orderDao;
	}

	@Override
	public List<Tb_books> selectBooksByState(Integer state, PageModel pageModel) {
		List<Tb_books> list = null;
		try {
			Integer count = booksDao.countByState(state);
			if (count > 0) {
				pageModel.setRecordCount(count);
				Map<String, Object> map = new HashMap<>();
				map.put("state", state);
				map.put("pageModel", pageModel);
				list = booksDao.selectsByStateWithPage(map);
			} else {
				list = booksDao.selectsByState(state);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		}
		
		return book;
	}

	/* ================================================================================= */

	@Override
	public List<Tb_state> selectStatesByIdentity(Integer identity) {
		List<Tb_state> list=new ArrayList<>();
		try {
			Set<Integer> set = new HashSet<>();
			if(identity==1||identity==3) {
				set.add(5);
				set.add(6);
				set.add(7);
			}
			if(identity==2||identity==3){
				set.add(7);
				set.add(8);
				set.add(9);
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

	/**
	 * 该身份能否修改该状态
	 *
	 * @param identity 身份
	 * @param state    状态
	 * @return
	 */
	@Override
	public Boolean checkStateIdentity(Integer identity, Integer state) {
		Set<Integer> set = getStatesByIdentity(identity);
		return set.contains(state);
	}

	@Override
	public Boolean changBookState(Integer detail_id, Integer state_id) {
		try {
			Tb_books book = booksDao.selectBookById(detail_id);
			if (!book.getState().equals(state_id)) {
				booksDao.updateState(detail_id, state_id);
			}

			if (state_id == 6 || state_id == 7) {
				orderDao.updateState(book.getOrder_id(), 2);
			} else if (state_id == 8 || state_id == 9) {
				Integer[] states = new Integer[]{0, 0};
				List<Tb_books> books = booksDao.selectsByOrder(book.getOrder_id());
				for (Tb_books b : books) {
					if (b.getState() == 8) {
						states[0]++;	//登记成功
					} else if (b.getState() == 9) {
						states[1]++;	//登记失败
					}
				}
				if (states[0] + states[1] == books.size()) {
					if (states[0] > 0) {
						orderDao.updateState(book.getOrder_id(), 3);
					} else if (states[0] == 0) {
						orderDao.updateState(book.getOrder_id(), 4);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	/**
	 * 获取所有状态信息
	 *
	 * @return
	 */
	@Override
	public List<Tb_state> getStates() {
		List<Tb_state> states = null;
		try {
			states = workerDao.selectStates();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return states;
	}

	@Override
	public List<Tb_books> selectBooksByIdentity(Integer identity) {
		List<Tb_books> books = new ArrayList<>();
		Set<Integer> set = getStatesByIdentity(identity);
		for (Integer state : set) {
			books.addAll(booksDao.selectsByState(state));
		}
		return books;
	}

	private Set<Integer> getStatesByIdentity(Integer identity) {
		List<Integer[]> sets = new ArrayList<>();
		sets.add(new Integer[]{5, 6, 7});
		sets.add(new Integer[]{7, 8, 9});
		Set<Integer> set = new HashSet<>();
		int index = 0;
		while (identity != 0) {
			int flag = identity & 1;
			if (flag == 1 && sets.size() > index) {
				Collections.addAll(set, sets.get(index));
			}
			identity = identity >> 1;
			index++;
		}
		return set;
	}

	@Override
	public List<Tb_books> getBooksByOrderId(Integer orderId) {
		List<Tb_books> result = null;
		try {
			result = booksDao.selectsByOrder(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
