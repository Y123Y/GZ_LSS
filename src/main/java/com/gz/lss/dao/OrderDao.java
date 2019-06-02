package com.gz.lss.dao;

import java.util.List;
import java.util.Map;

import com.gz.lss.entity.OrderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_order;

/**
 * 订单的数据库操作
 * @author guozhi
 *
 */
@Repository("OrderDao")
public interface OrderDao {
	
	/**
	 * 查询刚插入的记录的id
	 * @return
	 */
	@Select("select last_insert_id() ")
	public Integer selectId();
	
	/**
	 * 查找指定用户的订单
	 * @param state
	 * @return
	 */
	@Select("select * from tb_order where user_id = #{user_id} "
			+ "limit #{pageModel.firstLimitParam},#{pageModel.pageSize}")
	public List<Tb_order> selectsByUserWithPage(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @param user_id
	 * @return
	 */
	@Select("select count(*) from "+LssConstants.TBORDER+" where user_id = #{user_id}")
	public Integer countUser(Integer user_id)throws Exception;

	/**
	 * 查找指定用户的订单
	 * @param state
	 * @return
	 */
	@Select("select * from "+LssConstants.TBORDER+" where user_id = #{user_id}")
	public List<Tb_order> selectsByUser(Integer user_id)throws Exception;
	

	/**
	 * 
	 * @param order_id
	 * @return
	 */
	@Delete("delete from "+LssConstants.TBORDER+" where order_id = #{order_id}")
	public int delete(Integer order_id)throws Exception;

	
	/**
	 * 
	 * @param order
	 * @return
	 */
	@Insert("insert into tb_order (user_id,create_time,address_id,state,remarks) "
			+ "values(#{user_id},#{create_time},#{address_id},#{state},#{remarks})")
	public Boolean insert(Tb_order order);

	/**
	 * 
	 * @param order_id
	 * @param state
	 * @return
	 */
	@Update("update tb_order set state = #{state} where order_id = #{order_id}")
	public Boolean updateState(@Param("order_id") Integer order_id, @Param("state") Integer state);
	

	/*======================================== worker =============================================*/

	@Select("select order_id,login_name,create_time,content,"+LssConstants.TBADDRESS+".name," +LssConstants.TBADDRESS+".tel,remarks,state " +
			"from "+LssConstants.TBORDER+","+LssConstants.TBUSER+","+LssConstants.TBADDRESS+
			" where "+LssConstants.TBORDER+".user_id="+LssConstants.TBUSER+".user_id and "+LssConstants.TBORDER+".address_id="+LssConstants.TBADDRESS+".address_id")
	List<OrderInfo> selectAllOrder();

	@Select("select * from " + LssConstants.TBORDER + " where order_id=#{order_id}")
	Tb_order selectOrderById(Integer order_id);

	/*============================================================================================*/

	
	/**********************************************************************************/
	/*
	*//**
	 * 查找指定状态的订单
	 * @param state
	 * @return
	 *//*
	@Select("select * from "+LssConstants.TBORDER+" where state = #{state}")
	public List<Tb_order> selectOrderByState(String state);
	

	
	
	
	*//**
	 * 新增一条订单
	 * @param admin
	 * @return
	 *//*
	@Insert("insert into tb_order (user_id,create_time,address_id,state,remarks) "
			+ "values(#{user_id},#{create_time},#{address_id},#{state},#{remarks})")
	public int insertOrder(Tb_admin admin); 


	
	*//**
	 * 更新派送时间
	 * @param order_id
	 * @param deliver_time
	 * @return
	 *//*
	@Update("update tb_order set deliver_time = #{deliver_time} where order_id = #{order_id}")
	public int updateOrderDeliverTime(String order_id,Date deliver_time);
	
	*//**
	 * 更新订单完成时间
	 * @param order_id
	 * @param finish_time
	 * @return
	 *//*
	@Update("update tb_order set finish_time = #{finish_time} where order_id = #{order_id}")
	public int updateOrderFinishTime(String order_id,Date finish_time);
	
	*//**
	 * 更新订单状态
	 * @param order_id
	 * @param state
	 * @return
	 *//*
	@Update("update tb_order set state = #{state} where order_id = #{order_id}")
	public int updateOrderState(String order_id,String state);
	

	*//*****************************************************订单详情部分****************************************************//*
	
	*//**
	 * 查询所有的订单详情
	 * @return
	 *//*
	@Select("select * from tb_detail")
	public List<Tb_books> selectDetail();
	
	*//**
	 * 模糊查询
	 * @param content
	 * @return
	 *//*
	@Select("select * from tb_detail where "
			+ " book_name like CONCAT('%',#{content},'%') "
			+ " or book_index like CONCAT('%',#{content},'%') "
			+ " or state like CONCAT('%',#{content},'%') ")
	public List<Tb_books> selectDetail1(String content);
	
	*//**
	 * 查找指定状态的订单
	 * @param state
	 * @return
	 *//*
	@Select("select * from tb_detail where state = #{state}")
	public List<Tb_order> selectDetailByState(String state);
	
	*//**
	 * 查询某个订单的所有订单详情
	 * @param order_id
	 * @return
	 *//*
	@Select("select * from tb_detail where order_id = #{order_id}")
	public List<Tb_order> selectDetailByOrder(Integer order_id);
	
	*//**
	 * 插入一条订单详情记录
	 * @param detail
	 * @return
	 *//*
	@Insert("insert into tb_detail (order_id,book_id,book_index,book_name,state) "
			+ "values(#{order_id},#{book_id},#{book_index},#{book_name},#{state})")
	public int insertDetail(Tb_books detail);
	
	*//**
	 * 删除一条订单详情记录
	 * @param detail_id
	 * @return
	 *//*
	@Delete("delete from tb_detail where detail_id = #{detail_id}")
	public int deleteDetail(Integer detail_id);
	
	*//**
	 * 更新订单详情的状态
	 * @param detail_id
	 * @param state
	 * @return
	 *//*
	@Update("update tb_detail set state = #{state} where detail_id = #{detail_id}")
	public int updateDetailState(Integer detail_id,String state);*/
	
}
