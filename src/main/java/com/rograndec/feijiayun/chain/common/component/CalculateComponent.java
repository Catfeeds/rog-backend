package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.common.model.CalculateResultModel;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * @ClassName: CalculateComponent 
 * @Description: 计算组件
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年9月16日 下午5:12:05 
 *
 */
public class CalculateComponent {


	/**
	 * 
	 * @Title: getAmountByQuantityAndPriceAndLineDiscount 
	 * @Description: 根据数量、单价、行折扣获取金额（整单折扣前金额）：数量*单价*行折扣
	 * @param @param quantity 数量
	 * @param @param price 单价
	 * @param @param lineDiscount 行折扣（传％前数值）
	 * @param @return
	 * @return BigDecimal 返回类型 
	 * @throws
	 */
	public static BigDecimal getAmountByQuantityAndPriceAndLineDiscount(BigDecimal quantity, BigDecimal price, BigDecimal lineDiscount){
		BigDecimal result = BigDecimal.ZERO;
		result = quantity.multiply(price).multiply(lineDiscount).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
		return result;
	}
	
	/**
	 * 
	 * @Title: getLineRoundOffByLineAmountAndWholeAmountTotal 
	 * @Description: 根据整单舍入、整单金额合计获取行舍入：整单舍入*（行金额/整单金额合计）
	 * @param @param wholeRoundOff 整单舍入
	 * @param @param lineAmount 行金额
	 * @param @param wholeAmountTotal 整单金额合计
	 * @param @return
	 * @return BigDecimal 返回类型 
	 * @throws
	 */
	public static BigDecimal getLineRoundOffByLineAmountAndWholeAmountTotal(BigDecimal wholeRoundOff, 
			BigDecimal lineAmount, BigDecimal wholeAmountTotal){

		if(BigDecimal.ZERO.compareTo(wholeAmountTotal) >=0 ){
			return BigDecimal.ZERO;
		}

		BigDecimal result = BigDecimal.ZERO;
		result = lineAmount.divide(wholeAmountTotal, 4, BigDecimal.ROUND_HALF_UP).multiply(wholeRoundOff);
		return result;
	}
	
	/**
	 * 
	 * @Title: getRealAmountByQuantityAndPriceAndLineDiscount 
	 * @Description: 根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
	 * @param @param quantity 数量
	 * @param @param price 单价
	 * @param @param lineDiscount 行折扣（传％前数值）
	 * @param @param wholeDiscount 整单折扣（传％前数值）
	 * @param @param lineRoundOff 行舍入
	 * @param @return
	 * @return BigDecimal 返回类型
	 * @throws
	 */
	public static BigDecimal getRealAmountByQuantityAndPriceAndLineDiscount(BigDecimal quantity, BigDecimal price, 
			BigDecimal lineDiscount, BigDecimal wholeDiscount, BigDecimal lineRoundOff){
		BigDecimal result = BigDecimal.ZERO;
		result = quantity.multiply(price).multiply(lineDiscount).multiply(wholeDiscount).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP)
				.subtract(lineRoundOff).setScale(2, RoundingMode.HALF_UP);
		return result;
	}
	
	/**
	 * 
	 * @Title: getRealPriceByRealAmountAndQuantity 
	 * @Description: 根据实际金额和数量获取实际单价：实际金额/数量
	 * @param @param realAmount 实际金额
	 * @param @param quantity 数量
	 * @param @return
	 * @return BigDecimal 返回类型 
	 * @throws
	 */
	public static BigDecimal getRealPriceByRealAmountAndQuantity(BigDecimal realAmount, BigDecimal quantity){
		if(BigDecimal.ZERO.compareTo(quantity) >=0 ){
			return BigDecimal.ZERO;
		}
		BigDecimal result = BigDecimal.ZERO;
		result = realAmount.divide(quantity, 6, BigDecimal.ROUND_HALF_UP);
		return result;
	}
	
	/**
	 * @Title: getNotaxAmountByRealAmountAndTaxRate 
	 * @Description: 根据实际金额和税率获取不含税金额：金额/(1+税率)
	 * @param @param realAmount 实际金额
	 * @param @param taxRate 税率
	 * @param @return
	 * @return BigDecimal 返回类型 
	 * @throws
	 */
	public static BigDecimal getNotaxAmountByRealAmountAndTaxRate(BigDecimal realAmount, BigDecimal taxRate){

		BigDecimal result = BigDecimal.ZERO;
		result = realAmount.divide(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
		return result;
	}
	
	/**
	 * 
	 * @Title: getNotaxPriceByNotaxAmountAndQuantity 
	 * @Description: 根据不含税金额和数量获取不含税单价：不含税金额/数量
	 * @param @param notaxAmount 不含税金额
	 * @param @param quantity 数量
	 * @param @return
	 * @return BigDecimal 返回类型 
	 * @throws
	 */
	public static BigDecimal getNotaxPriceByNotaxAmountAndQuantity(BigDecimal notaxAmount, BigDecimal quantity){
		if(BigDecimal.ZERO.compareTo(quantity) >=0 ){
			return BigDecimal.ZERO;
		}
		BigDecimal result = BigDecimal.ZERO;
		result = notaxAmount.divide(quantity, 6, BigDecimal.ROUND_HALF_UP);
		return result;
	}
	
	/**
	 * 
	 * @Title: getTaxAmountByRealAmountAndNotaxAmount 
	 * @Description: 根据实际金额和不含税金额获取税额：金额-不含税金额 
	 * @param @param realAmount 实际金额
	 * @param @param notaxAmount 不含税金额
	 * @param @return
	 * @return BigDecimal 返回类型 
	 * @throws
	 */
	public static BigDecimal getTaxAmountByRealAmountAndNotaxAmount(BigDecimal realAmount, BigDecimal notaxAmount){
		BigDecimal result = BigDecimal.ZERO;
		result = realAmount.subtract(notaxAmount).setScale(2, RoundingMode.HALF_UP);
		return result;
	}
	
	/**
	 * @Title: getCalculateResult 
	 * @Description: 根据数量、单价、行折扣、整单折扣、整单舍入、税率、整单总额（前台页面可编辑项）<br>
	 *               获取计算结果（金额、行舍入、实际金额、实际单价、不含税金额、不含税单价、税额） 
	 * @param @param quantity 数量
	 * @param @param price 单价
	 * @param @param lineDiscount 行折扣
	 * @param @param wholeDiscount 整单折扣
	 * @param @param wholeRoundOff 整单舍入
	 * @param @param taxRate 税率
	 * @param @param wholeAmountTotal 整单总额
	 * @param @return
	 * @return CalculateResultModel 返回类型 
	 * @throws
	 */
	public static CalculateResultModel getCalculateResult(BigDecimal quantity, BigDecimal price, BigDecimal lineDiscount, 
			BigDecimal wholeDiscount, BigDecimal wholeRoundOff, BigDecimal taxRate, BigDecimal wholeAmountTotal){
		CalculateResultModel result = new CalculateResultModel();
		result.setPrice(price);
		if(BigDecimal.ZERO.compareTo(price) >= 0) return result;
		// 金额（整单折扣前金额）：数量*单价*行折扣
		BigDecimal amount = getAmountByQuantityAndPriceAndLineDiscount(quantity, price, lineDiscount);// 行金额
		result.setAmount(amount);
		// 行舍入：整单舍入*（行金额/整单金额合计）
		BigDecimal lineRoundOff = getLineRoundOffByLineAmountAndWholeAmountTotal(wholeRoundOff, amount, wholeAmountTotal);
		result.setLineRoundOff(lineRoundOff);
		// 实际金额：数量*单价*行折扣*整单折扣-行舍入
		BigDecimal realAmount = getRealAmountByQuantityAndPriceAndLineDiscount(quantity, price, lineDiscount, wholeDiscount, lineRoundOff);
		result.setRealAmount(realAmount);
		// 实际单价：实际金额/数量
		BigDecimal realPrice = getRealPriceByRealAmountAndQuantity(realAmount, quantity);
		result.setRealPrice(realPrice);
		// 不含税金额：金额/(1+税率)
		BigDecimal notaxAmount = getNotaxAmountByRealAmountAndTaxRate(realAmount, taxRate);
		result.setNotaxAmount(notaxAmount);
		// 不含税单价：金额/(1+税率)
		BigDecimal notaxPrice = getNotaxPriceByNotaxAmountAndQuantity(notaxAmount, quantity);
		result.setNotaxPrice(notaxPrice);
		// 税额：金额-不含税金额
		BigDecimal taxAmount = getTaxAmountByRealAmountAndNotaxAmount(realAmount, notaxAmount);
		result.setTaxAmount(taxAmount);
		return result;
	}
	
}
