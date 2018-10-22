package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LagSaleGoodsVO", description = "滞销商品列表")
public class LagSaleGoodsVO extends BaseGoodsDetailVO{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月21日 下午3:35:22 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "企业编码")
    private String enterpriseCode;
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;
	
	@ApiModelProperty(value = "数量")
	private BigDecimal usableQuantity;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "销售日期")
	private Date saleDate;
	
	@ApiModelProperty(value = "滞销周期",hidden=true)
	private Integer unsalableCycle;
	
	@ApiModelProperty(value = "滞销周期")
	private String unsalableCycleDesc;
	
	@ApiModelProperty(value = "0-天；1-月；2-年",hidden=true)
	private Integer unsalableCycleUnit;
	@ApiModelProperty(value = "滞销天数")
	private int unsalableDay;

	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}
	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public Integer getUnsalableCycle() {
		return unsalableCycle;
	}
	public void setUnsalableCycle(Integer unsalableCycle) {
		this.unsalableCycle = unsalableCycle;
	}
	public int getUnsalableDay() {
		return unsalableDay;
	}
	public void setUnsalableDay(int unsalableDay) {
		this.unsalableDay = unsalableDay;
	}
	public Integer getUnsalableCycleUnit() {
		return unsalableCycleUnit;
	}
	public void setUnsalableCycleUnit(Integer unsalableCycleUnit) {
		this.unsalableCycleUnit = unsalableCycleUnit;
	}
	
	public String getUnsalableCycleDesc() {
		if(null != unsalableCycle) {
			unsalableCycleDesc = unsalableCycle + "天";
		}
		return unsalableCycleDesc;
	}
	public void setUnsalableCycleDesc(String unsalableCycleDesc) {
		this.unsalableCycleDesc = unsalableCycleDesc;
	}
	
	
	
}
