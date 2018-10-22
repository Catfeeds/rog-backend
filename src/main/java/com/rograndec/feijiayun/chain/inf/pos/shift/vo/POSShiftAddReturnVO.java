package com.rograndec.feijiayun.chain.inf.pos.shift.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "POSShiftVO", description = "POS交班查看数据")
public class POSShiftAddReturnVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键id")
	private Long id;
	

    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;


    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;
    
    @ApiModelProperty(value = "开班人员账号")
    private String openingAccount;
    
    @ApiModelProperty(value = "收款人员ID")
    private Long payeeId;
	
	//默认当前登录人员
	@ApiModelProperty(value = "款员")
	private String payeeName;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "开班时间")
	private Date openingTime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "交班时间")
	private Date shiftTime;
	
	@ApiModelProperty(value = "销售笔数")
	private int salePens;
	
	@ApiModelProperty(value = "销售金额")
	private BigDecimal saleAmount;
	
	@ApiModelProperty(value = "退货笔数")
	private int returnPens;
	
	@ApiModelProperty(value = "退货金额")
	private BigDecimal returnAmount;
	
	@ApiModelProperty(value = "接收备用金")
	private BigDecimal acceptSpareMoney;
	
	@ApiModelProperty(value = "应缴现金")
	private BigDecimal payableCash;
	
	@ApiModelProperty(value = "上缴现金")
	private BigDecimal cash;
	
	@ApiModelProperty(value = "下放备用金")
	private BigDecimal sendSpareMoney;
	
	@ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
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

	public int getSalePens() {
		return salePens;
	}

	public void setSalePens(int salePens) {
		this.salePens = salePens;
	}

	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}

	public int getReturnPens() {
		return returnPens;
	}

	public void setReturnPens(int returnPens) {
		this.returnPens = returnPens;
	}

	public BigDecimal getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}

	public BigDecimal getAcceptSpareMoney() {
		return acceptSpareMoney;
	}

	public void setAcceptSpareMoney(BigDecimal acceptSpareMoney) {
		this.acceptSpareMoney = acceptSpareMoney;
	}

	public BigDecimal getPayableCash() {
		return payableCash;
	}

	public void setPayableCash(BigDecimal payableCash) {
		this.payableCash = payableCash;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public BigDecimal getSendSpareMoney() {
		return sendSpareMoney;
	}

	public void setSendSpareMoney(BigDecimal sendSpareMoney) {
		this.sendSpareMoney = sendSpareMoney;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getOpeningAccount() {
		return openingAccount;
	}

	public void setOpeningAccount(String openingAccount) {
		this.openingAccount = openingAccount;
	}

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
