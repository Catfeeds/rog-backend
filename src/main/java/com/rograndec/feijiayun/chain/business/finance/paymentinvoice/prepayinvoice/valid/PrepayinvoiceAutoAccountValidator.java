package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PerpayInvoiceAccountDetailParamVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PerpayInvoiceAccountParamListVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PerpayInvoiceAccountParamVO;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class PrepayinvoiceAutoAccountValidator implements ConstraintValidator<PrepayinvoiceAutpAccountVaild, PerpayInvoiceAccountParamVO> {
    private Logger logger = LoggerFactory.getLogger(PrepayinvoiceAutoAccountValidator.class);

    @Autowired
    private PrepayInvoiceMapper prepayInvoiceMapper;

    @Override
    public void initialize(PrepayinvoiceAutpAccountVaild prepayinvoiceAccountVaild) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(PerpayInvoiceAccountParamVO value, ConstraintValidatorContext context) {


        Long id = value.getId();

        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(id);

        if(FinanceReconciliationStatus.RECONCILED == prepayInvoice.getAccountStatus() || FinanceReconciliationStatus.WARITE_OF == prepayInvoice.getAccountStatus()){

            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"预付发票已冲销或者已对账不允许再次对账");
        }



        return true;
    }




}
