package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_distr_req_plan
 * 
 * 
 * @author kexinhao
 * 在途库存
 * 2017-10-07
 */
public class AnalyseStockVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "根据安全库存分析")
    private String safety;
    @ApiModelProperty(value = "按照缺断货数量分析")
    private String lack;
    @ApiModelProperty(value = "lackQuantity天内有销售，无库存的商品")
    private  BigDecimal lackQuantity;
    @ApiModelProperty(value = "按照动态存量要货")
    private String dynamicStock;
    @ApiModelProperty(value = "dynamicStockQuantity天内有销售，不满足undynamicStockQuantity天内销售")
    private BigDecimal dynamicStockQuantity;
    @ApiModelProperty(value = "dynamicStockQuantity天内有销售，不满足undynamicStockQuantity天内销售")
    private BigDecimal undynamicStockQuantity;
    @ApiModelProperty(value = "是否考虑在途库存")
    private String onPassage;
    @ApiModelProperty(value = "配货类型（0-总部配送；4-直调配送）,只能是这两种类型",required = true)
    @NotNull(message = "配货类型不能为空")
    Integer requestType;
    @ApiModelProperty(value = "供货单位ID")
    Long outboundUnitId;
    private Date startTime;
    
	public String getSafety() {
		return safety;
	}
	public void setSafety(String safety) {
		this.safety = safety;
	}
	public String getLack() {
		return lack;
	}
	public void setLack(String lack) {
		this.lack = lack;
	}
	public String getDynamicStock() {
		return dynamicStock;
	}
	public void setDynamicStock(String dynamicStock) {
		this.dynamicStock = dynamicStock;
	}
	public String getOnPassage() {
		return onPassage;
	}
	public void setOnPassage(String onPassage) {
		this.onPassage = onPassage;
	}
	public BigDecimal getLackQuantity() {
		return lackQuantity;
	}
	public void setLackQuantity(BigDecimal lackQuantity) {
		this.lackQuantity = lackQuantity;
	}
	public BigDecimal getDynamicStockQuantity() {
		return dynamicStockQuantity;
	}
	public void setDynamicStockQuantity(BigDecimal dynamicStockQuantity) {
		this.dynamicStockQuantity = dynamicStockQuantity;
	}
	public BigDecimal getUndynamicStockQuantity() {
		return undynamicStockQuantity;
	}
	public void setUndynamicStockQuantity(BigDecimal undynamicStockQuantity) {
		this.undynamicStockQuantity = undynamicStockQuantity;
	}
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public Long getOutboundUnitId() {
		return outboundUnitId;
	}
	public void setOutboundUnitId(Long outboundUnitId) {
		this.outboundUnitId = outboundUnitId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
}