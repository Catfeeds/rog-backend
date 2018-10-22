package com.rograndec.feijiayun.chain.business.retail.dailysettle.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by madong on 2017/9/22.
 */
public class WillDailySettleDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 交接班ID/日结总单ID(当未日结查看时是交接班ID,已日结查看是日结总单ID)
     */
    @ApiModelProperty(value = "交接班ID/日结总单ID(当未日结查看时是交接班ID,已日结查看是日结总单ID)")
    private Long id;
    /**
     * 交接班日期/日结日期(当未日结查看时是交接班日期,已日结查看是日结日期)
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "交接班日期/日结日期(当未日结查看时是交接班日期,已日结查看是日结日期)")
    private Date date;
    /**
     * 款台code集合
     */
    @ApiModelProperty(value = "款台code集合")
    private String standCodes;
    /**
     * 班组
     */
    @ApiModelProperty(value = "班组")
    private String posTeamName;
    /**
     * 收款人员ID
     */
    @ApiModelProperty(value = "收款人员ID")
    private Long payeeId;
    /**
     * 收款人员编码
     */
    @ApiModelProperty(value = "收款人员编码")
    private String payeeCode;
    /**
     * 收款人员姓名
     */
    @ApiModelProperty(value = "收款人员姓名")
    private String payeeName;
    /**
     * 开班时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开班时间")
    private java.util.Date openingTime;
    /**
     * 交班时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "交班时间")
    private java.util.Date shiftTime;
    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal amountTotal;
    /**
     * 交接班明细ID集合
     */
    @ApiModelProperty(value = "交接班明细ID集合")
    private String shiftDetailIds;
    /**
     * 零售流水ID集合
     */
    @ApiModelProperty(value = "零售流水ID集合")
    private String saleIds;
    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;
    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 门店ID
     */
    @ApiModelProperty(value = "门店ID")
    private Long enterpriseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStandCodes() {
        return standCodes;
    }

    public void setStandCodes(String standCodes) {
        this.standCodes = standCodes == null ? null : standCodes.trim();
    }

    public String getPosTeamName() {
        return posTeamName;
    }

    public void setPosTeamName(String posTeamName) {
        this.posTeamName = posTeamName == null ? null : posTeamName.trim();
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeCode() {
        return payeeCode;
    }

    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode == null ? null : payeeCode.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public java.util.Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(java.util.Date openingTime) {
        this.openingTime = openingTime;
    }

    public java.util.Date getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(java.util.Date shiftTime) {
        this.shiftTime = shiftTime;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public String getShiftDetailIds() {
        return shiftDetailIds;
    }

    public void setShiftDetailIds(String shiftDetailIds) {
        this.shiftDetailIds = shiftDetailIds == null ? null : shiftDetailIds.trim();
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public String getSaleIds() {
        return saleIds;
    }

    public void setSaleIds(String saleIds) {
        this.saleIds = saleIds == null ? null : saleIds.trim();
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WillDailySettleDetailVO that = (WillDailySettleDetailVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (standCodes != null ? !standCodes.equals(that.standCodes) : that.standCodes != null) return false;
        if (posTeamName != null ? !posTeamName.equals(that.posTeamName) : that.posTeamName != null) return false;
        if (payeeId != null ? !payeeId.equals(that.payeeId) : that.payeeId != null) return false;
        if (payeeCode != null ? !payeeCode.equals(that.payeeCode) : that.payeeCode != null) return false;
        if (payeeName != null ? !payeeName.equals(that.payeeName) : that.payeeName != null) return false;
        if (openingTime != null ? !openingTime.equals(that.openingTime) : that.openingTime != null) return false;
        if (shiftTime != null ? !shiftTime.equals(that.shiftTime) : that.shiftTime != null) return false;
        if (amountTotal != null ? !amountTotal.equals(that.amountTotal) : that.amountTotal != null) return false;
        if (shiftDetailIds != null ? !shiftDetailIds.equals(that.shiftDetailIds) : that.shiftDetailIds != null)
            return false;
        if (saleIds != null ? !saleIds.equals(that.saleIds) : that.saleIds != null) return false;
        if (notaxRealAmountTotal != null ? !notaxRealAmountTotal.equals(that.notaxRealAmountTotal) : that.notaxRealAmountTotal != null)
            return false;
        if (taxAmountTotal != null ? !taxAmountTotal.equals(that.taxAmountTotal) : that.taxAmountTotal != null)
            return false;
        return enterpriseId != null ? enterpriseId.equals(that.enterpriseId) : that.enterpriseId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (standCodes != null ? standCodes.hashCode() : 0);
        result = 31 * result + (posTeamName != null ? posTeamName.hashCode() : 0);
        result = 31 * result + (payeeId != null ? payeeId.hashCode() : 0);
        result = 31 * result + (payeeCode != null ? payeeCode.hashCode() : 0);
        result = 31 * result + (payeeName != null ? payeeName.hashCode() : 0);
        result = 31 * result + (openingTime != null ? openingTime.hashCode() : 0);
        result = 31 * result + (shiftTime != null ? shiftTime.hashCode() : 0);
        result = 31 * result + (amountTotal != null ? amountTotal.hashCode() : 0);
        result = 31 * result + (shiftDetailIds != null ? shiftDetailIds.hashCode() : 0);
        result = 31 * result + (saleIds != null ? saleIds.hashCode() : 0);
        result = 31 * result + (notaxRealAmountTotal != null ? notaxRealAmountTotal.hashCode() : 0);
        result = 31 * result + (taxAmountTotal != null ? taxAmountTotal.hashCode() : 0);
        result = 31 * result + (enterpriseId != null ? enterpriseId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WillDailySettleDetailVO{" +
                "id=" + id +
                ", date=" + date +
                ", standCodes='" + standCodes + '\'' +
                ", posTeamName='" + posTeamName + '\'' +
                ", payeeId=" + payeeId +
                ", payeeCode='" + payeeCode + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", openingTime=" + openingTime +
                ", shiftTime=" + shiftTime +
                ", amountTotal=" + amountTotal +
                ", shiftDetailIds='" + shiftDetailIds + '\'' +
                ", saleIds='" + saleIds + '\'' +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", enterpriseId=" + enterpriseId +
                '}';
    }
}
