package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ClickCheckHeadVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配退收货单id
     */
    @ApiModelProperty(value = "配退收货单id")
    private Long distrReturnReceiveId;

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;

    /**
     * 配货类型
     */
    @ApiModelProperty(value = "配货类型")
    private String distrTypeName;

    /**
     * 配货类型ID
     */
    @ApiModelProperty(value = "配货类型ID")
    private Integer distrType;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    private Date checkDate;

    /**
     * 验收人员1ID
     */
    @ApiModelProperty(value = "验收人员1ID")
    private Long checkerId;

    /**
     * 验收人员1
     */
    @ApiModelProperty(value = "验收人员1")
    private String checkerName;

    /**
     * 验收人员2ID
     */
    @ApiModelProperty(value = "验收人员2ID")
    private Long secondCheckerId;
    @ApiModelProperty(value = "验收人员2ID")
    private String secondCheckerName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（0-是  1-否）", required = true)
    private Integer specialDrug;

    public Long getDistrReturnReceiveId() {
        return distrReturnReceiveId;
    }

    public void setDistrReturnReceiveId(Long distrReturnReceiveId) {
        this.distrReturnReceiveId = distrReturnReceiveId;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode;
    }

    public String getRequestUnitName() {
        return requestUnitName;
    }

    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName;
    }

    public String getDistrTypeName() {
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        this.distrTypeName = distrTypeName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName;
    }

    @Override
    public String toString() {
        return "ClickCheckHeadVO[" +
                "distrReturnReceiveId=" + distrReturnReceiveId +
                ", requestUnitId=" + requestUnitId +
                ", requestUnitCode='" + requestUnitCode + '\'' +
                ", requestUnitName='" + requestUnitName + '\'' +
                ", distrTypeName='" + distrTypeName + '\'' +
                ", distrType=" + distrType +
                ", checkDate=" + checkDate +
                ", checkerId=" + checkerId +
                ", checkerName='" + checkerName + '\'' +
                ", remark='" + remark + '\'' +
                ']';
    }
}
