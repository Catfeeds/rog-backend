package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zeshi.sun on 2017/9/19.
 */
public class SaveCheckDetailTwoVO implements Serializable {


    /**
     * 验收明细ID
     */
    @ApiModelProperty(value = "验收明细ID", required = true)
    private Long checkDtlId;

    /**
     * 验收单ID
     */
    @ApiModelProperty(value = "验收单ID", required = true)
    private Long checkId;


    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号", required = true)
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期", required = true)
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期", required = true)
    private Date validDate;

    /**
     * 检验报告书（附件ID）集合
     */
    @ApiModelProperty(value = "检验报告书（附件ID）集合", required = true)
    private String testReportIds;

    /**
     * 检验项目ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "检验项目ID集合，多个用逗号分隔", required = true)
    private String checkProjectIds;

    /**
     * 检验项目ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "检验项目ID集合，多个用逗号分隔(用于保存草稿使用)", required = true)
    private String checkProjectNames;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantity;

    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽样数量", required = true)
    private BigDecimal samplingQuantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;

    /**
     * 验收结论ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收结论ID集合，多个用逗号分隔", required = true)
    private Integer conclusionIds;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;

    /**
     * 验收不合格原因ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收不合格原因ID集合，多个用逗号分隔", required = true)
    private String unqualifiedReasonIds;

    /**
     * 处置措施ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "处置措施ID集合，多个用逗号分隔", required = true)
    private String measuresIds;

//    /**
//     * 未清数量
//     */
//    @ApiModelProperty(value = "未清数量", required = true)
//    private BigDecimal unclearQuantity;
//
//    /**
//     * 已清数量
//     */
//    @ApiModelProperty(value = "已清数量", required = true)
//    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


    public Long getCheckDtlId() {
        return checkDtlId;
    }

    public void setCheckDtlId(Long checkDtlId) {
        this.checkDtlId = checkDtlId;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getTestReportIds() {
        return testReportIds;
    }

    public void setTestReportIds(String testReportIds) {
        this.testReportIds = testReportIds;
    }

    public String getCheckProjectIds() {
        return checkProjectIds;
    }

    public void setCheckProjectIds(String checkProjectIds) {
        this.checkProjectIds = checkProjectIds;
    }

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getSamplingQuantity() {
        return samplingQuantity;
    }

    public void setSamplingQuantity(BigDecimal samplingQuantity) {
        this.samplingQuantity = samplingQuantity;
    }

    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public Integer getConclusionIds() {
        return conclusionIds;
    }

    public void setConclusionIds(Integer conclusionIds) {
        this.conclusionIds = conclusionIds;
    }

    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public String getUnqualifiedReasonIds() {
        return unqualifiedReasonIds;
    }

    public void setUnqualifiedReasonIds(String unqualifiedReasonIds) {
        this.unqualifiedReasonIds = unqualifiedReasonIds;
    }

    public String getMeasuresIds() {
        return measuresIds;
    }

    public void setMeasuresIds(String measuresIds) {
        this.measuresIds = measuresIds;
    }

//    public BigDecimal getUnclearQuantity() {
//        return unclearQuantity;
//    }
//
//    public void setUnclearQuantity(BigDecimal unclearQuantity) {
//        this.unclearQuantity = unclearQuantity;
//    }
//
//    public BigDecimal getClearQuantity() {
//        return clearQuantity;
//    }
//
//    public void setClearQuantity(BigDecimal clearQuantity) {
//        this.clearQuantity = clearQuantity;
//    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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

    public String getCheckProjectNames() {
        return checkProjectNames;
    }

    public void setCheckProjectNames(String checkProjectNames) {
        this.checkProjectNames = checkProjectNames;
    }

    @Override
    public String toString() {
        return "SaveCheckDetailTwoVO[" +
                "checkDtlId=" + checkDtlId +
                ", checkId=" + checkId +
                ", goodsId=" + goodsId +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", testReportIds='" + testReportIds + '\'' +
                ", checkProjectIds='" + checkProjectIds + '\'' +
                ", receiveQuantity=" + receiveQuantity +
                ", samplingQuantity=" + samplingQuantity +
                ", qualifiedQuantity=" + qualifiedQuantity +
                ", conclusionIds='" + conclusionIds + '\'' +
                ", unqualifiedQuantity=" + unqualifiedQuantity +
                ", unqualifiedReasonIds='" + unqualifiedReasonIds + '\'' +
                ", checkProjectNames='" + checkProjectNames + '\'' +
                ", measuresIds='" + measuresIds + '\'' +
//                ", unclearQuantity=" + unclearQuantity +
//                ", clearQuantity=" + clearQuantity +
                ", lineNum=" + lineNum +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ']';
    }
}
