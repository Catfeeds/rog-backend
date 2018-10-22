package com.rograndec.feijiayun.chain.business.keytable.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BatchNumber implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 内部批次号
     */
    private String batchNum;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 业务单据ID
     */
    private Long orderId;

    /**
     * 业务单据编码
     */
    private String orderCode;

    /**
     * 单据类型
     */
    private Integer orderType;

    /**
     * 单据日期
     */
    private Date orderDate;

    /**
     * 业务单据明细ID
     */
    private Long orderDtlId;

    /**
     * 行号
     */
    private Integer lineNum;

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
     * 实际单价
     */
    private BigDecimal realPrice;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    private BigDecimal notaxRealPrice;

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

    /**
     * 批次入库数量
     */
    private BigDecimal quantity;

    /**
     * saas_batch_number
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
     * 内部批次号
     * @return batch_num 内部批次号
     */
    public String getBatchNum() {
        return batchNum;
    }

    /**
     * 内部批次号
     * @param batchNum 内部批次号
     */
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
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
     * 业务单据ID
     * @return order_id 业务单据ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 业务单据ID
     * @param orderId 业务单据ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 业务单据编码
     * @return order_code 业务单据编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 业务单据编码
     * @param orderCode 业务单据编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 单据类型
     * @return order_type 单据类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型
     * @param orderType 单据类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据日期
     * @return order_date 单据日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 单据日期
     * @param orderDate 单据日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 业务单据明细ID
     * @return order_dtl_id 业务单据明细ID
     */
    public Long getOrderDtlId() {
        return orderDtlId;
    }

    /**
     * 业务单据明细ID
     * @param orderDtlId 业务单据明细ID
     */
    public void setOrderDtlId(Long orderDtlId) {
        this.orderDtlId = orderDtlId;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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
     * 实际单价
     * @return real_price 实际单价
     */
    public BigDecimal getRealPrice() {
        return realPrice;
    }

    /**
     * 实际单价
     * @param realPrice 实际单价
     */
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * 税率
     * @return tax_rate 税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 税率
     * @param taxRate 税率
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 不含税实际单价
     * @return notax_real_price 不含税实际单价
     */
    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    /**
     * 不含税实际单价
     * @param notaxRealPrice 不含税实际单价
     */
    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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
        sb.append(", batchNum=").append(batchNum);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", orderDtlId=").append(orderDtlId);
        sb.append(", lineNum=").append(lineNum);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNum=").append(lotNum);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", notaxRealPrice=").append(notaxRealPrice);
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