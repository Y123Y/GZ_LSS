package com.gz.lss.service.impl;

import java.util.List;

import com.gz.lss.util.security.PasswordHelper;
import com.gz.lss.util.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.dao.ReviewDao;
import com.gz.lss.dao.WorkerDao;
import com.gz.lss.pojo.Tb_review;
import com.gz.lss.pojo.Tb_w_identity;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.service.WorkerService;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDao dao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public Tb_worker workerLogin(String loginname, String password) {
		Tb_worker worker=null;
		try {
			worker=dao.selectByLogin(loginname);
			if(worker==null||!worker.getPasswd().equals(PasswordHelper.getPasswordDigest(password, worker.getSecret_key()))) {
				worker=null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			worker=null;
		}
		return worker;
	}

	@Override
	public Tb_worker selectWorkerById(Integer worker_id) {
		Tb_worker worker=null;
		try {
			worker=dao.selectById(worker_id);
		}catch(Exception e) {
			e.printStackTrace();
			worker=null;
		}
		return worker;
	}

	@Override
	public Tb_worker selectWorkerByLoginName(String loginname) {
		Tb_worker worker=null;
		try {
			worker=dao.selectByLogin(loginname);
		}catch(Exception e) {
			e.printStackTrace();
			worker=null;
		}
		return worker;
	}

	@Override
	public Integer addWorker(Tb_worker worker) {
		Integer id;
		try {
			 dao.insert(worker);
			 id = dao.selectId();
		}catch(Exception e) {
			e.printStackTrace();
			id = null;
		}
		return id;
	}

	@Override
	public Boolean updateWorker(Tb_worker worker) {
		try {
			Tb_worker worker2=dao.selectByLogin(worker.getLogin_name());
			//判断是否修改了用户名，如果修改了，那是否已存在这样的用户名
			if(worker2!=null&&!worker2.getWorker_id().equals(worker.getWorker_id())) {
				return false;
			}
			return dao.updateNoPwd(worker) > 0;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean updatePasswd(String worker_name,String oldPasswd, String newPasswd) {
		try {
			Tb_worker worker=dao.selectByLogin(worker_name);
			if(worker==null||!worker.getPasswd().equals(PasswordHelper.getPasswordDigest(oldPasswd, worker.getSecret_key()))) {
				return false;
			}else {
				return dao.updatePwd(worker.getWorker_id(), PasswordHelper.getPasswordDigest(newPasswd, worker.getSecret_key())) > 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	@Override
	public Boolean changeIdentity(Integer worker_id, Integer currentIdentity, Integer wantIdentity, String descript) {
		try {
			Tb_review review=new Tb_review();
			review.setWorker_id(worker_id);
			review.setCurrent(currentIdentity);
			review.setWant(wantIdentity);
			review.setDescript(descript);
			review.setState(12);
			reviewDao.insert(review);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Tb_w_identity> getIdentities() {
		List<Tb_w_identity> list=null;
		try {
			list=dao.selectsIdentity();
			Tb_w_identity temp=null;
			for(Tb_w_identity i:list) {
				if(i.getIdentity()==8) {
					temp=i;
					break;
				}
			}
			if(temp != null) {
				list.remove(temp);
			}
		}catch(Exception e) {
			e.printStackTrace();
			list=null;
		}
		return list;
	}

	/**
	 * 根据员工id查看 还没有经过处理的身份请求
	 */
	@Override
	public Tb_review selectReviewById(Integer worker_id) {
		Tb_review review;
		try {
			review = reviewDao.selectByWorker(worker_id);
		}catch(Exception e) {
			e.printStackTrace();
			review = null;
		}
		return review;
	}

}
