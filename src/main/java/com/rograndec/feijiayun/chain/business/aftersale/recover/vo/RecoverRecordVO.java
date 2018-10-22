package com.rograndec.feijiayun.chain.business.aftersale.recover.vo;

import com.rograndec.feijiayun.chain.business.aftersale.recover.constant.RecoverHandleMeasuresEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverRecordDetail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xingjian.lan
 * @version 1.0
 * @ClassName: RecoverRecordVO
 * @Description: 售后管理-追回管理-追回记录
 * @date 2017-10-16 17:57:12
 */
@ApiModel(value = "RecoverRecordVO", description = "售后管理-追回管理-追回记录")
public class RecoverRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "主键ID")
    private Long id;

    /**
     * 单据编码
     */
    @ApiModelProperty(required = true, value = "单据编码")
    private String code;

    /**
     * 追回日期
     */
    @ApiModelProperty(required = false, value = "追回日期")
    private Date recoverDate;

    /**
     * 追回单位ID
     */
    @ApiModelProperty(required = false, value = "追回人员ID")
    private Long recoverUnitId;

    /**
     * 追回单位编码
     */
    @ApiModelProperty(required = false, value = "追回人员编码")
    private String recoverUnitCode;

    /**
     * 追回单位名称
     */
    @ApiModelProperty(required = false, value = "追回人员名称")
    private String recoverUnitName;

    /**
     * 追回人员ID
     */
    @ApiModelProperty(required = false, value = "追回人员ID")
    private Long recoverManId;

    /**
     * 追回人员编码
     */
    @ApiModelProperty(required = false, value = "追回人员编码")
    private String recoverManCode;

    /**
     * 追回人员名称
     */
    @ApiModelProperty(required = false, value = "追回人员名称")
    private String recoverManName;

    /**
     * 追回处理（0-退回供货单位；1-销毁）
     */
    @ApiModelProperty(required = false, value = "追回处理（0-退回供货单位；1-销毁）")
    private Integer handleMeasures;

    /**
     * 追回处理描述（0-退回供货单位；1-销毁）
     */
    @ApiModelProperty(required = false, value = "追回处理描述（0-退回供货单位；1-销毁）")
    private String handleMeasuresDesc;

    /**
     * 追回计划ID（基础单据ID）
     */
    @ApiModelProperty(value = "追回计划ID（基础单据ID）")
    private Long planId;

    /**
     * 追回记录明细
     */
    @ApiModelProperty(required = false, value = "追回记录明细")
    private List<RecoverRecordDetail> recordDetailList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getRecoverDate() {
        return recoverDate;
    }

    public void setRecoverDate(Date recoverDate) {
        this.recoverDate = recoverDate;
    }

    public Long getRecoverUnitId() {
        return recoverUnitId;
    }

    public void setRecoverUnitId(Long recoverUnitId) {
        this.recoverUnitId = recoverUnitId;
    }

    public String getRecoverUnitCode() {
        return recoverUnitCode;
    }

    public void setRecoverUnitCode(String recoverUnitCode) {
        this.recoverUnitCode = recoverUnitCode;
    }

    public String getRecoverUnitName() {
        return recoverUnitName;
    }

    public void setRecoverUnitName(String recoverUnitName) {
        this.recoverUnitName = recoverUnitName;
    }

    public Long getRecoverManId() {
        return recoverManId;
    }

    public void setRecoverManId(Long recoverManId) {
        this.recoverManId = recoverManId;
    }

    public String getRecoverManCode() {
        return recoverManCode;
    }

    public void setRecoverManCode(String recoverManCode) {
        this.recoverManCode = recoverManCode;
    }

    public String getRecoverManName() {
        return recoverManName;
    }

    public void setRecoverManName(String recoverManName) {
        this.recoverManName = recoverManName;
    }

    public Integer getHandleMeasures() {
        return handleMeasures;
    }

    public void setHandleMeasures(Integer handleMeasures) {
        this.handleMeasures = handleMeasures;
    }

    public String getHandleMeasuresDesc() {
        return (handleMeasures + 1) + "." + RecoverHandleMeasuresEnum.getName(handleMeasures);
    }

    public void setHandleMeasuresDesc(String handleMeasuresDesc) {
        this.handleMeasuresDesc = handleMeasuresDesc;
    }

    public List<RecoverRecordDetail> getRecordDetailList() {
        return recordDetailList;
    }

    public void setRecordDetailList(List<RecoverRecordDetail> recordDetailList) {
        this.recordDetailList = recordDetailList;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}