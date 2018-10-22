package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo;

import com.rograndec.feijiayun.chain.common.constant.status.ReceivableInvoiceStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_payment_voucher
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class PaymentVoucherListVO implements Serializable {
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
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 单据类型（2203）
     */
    @ApiModelProperty(value = "单据类型（2203）")
    private Integer orderType;

    /**
     * 过账日期
     */
    @ApiModelProperty(value = "过账日期")
    private Date postDate;

    /**
     * 过账人员ID
     */
    @ApiModelProperty(value = "过账人员ID")
    private Long postManId;

    /**
     * 过账人员编码
     */
    @ApiModelProperty(value = "过账人员编码")
    private String postManCode;

    /**
     * 过账人员名称
     */
    @ApiModelProperty(value = "过账人员名称")
    private String postManName;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;
//
//    /**
//     * 品种数量
//     */
//    @ApiModelProperty(value = "品种数量")
//    private Integer varietiesQuantity;

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

//    /**
//     * 已清金额合计
//     */
//    @ApiModelProperty(value = "已清金额合计")
//    private BigDecimal clearAmountTotal;
//
//    /**
//     * 未清金额合计
//     */
//    @ApiModelProperty(value = "未清金额合计")
//    private BigDecimal unclearAmountTotal;
//
//    /**
//     * 金额差额合计
//     */
//    @ApiModelProperty(value = "金额差额合计")
//    private BigDecimal diffAmountTotal;
//
//    /**
//     * 不含税金额差额合计
//     */
//    @ApiModelProperty(value = "不含税金额差额合计")
//    private BigDecimal diffNotaxAmountTotal;
//
//    /**
//     * 税额差额合计
//     */
//    @ApiModelProperty(value = "税额差额合计")
//    private BigDecimal diffTaxAmountTotal;

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private Integer status;

    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;





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
     * 单据编码
     * @return code 单据编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据编码
     * @param code 单据编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 单据类型（2203）
     * @return order_type 单据类型（2203）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（2203）
     * @param orderType 单据类型（2203）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 过账日期
     * @return post_date 过账日期
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * 过账日期
     * @param postDate 过账日期
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * 过账人员ID
     * @return post_man_id 过账人员ID
     */
    public Long getPostManId() {
        return postManId;
    }

    /**
     * 过账人员ID
     * @param postManId 过账人员ID
     */
    public void setPostManId(Long postManId) {
        this.postManId = postManId;
    }

    /**
     * 过账人员编码
     * @return post_man_code 过账人员编码
     */
    public String getPostManCode() {
        return postManCode;
    }

    /**
     * 过账人员编码
     * @param postManCode 过账人员编码
     */
    public void setPostManCode(String postManCode) {
        this.postManCode = postManCode == null ? null : postManCode.trim();
    }

    /**
     * 过账人员名称
     * @return post_man_name 过账人员名称
     */
    public String getPostManName() {
        return postManName;
    }

    /**
     * 过账人员名称
     * @param postManName 过账人员名称
     */
    public void setPostManName(String postManName) {
        this.postManName = postManName == null ? null : postManName.trim();
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
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }
//
    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }
//
//    /**
//     * 品种数量
//     * @return varieties_quantity 品种数量
//     */
//    public Integer getVarietiesQuantity() {
//        return varietiesQuantity;
//    }
//
//    /**
//     * 品种数量
//     * @param varietiesQuantity 品种数量
//     */
//    public void setVarietiesQuantity(Integer varietiesQuantity) {
//        this.varietiesQuantity = varietiesQuantity;
//    }

    /**
     * 金额合计
     * @return amount_total 金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计
     * @param amountTotal 金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxAmountTotal 不含税金额合计
     */
    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

//    /**
//     * 已清金额合计
//     * @return clear_amount_total 已清金额合计
//     */
//    public BigDecimal getClearAmountTotal() {
//        return clearAmountTotal;
//    }
//
//    /**
//     * 已清金额合计
//     * @param clearAmountTotal 已清金额合计
//     */
//    public void setClearAmountTotal(BigDecimal clearAmountTotal) {
//        this.clearAmountTotal = clearAmountTotal;
//    }
//
//    /**
//     * 未清金额合计
//     * @return unclear_amount_total 未清金额合计
//     */
//    public BigDecimal getUnclearAmountTotal() {
//        return unclearAmountTotal;
//    }
//
//    /**
//     * 未清金额合计
//     * @param unclearAmountTotal 未清金额合计
//     */
//    public void setUnclearAmountTotal(BigDecimal unclearAmountTotal) {
//        this.unclearAmountTotal = unclearAmountTotal;
//    }
//
//    /**
//     * 金额差额合计
//     * @return diff_amount_total 金额差额合计
//     */
//    public BigDecimal getDiffAmountTotal() {
//        return diffAmountTotal;
//    }
//
//    /**
//     * 金额差额合计
//     * @param diffAmountTotal 金额差额合计
//     */
//    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
//        this.diffAmountTotal = diffAmountTotal;
//    }
//
//    /**
//     * 不含税金额差额合计
//     * @return diff_notax_amount_total 不含税金额差额合计
//     */
//    public BigDecimal getDiffNotaxAmountTotal() {
//        return diffNotaxAmountTotal;
//    }
//
//    /**
//     * 不含税金额差额合计
//     * @param diffNotaxAmountTotal 不含税金额差额合计
//     */
//    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
//        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
//    }
//
//    /**
//     * 税额差额合计
//     * @return diff_tax_amount_total 税额差额合计
//     */
//    public BigDecimal getDiffTaxAmountTotal() {
//        return diffTaxAmountTotal;
//    }
//
//    /**
//     * 税额差额合计
//     * @param diffTaxAmountTotal 税额差额合计
//     */
//    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
//        this.diffTaxAmountTotal = diffTaxAmountTotal;
//    }

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     * @return status 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     * @param status 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
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
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     * @param status 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    public String getStatusName() {
        return ReceivableInvoiceStatus.getName(status);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}