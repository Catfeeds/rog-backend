package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 盘点记录报表
 * xingjian.lan
 */
public class InventoryRecordReportVO extends BaseGoodsDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    @ApiModelProperty(value = "企业编码")
    private String enterpriseCode;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "盘点单号")
    private String invCode;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "创建人员")
    private String createrName;

    @ApiModelProperty(value = "登记日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date registerDate;

    @ApiModelProperty(value = "登记人员")
    private String registerManName;

    @ApiModelProperty(value = "盘点日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date invDate;

    @ApiModelProperty(value = "盘点人员")
    private String invManName;

    @ApiModelProperty(value = "复盘人员")
    private String secondInvManName;

    @ApiModelProperty(value = "过账日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date postDate;

    @ApiModelProperty(value = "过账人员")
    private String postManName;

    @ApiModelProperty(value = "账面数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "实盘数量")
    private BigDecimal invQuantity;

    @ApiModelProperty(value = "损溢数量")
    private BigDecimal diffQuantity;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "账面金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "实盘金额")
    private BigDecimal realAmount;

    @ApiModelProperty(value = "损溢金额")
    private BigDecimal diffAmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;

    @ApiModelProperty(value = "不含税账面金额")
    private BigDecimal notaxAmount;

    @ApiModelProperty(value = "不含税实盘金额")
    private BigDecimal realNotaxAmount;

    @ApiModelProperty(value = "不含税损溢金额")
    private BigDecimal diffNotaxAmount;

    @ApiModelProperty(value = "账面税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "实盘税额")
    private BigDecimal realTaxAmount;

    @ApiModelProperty(value = "损益税额")
    private BigDecimal diffTaxAmount;

    @ApiModelProperty(value = "零售单价")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "账面零售金额")
    private BigDecimal retailAmount;

    @ApiModelProperty(value = "实盘零售金额")
    private BigDecimal realRetailAmount;

    @ApiModelProperty(value = "损益零售金额")
    private BigDecimal diffRetailAmount;

    @ApiModelProperty(value = "状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）")
    private Integer status;

    private String statusDesc;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 质量状况
     */
    @ApiModelProperty(value = "质量状况")
    private String shelfStatusDesc;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

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

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterManName() {
        return registerManName;
    }

    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public String getInvManName() {
        return invManName;
    }

    public void setInvManName(String invManName) {
        this.invManName = invManName;
    }

    public String getSecondInvManName() {
        return secondInvManName;
    }

    public void setSecondInvManName(String secondInvManName) {
        this.secondInvManName = secondInvManName;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostManName() {
        return postManName;
    }

    public void setPostManName(String postManName) {
        this.postManName = postManName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getInvQuantity() {
        return invQuantity;
    }

    public void setInvQuantity(BigDecimal invQuantity) {
        this.invQuantity = invQuantity;
    }

    public BigDecimal getDiffQuantity() {
        return diffQuantity;
    }

    public void setDiffQuantity(BigDecimal diffQuantity) {
        this.diffQuantity = diffQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getNotaxPrice() {
        return notaxPrice;
    }

    public void setNotaxPrice(BigDecimal notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    public BigDecimal getNotaxAmount() {
        return notaxAmount;
    }

    public void setNotaxAmount(BigDecimal notaxAmount) {
        this.notaxAmount = notaxAmount;
    }

    public BigDecimal getRealNotaxAmount() {
        return realNotaxAmount;
    }

    public void setRealNotaxAmount(BigDecimal realNotaxAmount) {
        this.realNotaxAmount = realNotaxAmount;
    }

    public BigDecimal getDiffNotaxAmount() {
        return diffNotaxAmount;
    }

    public void setDiffNotaxAmount(BigDecimal diffNotaxAmount) {
        this.diffNotaxAmount = diffNotaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getRealTaxAmount() {
        return realTaxAmount;
    }

    public void setRealTaxAmount(BigDecimal realTaxAmount) {
        this.realTaxAmount = realTaxAmount;
    }

    public BigDecimal getDiffTaxAmount() {
        return diffTaxAmount;
    }

    public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
        this.diffTaxAmount = diffTaxAmount;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getRetailAmount() {
        return retailAmount;
    }

    public void setRetailAmount(BigDecimal retailAmount) {
        this.retailAmount = retailAmount;
    }

    public BigDecimal getRealRetailAmount() {
        return realRetailAmount;
    }

    public void setRealRetailAmount(BigDecimal realRetailAmount) {
        this.realRetailAmount = realRetailAmount;
    }

    public BigDecimal getDiffRetailAmount() {
        return diffRetailAmount;
    }

    public void setDiffRetailAmount(BigDecimal diffRetailAmount) {
        this.diffRetailAmount = diffRetailAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
    public String getStatusDesc() {
        if (status == null) {
            return "";
        }
        switch (status) {
            case 0:
                return "待登记";
            case 1:
                return "待处理";
            case 2:
                return "待过账";
            case 3:
                return "已完成";
            case 4:
                return "已取消";
        }
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
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

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }
}