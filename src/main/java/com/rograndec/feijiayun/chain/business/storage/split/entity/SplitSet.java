package com.rograndec.feijiayun.chain.business.storage.split.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_split_set
 * 
 * 
 * @author lanxj
 * 
 * 2017-09-26
 */
public class SplitSet implements Serializable {
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
     * 原商品ID
     */
    @ApiModelProperty(value = "原商品ID")
    private Long goodsId;

    /**
     * 拆零商品ID
     */
    @ApiModelProperty(value = "拆零商品ID")
    private Long splitGoodsId;

    /**
     * 拆零规格
     */
    @ApiModelProperty(value = "拆零规格")
    private String splitSpecification;

    /**
     * 拆零单位ID
     */
    @ApiModelProperty(value = "拆零单位ID")
    private Long splitUnitId;

    /**
     * 拆零单位名称
     */
    @ApiModelProperty(value = "拆零单位名称")
    private String splitUnitName;

    /**
     * 源货位ID
     */
    @ApiModelProperty(value = "源货位ID")
    private Long splitShelfId;

    /**
     * 源货位名称
     */
    @ApiModelProperty(value = "源货位名称")
    private String splitShelfName;

    /**
     * 拆零数量
     */
    @ApiModelProperty(value = "拆零数量")
    private BigDecimal splitQuantity;

    /**
     * 拆零会员价
     */
    @ApiModelProperty(value = "拆零会员价")
    private BigDecimal splitMemberPrice;

    /**
     * 拆零零售单价
     */
    @ApiModelProperty(value = "拆零零售单价")
    private BigDecimal splitRetailPrice;

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
     * saas_split_set
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
     * 原商品ID
     * @return goods_id 原商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    public Long getSplitGoodsId() {
        return splitGoodsId;
    }

    public void setSplitGoodsId(Long splitGoodsId) {
        this.splitGoodsId = splitGoodsId;
    }

    /**
     * 原商品ID
     * @param goodsId 原商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 拆零规格
     * @return split_specification 拆零规格
     */
    public String getSplitSpecification() {
        return splitSpecification;
    }

    /**
     * 拆零规格
     * @param splitSpecification 拆零规格
     */
    public void setSplitSpecification(String splitSpecification) {
        this.splitSpecification = splitSpecification == null ? null : splitSpecification.trim();
    }

    /**
     * 拆零单位ID
     * @return split_unit_id 拆零单位ID
     */
    public Long getSplitUnitId() {
        return splitUnitId;
    }

    /**
     * 拆零单位ID
     * @param splitUnitId 拆零单位ID
     */
    public void setSplitUnitId(Long splitUnitId) {
        this.splitUnitId = splitUnitId;
    }

    /**
     * 拆零单位名称
     * @return split_unit_name 拆零单位名称
     */
    public String getSplitUnitName() {
        return splitUnitName;
    }

    /**
     * 拆零单位名称
     * @param splitUnitName 拆零单位名称
     */
    public void setSplitUnitName(String splitUnitName) {
        this.splitUnitName = splitUnitName == null ? null : splitUnitName.trim();
    }

    /**
     * 源货位ID
     * @return split_shelf_id 源货位ID
     */
    public Long getSplitShelfId() {
        return splitShelfId;
    }

    /**
     * 源货位ID
     * @param splitShelfId 源货位ID
     */
    public void setSplitShelfId(Long splitShelfId) {
        this.splitShelfId = splitShelfId;
    }

    /**
     * 源货位名称
     * @return split_shelf_name 源货位名称
     */
    public String getSplitShelfName() {
        return splitShelfName;
    }

    /**
     * 源货位名称
     * @param splitShelfName 源货位名称
     */
    public void setSplitShelfName(String splitShelfName) {
        this.splitShelfName = splitShelfName == null ? null : splitShelfName.trim();
    }

    /**
     * 拆零数量
     * @return split_quantity 拆零数量
     */
    public BigDecimal getSplitQuantity() {
        return splitQuantity;
    }

    /**
     * 拆零数量
     * @param splitQuantity 拆零数量
     */
    public void setSplitQuantity(BigDecimal splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    /**
     * 拆零会员价
     * @return split_member_price 拆零会员价
     */
    public BigDecimal getSplitMemberPrice() {
        return splitMemberPrice;
    }

    /**
     * 拆零会员价
     * @param splitMemberPrice 拆零会员价
     */
    public void setSplitMemberPrice(BigDecimal splitMemberPrice) {
        this.splitMemberPrice = splitMemberPrice;
    }

    /**
     * 拆零零售单价
     * @return split_retail_price 拆零零售单价
     */
    public BigDecimal getSplitRetailPrice() {
        return splitRetailPrice;
    }

    /**
     * 拆零零售单价
     * @param splitRetailPrice 拆零零售单价
     */
    public void setSplitRetailPrice(BigDecimal splitRetailPrice) {
        this.splitRetailPrice = splitRetailPrice;
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
        sb.append(", goodsId=").append(goodsId);
        sb.append(", splitSpecification=").append(splitSpecification);
        sb.append(", splitUnitId=").append(splitUnitId);
        sb.append(", splitUnitName=").append(splitUnitName);
        sb.append(", splitShelfId=").append(splitShelfId);
        sb.append(", splitShelfName=").append(splitShelfName);
        sb.append(", splitQuantity=").append(splitQuantity);
        sb.append(", splitMemberPrice=").append(splitMemberPrice);
        sb.append(", splitRetailPrice=").append(splitRetailPrice);
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