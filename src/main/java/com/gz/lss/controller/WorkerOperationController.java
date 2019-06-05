package com.gz.lss.controller;

import com.alibaba.fastjson.JSON;
import com.gz.lss.common.LssConstants;
import com.gz.lss.common.ResultGenerator;
import com.gz.lss.common.ResultMsg;
import com.gz.lss.entity.OrderInfo;
import com.gz.lss.pojo.Tb_books;
import com.gz.lss.pojo.Tb_state;
import com.gz.lss.pojo.Tb_user;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.service.OrderService;
import com.gz.lss.service.UserService;
import com.gz.lss.service.WorkerOperationService;
import com.gz.lss.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workerOperation")
public class WorkerOperationController {
	private final WorkerOperationService workerOperationService;
	private final WorkerService workerService;
	private final OrderService orderService;
	private final UserService userService;

	@Autowired
	public WorkerOperationController(WorkerOperationService workerOperationService, WorkerService workerService, OrderService orderService, UserService userService) {
		this.workerOperationService = workerOperationService;
		this.workerService = workerService;
		this.orderService = orderService;
		this.userService = userService;
	}

	/**
	 * 根据用户身份返回其所能操作的状态
	 * @param session
	 */
	@RequestMapping("/selectStates")
	public String selectStates(HttpSession session) {
		Tb_worker currentWorker = (Tb_worker) session.getAttribute(LssConstants.WORKER_SESSION);
		Tb_worker worker = workerService.selectWorkerById(currentWorker.getWorker_id());
		
		List<Tb_state> stateList;
		
		stateList = workerOperationService.selectStatesByIdentity(worker.getIdentity());
		
		return JSON.toJSONString(stateList);
	}
	
	/**
	 * 根据状态查询待处理图书
	 */
	@RequestMapping("/selectBooks")
	public ResultMsg selectBooks(HttpServletRequest request) {
		Integer identity = Integer.parseInt((String) request.getAttribute("authority"));
		List<Tb_books> dataList = workerOperationService.selectBooksByIdentity(identity);
		return ResultGenerator.genSuccessResultMsg(dataList);
	}

	/**
	 * 更改订单详情图书状态
	 *
	 * @param books_id 图书ID
	 * @param state_id 状态ID
	 */
	@RequestMapping("/changBookState")
	public ResultMsg changBookState(HttpServletRequest request, @RequestParam("stateId") Integer state_id, @RequestParam("booksId") Integer... books_id) {
		int identity = Integer.parseInt((String) request.getAttribute("authority"));
		if (!workerOperationService.checkStateIdentity(identity, state_id)) {
			return ResultGenerator.genFailResultMsg("您没有此操作权限");
		}
		for (int i = 0; i < books_id.length; i++) {
			Integer bookId = books_id[i];
			if (!workerOperationService.changBookState(bookId, state_id)) {
				if (i == 0) {
					return ResultGenerator.genFailResultMsg("状态更新失败");
				}
				return ResultGenerator.genFailResultMsg("成功更新" + i + "本书的状态，还有" + (books_id.length - i) + "本书更新失败");
			}
		}

		return ResultGenerator.genSuccessResultMsg();
	}

	@RequestMapping("/getAllOrder")
	public ResultMsg getAllOrder(){
		List<OrderInfo> allOrder = orderService.selectAllOrder();
		if (allOrder == null) {
			return ResultGenerator.genFailResultMsg("查询失败");
		}
		return ResultGenerator.genSuccessResultMsg(allOrder);
	}

	@RequestMapping("/getStates")
	public ResultMsg getStates(){
		List<Tb_state> states = workerOperationService.getStates();
		if (states == null) {
			return ResultGenerator.genFailResultMsg("查询失败");
		}
		Map<Integer, String> map = new HashMap<>();
		states.forEach((state) -> map.put(state.getState_id(), state.getName()));
		return ResultGenerator.genSuccessResultMsg(map);
	}

	@RequestMapping("/getBooksByOrderId")
	public ResultMsg getBooksByOrderId(@RequestParam("orderId") Integer orderId){
		List<Tb_books> books = workerOperationService.getBooksByOrderId(orderId);
		if (books == null) {
			return ResultGenerator.genFailResultMsg("未找到任何图书");
		}
		return ResultGenerator.genSuccessResultMsg(books);
	}

	@RequestMapping("/getUserInfo")
	public ResultMsg getUserInfo(@RequestParam("username") String username){
		Tb_user user = userService.selectUserByLoginName(username);
		if (user == null) {
			return ResultGenerator.genFailResultMsg("未找到该用户");
		}
		user.setPasswd(null);
		return ResultGenerator.genSuccessResultMsg(user);
	}
}
