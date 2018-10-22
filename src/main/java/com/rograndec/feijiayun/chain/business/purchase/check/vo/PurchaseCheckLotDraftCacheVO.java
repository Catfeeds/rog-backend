package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseCheckLotDraftCacheVO implements Serializable {


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
    private String conclusionIds;

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

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

	
    /**
     * saas_purchase_check_lot
     */
    private static final long serialVersionUID = 1L;


    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 批号
     * @return lot_number 批号
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * 批号
     * @param lotNumber 批号
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
    }

    /**
     * 生产日期
     * @return product_date 生产日期
     */
    public Date getProductDate() {
        return productDate;
    }

    /**
     * 生产日期
     * @param productDate 生产日期
     */
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    /**
     * 有效期
     * @return valid_date 有效期
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * 有效期
     * @param validDate 有效期
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * 检验报告书（附件ID）集合
     * @return test_report_ids 检验报告书（附件ID）集合
     */
    public String getTestReportIds() {
        return testReportIds;
    }

    /**
     * 检验报告书（附件ID）集合
     * @param testReportIds 检验报告书（附件ID）集合
     */
    public void setTestReportIds(String testReportIds) {
        this.testReportIds = testReportIds == null ? null : testReportIds.trim();
    }

    /**
     * 检验项目ID集合，多个用逗号分隔
     * @return check_project_ids 检验项目ID集合，多个用逗号分隔
     */
    public String getCheckProjectIds() {
        return checkProjectIds;
    }

    /**
     * 检验项目ID集合，多个用逗号分隔
     * @param checkProjectIds 检验项目ID集合，多个用逗号分隔
     */
    public void setCheckProjectIds(String checkProjectIds) {
        this.checkProjectIds = checkProjectIds == null ? null : checkProjectIds.trim();
    }

    /**
     * 收货数量
     * @return receive_quantity 收货数量
     */
    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    /**
     * 收货数量
     * @param receiveQuantity 收货数量
     */
    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    /**
     * 抽样数量
     * @return sampling_quantity 抽样数量
     */
    public BigDecimal getSamplingQuantity() {
        return samplingQuantity;
    }

    /**
     * 抽样数量
     * @param samplingQuantity 抽样数量
     */
    public void setSamplingQuantity(BigDecimal samplingQuantity) {
        this.samplingQuantity = samplingQuantity;
    }

    /**
     * 验收合格数量
     * @return qualified_quantity 验收合格数量
     */
    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    /**
     * 验收合格数量
     * @param qualifiedQuantity 验收合格数量
     */
    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    /**
     * 验收结论ID集合，多个用逗号分隔
     * @return conclusion_ids 验收结论ID集合，多个用逗号分隔
     */
    public String getConclusionIds() {
        return conclusionIds;
    }

    /**
     * 验收结论ID集合，多个用逗号分隔
     * @param conclusionIds 验收结论ID集合，多个用逗号分隔
     */
    public void setConclusionIds(String conclusionIds) {
        this.conclusionIds = conclusionIds == null ? null : conclusionIds.trim();
    }

    /**
     * 验收不合格数量
     * @return unqualified_quantity 验收不合格数量
     */
    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    /**
     * 验收不合格数量
     * @param unqualifiedQuantity 验收不合格数量
     */
    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    /**
     * 验收不合格原因ID集合，多个用逗号分隔
     * @return unqualified_reason_ids 验收不合格原因ID集合，多个用逗号分隔
     */
    public String getUnqualifiedReasonIds() {
        return unqualifiedReasonIds;
    }

    /**
     * 验收不合格原因ID集合，多个用逗号分隔
     * @param unqualifiedReasonIds 验收不合格原因ID集合，多个用逗号分隔
     */
    public void setUnqualifiedReasonIds(String unqualifiedReasonIds) {
        this.unqualifiedReasonIds = unqualifiedReasonIds == null ? null : unqualifiedReasonIds.trim();
    }

    /**
     * 处置措施ID集合，多个用逗号分隔
     * @return measures_ids 处置措施ID集合，多个用逗号分隔
     */
    public String getMeasuresIds() {
        return measuresIds;
    }

    /**
     * 处置措施ID集合，多个用逗号分隔
     * @param measuresIds 处置措施ID集合，多个用逗号分隔
     */
    public void setMeasuresIds(String measuresIds) {
        this.measuresIds = measuresIds == null ? null : measuresIds.trim();
    }


    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }


    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


    @Override
    public String toString() {
        return "PurchaseCheckLotDraftCacheVO[" +
                " goodsId=" + goodsId +
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
                ", measuresIds='" + measuresIds + '\'' +
                ", lineNum=" + lineNum +
                ", remark='" + remark + '\'' +
                ']';
    }
}