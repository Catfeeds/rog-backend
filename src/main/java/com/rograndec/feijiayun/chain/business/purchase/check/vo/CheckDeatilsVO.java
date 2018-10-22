package com.rograndec.feijiayun.chain.business.purchase.check.vo;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zeshi.sun on 2017/9/20.
 */
public class CheckDeatilsVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID", required = true)
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID", required = true)
    private Long parentId;

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
     * 单据（验收单）类型（5213）
     */
    @ApiModelProperty(value = "单据（验收单）类型（5213）", required = true)
    private Integer orderType;

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
     * 检验项目集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "检验项目集合，多个用逗号分隔", required = true)
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
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量", required = true)
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量", required = true)
    private BigDecimal clearQuantity;

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


    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码", required = true)
    private String code;

    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称", required = true)
    private String genericName;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称", required = true)
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格", required = true)
    private String specification;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value = "库存计量单位名称", required = true)
    private String unitName;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商", required = true)
    private String manufacturer;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantityMain;


    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantityMain;


    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantityMain;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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

    public String getConclusionIds() {
        return conclusionIds;
    }

    public void setConclusionIds(String conclusionIds) {
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

    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getReceiveQuantityMain() {
        return receiveQuantityMain;
    }

    public void setReceiveQuantityMain(BigDecimal receiveQuantityMain) {
        this.receiveQuantityMain = receiveQuantityMain;
    }

    public BigDecimal getQualifiedQuantityMain() {
        return qualifiedQuantityMain;
    }

    public void setQualifiedQuantityMain(BigDecimal qualifiedQuantityMain) {
        this.qualifiedQuantityMain = qualifiedQuantityMain;
    }

    public BigDecimal getUnqualifiedQuantityMain() {
        return unqualifiedQuantityMain;
    }

    public void setUnqualifiedQuantityMain(BigDecimal unqualifiedQuantityMain) {
        this.unqualifiedQuantityMain = unqualifiedQuantityMain;
    }

    public String getCheckProjectNames() {
        return checkProjectNames;
    }

    public void setCheckProjectNames(String checkProjectNames) {
        this.checkProjectNames = checkProjectNames;
    }

    @Override
    public String toString() {
        return "CheckDeatilsVO[" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", checkDtlId=" + checkDtlId +
                ", checkId=" + checkId +
                ", orderType=" + orderType +
                ", goodsId=" + goodsId +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", checkProjectNames=" + checkProjectNames +
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
                ", unclearQuantity=" + unclearQuantity +
                ", clearQuantity=" + clearQuantity +
                ", lineNum=" + lineNum +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", code='" + code + '\'' +
                ", genericName='" + genericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", receiveQuantityMain=" + receiveQuantityMain +
                ", qualifiedQuantityMain=" + qualifiedQuantityMain +
                ", unqualifiedQuantityMain=" + unqualifiedQuantityMain +
                ']';
    }
}
