package com.rograndec.feijiayun.chain.inf.pos.special.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionDetailVO;

public class POSSpecialRegisterVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月7日 下午1:44:28 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
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
     * 开具日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;
    
    /**
     * 处方单号
     */
    @ApiModelProperty(value = "单号")
    private String code;
    
    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;
    
    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;
    
    /**
     * 会员姓名
     */
    @ApiModelProperty(value = "会员姓名")
    private String memberName = "";
    
    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private BigDecimal currentIntegral = BigDecimal.ZERO;
    
    /**
     * 购药者姓名
     */
    @ApiModelProperty(value = "购药者姓名")
    private String consumerName;
    
    /**
     * 性别（0-女；1-男）
     */
    @ApiModelProperty(value = "性别（0-女；1-男）")
    private Integer sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;
    
    
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;
    
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;
    
    @ApiModelProperty(value = "整单折扣")
    private BigDecimal wholeDiscount;
    
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;
    
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;
    
    /**
     * 处方单药品明细
     */
    @ApiModelProperty(value = "处方单明细")
    private List<PrescriptionDetailVO> detailList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public List<PrescriptionDetailVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PrescriptionDetailVO> detailList) {
		this.detailList = detailList;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
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

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public BigDecimal getCurrentIntegral() {
		return currentIntegral;
	}

	public void setCurrentIntegral(BigDecimal currentIntegral) {
		this.currentIntegral = currentIntegral;
	}
	
}
