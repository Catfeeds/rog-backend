package com.rograndec.feijiayun.chain.business.finance.commission.vaild;

import com.rograndec.feijiayun.chain.business.finance.commission.dao.SaleCommissionMapper;
import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommission;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.constant.status.SaleCommissionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class SaleCommissionWriteOffValidator implements ConstraintValidator<SaleCommissionWriteOffVaild, Long> {
    private Logger logger = LoggerFactory.getLogger(SaleCommissionWriteOffValidator.class);

    @Autowired
    private SaleCommissionMapper saleCommissionMapper;


    @Override
    public void initialize(SaleCommissionWriteOffVaild prepayinvoiceWriteOffVaild) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        SaleCommission saleCommission = saleCommissionMapper.selectByPrimaryKey(value);
        if(SaleCommissionStatus.WARITE_OF == saleCommission.getStatus()){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"单据状态为已冲销不允许再次冲销");
        }

        return true;
    }



}
