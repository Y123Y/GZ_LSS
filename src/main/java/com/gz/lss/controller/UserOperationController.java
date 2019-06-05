package com.gz.lss.controller;

import com.alibaba.fastjson.JSON;
import com.gz.lss.common.LssConstants;
import com.gz.lss.entity.BookInfo;
import com.gz.lss.pojo.Tb_address;
import com.gz.lss.pojo.Tb_cart;
import com.gz.lss.pojo.Tb_user;
import com.gz.lss.service.AddressService;
import com.gz.lss.service.UserOperationService;
import com.gz.lss.service.UserService;
import com.gz.lss.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/userOperation")
public class UserOperationController {
	@Autowired
	private UserOperationService userOperationService;
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;

	/**
	 * 查询图书
	 * @return
	 */
	@RequestMapping(value="/selectBooks")
	@ResponseBody
	public String selectBooks(@RequestParam("keyword") String keyword, Integer pageIndex) {
		System.out.println(keyword);
		Map<String, Object> data = userOperationService.selectBooks(keyword, pageIndex);
		if(data.get("list") != null) {
			data.put("pageIndex", pageIndex);
			return JSON.toJSONString(data);			
		}else {
			return "failed";
		}
	
	}
	
	/**
	 * 添加图书至待订书单
	 * @param book 图书信息
	 */
	@RequestMapping("/addBooktoCart")
	@ResponseBody
	public String addBooktoCart(@ModelAttribute BookInfo book, Integer number, HttpSession session) {
		Tb_cart cart = new Tb_cart();
		
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		cart.setBook_name(book.getBook_name());
		cart.setBook_index(book.getBook_num());
		cart.setBook_author(book.getAuthor());
		cart.setBook_press(book.getPublish());
		cart.setPublish_year(book.getPublish_year());
		cart.setNumber(number);
		cart.setUser_id(currentUser.getUser_id());
		
		if(userOperationService.addBooktoCart(cart)) {
			return "true";
		}else {
			return "false";
		}
	}
	
	/**
	 * 从待订书单删除图书
	 * @param cart_id	图书ID
	 * @return
	 */
	@RequestMapping("/deleteBookFromCart")
	public String deleteBookFromCart(String cart_ids, Model model) {
		String[] idArray = cart_ids.split(",");
		for(String cart_id : idArray){
			userOperationService.deleteBookFromCart(Integer.parseInt(cart_id));
		}
		model.addAttribute("message", "删除成功");
		return "redirect:bookCart";
	}
	
	/**
	 * 展示待订书单
	 * @param session
	 * @return
	 */
	@RequestMapping("/bookCart")
	public String bookCart(Integer pageIndex, HttpSession session, Model model) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Tb_cart> cart = userOperationService.selectCartByUser(currentUser.getUser_id(), pageModel);
		model.addAttribute("cart", cart);
		model.addAttribute("pageModel", pageModel);
		List<Tb_address> addresses = addressService.getAddresses(currentUser.getUser_id());
		model.addAttribute("addresses", addresses);
		return LssConstants.BOOKCART;
	}
	
	/**
	 * 跳转用户确认页面
	 * @param bookIds	图书IDs
	 * @return
	 */
	@RequestMapping("/placeOrder")
	public String placeOrder(String bookIds, HttpSession session, Model model) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		Tb_user user = userService.selectUserById(currentUser.getUser_id());
		user.setPasswd(null);
		
		List<Tb_address> addresses = addressService.getAddresses(user.getUser_id());
		
		List<Tb_cart> books = new ArrayList<>();
		Tb_cart cart = null;
		String[] idArray = bookIds.split(",");
		for(String cart_id : idArray){
			cart = userOperationService.selectCartById(Integer.parseInt(cart_id));
			books.add(cart);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("addresses", addresses);
		model.addAttribute("books", books);
		
		return LssConstants.BOOKORDERDETERMINE;
	}
	
}
