package com.rograndec.feijiayun.chain.business.storage.displaycheck.dao;

import com.rograndec.feijiayun.chain.business.storage.displaycheck.entity.GoodsDisplayCheckDetail;

import java.util.List;

public interface GoodsDisplayCheckDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsDisplayCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsDisplayCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    GoodsDisplayCheckDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsDisplayCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsDisplayCheckDetail record);

    List<GoodsDisplayCheckDetail> selectByCheckId(Long id);

    int batchInsert(List<GoodsDisplayCheckDetail> goodsDisplayCheckDetails);
}