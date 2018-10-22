package com.rograndec.feijiayun.chain.business.storage.goodshandle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_goods_destroy
 * 
 * 
 * @author kexinhao
 * 
 * 2017-09-25
 */
public class GoodsDestroy implements Serializable {
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
     * 单据类型（5308）
     */
    @ApiModelProperty(value = "单据类型（5308）")
    private Integer orderType;

    /**
     * 销毁日期
     */
    @ApiModelProperty(value = "销毁日期")
    private Date destroyDate;

    /**
     * 销毁单号
     */
    @ApiModelProperty(value = "销毁单号")
    private String code;

    /**
     * 销毁人员ID
     */
    @ApiModelProperty(value = "销毁人员ID")
    private Long destroyManId;

    /**
     * 销毁人员编码
     */
    @ApiModelProperty(value = "销毁人员编码")
    private String destroyManCode;

    /**
     * 销毁人员名称
     */
    @ApiModelProperty(value = "销毁人员名称")
    private String destroyManName;

    /**
     * 销毁品种（0-常规商品；1-特殊管理药品）
     */
    @ApiModelProperty(value = "销毁品种（0-常规商品；1-特殊管理药品）")
    private Integer destroyGoods;

    /**
     * 销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）
     */
    @ApiModelProperty(value = "销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）")
    private Integer destroyReason;

    /**
     * 销毁时间
     */
    @ApiModelProperty(value = "销毁时间")
    private String destroyTime;

    /**
     * 销毁方式
     */
    @ApiModelProperty(value = "销毁方式")
    private String destroyMode;

    /**
     * 销毁单位
     */
    @ApiModelProperty(value = "销毁单位")
    private String destroyUnit;

    /**
     * 销毁地点
     */
    @ApiModelProperty(value = "销毁地点")
    private String destroyPlace;

    /**
     * 监控人员1
     */
    @ApiModelProperty(value = "监控人员1")
    private String monitor;

    /**
     * 监控人员2
     */
    @ApiModelProperty(value = "监控人员2")
    private String secondMonitor;

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
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

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
     * saas_goods_destroy
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
     * 单据类型（5308）
     * @return order_type 单据类型（5308）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5308）
     * @param orderType 单据类型（5308）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 销毁日期
     * @return destroy_date 销毁日期
     */
    public Date getDestroyDate() {
        return destroyDate;
    }

    /**
     * 销毁日期
     * @param destroyDate 销毁日期
     */
    public void setDestroyDate(Date destroyDate) {
        this.destroyDate = destroyDate;
    }

    /**
     * 销毁单号
     * @return code 销毁单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 销毁单号
     * @param code 销毁单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 销毁人员ID
     * @return destroy_man_id 销毁人员ID
     */
    public Long getDestroyManId() {
        return destroyManId;
    }

    /**
     * 销毁人员ID
     * @param destroyManId 销毁人员ID
     */
    public void setDestroyManId(Long destroyManId) {
        this.destroyManId = destroyManId;
    }

    /**
     * 销毁人员编码
     * @return destroy_man_code 销毁人员编码
     */
    public String getDestroyManCode() {
        return destroyManCode;
    }

    /**
     * 销毁人员编码
     * @param destroyManCode 销毁人员编码
     */
    public void setDestroyManCode(String destroyManCode) {
        this.destroyManCode = destroyManCode == null ? null : destroyManCode.trim();
    }

    /**
     * 销毁人员名称
     * @return destroy_man_name 销毁人员名称
     */
    public String getDestroyManName() {
        return destroyManName;
    }

    /**
     * 销毁人员名称
     * @param destroyManName 销毁人员名称
     */
    public void setDestroyManName(String destroyManName) {
        this.destroyManName = destroyManName == null ? null : destroyManName.trim();
    }

    /**
     * 销毁品种（0-常规商品；1-特殊管理药品）
     * @return destroy_goods 销毁品种（0-常规商品；1-特殊管理药品）
     */
    public Integer getDestroyGoods() {
        return destroyGoods;
    }

    /**
     * 销毁品种（0-常规商品；1-特殊管理药品）
     * @param destroyGoods 销毁品种（0-常规商品；1-特殊管理药品）
     */
    public void setDestroyGoods(Integer destroyGoods) {
        this.destroyGoods = destroyGoods;
    }

    /**
     * 销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）
     * @return destroy_reason 销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）
     */
    public Integer getDestroyReason() {
        return destroyReason;
    }

    /**
     * 销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）
     * @param destroyReason 销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）
     */
    public void setDestroyReason(Integer destroyReason) {
        this.destroyReason = destroyReason;
    }

    /**
     * 销毁时间
     * @return destroy_time 销毁时间
     */
    public String getDestroyTime() {
        return destroyTime;
    }

    /**
     * 销毁时间
     * @param destroyTime 销毁时间
     */
    public void setDestroyTime(String destroyTime) {
        this.destroyTime = destroyTime;
    }

    /**
     * 销毁方式
     * @return destroy_mode 销毁方式
     */
    public String getDestroyMode() {
        return destroyMode;
    }

    /**
     * 销毁方式
     * @param destroyMode 销毁方式
     */
    public void setDestroyMode(String destroyMode) {
        this.destroyMode = destroyMode == null ? null : destroyMode.trim();
    }

    /**
     * 销毁单位
     * @return destroy_unit 销毁单位
     */
    public String getDestroyUnit() {
        return destroyUnit;
    }

    /**
     * 销毁单位
     * @param destroyUnit 销毁单位
     */
    public void setDestroyUnit(String destroyUnit) {
        this.destroyUnit = destroyUnit == null ? null : destroyUnit.trim();
    }

    /**
     * 销毁地点
     * @return destroy_place 销毁地点
     */
    public String getDestroyPlace() {
        return destroyPlace;
    }

    /**
     * 销毁地点
     * @param destroyPlace 销毁地点
     */
    public void setDestroyPlace(String destroyPlace) {
        this.destroyPlace = destroyPlace == null ? null : destroyPlace.trim();
    }

    /**
     * 监控人员1
     * @return monitor 监控人员1
     */
    public String getMonitor() {
        return monitor;
    }

    /**
     * 监控人员1
     * @param monitor 监控人员1
     */
    public void setMonitor(String monitor) {
        this.monitor = monitor == null ? null : monitor.trim();
    }

    /**
     * 监控人员2
     * @return second_monitor 监控人员2
     */
    public String getSecondMonitor() {
        return secondMonitor;
    }

    /**
     * 监控人员2
     * @param secondMonitor 监控人员2
     */
    public void setSecondMonitor(String secondMonitor) {
        this.secondMonitor = secondMonitor == null ? null : secondMonitor.trim();
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

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    @Override
    public String toString() {
        return "GoodsDestroy{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", destroyDate=" + destroyDate +
                ", code='" + code + '\'' +
                ", destroyManId=" + destroyManId +
                ", destroyManCode='" + destroyManCode + '\'' +
                ", destroyManName='" + destroyManName + '\'' +
                ", destroyGoods=" + destroyGoods +
                ", destroyReason=" + destroyReason +
                ", destroyTime='" + destroyTime + '\'' +
                ", destroyMode='" + destroyMode + '\'' +
                ", destroyUnit='" + destroyUnit + '\'' +
                ", destroyPlace='" + destroyPlace + '\'' +
                ", monitor='" + monitor + '\'' +
                ", secondMonitor='" + secondMonitor + '\'' +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", amountTotal=" + amountTotal +
                ", notaxAmountTotal=" + notaxAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}