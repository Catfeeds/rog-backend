package com.rograndec.feijiayun.chain.business.system.opening.dao;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningReceivable;
import org.apache.ibatis.annotations.Param;

public interface OpeningReceivableMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OpeningReceivable record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OpeningReceivable record);

    /**
     *
     * @mbg.generated
     */
    OpeningReceivable selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OpeningReceivable record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OpeningReceivable record);

    OpeningReceivable selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId);
}