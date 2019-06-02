package com.gz.lss.dao;

import java.util.List;

import com.gz.lss.pojo.Tb_state;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_w_identity;
import com.gz.lss.pojo.Tb_worker;

@Repository("workerDao")
public interface WorkerDao {

	/**
	 * 
	 * @param id
	 * @param identity
	 * @return
	 * @throws Exception
	 */
	@Update("update "+LssConstants.TBWORKER+" set identity=#{identity} where worker_id=#{id} ")
	public Boolean updateIndentity(@Param("id") Integer id, @Param("identity") Integer identity) throws Exception;
	
	/**
	 * 
	 * @param worker
	 * @return
	 */
	@Update("update "+LssConstants.TBWORKER+" set "
			+ " login_name=#{login_name},"
			+ " name=#{name},"
			+ "	tel=#{tel} where worker_id=#{worker_id}")
	public Boolean updateNoPwd(Tb_worker worker) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@Update("update "+LssConstants.TBWORKER+" set passwd=#{pwd} where worker_id=#{id} ")
	public Boolean updatePwd(@Param("id") Integer id, @Param("pwd") String pwd) throws Exception;
	
	
	/**
	 * 
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	@Select("select * from "+LssConstants.TBWORKER+" where login_name=#{loginName} ")
	public Tb_worker selectByLogin(String loginName) throws Exception;

	/**
	 * 通过id查找姓名
	 * @param worker_id
	 * @return
	 * @throws Exception
	 */
	@Select("select name from " + LssConstants.TBWORKER + " where worker_id = #{worker_id}")
	public String selectNameById(Integer worker_id) throws Exception;

	/**
	 * 
	 * @param worker
	 * @return
	 */
	@Insert("insert into "+LssConstants.TBWORKER
			+" (login_name,passwd,secret_key) values("
			+ "#{login_name},#{passwd},#{secret_key})")
	Boolean insert(Tb_worker worker) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBWORKER+" where worker_id=#{id}")
	public Tb_worker selectById(Integer id) throws Exception;

	/**
	 * 查找所有身份
	 * @return
	 */
	@Select("select * from "+LssConstants.TBWIDENTITY)
	public List<Tb_w_identity> selectsIdentity() throws Exception;
	
	/**
	 * 查找所有工作人员
	 * @return
	 */
	@Select("select * from "+LssConstants.TBWORKER)
	public List<Tb_worker> selects() throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Delete("delete from "+LssConstants.TBWORKER+" where worker_id=#{id}")
	public int delete(Integer id) throws Exception;

	/**
	 * 
	 * @return
	 */
	@Select("select last_insert_id() ")
	public Integer selectId() throws Exception;

	/* ================================================================================= */

	/**
	 * 查找所有状态信息
	 * @return
	 */
	@Select("select * from "+LssConstants.TBSTATE)
	public List<Tb_state> selectStates() throws Exception;
}
