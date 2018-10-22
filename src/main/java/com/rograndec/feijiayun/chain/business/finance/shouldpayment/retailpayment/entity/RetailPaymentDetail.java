package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_retail_payment_detail
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailPaymentDetail implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 缴款清单ID
     */
    @ApiModelProperty(value = "缴款清单ID")
    private Long itemId;

    /**
     * 缴款单ID
     */
    @ApiModelProperty(value = "缴款单ID")
    private Long docId;

    /**
     * 交班单据ID
     */
    @ApiModelProperty(value = "交班单据ID")
    private Long shiftId;

    /**
     * 交班人员ID
     */
    @ApiModelProperty(value = "交班人员ID")
    private Long shiftManId;

    /**
     * 交班人编码
     */
    @ApiModelProperty(value = "交班人编码")
    private String shiftManCode;

    /**
     * 交班人名称
     */
    @ApiModelProperty(value = "交班人名称")
    private String shiftManName;

    /**
     * 应缴现金
     */
    @ApiModelProperty(value = "应缴现金")
    private BigDecimal payableCash;

    /**
     * 实缴现金
     */
    @ApiModelProperty(value = "实缴现金")
    private BigDecimal cash;

    /**
     * 现金差额
     */
    @ApiModelProperty(value = "现金差额")
    private BigDecimal diffCash;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待缴款；1-已缴款；2-已冲销）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_retail_payment_detail
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
     * 缴款清单ID
     * @return item_id 缴款清单ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 缴款清单ID
     * @param itemId 缴款清单ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 缴款单ID
     * @return doc_id 缴款单ID
     */
    public Long getDocId() {
        return docId;
    }

    /**
     * 缴款单ID
     * @param docId 缴款单ID
     */
    public void setDocId(Long docId) {
        this.docId = docId;
    }

    /**
     * 交班单据ID
     * @return shift_id 交班单据ID
     */
    public Long getShiftId() {
        return shiftId;
    }

    /**
     * 交班单据ID
     * @param shiftId 交班单据ID
     */
    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    /**
     * 交班人员ID
     * @return shift_man_id 交班人员ID
     */
    public Long getShiftManId() {
        return shiftManId;
    }

    /**
     * 交班人员ID
     * @param shiftManId 交班人员ID
     */
    public void setShiftManId(Long shiftManId) {
        this.shiftManId = shiftManId;
    }

    /**
     * 交班人编码
     * @return shift_man_code 交班人编码
     */
    public String getShiftManCode() {
        return shiftManCode;
    }

    /**
     * 交班人编码
     * @param shiftManCode 交班人编码
     */
    public void setShiftManCode(String shiftManCode) {
        this.shiftManCode = shiftManCode == null ? null : shiftManCode.trim();
    }

    /**
     * 交班人名称
     * @return shift_man_name 交班人名称
     */
    public String getShiftManName() {
        return shiftManName;
    }

    /**
     * 交班人名称
     * @param shiftManName 交班人名称
     */
    public void setShiftManName(String shiftManName) {
        this.shiftManName = shiftManName == null ? null : shiftManName.trim();
    }

    /**
     * 应缴现金
     * @return payable_cash 应缴现金
     */
    public BigDecimal getPayableCash() {
        return payableCash;
    }

    /**
     * 应缴现金
     * @param payableCash 应缴现金
     */
    public void setPayableCash(BigDecimal payableCash) {
        this.payableCash = payableCash;
    }

    /**
     * 实缴现金
     * @return cash 实缴现金
     */
    public BigDecimal getCash() {
        return cash;
    }

    /**
     * 实缴现金
     * @param cash 实缴现金
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    /**
     * 现金差额
     * @return diff_cash 现金差额
     */
    public BigDecimal getDiffCash() {
        return diffCash;
    }

    /**
     * 现金差额
     * @param diffCash 现金差额
     */
    public void setDiffCash(BigDecimal diffCash) {
        this.diffCash = diffCash;
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
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     * @return status 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     * @param status 状态（0-待缴款；1-已缴款；2-已冲销）
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
        sb.append(", itemId=").append(itemId);
        sb.append(", docId=").append(docId);
        sb.append(", shiftId=").append(shiftId);
        sb.append(", shiftManId=").append(shiftManId);
        sb.append(", shiftManCode=").append(shiftManCode);
        sb.append(", shiftManName=").append(shiftManName);
        sb.append(", payableCash=").append(payableCash);
        sb.append(", cash=").append(cash);
        sb.append(", diffCash=").append(diffCash);
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