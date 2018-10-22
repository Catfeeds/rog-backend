package com.rograndec.feijiayun.chain.business.retail.dailysettle.dao;

import com.rograndec.feijiayun.chain.business.retail.dailysettle.entity.DailySettleDetail;

import java.util.List;

public interface DailySettleDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DailySettleDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DailySettleDetail record);

    /**
     *
     * @mbg.generated
     */
    DailySettleDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DailySettleDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DailySettleDetail record);


    int batchInsert(List<DailySettleDetail> dailySettleDetails);
}