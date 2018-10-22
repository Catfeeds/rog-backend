package com.rograndec.feijiayun.chain.business.goods.sale.dao;

import com.rograndec.feijiayun.chain.business.goods.sale.entity.SpecialPriceStrategy;

import java.util.List;
import java.util.Map;

public interface SpecialPriceStrategyMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SpecialPriceStrategy record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SpecialPriceStrategy record);

    /**
     *
     * @mbg.generated
     */
    SpecialPriceStrategy selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SpecialPriceStrategy record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SpecialPriceStrategy record);

    List<SpecialPriceStrategy> selectByEnterpriseId(Map param);

    List<Map> selectIdNameByEnterpriseId(Long enterpriseId);

}