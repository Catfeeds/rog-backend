package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ClickCheckDetailOneVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号", required = true)
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期", required = true)
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期", required = true)
    private Date validDate;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;


    /**
     * 配进收货明细ID
     */
    /** 
    * @Description: 配后退回一间入库需要做下关联
    * @return:  
    * @Author: dongyang.du
    * @Date: 30/01/2018 
    */ 
    @ApiModelProperty(value = "配进收货明细ID", required = true)
    private Long distrReturnReceiveDetailId;

    public Long getDistrReturnReceiveDetailId() {
        return distrReturnReceiveDetailId;
    }

    public void setDistrReturnReceiveDetailId(Long distrReturnReceiveDetailId) {
        this.distrReturnReceiveDetailId = distrReturnReceiveDetailId;
    }

    public Long getGoodsId() {
        return goodsId;
    }
    

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    @Override
    public String toString() {
        return "ClickCheckDetailOneVO[" +
                "goodsId=" + goodsId +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", receiveQuantity=" + receiveQuantity +
                ", lineNum=" + lineNum +
                ']';
    }
}
