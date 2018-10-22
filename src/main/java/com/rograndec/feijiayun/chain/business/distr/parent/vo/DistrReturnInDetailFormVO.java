package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * saas_distr_return_in_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-08
 */
public class DistrReturnInDetailFormVO implements Serializable {

    /**
     *  配后退回验收明细单ID
     */
    @ApiModelProperty(value = "配后退回验收明细单ID",required = true)
    private Long checkDtId;


    /**
     * 单价
     */
    @ApiModelProperty(value = "单价",required = true)
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）",required = true)
    private BigDecimal lineDiscount;


    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID",required = true)
    private Long taxRateId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 行号
     */
    @ApiModelProperty(value = "行号",required = true)
    private Integer lineNum;

    @ApiModelProperty(value = "购后退出货位信息集合")
    private List<DistrReturnInShelfFormVO> distrReturnInShelfFormVOS;


    public static List<Long> getShelfIds(List<DistrReturnInDetailFormVO> distrReturnInDetailFormVOS){

        List<Long> ids = new ArrayList<>();

        distrReturnInDetailFormVOS.forEach(

                drid -> {

                    List<DistrReturnInShelfFormVO> distrReturnInShelfFormVOS = drid.getDistrReturnInShelfFormVOS();

                    distrReturnInShelfFormVOS.forEach(dris -> {
                        ids.add(dris.getShelfId());
                    });
                }
        );

        return ids;
    }

    public static List<Long> getTaxRateIds(List<DistrReturnInDetailFormVO> distrReturnInDetailFormVOS){

        List<Long> ids = new ArrayList<>();

        distrReturnInDetailFormVOS.forEach(
                drid -> {
                    ids.add(drid.getTaxRateId());
                }
        );

        return ids;
    }

    public Long getCheckDtId() {
        return checkDtId;
    }

    public void setCheckDtId(Long checkDtId) {
        this.checkDtId = checkDtId;
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

    public List<DistrReturnInShelfFormVO> getDistrReturnInShelfFormVOS() {
        return distrReturnInShelfFormVOS;
    }

    public void setDistrReturnInShelfFormVOS(List<DistrReturnInShelfFormVO> distrReturnInShelfFormVOS) {
        this.distrReturnInShelfFormVOS = distrReturnInShelfFormVOS;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}