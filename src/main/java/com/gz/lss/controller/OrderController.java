package com.gz.lss.controller;

import com.alibaba.fastjson.JSON;
import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.*;
import com.gz.lss.service.AddressService;
import com.gz.lss.service.OrderService;
import com.gz.lss.service.UserOperationService;
import com.gz.lss.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserOperationService userOperationService;
	@Autowired
	private AddressService addressService;

	/**
	 * 下单
	 * @param order	订单信息
	 * @return
	 */
	@RequestMapping("/placeOrder")
	public String placeOrder(Integer address_id, String remarks, String bookIds, HttpSession session) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		Tb_order order = new Tb_order();
		order.setUser_id(currentUser.getUser_id());
		order.setAddress_id(address_id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		order.setCreate_time(dateString);
		order.setRemarks(remarks);
		order.setState(1);
		Integer order_id = orderService.addOrder(order);
		
		Tb_cart cart = null;
		String[] idArray = bookIds.split(",");
		for(String cart_id : idArray){
			cart = userOperationService.selectCartById(Integer.parseInt(cart_id));
			Tb_books book = new Tb_books();
			book.setBook_author(cart.getBook_author());
			book.setBook_index(cart.getBook_index());
			book.setBook_press(cart.getBook_press());
			book.setBook_name(cart.getBook_name());
			book.setNumber(cart.getNumber());
			book.setOrder_id(order_id);
			book.setState(5);
			orderService.addBooks(book);
			
			userOperationService.deleteBookFromCart(Integer.parseInt(cart_id));
		}
		
		return "redirect:/order/history";
	}
	
	/**
	 * 订单详情
	 * @param order_id	订单ID
	 * @return
	 */
	@RequestMapping("/showOrderDetail")
	@ResponseBody
	public String showOrderDetail(Integer order_id) {
		String result = null;
		List<Tb_books> books = orderService.selectBooksByOrder(order_id);
		result = JSON.toJSONString(books);
		return result;
	}
	
	/**
	 * 跳转订单历史页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/history")
	public String orderHistroy(Integer pageIndex, HttpSession session, Model model) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Tb_order> orders = orderService.selectOrderByUser(currentUser.getUser_id(), pageModel);
		model.addAttribute("orders", orders);
		List<Tb_address> addresses = addressService.getAddresses(currentUser.getUser_id());
		model.addAttribute("addresses", addresses);
		return LssConstants.ORDERHISTORY;
	}
	
	/**
	 * 删除订单
	 * @param order_id	订单ID
	 * @return
	 */
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Integer order_id) {
		orderService.deleteOrder(order_id);
		
		return "forward:history";
	}
}
