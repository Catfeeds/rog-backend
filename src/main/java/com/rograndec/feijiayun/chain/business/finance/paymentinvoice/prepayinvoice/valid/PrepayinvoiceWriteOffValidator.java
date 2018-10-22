package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceSaveVO;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.constant.status.FinanceReconciliationStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class PrepayinvoiceWriteOffValidator implements ConstraintValidator<PrepayinvoiceWriteOffVaild, Long> {
    private Logger logger = LoggerFactory.getLogger(PrepayinvoiceWriteOffValidator.class);

    @Autowired
    private PrepayInvoiceMapper prepayInvoiceMapper;


    @Override
    public void initialize(PrepayinvoiceWriteOffVaild prepayinvoiceWriteOffVaild) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(value);
        if(FinancePaymentStatus.PRE_PAYMENT != prepayInvoice.getStatus() || FinanceReconciliationStatus.WARITE_OF == prepayInvoice.getAccountStatus()){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"单据状态为"+FinancePaymentStatus.getStatusDesc(prepayInvoice.getStatus())+"不允许冲销");
        }

        return true;
    }



}
