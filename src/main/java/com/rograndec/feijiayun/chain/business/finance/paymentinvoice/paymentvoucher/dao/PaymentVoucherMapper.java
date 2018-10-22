package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucher;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.PaymentVoucherListVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.PaymentVoucherTotalVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.PaymentVoucherVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.RequestVoucherParamVO;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.ReceivableDocumentsPageVO;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.ReceivableDocumentsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PaymentVoucherMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PaymentVoucher record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PaymentVoucher record);

    /**
     *
     * @mbg.generated
     */
    PaymentVoucher selectByPrimaryKey(@Param("id") Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PaymentVoucher record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PaymentVoucher record);

    List<ReceivableDocumentsPageVO> selectWithUnclear(Map<String, Object> map);


    List<PaymentVoucherListVO> getPaymentVoucherListPage(@Param("paramVO") RequestVoucherParamVO requestVoucherParamVO);

    Integer getPaymentVoucherListPageCount(@Param("paramVO") RequestVoucherParamVO requestVoucherParamVO);

    PaymentVoucherTotalVO getPaymentVoucherListTotal(@Param("paramVO") RequestVoucherParamVO requestVoucherParamVO);

    PaymentVoucherVO getPaymentVoucherDetailById(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);


    Integer selectTotalRecord(Map<String, Object> map);

    ReceivableDocumentsVO selectAmountTotal(Map<String, Object> map);




}