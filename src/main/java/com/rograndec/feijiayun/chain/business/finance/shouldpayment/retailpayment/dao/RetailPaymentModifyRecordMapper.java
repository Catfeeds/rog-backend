package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.dao;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity.RetailPaymentModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.RetailPaymentModifyRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetailPaymentModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RetailPaymentModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RetailPaymentModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    RetailPaymentModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RetailPaymentModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RetailPaymentModifyRecord record);

    List<RetailPaymentModifyRecordVO> selectByPageParam(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("keyId") Long id);

    Integer selectCountByKeyId(Long keyId);

    List<RetailPaymentModifyRecord> selectByKeyId(Long keyId);
}