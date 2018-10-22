package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "PurchaseInstorageShelfVO", description = "采购管理-采购入库-已入库货位对象")
public class PurchaseInstorageShelfVO implements Serializable{

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "质量状况：\n" +
            " job_area=0，含义为“合格品”；\n" +
            " job_area=2，含义为“不合格品”；\n"
            )
    private Integer jobArea;

    @ApiModelProperty(value = "(用于给前台显示)质量状况：\n" +
            " job_area=0，含义为“合格品”；\n" +
            " job_area=2，含义为“不合格品”；\n"
    )
    private String jobAreaName;

    /**
     * 批号
     */
    @ApiModelProperty(required = true, value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(required = true, value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(required = true, value = "有效期")
    private Date validDate;

    /**
     * 货位名称
     */
    @ApiModelProperty(required = true, value = "货位名称")
    private String shelfName;

    /**
     * 入库数量
     */
    private BigDecimal quantity;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    private BigDecimal amount;

    /**
     * 进项税
     */
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    private BigDecimal taxAmount;

    /**
     * 备注
     */
    private String remark;

    public Integer getJobArea() {
        return jobArea;
    }

    public void setJobArea(Integer jobArea) {
        this.jobArea = jobArea;
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

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getJobAreaName() {
        return jobAreaName;
    }

    public void setJobAreaName(String jobAreaName) {
        this.jobAreaName = jobAreaName;
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
        return "PurchaseInstorageShelfVO{" +
                "jobArea=" + jobArea +
                ", jobAreaName='" + jobAreaName + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfName='" + shelfName + '\'' +
                ", quantity=" + quantity +
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

    public static PurchaseInstorageShelfVO convertToVO(PurchaseInStorageShelf ps, PurchaseInStorageDetail purchaseInStorageDetail, WarehouseCargoArea warehouseCargoArea) {
        PurchaseInstorageShelfVO vo = new PurchaseInstorageShelfVO();
        vo.setLotNumber(ps.getLotNumber());
        vo.setProductDate(ps.getProductDate());
        vo.setValidDate(ps.getValidDate());
        vo.setShelfName(ps.getShelfName());
        String jobAreaName = "";
        // " job_area=0，含义为“合格品”；\n" +
        //" job_area=2，含义为“不合格品”；\n"
        if (0 == warehouseCargoArea.getJobArea()){
            jobAreaName = "合格品";
        }else if(2 == warehouseCargoArea.getJobArea()){
            jobAreaName = "不合格品";
        }
        vo.setJobArea(warehouseCargoArea.getJobArea());
        vo.setJobAreaName(jobAreaName);
        vo.setQuantity(ps.getQuantity());
        vo.setUnitPrice(ps.getUnitPrice());
        vo.setLineDiscount(ps.getLineDiscount());
        vo.setAmount(ps.getAmount());
        vo.setTaxRate(ps.getTaxRate());
        vo.setNotaxRealPrice(ps.getNotaxRealPrice());
        vo.setNotaxRealAmount(ps.getNotaxRealAmount());
        vo.setTaxAmount(ps.getTaxAmount());
        vo.setRemark(ps.getRemark());
        return vo;
    }
}
