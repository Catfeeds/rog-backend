package com.rograndec.feijiayun.chain.business.aftersale.recover.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xingjian.lan
 * @version 1.0
 * @ClassName: RecoverRecordSaveOrupdateVO
 * @Description: 售后管理-追回管理-追回记录-Rest接口
 * @date 2017-10-16 17:57:12
 */
@ApiModel(value = "RecoverRecordSaveOrupdateVO", description = "售后管理-追回管理-追回记录")
public class RecoverRecordSaveOrupdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 追回记录ID
     */
    @ApiModelProperty(required = true, value = "追回记录ID,修改时使用")
    private Long recoverId;

    /**
     * 追回计划ID(作为追回记录基础单据)
     */
    @ApiModelProperty(required = true, value = "追回计划ID(作为追回记录基础单据)")
    private Long baseOrderId;

    /**
     * 追回日期
     */
    @ApiModelProperty(required = false, value = "追回日期")
    private Date recoverDate;

    /**
     * 追回人员ID
     */
    @ApiModelProperty(required = false, value = "追回人员ID")
    private Long recoverManId;

    /**
     * 追回处理（0-退回供货单位；1-销毁）
     */
    @ApiModelProperty(required = false, value = "追回处理（0-退回供货单位；1-销毁）")
    private Integer handleMeasures;

    /**
     * 追回单位ID
     */
    @ApiModelProperty(required = false, value = "追回单位ID")
    private Long recoverUnitId;

    private List<RequestRecordGoodsVO> recordGoodsList;

    public Long getRecoverId() {
        return recoverId;
    }

    public void setRecoverId(Long recoverId) {
        this.recoverId = recoverId;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Date getRecoverDate() {
        return recoverDate;
    }

    public void setRecoverDate(Date recoverDate) {
        this.recoverDate = recoverDate;
    }

    public Long getRecoverManId() {
        return recoverManId;
    }

    public void setRecoverManId(Long recoverManId) {
        this.recoverManId = recoverManId;
    }

    public Integer getHandleMeasures() {
        return handleMeasures;
    }

    public void setHandleMeasures(Integer handleMeasures) {
        this.handleMeasures = handleMeasures;
    }

    public Long getRecoverUnitId() {
        return recoverUnitId;
    }

    public void setRecoverUnitId(Long recoverUnitId) {
        this.recoverUnitId = recoverUnitId;
    }

    public List<RequestRecordGoodsVO> getRecordGoodsList() {
        return recordGoodsList;
    }

    public void setRecordGoodsList(List<RequestRecordGoodsVO> recordGoodsList) {
        this.recordGoodsList = recordGoodsList;
    }
}