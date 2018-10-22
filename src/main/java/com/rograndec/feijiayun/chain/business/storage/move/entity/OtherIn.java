package com.rograndec.feijiayun.chain.business.storage.move.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_other_in
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
public class OtherIn implements Serializable {
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
     * 单据类型（5303）
     */
    @ApiModelProperty(value = "单据类型（5303）")
    private Integer orderType;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private Date inDate;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String code;

    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID")
    private Long inManId;

    /**
     * 入库人员编码
     */
    @ApiModelProperty(value = "入库人员编码")
    private String inManCode;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String inManName;

    /**
     * 入库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     */
    @ApiModelProperty(value = "入库类型（0-获赠；1-报溢；2-领用退回；3-其它）")
    private Integer inType;

    /**
     * 来源单位类型（0-部门；1-总部；2-门店；3-供货单位）
     */
    @ApiModelProperty(value = "来源单位类型（0-部门；1-总部；2-门店；3-供货单位）")
    private Integer srcUnitType;

    /**
     * 来源单位ID
     */
    @ApiModelProperty(value = "来源单位ID")
    private Long srcUnitId;

    /**
     * 来源单位编码
     */
    @ApiModelProperty(value = "来源单位编码")
    private String srcUnitCode;

    /**
     * 来源单位名称
     */
    @ApiModelProperty(value = "来源单位名称")
    private String srcUnitName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

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
     * 超量销售单号集合
     */
    private String excessiveSaleCodes;

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
     * saas_other_in
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
     * 单据类型（5303）
     * @return order_type 单据类型（5303）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5303）
     * @param orderType 单据类型（5303）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 入库日期
     * @return in_date 入库日期
     */
    public Date getInDate() {
        return inDate;
    }

    /**
     * 入库日期
     * @param inDate 入库日期
     */
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    /**
     * 入库单号
     * @return code 入库单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 入库单号
     * @param code 入库单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 入库人员ID
     * @return in_man_id 入库人员ID
     */
    public Long getInManId() {
        return inManId;
    }

    /**
     * 入库人员ID
     * @param inManId 入库人员ID
     */
    public void setInManId(Long inManId) {
        this.inManId = inManId;
    }

    /**
     * 入库人员编码
     * @return in_man_code 入库人员编码
     */
    public String getInManCode() {
        return inManCode;
    }

    /**
     * 入库人员编码
     * @param inManCode 入库人员编码
     */
    public void setInManCode(String inManCode) {
        this.inManCode = inManCode == null ? null : inManCode.trim();
    }

    /**
     * 入库人员名称
     * @return in_man_name 入库人员名称
     */
    public String getInManName() {
        return inManName;
    }

    /**
     * 入库人员名称
     * @param inManName 入库人员名称
     */
    public void setInManName(String inManName) {
        this.inManName = inManName == null ? null : inManName.trim();
    }

    /**
     * 入库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     * @return in_type 入库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     */
    public Integer getInType() {
        return inType;
    }

    /**
     * 入库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     * @param inType 入库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     */
    public void setInType(Integer inType) {
        this.inType = inType;
    }

    /**
     * 来源单位类型（0-部门；1-总部；2-门店；3-供货单位）
     * @return src_unit_type 来源单位类型（0-部门；1-总部；2-门店；3-供货单位）
     */
    public Integer getSrcUnitType() {
        return srcUnitType;
    }

    /**
     * 来源单位类型（0-部门；1-总部；2-门店；3-供货单位）
     * @param srcUnitType 来源单位类型（0-部门；1-总部；2-门店；3-供货单位）
     */
    public void setSrcUnitType(Integer srcUnitType) {
        this.srcUnitType = srcUnitType;
    }

    /**
     * 来源单位ID
     * @return src_unit_id 来源单位ID
     */
    public Long getSrcUnitId() {
        return srcUnitId;
    }

    /**
     * 来源单位ID
     * @param srcUnitId 来源单位ID
     */
    public void setSrcUnitId(Long srcUnitId) {
        this.srcUnitId = srcUnitId;
    }

    /**
     * 来源单位编码
     * @return src_unit_code 来源单位编码
     */
    public String getSrcUnitCode() {
        return srcUnitCode;
    }

    /**
     * 来源单位编码
     * @param srcUnitCode 来源单位编码
     */
    public void setSrcUnitCode(String srcUnitCode) {
        this.srcUnitCode = srcUnitCode == null ? null : srcUnitCode.trim();
    }

    /**
     * 来源单位名称
     * @return src_unit_name 来源单位名称
     */
    public String getSrcUnitName() {
        return srcUnitName;
    }

    /**
     * 来源单位名称
     * @param srcUnitName 来源单位名称
     */
    public void setSrcUnitName(String srcUnitName) {
        this.srcUnitName = srcUnitName == null ? null : srcUnitName.trim();
    }

    /**
     * 流通监管码
     * @return flow_code 流通监管码
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 流通监管码
     * @param flowCode 流通监管码
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
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
     * 金额合计
     * @return amount_total 金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计
     * @param amountTotal 金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxAmountTotal 不含税金额合计
     */
    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 超量销售单号集合
     * @return
     */
    public String getExcessiveSaleCodes() {
        return excessiveSaleCodes;
    }

    /**
     * 超量销售单号集合
     * @param excessiveSaleCodes
     */
    public void setExcessiveSaleCodes(String excessiveSaleCodes) {
        this.excessiveSaleCodes = excessiveSaleCodes;
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
        sb.append(", inDate=").append(inDate);
        sb.append(", code=").append(code);
        sb.append(", inManId=").append(inManId);
        sb.append(", inManCode=").append(inManCode);
        sb.append(", inManName=").append(inManName);
        sb.append(", inType=").append(inType);
        sb.append(", srcUnitType=").append(srcUnitType);
        sb.append(", srcUnitId=").append(srcUnitId);
        sb.append(", srcUnitCode=").append(srcUnitCode);
        sb.append(", srcUnitName=").append(srcUnitName);
        sb.append(", flowCode=").append(flowCode);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", notaxAmountTotal=").append(notaxAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", excessiveSaleCodes=").append(excessiveSaleCodes);
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