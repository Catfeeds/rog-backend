package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.status.DistrSendStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: DistrListVO
 * @date 2017-10-07 15:57:42
 */
@ApiModel(value = "DistrListVO", description = "总部- 配送单/配货出库单")
public class DistrListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "主键ID")
    private Long id;

    @ApiModelProperty(required = true, value = "日期")
    private Date date;

    @ApiModelProperty(required = true, value = "单号")
    private String code;

    @ApiModelProperty(required = false, value = "要货单位ID")
    private Long requestUnitId;

    @ApiModelProperty(required = false, value = "要货单位编码")
    private String requestUnitCode;
    /**
     * 要货单位名称
     */
    @ApiModelProperty(required = false, value = "要货单位名称")
    private String requestUnitName;

    @ApiModelProperty(required = false, value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    @ApiModelProperty(required = false, value = "配送类型描述")
    private String distrTypeStr;
    /**
     * 配货人员名称
     */
    @ApiModelProperty(required = false, value = "配货人员名称")
    private String sendManName;

    @ApiModelProperty(required = true, value = "状态 31-待出库 32-待复核 33-已完成")
    private Integer status;

    @ApiModelProperty(required = true, value = "状态描述")
    private String statusStr;
    /**
     * 实际金额合计
     */
    @ApiModelProperty(required = true, value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(required = true, value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(required = true, value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 出库人员名称
     */
    @ApiModelProperty(required = true, value = "出库人员名称")
    private String outManName;

    /**
     * 复核人员名称
     */
    @ApiModelProperty(required = false, value = "复核人员名称")
    private String checkerName;

    /**
     * 结算单位是否是加盟店（0-否  1-是）
     */
    @ApiModelProperty(required = true, value = "结算单位是否是加盟店（0-否  1-是）")
    private Integer franchisedStoreFlag;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getDistrTypeStr() {
        if (this.distrType == null) return null;
        String desc = DistributionType.getName(this.distrType);
        if (desc.equals("未知类型的状态")) {
            return DistributionType.getName(this.distrType);
        }
        return desc;
    }

    public void setDistrTypeStr(String distrTypeStr) {
        this.distrTypeStr = distrTypeStr;
    }

    public String getSendManName() {
        return sendManName;
    }

    public void setSendManName(String sendManName) {
        this.sendManName = sendManName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (this.status == null) return null;
        String desc = DistrSendStatus.getStatusDesc(this.status);
        if (desc.equals("未知类型的状态")) {
            return DistrSendStatus.getStatusDesc(this.status);
        }
        return desc;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public Integer getFranchisedStoreFlag() {
        return franchisedStoreFlag;
    }

    public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
        this.franchisedStoreFlag = franchisedStoreFlag;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }
}