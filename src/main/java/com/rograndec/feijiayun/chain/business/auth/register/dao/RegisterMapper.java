package com.rograndec.feijiayun.chain.business.auth.register.dao;

import com.rograndec.feijiayun.chain.business.auth.register.entity.Register;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterEnterpriseNotBindVO;

import java.util.List;

public interface RegisterMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Register record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Register record);

    /**
     *
     * @mbg.generated
     */
    Register selectByPrimaryKey(Long id);

    Register selectByUserId(Long id);

    Register selectByLoginAccount(String loginAccount);

    List<RegisterEnterpriseNotBindVO> selectNotBindEnterprise();

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Register record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Register record);
}