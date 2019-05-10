package com.gz.lss.service;

import java.util.List;

import com.gz.lss.pojo.Tb_address;

public interface AddressService {
	/**
	 * 根据用户ID查询所有地址
	 * @param user_id	用户ID
	 * @return
	 * @throws Exception 
	 */
	List<Tb_address> getAddresses(Integer user_id);
	
	/**
	 * 根据地址ID查询一条地址
	 * @param address_id	地址ID
	 * @return
	 * @throws Exception 
	 */
	Tb_address getAddress(Integer address_id);
	
	/**
	 * 添加地址
	 * @param address	地址信息
	 * @return
	 * @throws Exception 
	 */
	Boolean addAddress(Tb_address address);
	
	/**
	 * @param address_id	地址ID
	 * @return
	 * @throws Exception 
	 */
	Boolean deleteAddress(Integer address_id);
	
	/**
	 * 更新地址
	 * @param address	地址信息
	 * @return
	 * @throws Exception 
	 */
	Boolean updateAddress(Tb_address address);
}
