package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_payment_voucher
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class PaymentVoucherVO implements Serializable {

    private String updateReason;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    @ApiModelProperty(value = "如果调用草稿，需要传这个字段，后台删除")
    private String redisKeyValue;


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

    @ApiModelProperty(value = "科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）")
    private Integer financeAccountType;

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

    @ApiModelProperty(value = "单位类型（1:企业,2:供应商）")
    private Integer type;
    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;
    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

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
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private Integer status;

    //应付贷项凭证明细
    List<PaymentVoucherDetailVO> paymentVoucherDetailVOList;

    @ApiModelProperty(value = "数据合计VO")
    private DataTotalVO dataTotalVO;

    /**
     * 金额差额合计
     */
    @ApiModelProperty(value = "金额差额合计")
    private BigDecimal diffAmountTotal;

    /**
     * 不含税金额差额合计
     */
    @ApiModelProperty(value = "不含税金额差额合计")
    private BigDecimal diffNotaxAmountTotal;

    /**
     * 税额差额合计
     */
    @ApiModelProperty(value = "税额差额合计")
    private BigDecimal diffTaxAmountTotal;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Long getPostManId() {
        return postManId;
    }

    public void setPostManId(Long postManId) {
        this.postManId = postManId;
    }

    public String getPostManCode() {
        return postManCode;
    }

    public void setPostManCode(String postManCode) {
        this.postManCode = postManCode;
    }

    public String getPostManName() {
        return postManName;
    }

    public void setPostManName(String postManName) {
        this.postManName = postManName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PaymentVoucherDetailVO> getPaymentVoucherDetailVOList() {
        return paymentVoucherDetailVOList;
    }

    public void setPaymentVoucherDetailVOList(List<PaymentVoucherDetailVO> paymentVoucherDetailVOList) {
        this.paymentVoucherDetailVOList = paymentVoucherDetailVOList;
    }

    public DataTotalVO getDataTotalVO() {
        dataTotalVO = new DataTotalVO();
        dataTotalVO.setQuantityTotal(this.getQuantityTotal());
        dataTotalVO.setAmountTotal(this.getAmountTotal());
        dataTotalVO.setNotaxAmountTotal(this.getNotaxAmountTotal());
        dataTotalVO.setTaxAmountTotal(this.getTaxAmountTotal());
        return dataTotalVO;
    }

    public void setDataTotalVO(DataTotalVO dataTotalVO) {
        this.dataTotalVO = dataTotalVO;
    }

    public Integer getFinanceAccountType() {
        return financeAccountType;
    }

    public void setFinanceAccountType(Integer financeAccountType) {
        this.financeAccountType = financeAccountType;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    public BigDecimal getDiffNotaxAmountTotal() {
        return diffNotaxAmountTotal;
    }

    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
    }

    public BigDecimal getDiffTaxAmountTotal() {
        return diffTaxAmountTotal;
    }

    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
        this.diffTaxAmountTotal = diffTaxAmountTotal;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }
}