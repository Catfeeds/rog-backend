package com.rograndec.feijiayun.chain.business.online.purchase.smart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartPurchasingPlanVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingGoodsVO;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_smart_purchasing_plan
 * 
 * 
 * @author dudy
 * 
 * 2017-11-21
 */
public class SmartPurchasingPlan implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 生产企业ID
     */
    @ApiModelProperty(value = "生产企业ID")
    private Long manufacturerId;

    /**
     * 生产企业
     */
    @ApiModelProperty(value = "生产企业")
    private String manufacturer;

    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private BigDecimal inventoryQuantity;

    /**
     * 缺货数量
     */
    @ApiModelProperty(value = "缺货数量")
    private BigDecimal lackQuantity;

    /**
     * 采购数量
     */
    @ApiModelProperty(value = "采购数量")
    private BigDecimal purchaseQuantity;

    /**
     * 0系统添加，1手动添加
     */
    @ApiModelProperty(value = "0系统添加，1手动添加")
    private Integer type;

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
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

    /**
     * saas_smart_purchasing_plan
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
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
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 商品通用名称
     * @return goods_generic_name 商品通用名称
     */
    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    /**
     * 商品通用名称
     * @param goodsGenericName 商品通用名称
     */
    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName == null ? null : goodsGenericName.trim();
    }

    /**
     * 生产企业ID
     * @return manufacturer_id 生产企业ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 生产企业ID
     * @param manufacturerId 生产企业ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 生产企业
     * @return manufacturer 生产企业
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产企业
     * @param manufacturer 生产企业
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 库存数量
     * @return inventory_quantity 库存数量
     */
    public BigDecimal getInventoryQuantity() {
        return inventoryQuantity;
    }

    /**
     * 库存数量
     * @param inventoryQuantity 库存数量
     */
    public void setInventoryQuantity(BigDecimal inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    /**
     * 缺货数量
     * @return lack_quantity 缺货数量
     */
    public BigDecimal getLackQuantity() {
        return lackQuantity;
    }

    /**
     * 缺货数量
     * @param lackQuantity 缺货数量
     */
    public void setLackQuantity(BigDecimal lackQuantity) {
        this.lackQuantity = lackQuantity;
    }

    /**
     * 采购数量
     * @return purchase_quantity 采购数量
     */
    public BigDecimal getPurchaseQuantity() {
        return purchaseQuantity;
    }

    /**
     * 采购数量
     * @param purchaseQuantity 采购数量
     */
    public void setPurchaseQuantity(BigDecimal purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    /**
     * 0系统添加，1手动添加
     * @return type 0系统添加，1手动添加
     */
    public Integer getType() {
        return type;
    }

    /**
     * 0系统添加，1手动添加
     * @param type 0系统添加，1手动添加
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 企业id
     * @return enterprise_id 企业id
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业id
     * @param enterpriseId 企业id
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsGenericName=").append(goodsGenericName);
        sb.append(", manufacturerId=").append(manufacturerId);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", inventoryQuantity=").append(inventoryQuantity);
        sb.append(", lackQuantity=").append(lackQuantity);
        sb.append(", purchaseQuantity=").append(purchaseQuantity);
        sb.append(", type=").append(type);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static SmartPurchasingPlanVO convertToVO(SmartPurchasingPlan plan) {
        SmartPurchasingPlanVO vo = new SmartPurchasingPlanVO();
        vo.setTid(plan.getId());
        vo.setId(plan.getGoodsId());
        vo.setCode(plan.getGoodsCode());
        vo.setName(plan.getGoodsName());
        vo.setGenericName(plan.getGoodsGenericName());
        vo.setManufacturerId(plan.getManufacturerId());
        vo.setManufacturer(plan.getManufacturer());
        vo.setStockQuantity(plan.getInventoryQuantity());
        vo.setLackQuantity(plan.getLackQuantity());
        vo.setQuantity(plan.getPurchaseQuantity());
        vo.setType(plan.getType());
        vo.setRemark(plan.getRemark());
        return vo;
    }

    public static SmartSourcingGoodsVO convertToGoodsVO(SmartPurchasingPlan plan, Goods goods, UserVO userVO) {
        SmartSourcingGoodsVO goodsVO = new SmartSourcingGoodsVO();
        goodsVO.setGoodsId(plan.getGoodsId());
        goodsVO.setGoodsCode(goods.getCode());
        goodsVO.setGoodsGenericName(goods.getGenericName());
        goodsVO.setGoodsName(goods.getName());
        goodsVO.setGoodsPlace(goods.getPlace());
        goodsVO.setGoodsSpecification(goods.getSpecification());
        goodsVO.setBusinessVariety(goods.getBusinessVariety());
        goodsVO.setBusinessVarietyName(BusinessVariety.getName(goods.getBusinessVariety()));
        goodsVO.setManufacturerId(goods.getManufacturerId());
        goodsVO.setManufacturer(goods.getManufacturer());
        goodsVO.setRegistrationCode(goods.getApprovalNumber());
        goodsVO.setDosageId(goods.getDosageId());
        goodsVO.setDosageName(goods.getDosageName());
        goodsVO.setQuantity(plan.getPurchaseQuantity().intValue());
        goodsVO.setRetailPrice(BigDecimal.ZERO);
        goodsVO.setSubtotal(BigDecimal.ZERO);
        goodsVO.setUnitId(goods.getUnitId());
        goodsVO.setUnitName(goods.getUnitName());
        goodsVO.setEntrepriseId(userVO.getEnterpriseId());
        goodsVO.setCreaterId(userVO.getUserId());
        goodsVO.setCreaterName(userVO.getUserName());
        goodsVO.setAddCartTime(new Date());
        return goodsVO;
    }
}