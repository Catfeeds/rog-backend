package com.rograndec.feijiayun.chain.business.goods.sale.dao;

import com.rograndec.feijiayun.chain.business.goods.sale.entity.SpecialPriceGoods;

import java.util.List;
import java.util.Map;

public interface SpecialPriceGoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SpecialPriceGoods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SpecialPriceGoods record);

    /**
     *
     * @mbg.generated
     */
    SpecialPriceGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SpecialPriceGoods record);

    SpecialPriceGoods selectByEnterpriseIdAndGoodsId(Map param);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SpecialPriceGoods record);

    int deleteByPrimaryStrategyIdByEnterpriseId(Map<String, Long> param);

    List<SpecialPriceGoods> selectByStrategyId(Map<String, Long> param);

    int batchInsert(List<SpecialPriceGoods> list);

    List<SpecialPriceGoods> selectByEnterpriseId(Map param);

    Long selectGoodsExists(Map param);

    Integer selectCountByEnterpriseId(Map param);

    int deleteExistsGoods(Map param);
}