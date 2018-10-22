package com.rograndec.feijiayun.chain.business.storage.move.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_shelf_move
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
public class ShelfMove implements Serializable {
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
     * 单据类型（5302）
     */
    @ApiModelProperty(value = "单据类型（5302）")
    private Integer orderType;

    /**
     * 移动单号
     */
    @ApiModelProperty(value = "移动单号")
    private String code;

    /**
     * 移动日期
     */
    @ApiModelProperty(value = "移动日期")
    private Date moveDate;

    /**
     * 移动人员ID
     */
    @ApiModelProperty(value = "移动人员ID")
    private Long moveManId;

    /**
     * 移动人员编码
     */
    @ApiModelProperty(value = "移动人员编码")
    private String moveManCode;

    /**
     * 移动人员名称
     */
    @ApiModelProperty(value = "移动人员名称")
    private String moveManName;

    /**
     * 接收人员ID
     */
    @ApiModelProperty(value = "接收人员ID")
    private Long receiverId;

    /**
     * 接收人员编码
     */
    @ApiModelProperty(value = "接收人员编码")
    private String receiverCode;

    /**
     * 接收人员名称
     */
    @ApiModelProperty(value = "接收人员名称")
    private String receiverName;

    /**
     * 移动原因
     */
    @ApiModelProperty(value = "移动原因")
    private String moveReason;

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
     * saas_shelf_move
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
     * 单据类型（5302）
     * @return order_type 单据类型（5302）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5302）
     * @param orderType 单据类型（5302）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 移动单号
     * @return code 移动单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 移动单号
     * @param code 移动单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 移动日期
     * @return move_date 移动日期
     */
    public Date getMoveDate() {
        return moveDate;
    }

    /**
     * 移动日期
     * @param moveDate 移动日期
     */
    public void setMoveDate(Date moveDate) {
        this.moveDate = moveDate;
    }

    /**
     * 移动人员ID
     * @return move_man_id 移动人员ID
     */
    public Long getMoveManId() {
        return moveManId;
    }

    /**
     * 移动人员ID
     * @param moveManId 移动人员ID
     */
    public void setMoveManId(Long moveManId) {
        this.moveManId = moveManId;
    }

    /**
     * 移动人员编码
     * @return move_man_code 移动人员编码
     */
    public String getMoveManCode() {
        return moveManCode;
    }

    /**
     * 移动人员编码
     * @param moveManCode 移动人员编码
     */
    public void setMoveManCode(String moveManCode) {
        this.moveManCode = moveManCode == null ? null : moveManCode.trim();
    }

    /**
     * 移动人员名称
     * @return move_man_name 移动人员名称
     */
    public String getMoveManName() {
        return moveManName;
    }

    /**
     * 移动人员名称
     * @param moveManName 移动人员名称
     */
    public void setMoveManName(String moveManName) {
        this.moveManName = moveManName == null ? null : moveManName.trim();
    }

    /**
     * 接收人员ID
     * @return receiver_id 接收人员ID
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * 接收人员ID
     * @param receiverId 接收人员ID
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 接收人员编码
     * @return receiver_code 接收人员编码
     */
    public String getReceiverCode() {
        return receiverCode;
    }

    /**
     * 接收人员编码
     * @param receiverCode 接收人员编码
     */
    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode == null ? null : receiverCode.trim();
    }

    /**
     * 接收人员名称
     * @return receiver_name 接收人员名称
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 接收人员名称
     * @param receiverName 接收人员名称
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * 移动原因
     * @return move_reason 移动原因
     */
    public String getMoveReason() {
        return moveReason;
    }

    /**
     * 移动原因
     * @param moveReason 移动原因
     */
    public void setMoveReason(String moveReason) {
        this.moveReason = moveReason == null ? null : moveReason.trim();
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
        sb.append(", moveDate=").append(moveDate);
        sb.append(", moveManId=").append(moveManId);
        sb.append(", moveManCode=").append(moveManCode);
        sb.append(", moveManName=").append(moveManName);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", receiverCode=").append(receiverCode);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", moveReason=").append(moveReason);
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