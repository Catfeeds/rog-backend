package com.rograndec.feijiayun.chain.business.storage.move.vo;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherIn;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OtherInPageVO implements Serializable{


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private Date inDate;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String code;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String inManName;

    /**
     * 入库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     */
    @ApiModelProperty(value = "入库类型（0-获赠；1-报溢；2-领用退回；3-其它）")
    private Integer inType;

    /**
     * 来源单位名称
     */
    @ApiModelProperty(value = "来源单位名称")
    private String srcUnitName;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审批状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中")
    private Integer approveStatus;

    @ApiModelProperty(value = "审批状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中")
    private String approveStatusDesc;
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
     * 右下角当前查询条件下的金额合计
     */
    @ApiModelProperty(value = "右下角当前查询条件下的金额合计")
    private BigDecimal stasticAmountTotal;
    /**
     *
     * 右下角当前查询条件下的不含税金额合计
     */
    @ApiModelProperty(value = "右下角当前查询条件下的不含税金额合计")
    private BigDecimal stasticNotaxAmountTotal;
    /**
      右下角当前查询条件下税额的合计
     */
    @ApiModelProperty(value = "右下角当前查询条件下税额的合计")
    private BigDecimal stasticTaxAmountTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInManName() {
        return inManName;
    }

    public void setInManName(String inManName) {
        this.inManName = inManName;
    }

    public Integer getInType() {
        return inType;
    }

    public void setInType(Integer inType) {
        this.inType = inType;
    }

    public String getSrcUnitName() {
        return srcUnitName;
    }

    public void setSrcUnitName(String srcUnitName) {
        this.srcUnitName = srcUnitName;
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

    public BigDecimal getStasticAmountTotal() {
        return stasticAmountTotal;
    }

    public void setStasticAmountTotal(BigDecimal stasticAmountTotal) {
        this.stasticAmountTotal = stasticAmountTotal;
    }

    public BigDecimal getStasticNotaxAmountTotal() {
        return stasticNotaxAmountTotal;
    }

    public void setStasticNotaxAmountTotal(BigDecimal stasticNotaxAmountTotal) {
        this.stasticNotaxAmountTotal = stasticNotaxAmountTotal;
    }

    public BigDecimal getStasticTaxAmountTotal() {
        return stasticTaxAmountTotal;
    }

    public void setStasticTaxAmountTotal(BigDecimal stasticTaxAmountTotal) {
        this.stasticTaxAmountTotal = stasticTaxAmountTotal;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveStatusDesc() {
        return approveStatusDesc;
    }

    public void setApproveStatusDesc(String approveStatusDesc) {
        this.approveStatusDesc = approveStatusDesc;
    }

    @Override
    public String toString() {
        return "OtherInPageVO{" +
                "id=" + id +
                ", inDate=" + inDate +
                ", code='" + code + '\'' +
                ", inManName='" + inManName + '\'' +
                ", inType=" + inType +
                ", srcUnitName='" + srcUnitName + '\'' +
                ", amountTotal=" + amountTotal +
                '}';
    }

    public static OtherInPageVO convertToPageVO(OtherIn s) {
        OtherInPageVO vo = new OtherInPageVO();
        vo.setId(s.getId());
        vo.setInDate(s.getInDate());
        vo.setCode(s.getCode());
        vo.setInManName(s.getInManName());
        vo.setInType(s.getInType());
        vo.setSrcUnitName(s.getSrcUnitName());
        vo.setAmountTotal(s.getAmountTotal());
        vo.setNotaxAmountTotal(s.getNotaxAmountTotal());
        vo.setTaxAmountTotal(s.getTaxAmountTotal());
        vo.setApproveStatus(s.getStatus());
        return vo;
    }
}
