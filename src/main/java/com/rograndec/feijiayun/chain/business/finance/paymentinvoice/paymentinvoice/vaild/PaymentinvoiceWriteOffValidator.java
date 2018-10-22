package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vaild;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.constant.status.FinanceReconciliationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class PaymentinvoiceWriteOffValidator implements ConstraintValidator<PaymentinvoiceWriteOffVaild, Long> {
    private Logger logger = LoggerFactory.getLogger(PaymentinvoiceWriteOffValidator.class);

    @Autowired
    private PaymentInvoiceMapper paymentInvoiceMapper;


    @Override
    public void initialize(PaymentinvoiceWriteOffVaild prepayinvoiceWriteOffVaild) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        PaymentInvoice paymentInvoice = paymentInvoiceMapper.selectByPrimaryKey(value);
        if(FinancePaymentStatus.PRE_PAYMENT != paymentInvoice.getStatus()){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"单据状态为"+FinancePaymentStatus.getStatusDesc(paymentInvoice.getStatus())+"不允许冲销");
        }

        return true;
    }



}
