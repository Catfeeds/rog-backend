package com.rograndec.feijiayun.chain.business.system.opening.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningTax;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxDetailVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxVO;

public interface OpeningTaxMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OpeningTax record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OpeningTax record);

    /**
     *
     * @mbg.generated
     */
    OpeningTax selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OpeningTax record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OpeningTax record);
    
    OpeningTaxVO getOpeningTaxList(@Param("enterpriseId") Long enterpriseId);
    
    List<OpeningTaxDetailVO> queryGoodsTaxRates(@Param("enterpriseId") Long enterpriseId);
}