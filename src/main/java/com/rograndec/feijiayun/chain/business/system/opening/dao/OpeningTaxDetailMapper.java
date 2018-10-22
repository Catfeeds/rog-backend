package com.rograndec.feijiayun.chain.business.system.opening.dao;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningTaxDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpeningTaxDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OpeningTaxDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OpeningTaxDetail record);

    /**
     *
     * @mbg.generated
     */
    OpeningTaxDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OpeningTaxDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OpeningTaxDetail record);

    List<OpeningTaxDetail> queryByTaxId(@Param("taxId") Long taxId);

}