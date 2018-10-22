package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucherDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentVoucherDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PaymentVoucherDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PaymentVoucherDetail record);

    /**
     *
     * @mbg.generated
     */
    PaymentVoucherDetail selectByPrimaryKey(@Param("id") Long id, @Param("enterpriseId")Long enterpriseId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PaymentVoucherDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PaymentVoucherDetail record);

    List<PaymentVoucherDetail> queryByVoucherId(@Param("voucherId") Long voucherId, @Param("enterpriseId") Long enterpriseId);

    int updateStatusByVoucherId(PaymentVoucherDetail record);
}