package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/30 <br/>
 * 描述：配送单调用其它单据生成配送单保存传参
 */
public class RequestBaseOrderVO {
    @ApiModelProperty(value = "要货单位ID",required = true)
    private Long requestUnitId;

    @ApiModelProperty(value = "基础单据明细",required = true)
    private Long baseOrderDtlId;

    @ApiModelProperty(value = "配货数量",required = true)
    private BigDecimal quantity;

    @ApiModelProperty(value = "行号",required = true)
    private Integer lineNum;

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}
