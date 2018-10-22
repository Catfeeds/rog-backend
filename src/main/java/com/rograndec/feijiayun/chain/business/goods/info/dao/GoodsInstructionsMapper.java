package com.rograndec.feijiayun.chain.business.goods.info.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsInstructions;

public interface GoodsInstructionsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsInstructions record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsInstructions record);

    /**
     *
     * @mbg.generated
     */
    GoodsInstructions selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsInstructions record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsInstructions record);

    /**
     *
     * @mbg.generated
     */
    int updateByGoodsId(GoodsInstructions record);

    GoodsInstructions getByGoodsId(Long goodsId);
}