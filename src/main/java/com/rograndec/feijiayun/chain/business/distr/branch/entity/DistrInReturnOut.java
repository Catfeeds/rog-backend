package com.rograndec.feijiayun.chain.business.distr.branch.entity;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrAuditType;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutAuditFormVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutFormVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer.DistrInReturnOutParam;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInReturnStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * saas_distr_in_return_out
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-10
 */
public class DistrInReturnOut implements Serializable {
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
     * 单据类型（5422）
     */
    @ApiModelProperty(value = "单据类型（5422）")
    private Integer orderType;

    /**
     * 配进退出出库单号
     */
    @ApiModelProperty(value = "配进退出出库单号")
    private String code;

    /**
     * 配进退出出库日期
     */
    @ApiModelProperty(value = "配进退出出库日期")
    private Date outDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
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
     * 配货单位ID
     */
    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    /**
     * 配货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 配货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 配货类型（0-总部配送；1-分店间调剂；2-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；1-分店间调剂；2-直调配送）")
    private Integer distrType;

    /**
     * 出库人员ID
     */
    @ApiModelProperty(value = "出库人员ID")
    private Long outManId;

    /**
     * 出库人员编码
     */
    @ApiModelProperty(value = "出库人员编码")
    private String outManCode;

    /**
     * 出库人员名称
     */
    @ApiModelProperty(value = "出库人员名称")
    private String outManName;


    /**
     * 复核人员ID
     */
    @ApiModelProperty(value = "复核人员ID")
    private Long auditManId;

    /**
     * 出库人员编码
     */
    @ApiModelProperty(value = "复核人员编码")
    private String auditManCode;

    /**
     * 出库人员名称
     */
    @ApiModelProperty(value = "复核人员名称")
    private String auditManName;

    @ApiModelProperty(value = "复核原因")
    private String auditReason;

    @ApiModelProperty(value = "复核时间")
    private Date auditDate;

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
	 * 出库单位ID
	 */
	@ApiModelProperty(value = "出库单位ID")
	private Long outboundUnitId;

	/**
	 * 出库位单位编码
	 */
	@ApiModelProperty(value = "出库位单位编码 ")
	private String outboundUnitCode;

	/**
	 * 出库单位名称
	 */
	@ApiModelProperty(value = "出库单位名称")
	private String outboundUnitName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public static List<Long> getIds(List<DistrInReturnOut> DistrInReturnOuts){

        List<Long> collect = DistrInReturnOuts.stream().map(distrInReturnOut -> distrInReturnOut.getId()).collect(Collectors.toList());

        return collect;
    }

    public static DistrInReturnOut setAudit(UserVO userVO,DistrInReturnOut distrInReturnOut,DistrInReturnOutAuditFormVO distrInReturnOutAuditFormVO){

        distrInReturnOut.setId(distrInReturnOutAuditFormVO.getId());
        distrInReturnOut.setAuditManId(userVO.getUserId());
        distrInReturnOut.setAuditManCode(userVO.getUserCode());
        distrInReturnOut.setAuditManName(userVO.getUserName());
        distrInReturnOut.setAuditReason(distrInReturnOutAuditFormVO.getAuditReason());
        distrInReturnOut.setAuditDate(new Date());
        if(distrInReturnOutAuditFormVO.getAudit() == DistrAuditType.REFUSE.getCode()){
            /**
             * 复核被驳回后,为出库中状态,可以编辑
             */
            distrInReturnOut.setStatus(DistrInReturnStatus.AUDIT_REBUT);
        }else {
        	Integer chainType = userVO.getChainType();
        	Integer distrType = distrInReturnOut.getDistrType();
        	if(distrType != null) {
        		//直调配送
        		if(DistributionType.DIRECT_DISTRIBUTION.getCode() == distrType) {
        			//自营店已完成
        			if(ChainType.Selfoperatedshop.getCode() == chainType) {
        				distrInReturnOut.setStatus(DistrInReturnStatus.FINISHED);
        			}else if(ChainType.Division.getCode() == chainType) {
        				distrInReturnOut.setStatus(DistrInReturnStatus.WAIT_BILL);
        			}
        		}else if(DistributionType.DISTRIBUTION_HEAD.getCode() == distrType) {//总部配送
        			//自营店已完成
        			if(ChainType.Selfoperatedshop.getCode() == chainType) {
        				distrInReturnOut.setStatus(DistrInReturnStatus.FINISHED);
        			}else if(ChainType.Division.getCode() == chainType) {
        				distrInReturnOut.setStatus(DistrInReturnStatus.WAIT_BILL);
        			}
        		}else if(DistributionType.SWAP_BETWEEN_STORES.getCode() == distrType){//门店间调剂
        			//自营店已完成
        			if(ChainType.Selfoperatedshop.getCode() == chainType) {
        				distrInReturnOut.setStatus(DistrInReturnStatus.FINISHED);
        			}
        		}
        	}
        }

        return distrInReturnOut;
    }

    public static DistrInReturnOut afterGenerateDetailSetReturnOutEnd(DistrInReturnOut distrInReturnOut,List<DistrInReturnOutDetail> distrInReturnOutDetails){

        BigDecimal notaxRealAmountTotal = new BigDecimal(0);

        BigDecimal taxAmountTotal = new BigDecimal(0);

        for(DistrInReturnOutDetail drid : distrInReturnOutDetails){
            notaxRealAmountTotal = notaxRealAmountTotal.add(drid.getNotaxRealAmount());
            taxAmountTotal = taxAmountTotal.add(drid.getTaxAmount());

        };

        /**
         * 不含税金额合计
         */
        distrInReturnOut.setNotaxRealAmountTotal(notaxRealAmountTotal);

        /**
         * 税额合计
         */
        distrInReturnOut.setTaxAmountTotal(taxAmountTotal);

        return distrInReturnOut;
    }


    public static DistrInReturnOut afterGenerateDetailSetReturnOut(DistrInReturnOut distrInReturnOut, List<DistrInReturnOutDetail> distrInReturnOutDetails){
        BigDecimal amountTotal = new BigDecimal(0);

        for(DistrInReturnOutDetail drid : distrInReturnOutDetails) {
            amountTotal =  amountTotal.add(drid.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);

        }

        /**
         * 金额合计（整单优惠前的金额合计）
         */
        distrInReturnOut.setAmountTotal(amountTotal);


        /**
         * 实际金额合计
         */
        BigDecimal realAmountTotal = calcTotalRealAmount(
                distrInReturnOut.getAmountTotal()
                , distrInReturnOut.getWholeDiscount()
                , distrInReturnOut.getWholeDiscountAmount()
        );
        distrInReturnOut.setRealAmountTotal(realAmountTotal);

        return distrInReturnOut;

    }


    public static DistrInReturnOut getDistrInReturnOut(DistrInReturnOutParam distrInReturnOutParam) throws Exception {

        DistrInReturnOut distrInReturnOut = new DistrInReturnOut();

        UserVO userVO = distrInReturnOutParam.getUserVO();

        DistrInReturn distrInReturn = distrInReturnOutParam.getDistrInReturn();

        String code = distrInReturnOutParam.getCode();

        User outMan = distrInReturnOutParam.getOutMan();

        DistrInReturnOutFormVO distrInReturnOutFormVO = distrInReturnOutParam.getDistrInReturnOutFormVO();

        distrInReturnOut.setId(distrInReturnOutFormVO.getId());
        /**
         * 企业ID
         */
        distrInReturnOut.setEnterpriseId(userVO.getEnterpriseId());
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            distrInReturnOut.setParentId(0L);
        }else {
            distrInReturnOut.setParentId(userVO.getParentId());
        }

        /**
         * 单据类型（5422）
         */
        distrInReturnOut.setOrderType(OrderRule.DISTR_IN_RETURN_OUT.getType());

        /**
         * 配进退出出库单号
         */
        distrInReturnOut.setCode(code);

        /**
         * 配进退出出库日期
         */
        distrInReturnOut.setOutDate(distrInReturnOutFormVO.getOutDate());

        /**
         * 基础单据ID
         */
        distrInReturnOut.setBaseOrderId(distrInReturn.getId());

        /**
         * 基础单据类型
         */
        distrInReturnOut.setBaseOrderType(distrInReturn.getOrderType());

        /**
         * 基础单据编码
         */
        distrInReturnOut.setBaseOrderCode(distrInReturn.getCode());

        /**
         * 基础单据日期
         */
        distrInReturnOut.setBaseOrderDate(distrInReturn.getInReturnDate());

        /**
         * 配货单位ID
         */
        distrInReturnOut.setDistrUnitId(distrInReturn.getDistrUnitId());

        /**
         * 配货单位编码
         */
        distrInReturnOut.setDistrUnitCode(distrInReturn.getDistrUnitCode());

        /**
         * 配货单位名称
         */
        distrInReturnOut.setDistrUnitName(distrInReturn.getDistrUnitName());

        distrInReturnOut.setOutboundUnitId(distrInReturn.getOutboundUnitId());
        distrInReturnOut.setOutboundUnitCode(distrInReturn.getOutboundUnitCode());
        distrInReturnOut.setOutboundUnitName(distrInReturn.getOutboundUnitName());

        /**
         * 配货类型（0-总部配送；1-分店间调剂；2-直调配送）
         */
        distrInReturnOut.setDistrType(distrInReturn.getDistrType());

        /**
         * 出库人员ID
         */
        distrInReturnOut.setOutManId(outMan.getId());

        /**
         * 出库人员编码
         */
        distrInReturnOut.setOutManCode(outMan.getCode());

        /**
         * 出库人员名称
         */
        distrInReturnOut.setOutManName(outMan.getName());

        /**
         * 数量合计
         */
        distrInReturnOut.setQuantityTotal(distrInReturn.getQuantityTotal());

        /**
         * 品种数量
         */
        distrInReturnOut.setVarietiesQuantity(distrInReturn.getVarietiesQuantity());

        /**
         * 整单折扣（%）
         */
        distrInReturnOut.setWholeDiscount(distrInReturnOutFormVO.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        distrInReturnOut.setWholeDiscountAmount(distrInReturnOutFormVO.getWholeDiscountAmount());

        /**
         * 状态
         */
        distrInReturnOut.setStatus(DistrInReturnStatus.WAIT_AUDIT);

        /**
         * 备注
         */
        distrInReturnOut.setRemark(distrInReturnOutFormVO.getRemark());

        boolean isAdd = false;

        if(distrInReturnOutFormVO.getId() == null)
            isAdd = true;

        UserEnterpriseUtils.setUserCreateOrModify(distrInReturnOut,userVO,isAdd);
        return distrInReturnOut;
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
     * saas_distr_in_return_out
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
     * 单据类型（5422）
     * @return order_type 单据类型（5422）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5422）
     * @param orderType 单据类型（5422）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 配进退出出库单号
     * @return code 配进退出出库单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 配进退出出库单号
     * @param code 配进退出出库单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 配进退出出库日期
     * @return out_date 配进退出出库日期
     */
    public Date getOutDate() {
        return outDate;
    }

    /**
     * 配进退出出库日期
     * @param outDate 配进退出出库日期
     */
    public void setOutDate(Date outDate) {
        this.outDate = outDate;
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
     * 配货单位ID
     * @return distr_unit_id 配货单位ID
     */
    public Long getDistrUnitId() {
        return distrUnitId;
    }

    /**
     * 配货单位ID
     * @param distrUnitId 配货单位ID
     */
    public void setDistrUnitId(Long distrUnitId) {
        this.distrUnitId = distrUnitId;
    }

    /**
     * 配货单位编码
     * @return distr_unit_code 配货单位编码
     */
    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    /**
     * 配货单位编码
     * @param distrUnitCode 配货单位编码
     */
    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode == null ? null : distrUnitCode.trim();
    }

    /**
     * 配货单位名称
     * @return distr_unit_name 配货单位名称
     */
    public String getDistrUnitName() {
        return distrUnitName;
    }

    /**
     * 配货单位名称
     * @param distrUnitName 配货单位名称
     */
    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName == null ? null : distrUnitName.trim();
    }

    /**
     * 配货类型（0-总部配送；1-分店间调剂；2-直调配送）
     * @return distr_type 配货类型（0-总部配送；1-分店间调剂；2-直调配送）
     */
    public Integer getDistrType() {
        return distrType;
    }

    /**
     * 配货类型（0-总部配送；1-分店间调剂；2-直调配送）
     * @param distrType 配货类型（0-总部配送；1-分店间调剂；2-直调配送）
     */
    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    /**
     * 出库人员ID
     * @return out_man_id 出库人员ID
     */
    public Long getOutManId() {
        return outManId;
    }

    /**
     * 出库人员ID
     * @param outManId 出库人员ID
     */
    public void setOutManId(Long outManId) {
        this.outManId = outManId;
    }

    /**
     * 出库人员编码
     * @return out_man_code 出库人员编码
     */
    public String getOutManCode() {
        return outManCode;
    }

    /**
     * 出库人员编码
     * @param outManCode 出库人员编码
     */
    public void setOutManCode(String outManCode) {
        this.outManCode = outManCode == null ? null : outManCode.trim();
    }

    /**
     * 出库人员名称
     * @return out_man_name 出库人员名称
     */
    public String getOutManName() {
        return outManName;
    }

    /**
     * 出库人员名称
     * @param outManName 出库人员名称
     */
    public void setOutManName(String outManName) {
        this.outManName = outManName == null ? null : outManName.trim();
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
        sb.append(", outDate=").append(outDate);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", distrUnitId=").append(distrUnitId);
        sb.append(", distrUnitCode=").append(distrUnitCode);
        sb.append(", distrUnitName=").append(distrUnitName);
        sb.append(", distrType=").append(distrType);
        sb.append(", outManId=").append(outManId);
        sb.append(", outManCode=").append(outManCode);
        sb.append(", outManName=").append(outManName);
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

    public Long getAuditManId() {
        return auditManId;
    }

    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }

    public String getAuditManCode() {
        return auditManCode;
    }

    public void setAuditManCode(String auditManCode) {
        this.auditManCode = auditManCode;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

	public Long getOutboundUnitId() {
		return outboundUnitId;
	}

	public void setOutboundUnitId(Long outboundUnitId) {
		this.outboundUnitId = outboundUnitId;
	}

	public String getOutboundUnitCode() {
		return outboundUnitCode;
	}

	public void setOutboundUnitCode(String outboundUnitCode) {
		this.outboundUnitCode = outboundUnitCode;
	}

	public String getOutboundUnitName() {
		return outboundUnitName;
	}

	public void setOutboundUnitName(String outboundUnitName) {
		this.outboundUnitName = outboundUnitName;
	}


}