package com.rograndec.feijiayun.chain.business.distr.parent.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
 
/**
 * 
 * saas_distr_lack
 * 
 * 
 * @author Administrator
 * 
 * 2017-10-07
 */
public class DistrLack implements Serializable {
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
     * 单据类型（5402）
     */
    @ApiModelProperty(value = "单据类型（5402）")
    private Integer orderType;

    /**
     * 缺配单号
     */
    @ApiModelProperty(value = "缺配单号")
    private String code;

    /**
     * 配货日期
     */
    @ApiModelProperty(value = "配货日期")
    private Date sendDate;

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
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;

    /**
     * 要货人员ID
     */
    @ApiModelProperty(value = "要货人员ID")
    private Long requesterId;

    /**
     * 要货人员编码
     */
    @ApiModelProperty(value = "要货人员编码")
    private String requesterCode;

    /**
     * 要货人员名称
     */
    @ApiModelProperty(value = "要货人员名称")
    private String requesterName;


    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private String distrTypeName;

    /**
     * 要货计划ID
     */
    @ApiModelProperty(value = "要货计划ID")
    private Long requestPlanId;

    /**
     * 要货计划单号
     */
    @ApiModelProperty(value = "要货计划单号")
    private String requestPlanCode;

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
    /**zhuchao 新增下面三个字段，保存明细中要货，配货，缺配总数*/
    @ApiModelProperty(value = "总要货数量")
    private BigDecimal totalRequestQuantity;
    @ApiModelProperty(value = "总配货数量")
    private BigDecimal totalSendQuantity;
    @ApiModelProperty(value = "总缺配数量")
    private BigDecimal totalLackQuantity;

    private List<DistrLackDetail> distrLackDetailList;

    public List<DistrLackDetail> getDistrLackDetailList() {
        return distrLackDetailList;
    }

    public void setDistrLackDetailList(List<DistrLackDetail> distrLackDetailList) {
        this.distrLackDetailList = distrLackDetailList;
    }

    /**
     * saas_distr_lack
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
     * 单据类型（5402）
     * @return order_type 单据类型（5402）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5402）
     * @param orderType 单据类型（5402）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 缺配单号
     * @return code 缺配单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 缺配单号
     * @param code 缺配单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 配货日期
     * @return send_date 配货日期
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * 配货日期
     * @param sendDate 配货日期
     */
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
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
     * 要货单位ID
     * @return request_unit_id 要货单位ID
     */
    public Long getRequestUnitId() {
        return requestUnitId;
    }

    /**
     * 要货单位ID
     * @param requestUnitId 要货单位ID
     */
    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    /**
     * 要货单位编码
     * @return request_unit_code 要货单位编码
     */
    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    /**
     * 要货单位编码
     * @param requestUnitCode 要货单位编码
     */
    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode == null ? null : requestUnitCode.trim();
    }

    /**
     * 要货单位名称
     * @return request_unit_name 要货单位名称
     */
    public String getRequestUnitName() {
        return requestUnitName;
    }

    /**
     * 要货单位名称
     * @param requestUnitName 要货单位名称
     */
    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName == null ? null : requestUnitName.trim();
    }

    /**
     * 要货人员ID
     * @return requester_id 要货人员ID
     */
    public Long getRequesterId() {
        return requesterId;
    }

    /**
     * 要货人员ID
     * @param requesterId 要货人员ID
     */
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    /**
     * 要货人员编码
     * @return requester_code 要货人员编码
     */
    public String getRequesterCode() {
        return requesterCode;
    }

    /**
     * 要货人员编码
     * @param requesterCode 要货人员编码
     */
    public void setRequesterCode(String requesterCode) {
        this.requesterCode = requesterCode == null ? null : requesterCode.trim();
    }

    /**
     * 要货人员名称
     * @return requester_name 要货人员名称
     */
    public String getRequesterName() {
        return requesterName;
    }

    /**
     * 要货人员名称
     * @param requesterName 要货人员名称
     */
    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName == null ? null : requesterName.trim();
    }

    /**
     *配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     * @return distr_type 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    public Integer getDistrType() {
        return distrType;
    }

    /**
     *配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    /**
     * 要货计划ID
     * @return request_plan_id 要货计划ID
     */
    public Long getRequestPlanId() {
        return requestPlanId;
    }

    /**
     * 要货计划ID
     * @param requestPlanId 要货计划ID
     */
    public void setRequestPlanId(Long requestPlanId) {
        this.requestPlanId = requestPlanId;
    }

    /**
     * 要货计划单号
     * @return request_plan_code 要货计划单号
     */
    public String getRequestPlanCode() {
        return requestPlanCode;
    }

    /**
     * 要货计划单号
     * @param requestPlanCode 要货计划单号
     */
    public void setRequestPlanCode(String requestPlanCode) {
        this.requestPlanCode = requestPlanCode == null ? null : requestPlanCode.trim();
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

    public BigDecimal getTotalRequestQuantity() {
        return totalRequestQuantity;
    }

    public void setTotalRequestQuantity(BigDecimal totalRequestQuantity) {
        this.totalRequestQuantity = totalRequestQuantity;
    }

    public BigDecimal getTotalSendQuantity() {
        return totalSendQuantity;
    }

    public void setTotalSendQuantity(BigDecimal totalSendQuantity) {
        this.totalSendQuantity = totalSendQuantity;
    }

    public BigDecimal getTotalLackQuantity() {
        return totalLackQuantity;
    }

    public void setTotalLackQuantity(BigDecimal totalLackQuantity) {
        this.totalLackQuantity = totalLackQuantity;
    }

    public String getDistrTypeName() {
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        this.distrTypeName = distrTypeName;
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
        sb.append(", code=").append(code);
        sb.append(", sendDate=").append(sendDate);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", requestUnitId=").append(requestUnitId);
        sb.append(", requestUnitCode=").append(requestUnitCode);
        sb.append(", requestUnitName=").append(requestUnitName);
        sb.append(", requesterId=").append(requesterId);
        sb.append(", requesterCode=").append(requesterCode);
        sb.append(", requesterName=").append(requesterName);
        sb.append(", distrType=").append(distrType);
        sb.append(", requestPlanId=").append(requestPlanId);
        sb.append(", requestPlanCode=").append(requestPlanCode);
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