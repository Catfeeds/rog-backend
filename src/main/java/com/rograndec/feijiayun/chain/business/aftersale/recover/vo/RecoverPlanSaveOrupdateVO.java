package com.rograndec.feijiayun.chain.business.aftersale.recover.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xingjian.lan
 * @version 1.0
 * @ClassName: RecoverPlanSaveOrupdateVO
 * @Description: 售后管理-追回管理-追回计划-Rest接口
 * @date 2017-10-16 17:51:12
 */
@ApiModel(value = "RecoverPlanSaveOrupdateVO", description = "追回计划保存/更新传参")
public class RecoverPlanSaveOrupdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 追回计划ID
     */
    @ApiModelProperty(required = false, value = "追回计划ID,修改时需传")
    private Long planId;

    /**
     * 通知日期
     */
    @ApiModelProperty(required = true, value = "通知日期")
    private Date planDate;

    /**
     * 通知人ID
     */
    @ApiModelProperty(required = true, value = "通知人ID")
    private Long planManId;

    /**
     * 追回责任人
     */
    @ApiModelProperty(required = true, value = "追回责任人")
    private String recoverMan;

    /**
     * 追回责任人电话
     */
    @ApiModelProperty(required = true, value = "追回责任人电话")
    private String recoverManPhone;

    /**
     * 商品列表
     */
    @ApiModelProperty(required = true, value = "商品列表")
    List<RequestPlanGoodsVO> goodsList;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Long getPlanManId() {
        return planManId;
    }

    public void setPlanManId(Long planManId) {
        this.planManId = planManId;
    }

    public String getRecoverMan() {
        return recoverMan;
    }

    public void setRecoverMan(String recoverMan) {
        this.recoverMan = recoverMan;
    }

    public String getRecoverManPhone() {
        return recoverManPhone;
    }

    public void setRecoverManPhone(String recoverManPhone) {
        this.recoverManPhone = recoverManPhone;
    }

    public List<RequestPlanGoodsVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<RequestPlanGoodsVO> goodsList) {
        this.goodsList = goodsList;
    }
}