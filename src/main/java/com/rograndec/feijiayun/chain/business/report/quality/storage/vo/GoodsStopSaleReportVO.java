package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品停售通知报表
 * xingjian.lan
 */
public class GoodsStopSaleReportVO extends BaseGoodsDetailVO implements Serializable {

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
    private String stopCode;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "日期")
    private Date stopDate;

    /**
     * 通知人员
     */
    @ApiModelProperty(value = "通知人员")
    private String stopManName;

    /**
     * 通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）
     */
    @ApiModelProperty(value = "通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）")
    private Integer stopFrom;

    private String stopFromDesc;

    /**
     * 停售原因
     */
    @ApiModelProperty(value = "停售原因")
    private String stopReason;

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
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
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

    public String getStopCode() {
        return stopCode;
    }

    public void setStopCode(String stopCode) {
        this.stopCode = stopCode;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getStopManName() {
        return stopManName;
    }

    public void setStopManName(String stopManName) {
        this.stopManName = stopManName;
    }

    public Integer getStopFrom() {
        return stopFrom;
    }

    public void setStopFrom(Integer stopFrom) {
        this.stopFrom = stopFrom;
    }

    //通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）
    public String getStopFromDesc() {
        if (stopFrom  == null) {
            return "";
        }
        switch (stopFrom) {
            case 0:
                return "主管单位通知";
            case 1:
                return "陈列检查";
            case 2:
                return "药品养护";
            case 3:
                return "商品锁定";
        }
        return stopFromDesc;
    }

    public void setStopFromDesc(String stopFromDesc) {
        this.stopFromDesc = stopFromDesc;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
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