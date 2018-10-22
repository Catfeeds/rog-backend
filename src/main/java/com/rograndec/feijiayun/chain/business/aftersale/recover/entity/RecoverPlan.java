package com.rograndec.feijiayun.chain.business.aftersale.recover.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_recover_plan
 * 
 * 
 * @author lanxj
 * 
 * 2017-10-16
 */
public class RecoverPlan implements Serializable {
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
     * 单据类型（6303）
     */
    @ApiModelProperty(value = "单据类型（6303）")
    private Integer orderType;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 通知日期
     */
    @ApiModelProperty(value = "通知日期")
    private Date planDate;

    /**
     * 通知人ID
     */
    @ApiModelProperty(value = "通知人ID")
    private Long planManId;

    /**
     * 通知人编码
     */
    @ApiModelProperty(value = "通知人编码")
    private String planManCode;

    /**
     * 通知人名称
     */
    @ApiModelProperty(value = "通知人名称")
    private String planManName;

    /**
     * 追回责任人
     */
    @ApiModelProperty(value = "追回责任人")
    private String recoverMan;

    /**
     * 追回责任人电话
     */
    @ApiModelProperty(value = "追回责任人电话")
    private String recoverManPhone;

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
     * saas_recover_plan
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
     * 单据类型（6303）
     * @return order_type 单据类型（6303）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6303）
     * @param orderType 单据类型（6303）
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
     * 通知日期
     * @return plan_date 通知日期
     */
    public Date getPlanDate() {
        return planDate;
    }

    /**
     * 通知日期
     * @param planDate 通知日期
     */
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * 通知人ID
     * @return plan_man_id 通知人ID
     */
    public Long getPlanManId() {
        return planManId;
    }

    /**
     * 通知人ID
     * @param planManId 通知人ID
     */
    public void setPlanManId(Long planManId) {
        this.planManId = planManId;
    }

    /**
     * 通知人编码
     * @return plan_man_code 通知人编码
     */
    public String getPlanManCode() {
        return planManCode;
    }

    /**
     * 通知人编码
     * @param planManCode 通知人编码
     */
    public void setPlanManCode(String planManCode) {
        this.planManCode = planManCode == null ? null : planManCode.trim();
    }

    /**
     * 通知人名称
     * @return plan_man_name 通知人名称
     */
    public String getPlanManName() {
        return planManName;
    }

    /**
     * 通知人名称
     * @param planManName 通知人名称
     */
    public void setPlanManName(String planManName) {
        this.planManName = planManName == null ? null : planManName.trim();
    }

    /**
     * 追回责任人
     * @return recover_man 追回责任人
     */
    public String getRecoverMan() {
        return recoverMan;
    }

    /**
     * 追回责任人
     * @param recoverMan 追回责任人
     */
    public void setRecoverMan(String recoverMan) {
        this.recoverMan = recoverMan == null ? null : recoverMan.trim();
    }

    /**
     * 追回责任人电话
     * @return recover_man_phone 追回责任人电话
     */
    public String getRecoverManPhone() {
        return recoverManPhone;
    }

    /**
     * 追回责任人电话
     * @param recoverManPhone 追回责任人电话
     */
    public void setRecoverManPhone(String recoverManPhone) {
        this.recoverManPhone = recoverManPhone == null ? null : recoverManPhone.trim();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "RecoverPlan{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", code='" + code + '\'' +
                ", planDate=" + planDate +
                ", planManId=" + planManId +
                ", planManCode='" + planManCode + '\'' +
                ", planManName='" + planManName + '\'' +
                ", recoverMan='" + recoverMan + '\'' +
                ", recoverManPhone='" + recoverManPhone + '\'' +
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