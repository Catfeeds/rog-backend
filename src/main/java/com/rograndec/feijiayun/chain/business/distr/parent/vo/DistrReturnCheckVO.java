package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class DistrReturnCheckVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期", required = true)
    private Date checkDate;

    /**
     * 单号
     */
    @ApiModelProperty(value = "单号", required = true)
    private String code;

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID", required = true)
    private Long requestUnitId;


    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码", required = true)
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称", required = true)
    private String requestUnitName;

    /**
     * 配货类型ID
     */
    @ApiModelProperty(value = "配货类型ID", required = true)
    private Integer distrType;

    /**
     * 配货类型
     */
    @ApiModelProperty(value = "配货类型", required = true)
    private String distrTypeName;

    /**
     * 验收人员1
     */
    @ApiModelProperty(value = "验收人员1", required = true)
    private String checkerName;

    /**
     * 验收人员2
     */
    @ApiModelProperty(value = "验收人员2", required = true)
    private String secondCheckerName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private String status;

    /**
     * 结算单位是否是加盟店（0-否  1-是）
     */
    @ApiModelProperty(required = true, value = "结算单位是否是加盟店（0-否  1-是）")
    private Integer franchisedStoreFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public Integer getFranchisedStoreFlag() {
        return franchisedStoreFlag;
    }

    public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
        this.franchisedStoreFlag = franchisedStoreFlag;
    }

    @Override
    public String toString() {
        return "DistrReturnCheckVO[" +
                "id=" + id +
                ", checkDate=" + checkDate +
                ", code='" + code + '\'' +
                ", distrType='" + distrType + '\'' +
                ", requestUnitCode='" + requestUnitCode + '\'' +
                ", requestUnitName='" + requestUnitName + '\'' +
                ", distrTypeName='" + distrTypeName + '\'' +
                ", checkerName='" + checkerName + '\'' +
                ", requestUnitId='" + requestUnitId + '\'' +
                ", secondCheckerName='" + secondCheckerName + '\'' +
                ", status=" + status +
                ", franchisedStoreFlag=" + franchisedStoreFlag +
                ']';
    }
}
