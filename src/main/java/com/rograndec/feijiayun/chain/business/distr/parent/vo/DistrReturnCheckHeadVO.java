package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrReturnCheckHeadVO implements Serializable {

    /**
     * 配退验收单id
     */
    @ApiModelProperty(value = "配退验收单id")
    private Long id;

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
     * 配后退回验收单号
     */
    @ApiModelProperty(value = "配后退回验收单号")
    private String code;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    private Date checkDate;

    /**
     * 验收人员1
     */
    @ApiModelProperty(value = "验收人员1")
    private String checkerName;

    /**
     * 验收人员2
     */
    @ApiModelProperty(value = "验收人员2")
    private String secondCheckerName;

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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 打印接口需要添加的参数
     */
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    /**
     * 合计收货数量
     */
    @ApiModelProperty(value = "合计收货数量")
    private BigDecimal receiveQuantityTotal;

    /**
     * 合计验收合格数量
     */
    @ApiModelProperty(value = "合计验收合格数量")
    private BigDecimal qualifiedQuantityTotal;

    /**
     *合计验收不合格数量
     */
    @ApiModelProperty(value = "合计验收不合格数量")
    private BigDecimal unqualifiedQuantityTotal;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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

    public String getDistrTypeName() {
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        this.distrTypeName = distrTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public BigDecimal getReceiveQuantityTotal() {
        return receiveQuantityTotal;
    }

    public void setReceiveQuantityTotal(BigDecimal receiveQuantityTotal) {
        this.receiveQuantityTotal = receiveQuantityTotal;
    }

    public BigDecimal getQualifiedQuantityTotal() {
        return qualifiedQuantityTotal;
    }

    public void setQualifiedQuantityTotal(BigDecimal qualifiedQuantityTotal) {
        this.qualifiedQuantityTotal = qualifiedQuantityTotal;
    }

    public BigDecimal getUnqualifiedQuantityTotal() {
        return unqualifiedQuantityTotal;
    }

    public void setUnqualifiedQuantityTotal(BigDecimal unqualifiedQuantityTotal) {
        this.unqualifiedQuantityTotal = unqualifiedQuantityTotal;
    }

    @Override
    public String toString() {
        return "DistrReturnCheckHeadVO[" +
                "id=" + id +
                ", requestUnitCode='" + requestUnitCode + '\'' +
                ", requestUnitName='" + requestUnitName + '\'' +
                ", code='" + code + '\'' +
                ", checkDate=" + checkDate +
                ", distrType=" + distrType +
                ", checkerName='" + checkerName + '\'' +
                ", secondCheckerName='" + secondCheckerName + '\'' +
                ", distrTypeName='" + distrTypeName + '\'' +
                ", remark='" + remark + '\'' +
                ']';
    }
}
