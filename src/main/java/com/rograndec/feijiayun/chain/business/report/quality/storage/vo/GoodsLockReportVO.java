package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品锁定报表
 * xingjian.lan
 */
public class GoodsLockReportVO extends BaseGoodsDetailVO implements Serializable {

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
     * 单据（药品锁定单）编码
     */
    @ApiModelProperty(value = "单据（药品锁定单）编码")
    private String lockCode;

    /**
     * 单据（药品锁定单）日期
     */
    @ApiModelProperty(value = "单据（药品锁定单）日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lockDate;

    /**
     * 锁定人员名称
     */
    @ApiModelProperty(value = "锁定人员名称")
    private String lockManName;

    /**
     * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
     */
    @ApiModelProperty(value = "锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）")
    private Integer lockReason;

    private String lockReasonDesc;

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

    public String getLockCode() {
        return lockCode;
    }

    public void setLockCode(String lockCode) {
        this.lockCode = lockCode;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public String getLockManName() {
        return lockManName;
    }

    public void setLockManName(String lockManName) {
        this.lockManName = lockManName;
    }

    public Integer getLockReason() {
        return lockReason;
    }

    public void setLockReason(Integer lockReason) {
        this.lockReason = lockReason;
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

    // 34-已取消 92-待处理 33-已处理
    public String getStatusDesc() {
        String descStr = "";
        switch (status) {
            case 34:
                descStr = "已取消";
                break;
            case 92:
                descStr = "待处理";
                break;
            case 33:
                descStr = "已处理";
                break;
        }
        return descStr;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getLockReasonDesc() {
        String descStr = "";
        switch (lockReason) {
            case 0:
                descStr = "疑似质量问题";
                break;
            case 1:
                descStr = "疑似伪劣商品";
                break;
            case 2:
                descStr = "药品养护问题商品";
                break;
            case 3:
                descStr = "陈列检查问题商品";
                break;
        }
        return descStr;
    }

    public void setLockReasonDesc(String lockReasonDesc) {
        this.lockReasonDesc = lockReasonDesc;
    }
}