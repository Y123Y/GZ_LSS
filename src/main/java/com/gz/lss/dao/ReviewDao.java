package com.gz.lss.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_review;

@Repository
public interface ReviewDao {

	/**
	 * 
	 * @param state
	 * @return
	 */
	@Select("select * from "+LssConstants.TBREVIEW+" where state=#{state} ")
	public List<Tb_review> selectsByState(Integer state) throws Exception;
	
	/**
	 * 
	 * @param state
	 * @return
	 */
	@Select("select count(*) from "+LssConstants.TBREVIEW+" where state=#{state} ")
	public Integer countByState(Integer state) throws Exception;
	
	/**
	 * 
	 * @param review_id
	 * @return
	 */
	@Select("select want from "+LssConstants.TBREVIEW+" where review_id=#{review_id} ")
	public Integer selectWantState(Integer review_id);
	
	/**
	 * 
	 * @param review_id
	 * @return
	 */
	@Update("update "+LssConstants.TBREVIEW+" set state=13 where review_id=#{review_id} ")
	public Boolean passReview(Integer review_id)throws Exception;
	
	/**
	 * 
	 * @param review_id
	 * @return
	 */
	@Update("update "+LssConstants.TBREVIEW+" set state=14 where review_id=#{review_id} ")
	public Boolean rejectReview(Integer review_id)throws Exception;
	
	/**
	 * 
	 * @param review
	 * @return
	 */
	@Insert("insert into "+LssConstants.TBREVIEW+" (worker_id,current,want,descript,state)"
			+ "values(#{worker_id},#{current},#{want},#{descript},#{state})")
	public Boolean insert(Tb_review review)throws Exception;

	/**
	 * 
	 * @param worker_id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBREVIEW+" where worker_id=#{worker_id} and state=12 ")
	public Tb_review selectByWorker(Integer worker_id)throws Exception;


	/**
	 * 删除和指定工作人员相关的审核请求
	 * @param worker_id
	 * @return
	 * @throws Exception
	 */
	@Delete("delete from "+ LssConstants.TBREVIEW + " where worker_id = #{worker_id}")
	public int deleteByWorkerId(Integer worker_id) throws Exception;

	/**
	 * 根据id查询一条记录
	 * @param review_id
	 * @return
	 * @throws Exception
	 */
	@Select("select * from " + LssConstants.TBREVIEW + " where review_id = #{review_id}")
	public Tb_review selectById(Integer review_id) throws Exception;
	
}
