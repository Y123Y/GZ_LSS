package com.gz.lss.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_address;

@Repository("addressDao")
public interface AddressDao {

	/**
	 * 根据user_id查询
	 * @param userId
	 * @return
	 */
	@Select("select * from "+LssConstants.TBADDRESS+" where user_id=#{userId}")
	public List<Tb_address> selectsByUser(Integer userId) throws Exception;
	
	/**
	 * 
	 * @param address
	 * @throws Exception
	 */
	@Insert("insert into "+LssConstants.TBADDRESS+" "
			+ "(user_id,content,name,tel) "
			+ "values(#{user_id},#{content},#{name},#{tel})")
	public Boolean insert(Tb_address address) throws Exception;

	@Update("update "+LssConstants.TBADDRESS+" set "
			+ "user_id=#{user_id},"
			+ "content=#{content},"
			+ "name=#{name},"
			+ "tel=#{tel}"
			+ " where address_id=#{address_id} ")
	public Boolean update(Tb_address address) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBADDRESS+" where address_id=#{id}")
	public Tb_address selectById(Integer id) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Delete("delete from "+LssConstants.TBADDRESS+" where address_id=#{id}")
	public Boolean delete(Integer id) throws Exception;
	
}
