package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author dongdong.zhang
 * 2017-10-12
 */
@ApiModel
public class RequsetDistrReturnInSaveOrUpdateVO implements Serializable {
	
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "配进退出单头id,修改时需要传递,新增不需要")
    private Long id;

    /**
     * 配进退出日期
     */
    @ApiModelProperty(value = "配进退出日期" ,required = true)
    private Date inReturnDate;

    /**
     * 配进入库单id
     */
    @ApiModelProperty(value = " 配进入库单id,若不是调用的配进入库的单据则不用传该参数")
    private Long baseOrderId;

    /**
     * 退货人员ID
     */
    @ApiModelProperty(value = "退货人员ID" ,required = true)
    private Long returnManId;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "折扣",required = true)
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额",required = true)
    private BigDecimal wholeDiscountAmount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 明细行删除id集合
     */
    @ApiModelProperty(value = "明细行删除id,修改时需要传递,新增不需要" ,required = false)
    private List<Long> deleteDetailIds;

    /**
     * 配进退出单明细行
     */
    @ApiModelProperty(value = "配进退出单明细行" ,required = false)
    private List<RequsetDistrReturnDtlSaveOrUpdateVO> requsetDistrReturnDtlSaveOrUpdateVO;
    
    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）(新增或调用新增时必填，修改时不用)")
    private Integer distrType;
    
    /**
   	 * 出库单位ID
   	 */
   	@ApiModelProperty(value = "供货单位ID(直调配送),调入单位ID(门店间调剂)")
   	private Long outboundUnitId;


   	public static RequsetDistrReturnInSaveOrUpdateVO getRequsetDistrReturnInSaveOrUpdateVO(DistrInReturnOutAddFormVO distrInReturnOutAddFormVO){

		RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO = new RequsetDistrReturnInSaveOrUpdateVO();

		requsetDistrReturnInSaveOrUpdateVO.setBaseOrderId(distrInReturnOutAddFormVO.getBaseOrderId());
		/**
		 * 配进退出日期
		 */
		requsetDistrReturnInSaveOrUpdateVO.setInReturnDate(distrInReturnOutAddFormVO.getReturnDate());

		/**
		 * 退货人员ID
		 */
		requsetDistrReturnInSaveOrUpdateVO.setReturnManId(distrInReturnOutAddFormVO.getReturnManId());

		/**
		 * 整单折扣（%）
		 */
		requsetDistrReturnInSaveOrUpdateVO.setWholeDiscount(distrInReturnOutAddFormVO.getWholeDiscount());

		/**
		 * 整单优惠金额
		 */
		requsetDistrReturnInSaveOrUpdateVO.setWholeDiscountAmount(distrInReturnOutAddFormVO.getWholeDiscountAmount());

		/**
		 * 备注
		 */
		requsetDistrReturnInSaveOrUpdateVO.setRemark(distrInReturnOutAddFormVO.getRemark());

		/**
		 * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
		 */
		requsetDistrReturnInSaveOrUpdateVO.setDistrType(distrInReturnOutAddFormVO.getDistrType());
		if(null != distrInReturnOutAddFormVO.getBaseOrderId()){
			requsetDistrReturnInSaveOrUpdateVO.setBaseOrderId(distrInReturnOutAddFormVO.getBaseOrderId());
		}


		/**
		 * 出库单位ID
		 */
		if(DistrType.SWAP_BETWEEN_STORES.getCode() == distrInReturnOutAddFormVO.getDistrType()
				|| (DistrType.DIRECT_DISTRIBUTION.getCode() == distrInReturnOutAddFormVO.getDistrType())){
			requsetDistrReturnInSaveOrUpdateVO.setOutboundUnitId(distrInReturnOutAddFormVO.getDistrUnitId());
		}

/*

		if(DistrType.PARENT_DISTR.getCode() == distrInReturnOutAddFormVO.getDistrType()){
			*//**
			 * 总部配送
			 *//*
			requsetDistrReturnInSaveOrUpdateVO.setOutboundUnitId(distrInReturnOutAddFormVO.getDistrUnitId());

		}else if(DistrType.BRANCH_ADJUSTMENT.getCode() == distrInReturnOutAddFormVO.getDistrType()){
			*//**
			 * 分店间调剂
			 *//*
			requsetDistrReturnInSaveOrUpdateVO.setOutboundUnitId(distrInReturnOutAddFormVO.getDistrUnitId());

		}else if(DistrType.DIRECT_DISTR.getCode() == distrInReturnOutAddFormVO.getDistrType()){
			*//**
			 * 直接配送
			 *//*
			requsetDistrReturnInSaveOrUpdateVO.setOutboundUnitId(distrInReturnOutAddFormVO.getDistrUnitId());

		}*/

		return requsetDistrReturnInSaveOrUpdateVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInReturnDate() {
		return inReturnDate;
	}

	public void setInReturnDate(Date inReturnDate) {
		this.inReturnDate = inReturnDate;
	}

	public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public Long getReturnManId() {
		return returnManId;
	}

	public void setReturnManId(Long returnManId) {
		this.returnManId = returnManId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark==null?null:remark;
	}

	public List<Long> getDeleteDetailIds() {
		return deleteDetailIds;
	}

	public void setDeleteDetailIds(List<Long> deleteDetailIds) {
		this.deleteDetailIds = deleteDetailIds;
	}

	public List<RequsetDistrReturnDtlSaveOrUpdateVO> getRequsetDistrReturnDtlSaveOrUpdateVO() {
		return requsetDistrReturnDtlSaveOrUpdateVO;
	}

	public void setRequsetDistrReturnDtlSaveOrUpdateVO(
			List<RequsetDistrReturnDtlSaveOrUpdateVO> requsetDistrReturnDtlSaveOrUpdateVO) {
		this.requsetDistrReturnDtlSaveOrUpdateVO = requsetDistrReturnDtlSaveOrUpdateVO;
	}

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}

	public Long getOutboundUnitId() {
		return outboundUnitId;
	}

	public void setOutboundUnitId(Long outboundUnitId) {
		this.outboundUnitId = outboundUnitId;
	}

}