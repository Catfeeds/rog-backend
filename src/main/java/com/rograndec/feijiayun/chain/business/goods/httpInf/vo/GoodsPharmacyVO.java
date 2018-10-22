package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: GoodsPharmacyVO   
 * @Description: 标准库药学服务返回对象说明
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月17日 下午8:45:46
 */
@ApiModel(value = "GoodsPharmacyVO", description = "标准库药学服务返回对象说明")
public class GoodsPharmacyVO {
	
	@ApiModelProperty(value = "药品名称")
	private String nrCommonName;
	
	@ApiModelProperty(value = "标准药品")
	private NormDrugVO normDrug;
	
	@ApiModelProperty(value = "标准保健食品")
	private NormHealthProductsVO normHealthProducts;
	
	@ApiModelProperty(value = "标准医疗器械")
	private NormInstrumentVO normInstrument;
	
	@ApiModelProperty(value = "其它")
	private NormOtherVO normOther;

	public String getNrCommonName() {
		return nrCommonName;
	}

	public void setNrCommonName(String nrCommonName) {
		this.nrCommonName = nrCommonName;
	}

	public NormDrugVO getNormDrug() {
		return normDrug;
	}

	public void setNormDrug(NormDrugVO normDrug) {
		this.normDrug = normDrug;
	}

	public NormHealthProductsVO getNormHealthProducts() {
		return normHealthProducts;
	}

	public void setNormHealthProducts(NormHealthProductsVO normHealthProducts) {
		this.normHealthProducts = normHealthProducts;
	}

	public NormInstrumentVO getNormInstrument() {
		return normInstrument;
	}

	public void setNormInstrument(NormInstrumentVO normInstrument) {
		this.normInstrument = normInstrument;
	}

	public NormOtherVO getNormOther() {
		return normOther;
	}

	public void setNormOther(NormOtherVO normOther) {
		this.normOther = normOther;
	}

	
	
	

}
