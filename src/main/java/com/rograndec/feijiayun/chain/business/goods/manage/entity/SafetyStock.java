package com.rograndec.feijiayun.chain.business.goods.manage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SafetyStock implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 企业类型（0-总部；1-自营店；2-加盟店）
     */
    private Integer chainType;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 默认货位ID
     */
    private Long defaultShelfId;

    /**
     * 默认货位名称
     */
    private String defaultShelfName;

    /**
     * 库存上限
     */
    private BigDecimal inventoryUp;

    /**
     * 库存下限
     */
    private BigDecimal inventoryDown;

    /**
     * 安全库存
     */
    private BigDecimal safetyStock;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    public static Map<Long,SafetyStock> getSafetyStockMap(List<SafetyStock> safetyStocks){

        Map<Long,SafetyStock> map = new HashMap<>();

        for(SafetyStock safetyStock : safetyStocks){
            map.put(safetyStock.getGoodsId(),safetyStock);
        }

        return map;
    }

    /**
     * saas_safety_stock
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
     * 企业类型（0-总部；1-自营店；2-加盟店）
     * @return chain_type 企业类型（0-总部；1-自营店；2-加盟店）
     */
    public Integer getChainType() {
        return chainType;
    }

    /**
     * 企业类型（0-总部；1-自营店；2-加盟店）
     * @param chainType 企业类型（0-总部；1-自营店；2-加盟店）
     */
    public void setChainType(Integer chainType) {
        this.chainType = chainType;
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
     * 默认货位ID
     * @return default_shelf_id 默认货位ID
     */
    public Long getDefaultShelfId() {
        return defaultShelfId;
    }

    /**
     * 默认货位ID
     * @param defaultShelfId 默认货位ID
     */
    public void setDefaultShelfId(Long defaultShelfId) {
        this.defaultShelfId = defaultShelfId;
    }

    /**
     * 默认货位名称
     * @return default_shelf_name 默认货位名称
     */
    public String getDefaultShelfName() {
        return defaultShelfName;
    }

    /**
     * 默认货位名称
     * @param defaultShelfName 默认货位名称
     */
    public void setDefaultShelfName(String defaultShelfName) {
        this.defaultShelfName = defaultShelfName == null ? null : defaultShelfName.trim();
    }

    /**
     * 库存上限
     * @return inventory_up 库存上限
     */
    public BigDecimal getInventoryUp() {
        return inventoryUp;
    }

    /**
     * 库存上限
     * @param inventoryUp 库存上限
     */
    public void setInventoryUp(BigDecimal inventoryUp) {
        this.inventoryUp = inventoryUp;
    }

    /**
     * 库存下限
     * @return inventory_down 库存下限
     */
    public BigDecimal getInventoryDown() {
        return inventoryDown;
    }

    /**
     * 库存下限
     * @param inventoryDown 库存下限
     */
    public void setInventoryDown(BigDecimal inventoryDown) {
        this.inventoryDown = inventoryDown;
    }

    /**
     * 安全库存
     * @return safety_stock 安全库存
     */
    public BigDecimal getSafetyStock() {
        return safetyStock;
    }

    /**
     * 安全库存
     * @param safetyStock 安全库存
     */
    public void setSafetyStock(BigDecimal safetyStock) {
        this.safetyStock = safetyStock;
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
        sb.append(", chainType=").append(chainType);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", defaultShelfId=").append(defaultShelfId);
        sb.append(", defaultShelfName=").append(defaultShelfName);
        sb.append(", inventoryUp=").append(inventoryUp);
        sb.append(", inventoryDown=").append(inventoryDown);
        sb.append(", safetyStock=").append(safetyStock);
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