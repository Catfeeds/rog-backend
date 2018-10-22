package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo.PaymentInvoiceSaveVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceDetailResponseVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceResponseVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * saas_payment_invoice
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-15
 */
public class PaymentInvoice implements Serializable {
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
     * 单据类型（2202）
     */
    @ApiModelProperty(value = "单据类型（2202）")
    private Integer orderType;

    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期")
    private Date billDate;

    /**
     * 开票人员ID
     */
    @ApiModelProperty(value = "开票人员ID")
    private Long billManId;

    /**
     * 开票人员编码
     */
    @ApiModelProperty(value = "开票人员编码")
    private String billManCode;

    /**
     * 开票人员名称
     */
    @ApiModelProperty(value = "开票人员名称")
    private String billManName;

    /**
     * 科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     */
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

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

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
     * 已清金额合计
     */
    @ApiModelProperty(value = "已清金额合计")
    private BigDecimal clearAmountTotal;

    /**
     * 未清金额合计
     */
    @ApiModelProperty(value = "未清金额合计")
    private BigDecimal unclearAmountTotal;

    /**
     * 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待付款；1-部分付款；2-已付款；3-已冲销）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 给前端返回用,不需要赋值,并非数据库实体字段
     */
    @ApiModelProperty(value = "修改原因")
    private String reason;

    public static PaymentInvoice generateUpdatePaymentInvoice(UserVO userVO, PaymentInvoice paymentInvoice, User billMan, PaymentInvoiceSaveVO paymentInvoiceSaveVO) throws Exception {
        /**
         * 只允许编辑：开票日期、开票人员、备注字段
            已冲销单据不允许修改
         */
        PaymentInvoice pi = new PaymentInvoice();
        pi.setId(paymentInvoice.getId());
        pi.setBillManId(billMan.getId());
        pi.setBillManCode(billMan.getCode());
        pi.setBillManName(billMan.getName());
        pi.setBillDate(paymentInvoiceSaveVO.getBillDate());
        pi.setRemark(paymentInvoiceSaveVO.getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(paymentInvoice,userVO,false);
        return pi;

    }

    public static PaymentInvoice generatePaymentInvoice(UserVO userVO ,
                                                   String code,
                                                   User billMan,
                                                   Supplier supplier,
                                                   Enterprise enterprise,
                                                   PaymentInvoiceSaveVO paymentInvoiceSaveVO,
                                                   boolean isAdd) throws Exception {

        PaymentInvoice paymentInvoice = new PaymentInvoice();
        /**
         * 主键ID
         */
        paymentInvoice.setId(paymentInvoiceSaveVO.getId());

        /**
         * 企业ID
         */
        paymentInvoice.setEnterpriseId(userVO.getEnterpriseId());

        /**
         * 上级企业ID
         */
        paymentInvoice.setParentId(userVO.getParentId());

        /**
         * 单据编码
         */
        paymentInvoice.setCode(code);

        /**
         * 单据类型（2202）
         */
        paymentInvoice.setOrderType(OrderRule.PAYMENT_INVOICE.getType());

        /**
         * 开票日期
         */
        paymentInvoice.setBillDate(paymentInvoiceSaveVO.getBillDate());

        /**
         * 开票人员ID
         */
        paymentInvoice.setBillManId(paymentInvoiceSaveVO.getBillManId());

        /**
         * 开票人员编码
         */
        paymentInvoice.setBillManCode(billMan.getCode());

        /**
         * 开票人员名称
         */
        paymentInvoice.setBillManName(billMan.getName());

        /**
         * 科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
         */
        if(null != supplier){

            paymentInvoice.setFinanceAccountType(FinanceAccountType.SUPPLIER.getType());
            /**
             * 供货单位编码
             */
            paymentInvoice.setSupplierCode(supplier.getCode());
            /**
             * 供货单位名称
             */
            paymentInvoice.setSupplierName(supplier.getName());

        }else if(null != enterprise){

            paymentInvoice.setFinanceAccountType(FinanceAccountType.PARENT.getType());
            /**
             * 供货单位编码
             */
            paymentInvoice.setSupplierCode(enterprise.getCode());
            /**
             * 供货单位名称
             */
            paymentInvoice.setSupplierName(enterprise.getName());
        }

        /**
         * 供货单位ID
         */
        paymentInvoice.setSupplierId(paymentInvoiceSaveVO.getSupplierId());


        /**
         * 金额差额合计
         */
        paymentInvoice.setDiffAmountTotal(BigDecimal.ZERO);

        /**
         * 不含税金额差额合计
         */
        paymentInvoice.setDiffNotaxAmountTotal(BigDecimal.ZERO);

        /**
         * 税额差额合计
         */
        paymentInvoice.setDiffTaxAmountTotal(BigDecimal.ZERO);


        /**
         * 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
         */
        paymentInvoice.setStatus(FinancePaymentStatus.PRE_PAYMENT);

        /**
         * 备注
         */
        paymentInvoice.setRemark(paymentInvoiceSaveVO.getRemark());

        UserEnterpriseUtils.setUserCreateOrModify(paymentInvoice,userVO,isAdd);


        return paymentInvoice;
    }

    public static void afterSetPrepayInvoiceDiff(PaymentInvoice paymentInvoice, List<PaymentInvoiceDetail> paymentInvoiceDetails){

        BigDecimal diffAmountTotal = paymentInvoiceDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getDiffAmount()).map(PaymentInvoiceDetail::getDiffAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        /**
         * 金额差额
         */
        paymentInvoice.setDiffAmountTotal(diffAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));

        BigDecimal diffNotaxAmountTotal = paymentInvoiceDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getDiffNotaxAmount()).map(PaymentInvoiceDetail::getDiffNotaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        /**
         * 不含税金额差额
         */
        paymentInvoice.setDiffNotaxAmountTotal(diffNotaxAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));


        /**
         * 税额差额
         */
        BigDecimal diffTaxAmountTotal = paymentInvoiceDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getDiffTaxAmount()).map(PaymentInvoiceDetail::getDiffTaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        paymentInvoice.setDiffTaxAmountTotal(diffTaxAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
    }


    public static void afterSetPrepayInvoice(PaymentInvoice paymentInvoice, List<PaymentInvoiceDetail> paymentInvoiceDetails){

        BigDecimal quantityTotal = BigDecimal.ZERO;
        /**
         * 品种数量
         */
        Set<Long> varietiesQuantitySet = new HashSet<>();
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        for(PaymentInvoiceDetail paymentInvoiceDetail : paymentInvoiceDetails){
            if(null != paymentInvoiceDetail.getQuantity()){
                quantityTotal = quantityTotal.add(paymentInvoiceDetail.getQuantity()).setScale(2,BigDecimal.ROUND_HALF_UP);
            }

            varietiesQuantitySet.add(paymentInvoiceDetail.getGoodsId());

            if(null != paymentInvoiceDetail.getAmount()){
                amountTotal = amountTotal.add(paymentInvoiceDetail.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
            if(null != paymentInvoiceDetail.getNotaxAmount()){
                notaxAmountTotal = notaxAmountTotal.add(paymentInvoiceDetail.getNotaxAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            }

            if(null != paymentInvoiceDetail.getTaxAmount()){
                taxAmountTotal = taxAmountTotal.add(paymentInvoiceDetail.getTaxAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
            }

        }

        paymentInvoice.setQuantityTotal(quantityTotal);
        paymentInvoice.setVarietiesQuantity(varietiesQuantitySet.size());
        paymentInvoice.setAmountTotal(amountTotal);
        paymentInvoice.setNotaxAmountTotal(notaxAmountTotal);
        paymentInvoice.setTaxAmountTotal(taxAmountTotal);
        paymentInvoice.setClearAmountTotal(BigDecimal.ZERO);
        paymentInvoice.setUnclearAmountTotal(amountTotal);
        /**
         * 未清金额合计
         */
        paymentInvoice.setUnclearAmountTotal(paymentInvoice.getAmountTotal());

    }

    public static void setTotal(PrepayInvoiceResponseVO prepayInvoiceResponseVO){

        List<PrepayInvoiceDetailResponseVO> prepayInvoiceDetailResponseVOS = prepayInvoiceResponseVO.getPrepayInvoiceDetailSaveVOS();

        BigDecimal diffAmountTotal = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getDiffAmount()).map(PrepayInvoiceDetailResponseVO::getDiffAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        /**
         * 金额差额
         */
        prepayInvoiceResponseVO.setDiffAmountTotal(diffAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));

        BigDecimal diffNotaxAmountTotal = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getDiffNotaxAmount()).map(PrepayInvoiceDetailResponseVO::getDiffNotaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        /**
         * 不含税金额差额
         */
        prepayInvoiceResponseVO.setDiffNotaxAmountTotal(diffNotaxAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));


        /**
         * 税额差额
         */
        BigDecimal diffTaxAmountTotal = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getDiffTaxAmount()).map(PrepayInvoiceDetailResponseVO::getDiffTaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceResponseVO.setDiffTaxAmountTotal(diffTaxAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));

        /**
         * 已清数量
         */
        BigDecimal clearQuantityTotal = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getClearQuantity()).map(PrepayInvoiceDetailResponseVO::getClearQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceResponseVO.setClearQuantityTotal(clearQuantityTotal.setScale(2,BigDecimal.ROUND_HALF_UP));

        /**
         * 未清数量
         */
        BigDecimal unclearQuantityTotal = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getUnclearQuantity()).map(PrepayInvoiceDetailResponseVO::getUnclearQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceResponseVO.setUnclearQuantityTotal(unclearQuantityTotal.setScale(2,BigDecimal.ROUND_HALF_UP));

        /**
         * 本次对账数量合计
         */
        BigDecimal timeCheckAccountQtyTotal = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getTimeCheckAccountQty()).map(PrepayInvoiceDetailResponseVO::getTimeCheckAccountQty).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceResponseVO.setTimeCheckAccountQtyTotal(timeCheckAccountQtyTotal.setScale(2,BigDecimal.ROUND_HALF_UP));

        BigDecimal timeCheckAccountAmountTotal = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getTimeCheckAccountAmount()).map(PrepayInvoiceDetailResponseVO::getTimeCheckAccountAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceResponseVO.setTimeCheckAccountAmountTotal(timeCheckAccountAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));


        BigDecimal timeCheckAccountNotaxAmount = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getTimeCheckAccountNotaxAmount()).map(PrepayInvoiceDetailResponseVO::getTimeCheckAccountNotaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceResponseVO.setTimeCheckAccountNotaxAmountTotal(timeCheckAccountNotaxAmount.setScale(2,BigDecimal.ROUND_HALF_UP));


        BigDecimal timeCheckAccountTaxAmount = prepayInvoiceDetailResponseVOS.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getTimeCheckAccountTaxAmount()).map(PrepayInvoiceDetailResponseVO::getTimeCheckAccountTaxAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        prepayInvoiceResponseVO.setTimeCheckAccountNotaxAmountTotal(timeCheckAccountTaxAmount.setScale(2,BigDecimal.ROUND_HALF_UP));


    }

    /**
     * saas_payment_invoice
     */
    private static final long serialVersionUID = 1L;

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
     * 单据类型（2202）
     * @return order_type 单据类型（2202）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（2202）
     * @param orderType 单据类型（2202）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 开票日期
     * @return bill_date 开票日期
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * 开票日期
     * @param billDate 开票日期
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * 开票人员ID
     * @return bill_man_id 开票人员ID
     */
    public Long getBillManId() {
        return billManId;
    }

    /**
     * 开票人员ID
     * @param billManId 开票人员ID
     */
    public void setBillManId(Long billManId) {
        this.billManId = billManId;
    }

    /**
     * 开票人员编码
     * @return bill_man_code 开票人员编码
     */
    public String getBillManCode() {
        return billManCode;
    }

    /**
     * 开票人员编码
     * @param billManCode 开票人员编码
     */
    public void setBillManCode(String billManCode) {
        this.billManCode = billManCode == null ? null : billManCode.trim();
    }

    /**
     * 开票人员名称
     * @return bill_man_name 开票人员名称
     */
    public String getBillManName() {
        return billManName;
    }

    /**
     * 开票人员名称
     * @param billManName 开票人员名称
     */
    public void setBillManName(String billManName) {
        this.billManName = billManName == null ? null : billManName.trim();
    }

    /**
     * 科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     * @return finance_account_type 科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     */
    public Integer getFinanceAccountType() {
        return financeAccountType;
    }

    /**
     * 科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     * @param financeAccountType 科目类型（0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税）
     */
    public void setFinanceAccountType(Integer financeAccountType) {
        this.financeAccountType = financeAccountType;
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

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

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

    /**
     * 金额差额合计
     * @return diff_amount_total 金额差额合计
     */
    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    /**
     * 金额差额合计
     * @param diffAmountTotal 金额差额合计
     */
    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    /**
     * 不含税金额差额合计
     * @return diff_notax_amount_total 不含税金额差额合计
     */
    public BigDecimal getDiffNotaxAmountTotal() {
        return diffNotaxAmountTotal;
    }

    /**
     * 不含税金额差额合计
     * @param diffNotaxAmountTotal 不含税金额差额合计
     */
    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
    }

    /**
     * 税额差额合计
     * @return diff_tax_amount_total 税额差额合计
     */
    public BigDecimal getDiffTaxAmountTotal() {
        return diffTaxAmountTotal;
    }

    /**
     * 税额差额合计
     * @param diffTaxAmountTotal 税额差额合计
     */
    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
        this.diffTaxAmountTotal = diffTaxAmountTotal;
    }

    /**
     * 已清金额合计
     * @return clear_amount_total 已清金额合计
     */
    public BigDecimal getClearAmountTotal() {
        return clearAmountTotal;
    }

    /**
     * 已清金额合计
     * @param clearAmountTotal 已清金额合计
     */
    public void setClearAmountTotal(BigDecimal clearAmountTotal) {
        this.clearAmountTotal = clearAmountTotal;
    }

    /**
     * 未清金额合计
     * @return unclear_amount_total 未清金额合计
     */
    public BigDecimal getUnclearAmountTotal() {
        return unclearAmountTotal;
    }

    /**
     * 未清金额合计
     * @param unclearAmountTotal 未清金额合计
     */
    public void setUnclearAmountTotal(BigDecimal unclearAmountTotal) {
        this.unclearAmountTotal = unclearAmountTotal;
    }

    /**
     * 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
     * @return status 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
     * @param status 状态（0-待付款；1-部分付款；2-已付款；3-已冲销）
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
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", code=").append(code);
        sb.append(", orderType=").append(orderType);
        sb.append(", billDate=").append(billDate);
        sb.append(", billManId=").append(billManId);
        sb.append(", billManCode=").append(billManCode);
        sb.append(", billManName=").append(billManName);
        sb.append(", financeAccountType=").append(financeAccountType);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", notaxAmountTotal=").append(notaxAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", diffAmountTotal=").append(diffAmountTotal);
        sb.append(", diffNotaxAmountTotal=").append(diffNotaxAmountTotal);
        sb.append(", diffTaxAmountTotal=").append(diffTaxAmountTotal);
        sb.append(", clearAmountTotal=").append(clearAmountTotal);
        sb.append(", unclearAmountTotal=").append(unclearAmountTotal);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}