package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品销毁报表
 * xingjian.lan
 */
public class GoodsDestroyReportVO extends BaseGoodsDetailVO implements Serializable {

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
     * 单据（编码
     */
    @ApiModelProperty(value = "单号")
    private String destroyCode;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "日期")
    private Date destroyDate;

    /**
     * 销毁人员
     */
    @ApiModelProperty(value = "销毁人员")
    private String destroyManName;

    /**
     * 销毁原因
     */
    @ApiModelProperty(value = "销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）")
    private Integer destroyReason;

    private String destroyReasonDesc;

    /**
     * 销毁时间
     */
    @ApiModelProperty(value = "销毁时间")
    private String destroyTime;

    /**
     * 销毁方式
     */
    @ApiModelProperty(value = "销毁方式")
    private String destroyMode;

    /**
     * 销毁机构
     */
    @ApiModelProperty(value = "销毁机构")
    private String destroyUnit;

    /**
     * 销毁地点
     */
    @ApiModelProperty(value = "销毁地点")
    private String destroyPlace;

    /**
     * 监督人员1
     */
    @ApiModelProperty(value = "监督人员1")
    private String monitor;

    /**
     * 监督人员2
     */
    @ApiModelProperty(value = "监督人员2")
    private String secondMonitor;

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
        return quantity != null ? quantity.setScale(2, BigDecimal.ROUND_HALF_UP) : quantity;
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

    // 商品销毁没有相关状态，默认已完成
    public String getStatusDesc() {
        return "已完成";
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getDestroyCode() {
        return destroyCode;
    }

    public void setDestroyCode(String destroyCode) {
        this.destroyCode = destroyCode;
    }

    public Date getDestroyDate() {
        return destroyDate;
    }

    public void setDestroyDate(Date destroyDate) {
        this.destroyDate = destroyDate;
    }

    public String getDestroyManName() {
        return destroyManName;
    }

    public void setDestroyManName(String destroyManName) {
        this.destroyManName = destroyManName;
    }

    public Integer getDestroyReason() {
        return destroyReason;
    }

    public void setDestroyReason(Integer destroyReason) {
        this.destroyReason = destroyReason;
    }

    //0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它
    public String getDestroyReasonDesc() {
        if (destroyReason == null) {
            return "";
        }
        switch (destroyReason) {
            case 0:
                return "过期";
            case 1:
                return "失效";
            case 2:
                return "破损";
            case 3:
                return "残损";
            case 4:
                return "变色";
            case 5:
                return "发霉";
            case 6:
                return "虫蛀";
            case 7:
                return "其他";
        }
        return destroyReasonDesc;
    }

    public void setDestroyReasonDesc(String destroyReasonDesc) {
        this.destroyReasonDesc = destroyReasonDesc;
    }

    public String getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(String destroyTime) {
        this.destroyTime = destroyTime;
    }

    public String getDestroyMode() {
        return destroyMode;
    }

    public void setDestroyMode(String destroyMode) {
        this.destroyMode = destroyMode;
    }

    public String getDestroyUnit() {
        return destroyUnit;
    }

    public void setDestroyUnit(String destroyUnit) {
        this.destroyUnit = destroyUnit;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getSecondMonitor() {
        return secondMonitor;
    }

    public void setSecondMonitor(String secondMonitor) {
        this.secondMonitor = secondMonitor;
    }

    public String getDestroyPlace() {
        return destroyPlace;
    }

    public void setDestroyPlace(String destroyPlace) {
        this.destroyPlace = destroyPlace;
    }
}