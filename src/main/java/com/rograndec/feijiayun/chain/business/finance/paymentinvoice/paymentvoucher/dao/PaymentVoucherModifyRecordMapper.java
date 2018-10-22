package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucherModifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentVoucherModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PaymentVoucherModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PaymentVoucherModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    PaymentVoucherModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PaymentVoucherModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PaymentVoucherModifyRecord record);

    List<PaymentVoucherModifyRecord> getPaymentVoucherModifyRecord(@Param("enterpriseId")Long enterpriseId,
                                                                   @Param("paymentId")Long paymentId,
                                                                   @Param("start")Integer start,
                                                                   @Param("pageSize")Integer pageSize);

    Integer getPaymentVoucherModifyRecordCount(@Param("enterpriseId")Long enterpriseId, @Param("paymentId")Long paymentId);

}