package com.rograndec.feijiayun.chain.business.purchase.check.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class PurchaseCheckLot implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 验收明细ID
     */
    private Long checkDtlId;

    /**
     * 验收单ID
     */
    private Long checkId;

    /**
     * 单据（验收单）类型（5213）
     */
    private Integer orderType;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 批号
     */
    private String lotNumber;

    /**
     * 生产日期
     */
    private Date productDate;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 检验报告书（附件ID）集合
     */
    private String testReportIds;

    /**
     * 检验项目ID集合，多个用逗号分隔
     */
    private String checkProjectIds;

    /**
     * 收货数量
     */
    private BigDecimal receiveQuantity;

    /**
     * 抽样数量
     */
    private BigDecimal samplingQuantity;

    /**
     * 验收合格数量
     */
    private BigDecimal qualifiedQuantity;

    /**
     * 验收结论ID集合，多个用逗号分隔
     */
    private String conclusionIds;

    /**
     * 验收不合格数量
     */
    private BigDecimal unqualifiedQuantity;

    /**
     * 验收不合格原因ID集合，多个用逗号分隔
     */
    private String unqualifiedReasonIds;

    /**
     * 处置措施ID集合，多个用逗号分隔
     */
    private String measuresIds;

    /**
     * 未清数量
     */
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    private Integer lineNum;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    
    /**
     * 冗余3个字段 --- 采购入库时用
     */
	@ApiModelProperty(required = true, value = "合格品货位货位ID")
    private Long qualifiedShelfId;
	@ApiModelProperty(required = true, value = "不合格品货位货位ID")
    private Long unqualifiedShelfId;
	@ApiModelProperty(value = "质量状况：0-合格品，2不合格品" )
    private Integer jobArea;
	
    /**
     * saas_purchase_check_lot
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 验收明细ID
     * @return check_dtl_id 验收明细ID
     */
    public Long getCheckDtlId() {
        return checkDtlId;
    }

    /**
     * 验收明细ID
     * @param checkDtlId 验收明细ID
     */
    public void setCheckDtlId(Long checkDtlId) {
        this.checkDtlId = checkDtlId;
    }

    /**
     * 验收单ID
     * @return check_id 验收单ID
     */
    public Long getCheckId() {
        return checkId;
    }

    /**
     * 验收单ID
     * @param checkId 验收单ID
     */
    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    /**
     * 单据（验收单）类型（5213）
     * @return order_type 单据（验收单）类型（5213）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据（验收单）类型（5213）
     * @param orderType 单据（验收单）类型（5213）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

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
     * 未清数量
     * @return unclear_quantity 未清数量
     */
    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    /**
     * 未清数量
     * @param unclearQuantity 未清数量
     */
    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    /**
     * 已清数量
     * @return clear_quantity 已清数量
     */
    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    /**
     * 已清数量
     * @param clearQuantity 已清数量
     */
    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
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
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Long getQualifiedShelfId() {
		return qualifiedShelfId;
	}

	public void setQualifiedShelfId(Long qualifiedShelfId) {
		this.qualifiedShelfId = qualifiedShelfId;
	}

	public Long getUnqualifiedShelfId() {
		return unqualifiedShelfId;
	}

	public void setUnqualifiedShelfId(Long unqualifiedShelfId) {
		this.unqualifiedShelfId = unqualifiedShelfId;
	}

	public Integer getJobArea() {
		return jobArea;
	}

	public void setJobArea(Integer jobArea) {
		this.jobArea = jobArea;
	}

	/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", checkDtlId=").append(checkDtlId);
        sb.append(", checkId=").append(checkId);
        sb.append(", orderType=").append(orderType);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", productDate=").append(productDate);
        sb.append(", validDate=").append(validDate);
        sb.append(", testReportIds=").append(testReportIds);
        sb.append(", checkProjectIds=").append(checkProjectIds);
        sb.append(", receiveQuantity=").append(receiveQuantity);
        sb.append(", samplingQuantity=").append(samplingQuantity);
        sb.append(", qualifiedQuantity=").append(qualifiedQuantity);
        sb.append(", conclusionIds=").append(conclusionIds);
        sb.append(", unqualifiedQuantity=").append(unqualifiedQuantity);
        sb.append(", unqualifiedReasonIds=").append(unqualifiedReasonIds);
        sb.append(", measuresIds=").append(measuresIds);
        sb.append(", unclearQuantity=").append(unclearQuantity);
        sb.append(", clearQuantity=").append(clearQuantity);
        sb.append(", lineNum=").append(lineNum);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}