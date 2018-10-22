package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettleModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo.RetailDailySettleModifyRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetailDailySettleModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RetailDailySettleModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RetailDailySettleModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    RetailDailySettleModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RetailDailySettleModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RetailDailySettleModifyRecord record);

    List<RetailDailySettleModifyRecordVO> selectBySettleIdByPageParam(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("settleId") Long id);

    Integer selectCountBySettleId(Long settleId);

    List<RetailDailySettleModifyRecord> selectByKeyId(Long keyId);
}