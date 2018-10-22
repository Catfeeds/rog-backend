package com.rograndec.feijiayun.chain.business.retail.payment.dao;

import com.rograndec.feijiayun.chain.business.retail.payment.entity.SalePaymentDetail;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.PaymentVO;

public interface SalePaymentDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SalePaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SalePaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    SalePaymentDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SalePaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SalePaymentDetail record);

    /**
     * @Description: TODO根据paymentId查询PaymentVO
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月21日 上午10:49:47 
     * @param id
     * @return 
     * @return PaymentVO
     */
	PaymentVO selectPaymentVOByPaymentId(Long id);
}