package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品解停通知报表
 * xingjian.lan
 */
public class GoodsStartSaleReportVO extends BaseGoodsDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业编码")
    private String enterpriseCode;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    /**
     * 单据（编码)
     */
    @ApiModelProperty(value = "单号")
    private String startCode;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    /**
     * 通知人员
     */
    @ApiModelProperty(value = "通知人员")
    private String startManName;

    /**
     * 通知来源（0-主管单位通知；1-药品处理）
     */
    @ApiModelProperty(value = "通知来源（0-主管单位通知；1-药品处理）")
    private Integer startFrom;

    private String startFromDesc;

    /**
     * 解停原因
     */
    @ApiModelProperty(value = "解停原因")
    private String startReason;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validDate;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 质量状况
     */
    @ApiModelProperty(value = "质量状况")
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 状态描述
     */
    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartManName() {
        return startManName;
    }

    public void setStartManName(String startManName) {
        this.startManName = startManName;
    }

    public Integer getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(Integer startFrom) {
        this.startFrom = startFrom;
    }

    //通知来源（0-主管单位通知；1-药品处理）
    public String getStartFromDesc() {
        if (startFrom == null) {
            return "";
        }
        switch (startFrom) {
            case 0:
                return "主管单位通知";
            case 1:
                return "药品处理";

        }
        return startFromDesc;
    }

    public void setStartFromDesc(String startFromDesc) {
        this.startFromDesc = startFromDesc;
    }

    public String getStartReason() {
        return startReason;
    }

    public void setStartReason(String startReason) {
        this.startReason = startReason;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}