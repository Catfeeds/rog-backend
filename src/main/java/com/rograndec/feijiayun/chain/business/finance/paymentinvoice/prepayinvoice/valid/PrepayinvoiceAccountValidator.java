package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
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
public class PrepayinvoiceAccountValidator implements ConstraintValidator<PrepayinvoiceAccountVaild, PerpayInvoiceAccountParamListVO> {
    private Logger logger = LoggerFactory.getLogger(PrepayinvoiceAccountValidator.class);

    @Autowired
    private PrepayInvoiceMapper prepayInvoiceMapper;
    @Autowired
    private PrepayInvoiceDetailMapper prepayInvoiceDetailMapper;


    @Override
    public void initialize(PrepayinvoiceAccountVaild prepayinvoiceAccountVaild) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(PerpayInvoiceAccountParamListVO value, ConstraintValidatorContext context) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());

        BigDecimal accountQuantity = BigDecimal.ZERO;

        List<PerpayInvoiceAccountParamVO> perpayInvoiceAccountParamVOs = value.getPerpayInvoiceAccountParamVOs();

        for(PerpayInvoiceAccountParamVO prepay : perpayInvoiceAccountParamVOs){

            List<PerpayInvoiceAccountDetailParamVO> perpayInvoiceAccountDetailParamVOS = prepay.getPerpayInvoiceAccountDetailParamVOS();

            for(PerpayInvoiceAccountDetailParamVO pd : perpayInvoiceAccountDetailParamVOS){

                accountQuantity = accountQuantity.add(pd.getAccountQuantity());
            }

        }

        if(accountQuantity.compareTo(BigDecimal.ZERO) == 0){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"对账数量总和为0");
        }

        for(PerpayInvoiceAccountParamVO perpayInvoiceAccountParamVO : perpayInvoiceAccountParamVOs){

            Long id = perpayInvoiceAccountParamVO.getId();

            List<PerpayInvoiceAccountDetailParamVO> perpayInvoiceAccountDetailParamVOS = perpayInvoiceAccountParamVO.getPerpayInvoiceAccountDetailParamVOS();
            for(PerpayInvoiceAccountDetailParamVO perpayInvoiceAccountDetailParamVO : perpayInvoiceAccountDetailParamVOS){
                Long orderShelfId = perpayInvoiceAccountDetailParamVO.getOrderShelfId();
                if(null == orderShelfId){
                    /**
                     * 没有传递默认认为 批量对账 id保存的是预付发票id
                     */
                    PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(id);

                    checkAccountQuantity(prepayInvoice.getId(),accountQuantity);

                    if(FinanceReconciliationStatus.RECONCILED == prepayInvoice.getAccountStatus() || FinanceReconciliationStatus.WARITE_OF == prepayInvoice.getAccountStatus()){

                        throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"预付发票已冲销或者已对账不允许再次对账");
                    }
                }else {
                    PrepayInvoiceDetail prepayInvoiceDetail = prepayInvoiceDetailMapper.selectByPrimaryKey(id);

                    checkAccountQuantity(prepayInvoiceDetail.getInvoiceId(),accountQuantity);

                    if(FinanceReconciliationStatus.RECONCILED == prepayInvoiceDetail.getAccountStatus() || FinanceReconciliationStatus.WARITE_OF == prepayInvoiceDetail.getAccountStatus()){

                        throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"预付发票已冲销或者已对账不允许再次对账");
                    }

                }
            }

        }


        return true;
    }


    public void checkAccountQuantity(Long prepayInvoiceId, BigDecimal accountQuantityTotal){

        List<PrepayInvoiceDetail> prepayInvoiceDetails = prepayInvoiceDetailMapper.selectByInvoiceId(prepayInvoiceId);

        /**
         * 发票所有未清数量总和
         */
        BigDecimal unClearQuantityTotal = prepayInvoiceDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountQuantity()).map(PrepayInvoiceDetail::getUnAccountQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);

        if(accountQuantityTotal.compareTo(unClearQuantityTotal) > 0){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"对账总数量大于发票为清数量总和");
        }
    }



}
