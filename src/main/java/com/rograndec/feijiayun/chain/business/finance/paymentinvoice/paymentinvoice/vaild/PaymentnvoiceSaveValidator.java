package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vaild;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo.PaymentInvoiceDetailSaveVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo.PaymentInvoiceSaveVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceInStoreResponseVO;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class PaymentnvoiceSaveValidator implements ConstraintValidator<PaymentinvoiceSaveVaild, PaymentInvoiceSaveVO> {
    private Logger logger = LoggerFactory.getLogger(PaymentnvoiceSaveValidator.class);

    @Autowired
    private PaymentInvoiceMapper paymentInvoiceMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    public void initialize(PaymentinvoiceSaveVaild prepayinvoiceSaveVaild) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(PaymentInvoiceSaveVO value, ConstraintValidatorContext context) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());

        Date billDate = value.getBillDate();
        String date = DateUtils.getDate(billDate);
        commonComponent.validationAccountingDate(date,userVO);

        if(null != value.getId()){
            String reason = value.getReason();
            if(StringUtils.isEmpty(reason))
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"修改原因必须传递");
            /**
             * 表示是修改
             */

            Long id = value.getId();

            PaymentInvoice paymentInvoice = paymentInvoiceMapper.selectByPrimaryKey(id);
            if(FinancePaymentStatus.WARITE_OF == paymentInvoice.getStatus()){
                /**
                 * 如果是已冲销不允许修改
                 */
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"单据为已冲销,不允许修改");
            }

        }else {

            /**
             * 数据类型,默认为价格清单:0,1:企业,2:供应商
             */
            Integer type = value.getType();

            if(1 != type && 2 != type){
                throw new PrepayInvoiceBizException( PrepayInvoiceBizException.VALUE_CHECK,"错误的供应商类型");
            }

            List<PaymentInvoiceDetailSaveVO> paymentInvoiceDetails = value.getPaymentInvoiceDetails();

            if(CollectionUtils.isEmpty(paymentInvoiceDetails)){
                throw new PrepayInvoiceBizException( PrepayInvoiceBizException.VALUE_CHECK,"应付发票明细行未空");
            }

            BigDecimal accountQuantity = paymentInvoiceDetails.stream().filter(Objects::nonNull)
                    .filter(c -> null != c.getAccountQuantity()).map(PaymentInvoiceDetailSaveVO::getAccountQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

            if(accountQuantity.compareTo(BigDecimal.ZERO) == 0){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"对账数量总和为0");
            }

        }

        return true;
    }



}
