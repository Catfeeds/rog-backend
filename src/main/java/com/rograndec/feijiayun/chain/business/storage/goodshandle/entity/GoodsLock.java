package com.rograndec.feijiayun.chain.business.storage.goodshandle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_goods_lock
 * 
 * 
 * @author Administrator
 * 
 * 2017-09-27
 */
public class GoodsLock implements Serializable {
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
     * 单据类型（5311）
     */
    @ApiModelProperty(value = "单据类型（5311）")
    private Integer orderType;
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
     * 锁定日期
     */
    @ApiModelProperty(value = "锁定日期")
    private Date lockDate;

    /**
     * 锁定单号
     */
    @ApiModelProperty(value = "锁定单号")
    private String code;

    /**
     * 锁定人员ID
     */
    @ApiModelProperty(value = "锁定人员ID")
    private Long lockManId;

    /**
     * 锁定人员编码
     */
    @ApiModelProperty(value = "锁定人员编码")
    private String lockManCode;

    /**
     * 锁定人员名称
     */
    @ApiModelProperty(value = "锁定人员名称")
    private String lockManName;

    /**
     * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
     */
    @ApiModelProperty(value = "锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）")
    private Integer lockReason;

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
    @ApiModelProperty(value = "状态  34-已取消 92-待处理 33-已处理")
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

    @ApiModelProperty(value = "商品信息列表")
    private List<GoodsLockDetail> goodsLockDetailList;

    public List<GoodsLockDetail> getGoodsLockDetailList() {
        return goodsLockDetailList;
    }

    public void setGoodsLockDetailList(List<GoodsLockDetail> goodsLockDetailList) {
        this.goodsLockDetailList = goodsLockDetailList;
    }

    /**
     * saas_goods_lock
     */
    private static final long serialVersionUID = 1L;

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

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
     * 单据类型（5311）
     * @return order_type 单据类型（5311）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5311）
     * @param orderType 单据类型（5311）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 锁定日期
     * @return lock_date 锁定日期
     */
    public Date getLockDate() {
        return lockDate;
    }

    /**
     * 锁定日期
     * @param lockDate 锁定日期
     */
    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    /**
     * 锁定单号
     * @return code 锁定单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 锁定单号
     * @param code 锁定单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 锁定人员ID
     * @return lock_man_id 锁定人员ID
     */
    public Long getLockManId() {
        return lockManId;
    }

    /**
     * 锁定人员ID
     * @param lockManId 锁定人员ID
     */
    public void setLockManId(Long lockManId) {
        this.lockManId = lockManId;
    }

    /**
     * 锁定人员编码
     * @return lock_man_code 锁定人员编码
     */
    public String getLockManCode() {
        return lockManCode;
    }

    /**
     * 锁定人员编码
     * @param lockManCode 锁定人员编码
     */
    public void setLockManCode(String lockManCode) {
        this.lockManCode = lockManCode == null ? null : lockManCode.trim();
    }

    /**
     * 锁定人员名称
     * @return lock_man_name 锁定人员名称
     */
    public String getLockManName() {
        return lockManName;
    }

    /**
     * 锁定人员名称
     * @param lockManName 锁定人员名称
     */
    public void setLockManName(String lockManName) {
        this.lockManName = lockManName == null ? null : lockManName.trim();
    }

    /**
     * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
     * @return lock_reason 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
     */
    public Integer getLockReason() {
        return lockReason;
    }

    /**
     * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
     * @param lockReason 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
     */
    public void setLockReason(Integer lockReason) {
        this.lockReason = lockReason;
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderType=").append(orderType);
        sb.append(", lockDate=").append(lockDate);
        sb.append(", code=").append(code);
        sb.append(", lockManId=").append(lockManId);
        sb.append(", lockManCode=").append(lockManCode);
        sb.append(", lockManName=").append(lockManName);
        sb.append(", lockReason=").append(lockReason);
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