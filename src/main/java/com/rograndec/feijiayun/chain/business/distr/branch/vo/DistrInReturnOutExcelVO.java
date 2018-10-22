package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInReturnStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_in_return_out
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-10
 */
public class DistrInReturnOutExcelVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 购进退出单id
     */
    @ApiModelProperty(value = "购进退出单id")
    private Long returnId;

    /**
     * 配进退出出库单号
     */
    @ApiModelProperty(value = "配进退出出库单号")
    private String code;

    /**
     * 配进退出出库日期
     */
    @ApiModelProperty(value = "配进退出出库日期")
    private Date outDate;

    /**
     * 配货单位ID
     */
    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    /**
     * 配货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 配货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     *配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private String distrTypeDesc;

    /**
     * 出库人员ID
     */
    @ApiModelProperty(value = "出库人员ID")
    private Long outManId;

    /**
     * 出库人员编码
     */
    @ApiModelProperty(value = "出库人员编码")
    private String outManCode;

    /**
     * 出库人员名称
     */
    @ApiModelProperty(value = "出库人员名称")
    private String outManName;


    /**
     * 复核人员ID
     */
    @ApiModelProperty(value = "复核人员ID")
    private Long auditManId;

    /**
     * 复核人员编码
     */
    @ApiModelProperty(value = "复核人员编码")
    private String auditManCode;

    /**
     * 复核人员名称
     */
    @ApiModelProperty(value = "复核人员名称")
    private String auditManName;

    @ApiModelProperty(value = "复核原因")
    private String auditReason;

    @ApiModelProperty(value = "复核时间")
    private Date auditDate;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态描述")
    private String statusDesc;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private List<DistrInReturnOutDetailExcelVO> distrInReturnOutDetailFormVOS = new ArrayList<>();



    public static DistrInReturnOutExcelVO getDistrInReturnOutPageVO(DistrInReturnOutPageVO distrInReturnOut){

        DistrInReturnOutExcelVO distrInReturnOutPageVO = new DistrInReturnOutExcelVO();

        /**
         * 配进退出出库单号
         */
        distrInReturnOutPageVO.setCode(distrInReturnOut.getCode());

        /**
         * 配进退出出库日期
         */
        distrInReturnOutPageVO.setOutDate(distrInReturnOut.getOutDate());

        /**
         * 配货单位ID
         */
        distrInReturnOutPageVO.setDistrUnitId(distrInReturnOut.getDistrUnitId());

        /**
         * 配货单位编码
         */
        distrInReturnOutPageVO.setDistrUnitCode(distrInReturnOut.getDistrUnitCode());

        /**
         * 配货单位名称
         */
        distrInReturnOutPageVO.setDistrUnitName(distrInReturnOut.getDistrUnitName());

        /**
         * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
         */
        distrInReturnOutPageVO.setDistrType(distrInReturnOut.getDistrType());
        distrInReturnOutPageVO.setDistrTypeDesc(DistrType.getValue(distrInReturnOut.getDistrType()));

        /**
         * 出库人员ID
         */
        distrInReturnOutPageVO.setOutManId(distrInReturnOut.getOutManId());

        /**
         * 出库人员编码
         */
        distrInReturnOutPageVO.setOutManCode(distrInReturnOut.getOutManCode());

        /**
         * 出库人员名称
         */
        distrInReturnOutPageVO.setOutManName(distrInReturnOut.getOutManName());

        /**
         * 复核人员ID
         */
        distrInReturnOutPageVO.setAuditManId(distrInReturnOut.getAuditManId());

        /**
         * 复核人员编码
         */
        distrInReturnOutPageVO.setAuditManCode(distrInReturnOut.getAuditManCode());

        /**
         * 复核人员名称
         */
        distrInReturnOutPageVO.setAuditManName(distrInReturnOut.getAuditManName());

        distrInReturnOutPageVO.setAuditReason(distrInReturnOut.getAuditReason());

        distrInReturnOutPageVO.setAuditDate(distrInReturnOut.getAuditDate());

        /**
         * 数量合计
         */
        distrInReturnOutPageVO.setQuantityTotal(distrInReturnOut.getQuantityTotal());

        /**
         * 品种数量
         */
        distrInReturnOutPageVO.setVarietiesQuantity(distrInReturnOut.getVarietiesQuantity());

        /**
         * 金额合计（整单优惠前的金额合计）
         */
        distrInReturnOutPageVO.setAmountTotal(distrInReturnOut.getAmountTotal());

        /**
         * 整单折扣（%）
         */
        distrInReturnOutPageVO.setWholeDiscount(distrInReturnOut.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        distrInReturnOutPageVO.setWholeDiscountAmount(distrInReturnOut.getWholeDiscountAmount());

        /**
         * 实际金额合计
         */
        distrInReturnOutPageVO.setRealAmountTotal(distrInReturnOut.getRealAmountTotal());

        /**
         * 不含税金额合计
         */
        distrInReturnOutPageVO.setNotaxRealAmountTotal(distrInReturnOut.getNotaxRealAmountTotal());

        /**
         * 税额合计
         */
        distrInReturnOutPageVO.setTaxAmountTotal(distrInReturnOut.getTaxAmountTotal());

        /**
         * 状态
         */
        distrInReturnOutPageVO.setStatus(distrInReturnOut.getStatus());
        distrInReturnOutPageVO.setStatusDesc(DistrInReturnStatus.getStatusDesc(distrInReturnOut.getStatus()));

        /**
         * 备注
         */
        distrInReturnOutPageVO.setRemark(distrInReturnOut.getRemark());

        return distrInReturnOutPageVO;
    }

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

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Long getDistrUnitId() {
        return distrUnitId;
    }

    public void setDistrUnitId(Long distrUnitId) {
        this.distrUnitId = distrUnitId;
    }

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getDistrTypeDesc() {
        return distrTypeDesc;
    }

    public void setDistrTypeDesc(String distrTypeDesc) {
        this.distrTypeDesc = distrTypeDesc;
    }

    public Long getOutManId() {
        return outManId;
    }

    public void setOutManId(Long outManId) {
        this.outManId = outManId;
    }

    public String getOutManCode() {
        return outManCode;
    }

    public void setOutManCode(String outManCode) {
        this.outManCode = outManCode;
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public Long getAuditManId() {
        return auditManId;
    }

    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }

    public String getAuditManCode() {
        return auditManCode;
    }

    public void setAuditManCode(String auditManCode) {
        this.auditManCode = auditManCode;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DistrInReturnOutDetailExcelVO> getDistrInReturnOutDetailFormVOS() {
        return distrInReturnOutDetailFormVOS;
    }

    public void setDistrInReturnOutDetailFormVOS(List<DistrInReturnOutDetailExcelVO> distrInReturnOutDetailFormVOS) {
        this.distrInReturnOutDetailFormVOS = distrInReturnOutDetailFormVOS;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}