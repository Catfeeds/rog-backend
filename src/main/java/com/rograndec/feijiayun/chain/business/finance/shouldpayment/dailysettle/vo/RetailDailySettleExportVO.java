package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_retail_daily_settle
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailDailySettleExportVO implements Serializable {
    /**
     * 第一行标题
     */
    @ApiModelProperty(value = "第一行标题")
    private String titleFirstRow;

    /**
     * 第二行标题
     */
    @ApiModelProperty(value = "第二行标题")
    private String titleSecondRow;

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
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    private String enterpriseCode;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

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
     * 实收金额合计
     */
    @ApiModelProperty(value = "实收金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 实收金额合计
     */
    @ApiModelProperty(value = "实收金额合计")
    private BigDecimal diffAmountTotal;

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待缴款；1-已缴款；2-已冲销）")
    private Integer status;

    /**
     * 状态名
     */
    @ApiModelProperty(value = "状态名")
    private String statusName;

    @ApiModelProperty(value = "修改标记 ture可修改,false不可修改")
    private Boolean updateFlag;

    @ApiModelProperty(value = "冲销标记 ture可冲销,false不可冲销")
    private Boolean chargrAgainstFlag;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private List<RetailDailySettleDetailVO> retailDailySettleDetailVOS;

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
     * 实收金额合计
     * @return real_amount_total 实收金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实收金额合计
     * @param realAmountTotal 实收金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 实收金额合计
     * @return diff_amount_total 实收金额合计
     */
    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    /**
     * 实收金额合计
     * @param diffAmountTotal 实收金额合计
     */
    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
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

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public List<RetailDailySettleDetailVO> getRetailDailySettleDetailVOS() {
        return retailDailySettleDetailVOS;
    }

    public void setRetailDailySettleDetailVOS(List<RetailDailySettleDetailVO> retailDailySettleDetailVOS) {
        this.retailDailySettleDetailVOS = retailDailySettleDetailVOS;
    }

    public Boolean getChargrAgainstFlag() {
        return chargrAgainstFlag;
    }

    public void setChargrAgainstFlag(Boolean chargrAgainstFlag) {
        this.chargrAgainstFlag = chargrAgainstFlag;
    }

    public String getTitleFirstRow() {
        return titleFirstRow;
    }

    public void setTitleFirstRow(String titleFirstRow) {
        this.titleFirstRow = titleFirstRow;
    }

    public String getTitleSecondRow() {
        return titleSecondRow;
    }

    public void setTitleSecondRow(String titleSecondRow) {
        this.titleSecondRow = titleSecondRow;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "RetailDailySettleVO{" +
                "id=" + id +
                ", titleFirstRow=" + titleFirstRow +
                ", titleSecondRow=" + titleSecondRow +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", settleDate=" + settleDate +
                ", code='" + code + '\'' +
                ", settleManId=" + settleManId +
                ", settleManCode='" + settleManCode + '\'' +
                ", settleManName='" + settleManName + '\'' +
                ", salePensTotal=" + salePensTotal +
                ", saleAmountTotal=" + saleAmountTotal +
                ", returnPensTotal=" + returnPensTotal +
                ", returnAmountTotal=" + returnAmountTotal +
                ", amountTotal=" + amountTotal +
                ", realAmountTotal=" + realAmountTotal +
                ", diffAmountTotal=" + diffAmountTotal +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", updateFlag=" + updateFlag +
                ", chargrAgainstFlag=" + chargrAgainstFlag +
                ", remark='" + remark + '\'' +
                ", retailDailySettleDetailVOS=" + retailDailySettleDetailVOS +
                '}';
    }
}