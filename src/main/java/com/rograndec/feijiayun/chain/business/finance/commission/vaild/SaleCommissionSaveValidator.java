package com.rograndec.feijiayun.chain.business.finance.commission.vaild;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.finance.commission.dao.SaleCommissionMapper;
import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommission;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.NoSaleCommissionDetailResponseVO;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.NoSaleCommissionResponseTotalVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.status.SaleCommissionStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class SaleCommissionSaveValidator implements ConstraintValidator<SaleCommissionSaveVaild, NoSaleCommissionResponseTotalVO> {
    private Logger logger = LoggerFactory.getLogger(SaleCommissionSaveValidator.class);

    @Autowired
    private SaleCommissionMapper saleCommissionMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    public void initialize(SaleCommissionSaveVaild noSaleCommissionVaild) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(NoSaleCommissionResponseTotalVO value, ConstraintValidatorContext context) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());

        /**
         * 提成日期
         */
        Date commissionDate = value.getCommissionDate();
        if(null == commissionDate){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"提成日期不能为空");
        }

        String date = DateUtils.getDate(commissionDate);
        commonComponent.validationAccountingDate(date,userVO);

        /**
         * 提成人员ID
         */
        Long commissionManId = value.getCommissionManId();
        if(null == commissionManId){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"提成人员不能为空");
        }

        /**
         * 营业人员ID
         */
        Long clerkId = value.getClerkId();
        if(null == clerkId){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"营业人员不能为空");
        }

        /**
         * 新增
         */
        if(null == value.getId()){

            /**
             * 门店id
             */
            Long branchId = value.getBranchId();
            if(null == branchId){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"门店不能为空");
            }

            /**
             * 应提金额合计
             */
            BigDecimal amountTotal = value.getAmountTotal();
            if(null == amountTotal){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"应提金额不能为空");
            }


            /**
             * 实提金额合计
             */
            BigDecimal realAmountTotal = value.getRealAmountTotal();

            if(null == realAmountTotal){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"实提金额不能为空");
            }

            /**
             * 销售日期从
             */
            Date saleDateFrom = value.getSaleDateFrom();
            if(null == saleDateFrom){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"销售日期开始时间不能为空");
            }

            /**
             * 销售日期至
             */
            Date saleDateTo = value.getSaleDateTo();
            if(null == saleDateTo){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"销售日期结束时间不能为空");
            }

            List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS = value.getResponseNoSaleRoyaltyDetailVOS();

            if(CollectionUtils.isEmpty(responseNoSaleRoyaltyDetailVOS)){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"新增提成,提成明细为空");
            }

            for(NoSaleCommissionDetailResponseVO nsro : responseNoSaleRoyaltyDetailVOS){

                /**
                 * 基础单据ID
                 */
                Long saleId = nsro.getSaleId();
                if(null == saleId){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"销售单据不能为空");
                }


                /**
                 * 商品ID
                 */

                if(null == nsro.getGoodsId()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"商品不能为空");
                }

                /**
                 * 总数量
                 */
                if(null == nsro.getQuantity()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"数量不能为空");
                }



                /**
                 * 应提金额
                 */
                if(null == nsro.getCommissionAmount()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"应提金额不能为空");
                }


                /**
                 * 金额（整单优惠前金额）
                 */
                if(null == nsro.getAmount()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"销售金额不能为空");
                }


                if(null == nsro.getCostAmount()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"成本金额不能为空");
                }


                /**
                 * 利润总额
                 */
                if(null == nsro.getProfit()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"利润金额不能为空");
                }

                /**
                 * 利润率
                 */
                if(null == nsro.getProfitRate()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"利润率不能为空");
                }


                /**
                 * 策率ID
                 */
                if(null == nsro.getStrategyId()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"提成策略不能为空");
                }

                /**
                 * 策略名称
                 */
                if(null == nsro.getCommissionStrategy()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"提成策略不能为空");
                }


                /**
                 * 行号
                 */
                if(null == nsro.getLineNum()){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"行号不能为空");
                }

                if(CollectionUtils.isEmpty(nsro.getSaleDtlIds())){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"销售单明细集合");
                }
            }
        }else {

            SaleCommission saleCommission = saleCommissionMapper.selectByPrimaryKey(value.getId());
            if(SaleCommissionStatus.WARITE_OF == saleCommission.getStatus()){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"单据状态为已冲销不允许修改");
            }
        }

        return true;
    }



}
