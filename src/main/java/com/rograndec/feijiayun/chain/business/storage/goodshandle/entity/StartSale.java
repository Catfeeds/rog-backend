package com.rograndec.feijiayun.chain.business.storage.goodshandle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_start_sale
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
public class StartSale implements Serializable {
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
     * 单据类型（5314）
     */
    @ApiModelProperty(value = "单据类型（5314）")
    private Integer orderType;

    /**
     * 通知日期
     */
    @ApiModelProperty(value = "通知日期")
    private Date startDate;

    /**
     * 通知单号
     */
    @ApiModelProperty(value = "通知单号")
    private String code;

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
     * 通知人员ID
     */
    @ApiModelProperty(value = "通知人员ID")
    private Long startManId;

    /**
     * 通知人员编码
     */
    @ApiModelProperty(value = "通知人员编码")
    private String startManCode;

    /**
     * 通知人员名称
     */
    @ApiModelProperty(value = "通知人员名称")
    private String startManName;

    /**
     * 通知来源（0-主管单位通知；1-药品处理）
     */
    @ApiModelProperty(value = "通知来源（0-主管单位通知；1-药品处理）")
    private Integer startFrom;

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


    private List<StartSaleDetail> startSaleDetails;


    public List<StartSaleDetail> getStartSaleDetails() {
        return startSaleDetails;
    }

    public void setStartSaleDetails(List<StartSaleDetail> startSaleDetails) {
        this.startSaleDetails = startSaleDetails;
    }

    /**
     * saas_start_sale
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
     * 单据类型（5314）
     * @return order_type 单据类型（5314）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5314）
     * @param orderType 单据类型（5314）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 通知日期
     * @return start_date 通知日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 通知日期
     * @param startDate 通知日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 通知单号
     * @return code 通知单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 通知单号
     * @param code 通知单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 通知人员ID
     * @return start_man_id 通知人员ID
     */
    public Long getStartManId() {
        return startManId;
    }

    /**
     * 通知人员ID
     * @param startManId 通知人员ID
     */
    public void setStartManId(Long startManId) {
        this.startManId = startManId;
    }

    /**
     * 通知人员编码
     * @return start_man_code 通知人员编码
     */
    public String getStartManCode() {
        return startManCode;
    }

    /**
     * 通知人员编码
     * @param startManCode 通知人员编码
     */
    public void setStartManCode(String startManCode) {
        this.startManCode = startManCode == null ? null : startManCode.trim();
    }

    /**
     * 通知人员名称
     * @return start_man_name 通知人员名称
     */
    public String getStartManName() {
        return startManName;
    }

    /**
     * 通知人员名称
     * @param startManName 通知人员名称
     */
    public void setStartManName(String startManName) {
        this.startManName = startManName == null ? null : startManName.trim();
    }

    /**
     * 通知来源（0-主管单位通知；1-药品处理）
     * @return start_from 通知来源（0-主管单位通知；1-药品处理）
     */
    public Integer getStartFrom() {
        return startFrom;
    }

    /**
     * 通知来源（0-主管单位通知；1-药品处理）
     * @param startFrom 通知来源（0-主管单位通知；1-药品处理）
     */
    public void setStartFrom(Integer startFrom) {
        this.startFrom = startFrom;
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
        sb.append(", startDate=").append(startDate);
        sb.append(", code=").append(code);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", startManId=").append(startManId);
        sb.append(", startManCode=").append(startManCode);
        sb.append(", startManName=").append(startManName);
        sb.append(", startFrom=").append(startFrom);
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