package com.rograndec.feijiayun.chain.business.init.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: TaxRateModel  
 * @Description: 税率初始化数据模型 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 下午1:28:05  
 *
 */
public class TaxRateModel {

	private String code;
	private BigDecimal taxRate;
	
	public TaxRateModel(){}

	public TaxRateModel(String code, BigDecimal taxRate) {
		super();
		this.code = code;
		this.taxRate = taxRate;
	}

	public static List<TaxRateModel> build(){
		List<TaxRateModel> trList = new ArrayList<TaxRateModel>();
		trList.add(new TaxRateModel("0", BigDecimal.ZERO));
		trList.add(new TaxRateModel("11", new BigDecimal(11)));
		trList.add(new TaxRateModel("13", new BigDecimal(13)));
		trList.add(new TaxRateModel("17", new BigDecimal(17)));
		return trList;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
}
