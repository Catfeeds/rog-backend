package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by zeshi.sun on 2017/9/18.
 */
public class CheckHeadVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 单据（验收单）编号
     */
    @ApiModelProperty(value = "单据（验收单）编号", required = true)
    private String code;

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
     * 供货单位销售人员
     */
    @ApiModelProperty(value = "供货单位销售人员", required = true)
    private String supplierSalerName;

    /**
     * 供货单位销售人员联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
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
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期", required = true)
    private Date baseOrderDate;


    /**
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（0-是  1-否）", required = true)
    private Integer specialDrug;

    /**
     * 调用草稿返回的redisID
     */
    @ApiModelProperty(value = "调用草稿返回的redisID", required = true)
    private String redisKeyValue;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 调用草稿时使用
     */
    List<CheckDeatilVO> checkDeatilVO;

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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    public String getSupplierSalerCode() {
        return supplierSalerCode;
    }

    public void setSupplierSalerCode(String supplierSalerCode) {
        this.supplierSalerCode = supplierSalerCode;
    }

    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName;
    }

    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerCode() {
        return checkerCode;
    }

    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    public String getSecondCheckerCode() {
        return secondCheckerCode;
    }

    public void setSecondCheckerCode(String secondCheckerCode) {
        this.secondCheckerCode = secondCheckerCode;
    }

    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public List<CheckDeatilVO> getCheckDeatilVO() {
        return checkDeatilVO;
    }

    public void setCheckDeatilVO(List<CheckDeatilVO> checkDeatilVO) {
        this.checkDeatilVO = checkDeatilVO;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CheckHeadVO[" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", supplierId=" + supplierId +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerId=" + supplierSalerId +
                ", supplierSalerCode='" + supplierSalerCode + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", checkerId=" + checkerId +
                ", checkerCode='" + checkerCode + '\'' +
                ", checkerName='" + checkerName + '\'' +
                ", secondCheckerId=" + secondCheckerId +
                ", secondCheckerCode='" + secondCheckerCode + '\'' +
                ", secondCheckerName='" + secondCheckerName + '\'' +
                ", checkDate=" + checkDate +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderDate=" + baseOrderDate +
                ", specialDrug=" + specialDrug +
                ", redisKeyValue=" + redisKeyValue +
                ", remark=" + remark +
                ", checkDeatilVO=" + checkDeatilVO +
                ']';
    }
}
