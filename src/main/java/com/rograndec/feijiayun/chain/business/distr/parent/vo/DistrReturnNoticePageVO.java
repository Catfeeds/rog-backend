package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DistrReturnNoticePageVO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 配后退回通知单号
     */
    @ApiModelProperty(value = "配后退回通知单号")
    private String code;

    /**
     * 配后退回通知日期
     */
    @ApiModelProperty(value = "配后退回通知日期")
    private Date noticeDate;

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

    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    @ApiModelProperty(value = "配货类型描述")
    private String distrTypeDesc;
    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

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
     * 状态
     */
    @ApiModelProperty(value = "状态 30-待收货 31-待验收 32-待入库 33-已完成")
    private String status;

    /**
     * 当前页搜索条件下的金额总计----右下角的金额总计
     */
    @ApiModelProperty(value = "当前页搜索条件下的金额总计----右下角的金额总计")
    private BigDecimal stasticRealAmountTotal;

    /**
     * 当前页搜索条件下的含税金额总计----右下角的不含税金额总计
     */
    @ApiModelProperty(value = "当前页搜索条件下的含税金额总计----右下角的不含税金额总计")
    private BigDecimal stasticNotaxRealAmountTotal;

    /**
     * 当前页搜索条件下的税额总计----右下角的税额总计
     */
    @ApiModelProperty(value = "当前页搜索条件下的税额总计----右下角的税额总计")
    private BigDecimal stasticTaxAmountTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode;
    }

    public String getRequestUnitName() {
        return requestUnitName;
    }

    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getStasticRealAmountTotal() {
        return stasticRealAmountTotal;
    }

    public void setStasticRealAmountTotal(BigDecimal stasticRealAmountTotal) {
        this.stasticRealAmountTotal = stasticRealAmountTotal;
    }

    public BigDecimal getStasticNotaxRealAmountTotal() {
        return stasticNotaxRealAmountTotal;
    }

    public void setStasticNotaxRealAmountTotal(BigDecimal stasticNotaxRealAmountTotal) {
        this.stasticNotaxRealAmountTotal = stasticNotaxRealAmountTotal;
    }

    public BigDecimal getStasticTaxAmountTotal() {
        return stasticTaxAmountTotal;
    }

    public void setStasticTaxAmountTotal(BigDecimal stasticTaxAmountTotal) {
        this.stasticTaxAmountTotal = stasticTaxAmountTotal;
    }

    public String getDistrTypeDesc() {
        return DistrType.getValue(distrType);
    }

    public void setDistrTypeDesc(String distrTypeDesc) {
        this.distrTypeDesc = distrTypeDesc;
    }

    @Override
    public String toString() {
        return "DistrReturnNoticePageVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", noticeDate=" + noticeDate +
                ", requestUnitId=" + requestUnitId +
                ", requestUnitCode='" + requestUnitCode + '\'' +
                ", requestUnitName='" + requestUnitName + '\'' +
                ", distrType=" + distrType +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", status=" + status +
                '}';
    }

    public static DistrReturnNoticePageVO converToVO(DistrReturnNotice d) {
        DistrReturnNoticePageVO vo = new DistrReturnNoticePageVO();
        vo.setId(d.getId());
        vo.setCode(d.getCode());
        vo.setNoticeDate(d.getNoticeDate());
        vo.setRequestUnitId(d.getRequestUnitId());
        vo.setRequestUnitCode(d.getRequestUnitCode());
        vo.setRequestUnitName(d.getRequestUnitName());
        vo.setDistrType(d.getDistrType());
        vo.setRealAmountTotal(d.getRealAmountTotal());
        vo.setNotaxRealAmountTotal(d.getNotaxRealAmountTotal());
        vo.setTaxAmountTotal(d.getTaxAmountTotal());
        vo.setStatus(DistrReturnStatus.getStatusDesc(d.getStatus()));
        return vo;
    }
}
