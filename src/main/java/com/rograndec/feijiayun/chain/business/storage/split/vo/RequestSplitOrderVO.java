package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/30 <br/>
 * 描述：保存商品拆零请求传参
 */
public class RequestSplitOrderVO {

    @ApiModelProperty(value = "分拆人员ID",required = true)
    private Long splitManId;

    @ApiModelProperty(value = "拆单日期",required = true)
    private Date splitDate;

    @ApiModelProperty(value = "复核人员ID",required = true)
    private Long auditManId;

    @ApiModelProperty(value = "流向监管码",required = false)
    private String flowCode;

    /**
     * 拆零商品列表
     */
    @ApiModelProperty(value = "拆零列表",required = true)
    private List<RequestSaveGoodsVO> splitGoods;

    public Long getSplitManId() {
        return splitManId;
    }

    public void setSplitManId(Long splitManId) {
        this.splitManId = splitManId;
    }

    public Date getSplitDate() {
        return splitDate;
    }

    public void setSplitDate(Date splitDate) {
        this.splitDate = splitDate;
    }

    public Long getAuditManId() {
        return auditManId;
    }

    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public List<RequestSaveGoodsVO> getSplitGoods() {
        return splitGoods;
    }

    public void setSplitGoods(List<RequestSaveGoodsVO> splitGoods) {
        this.splitGoods = splitGoods;
    }
}
