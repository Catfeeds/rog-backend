package com.rograndec.feijiayun.chain.business.goods.price.dao;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjustDetail;

import java.util.List;
import java.util.Map;

public interface PriceAdjustDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    int deleteByAdjustId(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PriceAdjustDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PriceAdjustDetail record);

    /**
     *
     * @mbg.generated
     */
    PriceAdjustDetail selectByPrimaryKey(Long id);

    PriceAdjustDetail selectByPriceAdjustIdAndGoodsId(Map<String,Object> map);

    List<PriceAdjustDetail> selectByPriceAdjustId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PriceAdjustDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PriceAdjustDetail record);
}