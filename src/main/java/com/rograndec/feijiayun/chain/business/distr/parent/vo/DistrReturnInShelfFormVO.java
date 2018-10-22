package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * 
 * saas_distr_return_in_shelf
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-08
 */
public class DistrReturnInShelfFormVO implements Serializable {

    private Long goodsId;
    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID",required = true)
    private Long shelfId;

    /**
     * 验收单批号
     */
    @ApiModelProperty(value = "验收单批号",required = true)
    private String LotNumber;


    @ApiParam(value = "质量状况0-合格品，2不合格品", required = true)
    private Integer jobArea;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号",required = true)
    private Integer lineNum;

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getLotNumber() {
        return LotNumber;
    }

    public void setLotNumber(String lotNumber) {
        LotNumber = lotNumber;
    }

    public Integer getJobArea() {
        return jobArea;
    }

    public void setJobArea(Integer jobArea) {
        this.jobArea = jobArea;
    }

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
}