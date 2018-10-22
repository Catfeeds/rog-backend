package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ChGoodsClearOrderDtlShelfListOneVO implements Serializable {

    @ApiModelProperty(required = true, value = "清斗货位明细ID")
    private Long id;

    @ApiModelProperty(required = true, value = "清斗明细ID")
    private Long dtlId;

    @ApiModelProperty(required = true, value = "批号ID")
    private String lotId;

    @ApiModelProperty(required = true, value = "批号")
    private String lotNumber;

    @ApiModelProperty(required = true, value = "生产日期")
    private String productDate;

    @ApiModelProperty(required = true, value = "有效期至")
    private String validDate;

    @ApiModelProperty(required = true, value = "货位")
    private String shelfName;

    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(required = true, value = "质量状况")
    private String shelfStatusDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDtlId() {
        return dtlId;
    }

    public void setDtlId(Long dtlId) {
        this.dtlId = dtlId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
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

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    @Override
    public String toString() {
        return "ChGoodsClearOrderDtlShelfListOneVO[" +
                "id=" + id +
                ", dtlId=" + dtlId +
                ", lotId='" + lotId + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfName='" + shelfName + '\'' +
                ", quantity=" + quantity +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ']';
    }
}
