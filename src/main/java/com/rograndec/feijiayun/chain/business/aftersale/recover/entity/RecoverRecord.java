package com.rograndec.feijiayun.chain.business.aftersale.recover.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_recover_record
 * 
 * 
 * @author lanxj
 * 
 * 2017-10-17
 */
public class RecoverRecord implements Serializable {
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
     * 单据类型（6305）
     */
    @ApiModelProperty(value = "单据类型（6305）")
    private Integer orderType;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 追回日期
     */
    @ApiModelProperty(value = "追回日期")
    private Date recoverDate;

    /**
     * 追回单位ID
     */
    @ApiModelProperty(value = "追回单位ID")
    private Long recoverUnitId;

    /**
     * 追回单位编码
     */
    @ApiModelProperty(value = "追回单位编码")
    private String recoverUnitCode;

    /**
     * 追回单位名称
     */
    @ApiModelProperty(value = "追回单位名称")
    private String recoverUnitName;

    /**
     * 追回人员ID
     */
    @ApiModelProperty(value = "追回人员ID")
    private Long recoverManId;

    /**
     * 追回人员编码
     */
    @ApiModelProperty(value = "追回人员编码")
    private String recoverManCode;

    /**
     * 追回人员名称
     */
    @ApiModelProperty(value = "追回人员名称")
    private String recoverManName;

    /**
     * 追回处理（0-退回供货单位；1-销毁）
     */
    @ApiModelProperty(value = "追回处理（0-退回供货单位；1-销毁）")
    private Integer handleMeasures;

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
     * saas_recover_record
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
     * 单据类型（6305）
     * @return order_type 单据类型（6305）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6305）
     * @param orderType 单据类型（6305）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据编码
     * @return code 单据编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据编码
     * @param code 单据编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 追回日期
     * @return recover_date 追回日期
     */
    public Date getRecoverDate() {
        return recoverDate;
    }

    /**
     * 追回日期
     * @param recoverDate 追回日期
     */
    public void setRecoverDate(Date recoverDate) {
        this.recoverDate = recoverDate;
    }

    public Long getRecoverUnitId() {
        return recoverUnitId;
    }

    public void setRecoverUnitId(Long recoverUnitId) {
        this.recoverUnitId = recoverUnitId;
    }

    public String getRecoverUnitCode() {
        return recoverUnitCode;
    }

    public void setRecoverUnitCode(String recoverUnitCode) {
        this.recoverUnitCode = recoverUnitCode;
    }

    public String getRecoverUnitName() {
        return recoverUnitName;
    }

    public void setRecoverUnitName(String recoverUnitName) {
        this.recoverUnitName = recoverUnitName;
    }

    /**
     * 追回人员ID
     * @return recover_man_id 追回人员ID
     */
    public Long getRecoverManId() {
        return recoverManId;
    }

    /**
     * 追回人员ID
     * @param recoverManId 追回人员ID
     */
    public void setRecoverManId(Long recoverManId) {
        this.recoverManId = recoverManId;
    }

    /**
     * 追回人员编码
     * @return recover_man_code 追回人员编码
     */
    public String getRecoverManCode() {
        return recoverManCode;
    }

    /**
     * 追回人员编码
     * @param recoverManCode 追回人员编码
     */
    public void setRecoverManCode(String recoverManCode) {
        this.recoverManCode = recoverManCode == null ? null : recoverManCode.trim();
    }

    /**
     * 追回人员名称
     * @return recover_man_name 追回人员名称
     */
    public String getRecoverManName() {
        return recoverManName;
    }

    /**
     * 追回人员名称
     * @param recoverManName 追回人员名称
     */
    public void setRecoverManName(String recoverManName) {
        this.recoverManName = recoverManName == null ? null : recoverManName.trim();
    }

    /**
     * 追回处理（0-退回供货单位；1-销毁）
     * @return handle_measures 追回处理（0-退回供货单位；1-销毁）
     */
    public Integer getHandleMeasures() {
        return handleMeasures;
    }

    /**
     * 追回处理（0-退回供货单位；1-销毁）
     * @param handleMeasures 追回处理（0-退回供货单位；1-销毁）
     */
    public void setHandleMeasures(Integer handleMeasures) {
        this.handleMeasures = handleMeasures;
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
        return "RecoverRecord{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", code='" + code + '\'' +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderType=" + baseOrderType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
                ", recoverDate=" + recoverDate +
                ", recoverUnitId=" + recoverUnitId +
                ", recoverUnitCode='" + recoverUnitCode + '\'' +
                ", recoverUnitName='" + recoverUnitName + '\'' +
                ", recoverManId=" + recoverManId +
                ", recoverManCode='" + recoverManCode + '\'' +
                ", recoverManName='" + recoverManName + '\'' +
                ", handleMeasures=" + handleMeasures +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
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