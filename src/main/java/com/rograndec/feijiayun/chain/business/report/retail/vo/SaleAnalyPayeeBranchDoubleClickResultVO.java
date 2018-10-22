package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleAnalyPayeeBranchDoubleClickResultVO", description = "按销售日期查询收款人员双击分页明细对象")
public class SaleAnalyPayeeBranchDoubleClickResultVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "开交班ID")
	private Long id;
	
	@ApiModelProperty(value = "店铺ID")
    private Long enterpriseId;
	
	@ApiModelProperty(value = "门店编码")
	private String storeCode;

	@ApiModelProperty(value = "门店名称")
	private String storeName;
	
	@ApiModelProperty(value = "销售时间/日结日期")
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date saleDate;
	
	@ApiModelProperty(value = "开班时间，仅在按日结双击明细时显示")
	private Date openingTime;
	
	@ApiModelProperty(value = "交班时间，仅在按日结双击明细时显示")
	private Date shiftTime;

	@ApiModelProperty(value = "款台")
	private String standCode;
	
	@ApiModelProperty(value = "班组")
	private String teamName;
	
	@ApiModelProperty(value = "收款员ID")
    private Long payeeId;
	
	@ApiModelProperty(value = "收款人员")
	private String payeeName;
	
	@ApiModelProperty(value = "销售笔数")
    private Long saleQuantity = 0L;
    
    @ApiModelProperty(value = "销售金额")
    private BigDecimal realAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "退货笔数")
    private Long returnQuantity = 0L;
    
    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnRealAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总额")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "不含税总额")
    private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public Long getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Long saleQuantity) {
		this.saleQuantity = saleQuantity;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public Long getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(Long returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public BigDecimal getReturnRealAmount() {
		return returnRealAmount;
	}

	public void setReturnRealAmount(BigDecimal returnRealAmount) {
		this.returnRealAmount = returnRealAmount;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}

	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public Date getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}
	
}
