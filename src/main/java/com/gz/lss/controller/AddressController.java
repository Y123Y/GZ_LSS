package com.gz.lss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_address;
import com.gz.lss.pojo.Tb_user;
import com.gz.lss.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	/**
	 * 获取全部地址
	 * @param session
	 * @return
	 */
	@RequestMapping("/getAddresses")
	@ResponseBody
	public String getAddresses(HttpSession session, Model model) {
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);

		List<Tb_address> addresses = addressService.getAddresses(currentUser.getUser_id());
		return JSON.toJSONString(addresses);
	}
	
	/**
	 * 添加地址
	 * @param address	地址信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/addAddress")
	@ResponseBody
	public String addAddress(Tb_address address, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		Tb_user currentUser = (Tb_user) session.getAttribute(LssConstants.USER_SESSION);
		
		address.setUser_id(currentUser.getUser_id());
		
		if(addressService.addAddress(address)) {
			result.put("message", "添加地址成功");
		}else {
			result.put("message", "添加地址失败");
		}
		
		return JSON.toJSONString(result);
	}
	
	/**
	 * 删除地址
	 * @param address_id	地址ID
	 * @return
	 */
	@RequestMapping("/deleteAddress")
	@ResponseBody
	public String deleteAddress(Integer address_id) {
		Map<String, Object> result = new HashMap<>();
		
		if(addressService.deleteAddress(address_id)) {
			result.put("message", "删除地址成功");
		}else {
			result.put("message", "删除地址失败");
		}
		
		return JSON.toJSONString(result);
	}
	
	/**
	 * 根据地址ID查询一条地址
	 * @param address_id
	 * @return	json字符串
	 */
	@RequestMapping("/getAddress")
	@ResponseBody
	public String getAddress(Integer address_id) {
		Tb_address address = addressService.getAddress(address_id);
		return JSON.toJSONString(address);
	}
	
	/**
	 * 更新地址
	 * @param address	地址信息
	 * @return
	 */
	@RequestMapping("/updateAddress")
	@ResponseBody
	public String updateAddress(Tb_address address) {
		Map<String, Object> result = new HashMap<>();
		System.out.println(address.toString());
		if(addressService.updateAddress(address)) {
			result.put("message", "更新地址成功");
		}else {
			result.put("message", "更新地址失败");
		}
		
		return JSON.toJSONString(result);
	}
}
