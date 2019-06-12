package com.gz.lss.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_books;
import com.gz.lss.pojo.Tb_state;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


/**
 * 订单的数据库操作
 * @author guozhi
 *
 */
@Repository("booksDao")
public interface BooksDao {
	
	/**
	 * 查询某个订单的所有订单详情
	 * @param order_id
	 * @return
	 */
	@Select("select * from "+ LssConstants.TBBOOKS  +" where order_id = #{order_id}")
	public List<Tb_books> selectsByOrder(Integer order_id) throws Exception;
	
	/**
	 * 更新订单详情的状态
	 * @param detail_id
	 * @param state
	 * @return
	 */
	@Update("update "+LssConstants.TBBOOKS
			+ " set state = #{state} where books_id = #{books_id}")
	public int updateState(@Param("books_id") Integer books_id, @Param("state") Integer state);
	
	/**
	 * 查找指定状态的订单
	 * @param state
	 * @return
	 */
	@Select("select * from "+LssConstants.TBBOOKS+" where state = #{state}")
	public List<Tb_books> selectsByState(Integer state);
	
	/**
	 * 查找指定状态的订单
	 * @param state
	 * @return
	 */
	@Select("select * from "+LssConstants.TBBOOKS+" where state = #{state} "
			+ "  limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ")
	public List<Tb_books> selectsByStateWithPage(Map<String, Object> map);
	
	/**
	 * 查找指定状态的订单
	 * @param state
	 * @return
	 */
	@Select("select count(*) from "+LssConstants.TBBOOKS+" where state = #{state}")
	public Integer countByState(Integer state);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBSTATE+" where state_id=#{id}")
	public Tb_state selectById(Integer id);

	/**
	 * 
	 * @param book
	 * @return
	 */
	@Insert("insert into tb_books (order_id,book_name,book_index,state,remarks,number,book_press,book_author) "
			+ "values(#{order_id},#{book_name},#{book_index},#{state},#{remarks},#{number},#{book_press},#{book_author})")
	public int insert(Tb_books book);

	/**
	 * 
	 * @param books_id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBBOOKS+" where books_id=#{books_id}")
	public Tb_books selectBookById(Integer books_id);
	
	/*
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
	 * 插入一条订单详情记录
	 * @param detail
	 * @return
	 *//*
	@Insert("insert into tb_detail (order_id,book_id,book_index,book_name,state) "
			+ "values(#{order_id},#{book_id},#{book_index},#{book_name},#{state})")
	public int insertDetail(Tb_books detail);*/
	
	

	
}
