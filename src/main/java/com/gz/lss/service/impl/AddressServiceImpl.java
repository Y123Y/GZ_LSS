package com.gz.lss.service.impl;

import java.util.List;

import com.gz.lss.dao.AddressDao;
import com.gz.lss.pojo.Tb_address;
import com.gz.lss.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao dao;
	
	@Override
	public List<Tb_address> getAddresses(Integer user_id) {
		List<Tb_address> list=null;
		try {
			list=dao.selectsByUser(user_id);
		}catch(Exception e) {
			e.printStackTrace();
			list=null;
		}
		return list;
	}

	@Override
	public Tb_address getAddress(Integer address_id) {
		Tb_address address=null;
		try {
			address=(Tb_address) dao.selectById(address_id);
		}catch(Exception e) {
			e.printStackTrace();
			address=null;
		}
		return address;
	}

	@Override
	public Boolean addAddress(Tb_address address){
		try {
			 dao.insert(address);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteAddress(Integer address_id) {
		try {
			dao.delete(address_id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean updateAddress(Tb_address address){
		try {
			dao.update(address);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
