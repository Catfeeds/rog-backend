package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class PaymentInvoiceGoodsVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long shelfDtlId;

    /**
     * 入库品种明细ID
     */
    @ApiModelProperty(value = "入库品种明细ID")
    private Long dtlId;

    /**
     * 入库单ID
     */
    @ApiModelProperty(value = "入库单ID")
    private Long inId;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "日期")
    private Date inDate;
    /**
     * 配进入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String code;

    @ApiModelProperty(required = true, value = "商品id")
    private Long goodsId;
    @ApiModelProperty(required = true, value = "企业id")
    private Long enterpriseId;
    /**
     * 商品编码
     */
    @ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(required = true, value = "条形码")
    private String barcode;

    /**
     * 通用名称
     */
    @ApiModelProperty(required = true, value = "通用名称")
    private String goodsGenericName;


    /**
     * 商品名称
     */
    @ApiModelProperty(required = true, value = "商品名称")
    private String goodsName;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 剂型ID
     */
    @ApiModelProperty(required = true, value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(required = true, value = "剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(required = true, value = "规格")
    private String goodsSpecification;

    /**
     * 库存计量单位ID
     */
    @ApiModelProperty(required = true, value = "单位ID")
    private Long unitId;
    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(required = true, value = "单位名称")
    private String unitName;
    /**
     * 生产厂商ID
     */
    @ApiModelProperty(required = true, value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = true, value = "生产厂商")
    private String manufacturer;
    /**
     * 产地
     */
    @ApiModelProperty(required = true, value = "产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(required = true, value = "批准文号")
    private String approvalNumber;

    @ApiModelProperty(required = true, value = "批号")
    private String lotNumber;

    /**
     * 有效期至
     */
    @ApiModelProperty(required = true, value = "有效期至")
    private Date validUntil;

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
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;


    @ApiModelProperty(value = "对账数量")

    private BigDecimal accountQuantity = BigDecimal.ONE;

    /**
     * 单价
     */
    @ApiModelProperty(required = true, value = "单价")
    private BigDecimal unitPrice = BigDecimal.ZERO;


    @ApiModelProperty(value = "金额")
    private BigDecimal amount = BigDecimal.ZERO;

    @ApiModelProperty(value = "原有金额")
    private BigDecimal oldAmount = BigDecimal.ZERO;
    /**
     * 实际金额
     */
    @ApiModelProperty(value = "总额")
    private BigDecimal realAmount = BigDecimal.ZERO;

    @ApiModelProperty(value = "原有对账数量")
    private BigDecimal oldAccountQuantity;
    @ApiModelProperty(value = "原有单价")
    private BigDecimal oldUnitPrice;
    @ApiModelProperty(value = "原有税率")
    private BigDecimal oldTaxRate;


    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;


    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单折扣金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
    @ApiModelProperty(value = "整单优惠分摊")
    private BigDecimal lineDiscountAmount;


    @ApiModelProperty(required = true, value = "税率ID")
    private Long taxRateId = 0L;

    @ApiModelProperty(required = true, value = "税率")
    private BigDecimal taxRate = BigDecimal.ZERO;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice = BigDecimal.ZERO;

    @ApiModelProperty(value = "业务单据不含税实际金额")
    private BigDecimal oldNotaxRealAmount = BigDecimal.ZERO;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount = BigDecimal.ZERO;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @ApiModelProperty(value = "业务单据税额")
    private BigDecimal oldTaxAmount = BigDecimal.ZERO;

    /**
     * 金额差额
     */
    @ApiModelProperty(value = "金额差额")
    private BigDecimal diffAmount = BigDecimal.ZERO;

    /**
     * 不含税金额差额
     */
    @ApiModelProperty(value = "不含税金额差额")
    private BigDecimal diffNotaxAmount = BigDecimal.ZERO;

    /**
     * 税额差额
     */
    @ApiModelProperty(value = "税额差额")
    private BigDecimal diffTaxAmount = BigDecimal.ZERO;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量")
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量")
    private BigDecimal clearQuantity;


    /**
     * 经营方式（0-购销；1-实销实结）
     */
    @ApiModelProperty(value = "经营方式（0-购销；1-实销实结）")
    private Integer managementMode;


    /**
     *已核销数量
     */
    @ApiModelProperty(value = "已核销数量")
    private BigDecimal verificationQuantity;

    /**
     *未核销数量
     */
    @ApiModelProperty(value = "未核销数量")
    private BigDecimal unverificationQuantity;

    @ApiModelProperty(value = "最大开票数量")
    private BigDecimal maxQuantity;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public static List<Long> getGoodsIds(List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS){

        List<Long> collect = paymentInvoiceGoodsVOS.stream().map(PaymentInvoiceGoodsVO::getGoodsId).collect(Collectors.toList());

        return collect;

    }

    public static void afterCopySetBean4DistrIn(DistrInShelf distrInShelf, List<DistrIn> distrIns ,
                                                PaymentInvoiceGoodsVO paymentInvoiceGoodsVO, List<DistrInDetail> distrInDetails){


        /**
         * 有效期至
         */
        paymentInvoiceGoodsVO.setValidUntil(distrInShelf.getValidDate());
        paymentInvoiceGoodsVO.setShelfDtlId(distrInShelf.getId());
        paymentInvoiceGoodsVO.setAccountQuantity(distrInShelf.getUnclearQuantity());

        paymentInvoiceGoodsVO.setOldAmount(paymentInvoiceGoodsVO.getAmount());

        paymentInvoiceGoodsVO.setUnitPrice(distrInShelf.getRealPrice());

        paymentInvoiceGoodsVO.setOldNotaxRealAmount(paymentInvoiceGoodsVO.getNotaxRealAmount());
        paymentInvoiceGoodsVO.setOldTaxAmount(paymentInvoiceGoodsVO.getTaxAmount());


        /**
         * 金额
         */
        BigDecimal amount = paymentInvoiceGoodsVO.getAccountQuantity().multiply(paymentInvoiceGoodsVO.getUnitPrice());
        paymentInvoiceGoodsVO.setAmount(amount);


        /**
         * 不含税实际金额
         */
        BigDecimal notaxRealAmount= paymentInvoiceGoodsVO.getAccountQuantity().multiply(paymentInvoiceGoodsVO.getNotaxRealPrice()).setScale(2,BigDecimal.ROUND_HALF_UP);
        paymentInvoiceGoodsVO.setNotaxRealAmount(notaxRealAmount);

        /**
         *
         * 税额 根据实际金额和不含税金额获取税额：金额-不含税金额
         */
        BigDecimal taxAmount= CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(amount, notaxRealAmount);
        paymentInvoiceGoodsVO.setTaxAmount(taxAmount);



        paymentInvoiceGoodsVO.setOldAccountQuantity(paymentInvoiceGoodsVO.getAccountQuantity());

        paymentInvoiceGoodsVO.setOldUnitPrice(paymentInvoiceGoodsVO.getUnitPrice());
        paymentInvoiceGoodsVO.setOldTaxRate(paymentInvoiceGoodsVO.getTaxRate());

        for(DistrIn distrIn : distrIns){

            if(distrIn.getId().equals(paymentInvoiceGoodsVO.getInId())){
                /**
                 * 入库日期
                 */
                paymentInvoiceGoodsVO.setInDate(distrIn.getInDate());

                /**
                 * 配进入库单号
                 */
                paymentInvoiceGoodsVO.setCode(distrIn.getCode());

                /**
                 * 整单折扣（%）
                 */
                paymentInvoiceGoodsVO.setWholeDiscount(distrIn.getWholeDiscount());


                /**
                 * 整单优惠金额
                 */
                paymentInvoiceGoodsVO.setWholeDiscountAmount(distrIn.getWholeDiscountAmount());
            }

        }

        for(DistrInDetail pid : distrInDetails){

            if(paymentInvoiceGoodsVO.getDtlId().equals(pid.getId())){

                /**
                 * 条形码
                 */
                paymentInvoiceGoodsVO.setBarcode(pid.getBarcode());

                /**
                 * 通用名称
                 */
                paymentInvoiceGoodsVO.setGoodsGenericName(pid.getGoodsGenericName());

                /**
                 * 剂型ID
                 */
                paymentInvoiceGoodsVO.setDosageId(pid.getDosageId());

                /**
                 * 剂型名称
                 */
                paymentInvoiceGoodsVO.setDosageName(pid.getDosageName());
                /**
                 * 规格
                 */
                paymentInvoiceGoodsVO.setGoodsSpecification(pid.getGoodsSpecification());

                /**
                 * 库存计量单位ID
                 */
                paymentInvoiceGoodsVO.setUnitId(pid.getUnitId());
                /**
                 * 库存计量单位名称
                 */
                paymentInvoiceGoodsVO.setUnitName(pid.getUnitName());

                /**
                 * 生产厂商ID
                 */
                paymentInvoiceGoodsVO.setManufacturerId(pid.getManufacturerId());

                /**
                 * 生产厂商
                 */
                paymentInvoiceGoodsVO.setManufacturer(pid.getManufacturer());
                /**
                 * 产地
                 */
                paymentInvoiceGoodsVO.setGoodsPlace(pid.getGoodsPlace());

                /**
                 * 批准文号
                 */
                paymentInvoiceGoodsVO.setApprovalNumber(pid.getApprovalNumber());


            }

        }

    }


    public static void afterCopySetBean4PurchaseInStorage(PurchaseInStorageShelf purchaseInStorageShelf, List<PurchaseInStorage> purchaseInStorages , PaymentInvoiceGoodsVO paymentInvoiceGoodsVO, List<PurchaseInStorageDetail> purchaseInStorageDetails){

        /**
         * 入库品种明细ID
         */
        paymentInvoiceGoodsVO.setDtlId(purchaseInStorageShelf.getInStorageDtlId());

        paymentInvoiceGoodsVO.setUnitPrice(purchaseInStorageShelf.getRealPrice());
        /**
         * 入库单ID
         */
        paymentInvoiceGoodsVO.setInId(purchaseInStorageShelf.getInStorageId());
        paymentInvoiceGoodsVO.setShelfDtlId(purchaseInStorageShelf.getId());



        paymentInvoiceGoodsVO.setAccountQuantity(purchaseInStorageShelf.getUnclearQuantity());
        paymentInvoiceGoodsVO.setOldAccountQuantity(paymentInvoiceGoodsVO.getAccountQuantity());

        paymentInvoiceGoodsVO.setOldUnitPrice(paymentInvoiceGoodsVO.getUnitPrice());
        paymentInvoiceGoodsVO.setOldTaxRate(paymentInvoiceGoodsVO.getTaxRate());

        paymentInvoiceGoodsVO.setOldAmount(paymentInvoiceGoodsVO.getAmount());

        paymentInvoiceGoodsVO.setOldNotaxRealAmount(paymentInvoiceGoodsVO.getNotaxRealAmount());
        paymentInvoiceGoodsVO.setOldTaxAmount(paymentInvoiceGoodsVO.getTaxAmount());

        /**
         * 金额
         */
        BigDecimal amount = paymentInvoiceGoodsVO.getAccountQuantity().multiply(paymentInvoiceGoodsVO.getUnitPrice());
        paymentInvoiceGoodsVO.setAmount(amount);


        /**
         * 不含税实际金额
         */
        BigDecimal notaxRealAmount= paymentInvoiceGoodsVO.getAccountQuantity().multiply(paymentInvoiceGoodsVO.getNotaxRealPrice()).setScale(2,BigDecimal.ROUND_HALF_UP);
        paymentInvoiceGoodsVO.setNotaxRealAmount(notaxRealAmount);

        /**
         *
         * 税额 根据实际金额和不含税金额获取税额：金额-不含税金额
         */
        BigDecimal taxAmount= CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(amount, notaxRealAmount);
        paymentInvoiceGoodsVO.setTaxAmount(taxAmount);



        /**
         * 有效期至
         */
        paymentInvoiceGoodsVO.setValidUntil(purchaseInStorageShelf.getValidDate());

        for(PurchaseInStorage purchaseInStorage : purchaseInStorages){

            if(purchaseInStorage.getId().equals(paymentInvoiceGoodsVO.getInId())){
                /**
                 * 入库日期
                 */
                paymentInvoiceGoodsVO.setInDate(purchaseInStorage.getInStorageDate());

                /**
                 * 配进入库单号
                 */
                paymentInvoiceGoodsVO.setCode(purchaseInStorage.getCode());

                /**
                 * 整单折扣（%）
                 */
                paymentInvoiceGoodsVO.setWholeDiscount(purchaseInStorage.getWholeDiscount());


                /**
                 * 整单优惠金额
                 */
                paymentInvoiceGoodsVO.setWholeDiscountAmount(purchaseInStorage.getWholeDiscountAmount());
            }

        }

        for(PurchaseInStorageDetail psd : purchaseInStorageDetails){

            if(paymentInvoiceGoodsVO.getDtlId().equals(psd.getId())){

                /**
                 * 条形码
                 */
                paymentInvoiceGoodsVO.setBarcode(psd.getBarcode());

                /**
                 * 通用名称
                 */
                paymentInvoiceGoodsVO.setGoodsGenericName(psd.getGoodsGenericName());

                /**
                 * 剂型ID
                 */
                paymentInvoiceGoodsVO.setDosageId(psd.getDosageId());

                /**
                 * 剂型名称
                 */
                paymentInvoiceGoodsVO.setDosageName(psd.getDosageName());
                /**
                 * 规格
                 */
                paymentInvoiceGoodsVO.setGoodsSpecification(psd.getGoodsSpecification());

                /**
                 * 库存计量单位ID
                 */
                paymentInvoiceGoodsVO.setUnitId(psd.getUnitId());
                /**
                 * 库存计量单位名称
                 */
                paymentInvoiceGoodsVO.setUnitName(psd.getUnitName());

                /**
                 * 生产厂商ID
                 */
                paymentInvoiceGoodsVO.setManufacturerId(psd.getManufacturerId());

                /**
                 * 生产厂商
                 */
                paymentInvoiceGoodsVO.setManufacturer(psd.getManufacturer());
                /**
                 * 产地
                 */
                paymentInvoiceGoodsVO.setGoodsPlace(psd.getGoodsPlace());

                /**
                 * 批准文号
                 */
                paymentInvoiceGoodsVO.setApprovalNumber(psd.getApprovalNumber());

            }

        }

    }

    public Long getShelfDtlId() {
        return shelfDtlId;
    }

    public void setShelfDtlId(Long shelfDtlId) {
        this.shelfDtlId = shelfDtlId;
    }

    public Long getDtlId() {
        return dtlId;
    }

    public void setDtlId(Long dtlId) {
        this.dtlId = dtlId;
    }

    public Long getInId() {
        return inId;
    }

    public void setInId(Long inId) {
        this.inId = inId;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
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

    public BigDecimal getAccountQuantity() {
        return accountQuantity;
    }

    public void setAccountQuantity(BigDecimal accountQuantity) {
        this.accountQuantity = accountQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    public BigDecimal getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
        this.lineDiscountAmount = lineDiscountAmount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    public BigDecimal getDiffNotaxAmount() {
        return diffNotaxAmount;
    }

    public void setDiffNotaxAmount(BigDecimal diffNotaxAmount) {
        this.diffNotaxAmount = diffNotaxAmount;
    }

    public BigDecimal getDiffTaxAmount() {
        return diffTaxAmount;
    }

    public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
        this.diffTaxAmount = diffTaxAmount;
    }

    public BigDecimal getOldAccountQuantity() {
        return oldAccountQuantity;
    }

    public void setOldAccountQuantity(BigDecimal oldAccountQuantity) {
        this.oldAccountQuantity = oldAccountQuantity;
    }

    public BigDecimal getOldUnitPrice() {
        return oldUnitPrice;
    }

    public void setOldUnitPrice(BigDecimal oldUnitPrice) {
        this.oldUnitPrice = oldUnitPrice;
    }

    public BigDecimal getOldTaxRate() {
        return oldTaxRate;
    }

    public void setOldTaxRate(BigDecimal oldTaxRate) {
        this.oldTaxRate = oldTaxRate;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    public BigDecimal getOldNotaxRealAmount() {
        return oldNotaxRealAmount;
    }

    public void setOldNotaxRealAmount(BigDecimal oldNotaxRealAmount) {
        this.oldNotaxRealAmount = oldNotaxRealAmount;
    }

    public BigDecimal getOldTaxAmount() {
        return oldTaxAmount;
    }

    public void setOldTaxAmount(BigDecimal oldTaxAmount) {
        this.oldTaxAmount = oldTaxAmount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public Integer getManagementMode() {
        return managementMode;
    }

    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    public BigDecimal getVerificationQuantity() {
        return verificationQuantity;
    }

    public void setVerificationQuantity(BigDecimal verificationQuantity) {
        this.verificationQuantity = verificationQuantity;
    }

    public BigDecimal getUnverificationQuantity() {
        return unverificationQuantity;
    }

    public void setUnverificationQuantity(BigDecimal unverificationQuantity) {
        this.unverificationQuantity = unverificationQuantity;
    }

    public BigDecimal getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
