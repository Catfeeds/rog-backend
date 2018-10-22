package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrInStorageShelfDtlVO implements Serializable{
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
     * 货位质量状态描述(用于前台进行合格或者不合格货位的选择)
     */
    @ApiModelProperty(value = "货位质量状态描述(用于前台进行合格或者不合格货位的选择)0-合格品 2-不合格品")
    private Integer shelfStatus;

    /**
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

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
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价---用于导出EXCEL")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）---用于导出EXCEL")
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额（整单优惠前金额）---用于导出EXCEL")
    private BigDecimal amount;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率---用于导出EXCEL")
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价---用于导出EXCEL")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额---用于导出EXCEL")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额---用于导出EXCEL")
    private BigDecimal taxAmount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注---用于导出EXCEL")
    private String remark;

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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "DistrInStorageShelfDtlVO{" +
                "shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfStatus=" + shelfStatus +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", lineNum=" + lineNum +
                ", unitPrice=" + unitPrice +
                ", lineDiscount=" + lineDiscount +
                ", amount=" + amount +
                ", taxRate=" + taxRate +
                ", notaxRealPrice=" + notaxRealPrice +
                ", notaxRealAmount=" + notaxRealAmount +
                ", taxAmount=" + taxAmount +
                ", remark='" + remark + '\'' +
                '}';
    }

    public static DistrInStorageShelfDtlVO convertTo(DistrInShelf shelf) {
        DistrInStorageShelfDtlVO vo = new DistrInStorageShelfDtlVO();
        vo.setShelfId(shelf.getShelfId());
        vo.setShelfName(shelf.getShelfName());
        vo.setShelfStatusDesc(shelf.getShelfStatusDesc());
        vo.setQuantity(shelf.getQuantity());
        vo.setLotNumber(shelf.getLotNumber());
        vo.setProductDate(shelf.getProductDate());
        vo.setValidDate(shelf.getValidDate());
        vo.setLineNum(shelf.getLineNum());
        vo.setUnitPrice(shelf.getUnitPrice());
        vo.setLineDiscount(shelf.getLineDiscount());
        vo.setAmount(shelf.getAmount());
        vo.setTaxRate(shelf.getTaxRate());
        vo.setNotaxRealPrice(shelf.getNotaxRealPrice());
        vo.setNotaxRealAmount(shelf.getNotaxRealAmount());
        vo.setTaxAmount(shelf.getTaxAmount());
        vo.setRemark(shelf.getRemark());
        return vo;

    }
}
