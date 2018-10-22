package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PurchaseCheckDraftCacheVO implements Serializable {

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期", required = true)
    private Date checkDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID", required = true)
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型", required = true)
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码", required = true)
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期", required = true)
    private Date baseOrderDate;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID", required = true)
    private Long supplierId;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码", required = true)
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称", required = true)
    private String supplierName;

    /**
     * 供货单位销售人员ID
     */
    @ApiModelProperty(value = "供货单位销售人员ID", required = true)
    private Long supplierSalerId;

    /**
     * 供货单位销售人员编码
     */
    @ApiModelProperty(value = "供货单位销售人员编码", required = true)
    private String supplierSalerCode;

    /**
     * 供货单位销售人员名称
     */
    @ApiModelProperty(value = "供货单位销售人员名称", required = true)
    private String supplierSalerName;

    /**
     * 供货单位销售人员联系电话
     */
    @ApiModelProperty(value = "供货单位销售人员联系电话", required = true)
    private String supplierSalerPhone;

    /**
     * 验收人员ID
     */
    @ApiModelProperty(value = "验收人员ID", required = true)
    private Long checkerId;

    /**
     * 验收人员编码
     */
    @ApiModelProperty(value = "验收人员编码", required = true)
    private String checkerCode;

    /**
     * 验收人员名称
     */
    @ApiModelProperty(value = "验收人员名称", required = true)
    private String checkerName;

    /**
     * 验收人员2ID
     */
    @ApiModelProperty(value = "验收人员2ID", required = true)
    private Long secondCheckerId;

    /**
     * 验收人员2编码
     */
    @ApiModelProperty(value = "验收人员2编码", required = true)
    private String secondCheckerCode;

    /**
     * 验收人员2名称
     */
    @ApiModelProperty(value = "验收人员2名称", required = true)
    private String secondCheckerName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（0-是  1-否）", required = true)
    private Integer specialDrug;


    private List<PurchaseCheckDetailDraftCacheVO> purchaseCheckDetailDraftCacheVOS;

    /**
     * saas_purchase_check
     */
    private static final long serialVersionUID = 1L;


    /**
     * 验收日期
     * @return check_date 验收日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 验收日期
     * @param checkDate 验收日期
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 供货单位ID
     * @return supplier_id 供货单位ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * 供货单位ID
     * @param supplierId 供货单位ID
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 供货单位编码
     * @return supplier_code 供货单位编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * 供货单位编码
     * @param supplierCode 供货单位编码
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }

    /**
     * 供货单位名称
     * @return supplier_name 供货单位名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 供货单位名称
     * @param supplierName 供货单位名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     * 供货单位销售人员ID
     * @return supplier_saler_id 供货单位销售人员ID
     */
    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    /**
     * 供货单位销售人员ID
     * @param supplierSalerId 供货单位销售人员ID
     */
    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    /**
     * 供货单位销售人员编码
     * @return supplier_saler_code 供货单位销售人员编码
     */
    public String getSupplierSalerCode() {
        return supplierSalerCode;
    }

    /**
     * 供货单位销售人员编码
     * @param supplierSalerCode 供货单位销售人员编码
     */
    public void setSupplierSalerCode(String supplierSalerCode) {
        this.supplierSalerCode = supplierSalerCode == null ? null : supplierSalerCode.trim();
    }

    /**
     * 供货单位销售人员名称
     * @return supplier_saler_name 供货单位销售人员名称
     */
    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    /**
     * 供货单位销售人员名称
     * @param supplierSalerName 供货单位销售人员名称
     */
    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName == null ? null : supplierSalerName.trim();
    }

    /**
     * 供货单位销售人员联系电话
     * @return Tis upplier_saler_phone 供货单位销售人员联系电话
     */
    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    /**
     * 供货单位销售人员联系电话
     * @param supplierSalerPhone 供货单位销售人员联系电话
     */
    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone == null ? null : supplierSalerPhone.trim();
    }

    /**
     * 验收人员ID
     * @return checker_id 验收人员ID
     */
    public Long getCheckerId() {
        return checkerId;
    }

    /**
     * 验收人员ID
     * @param checkerId 验收人员ID
     */
    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * 验收人员编码
     * @return checker_code 验收人员编码
     */
    public String getCheckerCode() {
        return checkerCode;
    }

    /**
     * 验收人员编码
     * @param checkerCode 验收人员编码
     */
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode == null ? null : checkerCode.trim();
    }

    /**
     * 验收人员名称
     * @return checker_name 验收人员名称
     */
    public String getCheckerName() {
        return checkerName;
    }

    /**
     * 验收人员名称
     * @param checkerName 验收人员名称
     */
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName == null ? null : checkerName.trim();
    }

    /**
     * 验收人员2ID
     * @return second_checker_id 验收人员2ID
     */
    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    /**
     * 验收人员2ID
     * @param secondCheckerId 验收人员2ID
     */
    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    /**
     * 验收人员2编码
     * @return second_checker_code 验收人员2编码
     */
    public String getSecondCheckerCode() {
        return secondCheckerCode;
    }

    /**
     * 验收人员2编码
     * @param secondCheckerCode 验收人员2编码
     */
    public void setSecondCheckerCode(String secondCheckerCode) {
        this.secondCheckerCode = secondCheckerCode == null ? null : secondCheckerCode.trim();
    }

    /**
     * 验收人员2名称
     * @return second_checker_name 验收人员2名称
     */
    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    /**
     * 验收人员2名称
     * @param secondCheckerName 验收人员2名称
     */
    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName == null ? null : secondCheckerName.trim();
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
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


    public List<PurchaseCheckDetailDraftCacheVO> getPurchaseCheckDetailDraftCacheVOS() {
        return purchaseCheckDetailDraftCacheVOS;
    }

    public void setPurchaseCheckDetailDraftCacheVOS(List<PurchaseCheckDetailDraftCacheVO> purchaseCheckDetailDraftCacheVOS) {
        this.purchaseCheckDetailDraftCacheVOS = purchaseCheckDetailDraftCacheVOS;
    }

    @Override
    public String toString() {
        return "PurchaseCheckDraftCacheVO[" +
                "checkDate=" + checkDate +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderType=" + baseOrderType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
                ", supplierId=" + supplierId +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerId=" + supplierSalerId +
                ", supplierSalerCode='" + supplierSalerCode + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", checkerId=" + checkerId +
                ", checkerCode='" + checkerCode + '\'' +
                ", specialDrug='" + specialDrug + '\'' +
                ", checkerName='" + checkerName + '\'' +
                ", secondCheckerId=" + secondCheckerId +
                ", secondCheckerCode='" + secondCheckerCode + '\'' +
                ", secondCheckerName='" + secondCheckerName + '\'' +
                ", remark='" + remark + '\'' +
                ", purchaseCheckDetailDraftCacheVOS=" + purchaseCheckDetailDraftCacheVOS +
                ']';
    }
}