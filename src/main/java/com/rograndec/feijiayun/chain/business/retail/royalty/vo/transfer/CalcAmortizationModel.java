package com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer;

import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategy;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategyDetail;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 提成计算公式
 * Created by zhaiwei on 2017/9/27.
 */
public class CalcAmortizationModel {

    /**
     * 利润合计
     */
    private BigDecimal porfitAmount;

    /**
     * 利润率
     */
    private BigDecimal profitRate;

    /**
     * 金额（整单优惠前金额）
     */
    private BigDecimal realAmount;

    /**
     * 总数量
     */
    private BigDecimal quantity;

    /**
     * 提成策略
     */
    private CommissionStrategy royaltyStrategy;

    private List<CommissionStrategyDetail> royaltyStrategyDetails;

    private Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap;

    /**
     * 提成公式

     按销售数量 -> 金额 ->全额 销售数量范围计提金额

     按销售数量 -> 金额 ->盈余 利润金额>0并且销售数量范围计提金额

     按照金额 -> 金额 -> 全额 销售金额的范围计提金额

     按照金额 -> 金额 -> 盈余 利润金额>0并且销售金额的范围计提金额

     按照金额 -> 比例 -> 全额  销售金额的范围计提金额比例

     按照金额 -> 比例 -> 盈余 利润金额>0并且 销售金额的范围计提金额比例

     按照利润 -> 金额 -> 盈余 利润金额>0并且利润金额的范围计提利润金额

     按照利润 -> 比例 -> 盈余 利润金额>0并且利润金额的范围计提利润金额比例

     按照利润率 -> 比例 -> 盈余 利润金额>0并且 利润率的范围计提利润比例 --
     * @return
     */
    public static BigDecimal calcAmortization(CalcAmortizationModel calcAmortizationModel){
        CommissionStrategy royaltyStrategy = calcAmortizationModel.getRoyaltyStrategy();
        /**
         * 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
         */
        Integer method = royaltyStrategy.getMethod();
        /**
         * 方式（0-按比例；1-按金额
         */
        Integer mode = royaltyStrategy.getMode();
        /**
         * 0-全额提成；1-盈余提成
         */
        Integer range = royaltyStrategy.getRange();
        BigDecimal bigDecimal = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        // 按销售数量 -> 金额 ->全额 销售数量范围计提金额
        if(method == 0 && mode == 1 && range == 0){
            bigDecimal = useQuantityAndAmountAndFull(calcAmortizationModel.getQuantity()
                    , calcAmortizationModel.getRoyaltyStrategyDetailMap());
        }else if(method == 0 && mode == 1 && range == 1){
            // 按销售数量 -> 金额 ->盈余 利润金额>0并且销售数量范围计提金额
            bigDecimal = useQuantityAndAmountAndProfitAmount(calcAmortizationModel.getQuantity()
                    , calcAmortizationModel.getRoyaltyStrategyDetailMap()
                    , calcAmortizationModel.getPorfitAmount());
            return bigDecimal;
        }else if(method == 1 && mode == 1 && range == 0){
            //  按照金额 -> 金额 -> 全额 销售金额的范围计提金额
            bigDecimal = useAmountAndAmountAndFull(calcAmortizationModel.getRealAmount()
                    , calcAmortizationModel.getRoyaltyStrategyDetailMap()
            );
        }else if(method == 1 && mode == 1 && range == 1){
            // 按照金额 -> 金额 -> 盈余 利润金额>0并且销售金额的范围计提金额
            bigDecimal =  useAmountAndAmountAndProfitAmount(
                    calcAmortizationModel.getRealAmount()
                    ,calcAmortizationModel.getRoyaltyStrategyDetailMap()
                    ,calcAmortizationModel.getPorfitAmount()
            );
        }else if(method == 1 && mode == 0 && range == 1){
            // 按照金额 -> 比例 -> 全额  销售金额的范围计提金额比例
            bigDecimal = useAmountAndRatioAndFull(
                    calcAmortizationModel.getRealAmount()
                    ,calcAmortizationModel.getRoyaltyStrategyDetailMap()
            );
        }else if(method == 1 && mode == 0 && range == 0){
            //按照金额 -> 比例 -> 盈余 利润金额>0并且 销售金额的范围计提金额比例
            bigDecimal = useAmountAndRatioAndPorfitAmount(
                    calcAmortizationModel.getRealAmount()
                    ,calcAmortizationModel.getRoyaltyStrategyDetailMap()
                    ,calcAmortizationModel.getPorfitAmount()
            );
        }else if(method == 2 && mode == 1 && range == 1){
            //按照利润 -> 金额 -> 盈余 利润金额>0并且利润金额的范围计提利润金额
            bigDecimal = usePorfitAmountAndAmountAndPorfitAmount(
                    calcAmortizationModel.getPorfitAmount()
                    ,calcAmortizationModel.getRoyaltyStrategyDetailMap()
            );
        }else if(method == 2 && mode == 0 && range == 1){
            //  按照利润 -> 比例 -> 盈余 利润金额>0并且利润金额的范围计提利润金额比例
            bigDecimal = usePorfitAmountAndRatioAndPorfitAmount(
                    calcAmortizationModel.getPorfitAmount()
                    ,calcAmortizationModel.getRoyaltyStrategyDetailMap()
            );
        }else if(method == 3 && mode == 0 && range == 1){
            //按照利润率 -> 比例 -> 盈余 利润金额>0并且 利润率的范围计提利润比例
            bigDecimal = useProfitRateAndRatioAndPorfitAmount(
                    calcAmortizationModel.getProfitRate()
                    ,calcAmortizationModel.getRoyaltyStrategyDetailMap()
                    ,calcAmortizationModel.getPorfitAmount()
            );
        }

        return bigDecimal;
    }

    /**
     * 按照利润率 -> 比例 -> 盈余 利润金额>0并且 利润率的范围计提利润比例
     */
    public static BigDecimal useProfitRateAndRatioAndPorfitAmount(BigDecimal profitRate,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap,BigDecimal porfitAmount){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){

            profitRate = profitRate.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal ratio = getRatio(profitRate, royaltyStrategyDetailMap);
            BigDecimal multiply = porfitAmount.multiply(ratio);
            if(multiply.compareTo(BigDecimal.ZERO) == 0){
                return BigDecimal.ZERO;
            }
            BigDecimal bigDecimal = multiply.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return bigDecimal;
        }else {
            return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

    }

    /**
     * 按照利润 -> 比例 -> 盈余 利润金额>0并且利润金额的范围计提利润金额比例
     */
    public static BigDecimal usePorfitAmountAndRatioAndPorfitAmount(BigDecimal porfitAmount,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){
            BigDecimal ratio = getRatio(porfitAmount, royaltyStrategyDetailMap);
            BigDecimal bigDecimal = porfitAmount.multiply(ratio).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            return bigDecimal;
        }else {
            return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

    }

    /**
     * 按照利润 -> 金额 -> 盈余 利润金额>0并且利润金额的范围计提利润金额
     */
    public static BigDecimal usePorfitAmountAndAmountAndPorfitAmount(BigDecimal porfitAmount,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){
            BigDecimal ratio = getRatio(porfitAmount, royaltyStrategyDetailMap);
            return ratio;
        }else {
            return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

    }

    /**
     * 按照金额 -> 比例 -> 盈余 利润金额>0并且 销售金额的范围计提金额比例
     */
    public static BigDecimal useAmountAndRatioAndPorfitAmount(BigDecimal realAmount,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap,BigDecimal porfitAmount){
        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){
            return useAmountAndRatioAndFull(realAmount,royaltyStrategyDetailMap);
        }else {
            return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
    }



    /**
     * 按照金额 -> 比例 -> 全额  销售金额的范围计提金额比例
     */
    public static BigDecimal useAmountAndRatioAndFull(BigDecimal realAmount,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap){
        BigDecimal ratio = getRatio(realAmount, royaltyStrategyDetailMap);
        BigDecimal bigDecimal = realAmount.multiply(ratio).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal;
    }

    /**
     * 按照金额 -> 金额 -> 盈余 利润金额>0并且销售金额的范围计提金额
     */
    public static BigDecimal useAmountAndAmountAndProfitAmount(BigDecimal realAmount,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap,BigDecimal porfitAmount){
        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){
            return useAmountAndAmountAndFull(realAmount,royaltyStrategyDetailMap);
        }else {
            return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * 按照金额 -> 金额 -> 全额 销售金额的范围计提金额
     * @param realAmount
     * @param royaltyStrategyDetailMap
     * @return
     */
    public static BigDecimal useAmountAndAmountAndFull(BigDecimal realAmount,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap){
        //  按照金额 -> 金额 -> 全额 销售金额的范围计提金额
        BigDecimal ratio = getRatio(realAmount, royaltyStrategyDetailMap);
        return ratio;
    }

    /**
     *  按销售数量 -> 金额 ->盈余 利润金额>0并且销售数量范围计提金额
     */
    public static BigDecimal useQuantityAndAmountAndProfitAmount(BigDecimal quantity,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap,BigDecimal porfitAmount){

        //利润金额大于0才给提成
        if(porfitAmount.compareTo(new BigDecimal(0)) > 0){
            return useQuantityAndAmountAndFull(quantity,royaltyStrategyDetailMap);
        }else {
            return new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     *  按销售数量 -> 金额 ->全额 销售数量范围计提金额
     * @param quantity
     * @param royaltyStrategyDetailMap
     * @return
     */
    public static BigDecimal useQuantityAndAmountAndFull(BigDecimal quantity,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap){

        BigDecimal ratio = getRatio(quantity, royaltyStrategyDetailMap);
        return ratio;
    }

    public static BigDecimal getRatio(BigDecimal key ,Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap){
        TreeMap<String,BigDecimal> margin = new TreeMap<>();
        Set<BigDecimal> bigDecimals = royaltyStrategyDetailMap.keySet();
        for(BigDecimal a : bigDecimals){
            BigDecimal subtract = key.subtract(a);
            if(subtract.compareTo(new BigDecimal(0)) >= 0 ){
                margin.put(subtract.toString(),royaltyStrategyDetailMap.get(a));
            }
        }

        if(!CollectionUtils.isEmpty(margin)){
            BigDecimal bigDecimal = margin.get(margin.firstKey());
            return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        }


        return BigDecimal.ZERO;
    }

    public CalcAmortizationModel(BigDecimal porfitAmount, BigDecimal profitRate, BigDecimal realAmount, BigDecimal quantity, CommissionStrategy royaltyStrategy, List<CommissionStrategyDetail> royaltyStrategyDetails) {
        this.porfitAmount = porfitAmount;
        this.profitRate = profitRate;
        this.realAmount = realAmount;
        this.quantity = quantity;
        this.royaltyStrategy = royaltyStrategy;
        this.royaltyStrategyDetails = royaltyStrategyDetails;
        Map<BigDecimal,BigDecimal> royaltyStrategyDetailMap = new HashMap<>();
        for(CommissionStrategyDetail royaltyStrategyDetail : royaltyStrategyDetails){
            royaltyStrategyDetailMap.put(
                    royaltyStrategyDetail.getBase()
                    ,royaltyStrategyDetail.getRatio()
            );
        }
        this.royaltyStrategyDetailMap = royaltyStrategyDetailMap;
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

    public CommissionStrategy getRoyaltyStrategy() {
        return royaltyStrategy;
    }

    public void setRoyaltyStrategy(CommissionStrategy royaltyStrategy) {
        this.royaltyStrategy = royaltyStrategy;
    }

    public List<CommissionStrategyDetail> getRoyaltyStrategyDetails() {
        return royaltyStrategyDetails;
    }

    public void setRoyaltyStrategyDetails(List<CommissionStrategyDetail> royaltyStrategyDetails) {
        this.royaltyStrategyDetails = royaltyStrategyDetails;
    }

    public Map<BigDecimal, BigDecimal> getRoyaltyStrategyDetailMap() {
        return royaltyStrategyDetailMap;
    }

    public void setRoyaltyStrategyDetailMap(Map<BigDecimal, BigDecimal> royaltyStrategyDetailMap) {
        this.royaltyStrategyDetailMap = royaltyStrategyDetailMap;
    }
}
