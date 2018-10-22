package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import com.google.common.collect.Lists;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * saas_purchase_return
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-15
 */
@ApiModel
public class PurchaseReturnSaveOrUpdateVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "购进退出单头id,修改时需要传递,新增不需要")
    private Long id;

    @ApiModelProperty(value = "草稿id")
    private String redisKeyValue;
    /**
     * 购进退出日期
     */
    @ApiModelProperty(value = "购进退出日期" ,required = true)
    @NotNull(message = "购进退出日期不能为空")
    private Date returnDate;

    /**
     * 采购入库单id
     */
    @ApiModelProperty(value = "采购入库单id")
    private Long baseOrderId;


    /**
     * 退货类型（0-质量问题退货；1-非质量问题退货）
     */
    @ApiModelProperty(value = "退货类型（0-质量问题退货；1-非质量问题退货）" ,required = true)
    @NotNull(message = "退货类型不能为空")
    private Integer returnType;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID" ,required = true)
    @NotNull(message = "供货单位ID不能为空")
    private Long supplierId;

    @ApiModelProperty(value = "供货单位名称" ,required = true)
    private String supplierName;

    @ApiModelProperty(value = "供货单位编码" ,required = true)
    private String supplierCode;

//    /**
//     * 联系电话
//     */
//    @ApiModelProperty(value = "联系电话")
//    private String telPhone;

    /**
     * 退货人员ID
     */
    @NotNull(message = "退货人员ID不能为空")
    @ApiModelProperty(value = "退货人员ID" ,required = true)
    private Long returnManId;

    @ApiModelProperty(value = "退货人员名称" ,required = true)
    private String returnManName;


    @ApiModelProperty(value = "供货单位销售人员"  ,required = true)
    @NotNull(message = "供货单位销售人员ID不能为空")
    private Long supplierSalerId;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）",required = true)
    @NotNull(message = "整单折扣不能为空")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额",required = true)
    @NotNull(message = "整单优惠金额不能为空")
    private BigDecimal wholeDiscountAmount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "明细行删除id,修改时需要传递,新增不需要" ,required = false)
    private List<Long> deleteDetailIds;

    @ApiModelProperty(value = "购进退出单明细行" ,required = false)
    @NotNull(message = "购进退出单明细行不能为空")
    @Size(min = 1 ,message = "购进退出单明细行")
    private List<PurchaseReturnDetailSaveOrUpdateVO> purchaseReturnDetailSaveOrUpdateVOS;

    @ApiModelProperty(value = "购进退出单配送和结算信息" ,required = false)
    @NotNull(message = "购进退出单配送和结算信息不能为空")
    private PurchaseReturnOtherSaveOrUpdateVO purchaseReturnOtherSaveOrUpdateVO;

    // ------ ↓↓↓↓↓↓↓↓↓↓↓↓ 草稿需要字段 ↓↓↓↓↓↓↓↓↓-------------


    /**
     * 购进退出单号
     */
    @ApiModelProperty(value = "购进退出单号")
    private String code;


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
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String telPhone;


    /**
     * 退货人员编码
     */
    @ApiModelProperty(value = "退货人员编码")
    private String returnManCode;


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


    // ------ ↑↑↑↑↑↑↑↑↑↑↑↑ 草稿需要字段 ↑↑↑↑↑↑↑↑↑-------------

    public static PurchaseReturnSaveOrUpdateVO getPurchaseReturnSaveOrUpdateVO(DistrReturnInVO distrReturnInVO){

        PurchaseReturnSaveOrUpdateVO purchaseReturnSaveOrUpdateVO = new PurchaseReturnSaveOrUpdateVO();

        DistrReturnIn distrReturnIn = distrReturnInVO.getDistrReturnIn();
       /* List<DistrReturnInDetail> distrReturnInDetails = distrReturnInVO.getDistrReturnInDetails();*/
        List<DistrReturnInShelf> distrReturnInShelfs = distrReturnInVO.getDistrReturnInShelfs();

       /* Map<Long, List<DistrInReturnOutShelf>> shelfsGoodsMap = distrReturnInVO.getShelfsGoodsMap();*/

        Map<String, Long> goodsLotNumMap = distrReturnInVO.getGoodsLotNumMap();


        /**
         * 购进退出日期
         */
        purchaseReturnSaveOrUpdateVO.setReturnDate(new Date());

        /**
         * 退货类型（0-质量问题退货；1-非质量问题退货）
         */
        purchaseReturnSaveOrUpdateVO.setReturnType(1);

        /**
         * 供货单位ID
         */
        purchaseReturnSaveOrUpdateVO.setSupplierId(distrReturnInVO.getSupplier().getId());

        /**
         * 退货人员ID
         */
        purchaseReturnSaveOrUpdateVO.setReturnManId(distrReturnInVO.getReturnManId());

        /**
         * 供货单位销售人员
         */
        purchaseReturnSaveOrUpdateVO.setSupplierSalerId(distrReturnInVO.getSupplier().getSaleManId());

        /**
         * 整单折扣（%）
         */
        purchaseReturnSaveOrUpdateVO.setWholeDiscount(distrReturnIn.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        purchaseReturnSaveOrUpdateVO.setWholeDiscountAmount(distrReturnIn.getWholeDiscountAmount());

        List<PurchaseReturnDetailSaveOrUpdateVO> purchaseReturnDetailSaveOrUpdateVOS = distrReturnInShelfs.stream().map(distrReturnInDetail -> {

            PurchaseReturnDetailSaveOrUpdateVO purchaseReturnDetailSaveOrUpdateVO = new PurchaseReturnDetailSaveOrUpdateVO();
            /**
             * 商品ID
             */
            purchaseReturnDetailSaveOrUpdateVO.setGoodsId(distrReturnInDetail.getGoodsId());

            /**
             * 数量
             */
            purchaseReturnDetailSaveOrUpdateVO.setQuantity(distrReturnInDetail.getQuantity());

            /**
             * 单价
             */
            purchaseReturnDetailSaveOrUpdateVO.setUnitPrice(distrReturnInDetail.getUnitPrice());

            /**
             * 行折扣（%）
             */
            purchaseReturnDetailSaveOrUpdateVO.setLineDiscount(distrReturnInDetail.getLineDiscount());

            /**
             * 进项税
             */
            purchaseReturnDetailSaveOrUpdateVO.setTaxRateId(distrReturnInDetail.getTaxRateId());

            /**
             * 退货原因ID集合，多个用逗号隔开
             */
            ArrayList<Integer> returnReasonIds = Lists.newArrayList(distrReturnInVO.getQualitySetId().intValue());
            purchaseReturnDetailSaveOrUpdateVO.setReturnReasonIds(returnReasonIds);
            StringBuffer sb = new StringBuffer();
            sb.append(distrReturnInDetail.getGoodsId()).append("_").append(distrReturnInDetail.getLotNumber());
            Long lotId = goodsLotNumMap.get(sb.toString());
            purchaseReturnDetailSaveOrUpdateVO.setLotNumberId(lotId);
        /*    String lotNum = goodsLotMap.get(distrReturnInDetail.getGoodsId());
            if(!StringUtils.isEmpty(lotNum)){
                StringBuffer sb = new StringBuffer();
                sb.append(distrReturnInDetail.getGoodsId()).append("_").append(lotNum);
                Long lotId = goodsLotNumMap.get(sb.toString());
                purchaseReturnDetailSaveOrUpdateVO.setLotNumberId(lotId);
            }*/

            /**
             * 行号
             */
            purchaseReturnDetailSaveOrUpdateVO.setLineNum(distrReturnInDetail.getLineNum());

            return purchaseReturnDetailSaveOrUpdateVO;

        }).collect(Collectors.toList());

        purchaseReturnSaveOrUpdateVO.setPurchaseReturnDetailSaveOrUpdateVOS(purchaseReturnDetailSaveOrUpdateVOS);

        EnterpriseBusiness bus = distrReturnInVO.getBus();

        PurchaseReturnOtherSaveOrUpdateVO purchaseReturnOtherSaveOrUpdateVO = new PurchaseReturnOtherSaveOrUpdateVO();

        purchaseReturnSaveOrUpdateVO.setPurchaseReturnOtherSaveOrUpdateVO(purchaseReturnOtherSaveOrUpdateVO);
        /**
         * 结算方式（0-现结；1-账期）
         */
        purchaseReturnOtherSaveOrUpdateVO.setSettlementType(bus.getSettlementMode());

        UserVO sourceUser = distrReturnInVO.getSourceUser();
        /**
         *  承运单位默认供货单位，自营店结算单位默认是总部，加盟店计算单位默认是加盟店，发货单位默认是自营店或加盟店，收货单位默认是供货单位
         */
        Long settlementUnitId = sourceUser.getEnterpriseId();
        String settlementUnit = sourceUser.getEnterpriseName();
        Enterprise enterprise = distrReturnInVO.getEnterprise();
        Enterprise parentEnterprise = distrReturnInVO.getParentEnterprise();
        String settlementUnitPhone = enterprise.getTel();
        String settlementUnitAddress = enterprise.getCompanyAddress();
        Integer carriageMode = 4;
        if(sourceUser.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            /**
             * 自营店结算单位默认是总部，加盟店计算单位默认是加盟店
             */
            settlementUnitId = sourceUser.getParentId();
            settlementUnit = sourceUser.getParentName();
            settlementUnitPhone = parentEnterprise.getTel();
            settlementUnitAddress = parentEnterprise.getCompanyAddress();
            carriageMode = 0;
        }
        /**
         * 结算单位ID
         */
        purchaseReturnOtherSaveOrUpdateVO.setSettlementUnitId(settlementUnitId);

        /**
         * 结算单位
         */
        purchaseReturnOtherSaveOrUpdateVO.setSettlementUnit(settlementUnit);

        /**
         * 结算单位电话
         */
        purchaseReturnOtherSaveOrUpdateVO.setSettlementUnitPhone(settlementUnitPhone);

        /**
         * 结算单位地址
         */
        purchaseReturnOtherSaveOrUpdateVO.setSettlementUnitAddress(settlementUnitAddress);


        /**
         * 承运方式（0-配送；1-委托运输；2-自提）注：承运方式改名为  配货类型   0-总部配送；1-委托运输；2-自提  ；4-直调配送
         */
        purchaseReturnOtherSaveOrUpdateVO.setCarriageMode(carriageMode);

        /**
         * 承运单位
         */
        purchaseReturnOtherSaveOrUpdateVO.setCarriageUnit(distrReturnInVO.getSupplier().getName());

        /**
         * 收货单位
         */
        purchaseReturnOtherSaveOrUpdateVO.setReceiveUnit(distrReturnInVO.getSupplier().getName());

        return purchaseReturnSaveOrUpdateVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }


    public Long getReturnManId() {
        return returnManId;
    }

    public void setReturnManId(Long returnManId) {
        this.returnManId = returnManId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getDeleteDetailIds() {
        return deleteDetailIds;
    }

    public void setDeleteDetailIds(List<Long> deleteDetailIds) {
        this.deleteDetailIds = deleteDetailIds;
    }

    public List<PurchaseReturnDetailSaveOrUpdateVO> getPurchaseReturnDetailSaveOrUpdateVOS() {
        return purchaseReturnDetailSaveOrUpdateVOS;
    }

    public void setPurchaseReturnDetailSaveOrUpdateVOS(List<PurchaseReturnDetailSaveOrUpdateVO> purchaseReturnDetailSaveOrUpdateVOS) {
        this.purchaseReturnDetailSaveOrUpdateVOS = purchaseReturnDetailSaveOrUpdateVOS;
    }

    public PurchaseReturnOtherSaveOrUpdateVO getPurchaseReturnOtherSaveOrUpdateVO() {
        return purchaseReturnOtherSaveOrUpdateVO;
    }

    public void setPurchaseReturnOtherSaveOrUpdateVO(PurchaseReturnOtherSaveOrUpdateVO purchaseReturnOtherSaveOrUpdateVO) {
        this.purchaseReturnOtherSaveOrUpdateVO = purchaseReturnOtherSaveOrUpdateVO;
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

    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }


    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getReturnManName() {
        return returnManName;
    }

    public void setReturnManName(String returnManName) {
        this.returnManName = returnManName;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }



    // -----------  草稿 ---------------

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getReturnManCode() {
        return returnManCode;
    }

    public void setReturnManCode(String returnManCode) {
        this.returnManCode = returnManCode;
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

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
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
}