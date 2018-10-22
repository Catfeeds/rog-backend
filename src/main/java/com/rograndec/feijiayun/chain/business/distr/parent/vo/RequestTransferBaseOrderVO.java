package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/30 <br/>
 * 描述：配送单调用其它单据生成配送单保存传参
 */
public class RequestTransferBaseOrderVO {

    @ApiModelProperty(value = "配货人员ID",required = true)
    private Long distrManId;
    
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "配货日期",required = true)
    private Date distrDate;

    @ApiModelProperty(value = "配货规则（0-按要货顺序；1-按库存平均；2-按要货数量占比）",required = true)
    private Integer distrRule;

    @ApiModelProperty(value = "缺配处理（0-生成缺配单；1-不处理）",required = true)
    private Integer lackHandle;

    @ApiModelProperty(value = "单据明细列表",required = true)
    List<RequestBaseOrderVO> baseOrderDtlList;

    public Long getDistrManId() {
        return distrManId;
    }

    public void setDistrManId(Long distrManId) {
        this.distrManId = distrManId;
    }

    public Date getDistrDate() {
        return distrDate;
    }

    public void setDistrDate(Date distrDate) {
        this.distrDate = distrDate;
    }

    public Integer getDistrRule() {
        return distrRule;
    }

    public void setDistrRule(Integer distrRule) {
        this.distrRule = distrRule;
    }

    public Integer getLackHandle() {
        return lackHandle;
    }

    public void setLackHandle(Integer lackHandle) {
        this.lackHandle = lackHandle;
    }

    public List<RequestBaseOrderVO> getBaseOrderDtlList() {
        return baseOrderDtlList;
    }

    public void setBaseOrderDtlList(List<RequestBaseOrderVO> baseOrderDtlList) {
        this.baseOrderDtlList = baseOrderDtlList;
    }
}
