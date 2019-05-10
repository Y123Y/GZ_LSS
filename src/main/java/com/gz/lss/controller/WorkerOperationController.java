package com.gz.lss.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_books;
import com.gz.lss.pojo.Tb_state;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.service.OrderService;
import com.gz.lss.service.WorkerOperationService;
import com.gz.lss.service.WorkerService;
import com.gz.lss.util.tag.PageModel;

@Controller
@RequestMapping("/workerOperation")
public class WorkerOperationController {
	@Autowired
	private WorkerOperationService workerOperationService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 根据用户身份返回其所能操作的状态
	 * @param session
	 * @return
	 */
	@RequestMapping("/selectStates")
	@ResponseBody
	public String selectStates(HttpSession session) {
		Tb_worker currentWorker = (Tb_worker) session.getAttribute(LssConstants.WORKER_SESSION);
		Tb_worker worker = workerService.selectWorkerById(currentWorker.getWorker_id());
		
		List<Tb_state> stateList = new ArrayList<>();
		
		stateList = workerOperationService.selectStatesByIdentity(worker.getIdentity());
		
		return JSON.toJSONString(stateList);
	}
	
	/**
	 * 根据状态查询待处理图书
	 * @param state	状态ID
	 * @return
	 */
	@RequestMapping("/selectBooks")
	@ResponseBody
	public String selectBooks(Integer state, Integer pageIndex) {
		List<Tb_books> dataList = new ArrayList<>();
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		dataList = workerOperationService.selectBooksByState(state, pageModel);
		
		return JSON.toJSONString(dataList);
	}
	
	/**
	 * 更改订单详情图书状态
	 * @param detail_id 订单详情ID
	 * @param state_id 状态ID
	 */
	@RequestMapping("/changBookState")
	@ResponseBody
	public String changBookState(Integer books_id, Integer state_id) {
		String message = null;
		if(workerOperationService.changBookState(books_id, state_id)) {
			message = "true";
			if(state_id == 6 || state_id == 7) {
				Tb_books book = workerOperationService.selectBookById(books_id);
				orderService.setOrderState(book.getOrder_id(), 2);
			}
		}else {
			message = "false";
		}
		
		return message;
	}
}
