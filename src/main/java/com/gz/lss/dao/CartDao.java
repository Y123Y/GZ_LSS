package com.gz.lss.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_cart;

/**
 * 订单的数据库操作
 * @author guozhi
 *
 */
@Repository("cartDao")
public interface CartDao {
	
	/**
	 * 插入一条购物车记录
	 * @param detail
	 * @return
	 */
	@Insert("insert into "+LssConstants.TBCART
			+ " (book_name,book_index,publish_year,number,book_press,book_author,user_id) "
			+ "values(#{book_name},#{book_index},#{publish_year},#{number},#{book_press},#{book_author},#{user_id})")
	public Boolean insert(Tb_cart cart) throws Exception;
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	@Select("select * from "+LssConstants.TBCART+" where user_id=#{user_id} "
			+ " limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ")
	public List<Tb_cart> selectsByUserWithPage(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @param user_id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBCART+" where user_id=#{user_id} ")
	public List<Tb_cart> selectByUser(Integer user_id) throws Exception;
	
	/**
	 * 
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	@Select("select count(*) from "+LssConstants.TBCART+" where user_id=#{user_id} ")
	public Integer countByUser(Integer user_id) throws Exception;

	/**
	 * 
	 * @param id
	 */
	@Delete("delete from "+LssConstants.TBCART+" where cart_id=#{id}")
	public void delete(Integer id);

	/**
	 * 
	 * @param cart_id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBCART+" where cart_id=#{cart_id}")
	public Tb_cart select(Integer cart_id);
	
	
}
