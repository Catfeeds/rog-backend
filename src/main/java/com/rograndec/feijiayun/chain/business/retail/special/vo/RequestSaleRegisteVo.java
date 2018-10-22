package com.rograndec.feijiayun.chain.business.retail.special.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <零售管理-专管登记-待登记销售单列表查询param>
 *
 * @Author: Zhengbin.jin 金正斌
 * @Email: Zhengbin.jin@rograndec.com
 * @2017/9/22 18:11
 */
@ApiModel(value = "RequestSaleRegisteVo", description = "零售管理-专管登记-补登")
public class RequestSaleRegisteVo implements Serializable{
    @NotNull(message = "销售单id不能为空")
    @ApiModelProperty(value = "销售单ID", required = true)
    private Long saleId;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

}
