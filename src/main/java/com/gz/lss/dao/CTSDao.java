package com.gz.lss.dao;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_state;
import com.gz.lss.pojo.Tb_w_identity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CTSDao {

    /**
     * 查询所有的工作人员身份信息
     * @return
     * @throws Exception
     */
    @Select("select * from "+LssConstants.TBWIDENTITY)
    public List<Tb_w_identity> getAllIdentity() throws Exception;

    /**
     * 根据身份标识获取身份名称
     * @param identity
     * @return
     * @throws Exception
     */
    @Select("select name from "+ LssConstants.TBWIDENTITY +" where identity = #{identity} ")
    public String getIdentityByCode(Integer identity) throws  Exception;

    /**
     * 获取所有的状态类型
     * @return
     * @throws Exception
     */
    @Select("select * from " + LssConstants.TBSTATE)
    public List<Tb_state> getAllState() throws Exception;

    /**
     * 根据id获取状态名称
     * @param state_id
     * @return
     * @throws Exception
     */
    @Select("select name from "+ LssConstants.TBSTATE+" where state_id = #{state_id}")
    public String getStateByCode(Integer state_id) throws  Exception;
}
