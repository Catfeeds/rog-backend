package com.rograndec.feijiayun.chain.business.storage.move.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * saas_other_out_detail
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
@ApiModel
public class OtherOutDetailFormVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;


//    /**
//     * 成本表id
//     */
//    @ApiModelProperty(value = "成本表id")
//    private Long costId;


    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 销项税ID
     */
    @ApiModelProperty(value = "销项税ID")
    private Long taxRateId;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "货位信息")
    private List<OtherOutShelfFormVO> otherOutShelfFormVOS;


    public static Set<Long> getStockIds(List<OtherOutDetailFormVO> otherOutDetailFormVOS){

        Set<Long> ids = new HashSet<>();

        otherOutDetailFormVOS.forEach(otherOutDetailFormVO -> {
            otherOutDetailFormVO.getOtherOutShelfFormVOS().forEach(otherOutShelfFormVO -> {
                ids.add(otherOutShelfFormVO.getStockId());
            });
        });

        return ids;
    }

    public static Set<Long> getShelfIds(List<OtherOutDetailFormVO> otherOutDetailFormVOS){

        Set<Long> ids = new HashSet<>();

        otherOutDetailFormVOS.forEach(otherOutDetailFormVO -> {
            otherOutDetailFormVO.getOtherOutShelfFormVOS().forEach(otherOutShelfFormVO -> {
                ids.add(otherOutShelfFormVO.getShelfId());
            });
        });

        return ids;
    }

    public static Set<Long> getGoodsIds(List<OtherOutDetailFormVO> detailVOs){

        Set<Long> ids = new HashSet<>();


        detailVOs.forEach(detail -> ids.add(detail.getGoodsId()));

        return ids;
    }


//    public static Set<Long> getCostIds(List<OtherOutDetailFormVO> detailVOs){
//
//        Set<Long> ids = new HashSet<>();
//
//        detailVOs.forEach(detail -> ids.add(detail.getCostId()));
//
//        return ids;
//    }



    public static Set<Long> getTaxRateIds(List<OtherOutDetailFormVO> detailVOs){

        Set<Long> ids = new HashSet<>();

        detailVOs.forEach(detail -> ids.add(detail.getTaxRateId()));

        return ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }


//    public Long getCostId() {
//        return costId;
//    }
//
//    public void setCostId(Long costId) {
//        this.costId = costId;
//    }

    public List<OtherOutShelfFormVO> getOtherOutShelfFormVOS() {
        return otherOutShelfFormVOS;
    }

    public void setOtherOutShelfFormVOS(List<OtherOutShelfFormVO> otherOutShelfFormVOS) {
        this.otherOutShelfFormVOS = otherOutShelfFormVOS;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}