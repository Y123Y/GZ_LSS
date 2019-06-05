package com.gz.lss.service.impl;

import java.util.*;

import com.gz.lss.dao.CTSDao;
import com.gz.lss.entity.WorkerExamine;
import com.gz.lss.pojo.*;
import com.gz.lss.util.security.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AccessFieldTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.lss.dao.AdminDao;
import com.gz.lss.dao.ReviewDao;
import com.gz.lss.dao.WorkerDao;
import com.gz.lss.service.AdminOperationService;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Transactional
public class AdminOperationServiceImpl implements AdminOperationService {


    private final ReviewDao reviewDao;

    private final WorkerDao workerDao;

    private final AdminDao adminDao;

    private final CTSDao ctsDao;

    public AdminOperationServiceImpl(ReviewDao reviewDao, WorkerDao workerDao, AdminDao adminDao, CTSDao ctsDao) {
        this.reviewDao = reviewDao;
        this.workerDao = workerDao;
        this.adminDao = adminDao;
        this.ctsDao = ctsDao;
    }

    /**
     * 获取状态标识和用户身份标识的键值对信息
     *
     * @return
     */
    @Override
    public Map<String, Object> getStringAndCode() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Tb_state> states = ctsDao.getAllState();
            List<Tb_w_identity> identities = ctsDao.getAllIdentity();
            map.put("states", states);
            map.put("identities", identities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 重置工作人员的密码
     * 密码为 abc123
     *
     * @return
     */
    @Override
    public Boolean ressetPasswordOfWorker(Integer worker_id) {
        Boolean res;
        try {
            Tb_worker w = workerDao.selectById(worker_id);
            String pwd = PasswordHelper.getPasswordDigest("abc123", w.getSecret_key());
            res = workerDao.updatePwd(worker_id, pwd) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

    /**
     * 根据审核信息的状态获取所有的审核信息
     *
     * @return
     */
    @Override
    public List<WorkerExamine> getExamineOfNeed() {
        List<WorkerExamine> list = new ArrayList<>();
        try {
            List<Tb_review> list2 = reviewDao.selectsByState(12);
            for (Tb_review t : list2) {
                WorkerExamine w = new WorkerExamine();
                w.setReview_id(t.getReview_id());
                w.setWorker_name(workerDao.selectNameById(t.getWorker_id()));
                w.setCurrent(ctsDao.getIdentityByCode(t.getCurrent()));
                w.setWant(ctsDao.getIdentityByCode(t.getWant()));
                w.setDescription(t.getDescript());
                w.setState(ctsDao.getStateByCode(t.getState()));
                list.add(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 处理身份审核
     *
     * @param review_id
     * @param suggestion
     * @return
     */
    @Override
    public Boolean handleExamine(Integer review_id, Boolean suggestion) {
        Boolean res;
        try {
            if (suggestion) {
                reviewDao.passReview(review_id);
                Tb_review review = reviewDao.selectById(review_id);
                if (review.getWant() != null && review.getWant() != 0) {
                    res = workerDao.updateIndentity(review.getWorker_id(), review.getWant()) > 0;
                } else {
                    res = false;
                }
            } else {
                reviewDao.rejectReview(review_id);
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        if (!res) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

    @Override
    public List<Tb_worker> selectWorker() {
        List<Tb_worker> list = null;
        try {
            list = workerDao.selects();
            Iterator<Tb_worker> iter = list.iterator();
            while (iter.hasNext()) {
                Tb_worker wor = iter.next();
                wor.setPasswd(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Boolean deleteWorker(Integer worker_id) {
        boolean b;
        try {
            if (workerDao.delete(worker_id) <= 0) {
                b = false;
            } else {
                reviewDao.deleteByWorkerId(worker_id);
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return b;
    }

    @Override
    public Boolean addAdmin(Tb_admin admin) {
        boolean b;
        try {
            b = adminDao.insert(admin) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return b;
    }

    @Override
    public Boolean deleteAdmin(Integer admin_id) {
        boolean b;
        try {
            b = adminDao.delete(admin_id) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return b;
    }

}
