package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceSaveVO;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.status.FinanceReconciliationStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class PrepayinvoiceSaveValidator implements ConstraintValidator<PrepayinvoiceSaveVaild, PrepayInvoiceSaveVO> {
    private Logger logger = LoggerFactory.getLogger(PrepayinvoiceSaveValidator.class);

    @Autowired
    private PrepayInvoiceMapper prepayInvoiceMapper;
    @Autowired
    private PrepayInvoiceDetailMapper prepayInvoiceDetailMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    public void initialize(PrepayinvoiceSaveVaild prepayinvoiceSaveVaild) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(PrepayInvoiceSaveVO value, ConstraintValidatorContext context) {

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

            PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(id);
            if(FinanceReconciliationStatus.WARITE_OF == prepayInvoice.getAccountStatus()){
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


        }

        return true;
    }



}
