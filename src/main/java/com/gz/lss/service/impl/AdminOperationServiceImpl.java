package com.gz.lss.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.dao.AdminDao;
import com.gz.lss.dao.ReviewDao;
import com.gz.lss.dao.WorkerDao;
import com.gz.lss.pojo.Tb_admin;
import com.gz.lss.pojo.Tb_review;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.service.AdminOperationService;
import com.gz.lss.util.tag.PageModel;

@Service
@Transactional
public class AdminOperationServiceImpl implements AdminOperationService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private WorkerDao workerDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public List<Tb_review> getAllIdentityRequest(Integer state, PageModel pageModel){
		List<Tb_review> list=null;
		try {
			Integer count=reviewDao.countByState(state);
			if(count>0) {
				pageModel.setRecordCount(count);
				Map<String,Object> map=new HashMap<String , Object>();
				map.put("state", state);
				map.put("pageModel", pageModel);
				list=reviewDao.selectsByStateWithPage(map);
			}else {
				list=reviewDao.selectsByState(state);
			}
		}catch(Exception e) {
			e.printStackTrace();
			list=null;
		}
		return list;
	}

	@Override
	public Boolean passIdentityRequest(Integer review_id){
		try {
			reviewDao.passReview(review_id);
			Integer want=reviewDao.selectWantState(review_id);
			if(want!=null&&want!=0) {
				workerDao.updateIndentity(review_id,want);
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean rejectIdentityRequest(Integer review_id){
		try {
			reviewDao.rejectReview(review_id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Tb_worker> selectWorker() {
		List<Tb_worker> list=null;
		try {
			list=workerDao.selects();
			Iterator<Tb_worker> iter = list.iterator();
			while(iter.hasNext()){
				Tb_worker wor = iter.next();
				wor.setPasswd("");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Boolean deleteWorker(Integer worker_id) {
		Boolean b=false;
		try {
			b=workerDao.delete(worker_id);
		}catch(Exception e) {
			e.printStackTrace();
			b=false;
		}
		return b;
	}

	@Override
	public Boolean addAdmin(Tb_admin admin) {
		Boolean b=false;
		try {
			b=adminDao.insert(admin);
		}catch(Exception e) {
			e.printStackTrace();
			b=false;
		}
		return b;
	}

	@Override
	public Boolean deleteAdmin(Integer admin_id) {
		Boolean b=false;
		try {
			b=adminDao.delete(admin_id);
		}catch(Exception e) {
			e.printStackTrace();
			b=false;
		}
		return b;
	}

}
