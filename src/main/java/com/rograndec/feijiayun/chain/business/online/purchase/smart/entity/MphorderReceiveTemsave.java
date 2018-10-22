package com.rograndec.feijiayun.chain.business.online.purchase.smart.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_mphorder_receive_temsave
 * 
 * 
 * @author Administrator
 * 
 * 2017-11-30
 */
public class MphorderReceiveTemsave implements Serializable {
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
     * MPH订单ID
     */
    @ApiModelProperty(value = "MPH订单ID")
    private Long orderId;

    /**
     * MPH订单编码
     */
    @ApiModelProperty(value = "MPH订单编码")
    private String orderCode;

    /**
     * mph供应商ID
     */
    @ApiModelProperty(value = "mph供应商ID")
    private Long mphSupplierId;

    /**
     * MPH供应商名称
     */
    @ApiModelProperty(value = "MPH供应商名称")
    private String mphSupplierName;

    /**
     * 匹配Saas供应商ID
     */
    @ApiModelProperty(value = "匹配Saas供应商ID")
    private Long supplierId;

    /**
     * 匹配Saas供应商名称
     */
    @ApiModelProperty(value = "匹配Saas供应商名称")
    private String supplierName;

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
     * 采购日期
     */
    @ApiModelProperty(value = "采购日期")
    private Date purchaseDate;

    /**
     * 收货日期
     */
    @ApiModelProperty(value = "收货日期")
    private Date receiveDate;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    private Date checkDate;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private Date inDate;

    /**
     * 采购人员ID
     */
    @ApiModelProperty(value = "采购人员ID")
    private Long purchaseManId;

    /**
     * 采购人编码
     */
    @ApiModelProperty(value = "采购人编码")
    private String purchaseManCode;

    /**
     * 采购人员名称
     */
    @ApiModelProperty(value = "采购人员名称")
    private String purchaseManName;

    /**
     * 收货人员ID
     */
    @ApiModelProperty(value = "收货人员ID")
    private Long receiveManId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(value = "收货人员编码")
    private String receiveManCode;

    /**
     * 收货人员名称
     */
    @ApiModelProperty(value = "收货人员名称")
    private String receiveManName;

    /**
     * 第二收货人ID
     */
    @ApiModelProperty(value = "第二收货人ID")
    private Long secondReceiveManId;

    /**
     * 第二收货人编码
     */
    @ApiModelProperty(value = "第二收货人编码")
    private String secondReceiveManCode;

    /**
     * 第二收货人I名称
     */
    @ApiModelProperty(value = "第二收货人I名称")
    private String secondReceiveManName;

    /**
     * 验收人员ID
     */
    @ApiModelProperty(value = "验收人员ID")
    private Long checkManId;

    /**
     * 验收人员编码
     */
    @ApiModelProperty(value = "验收人员编码")
    private String checkManCode;

    /**
     * 第二验收人员ID
     */
    @ApiModelProperty(value = "第二验收人员ID")
    private Long secondCheckManId;

    /**
     * 验收人员名称
     */
    @ApiModelProperty(value = "验收人员名称")
    private String checkManName;

    /**
     * 第二验收人员编码
     */
    @ApiModelProperty(value = "第二验收人员编码")
    private String secondCheckManCode;

    /**
     * 第二验收人员名称
     */
    @ApiModelProperty(value = "第二验收人员名称")
    private String secondCheckManName;

    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID")
    private Long inManId;

    /**
     * 入库人员编码
     */
    @ApiModelProperty(value = "入库人员编码")
    private String inManCode;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String inManName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

    /**
     * saas_mphorder_receive_temsave
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
     * MPH订单ID
     * @return order_id MPH订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * MPH订单ID
     * @param orderId MPH订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * MPH订单编码
     * @return order_code MPH订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * MPH订单编码
     * @param orderCode MPH订单编码
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * mph供应商ID
     * @return mph_supplier_id mph供应商ID
     */
    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    /**
     * mph供应商ID
     * @param mphSupplierId mph供应商ID
     */
    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
    }

    /**
     * MPH供应商名称
     * @return mph_supplier_name MPH供应商名称
     */
    public String getMphSupplierName() {
        return mphSupplierName;
    }

    /**
     * MPH供应商名称
     * @param mphSupplierName MPH供应商名称
     */
    public void setMphSupplierName(String mphSupplierName) {
        this.mphSupplierName = mphSupplierName == null ? null : mphSupplierName.trim();
    }

    /**
     * 匹配Saas供应商ID
     * @return supplier_id 匹配Saas供应商ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * 匹配Saas供应商ID
     * @param supplierId 匹配Saas供应商ID
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 匹配Saas供应商名称
     * @return supplier_name 匹配Saas供应商名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 匹配Saas供应商名称
     * @param supplierName 匹配Saas供应商名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
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
     * 采购日期
     * @return purchase_date 采购日期
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * 采购日期
     * @param purchaseDate 采购日期
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * 收货日期
     * @return receive_date 收货日期
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 收货日期
     * @param receiveDate 收货日期
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 验收日期
     * @return check_date 验收日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 验收日期
     * @param checkDate 验收日期
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 入库日期
     * @return in_date 入库日期
     */
    public Date getInDate() {
        return inDate;
    }

    /**
     * 入库日期
     * @param inDate 入库日期
     */
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    /**
     * 采购人员ID
     * @return purchase_man_id 采购人员ID
     */
    public Long getPurchaseManId() {
        return purchaseManId;
    }

    /**
     * 采购人员ID
     * @param purchaseManId 采购人员ID
     */
    public void setPurchaseManId(Long purchaseManId) {
        this.purchaseManId = purchaseManId;
    }

    /**
     * 采购人编码
     * @return purchase_man_code 采购人编码
     */
    public String getPurchaseManCode() {
        return purchaseManCode;
    }

    /**
     * 采购人编码
     * @param purchaseManCode 采购人编码
     */
    public void setPurchaseManCode(String purchaseManCode) {
        this.purchaseManCode = purchaseManCode == null ? null : purchaseManCode.trim();
    }

    /**
     * 采购人员名称
     * @return purchase_man_name 采购人员名称
     */
    public String getPurchaseManName() {
        return purchaseManName;
    }

    /**
     * 采购人员名称
     * @param purchaseManName 采购人员名称
     */
    public void setPurchaseManName(String purchaseManName) {
        this.purchaseManName = purchaseManName == null ? null : purchaseManName.trim();
    }

    /**
     * 收货人员ID
     * @return receive_man_id 收货人员ID
     */
    public Long getReceiveManId() {
        return receiveManId;
    }

    /**
     * 收货人员ID
     * @param receiveManId 收货人员ID
     */
    public void setReceiveManId(Long receiveManId) {
        this.receiveManId = receiveManId;
    }

    /**
     * 收货人员编码
     * @return receive_man_code 收货人员编码
     */
    public String getReceiveManCode() {
        return receiveManCode;
    }

    /**
     * 收货人员编码
     * @param receiveManCode 收货人员编码
     */
    public void setReceiveManCode(String receiveManCode) {
        this.receiveManCode = receiveManCode == null ? null : receiveManCode.trim();
    }

    /**
     * 收货人员名称
     * @return receive_man_name 收货人员名称
     */
    public String getReceiveManName() {
        return receiveManName;
    }

    /**
     * 收货人员名称
     * @param receiveManName 收货人员名称
     */
    public void setReceiveManName(String receiveManName) {
        this.receiveManName = receiveManName == null ? null : receiveManName.trim();
    }

    /**
     * 第二收货人ID
     * @return second_receive_man_id 第二收货人ID
     */
    public Long getSecondReceiveManId() {
        return secondReceiveManId;
    }

    /**
     * 第二收货人ID
     * @param secondReceiveManId 第二收货人ID
     */
    public void setSecondReceiveManId(Long secondReceiveManId) {
        this.secondReceiveManId = secondReceiveManId;
    }

    /**
     * 第二收货人编码
     * @return second_receive_man_code 第二收货人编码
     */
    public String getSecondReceiveManCode() {
        return secondReceiveManCode;
    }

    /**
     * 第二收货人编码
     * @param secondReceiveManCode 第二收货人编码
     */
    public void setSecondReceiveManCode(String secondReceiveManCode) {
        this.secondReceiveManCode = secondReceiveManCode == null ? null : secondReceiveManCode.trim();
    }

    /**
     * 第二收货人I名称
     * @return second_receive_man_name 第二收货人I名称
     */
    public String getSecondReceiveManName() {
        return secondReceiveManName;
    }

    /**
     * 第二收货人I名称
     * @param secondReceiveManName 第二收货人I名称
     */
    public void setSecondReceiveManName(String secondReceiveManName) {
        this.secondReceiveManName = secondReceiveManName == null ? null : secondReceiveManName.trim();
    }

    /**
     * 验收人员ID
     * @return check_man_id 验收人员ID
     */
    public Long getCheckManId() {
        return checkManId;
    }

    /**
     * 验收人员ID
     * @param checkManId 验收人员ID
     */
    public void setCheckManId(Long checkManId) {
        this.checkManId = checkManId;
    }

    /**
     * 验收人员编码
     * @return check_man_code 验收人员编码
     */
    public String getCheckManCode() {
        return checkManCode;
    }

    /**
     * 验收人员编码
     * @param checkManCode 验收人员编码
     */
    public void setCheckManCode(String checkManCode) {
        this.checkManCode = checkManCode == null ? null : checkManCode.trim();
    }

    /**
     * 第二验收人员ID
     * @return second_check_man_id 第二验收人员ID
     */
    public Long getSecondCheckManId() {
        return secondCheckManId;
    }

    /**
     * 第二验收人员ID
     * @param secondCheckManId 第二验收人员ID
     */
    public void setSecondCheckManId(Long secondCheckManId) {
        this.secondCheckManId = secondCheckManId;
    }

    /**
     * 验收人员名称
     * @return check_man_name 验收人员名称
     */
    public String getCheckManName() {
        return checkManName;
    }

    /**
     * 验收人员名称
     * @param checkManName 验收人员名称
     */
    public void setCheckManName(String checkManName) {
        this.checkManName = checkManName == null ? null : checkManName.trim();
    }

    /**
     * 第二验收人员编码
     * @return second_check_man_code 第二验收人员编码
     */
    public String getSecondCheckManCode() {
        return secondCheckManCode;
    }

    /**
     * 第二验收人员编码
     * @param secondCheckManCode 第二验收人员编码
     */
    public void setSecondCheckManCode(String secondCheckManCode) {
        this.secondCheckManCode = secondCheckManCode == null ? null : secondCheckManCode.trim();
    }

    /**
     * 第二验收人员名称
     * @return second_check_man_name 第二验收人员名称
     */
    public String getSecondCheckManName() {
        return secondCheckManName;
    }

    /**
     * 第二验收人员名称
     * @param secondCheckManName 第二验收人员名称
     */
    public void setSecondCheckManName(String secondCheckManName) {
        this.secondCheckManName = secondCheckManName == null ? null : secondCheckManName.trim();
    }

    /**
     * 入库人员ID
     * @return in_man_id 入库人员ID
     */
    public Long getInManId() {
        return inManId;
    }

    /**
     * 入库人员ID
     * @param inManId 入库人员ID
     */
    public void setInManId(Long inManId) {
        this.inManId = inManId;
    }

    /**
     * 入库人员编码
     * @return in_man_code 入库人员编码
     */
    public String getInManCode() {
        return inManCode;
    }

    /**
     * 入库人员编码
     * @param inManCode 入库人员编码
     */
    public void setInManCode(String inManCode) {
        this.inManCode = inManCode == null ? null : inManCode.trim();
    }

    /**
     * 入库人员名称
     * @return in_man_name 入库人员名称
     */
    public String getInManName() {
        return inManName;
    }

    /**
     * 入库人员名称
     * @param inManName 入库人员名称
     */
    public void setInManName(String inManName) {
        this.inManName = inManName == null ? null : inManName.trim();
    }

    /**
     * 流通监管码
     * @return flow_code 流通监管码
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 流通监管码
     * @param flowCode 流通监管码
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
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
        sb.append(", orderId=").append(orderId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", mphSupplierId=").append(mphSupplierId);
        sb.append(", mphSupplierName=").append(mphSupplierName);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append(", receiveDate=").append(receiveDate);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", inDate=").append(inDate);
        sb.append(", purchaseManId=").append(purchaseManId);
        sb.append(", purchaseManCode=").append(purchaseManCode);
        sb.append(", purchaseManName=").append(purchaseManName);
        sb.append(", receiveManId=").append(receiveManId);
        sb.append(", receiveManCode=").append(receiveManCode);
        sb.append(", receiveManName=").append(receiveManName);
        sb.append(", secondReceiveManId=").append(secondReceiveManId);
        sb.append(", secondReceiveManCode=").append(secondReceiveManCode);
        sb.append(", secondReceiveManName=").append(secondReceiveManName);
        sb.append(", checkManId=").append(checkManId);
        sb.append(", checkManCode=").append(checkManCode);
        sb.append(", secondCheckManId=").append(secondCheckManId);
        sb.append(", checkManName=").append(checkManName);
        sb.append(", secondCheckManCode=").append(secondCheckManCode);
        sb.append(", secondCheckManName=").append(secondCheckManName);
        sb.append(", inManId=").append(inManId);
        sb.append(", inManCode=").append(inManCode);
        sb.append(", inManName=").append(inManName);
        sb.append(", flowCode=").append(flowCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}