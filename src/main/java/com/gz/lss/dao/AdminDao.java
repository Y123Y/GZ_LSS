package com.gz.lss.dao;

import com.gz.lss.entity.WorkerExamine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_admin;

import java.util.List;

@Repository("AdminDao")
public interface AdminDao {

	/**
	 * 修改管理员姓名
	 * @param admin_id
	 * @param name
	 * @return
	 */
	@Update("update "+ LssConstants.TBADMIN + " set name = #{name} where admin_id = #{admin_id}")
	public int updateAdminName(Integer admin_id, String name) throws Exception;

	/**
	 * 
	 * @param userName
	 * @return
	 */
	@Select("select * from "+LssConstants.TBADMIN+" where account=#{userName} ")
	public Tb_admin selectByUserName(String userName);

	/**
	 * 修改用户密码
	 * @param admin_id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@Update("update "+LssConstants.TBADMIN + " set passwd = #{newPassword} where admin_id = #{admin_id} and passwd = #{oldPassword}")
	public int updatePwd(@Param("admin_id") Integer admin_id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword) throws Exception;

	/**
	 * 
	 * @param admin
	 * @return
	 */
	@Insert("insert into "+LssConstants.TBADMIN+" (account,passwd,name) "
			+ "values(#{account},#{passwd},#{name}) ")
	public Boolean insert(Tb_admin admin);

	/**
	 * 
	 * @param admin_id
	 * @return
	 */
	@Delete("delete from "+LssConstants.TBADMIN+" where admin_id=#{admin_id}")
	public Boolean delete(Integer admin_id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBADMIN+" where admin_id=#{id} ")
	public Tb_admin select(Integer id);

	/**
	 * 
	 * @param account
	 * @return
	 */
	@Select("select * from "+LssConstants.TBADMIN+" where account=#{account} ")
	public Tb_admin selectByAccount(String account);

	/**
	 * 修改基本信息（不含密码）
	 * @param admin
	 * @return
	 */
	@Update("update "+LssConstants.TBADMIN+" set "
			+ "account=#{account},"
			+ "name=#{name} where admin_id=#{admin_id}")
	public Boolean update(Tb_admin admin);
	
	
}
