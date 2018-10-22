package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_distr_req_plan
 * 
 * 
 * @author dongdong.zhang
 * 
 * 2017-11-30
 */
public class DistrReqPlanSumVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = " 商品ID")
    private Long goodsId;
    /**
     * 供货或调出单位ID
     */
    @ApiModelProperty(value = "库存可用数量")
    private BigDecimal sumQuantity;
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public BigDecimal getSumQuantity() {
		return sumQuantity;
	}
	public void setSumQuantity(BigDecimal sumQuantity) {
		this.sumQuantity = sumQuantity;
	}
}