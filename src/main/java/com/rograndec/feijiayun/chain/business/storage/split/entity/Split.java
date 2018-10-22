package com.rograndec.feijiayun.chain.business.storage.split.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_split
 * 
 * 
 * @author lanxj
 * 
 * 2017-09-29
 */
public class Split implements Serializable {
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
     * 单据类型（5307）
     */
    @ApiModelProperty(value = "单据类型（5307）")
    private Integer orderType;

    /**
     * 拆零日期
     */
    @ApiModelProperty(value = "拆零日期")
    private Date splitDate;

    /**
     * 拆零单号
     */
    @ApiModelProperty(value = "拆零单号")
    private String code;

    /**
     * 拆零人员ID
     */
    @ApiModelProperty(value = "拆零人员ID")
    private Long splitManId;

    /**
     * 拆零人员编码
     */
    @ApiModelProperty(value = "拆零人员编码")
    private String splitManCode;

    /**
     * 拆零人员名称
     */
    @ApiModelProperty(value = "拆零人员名称")
    private String splitManName;

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

    /**
     * 流向监管码
     */
    @ApiModelProperty(value = "流向监管码")
    private String flowCode;

    /**
     * 整盒商品数量合计
     */
    @ApiModelProperty(value = "整盒商品数量合计")
    private BigDecimal quantityTotal;

    /**
     * 整盒商品品种数量
     */
    @ApiModelProperty(value = "整盒商品品种数量")
    private Integer varietiesQuantity;

    /**
     * 拆零商品数量合计
     */
    @ApiModelProperty(value = "拆零商品数量合计")
    private BigDecimal splitQuantityTotal;

    /**
     * 拆零商品品种数量
     */
    @ApiModelProperty(value = "拆零商品品种数量")
    private Integer splitVarietiesQuantity;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

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
     * saas_split
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
     * 单据类型（5307）
     * @return order_type 单据类型（5307）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5307）
     * @param orderType 单据类型（5307）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 拆零日期
     * @return split_date 拆零日期
     */
    public Date getSplitDate() {
        return splitDate;
    }

    /**
     * 拆零日期
     * @param splitDate 拆零日期
     */
    public void setSplitDate(Date splitDate) {
        this.splitDate = splitDate;
    }

    /**
     * 拆零单号
     * @return code 拆零单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 拆零单号
     * @param code 拆零单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 拆零人员ID
     * @return split_man_id 拆零人员ID
     */
    public Long getSplitManId() {
        return splitManId;
    }

    /**
     * 拆零人员ID
     * @param splitManId 拆零人员ID
     */
    public void setSplitManId(Long splitManId) {
        this.splitManId = splitManId;
    }

    /**
     * 拆零人员编码
     * @return split_man_code 拆零人员编码
     */
    public String getSplitManCode() {
        return splitManCode;
    }

    /**
     * 拆零人员编码
     * @param splitManCode 拆零人员编码
     */
    public void setSplitManCode(String splitManCode) {
        this.splitManCode = splitManCode == null ? null : splitManCode.trim();
    }

    /**
     * 拆零人员名称
     * @return split_man_name 拆零人员名称
     */
    public String getSplitManName() {
        return splitManName;
    }

    /**
     * 拆零人员名称
     * @param splitManName 拆零人员名称
     */
    public void setSplitManName(String splitManName) {
        this.splitManName = splitManName == null ? null : splitManName.trim();
    }

    /**
     * 复核人员ID
     * @return audit_man_id 复核人员ID
     */
    public Long getAuditManId() {
        return auditManId;
    }

    /**
     * 复核人员ID
     * @param auditManId 复核人员ID
     */
    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }

    /**
     * 复核人员编码
     * @return audit_man_code 复核人员编码
     */
    public String getAuditManCode() {
        return auditManCode;
    }

    /**
     * 复核人员编码
     * @param auditManCode 复核人员编码
     */
    public void setAuditManCode(String auditManCode) {
        this.auditManCode = auditManCode == null ? null : auditManCode.trim();
    }

    /**
     * 复核人员名称
     * @return audit_man_name 复核人员名称
     */
    public String getAuditManName() {
        return auditManName;
    }

    /**
     * 复核人员名称
     * @param auditManName 复核人员名称
     */
    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName == null ? null : auditManName.trim();
    }

    /**
     * 流向监管码
     * @return flow_code 流向监管码
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 流向监管码
     * @param flowCode 流向监管码
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
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

    public BigDecimal getSplitQuantityTotal() {
        return splitQuantityTotal;
    }

    public void setSplitQuantityTotal(BigDecimal splitQuantityTotal) {
        this.splitQuantityTotal = splitQuantityTotal;
    }

    public Integer getSplitVarietiesQuantity() {
        return splitVarietiesQuantity;
    }

    public void setSplitVarietiesQuantity(Integer splitVarietiesQuantity) {
        this.splitVarietiesQuantity = splitVarietiesQuantity;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
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

    @Override
    public String toString() {
        return "Split{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", splitDate=" + splitDate +
                ", code='" + code + '\'' +
                ", splitManId=" + splitManId +
                ", splitManCode='" + splitManCode + '\'' +
                ", splitManName='" + splitManName + '\'' +
                ", auditManId=" + auditManId +
                ", auditManCode='" + auditManCode + '\'' +
                ", auditManName='" + auditManName + '\'' +
                ", flowCode='" + flowCode + '\'' +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", splitQuantityTotal=" + splitQuantityTotal +
                ", splitVarietiesQuantity=" + splitVarietiesQuantity +
                ", amountTotal=" + amountTotal +
                ", notaxAmountTotal=" + notaxAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}