package com.rograndec.feijiayun.chain.common.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：查询处方登记列表参数
 * Created by ST on 2017/9/22 14:54
 */

public class RequestReportBaseParamVO extends RequestBaseParamVO {


    @ApiModelProperty("单号排序 0/倒序；1/正序;默认0")
    private Integer codeOrder;

    public Integer getCodeOrder() {
       return codeOrder == null ? 0 : codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }
}