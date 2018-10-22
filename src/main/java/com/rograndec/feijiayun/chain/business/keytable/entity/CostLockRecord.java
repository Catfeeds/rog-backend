package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_cost_lock_record
 * 
 * 
 * @author lizhongyi
 * 
 * 2017-11-20
 */
public class CostLockRecord implements Serializable {
    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long enterpriseId;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long parentId;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long baseOrderId;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Integer baseOrderType;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private String baseOrderCode;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Date baseOrderDate;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long goodsId;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private String goodsCode;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private String goodsName;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long lotId;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private String lotNum;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long batchId;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private String batchNum;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private BigDecimal lockQuantity;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long createrId;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private String createrCode;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private String createrName;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Date createTime;

    /**
     * saas_cost_lock_record
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return enterprise_id 
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 
     * @param enterpriseId 
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 
     * @return parent_id 
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 
     * @param parentId 
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return base_order_id 
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 
     * @param baseOrderId 
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 
     * @return base_order_type 
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 
     * @param baseOrderType 
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 
     * @return base_order_code 
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 
     * @param baseOrderCode 
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 
     * @return base_order_date 
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 
     * @param baseOrderDate 
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 
     * @return goods_id 
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 
     * @param goodsId 
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 
     * @return goods_code 
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 
     * @param goodsCode 
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 
     * @return goods_name 
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 
     * @param goodsName 
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 
     * @return lot_id 
     */
    public Long getLotId() {
        return lotId;
    }

    /**
     * 
     * @param lotId 
     */
    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    /**
     * 
     * @return lot_num 
     */
    public String getLotNum() {
        return lotNum;
    }

    /**
     * 
     * @param lotNum 
     */
    public void setLotNum(String lotNum) {
        this.lotNum = lotNum == null ? null : lotNum.trim();
    }

    /**
     * 
     * @return batch_id 
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * 
     * @param batchId 
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    /**
     * 
     * @return batch_num 
     */
    public String getBatchNum() {
        return batchNum;
    }

    /**
     * 
     * @param batchNum 
     */
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    /**
     * 
     * @return lock_quantity 
     */
    public BigDecimal getLockQuantity() {
        return lockQuantity;
    }

    /**
     * 
     * @param lockQuantity 
     */
    public void setLockQuantity(BigDecimal lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    /**
     * 
     * @return creater_id 
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 
     * @param createrId 
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 
     * @return creater_code 
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 
     * @param createrCode 
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 
     * @return creater_name 
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 
     * @param createrName 
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**ˇ
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNum=").append(lotNum);
        sb.append(", batchId=").append(batchId);
        sb.append(", batchNum=").append(batchNum);
        sb.append(", lockQuantity=").append(lockQuantity);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}