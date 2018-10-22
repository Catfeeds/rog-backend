package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettleDetail;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo.RetailDailySettleDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetailDailySettleDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RetailDailySettleDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RetailDailySettleDetail record);

    /**
     *
     * @mbg.generated
     */
    RetailDailySettleDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RetailDailySettleDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RetailDailySettleDetail record);

    List<RetailDailySettleDetail> selectBySettleId(Long settleId);

    List<RetailDailySettleDetailVO> selectRetailDailySettleDetailVOBySettleId(Long settleId);

    void updateStatus(@Param("settleId") Long settleId, @Param("status") Integer status);
}