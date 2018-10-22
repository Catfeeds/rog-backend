package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "HashBeenInstoragePageVO", description = "采购管理-采购入库-已入库分页对象")
public class HasBeenInstoragePageVO implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "主键ID")
    private Long id;

    /**
     * 入库日期
     */
    @ApiModelProperty(required = true, value = "入库日期")
    private Date inStorageDate;

    /**
     * 单据（入库单）编号
     */
    @ApiModelProperty(required = true, value = "单据（入库单）编号")
    private String code;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(required = true, value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(required = true, value = "供货单位名称")
    private String supplierName;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(required = true, value = "入库人员名称")
    private String storageManName;
    /**
     * 状态名称
     */
    @ApiModelProperty(value = "状态名称")
    private String statusName;
    /**
     * 实际金额合计
     */
    @ApiModelProperty(required = true, value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(required = true, value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(required = true, value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 当前页实际金额合计
     */
    @ApiModelProperty(required = true, value = "当前页实际金额合计（右下角合计的内容--如果一行数据都没有就为0）")
    private BigDecimal pageRealAmountTotal;


    /**
     * 当前页不含税金额合计
     */
    @ApiModelProperty(required = true, value = "当前页不含税金额合计（右下角合计的内容--如果一行数据都没有就为0）")
    private BigDecimal pageNotaxRealAmountTotal;


    /**
     * 当前页税额合计
     */
    @ApiModelProperty(required = true, value = "当前页税额合计（右下角合计的内容--如果一行数据都没有就为0）")
    private BigDecimal pagetaxAmountTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStorageManName() {
        return storageManName;
    }

    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public BigDecimal getPageRealAmountTotal() {
        return pageRealAmountTotal;
    }

    public void setPageRealAmountTotal(BigDecimal pageRealAmountTotal) {
        this.pageRealAmountTotal = pageRealAmountTotal;
    }

    public BigDecimal getPageNotaxRealAmountTotal() {
        return pageNotaxRealAmountTotal;
    }

    public void setPageNotaxRealAmountTotal(BigDecimal pageNotaxRealAmountTotal) {
        this.pageNotaxRealAmountTotal = pageNotaxRealAmountTotal;
    }

    public BigDecimal getPagetaxAmountTotal() {
        return pagetaxAmountTotal;
    }

    public void setPagetaxAmountTotal(BigDecimal pagetaxAmountTotal) {
        this.pagetaxAmountTotal = pagetaxAmountTotal;
    }

    @Override
    public String toString() {
        return "HasBeenInstoragePageVO{" +
                "id=" + id +
                ", inStorageDate=" + inStorageDate +
                ", code='" + code + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", storageManName='" + storageManName + '\'' +
                ", statusName='" + statusName + '\'' +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HasBeenInstoragePageVO that = (HasBeenInstoragePageVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (inStorageDate != null ? !inStorageDate.equals(that.inStorageDate) : that.inStorageDate != null)
            return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (supplierCode != null ? !supplierCode.equals(that.supplierCode) : that.supplierCode != null) return false;
        if (supplierName != null ? !supplierName.equals(that.supplierName) : that.supplierName != null) return false;
        if (storageManName != null ? !storageManName.equals(that.storageManName) : that.storageManName != null)
            return false;
        if (realAmountTotal != null ? !realAmountTotal.equals(that.realAmountTotal) : that.realAmountTotal != null)
            return false;
        if (notaxRealAmountTotal != null ? !notaxRealAmountTotal.equals(that.notaxRealAmountTotal) : that.notaxRealAmountTotal != null)
            return false;
        return taxAmountTotal != null ? taxAmountTotal.equals(that.taxAmountTotal) : that.taxAmountTotal == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (inStorageDate != null ? inStorageDate.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (supplierCode != null ? supplierCode.hashCode() : 0);
        result = 31 * result + (supplierName != null ? supplierName.hashCode() : 0);
        result = 31 * result + (storageManName != null ? storageManName.hashCode() : 0);
        result = 31 * result + (statusName != null ? statusName.hashCode() : 0);
        result = 31 * result + (realAmountTotal != null ? realAmountTotal.hashCode() : 0);
        result = 31 * result + (notaxRealAmountTotal != null ? notaxRealAmountTotal.hashCode() : 0);
        result = 31 * result + (taxAmountTotal != null ? taxAmountTotal.hashCode() : 0);
        return result;
    }

    public static HasBeenInstoragePageVO convertToVO(PurchaseInStorage purchase) {
        HasBeenInstoragePageVO pageVO = new HasBeenInstoragePageVO();
        pageVO.setId(purchase.getId());
        pageVO.setCode(purchase.getCode());
        pageVO.setInStorageDate(purchase.getInStorageDate());
        pageVO.setSupplierCode(purchase.getSupplierCode());
        pageVO.setSupplierName(purchase.getSupplierName());
        pageVO.setStorageManName(purchase.getStorageManName());
        pageVO.setStatusName(PurchaseStatus.getName(purchase.getStatus()));
        pageVO.setRealAmountTotal(purchase.getRealAmountTotal());
        pageVO.setNotaxRealAmountTotal(purchase.getNotaxRealAmountTotal());
        pageVO.setTaxAmountTotal(purchase.getTaxAmountTotal());
        return pageVO;
    }
}
