package com.rograndec.feijiayun.chain.business.auth.register.dao;

import com.rograndec.feijiayun.chain.business.auth.register.entity.RegisterAudit;

public interface RegisterAuditMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RegisterAudit record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RegisterAudit record);

    /**
     *
     * @mbg.generated
     */
    RegisterAudit selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RegisterAudit record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RegisterAudit record);
}