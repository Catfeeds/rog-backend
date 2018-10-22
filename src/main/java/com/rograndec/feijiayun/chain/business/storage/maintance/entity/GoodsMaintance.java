package com.rograndec.feijiayun.chain.business.storage.maintance.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_goods_maintance
 * 
 * 
 * @author Asze
 * 
 * 2017-09-26
 */
public class GoodsMaintance implements Serializable {
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
     * 单据类型（5331）
     */
    @ApiModelProperty(value = "单据类型（5331）")
    private Integer orderType;

    /**
     * 养护日期
     */
    @ApiModelProperty(value = "养护日期")
    private Date maintanceDate;

    /**
     * 养护单号
     */
    @ApiModelProperty(value = "养护单号")
    private String code;

    /**
     * 养护人员ID
     */
    @ApiModelProperty(value = "养护人员ID")
    private Long maintanceManId;

    /**
     * 养护人员编码
     */
    @ApiModelProperty(value = "养护人员编码")
    private String maintanceManCode;

    /**
     * 养护人员名称
     */
    @ApiModelProperty(value = "养护人员名称")
    private String maintanceManName;

    /**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;

    /**
     * 库区名称
     */
    @ApiModelProperty(value = "库区名称")
    private String warehouseAreaName;

    /**
     * 养护类型（0-重点养护；1-常规养护）
     */
    @ApiModelProperty(value = "养护类型（0-重点养护；1-常规养护）")
    private Integer maintanceType;

    /**
     * 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     */
    @ApiModelProperty(value = "药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）")
    private Integer goodsType;

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
     * saas_goods_maintance
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
     * 单据类型（5331）
     * @return order_type 单据类型（5331）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5331）
     * @param orderType 单据类型（5331）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 养护日期
     * @return maintance_date 养护日期
     */
    public Date getMaintanceDate() {
        return maintanceDate;
    }

    /**
     * 养护日期
     * @param maintanceDate 养护日期
     */
    public void setMaintanceDate(Date maintanceDate) {
        this.maintanceDate = maintanceDate;
    }

    /**
     * 养护单号
     * @return code 养护单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 养护单号
     * @param code 养护单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 养护人员ID
     * @return maintance_man_id 养护人员ID
     */
    public Long getMaintanceManId() {
        return maintanceManId;
    }

    /**
     * 养护人员ID
     * @param maintanceManId 养护人员ID
     */
    public void setMaintanceManId(Long maintanceManId) {
        this.maintanceManId = maintanceManId;
    }

    /**
     * 养护人员编码
     * @return maintance_man_code 养护人员编码
     */
    public String getMaintanceManCode() {
        return maintanceManCode;
    }

    /**
     * 养护人员编码
     * @param maintanceManCode 养护人员编码
     */
    public void setMaintanceManCode(String maintanceManCode) {
        this.maintanceManCode = maintanceManCode == null ? null : maintanceManCode.trim();
    }

    /**
     * 养护人员名称
     * @return maintance_man_name 养护人员名称
     */
    public String getMaintanceManName() {
        return maintanceManName;
    }

    /**
     * 养护人员名称
     * @param maintanceManName 养护人员名称
     */
    public void setMaintanceManName(String maintanceManName) {
        this.maintanceManName = maintanceManName == null ? null : maintanceManName.trim();
    }

    /**
     * 库区ID
     * @return warehouse_area_id 库区ID
     */
    public Long getWarehouseAreaId() {
        return warehouseAreaId;
    }

    /**
     * 库区ID
     * @param warehouseAreaId 库区ID
     */
    public void setWarehouseAreaId(Long warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    /**
     * 库区名称
     * @return warehouse_area_name 库区名称
     */
    public String getWarehouseAreaName() {
        return warehouseAreaName;
    }

    /**
     * 库区名称
     * @param warehouseAreaName 库区名称
     */
    public void setWarehouseAreaName(String warehouseAreaName) {
        this.warehouseAreaName = warehouseAreaName == null ? null : warehouseAreaName.trim();
    }

    /**
     * 养护类型（0-重点养护；1-常规养护）
     * @return maintance_type 养护类型（0-重点养护；1-常规养护）
     */
    public Integer getMaintanceType() {
        return maintanceType;
    }

    /**
     * 养护类型（0-重点养护；1-常规养护）
     * @param maintanceType 养护类型（0-重点养护；1-常规养护）
     */
    public void setMaintanceType(Integer maintanceType) {
        this.maintanceType = maintanceType;
    }

    /**
     * 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     * @return goods_type 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     */
    public Integer getGoodsType() {
        return goodsType;
    }

    /**
     * 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     * @param goodsType 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     */
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
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
        sb.append(", maintanceDate=").append(maintanceDate);
        sb.append(", code=").append(code);
        sb.append(", maintanceManId=").append(maintanceManId);
        sb.append(", maintanceManCode=").append(maintanceManCode);
        sb.append(", maintanceManName=").append(maintanceManName);
        sb.append(", warehouseAreaId=").append(warehouseAreaId);
        sb.append(", warehouseAreaName=").append(warehouseAreaName);
        sb.append(", maintanceType=").append(maintanceType);
        sb.append(", goodsType=").append(goodsType);
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