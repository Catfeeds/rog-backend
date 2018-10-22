package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Stock implements Serializable {
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
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 批号ID
     */
    private Long lotId;
    
    /**
     * 批号
     */
    private String lotNum;

    /**
     * 库存数量
     */
    private BigDecimal quantity;

    /**
     * 锁定数量
     */
    private BigDecimal lockQuantity;

    /**
     * 可用数量
     */
    private BigDecimal usableQuantity;

    /**
     * 货位ID
     */
    private Long shelfId;

    /**
     * 货位名称
     */
    private String shelfName;

    /**
     * 货位质量状态描述
     */
    private String shelfStatusDesc;
    
    /**
     * 盘点状态
     */
    private Integer inventoryStatus;


    /**
     * 最近养护时间
     */
    private Date lastMaintainTime;

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

    /**
     * saas_stock
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
     * 批号ID
     * @return lot_id 批号ID
     */
    public Long getLotId() {
		return lotId;
	}
    
    /**
     * 批号ID
     * @return lot_id 批号ID
     */
    public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

    /**
     * 批号
     * @return lot_num 批号
     */
    public String getLotNum() {
        return lotNum;
    }

    /**
     * 批号
     * @param lotNum 批号
     */
    public void setLotNum(String lotNum) {
        this.lotNum = lotNum == null ? null : lotNum.trim();
    }
    
    /**
     * 库存数量
     * @return quantity 库存数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 库存数量
     * @param quantity 库存数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 锁定数量
     * @return lock_quantity 锁定数量
     */
    public BigDecimal getLockQuantity() {
        return lockQuantity;
    }

    /**
     * 锁定数量
     * @param lockQuantity 锁定数量
     */
    public void setLockQuantity(BigDecimal lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    /**
     * 可用数量
     * @return usable_quantity 可用数量
     */
    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    /**
     * 可用数量
     * @param usableQuantity 可用数量
     */
    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    /**
     * 货位ID
     * @return shelf_id 货位ID
     */
    public Long getShelfId() {
        return shelfId;
    }

    /**
     * 货位ID
     * @param shelfId 货位ID
     */
    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    /**
     * 货位名称
     * @return shelf_name 货位名称
     */
    public String getShelfName() {
        return shelfName;
    }

    /**
     * 货位名称
     * @param shelfName 货位名称
     */
    public void setShelfName(String shelfName) {
        this.shelfName = shelfName == null ? null : shelfName.trim();
    }

    /**
     * 货位质量状态描述
     * @return shelf_status_desc 货位质量状态描述
     */
    public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}

    /**
     * 货位质量状态描述
     * @param shelfStatusDesc 货位质量状态描述
     */
	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}
    
    /**
     * 盘点状态
     * @return inventory_status 盘点状态
     */
    public Integer getInventoryStatus() {
        return inventoryStatus;
    }

    /**
     * 盘点状态
     * @param inventoryStatus 盘点状态
     */
    public void setInventoryStatus(Integer inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }
    
    /**
     * 最近养护时间
     * @return last_maintain_time 最近养护时间
     */
    public Date getLastMaintainTime() {
        return lastMaintainTime;
    }

    /**
     * 最近养护时间
     * @param lastMaintainTime 最近养护时间
     */
    public void setLastMaintainTime(Date lastMaintainTime) {
        this.lastMaintainTime = lastMaintainTime;
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
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNum=").append(lotNum);
        sb.append(", quantity=").append(quantity);
        sb.append(", lockQuantity=").append(lockQuantity);
        sb.append(", usableQuantity=").append(usableQuantity);
        sb.append(", shelfId=").append(shelfId);
        sb.append(", shelfName=").append(shelfName);
        sb.append(", shelfStatusDesc=").append(shelfStatusDesc);
        sb.append(", inventoryStatus=").append(inventoryStatus);
        sb.append(", lastMaintainTime=").append(lastMaintainTime);
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