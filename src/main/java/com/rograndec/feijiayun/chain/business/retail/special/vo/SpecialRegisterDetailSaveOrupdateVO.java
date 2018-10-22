package com.rograndec.feijiayun.chain.business.retail.special.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: SpecialRegisterDetailSaveOrupdateVO
 * @Description: 零售管理-专管登记单品种明细-Rest接口
 * @date 2017-09-22 16:34:07
 */
@ApiModel(value = "SpecialRegisterDetailSaveOrupdateVO", description = "零售管理-专管登记单品种明细")
public class  SpecialRegisterDetailSaveOrupdateVO implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空!")
    @ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空!")
    @ApiModelProperty(required = true, value = "数量")
    private BigDecimal quantity;


    /**
     * 单价
     */
    @NotNull(message = "单价不能为空!")
    @ApiModelProperty(required = true, value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @NotNull(message = "行折扣（%）不能为空!")
    @ApiModelProperty(required = true, value = "行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     *税率ID
     */
    @ApiModelProperty(required = true, value = "税率id")
    private Long taxRateId;

    /**
     *行号
     */
    @NotNull(message = "行号不能为空!")
    @ApiModelProperty(required = true, value = "行号")
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(required = false, value = "备注")
    private String remark;

    @ApiModelProperty(required = true, value = "专管登记单货位明细")
    private List<SpecialRegisterShelfSaveOrupdateVO> specialRegisterShelfSaveOrupdateVOList;

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SpecialRegisterShelfSaveOrupdateVO> getSpecialRegisterShelfSaveOrupdateVOList() {
        return specialRegisterShelfSaveOrupdateVOList;
    }

    public void setSpecialRegisterShelfSaveOrupdateVOList(List<SpecialRegisterShelfSaveOrupdateVO> specialRegisterShelfSaveOrupdateVOList) {
        this.specialRegisterShelfSaveOrupdateVOList = specialRegisterShelfSaveOrupdateVOList;
    }
}