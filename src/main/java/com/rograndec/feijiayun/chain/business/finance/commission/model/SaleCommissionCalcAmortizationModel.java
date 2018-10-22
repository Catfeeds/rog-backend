package com.rograndec.feijiayun.chain.business.finance.commission.model;

import com.rograndec.feijiayun.chain.common.constant.CommissionMethod;
import com.rograndec.feijiayun.chain.common.constant.CommissionModeType;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提成计算公式
 * Created by zhaiwei on 2017/9/27.
 */
public class SaleCommissionCalcAmortizationModel {

    /**
     * 利润合计
     */
    private BigDecimal porfitAmount;

    /**
     * 利润率
     */
    private BigDecimal profitRate;

    /**
     * 金额
     */
    private BigDecimal realAmount;

    /**
     * 总数量
     */
    private BigDecimal quantity;

    /**
     * 提成策略
     */
    private SaleCommissionRule saleCommissionRule;

    /**
     * 提成公式

     按销售数量 -> 销售数量 ->全额 销售数量范围计提金额

     按销售数量 -> 销售数量 ->盈余 利润金额>0并且销售数量范围计提金额

     按照金额 -> 金额 -> 全额 销售金额的范围计提金额

     按照金额 -> 金额 -> 盈余 利润金额>0并且销售金额的范围计提金额

     按照金额 -> 比例 -> 全额  销售金额的范围计提金额比例

     按照金额 -> 比例 -> 盈余 利润金额>0并且 销售金额的范围计提金额比例

     按照利润 -> 金额 -> 盈余 利润金额>0并且利润金额的范围计提利润金额

     按照利润 -> 比例 -> 盈余 利润金额>0并且利润金额的范围计提利润金额比例

     按照利润率 -> 比例 -> 盈余 利润金额>0并且 利润率的范围计提利润比例 --
     * @return
     */
    public static BigDecimal calcAmortization(SaleCommissionCalcAmortizationModel calcAmortizationModel){

        SaleCommissionRule saleCommissionRule = calcAmortizationModel.getSaleCommissionRule();

        if(CollectionUtils.isEmpty(saleCommissionRule.getSaleCommissionRanges())){
            return BigDecimal.ZERO;
        }
        /**
         * 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
         */
        Integer method = saleCommissionRule.getMethod();

        /**
         * 方式（0-按金额；1-按比例 2-数量 3-利润率
         */
        Integer mode = saleCommissionRule.getMode();
        /**
         * 0-全额提成；1-盈余提成
         */
        Integer range = saleCommissionRule.getRange();

        BigDecimal bigDecimal = BigDecimal.ZERO;
        // 按销售数量 -> 销售数量 ->全额 销售数量范围计提金额
/*
        if(method == 0 && mode == 2 && range == 0){
*/
        if(method == CommissionMethod.SALESQUANTITY.getCode() && mode == CommissionModeType.QUANLITY.getCode() && range == 0){

            bigDecimal = useQuantityAndAmountAndFull(calcAmortizationModel.getQuantity()
                    , saleCommissionRule);

        }
/*
        else if(method == 0 && mode == 2 && range == 1){
*/
        else if(method == CommissionMethod.SALESQUANTITY.getCode() && mode == CommissionModeType.QUANLITY.getCode() && range == 1){

            // 按销售数量 -> 销售数量 ->盈余 利润金额>0并且销售数量范围计提金额
            bigDecimal = useQuantityAndAmountAndProfitAmount(calcAmortizationModel.getQuantity()
                    , saleCommissionRule
                    , calcAmortizationModel.getPorfitAmount());

        }
/*
        else if(method == 1 && mode == 0 && range == 0){
*/
        else if(method == CommissionMethod.SALEROOM.getCode() && mode == CommissionModeType.AMOUNT.getCode() && range == 0){

            //  按照金额 -> 金额 -> 全额 销售金额的范围计提金额
            bigDecimal = useAmountAndAmountAndFull(calcAmortizationModel.getRealAmount()
                    , saleCommissionRule
            );

        }else if(method == CommissionMethod.SALEROOM.getCode() && mode == CommissionModeType.AMOUNT.getCode() && range == 1){

       /* else if(method == 1 && mode == 0 && range == 1){*/

            // 按照金额 -> 金额 -> 盈余 利润金额>0并且销售金额的范围计提金额
            bigDecimal =  useAmountAndAmountAndProfitAmount(
                    calcAmortizationModel.getRealAmount()
                    ,saleCommissionRule
                    ,calcAmortizationModel.getPorfitAmount()
            );
        }else if(method == CommissionMethod.SALEROOM.getCode() && mode == CommissionModeType.RATIO.getCode() && range == 1){
       /* }else if(method == 1 && mode == 1 && range == 1){*/
            // 按照金额 -> 比例 -> 全额  销售金额的范围计提金额比例
            bigDecimal = useAmountAndRatioAndFull(
                    calcAmortizationModel.getRealAmount()
                    ,saleCommissionRule
            );
        }else if(method == CommissionMethod.SALEROOM.getCode() && mode == CommissionModeType.RATIO.getCode() && range == 0){
       /* }else if(method == 1 && mode == 1 && range == 0){*/
            //按照金额 -> 比例 -> 盈余 利润金额>0并且 销售金额的范围计提金额比例
            bigDecimal = useAmountAndRatioAndPorfitAmount(
                    calcAmortizationModel.getRealAmount()
                    ,saleCommissionRule
                    ,calcAmortizationModel.getPorfitAmount()
            );
        }
       /* else if(method == CommissionMethod.TOTALPROFIT.getCode() && mode == CommissionModeType.AMOUNT.getCode() && range == 1){
            bigDecimal = usePorfitAmountAndAmountAndPorfitAmount(
                    calcAmortizationModel.getPorfitAmount()
                    ,saleCommissionRule
            );
        }*/
        else if(method == CommissionMethod.TOTALPROFIT.getCode() && mode == CommissionModeType.AMOUNT.getCode() && range == 0){
            //按照利润 -> 金额 -> 全额提成 利润金额>0并且利润金额的范围计提利润金额
            bigDecimal = usePorfitAmountAndAmountAndPorfitAmount(
                    calcAmortizationModel.getPorfitAmount()
                    ,saleCommissionRule
            );
        }
      /*  else if(method == 2 && mode == 0 && range == 1){
            //  按照利润 -> 比例 -> 盈余 利润金额>0并且利润金额的范围计提利润金额比例
            bigDecimal = usePorfitAmountAndRatioAndPorfitAmount(
                    calcAmortizationModel.getPorfitAmount()
                    ,saleCommissionRule
            );
        }*/
        else if(method == CommissionMethod.TOTALPROFIT.getCode() && mode == CommissionModeType.RATIO.getCode() && range == 0){
    /*    else if(method == 2 && mode == 1 && range == 0){*/
            //  按照利润 -> 比例 -> 全额提成 利润金额>0并且利润金额的范围计提利润金额比例
            bigDecimal = usePorfitAmountAndRatioAndPorfitAmount(
                    calcAmortizationModel.getPorfitAmount()
                    ,saleCommissionRule
            );
        }
       /* else if(method == 3 && mode == 0 && range == 1){
            //按照利润率 -> 比例 -> 盈余 利润金额>0并且 利润率的范围计提利润比例
            bigDecimal = useProfitRateAndRatioAndPorfitAmount(
                    calcAmortizationModel.getProfitRate()
                    ,saleCommissionRule
                    ,calcAmortizationModel.getPorfitAmount()
            );
        }*/
        else if(method == CommissionMethod.PROFITDRAWING.getCode() && mode == CommissionModeType.PROFITREAT.getCode() && range == 0){
       /* else if(method == 3 && mode == 3 && range == 0){*/
            //按照利润率 -> 利润率 -> 全额 利润金额>0并且 利润率的范围计提利润比例
            bigDecimal = useProfitRateAndProfitAndPorfitAmount(
                    calcAmortizationModel.getProfitRate()
                    ,saleCommissionRule
                    ,calcAmortizationModel.getPorfitAmount()
            );
        }

        return bigDecimal;
    }

    /**
     * 按照利润率 -> 比例 -> 盈余 利润金额>0并且 利润率的范围计提利润比例
     */
    public static BigDecimal useProfitRateAndRatioAndPorfitAmount(BigDecimal profitRate,SaleCommissionRule saleCommissionRule,BigDecimal porfitAmount){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){

            List<SaleCommissionRange> saleCommissionRanges = saleCommissionRule.getSaleCommissionRanges();

            for(SaleCommissionRange saleCommissionRange : saleCommissionRanges){

                if(null == saleCommissionRange.getEnd()){

                    if(profitRate.compareTo(saleCommissionRange.getStart()) > 0){
                        BigDecimal multiply = saleCommissionRange.getRatio().multiply(porfitAmount);
                        if(multiply.compareTo(BigDecimal.ZERO) == 0) return multiply;
                        BigDecimal bigDecimal = multiply.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        return bigDecimal;
                    }
                }else {
                    if(profitRate.compareTo(saleCommissionRange.getStart()) > 0 && profitRate.compareTo(saleCommissionRange.getEnd()) <= 0){
                        BigDecimal multiply = saleCommissionRange.getRatio().multiply(porfitAmount);
                        if(multiply.compareTo(BigDecimal.ZERO) == 0) return multiply;
                        BigDecimal bigDecimal = multiply.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        return bigDecimal;

                    }
                }



            }

        }else {
            return BigDecimal.ZERO;
        }


        return BigDecimal.ZERO;
    }

    /**
     * 按照利润率 -> 利润率 -> 全 利润金额>0并且 利润率的范围计提利润比例
     */
    public static BigDecimal useProfitRateAndProfitAndPorfitAmount(BigDecimal profitRate,SaleCommissionRule saleCommissionRule,BigDecimal porfitAmount){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){

            List<SaleCommissionRange> saleCommissionRanges = saleCommissionRule.getSaleCommissionRanges();

            for(SaleCommissionRange saleCommissionRange : saleCommissionRanges){

                if(null == saleCommissionRange.getEnd()){

                    if(profitRate.compareTo(saleCommissionRange.getStart()) > 0){
                        BigDecimal multiply = saleCommissionRange.getRatio().multiply(porfitAmount);
                        if(multiply.compareTo(BigDecimal.ZERO) == 0) return multiply;
                        BigDecimal bigDecimal = multiply.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        return bigDecimal;
                    }
                }else {
                    if(profitRate.compareTo(saleCommissionRange.getStart()) > 0 && profitRate.compareTo(saleCommissionRange.getEnd()) <= 0){
                        BigDecimal multiply = saleCommissionRange.getRatio().multiply(porfitAmount);
                        if(multiply.compareTo(BigDecimal.ZERO) == 0) return multiply;
                        BigDecimal bigDecimal = multiply.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        return bigDecimal;

                    }
                }



            }

        }else {
            return BigDecimal.ZERO;
        }


        return BigDecimal.ZERO;
    }

    /**
     * 按照利润 -> 比例 -> 盈余 利润金额>0并且利润金额的范围计提利润金额比例
     */
    public static BigDecimal usePorfitAmountAndRatioAndPorfitAmount(BigDecimal porfitAmount,SaleCommissionRule saleCommissionRule){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){
            List<SaleCommissionRange> saleCommissionRanges = saleCommissionRule.getSaleCommissionRanges();
            for(SaleCommissionRange saleCommissionRange : saleCommissionRanges){

                if(null == saleCommissionRange.getEnd()){

                    if(porfitAmount.compareTo(saleCommissionRange.getStart()) > 0){
                        return saleCommissionRange.getRatio().divide(new BigDecimal(100),2).multiply(porfitAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
                    }

                }else {

                    if(porfitAmount.compareTo(saleCommissionRange.getStart()) > 0 && porfitAmount.compareTo(saleCommissionRange.getEnd()) <= 0 ){
                        return saleCommissionRange.getRatio().divide(new BigDecimal(100),2).multiply(porfitAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
                    }
                }


            }
            return BigDecimal.ZERO;
        }else {
            return BigDecimal.ZERO;
        }

    }

    /**
     * 按照利润 -> 金额 -> 盈余 利润金额>0并且利润金额的范围计提利润金额
     */
    public static BigDecimal usePorfitAmountAndAmountAndPorfitAmount(BigDecimal porfitAmount,SaleCommissionRule saleCommissionRule){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){

            List<SaleCommissionRange> saleCommissionRanges = saleCommissionRule.getSaleCommissionRanges();

            for(SaleCommissionRange saleCommissionRange : saleCommissionRanges){

                if(null == saleCommissionRange.getEnd()){
                    if(porfitAmount.compareTo(saleCommissionRange.getStart()) > 0){
                        return saleCommissionRange.getRatio().setScale(2,BigDecimal.ROUND_HALF_UP);
                    }
                }else {

                    if(porfitAmount.compareTo(saleCommissionRange.getStart()) > 0 && (porfitAmount.compareTo(saleCommissionRange.getEnd()) <= 0)){
                        return saleCommissionRange.getRatio().setScale(2,BigDecimal.ROUND_HALF_UP);
                    }
                }

            }
            return BigDecimal.ZERO;
        }else {
            return BigDecimal.ZERO;
        }

    }

    /**
     * 按照金额 -> 比例 -> 盈余 利润金额>0并且 销售金额的范围计提金额比例
     */
    public static BigDecimal useAmountAndRatioAndPorfitAmount(BigDecimal realAmount,SaleCommissionRule saleCommissionRule,BigDecimal porfitAmount){
        //利润金额大于0才给提成
        if(porfitAmount.compareTo(BigDecimal.ZERO) > 0){
            return useAmountAndRatioAndFull(realAmount,saleCommissionRule);
        }else {
            return BigDecimal.ZERO;
        }
    }



    /**
     * 按照金额 -> 比例 -> 全额  销售金额的范围计提金额比例
     */
    public static BigDecimal useAmountAndRatioAndFull(BigDecimal realAmount,SaleCommissionRule saleCommissionRule){
        List<SaleCommissionRange> saleCommissionRanges = saleCommissionRule.getSaleCommissionRanges();

        for(SaleCommissionRange saleCommissionRange : saleCommissionRanges){

            if(null == saleCommissionRange.getEnd()){

                if(realAmount.compareTo(saleCommissionRange.getStart()) > 0){
                    return saleCommissionRange.getRatio().divide(new BigDecimal(100),2).multiply(realAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
                }

            }else {

                if(realAmount.compareTo(saleCommissionRange.getStart()) > 0 && (realAmount.compareTo(saleCommissionRange.getEnd()) <= 0)){
                    return saleCommissionRange.getRatio().divide(new BigDecimal(100),2).multiply(realAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
                }
            }

        }
        return BigDecimal.ZERO;
    }

    /**
     * 按照金额 -> 金额 -> 盈余 利润金额>0并且销售金额的范围计提金额
     */
    public static BigDecimal useAmountAndAmountAndProfitAmount(BigDecimal realAmount,SaleCommissionRule saleCommissionRule,BigDecimal porfitAmount){
        //利润金额大于0才给提成
        if(porfitAmount.compareTo(BigDecimal.ZERO) > 0){
            return useAmountAndAmountAndFull(realAmount,saleCommissionRule);
        }else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 按照金额 -> 金额 -> 全额 销售金额的范围计提金额
     * @param realAmount
     * @param saleCommissionRule
     * @return
     */
    public static BigDecimal useAmountAndAmountAndFull(BigDecimal realAmount,SaleCommissionRule saleCommissionRule){
        //  按照金额 -> 金额 -> 全额 销售金额的范围计提金额
        List<SaleCommissionRange> saleCommissionRanges = saleCommissionRule.getSaleCommissionRanges();

        for(SaleCommissionRange saleCommissionRange : saleCommissionRanges){

            if(null == saleCommissionRange.getEnd()){
                if(realAmount.compareTo(saleCommissionRange.getStart()) > 0){
                    return saleCommissionRange.getRatio();
                }
            }else {
                if(realAmount.compareTo(saleCommissionRange.getStart()) > 0 && (realAmount.compareTo(saleCommissionRange.getEnd()) <= 0)){
                    return saleCommissionRange.getRatio();
                }
            }


        }
        return BigDecimal.ZERO;
    }

    /**
     *  按销售数量 -> 销售数量 ->盈余 利润金额>0并且销售数量范围计提金额
     */
    public static BigDecimal useQuantityAndAmountAndProfitAmount(BigDecimal quantity,SaleCommissionRule saleCommissionRule,BigDecimal porfitAmount){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){
            return useQuantityAndAmountAndFull(quantity,saleCommissionRule);
        }else {
            return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     *  按销售数量 -> 销售数量 ->全额 销售数量范围计提金额
     * @param quantity
     * @param saleCommissionRule
     * @return
     */
    public static BigDecimal useQuantityAndAmountAndFull(BigDecimal quantity,SaleCommissionRule saleCommissionRule){

        List<SaleCommissionRange> saleCommissionRanges = saleCommissionRule.getSaleCommissionRanges();

        for(SaleCommissionRange saleCommissionRange : saleCommissionRanges){

            if(null == saleCommissionRange.getEnd()){
                if(quantity.compareTo(saleCommissionRange.getStart()) > 0){
                    return saleCommissionRange.getRatio();
                }
            }else {
                if(quantity.compareTo(saleCommissionRange.getStart()) > 0 && quantity.compareTo(saleCommissionRange.getEnd()) <= 0){
                    return saleCommissionRange.getRatio();
                }
            }

        }

        return BigDecimal.ZERO;
    }



    public SaleCommissionCalcAmortizationModel(BigDecimal porfitAmount, BigDecimal profitRate, BigDecimal realAmount, BigDecimal quantity, SaleCommissionRule saleCommissionRule) {
        this.porfitAmount = porfitAmount;
        this.profitRate = profitRate;
        this.realAmount = realAmount;
        this.quantity = quantity;
        this.saleCommissionRule = saleCommissionRule;
    }

    public BigDecimal getPorfitAmount() {
        return porfitAmount;
    }

    public void setPorfitAmount(BigDecimal porfitAmount) {
        this.porfitAmount = porfitAmount;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public SaleCommissionRule getSaleCommissionRule() {
        return saleCommissionRule;
    }

    public void setSaleCommissionRule(SaleCommissionRule saleCommissionRule) {
        this.saleCommissionRule = saleCommissionRule;
    }
}
