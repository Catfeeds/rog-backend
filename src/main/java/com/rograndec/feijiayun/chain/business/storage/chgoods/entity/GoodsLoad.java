package com.rograndec.feijiayun.chain.business.storage.chgoods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_goods_load
 * 
 * 
 * @author Asze
 * 
 * 2017-09-26
 */
public class GoodsLoad implements Serializable {
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
     * 单据类型（5305）
     */
    @ApiModelProperty(value = "单据类型（5305）")
    private Integer orderType;

    /**
     * 装斗单号
     */
    @ApiModelProperty(value = "装斗单号")
    private String code;

    /**
     * 装斗日期
     */
    @ApiModelProperty(value = "装斗日期")
    private Date loadDate;

    /**
     * 装斗人员ID
     */
    @ApiModelProperty(value = "装斗人员ID")
    private Long loadManId;

    /**
     * 装斗人员编码
     */
    @ApiModelProperty(value = "装斗人员编码")
    private String loadManCode;

    /**
     * 装斗人员名称
     */
    @ApiModelProperty(value = "装斗人员名称")
    private String loadManName;

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
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
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
     * saas_goods_load
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return Id 主键ID
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
     * 单据类型（5305）
     * @return order_type 单据类型（5305）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5305）
     * @param orderType 单据类型（5305）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 装斗单号
     * @return code 装斗单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 装斗单号
     * @param code 装斗单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 装斗日期
     * @return load_date 装斗日期
     */
    public Date getLoadDate() {
        return loadDate;
    }

    /**
     * 装斗日期
     * @param loadDate 装斗日期
     */
    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    /**
     * 装斗人员ID
     * @return load_man_id 装斗人员ID
     */
    public Long getLoadManId() {
        return loadManId;
    }

    /**
     * 装斗人员ID
     * @param loadManId 装斗人员ID
     */
    public void setLoadManId(Long loadManId) {
        this.loadManId = loadManId;
    }

    /**
     * 装斗人员编码
     * @return load_man_code 装斗人员编码
     */
    public String getLoadManCode() {
        return loadManCode;
    }

    /**
     * 装斗人员编码
     * @param loadManCode 装斗人员编码
     */
    public void setLoadManCode(String loadManCode) {
        this.loadManCode = loadManCode == null ? null : loadManCode.trim();
    }

    /**
     * 装斗人员名称
     * @return load_man_name 装斗人员名称
     */
    public String getLoadManName() {
        return loadManName;
    }

    /**
     * 装斗人员名称
     * @param loadManName 装斗人员名称
     */
    public void setLoadManName(String loadManName) {
        this.loadManName = loadManName == null ? null : loadManName.trim();
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
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
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
        sb.append(", orderType=").append(orderType);
        sb.append(", code=").append(code);
        sb.append(", loadDate=").append(loadDate);
        sb.append(", loadManId=").append(loadManId);
        sb.append(", loadManCode=").append(loadManCode);
        sb.append(", loadManName=").append(loadManName);
        sb.append(", auditManId=").append(auditManId);
        sb.append(", auditManCode=").append(auditManCode);
        sb.append(", auditManName=").append(auditManName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
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