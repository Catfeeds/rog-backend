package com.rograndec.feijiayun.chain.business.purchase.ret.entity;

import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnDetailSaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnShowVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnTotalShowVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.SaveOrUpdateBzVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * saas_purchase_return
 * 
 * 
 * @author ST
 * 
 * 2017-09-15
 */
public class PurchaseReturn implements Serializable {
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
     * 单据类型（5214）
     */
    @ApiModelProperty(value = "单据类型（5214）")
    private Integer orderType;

    /**
     * 购进退出单号
     */
    @ApiModelProperty(value = "购进退出单号")
    private String code;

    /**
     * 购进退出日期
     */
    @ApiModelProperty(value = "购进退出日期")
    private Date returnDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID" )
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 退货类型（0-质量问题退货；1-非质量问题退货）
     */
    @ApiModelProperty(value = "退货类型（0-质量问题退货；1-非质量问题退货）")
    private Integer returnType;

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
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String telPhone;

    /**
     * 退货人员ID
     */
    @ApiModelProperty(value = "退货人员ID")
    private Long returnManId;

    /**
     * 退货人员编码
     */
    @ApiModelProperty(value = "退货人员编码")
    private String returnManCode;

    /**
     * 退货人员名称
     */
    @ApiModelProperty(value = "退货人员名称")
    private String returnManName;

    @ApiModelProperty(value = "供货单位人员")
    private Long supplierSalerId;
    @ApiModelProperty(value = "供货单位编码")
    private String supplierSalerCode;
    @ApiModelProperty(value = "供货单位姓名")
    private String supplierSalerName;
    @ApiModelProperty(value = "供货单位电话")
    private String supplierSalerPhone;
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
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
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
     * 配货类型   0-总部配送；1-委托运输；2-自提  ；4-直调配送
     */
    @ApiModelProperty(value = " 配货类型  （ 0-总部配送；1-委托运输；2-自提  ；4-直调配送）" )
    private Integer carriageMode;
    
    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
	@ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType; 
	
	  /**
     * 结算单位ID,若该值与当前登录人企业id不同则特殊显示
     */
	@ApiModelProperty(value = "结算单位ID,若该值与当前登录人企业id不同则特殊显示")
    private Long settlementUnitId; 

    /**
     * saas_purchase_return
     */
    private static final long serialVersionUID = 1L;


//    public sta

    public static PurchaseReturnTotalShowVO getPurchaseReturnsNotDetails(
                List<PurchaseReturn> purchaseReturns
                ,BigDecimal amountTotal
                ,BigDecimal notaxRealAmountTotal
                ,BigDecimal taxAmountTotal){

        PurchaseReturnTotalShowVO pvo = new PurchaseReturnTotalShowVO();
        List<PurchaseReturnShowVO> purchaseReturnShowVOList = new ArrayList<>();
        for(PurchaseReturn pr : purchaseReturns){
            PurchaseReturnShowVO purchaseReturnShowVO = new PurchaseReturnShowVO();
            PurchaseReturnShowVO po = purchaseReturnShowVO.getPurchaseReturnShowVO(pr);
            purchaseReturnShowVOList.add(po);
        }
        pvo.setPurchaseReturnShowVOList(purchaseReturnShowVOList);
        pvo.setAmountTotal(amountTotal);
        pvo.setNotaxRealAmountTotal(notaxRealAmountTotal);
        pvo.setTaxAmountTotal(taxAmountTotal);
        return pvo;
    }


    public static PurchaseReturn setPurchaseReturn4Detail(PurchaseReturn purchaseReturn,List<PurchaseReturnDetail> purchaseReturnDetails){


//        /**
//         * 整单优惠前金额合计
//         */
//        BigDecimal amountTotal = calcTotalAmount(purchaseReturnDetails);
//        purchaseReturn.setAmountTotal(amountTotal);

        /**
         * 整单实际金额合计
         */
        BigDecimal realAmountTotal = calcTotalRealAmount(
                purchaseReturn.getAmountTotal()
                ,purchaseReturn.getWholeDiscount()
                ,purchaseReturn.getWholeDiscountAmount()
        );
        purchaseReturn.setRealAmountTotal(realAmountTotal);

        /**
         * 不含税金额合计
         * @param returnDetails
         * @return
         */
        BigDecimal notaxRealAmountTotal = calcNotaxRealAmountTotal(purchaseReturnDetails);
        purchaseReturn.setNotaxRealAmountTotal(notaxRealAmountTotal);

        /**
         * 税额合计
         */
        BigDecimal taxAmountTotal = calcTaxAmountTotal(purchaseReturnDetails);
        purchaseReturn.setTaxAmountTotal(taxAmountTotal);


        return purchaseReturn;

    }

    public static PurchaseReturn getPurchaseReturn4VO(SaveOrUpdateBzVO saveOrUpdateBzVO,boolean isAdd) throws Exception {

        PurchaseReturn purchaseReturn = new PurchaseReturn();

        purchaseReturn.setId(saveOrUpdateBzVO.getPurchaseReturnVO().getId());
        purchaseReturn.setEnterpriseId(saveOrUpdateBzVO.getUserVO().getEnterpriseId());
        if(saveOrUpdateBzVO.getUserVO().getChainType().equals(ChainType.Headquarters.getCode())){
            purchaseReturn.setParentId(0L);
        }else {
            purchaseReturn.setParentId(saveOrUpdateBzVO.getUserVO().getParentId());
        }
        purchaseReturn.setOrderType(OrderRule.PURCHASE_RETURN.getType());
        purchaseReturn.setCode(saveOrUpdateBzVO.getCode());
        purchaseReturn.setReturnDate(saveOrUpdateBzVO.getPurchaseReturnVO().getReturnDate());

        if(null != saveOrUpdateBzVO.getPurchaseInStorage()){
            purchaseReturn.setBaseOrderId(saveOrUpdateBzVO.getPurchaseInStorage().getId());
            purchaseReturn.setBaseOrderType(saveOrUpdateBzVO.getPurchaseInStorage().getOrderType());
            purchaseReturn.setBaseOrderCode(saveOrUpdateBzVO.getPurchaseInStorage().getCode());
            purchaseReturn.setBaseOrderDate(saveOrUpdateBzVO.getPurchaseInStorage().getInStorageDate());
        }

        purchaseReturn.setReturnType(saveOrUpdateBzVO.getPurchaseReturnVO().getReturnType());
        if(null != saveOrUpdateBzVO.getSupplier()){

            purchaseReturn.setSupplierCode(saveOrUpdateBzVO.getSupplier().getCode());

            purchaseReturn.setSupplierName(saveOrUpdateBzVO.getSupplier().getName());

        }
        purchaseReturn.setSupplierId(saveOrUpdateBzVO.getPurchaseReturnVO().getSupplierId());


        if(null != saveOrUpdateBzVO.getUser()){
            purchaseReturn.setReturnManId(saveOrUpdateBzVO.getPurchaseReturnVO().getReturnManId());
            purchaseReturn.setReturnManCode(saveOrUpdateBzVO.getUser().getCode());
            purchaseReturn.setReturnManName(saveOrUpdateBzVO.getUser().getName());
        }

        if(null != saveOrUpdateBzVO.getSupplierUser()){
            purchaseReturn.setSupplierSalerId(saveOrUpdateBzVO.getPurchaseReturnVO().getSupplierSalerId());
            purchaseReturn.setSupplierSalerCode(saveOrUpdateBzVO.getSupplierUser().getCode());
            purchaseReturn.setSupplierSalerName(saveOrUpdateBzVO.getSupplierUser().getName());
            purchaseReturn.setSupplierSalerPhone(saveOrUpdateBzVO.getSupplierUser().getMobilePhone());
        }

        List<PurchaseReturnDetailSaveOrUpdateVO> purchaseReturnDetailSaveOrUpdateVOS = saveOrUpdateBzVO.getPurchaseReturnVO().getPurchaseReturnDetailSaveOrUpdateVOS();
        BigDecimal quantityTotal = calcPurchaseReturnGoodsQuantity(purchaseReturnDetailSaveOrUpdateVOS);
        purchaseReturn.setQuantityTotal(quantityTotal);

        Integer varietiesQuantity = calcTotalVarietiesQuantity(purchaseReturnDetailSaveOrUpdateVOS);

        purchaseReturn.setVarietiesQuantity(varietiesQuantity);


        purchaseReturn.setWholeDiscount(saveOrUpdateBzVO.getPurchaseReturnVO().getWholeDiscount());

        purchaseReturn.setWholeDiscountAmount(saveOrUpdateBzVO.getPurchaseReturnVO().getWholeDiscountAmount());

        /**
         * 新增和修改时 都是待审核,单据只有还未被引用或者是被驳回的时候才可以修改
         * ,修改会重新提交审批流程,所以新增和修改状态都是待审核
         */
        purchaseReturn.setStatus(PurchaseStatus.PENDING_AUDIT.getStatus());
        purchaseReturn.setRemark(saveOrUpdateBzVO.getPurchaseReturnVO().getRemark());


        UserEnterpriseUtils.setUserCreateOrModify(purchaseReturn,saveOrUpdateBzVO.getUserVO(),isAdd);

        return purchaseReturn;
    }

//    /**
//     * 不含税利润合计
//     * @param returnDetails
//     * @return
//     */
//    public static BigDecimal calcProfitRate(List<PurchaseReturnDetail> returnDetails){
//        BigDecimal notaxProfitTotal = new BigDecimal(0);
//
//        for(PurchaseReturnDetail pd : returnDetails){
//            notaxProfitTotal = notaxProfitTotal.add(pd.getNotaxProfit()).setScale(2,BigDecimal.ROUND_HALF_UP);
//        }
//
//        return notaxProfitTotal;
//    }
//
//
//    /**
//     * 不含税利润合计
//     * @param returnDetails
//     * @return
//     */
//    public static BigDecimal calcNotaxProfitTotal(List<PurchaseReturnDetail> returnDetails){
//        BigDecimal notaxProfitTotal = new BigDecimal(0);
//
//        for(PurchaseReturnDetail pd : returnDetails){
//            notaxProfitTotal = notaxProfitTotal.add(pd.getNotaxProfit()).setScale(2,BigDecimal.ROUND_HALF_UP);
//        }
//
//        return notaxProfitTotal;
//    }
//
//    /**
//     * 利润合计
//     * @param returnDetails
//     * @return
//     */
//    public static BigDecimal calcProfitTotal(List<PurchaseReturnDetail> returnDetails){
//        BigDecimal notaxProfitTotal = new BigDecimal(0);
//
//        for(PurchaseReturnDetail pd : returnDetails){
//            notaxProfitTotal = notaxProfitTotal.add(pd.getProfit()).setScale(2,BigDecimal.ROUND_HALF_UP);
//        }
//
//        return notaxProfitTotal;
//    }

    /**
     * 税额合计
     * @param returnDetails
     * @return
     */
    public static BigDecimal calcTaxAmountTotal(List<PurchaseReturnDetail> returnDetails){

        BigDecimal taxAmountTotal = new BigDecimal(0);

        for(PurchaseReturnDetail pd : returnDetails){
            taxAmountTotal = taxAmountTotal.add(pd.getTaxAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        return taxAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param returnDetails
     * @return
     */
    public static BigDecimal calcNotaxRealAmountTotal(List<PurchaseReturnDetail> returnDetails){

        BigDecimal notaxRealAmountTotal = new BigDecimal(0);

        for(PurchaseReturnDetail vo : returnDetails){
//            BigDecimal notaxAmountByRealAmountAndTaxRate = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(vo.getRealAmount(), vo.getTaxRate());
            notaxRealAmountTotal = notaxRealAmountTotal.add(vo.getNotaxRealAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        return notaxRealAmountTotal;
    }

    /**
     * 实际金额合计
     * @return
     */
    public static BigDecimal calcTotalRealAmount(BigDecimal amountTotal ,BigDecimal wholeDiscount,BigDecimal wholeDiscountAmount){
        //整单折扣金额
        BigDecimal divide = wholeDiscount.divide(new BigDecimal(100));
        BigDecimal wholeDiscountTotal = amountTotal.multiply(divide);
        BigDecimal realAmountTotal = wholeDiscountTotal
                .subtract(wholeDiscountAmount)
                .setScale(2,BigDecimal.ROUND_HALF_UP);
        return realAmountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param returnDetails
     * @return
     */
    public static BigDecimal calcTotalAmount(List<PurchaseReturnDetail> returnDetails){

        BigDecimal amountTotal = new BigDecimal(0);

        for(PurchaseReturnDetail vo : returnDetails){
            amountTotal = amountTotal.add(
                    vo.getAmount()
            );
        }

//        for(PurchaseReturnDetail vo : returnDetails){
//            amountTotal = amountTotal.add(
//                    CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
//                            vo.getQuantity(),vo.getUnitPrice(),vo.getLineDiscount()
//                    )
//            );
//        }
        amountTotal = amountTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return amountTotal;

    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amounts
     * @return
     */
    public static BigDecimal calcTotalAmount4List(List<BigDecimal> amounts){

        BigDecimal amountTotal = new BigDecimal(0);

        for(BigDecimal amount : amounts){
            amountTotal = amountTotal.add(
                    amount
            );
        }

//        for(PurchaseReturnDetail vo : returnDetails){
//            amountTotal = amountTotal.add(
//                    CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
//                            vo.getQuantity(),vo.getUnitPrice(),vo.getLineDiscount()
//                    )
//            );
//        }
        amountTotal = amountTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return amountTotal;

    }

    /**
     *  计算购进退出头信息中的品种数量
     * @param returnDetails
     * @return
     */
    public static Integer calcTotalVarietiesQuantity(List<PurchaseReturnDetailSaveOrUpdateVO> returnDetails){

        Set<Long> goodsSet = new HashSet<>();

        for(PurchaseReturnDetailSaveOrUpdateVO pd : returnDetails){
            goodsSet.add(pd.getGoodsId());
        }

        return goodsSet.size();

    }
    /**
     * 计算购进退出头商品数量合计
     * @param detailVOs
     * @return
     */
    public static BigDecimal calcPurchaseReturnGoodsQuantity(List<PurchaseReturnDetailSaveOrUpdateVO> detailVOs){
        BigDecimal totalQuantity = new BigDecimal(0);
        for(PurchaseReturnDetailSaveOrUpdateVO vo : detailVOs){
            totalQuantity = totalQuantity.add(vo.getQuantity());
        }

        return totalQuantity;
    }
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
     * 单据类型（5214）
     * @return order_type 单据类型（5214）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5214）
     * @param orderType 单据类型（5214）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 购进退出单号
     * @return code 购进退出单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 购进退出单号
     * @param code 购进退出单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 购进退出日期
     * @return return_date 购进退出日期
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * 购进退出日期
     * @param returnDate 购进退出日期
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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
     * 退货类型（0-质量问题退货；1-非质量问题退货）
     * @return return_type 退货类型（0-质量问题退货；1-非质量问题退货）
     */
    public Integer getReturnType() {
        return returnType;
    }

    /**
     * 退货类型（0-质量问题退货；1-非质量问题退货）
     * @param returnType 退货类型（0-质量问题退货；1-非质量问题退货）
     */
    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
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
     * 联系电话
     * @return tel_phone 联系电话
     */
    public String getTelPhone() {
        return telPhone;
    }

    /**
     * 联系电话
     * @param telPhone 联系电话
     */
    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone == null ? null : telPhone.trim();
    }

    /**
     * 退货人员ID
     * @return return_man_id 退货人员ID
     */
    public Long getReturnManId() {
        return returnManId;
    }

    /**
     * 退货人员ID
     * @param returnManId 退货人员ID
     */
    public void setReturnManId(Long returnManId) {
        this.returnManId = returnManId;
    }

    /**
     * 退货人员编码
     * @return return_man_code 退货人员编码
     */
    public String getReturnManCode() {
        return returnManCode;
    }

    /**
     * 退货人员编码
     * @param returnManCode 退货人员编码
     */
    public void setReturnManCode(String returnManCode) {
        this.returnManCode = returnManCode == null ? null : returnManCode.trim();
    }

    /**
     * 退货人员名称
     * @return return_man_name 退货人员名称
     */
    public String getReturnManName() {
        return returnManName;
    }

    /**
     * 退货人员名称
     * @param returnManName 退货人员名称
     */
    public void setReturnManName(String returnManName) {
        this.returnManName = returnManName == null ? null : returnManName.trim();
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
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
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
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
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
        sb.append(", orderType=").append(orderType);
        sb.append(", code=").append(code);
        sb.append(", returnDate=").append(returnDate);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", returnType=").append(returnType);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", telPhone=").append(telPhone);
        sb.append(", returnManId=").append(returnManId);
        sb.append(", returnManCode=").append(returnManCode);
        sb.append(", returnManName=").append(returnManName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", wholeDiscountAmount=").append(wholeDiscountAmount);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", notaxRealAmountTotal=").append(notaxRealAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
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


	public Integer getCarriageMode() {
		return carriageMode;
	}


	public void setCarriageMode(Integer carriageMode) {
		this.carriageMode = carriageMode;
	}


	public Integer getChainType() {
		return chainType;
	}


	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public Long getSettlementUnitId() {
		return settlementUnitId;
	}

	public void setSettlementUnitId(Long settlementUnitId) {
		this.settlementUnitId = settlementUnitId;
	}
    
}