package com.gz.lss.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_admin;

@Repository("AdminDao")
public interface AdminDao {

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
	 * @param passwd
	 * @return
	 */
	@Update("update "+LssConstants.TBADMIN+" set passwd = #{passwd} where account = #{account}")
	public int updatePwd(@Param("account") String account, @Param("passwd") String passwd);

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
