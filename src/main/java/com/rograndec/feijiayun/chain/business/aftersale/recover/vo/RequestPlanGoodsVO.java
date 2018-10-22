package com.rograndec.feijiayun.chain.business.aftersale.recover.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*
* @ClassName: RecoverPlanSaveOrupdateVO
* @Description:  售后管理-追回管理-追回计划-保存明细
* @author xingjian.lan
* @version 1.0
* @date 2017-10-16 17:51:12
*/
@ApiModel(value = "RequestPlanGoodsVO", description = "售后管理-追回管理-追回计划")
public class RequestPlanGoodsVO implements Serializable {

   private static final long serialVersionUID = 1L;

   /**
    * 商品ID
    */
   @ApiModelProperty(required = true, value = "商品ID")
   private Long goodsId;

    /**
     * 批号ID
     */
    @ApiModelProperty(required = true, value = "批号ID")
    private Long lotId;

   /**
    * 追回原因
    */
   @ApiModelProperty(required = true, value = "追回原因")
   private String recoverReason;

    /**
     * 行号
     */
    @ApiModelProperty(required = true, value = "行号")
   private Integer lineNum;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getRecoverReason() {
        return recoverReason;
    }

    public void setRecoverReason(String recoverReason) {
        this.recoverReason = recoverReason;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}