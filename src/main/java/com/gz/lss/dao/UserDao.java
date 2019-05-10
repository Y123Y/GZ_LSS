package com.gz.lss.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_user;

@Repository("UserDao")
public interface UserDao {

	/**
	 * 根据用户账号查找用户
	 * @param loginName
	 * @return
	 */
	@Select("select * from "+LssConstants.TBUSER+" where login_name = #{loginName}")
	public Tb_user selectByLogin(String loginName) throws Exception;
	
	

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@Insert("insert into "+LssConstants.TBUSER
			+ " (login_name,card_number,name,passwd,tel,qq,department,office_tel) "
			+ " values(#{login_name},#{card_number},#{name},#{passwd},#{tel},#{qq},#{department},#{office_tel})")
	public int insert(Tb_user user) throws Exception; 
	
	/**
	 * 修改用户的普通信息（不包括密码）
	 * @param user
	 * @return
	 */
	@Update("update "+LssConstants.TBUSER
			+ " set "
			+ "login_name = #{login_name},"
			+ "card_number = #{card_number},"
			+ "name = #{name},"
			+ "tel = #{tel},"
			+ "qq = #{qq},"
			+ "department = #{department},"
			+ "office_tel = #{office_tel} "
			+ "where user_id = #{user_id}")
	public int updateNoPwd(Tb_user user) throws Exception;
	
	/**
	 * 修改用户密码
	 * @param user_id
	 * @param passwd
	 * @return
	 */
	@Update("update "+LssConstants.TBUSER
			+ " set passwd = #{passwd} where user_id = #{id}")
	public int updatePwd(@Param("id") Integer id, @Param("passwd") String passwd) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("select * from "+LssConstants.TBUSER+" where user_id=#{id}")
	public Tb_user selectById(Integer id);
	
}
