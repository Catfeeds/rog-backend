package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_retail_daily_settle
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailDailySettle implements Serializable {
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
     * 单据类型（6241）
     */
    @ApiModelProperty(value = "单据类型（6241）")
    private Integer orderType;

    /**
     * 日结日期
     */
    @ApiModelProperty(value = "日结日期")
    private Date settleDate;

    /**
     * 日结单号
     */
    @ApiModelProperty(value = "日结单号")
    private String code;

    /**
     * 日结人员ID
     */
    @ApiModelProperty(value = "日结人员ID")
    private Long settleManId;

    /**
     * 日结人员编码
     */
    @ApiModelProperty(value = "日结人员编码")
    private String settleManCode;

    /**
     * 日结人员名称
     */
    @ApiModelProperty(value = "日结人员名称")
    private String settleManName;

    /**
     * 销售笔数合计
     */
    @ApiModelProperty(value = "销售笔数合计")
    private Integer salePensTotal;

    /**
     * 销售金额合计
     */
    @ApiModelProperty(value = "销售金额合计")
    private BigDecimal saleAmountTotal;

    /**
     * 退货笔数合计
     */
    @ApiModelProperty(value = "退货笔数合计")
    private Integer returnPensTotal;

    /**
     * 退货金额合计
     */
    @ApiModelProperty(value = "退货金额合计")
    private BigDecimal returnAmountTotal;

    /**
     * 应收金额合计
     */
    @ApiModelProperty(value = "应收金额合计")
    private BigDecimal amountTotal;

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待缴款；1-已缴款；2-已冲销）")
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
     * saas_retail_daily_settle
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
     * 单据类型（6241）
     * @return order_type 单据类型（6241）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6241）
     * @param orderType 单据类型（6241）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 日结日期
     * @return settle_date 日结日期
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 日结日期
     * @param settleDate 日结日期
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 日结单号
     * @return code 日结单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 日结单号
     * @param code 日结单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 日结人员ID
     * @return settle_man_id 日结人员ID
     */
    public Long getSettleManId() {
        return settleManId;
    }

    /**
     * 日结人员ID
     * @param settleManId 日结人员ID
     */
    public void setSettleManId(Long settleManId) {
        this.settleManId = settleManId;
    }

    /**
     * 日结人员编码
     * @return settle_man_code 日结人员编码
     */
    public String getSettleManCode() {
        return settleManCode;
    }

    /**
     * 日结人员编码
     * @param settleManCode 日结人员编码
     */
    public void setSettleManCode(String settleManCode) {
        this.settleManCode = settleManCode == null ? null : settleManCode.trim();
    }

    /**
     * 日结人员名称
     * @return settle_man_name 日结人员名称
     */
    public String getSettleManName() {
        return settleManName;
    }

    /**
     * 日结人员名称
     * @param settleManName 日结人员名称
     */
    public void setSettleManName(String settleManName) {
        this.settleManName = settleManName == null ? null : settleManName.trim();
    }

    /**
     * 销售笔数合计
     * @return sale_pens_total 销售笔数合计
     */
    public Integer getSalePensTotal() {
        return salePensTotal;
    }

    /**
     * 销售笔数合计
     * @param salePensTotal 销售笔数合计
     */
    public void setSalePensTotal(Integer salePensTotal) {
        this.salePensTotal = salePensTotal;
    }

    /**
     * 销售金额合计
     * @return sale_amount_total 销售金额合计
     */
    public BigDecimal getSaleAmountTotal() {
        return saleAmountTotal;
    }

    /**
     * 销售金额合计
     * @param saleAmountTotal 销售金额合计
     */
    public void setSaleAmountTotal(BigDecimal saleAmountTotal) {
        this.saleAmountTotal = saleAmountTotal;
    }

    /**
     * 退货笔数合计
     * @return return_pens_total 退货笔数合计
     */
    public Integer getReturnPensTotal() {
        return returnPensTotal;
    }

    /**
     * 退货笔数合计
     * @param returnPensTotal 退货笔数合计
     */
    public void setReturnPensTotal(Integer returnPensTotal) {
        this.returnPensTotal = returnPensTotal;
    }

    /**
     * 退货金额合计
     * @return return_amount_total 退货金额合计
     */
    public BigDecimal getReturnAmountTotal() {
        return returnAmountTotal;
    }

    /**
     * 退货金额合计
     * @param returnAmountTotal 退货金额合计
     */
    public void setReturnAmountTotal(BigDecimal returnAmountTotal) {
        this.returnAmountTotal = returnAmountTotal;
    }

    /**
     * 应收金额合计
     * @return amount_total 应收金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 应收金额合计
     * @param amountTotal 应收金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     * @return status 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     * @param status 状态（0-待缴款；1-已缴款；2-已冲销）
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
        sb.append(", settleDate=").append(settleDate);
        sb.append(", code=").append(code);
        sb.append(", settleManId=").append(settleManId);
        sb.append(", settleManCode=").append(settleManCode);
        sb.append(", settleManName=").append(settleManName);
        sb.append(", salePensTotal=").append(salePensTotal);
        sb.append(", saleAmountTotal=").append(saleAmountTotal);
        sb.append(", returnPensTotal=").append(returnPensTotal);
        sb.append(", returnAmountTotal=").append(returnAmountTotal);
        sb.append(", amountTotal=").append(amountTotal);
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