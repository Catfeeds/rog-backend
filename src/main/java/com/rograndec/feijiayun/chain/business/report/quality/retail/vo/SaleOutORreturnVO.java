package com.rograndec.feijiayun.chain.business.report.quality.retail.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleOutORreturnVO", description = "销售出库/退货列表信息")
public class SaleOutORreturnVO extends BaseGoodsDetailVO{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月23日 上午11:27:53 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "企业编码")
    private String enterpriseCode;
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "日结日期")
	private Date dailyTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "销售日期")
	private Date saleDate;
    @ApiModelProperty(value = "单号")
    private String saleCode;  
    @ApiModelProperty(value = "销售模式 0-常规模式，1-中药模式",hidden=true)
    private Integer saleMode;
    @ApiModelProperty(value = "销售模式")
    private String saleModeStr;
    @ApiModelProperty(value = "款台编码")
    private String standCode;
    @ApiModelProperty(value = "班组")
    private String teamName;
    @ApiModelProperty(value = "收款人员")
    private String payeeName;
    @ApiModelProperty(value = "柜组")
    private String cargoAreaName;
    @ApiModelProperty(value = "营业人员")
    private String clerkName;
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;
    @ApiModelProperty(value = "货位id",hidden=true)
    private Long shelfId;
    @ApiModelProperty(value = "批号")
    private String lotNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生产日期")
	private Date productDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效日期")
	private Date validDate;
    @ApiModelProperty(value = "货位")
    private String shelfName;
    @ApiModelProperty(value = "质量状况")
    private String shelfStatusDesc;
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;
    @ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;
    @ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;
    @ApiModelProperty(value = "实际单价（实际金额/数量）")
    private BigDecimal realPrice;
    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;
    @ApiModelProperty(value = "销项税")
    private BigDecimal taxRate;
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice;
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount;
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;
    
    // 货区状态
    @ApiModelProperty(value = "作业区域",hidden=true)
    private Integer jobArea;
    @ApiModelProperty(value = "作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）",hidden=true)
    private Integer jobType;

    
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

	public Date getDailyTime() {
		return dailyTime;
	}

	public void setDailyTime(Date dailyTime) {
		this.dailyTime = dailyTime;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
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

	public String getCargoAreaName() {
		return cargoAreaName;
	}

	public void setCargoAreaName(String cargoAreaName) {
		this.cargoAreaName = cargoAreaName;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getShelfStatusDesc() {
		if(0==jobType && 0==jobArea){
			shelfStatusDesc = "合格";
		}else if(0==jobType && 2==jobArea){
			shelfStatusDesc = "不合格";
		}else{
			shelfStatusDesc = "其它";
		}
		return shelfStatusDesc;
	}

	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLineDiscount() {
		return lineDiscount;
	}

	public void setLineDiscount(BigDecimal lineDiscount) {
		this.lineDiscount = lineDiscount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getLineDiscountAmount() {
		return lineDiscountAmount;
	}

	public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}

	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
	}

	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}

	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getSaleModeStr() {
		if (saleMode == null) {
            return "";
        }
        switch (saleMode) {
            case 0:
                return "常规模式";
            case 1:
                return "中药模式";
            default:
                break;
        }
        return "";
	}

	public void setSaleModeStr(String saleModeStr) {
		this.saleModeStr = saleModeStr;
	}

	public Integer getJobArea() {
		return jobArea;
	}

	public void setJobArea(Integer jobArea) {
		this.jobArea = jobArea;
	}

	public Integer getJobType() {
		return jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

}
