package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_retail_daily_settle_detail
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailDailySettleDetailVO implements Serializable {
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
     * 日结单ID
     */
    @ApiModelProperty(value = "日结单ID")
    private Long settleId;

    /**
     * 交班单据ID
     */
    @ApiModelProperty(value = "交班单据ID")
    private Long shiftId;

    /**
     * 交班人员ID
     */
    @ApiModelProperty(value = "交班人员ID")
    private Long shiftManId;

    /**
     * 交班人编码
     */
    @ApiModelProperty(value = "交班人编码")
    private String shiftManCode;

    /**
     * 交班人名称
     */
    @ApiModelProperty(value = "交班人名称")
    private String shiftManName;

    /**
     * 开班时间
     */
    @ApiModelProperty(value = "开班时间")
    private Date shiftStartDate;

    /**
     * 交班时间
     */
    @ApiModelProperty(value = "交班时间")
    private Date shiftEndDate;

    /**
     * 销售笔数
     */
    @ApiModelProperty(value = "销售笔数")
    private Integer salePens;

    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private BigDecimal saleAmount;

    /**
     * 退货笔数
     */
    @ApiModelProperty(value = "退货笔数")
    private Integer returnPens;

    /**
     * 退货金额
     */
    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnAmount;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal amount;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

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
     * saas_retail_daily_settle_detail
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
     * 日结单ID
     * @return settle_id 日结单ID
     */
    public Long getSettleId() {
        return settleId;
    }

    /**
     * 日结单ID
     * @param settleId 日结单ID
     */
    public void setSettleId(Long settleId) {
        this.settleId = settleId;
    }

    /**
     * 交班单据ID
     * @return shift_id 交班单据ID
     */
    public Long getShiftId() {
        return shiftId;
    }

    /**
     * 交班单据ID
     * @param shiftId 交班单据ID
     */
    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    /**
     * 交班人员ID
     * @return shift_man_id 交班人员ID
     */
    public Long getShiftManId() {
        return shiftManId;
    }

    /**
     * 交班人员ID
     * @param shiftManId 交班人员ID
     */
    public void setShiftManId(Long shiftManId) {
        this.shiftManId = shiftManId;
    }

    /**
     * 交班人编码
     * @return shift_man_code 交班人编码
     */
    public String getShiftManCode() {
        return shiftManCode;
    }

    /**
     * 交班人编码
     * @param shiftManCode 交班人编码
     */
    public void setShiftManCode(String shiftManCode) {
        this.shiftManCode = shiftManCode == null ? null : shiftManCode.trim();
    }

    /**
     * 交班人名称
     * @return shift_man_name 交班人名称
     */
    public String getShiftManName() {
        return shiftManName;
    }

    /**
     * 交班人名称
     * @param shiftManName 交班人名称
     */
    public void setShiftManName(String shiftManName) {
        this.shiftManName = shiftManName == null ? null : shiftManName.trim();
    }

    /**
     * 开班时间
     * @return shift_start_date 开班时间
     */
    public Date getShiftStartDate() {
        return shiftStartDate;
    }

    /**
     * 开班时间
     * @param shiftStartDate 开班时间
     */
    public void setShiftStartDate(Date shiftStartDate) {
        this.shiftStartDate = shiftStartDate;
    }

    /**
     * 交班时间
     * @return shift_end_date 交班时间
     */
    public Date getShiftEndDate() {
        return shiftEndDate;
    }

    /**
     * 交班时间
     * @param shiftEndDate 交班时间
     */
    public void setShiftEndDate(Date shiftEndDate) {
        this.shiftEndDate = shiftEndDate;
    }

    /**
     * 销售笔数
     * @return sale_pens 销售笔数
     */
    public Integer getSalePens() {
        return salePens;
    }

    /**
     * 销售笔数
     * @param salePens 销售笔数
     */
    public void setSalePens(Integer salePens) {
        this.salePens = salePens;
    }

    /**
     * 销售金额
     * @return sale_amount 销售金额
     */
    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    /**
     * 销售金额
     * @param saleAmount 销售金额
     */
    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    /**
     * 退货笔数
     * @return return_pens 退货笔数
     */
    public Integer getReturnPens() {
        return returnPens;
    }

    /**
     * 退货笔数
     * @param returnPens 退货笔数
     */
    public void setReturnPens(Integer returnPens) {
        this.returnPens = returnPens;
    }

    /**
     * 退货金额
     * @return return_amount 退货金额
     */
    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    /**
     * 退货金额
     * @param returnAmount 退货金额
     */
    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    /**
     * 应收金额
     * @return amount 应收金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 应收金额
     * @param amount 应收金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
        sb.append(", settleId=").append(settleId);
        sb.append(", shiftId=").append(shiftId);
        sb.append(", shiftManId=").append(shiftManId);
        sb.append(", shiftManCode=").append(shiftManCode);
        sb.append(", shiftManName=").append(shiftManName);
        sb.append(", shiftStartDate=").append(shiftStartDate);
        sb.append(", shiftEndDate=").append(shiftEndDate);
        sb.append(", salePens=").append(salePens);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", returnPens=").append(returnPens);
        sb.append(", returnAmount=").append(returnAmount);
        sb.append(", amount=").append(amount);
        sb.append(", lineNum=").append(lineNum);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}