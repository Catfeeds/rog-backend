package com.rograndec.feijiayun.chain.business.retail.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/9/22 17:44
 */

public class ResponsePrescriptionRegisterShelfForDetailVO implements Serializable{

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "批号Id")
    private Long lotNumberId;

    @ApiModelProperty(value = "批号")
    private String lotNumber;

    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    @ApiModelProperty(value = "有效期至")
    private Date validDate;

    @ApiModelProperty(value = "货位")
    private String shelfName;

    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    @ApiModelProperty(value = "货位质量描述")
    private String shelfStatusDesc;


    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

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

    public Long getLotNumberId() {
        return lotNumberId;
    }

    public void setLotNumberId(Long lotNumberId) {
        this.lotNumberId = lotNumberId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}